package com.mob.mobapm.proxy;

import com.mob.mobapm.core.C2356c;
import com.mob.tools.proguard.ClassKeeper;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

public final class URLConnectionInstrumentation implements ClassKeeper {
    private URLConnectionInstrumentation() {
    }

    public static URLConnection openConnection(URLConnection uRLConnection) {
        if (C2356c.f2251e) {
            if (uRLConnection instanceof HttpsURLConnection) {
                return new C2398b((HttpsURLConnection) uRLConnection);
            }
            if (uRLConnection instanceof HttpURLConnection) {
                return new C2397a((HttpURLConnection) uRLConnection);
            }
        }
        return uRLConnection;
    }

    public static URLConnection openConnectionWithProxy(URLConnection uRLConnection) {
        if (C2356c.f2251e) {
            if (uRLConnection instanceof HttpsURLConnection) {
                return new C2398b((HttpsURLConnection) uRLConnection);
            }
            if (uRLConnection instanceof HttpURLConnection) {
                return new C2397a((HttpURLConnection) uRLConnection);
            }
        }
        return uRLConnection;
    }
}
