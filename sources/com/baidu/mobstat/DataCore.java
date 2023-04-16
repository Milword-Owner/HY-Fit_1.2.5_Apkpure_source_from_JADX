package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobstat.StatService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataCore {

    /* renamed from: a */
    private static JSONObject f785a = new JSONObject();

    /* renamed from: b */
    private static String f786b = "";

    /* renamed from: c */
    private static DataCore f787c = new DataCore();

    /* renamed from: d */
    private JSONArray f788d = new JSONArray();

    /* renamed from: e */
    private JSONArray f789e = new JSONArray();

    /* renamed from: f */
    private JSONArray f790f = new JSONArray();

    /* renamed from: g */
    private boolean f791g = false;

    /* renamed from: h */
    private volatile int f792h = 0;

    /* renamed from: i */
    private StatService.WearListener f793i;

    /* renamed from: j */
    private JSONObject f794j;

    /* renamed from: k */
    private Object f795k = new Object();

    /* renamed from: l */
    private boolean f796l = false;

    /* renamed from: m */
    private HashMap<String, String> f797m = new HashMap<>();

    /* renamed from: n */
    private List<String> f798n = Collections.synchronizedList(new ArrayList());

    /* renamed from: o */
    private JSONObject f799o = new JSONObject();

    /* renamed from: a */
    private void m735a(Context context, JSONObject jSONObject) {
    }

    /* renamed from: b */
    private void m744b(JSONObject jSONObject) {
    }

    public void sendDataForDueros(Context context) {
    }

    public static DataCore instance() {
        return f787c;
    }

    private DataCore() {
    }

    public void init(Context context) {
        instance().loadStatData(context);
        instance().loadLastSession(context);
        instance().installHeader(context);
    }

    public void updatePyd(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            f786b = str;
            C0939av.m1093a().mo11595a(str);
        } catch (Exception unused) {
        }
    }

    public void loadProperty(Context context) {
        String u = C0982bp.m1357a().mo11716u(context);
        if (!TextUtils.isEmpty(u)) {
            HashMap hashMap = new HashMap();
            try {
                JSONObject jSONObject = new JSONObject(u);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    JSONArray jSONArray = (JSONArray) jSONObject.get(next);
                    if (jSONArray != null && jSONArray.length() > 0) {
                        hashMap.put(next, jSONArray.optString(0));
                    }
                }
            } catch (Exception unused) {
            }
            if (hashMap.size() > 0) {
                setPydProperty(context, C0994bx.m1485a((Map<String, String>) hashMap), "1", "1");
            }
        }
        String q = C0982bp.m1357a().mo11712q(context);
        if (!TextUtils.isEmpty(q)) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("uid_", q);
            setPydProperty(context, C0994bx.m1485a((Map<String, String>) hashMap2), "1", "0");
        }
    }

    public int getCacheFileSzie() {
        return this.f792h;
    }

    public JSONObject getLogData() {
        return this.f794j;
    }

    public void putSession(Session session) {
        putSession(session.constructJSONObject());
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:8|9|10|11|12|13) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0025 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void putSession(org.json.JSONObject r4) {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.String r0 = r4.toString()
            boolean r0 = r3.m742a((java.lang.String) r0)
            if (r0 == 0) goto L_0x0017
            com.baidu.mobstat.bb r4 = com.baidu.mobstat.C0955bb.m1194c()
            java.lang.String r0 = "[WARNING] data to put exceed limit, ignored"
            r4.mo11627b((java.lang.String) r0)
            return
        L_0x0017:
            org.json.JSONArray r0 = r3.f788d
            monitor-enter(r0)
            org.json.JSONArray r1 = r3.f788d     // Catch:{ all -> 0x0027 }
            int r1 = r1.length()     // Catch:{ all -> 0x0027 }
            org.json.JSONArray r2 = r3.f788d     // Catch:{ JSONException -> 0x0025 }
            r2.put(r1, r4)     // Catch:{ JSONException -> 0x0025 }
        L_0x0025:
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            return
        L_0x0027:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.DataCore.putSession(org.json.JSONObject):void");
    }

    public void putSession(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(new JSONObject().toString())) {
            try {
                putSession(new JSONObject(str));
            } catch (JSONException unused) {
            }
        }
    }

    /* renamed from: a */
    private boolean m742a(String str) {
        return (str.getBytes().length + BDStatCore.instance().getSessionSize()) + this.f792h > 184320;
    }

    public void putEvent(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            if (m742a(jSONObject.toString())) {
                C0955bb.m1194c().mo11627b("[WARNING] data to put exceed limit, ignored");
                return;
            }
            synchronized (this.f789e) {
                EventAnalysis.doEventMerge(this.f789e, jSONObject);
            }
        }
    }

    public void installHeader(Context context) {
        synchronized (f785a) {
            CooperService.instance().getHeadObject().installHeader(context, f785a);
        }
    }

    public void flush(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            synchronized (this.f788d) {
                jSONObject.put(Config.PRINCIPAL_PART, new JSONArray(this.f788d.toString()));
            }
            synchronized (this.f789e) {
                jSONObject.put(Config.EVENT_PART, new JSONArray(this.f789e.toString()));
            }
            synchronized (f785a) {
                jSONObject.put(Config.HEADER_PART, new JSONObject(f785a.toString()));
            }
            jSONObject.put(Config.PYD, f786b);
        } catch (Exception unused) {
        }
        String jSONObject2 = jSONObject.toString();
        if (m741a()) {
            C0955bb.m1194c().mo11624a("[WARNING] stat cache exceed 184320 Bytes, ignored");
            return;
        }
        int length = jSONObject2.getBytes().length;
        if (length >= 184320) {
            m740a(true);
            return;
        }
        this.f792h = length;
        String u = C0991bw.m1474u(context);
        C0980bn.m1347a(context, u + Config.STAT_CACHE_FILE_NAME, jSONObject2, false);
        synchronized (this.f790f) {
            C0980bn.m1347a(context, Config.LAST_AP_INFO_FILE_NAME, this.f790f.toString(), false);
        }
    }

    /* renamed from: a */
    private void m740a(boolean z) {
        this.f791g = z;
    }

    /* renamed from: a */
    private boolean m741a() {
        return this.f791g;
    }

    public void loadLastSession(Context context) {
        if (context != null) {
            String str = C0991bw.m1474u(context) + Config.LAST_SESSION_FILE_NAME;
            if (C0980bn.m1350c(context, str)) {
                String a = C0980bn.m1345a(context, str);
                if (!TextUtils.isEmpty(a)) {
                    C0980bn.m1347a(context, str, new JSONObject().toString(), false);
                    putSession(a);
                    flush(context);
                }
            }
        }
    }

    public void loadWifiData(Context context) {
        if (context != null && C0980bn.m1350c(context, Config.LAST_AP_INFO_FILE_NAME)) {
            try {
                JSONArray jSONArray = new JSONArray(C0980bn.m1345a(context, Config.LAST_AP_INFO_FILE_NAME));
                int length = jSONArray.length();
                if (length >= 10) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (int i = length - 10; i < length; i++) {
                        jSONArray2.put(jSONArray.get(i));
                    }
                    jSONArray = jSONArray2;
                }
                String m = C0991bw.m1464m(1, context);
                if (!TextUtils.isEmpty(m)) {
                    jSONArray.put(m);
                }
                synchronized (this.f790f) {
                    this.f790f = jSONArray;
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void loadStatData(Context context) {
        if (context != null) {
            String str = C0991bw.m1474u(context) + Config.STAT_CACHE_FILE_NAME;
            if (C0980bn.m1350c(context, str)) {
                String a = C0980bn.m1345a(context, str);
                if (!TextUtils.isEmpty(a)) {
                    JSONObject jSONObject = null;
                    try {
                        jSONObject = new JSONObject(a);
                    } catch (Exception unused) {
                    }
                    if (jSONObject != null) {
                        long currentTimeMillis = System.currentTimeMillis();
                        try {
                            JSONArray jSONArray = jSONObject.getJSONArray(Config.PRINCIPAL_PART);
                            if (jSONArray != null) {
                                for (int i = 0; i < jSONArray.length(); i++) {
                                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                                    if (currentTimeMillis - jSONObject2.getLong("s") <= Config.MAX_LOG_DATA_EXSIT_TIME) {
                                        putSession(jSONObject2);
                                    }
                                }
                            }
                        } catch (Exception unused2) {
                        }
                        try {
                            JSONArray jSONArray2 = jSONObject.getJSONArray(Config.EVENT_PART);
                            if (jSONArray2 != null) {
                                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                                    JSONObject jSONObject3 = jSONArray2.getJSONObject(i2);
                                    if (currentTimeMillis - jSONObject3.getLong("t") <= Config.MAX_LOG_DATA_EXSIT_TIME) {
                                        putEvent(context, jSONObject3);
                                    }
                                }
                            }
                        } catch (Exception unused3) {
                        }
                        try {
                            JSONObject jSONObject4 = jSONObject.getJSONObject(Config.HEADER_PART);
                            if (jSONObject4 != null) {
                                synchronized (f785a) {
                                    f785a = jSONObject4;
                                }
                            }
                        } catch (Exception unused4) {
                        }
                        try {
                            String optString = jSONObject.optString(Config.PYD);
                            if (TextUtils.isEmpty(optString)) {
                                optString = "";
                            }
                            f786b = optString;
                        } catch (Exception unused5) {
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String constructLogWithEmptyBody(android.content.Context r7, java.lang.String r8) {
        /*
            r6 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            com.baidu.mobstat.CooperService r2 = com.baidu.mobstat.CooperService.instance()
            com.baidu.mobstat.HeadObject r2 = r2.getHeadObject()
            java.lang.String r3 = r2.f833e
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x001e
            r2.installHeader(r7, r1)
            goto L_0x0021
        L_0x001e:
            r2.updateHeader(r7, r1)
        L_0x0021:
            org.json.JSONArray r7 = new org.json.JSONArray
            r7.<init>()
            long r2 = java.lang.System.currentTimeMillis()
            r4 = 0
            java.lang.String r5 = "t"
            r1.put(r5, r2)     // Catch:{ Exception -> 0x006b }
            java.lang.String r5 = "ss"
            r1.put(r5, r2)     // Catch:{ Exception -> 0x006b }
            java.lang.String r2 = "wl2"
            r1.put(r2, r7)     // Catch:{ Exception -> 0x006b }
            java.lang.String r2 = "sq"
            r3 = 0
            r1.put(r2, r3)     // Catch:{ Exception -> 0x006b }
            java.lang.String r2 = "sign"
            com.baidu.mobstat.CooperService r3 = com.baidu.mobstat.CooperService.instance()     // Catch:{ Exception -> 0x006b }
            java.lang.String r3 = r3.getUUID()     // Catch:{ Exception -> 0x006b }
            r1.put(r2, r3)     // Catch:{ Exception -> 0x006b }
            java.lang.String r2 = "k"
            r1.put(r2, r8)     // Catch:{ Exception -> 0x006b }
            java.lang.String r8 = "he"
            r0.put(r8, r1)     // Catch:{ Exception -> 0x006b }
            java.lang.String r8 = "pr"
            r0.put(r8, r7)     // Catch:{  }
            java.lang.String r8 = "ev"
            r0.put(r8, r7)     // Catch:{  }
            java.lang.String r8 = "ex"
            r0.put(r8, r7)     // Catch:{  }
            java.lang.String r7 = r0.toString()
            return r7
        L_0x006b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.DataCore.constructLogWithEmptyBody(android.content.Context, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    private void m738a(Context context, JSONObject jSONObject, boolean z) {
        if (jSONObject != null) {
            JSONObject jSONObject2 = new JSONObject();
            boolean z2 = true;
            try {
                jSONObject2.put(Config.TRACE_APPLICATION_SESSION, z ? 1 : 0);
            } catch (Exception unused) {
            }
            try {
                jSONObject2.put(Config.TRACE_FAILED_CNT, 0);
            } catch (Exception unused2) {
            }
            try {
                jSONObject2.put(Config.TRACE_CIRCLE, C0879ad.m837c());
            } catch (Exception unused3) {
            }
            try {
                jSONObject.put(Config.TRACE_PART, jSONObject2);
            } catch (Exception unused4) {
                z2 = false;
            }
            if (z2) {
                m737a(context, jSONObject, jSONObject2);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0020  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001a  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m737a(android.content.Context r7, org.json.JSONObject r8, org.json.JSONObject r9) {
        /*
            r6 = this;
            int r5 = r6.m731a((org.json.JSONObject) r8)
            r0 = 0
            java.lang.String r2 = "he"
            org.json.JSONObject r8 = r8.getJSONObject(r2)     // Catch:{ Exception -> 0x0015 }
            if (r8 == 0) goto L_0x0015
            java.lang.String r2 = "ss"
            long r2 = r8.getLong(r2)     // Catch:{ Exception -> 0x0015 }
            goto L_0x0016
        L_0x0015:
            r2 = r0
        L_0x0016:
            int r8 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r8 != 0) goto L_0x0020
            long r0 = java.lang.System.currentTimeMillis()
            r3 = r0
            goto L_0x0021
        L_0x0020:
            r3 = r2
        L_0x0021:
            r0 = r6
            r1 = r7
            r2 = r9
            r0.m736a((android.content.Context) r1, (org.json.JSONObject) r2, (long) r3, (int) r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.DataCore.m737a(android.content.Context, org.json.JSONObject, org.json.JSONObject):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0037 A[Catch:{ Exception -> 0x0056 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int m731a(org.json.JSONObject r10) {
        /*
            r9 = this;
            r0 = 0
            if (r10 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            java.lang.String r3 = "he"
            org.json.JSONObject r3 = r10.getJSONObject(r3)     // Catch:{ Exception -> 0x0022 }
            java.lang.String r4 = "sq"
            long r4 = r3.getLong(r4)     // Catch:{ Exception -> 0x0022 }
            java.lang.String r6 = "ss"
            long r6 = r3.getLong(r6)     // Catch:{ Exception -> 0x0022 }
            int r3 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r3 <= 0) goto L_0x0022
            int r3 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r3 != 0) goto L_0x0022
            r3 = 1
            goto L_0x0023
        L_0x0022:
            r3 = 0
        L_0x0023:
            java.lang.String r4 = "pr"
            org.json.JSONArray r10 = r10.getJSONArray(r4)     // Catch:{ Exception -> 0x0056 }
            if (r10 == 0) goto L_0x0056
            int r4 = r10.length()     // Catch:{ Exception -> 0x0056 }
            if (r4 == 0) goto L_0x0056
        L_0x0031:
            int r4 = r10.length()     // Catch:{ Exception -> 0x0056 }
            if (r0 >= r4) goto L_0x0056
            java.lang.Object r4 = r10.get(r0)     // Catch:{ Exception -> 0x0056 }
            org.json.JSONObject r4 = (org.json.JSONObject) r4     // Catch:{ Exception -> 0x0056 }
            java.lang.String r5 = "c"
            long r5 = r4.getLong(r5)     // Catch:{ Exception -> 0x0056 }
            java.lang.String r7 = "e"
            long r7 = r4.getLong(r7)     // Catch:{ Exception -> 0x0056 }
            int r4 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r4 == 0) goto L_0x0053
            int r4 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r4 != 0) goto L_0x0053
            int r3 = r3 + 1
        L_0x0053:
            int r0 = r0 + 1
            goto L_0x0031
        L_0x0056:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.DataCore.m731a(org.json.JSONObject):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x0131 A[SYNTHETIC, Splitter:B:57:0x0131] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0139  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m736a(android.content.Context r15, org.json.JSONObject r16, long r17, int r19) {
        /*
            r14 = this;
            r6 = r14
            r7 = r15
            r8 = r16
            r0 = r17
            com.baidu.mobstat.ac r2 = com.baidu.mobstat.C0878ac.m821a()
            java.lang.Long r2 = r2.mo11434b(r15)
            long r2 = r2.longValue()
            r4 = 0
            int r9 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r9 > 0) goto L_0x0022
            if (r19 == 0) goto L_0x0022
            com.baidu.mobstat.ac r2 = com.baidu.mobstat.C0878ac.m821a()
            r2.mo11432a((android.content.Context) r15, (long) r0)
            r2 = r0
        L_0x0022:
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            java.lang.String r3 = "first"
            r14.m739a((org.json.JSONObject) r8, (java.lang.String) r3, (java.lang.Object) r2)
            if (r19 == 0) goto L_0x005c
            com.baidu.mobstat.ac r2 = com.baidu.mobstat.C0878ac.m821a()
            java.lang.Long r2 = r2.mo11437c(r15)
            long r2 = r2.longValue()
            long r9 = r0 - r2
            int r11 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x0046
            int r11 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r11 > 0) goto L_0x0046
            r2 = -1
            goto L_0x004d
        L_0x0046:
            int r11 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r11 != 0) goto L_0x004c
            r2 = r4
            goto L_0x004d
        L_0x004c:
            r2 = r9
        L_0x004d:
            com.baidu.mobstat.ac r9 = com.baidu.mobstat.C0878ac.m821a()
            r9.mo11435b((android.content.Context) r15, (long) r0)
            com.baidu.mobstat.ac r9 = com.baidu.mobstat.C0878ac.m821a()
            r9.mo11438c(r15, r2)
            goto L_0x0068
        L_0x005c:
            com.baidu.mobstat.ac r2 = com.baidu.mobstat.C0878ac.m821a()
            java.lang.Long r2 = r2.mo11439d(r15)
            long r2 = r2.longValue()
        L_0x0068:
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            java.lang.String r3 = "session_last_interval"
            r14.m739a((org.json.JSONObject) r8, (java.lang.String) r3, (java.lang.Object) r2)
            com.baidu.mobstat.ac r2 = com.baidu.mobstat.C0878ac.m821a()
            java.lang.String r2 = r2.mo11440e(r15)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            r9 = 0
            java.lang.String r10 = ""
            java.lang.String r11 = ":"
            if (r3 != 0) goto L_0x009d
            boolean r3 = r2.contains(r11)
            if (r3 == 0) goto L_0x009d
            java.lang.String[] r2 = r2.split(r11)
            if (r2 == 0) goto L_0x009d
            int r3 = r2.length
            r12 = 2
            if (r3 != r12) goto L_0x009d
            r10 = r2[r9]
            r3 = 1
            r2 = r2[r3]
            r13 = r10
            r10 = r2
            r2 = r13
            goto L_0x009e
        L_0x009d:
            r2 = r10
        L_0x009e:
            boolean r3 = android.text.TextUtils.isEmpty(r10)
            if (r3 != 0) goto L_0x00ac
            java.lang.Integer r3 = java.lang.Integer.valueOf(r10)     // Catch:{ Exception -> 0x00ac }
            int r9 = r3.intValue()     // Catch:{ Exception -> 0x00ac }
        L_0x00ac:
            java.lang.String r0 = com.baidu.mobstat.C0994bx.m1482a((long) r17)
            boolean r1 = android.text.TextUtils.isEmpty(r2)
            if (r1 != 0) goto L_0x00c0
            boolean r1 = r0.equals(r2)
            if (r1 == 0) goto L_0x00bd
            goto L_0x00c0
        L_0x00bd:
            r1 = r19
            goto L_0x00c2
        L_0x00c0:
            int r1 = r19 + r9
        L_0x00c2:
            if (r19 == 0) goto L_0x00dd
            com.baidu.mobstat.ac r3 = com.baidu.mobstat.C0878ac.m821a()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r0)
            r10.append(r11)
            r10.append(r1)
            java.lang.String r10 = r10.toString()
            r3.mo11433a((android.content.Context) r15, (java.lang.String) r10)
        L_0x00dd:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r3 = "session_today_cnt"
            r14.m739a((org.json.JSONObject) r8, (java.lang.String) r3, (java.lang.Object) r1)
            boolean r1 = android.text.TextUtils.isEmpty(r2)
            if (r1 != 0) goto L_0x00f6
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x00f6 }
            int r1 = r1.intValue()     // Catch:{ Exception -> 0x00f6 }
            long r10 = (long) r1
            goto L_0x00f7
        L_0x00f6:
            r10 = r4
        L_0x00f7:
            java.lang.String r12 = "recent"
            int r1 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x0122
            boolean r1 = android.text.TextUtils.isEmpty(r2)
            if (r1 != 0) goto L_0x0122
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0122
            if (r19 == 0) goto L_0x0122
            long r4 = (long) r9
            r0 = r14
            r1 = r15
            r2 = r10
            org.json.JSONArray r0 = r0.m732a((android.content.Context) r1, (long) r2, (long) r4)
            com.baidu.mobstat.ac r1 = com.baidu.mobstat.C0878ac.m821a()
            java.lang.String r2 = r0.toString()
            r1.mo11436b((android.content.Context) r15, (java.lang.String) r2)
            r14.m739a((org.json.JSONObject) r8, (java.lang.String) r12, (java.lang.Object) r0)
            goto L_0x0141
        L_0x0122:
            com.baidu.mobstat.ac r0 = com.baidu.mobstat.C0878ac.m821a()
            java.lang.String r0 = r0.mo11441f(r15)
            r1 = 0
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x0137
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ Exception -> 0x0137 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0137 }
            r1 = r2
        L_0x0137:
            if (r1 != 0) goto L_0x013e
            org.json.JSONArray r1 = new org.json.JSONArray
            r1.<init>()
        L_0x013e:
            r14.m739a((org.json.JSONObject) r8, (java.lang.String) r12, (java.lang.Object) r1)
        L_0x0141:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.DataCore.m736a(android.content.Context, org.json.JSONObject, long, int):void");
    }

    /* renamed from: a */
    private JSONArray m732a(Context context, long j, long j2) {
        List arrayList = new ArrayList();
        String f = C0878ac.m821a().mo11441f(context);
        boolean z = false;
        if (!TextUtils.isEmpty(f)) {
            try {
                JSONArray jSONArray = new JSONArray(f);
                if (jSONArray.length() != 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add((JSONObject) jSONArray.get(i));
                    }
                }
            } catch (Exception unused) {
            }
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            try {
                if (((JSONObject) it.next()).getLong(Config.TRACE_VISIT_RECENT_DAY) == j) {
                    break;
                }
            } catch (Exception unused2) {
            }
        }
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(Config.TRACE_VISIT_RECENT_DAY, j);
                jSONObject.put("count", j2);
                arrayList.add(jSONObject);
            } catch (Exception unused3) {
            }
        }
        int size = arrayList.size();
        if (size > 5) {
            arrayList = arrayList.subList(size - 5, size);
        }
        return new JSONArray(arrayList);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:2|(2:4|5)|6|7|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0013 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m739a(org.json.JSONObject r3, java.lang.String r4, java.lang.Object r5) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.String r0 = "visit"
            boolean r1 = r3.has(r0)
            if (r1 != 0) goto L_0x0013
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x0013 }
            r1.<init>()     // Catch:{ Exception -> 0x0013 }
            r3.put(r0, r1)     // Catch:{ Exception -> 0x0013 }
        L_0x0013:
            java.lang.Object r3 = r3.get(r0)     // Catch:{ Exception -> 0x001c }
            org.json.JSONObject r3 = (org.json.JSONObject) r3     // Catch:{ Exception -> 0x001c }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x001c }
        L_0x001c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.DataCore.m739a(org.json.JSONObject, java.lang.String, java.lang.Object):void");
    }

    public void saveLogDataAndSendForRaven(Context context) {
        synchronized (this.f795k) {
        }
    }

    public void saveLogData(Context context, boolean z, boolean z2, long j, boolean z3) {
        saveLogData(context, z, z2, j, z3, (JSONObject) null);
    }

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
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public void saveLogData(android.content.Context r9, boolean r10, boolean r11, long r12, boolean r14, org.json.JSONObject r15) {
        /*
            r8 = this;
            com.baidu.mobstat.CooperService r0 = com.baidu.mobstat.CooperService.instance()
            com.baidu.mobstat.HeadObject r0 = r0.getHeadObject()
            if (r0 == 0) goto L_0x0036
            org.json.JSONObject r1 = f785a
            monitor-enter(r1)
            java.lang.String r2 = r0.f833e     // Catch:{ all -> 0x0033 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0033 }
            if (r2 == 0) goto L_0x001b
            org.json.JSONObject r2 = f785a     // Catch:{ all -> 0x0033 }
            r0.installHeader(r9, r2)     // Catch:{ all -> 0x0033 }
            goto L_0x0020
        L_0x001b:
            org.json.JSONObject r2 = f785a     // Catch:{ all -> 0x0033 }
            r0.updateHeader(r9, r2)     // Catch:{ all -> 0x0033 }
        L_0x0020:
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
            java.lang.String r0 = r0.f833e
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0036
            com.baidu.mobstat.bb r9 = com.baidu.mobstat.C0955bb.m1194c()
            java.lang.String r10 = "[WARNING] 无法找到有效APP Key, 请参考文档配置"
            r9.mo11631c((java.lang.String) r10)
            return
        L_0x0033:
            r9 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
            throw r9
        L_0x0036:
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            org.json.JSONObject r1 = f785a
            monitor-enter(r1)
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0138 }
            org.json.JSONObject r4 = f785a     // Catch:{ Exception -> 0x0136 }
            java.lang.String r5 = "at"
            java.lang.String r4 = r4.optString(r5)     // Catch:{ Exception -> 0x0136 }
            org.json.JSONObject r5 = f785a     // Catch:{ Exception -> 0x0136 }
            java.lang.String r6 = "uid"
            java.lang.String r5 = r5.optString(r6)     // Catch:{ Exception -> 0x0136 }
            boolean r6 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Exception -> 0x0136 }
            if (r6 != 0) goto L_0x0086
            java.lang.String r6 = "0"
            boolean r4 = r4.equals(r6)     // Catch:{ Exception -> 0x0136 }
            if (r4 == 0) goto L_0x0086
            com.baidu.mobstat.CooperService r4 = com.baidu.mobstat.CooperService.instance()     // Catch:{ Exception -> 0x0136 }
            java.lang.String r4 = r4.getLastUserId(r9)     // Catch:{ Exception -> 0x0136 }
            boolean r4 = r5.equals(r4)     // Catch:{ Exception -> 0x0136 }
            if (r4 != 0) goto L_0x0076
            org.json.JSONObject r4 = f785a     // Catch:{ Exception -> 0x0136 }
            java.lang.String r6 = "uid_change"
            r4.put(r6, r5)     // Catch:{ Exception -> 0x0136 }
            goto L_0x007f
        L_0x0076:
            org.json.JSONObject r4 = f785a     // Catch:{ Exception -> 0x0136 }
            java.lang.String r6 = "uid_change"
            java.lang.String r7 = ""
            r4.put(r6, r7)     // Catch:{ Exception -> 0x0136 }
        L_0x007f:
            com.baidu.mobstat.CooperService r4 = com.baidu.mobstat.CooperService.instance()     // Catch:{ Exception -> 0x0136 }
            r4.setLastUserId(r9, r5)     // Catch:{ Exception -> 0x0136 }
        L_0x0086:
            org.json.JSONObject r4 = f785a     // Catch:{ Exception -> 0x0136 }
            java.lang.String r5 = "t"
            r4.put(r5, r2)     // Catch:{ Exception -> 0x0136 }
            org.json.JSONObject r2 = f785a     // Catch:{ Exception -> 0x0136 }
            java.lang.String r3 = "sq"
            r4 = 1
            if (r10 == 0) goto L_0x0096
            r5 = 0
            goto L_0x0097
        L_0x0096:
            r5 = 1
        L_0x0097:
            r2.put(r3, r5)     // Catch:{ Exception -> 0x0136 }
            org.json.JSONObject r2 = f785a     // Catch:{ Exception -> 0x0136 }
            java.lang.String r3 = "ss"
            r2.put(r3, r12)     // Catch:{ Exception -> 0x0136 }
            org.json.JSONArray r12 = r8.f790f     // Catch:{ Exception -> 0x0136 }
            monitor-enter(r12)     // Catch:{ Exception -> 0x0136 }
            org.json.JSONObject r13 = f785a     // Catch:{ all -> 0x0133 }
            java.lang.String r2 = "wl2"
            org.json.JSONArray r3 = r8.f790f     // Catch:{ all -> 0x0133 }
            r13.put(r2, r3)     // Catch:{ all -> 0x0133 }
            monitor-exit(r12)     // Catch:{ all -> 0x0133 }
            org.json.JSONObject r12 = f785a     // Catch:{ Exception -> 0x0136 }
            java.lang.String r13 = "sign"
            com.baidu.mobstat.CooperService r2 = com.baidu.mobstat.CooperService.instance()     // Catch:{ Exception -> 0x0136 }
            java.lang.String r2 = r2.getUUID()     // Catch:{ Exception -> 0x0136 }
            r12.put(r13, r2)     // Catch:{ Exception -> 0x0136 }
            org.json.JSONObject r12 = f785a     // Catch:{ Exception -> 0x0136 }
            r8.m743b(r9, r12, r15)     // Catch:{ Exception -> 0x0136 }
            java.lang.String r12 = "he"
            org.json.JSONObject r13 = f785a     // Catch:{ Exception -> 0x0136 }
            r0.put(r12, r13)     // Catch:{ Exception -> 0x0136 }
            org.json.JSONArray r12 = r8.f788d     // Catch:{ all -> 0x0138 }
            monitor-enter(r12)     // Catch:{ all -> 0x0138 }
            java.lang.String r13 = "pr"
            org.json.JSONArray r15 = r8.f788d     // Catch:{ JSONException -> 0x012e }
            r0.put(r13, r15)     // Catch:{ JSONException -> 0x012e }
            org.json.JSONArray r13 = r8.f789e     // Catch:{ all -> 0x012c }
            monitor-enter(r13)     // Catch:{ all -> 0x012c }
            java.lang.String r15 = "ev"
            org.json.JSONArray r2 = r8.f789e     // Catch:{ JSONException -> 0x0126 }
            r0.put(r15, r2)     // Catch:{ JSONException -> 0x0126 }
            java.lang.String r15 = "ex"
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0120 }
            r2.<init>()     // Catch:{ JSONException -> 0x0120 }
            r0.put(r15, r2)     // Catch:{ JSONException -> 0x0120 }
            java.lang.String r15 = "pyd"
            java.lang.String r2 = f786b     // Catch:{ JSONException -> 0x011c }
            r0.put(r15, r2)     // Catch:{ JSONException -> 0x011c }
            r8.m738a((android.content.Context) r9, (org.json.JSONObject) r0, (boolean) r11)     // Catch:{ all -> 0x0124 }
            r8.m744b(r0)     // Catch:{ all -> 0x0124 }
            r8.m735a(r9, r0)     // Catch:{ all -> 0x0124 }
            java.lang.String r11 = r0.toString()     // Catch:{ all -> 0x0124 }
            r8.m734a((android.content.Context) r9, (java.lang.String) r11, (boolean) r10, (boolean) r14)     // Catch:{ all -> 0x0124 }
            r8.f794j = r0     // Catch:{ all -> 0x0124 }
            r8.clearCache(r9)     // Catch:{ all -> 0x0124 }
            boolean r10 = r8.f796l     // Catch:{ all -> 0x0124 }
            if (r10 != 0) goto L_0x0118
            r8.f796l = r4     // Catch:{ all -> 0x0124 }
            java.lang.String r10 = ""
            r8.updatePyd(r10)     // Catch:{ all -> 0x0124 }
            r8.loadProperty(r9)     // Catch:{ all -> 0x0124 }
            java.lang.String r9 = r8.getTempPyd()     // Catch:{ all -> 0x0124 }
            r8.updatePyd(r9)     // Catch:{ all -> 0x0124 }
        L_0x0118:
            monitor-exit(r13)     // Catch:{ all -> 0x0124 }
            monitor-exit(r12)     // Catch:{ all -> 0x012c }
            monitor-exit(r1)     // Catch:{ all -> 0x0138 }
            return
        L_0x011c:
            monitor-exit(r13)     // Catch:{ all -> 0x0124 }
            monitor-exit(r12)     // Catch:{ all -> 0x012c }
            monitor-exit(r1)     // Catch:{ all -> 0x0138 }
            return
        L_0x0120:
            monitor-exit(r13)     // Catch:{ all -> 0x0124 }
            monitor-exit(r12)     // Catch:{ all -> 0x012c }
            monitor-exit(r1)     // Catch:{ all -> 0x0138 }
            return
        L_0x0124:
            r9 = move-exception
            goto L_0x012a
        L_0x0126:
            monitor-exit(r13)     // Catch:{ all -> 0x0124 }
            monitor-exit(r12)     // Catch:{ all -> 0x012c }
            monitor-exit(r1)     // Catch:{ all -> 0x0138 }
            return
        L_0x012a:
            monitor-exit(r13)     // Catch:{ all -> 0x0124 }
            throw r9     // Catch:{ all -> 0x012c }
        L_0x012c:
            r9 = move-exception
            goto L_0x0131
        L_0x012e:
            monitor-exit(r12)     // Catch:{ all -> 0x012c }
            monitor-exit(r1)     // Catch:{ all -> 0x0138 }
            return
        L_0x0131:
            monitor-exit(r12)     // Catch:{ all -> 0x012c }
            throw r9     // Catch:{ all -> 0x0138 }
        L_0x0133:
            r9 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x0133 }
            throw r9     // Catch:{ Exception -> 0x0136 }
        L_0x0136:
            monitor-exit(r1)     // Catch:{ all -> 0x0138 }
            return
        L_0x0138:
            r9 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0138 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.DataCore.saveLogData(android.content.Context, boolean, boolean, long, boolean, org.json.JSONObject):void");
    }

    /* renamed from: b */
    private void m743b(Context context, JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject != null && jSONObject.length() != 0 && jSONObject2 != null && jSONObject2.length() != 0) {
            try {
                jSONObject.put(Config.LAUNCH, jSONObject2);
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    private void m734a(Context context, String str, boolean z, boolean z2) {
        StatService.WearListener wearListener = this.f793i;
        if (wearListener == null || !wearListener.onSendLogData(str)) {
            boolean z3 = false;
            LogSender.instance().saveLogData(context, str, false);
            C0955bb c = C0955bb.m1194c();
            c.mo11624a("Save log: " + str);
            if (z) {
                C0936au.m1086a(context);
                return;
            }
            if (C0939av.m1093a().mo11597b() == 0 && C0939av.m1093a().mo11604c()) {
                z3 = true;
            }
            C0936au.m1087a(context, z3);
            return;
        }
        C0955bb c2 = C0955bb.m1194c();
        c2.mo11624a("Log has been passed to app level, log: " + str);
    }

    public void clearCache(Context context) {
        m740a(false);
        String optString = f785a.optString(Config.DEVICE_ID_SEC);
        if (!TextUtils.isEmpty(optString)) {
            C0982bp.m1357a().mo11704l(context, optString);
        }
        synchronized (f785a) {
            f785a = new JSONObject();
        }
        installHeader(context);
        m733a(context);
    }

    /* renamed from: a */
    private void m733a(Context context) {
        synchronized (this.f789e) {
            this.f789e = new JSONArray();
        }
        synchronized (this.f788d) {
            this.f788d = new JSONArray();
        }
        synchronized (this.f790f) {
            this.f790f = new JSONArray();
        }
        flush(context);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:(2:51|52)|53|54|(2:56|96)(2:57|97)|60) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x0142 */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0163 A[Catch:{ Exception -> 0x0254 }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0174 A[Catch:{ Exception -> 0x0254 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setPydProperty(android.content.Context r28, java.util.Map<java.lang.String, java.lang.String> r29, java.lang.String r30, java.lang.String r31) {
        /*
            r27 = this;
            r0 = r27
            r1 = r30
            r2 = r31
            java.lang.String r3 = "s"
            java.lang.String r4 = "v"
            java.lang.String r5 = "k"
            boolean r6 = android.text.TextUtils.isEmpty(r31)     // Catch:{ Exception -> 0x0254 }
            if (r6 != 0) goto L_0x0254
            boolean r6 = android.text.TextUtils.isEmpty(r30)     // Catch:{ Exception -> 0x0254 }
            if (r6 == 0) goto L_0x001a
            goto L_0x0254
        L_0x001a:
            if (r29 != 0) goto L_0x0020
            r0.clearProperty(r2)     // Catch:{ Exception -> 0x0254 }
            return
        L_0x0020:
            int r6 = r29.size()     // Catch:{ Exception -> 0x0254 }
            r7 = 100
            if (r6 > r7) goto L_0x0254
            java.util.List<java.lang.String> r6 = r0.f798n     // Catch:{ Exception -> 0x0254 }
            int r6 = r6.size()     // Catch:{ Exception -> 0x0254 }
            if (r6 <= r7) goto L_0x0032
            goto L_0x0254
        L_0x0032:
            java.lang.StringBuffer r6 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0254 }
            r6.<init>()     // Catch:{ Exception -> 0x0254 }
            java.lang.StringBuffer r8 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0254 }
            r8.<init>()     // Catch:{ Exception -> 0x0254 }
            java.lang.StringBuffer r9 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0254 }
            r9.<init>()     // Catch:{ Exception -> 0x0254 }
            java.lang.StringBuffer r10 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0254 }
            r10.<init>()     // Catch:{ Exception -> 0x0254 }
            java.lang.StringBuffer r11 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0254 }
            r11.<init>()     // Catch:{ Exception -> 0x0254 }
            org.json.JSONObject r12 = new org.json.JSONObject     // Catch:{ Exception -> 0x0254 }
            r12.<init>()     // Catch:{ Exception -> 0x0254 }
            java.util.Set r13 = r29.entrySet()     // Catch:{ Exception -> 0x0254 }
            java.util.Iterator r13 = r13.iterator()     // Catch:{ Exception -> 0x0254 }
        L_0x0058:
            boolean r14 = r13.hasNext()     // Catch:{ Exception -> 0x0254 }
            java.lang.String r15 = "4"
            java.lang.String r7 = "3"
            r16 = r12
            java.lang.String r12 = "1"
            r17 = r10
            java.lang.String r10 = "0"
            r29 = r15
            java.lang.String r15 = "2"
            if (r14 == 0) goto L_0x01a6
            java.lang.Object r14 = r13.next()     // Catch:{ Exception -> 0x0254 }
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14     // Catch:{ Exception -> 0x0254 }
            java.lang.Object r18 = r14.getKey()     // Catch:{ Exception -> 0x0254 }
            r19 = r13
            r13 = r18
            java.lang.String r13 = (java.lang.String) r13     // Catch:{ Exception -> 0x0254 }
            java.lang.Object r14 = r14.getValue()     // Catch:{ Exception -> 0x0254 }
            java.lang.String r14 = (java.lang.String) r14     // Catch:{ Exception -> 0x0254 }
            boolean r18 = android.text.TextUtils.isEmpty(r13)     // Catch:{ Exception -> 0x0254 }
            if (r18 != 0) goto L_0x018a
            boolean r18 = android.text.TextUtils.isEmpty(r14)     // Catch:{ Exception -> 0x0254 }
            if (r18 == 0) goto L_0x0092
            goto L_0x018a
        L_0x0092:
            r18 = r7
            int r7 = r13.length()     // Catch:{ Exception -> 0x0254 }
            r20 = r11
            r11 = 256(0x100, float:3.59E-43)
            if (r7 > r11) goto L_0x0177
            int r7 = r14.length()     // Catch:{ Exception -> 0x0254 }
            if (r7 <= r11) goto L_0x00a6
            goto L_0x0177
        L_0x00a6:
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ Exception -> 0x0254 }
            r7.<init>()     // Catch:{ Exception -> 0x0254 }
            r7.put(r5, r13)     // Catch:{ Exception -> 0x0254 }
            r7.put(r4, r14)     // Catch:{ Exception -> 0x0254 }
            r7.put(r3, r1)     // Catch:{ Exception -> 0x0254 }
            java.util.List<java.lang.String> r11 = r0.f798n     // Catch:{ Exception -> 0x0254 }
            int r11 = r11.size()     // Catch:{ Exception -> 0x0254 }
            r21 = 0
            r22 = 1
            if (r11 <= 0) goto L_0x0105
            r23 = r9
            r11 = 0
        L_0x00c3:
            java.util.List<java.lang.String> r9 = r0.f798n     // Catch:{ Exception -> 0x0254 }
            int r9 = r9.size()     // Catch:{ Exception -> 0x0254 }
            if (r11 >= r9) goto L_0x0102
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch:{ Exception -> 0x0254 }
            r24 = r15
            java.util.List<java.lang.String> r15 = r0.f798n     // Catch:{ Exception -> 0x0254 }
            java.lang.Object r15 = r15.get(r11)     // Catch:{ Exception -> 0x0254 }
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ Exception -> 0x0254 }
            r9.<init>(r15)     // Catch:{ Exception -> 0x0254 }
            java.lang.String r15 = r9.optString(r5)     // Catch:{ Exception -> 0x0254 }
            r25 = r5
            java.lang.String r5 = r9.optString(r4)     // Catch:{ Exception -> 0x0254 }
            java.lang.String r9 = r9.optString(r3)     // Catch:{ Exception -> 0x0254 }
            boolean r15 = r13.equals(r15)     // Catch:{ Exception -> 0x0254 }
            if (r15 == 0) goto L_0x00fb
            boolean r5 = r14.equals(r5)     // Catch:{ Exception -> 0x0254 }
            if (r5 == 0) goto L_0x00fb
            boolean r5 = r1.equals(r9)     // Catch:{ Exception -> 0x0254 }
            if (r5 == 0) goto L_0x00fb
            goto L_0x010e
        L_0x00fb:
            int r11 = r11 + 1
            r15 = r24
            r5 = r25
            goto L_0x00c3
        L_0x0102:
            r25 = r5
            goto L_0x0109
        L_0x0105:
            r25 = r5
            r23 = r9
        L_0x0109:
            r24 = r15
            r11 = 0
            r21 = 1
        L_0x010e:
            java.util.List<java.lang.String> r5 = r0.f798n     // Catch:{ Exception -> 0x0254 }
            int r5 = r5.size()     // Catch:{ Exception -> 0x0254 }
            r9 = 100
            if (r5 <= r9) goto L_0x0119
            return
        L_0x0119:
            if (r21 == 0) goto L_0x0142
            java.util.List<java.lang.String> r5 = r0.f798n     // Catch:{ Exception -> 0x0142 }
            java.lang.String r15 = r7.toString()     // Catch:{ Exception -> 0x0142 }
            r5.add(r15)     // Catch:{ Exception -> 0x0142 }
            java.util.List<java.lang.String> r5 = r0.f798n     // Catch:{ Exception -> 0x0142 }
            int r5 = r5.size()     // Catch:{ Exception -> 0x0142 }
            int r11 = r5 + -1
            org.json.JSONObject r5 = r0.f799o     // Catch:{ Exception -> 0x0142 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0142 }
            r15.<init>()     // Catch:{ Exception -> 0x0142 }
            r15.append(r11)     // Catch:{ Exception -> 0x0142 }
            java.lang.String r9 = ""
            r15.append(r9)     // Catch:{ Exception -> 0x0142 }
            java.lang.String r9 = r15.toString()     // Catch:{ Exception -> 0x0142 }
            r5.put(r9, r7)     // Catch:{ Exception -> 0x0142 }
        L_0x0142:
            r0.updatePropertyKey(r2, r6, r10, r11)     // Catch:{ Exception -> 0x0254 }
            r0.updatePropertyKey(r2, r8, r12, r11)     // Catch:{ Exception -> 0x0254 }
            r5 = r23
            r7 = r24
            r0.updatePropertyKey(r2, r5, r7, r11)     // Catch:{ Exception -> 0x0254 }
            r15 = r18
            r9 = r20
            r0.updatePropertyKey(r2, r9, r15, r11)     // Catch:{ Exception -> 0x0254 }
            r12 = r29
            r10 = r17
            r0.updatePropertyKey(r2, r10, r12, r11)     // Catch:{ Exception -> 0x0254 }
            boolean r11 = r2.equals(r7)     // Catch:{ Exception -> 0x0254 }
            if (r11 == 0) goto L_0x0174
            org.json.JSONArray r11 = new org.json.JSONArray     // Catch:{ Exception -> 0x0254 }
            r11.<init>()     // Catch:{ Exception -> 0x0254 }
            r11.put(r14)     // Catch:{ Exception -> 0x0254 }
            r11.put(r7)     // Catch:{ Exception -> 0x0254 }
            r14 = r16
            r14.put(r13, r11)     // Catch:{ Exception -> 0x0254 }
            goto L_0x019b
        L_0x0174:
            r14 = r16
            goto L_0x019b
        L_0x0177:
            r25 = r5
            r5 = r9
            r14 = r16
            r10 = r17
            r9 = r20
            com.baidu.mobstat.bb r7 = com.baidu.mobstat.C0955bb.m1194c()     // Catch:{ Exception -> 0x0254 }
            java.lang.String r11 = "[WARNING] setProperty failed,key or value can not over 256 bytes !"
            r7.mo11631c((java.lang.String) r11)     // Catch:{ Exception -> 0x0254 }
            goto L_0x019b
        L_0x018a:
            r25 = r5
            r5 = r9
            r9 = r11
            r14 = r16
            r10 = r17
            com.baidu.mobstat.bb r7 = com.baidu.mobstat.C0955bb.m1194c()     // Catch:{ Exception -> 0x0254 }
            java.lang.String r11 = "[WARNING] setProperty failed,key or value can not null !"
            r7.mo11631c((java.lang.String) r11)     // Catch:{ Exception -> 0x0254 }
        L_0x019b:
            r11 = r9
            r12 = r14
            r13 = r19
            r7 = 100
            r9 = r5
            r5 = r25
            goto L_0x0058
        L_0x01a6:
            r3 = r29
            r5 = r9
            r9 = r11
            r14 = r16
            r1 = r17
            r26 = r15
            r15 = r7
            r7 = r26
            org.json.JSONObject r4 = r0.f799o     // Catch:{ Exception -> 0x0254 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0254 }
            boolean r10 = r2.equals(r10)     // Catch:{ Exception -> 0x0254 }
            if (r10 == 0) goto L_0x01d1
            boolean r10 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Exception -> 0x0254 }
            if (r10 != 0) goto L_0x01d1
            java.util.HashMap<java.lang.String, java.lang.String> r1 = r0.f797m     // Catch:{ Exception -> 0x0254 }
            java.lang.String r3 = "uidPy"
            java.lang.String r5 = r6.toString()     // Catch:{ Exception -> 0x0254 }
            r1.put(r3, r5)     // Catch:{ Exception -> 0x0254 }
            goto L_0x023b
        L_0x01d1:
            boolean r6 = r2.equals(r12)     // Catch:{ Exception -> 0x0254 }
            if (r6 == 0) goto L_0x01e9
            boolean r6 = android.text.TextUtils.isEmpty(r8)     // Catch:{ Exception -> 0x0254 }
            if (r6 != 0) goto L_0x01e9
            java.util.HashMap<java.lang.String, java.lang.String> r1 = r0.f797m     // Catch:{ Exception -> 0x0254 }
            java.lang.String r3 = "userPy"
            java.lang.String r5 = r8.toString()     // Catch:{ Exception -> 0x0254 }
            r1.put(r3, r5)     // Catch:{ Exception -> 0x0254 }
            goto L_0x023b
        L_0x01e9:
            boolean r6 = r2.equals(r7)     // Catch:{ Exception -> 0x0254 }
            if (r6 == 0) goto L_0x020c
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception -> 0x0254 }
            if (r6 != 0) goto L_0x020c
            java.util.HashMap<java.lang.String, java.lang.String> r1 = r0.f797m     // Catch:{ Exception -> 0x0254 }
            java.lang.String r3 = "sessionPy"
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0254 }
            r1.put(r3, r5)     // Catch:{ Exception -> 0x0254 }
            java.util.HashMap<java.lang.String, java.lang.String> r1 = r0.f797m     // Catch:{ Exception -> 0x0254 }
            java.lang.String r3 = "sessionJson"
            java.lang.String r5 = r14.toString()     // Catch:{ Exception -> 0x0254 }
            r1.put(r3, r5)     // Catch:{ Exception -> 0x0254 }
            goto L_0x023b
        L_0x020c:
            boolean r5 = r2.equals(r15)     // Catch:{ Exception -> 0x0254 }
            if (r5 == 0) goto L_0x0224
            boolean r5 = android.text.TextUtils.isEmpty(r9)     // Catch:{ Exception -> 0x0254 }
            if (r5 != 0) goto L_0x0224
            java.util.HashMap<java.lang.String, java.lang.String> r1 = r0.f797m     // Catch:{ Exception -> 0x0254 }
            java.lang.String r3 = "eventPy"
            java.lang.String r5 = r9.toString()     // Catch:{ Exception -> 0x0254 }
            r1.put(r3, r5)     // Catch:{ Exception -> 0x0254 }
            goto L_0x023b
        L_0x0224:
            boolean r3 = r2.equals(r3)     // Catch:{ Exception -> 0x0254 }
            if (r3 == 0) goto L_0x023b
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0254 }
            if (r3 != 0) goto L_0x023b
            java.util.HashMap<java.lang.String, java.lang.String> r3 = r0.f797m     // Catch:{ Exception -> 0x0254 }
            java.lang.String r5 = "pagePy"
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0254 }
            r3.put(r5, r1)     // Catch:{ Exception -> 0x0254 }
        L_0x023b:
            boolean r1 = r2.equals(r7)     // Catch:{ Exception -> 0x0254 }
            if (r1 == 0) goto L_0x024c
            com.baidu.mobstat.CooperService r1 = com.baidu.mobstat.CooperService.instance()     // Catch:{ Exception -> 0x0254 }
            java.lang.String r2 = r14.toString()     // Catch:{ Exception -> 0x0254 }
            r1.setHeaderPy(r2)     // Catch:{ Exception -> 0x0254 }
        L_0x024c:
            boolean r1 = r0.f796l     // Catch:{ Exception -> 0x0254 }
            if (r1 == 0) goto L_0x0254
            r0.updatePyd(r4)     // Catch:{ Exception -> 0x0254 }
        L_0x0254:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.DataCore.setPydProperty(android.content.Context, java.util.Map, java.lang.String, java.lang.String):void");
    }

    public void clearProperty(String str) {
        if (str.equals("0")) {
            this.f797m.put(Config.UID_PY, "");
        } else if (str.equals("1")) {
            this.f797m.put(Config.USER_PY, "");
        } else if (str.equals("2")) {
            this.f797m.put(Config.SESSION_PY, "");
            this.f797m.put(Config.SESSION_JSON_PY, "");
        } else if (str.equals("3")) {
            this.f797m.put(Config.EVENT_PY, "");
        } else if (str.equals(PropertyType.PAGE_PROPERTRY)) {
            this.f797m.put(Config.PAGE_PY, "");
        }
    }

    public void updatePropertyKey(String str, StringBuffer stringBuffer, String str2, int i) {
        if (i >= 0 && str.equals(str2)) {
            if (!TextUtils.isEmpty(stringBuffer)) {
                stringBuffer.append(Config.replace);
            }
            stringBuffer.append(i);
        }
    }

    public String getTempPyd() {
        JSONObject jSONObject = this.f799o;
        return (jSONObject == null || jSONObject.length() <= 0) ? "" : this.f799o.toString();
    }

    public String getHeadSessionPy() {
        String str = this.f797m.get(Config.SESSION_JSON_PY);
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public String getSessionPy() {
        String str = this.f797m.get(Config.SESSION_PY);
        String str2 = this.f797m.get(Config.USER_PY);
        String str3 = this.f797m.get(Config.UID_PY);
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = "";
        }
        String replace = replace(str + Config.replace + str3, Config.replace);
        return replace(replace + Config.replace + str2, Config.replace);
    }

    public String getPagePy() {
        String str = this.f797m.get(Config.PAGE_PY);
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        String sessionPy = getSessionPy();
        return replace(sessionPy + Config.replace + str, Config.replace);
    }

    public String getEventPy() {
        String str = this.f797m.get(Config.EVENT_PY);
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        String sessionPy = getSessionPy();
        return replace(sessionPy + Config.replace + str, Config.replace);
    }

    public String replace(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.startsWith(str2)) {
            str = str.replaceFirst(str2, "");
        }
        if (str.endsWith(str2)) {
            str = str.substring(0, str.length() - 1);
        }
        String replace = str.replace("null", "");
        if (TextUtils.isEmpty(replace)) {
            return "";
        }
        return replace;
    }
}
