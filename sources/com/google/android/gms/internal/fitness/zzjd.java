package com.google.android.gms.internal.fitness;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzjd implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzabk;
    private final /* synthetic */ zzjb zzabl;

    private zzjd(zzjb zzjb) {
        this.zzabl = zzjb;
        this.pos = this.zzabl.zzabb.size();
    }

    public final boolean hasNext() {
        int i = this.pos;
        return (i > 0 && i <= this.zzabl.zzabb.size()) || zzdm().hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Map.Entry<K, V>> zzdm() {
        if (this.zzabk == null) {
            this.zzabk = this.zzabl.zzabe.entrySet().iterator();
        }
        return this.zzabk;
    }

    public final /* synthetic */ Object next() {
        if (zzdm().hasNext()) {
            return (Map.Entry) zzdm().next();
        }
        List zzb = this.zzabl.zzabb;
        int i = this.pos - 1;
        this.pos = i;
        return (Map.Entry) zzb.get(i);
    }

    /* synthetic */ zzjd(zzjb zzjb, zzje zzje) {
        this(zzjb);
    }
}
