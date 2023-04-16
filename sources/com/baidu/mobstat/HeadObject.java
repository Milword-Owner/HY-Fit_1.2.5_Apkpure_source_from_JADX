package com.baidu.mobstat;

import android.content.Context;
import org.json.JSONObject;

public class HeadObject {

    /* renamed from: A */
    JSONObject f822A;

    /* renamed from: B */
    JSONObject f823B;

    /* renamed from: C */
    String f824C;

    /* renamed from: D */
    int f825D;

    /* renamed from: E */
    String f826E = "";

    /* renamed from: F */
    String f827F;

    /* renamed from: G */
    String f828G = "";

    /* renamed from: a */
    boolean f829a = false;

    /* renamed from: b */
    String f830b;

    /* renamed from: c */
    String f831c;

    /* renamed from: d */
    String f832d = "0";

    /* renamed from: e */
    String f833e = null;

    /* renamed from: f */
    String f834f = null;

    /* renamed from: g */
    int f835g = -1;

    /* renamed from: h */
    String f836h;

    /* renamed from: i */
    String f837i;

    /* renamed from: j */
    int f838j;

    /* renamed from: k */
    int f839k;

    /* renamed from: l */
    String f840l = null;

    /* renamed from: m */
    String f841m;

    /* renamed from: n */
    String f842n;

    /* renamed from: o */
    String f843o;

    /* renamed from: p */
    String f844p;

    /* renamed from: q */
    String f845q;

    /* renamed from: r */
    String f846r;

    /* renamed from: s */
    String f847s;

    /* renamed from: t */
    String f848t;

    /* renamed from: u */
    String f849u;

    /* renamed from: v */
    String f850v;

    /* renamed from: w */
    String f851w;

    /* renamed from: x */
    String f852x;

    /* renamed from: y */
    String f853y;

    /* renamed from: z */
    String f854z;

    public synchronized void installHeader(Context context, JSONObject jSONObject) {
        m757a(context);
        if (jSONObject.length() <= 10) {
            updateHeader(context, jSONObject);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(31:6|7|8|(1:10)(1:11)|12|(1:14)|15|(4:17|18|19|20)|21|22|23|24|25|26|(1:28)|29|30|31|32|33|34|(2:36|(1:38)(1:39))|40|41|(2:43|(1:45)(1:46))|47|48|49|50|51|52) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:17|18|19|20) */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x00c5 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x00cb */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x00d1 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x00db */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0109 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0131 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x0150 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x016f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x0179 */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00f4 A[Catch:{ Exception -> 0x0109 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x013b A[Catch:{ Exception -> 0x0150 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x015a A[Catch:{ Exception -> 0x016f }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m757a(android.content.Context r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.f829a     // Catch:{ all -> 0x019f }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r4)
            return
        L_0x0007:
            java.lang.String r0 = "android.permission.READ_PHONE_STATE"
            com.baidu.mobstat.C0980bn.m1352e(r5, r0)     // Catch:{ all -> 0x019f }
            java.lang.String r0 = "android.permission.INTERNET"
            com.baidu.mobstat.C0980bn.m1352e(r5, r0)     // Catch:{ all -> 0x019f }
            java.lang.String r0 = "android.permission.ACCESS_NETWORK_STATE"
            com.baidu.mobstat.C0980bn.m1352e(r5, r0)     // Catch:{ all -> 0x019f }
            java.lang.String r0 = "phone"
            java.lang.Object r0 = r5.getSystemService(r0)     // Catch:{ all -> 0x019f }
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch:{ all -> 0x019f }
            com.baidu.mobstat.CooperService r1 = com.baidu.mobstat.CooperService.instance()     // Catch:{ all -> 0x019f }
            java.lang.String r1 = r1.getOSVersion()     // Catch:{ all -> 0x019f }
            r4.f830b = r1     // Catch:{ all -> 0x019f }
            com.baidu.mobstat.CooperService r1 = com.baidu.mobstat.CooperService.instance()     // Catch:{ all -> 0x019f }
            java.lang.String r1 = r1.getOSSysVersion()     // Catch:{ all -> 0x019f }
            r4.f831c = r1     // Catch:{ all -> 0x019f }
            com.baidu.mobstat.CooperService r1 = com.baidu.mobstat.CooperService.instance()     // Catch:{ all -> 0x019f }
            java.lang.String r1 = r1.getPhoneModel()     // Catch:{ all -> 0x019f }
            r4.f842n = r1     // Catch:{ all -> 0x019f }
            com.baidu.mobstat.CooperService r1 = com.baidu.mobstat.CooperService.instance()     // Catch:{ all -> 0x019f }
            java.lang.String r1 = r1.getManufacturer()     // Catch:{ all -> 0x019f }
            r4.f843o = r1     // Catch:{ all -> 0x019f }
            com.baidu.mobstat.CooperService r1 = com.baidu.mobstat.CooperService.instance()     // Catch:{ all -> 0x019f }
            java.lang.String r1 = r1.getUUID()     // Catch:{ all -> 0x019f }
            r4.f854z = r1     // Catch:{ all -> 0x019f }
            com.baidu.mobstat.CooperService r1 = com.baidu.mobstat.CooperService.instance()     // Catch:{ all -> 0x019f }
            org.json.JSONObject r1 = r1.getHeaderExt(r5)     // Catch:{ all -> 0x019f }
            r4.f822A = r1     // Catch:{ all -> 0x019f }
            com.baidu.mobstat.CooperService r1 = com.baidu.mobstat.CooperService.instance()     // Catch:{ all -> 0x019f }
            org.json.JSONObject r1 = r1.getPushId(r5)     // Catch:{ all -> 0x019f }
            r4.f823B = r1     // Catch:{ all -> 0x019f }
            com.baidu.mobstat.CooperService r1 = com.baidu.mobstat.CooperService.instance()     // Catch:{ all -> 0x019f }
            r2 = 1
            java.lang.String r1 = r1.getCUID(r5, r2)     // Catch:{ all -> 0x019f }
            r4.f834f = r1     // Catch:{ all -> 0x019f }
            com.baidu.mobstat.CooperService r1 = com.baidu.mobstat.CooperService.instance()     // Catch:{ all -> 0x019f }
            java.lang.String r1 = r1.getDeviceId(r0, r5)     // Catch:{ all -> 0x019f }
            r4.f837i = r1     // Catch:{ all -> 0x019f }
            com.baidu.mobstat.bp r1 = com.baidu.mobstat.C0982bp.m1357a()     // Catch:{ all -> 0x019f }
            boolean r1 = r1.mo11698i(r5)     // Catch:{ all -> 0x019f }
            if (r1 == 0) goto L_0x0086
            java.lang.String r1 = "1"
            goto L_0x0088
        L_0x0086:
            java.lang.String r1 = "0"
        L_0x0088:
            r4.f832d = r1     // Catch:{ all -> 0x019f }
            boolean r1 = com.baidu.mobstat.C0991bw.m1476w(r5)     // Catch:{ all -> 0x019f }
            if (r1 == 0) goto L_0x0094
            java.lang.String r1 = "2"
            r4.f832d = r1     // Catch:{ all -> 0x019f }
        L_0x0094:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x019f }
            r1.<init>()     // Catch:{ all -> 0x019f }
            java.lang.String r3 = r4.f832d     // Catch:{ all -> 0x019f }
            r1.append(r3)     // Catch:{ all -> 0x019f }
            java.lang.String r3 = "-14"
            r1.append(r3)     // Catch:{ all -> 0x019f }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x019f }
            r4.f832d = r1     // Catch:{ all -> 0x019f }
            com.baidu.mobstat.bu r1 = com.baidu.mobstat.C0989bu.m1416a()     // Catch:{ all -> 0x019f }
            boolean r1 = r1.mo11724c()     // Catch:{ all -> 0x019f }
            if (r1 == 0) goto L_0x00cb
            com.baidu.mobstat.CooperService r1 = com.baidu.mobstat.CooperService.instance()     // Catch:{ Exception -> 0x00c5 }
            boolean r1 = r1.isDeviceMacEnabled(r5)     // Catch:{ Exception -> 0x00c5 }
            com.baidu.mobstat.CooperService r3 = com.baidu.mobstat.CooperService.instance()     // Catch:{ Exception -> 0x00c5 }
            java.lang.String r1 = r3.getMacAddress(r5, r1)     // Catch:{ Exception -> 0x00c5 }
            r4.f847s = r1     // Catch:{ Exception -> 0x00c5 }
        L_0x00c5:
            java.lang.String r1 = com.baidu.mobstat.C0991bw.m1462l(r2, r5)     // Catch:{ Exception -> 0x00cb }
            r4.f849u = r1     // Catch:{ Exception -> 0x00cb }
        L_0x00cb:
            java.lang.String r1 = com.baidu.mobstat.C0991bw.m1433a((android.content.Context) r5, (int) r2)     // Catch:{ Exception -> 0x00d1 }
            r4.f850v = r1     // Catch:{ Exception -> 0x00d1 }
        L_0x00d1:
            com.baidu.mobstat.CooperService r1 = com.baidu.mobstat.CooperService.instance()     // Catch:{ Exception -> 0x00db }
            java.lang.String r0 = r1.getOperator(r0)     // Catch:{ Exception -> 0x00db }
            r4.f841m = r0     // Catch:{ Exception -> 0x00db }
        L_0x00db:
            int r0 = com.baidu.mobstat.C0991bw.m1441c(r5)     // Catch:{ Exception -> 0x0109 }
            r4.f838j = r0     // Catch:{ Exception -> 0x0109 }
            int r0 = com.baidu.mobstat.C0991bw.m1445d(r5)     // Catch:{ Exception -> 0x0109 }
            r4.f839k = r0     // Catch:{ Exception -> 0x0109 }
            android.content.res.Resources r0 = r5.getResources()     // Catch:{ Exception -> 0x0109 }
            android.content.res.Configuration r0 = r0.getConfiguration()     // Catch:{ Exception -> 0x0109 }
            int r0 = r0.orientation     // Catch:{ Exception -> 0x0109 }
            r1 = 2
            if (r0 != r1) goto L_0x0109
            int r0 = r4.f838j     // Catch:{ Exception -> 0x0109 }
            int r1 = r4.f839k     // Catch:{ Exception -> 0x0109 }
            r0 = r0 ^ r1
            r4.f838j = r0     // Catch:{ Exception -> 0x0109 }
            int r0 = r4.f838j     // Catch:{ Exception -> 0x0109 }
            int r1 = r4.f839k     // Catch:{ Exception -> 0x0109 }
            r0 = r0 ^ r1
            r4.f839k = r0     // Catch:{ Exception -> 0x0109 }
            int r0 = r4.f838j     // Catch:{ Exception -> 0x0109 }
            int r1 = r4.f839k     // Catch:{ Exception -> 0x0109 }
            r0 = r0 ^ r1
            r4.f838j = r0     // Catch:{ Exception -> 0x0109 }
        L_0x0109:
            com.baidu.mobstat.CooperService r0 = com.baidu.mobstat.CooperService.instance()     // Catch:{ all -> 0x019f }
            java.lang.String r0 = r0.getAppChannel(r5)     // Catch:{ all -> 0x019f }
            r4.f840l = r0     // Catch:{ all -> 0x019f }
            com.baidu.mobstat.CooperService r0 = com.baidu.mobstat.CooperService.instance()     // Catch:{ all -> 0x019f }
            java.lang.String r0 = r0.getAppKey(r5)     // Catch:{ all -> 0x019f }
            r4.f833e = r0     // Catch:{ all -> 0x019f }
            com.baidu.mobstat.CooperService r0 = com.baidu.mobstat.CooperService.instance()     // Catch:{ Exception -> 0x0131 }
            int r0 = r0.getAppVersionCode(r5)     // Catch:{ Exception -> 0x0131 }
            r4.f835g = r0     // Catch:{ Exception -> 0x0131 }
            com.baidu.mobstat.CooperService r0 = com.baidu.mobstat.CooperService.instance()     // Catch:{ Exception -> 0x0131 }
            java.lang.String r0 = r0.getAppVersionName(r5)     // Catch:{ Exception -> 0x0131 }
            r4.f836h = r0     // Catch:{ Exception -> 0x0131 }
        L_0x0131:
            com.baidu.mobstat.bu r0 = com.baidu.mobstat.C0989bu.m1416a()     // Catch:{ Exception -> 0x0150 }
            boolean r0 = r0.mo11724c()     // Catch:{ Exception -> 0x0150 }
            if (r0 == 0) goto L_0x0150
            com.baidu.mobstat.CooperService r0 = com.baidu.mobstat.CooperService.instance()     // Catch:{ Exception -> 0x0150 }
            boolean r0 = r0.checkCellLocationSetting(r5)     // Catch:{ Exception -> 0x0150 }
            if (r0 == 0) goto L_0x014c
            java.lang.String r0 = com.baidu.mobstat.C0991bw.m1457i(r5)     // Catch:{ Exception -> 0x0150 }
            r4.f844p = r0     // Catch:{ Exception -> 0x0150 }
            goto L_0x0150
        L_0x014c:
            java.lang.String r0 = "0_0_0"
            r4.f844p = r0     // Catch:{ Exception -> 0x0150 }
        L_0x0150:
            com.baidu.mobstat.bu r0 = com.baidu.mobstat.C0989bu.m1416a()     // Catch:{ Exception -> 0x016f }
            boolean r0 = r0.mo11724c()     // Catch:{ Exception -> 0x016f }
            if (r0 == 0) goto L_0x016f
            com.baidu.mobstat.CooperService r0 = com.baidu.mobstat.CooperService.instance()     // Catch:{ Exception -> 0x016f }
            boolean r0 = r0.checkGPSLocationSetting(r5)     // Catch:{ Exception -> 0x016f }
            if (r0 == 0) goto L_0x016b
            java.lang.String r0 = com.baidu.mobstat.C0991bw.m1459j(r5)     // Catch:{ Exception -> 0x016f }
            r4.f845q = r0     // Catch:{ Exception -> 0x016f }
            goto L_0x016f
        L_0x016b:
            java.lang.String r0 = ""
            r4.f845q = r0     // Catch:{ Exception -> 0x016f }
        L_0x016f:
            com.baidu.mobstat.CooperService r0 = com.baidu.mobstat.CooperService.instance()     // Catch:{ Exception -> 0x0179 }
            java.lang.String r0 = r0.getLinkedWay(r5)     // Catch:{ Exception -> 0x0179 }
            r4.f846r = r0     // Catch:{ Exception -> 0x0179 }
        L_0x0179:
            java.lang.String r0 = com.baidu.mobstat.C0991bw.m1436b()     // Catch:{ all -> 0x019f }
            r4.f851w = r0     // Catch:{ all -> 0x019f }
            java.lang.String r0 = android.os.Build.BOARD     // Catch:{ all -> 0x019f }
            r4.f852x = r0     // Catch:{ all -> 0x019f }
            java.lang.String r0 = android.os.Build.BRAND     // Catch:{ all -> 0x019f }
            r4.f853y = r0     // Catch:{ all -> 0x019f }
            com.baidu.mobstat.CooperService r0 = com.baidu.mobstat.CooperService.instance()     // Catch:{ all -> 0x019f }
            java.lang.String r0 = r0.getUserId(r5)     // Catch:{ all -> 0x019f }
            r4.f824C = r0     // Catch:{ all -> 0x019f }
            r4.f829a = r2     // Catch:{ all -> 0x019f }
            com.baidu.mobstat.bp r0 = com.baidu.mobstat.C0982bp.m1357a()     // Catch:{ all -> 0x019f }
            java.lang.String r5 = r0.mo11716u(r5)     // Catch:{ all -> 0x019f }
            r4.f826E = r5     // Catch:{ all -> 0x019f }
            monitor-exit(r4)
            return
        L_0x019f:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.HeadObject.m757a(android.content.Context):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void updateHeader(android.content.Context r6, org.json.JSONObject r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.String r0 = "o"
            java.lang.String r1 = "Android"
            r7.put(r0, r1)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "st"
            r1 = 0
            r7.put(r0, r1)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "s"
            java.lang.String r2 = r5.f830b     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r2 != 0) goto L_0x0017
            java.lang.String r2 = ""
            goto L_0x0019
        L_0x0017:
            java.lang.String r2 = r5.f830b     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x0019:
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "sv"
            java.lang.String r2 = r5.f831c     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r2 != 0) goto L_0x0025
            java.lang.String r2 = ""
            goto L_0x0027
        L_0x0025:
            java.lang.String r2 = r5.f831c     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x0027:
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "k"
            java.lang.String r2 = r5.f833e     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r2 != 0) goto L_0x0033
            java.lang.String r2 = ""
            goto L_0x0035
        L_0x0033:
            java.lang.String r2 = r5.f833e     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x0035:
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "pt"
            java.lang.String r2 = r5.f832d     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r2 != 0) goto L_0x0041
            java.lang.String r2 = "0"
            goto L_0x0043
        L_0x0041:
            java.lang.String r2 = r5.f832d     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x0043:
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "i"
            java.lang.String r2 = ""
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "v"
            java.lang.String r2 = "4.0.3.6"
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "sc"
            r2 = 14
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "a"
            int r2 = r5.f835g     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "n"
            java.lang.String r2 = r5.f836h     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r2 != 0) goto L_0x006b
            java.lang.String r2 = ""
            goto L_0x006d
        L_0x006b:
            java.lang.String r2 = r5.f836h     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x006d:
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "d"
            java.lang.String r2 = ""
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "mc"
            java.lang.String r2 = r5.f847s     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r2 != 0) goto L_0x0080
            java.lang.String r2 = ""
            goto L_0x0082
        L_0x0080:
            java.lang.String r2 = r5.f847s     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x0082:
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "bm"
            java.lang.String r2 = r5.f849u     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r2 != 0) goto L_0x008e
            java.lang.String r2 = ""
            goto L_0x0090
        L_0x008e:
            java.lang.String r2 = r5.f849u     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x0090:
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "dd"
            java.lang.String r2 = r5.f837i     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r2 != 0) goto L_0x009c
            java.lang.String r2 = ""
            goto L_0x009e
        L_0x009c:
            java.lang.String r2 = r5.f837i     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x009e:
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "ii"
            java.lang.String r2 = r5.f834f     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r2 != 0) goto L_0x00aa
            java.lang.String r2 = ""
            goto L_0x00ac
        L_0x00aa:
            java.lang.String r2 = r5.f834f     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x00ac:
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "tg"
            r2 = 1
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "w"
            int r3 = r5.f838j     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r0, r3)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "h"
            int r3 = r5.f839k     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r0, r3)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "dn"
            java.lang.String r3 = r5.f850v     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r3 != 0) goto L_0x00cc
            java.lang.String r3 = ""
            goto L_0x00ce
        L_0x00cc:
            java.lang.String r3 = r5.f850v     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x00ce:
            r7.put(r0, r3)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "c"
            java.lang.String r3 = r5.f840l     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r3 != 0) goto L_0x00da
            java.lang.String r3 = ""
            goto L_0x00dc
        L_0x00da:
            java.lang.String r3 = r5.f840l     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x00dc:
            r7.put(r0, r3)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "op"
            java.lang.String r3 = r5.f841m     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r3 != 0) goto L_0x00e8
            java.lang.String r3 = ""
            goto L_0x00ea
        L_0x00e8:
            java.lang.String r3 = r5.f841m     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x00ea:
            r7.put(r0, r3)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "m"
            java.lang.String r3 = r5.f842n     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r3 != 0) goto L_0x00f6
            java.lang.String r3 = ""
            goto L_0x00f8
        L_0x00f6:
            java.lang.String r3 = r5.f842n     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x00f8:
            r7.put(r0, r3)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "ma"
            java.lang.String r3 = r5.f843o     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r3 != 0) goto L_0x0104
            java.lang.String r3 = ""
            goto L_0x0106
        L_0x0104:
            java.lang.String r3 = r5.f843o     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x0106:
            r7.put(r0, r3)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "cl"
            java.lang.String r3 = r5.f844p     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r3 != 0) goto L_0x0112
            java.lang.String r3 = ""
            goto L_0x0114
        L_0x0112:
            java.lang.String r3 = r5.f844p     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x0114:
            r7.put(r0, r3)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "gl"
            java.lang.String r3 = r5.f845q     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r3 != 0) goto L_0x0120
            java.lang.String r3 = ""
            goto L_0x0122
        L_0x0120:
            java.lang.String r3 = r5.f845q     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x0122:
            r7.put(r0, r3)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "l"
            java.lang.String r3 = r5.f846r     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r3 != 0) goto L_0x012e
            java.lang.String r3 = ""
            goto L_0x0130
        L_0x012e:
            java.lang.String r3 = r5.f846r     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x0130:
            r7.put(r0, r3)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "t"
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r0, r3)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "pn"
            java.lang.String r3 = com.baidu.mobstat.C0991bw.m1466n(r2, r6)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r0, r3)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "rom"
            java.lang.String r3 = r5.f851w     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r3 != 0) goto L_0x014e
            java.lang.String r3 = ""
            goto L_0x0150
        L_0x014e:
            java.lang.String r3 = r5.f851w     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x0150:
            r7.put(r0, r3)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "bo"
            java.lang.String r3 = r5.f852x     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r3 != 0) goto L_0x015c
            java.lang.String r3 = ""
            goto L_0x015e
        L_0x015c:
            java.lang.String r3 = r5.f852x     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x015e:
            r7.put(r0, r3)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "bd"
            java.lang.String r3 = r5.f853y     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r3 != 0) goto L_0x016a
            java.lang.String r3 = ""
            goto L_0x016c
        L_0x016a:
            java.lang.String r3 = r5.f853y     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x016c:
            r7.put(r0, r3)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "td"
            java.lang.String r3 = com.baidu.mobstat.C0991bw.m1438b((android.content.Context) r6)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r0, r3)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "tv"
            if (r6 == 0) goto L_0x0189
            android.content.pm.ApplicationInfo r3 = r6.getApplicationInfo()     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r3 != 0) goto L_0x0183
            goto L_0x0189
        L_0x0183:
            android.content.pm.ApplicationInfo r1 = r6.getApplicationInfo()     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            int r1 = r1.targetSdkVersion     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x0189:
            r7.put(r0, r1)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "user_property"
            java.lang.String r1 = r5.f826E     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r0, r1)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "od"
            java.lang.String r1 = com.baidu.mobstat.C0991bw.m1437b((int) r2, (android.content.Context) r6)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r0, r1)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "out_od"
            java.lang.String r1 = com.baidu.mobstat.C0991bw.m1443c((int) r2, (android.content.Context) r6)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r0, r1)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "from"
            java.lang.String r1 = "0"
            r7.put(r0, r1)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "gaid"
            java.lang.String r1 = com.baidu.mobstat.C0991bw.m1449e(r2, r6)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r0, r1)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "iid"
            java.lang.String r1 = com.baidu.mobstat.C0991bw.m1447d(r2, r6)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r0, r1)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "ii3"
            java.lang.String r1 = com.baidu.mobstat.C0991bw.m1451f(r2, r6)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r0, r1)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "ssaid"
            java.lang.String r1 = com.baidu.mobstat.C0991bw.m1452g(r2, r6)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r0, r1)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "py"
            java.lang.String r1 = r5.f828G     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r0, r1)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "im"
            java.lang.String r1 = com.baidu.mobstat.C0991bw.m1478y(r6)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r0, r1)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = r5.f824C     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r0 != 0) goto L_0x021a
            java.lang.String r0 = r5.f826E     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r0 != 0) goto L_0x01f8
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r1 = r5.f826E     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            goto L_0x01fd
        L_0x01f8:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r0.<init>()     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x01fd:
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r1.<init>()     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r2 = r5.f824C     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r1.put(r2)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r2 = "1"
            r1.put(r2)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r2 = "uid_"
            r0.put(r2, r1)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r1 = "user_property"
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r1, r0)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x021a:
            java.lang.String r0 = "uid_change"
            java.lang.String r1 = ""
            r7.put(r0, r1)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = "at"
            java.lang.String r1 = "0"
            r7.put(r0, r1)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = com.baidu.mobstat.C0991bw.m1474u(r6)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r1 = "pl"
            r7.put(r1, r0)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r1 = 0
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r0 != 0) goto L_0x023c
            java.lang.String r1 = com.baidu.mobstat.C0991bw.m1475v(r6)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x023c:
            java.lang.String r6 = "scl"
            if (r1 != 0) goto L_0x0242
            java.lang.String r1 = ""
        L_0x0242:
            r7.put(r6, r1)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r6 = "sign"
            java.lang.String r0 = r5.f854z     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r0 != 0) goto L_0x024e
            java.lang.String r0 = ""
            goto L_0x0250
        L_0x024e:
            java.lang.String r0 = r5.f854z     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x0250:
            r7.put(r6, r0)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            org.json.JSONObject r6 = r5.f822A     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r6 == 0) goto L_0x0267
            org.json.JSONObject r6 = r5.f822A     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            int r6 = r6.length()     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r6 == 0) goto L_0x0267
            java.lang.String r6 = "ext"
            org.json.JSONObject r0 = r5.f822A     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r6, r0)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            goto L_0x026c
        L_0x0267:
            java.lang.String r6 = "ext"
            r7.remove(r6)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x026c:
            org.json.JSONObject r6 = r5.f823B     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            if (r6 != 0) goto L_0x0277
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r6.<init>()     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r5.f823B = r6     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
        L_0x0277:
            java.lang.String r6 = "push"
            org.json.JSONObject r0 = r5.f823B     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r6, r0)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r6 = "uid"
            java.lang.String r0 = r5.f824C     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r6, r0)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r6 = "startType"
            int r0 = r5.f825D     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            r7.put(r6, r0)     // Catch:{ Exception -> 0x0294, all -> 0x0291 }
            goto L_0x0294
        L_0x0291:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        L_0x0294:
            monitor-exit(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.HeadObject.updateHeader(android.content.Context, org.json.JSONObject):void");
    }

    public void setHeaderExt(JSONObject jSONObject) {
        this.f822A = jSONObject;
    }

    public void setPushInfo(JSONObject jSONObject) {
        this.f823B = jSONObject;
    }

    public void setUserId(String str) {
        this.f824C = str;
    }

    public void setUserProperty(String str) {
        this.f826E = str;
    }

    public void setZid(String str) {
        this.f827F = str;
    }

    public void setStartType(boolean z) {
        if (z) {
            this.f825D = 1;
        } else {
            this.f825D = 0;
        }
    }

    public void setHeaderPy(String str) {
        this.f828G = str;
    }
}
