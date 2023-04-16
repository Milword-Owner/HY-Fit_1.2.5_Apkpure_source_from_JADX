package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.share.internal.MessengerShareContentUtility;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.h */
public class C1052h {
    /* renamed from: a */
    public static JSONObject m1709a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("s", Build.VERSION.SDK_INT);
            jSONObject.put("sv", Build.VERSION.RELEASE);
            String b = C0989bu.m1416a().mo11722b();
            if (TextUtils.isEmpty(b)) {
                b = C0991bw.m1431a(2, context);
            }
            jSONObject.put(Config.CUID_SEC, b);
            jSONObject.put(Config.DEVICE_WIDTH, C0991bw.m1441c(context));
            jSONObject.put("h", C0991bw.m1445d(context));
            jSONObject.put("ly", C1088z.f1477c);
            jSONObject.put("pv", "35");
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                jSONObject.put(Config.PACKAGE_NAME, C0991bw.m1466n(2, context));
                jSONObject.put(Config.APP_VERSION_CODE, packageInfo.versionCode);
                jSONObject.put("n", packageInfo.versionName);
            } catch (Exception e) {
                C0954ba.m1191c().mo11626a((Throwable) e);
            }
            jSONObject.put(Config.DEVICE_MAC_ID, C0991bw.m1454h(2, context));
            jSONObject.put(Config.DEVICE_BLUETOOTH_MAC, C0991bw.m1462l(2, context));
            jSONObject.put(Config.MODEL, Build.MODEL);
            jSONObject.put(Config.DEVICE_NAME, C0991bw.m1433a(context, 2));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(Config.TRACE_FAILED_CNT, 0);
            jSONObject2.put("send_index", 0);
            String b2 = C0991bw.m1436b();
            if (b2 == null) {
                b2 = "";
            }
            jSONObject2.put(Config.ROM, b2);
            jSONObject.put(Config.TRACE_PART, jSONObject2);
            jSONObject.put(Config.DEVICE_IMEI, C0991bw.m1478y(context));
            jSONObject.put(Config.OAID, C0991bw.m1437b(2, context));
            jSONObject.put(Config.OUT_OAID, C0991bw.m1443c(2, context));
            jSONObject.put(Config.FROM, "0");
            jSONObject.put(Config.GAID, C0991bw.m1449e(2, context));
            jSONObject.put(Config.IID, C0991bw.m1447d(2, context));
            jSONObject.put(Config.CUID3, C0991bw.m1451f(2, context));
            jSONObject.put(Config.SSAID, C0991bw.m1452g(2, context));
        } catch (JSONException e2) {
            C0954ba.m1191c().mo11629b((Throwable) e2);
        }
        return jSONObject;
    }

    /* renamed from: a */
    public static JSONObject m1710a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            JSONArray jSONArray = (JSONArray) jSONObject.get(MessengerShareContentUtility.ATTACHMENT_PAYLOAD);
            JSONObject jSONObject2 = (jSONArray == null || jSONArray.length() <= 0) ? null : (JSONObject) jSONArray.get(0);
            if (jSONObject2 != null) {
                return jSONObject2.getJSONObject(Config.HEADER_PART);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: b */
    public static void m1711b(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(Config.TRACE_PART);
            jSONObject2.put(Config.TRACE_FAILED_CNT, jSONObject2.getLong(Config.TRACE_FAILED_CNT) + 1);
        } catch (Exception unused) {
        }
    }

    /* renamed from: c */
    public static void m1712c(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(Config.TRACE_PART);
            jSONObject2.put("send_index", jSONObject2.getLong("send_index") + 1);
        } catch (Exception unused) {
        }
    }
}
