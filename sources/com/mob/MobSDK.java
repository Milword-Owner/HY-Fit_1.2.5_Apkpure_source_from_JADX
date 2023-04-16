package com.mob;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.mob.MobUser;
import com.mob.PrivacyPolicy;
import com.mob.commons.C2212a;
import com.mob.commons.C2262b;
import com.mob.commons.C2305f;
import com.mob.commons.C2307h;
import com.mob.commons.C2308i;
import com.mob.commons.C2310j;
import com.mob.commons.InternationalDomain;
import com.mob.commons.MobProduct;
import com.mob.commons.MobProductCollector;
import com.mob.commons.authorize.C2255a;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.commons.dialog.C2297a;
import com.mob.commons.dialog.PolicyThrowable;
import com.mob.commons.dialog.entity.InternalPolicyUi;
import com.mob.commons.dialog.entity.MobPolicyUi;
import com.mob.commons.logcollector.DefaultLogsCollector;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.UIHandler;
import java.util.HashMap;
import java.util.Locale;

public class MobSDK implements PublicMemberKeeper {
    public static final int CHANNEL_APICLOUD = 5;
    public static final int CHANNEL_COCOS = 1;
    public static final int CHANNEL_FLUTTER = 4;
    public static final int CHANNEL_JS = 3;
    public static final int CHANNEL_NATIVE = 0;
    public static final int CHANNEL_QUICKSDK = 6;
    public static final int CHANNEL_UNIAPP = 7;
    public static final int CHANNEL_UNITY = 2;
    public static final int POLICY_TYPE_TXT = 2;
    public static final int POLICY_TYPE_URL = 1;
    public static final int SDK_VERSION_CODE;
    public static final String SDK_VERSION_NAME;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static Context f1570a = null;

    /* renamed from: b */
    private static String f1571b = null;

    /* renamed from: c */
    private static String f1572c = null;

    /* renamed from: d */
    private static volatile boolean f1573d = false;

    /* renamed from: e */
    private static InternationalDomain f1574e = null;

    /* renamed from: f */
    private static volatile boolean f1575f = false;

    /* renamed from: g */
    private static volatile boolean f1576g = false;

    /* renamed from: h */
    private static volatile boolean f1577h = false;

    public static final Boolean isGpAvailable() {
        return null;
    }

    public static final boolean isGppVer() {
        return false;
    }

    @Deprecated
    public static void setAllowDialog(boolean z) {
    }

    @Deprecated
    public static void setPolicyUi(MobPolicyUi mobPolicyUi) {
    }

    static {
        int i;
        String str = "1.0.0";
        try {
            str = "2020-12-17".replace("-", ".");
            i = Integer.parseInt("2020-12-17".replace("-", ""));
        } catch (Throwable unused) {
            i = 1;
        }
        SDK_VERSION_CODE = i;
        SDK_VERSION_NAME = str;
    }

    public static synchronized void init(Context context) {
        synchronized (MobSDK.class) {
            init(context, (String) null, (String) null);
        }
    }

    public static synchronized void init(Context context, String str) {
        synchronized (MobSDK.class) {
            init(context, str, (String) null);
        }
    }

    public static synchronized void init(Context context, String str, String str2) {
        synchronized (MobSDK.class) {
            if (f1570a == null) {
                f1570a = context.getApplicationContext();
                m1869a(str, str2);
                m1873e();
                m1871c();
                m1872d();
                m1876h();
            } else if (!TextUtils.isEmpty(str)) {
                boolean isEmpty = TextUtils.isEmpty(f1571b);
                f1571b = str;
                f1572c = str2;
                if (isEmpty) {
                    C2262b.m2289an();
                }
            }
        }
    }

    /* renamed from: c */
    private static void m1871c() {
        ((DefaultLogsCollector) NLog.setDefaultCollector(DefaultLogsCollector.get())).addSDK("MOBSDK", SDK_VERSION_CODE);
        try {
            NLog instance = NLog.getInstance("MOBSDK");
            instance.mo29768d("===============================", new Object[0]);
            instance.mo29768d("MobCommons name: " + SDK_VERSION_NAME + ", code: " + SDK_VERSION_CODE, new Object[0]);
            instance.mo29768d("===============================", new Object[0]);
        } catch (Throwable unused) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        r0 = true;
     */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean m1872d() {
        /*
            r0 = 0
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ Throwable -> 0x0047 }
            java.lang.StackTraceElement[] r1 = r1.getStackTrace()     // Catch:{ Throwable -> 0x0047 }
            r2 = 1
            if (r1 == 0) goto L_0x0047
            int r3 = r1.length     // Catch:{ Throwable -> 0x0047 }
            if (r3 <= 0) goto L_0x0047
            int r3 = r1.length     // Catch:{ Throwable -> 0x0047 }
            r4 = 0
        L_0x0011:
            if (r4 >= r3) goto L_0x0047
            r5 = r1[r4]     // Catch:{ Throwable -> 0x0047 }
            java.lang.String r6 = r5.getClassName()     // Catch:{ Throwable -> 0x0047 }
            java.lang.String r7 = "android.app.Instrumentation"
            boolean r7 = r7.equals(r6)     // Catch:{ Throwable -> 0x0047 }
            if (r7 == 0) goto L_0x002f
            java.lang.String r6 = "callApplicationOnCreate"
            java.lang.String r5 = r5.getMethodName()     // Catch:{ Throwable -> 0x0047 }
            boolean r5 = r6.equals(r5)     // Catch:{ Throwable -> 0x0047 }
            if (r5 == 0) goto L_0x0044
        L_0x002d:
            r0 = 1
            goto L_0x0047
        L_0x002f:
            java.lang.String r7 = "android.app.ActivityThread"
            boolean r6 = r7.equals(r6)     // Catch:{ Throwable -> 0x0047 }
            if (r6 == 0) goto L_0x0044
            java.lang.String r6 = "handleBindApplication"
            java.lang.String r5 = r5.getMethodName()     // Catch:{ Throwable -> 0x0047 }
            boolean r5 = r6.equals(r5)     // Catch:{ Throwable -> 0x0047 }
            if (r5 == 0) goto L_0x0044
            goto L_0x002d
        L_0x0044:
            int r4 = r4 + 1
            goto L_0x0011
        L_0x0047:
            if (r0 != 0) goto L_0x0050
            java.lang.String r1 = "MobSDK"
            java.lang.String r2 = "Please invoke MobSDK.init(context) method in your application onCreate()"
            android.util.Log.e(r1, r2)
        L_0x0050:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.MobSDK.m1872d():boolean");
    }

    /* renamed from: a */
    private static void m1869a(String str, String str2) {
        if (str == null || str2 == null) {
            Bundle bundle = null;
            try {
                bundle = f1570a.getPackageManager().getPackageInfo(f1570a.getPackageName(), 128).applicationInfo.metaData;
            } catch (Throwable unused) {
            }
            if (str == null && bundle != null) {
                str = bundle.getString("Mob-AppKey");
            }
            if (str2 == null && bundle != null) {
                str2 = bundle.getString("Mob-AppSecret");
            }
            if (str2 == null && bundle != null) {
                str2 = bundle.getString("Mob-AppSeret");
            }
        }
        f1571b = str;
        f1572c = str2;
    }

    /* renamed from: e */
    private static void m1873e() {
        Bundle bundle;
        if (f1570a == null) {
            Log.e("MobSDK", "Please invoke MobSDK.init(context) method firstly.");
        } else if (!f1573d) {
            f1573d = true;
            String str = null;
            try {
                bundle = f1570a.getPackageManager().getPackageInfo(f1570a.getPackageName(), 128).applicationInfo.metaData;
            } catch (Throwable unused) {
                bundle = null;
            }
            if (f1574e == null) {
                if (bundle != null) {
                    try {
                        f1574e = InternationalDomain.domainOf(bundle.getString("Domain"));
                    } catch (Throwable unused2) {
                        f1574e = InternationalDomain.DEFAULT;
                    }
                } else {
                    f1574e = InternationalDomain.DEFAULT;
                }
            }
            if (bundle != null) {
                try {
                    str = bundle.getString("Mob-Https");
                } catch (Throwable unused3) {
                }
                if (str == null) {
                    try {
                        f1575f = bundle.getBoolean("Mob-Https");
                    } catch (Throwable unused4) {
                    }
                } else {
                    f1575f = "yes".equalsIgnoreCase(str);
                }
            }
            if (bundle != null) {
                try {
                    f1576g = bundle.getBoolean("Mob-PpNecessary", false);
                } catch (Throwable unused5) {
                }
            }
            if (bundle != null) {
                try {
                    f1577h = bundle.getBoolean("Mob-V6", false);
                } catch (Throwable unused6) {
                }
            }
            C2308i.m2504I();
        }
    }

    /* renamed from: f */
    private static boolean m1874f() {
        return C2262b.m2259Z();
    }

    /* renamed from: g */
    private static boolean m1875g() {
        return C2262b.m2305b();
    }

    public static InternationalDomain getDomain() {
        if (f1574e == null) {
            m1873e();
        }
        InternationalDomain internationalDomain = f1574e;
        return internationalDomain == null ? InternationalDomain.DEFAULT : internationalDomain;
    }

    @Deprecated
    public static void setDomain(InternationalDomain internationalDomain) {
        f1574e = internationalDomain;
    }

    public static boolean checkForceHttps() {
        m1873e();
        return f1575f;
    }

    public static boolean checkPpNecessary() {
        m1873e();
        return f1576g;
    }

    public static boolean checkV6() {
        m1873e();
        return f1577h;
    }

    public static String checkRequestUrl(String str) {
        return C2310j.m2571a(str);
    }

    public static String dynamicModifyUrl(String str) {
        return C2310j.m2573b(str);
    }

    /* renamed from: h */
    private static void m1876h() {
        MobProductCollector.syncInit();
        try {
            new Thread() {
                public void run() {
                    try {
                        MobProductCollector.collect();
                        C2255a.m2204a(MobSDK.f1570a);
                        DeviceAuthorizer.authorize((MobProduct) null);
                        MobSDK.m1877i();
                    } catch (Throwable th) {
                        MobLog.getInstance().mo29787w(th);
                    }
                }
            }.start();
            C2212a.m1948a();
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: i */
    public static void m1877i() {
        if (C2308i.m2502G() == 0) {
            C2308i.m2548j(System.currentTimeMillis());
        }
    }

    public static Context getContext() {
        Context context;
        if (f1570a == null) {
            try {
                Object currentActivityThread = DeviceHelper.currentActivityThread();
                if (!(currentActivityThread == null || (context = (Context) ReflectHelper.invokeInstanceMethod(currentActivityThread, "getApplication", new Object[0])) == null)) {
                    init(context);
                }
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
            }
        }
        return f1570a;
    }

    public static final boolean isMob() {
        boolean z;
        boolean c = C2212a.m1961c();
        NLog instance = MobLog.getInstance();
        instance.mo29768d("isMob(). ppNece: " + c, new Object[0]);
        if (c) {
            boolean d = C2212a.m1963d();
            NLog instance2 = MobLog.getInstance();
            instance2.mo29768d("isMob(). isAgrPp: " + d, new Object[0]);
            if (d) {
                z = m1875g();
            } else {
                boolean j = C2212a.m1970j();
                NLog instance3 = MobLog.getInstance();
                instance3.mo29768d("isMob(). cltSch: " + j, new Object[0]);
                z = j ? m1875g() : false;
            }
        } else {
            z = m1875g();
        }
        NLog instance4 = MobLog.getInstance();
        instance4.mo29768d("isMob(). isMob: " + z, new Object[0]);
        return z;
    }

    public static final boolean isForb() {
        boolean z;
        boolean c = C2212a.m1961c();
        NLog instance = MobLog.getInstance();
        instance.mo29768d("isForb(). ppNece: " + c, new Object[0]);
        if (c) {
            boolean d = C2212a.m1963d();
            NLog instance2 = MobLog.getInstance();
            instance2.mo29768d("isForb(). isAgrPp: " + d, new Object[0]);
            if (d) {
                z = m1874f();
            } else {
                boolean i = C2212a.m1969i();
                NLog instance3 = MobLog.getInstance();
                instance3.mo29768d("isForb(). funcStch: " + i, new Object[0]);
                z = i ? m1874f() : true;
            }
        } else {
            z = m1874f();
        }
        NLog instance4 = MobLog.getInstance();
        instance4.mo29768d("isForb(). isForb: " + z, new Object[0]);
        return z;
    }

    public static final int isAuth() {
        int i;
        boolean c = C2212a.m1961c();
        NLog instance = MobLog.getInstance();
        instance.mo29768d("isAuth(). ppNece: " + c, new Object[0]);
        if (c) {
            Boolean k = C2212a.m1971k();
            NLog instance2 = MobLog.getInstance();
            instance2.mo29768d("isAuth(). isAgreePp: " + k, new Object[0]);
            i = k == null ? 0 : k.booleanValue() ? 1 : -1;
        } else {
            i = 2;
        }
        NLog instance3 = MobLog.getInstance();
        instance3.mo29768d("isAuth(). isAuth: " + i + "[2:ppms-off，1:agr，0:unkwn，-1:disagr]", new Object[0]);
        return i;
    }

    public static String getAppkey() {
        return f1571b;
    }

    public static String getAppSecret() {
        return f1572c;
    }

    public static void setChannel(MobProduct mobProduct, int i) {
        C2305f.m2476a().mo29099a(mobProduct, i);
    }

    public static PrivacyPolicy getPrivacyPolicy(int i) {
        return getPrivacyPolicy(i, (Locale) null);
    }

    public static PrivacyPolicy getPrivacyPolicy(int i, Locale locale) {
        int i2 = 2;
        if (i == 1) {
            i2 = 1;
        }
        try {
            return new C2307h().mo29101a(i2, locale);
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    public static void getPrivacyPolicyAsync(int i, PrivacyPolicy.OnPolicyListener onPolicyListener) {
        getPrivacyPolicyAsync(i, (Locale) null, onPolicyListener);
    }

    public static void getPrivacyPolicyAsync(final int i, final Locale locale, final PrivacyPolicy.OnPolicyListener onPolicyListener) {
        if (onPolicyListener != null) {
            new Thread(new Runnable() {
                public void run() {
                    final PrivacyPolicy a;
                    int i = 2;
                    try {
                        if (i == 1) {
                            i = 1;
                        }
                        a = new C2307h().mo29101a(i, locale);
                        UIHandler.sendEmptyMessage(0, new Handler.Callback() {
                            public boolean handleMessage(Message message) {
                                onPolicyListener.onComplete(a);
                                return false;
                            }
                        });
                    } catch (Throwable th) {
                        try {
                            MobLog.getInstance().mo29769d(th);
                            UIHandler.sendEmptyMessage(0, new Handler.Callback() {
                                public boolean handleMessage(Message message) {
                                    onPolicyListener.onFailure(th);
                                    return false;
                                }
                            });
                        } catch (Throwable th2) {
                            MobLog.getInstance().mo29769d(th2);
                            onPolicyListener.onFailure(th);
                        }
                    }
                }
            }).start();
        }
    }

    public static void submitPolicyGrantResult(final boolean z, final OperationCallback<Void> operationCallback) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    C2212a.m1951a(z, (OperationCallback<Void>) operationCallback);
                } catch (Throwable th) {
                    MobLog.getInstance().mo29772e(th);
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

    @Deprecated
    public static void submitPermissionGrantResult(boolean z, MobProduct mobProduct, final OperationCallback<Void> operationCallback) {
        if (operationCallback != null) {
            UIHandler.sendEmptyMessage(0, new Handler.Callback() {
                public boolean handleMessage(Message message) {
                    operationCallback.onComplete(null);
                    return false;
                }
            });
        }
    }

    public static void canIContinueBusiness(final MobProduct mobProduct, final InternalPolicyUi internalPolicyUi, final OperationCallback<Boolean> operationCallback) {
        if (operationCallback != null) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        if (mobProduct == null) {
                            operationCallback.onFailure(new PolicyThrowable("MobProduct can not be null"));
                        }
                        C2297a.m2464a().mo29075a(mobProduct, internalPolicyUi, operationCallback);
                    } catch (Throwable th) {
                        MobLog.getInstance().mo29772e(th);
                        operationCallback.onFailure(th);
                    }
                }
            }).start();
            return;
        }
        throw new IllegalArgumentException("callback can not be null");
    }

    public static synchronized void setUser(String str, String str2, String str3, HashMap<String, Object> hashMap) {
        synchronized (MobSDK.class) {
            setUser(str, str2, str3, hashMap, (String) null);
        }
    }

    public static synchronized void setUser(String str, String str2, String str3, HashMap<String, Object> hashMap, String str4) {
        synchronized (MobSDK.class) {
            MobUser.m1930a(str, str2, str3, hashMap, str4);
        }
    }

    public static synchronized void clearUser() {
        synchronized (MobSDK.class) {
            MobUser.m1927a();
        }
    }

    public static synchronized void addUserWatcher(MobUser.UserWatcher userWatcher) {
        synchronized (MobSDK.class) {
            if (userWatcher != null) {
                MobUser.m1929a(userWatcher);
            }
        }
    }

    public static synchronized void removeUserWatcher(MobUser.UserWatcher userWatcher) {
        synchronized (MobSDK.class) {
            if (userWatcher != null) {
                MobUser.m1934b(userWatcher);
            }
        }
    }

    public static synchronized void getUser(final MobUser.OnUserGotListener onUserGotListener) {
        synchronized (MobSDK.class) {
            MobUser.m1928a((MobUser.OnUserGotListener) new MobUser.OnUserGotListener() {
                public void onUserGot(MobUser mobUser) {
                    MobUser.OnUserGotListener onUserGotListener = onUserGotListener;
                    if (onUserGotListener != null) {
                        if (mobUser.getMobUserId() == null) {
                            mobUser = null;
                        }
                        onUserGotListener.onUserGot(mobUser);
                    }
                }
            });
        }
    }

    public static HashMap<String, String> exchangeIds(String[] strArr) {
        return MobUser.m1926a(strArr);
    }
}
