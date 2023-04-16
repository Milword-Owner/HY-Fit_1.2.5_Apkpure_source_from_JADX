package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "DataTypeCreator")
@SafeParcelable.Reserved({1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class DataType extends AbstractSafeParcelable implements ReflectedParcelable {
    @RecentlyNonNull
    public static final DataType AGGREGATE_ACTIVITY_SUMMARY = new DataType("com.google.activity.summary", 2, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY, Field.FIELD_DURATION, Field.FIELD_NUM_SEGMENTS);
    @RecentlyNonNull
    public static final DataType AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY = new DataType("com.google.calories.bmr.summary", 2, Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    @RecentlyNonNull
    public static final DataType AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY = new DataType("com.google.body.fat.percentage.summary", 2, Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    @RecentlyNonNull
    public static final DataType AGGREGATE_CALORIES_EXPENDED = TYPE_CALORIES_EXPENDED;
    @RecentlyNonNull
    public static final DataType AGGREGATE_DISTANCE_DELTA = TYPE_DISTANCE_DELTA;
    @RecentlyNonNull
    public static final DataType AGGREGATE_HEART_POINTS = new DataType("com.google.heart_minutes.summary", 2, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_INTENSITY, Field.FIELD_DURATION);
    @RecentlyNonNull
    public static final DataType AGGREGATE_HEART_RATE_SUMMARY = new DataType("com.google.heart_rate.summary", 2, "https://www.googleapis.com/auth/fitness.heart_rate.read", "https://www.googleapis.com/auth/fitness.heart_rate.write", Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    @RecentlyNonNull
    public static final DataType AGGREGATE_HEIGHT_SUMMARY = new DataType("com.google.height.summary", 2, Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    @RecentlyNonNull
    public static final DataType AGGREGATE_HYDRATION = TYPE_HYDRATION;
    @RecentlyNonNull
    public static final DataType AGGREGATE_LOCATION_BOUNDING_BOX = new DataType("com.google.location.bounding_box", 2, Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_LOW_LATITUDE, Field.FIELD_LOW_LONGITUDE, Field.FIELD_HIGH_LATITUDE, Field.FIELD_HIGH_LONGITUDE);
    @RecentlyNonNull
    public static final DataType AGGREGATE_MOVE_MINUTES;
    @RecentlyNonNull
    public static final DataType AGGREGATE_NUTRITION_SUMMARY = new DataType("com.google.nutrition.summary", 2, Scopes.FITNESS_NUTRITION_READ, Scopes.FITNESS_NUTRITION_READ_WRITE, Field.FIELD_NUTRIENTS, Field.FIELD_MEAL_TYPE);
    @RecentlyNonNull
    public static final DataType AGGREGATE_POWER_SUMMARY = new DataType("com.google.power.summary", 2, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    @RecentlyNonNull
    public static final DataType AGGREGATE_SPEED_SUMMARY = new DataType("com.google.speed.summary", 2, Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    @RecentlyNonNull
    public static final DataType AGGREGATE_STEP_COUNT_DELTA = TYPE_STEP_COUNT_DELTA;
    @RecentlyNonNull
    public static final DataType AGGREGATE_WEIGHT_SUMMARY = new DataType("com.google.weight.summary", 2, Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    @RecentlyNonNull
    public static final Parcelable.Creator<DataType> CREATOR = new zzm();
    @RecentlyNonNull
    public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.data_type/";
    @RecentlyNonNull
    public static final DataType TYPE_ACTIVITY_SEGMENT = new DataType("com.google.activity.segment", 2, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY);
    @RecentlyNonNull
    public static final DataType TYPE_BASAL_METABOLIC_RATE = new DataType("com.google.calories.bmr", 1, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_CALORIES);
    @RecentlyNonNull
    public static final DataType TYPE_BODY_FAT_PERCENTAGE = new DataType("com.google.body.fat.percentage", 1, Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_PERCENTAGE);
    @RecentlyNonNull
    public static final DataType TYPE_CALORIES_EXPENDED = new DataType("com.google.calories.expended", 2, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_CALORIES);
    @RecentlyNonNull
    public static final DataType TYPE_CYCLING_PEDALING_CADENCE = new DataType("com.google.cycling.pedaling.cadence", 1, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_RPM);
    @RecentlyNonNull
    public static final DataType TYPE_CYCLING_PEDALING_CUMULATIVE = new DataType("com.google.cycling.pedaling.cumulative", 1, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_REVOLUTIONS);
    @RecentlyNonNull
    public static final DataType TYPE_CYCLING_WHEEL_REVOLUTION = new DataType("com.google.cycling.wheel_revolution.cumulative", 1, Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_REVOLUTIONS);
    @RecentlyNonNull
    public static final DataType TYPE_CYCLING_WHEEL_RPM = new DataType("com.google.cycling.wheel_revolution.rpm", 1, Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_RPM);
    @RecentlyNonNull
    public static final DataType TYPE_DISTANCE_DELTA = new DataType("com.google.distance.delta", 2, Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_DISTANCE);
    @RecentlyNonNull
    public static final DataType TYPE_HEART_POINTS = new DataType("com.google.heart_minutes", 2, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_INTENSITY);
    @RecentlyNonNull
    public static final DataType TYPE_HEART_RATE_BPM = new DataType("com.google.heart_rate.bpm", 1, "https://www.googleapis.com/auth/fitness.heart_rate.read", "https://www.googleapis.com/auth/fitness.heart_rate.write", Field.FIELD_BPM);
    @RecentlyNonNull
    public static final DataType TYPE_HEIGHT = new DataType("com.google.height", 1, Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_HEIGHT);
    @RecentlyNonNull
    public static final DataType TYPE_HYDRATION = new DataType("com.google.hydration", 1, Scopes.FITNESS_NUTRITION_READ, Scopes.FITNESS_NUTRITION_READ_WRITE, Field.FIELD_VOLUME);
    @RecentlyNonNull
    public static final DataType TYPE_LOCATION_SAMPLE = new DataType("com.google.location.sample", 1, Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_ACCURACY, Field.FIELD_ALTITUDE);
    @RecentlyNonNull
    @Deprecated
    public static final DataType TYPE_LOCATION_TRACK = new DataType("com.google.location.track", 2, Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_ACCURACY, Field.FIELD_ALTITUDE);
    @RecentlyNonNull
    public static final DataType TYPE_MOVE_MINUTES;
    @RecentlyNonNull
    public static final DataType TYPE_NUTRITION = new DataType("com.google.nutrition", 1, Scopes.FITNESS_NUTRITION_READ, Scopes.FITNESS_NUTRITION_READ_WRITE, Field.FIELD_NUTRIENTS, Field.FIELD_MEAL_TYPE, Field.FIELD_FOOD_ITEM);
    @RecentlyNonNull
    public static final DataType TYPE_POWER_SAMPLE = new DataType("com.google.power.sample", 1, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_WATTS);
    @RecentlyNonNull
    public static final DataType TYPE_SLEEP_SEGMENT = new DataType("com.google.sleep.segment", 2, "https://www.googleapis.com/auth/fitness.sleep.read", "https://www.googleapis.com/auth/fitness.sleep.write", Field.FIELD_SLEEP_SEGMENT_TYPE);
    @RecentlyNonNull
    public static final DataType TYPE_SPEED = new DataType("com.google.speed", 1, Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_SPEED);
    @RecentlyNonNull
    public static final DataType TYPE_STEP_COUNT_CADENCE = new DataType("com.google.step_count.cadence", 1, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_RPM);
    @RecentlyNonNull
    @KeepName
    @ShowFirstParty
    public static final DataType TYPE_STEP_COUNT_CUMULATIVE = new DataType("com.google.step_count.cumulative", 1, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_STEPS);
    @RecentlyNonNull
    public static final DataType TYPE_STEP_COUNT_DELTA = new DataType("com.google.step_count.delta", 2, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_STEPS);
    @RecentlyNonNull
    public static final DataType TYPE_WEIGHT = new DataType("com.google.weight", 1, Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_WEIGHT);
    @RecentlyNonNull
    public static final DataType TYPE_WORKOUT_EXERCISE = new DataType("com.google.activity.exercise", 1, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_EXERCISE, Field.FIELD_REPETITIONS, Field.zzmz, Field.FIELD_RESISTANCE_TYPE, Field.FIELD_RESISTANCE);
    @RecentlyNonNull
    @ShowFirstParty
    public static final DataType zzmd = new DataType("com.google.internal.goal", 2, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zznd);
    @RecentlyNonNull
    @ShowFirstParty
    public static final DataType zzme = new DataType("com.google.sensor.events", 2, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zznf, Field.zzng, Field.zznh);
    @RecentlyNonNull
    @ShowFirstParty
    public static final DataType zzmf = new DataType("com.google.respiratory_rate", 1, "https://www.googleapis.com/auth/fitness.respiratory_rate.read", "https://www.googleapis.com/auth/fitness.respiratory_rate.write", Field.zznc);
    @RecentlyNonNull
    public static final DataType zzmg = new DataType("com.google.distance.cumulative", 1, Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_DISTANCE);
    @RecentlyNonNull
    @ShowFirstParty
    public static final DataType zzmh = new DataType("com.google.device_on_body", 1, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zznj);
    @RecentlyNonNull
    @ShowFirstParty
    public static final DataType zzmi = new DataType("com.google.internal.primary_device", 2, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zzne);
    @RecentlyNonNull
    @ShowFirstParty
    public static final DataType zzmj = new DataType("com.google.activity.samples", 1, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zzni);
    @RecentlyNonNull
    @Deprecated
    public static final DataType zzmk;
    @RecentlyNonNull
    @Deprecated
    public static final DataType zzml;
    @RecentlyNonNull
    @ShowFirstParty
    public static final DataType zzmm = new DataType("com.google.internal.sleep_attributes", 2, "https://www.googleapis.com/auth/fitness.sleep.read", "https://www.googleapis.com/auth/fitness.sleep.write", Field.zznk);
    @RecentlyNonNull
    @ShowFirstParty
    public static final DataType zzmn = new DataType("com.google.internal.sleep_schedule", 1, "https://www.googleapis.com/auth/fitness.sleep.read", "https://www.googleapis.com/auth/fitness.sleep.write", Field.zznl);
    @SafeParcelable.Field(getter = "getName", mo19514id = 1)
    private final String name;
    @SafeParcelable.Field(getter = "getFields", mo19514id = 2)
    private final List<Field> zzlz;
    @SafeParcelable.Field(getter = "getReadScope", mo19514id = 3)
    @Nullable
    private final String zzma;
    @SafeParcelable.Field(getter = "getWriteScope", mo19514id = 4)
    @Nullable
    private final String zzmb;
    private final int zzmc;

    @ShowFirstParty
    public DataType(@RecentlyNonNull String str, int i, @Nullable String str2, @Nullable String str3, @RecentlyNonNull Field... fieldArr) {
        this.name = str;
        this.zzlz = Collections.unmodifiableList(Arrays.asList(fieldArr));
        this.zzma = str2;
        this.zzmb = str3;
        this.zzmc = i;
    }

    @SafeParcelable.Constructor
    DataType(@SafeParcelable.Param(mo19517id = 1) String str, @SafeParcelable.Param(mo19517id = 2) List<Field> list, @SafeParcelable.Param(mo19517id = 3) @Nullable String str2, @SafeParcelable.Param(mo19517id = 4) @Nullable String str3) {
        this.name = str;
        this.zzlz = Collections.unmodifiableList(list);
        this.zzma = str2;
        this.zzmb = str3;
        this.zzmc = 0;
    }

    @RecentlyNonNull
    public static String getMimeType(@RecentlyNonNull DataType dataType) {
        String valueOf = String.valueOf(dataType.getName());
        return valueOf.length() != 0 ? MIME_TYPE_PREFIX.concat(valueOf) : new String(MIME_TYPE_PREFIX);
    }

    @RecentlyNonNull
    public final String getName() {
        return this.name;
    }

    @RecentlyNonNull
    public final List<Field> getFields() {
        return this.zzlz;
    }

    @ShowFirstParty
    @RecentlyNullable
    public final String zzk() {
        return this.zzma;
    }

    @ShowFirstParty
    @RecentlyNullable
    public final String zzl() {
        return this.zzmb;
    }

    public final int indexOf(@RecentlyNonNull Field field) {
        int indexOf = this.zzlz.indexOf(field);
        Preconditions.checkArgument(indexOf >= 0, "%s not a field of %s", field, this);
        return indexOf;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataType)) {
            return false;
        }
        DataType dataType = (DataType) obj;
        return this.name.equals(dataType.name) && this.zzlz.equals(dataType.zzlz);
    }

    public final int hashCode() {
        return this.name.hashCode();
    }

    @RecentlyNonNull
    public final String toString() {
        return String.format("DataType{%s%s}", new Object[]{this.name, this.zzlz});
    }

    @RecentlyNonNull
    @ShowFirstParty
    public final String zzm() {
        return this.name.startsWith("com.google.") ? this.name.substring(11) : this.name;
    }

    public final void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, getFields(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzma, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzmb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @RecentlyNonNull
    @Deprecated
    public static List<DataType> getAggregatesForInput(@RecentlyNonNull DataType dataType) {
        DataType aggregateType = dataType.getAggregateType();
        if (aggregateType == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(aggregateType);
    }

    @RecentlyNullable
    public final DataType getAggregateType() {
        return zzb.zzlc.get(this);
    }

    static {
        DataType dataType = new DataType("com.google.active_minutes", 2, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_DURATION);
        TYPE_MOVE_MINUTES = dataType;
        AGGREGATE_MOVE_MINUTES = dataType;
        DataType dataType2 = new DataType("com.google.calories.consumed", 2, Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_CALORIES);
        zzmk = dataType2;
        zzml = dataType2;
    }
}
