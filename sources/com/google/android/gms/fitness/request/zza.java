package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.BleDevice;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zza extends zzag {
    private final ListenerHolder<BleScanCallback> zzqi;

    private zza(ListenerHolder<BleScanCallback> listenerHolder) {
        this.zzqi = (ListenerHolder) Preconditions.checkNotNull(listenerHolder);
    }

    public final void onDeviceFound(BleDevice bleDevice) {
        this.zzqi.notifyListener(new zzc(this, bleDevice));
    }

    public final void onScanStopped() {
        this.zzqi.notifyListener(new zzb(this));
    }

    public final void release() {
        this.zzqi.clear();
    }

    /* synthetic */ zza(ListenerHolder listenerHolder, zzc zzc) {
        this(listenerHolder);
    }
}
