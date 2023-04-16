package com.mob.mobapm.core.p029l;

import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.mobapm.bean.ExceptionType;
import com.mob.mobapm.core.C2353a;
import com.mob.mobapm.core.C2355b;
import com.mob.mobapm.core.C2356c;
import com.mob.mobapm.core.C2357d;
import com.mob.mobapm.core.C2358e;
import com.mob.mobapm.core.C2365i;
import com.mob.mobapm.p026b.C2345a;
import com.mob.mobapm.p030d.C2373a;
import com.mob.mobapm.p031e.C2377c;
import com.mob.tools.log.NLog;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.mob.mobapm.core.l.a */
public class C2371a implements Thread.UncaughtExceptionHandler {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public Thread.UncaughtExceptionHandler f2282a;

    /* renamed from: com.mob.mobapm.core.l.a$a */
    class C2372a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Thread f2283a;

        /* renamed from: b */
        final /* synthetic */ Throwable f2284b;

        C2372a(Thread thread, Throwable th) {
            this.f2283a = thread;
            this.f2284b = th;
        }

        public void run() {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                HashMap hashMap = new HashMap();
                HashMap hashMap2 = new HashMap();
                hashMap2.put("type", ExceptionType.CRASH.name);
                hashMap2.put("happenTime", Long.valueOf(currentTimeMillis));
                hashMap2.put("threadName", this.f2283a.getId() + Config.replace + this.f2283a.getName());
                hashMap2.put("errType", this.f2284b.getClass().getName());
                hashMap2.put("errDesc", this.f2284b.getLocalizedMessage());
                hashMap2.put("stackDetail", C2377c.m2816a(this.f2284b));
                hashMap.put(String.valueOf(currentTimeMillis), hashMap2);
                C2345a.m2728h(hashMap);
                HashMap hashMap3 = new HashMap();
                ArrayList arrayList = new ArrayList();
                arrayList.add(hashMap2);
                hashMap3.put("bundleName", MobSDK.getContext().getPackageName());
                hashMap3.put("uploadTime", Long.valueOf(System.currentTimeMillis()));
                hashMap3.put("errorStack", arrayList);
                NLog a = C2373a.m2807a();
                a.mo29768d("APM: upload crash Object: " + hashMap3, new Object[0]);
                Object a2 = C2357d.m2771a(hashMap3, C2356c.f2252f);
                NLog a3 = C2373a.m2807a();
                a3.mo29768d("APM: upload crash result. object:" + a2, new Object[0]);
                if ((a2 instanceof HashMap) && ((Integer) ((HashMap) a2).get("code")).intValue() == 200) {
                    C2345a.m2715b((HashMap<String, Object>) hashMap2);
                }
                C2358e.m2781b().mo29254a();
                C2355b.m2759e().mo29251d();
                C2353a.m2757d().mo29265b();
                C2365i.m2791d().mo29265b();
                if (C2371a.this.f2282a == null) {
                    return;
                }
            } catch (Throwable th) {
                C2358e.m2781b().mo29254a();
                C2355b.m2759e().mo29251d();
                C2353a.m2757d().mo29265b();
                C2365i.m2791d().mo29265b();
                if (C2371a.this.f2282a != null) {
                    C2371a.this.f2282a.uncaughtException(this.f2283a, this.f2284b);
                }
                throw th;
            }
            C2371a.this.f2282a.uncaughtException(this.f2283a, this.f2284b);
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        NLog a = C2373a.m2807a();
        a.mo29768d("APM Thread: " + thread.getName() + ", Throwable: " + th + ", currentThread:" + Thread.currentThread().getName(), new Object[0]);
        if (C2356c.f2252f) {
            C2358e.m2781b().mo29255a(new C2372a(thread, th));
        }
    }

    /* renamed from: a */
    public void mo29271a() {
        this.f2282a = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
}
