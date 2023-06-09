package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
final class zzc implements DynamiteModule.VersionPolicy {
    zzc() {
    }

    public final DynamiteModule.VersionPolicy.zza zza(Context context, String str, DynamiteModule.VersionPolicy.zzb zzb) throws DynamiteModule.LoadingException {
        DynamiteModule.VersionPolicy.zza zza = new DynamiteModule.VersionPolicy.zza();
        zza.zzjh = zzb.zza(context, str, true);
        if (zza.zzjh != 0) {
            zza.zzji = 1;
        } else {
            zza.zzjg = zzb.getLocalVersion(context, str);
            if (zza.zzjg != 0) {
                zza.zzji = -1;
            }
        }
        return zza;
    }
}
