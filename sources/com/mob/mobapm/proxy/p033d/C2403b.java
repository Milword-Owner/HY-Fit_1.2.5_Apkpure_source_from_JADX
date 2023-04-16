package com.mob.mobapm.proxy.p033d;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.mobapm.bean.SocketTransaction;
import com.mob.mobapm.bean.TransactionType;
import com.mob.mobapm.core.C2356c;
import com.mob.mobapm.core.C2357d;
import com.mob.mobapm.core.C2363h;
import com.mob.mobapm.p027c.C2352a;
import com.mob.mobapm.p030d.C2373a;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.DeviceHelper;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;

/* renamed from: com.mob.mobapm.proxy.d.b */
public class C2403b extends C2352a {
    /* renamed from: a */
    public static void m2895a(SocketTransaction socketTransaction, InetSocketAddress inetSocketAddress) {
        NLog a = C2373a.m2807a();
        a.mo29775i("APM: socket start, switch is " + C2356c.f2254h, new Object[0]);
        if (C2356c.f2254h && socketTransaction != null && inetSocketAddress != null) {
            try {
                socketTransaction.setHost(inetSocketAddress.getHostName());
                socketTransaction.setPort(inetSocketAddress.getPort());
                socketTransaction.setTransType(TransactionType.socket);
                socketTransaction.setConnectBeginTime(System.currentTimeMillis());
                socketTransaction.setDuid(C2357d.m2780e());
                socketTransaction.setClientTime(System.currentTimeMillis());
                socketTransaction.setNetworkType(DeviceHelper.getInstance(MobSDK.getContext()).getNetworkType());
                socketTransaction.setDataNetworkType(String.valueOf(DeviceHelper.getInstance(MobSDK.getContext()).getDataNtType()));
                socketTransaction.setTransStatus(1);
            } catch (Throwable th) {
                NLog a2 = C2373a.m2807a();
                a2.mo29775i("APM: socket connect start error: " + th, new Object[0]);
            }
        }
    }

    /* renamed from: a */
    public static void m2893a(SocketTransaction socketTransaction) {
        NLog a = C2373a.m2807a();
        a.mo29775i("APM: socket connect end，transaction: " + socketTransaction, new Object[0]);
        if (C2356c.f2254h && socketTransaction != null && !TextUtils.isEmpty(socketTransaction.getHost())) {
            try {
                if (socketTransaction.getTransStatus() != 2) {
                    socketTransaction.setTransStatus(2);
                    socketTransaction.setConnectEndTime(System.currentTimeMillis());
                    socketTransaction.setConnectDuration(socketTransaction.getConnectEndTime() - socketTransaction.getConnectBeginTime());
                    socketTransaction.setStatus(200);
                    NLog a2 = C2373a.m2807a();
                    a2.mo29768d("APM: start inserting this socket transcation:" + socketTransaction, new Object[0]);
                    C2363h.m2788d().mo29260a(socketTransaction);
                }
            } catch (Throwable th) {
                NLog a3 = C2373a.m2807a();
                a3.mo29775i("APM: socket request end error： " + th, new Object[0]);
            }
        }
    }

    /* renamed from: a */
    public static void m2894a(SocketTransaction socketTransaction, Throwable th) {
        C2373a.m2807a().mo29775i("APM: socket connect error!", new Object[0]);
        if (C2356c.f2254h && socketTransaction != null) {
            int i = 200;
            try {
                if (th instanceof UnknownHostException) {
                    i = 901;
                } else if (th instanceof SocketTimeoutException) {
                    i = 903;
                } else if (th instanceof ConnectException) {
                    i = 902;
                } else if (th instanceof SSLException) {
                    i = 908;
                }
                if (socketTransaction.getTransStatus() != 2) {
                    socketTransaction.setTransStatus(2);
                    socketTransaction.setConnectEndTime(System.currentTimeMillis());
                    socketTransaction.setConnectDuration(socketTransaction.getConnectEndTime() - socketTransaction.getConnectBeginTime());
                    socketTransaction.setStatus(i);
                    NLog a = C2373a.m2807a();
                    a.mo29768d("APM: start inserting this socket transcation:" + socketTransaction, new Object[0]);
                    C2363h.m2788d().mo29260a(socketTransaction);
                }
            } catch (Throwable th2) {
                NLog a2 = C2373a.m2807a();
                a2.mo29775i("APM: socket connect end error： " + th2, new Object[0]);
            }
        }
    }
}
