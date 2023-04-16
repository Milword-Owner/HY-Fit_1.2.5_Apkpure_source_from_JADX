package com.google.android.gms.internal.p013authapi;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api.zzax */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
final class zzax extends zzah {
    private final /* synthetic */ TaskCompletionSource zzbq;

    zzax(zzao zzao, TaskCompletionSource taskCompletionSource) {
        this.zzbq = taskCompletionSource;
    }

    public final void zzc(Status status, PendingIntent pendingIntent) throws RemoteException {
        TaskUtil.setResultOrApiException(status, pendingIntent, this.zzbq);
    }
}
