package com.google.android.gms.internal.fitness;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzgq {
    private static final zzgo<?> zzux = new zzgr();
    private static final zzgo<?> zzuy = zzbg();

    private static zzgo<?> zzbg() {
        try {
            return (zzgo) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static zzgo<?> zzbh() {
        return zzux;
    }

    static zzgo<?> zzbi() {
        zzgo<?> zzgo = zzuy;
        if (zzgo != null) {
            return zzgo;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
