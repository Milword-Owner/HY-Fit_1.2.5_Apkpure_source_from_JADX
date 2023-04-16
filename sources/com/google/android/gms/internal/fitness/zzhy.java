package com.google.android.gms.internal.fitness;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzhy extends zzfs<Long> implements zzhh<Long>, zzit, RandomAccess {
    private static final zzhy zzzq;
    private int size;
    private long[] zzzr;

    public static zzhy zzcm() {
        return zzzq;
    }

    zzhy() {
        this(new long[10], 0);
    }

    private zzhy(long[] jArr, int i) {
        this.zzzr = jArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzas();
        if (i2 >= i) {
            long[] jArr = this.zzzr;
            System.arraycopy(jArr, i2, jArr, i, this.size - i2);
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
        if (!(obj instanceof zzhy)) {
            return super.equals(obj);
        }
        zzhy zzhy = (zzhy) obj;
        if (this.size != zzhy.size) {
            return false;
        }
        long[] jArr = zzhy.zzzr;
        for (int i = 0; i < this.size; i++) {
            if (this.zzzr[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzhc.zzj(this.zzzr[i2]);
        }
        return i;
    }

    public final long getLong(int i) {
        zzac(i);
        return this.zzzr[i];
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Long)) {
            return -1;
        }
        long longValue = ((Long) obj).longValue();
        int size2 = size();
        for (int i = 0; i < size2; i++) {
            if (this.zzzr[i] == longValue) {
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

    public final boolean addAll(Collection<? extends Long> collection) {
        zzas();
        zzhc.checkNotNull(collection);
        if (!(collection instanceof zzhy)) {
            return super.addAll(collection);
        }
        zzhy zzhy = (zzhy) collection;
        int i = zzhy.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            long[] jArr = this.zzzr;
            if (i3 > jArr.length) {
                this.zzzr = Arrays.copyOf(jArr, i3);
            }
            System.arraycopy(zzhy.zzzr, 0, this.zzzr, this.size, zzhy.size);
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
        long longValue = ((Long) obj).longValue();
        zzas();
        zzac(i);
        long[] jArr = this.zzzr;
        long j = jArr[i];
        jArr[i] = longValue;
        return Long.valueOf(j);
    }

    public final /* synthetic */ Object remove(int i) {
        zzas();
        zzac(i);
        long[] jArr = this.zzzr;
        long j = jArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Long.valueOf(j);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        long longValue = ((Long) obj).longValue();
        zzas();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzad(i));
        }
        long[] jArr = this.zzzr;
        if (i2 < jArr.length) {
            System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
        } else {
            long[] jArr2 = new long[(((i2 * 3) / 2) + 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            System.arraycopy(this.zzzr, i, jArr2, i + 1, this.size - i);
            this.zzzr = jArr2;
        }
        this.zzzr[i] = longValue;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ boolean add(Object obj) {
        long longValue = ((Long) obj).longValue();
        zzas();
        int i = this.size;
        long[] jArr = this.zzzr;
        if (i == jArr.length) {
            long[] jArr2 = new long[(((i * 3) / 2) + 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            this.zzzr = jArr2;
        }
        long[] jArr3 = this.zzzr;
        int i2 = this.size;
        this.size = i2 + 1;
        jArr3[i2] = longValue;
        return true;
    }

    public final /* synthetic */ zzhh zzae(int i) {
        if (i >= this.size) {
            return new zzhy(Arrays.copyOf(this.zzzr, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(getLong(i));
    }

    static {
        zzhy zzhy = new zzhy(new long[0], 0);
        zzzq = zzhy;
        zzhy.zzar();
    }
}
