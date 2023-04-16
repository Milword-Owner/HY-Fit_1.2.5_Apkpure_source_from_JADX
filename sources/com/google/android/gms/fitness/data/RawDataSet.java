package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzh;
import java.util.List;

@KeepName
@SafeParcelable.Class(creator = "RawDataSetCreator")
@SafeParcelable.Reserved({2, 4, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class RawDataSet extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<RawDataSet> CREATOR = new zzab();
    @SafeParcelable.Field(mo19514id = 1)
    public final int zzoa;
    @RecentlyNonNull
    @SafeParcelable.Field(mo19514id = 3)
    public final List<RawDataPoint> zzoc;

    @SafeParcelable.Constructor
    public RawDataSet(@SafeParcelable.Param(mo19517id = 1) int i, @RecentlyNonNull @SafeParcelable.Param(mo19517id = 3) List<RawDataPoint> list) {
        this.zzoa = i;
        this.zzoc = list;
    }

    public RawDataSet(@RecentlyNonNull DataSet dataSet, @RecentlyNonNull List<DataSource> list) {
        this.zzoc = dataSet.zza(list);
        this.zzoa = zzh.zza(dataSet.getDataSource(), list);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawDataSet)) {
            return false;
        }
        RawDataSet rawDataSet = (RawDataSet) obj;
        return this.zzoa == rawDataSet.zzoa && Objects.equal(this.zzoc, rawDataSet.zzoc);
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzoa));
    }

    @RecentlyNonNull
    public final String toString() {
        return String.format("RawDataSet{%s@[%s]}", new Object[]{Integer.valueOf(this.zzoa), this.zzoc});
    }

    public final void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzoa);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzoc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
