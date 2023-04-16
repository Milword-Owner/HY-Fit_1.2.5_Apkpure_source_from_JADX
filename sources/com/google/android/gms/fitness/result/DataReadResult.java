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
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.RawBucket;
import com.google.android.gms.fitness.data.RawDataSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@SafeParcelable.Class(creator = "DataReadResultCreator")
@SafeParcelable.Reserved({7, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class DataReadResult extends AbstractSafeParcelable implements Result {
    @RecentlyNonNull
    public static final Parcelable.Creator<DataReadResult> CREATOR = new zzc();
    @SafeParcelable.Field(getter = "getRawDataSets", mo19514id = 1, type = "java.util.List")
    private final List<DataSet> zzlh;
    @SafeParcelable.Field(getter = "getUniqueDataSources", mo19514id = 6)
    private final List<DataSource> zzlr;
    @SafeParcelable.Field(getter = "getStatus", mo19514id = 2)
    private final Status zzsv;
    @SafeParcelable.Field(getter = "getRawBuckets", mo19514id = 3, type = "java.util.List")
    private final List<Bucket> zzsw;
    @SafeParcelable.Field(getter = "getBatchCount", mo19514id = 5)
    private int zzsx;

    @SafeParcelable.Constructor
    DataReadResult(@SafeParcelable.Param(mo19517id = 1) List<RawDataSet> list, @SafeParcelable.Param(mo19517id = 2) Status status, @SafeParcelable.Param(mo19517id = 3) List<RawBucket> list2, @SafeParcelable.Param(mo19517id = 5) int i, @SafeParcelable.Param(mo19517id = 6) List<DataSource> list3) {
        this.zzsv = status;
        this.zzsx = i;
        this.zzlr = list3;
        this.zzlh = new ArrayList(list.size());
        for (RawDataSet dataSet : list) {
            this.zzlh.add(new DataSet(dataSet, list3));
        }
        this.zzsw = new ArrayList(list2.size());
        for (RawBucket bucket : list2) {
            this.zzsw.add(new Bucket(bucket, list3));
        }
    }

    @ShowFirstParty
    private DataReadResult(List<DataSet> list, List<Bucket> list2, Status status) {
        this.zzlh = list;
        this.zzsv = status;
        this.zzsw = list2;
        this.zzsx = 1;
        this.zzlr = new ArrayList();
    }

    @RecentlyNonNull
    @ShowFirstParty
    public static DataReadResult zza(@RecentlyNonNull Status status, @RecentlyNonNull List<DataType> list, @RecentlyNonNull List<DataSource> list2) {
        ArrayList arrayList = new ArrayList();
        for (DataSource builder : list2) {
            arrayList.add(DataSet.builder(builder).build());
        }
        for (DataType dataType : list) {
            arrayList.add(DataSet.builder(new DataSource.Builder().setType(1).setDataType(dataType).setStreamName("Default").build()).build());
        }
        return new DataReadResult(arrayList, Collections.emptyList(), status);
    }

    @RecentlyNonNull
    public DataSet getDataSet(@RecentlyNonNull DataType dataType) {
        for (DataSet next : this.zzlh) {
            if (dataType.equals(next.getDataType())) {
                return next;
            }
        }
        return DataSet.builder(new DataSource.Builder().setType(1).setDataType(dataType).build()).build();
    }

    @RecentlyNonNull
    public DataSet getDataSet(@RecentlyNonNull DataSource dataSource) {
        for (DataSet next : this.zzlh) {
            if (dataSource.equals(next.getDataSource())) {
                return next;
            }
        }
        return DataSet.builder(dataSource).build();
    }

    @RecentlyNonNull
    public List<DataSet> getDataSets() {
        return this.zzlh;
    }

    @RecentlyNonNull
    public List<Bucket> getBuckets() {
        return this.zzsw;
    }

    public final int zzab() {
        return this.zzsx;
    }

    public final void zzb(@RecentlyNonNull DataReadResult dataReadResult) {
        for (DataSet zza : dataReadResult.getDataSets()) {
            zza(zza, this.zzlh);
        }
        for (Bucket next : dataReadResult.getBuckets()) {
            Iterator<Bucket> it = this.zzsw.iterator();
            while (true) {
                if (!it.hasNext()) {
                    this.zzsw.add(next);
                    break;
                }
                Bucket next2 = it.next();
                if (next2.zza(next)) {
                    for (DataSet zza2 : next.getDataSets()) {
                        zza(zza2, next2.getDataSets());
                    }
                }
            }
        }
    }

    private static void zza(DataSet dataSet, List<DataSet> list) {
        for (DataSet next : list) {
            if (next.getDataSource().equals(dataSet.getDataSource())) {
                next.zza((Iterable<DataPoint>) dataSet.getDataPoints());
                return;
            }
        }
        list.add(dataSet);
    }

    @RecentlyNonNull
    public Status getStatus() {
        return this.zzsv;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataReadResult)) {
            return false;
        }
        DataReadResult dataReadResult = (DataReadResult) obj;
        return this.zzsv.equals(dataReadResult.zzsv) && Objects.equal(this.zzlh, dataReadResult.zzlh) && Objects.equal(this.zzsw, dataReadResult.zzsw);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzsv, this.zzlh, this.zzsw);
    }

    @RecentlyNonNull
    public String toString() {
        Object obj;
        Object obj2;
        Objects.ToStringHelper add = Objects.toStringHelper(this).add("status", this.zzsv);
        if (this.zzlh.size() > 5) {
            int size = this.zzlh.size();
            StringBuilder sb = new StringBuilder(21);
            sb.append(size);
            sb.append(" data sets");
            obj = sb.toString();
        } else {
            obj = this.zzlh;
        }
        Objects.ToStringHelper add2 = add.add("dataSets", obj);
        if (this.zzsw.size() > 5) {
            int size2 = this.zzsw.size();
            StringBuilder sb2 = new StringBuilder(19);
            sb2.append(size2);
            sb2.append(" buckets");
            obj2 = sb2.toString();
        } else {
            obj2 = this.zzsw;
        }
        return add2.add("buckets", obj2).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        ArrayList arrayList = new ArrayList(this.zzlh.size());
        for (DataSet rawDataSet : this.zzlh) {
            arrayList.add(new RawDataSet(rawDataSet, this.zzlr));
        }
        SafeParcelWriter.writeList(parcel, 1, arrayList, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        ArrayList arrayList2 = new ArrayList(this.zzsw.size());
        for (Bucket rawBucket : this.zzsw) {
            arrayList2.add(new RawBucket(rawBucket, this.zzlr));
        }
        SafeParcelWriter.writeList(parcel, 3, arrayList2, false);
        SafeParcelWriter.writeInt(parcel, 5, this.zzsx);
        SafeParcelWriter.writeTypedList(parcel, 6, this.zzlr, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
