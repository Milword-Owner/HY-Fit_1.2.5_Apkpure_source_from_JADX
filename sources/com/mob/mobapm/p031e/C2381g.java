package com.mob.mobapm.p031e;

import com.mob.MobSDK;
import com.mob.mobapm.p026b.C2345a;
import com.mob.tools.utils.DeviceHelper;
import java.util.HashMap;

/* renamed from: com.mob.mobapm.e.g */
public class C2381g {

    /* renamed from: com.mob.mobapm.e.g$a */
    static class C2382a extends Thread {
        C2382a() {
        }

        public void run() {
            super.run();
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("appKey", MobSDK.getAppkey());
                hashMap.put("appPkg", MobSDK.getContext().getPackageName());
                hashMap.put("appVersion", String.valueOf(DeviceHelper.getInstance(MobSDK.getContext()).getAppVersionName()));
                hashMap.put("plat", 1);
                hashMap.put("carrier", DeviceHelper.getInstance(MobSDK.getContext()).getCarrier());
                hashMap.put("factory", DeviceHelper.getInstance(MobSDK.getContext()).getManufacturer());
                hashMap.put("model", DeviceHelper.getInstance(MobSDK.getContext()).getModel());
                hashMap.put("systemVersion", DeviceHelper.getInstance(MobSDK.getContext()).getOSVersionName());
                hashMap.put("systemVersionInt", Integer.valueOf(DeviceHelper.getInstance(MobSDK.getContext()).getOSVersionInt()));
                hashMap.put("language", DeviceHelper.getInstance(MobSDK.getContext()).getOSLanguage());
                hashMap.put("serialno", DeviceHelper.getInstance(MobSDK.getContext()).getSerialno());
                hashMap.put("deviceId", DeviceHelper.getInstance(MobSDK.getContext()).getDeviceKey());
                C2345a.m2722e(hashMap);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: a */
    public static void m2824a() {
        new C2382a().start();
    }
}
