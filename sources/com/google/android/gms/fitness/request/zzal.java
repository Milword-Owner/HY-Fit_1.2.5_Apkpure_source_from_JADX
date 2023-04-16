package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.fitness.data.DataPoint;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzal implements ListenerHolder.Notifier<OnDataPointListener> {
    private final /* synthetic */ DataPoint zzrp;

    zzal(zzam zzam, DataPoint dataPoint) {
        this.zzrp = dataPoint;
    }

    public final void onNotifyListenerFailed() {
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((OnDataPointListener) obj).onDataPoint(this.zzrp);
    }
}
