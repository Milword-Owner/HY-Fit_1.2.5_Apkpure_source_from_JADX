package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public abstract class ActivityLifecycleObserver {
    @KeepForSdk
    public abstract ActivityLifecycleObserver onStopCallOnce(Runnable runnable);

    @KeepForSdk
    /* renamed from: of */
    public static final ActivityLifecycleObserver m1846of(Activity activity) {
        return new zaa(activity);
    }
}
