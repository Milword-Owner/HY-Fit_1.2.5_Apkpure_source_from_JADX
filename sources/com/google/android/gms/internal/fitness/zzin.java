package com.google.android.gms.internal.fitness;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzin<T> implements zzja<T> {
    private final boolean zzaaa;
    private final zzgo<?> zzaab;
    private final zzik zzzy;
    private final zzjs<?, ?> zzzz;

    private zzin(zzjs<?, ?> zzjs, zzgo<?> zzgo, zzik zzik) {
        this.zzzz = zzjs;
        this.zzaaa = zzgo.zze(zzik);
        this.zzaab = zzgo;
        this.zzzy = zzik;
    }

    static <T> zzin<T> zza(zzjs<?, ?> zzjs, zzgo<?> zzgo, zzik zzik) {
        return new zzin<>(zzjs, zzgo, zzik);
    }

    public final boolean equals(T t, T t2) {
        if (!this.zzzz.zzo(t).equals(this.zzzz.zzo(t2))) {
            return false;
        }
        if (this.zzaaa) {
            return this.zzaab.zzc(t).equals(this.zzaab.zzc(t2));
        }
        return true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzzz.zzo(t).hashCode();
        return this.zzaaa ? (hashCode * 53) + this.zzaab.zzc(t).hashCode() : hashCode;
    }

    public final void zzd(T t, T t2) {
        zzjc.zza(this.zzzz, t, t2);
        if (this.zzaaa) {
            zzjc.zza(this.zzaab, t, t2);
        }
    }

    public final void zza(T t, zzkm zzkm) throws IOException {
        Iterator<Map.Entry<?, Object>> it = this.zzaab.zzc(t).iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            zzgv zzgv = (zzgv) next.getKey();
            if (zzgv.zzbm() != zzkj.MESSAGE || zzgv.zzbn() || zzgv.zzbo()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (next instanceof zzhn) {
                zzkm.zza(zzgv.zzc(), (Object) ((zzhn) next).zzcg().zzam());
            } else {
                zzkm.zza(zzgv.zzc(), next.getValue());
            }
        }
        zzjs<?, ?> zzjs = this.zzzz;
        zzjs.zzc(zzjs.zzo(t), zzkm);
    }

    public final void zze(T t) {
        this.zzzz.zze(t);
        this.zzaab.zze((Object) t);
    }

    public final boolean zzl(T t) {
        return this.zzaab.zzc(t).isInitialized();
    }

    public final int zzm(T t) {
        zzjs<?, ?> zzjs = this.zzzz;
        int zzp = zzjs.zzp(zzjs.zzo(t)) + 0;
        return this.zzaaa ? zzp + this.zzaab.zzc(t).zzbk() : zzp;
    }
}
