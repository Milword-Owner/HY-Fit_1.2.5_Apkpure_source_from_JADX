package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.SessionStopResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzej extends zzcl {
    private final BaseImplementation.ResultHolder<SessionStopResult> zzpa;

    private zzej(BaseImplementation.ResultHolder<SessionStopResult> resultHolder) {
        this.zzpa = resultHolder;
    }

    public final void zza(SessionStopResult sessionStopResult) {
        this.zzpa.setResult(sessionStopResult);
    }

    /* synthetic */ zzej(BaseImplementation.ResultHolder resultHolder, zzea zzea) {
        this(resultHolder);
    }
}
