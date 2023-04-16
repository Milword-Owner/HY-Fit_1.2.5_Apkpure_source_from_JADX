package com.baidu.mobstat;

import android.content.Context;
import java.lang.Thread;

/* renamed from: com.baidu.mobstat.ab */
class C0877ab implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    private static final C0877ab f932a = new C0877ab();

    /* renamed from: b */
    private Thread.UncaughtExceptionHandler f933b;

    /* renamed from: c */
    private Context f934c;

    /* renamed from: a */
    public static C0877ab m819a() {
        return f932a;
    }

    private C0877ab() {
    }

    /* renamed from: a */
    public void mo11429a(Context context) {
        this.f934c = context;
        if (this.f933b == null) {
            this.f933b = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        ExceptionAnalysis.getInstance().saveCrashInfo(this.f934c, th, true);
        if (!this.f933b.equals(this)) {
            this.f933b.uncaughtException(thread, th);
        }
    }
}
