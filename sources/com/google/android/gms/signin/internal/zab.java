package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "AuthAccountResultCreator")
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public final class zab extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<zab> CREATOR = new zaa();
    @SafeParcelable.VersionField(mo19520id = 1)
    private final int versionCode;
    @SafeParcelable.Field(getter = "getConnectionResultCode", mo19514id = 2)
    private int zasv;
    @SafeParcelable.Field(getter = "getRawAuthResolutionIntent", mo19514id = 3)
    @Nullable
    private Intent zasw;

    @SafeParcelable.Constructor
    zab(@SafeParcelable.Param(mo19517id = 1) int i, @SafeParcelable.Param(mo19517id = 2) int i2, @SafeParcelable.Param(mo19517id = 3) @Nullable Intent intent) {
        this.versionCode = i;
        this.zasv = i2;
        this.zasw = intent;
    }

    public zab() {
        this(0, (Intent) null);
    }

    private zab(int i, @Nullable Intent intent) {
        this(2, 0, (Intent) null);
    }

    public final Status getStatus() {
        if (this.zasv == 0) {
            return Status.RESULT_SUCCESS;
        }
        return Status.RESULT_CANCELED;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeInt(parcel, 2, this.zasv);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zasw, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
