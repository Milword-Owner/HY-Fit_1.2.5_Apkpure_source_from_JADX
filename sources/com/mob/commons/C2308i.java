package com.mob.commons;

import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.facebook.appevents.UserDataStore;
import com.mob.MobSDK;
import com.mob.tools.MobLog;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.SharePrefrenceHelper;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.mob.commons.i */
/* compiled from: ProcessLevelSPDB */
public class C2308i {

    /* renamed from: a */
    private static volatile boolean f2141a = false;

    /* renamed from: b */
    private static SharePrefrenceHelper f2142b;

    /* renamed from: J */
    private static synchronized void m2505J() {
        synchronized (C2308i.class) {
            m2519b(false);
        }
    }

    /* renamed from: b */
    private static synchronized void m2519b(boolean z) {
        synchronized (C2308i.class) {
            if (f2142b == null || z) {
                f2142b = new SharePrefrenceHelper(MobSDK.getContext());
                f2142b.open("mob_commons", 1);
            }
        }
    }

    /* renamed from: a */
    public static synchronized String m2506a() {
        String string;
        synchronized (C2308i.class) {
            m2505J();
            string = f2142b.getString("key_ext_info");
        }
        return string;
    }

    /* renamed from: a */
    public static synchronized void m2510a(String str) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putString("key_ext_info", str);
        }
    }

    /* renamed from: a */
    public static synchronized void m2508a(long j) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putLong("wifi_last_time", Long.valueOf(j));
        }
    }

    /* renamed from: b */
    public static synchronized String m2514b() {
        String string;
        synchronized (C2308i.class) {
            m2505J();
            string = f2142b.getString("wifi_last_info");
        }
        return string;
    }

    /* renamed from: b */
    public static synchronized void m2517b(String str) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putString("wifi_last_info", str);
        }
    }

    /* renamed from: c */
    public static synchronized void m2523c(String str) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putString("key_cellinfo", str);
        }
    }

    /* renamed from: c */
    public static synchronized String m2520c() {
        String string;
        synchronized (C2308i.class) {
            m2505J();
            string = f2142b.getString("key_cellinfo");
        }
        return string;
    }

    /* renamed from: d */
    public static synchronized long m2525d() {
        long j;
        synchronized (C2308i.class) {
            m2505J();
            j = f2142b.getLong("key_cellinfo_next_total");
        }
        return j;
    }

    /* renamed from: b */
    public static synchronized void m2516b(long j) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putLong("key_cellinfo_next_total", Long.valueOf(j));
        }
    }

    /* renamed from: e */
    public static synchronized long m2529e() {
        long j;
        synchronized (C2308i.class) {
            m2505J();
            j = f2142b.getLong("key_art_next_total");
        }
        return j;
    }

    /* renamed from: c */
    public static synchronized void m2522c(long j) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putLong("key_art_next_total", Long.valueOf(j));
        }
    }

    /* renamed from: d */
    public static synchronized void m2528d(String str) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putString("key_switches", str);
        }
    }

    /* renamed from: f */
    public static synchronized String m2533f() {
        String string;
        synchronized (C2308i.class) {
            m2505J();
            string = f2142b.getString("key_switches");
        }
        return string;
    }

    /* renamed from: e */
    public static synchronized void m2532e(String str) {
        synchronized (C2308i.class) {
            m2505J();
            if (str == null) {
                f2142b.remove("key_data_url");
            } else {
                f2142b.putString("key_data_url", str);
            }
        }
    }

    /* renamed from: g */
    public static synchronized String m2537g() {
        String string;
        synchronized (C2308i.class) {
            m2505J();
            string = f2142b.getString("key_data_url");
        }
        return string;
    }

    /* renamed from: f */
    public static synchronized void m2536f(String str) {
        synchronized (C2308i.class) {
            m2505J();
            if (str == null) {
                f2142b.remove("key_conf_url");
            } else {
                f2142b.putString("key_conf_url", str);
            }
        }
    }

    /* renamed from: h */
    public static synchronized String m2541h() {
        String string;
        synchronized (C2308i.class) {
            m2505J();
            string = f2142b.getString("key_conf_url");
        }
        return string;
    }

    /* renamed from: i */
    public static synchronized String m2544i() {
        String string;
        synchronized (C2308i.class) {
            m2505J();
            string = f2142b.getString("key_wifi_list_hash");
        }
        return string;
    }

    /* renamed from: g */
    public static synchronized void m2540g(String str) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putString("key_wifi_list_hash", str);
        }
    }

    /* renamed from: j */
    public static synchronized long m2547j() {
        long j;
        synchronized (C2308i.class) {
            m2505J();
            j = f2142b.getLong("key_last_utag_config");
        }
        return j;
    }

    /* renamed from: k */
    public static synchronized HashMap<String, Object> m2550k() {
        synchronized (C2308i.class) {
            m2505J();
            String string = f2142b.getString("key_utag_config");
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            HashMap<String, Object> fromJson = new Hashon().fromJson(string);
            return fromJson;
        }
    }

    /* renamed from: a */
    public static synchronized void m2511a(HashMap<String, Object> hashMap) {
        synchronized (C2308i.class) {
            m2505J();
            if (hashMap == null) {
                f2142b.remove("key_utag_config");
            } else {
                f2142b.putString("key_utag_config", new Hashon().fromHashMap(hashMap));
                f2142b.putLong("key_last_utag_config", Long.valueOf(System.currentTimeMillis()));
            }
        }
    }

    /* renamed from: l */
    public static synchronized HashMap<String, Object> m2553l() {
        synchronized (C2308i.class) {
            m2505J();
            if (System.currentTimeMillis() > f2142b.getLong("key_utags_buffer_time")) {
                return null;
            }
            String string = f2142b.getString("key_utags");
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            HashMap<String, Object> fromJson = new Hashon().fromJson(string);
            return fromJson;
        }
    }

    /* renamed from: a */
    public static synchronized void m2512a(HashMap<String, Object> hashMap, int i) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putString("key_utags_buffer_time", new Hashon().fromHashMap(hashMap));
            f2142b.putLong("key_utags_buffer_time", Long.valueOf(System.currentTimeMillis() + ((long) (i * 1000))));
        }
    }

    /* renamed from: m */
    public static synchronized String m2555m() {
        String string;
        synchronized (C2308i.class) {
            m2505J();
            string = f2142b.getString("key_simulator_info_md5");
        }
        return string;
    }

    /* renamed from: h */
    public static synchronized void m2543h(String str) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putString("key_simulator_info_md5", str);
        }
    }

    /* renamed from: d */
    public static synchronized void m2527d(long j) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putLong("key_backend_time", Long.valueOf(j));
        }
    }

    /* renamed from: n */
    public static synchronized String m2557n() {
        String string;
        synchronized (C2308i.class) {
            m2505J();
            string = f2142b.getString("key_lduid");
        }
        return string;
    }

    /* renamed from: i */
    public static synchronized void m2546i(String str) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putString("key_lduid", str);
        }
    }

    /* renamed from: o */
    public static synchronized long m2558o() {
        long j;
        synchronized (C2308i.class) {
            m2505J();
            j = f2142b.getLong("key_next_dev_ext_info_upload_time");
        }
        return j;
    }

    /* renamed from: e */
    public static synchronized void m2531e(long j) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putLong("key_next_dev_ext_info_upload_time", Long.valueOf(j));
        }
    }

    /* renamed from: p */
    public static synchronized long m2559p() {
        long j;
        synchronized (C2308i.class) {
            m2505J();
            j = f2142b.getLong("key_next_upload_wifi_list_time");
        }
        return j;
    }

    /* renamed from: f */
    public static synchronized void m2535f(long j) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putLong("key_next_upload_wifi_list_time", Long.valueOf(j));
        }
    }

    /* renamed from: j */
    public static synchronized void m2549j(String str) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putString("key_buffered_location_md5", str);
        }
    }

    /* renamed from: q */
    public static synchronized String m2560q() {
        String string;
        synchronized (C2308i.class) {
            m2505J();
            string = f2142b.getString("key_buffered_location_md5");
        }
        return string;
    }

    /* renamed from: r */
    public static synchronized long m2561r() {
        long j;
        synchronized (C2308i.class) {
            m2505J();
            j = f2142b.getLong("key_next_upload_buffered_location_time");
        }
        return j;
    }

    /* renamed from: g */
    public static synchronized void m2539g(long j) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putLong("key_next_upload_buffered_location_time", Long.valueOf(j));
        }
    }

    /* renamed from: s */
    public static synchronized long m2562s() {
        long j;
        synchronized (C2308i.class) {
            m2505J();
            j = f2142b.getLong("key_next_upload_app_active_time");
        }
        return j;
    }

    /* renamed from: h */
    public static synchronized void m2542h(long j) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putLong("key_next_upload_app_active_time", Long.valueOf(j));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0067, code lost:
        return r2;
     */
    /* renamed from: t */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.util.HashMap<java.lang.Long, java.lang.Long> m2563t() {
        /*
            java.lang.Class<com.mob.commons.i> r0 = com.mob.commons.C2308i.class
            monitor-enter(r0)
            m2505J()     // Catch:{ all -> 0x0068 }
            com.mob.tools.utils.SharePrefrenceHelper r1 = f2142b     // Catch:{ all -> 0x0068 }
            java.lang.String r2 = "key_app_active_time"
            java.lang.String r1 = r1.getString(r2)     // Catch:{ all -> 0x0068 }
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x0068 }
            r2.<init>()     // Catch:{ all -> 0x0068 }
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0068 }
            if (r3 == 0) goto L_0x001b
            monitor-exit(r0)
            return r2
        L_0x001b:
            com.mob.tools.utils.Hashon r3 = new com.mob.tools.utils.Hashon     // Catch:{ all -> 0x0068 }
            r3.<init>()     // Catch:{ all -> 0x0068 }
            java.util.HashMap r1 = r3.fromJson((java.lang.String) r1)     // Catch:{ all -> 0x0068 }
            if (r1 == 0) goto L_0x0066
            boolean r3 = r1.isEmpty()     // Catch:{ all -> 0x0068 }
            if (r3 != 0) goto L_0x0066
            java.util.Set r1 = r1.entrySet()     // Catch:{ all -> 0x0068 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0068 }
        L_0x0034:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x0068 }
            if (r3 == 0) goto L_0x0066
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x0068 }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ all -> 0x0068 }
            if (r3 == 0) goto L_0x0034
            java.lang.Object r4 = r3.getKey()     // Catch:{ Throwable -> 0x0058 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Throwable -> 0x0058 }
            long r4 = java.lang.Long.parseLong(r4)     // Catch:{ Throwable -> 0x0058 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Throwable -> 0x0058 }
            java.lang.Object r3 = r3.getValue()     // Catch:{ Throwable -> 0x0058 }
            r2.put(r4, r3)     // Catch:{ Throwable -> 0x0058 }
            goto L_0x0034
        L_0x0058:
            r3 = move-exception
            com.mob.tools.log.NLog r4 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0068 }
            java.lang.String r5 = "Parse long error"
            r6 = 0
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ all -> 0x0068 }
            r4.mo29770d(r3, r5, r6)     // Catch:{ all -> 0x0068 }
            goto L_0x0034
        L_0x0066:
            monitor-exit(r0)
            return r2
        L_0x0068:
            r1 = move-exception
            monitor-exit(r0)
            goto L_0x006c
        L_0x006b:
            throw r1
        L_0x006c:
            goto L_0x006b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C2308i.m2563t():java.util.HashMap");
    }

    /* renamed from: b */
    public static synchronized void m2518b(HashMap<Long, Long> hashMap) {
        synchronized (C2308i.class) {
            m2505J();
            if (hashMap == null || hashMap.isEmpty()) {
                f2142b.remove("key_app_active_time");
            } else {
                try {
                    HashMap hashMap2 = new HashMap();
                    for (Map.Entry next : hashMap.entrySet()) {
                        if (next != null) {
                            hashMap2.put(String.valueOf(next.getKey()), next.getValue());
                        }
                    }
                    f2142b.putString("key_app_active_time", new Hashon().fromHashMap(hashMap2));
                } catch (Throwable th) {
                    MobLog.getInstance().mo29770d(th, "Parse String error", new Object[0]);
                }
            }
        }
    }

    /* renamed from: u */
    public static synchronized HashMap<String, Object> m2564u() {
        synchronized (C2308i.class) {
            m2505J();
            String string = f2142b.getString("key_channels");
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            HashMap<String, Object> fromJson = new Hashon().fromJson(string);
            return fromJson;
        }
    }

    /* renamed from: c */
    public static synchronized void m2524c(HashMap<String, Object> hashMap) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putString("key_channels", new Hashon().fromHashMap(hashMap));
        }
    }

    /* renamed from: k */
    public static synchronized void m2552k(String str) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putString("key_privacy_policy_txt", str);
        }
    }

    /* renamed from: v */
    public static synchronized String m2565v() {
        String string;
        synchronized (C2308i.class) {
            m2505J();
            string = f2142b.getString("key_privacy_policy_txt");
        }
        return string;
    }

    /* renamed from: a */
    public static synchronized void m2507a(int i) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putInt("key_policy_txt_related_version", Integer.valueOf(i));
        }
    }

    /* renamed from: w */
    public static synchronized int m2566w() {
        int i;
        synchronized (C2308i.class) {
            m2505J();
            i = f2142b.getInt("key_policy_txt_related_version");
        }
        return i;
    }

    /* renamed from: l */
    public static synchronized void m2554l(String str) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putString("key_privacy_policy_url", str);
        }
    }

    /* renamed from: x */
    public static synchronized String m2567x() {
        String string;
        synchronized (C2308i.class) {
            m2505J();
            string = f2142b.getString("key_privacy_policy_url");
        }
        return string;
    }

    /* renamed from: b */
    public static synchronized void m2515b(int i) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putInt("key_policy_url_related_version", Integer.valueOf(i));
        }
    }

    /* renamed from: y */
    public static synchronized int m2568y() {
        int i;
        synchronized (C2308i.class) {
            m2505J();
            i = f2142b.getInt("key_policy_url_related_version");
        }
        return i;
    }

    /* renamed from: m */
    public static synchronized void m2556m(String str) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putString("key_privacy_policy_language", str);
        }
    }

    /* renamed from: z */
    public static synchronized String m2569z() {
        String string;
        synchronized (C2308i.class) {
            m2505J();
            string = f2142b.getString("key_privacy_policy_language");
        }
        return string;
    }

    /* renamed from: a */
    public static synchronized void m2513a(boolean z) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putBoolean("key_pp_ncsy", Boolean.valueOf(z));
        }
    }

    /* renamed from: A */
    public static synchronized boolean m2496A() {
        boolean z;
        synchronized (C2308i.class) {
            m2505J();
            z = f2142b.getBoolean("key_pp_ncsy", MobSDK.checkPpNecessary());
        }
        return z;
    }

    /* renamed from: c */
    public static synchronized void m2521c(int i) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putInt("key_pp_grtd", Integer.valueOf(i));
        }
    }

    /* renamed from: B */
    public static synchronized int m2497B() {
        int i;
        synchronized (C2308i.class) {
            m2505J();
            i = f2142b.getInt("key_pp_grtd", -1);
        }
        return i;
    }

    /* renamed from: d */
    public static synchronized void m2526d(int i) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putInt("key_pprfms", Integer.valueOf(i));
        }
    }

    /* renamed from: C */
    public static synchronized int m2498C() {
        int i;
        synchronized (C2308i.class) {
            m2505J();
            i = f2142b.getInt("key_pprfms", -1);
        }
        return i;
    }

    /* renamed from: e */
    public static synchronized void m2530e(int i) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putInt("key_pprdms", Integer.valueOf(i));
        }
    }

    /* renamed from: D */
    public static synchronized int m2499D() {
        int i;
        synchronized (C2308i.class) {
            m2505J();
            i = f2142b.getInt("key_pprdms", -1);
        }
        return i;
    }

    /* renamed from: f */
    public static synchronized void m2534f(int i) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putInt("key_pprsbs", Integer.valueOf(i));
        }
    }

    /* renamed from: g */
    public static synchronized void m2538g(int i) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putInt("key_pprspw", Integer.valueOf(i));
        }
    }

    /* renamed from: a */
    public static synchronized void m2509a(Boolean bool) {
        int i;
        synchronized (C2308i.class) {
            m2505J();
            if (bool == null) {
                i = -1;
            } else {
                i = bool.booleanValue() ? 1 : 0;
            }
            f2142b.putInt("key_re_upload_policy_grant_result", Integer.valueOf(i));
        }
    }

    /* renamed from: E */
    public static synchronized Boolean m2500E() {
        boolean z;
        synchronized (C2308i.class) {
            m2505J();
            int i = f2142b.getInt("key_re_upload_policy_grant_result", -1);
            if (i == 1) {
                z = true;
            } else {
                z = i == 0 ? false : null;
            }
        }
        return z;
    }

    /* renamed from: F */
    public static synchronized long m2501F() {
        long j;
        synchronized (C2308i.class) {
            m2505J();
            j = f2142b.getLong("key_next_request_duid_time");
        }
        return j;
    }

    /* renamed from: i */
    public static synchronized void m2545i(long j) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putLong("key_next_request_duid_time", Long.valueOf(j));
        }
    }

    /* renamed from: G */
    public static synchronized long m2502G() {
        long j;
        synchronized (C2308i.class) {
            m2505J();
            j = f2142b.getLong("key_first_launch_time", 0);
        }
        return j;
    }

    /* renamed from: j */
    public static synchronized void m2548j(long j) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putLong("key_first_launch_time", Long.valueOf(j));
        }
    }

    /* renamed from: H */
    public static synchronized long m2503H() {
        long j;
        synchronized (C2308i.class) {
            m2505J();
            j = f2142b.getLong("key_next_dev_ext_var_upload_time");
        }
        return j;
    }

    /* renamed from: k */
    public static synchronized void m2551k(long j) {
        synchronized (C2308i.class) {
            m2505J();
            f2142b.putLong("key_next_dev_ext_var_upload_time", Long.valueOf(j));
        }
    }

    /* renamed from: I */
    public static void m2504I() {
        if (!f2141a) {
            f2141a = true;
            new Thread() {
                public void run() {
                    synchronized (C2300e.f2124a) {
                        try {
                            C2296d.m2449a().mo29071a(11);
                            C2300e.f2124a.wait();
                            HashMap hashMap = (HashMap) C2262b.m2291ap();
                            if (hashMap != null && hashMap.size() > 0) {
                                C2296d.m2449a().mo29071a(12);
                                Object obj = hashMap.get("h");
                                Object obj2 = hashMap.get(Config.APP_KEY);
                                Object obj3 = hashMap.get("b");
                                Object obj4 = hashMap.get("s");
                                Object obj5 = hashMap.get("cn");
                                Object obj6 = hashMap.get(UserDataStore.FIRST_NAME);
                                hashMap.clear();
                                C2293c.m2435a().mo29069a(obj, obj2, obj3, obj4, obj5, obj6);
                            }
                        } catch (Throwable th) {
                            C2296d.m2449a().mo29072a(3, th);
                        }
                    }
                }
            }.start();
        }
    }
}
