package com.mob.mobapm.core;

import android.content.ContentValues;
import com.mob.MobSDK;
import com.mob.mobapm.bean.SocketTransaction;
import com.mob.mobapm.p026b.C2349c;
import com.mob.mobapm.p030d.C2373a;
import com.mob.mobapm.p031e.C2383h;
import com.mob.tools.log.NLog;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.mob.mobapm.core.h */
public class C2363h extends C2367j {

    /* renamed from: c */
    private static C2363h f2270c;

    /* renamed from: com.mob.mobapm.core.h$a */
    class C2364a implements Runnable {
        C2364a() {
        }

        public void run() {
            int intValue;
            synchronized (C2363h.this.f2275b) {
                List<HashMap<String, Object>> a = C2349c.m2739a(MobSDK.getContext()).mo29171a(new String[]{"Id", "sockets"}, (String) null, (String[]) null, (String) null, (String) null, (String) null);
                if (a != null) {
                    if (!a.isEmpty()) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("records", a);
                        try {
                            Object d = C2357d.m2777d(hashMap);
                            NLog a2 = C2373a.m2807a();
                            a2.mo29768d("APM: upload transaction success. object:" + d, new Object[0]);
                            if ((d instanceof HashMap) && ((intValue = ((Integer) ((HashMap) d).get("code")).intValue()) == 200 || intValue == 4131002)) {
                                C2349c.m2739a(MobSDK.getContext()).mo29172a();
                            }
                        } catch (Throwable th) {
                            NLog a3 = C2373a.m2807a();
                            a3.mo29775i("APM: upload socketTransaction has error:" + th, new Object[0]);
                        }
                        C2363h.this.f2274a.sendEmptyMessageDelayed(0, (long) (C2356c.f2249c * 1000));
                        return;
                    }
                }
                C2363h.this.f2274a.sendEmptyMessageDelayed(0, (long) (C2356c.f2249c * 1000));
            }
        }
    }

    private C2363h() {
    }

    /* renamed from: d */
    public static synchronized C2363h m2788d() {
        C2363h hVar;
        synchronized (C2363h.class) {
            if (f2270c == null) {
                f2270c = new C2363h();
            }
            hVar = f2270c;
        }
        return hVar;
    }

    /* renamed from: a */
    public void mo29260a(SocketTransaction socketTransaction) {
        synchronized (this.f2275b) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("sockets", C2383h.m2825a().mo29276a((Object) socketTransaction));
            C2349c.m2739a(MobSDK.getContext()).mo29173a(contentValues);
        }
    }

    /* renamed from: c */
    public void mo29245c() {
        if (C2356c.f2254h) {
            C2358e.m2781b().mo29255a(new C2364a());
        }
    }
}
