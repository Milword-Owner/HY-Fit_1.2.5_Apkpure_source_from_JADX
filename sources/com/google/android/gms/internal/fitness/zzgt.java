package com.google.android.gms.internal.fitness;

import com.google.android.gms.internal.fitness.zzgv;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzgt<T extends zzgv<T>> {
    private static final zzgt zzve = new zzgt(true);
    final zzjb<T, Object> zzvb;
    private boolean zzvc;
    private boolean zzvd;

    private zzgt() {
        this.zzvb = zzjb.zzak(16);
    }

    private zzgt(boolean z) {
        this(zzjb.zzak(0));
        zzar();
    }

    private zzgt(zzjb<T, Object> zzjb) {
        this.zzvb = zzjb;
        zzar();
    }

    public static <T extends zzgv<T>> zzgt<T> zzbj() {
        return zzve;
    }

    public final void zzar() {
        if (!this.zzvc) {
            this.zzvb.zzar();
            this.zzvc = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzvc;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzgt)) {
            return false;
        }
        return this.zzvb.equals(((zzgt) obj).zzvb);
    }

    public final int hashCode() {
        return this.zzvb.hashCode();
    }

    public final Iterator<Map.Entry<T, Object>> iterator() {
        if (this.zzvd) {
            return new zzhq(this.zzvb.entrySet().iterator());
        }
        return this.zzvb.entrySet().iterator();
    }

    /* access modifiers changed from: package-private */
    public final Iterator<Map.Entry<T, Object>> descendingIterator() {
        if (this.zzvd) {
            return new zzhq(this.zzvb.zzde().iterator());
        }
        return this.zzvb.zzde().iterator();
    }

    private final Object zza(T t) {
        Object obj = this.zzvb.get(t);
        if (!(obj instanceof zzhl)) {
            return obj;
        }
        zzhl zzhl = (zzhl) obj;
        return zzhl.zzce();
    }

    private final void zza(T t, Object obj) {
        if (!t.zzbn()) {
            zza(t.zzbl(), obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(t.zzbl(), obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzhl) {
            this.zzvd = true;
        }
        this.zzvb.put(t, obj);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0025, code lost:
        if ((r3 instanceof com.google.android.gms.internal.fitness.zzhb) == false) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        if ((r3 instanceof byte[]) == false) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001c, code lost:
        if ((r3 instanceof com.google.android.gms.internal.fitness.zzhl) == false) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(com.google.android.gms.internal.fitness.zzkg r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.fitness.zzhc.checkNotNull(r3)
            int[] r0 = com.google.android.gms.internal.fitness.zzgs.zzuz
            com.google.android.gms.internal.fitness.zzkj r2 = r2.zzdx()
            int r2 = r2.ordinal()
            r2 = r0[r2]
            r0 = 1
            r1 = 0
            switch(r2) {
                case 1: goto L_0x0040;
                case 2: goto L_0x003d;
                case 3: goto L_0x003a;
                case 4: goto L_0x0037;
                case 5: goto L_0x0034;
                case 6: goto L_0x0031;
                case 7: goto L_0x0028;
                case 8: goto L_0x001f;
                case 9: goto L_0x0016;
                default: goto L_0x0014;
            }
        L_0x0014:
            r0 = 0
            goto L_0x0042
        L_0x0016:
            boolean r2 = r3 instanceof com.google.android.gms.internal.fitness.zzik
            if (r2 != 0) goto L_0x0042
            boolean r2 = r3 instanceof com.google.android.gms.internal.fitness.zzhl
            if (r2 == 0) goto L_0x0014
            goto L_0x0042
        L_0x001f:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L_0x0042
            boolean r2 = r3 instanceof com.google.android.gms.internal.fitness.zzhb
            if (r2 == 0) goto L_0x0014
            goto L_0x0042
        L_0x0028:
            boolean r2 = r3 instanceof com.google.android.gms.internal.fitness.zzfx
            if (r2 != 0) goto L_0x0042
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L_0x0014
            goto L_0x0042
        L_0x0031:
            boolean r0 = r3 instanceof java.lang.String
            goto L_0x0042
        L_0x0034:
            boolean r0 = r3 instanceof java.lang.Boolean
            goto L_0x0042
        L_0x0037:
            boolean r0 = r3 instanceof java.lang.Double
            goto L_0x0042
        L_0x003a:
            boolean r0 = r3 instanceof java.lang.Float
            goto L_0x0042
        L_0x003d:
            boolean r0 = r3 instanceof java.lang.Long
            goto L_0x0042
        L_0x0040:
            boolean r0 = r3 instanceof java.lang.Integer
        L_0x0042:
            if (r0 == 0) goto L_0x0045
            return
        L_0x0045:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            goto L_0x004e
        L_0x004d:
            throw r2
        L_0x004e:
            goto L_0x004d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fitness.zzgt.zza(com.google.android.gms.internal.fitness.zzkg, java.lang.Object):void");
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzvb.zzdc(); i++) {
            if (!zzb(this.zzvb.zzal(i))) {
                return false;
            }
        }
        for (Map.Entry<T, Object> zzb : this.zzvb.zzdd()) {
            if (!zzb(zzb)) {
                return false;
            }
        }
        return true;
    }

    private static <T extends zzgv<T>> boolean zzb(Map.Entry<T, Object> entry) {
        zzgv zzgv = (zzgv) entry.getKey();
        if (zzgv.zzbm() == zzkj.MESSAGE) {
            if (zzgv.zzbn()) {
                for (zzik isInitialized : (List) entry.getValue()) {
                    if (!isInitialized.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzik) {
                    if (!((zzik) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof zzhl) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzgt<T> zzgt) {
        for (int i = 0; i < zzgt.zzvb.zzdc(); i++) {
            zzc(zzgt.zzvb.zzal(i));
        }
        for (Map.Entry<T, Object> zzc : zzgt.zzvb.zzdd()) {
            zzc(zzc);
        }
    }

    private static Object zzf(Object obj) {
        if (obj instanceof zziq) {
            return ((zziq) obj).zzao();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final void zzc(Map.Entry<T, Object> entry) {
        Object obj;
        zzgv zzgv = (zzgv) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzhl) {
            zzhl zzhl = (zzhl) value;
            value = zzhl.zzce();
        }
        if (zzgv.zzbn()) {
            Object zza = zza(zzgv);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object zzf : (List) value) {
                ((List) zza).add(zzf(zzf));
            }
            this.zzvb.put(zzgv, zza);
        } else if (zzgv.zzbm() == zzkj.MESSAGE) {
            Object zza2 = zza(zzgv);
            if (zza2 == null) {
                this.zzvb.put(zzgv, zzf(value));
                return;
            }
            if (zza2 instanceof zziq) {
                obj = zzgv.zza((zziq) zza2, (zziq) value);
            } else {
                obj = zzgv.zza(((zzik) zza2).zzbt(), (zzik) value).zzca();
            }
            this.zzvb.put(zzgv, obj);
        } else {
            this.zzvb.put(zzgv, zzf(value));
        }
    }

    static void zza(zzgk zzgk, zzkg zzkg, int i, Object obj) throws IOException {
        if (zzkg == zzkg.GROUP) {
            zzik zzik = (zzik) obj;
            zzhc.zzf(zzik);
            zzgk.writeTag(i, 3);
            zzik.zzb(zzgk);
            zzgk.writeTag(i, 4);
            return;
        }
        zzgk.writeTag(i, zzkg.zzdy());
        switch (zzkg) {
            case DOUBLE:
                zzgk.zzb(((Double) obj).doubleValue());
                return;
            case FLOAT:
                zzgk.zzb(((Float) obj).floatValue());
                return;
            case INT64:
                zzgk.zza(((Long) obj).longValue());
                return;
            case UINT64:
                zzgk.zza(((Long) obj).longValue());
                return;
            case INT32:
                zzgk.zzn(((Integer) obj).intValue());
                return;
            case FIXED64:
                zzgk.zzc(((Long) obj).longValue());
                return;
            case FIXED32:
                zzgk.zzq(((Integer) obj).intValue());
                return;
            case BOOL:
                zzgk.zza(((Boolean) obj).booleanValue());
                return;
            case GROUP:
                ((zzik) obj).zzb(zzgk);
                return;
            case MESSAGE:
                zzgk.zzb((zzik) obj);
                return;
            case STRING:
                if (obj instanceof zzfx) {
                    zzgk.zza((zzfx) obj);
                    return;
                } else {
                    zzgk.zzl((String) obj);
                    return;
                }
            case BYTES:
                if (obj instanceof zzfx) {
                    zzgk.zza((zzfx) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzgk.zzb(bArr, 0, bArr.length);
                return;
            case UINT32:
                zzgk.zzo(((Integer) obj).intValue());
                return;
            case SFIXED32:
                zzgk.zzq(((Integer) obj).intValue());
                return;
            case SFIXED64:
                zzgk.zzc(((Long) obj).longValue());
                return;
            case SINT32:
                zzgk.zzp(((Integer) obj).intValue());
                return;
            case SINT64:
                zzgk.zzb(((Long) obj).longValue());
                return;
            case ENUM:
                if (obj instanceof zzhb) {
                    zzgk.zzn(((zzhb) obj).zzc());
                    return;
                } else {
                    zzgk.zzn(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public final int zzbk() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzvb.zzdc(); i2++) {
            i += zzd(this.zzvb.zzal(i2));
        }
        for (Map.Entry<T, Object> zzd : this.zzvb.zzdd()) {
            i += zzd(zzd);
        }
        return i;
    }

    private static int zzd(Map.Entry<T, Object> entry) {
        zzgv zzgv = (zzgv) entry.getKey();
        Object value = entry.getValue();
        if (zzgv.zzbm() != zzkj.MESSAGE || zzgv.zzbn() || zzgv.zzbo()) {
            return zzb((zzgv<?>) zzgv, value);
        }
        if (value instanceof zzhl) {
            return zzgk.zzb(((zzgv) entry.getKey()).zzc(), (zzhp) (zzhl) value);
        }
        return zzgk.zzb(((zzgv) entry.getKey()).zzc(), (zzik) value);
    }

    static int zza(zzkg zzkg, int i, Object obj) {
        int zzr = zzgk.zzr(i);
        if (zzkg == zzkg.GROUP) {
            zzhc.zzf((zzik) obj);
            zzr <<= 1;
        }
        return zzr + zzb(zzkg, obj);
    }

    private static int zzb(zzkg zzkg, Object obj) {
        switch (zzkg) {
            case DOUBLE:
                return zzgk.zzc(((Double) obj).doubleValue());
            case FLOAT:
                return zzgk.zzc(((Float) obj).floatValue());
            case INT64:
                return zzgk.zzd(((Long) obj).longValue());
            case UINT64:
                return zzgk.zze(((Long) obj).longValue());
            case INT32:
                return zzgk.zzs(((Integer) obj).intValue());
            case FIXED64:
                return zzgk.zzg(((Long) obj).longValue());
            case FIXED32:
                return zzgk.zzv(((Integer) obj).intValue());
            case BOOL:
                return zzgk.zzb(((Boolean) obj).booleanValue());
            case GROUP:
                return zzgk.zzd((zzik) obj);
            case MESSAGE:
                if (obj instanceof zzhl) {
                    return zzgk.zza((zzhp) (zzhl) obj);
                }
                return zzgk.zzc((zzik) obj);
            case STRING:
                if (obj instanceof zzfx) {
                    return zzgk.zzb((zzfx) obj);
                }
                return zzgk.zzm((String) obj);
            case BYTES:
                if (obj instanceof zzfx) {
                    return zzgk.zzb((zzfx) obj);
                }
                return zzgk.zzb((byte[]) obj);
            case UINT32:
                return zzgk.zzt(((Integer) obj).intValue());
            case SFIXED32:
                return zzgk.zzw(((Integer) obj).intValue());
            case SFIXED64:
                return zzgk.zzh(((Long) obj).longValue());
            case SINT32:
                return zzgk.zzu(((Integer) obj).intValue());
            case SINT64:
                return zzgk.zzf(((Long) obj).longValue());
            case ENUM:
                if (obj instanceof zzhb) {
                    return zzgk.zzx(((zzhb) obj).zzc());
                }
                return zzgk.zzx(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zzb(zzgv<?> zzgv, Object obj) {
        zzkg zzbl = zzgv.zzbl();
        int zzc = zzgv.zzc();
        if (!zzgv.zzbn()) {
            return zza(zzbl, zzc, obj);
        }
        int i = 0;
        if (zzgv.zzbo()) {
            for (Object zzb : (List) obj) {
                i += zzb(zzbl, zzb);
            }
            return zzgk.zzr(zzc) + i + zzgk.zzz(i);
        }
        for (Object zza : (List) obj) {
            i += zza(zzbl, zzc, zza);
        }
        return i;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzgt zzgt = new zzgt();
        for (int i = 0; i < this.zzvb.zzdc(); i++) {
            Map.Entry<T, Object> zzal = this.zzvb.zzal(i);
            zzgt.zza((zzgv) zzal.getKey(), zzal.getValue());
        }
        for (Map.Entry next : this.zzvb.zzdd()) {
            zzgt.zza((zzgv) next.getKey(), next.getValue());
        }
        zzgt.zzvd = this.zzvd;
        return zzgt;
    }
}
