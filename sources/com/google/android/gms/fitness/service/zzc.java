package com.google.android.gms.fitness.service;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.zzv;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzc implements SensorEventDispatcher {
    private final zzv zzrt;

    zzc(zzv zzv) {
        this.zzrt = (zzv) Preconditions.checkNotNull(zzv);
    }

    public final void publish(DataPoint dataPoint) throws RemoteException {
        dataPoint.zzh();
        this.zzrt.zzc(dataPoint);
    }

    public final void publish(List<DataPoint> list) throws RemoteException {
        for (DataPoint publish : list) {
            publish(publish);
        }
    }
}
