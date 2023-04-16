package com.google.android.gms.internal.fitness;

import java.util.Arrays;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zziy<E> extends zzfs<E> implements RandomAccess {
    private static final zziy<Object> zzaaz;
    private int size;
    private E[] zztn;

    public static <E> zziy<E> zzdb() {
        return zzaaz;
    }

    zziy() {
        this(new Object[10], 0);
    }

    private zziy(E[] eArr, int i) {
        this.zztn = eArr;
        this.size = i;
    }

    public final boolean add(E e) {
        zzas();
        int i = this.size;
        E[] eArr = this.zztn;
        if (i == eArr.length) {
            this.zztn = Arrays.copyOf(eArr, ((i * 3) / 2) + 1);
        }
        E[] eArr2 = this.zztn;
        int i2 = this.size;
        this.size = i2 + 1;
        eArr2[i2] = e;
        this.modCount++;
        return true;
    }

    public final void add(int i, E e) {
        int i2;
        zzas();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzad(i));
        }
        E[] eArr = this.zztn;
        if (i2 < eArr.length) {
            System.arraycopy(eArr, i, eArr, i + 1, i2 - i);
        } else {
            E[] eArr2 = new Object[(((i2 * 3) / 2) + 1)];
            System.arraycopy(eArr, 0, eArr2, 0, i);
            System.arraycopy(this.zztn, i, eArr2, i + 1, this.size - i);
            this.zztn = eArr2;
        }
        this.zztn[i] = e;
        this.size++;
        this.modCount++;
    }

    public final E get(int i) {
        zzac(i);
        return this.zztn[i];
    }

    public final E remove(int i) {
        zzas();
        zzac(i);
        E[] eArr = this.zztn;
        E e = eArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(eArr, i + 1, eArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return e;
    }

    public final E set(int i, E e) {
        zzas();
        zzac(i);
        E[] eArr = this.zztn;
        E e2 = eArr[i];
        eArr[i] = e;
        this.modCount++;
        return e2;
    }

    public final int size() {
        return this.size;
    }

    private final void zzac(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzad(i));
        }
    }

    private final String zzad(int i) {
        int i2 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    public final /* synthetic */ zzhh zzae(int i) {
        if (i >= this.size) {
            return new zziy(Arrays.copyOf(this.zztn, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    static {
        zziy<Object> zziy = new zziy<>(new Object[0], 0);
        zzaaz = zziy;
        zziy.zzar();
    }
}
