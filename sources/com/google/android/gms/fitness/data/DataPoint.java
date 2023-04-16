package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.internal.fitness.zzko;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SafeParcelable.Class(creator = "DataPointCreator")
@SafeParcelable.Reserved({1000, 8})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class DataPoint extends AbstractSafeParcelable implements ReflectedParcelable {
    @RecentlyNonNull
    @ShowFirstParty
    public static final Parcelable.Creator<DataPoint> CREATOR = new zzg();
    @SafeParcelable.Field(getter = "getDataSource", mo19514id = 1)
    private final DataSource zzkq;
    @SafeParcelable.Field(getter = "getTimestampNanos", mo19514id = 3)
    private long zzlj;
    @SafeParcelable.Field(getter = "getStartTimeNanos", mo19514id = 4)
    private long zzlk;
    @SafeParcelable.Field(getter = "getValues", mo19514id = 5)
    private final Value[] zzll;
    @SafeParcelable.Field(getter = "getOriginalDataSourceIfSet", mo19514id = 6)
    @Nullable
    private DataSource zzlm;
    @SafeParcelable.Field(getter = "getRawTimestamp", mo19514id = 7)
    private final long zzln;

    @SafeParcelable.Constructor
    public DataPoint(@RecentlyNonNull @SafeParcelable.Param(mo19517id = 1) DataSource dataSource, @SafeParcelable.Param(mo19517id = 3) long j, @SafeParcelable.Param(mo19517id = 4) long j2, @RecentlyNonNull @SafeParcelable.Param(mo19517id = 5) Value[] valueArr, @SafeParcelable.Param(mo19517id = 6) @Nullable DataSource dataSource2, @SafeParcelable.Param(mo19517id = 7) long j3) {
        this.zzkq = dataSource;
        this.zzlm = dataSource2;
        this.zzlj = j;
        this.zzlk = j2;
        this.zzll = valueArr;
        this.zzln = j3;
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class Builder {
        private final DataPoint zzlo;
        private boolean zzlp;

        private Builder(DataSource dataSource) {
            this.zzlp = false;
            this.zzlo = DataPoint.create(dataSource);
        }

        @RecentlyNonNull
        public DataPoint build() {
            Preconditions.checkState(!this.zzlp, "DataPoint#build should not be called multiple times.");
            this.zzlp = true;
            return this.zzlo;
        }

        @RecentlyNonNull
        public Builder setTimestamp(long j, @RecentlyNonNull TimeUnit timeUnit) {
            Preconditions.checkState(!this.zzlp, "Builder should not be mutated after calling #build.");
            this.zzlo.setTimestamp(j, timeUnit);
            return this;
        }

        @RecentlyNonNull
        public Builder setTimeInterval(long j, long j2, @RecentlyNonNull TimeUnit timeUnit) {
            Preconditions.checkState(!this.zzlp, "Builder should not be mutated after calling #build.");
            this.zzlo.setTimeInterval(j, j2, timeUnit);
            return this;
        }

        @RecentlyNonNull
        public Builder setActivityField(@RecentlyNonNull Field field, @RecentlyNonNull String str) {
            Preconditions.checkState(!this.zzlp, "Builder should not be mutated after calling #build.");
            this.zzlo.getValue(field).setInt(zzko.zzo(str));
            return this;
        }

        @RecentlyNonNull
        public Builder setField(@RecentlyNonNull Field field, int i) {
            Preconditions.checkState(!this.zzlp, "Builder should not be mutated after calling #build.");
            this.zzlo.getValue(field).setInt(i);
            return this;
        }

        @RecentlyNonNull
        public Builder setField(@RecentlyNonNull Field field, float f) {
            Preconditions.checkState(!this.zzlp, "Builder should not be mutated after calling #build.");
            this.zzlo.getValue(field).setFloat(f);
            return this;
        }

        @RecentlyNonNull
        public Builder setField(@RecentlyNonNull Field field, @RecentlyNonNull String str) {
            Preconditions.checkState(!this.zzlp, "Builder should not be mutated after calling #build.");
            this.zzlo.getValue(field).setString(str);
            return this;
        }

        @RecentlyNonNull
        public Builder setField(@RecentlyNonNull Field field, @RecentlyNonNull Map<String, Float> map) {
            Preconditions.checkState(!this.zzlp, "Builder should not be mutated after calling #build.");
            this.zzlo.getValue(field).zza(map);
            return this;
        }

        @RecentlyNonNull
        public Builder setFloatValues(@RecentlyNonNull float... fArr) {
            Preconditions.checkState(!this.zzlp, "Builder should not be mutated after calling #build.");
            this.zzlo.setFloatValues(fArr);
            return this;
        }

        @RecentlyNonNull
        public Builder setIntValues(@RecentlyNonNull int... iArr) {
            Preconditions.checkState(!this.zzlp, "Builder should not be mutated after calling #build.");
            this.zzlo.setIntValues(iArr);
            return this;
        }
    }

    DataPoint(List<DataSource> list, RawDataPoint rawDataPoint) {
        this((DataSource) Preconditions.checkNotNull(zza(list, rawDataPoint.zzp())), zza(list, rawDataPoint.zzq()), rawDataPoint);
    }

    @ShowFirstParty
    private DataPoint(DataSource dataSource, @Nullable DataSource dataSource2, RawDataPoint rawDataPoint) {
        this(dataSource, rawDataPoint.zzn(), rawDataPoint.zzo(), rawDataPoint.zze(), dataSource2, rawDataPoint.zzg());
    }

    private DataPoint(DataSource dataSource) {
        this.zzkq = (DataSource) Preconditions.checkNotNull(dataSource, "Data source cannot be null");
        List<Field> fields = dataSource.getDataType().getFields();
        this.zzll = new Value[fields.size()];
        int i = 0;
        for (Field format : fields) {
            this.zzll[i] = new Value(format.getFormat());
            i++;
        }
        this.zzln = 0;
    }

    @Nullable
    private static DataSource zza(List<DataSource> list, int i) {
        if (i < 0 || i >= list.size()) {
            return null;
        }
        return list.get(i);
    }

    @RecentlyNonNull
    public final DataSource getDataSource() {
        return this.zzkq;
    }

    @RecentlyNonNull
    public static Builder builder(@RecentlyNonNull DataSource dataSource) {
        Preconditions.checkNotNull(dataSource, "DataSource should be specified");
        return new Builder(dataSource);
    }

    @RecentlyNonNull
    @Deprecated
    public static DataPoint create(@RecentlyNonNull DataSource dataSource) {
        return new DataPoint(dataSource);
    }

    @RecentlyNullable
    public static DataPoint extract(@RecentlyNonNull Intent intent) {
        if (intent == null) {
            return null;
        }
        return (DataPoint) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.gms.fitness.EXTRA_DATA_POINT", CREATOR);
    }

    @RecentlyNonNull
    @Deprecated
    public final DataPoint setTimestamp(long j, @RecentlyNonNull TimeUnit timeUnit) {
        this.zzlj = timeUnit.toNanos(j);
        return this;
    }

    @RecentlyNonNull
    @Deprecated
    public final DataPoint setTimeInterval(long j, long j2, @RecentlyNonNull TimeUnit timeUnit) {
        this.zzlk = timeUnit.toNanos(j);
        this.zzlj = timeUnit.toNanos(j2);
        return this;
    }

    @RecentlyNonNull
    public final Value zzb(int i) {
        DataType dataType = getDataType();
        Preconditions.checkArgument(i >= 0 && i < dataType.getFields().size(), "fieldIndex %s is out of range for %s", Integer.valueOf(i), dataType);
        return this.zzll[i];
    }

    @RecentlyNonNull
    public final Value getValue(@RecentlyNonNull Field field) {
        return this.zzll[getDataType().indexOf(field)];
    }

    @RecentlyNonNull
    @ShowFirstParty
    public final Value[] zze() {
        return this.zzll;
    }

    @RecentlyNonNull
    @Deprecated
    public final DataPoint setFloatValues(@RecentlyNonNull float... fArr) {
        zzc(fArr.length);
        for (int i = 0; i < fArr.length; i++) {
            this.zzll[i].setFloat(fArr[i]);
        }
        return this;
    }

    @RecentlyNonNull
    @Deprecated
    public final DataPoint setIntValues(@RecentlyNonNull int... iArr) {
        zzc(iArr.length);
        for (int i = 0; i < iArr.length; i++) {
            this.zzll[i].setInt(iArr[i]);
        }
        return this;
    }

    private final void zzc(int i) {
        List<Field> fields = getDataType().getFields();
        int size = fields.size();
        Preconditions.checkArgument(i == size, "Attempting to insert %s values, but needed %s: %s", Integer.valueOf(i), Integer.valueOf(size), fields);
    }

    @RecentlyNonNull
    public final DataType getDataType() {
        return this.zzkq.getDataType();
    }

    @RecentlyNonNull
    public final DataSource getOriginalDataSource() {
        DataSource dataSource = this.zzlm;
        return dataSource != null ? dataSource : this.zzkq;
    }

    @ShowFirstParty
    @RecentlyNullable
    public final DataSource zzf() {
        return this.zzlm;
    }

    public final long getTimestamp(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzlj, TimeUnit.NANOSECONDS);
    }

    @ShowFirstParty
    public final long zzg() {
        return this.zzln;
    }

    public final long getStartTime(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzlk, TimeUnit.NANOSECONDS);
    }

    public final long getEndTime(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzlj, TimeUnit.NANOSECONDS);
    }

    public final void zzh() {
        Preconditions.checkArgument(getDataType().getName().equals(getDataSource().getDataType().getName()), "Conflicting data types found %s vs %s", getDataType(), getDataType());
        Preconditions.checkArgument(this.zzlj > 0, "Data point does not have the timestamp set: %s", this);
        Preconditions.checkArgument(this.zzlk <= this.zzlj, "Data point with start time greater than end time found: %s", this);
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataPoint)) {
            return false;
        }
        DataPoint dataPoint = (DataPoint) obj;
        return Objects.equal(this.zzkq, dataPoint.zzkq) && this.zzlj == dataPoint.zzlj && this.zzlk == dataPoint.zzlk && Arrays.equals(this.zzll, dataPoint.zzll) && Objects.equal(getOriginalDataSource(), dataPoint.getOriginalDataSource());
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzkq, Long.valueOf(this.zzlj), Long.valueOf(this.zzlk));
    }

    @RecentlyNonNull
    public final String toString() {
        Object[] objArr = new Object[6];
        objArr[0] = Arrays.toString(this.zzll);
        objArr[1] = Long.valueOf(this.zzlk);
        objArr[2] = Long.valueOf(this.zzlj);
        objArr[3] = Long.valueOf(this.zzln);
        objArr[4] = this.zzkq.toDebugString();
        DataSource dataSource = this.zzlm;
        objArr[5] = dataSource != null ? dataSource.toDebugString() : "N/A";
        return String.format("DataPoint{%s@[%s, %s,raw=%s](%s %s)}", objArr);
    }

    public final void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzlj);
        SafeParcelWriter.writeLong(parcel, 4, this.zzlk);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zzll, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzlm, i, false);
        SafeParcelWriter.writeLong(parcel, 7, this.zzln);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
