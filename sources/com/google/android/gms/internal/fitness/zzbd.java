package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DailyTotalResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzbd extends zzb implements zzbb {
    zzbd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IDailyTotalCallback");
    }

    public final void zzc(DailyTotalResult dailyTotalResult) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) dailyTotalResult);
        zzb(1, zza);
    }
}
