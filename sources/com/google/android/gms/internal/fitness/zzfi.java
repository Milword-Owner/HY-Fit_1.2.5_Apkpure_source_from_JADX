package com.google.android.gms.internal.fitness;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzfi<E> extends zzfc<E> {
    static final zzfc<Object> zztm = new zzfi(new Object[0], 0);
    private final transient int size;
    private final transient Object[] zztn;

    zzfi(Object[] objArr, int i) {
        this.zztn = objArr;
        this.size = i;
    }

    /* access modifiers changed from: package-private */
    public final int zzah() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaj() {
        return false;
    }

    public final int size() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzag() {
        return this.zztn;
    }

    /* access modifiers changed from: package-private */
    public final int zzai() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public final int zzb(Object[] objArr, int i) {
        System.arraycopy(this.zztn, 0, objArr, 0, this.size);
        return this.size + 0;
    }

    public final E get(int i) {
        zzez.zza(i, this.size);
        return this.zztn[i];
    }
}
