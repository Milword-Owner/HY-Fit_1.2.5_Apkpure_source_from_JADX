package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.request.zzaw;
import com.google.android.gms.fitness.request.zzay;
import com.google.android.gms.fitness.request.zzba;
import com.google.android.gms.fitness.request.zzbc;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzcd extends zzb implements zzca {
    zzcd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitSessionsApi");
    }

    public final void zza(zzay zzay) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzay);
        zza(1, zza);
    }

    public final void zza(zzba zzba) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzba);
        zza(2, zza);
    }

    public final void zza(SessionInsertRequest sessionInsertRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) sessionInsertRequest);
        zza(3, zza);
    }

    public final void zza(SessionReadRequest sessionReadRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) sessionReadRequest);
        zza(4, zza);
    }

    public final void zza(zzaw zzaw) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzaw);
        zza(5, zza);
    }

    public final void zza(zzbc zzbc) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzbc);
        zza(6, zza);
    }
}
