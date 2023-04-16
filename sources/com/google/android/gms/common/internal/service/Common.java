package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public final class Common {
    @KeepForSdk
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>("Common.API", zapv, CLIENT_KEY);
    @KeepForSdk
    public static final Api.ClientKey<zah> CLIENT_KEY = new Api.ClientKey<>();
    private static final Api.AbstractClientBuilder<zah, Api.ApiOptions.NoOptions> zapv = new zac();
    public static final zab zapw = new zae();
}
