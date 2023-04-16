package com.google.android.gms.internal.fitness;

import java.util.ListIterator;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzjw implements ListIterator<String> {
    private final /* synthetic */ zzjt zzabw;
    private ListIterator<String> zzabx = this.zzabw.zzabu.listIterator(this.zzaby);
    private final /* synthetic */ int zzaby;

    zzjw(zzjt zzjt, int i) {
        this.zzabw = zzjt;
        this.zzaby = i;
    }

    public final boolean hasNext() {
        return this.zzabx.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zzabx.hasPrevious();
    }

    public final int nextIndex() {
        return this.zzabx.nextIndex();
    }

    public final int previousIndex() {
        return this.zzabx.previousIndex();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ void add(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ void set(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ Object previous() {
        return this.zzabx.previous();
    }

    public final /* synthetic */ Object next() {
        return this.zzabx.next();
    }
}
