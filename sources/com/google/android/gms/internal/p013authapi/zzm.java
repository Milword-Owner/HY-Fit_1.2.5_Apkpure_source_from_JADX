package com.google.android.gms.internal.p013authapi;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* renamed from: com.google.android.gms.internal.auth-api.zzm */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
final class zzm extends zzo<Status> {
    zzm(zzj zzj, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }

    /* access modifiers changed from: protected */
    public final void zzc(Context context, zzx zzx) throws RemoteException {
        zzx.zzc(new zzp(this));
    }
}
