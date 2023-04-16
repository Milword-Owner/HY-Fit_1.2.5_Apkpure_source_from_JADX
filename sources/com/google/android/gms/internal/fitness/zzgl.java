package com.google.android.gms.internal.fitness;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzgl extends zzgj {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzuo;
    private int zzup;
    private int zzuq;
    private int zzur;

    private zzgl(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzur = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzuq = this.pos;
        this.zzuo = z;
    }

    public final int zzm(int i) throws zzhk {
        if (i >= 0) {
            int zzbb = i + zzbb();
            int i2 = this.zzur;
            if (zzbb <= i2) {
                this.zzur = zzbb;
                this.limit += this.zzup;
                int i3 = this.limit;
                int i4 = i3 - this.zzuq;
                int i5 = this.zzur;
                if (i4 > i5) {
                    this.zzup = i4 - i5;
                    this.limit = i3 - this.zzup;
                } else {
                    this.zzup = 0;
                }
                return i2;
            }
            throw new zzhk("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        throw new zzhk("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public final int zzbb() {
        return this.pos - this.zzuq;
    }
}
