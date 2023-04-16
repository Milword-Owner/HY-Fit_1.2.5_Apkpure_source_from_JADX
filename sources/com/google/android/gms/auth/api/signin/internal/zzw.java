package com.google.android.gms.auth.api.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.internal.p013authapi.zzd;
import com.google.android.gms.internal.p013authapi.zzf;

/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public final class zzw extends zzd implements zzt {
    zzw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.signin.internal.ISignInService");
    }

    public final void zzc(zzr zzr, GoogleSignInOptions googleSignInOptions) throws RemoteException {
        Parcel zzc = zzc();
        zzf.zzc(zzc, (IInterface) zzr);
        zzf.zzc(zzc, (Parcelable) googleSignInOptions);
        zzc(101, zzc);
    }

    public final void zzd(zzr zzr, GoogleSignInOptions googleSignInOptions) throws RemoteException {
        Parcel zzc = zzc();
        zzf.zzc(zzc, (IInterface) zzr);
        zzf.zzc(zzc, (Parcelable) googleSignInOptions);
        zzc(102, zzc);
    }

    public final void zze(zzr zzr, GoogleSignInOptions googleSignInOptions) throws RemoteException {
        Parcel zzc = zzc();
        zzf.zzc(zzc, (IInterface) zzr);
        zzf.zzc(zzc, (Parcelable) googleSignInOptions);
        zzc(103, zzc);
    }
}
