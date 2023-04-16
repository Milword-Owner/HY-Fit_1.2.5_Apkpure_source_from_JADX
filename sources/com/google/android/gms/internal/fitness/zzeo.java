package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.BleDevicesResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzeo extends zzb implements zzem {
    zzeo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.ble.IBleDevicesCallback");
    }

    public final void zza(BleDevicesResult bleDevicesResult) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) bleDevicesResult);
        zzb(1, zza);
    }
}
