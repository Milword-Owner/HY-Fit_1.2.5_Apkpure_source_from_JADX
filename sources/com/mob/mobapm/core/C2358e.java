package com.mob.mobapm.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.mob.mobapm.core.e */
public class C2358e {

    /* renamed from: b */
    private static C2358e f2266b;

    /* renamed from: a */
    private ExecutorService f2267a = Executors.newScheduledThreadPool(5);

    private C2358e() {
    }

    /* renamed from: b */
    public static C2358e m2781b() {
        if (f2266b == null) {
            synchronized (C2358e.class) {
                if (f2266b == null) {
                    f2266b = new C2358e();
                }
            }
        }
        return f2266b;
    }

    /* renamed from: a */
    public void mo29255a(Runnable runnable) {
        this.f2267a.execute(runnable);
    }

    /* renamed from: a */
    public void mo29254a() {
        ExecutorService executorService = this.f2267a;
        if (executorService != null && !executorService.isShutdown()) {
            this.f2267a.shutdown();
        }
    }
}
