package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.request.zzd;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzct extends zzr {
    private final /* synthetic */ String zzpe;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzct(zzco zzco, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzpe = str;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbo) ((zzk) anyClient).getService()).zza(new zzd(this.zzpe, (BleDevice) null, (zzcn) new zzei(this)));
    }
}
