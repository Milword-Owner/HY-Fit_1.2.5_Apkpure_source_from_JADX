package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public final class zabv {
    public final RegisterListenerMethod<Api.AnyClient, ?> zakc;
    public final UnregisterListenerMethod<Api.AnyClient, ?> zakd;

    public zabv(@NonNull RegisterListenerMethod<Api.AnyClient, ?> registerListenerMethod, @NonNull UnregisterListenerMethod<Api.AnyClient, ?> unregisterListenerMethod) {
        this.zakc = registerListenerMethod;
        this.zakd = unregisterListenerMethod;
    }
}
