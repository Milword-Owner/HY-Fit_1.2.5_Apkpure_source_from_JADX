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
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SafeParcelable.Class(creator = "DataDeleteRequestCreator")
@SafeParcelable.Reserved({9, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class DataDeleteRequest extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<DataDeleteRequest> CREATOR = new zzk();
    @SafeParcelable.Field(getter = "getStartTimeMillis", mo19514id = 1)
    private final long zzkr;
    @SafeParcelable.Field(getter = "getEndTimeMillis", mo19514id = 2)
    private final long zzks;
    @SafeParcelable.Field(getter = "getDataTypes", mo19514id = 4)
    private final List<DataType> zzlf;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 8, type = "android.os.IBinder")
    @Nullable
    private final zzcn zzql;
    @SafeParcelable.Field(getter = "getDataSources", mo19514id = 3)
    private final List<DataSource> zzqq;
    @SafeParcelable.Field(getter = "getSessions", mo19514id = 5)
    private final List<Session> zzqr;
    @SafeParcelable.Field(getter = "deleteAllData", mo19514id = 6)
    private final boolean zzqs;
    @SafeParcelable.Field(getter = "deleteAllSessions", mo19514id = 7)
    private final boolean zzqt;
    @SafeParcelable.Field(getter = "deleteByTimeRange", mo19514id = 10)
    private final boolean zzqu;
    @SafeParcelable.Field(getter = "enableLocationCleanup", mo19514id = 11)
    private final boolean zzqv;

    @SafeParcelable.Constructor
    DataDeleteRequest(@SafeParcelable.Param(mo19517id = 1) long j, @SafeParcelable.Param(mo19517id = 2) long j2, @SafeParcelable.Param(mo19517id = 3) List<DataSource> list, @SafeParcelable.Param(mo19517id = 4) List<DataType> list2, @SafeParcelable.Param(mo19517id = 5) List<Session> list3, @SafeParcelable.Param(mo19517id = 6) boolean z, @SafeParcelable.Param(mo19517id = 7) boolean z2, @SafeParcelable.Param(mo19517id = 10) boolean z3, @SafeParcelable.Param(mo19517id = 11) boolean z4, @SafeParcelable.Param(mo19517id = 8) @Nullable IBinder iBinder) {
        zzcn zzcn;
        this.zzkr = j;
        this.zzks = j2;
        this.zzqq = Collections.unmodifiableList(list);
        this.zzlf = Collections.unmodifiableList(list2);
        this.zzqr = list3;
        this.zzqs = z;
        this.zzqt = z2;
        this.zzqu = z3;
        this.zzqv = z4;
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
        public long zzkr;
        /* access modifiers changed from: private */
        public long zzks;
        /* access modifiers changed from: private */
        public final List<DataType> zzlf = new ArrayList();
        /* access modifiers changed from: private */
        public final List<DataSource> zzqq = new ArrayList();
        /* access modifiers changed from: private */
        public final List<Session> zzqr = new ArrayList();
        /* access modifiers changed from: private */
        public boolean zzqs = false;
        /* access modifiers changed from: private */
        public boolean zzqt = false;
        private boolean zzqu = false;
        private boolean zzqv = false;

        @RecentlyNonNull
        public Builder setTimeInterval(long j, long j2, @RecentlyNonNull TimeUnit timeUnit) {
            Preconditions.checkArgument(j > 0, "Invalid start time: %d", Long.valueOf(j));
            Preconditions.checkArgument(j2 > j, "Invalid end time: %d", Long.valueOf(j2));
            this.zzkr = timeUnit.toMillis(j);
            this.zzks = timeUnit.toMillis(j2);
            return this;
        }

        @RecentlyNonNull
        public Builder deleteAllData() {
            Preconditions.checkArgument(this.zzlf.isEmpty(), "Specific data type already added for deletion. deleteAllData() will delete all data types and cannot be combined with addDataType()");
            Preconditions.checkArgument(this.zzqq.isEmpty(), "Specific data source already added for deletion. deleteAllData() will delete all data sources and cannot be combined with addDataSource()");
            this.zzqs = true;
            return this;
        }

        @RecentlyNonNull
        public Builder addDataType(@RecentlyNonNull DataType dataType) {
            boolean z = true;
            Preconditions.checkArgument(!this.zzqs, "All data is already marked for deletion.  addDataType() cannot be combined with deleteAllData()");
            if (dataType == null) {
                z = false;
            }
            Preconditions.checkArgument(z, "Must specify a valid data type");
            if (!this.zzlf.contains(dataType)) {
                this.zzlf.add(dataType);
            }
            return this;
        }

        @RecentlyNonNull
        public Builder addDataSource(@RecentlyNonNull DataSource dataSource) {
            boolean z = true;
            Preconditions.checkArgument(!this.zzqs, "All data is already marked for deletion.  addDataSource() cannot be combined with deleteAllData()");
            if (dataSource == null) {
                z = false;
            }
            Preconditions.checkArgument(z, "Must specify a valid data source");
            if (!this.zzqq.contains(dataSource)) {
                this.zzqq.add(dataSource);
            }
            return this;
        }

        @RecentlyNonNull
        public Builder addSession(@RecentlyNonNull Session session) {
            Preconditions.checkArgument(!this.zzqt, "All sessions already marked for deletion.  addSession() cannot be combined with deleteAllSessions()");
            boolean z = false;
            Preconditions.checkArgument(session != null, "Must specify a valid session");
            if (session.getEndTime(TimeUnit.MILLISECONDS) > 0) {
                z = true;
            }
            Preconditions.checkArgument(z, "Cannot delete an ongoing session. Please stop the session prior to deleting it");
            this.zzqr.add(session);
            return this;
        }

        @RecentlyNonNull
        public Builder deleteAllSessions() {
            Preconditions.checkArgument(this.zzqr.isEmpty(), "Specific session already added for deletion. deleteAllData() will delete all sessions and cannot be combined with addSession()");
            this.zzqt = true;
            return this;
        }

        @RecentlyNonNull
        public DataDeleteRequest build() {
            long j = this.zzkr;
            Preconditions.checkState(j > 0 && this.zzks > j, "Must specify a valid time interval");
            Preconditions.checkState((this.zzqs || !this.zzqq.isEmpty() || !this.zzlf.isEmpty()) || (this.zzqt || !this.zzqr.isEmpty()), "No data or session marked for deletion");
            if (!this.zzqr.isEmpty()) {
                for (Session next : this.zzqr) {
                    Preconditions.checkState(next.getStartTime(TimeUnit.MILLISECONDS) >= this.zzkr && next.getEndTime(TimeUnit.MILLISECONDS) <= this.zzks, "Session %s is outside the time interval [%d, %d]", next, Long.valueOf(this.zzkr), Long.valueOf(this.zzks));
                }
            }
            return new DataDeleteRequest(this);
        }
    }

    private DataDeleteRequest(Builder builder) {
        this(builder.zzkr, builder.zzks, (List<DataSource>) builder.zzqq, (List<DataType>) builder.zzlf, (List<Session>) builder.zzqr, builder.zzqs, builder.zzqt, false, false, (zzcn) null);
    }

    public DataDeleteRequest(DataDeleteRequest dataDeleteRequest, zzcn zzcn) {
        this(dataDeleteRequest.zzkr, dataDeleteRequest.zzks, dataDeleteRequest.zzqq, dataDeleteRequest.zzlf, dataDeleteRequest.zzqr, dataDeleteRequest.zzqs, dataDeleteRequest.zzqt, dataDeleteRequest.zzqu, dataDeleteRequest.zzqv, zzcn);
    }

    private DataDeleteRequest(long j, long j2, List<DataSource> list, List<DataType> list2, List<Session> list3, boolean z, boolean z2, boolean z3, boolean z4, @Nullable zzcn zzcn) {
        this.zzkr = j;
        this.zzks = j2;
        this.zzqq = Collections.unmodifiableList(list);
        this.zzlf = Collections.unmodifiableList(list2);
        this.zzqr = list3;
        this.zzqs = z;
        this.zzqt = z2;
        this.zzqu = z3;
        this.zzqv = z4;
        this.zzql = zzcn;
    }

    public long getStartTime(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzkr, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzks, TimeUnit.MILLISECONDS);
    }

    @RecentlyNonNull
    public List<DataSource> getDataSources() {
        return this.zzqq;
    }

    @RecentlyNonNull
    public List<DataType> getDataTypes() {
        return this.zzlf;
    }

    @RecentlyNonNull
    public List<Session> getSessions() {
        return this.zzqr;
    }

    public boolean deleteAllData() {
        return this.zzqs;
    }

    public boolean deleteAllSessions() {
        return this.zzqt;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataDeleteRequest)) {
            return false;
        }
        DataDeleteRequest dataDeleteRequest = (DataDeleteRequest) obj;
        return this.zzkr == dataDeleteRequest.zzkr && this.zzks == dataDeleteRequest.zzks && Objects.equal(this.zzqq, dataDeleteRequest.zzqq) && Objects.equal(this.zzlf, dataDeleteRequest.zzlf) && Objects.equal(this.zzqr, dataDeleteRequest.zzqr) && this.zzqs == dataDeleteRequest.zzqs && this.zzqt == dataDeleteRequest.zzqt && this.zzqu == dataDeleteRequest.zzqu && this.zzqv == dataDeleteRequest.zzqv;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzkr), Long.valueOf(this.zzks));
    }

    @RecentlyNonNull
    public String toString() {
        Objects.ToStringHelper add = Objects.toStringHelper(this).add("startTimeMillis", Long.valueOf(this.zzkr)).add("endTimeMillis", Long.valueOf(this.zzks)).add("dataSources", this.zzqq).add("dateTypes", this.zzlf).add("sessions", this.zzqr).add("deleteAllData", Boolean.valueOf(this.zzqs)).add("deleteAllSessions", Boolean.valueOf(this.zzqt));
        boolean z = this.zzqu;
        if (z) {
            add.add("deleteByTimeRange", Boolean.valueOf(z));
        }
        return add.toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzkr);
        SafeParcelWriter.writeLong(parcel, 2, this.zzks);
        SafeParcelWriter.writeTypedList(parcel, 3, getDataSources(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, getDataTypes(), false);
        SafeParcelWriter.writeTypedList(parcel, 5, getSessions(), false);
        SafeParcelWriter.writeBoolean(parcel, 6, deleteAllData());
        SafeParcelWriter.writeBoolean(parcel, 7, deleteAllSessions());
        zzcn zzcn = this.zzql;
        SafeParcelWriter.writeIBinder(parcel, 8, zzcn == null ? null : zzcn.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzqu);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzqv);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
