package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.zzz;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzdb extends zzx {
    zzdb(zzcw zzcw, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbq) ((zzq) anyClient).getService()).zza(new zzz((zzcn) new zzei(this)));
    }
}
