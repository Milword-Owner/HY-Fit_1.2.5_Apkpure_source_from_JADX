package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ValidateAccountRequestCreator")
@Deprecated
/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
public final class zzs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzs> CREATOR = new zzr();
    @SafeParcelable.VersionField(mo19520id = 1)
    private final int zzq;

    @SafeParcelable.Constructor
    zzs(@SafeParcelable.Param(mo19517id = 1) int i) {
        this.zzq = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzq);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
