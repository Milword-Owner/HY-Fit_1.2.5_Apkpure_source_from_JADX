package com.mob.mobapm.core;

import android.content.ContentValues;
import com.mob.MobSDK;
import com.mob.mobapm.p026b.C2351e;
import com.mob.mobapm.p030d.C2373a;
import com.mob.mobapm.p031e.C2383h;
import com.mob.tools.log.NLog;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.mob.mobapm.core.i */
public class C2365i extends C2367j {

    /* renamed from: c */
    private static C2365i f2272c;

    /* renamed from: com.mob.mobapm.core.i$a */
    class C2366a implements Runnable {
        C2366a() {
        }

        public void run() {
            int intValue;
            synchronized (C2365i.this.f2275b) {
                List<HashMap<String, Object>> a = C2351e.m2744a(MobSDK.getContext()).mo29176a(new String[]{"Id", "trans"}, (String) null, (String[]) null, (String) null, (String) null, (String) null);
                if (a != null) {
                    if (!a.isEmpty()) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("records", a);
                        try {
                            Object e = C2357d.m2779e(hashMap);
                            NLog a2 = C2373a.m2807a();
                            a2.mo29768d("APM: upload transaction success. object:" + e, new Object[0]);
                            if ((e instanceof HashMap) && ((intValue = ((Integer) ((HashMap) e).get("code")).intValue()) == 200 || intValue == 4131002)) {
                                C2351e.m2744a(MobSDK.getContext()).mo29177a();
                            }
                        } catch (Throwable th) {
                            NLog a3 = C2373a.m2807a();
                            a3.mo29775i("APM: upload transaction has error:" + th, new Object[0]);
                        }
                        C2365i.this.f2274a.sendEmptyMessageDelayed(0, (long) (C2356c.f2249c * 1000));
                        return;
                    }
                }
                C2365i.this.f2274a.sendEmptyMessageDelayed(0, (long) (C2356c.f2249c * 1000));
            }
        }
    }

    private C2365i() {
    }

    /* renamed from: d */
    public static synchronized C2365i m2791d() {
        C2365i iVar;
        synchronized (C2365i.class) {
            if (f2272c == null) {
                f2272c = new C2365i();
            }
            iVar = f2272c;
        }
        return iVar;
    }

    /* renamed from: a */
    public void mo29262a(Transaction transaction) {
        synchronized (this.f2275b) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("trans", C2383h.m2825a().mo29276a((Object) transaction));
            C2351e.m2744a(MobSDK.getContext()).mo29178a(contentValues);
        }
    }

    /* renamed from: c */
    public void mo29245c() {
        if (C2356c.f2251e) {
            C2358e.m2781b().mo29255a(new C2366a());
        }
    }
}
