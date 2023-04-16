package com.mob.commons.p023a;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.mob.MobSDK;
import com.mob.commons.C2262b;
import com.mob.commons.FBListener;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.utils.ActivityTracker;
import com.mob.tools.utils.DeviceHelper;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: com.mob.commons.a.m */
/* compiled from: FBManager */
public class C2242m {

    /* renamed from: a */
    private static C2242m f1981a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final HashSet<FBListener> f1982b = new HashSet<>();
    /* access modifiers changed from: private */

    /* renamed from: c */
    public volatile Handler f1983c = MobHandlerThread.newHandler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                if (DeviceHelper.getInstance(MobSDK.getContext()).amIOnForeground()) {
                    long unused = C2242m.this.f1985e = SystemClock.elapsedRealtime();
                    C2242m.this.m2112a(false);
                } else {
                    C2242m.this.m2108a(0, false);
                }
                C2242m.this.m2115b();
            } else if (i == 1) {
                C2242m.this.m2112a(true);
            } else if (i == 2) {
                C2242m.this.m2108a(((Long) message.obj).longValue(), true);
            } else if (i == 3) {
                try {
                    FBListener fBListener = (FBListener) message.obj;
                    if (fBListener != null) {
                        C2242m.this.f1982b.add(fBListener);
                        fBListener.onFBChanged(C2242m.this.f1985e > 0, true, 0);
                    }
                } catch (Throwable th) {
                    MobLog.getInstance().mo29769d(th);
                }
            }
            return false;
        }
    });
    /* access modifiers changed from: private */

    /* renamed from: d */
    public String f1984d = null;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public long f1985e = 0;

    /* renamed from: a */
    public static synchronized C2242m m2106a() {
        C2242m mVar;
        synchronized (C2242m.class) {
            if (f1981a == null) {
                f1981a = new C2242m();
                if (f1981a.f1983c != null) {
                    f1981a.f1983c.sendEmptyMessage(0);
                }
            }
            mVar = f1981a;
        }
        return mVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo29012a(com.mob.commons.FBListener r4) {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x0003
            return
        L_0x0003:
            java.util.HashSet<com.mob.commons.FBListener> r0 = r3.f1982b
            monitor-enter(r0)
            java.util.HashSet<com.mob.commons.FBListener> r1 = r3.f1982b     // Catch:{ all -> 0x0025 }
            boolean r1 = r1.contains(r4)     // Catch:{ all -> 0x0025 }
            if (r1 == 0) goto L_0x0010
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return
        L_0x0010:
            android.os.Handler r1 = r3.f1983c     // Catch:{ all -> 0x0025 }
            if (r1 == 0) goto L_0x0023
            android.os.Message r1 = new android.os.Message     // Catch:{ all -> 0x0025 }
            r1.<init>()     // Catch:{ all -> 0x0025 }
            r2 = 3
            r1.what = r2     // Catch:{ all -> 0x0025 }
            r1.obj = r4     // Catch:{ all -> 0x0025 }
            android.os.Handler r4 = r3.f1983c     // Catch:{ all -> 0x0025 }
            r4.sendMessage(r1)     // Catch:{ all -> 0x0025 }
        L_0x0023:
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return
        L_0x0025:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p023a.C2242m.mo29012a(com.mob.commons.FBListener):void");
    }

    /* renamed from: b */
    public void mo29013b(FBListener fBListener) {
        if (fBListener != null) {
            synchronized (this.f1982b) {
                this.f1982b.remove(fBListener);
            }
        }
    }

    private C2242m() {
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2112a(boolean z) {
        C2262b.m2263a(0, true);
        if (z) {
            m2113a(true, false, 0);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2108a(long j, boolean z) {
        C2262b.m2263a(SystemClock.elapsedRealtime(), true);
        if (z) {
            m2113a(false, false, j);
        }
    }

    /* renamed from: a */
    private void m2113a(boolean z, boolean z2, long j) {
        synchronized (this.f1982b) {
            Iterator<FBListener> it = this.f1982b.iterator();
            while (it.hasNext()) {
                it.next().onFBChanged(z, z2, j);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m2115b() {
        ActivityTracker.getInstance(MobSDK.getContext()).addTracker(new ActivityTracker.Tracker() {
            public void onCreated(Activity activity, Bundle bundle) {
            }

            public void onPaused(Activity activity) {
            }

            public void onSaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onStarted(Activity activity) {
            }

            public void onResumed(Activity activity) {
                if (C2242m.this.f1985e == 0) {
                    long unused = C2242m.this.f1985e = SystemClock.elapsedRealtime();
                    if (C2242m.this.f1983c != null) {
                        C2242m.this.f1983c.sendEmptyMessage(1);
                    }
                }
                String unused2 = C2242m.this.f1984d = activity.toString();
            }

            public void onStopped(Activity activity) {
                if (C2242m.this.f1984d == null || activity.toString().equals(C2242m.this.f1984d.toString())) {
                    if (C2242m.this.f1983c != null) {
                        long elapsedRealtime = C2242m.this.f1985e > 0 ? SystemClock.elapsedRealtime() - C2242m.this.f1985e : 0;
                        Message message = new Message();
                        message.what = 2;
                        message.obj = Long.valueOf(elapsedRealtime);
                        C2242m.this.f1983c.sendMessage(message);
                    }
                    long unused = C2242m.this.f1985e = 0;
                    String unused2 = C2242m.this.f1984d = null;
                }
            }

            public void onDestroyed(Activity activity) {
                if (C2242m.this.f1985e > 0) {
                    onStopped(activity);
                }
            }
        });
    }
}
