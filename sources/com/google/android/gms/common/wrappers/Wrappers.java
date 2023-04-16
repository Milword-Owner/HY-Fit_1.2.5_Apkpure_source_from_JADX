package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
public class Wrappers {
    private static Wrappers zziq = new Wrappers();
    private PackageManagerWrapper zzip = null;

    @VisibleForTesting
    private final synchronized PackageManagerWrapper zzj(Context context) {
        if (this.zzip == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.zzip = new PackageManagerWrapper(context);
        }
        return this.zzip;
    }

    @KeepForSdk
    public static PackageManagerWrapper packageManager(Context context) {
        return zziq.zzj(context);
    }
}
