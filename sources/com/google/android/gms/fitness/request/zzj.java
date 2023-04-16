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
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;

@ShowFirstParty
@SafeParcelable.Class(creator = "DataInsertRequestCreator")
@SafeParcelable.Reserved({3, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzj> CREATOR = new zzl();
    @SafeParcelable.Field(getter = "getDataSet", mo19514id = 1)
    private final DataSet zzls;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 2, type = "android.os.IBinder")
    @Nullable
    private final zzcn zzql;
    @SafeParcelable.Field(getter = "shouldSkipSync", mo19514id = 4)
    private final boolean zzqw;

    @SafeParcelable.Constructor
    zzj(@SafeParcelable.Param(mo19517id = 1) DataSet dataSet, @SafeParcelable.Param(mo19517id = 2) @Nullable IBinder iBinder, @SafeParcelable.Param(mo19517id = 4) boolean z) {
        zzcn zzcn;
        this.zzls = dataSet;
        if (iBinder == null) {
            zzcn = null;
        } else {
            zzcn = zzcm.zzj(iBinder);
        }
        this.zzql = zzcn;
        this.zzqw = z;
    }

    public zzj(DataSet dataSet, zzcn zzcn, boolean z) {
        this.zzls = dataSet;
        this.zzql = zzcn;
        this.zzqw = z;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj != this) {
            return (obj instanceof zzj) && Objects.equal(this.zzls, ((zzj) obj).zzls);
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzls);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("dataSet", this.zzls).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzls, i, false);
        zzcn zzcn = this.zzql;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcn == null ? null : zzcn.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzqw);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
