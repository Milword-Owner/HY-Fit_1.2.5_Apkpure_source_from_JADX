package com.mob.guard;

import com.mob.MobCommunicator;
import com.mob.MobSDK;
import com.mob.commons.MOBGUARD;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.tools.utils.DeviceHelper;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.mob.guard.d */
public class C2334d {

    /* renamed from: a */
    protected static String f2208a;

    /* renamed from: b */
    private static String f2209b;

    /* renamed from: c */
    private static MobCommunicator f2210c;

    /* renamed from: d */
    private static final Object f2211d = new Object();

    static {
        try {
            f2208a = MobSDK.checkRequestUrl("sdk.guard.mob.com");
        } catch (Throwable th) {
            C2335e.m2675b().mo29772e(th);
        }
    }

    /* renamed from: a */
    public static <T> T m2669a(List<String> list, String str) throws Throwable {
        HashMap<String, Object> a = m2671a();
        a.put("pkgList", list);
        a.put("guardId", str);
        a.put("brand", DeviceHelper.getInstance(MobSDK.getContext()).getManufacturer());
        a.put("model", DeviceHelper.getInstance(MobSDK.getContext()).getModel());
        a.put("modelVersion", DeviceHelper.getInstance(MobSDK.getContext()).getOSVersionName());
        return m2668a("/guard/switchV2", a);
    }

    /* renamed from: a */
    public static <T> T m2670a(List<String> list, List<String> list2, int i, String str) throws Throwable {
        HashMap<String, Object> a = m2671a();
        a.put("guardSuccessList", list);
        a.put("guardFailList", list2);
        a.put("guardType", Integer.valueOf(i));
        a.put("workId", str);
        return m2668a("/guard/uploadV2", a);
    }

    /* renamed from: a */
    private static HashMap<String, Object> m2671a() {
        DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("appkey", MobSDK.getAppkey());
        hashMap.put("appver", Integer.valueOf(instance.getAppVersion()));
        hashMap.put("platVersion", instance.getOSVersionName());
        hashMap.put("apppkg", MobSDK.getContext().getPackageName());
        hashMap.put("sdkver", Integer.valueOf(MobGuard.SDK_VERSION_CODE));
        hashMap.put("duid", m2672b());
        hashMap.put("product", 1);
        hashMap.put("plat", 1);
        return hashMap;
    }

    /* renamed from: b */
    private static String m2672b() {
        synchronized (f2211d) {
            if (f2209b == null) {
                f2209b = DeviceAuthorizer.authorize(new MOBGUARD());
            }
        }
        return f2209b;
    }

    /* renamed from: c */
    private static synchronized MobCommunicator m2673c() {
        MobCommunicator mobCommunicator;
        synchronized (C2334d.class) {
            if (f2210c == null) {
                f2210c = new MobCommunicator(1024, "009cbd92ccef123be840deec0c6ed0547194c1e471d11b6f375e56038458fb18833e5bab2e1206b261495d7e2d1d9e5aa859e6d4b671a8ca5d78efede48e291a3f", "1dfd1d615cb891ce9a76f42d036af7fce5f8b8efaa11b2f42590ecc4ea4cff28f5f6b0726aeb76254ab5b02a58c1d5b486c39d9da1a58fa6ba2f22196493b3a4cbc283dcf749bf63679ee24d185de70c8dfe05605886c9b53e9f569082eabdf98c4fb0dcf07eb9bb3e647903489ff0b5d933bd004af5be4a1022fdda41f347f1");
            }
            mobCommunicator = f2210c;
        }
        return mobCommunicator;
    }

    /* renamed from: a */
    private static <T> T m2668a(String str, HashMap<String, Object> hashMap) throws Throwable {
        MobCommunicator c = m2673c();
        return c.requestSynchronized(hashMap, f2208a + str, false);
    }
}
