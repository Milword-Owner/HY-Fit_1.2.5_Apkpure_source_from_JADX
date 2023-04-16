package com.mob.mobapm.proxy.okhttp3;

import com.baidubce.http.StatusCodes;
import com.mob.mobapm.bean.TransactionType;
import com.mob.mobapm.core.C2356c;
import com.mob.mobapm.core.Transaction;
import com.mob.mobapm.p027c.C2352a;
import com.mob.mobapm.p030d.C2373a;
import com.mob.tools.log.NLog;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

/* renamed from: com.mob.mobapm.proxy.okhttp3.c */
public class C2412c extends C2352a {
    /* renamed from: a */
    public static void m2912a(Transaction transaction, Request request) {
        NLog a = C2373a.m2807a();
        a.mo29775i("APM: request start, switch is " + C2356c.f2251e, new Object[0]);
        if (C2356c.f2251e && transaction != null && request != null) {
            try {
                HttpUrl url = request.url();
                String host = url.host();
                String encodedPath = url.encodedPath();
                String str = url.isHttps() ? "https" : "http";
                transaction.setMethod(request.method());
                C2352a.m2751a(transaction, host, encodedPath, TransactionType.valueOf(str));
            } catch (Throwable th) {
                NLog a2 = C2373a.m2807a();
                a2.mo29775i("APM: OkHttp3 request start error: " + th, new Object[0]);
            }
        }
    }

    /* renamed from: a */
    public static Response m2911a(Transaction transaction, Response response) {
        int i;
        NLog a = C2373a.m2807a();
        a.mo29775i("APM: request end, transaction switch is " + transaction.isCreate(), new Object[0]);
        if (C2356c.f2251e && transaction != null && transaction.isCreate()) {
            String str = null;
            if (response == null) {
                i = StatusCodes.INTERNAL_ERROR;
            } else {
                try {
                    i = response.code();
                    if (i != 200) {
                        transaction.setErrMsg(response.peekBody(2147483647L).string());
                    }
                } catch (Throwable th) {
                    NLog a2 = C2373a.m2807a();
                    a2.mo29775i("APM: OKHttp3 request end error： " + th, new Object[0]);
                }
                Request request = response.request();
                if (request != null) {
                    str = request.method();
                }
            }
            C2352a.m2750a(transaction, str, i);
        }
        return response;
    }
}
