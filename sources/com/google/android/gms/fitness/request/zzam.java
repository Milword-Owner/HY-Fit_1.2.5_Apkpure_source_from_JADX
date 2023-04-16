package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.zzu;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzam extends zzu {
    private final ListenerHolder<OnDataPointListener> zzrq;

    private zzam(ListenerHolder<OnDataPointListener> listenerHolder) {
        this.zzrq = (ListenerHolder) Preconditions.checkNotNull(listenerHolder);
    }

    public final void zzc(DataPoint dataPoint) {
        this.zzrq.notifyListener(new zzal(this, dataPoint));
    }

    public final void release() {
        this.zzrq.clear();
    }

    /* synthetic */ zzam(ListenerHolder listenerHolder, zzal zzal) {
        this(listenerHolder);
    }
}
