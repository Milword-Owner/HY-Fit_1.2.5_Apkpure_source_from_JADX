package com.google.android.gms.internal.fitness;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzig implements zzid {
    zzig() {
    }

    public final zzib<?, ?> zzj(Object obj) {
        zzic zzic = (zzic) obj;
        throw new NoSuchMethodError();
    }

    public final Map<?, ?> zzh(Object obj) {
        return (zzie) obj;
    }

    public final Object zzi(Object obj) {
        ((zzie) obj).zzar();
        return obj;
    }

    public final Object zzc(Object obj, Object obj2) {
        zzie zzie = (zzie) obj;
        zzie zzie2 = (zzie) obj2;
        if (!zzie2.isEmpty()) {
            if (!zzie.isMutable()) {
                zzie = zzie.zzcn();
            }
            zzie.zza(zzie2);
        }
        return zzie;
    }

    public final int zzb(int i, Object obj, Object obj2) {
        zzie zzie = (zzie) obj;
        zzic zzic = (zzic) obj2;
        if (zzie.isEmpty()) {
            return 0;
        }
        Iterator it = zzie.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
