package com.google.android.gms.internal.fitness;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzgn implements zzkm {
    private final zzgk zzuh;

    public static zzgn zza(zzgk zzgk) {
        if (zzgk.zzun != null) {
            return zzgk.zzun;
        }
        return new zzgn(zzgk);
    }

    private zzgn(zzgk zzgk) {
        this.zzuh = (zzgk) zzhc.zza(zzgk, "output");
        this.zzuh.zzun = this;
    }

    public final int zzbe() {
        return zzkl.zzaea;
    }

    public final void zzo(int i, int i2) throws IOException {
        this.zzuh.zzh(i, i2);
    }

    public final void zzi(int i, long j) throws IOException {
        this.zzuh.zza(i, j);
    }

    public final void zzj(int i, long j) throws IOException {
        this.zzuh.zzc(i, j);
    }

    public final void zza(int i, float f) throws IOException {
        this.zzuh.zza(i, f);
    }

    public final void zza(int i, double d) throws IOException {
        this.zzuh.zza(i, d);
    }

    public final void zzp(int i, int i2) throws IOException {
        this.zzuh.zze(i, i2);
    }

    public final void zza(int i, long j) throws IOException {
        this.zzuh.zza(i, j);
    }

    public final void zze(int i, int i2) throws IOException {
        this.zzuh.zze(i, i2);
    }

    public final void zzc(int i, long j) throws IOException {
        this.zzuh.zzc(i, j);
    }

    public final void zzh(int i, int i2) throws IOException {
        this.zzuh.zzh(i, i2);
    }

    public final void zza(int i, boolean z) throws IOException {
        this.zzuh.zza(i, z);
    }

    public final void zza(int i, String str) throws IOException {
        this.zzuh.zza(i, str);
    }

    public final void zza(int i, zzfx zzfx) throws IOException {
        this.zzuh.zza(i, zzfx);
    }

    public final void zzf(int i, int i2) throws IOException {
        this.zzuh.zzf(i, i2);
    }

    public final void zzg(int i, int i2) throws IOException {
        this.zzuh.zzg(i, i2);
    }

    public final void zzb(int i, long j) throws IOException {
        this.zzuh.zzb(i, j);
    }

    public final void zza(int i, Object obj, zzja zzja) throws IOException {
        this.zzuh.zza(i, (zzik) obj, zzja);
    }

    public final void zzb(int i, Object obj, zzja zzja) throws IOException {
        zzgk zzgk = this.zzuh;
        zzgk.writeTag(i, 3);
        zzja.zza((zzik) obj, zzgk.zzun);
        zzgk.writeTag(i, 4);
    }

    public final void zzaa(int i) throws IOException {
        this.zzuh.writeTag(i, 3);
    }

    public final void zzab(int i) throws IOException {
        this.zzuh.writeTag(i, 4);
    }

    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzfx) {
            this.zzuh.zzb(i, (zzfx) obj);
        } else {
            this.zzuh.zza(i, (zzik) obj);
        }
    }

    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzuh.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzs(list.get(i4).intValue());
            }
            this.zzuh.zzo(i3);
            while (i2 < list.size()) {
                this.zzuh.zzn(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzuh.zze(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzuh.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzv(list.get(i4).intValue());
            }
            this.zzuh.zzo(i3);
            while (i2 < list.size()) {
                this.zzuh.zzq(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzuh.zzh(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzuh.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzd(list.get(i4).longValue());
            }
            this.zzuh.zzo(i3);
            while (i2 < list.size()) {
                this.zzuh.zza(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzuh.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzuh.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zze(list.get(i4).longValue());
            }
            this.zzuh.zzo(i3);
            while (i2 < list.size()) {
                this.zzuh.zza(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzuh.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzuh.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzg(list.get(i4).longValue());
            }
            this.zzuh.zzo(i3);
            while (i2 < list.size()) {
                this.zzuh.zzc(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzuh.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzuh.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzc(list.get(i4).floatValue());
            }
            this.zzuh.zzo(i3);
            while (i2 < list.size()) {
                this.zzuh.zzb(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzuh.zza(i, list.get(i2).floatValue());
            i2++;
        }
    }

    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzuh.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzc(list.get(i4).doubleValue());
            }
            this.zzuh.zzo(i3);
            while (i2 < list.size()) {
                this.zzuh.zzb(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzuh.zza(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzuh.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzx(list.get(i4).intValue());
            }
            this.zzuh.zzo(i3);
            while (i2 < list.size()) {
                this.zzuh.zzn(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzuh.zze(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzuh.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzb(list.get(i4).booleanValue());
            }
            this.zzuh.zzo(i3);
            while (i2 < list.size()) {
                this.zzuh.zza(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzuh.zza(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzhr) {
            zzhr zzhr = (zzhr) list;
            while (i2 < list.size()) {
                Object zzaf = zzhr.zzaf(i2);
                if (zzaf instanceof String) {
                    this.zzuh.zza(i, (String) zzaf);
                } else {
                    this.zzuh.zza(i, (zzfx) zzaf);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzuh.zza(i, list.get(i2));
            i2++;
        }
    }

    public final void zzb(int i, List<zzfx> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzuh.zza(i, list.get(i2));
        }
    }

    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzuh.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzt(list.get(i4).intValue());
            }
            this.zzuh.zzo(i3);
            while (i2 < list.size()) {
                this.zzuh.zzo(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzuh.zzf(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzuh.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzw(list.get(i4).intValue());
            }
            this.zzuh.zzo(i3);
            while (i2 < list.size()) {
                this.zzuh.zzq(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzuh.zzh(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzuh.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzh(list.get(i4).longValue());
            }
            this.zzuh.zzo(i3);
            while (i2 < list.size()) {
                this.zzuh.zzc(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzuh.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzuh.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzu(list.get(i4).intValue());
            }
            this.zzuh.zzo(i3);
            while (i2 < list.size()) {
                this.zzuh.zzp(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzuh.zzg(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzuh.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzgk.zzf(list.get(i4).longValue());
            }
            this.zzuh.zzo(i3);
            while (i2 < list.size()) {
                this.zzuh.zzb(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzuh.zzb(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zza(int i, List<?> list, zzja zzja) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, (Object) list.get(i2), zzja);
        }
    }

    public final void zzb(int i, List<?> list, zzja zzja) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, (Object) list.get(i2), zzja);
        }
    }

    public final <K, V> void zza(int i, zzib<K, V> zzib, Map<K, V> map) throws IOException {
        for (Map.Entry next : map.entrySet()) {
            this.zzuh.writeTag(i, 2);
            this.zzuh.zzo(zzgt.zza(zzib.zzzt, 1, next.getKey()) + zzgt.zza(zzib.zzzu, 2, next.getValue()));
            zzgk zzgk = this.zzuh;
            Object key = next.getKey();
            Object value = next.getValue();
            zzgt.zza(zzgk, zzib.zzzt, 1, key);
            zzgt.zza(zzgk, zzib.zzzu, 2, value);
        }
    }
}
