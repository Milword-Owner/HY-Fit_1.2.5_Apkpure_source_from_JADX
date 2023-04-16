package com.google.android.gms.internal.fitness;

import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
class zzgh extends zzge {
    protected final byte[] zzui;

    zzgh(byte[] bArr) {
        if (bArr != null) {
            this.zzui = bArr;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: protected */
    public int zzay() {
        return 0;
    }

    public byte zzj(int i) {
        return this.zzui[i];
    }

    /* access modifiers changed from: package-private */
    public byte zzk(int i) {
        return this.zzui[i];
    }

    public int size() {
        return this.zzui.length;
    }

    public final zzfx zzd(int i, int i2) {
        int zzc = zzc(0, i2, size());
        if (zzc == 0) {
            return zzfx.zzub;
        }
        return new zzga(this.zzui, zzay(), zzc);
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzfu zzfu) throws IOException {
        zzfu.zza(this.zzui, zzay(), size());
    }

    /* access modifiers changed from: protected */
    public final String zza(Charset charset) {
        return new String(this.zzui, zzay(), size(), charset);
    }

    public final boolean zzaw() {
        int zzay = zzay();
        return zzka.zzc(this.zzui, zzay, size() + zzay);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfx) || size() != ((zzfx) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzgh)) {
            return obj.equals(this);
        }
        zzgh zzgh = (zzgh) obj;
        int zzax = zzax();
        int zzax2 = zzgh.zzax();
        if (zzax == 0 || zzax2 == 0 || zzax == zzax2) {
            return zza(zzgh, 0, size());
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzfx zzfx, int i, int i2) {
        if (i2 > zzfx.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 > zzfx.size()) {
            int size2 = zzfx.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzfx instanceof zzgh)) {
            return zzfx.zzd(0, i2).equals(zzd(0, i2));
        } else {
            zzgh zzgh = (zzgh) zzfx;
            byte[] bArr = this.zzui;
            byte[] bArr2 = zzgh.zzui;
            int zzay = zzay() + i2;
            int zzay2 = zzay();
            int zzay3 = zzgh.zzay();
            while (zzay2 < zzay) {
                if (bArr[zzay2] != bArr2[zzay3]) {
                    return false;
                }
                zzay2++;
                zzay3++;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public final int zzb(int i, int i2, int i3) {
        return zzhc.zza(i, this.zzui, zzay(), i3);
    }
}
