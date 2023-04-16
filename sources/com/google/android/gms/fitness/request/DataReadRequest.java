package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzbc;
import com.google.android.gms.internal.fitness.zzbf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@SafeParcelable.Class(creator = "DataReadRequestCreator")
@SafeParcelable.Reserved({11, 15, 16, 17, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class DataReadRequest extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<DataReadRequest> CREATOR = new zzn();
    public static final int NO_LIMIT = 0;
    @SafeParcelable.Field(getter = "getLimit", mo19514id = 10)
    private final int limit;
    @SafeParcelable.Field(getter = "getStartTimeMillis", mo19514id = 3)
    private final long zzkr;
    @SafeParcelable.Field(getter = "getEndTimeMillis", mo19514id = 4)
    private final long zzks;
    @SafeParcelable.Field(getter = "getDataTypes", mo19514id = 1)
    private final List<DataType> zzlf;
    @SafeParcelable.Field(getter = "getBucketType", mo19514id = 7)
    private final int zzli;
    @SafeParcelable.Field(getter = "getDataSources", mo19514id = 2)
    private final List<DataSource> zzqq;
    @SafeParcelable.Field(getter = "getAggregatedDataTypes", mo19514id = 5)
    private final List<DataType> zzqx;
    @SafeParcelable.Field(getter = "getAggregatedDataSources", mo19514id = 6)
    private final List<DataSource> zzqy;
    @SafeParcelable.Field(getter = "getBucketDurationMillis", mo19514id = 8)
    private final long zzqz;
    @SafeParcelable.Field(getter = "getActivityDataSource", mo19514id = 9)
    private final DataSource zzra;
    @SafeParcelable.Field(getter = "flushBufferBeforeRead", mo19514id = 12)
    private final boolean zzrb;
    @SafeParcelable.Field(getter = "areServerQueriesEnabled", mo19514id = 13)
    private final boolean zzrc;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 14, type = "android.os.IBinder")
    @Nullable
    private final zzbc zzrd;
    @SafeParcelable.Field(getter = "getIntervalStartTimesNanos", mo19514id = 18)
    private final List<Long> zzre;
    @SafeParcelable.Field(getter = "getIntervalEndTimesNanos", mo19514id = 19)
    private final List<Long> zzrf;

    @SafeParcelable.Constructor
    DataReadRequest(@SafeParcelable.Param(mo19517id = 1) List<DataType> list, @SafeParcelable.Param(mo19517id = 2) List<DataSource> list2, @SafeParcelable.Param(mo19517id = 3) long j, @SafeParcelable.Param(mo19517id = 4) long j2, @SafeParcelable.Param(mo19517id = 5) List<DataType> list3, @SafeParcelable.Param(mo19517id = 6) List<DataSource> list4, @SafeParcelable.Param(mo19517id = 7) int i, @SafeParcelable.Param(mo19517id = 8) long j3, @SafeParcelable.Param(mo19517id = 9) DataSource dataSource, @SafeParcelable.Param(mo19517id = 10) int i2, @SafeParcelable.Param(mo19517id = 12) boolean z, @SafeParcelable.Param(mo19517id = 13) boolean z2, @SafeParcelable.Param(mo19517id = 14) @Nullable IBinder iBinder, @SafeParcelable.Param(mo19517id = 18) List<Long> list5, @SafeParcelable.Param(mo19517id = 19) List<Long> list6) {
        zzbc zzbc;
        this.zzlf = list;
        this.zzqq = list2;
        this.zzkr = j;
        this.zzks = j2;
        this.zzqx = list3;
        this.zzqy = list4;
        this.zzli = i;
        this.zzqz = j3;
        this.zzra = dataSource;
        this.limit = i2;
        this.zzrb = z;
        this.zzrc = z2;
        if (iBinder == null) {
            zzbc = null;
        } else {
            zzbc = zzbf.zzc(iBinder);
        }
        this.zzrd = zzbc;
        this.zzre = list5 == null ? Collections.emptyList() : list5;
        this.zzrf = list6 == null ? Collections.emptyList() : list6;
        Preconditions.checkArgument(this.zzre.size() == this.zzrf.size(), "Unequal number of interval start and end times.");
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class Builder {
        /* access modifiers changed from: private */
        public int limit = 0;
        /* access modifiers changed from: private */
        public long zzkr;
        /* access modifiers changed from: private */
        public long zzks;
        /* access modifiers changed from: private */
        public final List<DataType> zzlf = new ArrayList();
        /* access modifiers changed from: private */
        public int zzli = 0;
        /* access modifiers changed from: private */
        public final List<DataSource> zzqq = new ArrayList();
        /* access modifiers changed from: private */
        public final List<DataType> zzqx = new ArrayList();
        /* access modifiers changed from: private */
        public final List<DataSource> zzqy = new ArrayList();
        /* access modifiers changed from: private */
        public long zzqz = 0;
        /* access modifiers changed from: private */
        public DataSource zzra;
        private boolean zzrb = false;
        /* access modifiers changed from: private */
        public boolean zzrc = false;
        /* access modifiers changed from: private */
        public final List<Long> zzre = new ArrayList();
        /* access modifiers changed from: private */
        public final List<Long> zzrf = new ArrayList();

        @RecentlyNonNull
        public Builder read(@RecentlyNonNull DataSource dataSource) {
            Preconditions.checkNotNull(dataSource, "Attempting to add a null data source");
            Preconditions.checkArgument(!this.zzqy.contains(dataSource), "Cannot add the same data source as aggregated and detailed");
            if (!this.zzqq.contains(dataSource)) {
                this.zzqq.add(dataSource);
            }
            return this;
        }

        @RecentlyNonNull
        public Builder read(@RecentlyNonNull DataType dataType) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            Preconditions.checkState(!this.zzqx.contains(dataType), "Cannot add the same data type as aggregated and detailed");
            if (!this.zzlf.contains(dataType)) {
                this.zzlf.add(dataType);
            }
            return this;
        }

        @RecentlyNonNull
        @Deprecated
        public Builder aggregate(@RecentlyNonNull DataSource dataSource, @RecentlyNonNull DataType dataType) {
            Preconditions.checkNotNull(dataSource, "Attempting to add a null data source");
            Preconditions.checkState(!this.zzqq.contains(dataSource), "Cannot add the same data source for aggregated and detailed");
            DataType dataType2 = dataSource.getDataType();
            DataType aggregateType = dataType2.getAggregateType();
            if (aggregateType != null) {
                Preconditions.checkArgument(aggregateType.equals(dataType), "Invalid output aggregate data type specified: %s -> %s", dataType2, dataType);
                if (!this.zzqy.contains(dataSource)) {
                    this.zzqy.add(dataSource);
                }
                return this;
            }
            String valueOf = String.valueOf(dataType2);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 55);
            sb.append("Unsupported input data type specified for aggregation: ");
            sb.append(valueOf);
            throw new IllegalArgumentException(sb.toString());
        }

        @RecentlyNonNull
        public Builder aggregate(@RecentlyNonNull DataSource dataSource) {
            Preconditions.checkNotNull(dataSource, "Attempting to add a null data source");
            Preconditions.checkState(!this.zzqq.contains(dataSource), "Cannot add the same data source for aggregated and detailed");
            DataType dataType = dataSource.getDataType();
            Preconditions.checkArgument(dataType.getAggregateType() != null, "Unsupported input data type specified for aggregation: %s", dataType);
            if (!this.zzqy.contains(dataSource)) {
                this.zzqy.add(dataSource);
            }
            return this;
        }

        @RecentlyNonNull
        @Deprecated
        public Builder aggregate(@RecentlyNonNull DataType dataType, @RecentlyNonNull DataType dataType2) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            Preconditions.checkState(!this.zzlf.contains(dataType), "Cannot add the same data type as aggregated and detailed");
            DataType aggregateType = dataType.getAggregateType();
            if (aggregateType != null) {
                Preconditions.checkArgument(aggregateType.equals(dataType2), "Invalid output aggregate data type specified: %s -> %s", dataType, dataType2);
                if (!this.zzqx.contains(dataType)) {
                    this.zzqx.add(dataType);
                }
                return this;
            }
            String valueOf = String.valueOf(dataType);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 55);
            sb.append("Unsupported input data type specified for aggregation: ");
            sb.append(valueOf);
            throw new IllegalArgumentException(sb.toString());
        }

        @RecentlyNonNull
        public Builder aggregate(@RecentlyNonNull DataType dataType) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            Preconditions.checkState(!this.zzlf.contains(dataType), "Cannot add the same data type as aggregated and detailed");
            Preconditions.checkArgument(dataType.getAggregateType() != null, "Unsupported input data type specified for aggregation: %s", dataType);
            if (!this.zzqx.contains(dataType)) {
                this.zzqx.add(dataType);
            }
            return this;
        }

        @RecentlyNonNull
        public Builder bucketByTime(int i, @RecentlyNonNull TimeUnit timeUnit) {
            Preconditions.checkArgument(this.zzli == 0, "Bucketing strategy already set to %s", Integer.valueOf(this.zzli));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration: %d", Integer.valueOf(i));
            this.zzli = 1;
            this.zzqz = timeUnit.toMillis((long) i);
            return this;
        }

        @RecentlyNonNull
        public Builder bucketByActivityType(int i, @RecentlyNonNull TimeUnit timeUnit) {
            Preconditions.checkArgument(this.zzli == 0, "Bucketing strategy already set to %s", Integer.valueOf(this.zzli));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            this.zzli = 3;
            this.zzqz = timeUnit.toMillis((long) i);
            return this;
        }

        @RecentlyNonNull
        public Builder bucketByActivityType(int i, @RecentlyNonNull TimeUnit timeUnit, @RecentlyNonNull DataSource dataSource) {
            Preconditions.checkArgument(this.zzli == 0, "Bucketing strategy already set to %s", Integer.valueOf(this.zzli));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            Preconditions.checkArgument(dataSource != null, "Invalid activity data source specified");
            Preconditions.checkArgument(dataSource.getDataType().equals(DataType.TYPE_ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", dataSource);
            this.zzra = dataSource;
            this.zzli = 3;
            this.zzqz = timeUnit.toMillis((long) i);
            return this;
        }

        @RecentlyNonNull
        public Builder bucketByActivitySegment(int i, @RecentlyNonNull TimeUnit timeUnit) {
            Preconditions.checkArgument(this.zzli == 0, "Bucketing strategy already set to %s", Integer.valueOf(this.zzli));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            this.zzli = 4;
            this.zzqz = timeUnit.toMillis((long) i);
            return this;
        }

        @RecentlyNonNull
        public Builder bucketByActivitySegment(int i, @RecentlyNonNull TimeUnit timeUnit, @RecentlyNonNull DataSource dataSource) {
            Preconditions.checkArgument(this.zzli == 0, "Bucketing strategy already set to %s", Integer.valueOf(this.zzli));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            Preconditions.checkArgument(dataSource != null, "Invalid activity data source specified");
            Preconditions.checkArgument(dataSource.getDataType().equals(DataType.TYPE_ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", dataSource);
            this.zzra = dataSource;
            this.zzli = 4;
            this.zzqz = timeUnit.toMillis((long) i);
            return this;
        }

        @RecentlyNonNull
        public Builder bucketBySession(int i, @RecentlyNonNull TimeUnit timeUnit) {
            Preconditions.checkArgument(this.zzli == 0, "Bucketing strategy already set to %s", Integer.valueOf(this.zzli));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for a session: %d", Integer.valueOf(i));
            this.zzli = 2;
            this.zzqz = timeUnit.toMillis((long) i);
            return this;
        }

        @RecentlyNonNull
        public Builder setTimeRange(long j, long j2, @RecentlyNonNull TimeUnit timeUnit) {
            this.zzkr = timeUnit.toMillis(j);
            this.zzks = timeUnit.toMillis(j2);
            return this;
        }

        @RecentlyNonNull
        public Builder enableServerQueries() {
            this.zzrc = true;
            return this;
        }

        @RecentlyNonNull
        public Builder setLimit(int i) {
            Preconditions.checkArgument(i > 0, "Invalid limit %d is specified", Integer.valueOf(i));
            this.limit = i;
            return this;
        }

        @RecentlyNonNull
        public DataReadRequest build() {
            boolean z = false;
            Preconditions.checkState(!this.zzqq.isEmpty() || !this.zzlf.isEmpty() || !this.zzqy.isEmpty() || !this.zzqx.isEmpty(), "Must add at least one data source (aggregated or detailed)");
            if (this.zzli != 5) {
                Preconditions.checkState(this.zzkr > 0, "Invalid start time: %s", Long.valueOf(this.zzkr));
                long j = this.zzks;
                Preconditions.checkState(j > 0 && j > this.zzkr, "Invalid end time: %s", Long.valueOf(this.zzks));
            }
            boolean z2 = this.zzqy.isEmpty() && this.zzqx.isEmpty();
            if (this.zzli == 0) {
                Preconditions.checkState(z2, "Must specify a valid bucketing strategy while requesting aggregation");
            }
            if (!z2) {
                if (this.zzli != 0) {
                    z = true;
                }
                Preconditions.checkState(z, "Must specify a valid bucketing strategy while requesting aggregation");
            }
            return new DataReadRequest(this);
        }
    }

    private DataReadRequest(Builder builder) {
        this((List<DataType>) builder.zzlf, (List<DataSource>) builder.zzqq, builder.zzkr, builder.zzks, (List<DataType>) builder.zzqx, (List<DataSource>) builder.zzqy, builder.zzli, builder.zzqz, builder.zzra, builder.limit, false, builder.zzrc, (zzbc) null, (List<Long>) builder.zzre, (List<Long>) builder.zzrf);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DataReadRequest(com.google.android.gms.fitness.request.DataReadRequest r22, com.google.android.gms.internal.fitness.zzbc r23) {
        /*
            r21 = this;
            r0 = r22
            r1 = r21
            r17 = r23
            java.util.List<com.google.android.gms.fitness.data.DataType> r2 = r0.zzlf
            java.util.List<com.google.android.gms.fitness.data.DataSource> r3 = r0.zzqq
            long r4 = r0.zzkr
            long r6 = r0.zzks
            java.util.List<com.google.android.gms.fitness.data.DataType> r8 = r0.zzqx
            java.util.List<com.google.android.gms.fitness.data.DataSource> r9 = r0.zzqy
            int r10 = r0.zzli
            long r11 = r0.zzqz
            com.google.android.gms.fitness.data.DataSource r13 = r0.zzra
            int r14 = r0.limit
            boolean r15 = r0.zzrb
            r20 = r1
            boolean r1 = r0.zzrc
            r16 = r1
            java.util.List<java.lang.Long> r1 = r0.zzre
            r18 = r1
            java.util.List<java.lang.Long> r0 = r0.zzrf
            r19 = r0
            r1 = r20
            r1.<init>((java.util.List<com.google.android.gms.fitness.data.DataType>) r2, (java.util.List<com.google.android.gms.fitness.data.DataSource>) r3, (long) r4, (long) r6, (java.util.List<com.google.android.gms.fitness.data.DataType>) r8, (java.util.List<com.google.android.gms.fitness.data.DataSource>) r9, (int) r10, (long) r11, (com.google.android.gms.fitness.data.DataSource) r13, (int) r14, (boolean) r15, (boolean) r16, (com.google.android.gms.internal.fitness.zzbc) r17, (java.util.List<java.lang.Long>) r18, (java.util.List<java.lang.Long>) r19)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.DataReadRequest.<init>(com.google.android.gms.fitness.request.DataReadRequest, com.google.android.gms.internal.fitness.zzbc):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    private DataReadRequest(List<DataType> list, List<DataSource> list2, long j, long j2, List<DataType> list3, List<DataSource> list4, int i, long j3, DataSource dataSource, int i2, boolean z, boolean z2, @Nullable zzbc zzbc, List<Long> list5, List<Long> list6) {
        this(list, list2, j, j2, list3, list4, i, j3, dataSource, i2, z, z2, zzbc == null ? null : zzbc.asBinder(), list5, list6);
    }

    public long getStartTime(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzkr, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzks, TimeUnit.MILLISECONDS);
    }

    @RecentlyNonNull
    public List<DataType> getDataTypes() {
        return this.zzlf;
    }

    @RecentlyNonNull
    public List<DataSource> getDataSources() {
        return this.zzqq;
    }

    @RecentlyNonNull
    public List<DataType> getAggregatedDataTypes() {
        return this.zzqx;
    }

    @RecentlyNonNull
    public List<DataSource> getAggregatedDataSources() {
        return this.zzqy;
    }

    public int getBucketType() {
        return this.zzli;
    }

    public long getBucketDuration(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzqz, TimeUnit.MILLISECONDS);
    }

    @RecentlyNullable
    public DataSource getActivityDataSource() {
        return this.zzra;
    }

    public int getLimit() {
        return this.limit;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof DataReadRequest) {
                DataReadRequest dataReadRequest = (DataReadRequest) obj;
                if (this.zzlf.equals(dataReadRequest.zzlf) && this.zzqq.equals(dataReadRequest.zzqq) && this.zzkr == dataReadRequest.zzkr && this.zzks == dataReadRequest.zzks && this.zzli == dataReadRequest.zzli && this.zzqy.equals(dataReadRequest.zzqy) && this.zzqx.equals(dataReadRequest.zzqx) && Objects.equal(this.zzra, dataReadRequest.zzra) && this.zzqz == dataReadRequest.zzqz && this.zzrc == dataReadRequest.zzrc && this.limit == dataReadRequest.limit && this.zzrb == dataReadRequest.zzrb && Objects.equal(this.zzrd, dataReadRequest.zzrd)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzli), Long.valueOf(this.zzkr), Long.valueOf(this.zzks));
    }

    @RecentlyNonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DataReadRequest{");
        if (!this.zzlf.isEmpty()) {
            for (DataType zzm : this.zzlf) {
                sb.append(zzm.zzm());
                sb.append(" ");
            }
        }
        if (!this.zzqq.isEmpty()) {
            for (DataSource debugString : this.zzqq) {
                sb.append(debugString.toDebugString());
                sb.append(" ");
            }
        }
        if (this.zzli != 0) {
            sb.append("bucket by ");
            sb.append(Bucket.zza(this.zzli));
            if (this.zzqz > 0) {
                sb.append(" >");
                sb.append(this.zzqz);
                sb.append("ms");
            }
            sb.append(": ");
        }
        if (!this.zzqx.isEmpty()) {
            for (DataType zzm2 : this.zzqx) {
                sb.append(zzm2.zzm());
                sb.append(" ");
            }
        }
        if (!this.zzqy.isEmpty()) {
            for (DataSource debugString2 : this.zzqy) {
                sb.append(debugString2.toDebugString());
                sb.append(" ");
            }
        }
        sb.append(String.format(Locale.US, "(%tF %tT - %tF %tT)", new Object[]{Long.valueOf(this.zzkr), Long.valueOf(this.zzkr), Long.valueOf(this.zzks), Long.valueOf(this.zzks)}));
        if (this.zzra != null) {
            sb.append("activities: ");
            sb.append(this.zzra.toDebugString());
        }
        if (this.zzrc) {
            sb.append(" +server");
        }
        sb.append("}");
        return sb.toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataTypes(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, getDataSources(), false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzkr);
        SafeParcelWriter.writeLong(parcel, 4, this.zzks);
        SafeParcelWriter.writeTypedList(parcel, 5, getAggregatedDataTypes(), false);
        SafeParcelWriter.writeTypedList(parcel, 6, getAggregatedDataSources(), false);
        SafeParcelWriter.writeInt(parcel, 7, getBucketType());
        SafeParcelWriter.writeLong(parcel, 8, this.zzqz);
        SafeParcelWriter.writeParcelable(parcel, 9, getActivityDataSource(), i, false);
        SafeParcelWriter.writeInt(parcel, 10, getLimit());
        SafeParcelWriter.writeBoolean(parcel, 12, this.zzrb);
        SafeParcelWriter.writeBoolean(parcel, 13, this.zzrc);
        zzbc zzbc = this.zzrd;
        SafeParcelWriter.writeIBinder(parcel, 14, zzbc == null ? null : zzbc.asBinder(), false);
        SafeParcelWriter.writeLongList(parcel, 18, this.zzre, false);
        SafeParcelWriter.writeLongList(parcel, 19, this.zzrf, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
