package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.fitness.zzb;
import com.google.android.gms.internal.fitness.zzd;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzaf extends zzb implements zzad {
    zzaf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.request.IBleScanCallback");
    }

    public final void onDeviceFound(BleDevice bleDevice) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) bleDevice);
        zzb(1, zza);
    }

    public final void onScanStopped() throws RemoteException {
        zzb(2, zza());
    }
}
