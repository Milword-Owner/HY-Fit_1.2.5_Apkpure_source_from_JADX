package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.zzad;
import com.google.android.gms.fitness.request.zzbg;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzcq extends zzr {
    private final /* synthetic */ zzad zzpb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcq(zzco zzco, GoogleApiClient googleApiClient, zzad zzad) {
        super(googleApiClient);
        this.zzpb = zzad;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbo) ((zzk) anyClient).getService()).zza(new zzbg(this.zzpb, (zzcn) new zzei(this)));
    }
}
