package com.mob.mobapm.proxy.okhttp3;

import com.mob.mobapm.core.C2356c;
import com.mob.mobapm.core.Transaction;
import com.mob.mobapm.p030d.C2373a;
import com.mob.mobapm.proxy.C2397a;
import com.mob.mobapm.proxy.C2398b;
import com.mob.tools.log.NLog;
import com.mob.tools.proguard.ClassKeeper;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.OkUrlFactory;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.connection.StreamAllocation;

public class OkHttp3Instrumentation implements ClassKeeper {

    public static class OkHttp35 implements ClassKeeper {
        public static StreamAllocation callEngineGetStreamAllocation(Internal internal, Call call) {
            try {
                if (call instanceof C2410a) {
                    call = ((C2410a) call).mo29524a();
                }
                Method method = Internal.class.getMethod("callEngineGetStreamAllocation", new Class[]{Call.class});
                if (method != null) {
                    return (StreamAllocation) method.invoke(internal, new Object[]{call});
                }
                OkHttp3Instrumentation.logReflectionError("callEngineGetStreamAllocation(Lokhttp3/Call;)Lokhttp3/internal/connection/StreamAllocation;");
                return null;
            } catch (Exception e) {
                NLog a = C2373a.m2807a();
                a.mo29775i("APM: callEngineGetStreamAllocation error: " + e.getMessage(), new Object[0]);
            }
        }

        public static Call newWebSocketCall(Internal internal, OkHttpClient okHttpClient, Request request) {
            try {
                Method method = Internal.class.getMethod("newWebSocketCall", new Class[]{OkHttpClient.class, Request.class});
                if (method != null) {
                    return new C2410a(okHttpClient, request, (Call) method.invoke(internal, new Object[]{okHttpClient, request}), new Transaction());
                }
                OkHttp3Instrumentation.logReflectionError("newWebSocketCall(Lokhttp3/OkHttpClient;Lokhttp3/Request;)Lokhttp3/Call;");
                return null;
            } catch (Exception e) {
                NLog a = C2373a.m2807a();
                a.mo29775i("APM: newWebSocketCall error: " + e.getMessage(), new Object[0]);
            }
        }

        public static void setCallWebSocket(Internal internal, Call call) {
            try {
                if (call instanceof C2410a) {
                    call = ((C2410a) call).mo29524a();
                }
                Method method = Internal.class.getMethod("setCallWebSocket", new Class[]{Call.class});
                if (method != null) {
                    method.invoke(internal, new Object[]{call});
                    return;
                }
                OkHttp3Instrumentation.logReflectionError("setCallWebSocket(Lokhttp3/Call;)V");
            } catch (Exception e) {
                NLog a = C2373a.m2807a();
                a.mo29775i("APM: set callwebsocket error: " + e.getMessage(), new Object[0]);
            }
        }
    }

    public static Response.Builder body(Response.Builder builder, ResponseBody responseBody) {
        if (!C2356c.f2251e) {
            return builder.body(responseBody);
        }
        return new C2414e(builder).body(responseBody);
    }

    public static Request build(Request.Builder builder) {
        if (!C2356c.f2251e) {
            return builder.build();
        }
        return new C2413d(builder).build();
    }

    /* access modifiers changed from: private */
    public static void logReflectionError(String str) {
        String property = System.getProperty("line.separator");
        NLog a = C2373a.m2807a();
        a.mo29775i("Unable to resolve method \"" + str + "\"." + property + "This is usually due to building the app with unsupported OkHttp versions." + property + "Check your build configuration for compatibility.", new Object[0]);
    }

    public static Response.Builder newBuilder(Response.Builder builder) {
        if (!C2356c.f2251e) {
            return builder;
        }
        return new C2414e(builder);
    }

    public static Call newCall(OkHttpClient okHttpClient, Request request) {
        if (!C2356c.f2251e) {
            return okHttpClient.newCall(request);
        }
        return new C2410a(okHttpClient, request, okHttpClient.newCall(request), new Transaction());
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
}
