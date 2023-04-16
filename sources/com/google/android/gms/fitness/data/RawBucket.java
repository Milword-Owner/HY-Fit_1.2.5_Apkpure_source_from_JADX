package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@KeepName
@SafeParcelable.Class(creator = "RawBucketCreator")
@SafeParcelable.Reserved({7, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class RawBucket extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<RawBucket> CREATOR = new zzz();
    @SafeParcelable.Field(mo19514id = 1)
    public final long zzkr;
    @SafeParcelable.Field(mo19514id = 2)
    public final long zzks;
    @SafeParcelable.Field(mo19514id = 3)
    @RecentlyNullable
    public final Session zzky;
    @RecentlyNonNull
    @SafeParcelable.Field(mo19514id = 5)
    public final List<RawDataSet> zzlh;
    @SafeParcelable.Field(mo19514id = 6)
    public final int zzli;
    @SafeParcelable.Field(mo19514id = 4)
    public final int zzny;

    @SafeParcelable.Constructor
    public RawBucket(@SafeParcelable.Param(mo19517id = 1) long j, @SafeParcelable.Param(mo19517id = 2) long j2, @SafeParcelable.Param(mo19517id = 3) @Nullable Session session, @SafeParcelable.Param(mo19517id = 4) int i, @RecentlyNonNull @SafeParcelable.Param(mo19517id = 5) List<RawDataSet> list, @SafeParcelable.Param(mo19517id = 6) int i2) {
        this.zzkr = j;
        this.zzks = j2;
        this.zzky = session;
        this.zzny = i;
        this.zzlh = list;
        this.zzli = i2;
    }

    public RawBucket(@RecentlyNonNull Bucket bucket, @RecentlyNonNull List<DataSource> list) {
        this.zzkr = bucket.getStartTime(TimeUnit.MILLISECONDS);
        this.zzks = bucket.getEndTime(TimeUnit.MILLISECONDS);
        this.zzky = bucket.getSession();
        this.zzny = bucket.zzd();
        this.zzli = bucket.getBucketType();
        List<DataSet> dataSets = bucket.getDataSets();
        this.zzlh = new ArrayList(dataSets.size());
        for (DataSet rawDataSet : dataSets) {
            this.zzlh.add(new RawDataSet(rawDataSet, list));
        }
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawBucket)) {
            return false;
        }
        RawBucket rawBucket = (RawBucket) obj;
        return this.zzkr == rawBucket.zzkr && this.zzks == rawBucket.zzks && this.zzny == rawBucket.zzny && Objects.equal(this.zzlh, rawBucket.zzlh) && this.zzli == rawBucket.zzli;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzkr), Long.valueOf(this.zzks), Integer.valueOf(this.zzli));
    }

    @RecentlyNonNull
    public final String toString() {
        return Objects.toStringHelper(this).add("startTime", Long.valueOf(this.zzkr)).add("endTime", Long.valueOf(this.zzks)).add("activity", Integer.valueOf(this.zzny)).add("dataSets", this.zzlh).add("bucketType", Integer.valueOf(this.zzli)).toString();
    }

    public final void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzkr);
        SafeParcelWriter.writeLong(parcel, 2, this.zzks);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzky, i, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzny);
        SafeParcelWriter.writeTypedList(parcel, 5, this.zzlh, false);
        SafeParcelWriter.writeInt(parcel, 6, this.zzli);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
