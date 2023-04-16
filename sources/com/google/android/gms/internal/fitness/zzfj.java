package com.google.android.gms.internal.fitness;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzfj {
    static Object[] zzc(Object[] objArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            zza(objArr[i2], i2);
        }
        return objArr;
    }

    static Object zza(Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        StringBuilder sb = new StringBuilder(20);
        sb.append("at index ");
        sb.append(i);
        throw new NullPointerException(sb.toString());
    }
}
