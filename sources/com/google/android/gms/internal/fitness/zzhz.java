package com.google.android.gms.internal.fitness;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzhz implements zzih {
    private zzih[] zzzs;

    zzhz(zzih... zzihArr) {
        this.zzzs = zzihArr;
    }

    public final boolean zzb(Class<?> cls) {
        for (zzih zzb : this.zzzs) {
            if (zzb.zzb(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzii zzc(Class<?> cls) {
        for (zzih zzih : this.zzzs) {
            if (zzih.zzb(cls)) {
                return zzih.zzc(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }
}
