package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionStopResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class zzcl extends zza implements zzci {
    public zzcl() {
        super("com.google.android.gms.fitness.internal.ISessionStopCallback");
    }

    public static zzci zzi(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.ISessionStopCallback");
        if (queryLocalInterface instanceof zzci) {
            return (zzci) queryLocalInterface;
        }
        return new zzck(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza((SessionStopResult) zzd.zza(parcel, SessionStopResult.CREATOR));
        return true;
    }
}
