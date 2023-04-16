package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.BleDevicesResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzcx extends zzep {
    private final BaseImplementation.ResultHolder<BleDevicesResult> zzpa;

    private zzcx(BaseImplementation.ResultHolder<BleDevicesResult> resultHolder) {
        this.zzpa = resultHolder;
    }

    public final void zza(BleDevicesResult bleDevicesResult) {
        this.zzpa.setResult(bleDevicesResult);
    }

    /* synthetic */ zzcx(BaseImplementation.ResultHolder resultHolder, zzcr zzcr) {
        this(resultHolder);
    }
}
