package com.google.android.gms.internal.fitness;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzhq<K> implements Iterator<Map.Entry<K, Object>> {
    private Iterator<Map.Entry<K, Object>> zzzh;

    public zzhq(Iterator<Map.Entry<K, Object>> it) {
        this.zzzh = it;
    }

    public final boolean hasNext() {
        return this.zzzh.hasNext();
    }

    public final void remove() {
        this.zzzh.remove();
    }

    public final /* synthetic */ Object next() {
        Map.Entry next = this.zzzh.next();
        return next.getValue() instanceof zzhl ? new zzhn(next) : next;
    }
}
