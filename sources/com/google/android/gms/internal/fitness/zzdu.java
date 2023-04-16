package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzdu extends zzcf {
    private final BaseImplementation.ResultHolder<ListSubscriptionsResult> zzpa;

    private zzdu(BaseImplementation.ResultHolder<ListSubscriptionsResult> resultHolder) {
        this.zzpa = resultHolder;
    }

    public final void zza(ListSubscriptionsResult listSubscriptionsResult) {
        this.zzpa.setResult(listSubscriptionsResult);
    }

    /* synthetic */ zzdu(BaseImplementation.ResultHolder resultHolder, zzdr zzdr) {
        this(resultHolder);
    }
}
