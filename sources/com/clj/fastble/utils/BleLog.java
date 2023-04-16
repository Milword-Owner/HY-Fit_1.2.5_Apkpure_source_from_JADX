package com.clj.fastble.utils;

import android.util.Log;

public final class BleLog {
    private static String defaultTag = "FastBle";
    public static boolean isPrint = true;

    /* renamed from: d */
    public static void m1842d(String str) {
        if (isPrint && str != null) {
            Log.d(defaultTag, str);
        }
    }

    /* renamed from: i */
    public static void m1844i(String str) {
        if (isPrint && str != null) {
            Log.i(defaultTag, str);
        }
    }

    /* renamed from: w */
    public static void m1845w(String str) {
        if (isPrint && str != null) {
            Log.w(defaultTag, str);
        }
    }

    /* renamed from: e */
    public static void m1843e(String str) {
        if (isPrint && str != null) {
            Log.e(defaultTag, str);
        }
    }
}
