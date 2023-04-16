package com.google.android.gms.fitness.data;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzaj {
    private final double zzoq;
    private final double zzor;

    private zzaj(double d, double d2) {
        this.zzoq = d;
        this.zzor = d2;
    }

    public final boolean zza(double d) {
        return d >= this.zzoq && d <= this.zzor;
    }
}
