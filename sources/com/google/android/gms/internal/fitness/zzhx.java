package com.google.android.gms.internal.fitness;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzhx implements zziz {
    private static final zzih zzzp = new zzia();
    private final zzih zzzo;

    public zzhx() {
        this(new zzhz(zzgz.zzbv(), zzcl()));
    }

    private zzhx(zzih zzih) {
        this.zzzo = (zzih) zzhc.zza(zzih, "messageInfoFactory");
    }

    public final <T> zzja<T> zzd(Class<T> cls) {
        zzjc.zzf((Class<?>) cls);
        zzii zzc = this.zzzo.zzc(cls);
        if (zzc.zzct()) {
            if (zzgy.class.isAssignableFrom(cls)) {
                return zzin.zza(zzjc.zzdj(), zzgq.zzbh(), zzc.zzcu());
            }
            return zzin.zza(zzjc.zzdh(), zzgq.zzbi(), zzc.zzcu());
        } else if (zzgy.class.isAssignableFrom(cls)) {
            if (zza(zzc)) {
                return zzio.zza(cls, zzc, zzir.zzcw(), zzhu.zzck(), zzjc.zzdj(), zzgq.zzbh(), zzif.zzcq());
            }
            return zzio.zza(cls, zzc, zzir.zzcw(), zzhu.zzck(), zzjc.zzdj(), (zzgo<?>) null, zzif.zzcq());
        } else if (zza(zzc)) {
            return zzio.zza(cls, zzc, zzir.zzcv(), zzhu.zzcj(), zzjc.zzdh(), zzgq.zzbi(), zzif.zzcp());
        } else {
            return zzio.zza(cls, zzc, zzir.zzcv(), zzhu.zzcj(), zzjc.zzdi(), (zzgo<?>) null, zzif.zzcp());
        }
    }

    private static boolean zza(zzii zzii) {
        return zzii.zzcs() == zziw.zzaaw;
    }

    private static zzih zzcl() {
        try {
            return (zzih) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception unused) {
            return zzzp;
        }
    }
}
