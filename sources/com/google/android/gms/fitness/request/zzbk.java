package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;

@ShowFirstParty
@SafeParcelable.Class(creator = "UnclaimBleDeviceRequestCreator")
@SafeParcelable.Reserved({1, 4, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzbk extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbk> CREATOR = new zzbn();
    @SafeParcelable.Field(getter = "getDeviceAddress", mo19514id = 2)
    private final String deviceAddress;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 3, type = "android.os.IBinder")
    @Nullable
    private final zzcn zzql;

    @SafeParcelable.Constructor
    zzbk(@SafeParcelable.Param(mo19517id = 2) String str, @SafeParcelable.Param(mo19517id = 3) @Nullable IBinder iBinder) {
        zzcn zzcn;
        this.deviceAddress = str;
        if (iBinder == null) {
            zzcn = null;
        } else {
            zzcn = zzcm.zzj(iBinder);
        }
        this.zzql = zzcn;
    }

    public zzbk(String str, @Nullable zzcn zzcn) {
        this.deviceAddress = str;
        this.zzql = zzcn;
    }

    public final String toString() {
        return String.format("UnclaimBleDeviceRequest{%s}", new Object[]{this.deviceAddress});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.deviceAddress, false);
        zzcn zzcn = this.zzql;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcn == null ? null : zzcn.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
