package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.GoalsResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class zzbm extends zza implements zzbn {
    public zzbm() {
        super("com.google.android.gms.fitness.internal.IGoalsReadCallback");
    }

    public static zzbn zzf(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoalsReadCallback");
        if (queryLocalInterface instanceof zzbn) {
            return (zzbn) queryLocalInterface;
        }
        return new zzbp(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza((GoalsResult) zzd.zza(parcel, GoalsResult.CREATOR));
        return true;
    }
}
