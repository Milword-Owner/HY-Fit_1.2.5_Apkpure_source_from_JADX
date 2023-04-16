package com.google.android.gms.internal.fitness;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzjj implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzabk;
    private final /* synthetic */ zzjb zzabl;
    private boolean zzabo;

    private zzjj(zzjb zzjb) {
        this.zzabl = zzjb;
        this.pos = -1;
    }

    public final boolean hasNext() {
        if (this.pos + 1 < this.zzabl.zzabb.size() || (!this.zzabl.zzabc.isEmpty() && zzdm().hasNext())) {
            return true;
        }
        return false;
    }

    public final void remove() {
        if (this.zzabo) {
            this.zzabo = false;
            this.zzabl.zzdf();
            if (this.pos < this.zzabl.zzabb.size()) {
                zzjb zzjb = this.zzabl;
                int i = this.pos;
                this.pos = i - 1;
                Object unused = zzjb.zzam(i);
                return;
            }
            zzdm().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }

    private final Iterator<Map.Entry<K, V>> zzdm() {
        if (this.zzabk == null) {
            this.zzabk = this.zzabl.zzabc.entrySet().iterator();
        }
        return this.zzabk;
    }

    public final /* synthetic */ Object next() {
        this.zzabo = true;
        int i = this.pos + 1;
        this.pos = i;
        if (i < this.zzabl.zzabb.size()) {
            return (Map.Entry) this.zzabl.zzabb.get(this.pos);
        }
        return (Map.Entry) zzdm().next();
    }

    /* synthetic */ zzjj(zzjb zzjb, zzje zzje) {
        this(zzjb);
    }
}
