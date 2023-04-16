package com.google.android.gms.internal.p013authapi;

import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.SavePasswordResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api.zzap */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
final class zzap extends zzaj {
    private final /* synthetic */ TaskCompletionSource zzbq;

    zzap(zzak zzak, TaskCompletionSource taskCompletionSource) {
        this.zzbq = taskCompletionSource;
    }

    public final void zzc(Status status, SavePasswordResult savePasswordResult) throws RemoteException {
        TaskUtil.setResultOrApiException(status, savePasswordResult, this.zzbq);
    }
}
