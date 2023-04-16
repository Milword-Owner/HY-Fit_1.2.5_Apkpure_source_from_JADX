package com.mob.guard;

import com.mob.tools.log.NLog;
import com.mob.tools.utils.ReflectHelper;

/* renamed from: com.mob.guard.f */
public class C2336f {
    /* renamed from: a */
    public static boolean m2676a() {
        return m2677b();
    }

    /* renamed from: b */
    private static boolean m2677b() {
        try {
            ReflectHelper.importClass("com.mob.socketservice.ServiceMessageData");
            return true;
        } catch (Throwable th) {
            NLog b = C2335e.m2675b();
            b.mo29768d("ServiceHelper check import " + th.getMessage() + " not find.", new Object[0]);
            return false;
        }
    }
}
