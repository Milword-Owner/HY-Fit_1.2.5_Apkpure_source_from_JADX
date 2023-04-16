package com.google.android.gms.internal.p013authapi;

import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

/* renamed from: com.google.android.gms.internal.auth-api.zzaz */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public final class zzaz {
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }
}
