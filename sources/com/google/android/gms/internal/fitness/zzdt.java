package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.fitness.request.zzbi;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzdt extends zzam {
    private final /* synthetic */ Subscription zzpv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdt(zzdo zzdo, GoogleApiClient googleApiClient, Subscription subscription) {
        super(googleApiClient);
        this.zzpv = subscription;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbw) ((zzaj) anyClient).getService()).zza(new zzbi(this.zzpv, false, (zzcn) new zzei(this)));
    }
}
