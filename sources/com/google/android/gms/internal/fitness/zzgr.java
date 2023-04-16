package com.google.android.gms.internal.fitness;

import com.google.android.gms.internal.fitness.zzgy;
import java.io.IOException;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzgr extends zzgo<zzgy.zzc> {
    zzgr() {
    }

    /* access modifiers changed from: package-private */
    public final boolean zze(zzik zzik) {
        return zzik instanceof zzgy.zzd;
    }

    /* access modifiers changed from: package-private */
    public final zzgt<zzgy.zzc> zzc(Object obj) {
        return ((zzgy.zzd) obj).zzya;
    }

    /* access modifiers changed from: package-private */
    public final zzgt<zzgy.zzc> zzd(Object obj) {
        zzgy.zzd zzd = (zzgy.zzd) obj;
        if (zzd.zzya.isImmutable()) {
            zzd.zzya = (zzgt) zzd.zzya.clone();
        }
        return zzd.zzya;
    }

    /* access modifiers changed from: package-private */
    public final void zze(Object obj) {
        zzc(obj).zzar();
    }

    /* access modifiers changed from: package-private */
    public final int zza(Map.Entry<?, ?> entry) {
        zzgy.zzc zzc = (zzgy.zzc) entry.getKey();
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzkm zzkm, Map.Entry<?, ?> entry) throws IOException {
        zzgy.zzc zzc = (zzgy.zzc) entry.getKey();
        throw new NoSuchMethodError();
    }
}
