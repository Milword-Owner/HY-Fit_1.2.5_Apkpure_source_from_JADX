package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.zzad;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzcr extends zzr {
    private final /* synthetic */ zzad zzpb;
    private final /* synthetic */ StartBleScanRequest zzpc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcr(zzco zzco, GoogleApiClient googleApiClient, StartBleScanRequest startBleScanRequest, zzad zzad) {
        super(googleApiClient);
        this.zzpc = startBleScanRequest;
        this.zzpb = zzad;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbo) ((zzk) anyClient).getService()).zza(new StartBleScanRequest(this.zzpc.getDataTypes(), this.zzpb, this.zzpc.getTimeoutSecs(), (zzcn) new zzei(this)));
    }
}
