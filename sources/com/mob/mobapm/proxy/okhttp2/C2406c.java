package com.mob.mobapm.proxy.okhttp2;

import com.baidubce.http.StatusCodes;
import com.mob.mobapm.bean.TransactionType;
import com.mob.mobapm.core.C2356c;
import com.mob.mobapm.core.Transaction;
import com.mob.mobapm.p027c.C2352a;
import com.mob.mobapm.p030d.C2373a;
import com.mob.tools.log.NLog;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;

/* renamed from: com.mob.mobapm.proxy.okhttp2.c */
public class C2406c extends C2352a {
    /* renamed from: a */
    public static void m2902a(Transaction transaction, Request request) {
        NLog a = C2373a.m2807a();
        a.mo29775i("APM: OkHttp2 request start, transaction switch is " + C2356c.f2251e, new Object[0]);
        if (C2356c.f2251e && transaction != null && request != null) {
            String host = request.url().getHost();
            String path = request.url().getPath();
            String protocol = request.url().getProtocol();
            transaction.setMethod(request.method());
            C2352a.m2751a(transaction, host, path, TransactionType.valueOf(protocol));
        }
    }

    /* renamed from: a */
    public static Response m2901a(Transaction transaction, Response response) {
        int i;
        String str;
        NLog a = C2373a.m2807a();
        a.mo29775i("APM: OkHttp2 request end, transaction switch is " + C2356c.f2251e, new Object[0]);
        if (C2356c.f2251e && transaction != null && transaction.isCreate()) {
            String str2 = null;
            if (response == null) {
                i = StatusCodes.INTERNAL_ERROR;
            } else {
                i = response.code();
                if (i != 200) {
                    try {
                        ResponseBody a2 = C2407d.m2903a(response.body(), 2147483647L);
                        if (a2 == null) {
                            str = "";
                        } else {
                            str = a2.string();
                        }
                        transaction.setErrMsg(str);
                    } catch (IOException e) {
                        NLog a3 = C2373a.m2807a();
                        a3.mo29775i("APM: OkHttp2 request end error: " + e, new Object[0]);
                    }
                }
                Request request = response.request();
                if (request != null) {
                    str2 = request.method();
                }
            }
            C2352a.m2750a(transaction, str2, i);
        }
        return response;
    }
}
