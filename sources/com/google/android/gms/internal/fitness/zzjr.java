package com.google.android.gms.internal.fitness;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzjr {
    private static final zzjr zzabs = new zzjr(0, new int[0], new Object[0], false);
    private int count;
    private Object[] zzaaf;
    private int[] zzabt;
    private boolean zztv;
    private int zzxu;

    public static zzjr zzdp() {
        return zzabs;
    }

    static zzjr zza(zzjr zzjr, zzjr zzjr2) {
        int i = zzjr.count + zzjr2.count;
        int[] copyOf = Arrays.copyOf(zzjr.zzabt, i);
        System.arraycopy(zzjr2.zzabt, 0, copyOf, zzjr.count, zzjr2.count);
        Object[] copyOf2 = Arrays.copyOf(zzjr.zzaaf, i);
        System.arraycopy(zzjr2.zzaaf, 0, copyOf2, zzjr.count, zzjr2.count);
        return new zzjr(i, copyOf, copyOf2, true);
    }

    private zzjr() {
        this(0, new int[8], new Object[8], true);
    }

    private zzjr(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzxu = -1;
        this.count = i;
        this.zzabt = iArr;
        this.zzaaf = objArr;
        this.zztv = z;
    }

    public final void zzar() {
        this.zztv = false;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzkm zzkm) throws IOException {
        if (zzkm.zzbe() == zzkl.zzaeb) {
            for (int i = this.count - 1; i >= 0; i--) {
                zzkm.zza(this.zzabt[i] >>> 3, this.zzaaf[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            zzkm.zza(this.zzabt[i2] >>> 3, this.zzaaf[i2]);
        }
    }

    public final void zzb(zzkm zzkm) throws IOException {
        if (this.count != 0) {
            if (zzkm.zzbe() == zzkl.zzaea) {
                for (int i = 0; i < this.count; i++) {
                    zzb(this.zzabt[i], this.zzaaf[i], zzkm);
                }
                return;
            }
            for (int i2 = this.count - 1; i2 >= 0; i2--) {
                zzb(this.zzabt[i2], this.zzaaf[i2], zzkm);
            }
        }
    }

    private static void zzb(int i, Object obj, zzkm zzkm) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            zzkm.zzi(i2, ((Long) obj).longValue());
        } else if (i3 == 1) {
            zzkm.zzc(i2, ((Long) obj).longValue());
        } else if (i3 == 2) {
            zzkm.zza(i2, (zzfx) obj);
        } else if (i3 != 3) {
            if (i3 == 5) {
                zzkm.zzh(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzhk.zzcd());
        } else if (zzkm.zzbe() == zzkl.zzaea) {
            zzkm.zzaa(i2);
            ((zzjr) obj).zzb(zzkm);
            zzkm.zzab(i2);
        } else {
            zzkm.zzab(i2);
            ((zzjr) obj).zzb(zzkm);
            zzkm.zzaa(i2);
        }
    }

    public final int zzdq() {
        int i = this.zzxu;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            i2 += zzgk.zzd(this.zzabt[i3] >>> 3, (zzfx) this.zzaaf[i3]);
        }
        this.zzxu = i2;
        return i2;
    }

    public final int zzbp() {
        int i;
        int i2 = this.zzxu;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.count; i4++) {
            int i5 = this.zzabt[i4];
            int i6 = i5 >>> 3;
            int i7 = i5 & 7;
            if (i7 == 0) {
                i = zzgk.zze(i6, ((Long) this.zzaaf[i4]).longValue());
            } else if (i7 == 1) {
                i = zzgk.zzg(i6, ((Long) this.zzaaf[i4]).longValue());
            } else if (i7 == 2) {
                i = zzgk.zzc(i6, (zzfx) this.zzaaf[i4]);
            } else if (i7 == 3) {
                i = (zzgk.zzr(i6) << 1) + ((zzjr) this.zzaaf[i4]).zzbp();
            } else if (i7 == 5) {
                i = zzgk.zzl(i6, ((Integer) this.zzaaf[i4]).intValue());
            } else {
                throw new IllegalStateException(zzhk.zzcd());
            }
            i3 += i;
        }
        this.zzxu = i3;
        return i3;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzjr)) {
            return false;
        }
        zzjr zzjr = (zzjr) obj;
        int i = this.count;
        if (i == zzjr.count) {
            int[] iArr = this.zzabt;
            int[] iArr2 = zzjr.zzabt;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    z = true;
                    break;
                } else if (iArr[i2] != iArr2[i2]) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                Object[] objArr = this.zzaaf;
                Object[] objArr2 = zzjr.zzaaf;
                int i3 = this.count;
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        z2 = true;
                        break;
                    } else if (!objArr[i4].equals(objArr2[i4])) {
                        z2 = false;
                        break;
                    } else {
                        i4++;
                    }
                }
                return z2;
            }
        }
    }

    public final int hashCode() {
        int i = this.count;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzabt;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzaaf;
        int i7 = this.count;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    /* access modifiers changed from: package-private */
    public final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzil.zza(sb, i, String.valueOf(this.zzabt[i2] >>> 3), this.zzaaf[i2]);
        }
    }
}
