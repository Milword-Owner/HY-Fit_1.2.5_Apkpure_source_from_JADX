package com.google.android.gms.internal.fitness;

import com.github.mikephil.charting.utils.Utils;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public enum zzhm {
    VOID(Void.class, Void.class, (Class<?>) null),
    INT(Integer.TYPE, Integer.class, 0),
    LONG(Long.TYPE, Long.class, 0L),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(Utils.DOUBLE_EPSILON)),
    BOOLEAN(Boolean.TYPE, Boolean.class, false),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzfx.class, zzfx.class, zzfx.zzub),
    ENUM(Integer.TYPE, Integer.class, (Class<?>) null),
    MESSAGE(Object.class, Object.class, (Class<?>) null);
    
    private final Class<?> zzyz;
    private final Class<?> zzza;
    private final Object zzzb;

    private zzhm(Class<?> cls, Class<?> cls2, Object obj) {
        this.zzyz = cls;
        this.zzza = cls2;
        this.zzzb = obj;
    }

    public final Class<?> zzcf() {
        return this.zzza;
    }
}
