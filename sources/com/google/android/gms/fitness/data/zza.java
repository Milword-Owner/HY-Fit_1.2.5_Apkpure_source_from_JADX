package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ShowFirstParty
@SafeParcelable.Class(creator = "ApplicationCreator")
@SafeParcelable.Reserved({2, 3, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zza extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zza> CREATOR = new zzc();
    public static final zza zzlb = new zza("com.google.android.gms");
    @SafeParcelable.Field(getter = "getPackageName", mo19514id = 1)
    private final String packageName;

    public static zza zza(String str) {
        if ("com.google.android.gms".equals(str)) {
            return zzlb;
        }
        return new zza(str);
    }

    @SafeParcelable.Constructor
    public zza(@SafeParcelable.Param(mo19517id = 1) String str) {
        this.packageName = (String) Preconditions.checkNotNull(str);
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zza)) {
            return false;
        }
        return this.packageName.equals(((zza) obj).packageName);
    }

    public final int hashCode() {
        return this.packageName.hashCode();
    }

    public final String toString() {
        return String.format("Application{%s}", new Object[]{this.packageName});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.packageName, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
