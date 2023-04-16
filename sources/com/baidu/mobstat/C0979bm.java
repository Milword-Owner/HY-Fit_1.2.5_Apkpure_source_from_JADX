package com.baidu.mobstat;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: com.baidu.mobstat.bm */
public abstract class C0979bm {
    /* renamed from: a */
    public abstract SharedPreferences mo11431a(Context context);

    /* renamed from: a */
    public boolean mo11668a(Context context, String str, boolean z) {
        return mo11431a(context).getBoolean(str, z);
    }

    /* renamed from: b */
    public void mo11672b(Context context, String str, boolean z) {
        mo11431a(context).edit().putBoolean(str, z).commit();
    }

    /* renamed from: a */
    public int mo11665a(Context context, String str, int i) {
        return mo11431a(context).getInt(str, i);
    }

    /* renamed from: b */
    public void mo11669b(Context context, String str, int i) {
        mo11431a(context).edit().putInt(str, i).commit();
    }

    /* renamed from: a */
    public long mo11666a(Context context, String str, long j) {
        return mo11431a(context).getLong(str, j);
    }

    /* renamed from: b */
    public void mo11670b(Context context, String str, long j) {
        mo11431a(context).edit().putLong(str, j).commit();
    }

    /* renamed from: a */
    public String mo11667a(Context context, String str, String str2) {
        return mo11431a(context).getString(str, str2);
    }

    /* renamed from: b */
    public void mo11671b(Context context, String str, String str2) {
        mo11431a(context).edit().putString(str, str2).commit();
    }

    /* renamed from: c */
    public void mo11673c(Context context, String str) {
        mo11431a(context).edit().remove(str).commit();
    }
}
