package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public class RegistrationMethods<A extends Api.AnyClient, L> {
    public final RegisterListenerMethod<A, L> zaka;
    public final UnregisterListenerMethod<A, L> zakb;

    private RegistrationMethods(RegisterListenerMethod<A, L> registerListenerMethod, UnregisterListenerMethod<A, L> unregisterListenerMethod) {
        this.zaka = registerListenerMethod;
        this.zakb = unregisterListenerMethod;
    }

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    public static class Builder<A extends Api.AnyClient, L> {
        private boolean zajw;
        /* access modifiers changed from: private */
        public RemoteCall<A, TaskCompletionSource<Void>> zake;
        /* access modifiers changed from: private */
        public RemoteCall<A, TaskCompletionSource<Boolean>> zakf;
        private ListenerHolder<L> zakg;
        private Feature[] zakh;

        private Builder() {
            this.zajw = true;
        }

        @KeepForSdk
        @Deprecated
        public Builder<A, L> register(BiConsumer<A, TaskCompletionSource<Void>> biConsumer) {
            this.zake = new zaby(biConsumer);
            return this;
        }

        @KeepForSdk
        @Deprecated
        public Builder<A, L> unregister(BiConsumer<A, TaskCompletionSource<Boolean>> biConsumer) {
            this.zake = new zabx(this);
            return this;
        }

        @KeepForSdk
        public Builder<A, L> register(RemoteCall<A, TaskCompletionSource<Void>> remoteCall) {
            this.zake = remoteCall;
            return this;
        }

        @KeepForSdk
        public Builder<A, L> unregister(RemoteCall<A, TaskCompletionSource<Boolean>> remoteCall) {
            this.zakf = remoteCall;
            return this;
        }

        @KeepForSdk
        public Builder<A, L> withHolder(ListenerHolder<L> listenerHolder) {
            this.zakg = listenerHolder;
            return this;
        }

        @KeepForSdk
        public Builder<A, L> setFeatures(Feature... featureArr) {
            this.zakh = featureArr;
            return this;
        }

        @KeepForSdk
        public Builder<A, L> setAutoResolveMissingFeatures(boolean z) {
            this.zajw = z;
            return this;
        }

        @KeepForSdk
        public RegistrationMethods<A, L> build() {
            boolean z = true;
            Preconditions.checkArgument(this.zake != null, "Must set register function");
            Preconditions.checkArgument(this.zakf != null, "Must set unregister function");
            if (this.zakg == null) {
                z = false;
            }
            Preconditions.checkArgument(z, "Must set holder");
            return new RegistrationMethods<>(new zaca(this, this.zakg, this.zakh, this.zajw), new zabz(this, this.zakg.getListenerKey()));
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void zaa(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
            this.zake.accept(anyClient, taskCompletionSource);
        }
    }

    @KeepForSdk
    public static <A extends Api.AnyClient, L> Builder<A, L> builder() {
        return new Builder<>();
    }
}
