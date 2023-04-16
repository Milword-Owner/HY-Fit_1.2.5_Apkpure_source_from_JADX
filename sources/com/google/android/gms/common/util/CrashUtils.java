package com.google.android.gms.common.util;

import android.content.Context;
import android.os.DropBoxManager;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
public final class CrashUtils {
    private static final String[] zzgv = {"android.", "com.android.", "dalvik.", "java.", "javax."};
    private static DropBoxManager zzgw = null;
    private static boolean zzgx = false;
    private static int zzgy = -1;
    @GuardedBy("CrashUtils.class")
    private static int zzgz = 0;
    @GuardedBy("CrashUtils.class")
    private static int zzha = 0;

    @KeepForSdk
    public static boolean addDynamiteErrorToDropBox(Context context, Throwable th) {
        return zza(context, th, 536870912);
    }

    private static boolean zza(Context context, Throwable th, int i) {
        try {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(th);
            return false;
        } catch (Exception e) {
            Log.e("CrashUtils", "Error adding exception to DropBox!", e);
            return false;
        }
    }
}
