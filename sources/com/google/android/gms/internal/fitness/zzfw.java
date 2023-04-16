package com.google.android.gms.internal.fitness;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzfw extends zzfy {
    private final int limit = this.zzua.size();
    private int position = 0;
    private final /* synthetic */ zzfx zzua;

    zzfw(zzfx zzfx) {
        this.zzua = zzfx;
    }

    public final boolean hasNext() {
        return this.position < this.limit;
    }

    public final byte nextByte() {
        int i = this.position;
        if (i < this.limit) {
            this.position = i + 1;
            return this.zzua.zzk(i);
        }
        throw new NoSuchElementException();
    }
}
