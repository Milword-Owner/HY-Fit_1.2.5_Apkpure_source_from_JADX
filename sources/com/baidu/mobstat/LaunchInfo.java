package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import org.json.JSONObject;

public class LaunchInfo {

    /* renamed from: a */
    private String f855a;

    /* renamed from: b */
    private String f856b;

    /* renamed from: c */
    private String f857c;

    public String getPushLandingPage() {
        return !TextUtils.isEmpty(this.f855a) ? this.f855a : "";
    }

    public String getPushContent() {
        return !TextUtils.isEmpty(this.f856b) ? this.f856b : "";
    }

    public String getRefererPkgName() {
        return !TextUtils.isEmpty(this.f857c) ? this.f857c : "";
    }

    public void setPushInfo(String str, String str2) {
        this.f855a = str;
        this.f856b = C0994bx.m1484a(str2, 1024);
    }

    public void setRefererPkgName(String str) {
        this.f857c = str;
    }

    public int getLaunchType(Context context) {
        if (!TextUtils.isEmpty(this.f855a)) {
            return 2;
        }
        String packageName = context != null ? context.getPackageName() : "";
        if (TextUtils.isEmpty(this.f857c) || this.f857c.equals(packageName)) {
            return 0;
        }
        String a = C0968bi.m1243a(context);
        if (!TextUtils.isEmpty(a)) {
            if (!this.f857c.equals(a)) {
                return 1;
            }
            return 0;
        } else if (!C0968bi.m1255a(context, this.f857c)) {
            return 1;
        } else {
            return 0;
        }
    }

    public static JSONObject getConvertedJson(int i, String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", String.valueOf(i));
                if (str == null) {
                    str = "";
                }
                jSONObject.put(Config.LAUNCH_REFERER, str);
                if (str2 == null) {
                    str2 = "";
                }
                jSONObject.put(Config.LAUNCH_INFO, str2);
                jSONObject.put("content", "");
                return jSONObject;
            } catch (Exception unused) {
                return jSONObject;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    public static String getLauncherHomePkgName(Context context) {
        String a = C0968bi.m1243a(context);
        return !TextUtils.isEmpty(a) ? a : "";
    }
}
