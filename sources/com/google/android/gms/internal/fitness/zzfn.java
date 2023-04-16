package com.google.android.gms.internal.fitness;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzfn<E> extends zzfh<E> {
    private final transient E zzts;
    private transient int zztt;

    zzfn(E e) {
        this.zzts = zzez.checkNotNull(e);
    }

    public final int size() {
        return 1;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaj() {
        return false;
    }

    zzfn(E e, int i) {
        this.zzts = e;
        this.zztt = i;
    }

    public final boolean contains(Object obj) {
        return this.zzts.equals(obj);
    }

    public final zzfm<E> zzae() {
        return new zzfg(this.zzts);
    }

    /* access modifiers changed from: package-private */
    public final zzfc<E> zzal() {
        return zzfc.zzb(this.zzts);
    }

    /* access modifiers changed from: package-private */
    public final int zzb(Object[] objArr, int i) {
        objArr[0] = this.zzts;
        return 1;
    }

    public final int hashCode() {
        int i = this.zztt;
        if (i != 0) {
            return i;
        }
        int hashCode = this.zzts.hashCode();
        this.zztt = hashCode;
        return hashCode;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzak() {
        return this.zztt != 0;
    }

    public final String toString() {
        String obj = this.zzts.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 2);
        sb.append('[');
        sb.append(obj);
        sb.append(']');
        return sb.toString();
    }

    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
