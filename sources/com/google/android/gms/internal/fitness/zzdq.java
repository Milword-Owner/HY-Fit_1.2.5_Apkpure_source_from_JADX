package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.zzak;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzdq extends zzak<ListSubscriptionsResult> {
    private final /* synthetic */ DataType zzpr;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdq(zzdo zzdo, GoogleApiClient googleApiClient, DataType dataType) {
        super(googleApiClient);
        this.zzpr = dataType;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbw) ((zzaj) anyClient).getService()).zza(new zzak(this.zzpr, (zzcc) new zzdu(this, (zzdr) null)));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return ListSubscriptionsResult.zzd(status);
    }
}
