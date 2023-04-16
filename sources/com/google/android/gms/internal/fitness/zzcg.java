package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionReadResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class zzcg extends zza implements zzch {
    public zzcg() {
        super("com.google.android.gms.fitness.internal.ISessionReadCallback");
    }

    public static zzch zzh(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.ISessionReadCallback");
        if (queryLocalInterface instanceof zzch) {
            return (zzch) queryLocalInterface;
        }
        return new zzcj(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza((SessionReadResult) zzd.zza(parcel, SessionReadResult.CREATOR));
        return true;
    }
}
