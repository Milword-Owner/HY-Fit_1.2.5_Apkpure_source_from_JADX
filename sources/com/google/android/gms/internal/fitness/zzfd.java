package com.google.android.gms.internal.fitness;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class zzfd<E> extends AbstractCollection<E> implements Serializable {
    private static final Object[] zztg = new Object[0];

    zzfd() {
    }

    public abstract boolean contains(@NullableDecl Object obj);

    /* renamed from: zzae */
    public abstract zzfm<E> iterator();

    /* access modifiers changed from: package-private */
    @NullableDecl
    public Object[] zzag() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public abstract boolean zzaj();

    public final Object[] toArray() {
        return toArray(zztg);
    }

    public final <T> T[] toArray(T[] tArr) {
        zzez.checkNotNull(tArr);
        int size = size();
        if (tArr.length < size) {
            Object[] zzag = zzag();
            if (zzag != null) {
                return Arrays.copyOfRange(zzag, zzah(), zzai(), tArr.getClass());
            }
            tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size);
        } else if (tArr.length > size) {
            tArr[size] = null;
        }
        zzb(tArr, 0);
        return tArr;
    }

    /* access modifiers changed from: package-private */
    public int zzah() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public int zzai() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public zzfc<E> zzaf() {
        return isEmpty() ? zzfc.zzad() : zzfc.zza(toArray());
    }

    /* access modifiers changed from: package-private */
    public int zzb(Object[] objArr, int i) {
        zzfm zzfm = (zzfm) iterator();
        while (zzfm.hasNext()) {
            objArr[i] = zzfm.next();
            i++;
        }
        return i;
    }
}
