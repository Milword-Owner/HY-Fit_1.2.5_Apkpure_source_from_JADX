package com.google.android.gms.fitness.request;

import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class SensorRequest {
    public static final int ACCURACY_MODE_DEFAULT = 2;
    public static final int ACCURACY_MODE_HIGH = 3;
    public static final int ACCURACY_MODE_LOW = 1;
    @Nullable
    private final DataType zzkp;
    @Nullable
    private final DataSource zzkq;
    private final long zzof;
    private final int zzog;
    private final long zzru;
    private final long zzrv;
    private final long zzry;

    private SensorRequest(Builder builder) {
        this.zzkq = builder.zzkq;
        this.zzkp = builder.zzkp;
        this.zzof = builder.zzof;
        this.zzrv = builder.zzrv;
        this.zzru = builder.zzru;
        this.zzog = builder.zzog;
        this.zzry = builder.zzry;
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class Builder {
        /* access modifiers changed from: private */
        public DataType zzkp;
        /* access modifiers changed from: private */
        public DataSource zzkq;
        /* access modifiers changed from: private */
        public long zzof = -1;
        /* access modifiers changed from: private */
        public int zzog = 2;
        /* access modifiers changed from: private */
        public long zzru = 0;
        /* access modifiers changed from: private */
        public long zzrv = 0;
        /* access modifiers changed from: private */
        public long zzry = LongCompanionObject.MAX_VALUE;
        private boolean zzrz = false;

        @RecentlyNonNull
        public Builder setDataSource(@RecentlyNonNull DataSource dataSource) {
            this.zzkq = dataSource;
            return this;
        }

        @RecentlyNonNull
        public Builder setDataType(@RecentlyNonNull DataType dataType) {
            this.zzkp = dataType;
            return this;
        }

        @RecentlyNonNull
        public Builder setSamplingRate(long j, @RecentlyNonNull TimeUnit timeUnit) {
            Preconditions.checkArgument(j >= 0, "Cannot use a negative sampling interval");
            this.zzof = timeUnit.toMicros(j);
            if (!this.zzrz) {
                this.zzrv = this.zzof / 2;
            }
            return this;
        }

        @RecentlyNonNull
        public Builder setFastestRate(int i, @RecentlyNonNull TimeUnit timeUnit) {
            Preconditions.checkArgument(i >= 0, "Cannot use a negative interval");
            this.zzrz = true;
            this.zzrv = timeUnit.toMicros((long) i);
            return this;
        }

        @RecentlyNonNull
        public Builder setMaxDeliveryLatency(int i, @RecentlyNonNull TimeUnit timeUnit) {
            Preconditions.checkArgument(i >= 0, "Cannot use a negative delivery interval");
            this.zzru = timeUnit.toMicros((long) i);
            return this;
        }

        @RecentlyNonNull
        public Builder setAccuracyMode(int i) {
            if (!(i == 1 || i == 3)) {
                i = 2;
            }
            this.zzog = i;
            return this;
        }

        @RecentlyNonNull
        public Builder setTimeout(long j, @RecentlyNonNull TimeUnit timeUnit) {
            boolean z = true;
            Preconditions.checkArgument(j > 0, "Invalid time out value specified: %d", Long.valueOf(j));
            if (timeUnit == null) {
                z = false;
            }
            Preconditions.checkArgument(z, "Invalid time unit specified");
            this.zzry = timeUnit.toMicros(j);
            return this;
        }

        @RecentlyNonNull
        public SensorRequest build() {
            DataSource dataSource;
            boolean z = false;
            Preconditions.checkState((this.zzkq == null && this.zzkp == null) ? false : true, "Must call setDataSource() or setDataType()");
            DataType dataType = this.zzkp;
            if (dataType == null || (dataSource = this.zzkq) == null || dataType.equals(dataSource.getDataType())) {
                z = true;
            }
            Preconditions.checkState(z, "Specified data type is incompatible with specified data source");
            return new SensorRequest(this);
        }
    }

    @RecentlyNullable
    public DataSource getDataSource() {
        return this.zzkq;
    }

    @RecentlyNullable
    public DataType getDataType() {
        return this.zzkp;
    }

    public long getSamplingRate(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzof, TimeUnit.MICROSECONDS);
    }

    public long getFastestRate(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzrv, TimeUnit.MICROSECONDS);
    }

    public long getMaxDeliveryLatency(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzru, TimeUnit.MICROSECONDS);
    }

    public int getAccuracyMode() {
        return this.zzog;
    }

    public final long zzy() {
        return this.zzry;
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("dataSource", this.zzkq).add("dataType", this.zzkp).add("samplingRateMicros", Long.valueOf(this.zzof)).add("deliveryLatencyMicros", Long.valueOf(this.zzru)).add("timeOutMicros", Long.valueOf(this.zzry)).toString();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SensorRequest)) {
            return false;
        }
        SensorRequest sensorRequest = (SensorRequest) obj;
        return Objects.equal(this.zzkq, sensorRequest.zzkq) && Objects.equal(this.zzkp, sensorRequest.zzkp) && this.zzof == sensorRequest.zzof && this.zzrv == sensorRequest.zzrv && this.zzru == sensorRequest.zzru && this.zzog == sensorRequest.zzog && this.zzry == sensorRequest.zzry;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzkq, this.zzkp, Long.valueOf(this.zzof), Long.valueOf(this.zzrv), Long.valueOf(this.zzru), Integer.valueOf(this.zzog), Long.valueOf(this.zzry));
    }
}
