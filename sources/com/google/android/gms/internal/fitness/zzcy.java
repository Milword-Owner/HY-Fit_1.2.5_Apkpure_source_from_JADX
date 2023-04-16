package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.zzr;
import com.google.android.gms.fitness.result.DataTypeResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzcy extends zzv<DataTypeResult> {
    private final /* synthetic */ String zzpf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcy(zzcw zzcw, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzpf = str;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbq) ((zzq) anyClient).getService()).zza(new zzr(this.zzpf, (zzbi) new zzda(this, (zzcz) null)));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return DataTypeResult.zzc(status);
    }
}
