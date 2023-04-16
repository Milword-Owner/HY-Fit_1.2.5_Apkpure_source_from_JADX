package com.mob.mobapm.core;

import android.text.TextUtils;
import com.baidubce.http.Headers;
import com.facebook.share.internal.ShareConstants;
import com.mob.MobSDK;
import com.mob.commons.MOBAPM;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.mobapm.MobAPM;
import com.mob.mobapm.internal.APMMobCommunicator;
import com.mob.mobapm.p025a.C2343a;
import com.mob.mobapm.p026b.C2345a;
import com.mob.mobapm.p030d.C2373a;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/* renamed from: com.mob.mobapm.core.d */
public class C2357d {

    /* renamed from: a */
    private static String f2257a = "http://api.data.sentinel.mob.com";

    /* renamed from: b */
    private static String f2258b = "http://api.manager.sentinel.mob.com";

    /* renamed from: c */
    private static String f2259c = "http://api.config.sentinel.mob.com";

    /* renamed from: d */
    private static String f2260d;

    /* renamed from: e */
    private static final Object f2261e = new Object();

    /* renamed from: f */
    private static APMMobCommunicator f2262f;

    /* renamed from: g */
    public static final Hashon f2263g = new Hashon();

    /* renamed from: h */
    private static HashMap<String, Object> f2264h;

    /* renamed from: i */
    private static final Object f2265i = new Object();

    static {
        try {
            f2257a = MobSDK.checkRequestUrl(f2257a);
            f2258b = MobSDK.checkRequestUrl(f2258b);
            f2259c = MobSDK.checkRequestUrl(f2259c);
        } catch (Throwable th) {
            NLog a = C2373a.m2807a();
            a.mo29775i("APM: replace server url from commons has error:" + th, new Object[0]);
        }
        m2772a();
    }

    /* renamed from: a */
    private static byte[] m2772a() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeLong(new Random().nextLong());
            String byteToHex = Data.byteToHex(byteArrayOutputStream.toByteArray());
            if (byteToHex.length() == 16) {
                byte[] bytes = byteToHex.getBytes();
                try {
                    dataOutputStream.close();
                } catch (Throwable th) {
                    C2373a.m2807a().mo29769d(th);
                }
                return bytes;
            }
            dataOutputStream.close();
            return "ar1xcsbglilzpjs5".getBytes();
        } catch (Throwable th2) {
            C2373a.m2807a().mo29769d(th2);
        }
    }

    /* renamed from: b */
    public static synchronized APMMobCommunicator m2773b() {
        APMMobCommunicator aPMMobCommunicator;
        synchronized (C2357d.class) {
            if (f2262f == null) {
                f2262f = new APMMobCommunicator(1024, "930665c1fb1db3ce89aae20f9cfe049b57436060f045840870702fa444694cdcde320d6d06f58a3f6f0234ecea073069bc6e82ec1b51f96852bf44447187bf1f", "1cb7ded4a8927b030cc6a34f692c59fcdf3135a3a477227b0115d1236f9fedeaae79cf3fb2dce4b95279623917724064f2d36fbe998453bf4b864f8b0afaf6372b4a04b3dbb1bbe1d3cf0f46d2b0cf92645b1224b007379aa4513d3a37df57aac93e9b9fa12f80dff3023f06e1d975498a647ba7222f8608111c89d3a77d43cf");
            }
            aPMMobCommunicator = f2262f;
        }
        return aPMMobCommunicator;
    }

    /* renamed from: c */
    private static void m2776c() {
        synchronized (f2265i) {
            try {
                HashMap hashMap = new HashMap();
                hashMap.putAll(C2345a.m2721e());
                hashMap.remove("language");
                hashMap.remove("serialno");
                hashMap.put("sdkVersion", MOBAPM.SDK_VERSION_NAME);
                hashMap.put("sdkVersionInt", Integer.valueOf(MOBAPM.SDK_VERSION_CODE));
                APMMobCommunicator b = m2773b();
                Object a = b.mo29284a(f2259c + "/config", f2263g.fromHashMap(hashMap), false);
                if (a instanceof String) {
                    HashMap<String, Object> hashMap2 = (HashMap) f2263g.fromJson((String) a).get(ShareConstants.WEB_DIALOG_PARAM_DATA);
                    f2264h = hashMap2;
                    if (hashMap2 != null && !hashMap2.isEmpty()) {
                        if (!f2264h.containsKey("openSentinel")) {
                            return;
                        }
                        if (((Boolean) f2264h.get("openSentinel")).booleanValue()) {
                            if (f2264h.containsKey("openDialingTest")) {
                                boolean booleanValue = ((Boolean) f2264h.get("openDialingTest")).booleanValue();
                                C2343a.m2707b().mo29162a(booleanValue);
                                if (!booleanValue) {
                                    return;
                                }
                            }
                            ArrayList arrayList = new ArrayList();
                            List<HashMap> list = (List) f2264h.get("rules");
                            if (list != null && !list.isEmpty()) {
                                for (HashMap hashMap3 : list) {
                                    hashMap3.remove("id");
                                    hashMap3.remove("appKey");
                                    arrayList.add(hashMap3);
                                }
                                f2264h.put("rules", arrayList);
                            }
                            List<HashMap> list2 = (List) f2264h.get("dialingTasks");
                            if (list2 != null) {
                                if (!list2.isEmpty()) {
                                    HashMap hashMap4 = new HashMap();
                                    for (HashMap hashMap5 : list2) {
                                        hashMap4.put(String.valueOf(hashMap5.get("id")), hashMap5);
                                    }
                                    C2345a.m2730i(hashMap4);
                                    C2343a.m2707b().mo29161a();
                                }
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                NLog a2 = C2373a.m2807a();
                a2.mo29768d("APM:config request error:" + th.getMessage(), new Object[0]);
            }
        }
    }

    /* renamed from: d */
    public static Object m2777d(HashMap<String, Object> hashMap) throws Throwable {
        if (hashMap != null) {
            hashMap.putAll(C2345a.m2721e());
            hashMap.remove("serialno");
        }
        if (!C2356c.f2254h) {
            return -1;
        }
        return m2767a(f2257a + "/socket-log", hashMap, false);
    }

    /* renamed from: e */
    public static String m2780e() {
        synchronized (f2261e) {
            if (f2260d == null) {
                f2260d = DeviceAuthorizer.authorize(new MOBAPM());
            }
        }
        return f2260d;
    }

    /* renamed from: b */
    public static Object m2774b(HashMap<String, Object> hashMap) throws Throwable {
        if (hashMap != null) {
            hashMap.putAll(C2345a.m2721e());
        }
        if (!C2356c.f2251e) {
            return -1;
        }
        return m2767a(f2257a + "/app-log", hashMap, false);
    }

    /* renamed from: d */
    public static HashMap<String, Object> m2778d() {
        HashMap<String, Object> hashMap = f2264h;
        if (hashMap == null || hashMap.isEmpty()) {
            m2776c();
        }
        return f2264h;
    }

    /* renamed from: e */
    public static Object m2779e(HashMap<String, Object> hashMap) throws Throwable {
        if (hashMap != null) {
            hashMap.putAll(C2345a.m2721e());
        }
        if (!C2356c.f2251e) {
            return -1;
        }
        return m2767a(f2257a + "/http-log", hashMap, false);
    }

    /* renamed from: a */
    protected static Object m2767a(String str, HashMap<String, Object> hashMap, boolean z) throws Throwable {
        HashMap<String, Object> hashMap2 = f2264h;
        if ((hashMap2 == null || hashMap2.isEmpty()) && !str.contains("config")) {
            m2776c();
        }
        return m2770a(hashMap, str, false, z);
    }

    /* renamed from: a */
    private static Object m2770a(HashMap<String, Object> hashMap, String str, boolean z, boolean z2) throws Throwable {
        if (!MobAPM.goldenKey) {
            return new Throwable("Mob Service forbidden");
        }
        try {
            NLog a = C2373a.m2807a();
            a.mo29768d("APM:请求地址和参数>>>" + str + " -- " + hashMap, new Object[0]);
            Object a2 = m2773b().mo29286a(hashMap, str, z, z2);
            NLog a3 = C2373a.m2807a();
            a3.mo29768d("APM:请求返回结果>>>" + str + " -- " + a2, new Object[0]);
            return a2;
        } catch (Throwable th) {
            NLog a4 = C2373a.m2807a();
            a4.mo29768d("APM:request error:" + th.getMessage(), new Object[0]);
            throw th;
        }
    }

    /* renamed from: a */
    public static Object m2771a(HashMap<String, Object> hashMap, boolean z) throws Throwable {
        if (!MobAPM.goldenKey) {
            return new Throwable("APM: Service closed");
        }
        if (hashMap != null) {
            hashMap.putAll(C2345a.m2721e());
        }
        hashMap.put("sdkVersion", MOBAPM.SDK_VERSION_NAME);
        hashMap.put("sdkVersionInt", Integer.valueOf(MOBAPM.SDK_VERSION_CODE));
        hashMap.put("appVersion", DeviceHelper.getInstance(MobSDK.getContext()).getAppVersionName() + "(" + DeviceHelper.getInstance(MobSDK.getContext()).getAppVersion() + ")");
        hashMap.remove("serialno");
        if (!z) {
            return -1;
        }
        return m2767a(f2257a + "/android/error-stack-msg", hashMap, false);
    }

    /* renamed from: a */
    private static Object m2766a(String str) throws Throwable {
        if (MobAPM.goldenKey) {
            return m2773b().mo29290a(str);
        }
        throw new Throwable("APM: Service closed");
    }

    /* renamed from: a */
    private static Object m2769a(HashMap<String, Object> hashMap, String str, boolean z) throws Throwable {
        Object a;
        if (!MobAPM.goldenKey) {
            return new Throwable("APM: Service closed");
        }
        try {
            String str2 = "";
            String str3 = hashMap.containsKey("param") ? (String) hashMap.get("param") : str2;
            String str4 = hashMap.containsKey("header") ? (String) hashMap.get("header") : str2;
            if (hashMap.containsKey("body")) {
                str2 = (String) hashMap.get("body");
            }
            int intValue = hashMap.containsKey("postType") ? ((Integer) hashMap.get("postType")).intValue() : 0;
            if (z) {
                a = m2773b().mo29291b(str, str4, false);
            } else {
                HashMap hashMap2 = new HashMap();
                if (intValue == 2) {
                    hashMap2.put(Headers.CONTENT_TYPE, "application/json");
                    a = m2773b().mo29292b(str, false, hashMap2, str2);
                } else if (intValue != 1) {
                    return null;
                } else {
                    a = m2773b().mo29285a(str, false, (HashMap<String, String>) hashMap2, str3);
                }
            }
            return a;
        } catch (Throwable th) {
            NLog a2 = C2373a.m2807a();
            a2.mo29768d("APM:radar reqeust error:" + th.getMessage(), new Object[0]);
            throw th;
        }
    }

    /* renamed from: a */
    public static Object m2768a(HashMap<String, Object> hashMap) throws Throwable {
        String str = (String) hashMap.get("url");
        int intValue = ((Integer) hashMap.get("requestType")).intValue();
        String str2 = (String) hashMap.get("requestMethod");
        if (intValue == 2) {
            if (!MobAPM.goldenKey || TextUtils.isEmpty(str)) {
                return -1;
            }
            return m2766a(str);
        } else if (intValue != 1) {
            return null;
        } else {
            if (MobAPM.goldenKey) {
                return m2769a(hashMap, str, "GET".equals(str2));
            }
            return -1;
        }
    }

    /* renamed from: c */
    public static Object m2775c(HashMap<String, Object> hashMap) throws Throwable {
        if (!MobAPM.goldenKey) {
            return -1;
        }
        return m2767a(f2258b + "/dialing-task/result", hashMap, false);
    }
}
