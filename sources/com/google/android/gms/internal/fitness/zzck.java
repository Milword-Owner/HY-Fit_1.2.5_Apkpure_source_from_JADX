package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionStopResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzck extends zzb implements zzci {
    zzck(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.ISessionStopCallback");
    }

    public final void zza(SessionStopResult sessionStopResult) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) sessionStopResult);
        zzb(1, zza);
    }
}
