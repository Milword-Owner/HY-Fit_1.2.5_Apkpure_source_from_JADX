package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzei extends zzcm {
    private final BaseImplementation.ResultHolder<Status> zzpa;

    public zzei(BaseImplementation.ResultHolder<Status> resultHolder) {
        this.zzpa = resultHolder;
    }

    public final void onResult(Status status) {
        this.zzpa.setResult(status);
    }

    public static zzei zza(TaskCompletionSource<Void> taskCompletionSource) {
        return new zzei(new zzel(taskCompletionSource));
    }

    public static zzei zzb(TaskCompletionSource<Boolean> taskCompletionSource) {
        return new zzei(new zzek(taskCompletionSource));
    }
}
