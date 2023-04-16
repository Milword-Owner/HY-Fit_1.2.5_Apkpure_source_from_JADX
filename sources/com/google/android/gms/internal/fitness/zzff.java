package com.google.android.gms.internal.fitness;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzff<E> extends zzfb<E> {
    private final zzfc<E> zzti;

    zzff(zzfc<E> zzfc, int i) {
        super(zzfc.size(), i);
        this.zzti = zzfc;
    }

    /* access modifiers changed from: protected */
    public final E get(int i) {
        return this.zzti.get(i);
    }
}
