package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public class HashAccumulator {
    @VisibleForTesting
    private static int zaam = 31;
    private int zaan = 1;

    @KeepForSdk
    public HashAccumulator addObject(Object obj) {
        this.zaan = (zaam * this.zaan) + (obj == null ? 0 : obj.hashCode());
        return this;
    }

    public final HashAccumulator zaa(boolean z) {
        this.zaan = (zaam * this.zaan) + (z ? 1 : 0);
        return this;
    }

    @KeepForSdk
    public int hash() {
        return this.zaan;
    }
}
