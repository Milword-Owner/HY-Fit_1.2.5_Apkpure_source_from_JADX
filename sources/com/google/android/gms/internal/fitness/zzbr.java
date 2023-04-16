package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.zzai;
import com.google.android.gms.fitness.request.zzbg;
import com.google.android.gms.fitness.request.zzbk;
import com.google.android.gms.fitness.request.zzd;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzbr extends zzb implements zzbo {
    zzbr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitBleApi");
    }

    public final void zza(StartBleScanRequest startBleScanRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) startBleScanRequest);
        zza(1, zza);
    }

    public final void zza(zzbg zzbg) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzbg);
        zza(2, zza);
    }

    public final void zza(zzd zzd) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzd);
        zza(3, zza);
    }

    public final void zza(zzbk zzbk) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzbk);
        zza(4, zza);
    }

    public final void zza(zzai zzai) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzai);
        zza(5, zza);
    }
}
