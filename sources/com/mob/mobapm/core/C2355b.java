package com.mob.mobapm.core;

import android.os.Handler;
import android.os.Message;
import com.mob.mobapm.p026b.C2345a;
import com.mob.mobapm.p030d.C2373a;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.log.NLog;
import java.util.HashMap;

/* renamed from: com.mob.mobapm.core.b */
public class C2355b implements Handler.Callback {

    /* renamed from: g */
    private static C2355b f2241g;

    /* renamed from: a */
    private Handler f2242a = MobHandlerThread.newHandler(this);

    /* renamed from: b */
    private Object f2243b = new Object();

    /* renamed from: c */
    private long f2244c;

    /* renamed from: d */
    private long f2245d;

    /* renamed from: e */
    private long f2246e;

    /* renamed from: f */
    private long f2247f;

    private C2355b() {
        new HashMap();
    }

    /* renamed from: e */
    public static synchronized C2355b m2759e() {
        C2355b bVar;
        synchronized (C2355b.class) {
            if (f2241g == null) {
                f2241g = new C2355b();
            }
            bVar = f2241g;
        }
        return bVar;
    }

    /* renamed from: a */
    public void mo29248a() {
        try {
            this.f2242a.removeCallbacksAndMessages((Object) null);
            long currentTimeMillis = System.currentTimeMillis();
            this.f2245d = currentTimeMillis;
            this.f2247f = (currentTimeMillis - this.f2244c) - this.f2246e;
            HashMap hashMap = new HashMap();
            hashMap.put("appLaunchTime", Long.valueOf(this.f2244c));
            hashMap.put("appCloseTime", Long.valueOf(this.f2245d));
            hashMap.put("appDuration", Long.valueOf(this.f2247f));
            hashMap.put("clientTime", Long.valueOf(this.f2244c));
            C2345a.m2726g(hashMap);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: b */
    public void mo29249b() {
        synchronized (this.f2243b) {
            this.f2245d = System.currentTimeMillis();
            this.f2242a.sendEmptyMessageDelayed(0, 50000);
        }
    }

    /* renamed from: c */
    public void mo29250c() {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.f2245d > 0 && this.f2244c > 0 && currentTimeMillis - this.f2245d >= 30000) {
                this.f2247f = (this.f2245d - this.f2244c) - this.f2246e;
                HashMap hashMap = new HashMap();
                hashMap.put("appLaunchTime", Long.valueOf(this.f2244c));
                hashMap.put("appCloseTime", Long.valueOf(this.f2245d));
                hashMap.put("appDuration", Long.valueOf(this.f2247f));
                hashMap.put("clientTime", Long.valueOf(this.f2244c));
                C2345a.m2724f(hashMap);
                this.f2244c = currentTimeMillis;
                this.f2245d = 0;
                this.f2246e = 0;
                this.f2247f = 0;
            } else if (this.f2245d > 0 && this.f2244c > 0 && currentTimeMillis - this.f2245d < 30000) {
                this.f2246e += currentTimeMillis - this.f2245d;
                this.f2245d = currentTimeMillis;
            } else if (this.f2244c <= 0) {
                this.f2244c = currentTimeMillis;
                this.f2246e = 0;
                this.f2247f = 0;
                this.f2245d = 0;
            }
            this.f2242a.sendEmptyMessageDelayed(0, 50000);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: d */
    public void mo29251d() {
        try {
            if (this.f2242a != null) {
                this.f2242a.removeCallbacksAndMessages((Object) null);
            }
        } catch (Throwable th) {
            NLog a = C2373a.m2807a();
            a.mo29768d("APM: stop work error: " + th, new Object[0]);
        }
    }

    public boolean handleMessage(Message message) {
        if (message.what != 0 || !C2356c.f2251e) {
            return false;
        }
        mo29249b();
        return false;
    }
}
