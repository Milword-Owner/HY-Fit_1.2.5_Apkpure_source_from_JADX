package com.mob.mobapm.proxy.okhttp3;

import com.mob.mobapm.core.Transaction;
import com.mob.mobapm.p027c.C2352a;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Timeout;

/* renamed from: com.mob.mobapm.proxy.okhttp3.a */
public class C2410a implements Call {

    /* renamed from: a */
    private Transaction f2339a;

    /* renamed from: b */
    private Request f2340b;

    /* renamed from: c */
    private Call f2341c;

    C2410a(OkHttpClient okHttpClient, Request request, Call call, Transaction transaction) {
        this.f2340b = request;
        this.f2341c = call;
        this.f2339a = transaction;
    }

    /* renamed from: a */
    private Response m2904a(Response response) {
        return this.f2339a.getTransStatus() < 2 ? C2412c.m2911a(mo29526b(), response) : response;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public Transaction mo29526b() {
        if (this.f2339a == null) {
            this.f2339a = new Transaction();
        }
        C2412c.m2912a(this.f2339a, this.f2340b);
        return this.f2339a;
    }

    public void cancel() {
        this.f2341c.cancel();
    }

    public void enqueue(Callback callback) {
        mo29526b();
        this.f2341c.enqueue(new C2411b(callback, this.f2339a));
    }

    public Response execute() throws IOException {
        mo29526b();
        try {
            return m2904a(this.f2341c.execute());
        } catch (IOException e) {
            mo29525a((Exception) e);
            throw e;
        }
    }

    public boolean isCanceled() {
        return this.f2341c.isCanceled();
    }

    public boolean isExecuted() {
        return false;
    }

    public Request request() {
        return this.f2341c.request();
    }

    public Timeout timeout() {
        return this.f2341c.timeout();
    }

    public Call clone() {
        return this.f2341c.clone();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo29525a(Exception exc) {
        C2352a.m2752a(mo29526b(), (Throwable) exc);
    }

    /* renamed from: a */
    public Call mo29524a() {
        return this.f2341c;
    }
}
