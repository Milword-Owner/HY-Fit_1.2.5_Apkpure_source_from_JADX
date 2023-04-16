package com.mob.mobapm.core;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.mobapm.p026b.C2345a;
import com.mob.mobapm.p030d.C2373a;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.DeviceHelper;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.mob.mobapm.core.f */
public class C2359f {

    /* renamed from: a */
    private static volatile C2359f f2268a;

    /* renamed from: com.mob.mobapm.core.f$a */
    class C2360a implements Runnable {
        C2360a(C2359f fVar) {
        }

        public void run() {
            try {
                String c = C2345a.m2717c();
                String valueOf = String.valueOf(DeviceHelper.getInstance(MobSDK.getContext()).getAppVersion());
                if (TextUtils.isEmpty(c) || valueOf.equals(c)) {
                    HashMap<String, Object> b = C2345a.m2714b();
                    if (b == null) {
                        return;
                    }
                    if (!b.isEmpty()) {
                        HashMap hashMap = new HashMap();
                        ArrayList arrayList = new ArrayList();
                        arrayList.addAll(b.values());
                        hashMap.put("bundleName", MobSDK.getContext().getPackageName());
                        hashMap.put("uploadTime", Long.valueOf(System.currentTimeMillis()));
                        hashMap.put("errorStack", arrayList);
                        Object a = C2357d.m2771a(hashMap, C2356c.f2253g);
                        NLog a2 = C2373a.m2807a();
                        a2.mo29768d("APM: upload anr success. object:" + a, new Object[0]);
                        if (a instanceof HashMap) {
                            int intValue = ((Integer) ((HashMap) a).get("code")).intValue();
                            if (intValue == 200 || intValue == 4131002) {
                                C2345a.m2733k();
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                C2345a.m2734l();
                C2345a.m2733k();
            } catch (Throwable th) {
                NLog a3 = C2373a.m2807a();
                a3.mo29768d("APM: upload anr fail. error:" + th, new Object[0]);
            }
        }
    }

    private C2359f() {
    }

    /* renamed from: b */
    public static C2359f m2784b() {
        if (f2268a == null) {
            synchronized (C2359f.class) {
                if (f2268a == null) {
                    f2268a = new C2359f();
                }
            }
        }
        return f2268a;
    }

    /* renamed from: a */
    public void mo29256a() {
        C2358e.m2781b().mo29255a(new C2360a(this));
    }
}
