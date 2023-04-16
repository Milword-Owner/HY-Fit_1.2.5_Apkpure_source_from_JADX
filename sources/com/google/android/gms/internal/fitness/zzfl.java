package com.google.android.gms.internal.fitness;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzfl<E> extends zzfh<E> {
    static final zzfl<Object> zzto = new zzfl(new Object[0], 0, (Object[]) null, 0, 0);
    private final transient int mask;
    private final transient int size;
    private final transient Object[] zztp;
    private final transient Object[] zztq;
    private final transient int zztr;

    zzfl(Object[] objArr, int i, Object[] objArr2, int i2, int i3) {
        this.zztp = objArr;
        this.zztq = objArr2;
        this.mask = i2;
        this.zztr = i;
        this.size = i3;
    }

    /* access modifiers changed from: package-private */
    public final int zzah() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaj() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzak() {
        return true;
    }

    public final boolean contains(@NullableDecl Object obj) {
        int i;
        Object[] objArr = this.zztq;
        if (obj == null || objArr == null) {
            return false;
        }
        if (obj == null) {
            i = 0;
        } else {
            i = obj.hashCode();
        }
        int zzg = zzfa.zzg(i);
        while (true) {
            int i2 = zzg & this.mask;
            Object obj2 = objArr[i2];
            if (obj2 == null) {
                return false;
            }
            if (obj2.equals(obj)) {
                return true;
            }
            zzg = i2 + 1;
        }
    }

    public final int size() {
        return this.size;
    }

    public final zzfm<E> zzae() {
        return (zzfm) zzaf().iterator();
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzag() {
        return this.zztp;
    }

    /* access modifiers changed from: package-private */
    public final int zzai() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public final int zzb(Object[] objArr, int i) {
        System.arraycopy(this.zztp, 0, objArr, 0, this.size);
        return this.size + 0;
    }

    /* access modifiers changed from: package-private */
    public final zzfc<E> zzal() {
        return zzfc.zza(this.zztp, this.size);
    }

    public final int hashCode() {
        return this.zztr;
    }

    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
