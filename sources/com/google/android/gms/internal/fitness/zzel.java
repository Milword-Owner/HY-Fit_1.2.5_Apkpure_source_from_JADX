package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzel implements BaseImplementation.ResultHolder<Status> {
    private final /* synthetic */ TaskCompletionSource zzqg;

    zzel(TaskCompletionSource taskCompletionSource) {
        this.zzqg = taskCompletionSource;
    }

    public final void setFailedResult(Status status) {
        throw new UnsupportedOperationException("This method should never get invoked");
    }

    public final /* synthetic */ void setResult(Object obj) {
        TaskUtil.setResultOrApiException((Status) obj, this.zzqg);
    }
}
