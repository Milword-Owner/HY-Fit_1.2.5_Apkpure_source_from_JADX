package com.mob.mobapm.p031e;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import com.mob.MobSDK;
import com.mob.mobapm.core.C2355b;
import com.mob.tools.utils.ActivityTracker;

/* renamed from: com.mob.mobapm.e.a */
public class C2374a {

    /* renamed from: c */
    private static C2374a f2287c;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public String f2288a = null;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public long f2289b = 0;

    /* renamed from: com.mob.mobapm.e.a$a */
    class C2375a implements ActivityTracker.Tracker {
        C2375a() {
        }

        public void onCreated(Activity activity, Bundle bundle) {
        }

        public void onDestroyed(Activity activity) {
            if (C2374a.this.f2289b > 0) {
                onStopped(activity);
            }
        }

        public void onPaused(Activity activity) {
        }

        public void onResumed(Activity activity) {
            if (C2374a.this.f2289b == 0) {
                long unused = C2374a.this.f2289b = SystemClock.elapsedRealtime();
                C2355b.m2759e().mo29250c();
            }
            String unused2 = C2374a.this.f2288a = activity.toString();
        }

        public void onSaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onStarted(Activity activity) {
        }

        public void onStopped(Activity activity) {
            if (C2374a.this.f2288a == null || activity.toString().equals(C2374a.this.f2288a.toString())) {
                long unused = C2374a.this.f2289b = 0;
                String unused2 = C2374a.this.f2288a = null;
                C2355b.m2759e().mo29248a();
            }
        }
    }

    /* renamed from: b */
    public static synchronized C2374a m2812b() {
        C2374a aVar;
        synchronized (C2374a.class) {
            if (f2287c == null) {
                f2287c = new C2374a();
            }
            aVar = f2287c;
        }
        return aVar;
    }

    /* renamed from: a */
    public void mo29274a() {
        ActivityTracker.getInstance(MobSDK.getContext()).addTracker(new C2375a());
    }
}
