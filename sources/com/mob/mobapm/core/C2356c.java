package com.mob.mobapm.core;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.mobapm.MobAPM;
import com.mob.mobapm.core.p028k.C2368a;
import com.mob.mobapm.core.p029l.C2371a;
import com.mob.mobapm.p030d.C2373a;
import com.mob.mobapm.p031e.C2374a;
import com.mob.mobapm.p031e.C2376b;
import com.mob.mobapm.p031e.C2381g;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;

/* renamed from: com.mob.mobapm.core.c */
public class C2356c {

    /* renamed from: b */
    public static boolean f2248b = false;

    /* renamed from: c */
    public static int f2249c = 300;

    /* renamed from: d */
    public static int f2250d = 300;

    /* renamed from: e */
    public static boolean f2251e = false;

    /* renamed from: f */
    public static boolean f2252f = false;

    /* renamed from: g */
    public static boolean f2253g = false;

    /* renamed from: h */
    public static boolean f2254h = false;

    /* renamed from: i */
    public static boolean f2255i = false;

    /* renamed from: a */
    private String f2256a;

    /* renamed from: b */
    private void m2764b(String str) {
        HashMap fromJson;
        try {
            if (!TextUtils.isEmpty(str) && (fromJson = new Hashon().fromJson(str)) != null && !fromJson.isEmpty()) {
                Integer num = (Integer) fromJson.get("apm");
                if (num != null) {
                    f2248b = num.intValue() == 1;
                }
                if (MobSDK.isForb()) {
                    return;
                }
                if ((MobSDK.isAuth() == 2 || MobSDK.isAuth() == 1) && f2248b) {
                    MobAPM.goldenKey = true;
                    HashMap<String, Object> d = C2357d.m2778d();
                    if (d != null && !d.isEmpty()) {
                        Object obj = d.get("openSentinel");
                        if (obj != null) {
                            f2251e = ((Boolean) obj).booleanValue();
                        }
                        Object obj2 = d.get("stuckCollection");
                        if (obj2 != null) {
                            f2253g = ((Boolean) obj2).booleanValue();
                        }
                        Object obj3 = d.get("crashCollection");
                        if (obj3 != null) {
                            f2252f = ((Boolean) obj3).booleanValue();
                        }
                        Object obj4 = d.get("socketCollection");
                        if (obj4 != null) {
                            f2254h = ((Boolean) obj4).booleanValue();
                        }
                        Object obj5 = d.get("dnsCollection");
                        if (obj5 != null) {
                            f2255i = ((Boolean) obj5).booleanValue();
                        }
                        Object obj6 = d.get("apmhuh");
                        if (obj6 != null) {
                            int intValue = ((Integer) obj6).intValue();
                            f2249c = intValue;
                            if (intValue <= 0) {
                                f2249c = 300;
                            }
                        }
                        Object obj7 = d.get("apmauh");
                        if (obj7 != null) {
                            int intValue2 = ((Integer) obj7).intValue();
                            f2250d = intValue2;
                            if (intValue2 <= 0) {
                                f2250d = 300;
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            C2373a.m2807a().mo29769d(th);
        }
    }

    /* renamed from: a */
    public void mo29253a(String str) {
        if (C2376b.m2815a(MobSDK.getContext())) {
            this.f2256a = str;
            m2764b(str);
            NLog a = C2373a.m2807a();
            a.mo29775i("APM: init golden key: " + MobAPM.goldenKey + ", apmhuh: " + f2249c + ", apmauh: " + f2250d, new Object[0]);
            NLog a2 = C2373a.m2807a();
            a2.mo29775i("APM: init os: " + f2251e + ", sc: " + f2253g + ", cc: " + f2252f + ", soc: " + f2254h + ", dc: " + f2255i, new Object[0]);
            if (MobAPM.goldenKey) {
                C2381g.m2824a();
            }
            if (f2251e) {
                C2374a.m2812b().mo29274a();
                C2353a.m2757d().mo29264a();
                C2365i.m2791d().mo29264a();
                C2355b.m2759e().mo29250c();
            }
            if (f2252f) {
                new C2371a().mo29271a();
                C2361g.m2786b().mo29258a();
            }
            if (f2253g) {
                new C2368a().start();
                C2359f.m2784b().mo29256a();
            }
            if (f2254h) {
                C2363h.m2788d().mo29264a();
            }
        }
    }
}
