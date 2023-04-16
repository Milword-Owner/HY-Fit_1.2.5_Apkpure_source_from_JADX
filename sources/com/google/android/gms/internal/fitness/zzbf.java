package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataReadResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class zzbf extends zza implements zzbc {
    public zzbf() {
        super("com.google.android.gms.fitness.internal.IDataReadCallback");
    }

    public static zzbc zzc(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IDataReadCallback");
        if (queryLocalInterface instanceof zzbc) {
            return (zzbc) queryLocalInterface;
        }
        return new zzbe(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza((DataReadResult) zzd.zza(parcel, DataReadResult.CREATOR));
        return true;
    }
}
