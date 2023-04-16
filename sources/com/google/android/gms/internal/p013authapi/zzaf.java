package com.google.android.gms.internal.p013authapi;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.SavePasswordRequest;

/* renamed from: com.google.android.gms.internal.auth-api.zzaf */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public final class zzaf extends zzd implements zzac {
    zzaf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.identity.internal.ICredentialSavingService");
    }

    public final void zzc(zzag zzag, SavePasswordRequest savePasswordRequest) throws RemoteException {
        Parcel zzc = zzc();
        zzf.zzc(zzc, (IInterface) zzag);
        zzf.zzc(zzc, (Parcelable) savePasswordRequest);
        zzc(2, zzc);
    }
}
