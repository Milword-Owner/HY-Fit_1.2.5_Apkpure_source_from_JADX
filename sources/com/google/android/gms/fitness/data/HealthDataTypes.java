package com.google.android.gms.fitness.data;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.Scopes;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class HealthDataTypes {
    @RecentlyNonNull
    @Deprecated
    public static final DataType AGGREGATE_BASAL_BODY_TEMPERATURE_SUMMARY = new DataType("com.google.body.temperature.basal.summary", 2, Scopes.FITNESS_REPRODUCTIVE_HEALTH_READ, Scopes.FITNESS_REPRODUCTIVE_HEALTH_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN, HealthFields.FIELD_BODY_TEMPERATURE_MEASUREMENT_LOCATION);
    @RecentlyNonNull
    public static final DataType AGGREGATE_BLOOD_GLUCOSE_SUMMARY = new DataType("com.google.blood_glucose.summary", 2, Scopes.FITNESS_BLOOD_GLUCOSE_READ, Scopes.FITNESS_BLOOD_GLUCOSE_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN, HealthFields.FIELD_TEMPORAL_RELATION_TO_MEAL, Field.FIELD_MEAL_TYPE, HealthFields.FIELD_TEMPORAL_RELATION_TO_SLEEP, HealthFields.FIELD_BLOOD_GLUCOSE_SPECIMEN_SOURCE);
    @RecentlyNonNull
    public static final DataType AGGREGATE_BLOOD_PRESSURE_SUMMARY = new DataType("com.google.blood_pressure.summary", 2, Scopes.FITNESS_BLOOD_PRESSURE_READ, Scopes.FITNESS_BLOOD_PRESSURE_READ_WRITE, HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_AVERAGE, HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_MAX, HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_MIN, HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_AVERAGE, HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_MAX, HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_MIN, HealthFields.FIELD_BODY_POSITION, HealthFields.FIELD_BLOOD_PRESSURE_MEASUREMENT_LOCATION);
    @RecentlyNonNull
    public static final DataType AGGREGATE_BODY_TEMPERATURE_SUMMARY = new DataType("com.google.body.temperature.summary", 2, Scopes.FITNESS_BODY_TEMPERATURE_READ, Scopes.FITNESS_BODY_TEMPERATURE_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN, HealthFields.FIELD_BODY_TEMPERATURE_MEASUREMENT_LOCATION);
    @RecentlyNonNull
    public static final DataType AGGREGATE_OXYGEN_SATURATION_SUMMARY = new DataType("com.google.oxygen_saturation.summary", 2, Scopes.FITNESS_OXYGEN_SATURATION_READ, Scopes.FITNESS_OXYGEN_SATURATION_READ_WRITE, HealthFields.FIELD_OXYGEN_SATURATION_AVERAGE, HealthFields.FIELD_OXYGEN_SATURATION_MAX, HealthFields.FIELD_OXYGEN_SATURATION_MIN, HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_AVERAGE, HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_MAX, HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_MIN, HealthFields.FIELD_OXYGEN_THERAPY_ADMINISTRATION_MODE, HealthFields.FIELD_OXYGEN_SATURATION_SYSTEM, HealthFields.FIELD_OXYGEN_SATURATION_MEASUREMENT_METHOD);
    @RecentlyNonNull
    @Deprecated
    public static final DataType TYPE_BASAL_BODY_TEMPERATURE = new DataType("com.google.body.temperature.basal", 1, Scopes.FITNESS_REPRODUCTIVE_HEALTH_READ, Scopes.FITNESS_REPRODUCTIVE_HEALTH_READ_WRITE, HealthFields.FIELD_BODY_TEMPERATURE, HealthFields.FIELD_BODY_TEMPERATURE_MEASUREMENT_LOCATION);
    @RecentlyNonNull
    public static final DataType TYPE_BLOOD_GLUCOSE = new DataType("com.google.blood_glucose", 1, Scopes.FITNESS_BLOOD_GLUCOSE_READ, Scopes.FITNESS_BLOOD_GLUCOSE_READ_WRITE, HealthFields.FIELD_BLOOD_GLUCOSE_LEVEL, HealthFields.FIELD_TEMPORAL_RELATION_TO_MEAL, Field.FIELD_MEAL_TYPE, HealthFields.FIELD_TEMPORAL_RELATION_TO_SLEEP, HealthFields.FIELD_BLOOD_GLUCOSE_SPECIMEN_SOURCE);
    @RecentlyNonNull
    public static final DataType TYPE_BLOOD_PRESSURE = new DataType("com.google.blood_pressure", 1, Scopes.FITNESS_BLOOD_PRESSURE_READ, Scopes.FITNESS_BLOOD_PRESSURE_READ_WRITE, HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC, HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC, HealthFields.FIELD_BODY_POSITION, HealthFields.FIELD_BLOOD_PRESSURE_MEASUREMENT_LOCATION);
    @RecentlyNonNull
    public static final DataType TYPE_BODY_TEMPERATURE = new DataType("com.google.body.temperature", 1, Scopes.FITNESS_BODY_TEMPERATURE_READ, Scopes.FITNESS_BODY_TEMPERATURE_READ_WRITE, HealthFields.FIELD_BODY_TEMPERATURE, HealthFields.FIELD_BODY_TEMPERATURE_MEASUREMENT_LOCATION);
    @RecentlyNonNull
    public static final DataType TYPE_CERVICAL_MUCUS = new DataType("com.google.cervical_mucus", 1, Scopes.FITNESS_REPRODUCTIVE_HEALTH_READ, Scopes.FITNESS_REPRODUCTIVE_HEALTH_READ_WRITE, HealthFields.FIELD_CERVICAL_MUCUS_TEXTURE, HealthFields.FIELD_CERVICAL_MUCUS_AMOUNT);
    @RecentlyNonNull
    public static final DataType TYPE_CERVICAL_POSITION = new DataType("com.google.cervical_position", 1, Scopes.FITNESS_REPRODUCTIVE_HEALTH_READ, Scopes.FITNESS_REPRODUCTIVE_HEALTH_READ_WRITE, HealthFields.FIELD_CERVICAL_POSITION, HealthFields.FIELD_CERVICAL_DILATION, HealthFields.FIELD_CERVICAL_FIRMNESS);
    @RecentlyNonNull
    public static final DataType TYPE_MENSTRUATION = new DataType("com.google.menstruation", 1, Scopes.FITNESS_REPRODUCTIVE_HEALTH_READ, Scopes.FITNESS_REPRODUCTIVE_HEALTH_READ_WRITE, HealthFields.FIELD_MENSTRUAL_FLOW);
    @RecentlyNonNull
    public static final DataType TYPE_OVULATION_TEST = new DataType("com.google.ovulation_test", 1, Scopes.FITNESS_REPRODUCTIVE_HEALTH_READ, Scopes.FITNESS_REPRODUCTIVE_HEALTH_READ_WRITE, HealthFields.FIELD_OVULATION_TEST_RESULT);
    @RecentlyNonNull
    public static final DataType TYPE_OXYGEN_SATURATION = new DataType("com.google.oxygen_saturation", 1, Scopes.FITNESS_OXYGEN_SATURATION_READ, Scopes.FITNESS_OXYGEN_SATURATION_READ_WRITE, HealthFields.FIELD_OXYGEN_SATURATION, HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE, HealthFields.FIELD_OXYGEN_THERAPY_ADMINISTRATION_MODE, HealthFields.FIELD_OXYGEN_SATURATION_SYSTEM, HealthFields.FIELD_OXYGEN_SATURATION_MEASUREMENT_METHOD);
    @RecentlyNonNull
    public static final DataType TYPE_VAGINAL_SPOTTING = new DataType("com.google.vaginal_spotting", 1, Scopes.FITNESS_REPRODUCTIVE_HEALTH_READ, Scopes.FITNESS_REPRODUCTIVE_HEALTH_READ_WRITE, Field.FIELD_OCCURRENCES);

    private HealthDataTypes() {
    }
}
