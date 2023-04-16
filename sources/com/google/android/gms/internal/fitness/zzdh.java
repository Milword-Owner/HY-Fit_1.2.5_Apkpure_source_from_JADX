package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.request.zzj;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzdh extends zzag {
    private final /* synthetic */ DataSet zzpk;
    private final /* synthetic */ boolean zzpl = false;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdh(zzde zzde, GoogleApiClient googleApiClient, DataSet dataSet, boolean z) {
        super(googleApiClient);
        this.zzpk = dataSet;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbu) ((zzad) anyClient).getService()).zza(new zzj(this.zzpk, (zzcn) new zzei(this), false));
    }
}
