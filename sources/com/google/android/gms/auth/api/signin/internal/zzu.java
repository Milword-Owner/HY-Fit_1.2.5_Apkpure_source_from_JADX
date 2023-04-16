package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.p013authapi.zzc;
import com.google.android.gms.internal.p013authapi.zzf;

/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public abstract class zzu extends zzc implements zzr {
    public zzu() {
        super("com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean zzc(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 101:
                zzc((GoogleSignInAccount) zzf.zzc(parcel, GoogleSignInAccount.CREATOR), (Status) zzf.zzc(parcel, Status.CREATOR));
                break;
            case 102:
                zze((Status) zzf.zzc(parcel, Status.CREATOR));
                break;
            case 103:
                zzf((Status) zzf.zzc(parcel, Status.CREATOR));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
