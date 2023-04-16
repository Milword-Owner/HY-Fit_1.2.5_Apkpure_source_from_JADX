package com.google.android.gms.internal.p013authapi;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* renamed from: com.google.android.gms.internal.auth-api.zzo */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
abstract class zzo<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzq> {
    zzo(GoogleApiClient googleApiClient) {
        super((Api<?>) Auth.CREDENTIALS_API, googleApiClient);
    }

    /* access modifiers changed from: protected */
    public abstract void zzc(Context context, zzx zzx) throws DeadObjectException, RemoteException;

    /* access modifiers changed from: protected */
    public /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        zzq zzq = (zzq) anyClient;
        zzc(zzq.getContext(), (zzx) zzq.getService());
    }

    @KeepForSdk
    public /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((Result) obj);
    }
}
