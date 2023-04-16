package com.mob.mobapm.core;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.mobapm.p026b.C2345a;
import com.mob.mobapm.p030d.C2373a;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.DeviceHelper;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.mob.mobapm.core.g */
public class C2361g {

    /* renamed from: a */
    private static volatile C2361g f2269a;

    /* renamed from: com.mob.mobapm.core.g$a */
    class C2362a implements Runnable {
        C2362a(C2361g gVar) {
        }

        public void run() {
            try {
                String d = C2345a.m2719d();
                String valueOf = String.valueOf(DeviceHelper.getInstance(MobSDK.getContext()).getAppVersion());
                if (TextUtils.isEmpty(d) || valueOf.equals(d)) {
                    HashMap<String, Object> h = C2345a.m2727h();
                    if (h == null) {
                        return;
                    }
                    if (!h.isEmpty()) {
                        HashMap hashMap = new HashMap();
                        ArrayList arrayList = new ArrayList();
                        arrayList.addAll(h.values());
                        hashMap.put("bundleName", MobSDK.getContext().getPackageName());
                        hashMap.put("uploadTime", Long.valueOf(System.currentTimeMillis()));
                        hashMap.put("errorStack", arrayList);
                        Object a = C2357d.m2771a(hashMap, C2356c.f2252f);
                        NLog a2 = C2373a.m2807a();
                        a2.mo29768d("APM: upload crash success. object:" + a, new Object[0]);
                        if (a instanceof HashMap) {
                            int intValue = ((Integer) ((HashMap) a).get("code")).intValue();
                            if (intValue == 200 || intValue == 4131002) {
                                C2345a.m2734l();
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
                a3.mo29768d("APM: upload crash fail. error:" + th, new Object[0]);
            }
        }
    }

    private C2361g() {
    }

    /* renamed from: b */
    public static C2361g m2786b() {
        if (f2269a == null) {
            synchronized (C2361g.class) {
                if (f2269a == null) {
                    f2269a = new C2361g();
                }
            }
        }
        return f2269a;
    }

    /* renamed from: a */
    public void mo29258a() {
        C2358e.m2781b().mo29255a(new C2362a(this));
    }
}
