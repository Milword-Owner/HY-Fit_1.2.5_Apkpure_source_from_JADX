package com.google.android.gms.internal.p013authapi;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* renamed from: com.google.android.gms.internal.auth-api.zzi */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
final class zzi extends zzo<CredentialRequestResult> {
    private final /* synthetic */ CredentialRequest zzan;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzi(zzj zzj, GoogleApiClient googleApiClient, CredentialRequest credentialRequest) {
        super(googleApiClient);
        this.zzan = credentialRequest;
    }

    /* access modifiers changed from: protected */
    public final void zzc(Context context, zzx zzx) throws RemoteException {
        zzx.zzc((zzv) new zzl(this), this.zzan);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return zzg.zzc(status);
    }
}
