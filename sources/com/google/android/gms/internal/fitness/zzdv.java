package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.zzbm;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzdv extends zzam {
    private final /* synthetic */ DataSource zzpw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdv(zzdo zzdo, GoogleApiClient googleApiClient, DataSource dataSource) {
        super(googleApiClient);
        this.zzpw = dataSource;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbw) ((zzaj) anyClient).getService()).zza(new zzbm((DataType) null, this.zzpw, (zzcn) new zzei(this)));
    }
}
