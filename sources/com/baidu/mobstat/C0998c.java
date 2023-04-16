package com.baidu.mobstat;

import android.content.Context;

/* renamed from: com.baidu.mobstat.c */
public class C0998c {
    /* renamed from: a */
    public static void m1489a(Context context) {
        if (C0989bu.m1416a().mo11724c()) {
            C0953b.f1196a.mo11635a(context);
            C1085w.m1796a(context).mo11889a(C1051g.AP_LIST, System.currentTimeMillis());
        }
    }

    /* renamed from: a */
    public static void m1490a(Context context, boolean z) {
        if (C0989bu.m1416a().mo11724c()) {
            C1048e.f1402a.mo11831a(context, z);
            C1085w.m1796a(context).mo11889a(z ? C1051g.APP_SYS_LIST : C1051g.APP_USER_LIST, System.currentTimeMillis());
        }
    }

    /* renamed from: b */
    public static void m1492b(Context context, boolean z) {
        if (C0989bu.m1416a().mo11724c()) {
            C1049f.f1403a.mo11832a(context, z);
            C1085w.m1796a(context).mo11889a(z ? C1051g.APP_TRACE_CURRENT : C1051g.APP_TRACE_HIS, System.currentTimeMillis());
        }
    }

    /* renamed from: b */
    public static void m1491b(Context context) {
        if (C0989bu.m1416a().mo11724c()) {
            C1036d.f1373a.mo11817a(context);
            C1085w.m1796a(context).mo11889a(C1051g.APP_APK, System.currentTimeMillis());
        }
    }
}
