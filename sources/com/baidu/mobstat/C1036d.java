package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.baidu.mobstat.C0976bl;
import com.baidu.mobstat.C0985bs;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.d */
public class C1036d {

    /* renamed from: a */
    static C1036d f1373a = new C1036d();

    /* renamed from: a */
    public synchronized void mo11817a(Context context) {
        m1652b(context);
    }

    /* renamed from: b */
    private void m1652b(Context context) {
        m1651a(context, m1653c(context));
    }

    /* renamed from: c */
    private ArrayList<C1037a> m1653c(Context context) {
        ArrayList<C1037a> arrayList = new ArrayList<>();
        Iterator<PackageInfo> it = m1654d(context).iterator();
        while (it.hasNext()) {
            PackageInfo next = it.next();
            ApplicationInfo applicationInfo = next.applicationInfo;
            if (applicationInfo != null) {
                String str = next.packageName;
                String str2 = next.versionName;
                Signature[] signatureArr = next.signatures;
                String str3 = "";
                String a = C0985bs.C0987b.m1413a(((signatureArr == null || signatureArr.length == 0) ? str3 : signatureArr[0].toChars().toString()).getBytes());
                String str4 = applicationInfo.sourceDir;
                if (!TextUtils.isEmpty(str4)) {
                    str3 = C0985bs.C0987b.m1412a(new File(str4));
                }
                arrayList.add(new C1037a(str, str2, a, str3));
            }
        }
        return arrayList;
    }

    /* renamed from: d */
    private ArrayList<PackageInfo> m1654d(Context context) {
        ArrayList<PackageInfo> arrayList = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return arrayList;
        }
        List<PackageInfo> arrayList2 = new ArrayList<>(1);
        try {
            arrayList2 = packageManager.getInstalledPackages(64);
        } catch (Exception e) {
            C0954ba.m1191c().mo11629b((Throwable) e);
        }
        for (PackageInfo packageInfo : arrayList2) {
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo != null && (applicationInfo.flags & 1) == 0) {
                arrayList.add(packageInfo);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m1651a(Context context, ArrayList<C1037a> arrayList) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<C1037a> it = arrayList.iterator();
            while (it.hasNext()) {
                JSONObject a = it.next().mo11818a();
                if (a != null) {
                    jSONArray.put(a);
                }
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("app_apk", jSONArray);
            jSONObject.put("meta-data", sb.toString());
            str = C0976bl.C0977a.m1326a(jSONObject.toString().getBytes());
        } catch (Exception e) {
            C0954ba.m1191c().mo11629b((Throwable) e);
            str = "";
        }
        if (!TextUtils.isEmpty(str)) {
            C1055k.APP_APK.mo11848a(context, System.currentTimeMillis(), str);
        }
    }

    /* renamed from: com.baidu.mobstat.d$a */
    static class C1037a {

        /* renamed from: a */
        private String f1374a;

        /* renamed from: b */
        private String f1375b;

        /* renamed from: c */
        private String f1376c;

        /* renamed from: d */
        private String f1377d;

        public C1037a(String str, String str2, String str3, String str4) {
            str = str == null ? "" : str;
            str2 = str2 == null ? "" : str2;
            str3 = str3 == null ? "" : str3;
            str4 = str4 == null ? "" : str4;
            this.f1374a = str;
            this.f1375b = str2;
            this.f1376c = str3;
            this.f1377d = str4;
        }

        /* renamed from: a */
        public JSONObject mo11818a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("n", this.f1374a);
                jSONObject.put("v", this.f1375b);
                jSONObject.put("c", this.f1376c);
                jSONObject.put(Config.APP_VERSION_CODE, this.f1377d);
                return jSONObject;
            } catch (JSONException e) {
                C0954ba.m1191c().mo11629b((Throwable) e);
                return null;
            }
        }
    }
}
