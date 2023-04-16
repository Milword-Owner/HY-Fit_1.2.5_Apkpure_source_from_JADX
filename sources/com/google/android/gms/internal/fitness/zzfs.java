package com.google.android.gms.internal.fitness;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
abstract class zzfs<E> extends AbstractList<E> implements zzhh<E> {
    private boolean zztv = true;

    zzfs() {
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        if (!(obj instanceof RandomAccess)) {
            return super.equals(obj);
        }
        List list = (List) obj;
        int size = size();
        if (size != list.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    public boolean add(E e) {
        zzas();
        return super.add(e);
    }

    public void add(int i, E e) {
        zzas();
        super.add(i, e);
    }

    public boolean addAll(Collection<? extends E> collection) {
        zzas();
        return super.addAll(collection);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        zzas();
        return super.addAll(i, collection);
    }

    public void clear() {
        zzas();
        super.clear();
    }

    public boolean zzaq() {
        return this.zztv;
    }

    public final void zzar() {
        this.zztv = false;
    }

    public E remove(int i) {
        zzas();
        return super.remove(i);
    }

    public boolean remove(Object obj) {
        zzas();
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    public boolean removeAll(Collection<?> collection) {
        zzas();
        return super.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        zzas();
        return super.retainAll(collection);
    }

    public E set(int i, E e) {
        zzas();
        return super.set(i, e);
    }

    /* access modifiers changed from: protected */
    public final void zzas() {
        if (!this.zztv) {
            throw new UnsupportedOperationException();
        }
    }
}
