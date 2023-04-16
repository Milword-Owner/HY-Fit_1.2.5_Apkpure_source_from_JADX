package com.google.android.gms.internal.fitness;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzjc {
    private static final Class<?> zzabg = zzdk();
    private static final zzjs<?, ?> zzabh = zzd(false);
    private static final zzjs<?, ?> zzabi = zzd(true);
    private static final zzjs<?, ?> zzabj = new zzju();

    public static void zzf(Class<?> cls) {
        Class<?> cls2;
        if (!zzgy.class.isAssignableFrom(cls) && (cls2 = zzabg) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzkm zzkm, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkm.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<Float> list, zzkm zzkm, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkm.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzkm zzkm, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkm.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzkm zzkm, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkm.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzkm zzkm, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkm.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzkm zzkm, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkm.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzkm zzkm, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkm.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzkm zzkm, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkm.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzkm zzkm, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkm.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzkm zzkm, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkm.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzkm zzkm, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkm.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzkm zzkm, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkm.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzkm zzkm, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkm.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzkm zzkm, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkm.zzi(i, list, z);
        }
    }

    public static void zza(int i, List<String> list, zzkm zzkm) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkm.zza(i, list);
        }
    }

    public static void zzb(int i, List<zzfx> list, zzkm zzkm) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkm.zzb(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzkm zzkm, zzja zzja) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkm.zza(i, list, zzja);
        }
    }

    public static void zzb(int i, List<?> list, zzkm zzkm, zzja zzja) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzkm.zzb(i, list, zzja);
        }
    }

    static int zzb(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhy) {
            zzhy zzhy = (zzhy) list;
            i = 0;
            while (i2 < size) {
                i += zzgk.zzd(zzhy.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzgk.zzd(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzb(list) + (list.size() * zzgk.zzr(i));
    }

    static int zzc(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhy) {
            zzhy zzhy = (zzhy) list;
            i = 0;
            while (i2 < size) {
                i += zzgk.zze(zzhy.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzgk.zze(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzc(list) + (size * zzgk.zzr(i));
    }

    static int zzd(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhy) {
            zzhy zzhy = (zzhy) list;
            i = 0;
            while (i2 < size) {
                i += zzgk.zzf(zzhy.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzgk.zzf(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzgk.zzr(i));
    }

    static int zze(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzha) {
            zzha zzha = (zzha) list;
            i = 0;
            while (i2 < size) {
                i += zzgk.zzx(zzha.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzgk.zzx(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzgk.zzr(i));
    }

    static int zzf(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzha) {
            zzha zzha = (zzha) list;
            i = 0;
            while (i2 < size) {
                i += zzgk.zzs(zzha.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzgk.zzs(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzf(list) + (size * zzgk.zzr(i));
    }

    static int zzg(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzha) {
            zzha zzha = (zzha) list;
            i = 0;
            while (i2 < size) {
                i += zzgk.zzt(zzha.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzgk.zzt(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzgk.zzr(i));
    }

    static int zzh(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzha) {
            zzha zzha = (zzha) list;
            i = 0;
            while (i2 < size) {
                i += zzgk.zzu(zzha.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzgk.zzu(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzh(list) + (size * zzgk.zzr(i));
    }

    static int zzi(List<?> list) {
        return list.size() << 2;
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzgk.zzl(i, 0);
    }

    static int zzj(List<?> list) {
        return list.size() << 3;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzgk.zzg(i, 0);
    }

    static int zzk(List<?> list) {
        return list.size();
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzgk.zzb(i, true);
    }

    static int zzc(int i, List<?> list) {
        int i2;
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int zzr = zzgk.zzr(i) * size;
        if (list instanceof zzhr) {
            zzhr zzhr = (zzhr) list;
            while (i4 < size) {
                Object zzaf = zzhr.zzaf(i4);
                if (zzaf instanceof zzfx) {
                    i3 = zzgk.zzb((zzfx) zzaf);
                } else {
                    i3 = zzgk.zzm((String) zzaf);
                }
                zzr += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzfx) {
                    i2 = zzgk.zzb((zzfx) obj);
                } else {
                    i2 = zzgk.zzm((String) obj);
                }
                zzr += i2;
                i4++;
            }
        }
        return zzr;
    }

    static int zzc(int i, Object obj, zzja zzja) {
        if (obj instanceof zzhp) {
            return zzgk.zza(i, (zzhp) obj);
        }
        return zzgk.zzb(i, (zzik) obj, zzja);
    }

    static int zzc(int i, List<?> list, zzja zzja) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzr = zzgk.zzr(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzhp) {
                i2 = zzgk.zza((zzhp) obj);
            } else {
                i2 = zzgk.zza((zzik) obj, zzja);
            }
            zzr += i2;
        }
        return zzr;
    }

    static int zzd(int i, List<zzfx> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzr = size * zzgk.zzr(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzr += zzgk.zzb(list.get(i2));
        }
        return zzr;
    }

    static int zzd(int i, List<zzik> list, zzja zzja) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzgk.zzc(i, list.get(i3), zzja);
        }
        return i2;
    }

    public static zzjs<?, ?> zzdh() {
        return zzabh;
    }

    public static zzjs<?, ?> zzdi() {
        return zzabi;
    }

    public static zzjs<?, ?> zzdj() {
        return zzabj;
    }

    private static zzjs<?, ?> zzd(boolean z) {
        try {
            Class<?> zzdl = zzdl();
            if (zzdl == null) {
                return null;
            }
            return (zzjs) zzdl.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzdk() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzdl() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zze(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static <T> void zza(zzid zzid, T t, T t2, long j) {
        zzjy.zza((Object) t, j, zzid.zzc(zzjy.zzo(t, j), zzjy.zzo(t2, j)));
    }

    static <T, FT extends zzgv<FT>> void zza(zzgo<FT> zzgo, T t, T t2) {
        zzgt<FT> zzc = zzgo.zzc(t2);
        if (!zzc.zzvb.isEmpty()) {
            zzgo.zzd(t).zza(zzc);
        }
    }

    static <T, UT, UB> void zza(zzjs<UT, UB> zzjs, T t, T t2) {
        zzjs.zzf(t, zzjs.zzg(zzjs.zzo(t), zzjs.zzo(t2)));
    }
}
