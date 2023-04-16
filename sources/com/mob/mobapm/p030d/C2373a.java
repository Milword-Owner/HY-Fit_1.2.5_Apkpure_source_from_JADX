package com.mob.mobapm.p030d;

import com.mob.commons.MOBAPM;
import com.mob.commons.logcollector.DefaultLogsCollector;
import com.mob.tools.log.NLog;

/* renamed from: com.mob.mobapm.d.a */
public class C2373a {

    /* renamed from: a */
    private static NLog f2286a;

    /* renamed from: a */
    public static NLog m2807a() {
        if (f2286a == null) {
            synchronized (C2373a.class) {
                if (f2286a == null) {
                    m2808b();
                }
            }
        }
        return f2286a;
    }

    /* renamed from: b */
    public static NLog m2808b() {
        DefaultLogsCollector.get().addSDK(MOBAPM.sdkTag, MOBAPM.SDK_VERSION_CODE);
        NLog instance = NLog.getInstance(MOBAPM.sdkTag);
        f2286a = instance;
        return instance;
    }
}
