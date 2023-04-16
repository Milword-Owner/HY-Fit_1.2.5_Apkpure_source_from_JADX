package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.zzv;
import com.google.android.gms.fitness.request.zzar;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzdy extends zzas {
    private final /* synthetic */ PendingIntent zzpp;
    private final /* synthetic */ zzv zzpy;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdy(zzdx zzdx, GoogleApiClient googleApiClient, zzv zzv, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzpy = zzv;
        this.zzpp = pendingIntent;
    }

    /* access modifiers changed from: protected */
    public final Status zza(Status status) {
        return status;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzby) ((zzap) anyClient).getService()).zza(new zzar(this.zzpy, this.zzpp, (zzcn) new zzei(this)));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return createFailedResult(status);
    }
}
