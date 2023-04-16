package com.google.android.gms.internal.p013authapi;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* renamed from: com.google.android.gms.internal.auth-api.zzk */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
final class zzk extends zzo<Status> {
    private final /* synthetic */ Credential zzao;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzk(zzj zzj, GoogleApiClient googleApiClient, Credential credential) {
        super(googleApiClient);
        this.zzao = credential;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }

    /* access modifiers changed from: protected */
    public final void zzc(Context context, zzx zzx) throws RemoteException {
        zzx.zzc((zzv) new zzp(this), new zzz(this.zzao));
    }
}
