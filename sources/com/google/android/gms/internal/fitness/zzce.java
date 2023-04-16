package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzce extends zzb implements zzcc {
    zzce(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IListSubscriptionsCallback");
    }

    public final void zza(ListSubscriptionsResult listSubscriptionsResult) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) listSubscriptionsResult);
        zzb(1, zza);
    }
}
