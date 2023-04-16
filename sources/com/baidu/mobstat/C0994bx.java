package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.android.common.util.CommonParam;
import com.baidu.mobstat.util.CuidUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/* renamed from: com.baidu.mobstat.bx */
public final class C0994bx {
    /* renamed from: a */
    public static String m1483a(Context context) {
        String str = "";
        if (!C0989bu.m1416a().mo11721a(false)) {
            return str;
        }
        try {
            str = CommonParam.getCUID(context);
        } catch (Throwable unused) {
        }
        return TextUtils.isEmpty(str) ? CuidUtil.getCuid3(context) : str;
    }

    /* renamed from: a */
    public static HashMap<String, String> m1485a(Map<String, String> map) {
        if (map != null) {
            return new HashMap<>(map);
        }
        return null;
    }

    /* renamed from: a */
    public static String m1482a(long j) {
        Date date = new Date(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        return simpleDateFormat.format(date);
    }

    /* renamed from: a */
    public static boolean m1486a(Class<?> cls, String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            String methodName = stackTraceElement.getMethodName();
            if (!TextUtils.isEmpty(methodName) && cls != null && methodName.equals(str)) {
                Class<?> cls2 = null;
                try {
                    cls2 = Class.forName(stackTraceElement.getClassName());
                } catch (Throwable unused) {
                }
                if (cls2 != null && cls.isAssignableFrom(cls2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    public static String m1484a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        return str.length() > i ? str.substring(0, i) : str;
    }
}
