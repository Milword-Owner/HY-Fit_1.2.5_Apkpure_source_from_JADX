package com.google.android.gms.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fitness.request.zza;
import com.google.android.gms.fitness.request.zzad;
import com.google.android.gms.fitness.request.zzbg;
import com.google.android.gms.fitness.request.zze;
import com.google.android.gms.internal.fitness.zzbo;
import com.google.android.gms.internal.fitness.zzcn;
import com.google.android.gms.internal.fitness.zzei;
import com.google.android.gms.internal.fitness.zzk;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzc implements RemoteCall<zzk, TaskCompletionSource<Boolean>> {
    private final /* synthetic */ ListenerHolder zzkb;

    zzc(BleClient bleClient, ListenerHolder listenerHolder) {
        this.zzkb = listenerHolder;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
        zzk zzk = (zzk) obj;
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
        zza zzb = zze.zzu().zzb(this.zzkb);
        if (zzb == null) {
            taskCompletionSource.setResult(false);
            return;
        }
        ((zzbo) zzk.getService()).zza(new zzbg((zzad) zzb, (zzcn) zzei.zzb(taskCompletionSource)));
    }
}
