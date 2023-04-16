package com.mob.mobapm;

import com.mob.mobapm.core.C2356c;
import com.mob.tools.proguard.ClassKeeper;

public class MobAPM implements ClassKeeper {
    public static boolean goldenKey = false;
    private static C2356c impl;

    static {
        ensureInit();
    }

    private static void ensureInit() {
        if (impl == null) {
            synchronized (MobAPM.class) {
                if (impl == null) {
                    impl = new C2356c();
                }
            }
        }
    }

    public static void setJson(String str) {
        ensureInit();
        impl.mo29253a(str);
    }
}
