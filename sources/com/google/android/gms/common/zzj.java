package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzk;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.Nullable;

@SafeParcelable.Class(creator = "GoogleCertificatesQueryCreator")
/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
public final class zzj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzj> CREATOR = new zzm();
    @SafeParcelable.Field(getter = "getCallingPackage", mo19514id = 1)
    private final String zzak;
    @SafeParcelable.Field(getter = "getCallingCertificateBinder", mo19514id = 2, type = "android.os.IBinder")
    @Nullable
    private final zzd zzal;
    @SafeParcelable.Field(getter = "getAllowTestKeys", mo19514id = 3)
    private final boolean zzam;
    @SafeParcelable.Field(defaultValue = "false", getter = "getIgnoreTestKeysOverride", mo19514id = 4)
    private final boolean zzan;

    @SafeParcelable.Constructor
    zzj(@SafeParcelable.Param(mo19517id = 1) String str, @SafeParcelable.Param(mo19517id = 2) @Nullable IBinder iBinder, @SafeParcelable.Param(mo19517id = 3) boolean z, @SafeParcelable.Param(mo19517id = 4) boolean z2) {
        this.zzak = str;
        this.zzal = zza(iBinder);
        this.zzam = z;
        this.zzan = z2;
    }

    zzj(String str, @Nullable zzd zzd, boolean z, boolean z2) {
        this.zzak = str;
        this.zzal = zzd;
        this.zzam = z;
        this.zzan = z2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        IBinder iBinder;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzak, false);
        zzd zzd = this.zzal;
        if (zzd == null) {
            Log.w("GoogleCertificatesQuery", "certificate binder is null");
            iBinder = null;
        } else {
            iBinder = zzd.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 2, iBinder, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzam);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzan);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    private static zzd zza(@Nullable IBinder iBinder) {
        byte[] bArr;
        if (iBinder == null) {
            return null;
        }
        try {
            IObjectWrapper zzb = zzk.zzb(iBinder).zzb();
            if (zzb == null) {
                bArr = null;
            } else {
                bArr = (byte[]) ObjectWrapper.unwrap(zzb);
            }
            if (bArr != null) {
                return new zzg(bArr);
            }
            Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
            return null;
        } catch (RemoteException e) {
            Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", e);
            return null;
        }
    }
}
