package com.baidu.mobstat;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: com.baidu.mobstat.ac */
class C0878ac extends C0979bm {

    /* renamed from: a */
    private static final String f935a = "baidu_mtj_sdk_record";

    /* renamed from: b */
    private static C0878ac f936b = new C0878ac();

    private C0878ac() {
    }

    /* renamed from: a */
    public static C0878ac m821a() {
        return f936b;
    }

    /* renamed from: a */
    public SharedPreferences mo11431a(Context context) {
        return context.getSharedPreferences(f935a, 0);
    }

    /* renamed from: a */
    public void mo11432a(Context context, long j) {
        mo11670b(context, "session_first_visit_time", j);
    }

    /* renamed from: b */
    public Long mo11434b(Context context) {
        return Long.valueOf(mo11666a(context, "session_first_visit_time", 0));
    }

    /* renamed from: b */
    public void mo11435b(Context context, long j) {
        mo11670b(context, "session_last_visit_time", j);
    }

    /* renamed from: c */
    public Long mo11437c(Context context) {
        return Long.valueOf(mo11666a(context, "session_last_visit_time", 0));
    }

    /* renamed from: c */
    public void mo11438c(Context context, long j) {
        mo11670b(context, "session_visit_interval", j);
    }

    /* renamed from: d */
    public Long mo11439d(Context context) {
        return Long.valueOf(mo11666a(context, "session_visit_interval", 0));
    }

    /* renamed from: a */
    public void mo11433a(Context context, String str) {
        mo11671b(context, "session_today_visit_count", str);
    }

    /* renamed from: e */
    public String mo11440e(Context context) {
        return mo11667a(context, "session_today_visit_count", "");
    }

    /* renamed from: b */
    public void mo11436b(Context context, String str) {
        mo11671b(context, "session_recent_visit", str);
    }

    /* renamed from: f */
    public String mo11441f(Context context) {
        return mo11667a(context, "session_recent_visit", "");
    }
}
