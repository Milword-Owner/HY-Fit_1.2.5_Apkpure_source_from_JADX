package com.google.android.gms.internal.fitness;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzfe extends zzfc<E> {
    private final transient int length;
    private final transient int offset;
    private final /* synthetic */ zzfc zzth;

    zzfe(zzfc zzfc, int i, int i2) {
        this.zzth = zzfc;
        this.offset = i;
        this.length = i2;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaj() {
        return true;
    }

    public final int size() {
        return this.length;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzag() {
        return this.zzth.zzag();
    }

    /* access modifiers changed from: package-private */
    public final int zzah() {
        return this.zzth.zzah() + this.offset;
    }

    /* access modifiers changed from: package-private */
    public final int zzai() {
        return this.zzth.zzah() + this.offset + this.length;
    }

    public final E get(int i) {
        zzez.zza(i, this.length);
        return this.zzth.get(i + this.offset);
    }

    public final zzfc<E> zzc(int i, int i2) {
        zzez.zza(i, i2, this.length);
        zzfc zzfc = this.zzth;
        int i3 = this.offset;
        return (zzfc) zzfc.subList(i + i3, i2 + i3);
    }

    public final /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }
}
