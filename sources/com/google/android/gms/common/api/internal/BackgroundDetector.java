package com.google.android.gms.common.api.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
public final class BackgroundDetector implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private static final BackgroundDetector zzbe = new BackgroundDetector();
    private final AtomicBoolean zzbf = new AtomicBoolean();
    private final AtomicBoolean zzbg = new AtomicBoolean();
    @GuardedBy("sInstance")
    private final ArrayList<BackgroundStateChangeListener> zzbh = new ArrayList<>();
    @GuardedBy("sInstance")
    private boolean zzbi = false;

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    public interface BackgroundStateChangeListener {
        @KeepForSdk
        void onBackgroundStateChanged(boolean z);
    }

    @KeepForSdk
    private BackgroundDetector() {
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
    }

    @KeepForSdk
    public static BackgroundDetector getInstance() {
        return zzbe;
    }

    @KeepForSdk
    public static void initialize(Application application) {
        synchronized (zzbe) {
            if (!zzbe.zzbi) {
                application.registerActivityLifecycleCallbacks(zzbe);
                application.registerComponentCallbacks(zzbe);
                zzbe.zzbi = true;
            }
        }
    }

    @TargetApi(16)
    @KeepForSdk
    public final boolean readCurrentStateIfPossible(boolean z) {
        if (!this.zzbg.get()) {
            if (!PlatformVersion.isAtLeastJellyBean()) {
                return z;
            }
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (!this.zzbg.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                this.zzbf.set(true);
            }
        }
        return isInBackground();
    }

    @KeepForSdk
    public final boolean isInBackground() {
        return this.zzbf.get();
    }

    @KeepForSdk
    public final void addListener(BackgroundStateChangeListener backgroundStateChangeListener) {
        synchronized (zzbe) {
            this.zzbh.add(backgroundStateChangeListener);
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        boolean compareAndSet = this.zzbf.compareAndSet(true, false);
        this.zzbg.set(true);
        if (compareAndSet) {
            onBackgroundStateChanged(false);
        }
    }

    public final void onActivityResumed(Activity activity) {
        boolean compareAndSet = this.zzbf.compareAndSet(true, false);
        this.zzbg.set(true);
        if (compareAndSet) {
            onBackgroundStateChanged(false);
        }
    }

    public final void onTrimMemory(int i) {
        if (i == 20 && this.zzbf.compareAndSet(false, true)) {
            this.zzbg.set(true);
            onBackgroundStateChanged(true);
        }
    }

    private final void onBackgroundStateChanged(boolean z) {
        synchronized (zzbe) {
            ArrayList arrayList = this.zzbh;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((BackgroundStateChangeListener) obj).onBackgroundStateChanged(z);
            }
        }
    }
}
