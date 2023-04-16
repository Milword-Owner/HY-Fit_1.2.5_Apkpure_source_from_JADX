package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzb implements ListenerHolder.Notifier<BleScanCallback> {
    zzb(zza zza) {
    }

    public final void onNotifyListenerFailed() {
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((BleScanCallback) obj).onScanStopped();
    }
}
