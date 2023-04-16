package com.mob.mobapm.core;

import android.os.Handler;
import android.os.Message;
import com.mob.mobapm.p030d.C2373a;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.log.NLog;

/* renamed from: com.mob.mobapm.core.j */
public abstract class C2367j implements Handler.Callback {

    /* renamed from: a */
    protected Handler f2274a = MobHandlerThread.newHandler(this);

    /* renamed from: b */
    protected Object f2275b = new Object();

    /* renamed from: a */
    public void mo29264a() {
        if (this.f2274a == null) {
            this.f2274a = MobHandlerThread.newHandler(this);
        }
        this.f2274a.removeCallbacksAndMessages((Object) null);
        this.f2274a.sendEmptyMessage(0);
    }

    /* renamed from: b */
    public void mo29265b() {
        try {
            if (this.f2274a != null) {
                this.f2274a.removeCallbacksAndMessages((Object) null);
            }
        } catch (Throwable th) {
            NLog a = C2373a.m2807a();
            a.mo29768d("APM: stop work error: " + th, new Object[0]);
        }
    }

    /* renamed from: c */
    public abstract void mo29245c();

    public boolean handleMessage(Message message) {
        if (message.what != 0) {
            return false;
        }
        mo29245c();
        return false;
    }
}
