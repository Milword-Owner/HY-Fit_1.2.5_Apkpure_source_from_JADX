package com.google.android.gms.fitness.data;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.fitness.zzb;
import com.google.android.gms.internal.fitness.zzd;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzw extends zzb implements zzv {
    zzw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.data.IDataSourceListener");
    }

    public final void zzc(DataPoint dataPoint) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) dataPoint);
        zzb(1, zza);
    }
}
