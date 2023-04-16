package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;

@ShowFirstParty
@SafeParcelable.Class(creator = "ClaimBleDeviceRequestCreator")
@SafeParcelable.Reserved({4, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzd extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzd> CREATOR = new zzg();
    @SafeParcelable.Field(getter = "getDeviceAddress", mo19514id = 1)
    private final String deviceAddress;
    @SafeParcelable.Field(getter = "getBleDevice", mo19514id = 2)
    @Nullable
    private final BleDevice zzqk;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 3, type = "android.os.IBinder")
    private final zzcn zzql;

    @SafeParcelable.Constructor
    zzd(@SafeParcelable.Param(mo19517id = 1) String str, @SafeParcelable.Param(mo19517id = 2) @Nullable BleDevice bleDevice, @SafeParcelable.Param(mo19517id = 3) IBinder iBinder) {
        this.deviceAddress = str;
        this.zzqk = bleDevice;
        this.zzql = zzcm.zzj(iBinder);
    }

    public zzd(String str, @Nullable BleDevice bleDevice, zzcn zzcn) {
        this.deviceAddress = str;
        this.zzqk = bleDevice;
        this.zzql = zzcn;
    }

    public final String toString() {
        return String.format("ClaimBleDeviceRequest{%s %s}", new Object[]{this.deviceAddress, this.zzqk});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.deviceAddress, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzqk, i, false);
        zzcn zzcn = this.zzql;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcn == null ? null : zzcn.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
