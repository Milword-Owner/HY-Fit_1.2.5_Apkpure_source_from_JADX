package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataTypeResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzbk extends zzb implements zzbi {
    zzbk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IDataTypeCallback");
    }

    public final void zzc(DataTypeResult dataTypeResult) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) dataTypeResult);
        zzb(1, zza);
    }
}
