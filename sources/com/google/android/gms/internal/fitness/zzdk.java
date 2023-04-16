package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzdk extends zzae<DataReadResult> {
    private final /* synthetic */ DataReadRequest zzpo;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdk(zzde zzde, GoogleApiClient googleApiClient, DataReadRequest dataReadRequest) {
        super(googleApiClient);
        this.zzpo = dataReadRequest;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbu) ((zzad) anyClient).getService()).zza(new DataReadRequest(this.zzpo, (zzbc) new zzdp(this, (zzdh) null)));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return DataReadResult.zza(status, this.zzpo.getDataTypes(), this.zzpo.getDataSources());
    }
}
