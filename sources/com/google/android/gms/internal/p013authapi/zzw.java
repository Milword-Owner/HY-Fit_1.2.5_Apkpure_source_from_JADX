package com.google.android.gms.internal.p013authapi;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;

/* renamed from: com.google.android.gms.internal.auth-api.zzw */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public final class zzw extends zzd implements zzx {
    zzw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
    }

    public final void zzc(zzv zzv, CredentialRequest credentialRequest) throws RemoteException {
        Parcel zzc = zzc();
        zzf.zzc(zzc, (IInterface) zzv);
        zzf.zzc(zzc, (Parcelable) credentialRequest);
        zzc(1, zzc);
    }

    public final void zzc(zzv zzv, zzz zzz) throws RemoteException {
        Parcel zzc = zzc();
        zzf.zzc(zzc, (IInterface) zzv);
        zzf.zzc(zzc, (Parcelable) zzz);
        zzc(2, zzc);
    }

    public final void zzc(zzv zzv, zzt zzt) throws RemoteException {
        Parcel zzc = zzc();
        zzf.zzc(zzc, (IInterface) zzv);
        zzf.zzc(zzc, (Parcelable) zzt);
        zzc(3, zzc);
    }

    public final void zzc(zzv zzv) throws RemoteException {
        Parcel zzc = zzc();
        zzf.zzc(zzc, (IInterface) zzv);
        zzc(4, zzc);
    }
}
