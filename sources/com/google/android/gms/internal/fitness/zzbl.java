package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataTypeResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class zzbl extends zza implements zzbi {
    public zzbl() {
        super("com.google.android.gms.fitness.internal.IDataTypeCallback");
    }

    public static zzbi zze(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IDataTypeCallback");
        if (queryLocalInterface instanceof zzbi) {
            return (zzbi) queryLocalInterface;
        }
        return new zzbk(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzc((DataTypeResult) zzd.zza(parcel, DataTypeResult.CREATOR));
        return true;
    }
}
