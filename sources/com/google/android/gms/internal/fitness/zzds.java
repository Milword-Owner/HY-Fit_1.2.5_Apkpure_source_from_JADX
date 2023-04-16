package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.zzbm;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzds extends zzam {
    private final /* synthetic */ DataType zzpr;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzds(zzdo zzdo, GoogleApiClient googleApiClient, DataType dataType) {
        super(googleApiClient);
        this.zzpr = dataType;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbw) ((zzaj) anyClient).getService()).zza(new zzbm(this.zzpr, (DataSource) null, (zzcn) new zzei(this)));
    }
}
