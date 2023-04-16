package com.mob.mobapm.proxy.okhttp2;

import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import java.net.URL;

/* renamed from: com.mob.mobapm.proxy.okhttp2.e */
public class C2408e extends Request.Builder {

    /* renamed from: a */
    private Request.Builder f2337a;

    public C2408e(Request.Builder builder) {
        this.f2337a = builder;
    }

    public Request.Builder addHeader(String str, String str2) {
        return this.f2337a.addHeader(str, str2);
    }

    public Request build() {
        return this.f2337a.build();
    }

    public Request.Builder cacheControl(CacheControl cacheControl) {
        return this.f2337a.cacheControl(cacheControl);
    }

    public Request.Builder delete() {
        return this.f2337a.delete();
    }

    public Request.Builder get() {
        return this.f2337a.get();
    }

    public Request.Builder head() {
        return this.f2337a.head();
    }

    public Request.Builder header(String str, String str2) {
        return this.f2337a.header(str, str2);
    }

    public Request.Builder headers(Headers headers) {
        return this.f2337a.headers(headers);
    }

    public Request.Builder method(String str, RequestBody requestBody) {
        return this.f2337a.method(str, requestBody);
    }

    public Request.Builder patch(RequestBody requestBody) {
        return this.f2337a.patch(requestBody);
    }

    public Request.Builder post(RequestBody requestBody) {
        return this.f2337a.post(requestBody);
    }

    public Request.Builder put(RequestBody requestBody) {
        return this.f2337a.put(requestBody);
    }

    public Request.Builder removeHeader(String str) {
        return this.f2337a.removeHeader(str);
    }

    public Request.Builder tag(Object obj) {
        return this.f2337a.tag(obj);
    }

    public Request.Builder url(String str) {
        return this.f2337a.url(str);
    }

    public Request.Builder url(URL url) {
        return this.f2337a.url(url);
    }
}
