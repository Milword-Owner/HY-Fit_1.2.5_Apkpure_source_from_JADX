package com.google.android.gms.internal.fitness;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
class zzjm extends AbstractSet<Map.Entry<K, V>> {
    private final /* synthetic */ zzjb zzabl;

    private zzjm(zzjb zzjb) {
        this.zzabl = zzjb;
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return new zzjj(this.zzabl, (zzje) null);
    }

    public int size() {
        return this.zzabl.size();
    }

    public boolean contains(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.zzabl.get(entry.getKey());
        Object value = entry.getValue();
        if (obj2 != value) {
            return obj2 != null && obj2.equals(value);
        }
        return true;
    }

    public boolean remove(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zzabl.remove(entry.getKey());
        return true;
    }

    public void clear() {
        this.zzabl.clear();
    }

    public /* synthetic */ boolean add(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zzabl.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    /* synthetic */ zzjm(zzjb zzjb, zzje zzje) {
        this(zzjb);
    }
}
