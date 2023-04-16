package com.google.android.gms.fitness.request;

import android.os.Looper;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.Preconditions;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzan {
    private static final zzan zzrr = new zzan();
    private final Map<ListenerHolder.ListenerKey<OnDataPointListener>, zzam> zzrs = new HashMap();

    private zzan() {
    }

    public static zzan zzx() {
        return zzrr;
    }

    public final zzam zzc(ListenerHolder<OnDataPointListener> listenerHolder) {
        zzam zzam;
        synchronized (this.zzrs) {
            ListenerHolder.ListenerKey listenerKey = (ListenerHolder.ListenerKey) Preconditions.checkNotNull(listenerHolder.getListenerKey(), "Key must not be null");
            zzam = this.zzrs.get(listenerKey);
            if (zzam == null) {
                zzam = new zzam(listenerHolder, (zzal) null);
                this.zzrs.put(listenerKey, zzam);
            }
        }
        return zzam;
    }

    public final zzam zza(OnDataPointListener onDataPointListener, Looper looper) {
        return zzc(zzc(onDataPointListener, looper));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        return r3;
     */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.fitness.request.zzam zzd(com.google.android.gms.common.api.internal.ListenerHolder<com.google.android.gms.fitness.request.OnDataPointListener> r3) {
        /*
            r2 = this;
            java.util.Map<com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey<com.google.android.gms.fitness.request.OnDataPointListener>, com.google.android.gms.fitness.request.zzam> r0 = r2.zzrs
            monitor-enter(r0)
            com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey r3 = r3.getListenerKey()     // Catch:{ all -> 0x001b }
            if (r3 != 0) goto L_0x000c
            r3 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            return r3
        L_0x000c:
            java.util.Map<com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey<com.google.android.gms.fitness.request.OnDataPointListener>, com.google.android.gms.fitness.request.zzam> r1 = r2.zzrs     // Catch:{ all -> 0x001b }
            java.lang.Object r3 = r1.remove(r3)     // Catch:{ all -> 0x001b }
            com.google.android.gms.fitness.request.zzam r3 = (com.google.android.gms.fitness.request.zzam) r3     // Catch:{ all -> 0x001b }
            if (r3 == 0) goto L_0x0019
            r3.release()     // Catch:{ all -> 0x001b }
        L_0x0019:
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            return r3
        L_0x001b:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.zzan.zzd(com.google.android.gms.common.api.internal.ListenerHolder):com.google.android.gms.fitness.request.zzam");
    }

    @Nullable
    public final zzam zzb(OnDataPointListener onDataPointListener, Looper looper) {
        return zzd(zzc(onDataPointListener, looper));
    }

    private static ListenerHolder<OnDataPointListener> zzc(OnDataPointListener onDataPointListener, Looper looper) {
        return ListenerHolders.createListenerHolder(onDataPointListener, looper, OnDataPointListener.class.getSimpleName());
    }
}
