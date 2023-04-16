package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.zzbc;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzeh extends zzay {
    private final /* synthetic */ PendingIntent zzqa;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzeh(zzeb zzeb, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzqa = pendingIntent;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzca) ((zzav) anyClient).getService()).zza(new zzbc(this.zzqa, (zzcn) new zzei(this)));
    }
}
