package com.mob.commons;

import com.mob.tools.proguard.ClassKeeper;

public class MOBAPM implements MobProduct, ClassKeeper {
    public static final int SDK_VERSION_CODE;
    public static final String SDK_VERSION_NAME = "1.3.0";
    public static String sdkTag = "MOBAPM";

    static {
        String[] split = SDK_VERSION_NAME.split("\\.");
        int length = split.length;
        if (length > 3) {
            length = 3;
        }
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i = (i * 100) + Integer.parseInt(split[i2]);
        }
        SDK_VERSION_CODE = i;
    }

    public String getProductTag() {
        return sdkTag;
    }

    public int getSdkver() {
        return SDK_VERSION_CODE;
    }
}
