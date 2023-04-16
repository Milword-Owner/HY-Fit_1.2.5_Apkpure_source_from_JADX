package com.google.android.gms.internal.fitness;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzaz extends Api.AbstractClientBuilder<zzav, Api.ApiOptions.HasGoogleSignInAccountOptions> {
    private zzaz() {
    }

    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions = (Api.ApiOptions.HasGoogleSignInAccountOptions) obj;
        return new zzav(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }
}
