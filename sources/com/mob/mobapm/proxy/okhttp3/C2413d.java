package com.mob.mobapm.proxy.okhttp3;

import java.net.URL;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/* renamed from: com.mob.mobapm.proxy.okhttp3.d */
public class C2413d extends Request.Builder {

    /* renamed from: a */
    private Request.Builder f2344a;

    public C2413d(Request.Builder builder) {
        this.f2344a = builder;
    }

    public Request.Builder addHeader(String str, String str2) {
        return this.f2344a.addHeader(str, str2);
    }

    public Request build() {
        return this.f2344a.build();
    }

    public Request.Builder cacheControl(CacheControl cacheControl) {
        return this.f2344a.cacheControl(cacheControl);
    }

    public Request.Builder delete() {
        return this.f2344a.delete();
    }

    public Request.Builder get() {
        return this.f2344a.get();
    }

    public Request.Builder head() {
        return this.f2344a.head();
    }

    public Request.Builder header(String str, String str2) {
        return this.f2344a.header(str, str2);
    }

    public Request.Builder headers(Headers headers) {
        return this.f2344a.headers(headers);
    }

    public Request.Builder method(String str, RequestBody requestBody) {
        return this.f2344a.method(str, requestBody);
    }

    public Request.Builder patch(RequestBody requestBody) {
        return this.f2344a.patch(requestBody);
    }

    public Request.Builder post(RequestBody requestBody) {
        return this.f2344a.post(requestBody);
    }

    public Request.Builder put(RequestBody requestBody) {
        return this.f2344a.put(requestBody);
    }

    public Request.Builder removeHeader(String str) {
        return this.f2344a.removeHeader(str);
    }

    public Request.Builder tag(Object obj) {
        return this.f2344a.tag(obj);
    }

    public Request.Builder url(String str) {
        return this.f2344a.url(str);
    }

    public Request.Builder url(URL url) {
        return this.f2344a.url(url);
    }
}
