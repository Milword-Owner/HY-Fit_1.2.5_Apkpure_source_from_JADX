package com.mob.mobapm.p027c;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.mobapm.bean.TransactionType;
import com.mob.mobapm.core.C2356c;
import com.mob.mobapm.core.C2357d;
import com.mob.mobapm.core.C2365i;
import com.mob.mobapm.core.Transaction;
import com.mob.mobapm.p030d.C2373a;
import com.mob.mobapm.p031e.C2378d;
import com.mob.mobapm.p031e.C2380f;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.DeviceHelper;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;

/* renamed from: com.mob.mobapm.c.a */
public class C2352a {
    /* renamed from: a */
    public static void m2751a(Transaction transaction, String str, String str2, TransactionType transactionType) {
        NLog a = C2373a.m2807a();
        a.mo29775i("APM: request prepare, switch is " + C2356c.f2251e, new Object[0]);
        if (C2356c.f2251e && transaction != null) {
            if (transactionType != null) {
                try {
                    if (!TextUtils.isEmpty(str2)) {
                        transaction.setPath(str2);
                        transaction.setTransType(transactionType);
                        transaction.setImei(DeviceHelper.getInstance(MobSDK.getContext()).getIMEI());
                        transaction.setRequestTime(System.currentTimeMillis());
                        transaction.setMac(DeviceHelper.getInstance(MobSDK.getContext()).getMacAddress());
                        transaction.setHost(str);
                        C2380f.m2821a(transaction);
                        transaction.setDuid(C2357d.m2780e());
                        transaction.setClientTime(System.currentTimeMillis());
                        transaction.setNetworkType(DeviceHelper.getInstance(MobSDK.getContext()).getNetworkType());
                        transaction.setDataNetworkType(String.valueOf(DeviceHelper.getInstance(MobSDK.getContext()).getDataNtType()));
                        transaction.setTransStatus(1);
                    }
                } catch (Throwable th) {
                    NLog a2 = C2373a.m2807a();
                    a2.mo29768d("APM: request prepare error:" + th, new Object[0]);
                    return;
                }
            }
            if (transactionType == null && TextUtils.isEmpty(str2)) {
                transaction.setRequestTime(System.currentTimeMillis());
            }
            transaction.setHost(str);
            C2380f.m2821a(transaction);
            transaction.setDuid(C2357d.m2780e());
            transaction.setClientTime(System.currentTimeMillis());
            transaction.setNetworkType(DeviceHelper.getInstance(MobSDK.getContext()).getNetworkType());
            transaction.setDataNetworkType(String.valueOf(DeviceHelper.getInstance(MobSDK.getContext()).getDataNtType()));
            transaction.setTransStatus(1);
        }
    }

    /* renamed from: b */
    public static void m2755b(Transaction transaction, HttpURLConnection httpURLConnection) {
        NLog a = C2373a.m2807a();
        a.mo29775i("APM: request prepare, switch is " + C2356c.f2251e, new Object[0]);
        if (C2356c.f2251e && transaction != null) {
            try {
                transaction.setTransType(httpURLConnection.getURL().getProtocol().equals("http") ? TransactionType.http : TransactionType.https);
                transaction.setHost(httpURLConnection.getURL().getHost());
                transaction.setPath(httpURLConnection.getURL().getPath());
                transaction.setImei(DeviceHelper.getInstance(MobSDK.getContext()).getIMEI());
                transaction.setDuid(C2357d.m2780e());
                transaction.setClientTime(System.currentTimeMillis());
            } catch (Throwable th) {
                NLog a2 = C2373a.m2807a();
                a2.mo29768d("APM: request prepare error:" + th, new Object[0]);
            }
        }
    }

    /* renamed from: a */
    public static void m2749a(Transaction transaction) {
        C2373a.m2807a().mo29775i("APM: request start", new Object[0]);
        if (C2356c.f2251e && transaction != null && transaction.getTransStatus() < 1) {
            try {
                transaction.setMac(DeviceHelper.getInstance(MobSDK.getContext()).getMacAddress());
                transaction.setNetworkType(DeviceHelper.getInstance(MobSDK.getContext()).getNetworkType());
                transaction.setDataNetworkType(String.valueOf(DeviceHelper.getInstance(MobSDK.getContext()).getDataNtType()));
                transaction.setRequestTime(System.currentTimeMillis());
                transaction.setTransStatus(1);
                C2380f.m2821a(transaction);
            } catch (Throwable th) {
                NLog a = C2373a.m2807a();
                a.mo29768d("APM: request start error:" + th, new Object[0]);
            }
        }
    }

    /* renamed from: a */
    public static void m2753a(Transaction transaction, HttpURLConnection httpURLConnection) {
        NLog a = C2373a.m2807a();
        a.mo29775i("APM: request end, transaction switch is " + transaction.isCreate(), new Object[0]);
        if (C2356c.f2251e && transaction != null && transaction.isCreate()) {
            int i = -1;
            try {
                i = httpURLConnection.getResponseCode();
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
                a2.mo29775i("APM: get response code exception :" + th, new Object[0]);
            }
            if (i >= 300) {
                try {
                    transaction.setErrMsg(C2378d.m2819b(httpURLConnection.getInputStream()));
                } catch (Throwable unused) {
                }
            }
            m2750a(transaction, httpURLConnection.getRequestMethod(), i);
        }
    }

    /* renamed from: a */
    public static void m2750a(Transaction transaction, String str, int i) {
        NLog a = C2373a.m2807a();
        a.mo29775i("APM: request end, transaction switch is " + transaction.isCreate(), new Object[0]);
        if (C2356c.f2251e && transaction != null && transaction.isCreate() && transaction.getTransStatus() != 2) {
            try {
                transaction.setTransStatus(2);
                transaction.setResponseTime(System.currentTimeMillis());
                transaction.setRequestDuration(transaction.getResponseTime() - transaction.getRequestTime());
                transaction.setStatus(i);
                if (!TextUtils.isEmpty(str)) {
                    transaction.setMethod(str);
                }
                NLog a2 = C2373a.m2807a();
                a2.mo29768d("APM: start inserting this transcation:" + transaction, new Object[0]);
                C2365i.m2791d().mo29262a(transaction);
            } catch (Throwable th) {
                NLog a3 = C2373a.m2807a();
                a3.mo29768d("APM: an error occurred while inserting this data:" + th, new Object[0]);
            }
        }
    }

    /* renamed from: a */
    public static void m2754a(Transaction transaction, HttpURLConnection httpURLConnection, String str) {
        NLog a = C2373a.m2807a();
        a.mo29775i("APM: request error! transaction switch is " + transaction.isCreate(), new Object[0]);
        if (C2356c.f2251e && transaction != null && transaction.isCreate()) {
            int i = -1;
            try {
                i = httpURLConnection.getResponseCode();
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
                a2.mo29775i("APM: get response code exception :" + th, new Object[0]);
            }
            transaction.setErrMsg(str);
            m2750a(transaction, httpURLConnection.getRequestMethod(), i);
        }
    }

    /* renamed from: a */
    public static void m2752a(Transaction transaction, Throwable th) {
        NLog a = C2373a.m2807a();
        a.mo29775i("APM: request error! transaction switch is " + transaction.isCreate(), new Object[0]);
        if (C2356c.f2251e && transaction != null && transaction.isCreate()) {
            transaction.setErrMsg(th.getMessage());
            int i = -1;
            if (th instanceof UnknownHostException) {
                i = 901;
            } else if (th instanceof SocketTimeoutException) {
                i = 903;
            } else if (th instanceof ConnectException) {
                i = 902;
            } else if (th instanceof SSLException) {
                i = 908;
            }
            m2750a(transaction, "", i);
        }
    }
}
