package com.mob.guard;

import com.mob.MobSDK;
import com.mob.tools.utils.SharePrefrenceHelper;

/* renamed from: com.mob.guard.i */
public class C2341i {

    /* renamed from: a */
    private static SharePrefrenceHelper f2223a;

    /* renamed from: f */
    private static synchronized void m2705f() {
        synchronized (C2341i.class) {
            if (f2223a == null) {
                f2223a = new SharePrefrenceHelper(MobSDK.getContext());
                f2223a.open(MobGuard.getSdkTag(), 100);
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m2694a(String str) {
        synchronized (C2341i.class) {
            m2705f();
            f2223a.putString("init_id", str);
        }
    }

    /* renamed from: b */
    public static synchronized void m2697b(String str) {
        synchronized (C2341i.class) {
            m2705f();
            f2223a.putString("last_id", str);
        }
    }

    /* renamed from: a */
    public static synchronized String m2692a() {
        String string;
        synchronized (C2341i.class) {
            m2705f();
            string = f2223a.getString("key_msg");
        }
        return string;
    }

    /* renamed from: b */
    public static synchronized long m2696b() {
        long j;
        synchronized (C2341i.class) {
            m2705f();
            j = f2223a.getLong("key_tcp_time");
        }
        return j;
    }

    /* renamed from: a */
    public static synchronized void m2693a(long j) {
        synchronized (C2341i.class) {
            m2705f();
            f2223a.putLong("key_tcp_time", Long.valueOf(j));
        }
    }

    /* renamed from: c */
    public static synchronized void m2700c(String str) {
        synchronized (C2341i.class) {
            m2705f();
            f2223a.putString("key_msg", str);
        }
    }

    /* renamed from: d */
    public static synchronized void m2702d(String str) {
        synchronized (C2341i.class) {
            m2705f();
            f2223a.putString("key_guard_id", str);
        }
    }

    /* renamed from: c */
    public static synchronized String m2699c() {
        String string;
        synchronized (C2341i.class) {
            m2705f();
            string = f2223a.getString("key_guard_id");
        }
        return string;
    }

    /* renamed from: e */
    public static synchronized void m2704e(String str) {
        synchronized (C2341i.class) {
            m2705f();
            f2223a.putString("key_device_state", str);
        }
    }

    /* renamed from: d */
    public static synchronized String m2701d() {
        String string;
        synchronized (C2341i.class) {
            m2705f();
            string = f2223a.getString("key_device_state");
        }
        return string;
    }

    /* renamed from: f */
    public static synchronized void m2706f(String str) {
        synchronized (C2341i.class) {
            m2705f();
            f2223a.putString("key_work_id", str);
        }
    }

    /* renamed from: e */
    public static synchronized String m2703e() {
        String string;
        synchronized (C2341i.class) {
            m2705f();
            string = f2223a.getString("key_work_id");
        }
        return string;
    }

    /* renamed from: a */
    public static void m2695a(boolean z) {
        m2705f();
        f2223a.putBoolean("key_bc_id", Boolean.valueOf(z));
    }

    /* renamed from: b */
    public static synchronized void m2698b(boolean z) {
        synchronized (C2341i.class) {
            m2705f();
            f2223a.putBoolean("key_pull_pkg", Boolean.valueOf(z));
        }
    }
}
