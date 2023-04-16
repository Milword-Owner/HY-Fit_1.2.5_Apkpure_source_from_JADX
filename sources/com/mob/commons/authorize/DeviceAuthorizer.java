package com.mob.commons.authorize;

import android.text.TextUtils;
import com.mob.commons.C2212a;
import com.mob.commons.C2262b;
import com.mob.commons.MobProduct;
import com.mob.commons.MobProductCollector;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import java.util.HashSet;

public final class DeviceAuthorizer implements PublicMemberKeeper {

    /* renamed from: a */
    static String f2005a;

    /* renamed from: b */
    private static HashSet<String> f2006b = new HashSet<>();

    /* renamed from: c */
    private static Object f2007c = new Object();

    public static String authorizeForOnce() {
        if (C2262b.m2276aa()) {
            return null;
        }
        String str = f2005a;
        if (str != null) {
            return str;
        }
        return new C2255a().mo29033a(true, true);
    }

    public static synchronized String authorize(final MobProduct mobProduct) {
        synchronized (DeviceAuthorizer.class) {
            boolean z = false;
            if (mobProduct != null) {
                MobProductCollector.registerProduct(mobProduct);
                z = !f2006b.contains(mobProduct.getProductTag());
                if (z) {
                    f2006b.add(mobProduct.getProductTag());
                }
            }
            if (TextUtils.isEmpty(f2005a)) {
                f2005a = new C2255a().mo29033a(true, true);
                z = true;
            }
            if (TextUtils.isEmpty(f2005a)) {
                f2005a = m2196b(mobProduct, (String) null);
                if (TextUtils.isEmpty(f2005a)) {
                    String a = new C2255a().mo29031a();
                    return a;
                }
                String str = f2005a;
                return str;
            }
            if (z) {
                new Thread() {
                    public void run() {
                        try {
                            String a = DeviceAuthorizer.m2196b(mobProduct, DeviceAuthorizer.f2005a);
                            if (!TextUtils.isEmpty(a)) {
                                DeviceAuthorizer.f2005a = a;
                            }
                        } catch (Throwable th) {
                            MobLog.getInstance().mo29769d(th);
                        }
                    }
                }.start();
            }
            String str2 = f2005a;
            return str2;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static String m2196b(MobProduct mobProduct, String str) {
        synchronized (f2007c) {
            C2255a aVar = new C2255a();
            C2212a.m1957b();
            if (C2262b.m2259Z() || !C2262b.m2314i()) {
                String a = aVar.mo29033a(false, true);
                return a;
            }
            String a2 = aVar.mo29032a(mobProduct, str);
            return a2;
        }
    }
}
