package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.result.BleDevicesResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final /* synthetic */ class zza implements PendingResultUtil.ResultConverter {
    static final PendingResultUtil.ResultConverter zzjz = new zza();

    private zza() {
    }

    public final Object convert(Result result) {
        return ((BleDevicesResult) result).getClaimedBleDevices();
    }
}
