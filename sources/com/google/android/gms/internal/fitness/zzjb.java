package com.google.android.gms.internal.fitness;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
class zzjb<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private final int zzaba;
    /* access modifiers changed from: private */
    public List<zzjk> zzabb;
    /* access modifiers changed from: private */
    public Map<K, V> zzabc;
    private volatile zzjm zzabd;
    /* access modifiers changed from: private */
    public Map<K, V> zzabe;
    private volatile zzjg zzabf;
    private boolean zzvc;

    static <FieldDescriptorType extends zzgv<FieldDescriptorType>> zzjb<FieldDescriptorType, Object> zzak(int i) {
        return new zzje(i);
    }

    private zzjb(int i) {
        this.zzaba = i;
        this.zzabb = Collections.emptyList();
        this.zzabc = Collections.emptyMap();
        this.zzabe = Collections.emptyMap();
    }

    public void zzar() {
        Map<K, V> map;
        Map<K, V> map2;
        if (!this.zzvc) {
            if (this.zzabc.isEmpty()) {
                map = Collections.emptyMap();
            } else {
                map = Collections.unmodifiableMap(this.zzabc);
            }
            this.zzabc = map;
            if (this.zzabe.isEmpty()) {
                map2 = Collections.emptyMap();
            } else {
                map2 = Collections.unmodifiableMap(this.zzabe);
            }
            this.zzabe = map2;
            this.zzvc = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzvc;
    }

    public final int zzdc() {
        return this.zzabb.size();
    }

    public final Map.Entry<K, V> zzal(int i) {
        return this.zzabb.get(i);
    }

    public final Iterable<Map.Entry<K, V>> zzdd() {
        if (this.zzabc.isEmpty()) {
            return zzjf.zzdn();
        }
        return this.zzabc.entrySet();
    }

    public int size() {
        return this.zzabb.size() + this.zzabc.size();
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzabc.containsKey(comparable);
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        if (zza >= 0) {
            return this.zzabb.get(zza).getValue();
        }
        return this.zzabc.get(comparable);
    }

    /* renamed from: zza */
    public final V put(K k, V v) {
        zzdf();
        int zza = zza(k);
        if (zza >= 0) {
            return this.zzabb.get(zza).setValue(v);
        }
        zzdf();
        if (this.zzabb.isEmpty() && !(this.zzabb instanceof ArrayList)) {
            this.zzabb = new ArrayList(this.zzaba);
        }
        int i = -(zza + 1);
        if (i >= this.zzaba) {
            return zzdg().put(k, v);
        }
        int size = this.zzabb.size();
        int i2 = this.zzaba;
        if (size == i2) {
            zzjk remove = this.zzabb.remove(i2 - 1);
            zzdg().put((Comparable) remove.getKey(), remove.getValue());
        }
        this.zzabb.add(i, new zzjk(this, k, v));
        return null;
    }

    public void clear() {
        zzdf();
        if (!this.zzabb.isEmpty()) {
            this.zzabb.clear();
        }
        if (!this.zzabc.isEmpty()) {
            this.zzabc.clear();
        }
    }

    public V remove(Object obj) {
        zzdf();
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        if (zza >= 0) {
            return zzam(zza);
        }
        if (this.zzabc.isEmpty()) {
            return null;
        }
        return this.zzabc.remove(comparable);
    }

    /* access modifiers changed from: private */
    public final V zzam(int i) {
        zzdf();
        V value = this.zzabb.remove(i).getValue();
        if (!this.zzabc.isEmpty()) {
            Iterator it = zzdg().entrySet().iterator();
            this.zzabb.add(new zzjk(this, (Map.Entry) it.next()));
            it.remove();
        }
        return value;
    }

    private final int zza(K k) {
        int size = this.zzabb.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) this.zzabb.get(size).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            int compareTo2 = k.compareTo((Comparable) this.zzabb.get(i2).getKey());
            if (compareTo2 < 0) {
                size = i2 - 1;
            } else if (compareTo2 <= 0) {
                return i2;
            } else {
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zzabd == null) {
            this.zzabd = new zzjm(this, (zzje) null);
        }
        return this.zzabd;
    }

    /* access modifiers changed from: package-private */
    public final Set<Map.Entry<K, V>> zzde() {
        if (this.zzabf == null) {
            this.zzabf = new zzjg(this, (zzje) null);
        }
        return this.zzabf;
    }

    /* access modifiers changed from: private */
    public final void zzdf() {
        if (this.zzvc) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzdg() {
        zzdf();
        if (this.zzabc.isEmpty() && !(this.zzabc instanceof TreeMap)) {
            this.zzabc = new TreeMap();
            this.zzabe = ((TreeMap) this.zzabc).descendingMap();
        }
        return (SortedMap) this.zzabc;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzjb)) {
            return super.equals(obj);
        }
        zzjb zzjb = (zzjb) obj;
        int size = size();
        if (size != zzjb.size()) {
            return false;
        }
        int zzdc = zzdc();
        if (zzdc != zzjb.zzdc()) {
            return entrySet().equals(zzjb.entrySet());
        }
        for (int i = 0; i < zzdc; i++) {
            if (!zzal(i).equals(zzjb.zzal(i))) {
                return false;
            }
        }
        if (zzdc != size) {
            return this.zzabc.equals(zzjb.zzabc);
        }
        return true;
    }

    public int hashCode() {
        int zzdc = zzdc();
        int i = 0;
        for (int i2 = 0; i2 < zzdc; i2++) {
            i += this.zzabb.get(i2).hashCode();
        }
        return this.zzabc.size() > 0 ? i + this.zzabc.hashCode() : i;
    }

    /* synthetic */ zzjb(int i, zzje zzje) {
        this(i);
    }
}
