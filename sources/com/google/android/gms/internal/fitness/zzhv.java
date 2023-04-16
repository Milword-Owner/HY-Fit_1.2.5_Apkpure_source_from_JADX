package com.google.android.gms.internal.fitness;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzhv extends zzhu {
    private zzhv() {
        super();
    }

    /* access modifiers changed from: package-private */
    public final void zza(Object obj, long j) {
        zzb(obj, j).zzar();
    }

    /* access modifiers changed from: package-private */
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzhh zzb = zzb(obj, j);
        zzhh zzb2 = zzb(obj2, j);
        int size = zzb.size();
        int size2 = zzb2.size();
        if (size > 0 && size2 > 0) {
            if (!zzb.zzaq()) {
                zzb = zzb.zzae(size2 + size);
            }
            zzb.addAll(zzb2);
        }
        if (size > 0) {
            zzb2 = zzb;
        }
        zzjy.zza(obj, j, (Object) zzb2);
    }

    private static <E> zzhh<E> zzb(Object obj, long j) {
        return (zzhh) zzjy.zzo(obj, j);
    }
}
