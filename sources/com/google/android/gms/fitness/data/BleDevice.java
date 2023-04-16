package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@SafeParcelable.Class(creator = "BleDeviceCreator")
@SafeParcelable.Reserved({1000})
@Deprecated
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class BleDevice extends AbstractSafeParcelable implements ReflectedParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<BleDevice> CREATOR = new zzd();
    @SafeParcelable.Field(getter = "getName", mo19514id = 2)
    private final String name;
    @SafeParcelable.Field(getter = "getAddress", mo19514id = 1)
    private final String zzld;
    @SafeParcelable.Field(getter = "getSupportedProfiles", mo19514id = 3)
    private final List<String> zzle;
    @SafeParcelable.Field(getter = "getDataTypes", mo19514id = 4)
    private final List<DataType> zzlf;

    @SafeParcelable.Constructor
    BleDevice(@SafeParcelable.Param(mo19517id = 1) String str, @SafeParcelable.Param(mo19517id = 2) String str2, @SafeParcelable.Param(mo19517id = 3) List<String> list, @SafeParcelable.Param(mo19517id = 4) List<DataType> list2) {
        this.zzld = str;
        this.name = str2;
        this.zzle = Collections.unmodifiableList(list);
        this.zzlf = Collections.unmodifiableList(list2);
    }

    @RecentlyNonNull
    public String getAddress() {
        return this.zzld;
    }

    @RecentlyNonNull
    public String getName() {
        return this.name;
    }

    @RecentlyNonNull
    public List<String> getSupportedProfiles() {
        return this.zzle;
    }

    @RecentlyNonNull
    public List<DataType> getDataTypes() {
        return this.zzlf;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BleDevice)) {
            return false;
        }
        BleDevice bleDevice = (BleDevice) obj;
        return this.name.equals(bleDevice.name) && this.zzld.equals(bleDevice.zzld) && new HashSet(this.zzle).equals(new HashSet(bleDevice.zzle)) && new HashSet(this.zzlf).equals(new HashSet(bleDevice.zzlf));
    }

    public int hashCode() {
        return Objects.hashCode(this.name, this.zzld, this.zzle, this.zzlf);
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("address", this.zzld).add("dataTypes", this.zzlf).add("supportedProfiles", this.zzle).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getAddress(), false);
        SafeParcelWriter.writeString(parcel, 2, getName(), false);
        SafeParcelWriter.writeStringList(parcel, 3, getSupportedProfiles(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, getDataTypes(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
