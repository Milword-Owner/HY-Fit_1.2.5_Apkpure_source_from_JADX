package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;

@ShowFirstParty
@SafeParcelable.Class(creator = "FieldMapPairCreator")
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public final class zal extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zal> CREATOR = new zak();
    @SafeParcelable.VersionField(mo19520id = 1)
    private final int versionCode;
    @SafeParcelable.Field(mo19514id = 2)
    final String zarm;
    @SafeParcelable.Field(mo19514id = 3)
    final FastJsonResponse.Field<?, ?> zarn;

    @SafeParcelable.Constructor
    zal(@SafeParcelable.Param(mo19517id = 1) int i, @SafeParcelable.Param(mo19517id = 2) String str, @SafeParcelable.Param(mo19517id = 3) FastJsonResponse.Field<?, ?> field) {
        this.versionCode = i;
        this.zarm = str;
        this.zarn = field;
    }

    zal(String str, FastJsonResponse.Field<?, ?> field) {
        this.versionCode = 1;
        this.zarm = str;
        this.zarn = field;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, this.zarm, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zarn, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
