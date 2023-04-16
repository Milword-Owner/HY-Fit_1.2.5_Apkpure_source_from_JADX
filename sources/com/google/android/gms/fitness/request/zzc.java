package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.fitness.data.BleDevice;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzc implements ListenerHolder.Notifier<BleScanCallback> {
    private final /* synthetic */ BleDevice zzqj;

    zzc(zza zza, BleDevice bleDevice) {
        this.zzqj = bleDevice;
    }

    public final void onNotifyListenerFailed() {
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((BleScanCallback) obj).onDeviceFound(this.zzqj);
    }
}
