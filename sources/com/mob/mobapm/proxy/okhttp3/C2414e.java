package com.mob.mobapm.proxy.okhttp3;

import okhttp3.Handshake;
import okhttp3.Headers;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* renamed from: com.mob.mobapm.proxy.okhttp3.e */
public class C2414e extends Response.Builder {

    /* renamed from: a */
    private Response.Builder f2345a;

    public C2414e(Response.Builder builder) {
        this.f2345a = builder;
    }

    public Response.Builder addHeader(String str, String str2) {
        return this.f2345a.addHeader(str, str2);
    }

    public Response.Builder body(ResponseBody responseBody) {
        return this.f2345a.body(responseBody);
    }

    public Response build() {
        return this.f2345a.build();
    }

    public Response.Builder cacheResponse(Response response) {
        return this.f2345a.cacheResponse(response);
    }

    public Response.Builder code(int i) {
        return this.f2345a.code(i);
    }

    public Response.Builder handshake(Handshake handshake) {
        return this.f2345a.handshake(handshake);
    }

    public Response.Builder header(String str, String str2) {
        return this.f2345a.header(str, str2);
    }

    public Response.Builder headers(Headers headers) {
        return this.f2345a.headers(headers);
    }

    public Response.Builder message(String str) {
        return this.f2345a.message(str);
    }

    public Response.Builder networkResponse(Response response) {
        return this.f2345a.networkResponse(response);
    }

    public Response.Builder priorResponse(Response response) {
        return this.f2345a.priorResponse(response);
    }

    public Response.Builder protocol(Protocol protocol) {
        return this.f2345a.protocol(protocol);
    }

    public Response.Builder removeHeader(String str) {
        return this.f2345a.removeHeader(str);
    }

    public Response.Builder request(Request request) {
        return this.f2345a.request(request);
    }
}
