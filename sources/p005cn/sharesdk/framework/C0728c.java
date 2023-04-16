package p005cn.sharesdk.framework;

import com.mob.MobSDK;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.framework.c */
/* compiled from: ForbSwitchFunction */
public class C0728c {

    /* renamed from: b */
    private static volatile C0728c f290b;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public boolean f291a = false;

    private C0728c() {
        new Thread() {
            public void run() {
                try {
                    boolean unused = C0728c.this.f291a = MobSDK.isForb();
                } catch (Throwable th) {
                    SSDKLog.m645b().mo29787w(th);
                }
            }
        }.start();
    }

    /* renamed from: a */
    public static C0728c m322a() {
        synchronized (C0728c.class) {
            if (f290b == null) {
                synchronized (C0728c.class) {
                    if (f290b == null) {
                        f290b = new C0728c();
                    }
                }
            }
        }
        return f290b;
    }
}
