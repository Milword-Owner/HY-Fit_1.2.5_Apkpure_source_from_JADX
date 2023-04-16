package com.google.android.gms.internal.fitness;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import kotlin.UByte;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class zzfx implements Serializable, Iterable<Byte> {
    public static final zzfx zzub = new zzgh(zzhc.zzyl);
    private static final zzgd zzuc = (zzfv.zzat() ? new zzgg((zzfw) null) : new zzgb((zzfw) null));
    private static final Comparator<zzfx> zzue = new zzfz();
    private int zzud = 0;

    zzfx() {
    }

    /* access modifiers changed from: private */
    public static int zza(byte b) {
        return b & UByte.MAX_VALUE;
    }

    public abstract boolean equals(Object obj);

    public abstract int size();

    /* access modifiers changed from: protected */
    public abstract String zza(Charset charset);

    /* access modifiers changed from: package-private */
    public abstract void zza(zzfu zzfu) throws IOException;

    public abstract boolean zzaw();

    /* access modifiers changed from: protected */
    public abstract int zzb(int i, int i2, int i3);

    public abstract zzfx zzd(int i, int i2);

    public abstract byte zzj(int i);

    /* access modifiers changed from: package-private */
    public abstract byte zzk(int i);

    public static zzfx zzk(String str) {
        return new zzgh(str.getBytes(zzhc.UTF_8));
    }

    public final String zzav() {
        return size() == 0 ? "" : zza(zzhc.UTF_8);
    }

    public final int hashCode() {
        int i = this.zzud;
        if (i == 0) {
            int size = size();
            i = zzb(size, 0, size);
            if (i == 0) {
                i = 1;
            }
            this.zzud = i;
        }
        return i;
    }

    static zzgf zzl(int i) {
        return new zzgf(i, (zzfw) null);
    }

    /* access modifiers changed from: protected */
    public final int zzax() {
        return this.zzud;
    }

    static int zzc(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Beginning index: ");
            sb.append(i);
            sb.append(" < 0");
            throw new IndexOutOfBoundsException(sb.toString());
        } else if (i2 < i) {
            StringBuilder sb2 = new StringBuilder(66);
            sb2.append("Beginning index larger than ending index: ");
            sb2.append(i);
            sb2.append(", ");
            sb2.append(i2);
            throw new IndexOutOfBoundsException(sb2.toString());
        } else {
            StringBuilder sb3 = new StringBuilder(37);
            sb3.append("End index: ");
            sb3.append(i2);
            sb3.append(" >= ");
            sb3.append(i3);
            throw new IndexOutOfBoundsException(sb3.toString());
        }
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(size());
        objArr[2] = size() <= 50 ? zzjo.zzc(this) : String.valueOf(zzjo.zzc(zzd(0, 47))).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public /* synthetic */ Iterator iterator() {
        return new zzfw(this);
    }
}
