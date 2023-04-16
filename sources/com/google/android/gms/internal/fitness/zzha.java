package com.google.android.gms.internal.fitness;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzha extends zzfs<Integer> implements zzhh<Integer>, zzit, RandomAccess {
    private static final zzha zzyb;
    private int size;
    private int[] zzyc;

    public static zzha zzcb() {
        return zzyb;
    }

    zzha() {
        this(new int[10], 0);
    }

    private zzha(int[] iArr, int i) {
        this.zzyc = iArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzas();
        if (i2 >= i) {
            int[] iArr = this.zzyc;
            System.arraycopy(iArr, i2, iArr, i, this.size - i2);
            this.size -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzha)) {
            return super.equals(obj);
        }
        zzha zzha = (zzha) obj;
        if (this.size != zzha.size) {
            return false;
        }
        int[] iArr = zzha.zzyc;
        for (int i = 0; i < this.size; i++) {
            if (this.zzyc[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + this.zzyc[i2];
        }
        return i;
    }

    public final int getInt(int i) {
        zzac(i);
        return this.zzyc[i];
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int intValue = ((Integer) obj).intValue();
        int size2 = size();
        for (int i = 0; i < size2; i++) {
            if (this.zzyc[i] == intValue) {
                return i;
            }
        }
        return -1;
    }

    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final int size() {
        return this.size;
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        zzas();
        zzhc.checkNotNull(collection);
        if (!(collection instanceof zzha)) {
            return super.addAll(collection);
        }
        zzha zzha = (zzha) collection;
        int i = zzha.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            int[] iArr = this.zzyc;
            if (i3 > iArr.length) {
                this.zzyc = Arrays.copyOf(iArr, i3);
            }
            System.arraycopy(zzha.zzyc, 0, this.zzyc, this.size, zzha.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
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

    public final /* synthetic */ Object set(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzas();
        zzac(i);
        int[] iArr = this.zzyc;
        int i2 = iArr[i];
        iArr[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ Object remove(int i) {
        zzas();
        zzac(i);
        int[] iArr = this.zzyc;
        int i2 = iArr[i];
        int i3 = this.size;
        if (i < i3 - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, (i3 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        int intValue = ((Integer) obj).intValue();
        zzas();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzad(i));
        }
        int[] iArr = this.zzyc;
        if (i2 < iArr.length) {
            System.arraycopy(iArr, i, iArr, i + 1, i2 - i);
        } else {
            int[] iArr2 = new int[(((i2 * 3) / 2) + 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            System.arraycopy(this.zzyc, i, iArr2, i + 1, this.size - i);
            this.zzyc = iArr2;
        }
        this.zzyc[i] = intValue;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ boolean add(Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzas();
        int i = this.size;
        int[] iArr = this.zzyc;
        if (i == iArr.length) {
            int[] iArr2 = new int[(((i * 3) / 2) + 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            this.zzyc = iArr2;
        }
        int[] iArr3 = this.zzyc;
        int i2 = this.size;
        this.size = i2 + 1;
        iArr3[i2] = intValue;
        return true;
    }

    public final /* synthetic */ zzhh zzae(int i) {
        if (i >= this.size) {
            return new zzha(Arrays.copyOf(this.zzyc, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    static {
        zzha zzha = new zzha(new int[0], 0);
        zzyb = zzha;
        zzha.zzar();
    }
}
