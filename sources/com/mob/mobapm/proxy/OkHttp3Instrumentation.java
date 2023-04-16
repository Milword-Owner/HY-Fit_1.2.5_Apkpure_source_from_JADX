package com.mob.mobapm.proxy;

import com.mob.mobapm.core.C2356c;
import com.mob.mobapm.p030d.C2373a;
import com.mob.tools.proguard.ClassKeeper;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;

public class OkHttp3Instrumentation implements ClassKeeper {
    public static HttpURLConnection open(HttpURLConnection httpURLConnection) {
        if (!C2356c.f2251e) {
            return httpURLConnection;
        }
        if (httpURLConnection instanceof HttpsURLConnection) {
            return new C2398b((HttpsURLConnection) httpURLConnection);
        }
        if (httpURLConnection != null) {
            return new C2397a(httpURLConnection);
        }
        return null;
    }

    public static HttpURLConnection openWithProxy(HttpURLConnection httpURLConnection) {
        if (!C2356c.f2251e) {
            return httpURLConnection;
        }
        if (httpURLConnection instanceof HttpsURLConnection) {
            return new C2398b((HttpsURLConnection) httpURLConnection);
        }
        if (httpURLConnection != null) {
            return new C2397a(httpURLConnection);
        }
        return null;
    }

    public static HttpURLConnection urlFactoryOpen(HttpURLConnection httpURLConnection) {
        if (!C2356c.f2251e) {
            return httpURLConnection;
        }
        C2373a.m2807a().mo29768d("OkHttpInstrumentation - wrapping return of call to OkUrlFactory.open...", new Object[0]);
        if (httpURLConnection instanceof HttpsURLConnection) {
            return new C2398b((HttpsURLConnection) httpURLConnection);
        }
        if (httpURLConnection != null) {
            return new C2397a(httpURLConnection);
        }
        return null;
    }
}
