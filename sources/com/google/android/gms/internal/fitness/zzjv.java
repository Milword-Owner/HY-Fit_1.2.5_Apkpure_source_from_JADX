package com.google.android.gms.internal.fitness;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzjv implements Iterator<String> {
    private Iterator<String> zzabv = this.zzabw.zzabu.iterator();
    private final /* synthetic */ zzjt zzabw;

    zzjv(zzjt zzjt) {
        this.zzabw = zzjt;
    }

    public final boolean hasNext() {
        return this.zzabv.hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ Object next() {
        return this.zzabv.next();
    }
}
