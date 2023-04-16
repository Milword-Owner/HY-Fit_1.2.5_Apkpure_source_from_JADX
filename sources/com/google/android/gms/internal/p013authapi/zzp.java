package com.google.android.gms.internal.p013authapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* renamed from: com.google.android.gms.internal.auth-api.zzp */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
final class zzp extends zzh {
    private BaseImplementation.ResultHolder<Status> zzaq;

    zzp(BaseImplementation.ResultHolder<Status> resultHolder) {
        this.zzaq = resultHolder;
    }

    public final void zzd(Status status) {
        this.zzaq.setResult(status);
    }
}
