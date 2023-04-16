package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class(creator = "FavaDiagnosticsEntityCreator")
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public class FavaDiagnosticsEntity extends AbstractSafeParcelable implements ReflectedParcelable {
    @KeepForSdk
    public static final Parcelable.Creator<FavaDiagnosticsEntity> CREATOR = new zaa();
    @SafeParcelable.VersionField(mo19520id = 1)
    private final int zali;
    @SafeParcelable.Field(mo19514id = 2)
    private final String zapx;
    @SafeParcelable.Field(mo19514id = 3)
    private final int zapy;

    @SafeParcelable.Constructor
    public FavaDiagnosticsEntity(@SafeParcelable.Param(mo19517id = 1) int i, @SafeParcelable.Param(mo19517id = 2) String str, @SafeParcelable.Param(mo19517id = 3) int i2) {
        this.zali = i;
        this.zapx = str;
        this.zapy = i2;
    }

    @KeepForSdk
    public FavaDiagnosticsEntity(String str, int i) {
        this.zali = 1;
        this.zapx = str;
        this.zapy = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zali);
        SafeParcelWriter.writeString(parcel, 2, this.zapx, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zapy);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
