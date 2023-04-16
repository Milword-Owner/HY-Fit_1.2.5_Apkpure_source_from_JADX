package com.google.android.gms.fitness.service;

import android.os.RemoteException;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.fitness.data.DataPoint;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public interface SensorEventDispatcher {
    void publish(@RecentlyNonNull DataPoint dataPoint) throws RemoteException;

    void publish(@RecentlyNonNull List<DataPoint> list) throws RemoteException;
}
