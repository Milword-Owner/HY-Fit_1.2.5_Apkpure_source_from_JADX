package com.baidu.mobstat;

import android.content.Context;

/* renamed from: com.baidu.mobstat.ag */
public class C0884ag {
    /* renamed from: a */
    public static int m838a(Context context, float f) {
        if (context == null) {
            return 0;
        }
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: b */
    public static float m839b(Context context, float f) {
        if (context == null) {
            return 0.0f;
        }
        return (f / context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    /* renamed from: c */
    public static int m840c(Context context, float f) {
        if (context == null) {
            return 0;
        }
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
