package com.baidu.mobstat;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.baidu.mobstat.C0976bl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.f */
class C1049f {

    /* renamed from: a */
    static C1049f f1403a = new C1049f();

    /* renamed from: b */
    private String f1404b = "";

    /* renamed from: a */
    private boolean m1701a(int i) {
        return i == 100 || i == 200 || i == 130;
    }

    C1049f() {
    }

    /* renamed from: a */
    public synchronized void mo11832a(Context context, boolean z) {
        int i = 1;
        if (!z) {
            i = 20;
        }
        m1700a(context, z, i);
    }

    /* renamed from: a */
    private void m1700a(Context context, boolean z, int i) {
        ArrayList<C1050a> a = m1698a(context, i);
        if (a != null && a.size() != 0) {
            if (z) {
                String b = a.get(0).mo11834b();
                if (m1702a(b, this.f1404b)) {
                    this.f1404b = b;
                }
            }
            m1699a(context, a, z);
        }
    }

    /* renamed from: a */
    private ArrayList<C1050a> m1698a(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return m1705c(context, i);
        }
        return m1703b(context, i);
    }

    /* renamed from: b */
    private ArrayList<C1050a> m1703b(Context context, int i) {
        List<ActivityManager.RunningTaskInfo> list;
        try {
            list = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(50);
        } catch (Exception e) {
            C0954ba.m1191c().mo11629b((Throwable) e);
            list = null;
        }
        if (list == null) {
            return new ArrayList<>();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (ActivityManager.RunningTaskInfo next : list) {
            if (linkedHashMap.size() > i) {
                break;
            }
            ComponentName componentName = next.topActivity;
            if (componentName != null) {
                String packageName = componentName.getPackageName();
                if (!TextUtils.isEmpty(packageName) && !m1704b(context, packageName) && !linkedHashMap.containsKey(packageName)) {
                    linkedHashMap.put(packageName, new C1050a(packageName, m1697a(context, packageName), ""));
                }
            }
        }
        return new ArrayList<>(linkedHashMap.values());
    }

    /* renamed from: c */
    private ArrayList<C1050a> m1705c(Context context, int i) {
        String[] strArr;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return new ArrayList<>();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i2 = 0; i2 < runningAppProcesses.size() && linkedHashMap.size() <= i; i2++) {
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i2);
            if (!(!m1701a(runningAppProcessInfo.importance) || (strArr = runningAppProcessInfo.pkgList) == null || strArr.length == 0)) {
                String str = runningAppProcessInfo.pkgList[0];
                if (!TextUtils.isEmpty(str) && !m1704b(context, str) && !linkedHashMap.containsKey(str)) {
                    linkedHashMap.put(str, new C1050a(str, m1697a(context, str), String.valueOf(runningAppProcessInfo.importance)));
                }
            }
        }
        return new ArrayList<>(linkedHashMap.values());
    }

    /* renamed from: a */
    private boolean m1702a(String str, String str2) {
        return !TextUtils.isEmpty(str) && !str.equals(this.f1404b);
    }

    /* renamed from: a */
    private String m1697a(Context context, String str) {
        String str2;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return "";
        }
        try {
            str2 = packageManager.getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            C0954ba.m1191c().mo11629b((Throwable) e);
            str2 = "";
        }
        if (str2 == null) {
            return "";
        }
        return str2;
    }

    /* renamed from: b */
    private boolean m1704b(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getPackageInfo(str, 0).applicationInfo;
            if (applicationInfo == null || (applicationInfo.flags & 1) == 0) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            C0954ba.m1191c().mo11629b((Throwable) e);
            return false;
        }
    }

    /* renamed from: a */
    private void m1699a(Context context, ArrayList<C1050a> arrayList, boolean z) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() + "|");
        sb.append(z ? 1 : 0);
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<C1050a> it = arrayList.iterator();
            while (it.hasNext()) {
                JSONObject a = it.next().mo11833a();
                if (a != null) {
                    jSONArray.put(a);
                }
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("app_trace", jSONArray);
            jSONObject.put("meta-data", sb.toString());
            str = C0976bl.C0977a.m1326a(jSONObject.toString().getBytes());
        } catch (Exception e) {
            C0954ba.m1191c().mo11629b((Throwable) e);
            str = "";
        }
        if (!TextUtils.isEmpty(str)) {
            C1055k.APP_TRACE.mo11848a(context, System.currentTimeMillis(), str);
        }
    }

    /* renamed from: com.baidu.mobstat.f$a */
    static class C1050a {

        /* renamed from: a */
        private String f1405a;

        /* renamed from: b */
        private String f1406b;

        /* renamed from: c */
        private String f1407c;

        public C1050a(String str, String str2, String str3) {
            this.f1405a = str == null ? "" : str;
            this.f1406b = str2 == null ? "" : str2;
            this.f1407c = str3 == null ? "" : str3;
        }

        /* renamed from: a */
        public JSONObject mo11833a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("n", this.f1405a);
                jSONObject.put("v", this.f1406b);
                jSONObject.put(Config.DEVICE_WIDTH, this.f1407c);
                return jSONObject;
            } catch (JSONException e) {
                C0954ba.m1191c().mo11629b((Throwable) e);
                return null;
            }
        }

        /* renamed from: b */
        public String mo11834b() {
            return this.f1405a;
        }
    }
}
