package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.result.DataTypeResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final /* synthetic */ class zzd implements PendingResultUtil.ResultConverter {
    static final PendingResultUtil.ResultConverter zzjz = new zzd();

    private zzd() {
    }

    public final Object convert(Result result) {
        return ConfigClient.zza((DataTypeResult) result);
    }
}
