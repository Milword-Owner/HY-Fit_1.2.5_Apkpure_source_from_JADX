package com.mob.mobapm.p026b;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.mobapm.core.C2357d;
import com.mob.mobapm.p030d.C2373a;
import com.mob.mobapm.p031e.C2383h;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.SharePrefrenceHelper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.mob.mobapm.b.a */
public class C2345a {

    /* renamed from: a */
    private static SharePrefrenceHelper f2230a;

    /* renamed from: com.mob.mobapm.b.a$a */
    static class C2346a implements Comparator<String> {
        C2346a() {
        }

        /* renamed from: a */
        public int compare(String str, String str2) {
            return (int) (Long.valueOf(str).longValue() - Long.valueOf(str2).longValue());
        }
    }

    /* renamed from: com.mob.mobapm.b.a$b */
    static class C2347b implements Comparator<String> {
        C2347b() {
        }

        /* renamed from: a */
        public int compare(String str, String str2) {
            return (int) (Long.valueOf(str).longValue() - Long.valueOf(str2).longValue());
        }
    }

    static {
        m2710a();
    }

    /* renamed from: a */
    private static final synchronized void m2710a() {
        synchronized (C2345a.class) {
            if (f2230a == null) {
                SharePrefrenceHelper sharePrefrenceHelper = new SharePrefrenceHelper(MobSDK.getContext());
                f2230a = sharePrefrenceHelper;
                sharePrefrenceHelper.open("apm", 1);
            }
        }
    }

    /* renamed from: b */
    public static synchronized void m2716b(List<HashMap<String, Object>> list) {
        synchronized (C2345a.class) {
            m2710a();
            try {
                List<HashMap<String, Object>> f = m2723f();
                ArrayList arrayList = new ArrayList();
                for (HashMap next : list) {
                    String valueOf = String.valueOf(next.get("appLaunchTime"));
                    String valueOf2 = String.valueOf(next.get("appCloseTime"));
                    String valueOf3 = String.valueOf(next.get("appDuration"));
                    Iterator<HashMap<String, Object>> it = f.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        HashMap next2 = it.next();
                        String valueOf4 = String.valueOf(next2.get("appLaunchTime"));
                        String valueOf5 = String.valueOf(next2.get("appCloseTime"));
                        String valueOf6 = String.valueOf(next2.get("appDuration"));
                        if (valueOf.equals(valueOf4) && valueOf2.equals(valueOf5) && valueOf3.equals(valueOf6)) {
                            arrayList.add(next2);
                            break;
                        }
                    }
                }
                f.removeAll(arrayList);
                m2713a(f);
            } catch (Throwable th) {
                NLog a = C2373a.m2807a();
                a.mo29775i("APM: update running time error: " + th, new Object[0]);
            }
        }
        return;
    }

    /* renamed from: c */
    public static synchronized void m2718c(HashMap<String, Object> hashMap) {
        synchronized (C2345a.class) {
            m2710a();
            try {
                HashMap<String, Object> j = m2731j();
                if (j == null) {
                    j = new HashMap<>();
                }
                int intValue = ((Integer) hashMap.get("id")).intValue();
                if (j.containsKey(String.valueOf(intValue))) {
                    j.remove(String.valueOf(intValue));
                }
                f2230a.putString("radar", C2383h.m2825a().mo29276a((Object) j));
            } catch (Throwable th) {
                NLog a = C2373a.m2807a();
                a.mo29768d("APM: remove radar data error: " + th, new Object[0]);
            }
        }
        return;
    }

    /* renamed from: d */
    public static synchronized String m2719d() {
        String string;
        synchronized (C2345a.class) {
            m2710a();
            string = f2230a.getString("appCrashVersion");
        }
        return string;
    }

    /* renamed from: e */
    public static void m2722e(HashMap<String, Object> hashMap) {
        m2710a();
        if (hashMap != null) {
            f2230a.putString("appinfo", C2383h.m2825a().mo29277a(hashMap));
        }
    }

    /* renamed from: f */
    public static synchronized void m2724f(HashMap<String, Object> hashMap) {
        synchronized (C2345a.class) {
            m2710a();
            try {
                m2732j(hashMap);
                List f = m2723f();
                if (f == null) {
                    f = new ArrayList();
                }
                f.add(hashMap);
                f2230a.putString("appRunningTime", C2383h.m2825a().mo29276a((Object) f));
            } catch (Throwable th) {
                NLog a = C2373a.m2807a();
                a.mo29775i("APM: set running time error: " + th, new Object[0]);
            }
        }
        return;
    }

    /* renamed from: g */
    public static synchronized void m2726g(HashMap<String, Object> hashMap) {
        synchronized (C2345a.class) {
            m2710a();
            m2732j(hashMap);
            f2230a.putString("appRunningTimeTemp", C2383h.m2825a().mo29277a(hashMap));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d2, code lost:
        return;
     */
    /* renamed from: h */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void m2728h(java.util.HashMap r8) {
        /*
            java.lang.Class<com.mob.mobapm.b.a> r0 = com.mob.mobapm.p026b.C2345a.class
            monitor-enter(r0)
            if (r8 == 0) goto L_0x00d1
            boolean r1 = r8.isEmpty()     // Catch:{ all -> 0x00ce }
            if (r1 == 0) goto L_0x000d
            goto L_0x00d1
        L_0x000d:
            m2710a()     // Catch:{ all -> 0x00ce }
            r1 = 0
            java.lang.String r2 = m2719d()     // Catch:{ all -> 0x00b1 }
            android.content.Context r3 = com.mob.MobSDK.getContext()     // Catch:{ all -> 0x00b1 }
            com.mob.tools.utils.DeviceHelper r3 = com.mob.tools.utils.DeviceHelper.getInstance(r3)     // Catch:{ all -> 0x00b1 }
            int r3 = r3.getAppVersion()     // Catch:{ all -> 0x00b1 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00b1 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00b1 }
            if (r4 != 0) goto L_0x009e
            boolean r2 = r3.equals(r2)     // Catch:{ all -> 0x00b1 }
            if (r2 != 0) goto L_0x0032
            goto L_0x009e
        L_0x0032:
            java.util.HashMap r2 = m2727h()     // Catch:{ all -> 0x00b1 }
            if (r2 == 0) goto L_0x0084
            int r3 = r2.size()     // Catch:{ all -> 0x00b1 }
            r4 = 5
            if (r3 < r4) goto L_0x0084
            java.util.Set r3 = r2.keySet()     // Catch:{ all -> 0x00b1 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x00b1 }
            r4.<init>(r3)     // Catch:{ all -> 0x00b1 }
            com.mob.mobapm.b.a$a r3 = new com.mob.mobapm.b.a$a     // Catch:{ all -> 0x00b1 }
            r3.<init>()     // Catch:{ all -> 0x00b1 }
            java.util.Collections.sort(r4, r3)     // Catch:{ all -> 0x00b1 }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x00b1 }
            java.util.Set r5 = r8.keySet()     // Catch:{ all -> 0x00b1 }
            r3.<init>(r5)     // Catch:{ all -> 0x00b1 }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ all -> 0x00b1 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x00b1 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x00b1 }
            long r5 = r3.longValue()     // Catch:{ all -> 0x00b1 }
            java.lang.Object r3 = r4.get(r1)     // Catch:{ all -> 0x00b1 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x00b1 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x00b1 }
            long r3 = r3.longValue()     // Catch:{ all -> 0x00b1 }
            int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r7 <= 0) goto L_0x008e
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00b1 }
            r2.remove(r3)     // Catch:{ all -> 0x00b1 }
            r2.putAll(r8)     // Catch:{ all -> 0x00b1 }
            goto L_0x008e
        L_0x0084:
            if (r2 != 0) goto L_0x008b
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x00b1 }
            r2.<init>()     // Catch:{ all -> 0x00b1 }
        L_0x008b:
            r2.putAll(r8)     // Catch:{ all -> 0x00b1 }
        L_0x008e:
            com.mob.tools.utils.SharePrefrenceHelper r8 = f2230a     // Catch:{ all -> 0x00b1 }
            java.lang.String r3 = "crashInfo"
            com.mob.mobapm.e.h r4 = com.mob.mobapm.p031e.C2383h.m2825a()     // Catch:{ all -> 0x00b1 }
            java.lang.String r2 = r4.mo29277a((java.util.HashMap<java.lang.String, java.lang.Object>) r2)     // Catch:{ all -> 0x00b1 }
            r8.putString(r3, r2)     // Catch:{ all -> 0x00b1 }
            goto L_0x00cc
        L_0x009e:
            com.mob.tools.utils.SharePrefrenceHelper r2 = f2230a     // Catch:{ all -> 0x00b1 }
            java.lang.String r3 = "crashInfo"
            com.mob.mobapm.e.h r4 = com.mob.mobapm.p031e.C2383h.m2825a()     // Catch:{ all -> 0x00b1 }
            java.lang.String r8 = r4.mo29277a((java.util.HashMap<java.lang.String, java.lang.Object>) r8)     // Catch:{ all -> 0x00b1 }
            r2.putString(r3, r8)     // Catch:{ all -> 0x00b1 }
            m2736n()     // Catch:{ all -> 0x00b1 }
            goto L_0x00cc
        L_0x00b1:
            r8 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.mobapm.p030d.C2373a.m2807a()     // Catch:{ all -> 0x00ce }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ce }
            r3.<init>()     // Catch:{ all -> 0x00ce }
            java.lang.String r4 = "APM: set crash info error: "
            r3.append(r4)     // Catch:{ all -> 0x00ce }
            r3.append(r8)     // Catch:{ all -> 0x00ce }
            java.lang.String r8 = r3.toString()     // Catch:{ all -> 0x00ce }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x00ce }
            r2.mo29768d(r8, r1)     // Catch:{ all -> 0x00ce }
        L_0x00cc:
            monitor-exit(r0)
            return
        L_0x00ce:
            r8 = move-exception
            monitor-exit(r0)
            throw r8
        L_0x00d1:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.mobapm.p026b.C2345a.m2728h(java.util.HashMap):void");
    }

    /* renamed from: i */
    public static synchronized void m2730i(HashMap<String, Object> hashMap) {
        synchronized (C2345a.class) {
            m2710a();
            try {
                HashMap<String, Object> j = m2731j();
                if (j == null) {
                    j = new HashMap<>();
                }
                for (String next : hashMap.keySet()) {
                    if (!j.containsKey(next)) {
                        j.put(next, hashMap.get(next));
                    }
                }
                f2230a.putString("radar", C2383h.m2825a().mo29276a((Object) j));
            } catch (Throwable th) {
                NLog a = C2373a.m2807a();
                a.mo29768d("APM: set radar data error: " + th, new Object[0]);
            }
        }
        return;
    }

    /* renamed from: j */
    private static void m2732j(HashMap<String, Object> hashMap) {
        try {
            hashMap.put("networkType", DeviceHelper.getInstance(MobSDK.getContext()).getNetworkType());
            hashMap.put("dataNetworkType", Integer.valueOf(DeviceHelper.getInstance(MobSDK.getContext()).getDataNtType()));
            hashMap.put("mac", DeviceHelper.getInstance(MobSDK.getContext()).getMacAddress());
            hashMap.put("imei", DeviceHelper.getInstance(MobSDK.getContext()).getIMEI());
            hashMap.put("duid", C2357d.m2780e());
        } catch (Throwable th) {
            NLog a = C2373a.m2807a();
            a.mo29768d("APM: update device info error: " + th, new Object[0]);
        }
    }

    /* renamed from: k */
    public static synchronized void m2733k() {
        synchronized (C2345a.class) {
            m2710a();
            f2230a.putString("anrInfo", (String) null);
            m2735m();
        }
    }

    /* renamed from: l */
    public static synchronized void m2734l() {
        synchronized (C2345a.class) {
            m2710a();
            f2230a.putString("crashInfo", (String) null);
            m2736n();
        }
    }

    /* renamed from: m */
    public static synchronized void m2735m() {
        synchronized (C2345a.class) {
            m2710a();
            f2230a.putString("appANRVersion", String.valueOf(DeviceHelper.getInstance(MobSDK.getContext()).getAppVersion()));
        }
    }

    /* renamed from: n */
    public static synchronized void m2736n() {
        synchronized (C2345a.class) {
            m2710a();
            f2230a.putString("appCrashVersion", String.valueOf(DeviceHelper.getInstance(MobSDK.getContext()).getAppVersion()));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d2, code lost:
        return;
     */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void m2720d(java.util.HashMap r8) {
        /*
            java.lang.Class<com.mob.mobapm.b.a> r0 = com.mob.mobapm.p026b.C2345a.class
            monitor-enter(r0)
            if (r8 == 0) goto L_0x00d1
            boolean r1 = r8.isEmpty()     // Catch:{ all -> 0x00ce }
            if (r1 == 0) goto L_0x000d
            goto L_0x00d1
        L_0x000d:
            m2710a()     // Catch:{ all -> 0x00ce }
            r1 = 0
            java.lang.String r2 = m2717c()     // Catch:{ all -> 0x00b1 }
            android.content.Context r3 = com.mob.MobSDK.getContext()     // Catch:{ all -> 0x00b1 }
            com.mob.tools.utils.DeviceHelper r3 = com.mob.tools.utils.DeviceHelper.getInstance(r3)     // Catch:{ all -> 0x00b1 }
            int r3 = r3.getAppVersion()     // Catch:{ all -> 0x00b1 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00b1 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00b1 }
            if (r4 != 0) goto L_0x009e
            boolean r2 = r3.equals(r2)     // Catch:{ all -> 0x00b1 }
            if (r2 != 0) goto L_0x0032
            goto L_0x009e
        L_0x0032:
            java.util.HashMap r2 = m2714b()     // Catch:{ all -> 0x00b1 }
            if (r2 == 0) goto L_0x0084
            int r3 = r2.size()     // Catch:{ all -> 0x00b1 }
            r4 = 5
            if (r3 < r4) goto L_0x0084
            java.util.Set r3 = r2.keySet()     // Catch:{ all -> 0x00b1 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x00b1 }
            r4.<init>(r3)     // Catch:{ all -> 0x00b1 }
            com.mob.mobapm.b.a$b r3 = new com.mob.mobapm.b.a$b     // Catch:{ all -> 0x00b1 }
            r3.<init>()     // Catch:{ all -> 0x00b1 }
            java.util.Collections.sort(r4, r3)     // Catch:{ all -> 0x00b1 }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x00b1 }
            java.util.Set r5 = r8.keySet()     // Catch:{ all -> 0x00b1 }
            r3.<init>(r5)     // Catch:{ all -> 0x00b1 }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ all -> 0x00b1 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x00b1 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x00b1 }
            long r5 = r3.longValue()     // Catch:{ all -> 0x00b1 }
            java.lang.Object r3 = r4.get(r1)     // Catch:{ all -> 0x00b1 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x00b1 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x00b1 }
            long r3 = r3.longValue()     // Catch:{ all -> 0x00b1 }
            int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r7 <= 0) goto L_0x008e
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00b1 }
            r2.remove(r3)     // Catch:{ all -> 0x00b1 }
            r2.putAll(r8)     // Catch:{ all -> 0x00b1 }
            goto L_0x008e
        L_0x0084:
            if (r2 != 0) goto L_0x008b
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x00b1 }
            r2.<init>()     // Catch:{ all -> 0x00b1 }
        L_0x008b:
            r2.putAll(r8)     // Catch:{ all -> 0x00b1 }
        L_0x008e:
            com.mob.tools.utils.SharePrefrenceHelper r8 = f2230a     // Catch:{ all -> 0x00b1 }
            java.lang.String r3 = "anrInfo"
            com.mob.mobapm.e.h r4 = com.mob.mobapm.p031e.C2383h.m2825a()     // Catch:{ all -> 0x00b1 }
            java.lang.String r2 = r4.mo29277a((java.util.HashMap<java.lang.String, java.lang.Object>) r2)     // Catch:{ all -> 0x00b1 }
            r8.putString(r3, r2)     // Catch:{ all -> 0x00b1 }
            goto L_0x00cc
        L_0x009e:
            com.mob.tools.utils.SharePrefrenceHelper r2 = f2230a     // Catch:{ all -> 0x00b1 }
            java.lang.String r3 = "anrInfo"
            com.mob.mobapm.e.h r4 = com.mob.mobapm.p031e.C2383h.m2825a()     // Catch:{ all -> 0x00b1 }
            java.lang.String r8 = r4.mo29277a((java.util.HashMap<java.lang.String, java.lang.Object>) r8)     // Catch:{ all -> 0x00b1 }
            r2.putString(r3, r8)     // Catch:{ all -> 0x00b1 }
            m2735m()     // Catch:{ all -> 0x00b1 }
            goto L_0x00cc
        L_0x00b1:
            r8 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.mobapm.p030d.C2373a.m2807a()     // Catch:{ all -> 0x00ce }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ce }
            r3.<init>()     // Catch:{ all -> 0x00ce }
            java.lang.String r4 = "APM: set anr info error: "
            r3.append(r4)     // Catch:{ all -> 0x00ce }
            r3.append(r8)     // Catch:{ all -> 0x00ce }
            java.lang.String r8 = r3.toString()     // Catch:{ all -> 0x00ce }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x00ce }
            r2.mo29768d(r8, r1)     // Catch:{ all -> 0x00ce }
        L_0x00cc:
            monitor-exit(r0)
            return
        L_0x00ce:
            r8 = move-exception
            monitor-exit(r0)
            throw r8
        L_0x00d1:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.mobapm.p026b.C2345a.m2720d(java.util.HashMap):void");
    }

    /* renamed from: a */
    public static synchronized void m2713a(List<HashMap<String, Object>> list) {
        synchronized (C2345a.class) {
            m2710a();
            f2230a.putString("appRunningTime", C2383h.m2825a().mo29276a((Object) list));
        }
    }

    /* renamed from: e */
    public static HashMap<String, Object> m2721e() {
        m2710a();
        String string = f2230a.getString("appinfo");
        if (string != null) {
            return C2383h.m2825a().mo29278a(string);
        }
        return null;
    }

    /* renamed from: g */
    public static synchronized HashMap<String, Object> m2725g() {
        HashMap<String, Object> a;
        synchronized (C2345a.class) {
            m2710a();
            String string = f2230a.getString("appRunningTimeTemp");
            a = TextUtils.isEmpty(string) ? null : C2383h.m2825a().mo29278a(string);
        }
        return a;
    }

    /* renamed from: a */
    public static synchronized void m2711a(String str) {
        synchronized (C2345a.class) {
            m2710a();
            f2230a.putString("mm", C2383h.m2825a().mo29283f(str));
        }
    }

    /* renamed from: a */
    public static synchronized void m2712a(HashMap<String, Object> hashMap) {
        synchronized (C2345a.class) {
            m2710a();
            try {
                HashMap<String, Object> b = m2714b();
                if (b == null) {
                    b = new HashMap<>();
                }
                String valueOf = hashMap.containsKey("happenTime") ? String.valueOf(hashMap.get("happenTime")) : null;
                if (b.containsKey(valueOf)) {
                    b.remove(valueOf);
                }
                f2230a.putString("anrInfo", C2383h.m2825a().mo29277a(b));
            } catch (Throwable th) {
                NLog a = C2373a.m2807a();
                a.mo29768d("APM: remove anr info error: " + th, new Object[0]);
            }
        }
        return;
    }

    /* renamed from: j */
    public static synchronized HashMap<String, Object> m2731j() {
        HashMap<String, Object> a;
        synchronized (C2345a.class) {
            m2710a();
            String string = f2230a.getString("radar");
            a = TextUtils.isEmpty(string) ? null : C2383h.m2825a().mo29278a(string);
        }
        return a;
    }

    /* renamed from: f */
    public static synchronized List<HashMap<String, Object>> m2723f() {
        List<HashMap<String, Object>> b;
        synchronized (C2345a.class) {
            m2710a();
            String string = f2230a.getString("appRunningTime");
            b = TextUtils.isEmpty(string) ? null : C2383h.m2825a().mo29279b(string);
        }
        return b;
    }

    /* renamed from: c */
    public static synchronized String m2717c() {
        String string;
        synchronized (C2345a.class) {
            m2710a();
            string = f2230a.getString("appANRVersion");
        }
        return string;
    }

    /* renamed from: i */
    public static synchronized String m2729i() {
        String trim;
        synchronized (C2345a.class) {
            m2710a();
            String string = f2230a.getString("mm");
            trim = TextUtils.isEmpty(string) ? null : C2383h.m2825a().mo29281d(string).trim();
        }
        return trim;
    }

    /* renamed from: b */
    public static synchronized void m2715b(HashMap<String, Object> hashMap) {
        synchronized (C2345a.class) {
            m2710a();
            try {
                HashMap<String, Object> h = m2727h();
                if (h == null) {
                    h = new HashMap<>();
                }
                String valueOf = hashMap.containsKey("happenTime") ? String.valueOf(hashMap.get("happenTime")) : null;
                if (h.containsKey(valueOf)) {
                    h.remove(valueOf);
                }
                f2230a.putString("crashInfo", C2383h.m2825a().mo29277a(h));
            } catch (Throwable th) {
                NLog a = C2373a.m2807a();
                a.mo29768d("APM: remove crash info error: " + th, new Object[0]);
            }
        }
        return;
    }

    /* renamed from: b */
    public static synchronized HashMap<String, Object> m2714b() {
        HashMap<String, Object> a;
        synchronized (C2345a.class) {
            m2710a();
            String string = f2230a.getString("anrInfo");
            a = TextUtils.isEmpty(string) ? null : C2383h.m2825a().mo29278a(string);
        }
        return a;
    }

    /* renamed from: h */
    public static synchronized HashMap<String, Object> m2727h() {
        HashMap<String, Object> a;
        synchronized (C2345a.class) {
            m2710a();
            String string = f2230a.getString("crashInfo");
            a = TextUtils.isEmpty(string) ? null : C2383h.m2825a().mo29278a(string);
        }
        return a;
    }
}
