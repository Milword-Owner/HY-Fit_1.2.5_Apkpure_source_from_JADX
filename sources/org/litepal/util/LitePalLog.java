package org.litepal.util;

import android.util.Log;

public final class LitePalLog {
    public static final int DEBUG = 2;
    public static final int ERROR = 5;
    public static int level = 5;

    /* renamed from: d */
    public static void m2964d(String str, String str2) {
        if (level <= 2) {
            Log.d(str, str2);
        }
    }

    /* renamed from: e */
    public static void m2965e(String str, Exception exc) {
        if (level <= 5) {
            Log.e(str, exc.getMessage(), exc);
        }
    }
}
