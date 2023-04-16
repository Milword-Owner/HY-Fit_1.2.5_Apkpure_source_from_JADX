package com.google.android.gms.internal.fitness;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class zzfh<E> extends zzfd<E> implements Set<E> {
    @NullableDecl
    private transient zzfc<E> zztl;

    public static <E> zzfh<E> zza(E e, E e2, E e3, E e4, E e5) {
        int i = 5;
        Object[] objArr = {e, e2, e3, e4, e5};
        while (i != 0) {
            if (i == 1) {
                return new zzfn(objArr[0]);
            }
            int zzh = zzh(i);
            Object[] objArr2 = new Object[zzh];
            int i2 = zzh - 1;
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                Object zza = zzfj.zza(objArr[i5], i5);
                int hashCode = zza.hashCode();
                int zzg = zzfa.zzg(hashCode);
                while (true) {
                    int i6 = zzg & i2;
                    Object obj = objArr2[i6];
                    if (obj != null) {
                        if (obj.equals(zza)) {
                            break;
                        }
                        zzg++;
                    } else {
                        objArr[i4] = zza;
                        objArr2[i6] = zza;
                        i3 += hashCode;
                        i4++;
                        break;
                    }
                }
            }
            Arrays.fill(objArr, i4, i, (Object) null);
            if (i4 == 1) {
                return new zzfn(objArr[0], i3);
            }
            if (zzh(i4) < zzh / 2) {
                i = i4;
            } else {
                if (i4 < 3) {
                    objArr = Arrays.copyOf(objArr, i4);
                }
                return new zzfl(objArr, i3, objArr2, i2, i4);
            }
        }
        return zzfl.zzto;
    }

    /* access modifiers changed from: package-private */
    public boolean zzak() {
        return false;
    }

    private static int zzh(int i) {
        int max = Math.max(i, 2);
        boolean z = true;
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1) << 1;
            while (true) {
                double d = (double) highestOneBit;
                Double.isNaN(d);
                if (d * 0.7d >= ((double) max)) {
                    return highestOneBit;
                }
                highestOneBit <<= 1;
            }
        } else {
            if (max >= 1073741824) {
                z = false;
            }
            if (z) {
                return 1073741824;
            }
            throw new IllegalArgumentException("collection too large");
        }
    }

    zzfh() {
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfh) || !zzak() || !((zzfh) obj).zzak() || hashCode() == obj.hashCode()) {
            return zzfk.zza(this, obj);
        }
        return false;
    }

    public int hashCode() {
        Iterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i = ((i + (next != null ? next.hashCode() : 0)) ^ -1) ^ -1;
        }
        return i;
    }

    public final zzfc<E> zzaf() {
        zzfc<E> zzfc = this.zztl;
        if (zzfc != null) {
            return zzfc;
        }
        zzfc<E> zzal = zzal();
        this.zztl = zzal;
        return zzal;
    }

    /* access modifiers changed from: package-private */
    public zzfc<E> zzal() {
        return zzfc.zza(toArray());
    }

    public /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
