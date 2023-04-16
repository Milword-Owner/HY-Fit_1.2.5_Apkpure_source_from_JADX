package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionReadResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzcj extends zzb implements zzch {
    zzcj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.ISessionReadCallback");
    }

    public final void zza(SessionReadResult sessionReadResult) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) sessionReadResult);
        zzb(1, zza);
    }
}
