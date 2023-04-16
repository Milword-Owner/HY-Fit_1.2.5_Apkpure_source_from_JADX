package com.google.android.gms.internal.fitness;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzfz implements Comparator<zzfx> {
    zzfz() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzfx zzfx = (zzfx) obj;
        zzfx zzfx2 = (zzfx) obj2;
        zzgc zzgc = (zzgc) zzfx.iterator();
        zzgc zzgc2 = (zzgc) zzfx2.iterator();
        while (zzgc.hasNext() && zzgc2.hasNext()) {
            int compare = Integer.compare(zzfx.zza(zzgc.nextByte()), zzfx.zza(zzgc2.nextByte()));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzfx.size(), zzfx2.size());
    }
}
