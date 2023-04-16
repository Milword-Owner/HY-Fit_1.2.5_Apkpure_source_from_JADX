package com.google.android.gms.internal.fitness;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zziv {
    private static final zziv zzaat = new zziv();
    private final zziz zzaau = new zzhx();
    private final ConcurrentMap<Class<?>, zzja<?>> zzaav = new ConcurrentHashMap();

    public static zziv zzcy() {
        return zzaat;
    }

    public final <T> zzja<T> zze(Class<T> cls) {
        zzhc.zza(cls, "messageType");
        zzja<T> zzja = (zzja) this.zzaav.get(cls);
        if (zzja != null) {
            return zzja;
        }
        zzja<T> zzd = this.zzaau.zzd(cls);
        zzhc.zza(cls, "messageType");
        zzhc.zza(zzd, "schema");
        zzja<T> putIfAbsent = this.zzaav.putIfAbsent(cls, zzd);
        return putIfAbsent != null ? putIfAbsent : zzd;
    }

    public final <T> zzja<T> zzn(T t) {
        return zze(t.getClass());
    }

    private zziv() {
    }
}
