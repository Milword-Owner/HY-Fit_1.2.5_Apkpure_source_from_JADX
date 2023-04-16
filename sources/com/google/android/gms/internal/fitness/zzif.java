package com.google.android.gms.internal.fitness;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzif {
    private static final zzid zzzw = zzcr();
    private static final zzid zzzx = new zzig();

    static zzid zzcp() {
        return zzzw;
    }

    static zzid zzcq() {
        return zzzx;
    }

    private static zzid zzcr() {
        try {
            return (zzid) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
