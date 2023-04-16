package com.mob.mobapm.proxy.okhttp2;

import com.squareup.okhttp.Handshake;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

/* renamed from: com.mob.mobapm.proxy.okhttp2.f */
public class C2409f extends Response.Builder {

    /* renamed from: a */
    private Response.Builder f2338a;

    public C2409f(Response.Builder builder) {
        this.f2338a = builder;
    }

    public Response.Builder addHeader(String str, String str2) {
        return this.f2338a.addHeader(str, str2);
    }

    public Response.Builder body(ResponseBody responseBody) {
        return this.f2338a.body(responseBody);
    }

    public Response build() {
        return this.f2338a.build();
    }

    public Response.Builder cacheResponse(Response response) {
        return this.f2338a.cacheResponse(response);
    }

    public Response.Builder code(int i) {
        return this.f2338a.code(i);
    }

    public Response.Builder handshake(Handshake handshake) {
        return this.f2338a.handshake(handshake);
    }

    public Response.Builder header(String str, String str2) {
        return this.f2338a.header(str, str2);
    }

    public Response.Builder headers(Headers headers) {
        return this.f2338a.headers(headers);
    }

    public Response.Builder message(String str) {
        return this.f2338a.message(str);
    }

    public Response.Builder networkResponse(Response response) {
        return this.f2338a.networkResponse(response);
    }

    public Response.Builder priorResponse(Response response) {
        return this.f2338a.priorResponse(response);
    }

    public Response.Builder protocol(Protocol protocol) {
        return this.f2338a.protocol(protocol);
    }

    public Response.Builder removeHeader(String str) {
        return this.f2338a.removeHeader(str);
    }

    public Response.Builder request(Request request) {
        return this.f2338a.request(request);
    }
}
