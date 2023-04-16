package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zacc;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public abstract class ResultTransform<R extends Result, S extends Result> {
    @NonNull
    public Status onFailure(@NonNull Status status) {
        return status;
    }

    @WorkerThread
    @Nullable
    public abstract PendingResult<S> onSuccess(@NonNull R r);

    @NonNull
    public final PendingResult<S> createFailedResult(@NonNull Status status) {
        return new zacc(status);
    }
}
