package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataReadResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzbe extends zzb implements zzbc {
    zzbe(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IDataReadCallback");
    }

    public final void zza(DataReadResult dataReadResult) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) dataReadResult);
        zzb(1, zza);
    }
}
