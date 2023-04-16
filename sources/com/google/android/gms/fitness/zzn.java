package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.request.zzan;
import com.google.android.gms.internal.fitness.zzap;
import com.google.android.gms.internal.fitness.zzby;
import com.google.android.gms.internal.fitness.zzei;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzn implements RemoteCall<zzap, TaskCompletionSource<Void>> {
    private final /* synthetic */ ListenerHolder zzkb;
    private final /* synthetic */ SensorRequest zzkx;

    zzn(SensorsClient sensorsClient, ListenerHolder listenerHolder, SensorRequest sensorRequest) {
        this.zzkb = listenerHolder;
        this.zzkx = sensorRequest;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
        ((zzby) ((zzap) obj).getService()).zza(new com.google.android.gms.fitness.request.zzap(this.zzkx, zzan.zzx().zzc(this.zzkb), (PendingIntent) null, zzei.zza((TaskCompletionSource) obj2)));
    }
}
