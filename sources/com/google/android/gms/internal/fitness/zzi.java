package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.concurrent.TimeUnit;

@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzi {
    public static long zza(long j, TimeUnit timeUnit, TimeUnit timeUnit2) {
        return timeUnit.convert(timeUnit2.convert(j, timeUnit), timeUnit2);
    }
}
