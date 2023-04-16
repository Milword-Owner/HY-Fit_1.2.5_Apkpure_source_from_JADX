package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.GoalsResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzbp extends zzb implements zzbn {
    zzbp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoalsReadCallback");
    }

    public final void zza(GoalsResult goalsResult) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) goalsResult);
        zzb(1, zza);
    }
}
