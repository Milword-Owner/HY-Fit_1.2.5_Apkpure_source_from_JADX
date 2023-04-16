package com.google.android.gms.internal.fitness;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzhc {
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final byte[] zzyl;
    private static final ByteBuffer zzym;
    private static final zzgj zzyn;

    public static int zzc(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int zzj(long j) {
        return (int) (j ^ (j >>> 32));
    }

    static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    static <T> T zza(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static boolean zzc(byte[] bArr) {
        return zzka.zzc(bArr);
    }

    public static String zzd(byte[] bArr) {
        return new String(bArr, UTF_8);
    }

    public static int hashCode(byte[] bArr) {
        int length = bArr.length;
        int zza = zza(length, bArr, 0, length);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 = (i4 * 31) + bArr[i5];
        }
        return i4;
    }

    static boolean zzf(zzik zzik) {
        if (!(zzik instanceof zzfq)) {
            return false;
        }
        zzfq zzfq = (zzfq) zzik;
        return false;
    }

    static Object zzb(Object obj, Object obj2) {
        return ((zzik) obj).zzbt().zza((zzik) obj2).zzbz();
    }

    static {
        byte[] bArr = new byte[0];
        zzyl = bArr;
        zzym = ByteBuffer.wrap(bArr);
        byte[] bArr2 = zzyl;
        zzyn = zzgj.zza(bArr2, 0, bArr2.length, false);
    }
}
