package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.DataSourcesResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzl extends zzbg {
    private final BaseImplementation.ResultHolder<DataSourcesResult> zzpa;

    public zzl(BaseImplementation.ResultHolder<DataSourcesResult> resultHolder) {
        this.zzpa = resultHolder;
    }

    public final void zza(DataSourcesResult dataSourcesResult) {
        this.zzpa.setResult(dataSourcesResult);
    }
}
