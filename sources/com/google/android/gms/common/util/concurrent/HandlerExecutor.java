package com.google.android.gms.common.util.concurrent;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.common.zzi;
import java.util.concurrent.Executor;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
public class HandlerExecutor implements Executor {
    private final Handler handler;

    @KeepForSdk
    public HandlerExecutor(Looper looper) {
        this.handler = new zzi(looper);
    }

    public void execute(@NonNull Runnable runnable) {
        this.handler.post(runnable);
    }
}
