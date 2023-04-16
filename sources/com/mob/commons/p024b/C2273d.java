package com.mob.commons.p024b;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.mob.MobSDK;
import com.mob.commons.C2312k;
import com.mob.tools.MobLog;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import java.lang.reflect.Method;
import java.util.HashMap;

/* renamed from: com.mob.commons.b.d */
/* compiled from: FidsSDK */
public class C2273d {

    /* renamed from: a */
    private static C2277f f2047a;

    /* renamed from: a */
    public static synchronized HashMap<String, Object> m2348a(Context context) {
        synchronized (C2273d.class) {
            HashMap<String, Object> hashMap = new HashMap<>();
            C2271b a = C2271b.m2337a(context);
            HashMap<String, Object> a2 = a.mo29042a();
            boolean z = a2 != null && a2.size() > 0;
            if (z) {
                HashMap hashMap2 = new HashMap();
                if (a2.containsKey(C2312k.m2575a(71))) {
                    a2.put(C2312k.m2575a(72), a2.remove(C2312k.m2575a(71)));
                }
                if (a2.containsKey(C2312k.m2575a(74))) {
                    a2.put(C2312k.m2575a(73), a2.remove(C2312k.m2575a(74)));
                }
                hashMap2.putAll(a2);
                hashMap.put("fidsCache", hashMap2);
            }
            String c = m2353c(context);
            String e = m2355e(context);
            String g = m2357g(context);
            String f = m2356f(context);
            if (!z && TextUtils.isEmpty(c) && TextUtils.isEmpty(f)) {
                return null;
            }
            boolean b = m2352b(context);
            hashMap.put(C2312k.m2575a(75), e);
            hashMap.put(C2312k.m2575a(69), c);
            hashMap.put(C2312k.m2575a(70), g);
            hashMap.put(C2312k.m2575a(72), f);
            hashMap.put(C2312k.m2575a(73), Boolean.valueOf(b));
            a.mo29043a(c, e, g, f, b);
            return hashMap;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x008b, code lost:
        return;
     */
    /* renamed from: h */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void m2358h(android.content.Context r3) {
        /*
            java.lang.Class<com.mob.commons.b.d> r0 = com.mob.commons.p024b.C2273d.class
            monitor-enter(r0)
            com.mob.commons.b.f r1 = f2047a     // Catch:{ all -> 0x008c }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)
            return
        L_0x0009:
            java.lang.String r1 = android.os.Build.MANUFACTURER     // Catch:{ all -> 0x008c }
            com.mob.commons.b.d$a r1 = m2347a((java.lang.String) r1)     // Catch:{ all -> 0x008c }
            com.mob.commons.b.d$a r2 = com.mob.commons.p024b.C2273d.C2275a.UNSUPPORT     // Catch:{ all -> 0x008c }
            if (r1 != r2) goto L_0x002f
            com.mob.commons.b.c r3 = com.mob.commons.p024b.C2272c.m2344a()     // Catch:{ all -> 0x008c }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x008c }
            r1.<init>()     // Catch:{ all -> 0x008c }
            java.lang.String r2 = android.os.Build.MANUFACTURER     // Catch:{ all -> 0x008c }
            r1.append(r2)     // Catch:{ all -> 0x008c }
            java.lang.String r2 = " not support"
            r1.append(r2)     // Catch:{ all -> 0x008c }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x008c }
            r3.mo29044a(r1)     // Catch:{ all -> 0x008c }
            monitor-exit(r0)
            return
        L_0x002f:
            int[] r2 = com.mob.commons.p024b.C2273d.C22741.f2048a     // Catch:{ all -> 0x008c }
            int r1 = r1.ordinal()     // Catch:{ all -> 0x008c }
            r1 = r2[r1]     // Catch:{ all -> 0x008c }
            switch(r1) {
                case 1: goto L_0x0083;
                case 2: goto L_0x0083;
                case 3: goto L_0x007b;
                case 4: goto L_0x0073;
                case 5: goto L_0x006b;
                case 6: goto L_0x006b;
                case 7: goto L_0x0063;
                case 8: goto L_0x0063;
                case 9: goto L_0x005b;
                case 10: goto L_0x0053;
                case 11: goto L_0x004b;
                case 12: goto L_0x004b;
                case 13: goto L_0x0043;
                case 14: goto L_0x003b;
                case 15: goto L_0x003b;
                case 16: goto L_0x003b;
                default: goto L_0x003a;
            }     // Catch:{ all -> 0x008c }
        L_0x003a:
            goto L_0x008a
        L_0x003b:
            com.mob.commons.b.n r1 = new com.mob.commons.b.n     // Catch:{ all -> 0x008c }
            r1.<init>(r3)     // Catch:{ all -> 0x008c }
            f2047a = r1     // Catch:{ all -> 0x008c }
            goto L_0x008a
        L_0x0043:
            com.mob.commons.b.i r1 = new com.mob.commons.b.i     // Catch:{ all -> 0x008c }
            r1.<init>(r3)     // Catch:{ all -> 0x008c }
            f2047a = r1     // Catch:{ all -> 0x008c }
            goto L_0x008a
        L_0x004b:
            com.mob.commons.b.g r1 = new com.mob.commons.b.g     // Catch:{ all -> 0x008c }
            r1.<init>(r3)     // Catch:{ all -> 0x008c }
            f2047a = r1     // Catch:{ all -> 0x008c }
            goto L_0x008a
        L_0x0053:
            com.mob.commons.b.k r1 = new com.mob.commons.b.k     // Catch:{ all -> 0x008c }
            r1.<init>(r3)     // Catch:{ all -> 0x008c }
            f2047a = r1     // Catch:{ all -> 0x008c }
            goto L_0x008a
        L_0x005b:
            com.mob.commons.b.a r1 = new com.mob.commons.b.a     // Catch:{ all -> 0x008c }
            r1.<init>(r3)     // Catch:{ all -> 0x008c }
            f2047a = r1     // Catch:{ all -> 0x008c }
            goto L_0x008a
        L_0x0063:
            com.mob.commons.b.h r1 = new com.mob.commons.b.h     // Catch:{ all -> 0x008c }
            r1.<init>(r3)     // Catch:{ all -> 0x008c }
            f2047a = r1     // Catch:{ all -> 0x008c }
            goto L_0x008a
        L_0x006b:
            com.mob.commons.b.j r1 = new com.mob.commons.b.j     // Catch:{ all -> 0x008c }
            r1.<init>(r3)     // Catch:{ all -> 0x008c }
            f2047a = r1     // Catch:{ all -> 0x008c }
            goto L_0x008a
        L_0x0073:
            com.mob.commons.b.e r1 = new com.mob.commons.b.e     // Catch:{ all -> 0x008c }
            r1.<init>(r3)     // Catch:{ all -> 0x008c }
            f2047a = r1     // Catch:{ all -> 0x008c }
            goto L_0x008a
        L_0x007b:
            com.mob.commons.b.l r1 = new com.mob.commons.b.l     // Catch:{ all -> 0x008c }
            r1.<init>(r3)     // Catch:{ all -> 0x008c }
            f2047a = r1     // Catch:{ all -> 0x008c }
            goto L_0x008a
        L_0x0083:
            com.mob.commons.b.m r1 = new com.mob.commons.b.m     // Catch:{ all -> 0x008c }
            r1.<init>(r3)     // Catch:{ all -> 0x008c }
            f2047a = r1     // Catch:{ all -> 0x008c }
        L_0x008a:
            monitor-exit(r0)
            return
        L_0x008c:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p024b.C2273d.m2358h(android.content.Context):void");
    }

    /* renamed from: b */
    public static boolean m2352b(Context context) {
        m2358h(context);
        C2277f fVar = f2047a;
        if (fVar != null) {
            return fVar.mo29056h();
        }
        return false;
    }

    /* renamed from: c */
    public static String m2353c(Context context) {
        m2358h(context);
        C2277f fVar = f2047a;
        if (fVar != null) {
            return fVar.mo29053e();
        }
        return null;
    }

    /* renamed from: d */
    public static String m2354d(Context context) {
        m2358h(context);
        C2277f fVar = f2047a;
        if (fVar == null) {
            return null;
        }
        String e = fVar.mo29053e();
        if (TextUtils.isEmpty(e)) {
            return null;
        }
        try {
            return Base64.encodeToString(Data.AES128Encode(Data.MD5(DeviceHelper.getInstance(MobSDK.getContext()).getManufacturer()), e), 2);
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return e;
        }
    }

    /* renamed from: e */
    public static String m2355e(Context context) {
        m2358h(context);
        C2277f fVar = f2047a;
        if (fVar != null) {
            return fVar.mo29045b();
        }
        return null;
    }

    /* renamed from: f */
    public static String m2356f(Context context) {
        m2358h(context);
        C2277f fVar = f2047a;
        if (fVar != null) {
            return fVar.mo29054f();
        }
        return null;
    }

    /* renamed from: g */
    public static String m2357g(Context context) {
        m2358h(context);
        C2277f fVar = f2047a;
        if (fVar != null) {
            return fVar.mo29055g();
        }
        return null;
    }

    /* renamed from: a */
    public static C2275a m2347a(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (C2275a aVar : C2275a.values()) {
                if (aVar.f2068s.equalsIgnoreCase(str)) {
                    return aVar;
                }
            }
        }
        if (m2349a() || m2351b()) {
            return C2275a.ZTE;
        }
        return C2275a.UNSUPPORT;
    }

    /* renamed from: a */
    private static boolean m2349a() {
        try {
            String b = m2350b("ro.build.freeme.label");
            if (TextUtils.isEmpty(b) || !b.equalsIgnoreCase("FREEMEOS")) {
                return false;
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: b */
    private static boolean m2351b() {
        try {
            String b = m2350b("ro.ssui.product");
            if (TextUtils.isEmpty(b) || b.equalsIgnoreCase("unknown")) {
                return false;
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static String m2350b(String str) {
        try {
            Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class});
            declaredMethod.setAccessible(true);
            return String.valueOf(declaredMethod.invoke((Object) null, new Object[]{str}));
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: com.mob.commons.b.d$a */
    /* compiled from: FidsSDK */
    enum C2275a {
        UNSUPPORT(-1, C2312k.m2575a(103)),
        HUA_WEI(0, C2312k.m2575a(104)),
        XIAOMI(1, C2312k.m2575a(105)),
        f2052d(2, C2312k.m2575a(106)),
        f2053e(3, C2312k.m2575a(107)),
        MOTO(4, C2312k.m2575a(108)),
        LENOVO(5, C2312k.m2575a(109)),
        ASUS(6, C2312k.m2575a(110)),
        SAMSUNG(7, C2312k.m2575a(111)),
        MEIZU(8, C2312k.m2575a(112)),
        ALPS(9, C2312k.m2575a(113)),
        NUBIA(10, C2312k.m2575a(114)),
        ONEPLUS(11, C2312k.m2575a(133)),
        BLACKSHARK(12, C2312k.m2575a(134)),
        ZTE(13, C2312k.m2575a(135)),
        FERRMEOS(14, C2312k.m2575a(136)),
        SSUI(15, C2312k.m2575a(137));
        

        /* renamed from: r */
        private int f2067r;
        /* access modifiers changed from: private */

        /* renamed from: s */
        public String f2068s;

        private C2275a(int i, String str) {
            this.f2067r = i;
            this.f2068s = str;
        }
    }
}
