package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.DataUpdateRequest;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzdj extends zzag {
    private final /* synthetic */ DataUpdateRequest zzpn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdj(zzde zzde, GoogleApiClient googleApiClient, DataUpdateRequest dataUpdateRequest) {
        super(googleApiClient);
        this.zzpn = dataUpdateRequest;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbu) ((zzad) anyClient).getService()).zza(new DataUpdateRequest(this.zzpn, (IBinder) new zzei(this)));
    }
}
