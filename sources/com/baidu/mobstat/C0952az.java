package com.baidu.mobstat;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

/* renamed from: com.baidu.mobstat.az */
public abstract class C0952az {

    /* renamed from: a */
    public static int f1195a = 2;

    /* renamed from: a */
    public abstract String mo11623a();

    /* renamed from: b */
    public abstract boolean mo11630b();

    /* renamed from: a */
    public void mo11625a(String str, Throwable th) {
        m1176a(2, str + 10 + m1177d(th));
    }

    /* renamed from: a */
    public void mo11624a(String str) {
        m1176a(3, str);
    }

    /* renamed from: a */
    public void mo11626a(Throwable th) {
        m1176a(3, m1177d(th));
    }

    /* renamed from: b */
    public void mo11628b(String str, Throwable th) {
        m1176a(3, str + 10 + m1177d(th));
    }

    /* renamed from: c */
    public void mo11632c(String str, Throwable th) {
        m1176a(4, str + 10 + m1177d(th));
    }

    /* renamed from: b */
    public void mo11627b(String str) {
        m1176a(5, str);
    }

    /* renamed from: b */
    public void mo11629b(Throwable th) {
        m1176a(5, m1177d(th));
    }

    /* renamed from: c */
    public void mo11631c(String str) {
        m1176a(6, str);
    }

    /* renamed from: c */
    public void mo11633c(Throwable th) {
        m1176a(6, m1177d(th));
    }

    /* renamed from: d */
    public void mo11634d(String str, Throwable th) {
        m1176a(6, str + 10 + m1177d(th));
    }

    /* renamed from: d */
    private String m1177d(Throwable th) {
        if (th == null) {
            return "";
        }
        for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
            if (th2 instanceof UnknownHostException) {
                return "";
            }
        }
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    /* renamed from: a */
    private void m1176a(int i, String str) {
        if (mo11630b() && i >= f1195a) {
            Log.println(i, mo11623a(), str);
        }
    }
}
