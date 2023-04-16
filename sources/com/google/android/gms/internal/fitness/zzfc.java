package com.google.android.gms.internal.fitness;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class zzfc<E> extends zzfd<E> implements List<E>, RandomAccess {
    private static final zzfp<Object> zztf = new zzff(zzfi.zztm, 0);

    public static <E> zzfc<E> zzad() {
        return zzfi.zztm;
    }

    public final zzfc<E> zzaf() {
        return this;
    }

    public static <E> zzfc<E> zzb(E e) {
        Object[] zzc = zzfj.zzc(new Object[]{e}, 1);
        return zza(zzc, zzc.length);
    }

    public static <E> zzfc<E> zza(Collection<? extends E> collection) {
        if (collection instanceof zzfd) {
            zzfc<E> zzaf = ((zzfd) collection).zzaf();
            if (!zzaf.zzaj()) {
                return zzaf;
            }
            Object[] array = zzaf.toArray();
            return zza(array, array.length);
        }
        Object[] array2 = collection.toArray();
        Object[] zzc = zzfj.zzc(array2, array2.length);
        return zza(zzc, zzc.length);
    }

    static <E> zzfc<E> zza(Object[] objArr) {
        return zza(objArr, objArr.length);
    }

    static <E> zzfc<E> zza(Object[] objArr, int i) {
        if (i == 0) {
            return zzfi.zztm;
        }
        return new zzfi(objArr, i);
    }

    zzfc() {
    }

    public final zzfm<E> zzae() {
        return (zzfp) listIterator();
    }

    public int indexOf(@NullableDecl Object obj) {
        if (obj == null) {
            return -1;
        }
        if (this instanceof RandomAccess) {
            int size = size();
            int i = 0;
            if (obj == null) {
                while (i < size) {
                    if (get(i) == null) {
                        return i;
                    }
                    i++;
                }
            } else {
                while (i < size) {
                    if (obj.equals(get(i))) {
                        return i;
                    }
                    i++;
                }
            }
            return -1;
        }
        ListIterator listIterator = listIterator();
        while (listIterator.hasNext()) {
            if (zzew.equal(obj, listIterator.next())) {
                return listIterator.previousIndex();
            }
        }
        return -1;
    }

    public int lastIndexOf(@NullableDecl Object obj) {
        if (obj == null) {
            return -1;
        }
        if (this instanceof RandomAccess) {
            if (obj == null) {
                for (int size = size() - 1; size >= 0; size--) {
                    if (get(size) == null) {
                        return size;
                    }
                }
            } else {
                for (int size2 = size() - 1; size2 >= 0; size2--) {
                    if (obj.equals(get(size2))) {
                        return size2;
                    }
                }
            }
            return -1;
        }
        ListIterator listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (zzew.equal(obj, listIterator.previous())) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    public boolean contains(@NullableDecl Object obj) {
        return indexOf(obj) >= 0;
    }

    /* renamed from: zzc */
    public zzfc<E> subList(int i, int i2) {
        zzez.zza(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 == 0) {
            return zzfi.zztm;
        }
        return new zzfe(this, i, i3);
    }

    @Deprecated
    public final boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final E set(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void add(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final E remove(int i) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public int zzb(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i2] = get(i2);
        }
        return size;
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj == zzez.checkNotNull(this)) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (!(this instanceof RandomAccess) || !(list instanceof RandomAccess)) {
                    zzfc zzfc = this;
                    int size2 = zzfc.size();
                    Iterator it = list.iterator();
                    int i = 0;
                    while (true) {
                        if (i < size2) {
                            if (!it.hasNext()) {
                                break;
                            }
                            Object obj2 = zzfc.get(i);
                            i++;
                            if (!zzew.equal(obj2, it.next())) {
                                break;
                            }
                        } else if (!it.hasNext()) {
                            return true;
                        }
                    }
                } else {
                    int i2 = 0;
                    while (i2 < size) {
                        if (zzew.equal(get(i2), list.get(i2))) {
                            i2++;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (((i * 31) + get(i2).hashCode()) ^ -1) ^ -1;
        }
        return i;
    }

    public /* synthetic */ Iterator iterator() {
        return iterator();
    }

    public /* synthetic */ ListIterator listIterator(int i) {
        zzez.zzb(i, size());
        if (isEmpty()) {
            return zztf;
        }
        return new zzff(this, i);
    }

    public /* synthetic */ ListIterator listIterator() {
        return (zzfp) listIterator(0);
    }
}
