package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.request.zzf;
import com.google.android.gms.fitness.request.zzj;
import com.google.android.gms.fitness.request.zzv;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzbx extends zzb implements zzbu {
    zzbx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitHistoryApi");
    }

    public final void zza(DataReadRequest dataReadRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) dataReadRequest);
        zza(1, zza);
    }

    public final void zza(zzj zzj) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzj);
        zza(2, zza);
    }

    public final void zza(DataDeleteRequest dataDeleteRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) dataDeleteRequest);
        zza(3, zza);
    }

    public final void zza(zzf zzf) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzf);
        zza(7, zza);
    }

    public final void zza(DataUpdateRequest dataUpdateRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) dataUpdateRequest);
        zza(9, zza);
    }

    public final void zza(DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) dataUpdateListenerRegistrationRequest);
        zza(10, zza);
    }

    public final void zza(zzv zzv) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzv);
        zza(11, zza);
    }
}
