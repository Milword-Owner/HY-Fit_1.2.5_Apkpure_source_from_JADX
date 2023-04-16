package com.huayu.tzc.customview.ruler;

public class RulerStringUtil {
    private static float mDividerCache = 1.0f;
    private static float mFactorCache;

    public static String resultValueOf(float f, float f2) {
        if (f2 >= 1.0f) {
            return String.valueOf((int) (f * f2));
        }
        if (f2 > 0.0f) {
            if (mFactorCache != f2) {
                mFactorCache = f2;
                mDividerCache = 1.0f / f2;
            }
            return String.valueOf(f / mDividerCache);
        }
        try {
            throw new Exception("Invalid factor!");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
