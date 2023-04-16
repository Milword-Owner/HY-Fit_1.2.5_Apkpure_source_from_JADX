package com.baidu.mobstat;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Method;

/* renamed from: com.baidu.mobstat.cc */
public class C1002cc {

    /* renamed from: a */
    private static Object f1293a;

    /* renamed from: b */
    private static Class<?> f1294b;

    /* renamed from: c */
    private static Method f1295c;

    static {
        try {
            f1294b = Class.forName("com.android.id.impl.IdProviderImpl");
            f1293a = f1294b.newInstance();
            f1295c = f1294b.getMethod("getOAID", new Class[]{Context.class});
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public static String m1499a(Context context) {
        return m1500a(context, f1295c);
    }

    /* renamed from: a */
    private static String m1500a(Context context, Method method) {
        Object obj = f1293a;
        if (obj == null || method == null) {
            return null;
        }
        try {
            Object invoke = method.invoke(obj, new Object[]{context});
            if (invoke != null) {
                return (String) invoke;
            }
            return null;
        } catch (Exception e) {
            Log.e("IdentifierManager", "invoke exception!", e);
            return null;
        }
    }
}
