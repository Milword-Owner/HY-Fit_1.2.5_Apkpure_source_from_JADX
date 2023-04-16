package com.mob.commons;

import android.location.Location;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.blankj.utilcode.constant.CacheConstants;
import com.clj.fastble.BleManager;
import com.huayu.tzc.utils.DateUtil;
import com.mob.MobSDK;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.commons.p024b.C2273d;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.mob.commons.b */
/* compiled from: CommonConfig */
public class C2262b {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static HashMap<String, Object> f2028a = new HashMap<>();
    /* access modifiers changed from: private */

    /* renamed from: b */
    public static boolean f2029b = false;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public static volatile boolean f2030c = false;

    /* renamed from: d */
    private static int f2031d = 0;

    /* renamed from: e */
    private static Object f2032e = new Object();

    /* renamed from: f */
    private static Object f2033f = new Object();

    /* renamed from: g */
    private static HashMap<String, Object> f2034g;

    /* renamed from: h */
    private static HashMap<String, Object> f2035h = null;

    /* renamed from: i */
    private static Object f2036i = new Object();

    /* renamed from: j */
    private static long f2037j = 0;

    /* renamed from: com.mob.commons.b$a */
    /* compiled from: CommonConfig */
    public static abstract class C2269a {
        /* renamed from: a */
        public void mo28982a() {
        }
    }

    /* renamed from: a */
    private static boolean m2269a(long j) {
        return false;
    }

    /* renamed from: a */
    public static long m2260a() {
        long j;
        try {
            j = Long.valueOf(String.valueOf(f2034g.get("serverTime"))).longValue();
        } catch (Throwable unused) {
            j = 0;
        }
        if (j == 0) {
            return System.currentTimeMillis();
        }
        return j + m2297av();
    }

    /* renamed from: av */
    private static long m2297av() {
        long j;
        try {
            j = Long.valueOf(String.valueOf(f2034g.get("deviceTime"))).longValue();
        } catch (Throwable unused) {
            j = 0;
        }
        if (j == 0) {
            return 0;
        }
        return SystemClock.elapsedRealtime() - j;
    }

    /* renamed from: b */
    public static boolean m2305b() {
        return 1 == ((Integer) m2261a("conn", 0)).intValue();
    }

    /* renamed from: c */
    public static boolean m2307c() {
        return 1 == ((Integer) m2261a("rt", 0)).intValue();
    }

    /* renamed from: d */
    public static int m2309d() {
        return ((Integer) m2261a("rtsr", 180)).intValue();
    }

    /* renamed from: e */
    public static boolean m2310e() {
        return 1 == ((Integer) m2261a("in", 0)).intValue();
    }

    /* renamed from: f */
    public static boolean m2311f() {
        return 1 == ((Integer) m2261a("all", 0)).intValue();
    }

    /* renamed from: g */
    public static boolean m2312g() {
        return 1 == ((Integer) m2261a("un", 0)).intValue();
    }

    /* renamed from: h */
    public static long m2313h() {
        return ((Long) m2261a("aspa", 2592000L)).longValue();
    }

    /* renamed from: i */
    public static boolean m2314i() {
        return 1 == ((Integer) m2261a("di", 0)).intValue();
    }

    /* renamed from: j */
    public static boolean m2315j() {
        return 1 == ((Integer) m2261a("ext", 0)).intValue();
    }

    /* renamed from: k */
    public static boolean m2316k() {
        return 1 == ((Integer) m2261a("bs", 0)).intValue();
    }

    /* renamed from: l */
    public static int m2317l() {
        return ((Integer) m2261a("bsgap", Integer.valueOf(CacheConstants.DAY))).intValue();
    }

    /* renamed from: m */
    public static boolean m2318m() {
        return 1 == ((Integer) m2261a("l", 0)).intValue();
    }

    /* renamed from: n */
    public static int m2319n() {
        return ((Integer) m2261a("lgap", Integer.valueOf(CacheConstants.DAY))).intValue();
    }

    /* renamed from: o */
    public static boolean m2320o() {
        return 1 == ((Integer) m2261a("wi", 0)).intValue();
    }

    /* renamed from: p */
    public static int m2321p() {
        return ((Integer) m2261a("wigap", Integer.valueOf(CacheConstants.HOUR))).intValue();
    }

    /* renamed from: q */
    public static boolean m2322q() {
        return ((Integer) m2261a("wl", 0)).intValue() > 0;
    }

    /* renamed from: r */
    public static long m2323r() {
        return (long) ((Integer) m2261a("wlsr", 300)).intValue();
    }

    /* renamed from: s */
    public static int m2324s() {
        return ((Integer) m2261a("wlgap", 7200)).intValue();
    }

    /* renamed from: t */
    public static ArrayList<String> m2325t() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("SSID");
        arrayList.add("BSSID");
        arrayList.add("level");
        arrayList.add("frequency");
        arrayList.add("___curConn");
        return (ArrayList) m2261a("wisc", arrayList);
    }

    /* renamed from: u */
    public static long m2326u() {
        return m2260a() + (((long) ((Integer) m2261a("adle", 0)).intValue()) * 1000);
    }

    /* renamed from: v */
    public static long m2327v() {
        return ((long) ((Integer) m2261a("rtgap", Integer.valueOf(CacheConstants.HOUR))).intValue()) * 1000;
    }

    /* renamed from: w */
    public static boolean m2328w() {
        return 1 == ((Integer) m2261a("p", 0)).intValue();
    }

    /* renamed from: x */
    public static boolean m2329x() {
        return 1 == ((Integer) m2261a("ol", 0)).intValue();
    }

    /* renamed from: y */
    public static long m2330y() {
        return ((Long) m2261a("olgapl", 3600L)).longValue();
    }

    /* renamed from: z */
    public static long m2331z() {
        return ((Long) m2261a("olgaph", 60L)).longValue();
    }

    /* renamed from: A */
    public static long m2234A() {
        return ((Long) m2261a("xmar", 0L)).longValue() * 1000;
    }

    /* renamed from: B */
    public static boolean m2235B() {
        return 1 == ((Integer) m2261a("bi", 0)).intValue();
    }

    /* renamed from: C */
    public static long m2236C() {
        return ((Long) m2261a("bigap", 30L)).longValue();
    }

    /* renamed from: D */
    public static long m2237D() {
        return ((Long) m2261a(Config.PROCESS_LABEL, 0L)).longValue();
    }

    /* renamed from: E */
    public static long m2238E() {
        return ((Long) m2261a("plgap", 86400L)).longValue();
    }

    /* renamed from: F */
    public static long m2239F() {
        return ((Long) m2261a("le", 0L)).longValue();
    }

    /* renamed from: G */
    public static long m2240G() {
        return ((Long) m2261a("legap", 86400L)).longValue();
    }

    /* renamed from: H */
    public static long m2241H() {
        return ((Long) m2261a(Config.FEED_LIST_MAPPING, 0L)).longValue();
    }

    /* renamed from: a */
    public static void m2263a(long j, boolean z) {
        f2037j = j;
        if (!z && j == 0) {
            z = DeviceHelper.getInstance(MobSDK.getContext()).amIOnForeground();
        }
        if (z) {
            C2308i.m2527d(j);
        }
    }

    /* renamed from: I */
    public static boolean m2242I() {
        return m2269a(480000);
    }

    /* renamed from: J */
    public static boolean m2243J() {
        return m2269a(480000);
    }

    /* renamed from: K */
    public static long m2244K() {
        return ((long) ((Integer) m2261a("deup", 2)).intValue()) * 1000;
    }

    /* renamed from: L */
    public static long m2245L() {
        return ((long) ((Integer) m2261a("digap", 2592000)).intValue()) * 1000;
    }

    /* renamed from: M */
    public static long m2246M() {
        return ((Long) m2261a("pe", 0L)).longValue();
    }

    /* renamed from: N */
    public static long m2247N() {
        return ((Long) m2261a("ac", 0L)).longValue();
    }

    /* renamed from: O */
    public static long m2248O() {
        return ((Long) m2261a("sys", 0L)).longValue();
    }

    /* renamed from: P */
    public static long m2249P() {
        return ((Long) m2261a("sysgap", 2592000L)).longValue();
    }

    /* renamed from: Q */
    public static long m2250Q() {
        return ((Long) m2261a("arpl", 0L)).longValue();
    }

    /* renamed from: R */
    public static long m2251R() {
        return ((Long) m2261a("arplgap", 604800L)).longValue();
    }

    /* renamed from: S */
    public static boolean m2252S() {
        return ((Long) m2261a("gm", 0L)).longValue() == 1;
    }

    /* renamed from: T */
    public static long m2253T() {
        return ((Long) m2261a("aa", 0L)).longValue();
    }

    /* renamed from: U */
    public static long m2254U() {
        return ((Long) m2261a("aagap", 86400L)).longValue();
    }

    /* renamed from: V */
    public static long m2255V() {
        return ((Long) m2261a("rs", 0L)).longValue();
    }

    /* renamed from: W */
    public static long m2256W() {
        return ((Long) m2261a("at", 0L)).longValue();
    }

    /* renamed from: X */
    public static boolean m2257X() {
        return ((Integer) m2261a("lno", 0)).intValue() > 0;
    }

    /* renamed from: Y */
    public static long m2258Y() {
        return ((Long) m2261a("atgap", 900L)).longValue();
    }

    /* renamed from: Z */
    public static boolean m2259Z() {
        return 1 == ((Integer) m2261a("to", 0)).intValue();
    }

    /* renamed from: aa */
    public static boolean m2276aa() {
        HashMap<String, Object> hashMap = f2034g;
        if (1 == ((Integer) ResHelper.forceCast(hashMap != null ? hashMap.get("to") : null, 0)).intValue()) {
            return true;
        }
        return false;
    }

    /* renamed from: ab */
    public static boolean m2277ab() {
        return 1 == ((Integer) m2261a("ppl", 0)).intValue();
    }

    /* renamed from: ac */
    public static boolean m2278ac() {
        return 1 == ((Long) m2261a("dv", 0L)).longValue();
    }

    /* renamed from: ad */
    public static long m2279ad() {
        return ((Long) m2261a("dvch", 3600L)).longValue();
    }

    /* renamed from: ae */
    public static long m2280ae() {
        return ((Long) m2261a("dvuh", 3600L)).longValue();
    }

    /* renamed from: af */
    public static boolean m2281af() {
        return ((Integer) m2261a("cerr", 1)).intValue() == 1;
    }

    /* renamed from: ag */
    public static boolean m2282ag() {
        return ((Integer) m2261a("serr", 0)).intValue() == 1;
    }

    /* renamed from: ah */
    public static long m2283ah() {
        return ((Long) m2261a("strategyId", 0L)).longValue();
    }

    /* renamed from: ai */
    public static int m2284ai() {
        return ((Integer) m2261a("apm", 0)).intValue();
    }

    /* renamed from: aj */
    public static long m2285aj() {
        return ((Long) m2261a("apmhuh", 300L)).longValue();
    }

    /* renamed from: ak */
    public static long m2286ak() {
        return ((Long) m2261a("apmauh", 300L)).longValue();
    }

    /* renamed from: al */
    public static boolean m2287al() {
        return 1 == ((Integer) m2261a("oid", 0)).intValue();
    }

    /* renamed from: am */
    public static void m2288am() {
        m2304b((HashMap<String, Object>) null);
    }

    /* renamed from: a */
    public static void m2264a(final C2269a aVar) {
        if (!f2029b) {
            f2029b = true;
            new Thread() {
                public void run() {
                    try {
                        if (!C2262b.m2299ax()) {
                            C2262b.m2300ay();
                        }
                    } catch (Throwable th) {
                        MobLog.getInstance().mo29769d(th);
                    }
                    boolean unused = C2262b.f2029b = false;
                    C2269a aVar = aVar;
                    if (aVar != null) {
                        aVar.mo28982a();
                    }
                }
            }.start();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003c, code lost:
        r4 = com.mob.commons.C2262b.class;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003e, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0041, code lost:
        if (f2034g != null) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0043, code lost:
        if (r1 != false) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0049, code lost:
        if (m2299ax() != false) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x004d, code lost:
        if (f2029b != false) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x004f, code lost:
        f2029b = true;
        new com.mob.commons.C2262b.C22642().start();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0059, code lost:
        com.mob.commons.C2212a.m1949a(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x005c, code lost:
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x005f, code lost:
        if (f2034g == null) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0067, code lost:
        if ("to".equals(r6) == false) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0069, code lost:
        r1 = f2034g.get(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0086, code lost:
        if (1 == ((java.lang.Integer) com.mob.tools.utils.ResHelper.forceCast(f2034g.get("to"), 0)).intValue()) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x009e, code lost:
        if (1 != ((java.lang.Integer) com.mob.tools.utils.ResHelper.forceCast(f2034g.get("conn"), 0)).intValue()) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a0, code lost:
        r0 = (java.util.ArrayList) f2034g.get(com.baidu.mobstat.Config.CELL_LOCATION);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00aa, code lost:
        if (r0 == null) goto L_0x00cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00b0, code lost:
        if (r0.size() <= 0) goto L_0x00cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00b2, code lost:
        r0 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ba, code lost:
        if (r0.hasNext() == false) goto L_0x00ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00c6, code lost:
        if (((java.lang.String) r0.next()).equals(r6) == false) goto L_0x00b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00c8, code lost:
        r2 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ca, code lost:
        r3 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00cc, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00cd, code lost:
        if (r3 == 0) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00cf, code lost:
        r1 = f2034g.get(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00d7, code lost:
        com.mob.commons.C2212a.m1949a(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00da, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00db, code lost:
        if (r3 == 0) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00e1, code lost:
        return com.mob.tools.utils.ResHelper.forceCast(r1, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00e6, code lost:
        return m2302b(r6, r7);
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003b A[DONT_GENERATE] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> T m2261a(java.lang.String r6, T r7) {
        /*
            java.lang.Object r0 = f2033f
            monitor-enter(r0)
            java.util.HashMap<java.lang.String, java.lang.Object> r1 = f2034g     // Catch:{ all -> 0x00ea }
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0014
            java.util.HashMap<java.lang.String, java.lang.Object> r1 = f2034g     // Catch:{ all -> 0x00ea }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x00ea }
            if (r1 == 0) goto L_0x0012
            goto L_0x0014
        L_0x0012:
            r1 = 0
            goto L_0x0015
        L_0x0014:
            r1 = 1
        L_0x0015:
            boolean r1 = m2308c(r1)     // Catch:{ all -> 0x00ea }
            if (r1 != 0) goto L_0x003b
            java.lang.String r4 = "to"
            boolean r4 = r4.equals(r6)     // Catch:{ all -> 0x00ea }
            if (r4 != 0) goto L_0x002b
            java.lang.String r4 = "conn"
            boolean r4 = r4.equals(r6)     // Catch:{ all -> 0x00ea }
            if (r4 == 0) goto L_0x003b
        L_0x002b:
            java.lang.String r7 = "to"
            boolean r6 = r7.equals(r6)     // Catch:{ all -> 0x00ea }
            if (r6 == 0) goto L_0x0034
            goto L_0x0035
        L_0x0034:
            r2 = 1
        L_0x0035:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x00ea }
            monitor-exit(r0)     // Catch:{ all -> 0x00ea }
            return r6
        L_0x003b:
            monitor-exit(r0)     // Catch:{ all -> 0x00ea }
            java.lang.Class<com.mob.commons.b> r4 = com.mob.commons.C2262b.class
            monitor-enter(r4)
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = f2034g     // Catch:{ all -> 0x00e7 }
            if (r0 != 0) goto L_0x005c
            if (r1 != 0) goto L_0x005c
            boolean r0 = m2299ax()     // Catch:{ all -> 0x00e7 }
            if (r0 != 0) goto L_0x0059
            boolean r0 = f2029b     // Catch:{ all -> 0x00e7 }
            if (r0 != 0) goto L_0x0059
            f2029b = r3     // Catch:{ all -> 0x00e7 }
            com.mob.commons.b$2 r0 = new com.mob.commons.b$2     // Catch:{ all -> 0x00e7 }
            r0.<init>()     // Catch:{ all -> 0x00e7 }
            r0.start()     // Catch:{ all -> 0x00e7 }
        L_0x0059:
            com.mob.commons.C2212a.m1949a((int) r3)     // Catch:{ all -> 0x00e7 }
        L_0x005c:
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = f2034g     // Catch:{ all -> 0x00e7 }
            r1 = 0
            if (r0 == 0) goto L_0x00d7
            java.lang.String r0 = "to"
            boolean r0 = r0.equals(r6)     // Catch:{ all -> 0x00e7 }
            if (r0 == 0) goto L_0x0070
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = f2034g     // Catch:{ all -> 0x00e7 }
            java.lang.Object r1 = r0.get(r6)     // Catch:{ all -> 0x00e7 }
            goto L_0x00da
        L_0x0070:
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = f2034g     // Catch:{ all -> 0x00e7 }
            java.lang.String r5 = "to"
            java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x00e7 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x00e7 }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0, r5)     // Catch:{ all -> 0x00e7 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ all -> 0x00e7 }
            int r0 = r0.intValue()     // Catch:{ all -> 0x00e7 }
            if (r3 == r0) goto L_0x00da
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = f2034g     // Catch:{ all -> 0x00e7 }
            java.lang.String r5 = "conn"
            java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x00e7 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x00e7 }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0, r5)     // Catch:{ all -> 0x00e7 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ all -> 0x00e7 }
            int r0 = r0.intValue()     // Catch:{ all -> 0x00e7 }
            if (r3 != r0) goto L_0x00da
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = f2034g     // Catch:{ all -> 0x00e7 }
            java.lang.String r5 = "cl"
            java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x00e7 }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ all -> 0x00e7 }
            if (r0 == 0) goto L_0x00cc
            int r5 = r0.size()     // Catch:{ all -> 0x00e7 }
            if (r5 <= 0) goto L_0x00cc
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x00e7 }
        L_0x00b6:
            boolean r5 = r0.hasNext()     // Catch:{ all -> 0x00e7 }
            if (r5 == 0) goto L_0x00ca
            java.lang.Object r5 = r0.next()     // Catch:{ all -> 0x00e7 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x00e7 }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x00e7 }
            if (r5 == 0) goto L_0x00b6
            r2 = 1
            goto L_0x00b6
        L_0x00ca:
            r3 = r2
            goto L_0x00cd
        L_0x00cc:
            r3 = 0
        L_0x00cd:
            if (r3 == 0) goto L_0x00da
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = f2034g     // Catch:{ all -> 0x00e7 }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x00e7 }
            r1 = r0
            goto L_0x00da
        L_0x00d7:
            com.mob.commons.C2212a.m1949a((int) r2)     // Catch:{ all -> 0x00e7 }
        L_0x00da:
            monitor-exit(r4)     // Catch:{ all -> 0x00e7 }
            if (r3 == 0) goto L_0x00e2
            java.lang.Object r6 = com.mob.tools.utils.ResHelper.forceCast(r1, r7)
            return r6
        L_0x00e2:
            java.lang.Object r6 = m2302b(r6, r7)
            return r6
        L_0x00e7:
            r6 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x00e7 }
            throw r6
        L_0x00ea:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ea }
            goto L_0x00ee
        L_0x00ed:
            throw r6
        L_0x00ee:
            goto L_0x00ed
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C2262b.m2261a(java.lang.String, java.lang.Object):java.lang.Object");
    }

    /* renamed from: b */
    private static <T> T m2302b(String str, T t) {
        synchronized (f2036i) {
            if (f2035h == null) {
                try {
                    MobLog.getInstance().mo29768d("wait onlineLock", new Object[0]);
                    f2036i.wait(600000);
                } catch (Throwable th) {
                    MobLog.getInstance().mo29769d(th);
                }
            }
            if (f2035h == null) {
                return t;
            }
            T forceCast = ResHelper.forceCast(f2035h.get(str), t);
            return forceCast;
        }
    }

    /* renamed from: a */
    public static void m2267a(HashMap<String, Object> hashMap) {
        synchronized (f2036i) {
            f2035h = new HashMap<>();
            if (hashMap != null) {
                f2035h.putAll(hashMap);
            }
            MobLog.getInstance().mo29768d("notify onlineLock", new Object[0]);
            f2036i.notifyAll();
        }
    }

    /* renamed from: c */
    private static boolean m2308c(boolean z) {
        boolean z2 = m2297av() >= DateUtil.DAY_MILL_SECONDS;
        if (!z && !z2) {
            return true;
        }
        if (!m2298aw()) {
            return false;
        }
        if (z || z2) {
            m2264a((C2269a) null);
        }
        C2212a.m1949a(1);
        return true;
    }

    /* renamed from: aw */
    private static boolean m2298aw() {
        HashMap hashMap = null;
        try {
            String f = C2308i.m2533f();
            if (!TextUtils.isEmpty(f)) {
                hashMap = new Hashon().fromJson(f);
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return false;
        }
        if (hashMap != null) {
            if (!hashMap.isEmpty()) {
                m2304b((HashMap<String, Object>) hashMap);
                m2268a((HashMap<String, Object>) hashMap, false);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: ax */
    public static boolean m2299ax() {
        String aA = m2271aA();
        HashMap fromJson = !TextUtils.isEmpty(aA) ? new Hashon().fromJson(aA) : null;
        if (fromJson == null || fromJson.isEmpty()) {
            C2308i.m2528d((String) null);
            m2304b((HashMap<String, Object>) null);
            return false;
        }
        C2308i.m2528d(aA);
        m2267a((HashMap<String, Object>) fromJson);
        m2304b((HashMap<String, Object>) fromJson);
        m2275aE();
        m2268a((HashMap<String, Object>) fromJson, true);
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: ay */
    public static void m2300ay() {
        long j = 2;
        long j2 = 1;
        while (true) {
            try {
                Thread.sleep(1000 * j2);
            } catch (Throwable th) {
                try {
                    MobLog.getInstance().mo29769d(th);
                } catch (Throwable th2) {
                    m2267a((HashMap<String, Object>) null);
                    throw th2;
                }
            }
            boolean aC = m2273aC();
            if (aC) {
                if (j <= 0) {
                    m2275aE();
                    break;
                }
                j--;
            }
            j2 *= 2;
            if (!aC && j2 < 8) {
                j2 = 8;
            }
            if (j2 > 300) {
                j2 = 8;
            }
            if (!m2299ax()) {
                if (m2242I()) {
                    break;
                }
            } else {
                break;
            }
        }
        m2267a((HashMap<String, Object>) null);
    }

    /* renamed from: a */
    private static void m2262a(int i) {
        if (f2031d < i) {
            synchronized (f2032e) {
                f2031d = i;
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: az */
    public static boolean m2301az() {
        boolean z;
        synchronized (f2032e) {
            z = true;
            if (f2031d != 1) {
                z = false;
            }
        }
        return z;
    }

    /* renamed from: an */
    public static void m2289an() {
        if (m2301az()) {
            new Thread() {
                public void run() {
                    while (C2262b.m2301az() && C2262b.f2029b) {
                        try {
                            Thread.sleep(1000);
                        } catch (Throwable unused) {
                        }
                    }
                    if (C2262b.m2301az() && !C2262b.f2029b) {
                        boolean unused2 = C2262b.f2029b = true;
                        boolean unused3 = C2262b.f2030c = false;
                        try {
                            if (!C2262b.m2299ax()) {
                                C2262b.m2300ay();
                            }
                        } catch (Throwable th) {
                            MobLog.getInstance().mo29769d(th);
                        }
                        boolean unused4 = C2262b.f2029b = false;
                    }
                }
            }.start();
        }
    }

    /* renamed from: aA */
    private static String m2271aA() {
        String str;
        String str2;
        HashMap fromJson;
        try {
            if (m2242I()) {
                return null;
            }
            if (!m2273aC()) {
                return null;
            }
            DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
            NetworkHelper networkHelper = new NetworkHelper();
            String packageName = instance.getPackageName();
            String appkey = MobSDK.getAppkey();
            m2262a(TextUtils.isEmpty(appkey) ? 1 : 2);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("appkey", appkey));
            arrayList.add(new KVPair("plat", String.valueOf(instance.getPlatformCode())));
            arrayList.add(new KVPair("apppkg", packageName));
            arrayList.add(new KVPair("appver", instance.getAppVersionName()));
            arrayList.add(new KVPair("networktype", instance.getDetailNetworkTypeForStatic()));
            String authorizeForOnce = DeviceAuthorizer.authorizeForOnce();
            if (!TextUtils.isEmpty(authorizeForOnce)) {
                arrayList.add(new KVPair("duid", authorizeForOnce));
            }
            arrayList.add(new KVPair("ags", String.valueOf(instance.isPackageInstalled(C2312k.m2575a(17)) ? 1 : -1)));
            long currentTimeMillis = System.currentTimeMillis();
            arrayList.add(new KVPair("ts", String.valueOf(currentTimeMillis)));
            String defaultResolvePkg = instance.getDefaultResolvePkg(C2312k.m2575a(18));
            List<String> resolvePkgs = instance.getResolvePkgs(C2312k.m2575a(18));
            StringBuilder sb = new StringBuilder();
            sb.append(defaultResolvePkg);
            sb.append("|");
            if (resolvePkgs == null || resolvePkgs.size() <= 0) {
                sb.append("null");
            } else {
                int size = resolvePkgs.size();
                for (int i = 0; i < size; i++) {
                    sb.append(resolvePkgs.get(i));
                    if (i < size - 1) {
                        sb.append(",");
                    }
                }
            }
            arrayList.add(new KVPair("as", Base64.encodeToString(Data.AES128Encode(Data.rawMD5(appkey + Config.TRACE_TODAY_VISIT_SPLIT + packageName + Config.TRACE_TODAY_VISIT_SPLIT + currentTimeMillis), sb.toString()), 2)));
            Boolean e = C2212a.m1964e();
            if (e != null) {
                arrayList.add(new KVPair("isAgreePp", String.valueOf(e)));
            }
            Boolean f = C2212a.m1966f();
            if (f != null) {
                arrayList.add(new KVPair("isAgreePd", String.valueOf(f)));
            }
            arrayList.add(new KVPair("ppVersion", String.valueOf(C2212a.m1967g())));
            arrayList.add(new KVPair("v6", String.valueOf(MobSDK.checkV6() ? 1 : -1)));
            arrayList.add(new KVPair("uc", String.valueOf(instance.checkUA() ? 1 : 0)));
            arrayList.add(new KVPair("ud", String.valueOf(instance.usbEnable() ? 1 : 0)));
            arrayList.add(new KVPair("dv", String.valueOf(instance.devEnable() ? 1 : 0)));
            arrayList.add(new KVPair("vp", String.valueOf(instance.vpn() ? 1 : 0)));
            arrayList.add(new KVPair("wp", String.valueOf(instance.isWifiProxy() ? 1 : 0)));
            arrayList.add(new KVPair("rt", String.valueOf(instance.isRooted() ? 1 : 0)));
            arrayList.add(new KVPair(Config.EVENT_HEAT_XP, String.valueOf(instance.mo29988cx() ? 1 : 0)));
            arrayList.add(new KVPair("ad", String.valueOf(instance.debugable() ? 1 : 0)));
            NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
            networkTimeOut.readTimout = 30000;
            networkTimeOut.connectionTimeout = BleManager.DEFAULT_SCAN_TIME;
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new KVPair("User-Identity", MobProductCollector.getUserIdentity()));
            arrayList2.add(new KVPair(C2312k.m2575a(68), C2273d.m2354d(MobSDK.getContext())));
            String httpGet = networkHelper.httpGet(m2274aD(), arrayList, arrayList2, networkTimeOut);
            Hashon hashon = new Hashon();
            HashMap fromJson2 = hashon.fromJson(httpGet);
            if (fromJson2 == null) {
                return null;
            }
            if ("200".equals(String.valueOf(fromJson2.get("status")))) {
                String str3 = (String) ResHelper.forceCast(fromJson2.get("sr"));
                byte[] rawMD5 = Data.rawMD5((appkey + Config.TRACE_TODAY_VISIT_SPLIT + packageName + Config.TRACE_TODAY_VISIT_SPLIT + fromJson2.get("timestamp")).getBytes("utf-8"));
                if (str3 == null || (fromJson = hashon.fromJson(new String(Data.AES128Decode(rawMD5, Base64.decode(str3, 2)), "utf-8"))) == null) {
                    str2 = httpGet;
                } else {
                    HashMap hashMap = (HashMap) ResHelper.forceCast(fromJson.get("cdata"));
                    if (hashMap != null) {
                        String str4 = (String) ResHelper.forceCast(hashMap.get("host"));
                        str2 = httpGet;
                        int intValue = ((Integer) ResHelper.forceCast(hashMap.get("httpport"), 0)).intValue();
                        String str5 = (String) ResHelper.forceCast(hashMap.get(Config.FEED_LIST_ITEM_PATH));
                        if (str4 == null || intValue == 0 || str5 == null) {
                            try {
                                C2308i.m2532e((String) null);
                            } catch (Throwable th) {
                                th = th;
                                str = null;
                                MobLog.getInstance().mo29787w(th);
                                try {
                                    C2308i.m2532e(str);
                                    C2308i.m2536f(str);
                                } catch (Throwable th2) {
                                    MobLog.getInstance().mo29769d(th2);
                                }
                                return str;
                            }
                        } else {
                            C2308i.m2532e("http://" + str4 + Config.TRACE_TODAY_VISIT_SPLIT + intValue + str5);
                        }
                    } else {
                        str2 = httpGet;
                        C2308i.m2532e((String) null);
                    }
                    HashMap hashMap2 = (HashMap) ResHelper.forceCast(fromJson.get("cconf"));
                    if (hashMap2 != null) {
                        String str6 = (String) ResHelper.forceCast(hashMap2.get("host"));
                        int intValue2 = ((Integer) ResHelper.forceCast(hashMap2.get("httpport"), 0)).intValue();
                        String str7 = (String) ResHelper.forceCast(hashMap2.get(Config.FEED_LIST_ITEM_PATH));
                        if (str6 == null || intValue2 == 0 || str7 == null) {
                            try {
                                C2308i.m2536f((String) null);
                            } catch (Throwable th3) {
                                th = th3;
                                str = null;
                                MobLog.getInstance().mo29787w(th);
                                C2308i.m2532e(str);
                                C2308i.m2536f(str);
                                return str;
                            }
                        } else {
                            C2308i.m2536f("http://" + str6 + Config.TRACE_TODAY_VISIT_SPLIT + intValue2 + str7);
                        }
                    } else {
                        C2308i.m2536f((String) null);
                    }
                }
                String str8 = (String) ResHelper.forceCast(fromJson2.get(Config.STAT_SDK_CHANNEL));
                if (str8 != null) {
                    HashMap fromJson3 = hashon.fromJson(new String(Data.AES128Decode(rawMD5, Base64.decode(str8, 2)), "utf-8"));
                    if (fromJson3 != null) {
                        m2265a(fromJson3.get("illegalMacs"));
                        long longValue = ((Long) ResHelper.forceCast(fromJson2.get("timestamp"), 0L)).longValue();
                        fromJson3.put("deviceTime", Long.valueOf(SystemClock.elapsedRealtime()));
                        fromJson3.put("serverTime", Long.valueOf(longValue));
                        return hashon.fromHashMap(fromJson3);
                    }
                    throw new Throwable("response is illegal: " + str2);
                }
                throw new Throwable("response is illegal: " + str2);
            }
            throw new Throwable("response is illegal: " + httpGet);
        } catch (Throwable th4) {
            th = th4;
            str = null;
            MobLog.getInstance().mo29787w(th);
            C2308i.m2532e(str);
            C2308i.m2536f(str);
            return str;
        }
    }

    /* renamed from: a */
    private static void m2265a(Object obj) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("list", obj);
            File dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), ".mcli");
            ResHelper.saveObjectToFile(dataCacheFile.getPath(), Data.AES128Encode("1234567890abcdfi", new Hashon().fromHashMap(hashMap)));
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    /* renamed from: b */
    private static synchronized void m2304b(HashMap<String, Object> hashMap) {
        synchronized (C2262b.class) {
            if (hashMap != null) {
                if (!hashMap.isEmpty()) {
                    f2034g = hashMap;
                }
            }
            if (f2034g == null || f2034g.isEmpty()) {
                m2272aB();
            }
        }
    }

    /* renamed from: a */
    public static void m2268a(HashMap<String, Object> hashMap, boolean z) {
        if (!f2030c && hashMap != null && ((Integer) ResHelper.forceCast(hashMap.get("to"), 0)).intValue() != 1 && ((Integer) ResHelper.forceCast(hashMap.get("conn"), 0)).intValue() != 0) {
            f2030c = true;
            final String str = (String) hashMap.get("fnc");
            if (TextUtils.isEmpty(str) && !z) {
                f2030c = false;
            } else if (!TextUtils.isEmpty(str)) {
                new MobHandlerThread() {
                    public void run() {
                        C2300e.m2468a(C2300e.m2466a("comm/locks/.dy_lock"), true, new LockAction() {
                            public boolean run(FileLocker fileLocker) {
                                try {
                                    synchronized (C2262b.f2028a) {
                                        C2262b.m2303b(str);
                                    }
                                    C22664.this.m2332a();
                                    return false;
                                } catch (Throwable th) {
                                    C2296d.m2449a().mo29072a(1, th);
                                    return false;
                                }
                            }
                        });
                    }

                    /* access modifiers changed from: private */
                    /* renamed from: a */
                    public void m2332a() {
                        super.run();
                    }
                }.start();
            }
        }
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* renamed from: b */
    public static void m2303b(java.lang.String r15) {
        /*
            r0 = 2
            r1 = 0
            com.mob.commons.d r2 = com.mob.commons.C2296d.m2449a()     // Catch:{ Throwable -> 0x01aa }
            r3 = 0
            r2.mo29071a((int) r3)     // Catch:{ Throwable -> 0x01aa }
            java.lang.String r15 = com.mob.commons.C2310j.m2574c(r15)     // Catch:{ Throwable -> 0x01aa }
            java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x01aa }
            android.content.Context r4 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x01aa }
            java.io.File r4 = r4.getFilesDir()     // Catch:{ Throwable -> 0x01aa }
            r5 = 5
            java.lang.String r6 = com.mob.commons.C2312k.m2575a(r5)     // Catch:{ Throwable -> 0x01aa }
            r2.<init>(r4, r6)     // Catch:{ Throwable -> 0x01aa }
            android.content.Context r4 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x01a8 }
            com.mob.tools.utils.DeviceHelper r4 = com.mob.tools.utils.DeviceHelper.getInstance(r4)     // Catch:{ Throwable -> 0x01a8 }
            r6 = 17
            boolean r6 = r4.checkADBModel(r6)     // Catch:{ Throwable -> 0x01a8 }
            if (r6 == 0) goto L_0x0044
            boolean r4 = r4.checkUA()     // Catch:{ Throwable -> 0x01a8 }
            if (r4 == 0) goto L_0x0044
            com.mob.commons.d r15 = com.mob.commons.C2296d.m2449a()     // Catch:{ Throwable -> 0x01a8 }
            r1 = 18
            r15.mo29071a((int) r1)     // Catch:{ Throwable -> 0x01a8 }
            com.mob.tools.utils.ResHelper.deleteFileAndFolder(r2)     // Catch:{ Throwable -> 0x01a8 }
            goto L_0x01b8
        L_0x0044:
            boolean r4 = android.text.TextUtils.isEmpty(r15)     // Catch:{ Throwable -> 0x01a8 }
            r6 = 1
            if (r4 == 0) goto L_0x0057
            com.mob.commons.d r15 = com.mob.commons.C2296d.m2449a()     // Catch:{ Throwable -> 0x01a8 }
            r15.mo29071a((int) r6)     // Catch:{ Throwable -> 0x01a8 }
            com.mob.tools.utils.ResHelper.deleteFileAndFolder(r2)     // Catch:{ Throwable -> 0x01a8 }
            goto L_0x01b8
        L_0x0057:
            r4 = 10
            com.mob.commons.d r7 = com.mob.commons.C2296d.m2449a()     // Catch:{ all -> 0x0193 }
            r7.mo29071a((int) r0)     // Catch:{ all -> 0x0193 }
            java.util.HashMap r7 = m2290ao()     // Catch:{ all -> 0x0193 }
            com.mob.MobCommunicator r8 = new com.mob.MobCommunicator     // Catch:{ all -> 0x0193 }
            r9 = 1024(0x400, float:1.435E-42)
            java.lang.String r10 = "9e87e8d4b8f52f2916d0fb4342aa6b54a81a05666d0bdb23cc5ebf3a07440bc3976adff1ce11c64ddcdbfc017920648217196d51e3165e780e58b5460c525ee9"
            java.lang.String r11 = "13bda4b87eb42ab9e64e6b4f3d17cf8005a4ae94af37bc9fd76ebd91a828f017c81bd63cbe2924e361e20003b9e5f47cdac1f5fba5fca05730a32c5c65869590287207e79a604a2aac429e55f0d35c211367bd226dd5e57df7810f036071854aa1061a0f34b418b9178895a531107c652a428cfa6ecfa65333580ae7e0edf0e1"
            r8.<init>(r9, r10, r11)     // Catch:{ all -> 0x0193 }
            java.lang.Object r15 = r8.requestSynchronized((java.util.HashMap<java.lang.String, java.lang.Object>) r7, (java.lang.String) r15, (boolean) r3)     // Catch:{ all -> 0x0193 }
            java.util.HashMap r15 = (java.util.HashMap) r15     // Catch:{ all -> 0x0193 }
            com.mob.commons.d r7 = com.mob.commons.C2296d.m2449a()     // Catch:{ all -> 0x0193 }
            r8 = 3
            r7.mo29071a((int) r8)     // Catch:{ all -> 0x0193 }
            java.lang.String r7 = "fl"
            java.lang.Object r7 = r15.get(r7)     // Catch:{ all -> 0x0193 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x0193 }
            java.lang.String r8 = "m"
            java.lang.Object r8 = r15.get(r8)     // Catch:{ all -> 0x0193 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x0193 }
            java.lang.String r9 = "as"
            java.lang.Object r9 = r15.get(r9)     // Catch:{ all -> 0x0193 }
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ all -> 0x0193 }
            boolean r9 = r9.booleanValue()     // Catch:{ all -> 0x0193 }
            java.lang.String r10 = "ak"
            java.lang.Object r10 = r15.get(r10)     // Catch:{ all -> 0x0193 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x0193 }
            java.lang.String r11 = "cn"
            java.lang.Object r11 = r15.get(r11)     // Catch:{ all -> 0x0193 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x0193 }
            java.lang.String r12 = "fn"
            java.lang.Object r15 = r15.get(r12)     // Catch:{ all -> 0x0193 }
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ all -> 0x0193 }
            boolean r12 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0193 }
            if (r12 != 0) goto L_0x0174
            boolean r12 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x0193 }
            if (r12 != 0) goto L_0x0174
            boolean r12 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x0193 }
            if (r12 == 0) goto L_0x00c5
            goto L_0x0174
        L_0x00c5:
            java.lang.Object r12 = com.mob.commons.C2300e.f2124a     // Catch:{ all -> 0x0193 }
            monitor-enter(r12)     // Catch:{ all -> 0x0193 }
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = f2028a     // Catch:{ all -> 0x0171 }
            r13.clear()     // Catch:{ all -> 0x0171 }
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = f2028a     // Catch:{ all -> 0x0171 }
            java.lang.String r14 = "h"
            r13.put(r14, r8)     // Catch:{ all -> 0x0171 }
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = f2028a     // Catch:{ all -> 0x0171 }
            java.lang.String r14 = "k"
            r13.put(r14, r10)     // Catch:{ all -> 0x0171 }
            java.util.HashMap<java.lang.String, java.lang.Object> r10 = f2028a     // Catch:{ all -> 0x0171 }
            java.lang.String r13 = "cn"
            r10.put(r13, r11)     // Catch:{ all -> 0x0171 }
            java.util.HashMap<java.lang.String, java.lang.Object> r10 = f2028a     // Catch:{ all -> 0x0171 }
            java.lang.String r11 = "fn"
            r10.put(r11, r15)     // Catch:{ all -> 0x0171 }
            if (r9 == 0) goto L_0x0130
            com.mob.commons.d r15 = com.mob.commons.C2296d.m2449a()     // Catch:{ all -> 0x0171 }
            r15.mo29071a((int) r5)     // Catch:{ all -> 0x0171 }
            java.io.File r15 = new java.io.File     // Catch:{ all -> 0x0171 }
            java.lang.String r3 = "conf.scc"
            r15.<init>(r2, r3)     // Catch:{ all -> 0x0171 }
            boolean r3 = r15.exists()     // Catch:{ all -> 0x0171 }
            if (r3 == 0) goto L_0x0109
            java.lang.String r3 = com.mob.tools.utils.Data.MD5((java.io.File) r15)     // Catch:{ all -> 0x0171 }
            boolean r3 = r8.equals(r3)     // Catch:{ all -> 0x0171 }
            if (r3 != 0) goto L_0x016f
        L_0x0109:
            com.mob.commons.d r3 = com.mob.commons.C2296d.m2449a()     // Catch:{ all -> 0x0171 }
            r5 = 6
            r3.mo29071a((int) r5)     // Catch:{ all -> 0x0171 }
            com.mob.tools.utils.ResHelper.deleteFileAndFolder(r2)     // Catch:{ all -> 0x0171 }
            r2.mkdirs()     // Catch:{ all -> 0x0171 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x0171 }
            r3.<init>(r15)     // Catch:{ all -> 0x0171 }
            com.mob.tools.network.NetworkHelper r15 = new com.mob.tools.network.NetworkHelper     // Catch:{ all -> 0x0171 }
            r15.<init>()     // Catch:{ all -> 0x0171 }
            r15.download(r7, r3, r1)     // Catch:{ all -> 0x0171 }
            com.mob.commons.d r15 = com.mob.commons.C2296d.m2449a()     // Catch:{ all -> 0x0171 }
            r1 = 7
            r15.mo29071a((int) r1)     // Catch:{ all -> 0x0171 }
            r3.close()     // Catch:{ all -> 0x0171 }
            goto L_0x016f
        L_0x0130:
            com.mob.commons.d r15 = com.mob.commons.C2296d.m2449a()     // Catch:{ all -> 0x0171 }
            r5 = 8
            r15.mo29071a((int) r5)     // Catch:{ all -> 0x0171 }
            com.mob.tools.utils.ResHelper.deleteFileAndFolder(r2)     // Catch:{ all -> 0x0171 }
            byte[][] r15 = new byte[r6][]     // Catch:{ all -> 0x0171 }
            int[] r5 = new int[r6]     // Catch:{ all -> 0x0171 }
            com.mob.commons.b$5 r6 = new com.mob.commons.b$5     // Catch:{ all -> 0x0171 }
            r6.<init>(r15, r5)     // Catch:{ all -> 0x0171 }
            com.mob.tools.network.NetworkHelper r8 = new com.mob.tools.network.NetworkHelper     // Catch:{ all -> 0x0171 }
            r8.<init>()     // Catch:{ all -> 0x0171 }
            r8.download(r7, r6, r1)     // Catch:{ all -> 0x0171 }
            com.mob.commons.d r1 = com.mob.commons.C2296d.m2449a()     // Catch:{ all -> 0x0171 }
            r7 = 9
            r1.mo29071a((int) r7)     // Catch:{ all -> 0x0171 }
            r6.close()     // Catch:{ all -> 0x0171 }
            java.util.HashMap<java.lang.String, java.lang.Object> r1 = f2028a     // Catch:{ all -> 0x0171 }
            java.lang.String r6 = "b"
            r15 = r15[r3]     // Catch:{ all -> 0x0171 }
            r1.put(r6, r15)     // Catch:{ all -> 0x0171 }
            java.util.HashMap<java.lang.String, java.lang.Object> r15 = f2028a     // Catch:{ all -> 0x0171 }
            java.lang.String r1 = "s"
            r3 = r5[r3]     // Catch:{ all -> 0x0171 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0171 }
            r15.put(r1, r3)     // Catch:{ all -> 0x0171 }
        L_0x016f:
            monitor-exit(r12)     // Catch:{ all -> 0x0171 }
            goto L_0x017f
        L_0x0171:
            r15 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x0171 }
            throw r15     // Catch:{ all -> 0x0193 }
        L_0x0174:
            com.mob.commons.d r15 = com.mob.commons.C2296d.m2449a()     // Catch:{ all -> 0x0193 }
            r1 = 4
            r15.mo29071a((int) r1)     // Catch:{ all -> 0x0193 }
            com.mob.tools.utils.ResHelper.deleteFileAndFolder(r2)     // Catch:{ all -> 0x0193 }
        L_0x017f:
            java.lang.Object r15 = com.mob.commons.C2300e.f2124a     // Catch:{ Throwable -> 0x01a8 }
            monitor-enter(r15)     // Catch:{ Throwable -> 0x01a8 }
            com.mob.commons.d r1 = com.mob.commons.C2296d.m2449a()     // Catch:{ all -> 0x0190 }
            r1.mo29071a((int) r4)     // Catch:{ all -> 0x0190 }
            java.lang.Object r1 = com.mob.commons.C2300e.f2124a     // Catch:{ all -> 0x0190 }
            r1.notifyAll()     // Catch:{ all -> 0x0190 }
            monitor-exit(r15)     // Catch:{ all -> 0x0190 }
            goto L_0x01b8
        L_0x0190:
            r1 = move-exception
            monitor-exit(r15)     // Catch:{ all -> 0x0190 }
            throw r1     // Catch:{ Throwable -> 0x01a8 }
        L_0x0193:
            r15 = move-exception
            java.lang.Object r1 = com.mob.commons.C2300e.f2124a     // Catch:{ Throwable -> 0x01a8 }
            monitor-enter(r1)     // Catch:{ Throwable -> 0x01a8 }
            com.mob.commons.d r3 = com.mob.commons.C2296d.m2449a()     // Catch:{ all -> 0x01a5 }
            r3.mo29071a((int) r4)     // Catch:{ all -> 0x01a5 }
            java.lang.Object r3 = com.mob.commons.C2300e.f2124a     // Catch:{ all -> 0x01a5 }
            r3.notifyAll()     // Catch:{ all -> 0x01a5 }
            monitor-exit(r1)     // Catch:{ all -> 0x01a5 }
            throw r15     // Catch:{ Throwable -> 0x01a8 }
        L_0x01a5:
            r15 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x01a5 }
            throw r15     // Catch:{ Throwable -> 0x01a8 }
        L_0x01a8:
            r15 = move-exception
            goto L_0x01ac
        L_0x01aa:
            r15 = move-exception
            r2 = r1
        L_0x01ac:
            if (r2 == 0) goto L_0x01b1
            com.mob.tools.utils.ResHelper.deleteFileAndFolder(r2)     // Catch:{ Throwable -> 0x01b1 }
        L_0x01b1:
            com.mob.commons.d r1 = com.mob.commons.C2296d.m2449a()
            r1.mo29072a(r0, r15)
        L_0x01b8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C2262b.m2303b(java.lang.String):void");
    }

    /* renamed from: ao */
    public static HashMap<String, Object> m2290ao() {
        Location location;
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
        hashMap.put(C2312k.m2575a(41), MobSDK.getAppkey());
        hashMap.put(C2312k.m2575a(42), Integer.valueOf(MobSDK.SDK_VERSION_CODE));
        hashMap.put(C2312k.m2575a(64), 1);
        hashMap.put(C2312k.m2575a(43), DeviceAuthorizer.authorize((MobProduct) null));
        hashMap.put(C2312k.m2575a(44), MobSDK.getContext().getPackageName());
        hashMap.put(C2312k.m2575a(45), Integer.valueOf(instance.getAppVersion()));
        hashMap.put(C2312k.m2575a(46), instance.getIMEI());
        hashMap.put(C2312k.m2575a(47), instance.getSerialno());
        hashMap.put(C2312k.m2575a(48), instance.getMacAddress());
        hashMap.put(C2312k.m2575a(49), instance.getCarrier());
        hashMap.put(C2312k.m2575a(50), instance.getModel());
        hashMap.put(C2312k.m2575a(51), instance.getManufacturer());
        hashMap.put(C2312k.m2575a(52), instance.getNetworkType());
        hashMap.put(C2312k.m2575a(53), instance.getOSVersionName());
        hashMap.put(C2312k.m2575a(54), instance.getMIUIVersion());
        hashMap.put(C2312k.m2575a(55), Integer.valueOf(instance.getOSVersionInt()));
        if (m2329x() && (location = instance.getLocation(0, 0, true)) != null) {
            hashMap.put(C2312k.m2575a(56), Float.valueOf(location.getAccuracy()));
            hashMap.put(C2312k.m2575a(57), Double.valueOf(location.getLatitude()));
            hashMap.put(C2312k.m2575a(58), Double.valueOf(location.getLongitude()));
        }
        hashMap.put(C2312k.m2575a(59), Long.valueOf(System.currentTimeMillis()));
        hashMap.put(C2312k.m2575a(60), instance.getSignMD5());
        hashMap.put(C2312k.m2575a(61), Integer.valueOf(instance.cscreen()));
        try {
            hashMap.put(C2312k.m2575a(62), Integer.valueOf(instance.mo30100ih(MobSDK.getContext())));
        } catch (Throwable unused) {
        }
        hashMap.put(C2312k.m2575a(63), Boolean.valueOf(instance.amIOnForeground()));
        hashMap.put(C2312k.m2575a(65), instance.getAndroidID());
        hashMap.put(C2312k.m2575a(66), instance.getIMSI());
        hashMap.put(C2312k.m2575a(67), Build.BRAND);
        return hashMap;
    }

    /* renamed from: ap */
    public static Object m2291ap() {
        return f2028a;
    }

    /* renamed from: aB */
    private static void m2272aB() {
        f2034g = new HashMap<>();
        f2034g.put("conn", 0);
        f2034g.put("in", 0);
        f2034g.put("all", 0);
        f2034g.put("aspa", 2592000L);
        f2034g.put("un", 0);
        f2034g.put("rt", 0);
        f2034g.put("rtsr", 180);
        f2034g.put("ext", 0);
        f2034g.put("bs", 0);
        HashMap<String, Object> hashMap = f2034g;
        Integer valueOf = Integer.valueOf(CacheConstants.DAY);
        hashMap.put("bsgap", valueOf);
        f2034g.put("di", 0);
        f2034g.put("l", 0);
        f2034g.put("lgap", valueOf);
        f2034g.put("wi", 0);
        f2034g.put("wigap", 3600L);
        f2034g.put("wl", 0);
        f2034g.put("wlsr", 300);
        f2034g.put("wlgap", 7200);
        f2034g.put("adle", 0);
        f2034g.put("rtgap", Integer.valueOf(CacheConstants.HOUR));
        f2034g.put("p", 0);
        f2034g.put("ol", 0);
        f2034g.put("olgapl", 3600L);
        f2034g.put("olgaph", 60L);
        f2034g.put("xmar", 0);
        f2034g.put("bi", 0);
        f2034g.put("bigap", 30L);
        f2034g.put(Config.PROCESS_LABEL, 0);
        f2034g.put("plgap", 86400L);
        f2034g.put("le", 0L);
        f2034g.put("legap", 86400L);
        f2034g.put(Config.FEED_LIST_MAPPING, 0L);
        f2034g.put("deup", 2);
        f2034g.put("digap", 2592000L);
        f2034g.put("illegalMacs", (Object) null);
        f2034g.put("pe", 0L);
        f2034g.put("pegap", 86400L);
        f2034g.put("ac", 0L);
        f2034g.put("acgap", 86400L);
        f2034g.put("sys", 0L);
        f2034g.put("sysgap", 2592000L);
        f2034g.put("arpl", 0L);
        f2034g.put("arplgap", 604800L);
        f2034g.put("mph", 0L);
        f2034g.put("aw", (Object) null);
        f2034g.put("to", 0);
        f2034g.put("gm", 0);
        f2034g.put("gmgap", 900);
        f2034g.put("aa", 0L);
        f2034g.put("aagap", 86400L);
        f2034g.put("rs", 0L);
        f2034g.put("rsgap", 86400L);
        f2034g.put(Config.CELL_LOCATION, (Object) null);
        f2034g.put("at", 0L);
        f2034g.put("atgap", 900L);
        f2034g.put("bt", 0L);
        f2034g.put("bts", 0L);
        f2034g.put("btsgap", 7200L);
        f2034g.put("ppl", 0L);
        f2034g.put("lno", 0);
        f2034g.put("dv", 0L);
        f2034g.put("dvch", 3600L);
        f2034g.put("dvuh", 3600L);
        f2034g.put("cerr", 1);
        f2034g.put("serr", 0);
        f2034g.put("strategyId", 0L);
        f2034g.put("apm", 0);
        f2034g.put("apmhuh", 300L);
        f2034g.put("apmauh", 300L);
        f2034g.put("oid", 0);
    }

    /* renamed from: aC */
    private static boolean m2273aC() {
        try {
            String detailNetworkTypeForStatic = DeviceHelper.getInstance(MobSDK.getContext()).getDetailNetworkTypeForStatic();
            if ("wifi".equals(detailNetworkTypeForStatic) || "5g".equals(detailNetworkTypeForStatic) || "4g".equals(detailNetworkTypeForStatic) || "3g".equals(detailNetworkTypeForStatic) || "2g".equals(detailNetworkTypeForStatic)) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* renamed from: aD */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m2274aD() {
        /*
            java.lang.String r0 = com.mob.commons.C2308i.m2541h()     // Catch:{ Throwable -> 0x000b }
            java.lang.String r0 = com.mob.commons.C2310j.m2573b(r0)     // Catch:{ Throwable -> 0x0009 }
            goto L_0x0014
        L_0x0009:
            r1 = move-exception
            goto L_0x000d
        L_0x000b:
            r1 = move-exception
            r0 = 0
        L_0x000d:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29787w((java.lang.Throwable) r1)
        L_0x0014:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x002f
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = com.mob.commons.C2310j.m2570a()
            r0.append(r1)
            java.lang.String r1 = "/v5/gcf"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L_0x002f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C2262b.m2274aD():java.lang.String");
    }

    /* renamed from: aE */
    private static void m2275aE() {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("apm", Integer.valueOf(m2284ai()));
            hashMap.put("apmhuh", Long.valueOf(m2285aj()));
            hashMap.put("apmauh", Long.valueOf(m2286ak()));
            String fromHashMap = new Hashon().fromHashMap(hashMap);
            ReflectHelper.invokeStaticMethod(ReflectHelper.importClass("com.mob.mobapm.MobAPM"), "setJson", fromHashMap);
            NLog instance = MobLog.getInstance();
            instance.mo29768d(">>>>> Has APM <<<<< conf: " + fromHashMap, new Object[0]);
        } catch (Throwable unused) {
            MobLog.getInstance().mo29768d(">>>>> No APM <<<<<", new Object[0]);
        }
    }
}
