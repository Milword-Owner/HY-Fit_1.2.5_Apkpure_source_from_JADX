package com.google.android.gms.fitness.data;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "DeviceCreator")
@SafeParcelable.Reserved({3, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class Device extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<Device> CREATOR = new zzo();
    public static final int TYPE_CHEST_STRAP = 4;
    public static final int TYPE_HEAD_MOUNTED = 6;
    public static final int TYPE_PHONE = 1;
    public static final int TYPE_SCALE = 5;
    public static final int TYPE_TABLET = 2;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_WATCH = 3;
    @SafeParcelable.Field(getter = "getType", mo19514id = 5)
    private final int type;
    @SafeParcelable.Field(getter = "getManufacturer", mo19514id = 1)
    private final String zzms;
    @SafeParcelable.Field(getter = "getModel", mo19514id = 2)
    private final String zzmt;
    @SafeParcelable.Field(getter = "getUid", mo19514id = 4)
    private final String zzmu;
    @SafeParcelable.Field(getter = "getPlatformType", mo19514id = 6)
    private final int zzmv;

    @RecentlyNonNull
    public static Device getLocalDevice(@RecentlyNonNull Context context) {
        int zzb = zzq.zzb(context);
        return new Device(Build.MANUFACTURER, Build.MODEL, zzq.zza(context), zzb, 2);
    }

    public Device(@RecentlyNonNull String str, @RecentlyNonNull String str2, @RecentlyNonNull String str3, int i) {
        this(str, str2, str3, i, 0);
    }

    @ShowFirstParty
    @SafeParcelable.Constructor
    public Device(@RecentlyNonNull @SafeParcelable.Param(mo19517id = 1) String str, @RecentlyNonNull @SafeParcelable.Param(mo19517id = 2) String str2, @RecentlyNonNull @SafeParcelable.Param(mo19517id = 4) String str3, @SafeParcelable.Param(mo19517id = 5) int i, @SafeParcelable.Param(mo19517id = 6) int i2) {
        this.zzms = (String) Preconditions.checkNotNull(str);
        this.zzmt = (String) Preconditions.checkNotNull(str2);
        if (str3 != null) {
            this.zzmu = str3;
            this.type = i;
            this.zzmv = i2;
            return;
        }
        throw new IllegalStateException("Device UID is null.");
    }

    @RecentlyNonNull
    public final String getManufacturer() {
        return this.zzms;
    }

    @RecentlyNonNull
    public final String getModel() {
        return this.zzmt;
    }

    @RecentlyNonNull
    public final String getUid() {
        return this.zzmu;
    }

    public final int getType() {
        return this.type;
    }

    /* access modifiers changed from: package-private */
    public final String getStreamIdentifier() {
        return String.format("%s:%s:%s", new Object[]{this.zzms, this.zzmt, this.zzmu});
    }

    @RecentlyNonNull
    public final String toString() {
        return String.format("Device{%s:%s:%s}", new Object[]{getStreamIdentifier(), Integer.valueOf(this.type), Integer.valueOf(this.zzmv)});
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Device)) {
            return false;
        }
        Device device = (Device) obj;
        return Objects.equal(this.zzms, device.zzms) && Objects.equal(this.zzmt, device.zzmt) && Objects.equal(this.zzmu, device.zzmu) && this.type == device.type && this.zzmv == device.zzmv;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzms, this.zzmt, this.zzmu, Integer.valueOf(this.type));
    }

    public final void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getManufacturer(), false);
        SafeParcelWriter.writeString(parcel, 2, getModel(), false);
        SafeParcelWriter.writeString(parcel, 4, getUid(), false);
        SafeParcelWriter.writeInt(parcel, 5, getType());
        SafeParcelWriter.writeInt(parcel, 6, this.zzmv);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
