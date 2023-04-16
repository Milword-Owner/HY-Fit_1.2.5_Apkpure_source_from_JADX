package com.baidu.mobstat;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.clj.fastble.BleManager;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.x */
public class C1086x {

    /* renamed from: a */
    public static final C1086x f1468a = new C1086x();

    /* renamed from: a */
    public void mo11907a(Context context, JSONObject jSONObject) {
        C0954ba.m1191c().mo11624a("startDataAnynalyzed start");
        m1816a(jSONObject);
        C1085w a = C1085w.m1796a(context);
        boolean a2 = a.mo11891a();
        C0954ba c = C0954ba.m1191c();
        c.mo11624a("is data collect closed:" + a2);
        if (!a2) {
            if (!C1055k.AP_LIST.mo11853b(context, BleManager.DEFAULT_SCAN_TIME)) {
                m1818c(context);
            }
            String str = Build.MANUFACTURER;
            int i = Build.VERSION.SDK_INT;
            boolean z = false;
            if (!TextUtils.isEmpty(str) && "huawei".equals(str.trim().toLowerCase()) && i >= 28) {
                z = true;
            }
            if (!C1055k.APP_LIST.mo11853b(context, BleManager.DEFAULT_SCAN_TIME) && !z) {
                m1819d(context);
            }
            if (!C1055k.APP_TRACE.mo11853b(context, BleManager.DEFAULT_SCAN_TIME) && !z) {
                m1820e(context);
            }
            if (C1088z.f1479e && !C1055k.APP_APK.mo11853b(context, BleManager.DEFAULT_SCAN_TIME) && !z) {
                m1821f(context);
            }
            boolean q = C0991bw.m1470q(context);
            if (q && a.mo11904l()) {
                C0954ba.m1191c().mo11624a("sendLog");
                m1822g(context);
            } else if (!q) {
                C0954ba.m1191c().mo11624a("isWifiAvailable = false, will not sendLog");
            } else {
                C0954ba.m1191c().mo11624a("can not sendLog due to time stratergy");
            }
        }
        C0954ba.m1191c().mo11624a("startDataAnynalyzed finished");
    }

    /* renamed from: a */
    private void m1816a(JSONObject jSONObject) {
        C0876aa aaVar = new C0876aa(jSONObject);
        C1088z.f1476b = aaVar.f929a;
        C1088z.f1477c = aaVar.f930b;
        C1088z.f1478d = aaVar.f931c;
    }

    /* renamed from: c */
    private void m1818c(Context context) {
        C0954ba.m1191c().mo11624a("collectAPWithStretegy 1");
        C1085w a = C1085w.m1796a(context);
        long a2 = a.mo11888a(C1051g.AP_LIST);
        long currentTimeMillis = System.currentTimeMillis();
        long e = a.mo11897e();
        C0954ba c = C0954ba.m1191c();
        c.mo11624a("now time: " + currentTimeMillis + ": last time: " + a2 + "; time interval: " + e);
        if (a2 == 0 || currentTimeMillis - a2 > e) {
            C0954ba.m1191c().mo11624a("collectAPWithStretegy 2");
            C0998c.m1489a(context);
        }
    }

    /* renamed from: d */
    private void m1819d(Context context) {
        C0954ba.m1191c().mo11624a("collectAPPListWithStretegy 1");
        long currentTimeMillis = System.currentTimeMillis();
        C1085w a = C1085w.m1796a(context);
        long a2 = a.mo11888a(C1051g.APP_USER_LIST);
        long f = a.mo11898f();
        C0954ba c = C0954ba.m1191c();
        c.mo11624a("now time: " + currentTimeMillis + ": last time: " + a2 + "; userInterval : " + f);
        if (a2 == 0 || currentTimeMillis - a2 > f || !a.mo11892a(a2)) {
            C0954ba.m1191c().mo11624a("collectUserAPPListWithStretegy 2");
            C0998c.m1490a(context, false);
        }
        long a3 = a.mo11888a(C1051g.APP_SYS_LIST);
        long g = a.mo11899g();
        C0954ba c2 = C0954ba.m1191c();
        c2.mo11624a("now time: " + currentTimeMillis + ": last time: " + a3 + "; sysInterval : " + g);
        if (a3 == 0 || currentTimeMillis - a3 > g) {
            C0954ba.m1191c().mo11624a("collectSysAPPListWithStretegy 2");
            C0998c.m1490a(context, true);
        }
    }

    /* renamed from: e */
    private void m1820e(Context context) {
        C0954ba.m1191c().mo11624a("collectAPPTraceWithStretegy 1");
        long currentTimeMillis = System.currentTimeMillis();
        C1085w a = C1085w.m1796a(context);
        long a2 = a.mo11888a(C1051g.APP_TRACE_HIS);
        long i = a.mo11901i();
        C0954ba c = C0954ba.m1191c();
        c.mo11624a("now time: " + currentTimeMillis + ": last time: " + a2 + "; time interval: " + i);
        if (a2 == 0 || currentTimeMillis - a2 > i) {
            C0954ba.m1191c().mo11624a("collectAPPTraceWithStretegy 2");
            C0998c.m1492b(context, false);
        }
    }

    /* renamed from: f */
    private void m1821f(Context context) {
        C0954ba.m1191c().mo11624a("collectAPKWithStretegy 1");
        long currentTimeMillis = System.currentTimeMillis();
        C1085w a = C1085w.m1796a(context);
        long a2 = a.mo11888a(C1051g.APP_APK);
        long h = a.mo11900h();
        C0954ba c = C0954ba.m1191c();
        c.mo11624a("now time: " + currentTimeMillis + ": last time: " + a2 + "; interval : " + h);
        if (a2 == 0 || currentTimeMillis - a2 > h) {
            C0954ba.m1191c().mo11624a("collectAPKWithStretegy 2");
            C0998c.m1491b(context);
        }
    }

    /* renamed from: a */
    public void mo11906a(Context context, String str) {
        C1085w.m1796a(context).mo11890a(str);
    }

    /* renamed from: b */
    public void mo11909b(Context context, String str) {
        C1085w.m1796a(context).mo11893b(str);
    }

    /* renamed from: a */
    public void mo11905a(Context context, long j) {
        C1085w.m1796a(context).mo11889a(C1051g.LAST_UPDATE, j);
    }

    /* renamed from: g */
    private void m1822g(Context context) {
        C1085w.m1796a(context).mo11889a(C1051g.LAST_SEND, System.currentTimeMillis());
        JSONObject a = C1052h.m1709a(context);
        C0954ba c = C0954ba.m1191c();
        c.mo11624a("header: " + a);
        int i = 0;
        while (m1823h(context)) {
            int i2 = i + 1;
            if (i > 0) {
                C1052h.m1712c(a);
            }
            m1817b(context, a);
            i = i2;
        }
    }

    /* renamed from: h */
    private boolean m1823h(Context context) {
        if (C1055k.AP_LIST.mo11852b(context) && C1055k.APP_LIST.mo11852b(context) && C1055k.APP_TRACE.mo11852b(context) && C1055k.APP_CHANGE.mo11852b(context) && C1055k.APP_APK.mo11852b(context)) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    private void m1817b(Context context, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        int i = 0;
        try {
            jSONObject2.put(Config.HEADER_PART, jSONObject);
            i = 0 + jSONObject.toString().length();
        } catch (JSONException e) {
            C0954ba.m1191c().mo11626a((Throwable) e);
        }
        C0954ba.m1191c().mo11624a("APP_MEM");
        if (!C1085w.m1796a(context).mo11894b()) {
            String x = C0991bw.m1477x(context);
            JSONArray jSONArray = new JSONArray();
            C0954ba.m1191c().mo11624a(x);
            jSONArray.put(x);
            if (jSONArray.length() > 0) {
                try {
                    jSONObject2.put("app_mem3", jSONArray);
                    i += jSONArray.toString().length();
                } catch (JSONException e2) {
                    C0954ba.m1191c().mo11626a((Throwable) e2);
                }
            }
        }
        C0954ba.m1191c().mo11624a("APP_APK");
        List<String> a = C1055k.APP_APK.mo11851a(context, 20480);
        JSONArray jSONArray2 = new JSONArray();
        for (String next : a) {
            C0954ba.m1191c().mo11624a(next);
            jSONArray2.put(next);
        }
        if (jSONArray2.length() > 0) {
            try {
                jSONObject2.put("app_apk3", jSONArray2);
                i += jSONArray2.toString().length();
            } catch (JSONException e3) {
                C0954ba.m1191c().mo11626a((Throwable) e3);
            }
        }
        C0954ba.m1191c().mo11624a("APP_CHANGE");
        List<String> a2 = C1055k.APP_CHANGE.mo11851a(context, 10240);
        JSONArray jSONArray3 = new JSONArray();
        for (String next2 : a2) {
            C0954ba.m1191c().mo11624a(next2);
            jSONArray3.put(next2);
        }
        if (jSONArray3.length() > 0) {
            try {
                jSONObject2.put("app_change3", jSONArray3);
                i += jSONArray3.toString().length();
            } catch (JSONException e4) {
                C0954ba.m1191c().mo11626a((Throwable) e4);
            }
        }
        C0954ba.m1191c().mo11624a("APP_TRACE");
        List<String> a3 = C1055k.APP_TRACE.mo11851a(context, 15360);
        JSONArray jSONArray4 = new JSONArray();
        for (String next3 : a3) {
            C0954ba.m1191c().mo11624a(next3);
            jSONArray4.put(next3);
        }
        if (jSONArray4.length() > 0) {
            try {
                jSONObject2.put("app_trace3", jSONArray4);
                i += jSONArray4.toString().length();
            } catch (JSONException e5) {
                C0954ba.m1191c().mo11626a((Throwable) e5);
            }
        }
        C0954ba.m1191c().mo11624a("APP_LIST");
        List<String> a4 = C1055k.APP_LIST.mo11851a(context, 46080);
        JSONArray jSONArray5 = new JSONArray();
        for (String next4 : a4) {
            C0954ba.m1191c().mo11624a(next4);
            jSONArray5.put(next4);
        }
        if (jSONArray5.length() > 0) {
            try {
                jSONObject2.put("app_list3", jSONArray5);
                i += jSONArray5.toString().length();
            } catch (JSONException e6) {
                C0954ba.m1191c().mo11626a((Throwable) e6);
            }
        }
        C0954ba.m1191c().mo11624a("AP_LIST");
        List<String> a5 = C1055k.AP_LIST.mo11851a(context, Config.MAX_CACHE_JSON_CAPACITY - i);
        JSONArray jSONArray6 = new JSONArray();
        for (String next5 : a5) {
            C0954ba.m1191c().mo11624a(next5);
            jSONArray6.put(next5);
        }
        if (jSONArray6.length() > 0) {
            try {
                jSONObject2.put("ap_list3", jSONArray6);
                i += jSONArray6.toString().length();
            } catch (JSONException e7) {
                C0954ba.m1191c().mo11626a((Throwable) e7);
            }
        }
        C0954ba c = C0954ba.m1191c();
        c.mo11624a("log in bytes is almost :" + i);
        JSONArray jSONArray7 = new JSONArray();
        jSONArray7.put(jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, jSONArray7);
            C1067r.m1765a().mo11867a(context, jSONObject3.toString());
        } catch (Exception e8) {
            C0954ba.m1191c().mo11626a((Throwable) e8);
        }
    }

    /* renamed from: a */
    public boolean mo11908a(Context context) {
        if (!C0991bw.m1442c().booleanValue()) {
            return false;
        }
        C1085w a = C1085w.m1796a(context);
        long a2 = a.mo11888a(C1051g.LAST_UPDATE);
        long c = a.mo11895c();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - a2 > c) {
            C0954ba c2 = C0954ba.m1191c();
            c2.mo11624a("need to update, checkWithLastUpdateTime lastUpdateTime =" + a2 + "nowTime=" + currentTimeMillis + ";timeInteveral=" + c);
            return true;
        }
        C0954ba c3 = C0954ba.m1191c();
        c3.mo11624a("no need to update, checkWithLastUpdateTime lastUpdateTime =" + a2 + "nowTime=" + currentTimeMillis + ";timeInteveral=" + c);
        return false;
    }

    /* renamed from: b */
    public boolean mo11910b(Context context) {
        return !C1085w.m1796a(context).mo11891a() || mo11908a(context);
    }
}
