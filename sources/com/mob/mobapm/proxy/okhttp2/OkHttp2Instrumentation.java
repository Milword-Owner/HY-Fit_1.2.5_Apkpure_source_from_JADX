package com.mob.mobapm.proxy.okhttp2;

import com.mob.mobapm.core.C2356c;
import com.mob.mobapm.core.Transaction;
import com.mob.mobapm.proxy.C2397a;
import com.mob.mobapm.proxy.C2398b;
import com.mob.tools.proguard.ClassKeeper;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class OkHttp2Instrumentation implements ClassKeeper {
    static final String CACHED_RESPONSE_CLASS = "com.squareup.okhttp.Cache$CacheResponseBody";

    public static Response.Builder body(Response.Builder builder, ResponseBody responseBody) {
        if (!C2356c.f2251e) {
            return builder.body(responseBody);
        }
        return new C2409f(builder).body(responseBody);
    }

    public static Request build(Request.Builder builder) {
        if (!C2356c.f2251e) {
            return builder.build();
        }
        return new C2408e(builder).build();
    }

    public static Response.Builder newBuilder(Response.Builder builder) {
        if (!C2356c.f2251e) {
            return builder;
        }
        return new C2409f(builder);
    }

    public static Call newCall(OkHttpClient okHttpClient, Request request) {
        if (!C2356c.f2251e) {
            return okHttpClient.newCall(request);
        }
        return new C2404a(okHttpClient, request, okHttpClient.newCall(request), new Transaction());
    }

    public static HttpURLConnection open(OkUrlFactory okUrlFactory, URL url) {
        HttpURLConnection open = okUrlFactory.open(url);
        if (!C2356c.f2251e) {
            return open;
        }
        String protocol = url.getProtocol();
        if (protocol.equals("http")) {
            return new C2397a(open);
        }
        if (!protocol.equals("https") || !(open instanceof HttpsURLConnection)) {
            return new C2397a(open);
        }
        return new C2398b((HttpsURLConnection) open);
    }

    public static ResponseBody body(Response response) {
        return response.body();
    }
}
