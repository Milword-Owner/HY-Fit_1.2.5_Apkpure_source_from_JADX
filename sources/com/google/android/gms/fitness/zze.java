package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.result.DataTypeResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final /* synthetic */ class zze implements PendingResultUtil.ResultConverter {
    static final PendingResultUtil.ResultConverter zzjz = new zze();

    private zze() {
    }

    public final Object convert(Result result) {
        return ConfigClient.zzb((DataTypeResult) result);
    }
}
