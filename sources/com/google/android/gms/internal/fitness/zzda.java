package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.DataTypeResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzda extends zzbl {
    private final BaseImplementation.ResultHolder<DataTypeResult> zzpa;

    private zzda(BaseImplementation.ResultHolder<DataTypeResult> resultHolder) {
        this.zzpa = resultHolder;
    }

    public final void zzc(DataTypeResult dataTypeResult) {
        this.zzpa.setResult(dataTypeResult);
    }

    /* synthetic */ zzda(BaseImplementation.ResultHolder resultHolder, zzcz zzcz) {
        this(resultHolder);
    }
}
