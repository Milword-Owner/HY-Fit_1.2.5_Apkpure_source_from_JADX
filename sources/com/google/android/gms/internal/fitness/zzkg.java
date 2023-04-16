package com.google.android.gms.internal.fitness;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public enum zzkg {
    DOUBLE(zzkj.DOUBLE, 1),
    FLOAT(zzkj.FLOAT, 5),
    INT64(zzkj.LONG, 0),
    UINT64(zzkj.LONG, 0),
    INT32(zzkj.INT, 0),
    FIXED64(zzkj.LONG, 1),
    FIXED32(zzkj.INT, 5),
    BOOL(zzkj.BOOLEAN, 0),
    STRING(zzkj.STRING, 2),
    GROUP(zzkj.MESSAGE, 3),
    MESSAGE(zzkj.MESSAGE, 2),
    BYTES(zzkj.BYTE_STRING, 2),
    UINT32(zzkj.INT, 0),
    ENUM(zzkj.ENUM, 0),
    SFIXED32(zzkj.INT, 5),
    SFIXED64(zzkj.LONG, 1),
    SINT32(zzkj.INT, 0),
    SINT64(zzkj.LONG, 0);
    
    private final zzkj zzadn;
    private final int zzado;

    private zzkg(zzkj zzkj, int i) {
        this.zzadn = zzkj;
        this.zzado = i;
    }

    public final zzkj zzdx() {
        return this.zzadn;
    }

    public final int zzdy() {
        return this.zzado;
    }
}
