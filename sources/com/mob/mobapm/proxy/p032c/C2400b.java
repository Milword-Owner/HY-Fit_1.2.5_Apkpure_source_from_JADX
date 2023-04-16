package com.mob.mobapm.proxy.p032c;

import com.mob.MobSDK;
import com.mob.mobapm.bean.TransactionType;
import com.mob.mobapm.core.C2356c;
import com.mob.mobapm.core.C2357d;
import com.mob.mobapm.core.Transaction;
import com.mob.mobapm.p027c.C2352a;
import com.mob.mobapm.p030d.C2373a;
import com.mob.mobapm.p031e.C2378d;
import com.mob.mobapm.p031e.C2380f;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.DeviceHelper;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

/* renamed from: com.mob.mobapm.proxy.c.b */
public class C2400b extends C2352a {
    /* renamed from: a */
    public static HttpUriRequest m2890a(Transaction transaction, HttpUriRequest httpUriRequest) {
        NLog a = C2373a.m2807a();
        a.mo29775i("APM: HttpClient request start, switch is " + C2356c.f2251e, new Object[0]);
        if (C2356c.f2251e && transaction != null) {
            m2891a(transaction, httpUriRequest.getURI().toString(), httpUriRequest.getURI().getHost(), httpUriRequest.getURI().getPath(), httpUriRequest.getMethod());
        }
        return httpUriRequest;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00af A[Catch:{ MalformedURLException -> 0x003a, all -> 0x00bb }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.http.HttpRequest m2888a(com.mob.mobapm.core.Transaction r8, org.apache.http.HttpHost r9, org.apache.http.HttpRequest r10) {
        /*
            java.lang.String r0 = "/"
            com.mob.tools.log.NLog r1 = com.mob.mobapm.p030d.C2373a.m2807a()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "APM: HttpClient request start, switch is "
            r2.append(r3)
            boolean r3 = com.mob.mobapm.core.C2356c.f2251e
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r3 = 0
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r1.mo29775i(r2, r4)
            boolean r1 = com.mob.mobapm.core.C2356c.f2251e
            if (r1 == 0) goto L_0x00d6
            if (r8 != 0) goto L_0x0027
            goto L_0x00d6
        L_0x0027:
            org.apache.http.RequestLine r1 = r10.getRequestLine()     // Catch:{ all -> 0x00bb }
            java.lang.String r1 = r1.getUri()     // Catch:{ all -> 0x00bb }
            r2 = 0
            java.net.URL r4 = new java.net.URL     // Catch:{ MalformedURLException -> 0x003a }
            r4.<init>(r1)     // Catch:{ MalformedURLException -> 0x003a }
            java.lang.String r4 = r4.getHost()     // Catch:{ MalformedURLException -> 0x003a }
            goto L_0x005d
        L_0x003a:
            r4 = move-exception
            com.mob.tools.log.NLog r5 = com.mob.mobapm.p030d.C2373a.m2807a()     // Catch:{ all -> 0x00bb }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bb }
            r6.<init>()     // Catch:{ all -> 0x00bb }
            java.lang.String r7 = "dispatchHttpClientRequest error!"
            r6.append(r7)     // Catch:{ all -> 0x00bb }
            java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x00bb }
            r6.append(r4)     // Catch:{ all -> 0x00bb }
            r6.append(r1)     // Catch:{ all -> 0x00bb }
            java.lang.String r4 = r6.toString()     // Catch:{ all -> 0x00bb }
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ all -> 0x00bb }
            r5.mo29775i(r4, r6)     // Catch:{ all -> 0x00bb }
            r4 = r2
        L_0x005d:
            org.apache.http.RequestLine r5 = r10.getRequestLine()     // Catch:{ all -> 0x00bb }
            if (r5 == 0) goto L_0x00b3
            if (r1 == 0) goto L_0x007c
            int r6 = r1.length()     // Catch:{ all -> 0x00bb }
            r7 = 10
            if (r6 < r7) goto L_0x007c
            java.lang.String r6 = r1.substring(r3, r7)     // Catch:{ all -> 0x00bb }
            java.lang.String r7 = "://"
            int r6 = r6.indexOf(r7)     // Catch:{ all -> 0x00bb }
            if (r6 >= 0) goto L_0x007a
            goto L_0x007c
        L_0x007a:
            r6 = 1
            goto L_0x007d
        L_0x007c:
            r6 = 0
        L_0x007d:
            if (r6 != 0) goto L_0x00ad
            if (r1 == 0) goto L_0x00ad
            if (r4 == 0) goto L_0x00ad
            java.lang.String r9 = r9.toURI()     // Catch:{ all -> 0x00bb }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x00bb }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bb }
            r2.<init>()     // Catch:{ all -> 0x00bb }
            r2.append(r9)     // Catch:{ all -> 0x00bb }
            boolean r9 = r9.endsWith(r0)     // Catch:{ all -> 0x00bb }
            if (r9 != 0) goto L_0x009f
            boolean r9 = r1.startsWith(r0)     // Catch:{ all -> 0x00bb }
            if (r9 == 0) goto L_0x00a1
        L_0x009f:
            java.lang.String r0 = ""
        L_0x00a1:
            r2.append(r0)     // Catch:{ all -> 0x00bb }
            r2.append(r1)     // Catch:{ all -> 0x00bb }
            java.lang.String r9 = r2.toString()     // Catch:{ all -> 0x00bb }
            r2 = r9
            goto L_0x00b0
        L_0x00ad:
            if (r6 == 0) goto L_0x00b0
            r2 = r1
        L_0x00b0:
            r8.setPath(r2)     // Catch:{ all -> 0x00bb }
        L_0x00b3:
            java.lang.String r9 = r5.getMethod()     // Catch:{ all -> 0x00bb }
            m2891a(r8, r1, r4, r2, r9)     // Catch:{ all -> 0x00bb }
            goto L_0x00d6
        L_0x00bb:
            r8 = move-exception
            com.mob.tools.log.NLog r9 = com.mob.mobapm.p030d.C2373a.m2807a()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "APM: HttpClient request start error:"
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            java.lang.Object[] r0 = new java.lang.Object[r3]
            r9.mo29768d(r8, r0)
        L_0x00d6:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.mobapm.proxy.p032c.C2400b.m2888a(com.mob.mobapm.core.Transaction, org.apache.http.HttpHost, org.apache.http.HttpRequest):org.apache.http.HttpRequest");
    }

    /* renamed from: a */
    private static void m2891a(Transaction transaction, String str, String str2, String str3, String str4) {
        NLog a = C2373a.m2807a();
        a.mo29775i("APM: HttpClient request start, switch is " + C2356c.f2251e, new Object[0]);
        if (C2356c.f2251e && transaction != null) {
            try {
                transaction.setTransType(!str.contains("https") ? TransactionType.http : TransactionType.https);
                transaction.setHost(str2);
                transaction.setPath(str3);
                C2380f.m2821a(transaction);
                transaction.setImei(DeviceHelper.getInstance(MobSDK.getContext()).getIMEI());
                transaction.setDuid(C2357d.m2780e());
                transaction.setMac(DeviceHelper.getInstance(MobSDK.getContext()).getMacAddress());
                transaction.setNetworkType(DeviceHelper.getInstance(MobSDK.getContext()).getNetworkType());
                transaction.setDataNetworkType(String.valueOf(DeviceHelper.getInstance(MobSDK.getContext()).getDataNtType()));
                transaction.setTransStatus(1);
                transaction.setClientTime(System.currentTimeMillis());
                transaction.setRequestTime(transaction.getClientTime());
                transaction.setMethod(str4);
            } catch (Throwable th) {
                NLog a2 = C2373a.m2807a();
                a2.mo29768d("APM: HttpClient request start error:" + th, new Object[0]);
            }
        }
    }

    /* renamed from: a */
    public static HttpResponse m2889a(Transaction transaction, HttpResponse httpResponse) {
        NLog a = C2373a.m2807a();
        a.mo29775i("APM: HttpClient request end, transaction switch is " + transaction.isCreate(), new Object[0]);
        if (C2356c.f2251e && transaction != null && transaction.isCreate()) {
            int i = -1;
            try {
                httpResponse.setEntity(new C2399a(httpResponse.getEntity()));
                i = httpResponse.getStatusLine().getStatusCode();
                if (i >= 300) {
                    transaction.setErrMsg(C2378d.m2819b(httpResponse.getEntity().getContent()));
                }
            } catch (Throwable th) {
                if (th instanceof UnknownHostException) {
                    i = 901;
                } else if (th instanceof SocketTimeoutException) {
                    i = 903;
                } else if (th instanceof ConnectException) {
                    i = 902;
                } else if (th instanceof SSLException) {
                    i = 908;
                }
                NLog a2 = C2373a.m2807a();
                a2.mo29775i("APM: HttpClient get response code exception :" + th, new Object[0]);
            }
            C2352a.m2750a(transaction, (String) null, i);
        }
        return httpResponse;
    }
}
