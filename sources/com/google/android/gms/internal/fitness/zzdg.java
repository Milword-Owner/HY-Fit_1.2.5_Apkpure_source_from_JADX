package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.DataDeleteRequest;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzdg extends zzag {
    private final /* synthetic */ DataDeleteRequest zzpj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdg(zzde zzde, GoogleApiClient googleApiClient, DataDeleteRequest dataDeleteRequest) {
        super(googleApiClient);
        this.zzpj = dataDeleteRequest;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbu) ((zzad) anyClient).getService()).zza(new DataDeleteRequest(this.zzpj, (zzcn) new zzei(this)));
    }
}
