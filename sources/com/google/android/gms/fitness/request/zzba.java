package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzci;
import com.google.android.gms.internal.fitness.zzcl;

@ShowFirstParty
@SafeParcelable.Class(creator = "SessionStopRequestCreator")
@SafeParcelable.Reserved({4, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzba extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzba> CREATOR = new zzbd();
    @SafeParcelable.Field(getter = "getName", mo19514id = 1)
    @Nullable
    private final String name;
    @SafeParcelable.Field(getter = "getIdentifier", mo19514id = 2)
    @Nullable
    private final String zzod;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 3, type = "android.os.IBinder")
    @Nullable
    private final zzci zzsm;

    @SafeParcelable.Constructor
    zzba(@SafeParcelable.Param(mo19517id = 1) @Nullable String str, @SafeParcelable.Param(mo19517id = 2) @Nullable String str2, @SafeParcelable.Param(mo19517id = 3) @Nullable IBinder iBinder) {
        zzci zzci;
        this.name = str;
        this.zzod = str2;
        if (iBinder == null) {
            zzci = null;
        } else {
            zzci = zzcl.zzi(iBinder);
        }
        this.zzsm = zzci;
    }

    public zzba(@Nullable String str, @Nullable String str2, @Nullable zzci zzci) {
        this.name = str;
        this.zzod = str2;
        this.zzsm = zzci;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzba)) {
            return false;
        }
        zzba zzba = (zzba) obj;
        return Objects.equal(this.name, zzba.name) && Objects.equal(this.zzod, zzba.zzod);
    }

    public final int hashCode() {
        return Objects.hashCode(this.name, this.zzod);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("identifier", this.zzod).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.name, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzod, false);
        zzci zzci = this.zzsm;
        SafeParcelWriter.writeIBinder(parcel, 3, zzci == null ? null : zzci.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
