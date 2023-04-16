package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.zzv;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.request.zzap;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzdz extends zzas {
    private final /* synthetic */ SensorRequest zzkx;
    private final /* synthetic */ zzv zzpz;
    private final /* synthetic */ PendingIntent zzqa;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdz(zzdx zzdx, GoogleApiClient googleApiClient, SensorRequest sensorRequest, zzv zzv, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzkx = sensorRequest;
        this.zzpz = zzv;
        this.zzqa = pendingIntent;
    }

    /* access modifiers changed from: protected */
    public final Status zza(Status status) {
        return status;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzby) ((zzap) anyClient).getService()).zza(new zzap(this.zzkx, this.zzpz, this.zzqa, new zzei(this)));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return createFailedResult(status);
    }
}
