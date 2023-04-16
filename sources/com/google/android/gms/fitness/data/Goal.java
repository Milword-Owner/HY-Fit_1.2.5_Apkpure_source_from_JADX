package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.baidu.mobstat.Config;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzko;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SafeParcelable.Class(creator = "GoalCreator")
@SafeParcelable.Reserved({1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class Goal extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<Goal> CREATOR = new zzt();
    public static final int OBJECTIVE_TYPE_DURATION = 2;
    public static final int OBJECTIVE_TYPE_FREQUENCY = 3;
    public static final int OBJECTIVE_TYPE_METRIC = 1;
    @SafeParcelable.Field(getter = "getCreateTimeNanos", mo19514id = 1)
    private final long zznm;
    @SafeParcelable.Field(getter = "getExpireTimeNanos", mo19514id = 2)
    private final long zznn;
    @SafeParcelable.Field(getter = "getActivities", mo19514id = 3, type = "java.util.List")
    private final List<Integer> zzno;
    @SafeParcelable.Field(getter = "getRecurrence", mo19514id = 4)
    private final Recurrence zznp;
    @SafeParcelable.Field(getter = "getObjectiveType", mo19514id = 5)
    private final int zznq;
    @SafeParcelable.Field(getter = "getMetricObjectiveWithOutChecking", mo19514id = 6)
    private final MetricObjective zznr;
    @SafeParcelable.Field(getter = "getDurationObjectiveWithOutChecking", mo19514id = 7)
    private final DurationObjective zzns;
    @SafeParcelable.Field(getter = "getFrequencyObjectiveWithOutChecking", mo19514id = 8)
    private final FrequencyObjective zznt;

    @SafeParcelable.Class(creator = "DurationObjectiveCreator")
    @SafeParcelable.Reserved({1000})
    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class DurationObjective extends AbstractSafeParcelable {
        @RecentlyNonNull
        public static final Parcelable.Creator<DurationObjective> CREATOR = new zzp();
        @SafeParcelable.Field(getter = "getDuration", mo19514id = 1)
        private final long zznu;

        public long getDuration(@RecentlyNonNull TimeUnit timeUnit) {
            return timeUnit.convert(this.zznu, TimeUnit.NANOSECONDS);
        }

        @SafeParcelable.Constructor
        DurationObjective(@SafeParcelable.Param(mo19517id = 1) long j) {
            this.zznu = j;
        }

        public DurationObjective(long j, @RecentlyNonNull TimeUnit timeUnit) {
            this(timeUnit.toNanos(j));
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof DurationObjective) && this.zznu == ((DurationObjective) obj).zznu;
        }

        public int hashCode() {
            return (int) this.zznu;
        }

        @RecentlyNonNull
        public String toString() {
            return Objects.toStringHelper(this).add("duration", Long.valueOf(this.zznu)).toString();
        }

        public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeLong(parcel, 1, this.zznu);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @SafeParcelable.Class(creator = "FrequencyObjectiveCreator")
    @SafeParcelable.Reserved({1000})
    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class FrequencyObjective extends AbstractSafeParcelable {
        @RecentlyNonNull
        public static final Parcelable.Creator<FrequencyObjective> CREATOR = new zzs();
        @SafeParcelable.Field(getter = "getFrequency", mo19514id = 1)
        private final int frequency;

        public int getFrequency() {
            return this.frequency;
        }

        @SafeParcelable.Constructor
        public FrequencyObjective(@SafeParcelable.Param(mo19517id = 1) int i) {
            this.frequency = i;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof FrequencyObjective) && this.frequency == ((FrequencyObjective) obj).frequency;
        }

        public int hashCode() {
            return this.frequency;
        }

        @RecentlyNonNull
        public String toString() {
            return Objects.toStringHelper(this).add("frequency", Integer.valueOf(this.frequency)).toString();
        }

        public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, getFrequency());
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @SafeParcelable.Class(creator = "MetricObjectiveCreator")
    @SafeParcelable.Reserved({1000})
    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class MetricObjective extends AbstractSafeParcelable {
        @RecentlyNonNull
        public static final Parcelable.Creator<MetricObjective> CREATOR = new zzx();
        @SafeParcelable.Field(getter = "getValue", mo19514id = 2)
        private final double value;
        @SafeParcelable.Field(getter = "getDataTypeName", mo19514id = 1)
        private final String zznv;
        @SafeParcelable.Field(getter = "getInitialValue", mo19514id = 3)
        private final double zznw;

        @RecentlyNonNull
        public String getDataTypeName() {
            return this.zznv;
        }

        public double getValue() {
            return this.value;
        }

        @ShowFirstParty
        @SafeParcelable.Constructor
        public MetricObjective(@RecentlyNonNull @SafeParcelable.Param(mo19517id = 1) String str, @SafeParcelable.Param(mo19517id = 2) double d, @SafeParcelable.Param(mo19517id = 3) double d2) {
            this.zznv = str;
            this.value = d;
            this.zznw = d2;
        }

        public MetricObjective(@RecentlyNonNull String str, double d) {
            this(str, d, Utils.DOUBLE_EPSILON);
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MetricObjective)) {
                return false;
            }
            MetricObjective metricObjective = (MetricObjective) obj;
            return Objects.equal(this.zznv, metricObjective.zznv) && this.value == metricObjective.value && this.zznw == metricObjective.zznw;
        }

        public int hashCode() {
            return this.zznv.hashCode();
        }

        @RecentlyNonNull
        public String toString() {
            return Objects.toStringHelper(this).add("dataTypeName", this.zznv).add("value", Double.valueOf(this.value)).add("initialValue", Double.valueOf(this.zznw)).toString();
        }

        public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeString(parcel, 1, getDataTypeName(), false);
            SafeParcelWriter.writeDouble(parcel, 2, getValue());
            SafeParcelWriter.writeDouble(parcel, 3, this.zznw);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class MismatchedGoalException extends IllegalStateException {
        public MismatchedGoalException(@RecentlyNonNull String str) {
            super(str);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public @interface ObjectiveType {
    }

    @SafeParcelable.Class(creator = "RecurrenceCreator")
    @SafeParcelable.Reserved({1000})
    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class Recurrence extends AbstractSafeParcelable {
        @RecentlyNonNull
        public static final Parcelable.Creator<Recurrence> CREATOR = new zzac();
        public static final int UNIT_DAY = 1;
        public static final int UNIT_MONTH = 3;
        public static final int UNIT_WEEK = 2;
        @SafeParcelable.Field(getter = "getCount", mo19514id = 1)
        private final int count;
        /* access modifiers changed from: private */
        @SafeParcelable.Field(getter = "getUnit", mo19514id = 2)
        public final int zznx;

        @Retention(RetentionPolicy.SOURCE)
        /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
        public @interface RecurrenceUnit {
        }

        public int getCount() {
            return this.count;
        }

        public int getUnit() {
            return this.zznx;
        }

        @SafeParcelable.Constructor
        public Recurrence(@SafeParcelable.Param(mo19517id = 1) int i, @SafeParcelable.Param(mo19517id = 2) int i2) {
            this.count = i;
            Preconditions.checkState(i2 > 0 && i2 <= 3);
            this.zznx = i2;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Recurrence)) {
                return false;
            }
            Recurrence recurrence = (Recurrence) obj;
            return this.count == recurrence.count && this.zznx == recurrence.zznx;
        }

        public int hashCode() {
            return this.zznx;
        }

        @RecentlyNonNull
        public String toString() {
            String str;
            Objects.ToStringHelper add = Objects.toStringHelper(this).add("count", Integer.valueOf(this.count));
            int i = this.zznx;
            if (i == 1) {
                str = Config.TRACE_VISIT_RECENT_DAY;
            } else if (i == 2) {
                str = "week";
            } else if (i == 3) {
                str = "month";
            } else {
                throw new IllegalArgumentException("invalid unit value");
            }
            return add.add("unit", str).toString();
        }

        public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, getCount());
            SafeParcelWriter.writeInt(parcel, 2, getUnit());
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    public long getCreateTime(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zznm, TimeUnit.NANOSECONDS);
    }

    public long getStartTime(@RecentlyNonNull Calendar calendar, @RecentlyNonNull TimeUnit timeUnit) {
        if (this.zznp == null) {
            return timeUnit.convert(this.zznm, TimeUnit.NANOSECONDS);
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(calendar.getTime());
        int zza = this.zznp.zznx;
        if (zza == 1) {
            instance.set(11, 0);
            return timeUnit.convert(instance.getTimeInMillis(), TimeUnit.MILLISECONDS);
        } else if (zza == 2) {
            instance.set(7, 2);
            instance.set(11, 0);
            return timeUnit.convert(instance.getTimeInMillis(), TimeUnit.MILLISECONDS);
        } else if (zza == 3) {
            instance.set(5, 1);
            instance.set(11, 0);
            return timeUnit.convert(instance.getTimeInMillis(), TimeUnit.MILLISECONDS);
        } else {
            int zza2 = this.zznp.zznx;
            StringBuilder sb = new StringBuilder(24);
            sb.append("Invalid unit ");
            sb.append(zza2);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public long getEndTime(@RecentlyNonNull Calendar calendar, @RecentlyNonNull TimeUnit timeUnit) {
        if (this.zznp == null) {
            return timeUnit.convert(this.zznn, TimeUnit.NANOSECONDS);
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(calendar.getTime());
        int zza = this.zznp.zznx;
        if (zza == 1) {
            instance.add(5, 1);
            instance.set(11, 0);
            return timeUnit.convert(instance.getTimeInMillis(), TimeUnit.MILLISECONDS);
        } else if (zza == 2) {
            instance.add(4, 1);
            instance.set(7, 2);
            instance.set(11, 0);
            return timeUnit.convert(instance.getTimeInMillis(), TimeUnit.MILLISECONDS);
        } else if (zza == 3) {
            instance.add(2, 1);
            instance.set(5, 1);
            instance.set(11, 0);
            return timeUnit.convert(instance.getTimeInMillis(), TimeUnit.MILLISECONDS);
        } else {
            int zza2 = this.zznp.zznx;
            StringBuilder sb = new StringBuilder(24);
            sb.append("Invalid unit ");
            sb.append(zza2);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    @RecentlyNullable
    public String getActivityName() {
        if (this.zzno.isEmpty() || this.zzno.size() > 1) {
            return null;
        }
        return zzko.getName(this.zzno.get(0).intValue());
    }

    @RecentlyNullable
    public Recurrence getRecurrence() {
        return this.zznp;
    }

    public int getObjectiveType() {
        return this.zznq;
    }

    private static String zze(int i) {
        if (i == 0) {
            return "unknown";
        }
        if (i == 1) {
            return "metric";
        }
        if (i == 2) {
            return "duration";
        }
        if (i == 3) {
            return "frequency";
        }
        throw new IllegalArgumentException("invalid objective type value");
    }

    @RecentlyNonNull
    public MetricObjective getMetricObjective() {
        zzf(1);
        return this.zznr;
    }

    @RecentlyNonNull
    public DurationObjective getDurationObjective() {
        zzf(2);
        return this.zzns;
    }

    @RecentlyNonNull
    public FrequencyObjective getFrequencyObjective() {
        zzf(3);
        return this.zznt;
    }

    @SafeParcelable.Constructor
    Goal(@SafeParcelable.Param(mo19517id = 1) long j, @SafeParcelable.Param(mo19517id = 2) long j2, @SafeParcelable.Param(mo19517id = 3) List<Integer> list, @SafeParcelable.Param(mo19517id = 4) Recurrence recurrence, @SafeParcelable.Param(mo19517id = 5) int i, @SafeParcelable.Param(mo19517id = 6) MetricObjective metricObjective, @SafeParcelable.Param(mo19517id = 7) DurationObjective durationObjective, @SafeParcelable.Param(mo19517id = 8) FrequencyObjective frequencyObjective) {
        this.zznm = j;
        this.zznn = j2;
        this.zzno = list;
        this.zznp = recurrence;
        this.zznq = i;
        this.zznr = metricObjective;
        this.zzns = durationObjective;
        this.zznt = frequencyObjective;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Goal)) {
            return false;
        }
        Goal goal = (Goal) obj;
        return this.zznm == goal.zznm && this.zznn == goal.zznn && Objects.equal(this.zzno, goal.zzno) && Objects.equal(this.zznp, goal.zznp) && this.zznq == goal.zznq && Objects.equal(this.zznr, goal.zznr) && Objects.equal(this.zzns, goal.zzns) && Objects.equal(this.zznt, goal.zznt);
    }

    public int hashCode() {
        return this.zznq;
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("activity", getActivityName()).add("recurrence", this.zznp).add("metricObjective", this.zznr).add("durationObjective", this.zzns).add("frequencyObjective", this.zznt).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zznm);
        SafeParcelWriter.writeLong(parcel, 2, this.zznn);
        SafeParcelWriter.writeList(parcel, 3, this.zzno, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getRecurrence(), i, false);
        SafeParcelWriter.writeInt(parcel, 5, getObjectiveType());
        SafeParcelWriter.writeParcelable(parcel, 6, this.zznr, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzns, i, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zznt, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    private final void zzf(int i) {
        int i2 = this.zznq;
        if (i != i2) {
            throw new MismatchedGoalException(String.format("%s goal does not have %s objective", new Object[]{zze(i2), zze(i)}));
        }
    }
}
