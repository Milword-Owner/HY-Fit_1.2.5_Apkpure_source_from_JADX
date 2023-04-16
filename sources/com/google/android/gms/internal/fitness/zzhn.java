package com.google.android.gms.internal.fitness;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzhn<K> implements Map.Entry<K, Object> {
    private Map.Entry<K, zzhl> zzzd;

    private zzhn(Map.Entry<K, zzhl> entry) {
        this.zzzd = entry;
    }

    public final K getKey() {
        return this.zzzd.getKey();
    }

    public final Object getValue() {
        if (this.zzzd.getValue() == null) {
            return null;
        }
        return zzhl.zzce();
    }

    public final zzhl zzcg() {
        return this.zzzd.getValue();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzik) {
            return this.zzzd.getValue().zzh((zzik) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
