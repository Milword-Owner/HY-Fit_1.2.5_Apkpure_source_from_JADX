package com.google.android.gms.internal.fitness;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class zzgj {
    private int zzuj;
    private int zzuk;
    private boolean zzul;

    static zzgj zza(byte[] bArr, int i, int i2, boolean z) {
        zzgl zzgl = new zzgl(bArr, i2);
        try {
            zzgl.zzm(i2);
            return zzgl;
        } catch (zzhk e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract int zzbb();

    public abstract int zzm(int i) throws zzhk;

    private zzgj() {
        this.zzuj = 100;
        this.zzuk = Integer.MAX_VALUE;
        this.zzul = false;
    }
}
