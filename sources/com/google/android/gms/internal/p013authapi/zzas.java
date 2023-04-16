package com.google.android.gms.internal.p013authapi;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.auth.api.identity.zzl;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;

/* renamed from: com.google.android.gms.internal.auth-api.zzas */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
final class zzas extends Api.AbstractClientBuilder<zzaw, zzl> {
    zzas() {
    }

    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        return new zzaw(context, looper, (zzl) obj, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }
}
