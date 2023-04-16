package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ConnectionInfoCreator")
/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
public final class zza extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zza> CREATOR = new zzc();
    @SafeParcelable.Field(mo19514id = 1)
    Bundle zzdm;
    @SafeParcelable.Field(mo19514id = 2)
    Feature[] zzdn;
    @SafeParcelable.Field(defaultValue = "0", mo19514id = 3)
    private int zzdo;

    @SafeParcelable.Constructor
    zza(@SafeParcelable.Param(mo19517id = 1) Bundle bundle, @SafeParcelable.Param(mo19517id = 2) Feature[] featureArr, @SafeParcelable.Param(mo19517id = 3) int i) {
        this.zzdm = bundle;
        this.zzdn = featureArr;
        this.zzdo = i;
    }

    public zza() {
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, this.zzdm, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzdn, i, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzdo);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
