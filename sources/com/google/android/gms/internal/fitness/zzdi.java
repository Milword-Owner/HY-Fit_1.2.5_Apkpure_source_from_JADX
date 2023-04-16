package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzdi extends zzag {
    private final /* synthetic */ DataUpdateListenerRegistrationRequest zzpm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdi(zzde zzde, GoogleApiClient googleApiClient, DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest) {
        super(googleApiClient);
        this.zzpm = dataUpdateListenerRegistrationRequest;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbu) ((zzad) anyClient).getService()).zza(new DataUpdateListenerRegistrationRequest(this.zzpm, (IBinder) new zzei(this)));
    }
}
