package com.mob.mobapm.core;

import android.os.Message;
import com.mob.mobapm.p026b.C2345a;
import com.mob.mobapm.p030d.C2373a;
import com.mob.tools.log.NLog;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.mob.mobapm.core.a */
public class C2353a extends C2367j {

    /* renamed from: c */
    private static C2353a f2239c;

    /* renamed from: com.mob.mobapm.core.a$a */
    class C2354a implements Runnable {
        C2354a() {
        }

        public void run() {
            try {
                List<HashMap<String, Object>> f = C2345a.m2723f();
                if (f != null && !f.isEmpty()) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("records", f);
                    Object b = C2357d.m2774b(hashMap);
                    NLog a = C2373a.m2807a();
                    a.mo29768d("APM: upload app running time success. object:" + b, new Object[0]);
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    obtain.obj = f;
                    C2353a.this.f2274a.sendMessage(obtain);
                }
            } catch (Throwable th) {
                NLog a2 = C2373a.m2807a();
                a2.mo29775i("APM: upload transaction has error:" + th, new Object[0]);
            }
            C2353a.this.f2274a.sendEmptyMessageDelayed(0, (long) (C2356c.f2250d * 1000));
        }
    }

    private C2353a() {
        try {
            HashMap<String, Object> g = C2345a.m2725g();
            if (g != null && !g.isEmpty()) {
                C2345a.m2724f(g);
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    private void m2756a(List<HashMap<String, Object>> list) {
        if (list != null && !list.isEmpty()) {
            C2345a.m2716b(list);
        }
    }

    /* renamed from: d */
    public static synchronized C2353a m2757d() {
        C2353a aVar;
        synchronized (C2353a.class) {
            if (f2239c == null) {
                f2239c = new C2353a();
            }
            aVar = f2239c;
        }
        return aVar;
    }

    /* renamed from: c */
    public void mo29245c() {
        C2358e.m2781b().mo29255a(new C2354a());
    }

    public boolean handleMessage(Message message) {
        try {
            int i = message.what;
            if (i != 0) {
                if (i != 1) {
                    return false;
                }
                m2756a((List) message.obj);
                return false;
            } else if (!C2356c.f2251e) {
                return false;
            } else {
                mo29245c();
                return false;
            }
        } catch (Throwable unused) {
            return false;
        }
    }
}
