package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.SessionInsertRequest;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzec extends zzay {
    private final /* synthetic */ SessionInsertRequest zzqc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzec(zzeb zzeb, GoogleApiClient googleApiClient, SessionInsertRequest sessionInsertRequest) {
        super(googleApiClient);
        this.zzqc = sessionInsertRequest;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzca) ((zzav) anyClient).getService()).zza(new SessionInsertRequest(this.zzqc, (zzcn) new zzei(this)));
    }
}
