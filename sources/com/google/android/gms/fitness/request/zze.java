package com.google.android.gms.fitness.request;

import android.os.Looper;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.Preconditions;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zze {
    private static final zze zzqm = new zze();
    private final Map<ListenerHolder.ListenerKey<BleScanCallback>, zza> zzqn = new HashMap();

    private zze() {
    }

    public static zze zzu() {
        return zzqm;
    }

    public final zza zza(BleScanCallback bleScanCallback, Looper looper) {
        return zza(zzc(bleScanCallback, looper));
    }

    public final zza zza(ListenerHolder<BleScanCallback> listenerHolder) {
        zza zza;
        synchronized (this.zzqn) {
            ListenerHolder.ListenerKey listenerKey = (ListenerHolder.ListenerKey) Preconditions.checkNotNull(listenerHolder.getListenerKey(), "Key must not be null");
            zza = this.zzqn.get(listenerKey);
            if (zza == null) {
                zza = new zza(listenerHolder, (zzc) null);
                this.zzqn.put(listenerKey, zza);
            }
        }
        return zza;
    }

    @Nullable
    public final zza zzb(BleScanCallback bleScanCallback, Looper looper) {
        return zzb(zzc(bleScanCallback, looper));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        return r3;
     */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.fitness.request.zza zzb(com.google.android.gms.common.api.internal.ListenerHolder<com.google.android.gms.fitness.request.BleScanCallback> r3) {
        /*
            r2 = this;
            java.util.Map<com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey<com.google.android.gms.fitness.request.BleScanCallback>, com.google.android.gms.fitness.request.zza> r0 = r2.zzqn
            monitor-enter(r0)
            com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey r3 = r3.getListenerKey()     // Catch:{ all -> 0x001b }
            if (r3 != 0) goto L_0x000c
            r3 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            return r3
        L_0x000c:
            java.util.Map<com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey<com.google.android.gms.fitness.request.BleScanCallback>, com.google.android.gms.fitness.request.zza> r1 = r2.zzqn     // Catch:{ all -> 0x001b }
            java.lang.Object r3 = r1.get(r3)     // Catch:{ all -> 0x001b }
            com.google.android.gms.fitness.request.zza r3 = (com.google.android.gms.fitness.request.zza) r3     // Catch:{ all -> 0x001b }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.zze.zzb(com.google.android.gms.common.api.internal.ListenerHolder):com.google.android.gms.fitness.request.zza");
    }

    private static ListenerHolder<BleScanCallback> zzc(BleScanCallback bleScanCallback, Looper looper) {
        return ListenerHolders.createListenerHolder(bleScanCallback, looper, BleScanCallback.class.getSimpleName());
    }
}
