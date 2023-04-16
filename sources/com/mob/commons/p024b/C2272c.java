package com.mob.commons.p024b;

import com.mob.tools.MobLog;

/* renamed from: com.mob.commons.b.c */
/* compiled from: FidsLog */
public class C2272c {

    /* renamed from: a */
    private static final C2272c f2046a = new C2272c();

    private C2272c() {
    }

    /* renamed from: a */
    public static C2272c m2344a() {
        return f2046a;
    }

    /* renamed from: a */
    public void mo29044a(Object obj) {
        m2345b(obj);
    }

    /* renamed from: b */
    private void m2345b(Object obj) {
        try {
            if (obj instanceof Throwable) {
                MobLog.getInstance().mo29769d((Throwable) obj);
            } else {
                MobLog.getInstance().mo29768d(obj, new Object[0]);
            }
        } catch (Throwable unused) {
        }
    }
}
