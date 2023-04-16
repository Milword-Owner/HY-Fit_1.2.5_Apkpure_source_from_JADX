package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.SessionReadResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzeg extends zzcg {
    private final BaseImplementation.ResultHolder<SessionReadResult> zzpa;

    private zzeg(BaseImplementation.ResultHolder<SessionReadResult> resultHolder) {
        this.zzpa = resultHolder;
    }

    public final void zza(SessionReadResult sessionReadResult) {
        this.zzpa.setResult(sessionReadResult);
    }

    /* synthetic */ zzeg(BaseImplementation.ResultHolder resultHolder, zzea zzea) {
        this(resultHolder);
    }
}
