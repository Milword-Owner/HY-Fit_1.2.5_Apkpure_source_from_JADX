package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.zzv;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzdl extends zzag {
    private final /* synthetic */ PendingIntent zzpp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdl(zzde zzde, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzpp = pendingIntent;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbu) ((zzad) anyClient).getService()).zza(new zzv(this.zzpp, new zzei(this)));
    }
}
