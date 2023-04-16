package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
abstract class zzn<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzf> {
    public zzn(GoogleApiClient googleApiClient) {
        super((Api<?>) Auth.GOOGLE_SIGN_IN_API, googleApiClient);
    }

    @KeepForSdk
    public /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((Result) obj);
    }
}
