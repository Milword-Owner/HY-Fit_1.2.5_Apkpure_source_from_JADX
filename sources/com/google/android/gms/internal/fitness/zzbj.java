package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataSourcesResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzbj extends zzb implements zzbh {
    zzbj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IDataSourcesCallback");
    }

    public final void zza(DataSourcesResult dataSourcesResult) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) dataSourcesResult);
        zzb(1, zza);
    }
}
