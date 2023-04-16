package com.google.android.gms.internal.p013authapi;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.auth.api.identity.zzf;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;

/* renamed from: com.google.android.gms.internal.auth-api.zzam */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
final class zzam extends Api.AbstractClientBuilder<zzab, zzf> {
    zzam() {
    }

    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        return new zzab(context, looper, (zzf) obj, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }
}
