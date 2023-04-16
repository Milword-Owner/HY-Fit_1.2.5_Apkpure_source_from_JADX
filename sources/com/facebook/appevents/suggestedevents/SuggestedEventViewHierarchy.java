package com.facebook.appevents.suggestedevents;

import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class SuggestedEventViewHierarchy {
    static final String TAG = "com.facebook.appevents.suggestedevents.SuggestedEventViewHierarchy";
    private static final List<Class<? extends View>> blacklistedViews = new ArrayList(Arrays.asList(new Class[]{Switch.class, Spinner.class, DatePicker.class, TimePicker.class, RadioGroup.class, RatingBar.class, EditText.class, AdapterView.class}));

    SuggestedEventViewHierarchy() {
    }

    static JSONObject getDictionaryOfView(View view, View view2) {
        JSONObject jSONObject = new JSONObject();
        if (view == view2) {
            try {
                jSONObject.put(ViewHierarchyConstants.IS_INTERACTED_KEY, true);
            } catch (JSONException unused) {
            }
        }
        updateBasicInfo(view, jSONObject);
        JSONArray jSONArray = new JSONArray();
        List<View> childrenOfView = ViewHierarchy.getChildrenOfView(view);
        for (int i = 0; i < childrenOfView.size(); i++) {
            jSONArray.put(getDictionaryOfView(childrenOfView.get(i), view2));
        }
        jSONObject.put(ViewHierarchyConstants.CHILDREN_VIEW_KEY, jSONArray);
        return jSONObject;
    }

    static void updateBasicInfo(View view, JSONObject jSONObject) {
        try {
            String textOfView = ViewHierarchy.getTextOfView(view);
            String hintOfView = ViewHierarchy.getHintOfView(view);
            jSONObject.put(ViewHierarchyConstants.CLASS_NAME_KEY, view.getClass().getSimpleName());
            jSONObject.put(ViewHierarchyConstants.CLASS_TYPE_BITMASK_KEY, ViewHierarchy.getClassTypeBitmask(view));
            if (!textOfView.isEmpty()) {
                jSONObject.put(ViewHierarchyConstants.TEXT_KEY, textOfView);
            }
            if (!hintOfView.isEmpty()) {
                jSONObject.put(ViewHierarchyConstants.HINT_KEY, hintOfView);
            }
            if (view instanceof EditText) {
                jSONObject.put(ViewHierarchyConstants.INPUT_TYPE_KEY, ((EditText) view).getInputType());
            }
        } catch (JSONException unused) {
        }
    }

    static List<View> getAllClickableViews(View view) {
        ArrayList arrayList = new ArrayList();
        for (Class<? extends View> isInstance : blacklistedViews) {
            if (isInstance.isInstance(view)) {
                return arrayList;
            }
        }
        if (view.isClickable()) {
            arrayList.add(view);
        }
        for (View allClickableViews : ViewHierarchy.getChildrenOfView(view)) {
            arrayList.addAll(getAllClickableViews(allClickableViews));
        }
        return arrayList;
    }
}
