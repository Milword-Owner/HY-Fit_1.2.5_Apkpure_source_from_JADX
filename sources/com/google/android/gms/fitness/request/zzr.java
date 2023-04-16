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
import com.google.android.gms.internal.fitness.zzbi;
import com.google.android.gms.internal.fitness.zzbl;

@ShowFirstParty
@SafeParcelable.Class(creator = "DataTypeReadRequestCreator")
@SafeParcelable.Reserved({4, 1000})
@Deprecated
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzr> CREATOR = new zzt();
    @SafeParcelable.Field(getter = "getName", mo19514id = 1)
    private final String name;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 3, type = "android.os.IBinder")
    private final zzbi zzrj;

    @SafeParcelable.Constructor
    zzr(@SafeParcelable.Param(mo19517id = 1) String str, @SafeParcelable.Param(mo19517id = 3) IBinder iBinder) {
        this.name = str;
        this.zzrj = zzbl.zze(iBinder);
    }

    public zzr(String str, zzbi zzbi) {
        this.name = str;
        this.zzrj = zzbi;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj != this) {
            return (obj instanceof zzr) && Objects.equal(this.name, ((zzr) obj).name);
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.name);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("name", this.name).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.name, false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zzrj.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
