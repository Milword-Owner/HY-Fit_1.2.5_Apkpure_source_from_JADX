package com.huayu.tzc.utils;

import android.util.Log;
import com.huayu.tzc.base.Constant;

public class BodyUtil {
    public static String[] colors = {"#56A3FA", "#0EDAAE", "#FAD65B", "#F8A139", "#F66B89"};

    public static int getTx(String[] strArr, String str) {
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public static String getColor1(float f) {
        if (f >= 60.0f) {
            return colors[1];
        }
        return colors[4];
    }

    public static String getColor5(float[] fArr, float f) {
        if (f <= fArr[0]) {
            return colors[0];
        }
        if (f <= fArr[1]) {
            return colors[1];
        }
        if (f <= fArr[2]) {
            return colors[2];
        }
        if (f <= fArr[3]) {
            return colors[3];
        }
        return colors[4];
    }

    public static String getWeightColor(int i) {
        return Constant.COLORS4[i];
    }

    public static String getColor3(float[] fArr, float f) {
        if (f <= fArr[0]) {
            return colors[0];
        }
        if (f <= fArr[1]) {
            return colors[1];
        }
        return colors[3];
    }

    public static String getColorBmi(float[] fArr, float f) {
        if (f <= fArr[0]) {
            return colors[0];
        }
        if (f <= fArr[1]) {
            return colors[1];
        }
        return colors[3];
    }

    public static String getColor4(float[] fArr, float f) {
        if (f <= fArr[0]) {
            return colors[0];
        }
        if (f <= fArr[1]) {
            return colors[1];
        }
        if (f <= fArr[2]) {
            return colors[2];
        }
        return colors[4];
    }

    public static String getColor2(float[] fArr, float f) {
        if (f <= fArr[0]) {
            return colors[0];
        }
        return colors[1];
    }

    public static float[] getUnits(float[] fArr, String str) {
        float[] fArr2 = new float[fArr.length];
        int i = 0;
        for (int i2 = 0; i2 < fArr.length; i2++) {
            fArr2[i2] = fArr[i2];
            Log.e("s", "getUnits: 66   " + fArr2[i2]);
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != 3420) {
            if (hashCode != 3446) {
                if (hashCode == 109719855 && str.equals("st:lb")) {
                    c = 2;
                }
            } else if (str.equals("lb")) {
                c = 1;
            }
        } else if (str.equals("kg")) {
            c = 0;
        }
        if (c != 0) {
            if (c == 1) {
                while (i < fArr2.length) {
                    fArr2[i] = UnitUtils.kg2Lb(fArr2[i]);
                    i++;
                }
            } else if (c != 2) {
                return fArr2;
            } else {
                while (i < fArr2.length) {
                    fArr2[i] = UnitUtils.kg2St(fArr2[i]);
                    i++;
                }
                return fArr2;
            }
        }
        return fArr2;
    }

    public static String getColorTs(float[] fArr, float f) {
        if (f <= fArr[0]) {
            return colors[3];
        }
        if (f <= fArr[1]) {
            return colors[1];
        }
        return colors[0];
    }

    public static String getColorTx(float f) {
        if (f <= 3.0f) {
            return colors[0];
        }
        if (f <= 5.0f) {
            return colors[1];
        }
        return colors[4];
    }

    public static int getFatLevel(float f, float[] fArr) {
        if (f <= fArr[1]) {
            return 0;
        }
        if (f <= fArr[2]) {
            return 1;
        }
        if (f <= fArr[3]) {
        }
        return 2;
    }
}
