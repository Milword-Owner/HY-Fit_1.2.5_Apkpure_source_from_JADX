package com.google.android.gms.fitness.request;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public interface zzad extends IInterface {
    void onDeviceFound(BleDevice bleDevice) throws RemoteException;

    void onScanStopped() throws RemoteException;
}
