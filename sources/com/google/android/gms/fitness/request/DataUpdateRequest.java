package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
import java.util.concurrent.TimeUnit;

@SafeParcelable.Class(creator = "DataUpdateRequestCreator")
@SafeParcelable.Reserved({1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class DataUpdateRequest extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<DataUpdateRequest> CREATOR = new zzaa();
    @SafeParcelable.Field(getter = "getStartTimeMillis", mo19514id = 1)
    private final long zzkr;
    @SafeParcelable.Field(getter = "getEndTimeMillis", mo19514id = 2)
    private final long zzks;
    @SafeParcelable.Field(getter = "getDataSet", mo19514id = 3)
    private final DataSet zzls;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 4, type = "android.os.IBinder")
    @Nullable
    private final zzcn zzql;

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class Builder {
        /* access modifiers changed from: private */
        public long zzkr;
        /* access modifiers changed from: private */
        public long zzks;
        /* access modifiers changed from: private */
        public DataSet zzls;

        @RecentlyNonNull
        public Builder setTimeInterval(long j, long j2, @RecentlyNonNull TimeUnit timeUnit) {
            Preconditions.checkArgument(j > 0, "Invalid start time :%d", Long.valueOf(j));
            Preconditions.checkArgument(j2 >= j, "Invalid end time :%d", Long.valueOf(j2));
            this.zzkr = timeUnit.toMillis(j);
            this.zzks = timeUnit.toMillis(j2);
            return this;
        }

        @RecentlyNonNull
        public Builder setDataSet(@RecentlyNonNull DataSet dataSet) {
            Preconditions.checkNotNull(dataSet, "Must set the data set");
            this.zzls = dataSet;
            return this;
        }

        @RecentlyNonNull
        public DataUpdateRequest build() {
            Preconditions.checkNotZero(this.zzkr, (Object) "Must set a non-zero value for startTimeMillis/startTime");
            Preconditions.checkNotZero(this.zzks, (Object) "Must set a non-zero value for endTimeMillis/endTime");
            Preconditions.checkNotNull(this.zzls, "Must set the data set");
            for (DataPoint next : this.zzls.getDataPoints()) {
                long startTime = next.getStartTime(TimeUnit.MILLISECONDS);
                long endTime = next.getEndTime(TimeUnit.MILLISECONDS);
                Preconditions.checkState(!(startTime > endTime || (startTime != 0 && startTime < this.zzkr) || ((startTime != 0 && startTime > this.zzks) || endTime > this.zzks || endTime < this.zzkr)), "Data Point's startTimeMillis %d, endTimeMillis %d should lie between timeRange provided in the request. StartTimeMillis %d, EndTimeMillis: %d", Long.valueOf(startTime), Long.valueOf(endTime), Long.valueOf(this.zzkr), Long.valueOf(this.zzks));
            }
            return new DataUpdateRequest(this);
        }
    }

    @SafeParcelable.Constructor
    public DataUpdateRequest(@SafeParcelable.Param(mo19517id = 1) long j, @SafeParcelable.Param(mo19517id = 2) long j2, @RecentlyNonNull @SafeParcelable.Param(mo19517id = 3) DataSet dataSet, @SafeParcelable.Param(mo19517id = 4) @Nullable IBinder iBinder) {
        zzcn zzcn;
        this.zzkr = j;
        this.zzks = j2;
        this.zzls = dataSet;
        if (iBinder == null) {
            zzcn = null;
        } else {
            zzcn = zzcm.zzj(iBinder);
        }
        this.zzql = zzcn;
    }

    private DataUpdateRequest(Builder builder) {
        this(builder.zzkr, builder.zzks, builder.zzls, (IBinder) null);
    }

    public DataUpdateRequest(@RecentlyNonNull DataUpdateRequest dataUpdateRequest, @RecentlyNonNull IBinder iBinder) {
        this(dataUpdateRequest.zzkr, dataUpdateRequest.zzks, dataUpdateRequest.getDataSet(), iBinder);
    }

    public final long zzv() {
        return this.zzkr;
    }

    public final long zzw() {
        return this.zzks;
    }

    @RecentlyNonNull
    public DataSet getDataSet() {
        return this.zzls;
    }

    public long getStartTime(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzkr, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzks, TimeUnit.MILLISECONDS);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataUpdateRequest)) {
            return false;
        }
        DataUpdateRequest dataUpdateRequest = (DataUpdateRequest) obj;
        return this.zzkr == dataUpdateRequest.zzkr && this.zzks == dataUpdateRequest.zzks && Objects.equal(this.zzls, dataUpdateRequest.zzls);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzkr), Long.valueOf(this.zzks), this.zzls);
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("startTimeMillis", Long.valueOf(this.zzkr)).add("endTimeMillis", Long.valueOf(this.zzks)).add("dataSet", this.zzls).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzkr);
        SafeParcelWriter.writeLong(parcel, 2, this.zzks);
        SafeParcelWriter.writeParcelable(parcel, 3, getDataSet(), i, false);
        zzcn zzcn = this.zzql;
        SafeParcelWriter.writeIBinder(parcel, 4, zzcn == null ? null : zzcn.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
