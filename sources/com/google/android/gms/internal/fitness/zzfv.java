package com.google.android.gms.internal.fitness;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzfv {
    private static final Class<?> zzty = zzj("libcore.io.Memory");
    private static final boolean zztz = (zzj("org.robolectric.Robolectric") != null);

    static boolean zzat() {
        return zzty != null && !zztz;
    }

    static Class<?> zzau() {
        return zzty;
    }

    private static <T> Class<T> zzj(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
