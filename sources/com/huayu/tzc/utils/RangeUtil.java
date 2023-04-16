package com.huayu.tzc.utils;

public class RangeUtil {
    public static final float[] RANGE_DBZ = {5.0f, 32.0f};
    public static final float[] RANGE_JCDX = {400.0f, 3500.0f};
    public static final float[] RANGE_NZ = {1.0f, 59.0f};
    public static final float[] RANGE_TS = {20.0f, 85.0f};
    public static final float[] RANGE_TZ = {5.0f, 45.0f};

    public static float getQzRange(float f, float f2) {
        return f - ((f2 * f) / 100.0f);
    }

    public static float[] getWeightArray(int i, float f) {
        float f2;
        float f3;
        if (i == 1) {
            f2 = f - 70.0f;
            f3 = 0.6f;
        } else {
            f2 = f - 80.0f;
            f3 = 0.7f;
        }
        float f4 = f2 * f3;
        return new float[]{UnitUtils.oneF(0.8f * f4), UnitUtils.oneF(0.9f * f4), UnitUtils.oneF(1.1f * f4), UnitUtils.oneF(f4 * 1.2f)};
    }

    public static float getRange(float[] fArr, float f) {
        if (f < fArr[0]) {
            return fArr[0];
        }
        return f > fArr[1] ? fArr[1] : f;
    }

    public static float[] getJRRange(int i, float f) {
        if (i == 1) {
            if (f < 150.0f) {
                return new float[]{21.9f, 34.7f};
            }
            if (f <= 160.0f) {
                return new float[]{32.9f, 37.5f};
            }
            return new float[]{36.5f, 42.5f};
        } else if (f < 160.0f) {
            return new float[]{38.5f, 46.5f};
        } else {
            if (f <= 170.0f) {
                return new float[]{44.0f, 52.4f};
            }
            return new float[]{49.4f, 59.4f};
        }
    }

    public static float[] getFatRangFalse(int i, int i2) {
        if (i == 1) {
            if (i2 <= 39) {
                return new float[]{20.0f, 34.0f, 39.0f};
            }
            if (i2 <= 59) {
                return new float[]{21.0f, 35.0f, 40.0f};
            }
            return new float[]{22.0f, 36.0f, 41.0f};
        } else if (i2 <= 39) {
            return new float[]{10.0f, 21.0f, 26.0f};
        } else {
            if (i2 <= 59) {
                return new float[]{11.0f, 22.0f, 29.0f};
            }
            return new float[]{13.0f, 24.0f, 29.0f};
        }
    }

    public static float[] getPxRang(int i) {
        if (i == 1) {
            return new float[]{18.5f, 26.7f};
        }
        return new float[]{8.6f, 16.7f};
    }

    public static float[] getTsRang(int i) {
        if (i == 1) {
            return new float[]{45.0f, 60.0f};
        }
        return new float[]{55.0f, 65.0f};
    }
}
