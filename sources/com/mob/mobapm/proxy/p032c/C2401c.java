package com.mob.mobapm.proxy.p032c;

import com.mob.mobapm.core.Transaction;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

@Deprecated
/* renamed from: com.mob.mobapm.proxy.c.c */
public final class C2401c<T> implements ResponseHandler<T> {

    /* renamed from: a */
    private final ResponseHandler<T> f2328a;

    /* renamed from: b */
    private final Transaction f2329b;

    private C2401c(ResponseHandler<T> responseHandler, Transaction transaction) {
        this.f2328a = responseHandler;
        this.f2329b = transaction;
    }

    /* renamed from: a */
    public static <T> ResponseHandler<? extends T> m2892a(ResponseHandler<? extends T> responseHandler, Transaction transaction) {
        return new C2401c(responseHandler, transaction);
    }

    public T handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
        C2400b.m2889a(this.f2329b, httpResponse);
        return this.f2328a.handleResponse(httpResponse);
    }
}
