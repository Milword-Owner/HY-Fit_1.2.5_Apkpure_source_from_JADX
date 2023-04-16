package com.google.android.gms.internal.fitness;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class zzgk extends zzfu {
    private static final Logger logger = Logger.getLogger(zzgk.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzum = zzjy.zzdr();
    zzgn zzun;

    public static zzgk zza(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zzb(boolean z) {
        return 1;
    }

    public static int zzc(double d) {
        return 8;
    }

    public static int zzc(float f) {
        return 4;
    }

    public static int zze(long j) {
        int i;
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if ((-34359738368L & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if ((-2097152 & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & -16384) != 0 ? i + 1 : i;
    }

    public static int zzg(long j) {
        return 8;
    }

    public static int zzh(long j) {
        return 8;
    }

    private static long zzi(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int zzt(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (i & -268435456) == 0 ? 4 : 5;
    }

    public static int zzv(int i) {
        return 4;
    }

    public static int zzw(int i) {
        return 4;
    }

    private static int zzy(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public abstract void writeTag(int i, int i2) throws IOException;

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzfx zzfx) throws IOException;

    public abstract void zza(int i, zzik zzik) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zza(int i, zzik zzik, zzja zzja) throws IOException;

    public abstract void zza(int i, String str) throws IOException;

    public abstract void zza(int i, boolean z) throws IOException;

    public abstract void zza(long j) throws IOException;

    public abstract void zza(zzfx zzfx) throws IOException;

    public abstract void zzb(int i, zzfx zzfx) throws IOException;

    public abstract void zzb(zzik zzik) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zzb(byte[] bArr, int i, int i2) throws IOException;

    public abstract int zzbc();

    public abstract void zzc(byte b) throws IOException;

    public abstract void zzc(int i, long j) throws IOException;

    public abstract void zzc(long j) throws IOException;

    public abstract void zze(int i, int i2) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzh(int i, int i2) throws IOException;

    public abstract void zzl(String str) throws IOException;

    public abstract void zzn(int i) throws IOException;

    public abstract void zzo(int i) throws IOException;

    public abstract void zzq(int i) throws IOException;

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class zzb extends IOException {
        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzb(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        zzb(java.lang.String r3, java.lang.Throwable r4) {
            /*
                r2 = this;
                java.lang.String r3 = java.lang.String.valueOf(r3)
                int r0 = r3.length()
                java.lang.String r1 = "CodedOutputStream was writing to a flat byte array and ran out of space.: "
                if (r0 == 0) goto L_0x0011
                java.lang.String r3 = r1.concat(r3)
                goto L_0x0016
            L_0x0011:
                java.lang.String r3 = new java.lang.String
                r3.<init>(r1)
            L_0x0016:
                r2.<init>(r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fitness.zzgk.zzb.<init>(java.lang.String, java.lang.Throwable):void");
        }
    }

    private zzgk() {
    }

    public final void zzg(int i, int i2) throws IOException {
        zzf(i, zzy(i2));
    }

    public final void zzb(int i, long j) throws IOException {
        zza(i, zzi(j));
    }

    public final void zza(int i, float f) throws IOException {
        zzh(i, Float.floatToRawIntBits(f));
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zzp(int i) throws IOException {
        zzo(zzy(i));
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    private static class zza extends zzgk {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zza(byte[] bArr, int i, int i2) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            } else if ((i2 | 0 | (bArr.length - i2)) >= 0) {
                this.buffer = bArr;
                this.offset = 0;
                this.position = 0;
                this.limit = i2;
            } else {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), 0, Integer.valueOf(i2)}));
            }
        }

        public final void writeTag(int i, int i2) throws IOException {
            zzo((i << 3) | i2);
        }

        public final void zze(int i, int i2) throws IOException {
            writeTag(i, 0);
            zzn(i2);
        }

        public final void zzf(int i, int i2) throws IOException {
            writeTag(i, 0);
            zzo(i2);
        }

        public final void zzh(int i, int i2) throws IOException {
            writeTag(i, 5);
            zzq(i2);
        }

        public final void zza(int i, long j) throws IOException {
            writeTag(i, 0);
            zza(j);
        }

        public final void zzc(int i, long j) throws IOException {
            writeTag(i, 1);
            zzc(j);
        }

        public final void zza(int i, boolean z) throws IOException {
            writeTag(i, 0);
            zzc(z ? (byte) 1 : 0);
        }

        public final void zza(int i, String str) throws IOException {
            writeTag(i, 2);
            zzl(str);
        }

        public final void zza(int i, zzfx zzfx) throws IOException {
            writeTag(i, 2);
            zza(zzfx);
        }

        public final void zza(zzfx zzfx) throws IOException {
            zzo(zzfx.size());
            zzfx.zza((zzfu) this);
        }

        public final void zzb(byte[] bArr, int i, int i2) throws IOException {
            zzo(i2);
            write(bArr, 0, i2);
        }

        /* access modifiers changed from: package-private */
        public final void zza(int i, zzik zzik, zzja zzja) throws IOException {
            writeTag(i, 2);
            zzfo zzfo = (zzfo) zzik;
            int zzan = zzfo.zzan();
            if (zzan == -1) {
                zzan = zzja.zzm(zzfo);
                zzfo.zzi(zzan);
            }
            zzo(zzan);
            zzja.zza(zzik, this.zzun);
        }

        public final void zza(int i, zzik zzik) throws IOException {
            writeTag(1, 3);
            zzf(2, i);
            writeTag(3, 2);
            zzb(zzik);
            writeTag(1, 4);
        }

        public final void zzb(int i, zzfx zzfx) throws IOException {
            writeTag(1, 3);
            zzf(2, i);
            zza(3, zzfx);
            writeTag(1, 4);
        }

        public final void zzb(zzik zzik) throws IOException {
            zzo(zzik.zzbp());
            zzik.zzb(this);
        }

        public final void zzc(byte b) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }

        public final void zzn(int i) throws IOException {
            if (i >= 0) {
                zzo(i);
            } else {
                zza((long) i);
            }
        }

        public final void zzo(int i) throws IOException {
            if (!zzgk.zzum || zzfv.zzat() || zzbc() < 5) {
                while ((i & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr[i2] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    bArr2[i3] = (byte) i;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
                }
            } else if ((i & -128) == 0) {
                byte[] bArr3 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                zzjy.zza(bArr3, (long) i4, (byte) i);
            } else {
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                zzjy.zza(bArr4, (long) i5, (byte) (i | 128));
                int i6 = i >>> 7;
                if ((i6 & -128) == 0) {
                    byte[] bArr5 = this.buffer;
                    int i7 = this.position;
                    this.position = i7 + 1;
                    zzjy.zza(bArr5, (long) i7, (byte) i6);
                    return;
                }
                byte[] bArr6 = this.buffer;
                int i8 = this.position;
                this.position = i8 + 1;
                zzjy.zza(bArr6, (long) i8, (byte) (i6 | 128));
                int i9 = i6 >>> 7;
                if ((i9 & -128) == 0) {
                    byte[] bArr7 = this.buffer;
                    int i10 = this.position;
                    this.position = i10 + 1;
                    zzjy.zza(bArr7, (long) i10, (byte) i9);
                    return;
                }
                byte[] bArr8 = this.buffer;
                int i11 = this.position;
                this.position = i11 + 1;
                zzjy.zza(bArr8, (long) i11, (byte) (i9 | 128));
                int i12 = i9 >>> 7;
                if ((i12 & -128) == 0) {
                    byte[] bArr9 = this.buffer;
                    int i13 = this.position;
                    this.position = i13 + 1;
                    zzjy.zza(bArr9, (long) i13, (byte) i12);
                    return;
                }
                byte[] bArr10 = this.buffer;
                int i14 = this.position;
                this.position = i14 + 1;
                zzjy.zza(bArr10, (long) i14, (byte) (i12 | 128));
                byte[] bArr11 = this.buffer;
                int i15 = this.position;
                this.position = i15 + 1;
                zzjy.zza(bArr11, (long) i15, (byte) (i12 >>> 7));
            }
        }

        public final void zzq(int i) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) i;
                byte[] bArr2 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr2[i3] = (byte) (i >> 8);
                byte[] bArr3 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr3[i4] = (byte) (i >> 16);
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr4[i5] = (byte) (i >>> 24);
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }

        public final void zza(long j) throws IOException {
            if (!zzgk.zzum || zzbc() < 10) {
                while ((j & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i = this.position;
                    this.position = i + 1;
                    bArr[i] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr2[i2] = (byte) ((int) j);
                } catch (IndexOutOfBoundsException e) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
                }
            } else {
                while ((j & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    zzjy.zza(bArr3, (long) i3, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                zzjy.zza(bArr4, (long) i4, (byte) ((int) j));
            }
        }

        public final void zzc(long j) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) j);
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr2[i2] = (byte) ((int) (j >> 8));
                byte[] bArr3 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr3[i3] = (byte) ((int) (j >> 16));
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr4[i4] = (byte) ((int) (j >> 24));
                byte[] bArr5 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr5[i5] = (byte) ((int) (j >> 32));
                byte[] bArr6 = this.buffer;
                int i6 = this.position;
                this.position = i6 + 1;
                bArr6[i6] = (byte) ((int) (j >> 40));
                byte[] bArr7 = this.buffer;
                int i7 = this.position;
                this.position = i7 + 1;
                bArr7[i7] = (byte) ((int) (j >> 48));
                byte[] bArr8 = this.buffer;
                int i8 = this.position;
                this.position = i8 + 1;
                bArr8[i8] = (byte) ((int) (j >> 56));
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }

        private final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
            }
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzl(String str) throws IOException {
            int i = this.position;
            try {
                int zzt = zzt(str.length() * 3);
                int zzt2 = zzt(str.length());
                if (zzt2 == zzt) {
                    this.position = i + zzt2;
                    int zzb = zzka.zzb(str, this.buffer, this.position, zzbc());
                    this.position = i;
                    zzo((zzb - i) - zzt2);
                    this.position = zzb;
                    return;
                }
                zzo(zzka.zza(str));
                this.position = zzka.zzb(str, this.buffer, this.position, zzbc());
            } catch (zzkb e) {
                this.position = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(e2);
            }
        }

        public final int zzbc() {
            return this.limit - this.position;
        }
    }

    public final void zzb(long j) throws IOException {
        zza(zzi(j));
    }

    public final void zzb(float f) throws IOException {
        zzq(Float.floatToRawIntBits(f));
    }

    public final void zzb(double d) throws IOException {
        zzc(Double.doubleToRawLongBits(d));
    }

    public final void zza(boolean z) throws IOException {
        zzc(z ? (byte) 1 : 0);
    }

    public static int zzi(int i, int i2) {
        return zzt(i << 3) + zzs(i2);
    }

    public static int zzj(int i, int i2) {
        return zzt(i << 3) + zzt(i2);
    }

    public static int zzk(int i, int i2) {
        return zzt(i << 3) + zzt(zzy(i2));
    }

    public static int zzl(int i, int i2) {
        return zzt(i << 3) + 4;
    }

    public static int zzm(int i, int i2) {
        return zzt(i << 3) + 4;
    }

    public static int zzd(int i, long j) {
        return zzt(i << 3) + zze(j);
    }

    public static int zze(int i, long j) {
        return zzt(i << 3) + zze(j);
    }

    public static int zzf(int i, long j) {
        return zzt(i << 3) + zze(zzi(j));
    }

    public static int zzg(int i, long j) {
        return zzt(i << 3) + 8;
    }

    public static int zzh(int i, long j) {
        return zzt(i << 3) + 8;
    }

    public static int zzb(int i, float f) {
        return zzt(i << 3) + 4;
    }

    public static int zzb(int i, double d) {
        return zzt(i << 3) + 8;
    }

    public static int zzb(int i, boolean z) {
        return zzt(i << 3) + 1;
    }

    public static int zzn(int i, int i2) {
        return zzt(i << 3) + zzs(i2);
    }

    public static int zzb(int i, String str) {
        return zzt(i << 3) + zzm(str);
    }

    public static int zzc(int i, zzfx zzfx) {
        int zzt = zzt(i << 3);
        int size = zzfx.size();
        return zzt + zzt(size) + size;
    }

    public static int zza(int i, zzhp zzhp) {
        int zzt = zzt(i << 3);
        int zzbp = zzhp.zzbp();
        return zzt + zzt(zzbp) + zzbp;
    }

    static int zzb(int i, zzik zzik, zzja zzja) {
        return zzt(i << 3) + zza(zzik, zzja);
    }

    public static int zzb(int i, zzik zzik) {
        return (zzt(8) << 1) + zzj(2, i) + zzt(24) + zzc(zzik);
    }

    public static int zzd(int i, zzfx zzfx) {
        return (zzt(8) << 1) + zzj(2, i) + zzc(3, zzfx);
    }

    public static int zzb(int i, zzhp zzhp) {
        return (zzt(8) << 1) + zzj(2, i) + zza(3, zzhp);
    }

    public static int zzr(int i) {
        return zzt(i << 3);
    }

    public static int zzs(int i) {
        if (i >= 0) {
            return zzt(i);
        }
        return 10;
    }

    public static int zzu(int i) {
        return zzt(zzy(i));
    }

    public static int zzd(long j) {
        return zze(j);
    }

    public static int zzf(long j) {
        return zze(zzi(j));
    }

    public static int zzx(int i) {
        return zzs(i);
    }

    public static int zzm(String str) {
        int i;
        try {
            i = zzka.zza(str);
        } catch (zzkb unused) {
            i = str.getBytes(zzhc.UTF_8).length;
        }
        return zzt(i) + i;
    }

    public static int zza(zzhp zzhp) {
        int zzbp = zzhp.zzbp();
        return zzt(zzbp) + zzbp;
    }

    public static int zzb(zzfx zzfx) {
        int size = zzfx.size();
        return zzt(size) + size;
    }

    public static int zzb(byte[] bArr) {
        int length = bArr.length;
        return zzt(length) + length;
    }

    public static int zzc(zzik zzik) {
        int zzbp = zzik.zzbp();
        return zzt(zzbp) + zzbp;
    }

    static int zza(zzik zzik, zzja zzja) {
        zzfo zzfo = (zzfo) zzik;
        int zzan = zzfo.zzan();
        if (zzan == -1) {
            zzan = zzja.zzm(zzfo);
            zzfo.zzi(zzan);
        }
        return zzt(zzan) + zzan;
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, zzkb zzkb) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzkb);
        byte[] bytes = str.getBytes(zzhc.UTF_8);
        try {
            zzo(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzb(e);
        } catch (zzb e2) {
            throw e2;
        }
    }

    @Deprecated
    static int zzc(int i, zzik zzik, zzja zzja) {
        int zzt = zzt(i << 3) << 1;
        zzfo zzfo = (zzfo) zzik;
        int zzan = zzfo.zzan();
        if (zzan == -1) {
            zzan = zzja.zzm(zzfo);
            zzfo.zzi(zzan);
        }
        return zzt + zzan;
    }

    @Deprecated
    public static int zzd(zzik zzik) {
        return zzik.zzbp();
    }

    @Deprecated
    public static int zzz(int i) {
        return zzt(i);
    }
}
