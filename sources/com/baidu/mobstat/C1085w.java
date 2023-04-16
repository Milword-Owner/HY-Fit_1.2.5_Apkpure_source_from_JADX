package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import java.text.SimpleDateFormat;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.w */
public class C1085w {

    /* renamed from: a */
    private static C1085w f1451a;

    /* renamed from: b */
    private Context f1452b;

    /* renamed from: c */
    private JSONObject f1453c = new JSONObject();

    /* renamed from: d */
    private long f1454d = 24;

    /* renamed from: e */
    private long f1455e = 0;

    /* renamed from: f */
    private long f1456f = 0;

    /* renamed from: g */
    private long f1457g = 0;

    /* renamed from: h */
    private long f1458h = 5;

    /* renamed from: i */
    private long f1459i = 24;

    /* renamed from: j */
    private long f1460j = 15;

    /* renamed from: k */
    private long f1461k = 15;

    /* renamed from: l */
    private long f1462l = 30;

    /* renamed from: m */
    private long f1463m = 12;

    /* renamed from: n */
    private long f1464n = 1;

    /* renamed from: o */
    private long f1465o = 24;

    /* renamed from: p */
    private String f1466p = "";

    /* renamed from: q */
    private String f1467q = "";

    /* renamed from: a */
    public static C1085w m1796a(Context context) {
        if (f1451a == null) {
            synchronized (C1085w.class) {
                if (f1451a == null) {
                    f1451a = new C1085w(context);
                }
            }
        }
        return f1451a;
    }

    private C1085w(Context context) {
        this.f1452b = context;
        m1798m();
        mo11902j();
        mo11903k();
    }

    /* renamed from: m */
    private void m1798m() {
        String a = C0980bn.m1345a(this.f1452b, C1087y.f1472d);
        try {
            if (!TextUtils.isEmpty(a)) {
                this.f1453c = new JSONObject(a);
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public boolean mo11891a() {
        return this.f1455e != 0;
    }

    /* renamed from: b */
    public boolean mo11894b() {
        return this.f1456f != 0;
    }

    /* renamed from: c */
    public long mo11895c() {
        return this.f1454d * 60 * 60 * 1000;
    }

    /* renamed from: d */
    public long mo11896d() {
        return this.f1465o * 60 * 60 * 1000;
    }

    /* renamed from: e */
    public long mo11897e() {
        return this.f1458h * 60 * 1000;
    }

    /* renamed from: f */
    public long mo11898f() {
        return this.f1459i * 60 * 60 * 1000;
    }

    /* renamed from: g */
    public long mo11899g() {
        return this.f1460j * 24 * 60 * 60 * 1000;
    }

    /* renamed from: h */
    public long mo11900h() {
        return this.f1461k * 24 * 60 * 60 * 1000;
    }

    /* renamed from: i */
    public long mo11901i() {
        return this.f1463m * 60 * 60 * 1000;
    }

    /* renamed from: j */
    public void mo11902j() {
        try {
            String str = new String(C0990bv.m1428b(false, C0983bq.m1404a(), C0981bo.m1354a(C0980bn.m1345a(this.f1452b, ".config2").getBytes())));
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                try {
                    this.f1455e = jSONObject.getLong("c");
                } catch (JSONException e) {
                    C0954ba.m1191c().mo11629b((Throwable) e);
                }
                try {
                    this.f1458h = jSONObject.getLong("d");
                } catch (JSONException e2) {
                    C0954ba.m1191c().mo11629b((Throwable) e2);
                }
                try {
                    this.f1459i = jSONObject.getLong("e");
                } catch (JSONException e3) {
                    C0954ba.m1191c().mo11629b((Throwable) e3);
                }
                try {
                    this.f1460j = jSONObject.getLong("i");
                } catch (JSONException e4) {
                    C0954ba.m1191c().mo11629b((Throwable) e4);
                }
                try {
                    this.f1454d = jSONObject.getLong("f");
                } catch (JSONException e5) {
                    C0954ba.m1191c().mo11629b((Throwable) e5);
                }
                try {
                    this.f1465o = jSONObject.getLong("s");
                } catch (JSONException e6) {
                    C0954ba.m1191c().mo11629b((Throwable) e6);
                }
                try {
                    this.f1461k = jSONObject.getLong("pk");
                } catch (JSONException e7) {
                    C0954ba.m1191c().mo11629b((Throwable) e7);
                }
                try {
                    this.f1462l = jSONObject.getLong("at");
                } catch (JSONException e8) {
                    C0954ba.m1191c().mo11629b((Throwable) e8);
                }
                try {
                    this.f1463m = jSONObject.getLong("as");
                } catch (JSONException e9) {
                    C0954ba.m1191c().mo11629b((Throwable) e9);
                }
                try {
                    this.f1464n = jSONObject.getLong("ac");
                } catch (JSONException e10) {
                    C0954ba.m1191c().mo11629b((Throwable) e10);
                }
                try {
                    this.f1456f = jSONObject.getLong(Config.DEVICE_MAC_ID);
                } catch (JSONException e11) {
                    C0954ba.m1191c().mo11629b((Throwable) e11);
                }
                try {
                    this.f1457g = jSONObject.getLong("lsc");
                } catch (JSONException e12) {
                    C0954ba.m1191c().mo11629b((Throwable) e12);
                }
            }
        } catch (Exception e13) {
            C0954ba.m1191c().mo11629b((Throwable) e13);
        }
    }

    /* renamed from: k */
    public void mo11903k() {
        try {
            String str = new String(C0990bv.m1428b(false, C0983bq.m1404a(), C0981bo.m1354a(C0980bn.m1345a(this.f1452b, ".sign").getBytes())));
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                try {
                    this.f1467q = jSONObject.getString("sign");
                } catch (Exception e) {
                    C0954ba.m1191c().mo11629b((Throwable) e);
                }
                try {
                    this.f1466p = jSONObject.getString("ver");
                } catch (Exception e2) {
                    C0954ba.m1191c().mo11629b((Throwable) e2);
                }
            }
        } catch (Exception e3) {
            C0954ba.m1191c().mo11629b((Throwable) e3);
        }
    }

    /* renamed from: a */
    public void mo11890a(String str) {
        C0980bn.m1347a(this.f1452b, ".config2", str, false);
        mo11902j();
    }

    /* renamed from: b */
    public void mo11893b(String str) {
        C0980bn.m1347a(this.f1452b, ".sign", str, false);
        mo11903k();
    }

    /* renamed from: a */
    public long mo11888a(C1051g gVar) {
        long j = gVar.f1418j;
        try {
            String gVar2 = gVar.toString();
            if (this.f1453c.has(gVar2)) {
                j = this.f1453c.getLong(gVar2);
            }
        } catch (Exception e) {
            C0954ba.m1191c().mo11626a((Throwable) e);
        }
        return m1797b(j);
    }

    /* renamed from: a */
    public void mo11889a(C1051g gVar, long j) {
        gVar.f1418j = j;
        try {
            this.f1453c.put(gVar.toString(), j);
        } catch (Exception e) {
            C0954ba.m1191c().mo11626a((Throwable) e);
        }
        try {
            C0980bn.m1347a(this.f1452b, C1087y.f1472d, this.f1453c.toString(), false);
        } catch (Exception e2) {
            C0954ba.m1191c().mo11626a((Throwable) e2);
        }
    }

    /* renamed from: l */
    public boolean mo11904l() {
        long currentTimeMillis = System.currentTimeMillis();
        long a = mo11888a(C1051g.LAST_SEND);
        long d = mo11896d();
        C0954ba c = C0954ba.m1191c();
        c.mo11624a("canSend now=" + currentTimeMillis + ";lastSendTime=" + a + ";sendLogTimeInterval=" + d);
        return currentTimeMillis - a > d || !mo11892a(a);
    }

    /* renamed from: a */
    public boolean mo11892a(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(Long.valueOf(j)).equals(simpleDateFormat.format(Long.valueOf(System.currentTimeMillis())));
    }

    /* renamed from: b */
    private long m1797b(long j) {
        if (j - System.currentTimeMillis() > 0) {
            return 0;
        }
        return j;
    }
}
