package com.google.android.gms.internal.fitness;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzgf {
    private final byte[] buffer;
    private final zzgk zzuh;

    private zzgf(int i) {
        this.buffer = new byte[i];
        this.zzuh = zzgk.zza(this.buffer);
    }

    public final zzfx zzaz() {
        if (this.zzuh.zzbc() == 0) {
            return new zzgh(this.buffer);
        }
        throw new IllegalStateException("Did not write as much data as expected.");
    }

    public final zzgk zzba() {
        return this.zzuh;
    }

    /* synthetic */ zzgf(int i, zzfw zzfw) {
        this(i);
    }
}
