package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.zzak;
import com.google.android.gms.fitness.request.zzbi;
import com.google.android.gms.fitness.request.zzbm;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzbz extends zzb implements zzbw {
    zzbz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitRecordingApi");
    }

    public final void zza(zzbi zzbi) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzbi);
        zza(1, zza);
    }

    public final void zza(zzbm zzbm) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzbm);
        zza(2, zza);
    }

    public final void zza(zzak zzak) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzak);
        zza(3, zza);
    }
}
