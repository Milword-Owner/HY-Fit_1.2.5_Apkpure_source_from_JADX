package com.mob.mobapm.proxy.okhttp2;

import com.mob.mobapm.core.Transaction;
import com.mob.mobapm.p027c.C2352a;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

/* renamed from: com.mob.mobapm.proxy.okhttp2.a */
public class C2404a extends Call {

    /* renamed from: a */
    private Call f2332a;

    /* renamed from: b */
    private Request f2333b;

    /* renamed from: c */
    private Transaction f2334c;

    C2404a(OkHttpClient okHttpClient, Request request, Call call, Transaction transaction) {
        super(okHttpClient, request);
        this.f2333b = request;
        this.f2332a = call;
        this.f2334c = transaction;
    }

    /* renamed from: a */
    private Response m2896a(Response response) {
        return this.f2334c.getTransStatus() < 2 ? C2406c.m2901a(mo29485a(), response) : response;
    }

    public void cancel() {
        this.f2332a.cancel();
    }

    public void enqueue(Callback callback) {
        mo29485a();
        this.f2332a.enqueue(new C2405b(callback, this.f2334c));
    }

    public Response execute() throws IOException {
        mo29485a();
        try {
            return m2896a(this.f2332a.execute());
        } catch (IOException e) {
            mo29486a((Exception) e);
            throw e;
        }
    }

    public boolean isCanceled() {
        return this.f2332a.isCanceled();
    }

    /* renamed from: a */
    public Transaction mo29485a() {
        if (this.f2334c == null) {
            this.f2334c = new Transaction();
        }
        C2406c.m2902a(this.f2334c, this.f2333b);
        return this.f2334c;
    }

    /* renamed from: a */
    public void mo29486a(Exception exc) {
        C2352a.m2752a(mo29485a(), (Throwable) exc);
    }
}
