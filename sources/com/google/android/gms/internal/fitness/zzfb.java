package com.google.android.gms.internal.fitness;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
abstract class zzfb<E> extends zzfp<E> {
    private int position;
    private final int size;

    protected zzfb(int i, int i2) {
        zzez.zzb(i2, i);
        this.size = i;
        this.position = i2;
    }

    /* access modifiers changed from: protected */
    public abstract E get(int i);

    public final boolean hasNext() {
        return this.position < this.size;
    }

    public final E next() {
        if (hasNext()) {
            int i = this.position;
            this.position = i + 1;
            return get(i);
        }
        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return this.position;
    }

    public final boolean hasPrevious() {
        return this.position > 0;
    }

    public final E previous() {
        if (hasPrevious()) {
            int i = this.position - 1;
            this.position = i;
            return get(i);
        }
        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return this.position - 1;
    }
}
