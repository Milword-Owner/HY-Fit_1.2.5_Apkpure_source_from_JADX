package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "FieldCreator")
@SafeParcelable.Reserved({1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class Field extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<Field> CREATOR = new zzr();
    @RecentlyNonNull
    public static final Field FIELD_ACCURACY = zze("accuracy");
    @RecentlyNonNull
    public static final Field FIELD_ACTIVITY = zzc("activity");
    @RecentlyNonNull
    public static final Field FIELD_ALTITUDE = zzf("altitude");
    @RecentlyNonNull
    public static final Field FIELD_AVERAGE = zze("average");
    @RecentlyNonNull
    public static final Field FIELD_BPM = zze("bpm");
    @RecentlyNonNull
    public static final Field FIELD_CALORIES = zze(NUTRIENT_CALORIES);
    @RecentlyNonNull
    @Deprecated
    public static final Field FIELD_CIRCUMFERENCE = zze("circumference");
    @RecentlyNonNull
    public static final Field FIELD_CONFIDENCE = zze("confidence");
    @RecentlyNonNull
    public static final Field FIELD_DISTANCE = zze("distance");
    @RecentlyNonNull
    public static final Field FIELD_DURATION = zzc("duration");
    @RecentlyNonNull
    public static final Field FIELD_EXERCISE = new Field("exercise", 3);
    @RecentlyNonNull
    public static final Field FIELD_FOOD_ITEM = new Field("food_item", 3, true);
    @RecentlyNonNull
    public static final Field FIELD_HEIGHT = zze(ViewHierarchyConstants.DIMENSION_HEIGHT_KEY);
    @RecentlyNonNull
    public static final Field FIELD_HIGH_LATITUDE = zze("high_latitude");
    @RecentlyNonNull
    public static final Field FIELD_HIGH_LONGITUDE = zze("high_longitude");
    @RecentlyNonNull
    public static final Field FIELD_INTENSITY = zze("intensity");
    @RecentlyNonNull
    public static final Field FIELD_LATITUDE = zze("latitude");
    @RecentlyNonNull
    public static final Field FIELD_LONGITUDE = zze("longitude");
    @RecentlyNonNull
    public static final Field FIELD_LOW_LATITUDE = zze("low_latitude");
    @RecentlyNonNull
    public static final Field FIELD_LOW_LONGITUDE = zze("low_longitude");
    @RecentlyNonNull
    public static final Field FIELD_MAX = zze("max");
    @RecentlyNonNull
    public static final Field FIELD_MEAL_TYPE = zzd("meal_type");
    @RecentlyNonNull
    public static final Field FIELD_MIN = zze("min");
    @RecentlyNonNull
    public static final Field FIELD_NUM_SEGMENTS = zzc("num_segments");
    @RecentlyNonNull
    public static final Field FIELD_NUTRIENTS = zzg("nutrients");
    @RecentlyNonNull
    public static final Field FIELD_OCCURRENCES = zzc("occurrences");
    @RecentlyNonNull
    public static final Field FIELD_PERCENTAGE = zze("percentage");
    @RecentlyNonNull
    public static final Field FIELD_REPETITIONS = zzd("repetitions");
    @RecentlyNonNull
    public static final Field FIELD_RESISTANCE = zzf("resistance");
    @RecentlyNonNull
    public static final Field FIELD_RESISTANCE_TYPE = zzd("resistance_type");
    @RecentlyNonNull
    public static final Field FIELD_REVOLUTIONS = zzc("revolutions");
    @RecentlyNonNull
    public static final Field FIELD_RPM = zze("rpm");
    @RecentlyNonNull
    public static final Field FIELD_SLEEP_SEGMENT_TYPE = zzc("sleep_segment_type");
    @RecentlyNonNull
    public static final Field FIELD_SPEED = zze("speed");
    @RecentlyNonNull
    public static final Field FIELD_STEPS = zzc("steps");
    @RecentlyNonNull
    @Deprecated
    public static final Field FIELD_STEP_LENGTH = zze("step_length");
    @RecentlyNonNull
    public static final Field FIELD_VOLUME = zze("volume");
    @RecentlyNonNull
    public static final Field FIELD_WATTS = zze("watts");
    @RecentlyNonNull
    public static final Field FIELD_WEIGHT = zze("weight");
    public static final int FORMAT_FLOAT = 2;
    public static final int FORMAT_INT32 = 1;
    public static final int FORMAT_MAP = 4;
    public static final int FORMAT_STRING = 3;
    public static final int MEAL_TYPE_BREAKFAST = 1;
    public static final int MEAL_TYPE_DINNER = 3;
    public static final int MEAL_TYPE_LUNCH = 2;
    public static final int MEAL_TYPE_SNACK = 4;
    public static final int MEAL_TYPE_UNKNOWN = 0;
    @RecentlyNonNull
    public static final String NUTRIENT_CALCIUM = "calcium";
    @RecentlyNonNull
    public static final String NUTRIENT_CALORIES = "calories";
    @RecentlyNonNull
    public static final String NUTRIENT_CHOLESTEROL = "cholesterol";
    @RecentlyNonNull
    public static final String NUTRIENT_DIETARY_FIBER = "dietary_fiber";
    @RecentlyNonNull
    public static final String NUTRIENT_IRON = "iron";
    @RecentlyNonNull
    public static final String NUTRIENT_MONOUNSATURATED_FAT = "fat.monounsaturated";
    @RecentlyNonNull
    public static final String NUTRIENT_POLYUNSATURATED_FAT = "fat.polyunsaturated";
    @RecentlyNonNull
    public static final String NUTRIENT_POTASSIUM = "potassium";
    @RecentlyNonNull
    public static final String NUTRIENT_PROTEIN = "protein";
    @RecentlyNonNull
    public static final String NUTRIENT_SATURATED_FAT = "fat.saturated";
    @RecentlyNonNull
    public static final String NUTRIENT_SODIUM = "sodium";
    @RecentlyNonNull
    public static final String NUTRIENT_SUGAR = "sugar";
    @RecentlyNonNull
    public static final String NUTRIENT_TOTAL_CARBS = "carbs.total";
    @RecentlyNonNull
    public static final String NUTRIENT_TOTAL_FAT = "fat.total";
    @RecentlyNonNull
    public static final String NUTRIENT_TRANS_FAT = "fat.trans";
    @RecentlyNonNull
    public static final String NUTRIENT_UNSATURATED_FAT = "fat.unsaturated";
    @RecentlyNonNull
    public static final String NUTRIENT_VITAMIN_A = "vitamin_a";
    @RecentlyNonNull
    public static final String NUTRIENT_VITAMIN_C = "vitamin_c";
    public static final int RESISTANCE_TYPE_BARBELL = 1;
    public static final int RESISTANCE_TYPE_BODY = 6;
    public static final int RESISTANCE_TYPE_CABLE = 2;
    public static final int RESISTANCE_TYPE_DUMBBELL = 3;
    public static final int RESISTANCE_TYPE_KETTLEBELL = 4;
    public static final int RESISTANCE_TYPE_MACHINE = 5;
    public static final int RESISTANCE_TYPE_UNKNOWN = 0;
    @RecentlyNonNull
    @ShowFirstParty
    public static final Field zzmz = zzd("duration");
    @ShowFirstParty
    private static final Field zzna = zzg("activity_duration.ascending");
    @ShowFirstParty
    private static final Field zznb = zzg("activity_duration.descending");
    @RecentlyNonNull
    @ShowFirstParty
    public static final Field zznc = zze("respiratory_rate");
    @RecentlyNonNull
    @ShowFirstParty
    public static final Field zznd = zzh("google.android.fitness.GoalV2");
    @RecentlyNonNull
    @ShowFirstParty
    public static final Field zzne = zzh("google.android.fitness.Device");
    @RecentlyNonNull
    @ShowFirstParty
    public static final Field zznf = zzc("sensor_type");
    @RecentlyNonNull
    @ShowFirstParty
    public static final Field zzng = new Field("timestamps", 5);
    @RecentlyNonNull
    @ShowFirstParty
    public static final Field zznh = new Field("sensor_values", 6);
    @RecentlyNonNull
    @ShowFirstParty
    public static final Field zzni = zzg("activity_confidence");
    @RecentlyNonNull
    @ShowFirstParty
    public static final Field zznj = zze("probability");
    @RecentlyNonNull
    @ShowFirstParty
    public static final Field zznk = zzh("google.android.fitness.SleepAttributes");
    @RecentlyNonNull
    @ShowFirstParty
    public static final Field zznl = zzh("google.android.fitness.SleepSchedule");
    @SafeParcelable.Field(getter = "getFormat", mo19514id = 2)
    private final int format;
    @SafeParcelable.Field(getter = "getName", mo19514id = 1)
    private final String name;
    @SafeParcelable.Field(getter = "isOptional", mo19514id = 3)
    @Nullable
    private final Boolean zzmy;

    @ShowFirstParty
    private static Field zzc(String str) {
        return new Field(str, 1);
    }

    @RecentlyNonNull
    @ShowFirstParty
    public static Field zzd(@RecentlyNonNull String str) {
        return new Field(str, 1, true);
    }

    @RecentlyNonNull
    @ShowFirstParty
    public static Field zze(@RecentlyNonNull String str) {
        return new Field(str, 2);
    }

    @ShowFirstParty
    private static Field zzf(String str) {
        return new Field(str, 2, true);
    }

    @ShowFirstParty
    private static Field zzg(String str) {
        return new Field(str, 4);
    }

    @ShowFirstParty
    private static Field zzh(String str) {
        return new Field(str, 7);
    }

    @ShowFirstParty
    public Field(@RecentlyNonNull String str, int i) {
        this(str, i, (Boolean) null);
    }

    @ShowFirstParty
    @SafeParcelable.Constructor
    public Field(@RecentlyNonNull @SafeParcelable.Param(mo19517id = 1) String str, @SafeParcelable.Param(mo19517id = 2) int i, @SafeParcelable.Param(mo19517id = 3) @Nullable Boolean bool) {
        this.name = (String) Preconditions.checkNotNull(str);
        this.format = i;
        this.zzmy = bool;
    }

    @RecentlyNonNull
    public final String getName() {
        return this.name;
    }

    public final int getFormat() {
        return this.format;
    }

    @RecentlyNullable
    public final Boolean isOptional() {
        return this.zzmy;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Field)) {
            return false;
        }
        Field field = (Field) obj;
        return this.name.equals(field.name) && this.format == field.format;
    }

    public final int hashCode() {
        return this.name.hashCode();
    }

    @RecentlyNonNull
    public final String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = this.name;
        objArr[1] = this.format == 1 ? "i" : "f";
        return String.format("%s(%s)", objArr);
    }

    public final void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeInt(parcel, 2, getFormat());
        SafeParcelWriter.writeBooleanObject(parcel, 3, isOptional(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
