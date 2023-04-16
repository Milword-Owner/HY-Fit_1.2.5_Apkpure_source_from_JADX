package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.zzap;
import com.google.android.gms.fitness.request.zzar;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzcb extends zzb implements zzby {
    zzcb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitSensorsApi");
    }

    public final void zza(DataSourcesRequest dataSourcesRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) dataSourcesRequest);
        zza(1, zza);
    }

    public final void zza(zzap zzap) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzap);
        zza(2, zza);
    }

    public final void zza(zzar zzar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzar);
        zza(3, zza);
    }
}
