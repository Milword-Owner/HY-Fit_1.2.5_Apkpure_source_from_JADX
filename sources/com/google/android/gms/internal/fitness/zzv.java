package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
abstract class zzv<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzq> {
    public zzv(GoogleApiClient googleApiClient) {
        super((Api<?>) zzq.API, googleApiClient);
    }

    @KeepForSdk
    public /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((Result) obj);
    }
}
