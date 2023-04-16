package com.facebook.appevents.p011ml;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.appevents.restrictivedatafilter.AddressFilterManager;
import com.facebook.appevents.suggestedevents.SuggestedEventsManager;
import com.facebook.internal.FeatureManager;
import com.facebook.internal.Utility;
import com.facebook.share.internal.ShareConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* renamed from: com.facebook.appevents.ml.ModelManager */
public final class ModelManager {
    private static final String[] APP_SETTING_FIELDS = {"version_id", "asset_uri", "use_case", "thresholds", "rules_uri"};
    private static final String CACHE_KEY_MODELS = "models";
    public static final String MODEL_ADDRESS_DETECTION = "DATA_DETECTION_ADDRESS";
    private static final String MODEL_ASSERT_STORE = "com.facebook.internal.MODEL_STORE";
    public static final String MODEL_SUGGESTED_EVENTS = "SUGGEST_EVENT";
    private static final String SDK_MODEL_ASSET = "%s/model_asset";
    /* access modifiers changed from: private */
    public static final ConcurrentMap<String, Model> models = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public static SharedPreferences shardPreferences;

    public static void enable() {
        initialize();
    }

    public static void initialize() {
        shardPreferences = FacebookSdk.getApplicationContext().getSharedPreferences(MODEL_ASSERT_STORE, 0);
        initializeModels();
    }

    private static void initializeModels() {
        Utility.runOnNonUiThread(new Runnable() {
            public void run() {
                try {
                    JSONObject access$000 = ModelManager.fetchFromServer();
                    if (access$000 != null) {
                        ModelManager.shardPreferences.edit().putString(ModelManager.CACHE_KEY_MODELS, access$000.toString()).apply();
                    } else {
                        access$000 = new JSONObject(ModelManager.shardPreferences.getString(ModelManager.CACHE_KEY_MODELS, ""));
                    }
                    ModelManager.addModelsFromModelJson(access$000);
                    ModelManager.enableSuggestedEvents();
                    ModelManager.enablePIIFiltering();
                } catch (Exception unused) {
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static void addModelsFromModelJson(JSONObject jSONObject) {
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                String next = keys.next();
                Model jsonObjectToModel = jsonObjectToModel(jSONObject.getJSONObject(next));
                if (jsonObjectToModel != null) {
                    models.put(next, jsonObjectToModel);
                }
            } catch (JSONException unused) {
                return;
            }
        }
    }

    private static JSONObject parseRawJsonObject(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(ShareConstants.WEB_DIALOG_PARAM_DATA);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("version_id", jSONObject3.getString("version_id"));
                jSONObject4.put("use_case", jSONObject3.getString("use_case"));
                jSONObject4.put("thresholds", jSONObject3.getJSONArray("thresholds"));
                jSONObject4.put("asset_uri", jSONObject3.getString("asset_uri"));
                if (jSONObject3.has("rules_uri")) {
                    jSONObject4.put("rules_uri", jSONObject3.getString("rules_uri"));
                }
                jSONObject2.put(jSONObject3.getString("use_case"), jSONObject4);
            }
            return jSONObject2;
        } catch (JSONException unused) {
            return new JSONObject();
        }
    }

    /* access modifiers changed from: private */
    @Nullable
    public static JSONObject fetchFromServer() {
        ArrayList arrayList = new ArrayList(Arrays.asList(APP_SETTING_FIELDS));
        Bundle bundle = new Bundle();
        bundle.putString(GraphRequest.FIELDS_PARAM, TextUtils.join(",", arrayList));
        GraphRequest newGraphPathRequest = GraphRequest.newGraphPathRequest((AccessToken) null, String.format(SDK_MODEL_ASSET, new Object[]{FacebookSdk.getApplicationId()}), (GraphRequest.Callback) null);
        newGraphPathRequest.setSkipClientToken(true);
        newGraphPathRequest.setParameters(bundle);
        JSONObject jSONObject = newGraphPathRequest.executeAndWait().getJSONObject();
        if (jSONObject == null) {
            return null;
        }
        return parseRawJsonObject(jSONObject);
    }

    @Nullable
    private static Model jsonObjectToModel(JSONObject jSONObject) {
        try {
            return new Model(jSONObject.getString("use_case"), Integer.parseInt(jSONObject.getString("version_id")), jSONObject.getString("asset_uri"), jSONObject.optString("rules_uri", (String) null), parseJsonArray(jSONObject.getJSONArray("thresholds")));
        } catch (JSONException unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void enableSuggestedEvents() {
        if (models.containsKey(MODEL_SUGGESTED_EVENTS)) {
            Locale resourceLocale = Utility.getResourceLocale();
            if (resourceLocale == null || resourceLocale.getLanguage().contains("en")) {
                FeatureManager.checkFeature(FeatureManager.Feature.SuggestedEvents, new FeatureManager.Callback() {
                    public void onCompleted(boolean z) {
                        if (z) {
                            ((Model) ModelManager.models.get(ModelManager.MODEL_SUGGESTED_EVENTS)).initialize(new Runnable() {
                                public void run() {
                                    SuggestedEventsManager.enable();
                                }
                            });
                        }
                    }
                });
            }
        }
    }

    public static void enablePIIFiltering() {
        if (models.containsKey(MODEL_ADDRESS_DETECTION)) {
            FeatureManager.checkFeature(FeatureManager.Feature.PIIFiltering, new FeatureManager.Callback() {
                public void onCompleted(boolean z) {
                    if (z) {
                        ((Model) ModelManager.models.get(ModelManager.MODEL_ADDRESS_DETECTION)).initialize(new Runnable() {
                            public void run() {
                                AddressFilterManager.enable();
                            }
                        });
                    }
                }
            });
        }
    }

    private static float[] parseJsonArray(JSONArray jSONArray) {
        float[] fArr = new float[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                fArr[i] = Float.parseFloat(jSONArray.getString(i));
            } catch (JSONException unused) {
            }
        }
        return fArr;
    }

    @Nullable
    public static String predict(String str, float[] fArr, String str2) {
        if (!models.containsKey(str)) {
            return null;
        }
        return ((Model) models.get(str)).predict(fArr, str2);
    }

    @Nullable
    public static File getRuleFile(String str) {
        if (!models.containsKey(str)) {
            return null;
        }
        return ((Model) models.get(str)).getRuleFile();
    }
}
