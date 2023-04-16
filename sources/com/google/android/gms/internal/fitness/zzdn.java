package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.zzf;
import com.google.android.gms.fitness.result.DailyTotalResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzdn extends zzae<DailyTotalResult> {
    private final /* synthetic */ DataType zzpr;
    private final /* synthetic */ boolean zzps;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdn(zzde zzde, GoogleApiClient googleApiClient, DataType dataType, boolean z) {
        super(googleApiClient);
        this.zzpr = dataType;
        this.zzps = z;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbu) ((zzad) anyClient).getService()).zza(new zzf((zzbb) new zzdm(this), this.zzpr, this.zzps));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return DailyTotalResult.zza(status, this.zzpr);
    }
}
