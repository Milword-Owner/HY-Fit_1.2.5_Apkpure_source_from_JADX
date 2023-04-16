package com.mob.mobapm.proxy;

import com.mob.mobapm.core.C2356c;
import com.mob.mobapm.core.Transaction;
import com.mob.mobapm.p027c.C2352a;
import com.mob.mobapm.proxy.p032c.C2400b;
import com.mob.mobapm.proxy.p032c.C2401c;
import com.mob.tools.proguard.ClassKeeper;
import java.io.IOException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

public class ApacheInstrumentation implements ClassKeeper {
    private static HttpUriRequest delegate(HttpUriRequest httpUriRequest, Transaction transaction) {
        return C2400b.m2890a(transaction, httpUriRequest);
    }

    public static HttpResponse execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException {
        if (!C2356c.f2251e) {
            return httpClient.execute(httpHost, httpRequest, httpContext);
        }
        Transaction transaction = new Transaction();
        try {
            return delegate(httpClient.execute(httpHost, delegate(httpHost, httpRequest, transaction), httpContext), transaction);
        } catch (Throwable th) {
            C2352a.m2752a(transaction, th);
            throw th;
        }
    }

    private static HttpRequest delegate(HttpHost httpHost, HttpRequest httpRequest, Transaction transaction) {
        return C2400b.m2888a(transaction, httpHost, httpRequest);
    }

    private static HttpResponse delegate(HttpResponse httpResponse, Transaction transaction) {
        return C2400b.m2889a(transaction, httpResponse);
    }

    private static <T> ResponseHandler<? extends T> delegate(ResponseHandler<? extends T> responseHandler, Transaction transaction) {
        return C2401c.m2892a(responseHandler, transaction);
    }

    public static <T> T execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException, ClientProtocolException {
        if (!C2356c.f2251e) {
            return httpClient.execute(httpHost, httpRequest, responseHandler, httpContext);
        }
        Transaction transaction = new Transaction();
        try {
            return httpClient.execute(httpHost, delegate(httpHost, httpRequest, transaction), delegate(responseHandler, transaction), httpContext);
        } catch (Throwable th) {
            C2352a.m2752a(transaction, th);
            throw th;
        }
    }

    public static <T> T execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        if (!C2356c.f2251e) {
            return httpClient.execute(httpHost, httpRequest, responseHandler);
        }
        Transaction transaction = new Transaction();
        try {
            return httpClient.execute(httpHost, delegate(httpHost, httpRequest, transaction), delegate(responseHandler, transaction));
        } catch (Throwable th) {
            C2352a.m2752a(transaction, th);
            throw th;
        }
    }

    public static HttpResponse execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        if (!C2356c.f2251e) {
            return httpClient.execute(httpHost, httpRequest);
        }
        Transaction transaction = new Transaction();
        try {
            return delegate(httpClient.execute(httpHost, delegate(httpHost, httpRequest, transaction)), transaction);
        } catch (Throwable th) {
            C2352a.m2752a(transaction, th);
            throw th;
        }
    }

    public static HttpResponse execute(HttpClient httpClient, HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException {
        if (!C2356c.f2251e) {
            return httpClient.execute(httpUriRequest, httpContext);
        }
        Transaction transaction = new Transaction();
        try {
            return delegate(httpClient.execute(delegate(httpUriRequest, transaction), httpContext), transaction);
        } catch (Throwable th) {
            C2352a.m2752a(transaction, th);
            throw th;
        }
    }

    public static <T> T execute(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException, ClientProtocolException {
        if (!C2356c.f2251e) {
            return httpClient.execute(httpUriRequest, responseHandler, httpContext);
        }
        Transaction transaction = new Transaction();
        try {
            return httpClient.execute(delegate(httpUriRequest, transaction), delegate(responseHandler, transaction), httpContext);
        } catch (Throwable th) {
            C2352a.m2752a(transaction, th);
            throw th;
        }
    }

    public static <T> T execute(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        if (!C2356c.f2251e) {
            return httpClient.execute(httpUriRequest, responseHandler);
        }
        Transaction transaction = new Transaction();
        try {
            return httpClient.execute(delegate(httpUriRequest, transaction), delegate(responseHandler, transaction));
        } catch (Throwable th) {
            C2352a.m2752a(transaction, th);
            throw th;
        }
    }

    public static HttpResponse execute(HttpClient httpClient, HttpUriRequest httpUriRequest) throws IOException {
        if (!C2356c.f2251e) {
            return httpClient.execute(httpUriRequest);
        }
        Transaction transaction = new Transaction();
        try {
            return delegate(httpClient.execute(delegate(httpUriRequest, transaction)), transaction);
        } catch (Throwable th) {
            C2352a.m2752a(transaction, th);
            throw th;
        }
    }
}
