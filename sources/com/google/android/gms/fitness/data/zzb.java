package com.google.android.gms.fitness.data;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzb {
    @VisibleForTesting
    public static final Map<DataType, DataType> zzlc;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(DataType.TYPE_ACTIVITY_SEGMENT, DataType.AGGREGATE_ACTIVITY_SUMMARY);
        hashMap.put(HealthDataTypes.TYPE_BASAL_BODY_TEMPERATURE, HealthDataTypes.AGGREGATE_BASAL_BODY_TEMPERATURE_SUMMARY);
        hashMap.put(DataType.TYPE_BASAL_METABOLIC_RATE, DataType.AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY);
        hashMap.put(HealthDataTypes.TYPE_BLOOD_GLUCOSE, HealthDataTypes.AGGREGATE_BLOOD_GLUCOSE_SUMMARY);
        hashMap.put(HealthDataTypes.TYPE_BLOOD_PRESSURE, HealthDataTypes.AGGREGATE_BLOOD_PRESSURE_SUMMARY);
        hashMap.put(DataType.TYPE_BODY_FAT_PERCENTAGE, DataType.AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY);
        hashMap.put(HealthDataTypes.TYPE_BODY_TEMPERATURE, HealthDataTypes.AGGREGATE_BODY_TEMPERATURE_SUMMARY);
        hashMap.put(DataType.TYPE_CALORIES_EXPENDED, DataType.AGGREGATE_CALORIES_EXPENDED);
        hashMap.put(HealthDataTypes.TYPE_CERVICAL_MUCUS, HealthDataTypes.TYPE_CERVICAL_MUCUS);
        hashMap.put(HealthDataTypes.TYPE_CERVICAL_POSITION, HealthDataTypes.TYPE_CERVICAL_POSITION);
        hashMap.put(DataType.TYPE_DISTANCE_DELTA, DataType.AGGREGATE_DISTANCE_DELTA);
        hashMap.put(DataType.TYPE_HEART_POINTS, DataType.AGGREGATE_HEART_POINTS);
        hashMap.put(DataType.TYPE_HEART_RATE_BPM, DataType.AGGREGATE_HEART_RATE_SUMMARY);
        hashMap.put(DataType.TYPE_HEIGHT, DataType.AGGREGATE_HEIGHT_SUMMARY);
        hashMap.put(DataType.TYPE_HYDRATION, DataType.AGGREGATE_HYDRATION);
        hashMap.put(DataType.TYPE_LOCATION_SAMPLE, DataType.AGGREGATE_LOCATION_BOUNDING_BOX);
        hashMap.put(HealthDataTypes.TYPE_MENSTRUATION, HealthDataTypes.TYPE_MENSTRUATION);
        hashMap.put(DataType.TYPE_MOVE_MINUTES, DataType.AGGREGATE_MOVE_MINUTES);
        hashMap.put(DataType.TYPE_NUTRITION, DataType.AGGREGATE_NUTRITION_SUMMARY);
        hashMap.put(HealthDataTypes.TYPE_OVULATION_TEST, HealthDataTypes.TYPE_OVULATION_TEST);
        hashMap.put(HealthDataTypes.TYPE_OXYGEN_SATURATION, HealthDataTypes.AGGREGATE_OXYGEN_SATURATION_SUMMARY);
        hashMap.put(DataType.TYPE_POWER_SAMPLE, DataType.AGGREGATE_POWER_SUMMARY);
        hashMap.put(DataType.TYPE_SPEED, DataType.AGGREGATE_SPEED_SUMMARY);
        hashMap.put(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA);
        hashMap.put(HealthDataTypes.TYPE_VAGINAL_SPOTTING, HealthDataTypes.TYPE_VAGINAL_SPOTTING);
        hashMap.put(DataType.TYPE_WEIGHT, DataType.AGGREGATE_WEIGHT_SUMMARY);
        zzlc = Collections.unmodifiableMap(hashMap);
    }
}
