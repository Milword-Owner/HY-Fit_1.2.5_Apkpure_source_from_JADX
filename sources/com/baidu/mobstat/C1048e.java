package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.baidu.mobstat.C0976bl;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.e */
class C1048e {

    /* renamed from: a */
    static final C1048e f1402a = new C1048e();

    C1048e() {
    }

    /* renamed from: a */
    public synchronized void mo11831a(Context context, boolean z) {
        m1695b(context, z);
    }

    /* renamed from: b */
    private void m1695b(Context context, boolean z) {
        String str;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            List<PackageInfo> arrayList = new ArrayList<>(1);
            try {
                arrayList = packageManager.getInstalledPackages(0);
            } catch (Exception e) {
                C0954ba.m1191c().mo11629b((Throwable) e);
            }
            JSONArray jSONArray = new JSONArray();
            for (PackageInfo packageInfo : arrayList) {
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                if (applicationInfo != null) {
                    boolean z2 = (applicationInfo.flags & 1) != 0;
                    String str2 = applicationInfo.sourceDir;
                    if (z == z2) {
                        m1694a(z, "", str2, packageInfo, jSONArray);
                    }
                }
            }
            if (jSONArray.length() != 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(System.currentTimeMillis() + "|");
                sb.append(z ? 1 : 0);
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("app_list", jSONArray);
                    jSONObject.put("meta-data", sb.toString());
                    str = C0976bl.C0977a.m1326a(jSONObject.toString().getBytes());
                } catch (Exception unused) {
                    str = "";
                }
                if (!TextUtils.isEmpty(str)) {
                    C1055k.APP_LIST.mo11848a(context, System.currentTimeMillis(), str);
                }
            }
        }
    }

    /* renamed from: a */
    private void m1694a(boolean z, String str, String str2, PackageInfo packageInfo, JSONArray jSONArray) {
        long j;
        if (!z || !packageInfo.packageName.startsWith("com.android.")) {
            long j2 = 0;
            try {
                j = packageInfo.firstInstallTime;
            } catch (Throwable th) {
                C0954ba.m1191c().mo11629b(th);
                j = 0;
            }
            try {
                j2 = packageInfo.lastUpdateTime;
            } catch (Throwable th2) {
                C0954ba.m1191c().mo11629b(th2);
            }
            long a = m1693a(str2);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("n", packageInfo.packageName);
                jSONObject.put(Config.APP_VERSION_CODE, str);
                jSONObject.put("v", String.valueOf(packageInfo.versionName));
                jSONObject.put("f", j);
                jSONObject.put("l", j2);
                jSONObject.put(Config.MODEL, a);
                jSONArray.put(jSONObject);
            } catch (JSONException e) {
                C0954ba.m1191c().mo11629b((Throwable) e);
            }
        }
    }

    /* renamed from: a */
    private long m1693a(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.exists()) {
                return file.lastModified();
            }
        }
        return 0;
    }
}
