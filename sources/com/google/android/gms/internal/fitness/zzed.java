package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.zzba;
import com.google.android.gms.fitness.result.SessionStopResult;
import java.util.Collections;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzed extends zzaw<SessionStopResult> {
    private final /* synthetic */ String zzqd = null;
    private final /* synthetic */ String zzqe;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzed(zzeb zzeb, GoogleApiClient googleApiClient, String str, String str2) {
        super(googleApiClient);
        this.zzqe = str2;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzca) ((zzav) anyClient).getService()).zza(new zzba((String) null, this.zzqe, (zzci) new zzej(this, (zzea) null)));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new SessionStopResult(status, Collections.emptyList());
    }
}
