package com.google.android.gms.internal.p013authapi;

import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.Status;

/* renamed from: com.google.android.gms.internal.auth-api.zzg */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public final class zzg implements CredentialRequestResult {
    private final Status mStatus;
    @Nullable
    private final Credential zzam;

    public zzg(Status status, @Nullable Credential credential) {
        this.mStatus = status;
        this.zzam = credential;
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    @Nullable
    public final Credential getCredential() {
        return this.zzam;
    }

    public static zzg zzc(Status status) {
        return new zzg(status, (Credential) null);
    }
}
