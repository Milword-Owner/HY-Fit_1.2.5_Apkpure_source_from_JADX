package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.zzay;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzea extends zzay {
    private final /* synthetic */ Session zzqb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzea(zzeb zzeb, GoogleApiClient googleApiClient, Session session) {
        super(googleApiClient);
        this.zzqb = session;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzca) ((zzav) anyClient).getService()).zza(new zzay(this.zzqb, (zzcn) new zzei(this)));
    }
}
