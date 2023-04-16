package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class zzcm extends zza implements zzcn {
    public zzcm() {
        super("com.google.android.gms.fitness.internal.IStatusCallback");
    }

    public static zzcn zzj(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IStatusCallback");
        if (queryLocalInterface instanceof zzcn) {
            return (zzcn) queryLocalInterface;
        }
        return new zzcp(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        onResult((Status) zzd.zza(parcel, Status.CREATOR));
        return true;
    }
}
