package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public abstract class TaskApiCall<A extends Api.AnyClient, ResultT> {
    private final Feature[] zakh;
    private final boolean zako;

    @KeepForSdk
    @Deprecated
    public TaskApiCall() {
        this.zakh = null;
        this.zako = false;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract void doExecute(A a, TaskCompletionSource<ResultT> taskCompletionSource) throws RemoteException;

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    public static class Builder<A extends Api.AnyClient, ResultT> {
        private Feature[] zakh;
        private boolean zako;
        /* access modifiers changed from: private */
        public RemoteCall<A, TaskCompletionSource<ResultT>> zakp;

        private Builder() {
            this.zako = true;
        }

        @KeepForSdk
        @Deprecated
        public Builder<A, ResultT> execute(BiConsumer<A, TaskCompletionSource<ResultT>> biConsumer) {
            this.zakp = new zaci(biConsumer);
            return this;
        }

        @KeepForSdk
        public Builder<A, ResultT> run(RemoteCall<A, TaskCompletionSource<ResultT>> remoteCall) {
            this.zakp = remoteCall;
            return this;
        }

        @KeepForSdk
        public Builder<A, ResultT> setFeatures(Feature... featureArr) {
            this.zakh = featureArr;
            return this;
        }

        @KeepForSdk
        public Builder<A, ResultT> setAutoResolveMissingFeatures(boolean z) {
            this.zako = z;
            return this;
        }

        @KeepForSdk
        public TaskApiCall<A, ResultT> build() {
            Preconditions.checkArgument(this.zakp != null, "execute parameter required");
            return new zacj(this, this.zakh, this.zako);
        }
    }

    @KeepForSdk
    private TaskApiCall(Feature[] featureArr, boolean z) {
        this.zakh = featureArr;
        this.zako = z;
    }

    @Nullable
    public final Feature[] zabr() {
        return this.zakh;
    }

    @KeepForSdk
    public boolean shouldAutoResolveMissingFeatures() {
        return this.zako;
    }

    @KeepForSdk
    public static <A extends Api.AnyClient, ResultT> Builder<A, ResultT> builder() {
        return new Builder<>();
    }
}
