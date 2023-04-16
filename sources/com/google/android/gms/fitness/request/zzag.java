package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.fitness.zza;
import com.google.android.gms.internal.fitness.zzd;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class zzag extends zza implements zzad {
    public zzag() {
        super("com.google.android.gms.fitness.request.IBleScanCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            onDeviceFound((BleDevice) zzd.zza(parcel, BleDevice.CREATOR));
        } else if (i != 2) {
            return false;
        } else {
            onScanStopped();
        }
        return true;
    }
}
