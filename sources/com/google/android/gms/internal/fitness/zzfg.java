package com.google.android.gms.internal.fitness;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzfg extends zzfm<T> {
    private boolean zztj;
    private final /* synthetic */ Object zztk;

    zzfg(Object obj) {
        this.zztk = obj;
    }

    public final boolean hasNext() {
        return !this.zztj;
    }

    public final T next() {
        if (!this.zztj) {
            this.zztj = true;
            return this.zztk;
        }
        throw new NoSuchElementException();
    }
}
