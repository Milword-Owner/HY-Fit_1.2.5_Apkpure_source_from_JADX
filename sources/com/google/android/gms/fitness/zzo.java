package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fitness.data.zzv;
import com.google.android.gms.fitness.request.zzam;
import com.google.android.gms.fitness.request.zzan;
import com.google.android.gms.fitness.request.zzar;
import com.google.android.gms.internal.fitness.zzap;
import com.google.android.gms.internal.fitness.zzby;
import com.google.android.gms.internal.fitness.zzcn;
import com.google.android.gms.internal.fitness.zzei;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzo implements RemoteCall<zzap, TaskCompletionSource<Boolean>> {
    private final /* synthetic */ ListenerHolder zzkb;

    zzo(SensorsClient sensorsClient, ListenerHolder listenerHolder) {
        this.zzkb = listenerHolder;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
        zzap zzap = (zzap) obj;
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
        zzam zzd = zzan.zzx().zzd(this.zzkb);
        if (zzd == null) {
            taskCompletionSource.setResult(false);
            return;
        }
        ((zzby) zzap.getService()).zza(new zzar((zzv) zzd, (PendingIntent) null, (zzcn) zzei.zzb(taskCompletionSource)));
    }
}
