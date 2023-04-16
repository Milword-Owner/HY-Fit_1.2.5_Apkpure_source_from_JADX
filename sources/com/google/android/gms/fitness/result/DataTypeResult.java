package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;

@SafeParcelable.Class(creator = "DataTypeResultCreator")
@SafeParcelable.Reserved({1000})
@Deprecated
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class DataTypeResult extends AbstractSafeParcelable implements Result {
    @RecentlyNonNull
    public static final Parcelable.Creator<DataTypeResult> CREATOR = new zze();
    @SafeParcelable.Field(getter = "getDataType", mo19514id = 3)
    @Nullable
    private final DataType zzkp;
    @SafeParcelable.Field(getter = "getStatus", mo19514id = 1)
    private final Status zzsv;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public DataTypeResult(@RecentlyNonNull @SafeParcelable.Param(mo19517id = 1) Status status, @SafeParcelable.Param(mo19517id = 3) @Nullable DataType dataType) {
        this.zzsv = status;
        this.zzkp = dataType;
    }

    @RecentlyNonNull
    @ShowFirstParty
    public static DataTypeResult zzc(@RecentlyNonNull Status status) {
        return new DataTypeResult(status, (DataType) null);
    }

    @RecentlyNonNull
    public Status getStatus() {
        return this.zzsv;
    }

    @RecentlyNullable
    public DataType getDataType() {
        return this.zzkp;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataTypeResult)) {
            return false;
        }
        DataTypeResult dataTypeResult = (DataTypeResult) obj;
        return this.zzsv.equals(dataTypeResult.zzsv) && Objects.equal(this.zzkp, dataTypeResult.zzkp);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzsv, this.zzkp);
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzsv).add("dataType", this.zzkp).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getDataType(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
