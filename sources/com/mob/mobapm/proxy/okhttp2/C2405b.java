package com.mob.mobapm.proxy.okhttp2;

import com.mob.mobapm.core.Transaction;
import com.mob.mobapm.p027c.C2352a;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

/* renamed from: com.mob.mobapm.proxy.okhttp2.b */
public class C2405b implements Callback {

    /* renamed from: a */
    private Callback f2335a;

    /* renamed from: b */
    private Transaction f2336b;

    public C2405b(Callback callback, Transaction transaction) {
        this.f2335a = callback;
        this.f2336b = transaction;
    }

    /* renamed from: a */
    private Response m2899a(Response response) {
        if (this.f2336b.getTransStatus() >= 2) {
            return response;
        }
        return C2406c.m2901a(this.f2336b, response);
    }

    public void onFailure(Request request, IOException iOException) {
        mo29491a((Exception) iOException);
        this.f2335a.onFailure(request, iOException);
    }

    public void onResponse(Response response) throws IOException {
        this.f2335a.onResponse(m2899a(response));
    }

    /* renamed from: a */
    public void mo29491a(Exception exc) {
        C2352a.m2752a(this.f2336b, (Throwable) exc);
    }
}
