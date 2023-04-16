package com.mob.guard;

import com.mob.commons.logcollector.DefaultLogsCollector;
import com.mob.tools.log.NLog;

/* renamed from: com.mob.guard.e */
public class C2335e {

    /* renamed from: a */
    public static String f2212a = MobGuard.getSdkTag();

    /* renamed from: b */
    private static NLog f2213b;

    /* renamed from: c */
    private static Object f2214c = new Object();

    /* renamed from: a */
    public static NLog m2674a() {
        DefaultLogsCollector defaultLogsCollector = DefaultLogsCollector.get();
        defaultLogsCollector.addSDK(f2212a, MobGuard.SDK_VERSION_CODE);
        f2213b = NLog.getInstance(f2212a);
        f2213b.setCollector(defaultLogsCollector);
        return f2213b;
    }

    /* renamed from: b */
    public static NLog m2675b() {
        if (f2213b == null) {
            synchronized (f2214c) {
                if (f2213b == null) {
                    m2674a();
                }
            }
        }
        return f2213b;
    }
}
