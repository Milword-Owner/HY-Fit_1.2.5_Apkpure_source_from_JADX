package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.GoalsReadRequest;
import com.google.android.gms.fitness.result.GoalsResult;
import java.util.Collections;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzdc extends zzab<GoalsResult> {
    private final /* synthetic */ GoalsReadRequest zzph;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdc(zzdd zzdd, GoogleApiClient googleApiClient, GoalsReadRequest goalsReadRequest) {
        super(googleApiClient);
        this.zzph = goalsReadRequest;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbs) ((zzw) anyClient).getService()).zza(new GoalsReadRequest(this.zzph, (zzbn) new zzdf(this)));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new GoalsResult(status, Collections.emptyList());
    }
}
