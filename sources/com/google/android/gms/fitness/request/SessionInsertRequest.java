package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
import com.google.android.gms.internal.fitness.zzi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SafeParcelable.Class(creator = "SessionInsertRequestCreator")
@SafeParcelable.Reserved({5, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class SessionInsertRequest extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<SessionInsertRequest> CREATOR = new zzau();
    /* access modifiers changed from: private */
    public static final TimeUnit zzsa = TimeUnit.MILLISECONDS;
    @SafeParcelable.Field(getter = "getSession", mo19514id = 1)
    private final Session zzky;
    @SafeParcelable.Field(getter = "getDataSets", mo19514id = 2)
    private final List<DataSet> zzlh;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 4, type = "android.os.IBinder")
    @Nullable
    private final zzcn zzql;
    @SafeParcelable.Field(getter = "getAggregateDataPoints", mo19514id = 3)
    private final List<DataPoint> zzsb;

    @SafeParcelable.Constructor
    SessionInsertRequest(@SafeParcelable.Param(mo19517id = 1) Session session, @SafeParcelable.Param(mo19517id = 2) List<DataSet> list, @SafeParcelable.Param(mo19517id = 3) List<DataPoint> list2, @SafeParcelable.Param(mo19517id = 4) @Nullable IBinder iBinder) {
        zzcn zzcn;
        this.zzky = session;
        this.zzlh = Collections.unmodifiableList(list);
        this.zzsb = Collections.unmodifiableList(list2);
        if (iBinder == null) {
            zzcn = null;
        } else {
            zzcn = zzcm.zzj(iBinder);
        }
        this.zzql = zzcn;
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class Builder {
        /* access modifiers changed from: private */
        public Session zzky;
        /* access modifiers changed from: private */
        public final List<DataSet> zzlh = new ArrayList();
        /* access modifiers changed from: private */
        public final List<DataPoint> zzsb = new ArrayList();
        private final List<DataSource> zzsc = new ArrayList();

        @RecentlyNonNull
        public Builder setSession(@RecentlyNonNull Session session) {
            this.zzky = session;
            return this;
        }

        @RecentlyNonNull
        public Builder addDataSet(@RecentlyNonNull DataSet dataSet) {
            Preconditions.checkArgument(dataSet != null, "Must specify a valid data set.");
            DataSource dataSource = dataSet.getDataSource();
            Preconditions.checkState(!this.zzsc.contains(dataSource), "Data set for this data source %s is already added.", dataSource);
            Preconditions.checkArgument(!dataSet.getDataPoints().isEmpty(), "No data points specified in the input data set.");
            this.zzsc.add(dataSource);
            this.zzlh.add(dataSet);
            return this;
        }

        @RecentlyNonNull
        public Builder addAggregateDataPoint(@RecentlyNonNull DataPoint dataPoint) {
            Preconditions.checkArgument(dataPoint != null, "Must specify a valid aggregate data point.");
            DataSource dataSource = dataPoint.getDataSource();
            Preconditions.checkState(!this.zzsc.contains(dataSource), "Data set/Aggregate data point for this data source %s is already added.", dataSource);
            DataSet.zzb(dataPoint);
            this.zzsc.add(dataSource);
            this.zzsb.add(dataPoint);
            return this;
        }

        @RecentlyNonNull
        public SessionInsertRequest build() {
            boolean z = true;
            Preconditions.checkState(this.zzky != null, "Must specify a valid session.");
            if (this.zzky.getEndTime(TimeUnit.MILLISECONDS) == 0) {
                z = false;
            }
            Preconditions.checkState(z, "Must specify a valid end time, cannot insert a continuing session.");
            for (DataSet dataPoints : this.zzlh) {
                for (DataPoint zzd : dataPoints.getDataPoints()) {
                    zzd(zzd);
                }
            }
            for (DataPoint zzd2 : this.zzsb) {
                zzd(zzd2);
            }
            return new SessionInsertRequest(this);
        }

        private final void zzd(DataPoint dataPoint) {
            DataPoint dataPoint2 = dataPoint;
            long startTime = this.zzky.getStartTime(TimeUnit.NANOSECONDS);
            long endTime = this.zzky.getEndTime(TimeUnit.NANOSECONDS);
            long timestamp = dataPoint2.getTimestamp(TimeUnit.NANOSECONDS);
            if (timestamp != 0) {
                if (timestamp < startTime || timestamp > endTime) {
                    timestamp = zzi.zza(timestamp, TimeUnit.NANOSECONDS, SessionInsertRequest.zzsa);
                }
                Preconditions.checkState(timestamp >= startTime && timestamp <= endTime, "Data point %s has time stamp outside session interval [%d, %d]", dataPoint2, Long.valueOf(startTime), Long.valueOf(endTime));
                if (dataPoint2.getTimestamp(TimeUnit.NANOSECONDS) != timestamp) {
                    Log.w("Fitness", String.format("Data point timestamp [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", new Object[]{Long.valueOf(dataPoint2.getTimestamp(TimeUnit.NANOSECONDS)), Long.valueOf(timestamp), SessionInsertRequest.zzsa}));
                    dataPoint2.setTimestamp(timestamp, TimeUnit.NANOSECONDS);
                }
            }
            long startTime2 = this.zzky.getStartTime(TimeUnit.NANOSECONDS);
            long endTime2 = this.zzky.getEndTime(TimeUnit.NANOSECONDS);
            long startTime3 = dataPoint2.getStartTime(TimeUnit.NANOSECONDS);
            long endTime3 = dataPoint2.getEndTime(TimeUnit.NANOSECONDS);
            if (startTime3 != 0 && endTime3 != 0) {
                if (endTime3 > endTime2) {
                    endTime3 = zzi.zza(endTime3, TimeUnit.NANOSECONDS, SessionInsertRequest.zzsa);
                }
                Preconditions.checkState(startTime3 >= startTime2 && endTime3 <= endTime2, "Data point %s has start and end times outside session interval [%d, %d]", dataPoint2, Long.valueOf(startTime2), Long.valueOf(endTime2));
                if (endTime3 != dataPoint2.getEndTime(TimeUnit.NANOSECONDS)) {
                    Log.w("Fitness", String.format("Data point end time [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", new Object[]{Long.valueOf(dataPoint2.getEndTime(TimeUnit.NANOSECONDS)), Long.valueOf(endTime3), SessionInsertRequest.zzsa}));
                    dataPoint.setTimeInterval(startTime3, endTime3, TimeUnit.NANOSECONDS);
                }
            }
        }
    }

    private SessionInsertRequest(Builder builder) {
        this(builder.zzky, (List<DataSet>) builder.zzlh, (List<DataPoint>) builder.zzsb, (zzcn) null);
    }

    public SessionInsertRequest(SessionInsertRequest sessionInsertRequest, zzcn zzcn) {
        this(sessionInsertRequest.zzky, sessionInsertRequest.zzlh, sessionInsertRequest.zzsb, zzcn);
    }

    private SessionInsertRequest(Session session, List<DataSet> list, List<DataPoint> list2, @Nullable zzcn zzcn) {
        this.zzky = session;
        this.zzlh = Collections.unmodifiableList(list);
        this.zzsb = Collections.unmodifiableList(list2);
        this.zzql = zzcn;
    }

    @RecentlyNonNull
    public Session getSession() {
        return this.zzky;
    }

    @RecentlyNonNull
    public List<DataSet> getDataSets() {
        return this.zzlh;
    }

    @RecentlyNonNull
    public List<DataPoint> getAggregateDataPoints() {
        return this.zzsb;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj != this) {
            if (obj instanceof SessionInsertRequest) {
                SessionInsertRequest sessionInsertRequest = (SessionInsertRequest) obj;
                if (Objects.equal(this.zzky, sessionInsertRequest.zzky) && Objects.equal(this.zzlh, sessionInsertRequest.zzlh) && Objects.equal(this.zzsb, sessionInsertRequest.zzsb)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzky, this.zzlh, this.zzsb);
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("session", this.zzky).add("dataSets", this.zzlh).add("aggregateDataPoints", this.zzsb).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getSession(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, getDataSets(), false);
        SafeParcelWriter.writeTypedList(parcel, 3, getAggregateDataPoints(), false);
        zzcn zzcn = this.zzql;
        SafeParcelWriter.writeIBinder(parcel, 4, zzcn == null ? null : zzcn.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
