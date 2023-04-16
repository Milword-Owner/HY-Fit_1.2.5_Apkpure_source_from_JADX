package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.GoalsReadRequest;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzbv extends zzb implements zzbs {
    zzbv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitGoalsApi");
    }

    public final void zza(GoalsReadRequest goalsReadRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) goalsReadRequest);
        zza(1, zza);
    }
}
