package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzef extends zzaw<SessionReadResult> {
    private final /* synthetic */ SessionReadRequest zzqf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzef(zzeb zzeb, GoogleApiClient googleApiClient, SessionReadRequest sessionReadRequest) {
        super(googleApiClient);
        this.zzqf = sessionReadRequest;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzca) ((zzav) anyClient).getService()).zza(new SessionReadRequest(this.zzqf, (zzch) new zzeg(this, (zzea) null)));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return SessionReadResult.zze(status);
    }
}
