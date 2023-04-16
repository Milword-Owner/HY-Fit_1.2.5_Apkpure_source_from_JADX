package com.mob.commons;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.baidu.mobstat.Config;
import com.clj.fastble.BleManager;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.mob.MobSDK;
import com.mob.OperationCallback;
import com.mob.commons.C2262b;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.commons.p023a.C2221a;
import com.mob.commons.p023a.C2224b;
import com.mob.commons.p023a.C2225c;
import com.mob.commons.p023a.C2226d;
import com.mob.commons.p023a.C2230e;
import com.mob.commons.p023a.C2231f;
import com.mob.commons.p023a.C2232g;
import com.mob.commons.p023a.C2233h;
import com.mob.commons.p023a.C2235i;
import com.mob.commons.p023a.C2236j;
import com.mob.commons.p023a.C2237k;
import com.mob.commons.p023a.C2241l;
import com.mob.commons.p023a.C2245n;
import com.mob.commons.p023a.C2248o;
import com.mob.commons.p023a.C2250p;
import com.mob.commons.p023a.C2251q;
import com.mob.commons.p023a.C2252r;
import com.mob.commons.p023a.C2253s;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.UIHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/* renamed from: com.mob.commons.a */
/* compiled from: CltsInitializer */
public final class C2212a {

    /* renamed from: a */
    private static final String f1919a = C2310j.m2570a();

    /* renamed from: b */
    private static final String f1920b = C2310j.m2572b();
    /* access modifiers changed from: private */

    /* renamed from: c */
    public static final String f1921c = (f1919a + "/privacy/policy/ms/version");

    /* renamed from: d */
    private static final String f1922d = (f1919a + "/privacy/policy/rejection/strategy");

    /* renamed from: e */
    private static final String f1923e = (f1920b + "/privacy/policy/authorization/status");

    /* renamed from: f */
    private static final String f1924f = (f1920b + "/privacy/policy/permission/window/status");
    /* access modifiers changed from: private */

    /* renamed from: g */
    public static Boolean f1925g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public static Boolean f1926h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public static int f1927i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public static Boolean f1928j;

    /* renamed from: k */
    private static Boolean f1929k;

    /* renamed from: l */
    private static int f1930l = -1;

    /* renamed from: m */
    private static int f1931m = -1;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public static int f1932n;

    /* renamed from: o */
    private static Boolean f1933o;

    /* renamed from: p */
    private static Boolean f1934p;

    /* renamed from: q */
    private static Boolean f1935q;

    /* renamed from: r */
    private static Boolean f1936r;

    /* renamed from: s */
    private static byte[] f1937s = new byte[0];

    /* renamed from: t */
    private static byte[] f1938t = new byte[0];

    /* renamed from: u */
    private static volatile int f1939u = -1;

    /* renamed from: a */
    public static final void m1948a() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    C2212a.m1982v();
                    boolean c = C2212a.m1961c();
                    NLog instance = MobLog.getInstance();
                    instance.mo29768d("====> ppNece: " + c, new Object[0]);
                    if (!c) {
                        MobLog.getInstance().mo29768d("====> Entr 1", new Object[0]);
                        Boolean unused = C2212a.f1926h = null;
                        Boolean unused2 = C2212a.f1925g = null;
                        int unused3 = C2212a.f1927i = C2212a.m1984x();
                        C2212a.m1981u();
                        return;
                    }
                    boolean d = C2212a.m1963d();
                    NLog instance2 = MobLog.getInstance();
                    instance2.mo29768d("====> isAgrPp: " + d, new Object[0]);
                    if (d) {
                        MobLog.getInstance().mo29768d("====> Entr 2", new Object[0]);
                        Boolean unused4 = C2212a.f1925g = true;
                        Boolean unused5 = C2212a.f1926h = null;
                        int unused6 = C2212a.f1927i = C2212a.m1984x();
                        C2212a.m1981u();
                        return;
                    }
                    boolean j = C2212a.m1970j();
                    NLog instance3 = MobLog.getInstance();
                    instance3.mo29768d("====> cltStch: " + j, new Object[0]);
                    if (j) {
                        MobLog.getInstance().mo29768d("====> Entr 3", new Object[0]);
                        Boolean unused7 = C2212a.f1925g = C2212a.m1971k();
                        Boolean unused8 = C2212a.f1926h = null;
                        int unused9 = C2212a.f1927i = C2212a.m1984x();
                        C2212a.m1981u();
                    } else {
                        MobLog.getInstance().mo29768d("====> Entr cltSwth=false", new Object[0]);
                        C2262b.m2288am();
                        C2262b.m2267a((HashMap<String, Object>) null);
                        C2212a.m1949a(1);
                    }
                    C2212a.m1980t();
                } catch (Throwable th) {
                    MobLog.getInstance().mo29773e(th, "Clt init error", new Object[0]);
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                Boolean E = C2308i.m2500E();
                if (E != null) {
                    C2212a.m1960c(E.booleanValue(), (OperationCallback<Void>) null);
                }
            }
        }).start();
    }

    /* renamed from: b */
    public static boolean m1957b() {
        if (f1939u == -1) {
            synchronized (f1938t) {
                if (f1939u == -1) {
                    try {
                        MobLog.getInstance().mo29768d("Wait initLock", new Object[0]);
                        f1938t.wait();
                    } catch (Throwable th) {
                        MobLog.getInstance().mo29770d(th, "Init lock error", new Object[0]);
                    }
                }
            }
            if (f1939u == 1) {
                return true;
            }
            return false;
        } else if (f1939u == 1) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: a */
    public static void m1949a(int i) {
        NLog instance = MobLog.getInstance();
        instance.mo29768d("Notify initLock. initialized: " + i, new Object[0]);
        synchronized (f1938t) {
            try {
                f1939u = i;
                f1938t.notifyAll();
            } catch (Throwable th) {
                MobLog.getInstance().mo29770d(th, "Init lock error", new Object[0]);
            }
        }
    }

    /* renamed from: c */
    public static boolean m1961c() {
        if (f1928j == null) {
            f1928j = Boolean.valueOf(C2308i.m2496A());
        }
        return f1928j.booleanValue();
    }

    /* renamed from: d */
    public static boolean m1963d() {
        Boolean k = m1971k();
        if (k == null) {
            k = false;
        }
        return k.booleanValue();
    }

    /* renamed from: e */
    public static Boolean m1964e() {
        return f1925g;
    }

    /* renamed from: f */
    public static Boolean m1966f() {
        return f1926h;
    }

    /* renamed from: g */
    public static int m1967g() {
        return f1927i;
    }

    /* renamed from: h */
    public static int m1968h() {
        return f1932n;
    }

    /* renamed from: i */
    public static synchronized boolean m1969i() {
        boolean booleanValue;
        synchronized (C2212a.class) {
            if (f1933o == null) {
                int C = C2308i.m2498C();
                if (C == 1) {
                    f1933o = true;
                } else if (C == 0) {
                    f1933o = false;
                } else {
                    f1933o = true;
                }
            }
            booleanValue = f1933o.booleanValue();
        }
        return booleanValue;
    }

    /* renamed from: j */
    public static boolean m1970j() {
        if (f1934p == null) {
            synchronized (f1937s) {
                if (f1934p == null) {
                    int D = C2308i.m2499D();
                    if (D == -1) {
                        m1983w();
                    } else {
                        if (D == 1) {
                            f1934p = true;
                        } else if (D == 0) {
                            f1934p = false;
                        } else {
                            f1934p = false;
                        }
                        new Thread(new Runnable() {
                            public void run() {
                                C2212a.m1983w();
                            }
                        }).start();
                    }
                }
            }
        }
        return f1934p.booleanValue();
    }

    /* renamed from: a */
    public static void m1951a(boolean z, OperationCallback<Void> operationCallback) {
        m1960c(z, operationCallback);
        MobLog.getInstance().mo29768d("submitPpResult().", new Object[0]);
        NLog instance = MobLog.getInstance();
        instance.mo29768d("grtd: " + z, new Object[0]);
        if (!z) {
            f1929k = false;
            C2308i.m2521c(0);
            return;
        }
        boolean d = m1963d();
        NLog instance2 = MobLog.getInstance();
        instance2.mo29768d("bfdIsAgrPp: " + d, new Object[0]);
        if (!d) {
            f1929k = true;
            C2308i.m2521c(1);
            MobLog.getInstance().mo29768d("====> Entr 5", new Object[0]);
            f1925g = Boolean.valueOf(m1963d());
            f1926h = null;
            f1927i = m1984x();
            C2262b.m2264a((C2262b.C2269a) new C2262b.C2269a() {
                /* renamed from: a */
                public void mo28982a() {
                    C2212a.m1981u();
                }
            });
        }
    }

    /* renamed from: k */
    public static Boolean m1971k() {
        int B;
        if (f1929k == null && (B = C2308i.m2497B()) != -1) {
            boolean z = true;
            if (B != 1) {
                z = false;
            }
            f1929k = Boolean.valueOf(z);
        }
        return f1929k;
    }

    /* access modifiers changed from: private */
    /* renamed from: t */
    public static void m1980t() {
        if (C2262b.m2277ab()) {
            Log.w("MobSDK/Policy", "您好！依照国家对网络安全及数据安全的要求，请您运营的APP集成并向终端用户展示Mob SDK的隐私政策。");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: u */
    public static void m1981u() {
        C2226d.m2017a((Class<? extends C2226d>[]) new Class[]{C2221a.class, C2237k.class, C2248o.class, C2251q.class, C2224b.class, C2253s.class, C2233h.class, C2252r.class, C2250p.class, C2245n.class, C2236j.class, C2235i.class, C2230e.class, C2225c.class, C2231f.class, C2232g.class, C2241l.class});
    }

    /* access modifiers changed from: private */
    /* renamed from: v */
    public static void m1982v() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new KVPair("appkey", MobSDK.getAppkey()));
                    arrayList.add(new KVPair("apppkg", instance.getPackageName()));
                    arrayList.add(new KVPair("appver", instance.getAppVersionName()));
                    arrayList.add(new KVPair("plat", String.valueOf(instance.getPlatformCode())));
                    arrayList.add(new KVPair("networktype", instance.getDetailNetworkTypeForStatic()));
                    String authorizeForOnce = DeviceAuthorizer.authorizeForOnce();
                    if (!TextUtils.isEmpty(authorizeForOnce)) {
                        arrayList.add(new KVPair("duid", authorizeForOnce));
                    }
                    NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
                    networkTimeOut.readTimout = BleManager.DEFAULT_SCAN_TIME;
                    networkTimeOut.connectionTimeout = FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS;
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(new KVPair("User-Identity", MobProductCollector.getUserIdentity()));
                    NLog instance2 = MobLog.getInstance();
                    instance2.mo29768d("Request: " + C2212a.f1921c + "\nHeaders: " + arrayList2 + "\nValues: " + arrayList, new Object[0]);
                    String httpGet = new NetworkHelper().httpGet(C2212a.f1921c, arrayList, arrayList2, networkTimeOut);
                    NLog instance3 = MobLog.getInstance();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Response: ");
                    sb.append(httpGet);
                    instance3.mo29768d(sb.toString(), new Object[0]);
                    Hashon hashon = new Hashon();
                    HashMap fromJson = hashon.fromJson(httpGet);
                    if (fromJson == null) {
                        throw new Throwable("Response is illegal: " + httpGet);
                    } else if ("200".equals(String.valueOf(fromJson.get("code")))) {
                        Object obj = fromJson.get(ShareConstants.WEB_DIALOG_PARAM_DATA);
                        if (obj != null) {
                            HashMap hashMap = (HashMap) obj;
                            if (hashMap != null) {
                                String str = (String) hashMap.get("content");
                                Long l = (Long) hashMap.get("timestamp");
                                if (!TextUtils.isEmpty(str)) {
                                    String a = C2212a.m1954b(l.longValue(), str);
                                    NLog instance4 = MobLog.getInstance();
                                    instance4.mo29768d("contentDe: " + a + " (ppms->ppNece)", new Object[0]);
                                    HashMap fromJson2 = hashon.fromJson(a);
                                    if (fromJson2 != null && !fromJson2.isEmpty()) {
                                        Integer num = (Integer) fromJson2.get("ppms");
                                        if (num != null) {
                                            boolean z = true;
                                            if (num.intValue() != 1) {
                                                z = false;
                                            }
                                            Boolean unused = C2212a.f1928j = Boolean.valueOf(z);
                                            C2308i.m2513a(C2212a.f1928j.booleanValue());
                                        }
                                        Integer num2 = (Integer) fromJson2.get("ppVersion");
                                        if (num2 != null) {
                                            int unused2 = C2212a.f1932n = num2.intValue();
                                            C2212a.m1965e(C2212a.f1932n);
                                            return;
                                        }
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            throw new Throwable("Response is illegal: " + httpGet);
                        }
                        throw new Throwable("Response is illegal: " + httpGet);
                    } else {
                        throw new Throwable("Response code is not 200: " + httpGet);
                    }
                } catch (Throwable th) {
                    MobLog.getInstance().mo29773e(th, "Request total switch error", new Object[0]);
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    /* renamed from: w */
    public static void m1983w() {
        try {
            DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("appkey", MobSDK.getAppkey()));
            arrayList.add(new KVPair("apppkg", instance.getPackageName()));
            arrayList.add(new KVPair("appver", instance.getAppVersionName()));
            arrayList.add(new KVPair("plat", String.valueOf(instance.getPlatformCode())));
            arrayList.add(new KVPair("networktype", instance.getDetailNetworkTypeForStatic()));
            String authorizeForOnce = DeviceAuthorizer.authorizeForOnce();
            if (!TextUtils.isEmpty(authorizeForOnce)) {
                arrayList.add(new KVPair("duid", authorizeForOnce));
            }
            NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
            networkTimeOut.readTimout = BleManager.DEFAULT_SCAN_TIME;
            networkTimeOut.connectionTimeout = FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS;
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new KVPair("User-Identity", MobProductCollector.getUserIdentity()));
            NLog instance2 = MobLog.getInstance();
            instance2.mo29768d("Request: " + f1922d + "\nHeaders: " + arrayList2 + "\nValues: " + arrayList, new Object[0]);
            String httpGet = new NetworkHelper().httpGet(f1922d, arrayList, arrayList2, networkTimeOut);
            NLog instance3 = MobLog.getInstance();
            StringBuilder sb = new StringBuilder();
            sb.append("Response: ");
            sb.append(httpGet);
            instance3.mo29768d(sb.toString(), new Object[0]);
            Hashon hashon = new Hashon();
            HashMap fromJson = hashon.fromJson(httpGet);
            if (fromJson == null) {
                throw new Throwable("Response is illegal: " + httpGet);
            } else if ("200".equals(String.valueOf(fromJson.get("code")))) {
                Object obj = fromJson.get(ShareConstants.WEB_DIALOG_PARAM_DATA);
                if (obj != null) {
                    HashMap hashMap = (HashMap) obj;
                    if (hashMap != null) {
                        String str = (String) hashMap.get("content");
                        Long l = (Long) hashMap.get("timestamp");
                        if (!TextUtils.isEmpty(str)) {
                            String b = m1954b(l.longValue(), str);
                            NLog instance4 = MobLog.getInstance();
                            instance4.mo29768d("contentDe: " + b + " (pprdms->clt, pprfms->func, pprsbs->cover, pprspw->dialog)", new Object[0]);
                            HashMap fromJson2 = hashon.fromJson(b);
                            if (fromJson2 != null && !fromJson2.isEmpty()) {
                                Integer num = (Integer) fromJson2.get("pprdms");
                                if (num != null) {
                                    f1934p = Boolean.valueOf(num.intValue() == 1);
                                    C2308i.m2530e(num.intValue());
                                }
                                Integer num2 = (Integer) fromJson2.get("pprfms");
                                if (num2 != null) {
                                    f1933o = Boolean.valueOf(num2.intValue() == 1);
                                    C2308i.m2526d(num2.intValue());
                                }
                                Integer num3 = (Integer) fromJson2.get("pprsbs");
                                if (num3 != null) {
                                    f1935q = Boolean.valueOf(num3.intValue() == 1);
                                    C2308i.m2534f(num3.intValue());
                                }
                                Integer num4 = (Integer) fromJson2.get("pprspw");
                                if (num4 != null) {
                                    f1936r = Boolean.valueOf(num4.intValue() == 1);
                                    C2308i.m2538g(num4.intValue());
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    throw new Throwable("Response is illegal: " + httpGet);
                }
                throw new Throwable("Response is illegal: " + httpGet);
            } else {
                throw new Throwable("Response code is not 200: " + httpGet);
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29773e(th, "Request total switch error", new Object[0]);
            f1934p = false;
            f1933o = true;
            f1935q = true;
            f1936r = true;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public static void m1960c(final boolean z, final OperationCallback<Void> operationCallback) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    C2212a.m1955b(0, z);
                    C2308i.m2509a((Boolean) null);
                    if (operationCallback != null) {
                        UIHandler.sendEmptyMessage(0, new Handler.Callback() {
                            public boolean handleMessage(Message message) {
                                operationCallback.onComplete(null);
                                return false;
                            }
                        });
                    }
                } catch (Throwable th) {
                    MobLog.getInstance().mo29773e(th, "Submit privacy grant result error", new Object[0]);
                    C2308i.m2509a(Boolean.valueOf(z));
                    if (operationCallback != null) {
                        UIHandler.sendEmptyMessage(0, new Handler.Callback() {
                            public boolean handleMessage(Message message) {
                                operationCallback.onFailure(th);
                                return false;
                            }
                        });
                    }
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static void m1955b(int i, boolean z) throws Throwable {
        int i2 = i + 1;
        try {
            DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("appkey", MobSDK.getAppkey()));
            arrayList.add(new KVPair("apppkg", instance.getPackageName()));
            arrayList.add(new KVPair("appver", instance.getAppVersionName()));
            arrayList.add(new KVPair("plat", String.valueOf(instance.getPlatformCode())));
            arrayList.add(new KVPair("networktype", instance.getDetailNetworkTypeForStatic()));
            String authorizeForOnce = DeviceAuthorizer.authorizeForOnce();
            if (!TextUtils.isEmpty(authorizeForOnce)) {
                arrayList.add(new KVPair("duid", authorizeForOnce));
            }
            arrayList.add(new KVPair("isAgreePp", String.valueOf(z)));
            NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
            networkTimeOut.readTimout = BleManager.DEFAULT_SCAN_TIME;
            networkTimeOut.connectionTimeout = FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS;
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new KVPair("User-Identity", MobProductCollector.getUserIdentity()));
            NLog instance2 = MobLog.getInstance();
            instance2.mo29768d("Request: " + f1923e + "\nHeaders: " + arrayList2 + "\nValues: " + arrayList, new Object[0]);
            String httpGet = new NetworkHelper().httpGet(f1923e, arrayList, arrayList2, networkTimeOut);
            NLog instance3 = MobLog.getInstance();
            StringBuilder sb = new StringBuilder();
            sb.append("Response: ");
            sb.append(httpGet);
            instance3.mo29768d(sb.toString(), new Object[0]);
            HashMap fromJson = new Hashon().fromJson(httpGet);
            if (fromJson == null) {
                if (i2 < 2) {
                    m1955b(i2, z);
                } else {
                    throw new Throwable("Response is illegal: " + httpGet);
                }
            }
            if ("200".equals(String.valueOf(fromJson.get("code")))) {
                return;
            }
            if (i2 < 2) {
                m1955b(i2, z);
                return;
            }
            throw new Throwable("Response code is not 200: " + httpGet);
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            if (i2 < 2) {
                m1955b(i2, z);
                return;
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static String m1954b(long j, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            String appkey = MobSDK.getAppkey();
            String packageName = DeviceHelper.getInstance(MobSDK.getContext()).getPackageName();
            return new String(Data.AES128Decode(Data.rawMD5(appkey + Config.TRACE_TODAY_VISIT_SPLIT + packageName + Config.TRACE_TODAY_VISIT_SPLIT + j), Base64.decode(str, 0)), "UTF-8");
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public static void m1965e(int i) {
        if (f1930l == -1) {
            f1930l = C2308i.m2566w();
        }
        if (f1931m == -1) {
            f1931m = C2308i.m2568y();
        }
        try {
            if (f1930l != i) {
                f1930l = new C2307h().mo29102b(2, (Locale) null).getPpVersion();
            }
            if (f1931m != i) {
                f1931m = new C2307h().mo29102b(1, (Locale) null).getPpVersion();
            }
        } catch (Throwable unused) {
            MobLog.getInstance().mo29768d("Update privacy policy err.", new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: x */
    public static int m1984x() {
        if (f1930l == -1) {
            f1930l = C2308i.m2566w();
        }
        if (f1931m == -1) {
            f1931m = C2308i.m2568y();
        }
        int i = f1930l;
        int i2 = f1931m;
        return i >= i2 ? i : i2;
    }
}
