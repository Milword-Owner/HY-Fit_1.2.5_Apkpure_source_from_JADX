package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "DataSourcesResultCreator")
@SafeParcelable.Reserved({1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class DataSourcesResult extends AbstractSafeParcelable implements Result {
    @RecentlyNonNull
    public static final Parcelable.Creator<DataSourcesResult> CREATOR = new zzd();
    @SafeParcelable.Field(getter = "getDataSources", mo19514id = 1)
    private final List<DataSource> zzqq;
    @SafeParcelable.Field(getter = "getStatus", mo19514id = 2)
    private final Status zzsv;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public DataSourcesResult(@RecentlyNonNull @SafeParcelable.Param(mo19517id = 1) List<DataSource> list, @RecentlyNonNull @SafeParcelable.Param(mo19517id = 2) Status status) {
        this.zzqq = Collections.unmodifiableList(list);
        this.zzsv = status;
    }

    @RecentlyNonNull
    public List<DataSource> getDataSources() {
        return this.zzqq;
    }

    @RecentlyNonNull
    public List<DataSource> getDataSources(@RecentlyNonNull DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (DataSource next : this.zzqq) {
            if (next.getDataType().equals(dataType)) {
                arrayList.add(next);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @RecentlyNonNull
    public Status getStatus() {
        return this.zzsv;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof DataSourcesResult) {
                DataSourcesResult dataSourcesResult = (DataSourcesResult) obj;
                if (this.zzsv.equals(dataSourcesResult.zzsv) && Objects.equal(this.zzqq, dataSourcesResult.zzqq)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzsv, this.zzqq);
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzsv).add("dataSources", this.zzqq).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataSources(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
