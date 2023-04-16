package com.google.android.gms.internal.fitness;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzir {
    private static final zzip zzaar = zzcx();
    private static final zzip zzaas = new zzis();

    static zzip zzcv() {
        return zzaar;
    }

    static zzip zzcw() {
        return zzaas;
    }

    private static zzip zzcx() {
        try {
            return (zzip) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
