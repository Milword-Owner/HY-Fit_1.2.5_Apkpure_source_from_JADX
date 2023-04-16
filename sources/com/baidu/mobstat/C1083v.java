package com.baidu.mobstat;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

/* renamed from: com.baidu.mobstat.v */
class C1083v {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static volatile boolean f1448a = false;

    /* renamed from: a */
    public static synchronized void m1791a(Context context, C0875a aVar) {
        synchronized (C1083v.class) {
            if (!f1448a) {
                if (!C0991bw.m1470q(context)) {
                    C0954ba.m1191c().mo11624a("isWifiAvailable = false, will not to update");
                } else if (!aVar.mo11425a(context)) {
                    C0954ba.m1191c().mo11624a("check time, will not to update");
                } else {
                    C0954ba.m1191c().mo11624a("can start update config");
                    new C1084a(context, aVar).start();
                    f1448a = true;
                }
            }
        }
    }

    /* renamed from: com.baidu.mobstat.v$a */
    static class C1084a extends Thread {

        /* renamed from: a */
        private Context f1449a;

        /* renamed from: b */
        private C0875a f1450b;

        public C1084a(Context context, C0875a aVar) {
            this.f1449a = context;
            this.f1450b = aVar;
        }

        public void run() {
            try {
                int i = C1087y.f1469a ? 3 : 10;
                C0954ba c = C0954ba.m1191c();
                c.mo11624a("start version check in " + i + "s");
                sleep((long) (i * 1000));
                m1793a();
                m1794a(this.f1449a);
            } catch (Exception e) {
                C0954ba.m1191c().mo11626a((Throwable) e);
            }
            boolean unused = C1083v.f1448a = false;
        }

        /* renamed from: a */
        private void m1794a(Context context) {
            this.f1450b.mo11422a(context, System.currentTimeMillis());
        }

        /* JADX INFO: finally extract failed */
        /* renamed from: a */
        private synchronized void m1793a() throws Exception {
            C0954ba.m1191c().mo11624a("start get config");
            Context context = this.f1449a;
            C0875a aVar = this.f1450b;
            String b = m1795b(context);
            C0954ba c = C0954ba.m1191c();
            c.mo11631c("update req url is:" + b);
            HttpURLConnection d = C0980bn.m1351d(context, b);
            try {
                d.connect();
                String headerField = d.getHeaderField("X-CONFIG");
                C0954ba c2 = C0954ba.m1191c();
                c2.mo11624a("config is: " + headerField);
                String headerField2 = d.getHeaderField("X-SIGN");
                C0954ba c3 = C0954ba.m1191c();
                c3.mo11624a("sign is: " + headerField2);
                int responseCode = d.getResponseCode();
                C0954ba c4 = C0954ba.m1191c();
                c4.mo11624a("update response code is: " + responseCode);
                int contentLength = d.getContentLength();
                C0954ba c5 = C0954ba.m1191c();
                c5.mo11624a("update response content length is: " + contentLength);
                if (responseCode == 200) {
                    C0954ba.m1191c().mo11624a("request  success");
                }
                if (!TextUtils.isEmpty(headerField)) {
                    C0954ba c6 = C0954ba.m1191c();
                    c6.mo11624a("save Config " + headerField);
                    aVar.mo11423a(context, headerField);
                }
                if (!TextUtils.isEmpty(headerField2)) {
                    C0954ba c7 = C0954ba.m1191c();
                    c7.mo11624a("save Sign " + headerField2);
                    aVar.mo11426b(context, headerField2);
                }
                d.disconnect();
                C0954ba.m1191c().mo11624a("finish get config");
            } catch (Throwable th) {
                d.disconnect();
                throw th;
            }
        }

        /* renamed from: b */
        private String m1795b(Context context) {
            ArrayList<Pair> arrayList = new ArrayList<>();
            arrayList.add(new Pair("dynamicVersion", "" + "35"));
            arrayList.add(new Pair("packageName", C0991bw.m1473t(context)));
            arrayList.add(new Pair("appVersion", C0991bw.m1453g(context)));
            arrayList.add(new Pair("cuid", C0991bw.m1432a(context)));
            arrayList.add(new Pair("platform", "Android"));
            arrayList.add(new Pair(Config.MODEL, Build.MODEL));
            arrayList.add(new Pair("s", Build.VERSION.SDK_INT + ""));
            arrayList.add(new Pair(Config.f779OS, Build.VERSION.RELEASE));
            arrayList.add(new Pair("i", "35"));
            StringBuilder sb = new StringBuilder();
            for (Pair pair : arrayList) {
                try {
                    String encode = URLEncoder.encode(((String) pair.first).toString(), "UTF-8");
                    String encode2 = URLEncoder.encode(((String) pair.second).toString(), "UTF-8");
                    if (TextUtils.isEmpty(sb.toString())) {
                        sb.append(encode + "=" + encode2);
                    } else {
                        sb.append("&" + encode + "=" + encode2);
                    }
                } catch (Exception unused) {
                }
            }
            return C1087y.f1471c + "?" + sb.toString();
        }
    }
}
