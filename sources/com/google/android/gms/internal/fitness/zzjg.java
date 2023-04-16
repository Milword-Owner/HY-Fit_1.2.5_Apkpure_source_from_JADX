package com.google.android.gms.internal.fitness;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzjg extends zzjm {
    private final /* synthetic */ zzjb zzabl;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzjg(zzjb zzjb) {
        super(zzjb, (zzje) null);
        this.zzabl = zzjb;
    }

    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzjd(this.zzabl, (zzje) null);
    }

    /* synthetic */ zzjg(zzjb zzjb, zzje zzje) {
        this(zzjb);
    }
}
