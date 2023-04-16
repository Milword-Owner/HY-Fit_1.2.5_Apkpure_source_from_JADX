package com.google.android.gms.internal.fitness;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzhs extends zzfs<String> implements zzhr, RandomAccess {
    private static final zzhs zzzi;
    private static final zzhr zzzj = zzzi;
    private final List<Object> zzzk;

    public zzhs() {
        this(10);
    }

    public zzhs(int i) {
        this((ArrayList<Object>) new ArrayList(i));
    }

    private zzhs(ArrayList<Object> arrayList) {
        this.zzzk = arrayList;
    }

    public final int size() {
        return this.zzzk.size();
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzas();
        if (collection instanceof zzhr) {
            collection = ((zzhr) collection).zzch();
        }
        boolean addAll = this.zzzk.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final void clear() {
        zzas();
        this.zzzk.clear();
        this.modCount++;
    }

    public final Object zzaf(int i) {
        return this.zzzk.get(i);
    }

    private static String zzg(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzfx) {
            return ((zzfx) obj).zzav();
        }
        return zzhc.zzd((byte[]) obj);
    }

    public final List<?> zzch() {
        return Collections.unmodifiableList(this.zzzk);
    }

    public final zzhr zzci() {
        return zzaq() ? new zzjt(this) : this;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        zzas();
        return zzg(this.zzzk.set(i, (String) obj));
    }

    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    public final /* synthetic */ Object remove(int i) {
        zzas();
        Object remove = this.zzzk.remove(i);
        this.modCount++;
        return zzg(remove);
    }

    public final /* bridge */ /* synthetic */ boolean zzaq() {
        return super.zzaq();
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzas();
        this.zzzk.add(i, (String) obj);
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        return super.add(obj);
    }

    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final /* synthetic */ zzhh zzae(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzzk);
            return new zzhs((ArrayList<Object>) arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzzk.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzfx) {
            zzfx zzfx = (zzfx) obj;
            String zzav = zzfx.zzav();
            if (zzfx.zzaw()) {
                this.zzzk.set(i, zzav);
            }
            return zzav;
        }
        byte[] bArr = (byte[]) obj;
        String zzd = zzhc.zzd(bArr);
        if (zzhc.zzc(bArr)) {
            this.zzzk.set(i, zzd);
        }
        return zzd;
    }

    static {
        zzhs zzhs = new zzhs();
        zzzi = zzhs;
        zzhs.zzar();
    }
}
