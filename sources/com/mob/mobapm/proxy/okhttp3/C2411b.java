package com.mob.mobapm.proxy.okhttp3;

import com.mob.mobapm.core.Transaction;
import com.mob.mobapm.p027c.C2352a;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/* renamed from: com.mob.mobapm.proxy.okhttp3.b */
public class C2411b implements Callback {

    /* renamed from: a */
    private Transaction f2342a;

    /* renamed from: b */
    private Callback f2343b;

    public C2411b(Callback callback, Transaction transaction) {
        this.f2343b = callback;
        this.f2342a = transaction;
    }

    /* renamed from: a */
    private Response m2908a(Response response) {
        Transaction transaction = this.f2342a;
        return (transaction == null || transaction.getTransStatus() >= 2) ? response : C2412c.m2911a(this.f2342a, response);
    }

    public void onFailure(Call call, IOException iOException) {
        mo29536a((Exception) iOException);
        this.f2343b.onFailure(call, iOException);
    }

    public void onResponse(Call call, Response response) throws IOException {
        this.f2343b.onResponse(call, m2908a(response));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Transaction mo29535a() {
        return this.f2342a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo29536a(Exception exc) {
        C2352a.m2752a(mo29535a(), (Throwable) exc);
    }
}
