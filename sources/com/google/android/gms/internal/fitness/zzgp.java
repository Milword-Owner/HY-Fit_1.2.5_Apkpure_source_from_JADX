package com.google.android.gms.internal.fitness;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class zzgp {
    private static volatile boolean zzus = false;
    private static boolean zzut = true;
    private static volatile zzgp zzuu;
    private static final zzgp zzuv = new zzgp(true);
    private final Map<Object, Object> zzuw;

    public static zzgp zzbf() {
        zzgp zzgp = zzuu;
        if (zzgp == null) {
            synchronized (zzgp.class) {
                zzgp = zzuu;
                if (zzgp == null) {
                    zzgp = zzuv;
                    zzuu = zzgp;
                }
            }
        }
        return zzgp;
    }

    zzgp() {
        this.zzuw = new HashMap();
    }

    private zzgp(boolean z) {
        this.zzuw = Collections.emptyMap();
    }
}
