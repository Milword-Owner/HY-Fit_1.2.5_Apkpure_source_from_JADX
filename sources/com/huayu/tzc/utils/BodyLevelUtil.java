package com.huayu.tzc.utils;

import android.util.Log;
import com.huayu.tzc.base.Constant;

public class BodyLevelUtil {
    public static int getFpLevel(float f) {
        if (f == 0.0f) {
            return 0;
        }
        if (f == 1.0f) {
            return 1;
        }
        return f == 2.0f ? 2 : 3;
    }

    public static int getScoreLevel(float f) {
        return f < 60.0f ? 0 : 1;
    }

    public static int getBmiLevel(float f) {
        if (f <= Constant.BMI_FALSE[0]) {
            return 0;
        }
        return f <= Constant.BMI_FALSE[1] ? 1 : 2;
    }

    public static int getDbzLevel(float f) {
        if (f <= Constant.DBZ[0]) {
            return 0;
        }
        return f <= Constant.DBZ[1] ? 1 : 2;
    }

    public static int getJrLevel(float f, float[] fArr) {
        if (f <= fArr[0]) {
            return 2;
        }
        return f <= fArr[1] ? 1 : 0;
    }

    public static int getGLevel(float f) {
        if (f <= Constant.f1685GL[0]) {
            return 2;
        }
        if (f <= Constant.f1685GL[1]) {
            return 1;
        }
        return 0;
    }

    public static int getNzLevel(float f) {
        if (f <= Constant.NZZZ[0]) {
            return 0;
        }
        if (f <= Constant.NZZZ[1]) {
            return 1;
        }
        return f <= Constant.NZZZ[2] ? 2 : 3;
    }

    public static int getPxLevel(float f, float[] fArr) {
        if (f <= fArr[0]) {
            return 0;
        }
        if (f <= fArr[1]) {
            return 1;
        }
        return 2;
    }

    public static int getTsLevel(float f, float[] fArr) {
        if (f <= fArr[0]) {
            return 2;
        }
        return f <= fArr[1] ? 1 : 0;
    }

    public static int getJcLevel(float f) {
        return f <= Constant.JCDX[0] ? 0 : 1;
    }

    public static int getGgjLevel(float f) {
        if (f <= Constant.GGJ[0]) {
            return 2;
        }
        if (f <= Constant.GGJ[1]) {
            return 1;
        }
        return 0;
    }

    public static int getTLevel(float f, float[] fArr) {
        if (f <= fArr[0]) {
            return 0;
        }
        if (f <= fArr[1]) {
            return 1;
        }
        if (f <= fArr[2]) {
            return 2;
        }
        return 3;
    }

    public static int getQzLevel(float f) {
        if (f <= Constant.WEIGHT[0]) {
            return 0;
        }
        return f <= Constant.WEIGHT[1] ? 1 : 2;
    }

    public static int getLxLevel(float f) {
        Log.e("s", "getLxLevel: " + f);
        if (f <= Constant.LXWEIGHT[0]) {
            return 0;
        }
        return f <= Constant.LXWEIGHT[1] ? 1 : 2;
    }

    public static int getWeightLevel(float f, int i, float f2) {
        float[] weightArray = RangeUtil.getWeightArray(i, f2);
        for (int i2 = 0; i2 < weightArray.length; i2++) {
            Log.e("TAG", "getWeightLevel: " + weightArray[i2]);
        }
        if (f <= weightArray[0]) {
            return 0;
        }
        if (f <= weightArray[1]) {
            return 1;
        }
        if (f <= weightArray[2]) {
            return 2;
        }
        if (f <= weightArray[3]) {
            return 3;
        }
        return 4;
    }
}
