package com.google.android.gms.internal.fitness;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzjt extends AbstractList<String> implements zzhr, RandomAccess {
    /* access modifiers changed from: private */
    public final zzhr zzabu;

    public zzjt(zzhr zzhr) {
        this.zzabu = zzhr;
    }

    public final zzhr zzci() {
        return this;
    }

    public final Object zzaf(int i) {
        return this.zzabu.zzaf(i);
    }

    public final int size() {
        return this.zzabu.size();
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzjw(this, i);
    }

    public final Iterator<String> iterator() {
        return new zzjv(this);
    }

    public final List<?> zzch() {
        return this.zzabu.zzch();
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.zzabu.get(i);
    }
}
