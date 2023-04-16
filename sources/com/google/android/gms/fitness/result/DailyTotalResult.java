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
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;

@SafeParcelable.Class(creator = "DailyTotalResultCreator")
@SafeParcelable.Reserved({1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class DailyTotalResult extends AbstractSafeParcelable implements Result {
    @RecentlyNonNull
    public static final Parcelable.Creator<DailyTotalResult> CREATOR = new zzb();
    @SafeParcelable.Field(getter = "getTotal", mo19514id = 2)
    @Nullable
    private final DataSet zzls;
    @SafeParcelable.Field(getter = "getStatus", mo19514id = 1)
    private final Status zzsv;

    @SafeParcelable.Constructor
    public DailyTotalResult(@RecentlyNonNull @SafeParcelable.Param(mo19517id = 1) Status status, @SafeParcelable.Param(mo19517id = 2) @Nullable DataSet dataSet) {
        this.zzsv = status;
        this.zzls = dataSet;
    }

    @RecentlyNonNull
    @ShowFirstParty
    public static DailyTotalResult zza(@RecentlyNonNull Status status, @RecentlyNonNull DataType dataType) {
        return new DailyTotalResult(status, DataSet.builder(new DataSource.Builder().setType(1).setDataType(dataType).build()).build());
    }

    @RecentlyNullable
    public DataSet getTotal() {
        return this.zzls;
    }

    @RecentlyNonNull
    public Status getStatus() {
        return this.zzsv;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DailyTotalResult)) {
            return false;
        }
        DailyTotalResult dailyTotalResult = (DailyTotalResult) obj;
        return this.zzsv.equals(dailyTotalResult.zzsv) && Objects.equal(this.zzls, dailyTotalResult.zzls);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzsv, this.zzls);
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzsv).add("dataPoint", this.zzls).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getTotal(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
