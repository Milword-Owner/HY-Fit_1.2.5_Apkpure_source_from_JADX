package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.request.zzr;
import com.google.android.gms.fitness.request.zzz;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzbt extends zzb implements zzbq {
    zzbt(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitConfigApi");
    }

    public final void zza(DataTypeCreateRequest dataTypeCreateRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) dataTypeCreateRequest);
        zza(1, zza);
    }

    public final void zza(zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzr);
        zza(2, zza);
    }

    public final void zza(zzz zzz) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzz);
        zza(22, zza);
    }
}
