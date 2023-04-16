package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "BleDevicesResultCreator")
@SafeParcelable.Reserved({1000})
@Deprecated
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class BleDevicesResult extends AbstractSafeParcelable implements Result {
    @RecentlyNonNull
    public static final Parcelable.Creator<BleDevicesResult> CREATOR = new zza();
    @SafeParcelable.Field(getter = "getClaimedBleDevices", mo19514id = 1)
    private final List<BleDevice> zzsu;
    @SafeParcelable.Field(getter = "getStatus", mo19514id = 2)
    private final Status zzsv;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public BleDevicesResult(@RecentlyNonNull @SafeParcelable.Param(mo19517id = 1) List<BleDevice> list, @RecentlyNonNull @SafeParcelable.Param(mo19517id = 2) Status status) {
        this.zzsu = Collections.unmodifiableList(list);
        this.zzsv = status;
    }

    @RecentlyNonNull
    @ShowFirstParty
    public static BleDevicesResult zzb(@RecentlyNonNull Status status) {
        return new BleDevicesResult(Collections.emptyList(), status);
    }

    @RecentlyNonNull
    public List<BleDevice> getClaimedBleDevices() {
        return this.zzsu;
    }

    @RecentlyNonNull
    public List<BleDevice> getClaimedBleDevices(@RecentlyNonNull DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (BleDevice next : this.zzsu) {
            if (next.getDataTypes().contains(dataType)) {
                arrayList.add(next);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @RecentlyNonNull
    public Status getStatus() {
        return this.zzsv;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BleDevicesResult)) {
            return false;
        }
        BleDevicesResult bleDevicesResult = (BleDevicesResult) obj;
        return this.zzsv.equals(bleDevicesResult.zzsv) && Objects.equal(this.zzsu, bleDevicesResult.zzsu);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzsv, this.zzsu);
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzsv).add("bleDevices", this.zzsu).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getClaimedBleDevices(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
