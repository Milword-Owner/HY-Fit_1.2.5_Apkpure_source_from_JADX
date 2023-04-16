package com.google.android.gms.internal.p013authapi;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

/* renamed from: com.google.android.gms.internal.auth-api.zzl */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
final class zzl extends zzh {
    private final /* synthetic */ zzi zzap;

    zzl(zzi zzi) {
        this.zzap = zzi;
    }

    public final void zzc(Status status, Credential credential) {
        this.zzap.setResult(new zzg(status, credential));
    }

    public final void zzd(Status status) {
        this.zzap.setResult(zzg.zzc(status));
    }
}
