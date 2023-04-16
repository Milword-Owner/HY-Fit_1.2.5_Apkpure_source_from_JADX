package com.google.android.gms.internal.fitness;

import com.github.mikephil.charting.utils.Utils;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public enum zzkj {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(Utils.DOUBLE_EPSILON)),
    BOOLEAN(false),
    STRING(""),
    BYTE_STRING(zzfx.zzub),
    ENUM((String) null),
    MESSAGE((String) null);
    
    private final Object zzzb;

    private zzkj(Object obj) {
        this.zzzb = obj;
    }
}
