package com.baidu.mobstat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.baidu.mobstat.C0933at;
import com.baidu.mobstat.Config;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.av */
public class C0939av {

    /* renamed from: b */
    private static C0939av f1129b = new C0939av();

    /* renamed from: l */
    private static String f1130l = "";

    /* renamed from: a */
    public C0946a f1131a;

    /* renamed from: c */
    private HandlerThread f1132c = new HandlerThread("fullTraceHandleThread");

    /* renamed from: d */
    private Handler f1133d;

    /* renamed from: e */
    private volatile int f1134e;

    /* renamed from: f */
    private int f1135f;

    /* renamed from: g */
    private JSONObject f1136g = new JSONObject();

    /* renamed from: h */
    private JSONArray f1137h = new JSONArray();
    /* access modifiers changed from: private */

    /* renamed from: i */
    public JSONArray f1138i = new JSONArray();

    /* renamed from: j */
    private JSONArray f1139j = new JSONArray();

    /* renamed from: k */
    private JSONArray f1140k = new JSONArray();

    /* renamed from: m */
    private boolean f1141m = false;

    /* renamed from: n */
    private List<JSONObject> f1142n = new ArrayList();

    /* renamed from: o */
    private List<String> f1143o = new ArrayList();

    /* renamed from: p */
    private List<String> f1144p = new ArrayList();

    /* renamed from: com.baidu.mobstat.av$a */
    public interface C0946a {
        /* renamed from: a */
        void mo11611a(JSONObject jSONObject);
    }

    /* renamed from: b */
    private void m1113b(JSONObject jSONObject) {
    }

    /* renamed from: b */
    public void mo11601b(String str) {
    }

    /* renamed from: a */
    public static C0939av m1093a() {
        return f1129b;
    }

    private C0939av() {
        this.f1132c.start();
        this.f1132c.setPriority(10);
        this.f1133d = new Handler(this.f1132c.getLooper());
    }

    /* renamed from: b */
    public int mo11597b() {
        return this.f1135f;
    }

    /* renamed from: c */
    public boolean mo11604c() {
        return this.f1141m;
    }

    /* renamed from: a */
    public void mo11588a(Context context) {
        if (context != null) {
            this.f1141m = true;
            m1123e();
            String str = C0991bw.m1474u(context) + Config.STAT_FULL_CACHE_FILE_NAME;
            if (C0980bn.m1350c(context, str)) {
                String a = C0980bn.m1345a(context, str);
                if (!TextUtils.isEmpty(a)) {
                    JSONObject jSONObject = null;
                    try {
                        jSONObject = new JSONObject(a);
                    } catch (Exception unused) {
                    }
                    if (jSONObject != null) {
                        try {
                            JSONArray optJSONArray = jSONObject.optJSONArray(Config.EVENT_PART);
                            JSONArray optJSONArray2 = jSONObject.optJSONArray(Config.PRINCIPAL_PART);
                            JSONArray optJSONArray3 = jSONObject.optJSONArray(Config.FEED_LIST_PART);
                            JSONArray optJSONArray4 = jSONObject.optJSONArray("sv");
                            if ((optJSONArray == null || optJSONArray.length() == 0) && ((optJSONArray2 == null || optJSONArray2.length() == 0) && (optJSONArray3 == null || optJSONArray3.length() == 0))) {
                                if (optJSONArray4 != null) {
                                    if (optJSONArray4.length() == 0) {
                                    }
                                }
                                if (C0963bg.m1227c().mo11630b()) {
                                    C0963bg.m1227c().mo11624a("saveLastCacheToSend content:empty, return");
                                    return;
                                }
                                return;
                            }
                            m1110b(context, jSONObject.getJSONObject(Config.HEADER_PART));
                            m1118c(context, jSONObject);
                            m1113b(jSONObject);
                            a = jSONObject.toString();
                        } catch (Exception unused2) {
                        }
                        if (C0963bg.m1227c().mo11630b()) {
                            C0963bg.m1227c().mo11624a("saveLastCacheToSend content: " + a);
                        }
                        m1116c(context, a);
                        mo11603c(context, false);
                        this.f1141m = false;
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public void mo11591a(Context context, String str, String str2, String str3, int i, long j, String str4, JSONArray jSONArray, String str5, JSONArray jSONArray2, String str6, Map<String, String> map, JSONObject jSONObject, String str7, JSONArray jSONArray3) {
        mo11592a(context, str, str2, str3, i, j, str4, jSONArray, str5, jSONArray2, str6, map, false, jSONObject, str7, jSONArray3);
    }

    /* renamed from: a */
    public void mo11592a(Context context, String str, String str2, String str3, int i, long j, String str4, JSONArray jSONArray, String str5, JSONArray jSONArray2, String str6, Map<String, String> map, boolean z, JSONObject jSONObject, String str7, JSONArray jSONArray3) {
        final Context context2 = context;
        final String str8 = str;
        final String str9 = str2;
        final String str10 = str3;
        final int i2 = i;
        final long j2 = j;
        final String str11 = str4;
        final JSONArray jSONArray4 = jSONArray;
        final String str12 = str5;
        final JSONArray jSONArray5 = jSONArray2;
        final String str13 = str6;
        final Map<String, String> map2 = map;
        final boolean z2 = z;
        final JSONObject jSONObject2 = jSONObject;
        final String str14 = str7;
        final JSONArray jSONArray6 = jSONArray3;
        C09401 r21 = r0;
        C09401 r0 = new Runnable(this) {

            /* renamed from: q */
            final /* synthetic */ C0939av f1161q;

            {
                this.f1161q = r4;
            }

            public void run() {
                long sessionStartTime = BDStatCore.instance().getSessionStartTime();
                if (sessionStartTime > 0) {
                    this.f1161q.m1097a(context2, sessionStartTime, str8, str9, str10, i2, j2, str11, jSONArray4, str12, jSONArray5, str13, map2, z2, jSONObject2, str14, jSONArray6);
                }
            }
        };
        this.f1133d.post(r21);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1097a(Context context, long j, String str, String str2, String str3, int i, long j2, String str4, JSONArray jSONArray, String str5, JSONArray jSONArray2, String str6, Map<String, String> map, boolean z, JSONObject jSONObject, String str7, JSONArray jSONArray3) {
        String c = C0968bi.m1274c(jSONArray);
        String d = C0968bi.m1280d(jSONArray2);
        m1099a(context, EventAnalysis.getEvent(context, j, str, str2, str3, i, j2, 0, "", (JSONArray) null, (JSONArray) null, C0968bi.m1251a(str4), C0968bi.m1251a(str5), str6, Config.EventViewType.EDIT.getValue(), 3, (ExtraInfo) null, map, c, d, z, jSONObject, str7, jSONArray3));
        mo11602c(context);
    }

    /* renamed from: a */
    private void m1099a(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            if (C0963bg.m1227c().mo11630b()) {
                C0963bg c = C0963bg.m1227c();
                c.mo11624a("putEvent: " + jSONObject.toString());
            }
            String jSONObject2 = jSONObject.toString();
            if (m1115b(context, jSONObject2)) {
                if (C0963bg.m1227c().mo11630b()) {
                    C0963bg c2 = C0963bg.m1227c();
                    c2.mo11624a("checkExceedLogLimit exceed:true; mCacheLogSize: " + this.f1134e + "; addedSize:" + jSONObject2.length());
                }
                m1120d(context);
            }
            try {
                jSONObject.put(Config.EVENT_NEXT_PAGENAME, "");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            EventAnalysis.doEventMerge(this.f1137h, jSONObject);
        }
    }

    /* renamed from: b */
    private boolean m1115b(Context context, String str) {
        if ((str != null ? str.getBytes().length : 0) + this.f1134e > 184320) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public void mo11594a(final Context context, final boolean z) {
        this.f1133d.post(new Runnable() {
            public void run() {
                C0939av.this.mo11600b(context, z);
            }
        });
    }

    /* renamed from: a */
    public void mo11596a(final JSONObject jSONObject) {
        this.f1133d.post(new Runnable() {
            public void run() {
                JSONObject jSONObject = jSONObject;
                if (jSONObject != null && jSONObject.length() != 0) {
                    C0939av avVar = C0939av.this;
                    JSONArray unused = avVar.f1138i = avVar.m1107b(avVar.f1138i, jSONObject);
                }
            }
        });
    }

    /* renamed from: b */
    public void mo11600b(Context context, boolean z) {
        if (z) {
            m1123e();
        } else {
            m1119d();
        }
        try {
            m1110b(context, this.f1136g);
        } catch (Exception unused) {
        }
        if (this.f1137h.length() != 0 || this.f1138i.length() != 0 || this.f1139j.length() != 0 || this.f1140k.length() != 0) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Config.HEADER_PART, this.f1136g);
            } catch (Exception unused2) {
            }
            try {
                jSONObject.put(Config.PRINCIPAL_PART, this.f1138i);
            } catch (Exception unused3) {
            }
            try {
                jSONObject.put(Config.EVENT_PART, this.f1137h);
            } catch (Exception unused4) {
            }
            try {
                jSONObject.put(Config.FEED_LIST_PART, this.f1139j);
            } catch (Exception unused5) {
            }
            try {
                jSONObject.put("sv", this.f1140k);
            } catch (Exception unused6) {
            }
            try {
                jSONObject.put(Config.EVENT_PAGE_MAPPING, C0933at.m1079a().mo11583a(C0933at.C0935a.f1127b));
            } catch (Exception unused7) {
            }
            try {
                jSONObject.put(Config.EVENT_PATH_MAPPING, C0933at.m1079a().mo11583a(C0933at.C0935a.f1126a));
            } catch (Exception unused8) {
            }
            try {
                jSONObject.put(Config.FEED_LIST_MAPPING, C0933at.m1079a().mo11583a(C0933at.C0935a.f1128c));
            } catch (Exception unused9) {
            }
            try {
                jSONObject.put(Config.PYD, f1130l);
            } catch (Exception unused10) {
            }
            m1118c(context, jSONObject);
            m1113b(jSONObject);
            String jSONObject2 = jSONObject.toString();
            if (C0963bg.m1227c().mo11630b()) {
                C0963bg c = C0963bg.m1227c();
                c.mo11624a("saveCurrentCacheToSend content: " + jSONObject2);
            }
            m1116c(context, jSONObject2);
            mo11603c(context, !z);
            this.f1141m = true;
        }
    }

    /* renamed from: a */
    public void mo11595a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            f1130l = str;
        } catch (Exception unused) {
        }
    }

    /* renamed from: c */
    public void mo11603c(Context context, boolean z) {
        this.f1136g = new JSONObject();
        mo11598b(context);
        this.f1138i = new JSONArray();
        this.f1137h = new JSONArray();
        this.f1139j = new JSONArray();
        this.f1140k = new JSONArray();
        if (!z) {
            C0933at.m1079a().mo11584b();
        }
        mo11602c(context);
    }

    /* renamed from: b */
    public void mo11598b(Context context) {
        CooperService.instance().getHeadObject().installHeader(context, this.f1136g);
    }

    /* renamed from: c */
    public void mo11602c(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Config.HEADER_PART, new JSONObject(this.f1136g.toString()));
            jSONObject.put(Config.PRINCIPAL_PART, new JSONArray(this.f1138i.toString()));
            jSONObject.put(Config.EVENT_PART, new JSONArray(this.f1137h.toString()));
            jSONObject.put(Config.FEED_LIST_PART, new JSONArray(this.f1139j.toString()));
            jSONObject.put("sv", new JSONArray(this.f1140k.toString()));
            jSONObject.put(Config.PYD, f1130l);
            jSONObject.put(Config.EVENT_PAGE_MAPPING, C0933at.m1079a().mo11583a(C0933at.C0935a.f1127b));
            jSONObject.put(Config.EVENT_PATH_MAPPING, C0933at.m1079a().mo11583a(C0933at.C0935a.f1126a));
            jSONObject.put(Config.FEED_LIST_MAPPING, C0933at.m1079a().mo11583a(C0933at.C0935a.f1128c));
        } catch (Exception unused) {
        }
        String jSONObject2 = jSONObject.toString();
        int length = jSONObject2.getBytes().length;
        if (length < 184320) {
            this.f1134e = length;
            String u = C0991bw.m1474u(context);
            C0980bn.m1347a(context, u + Config.STAT_FULL_CACHE_FILE_NAME, jSONObject2, false);
        }
    }

    /* renamed from: b */
    private void m1110b(Context context, JSONObject jSONObject) {
        CooperService.instance().getHeadObject().installHeader(context, jSONObject);
        try {
            jSONObject.put("t", System.currentTimeMillis());
            jSONObject.put(Config.SEQUENCE_INDEX, this.f1135f);
            jSONObject.put("ss", BDStatCore.instance().getSessionStartTime());
            jSONObject.put("at", "1");
            jSONObject.put("sign", CooperService.instance().getUUID());
            jSONObject.put(Config.f780PY, DataCore.instance().getHeadSessionPy());
        } catch (Exception unused) {
        }
    }

    /* renamed from: d */
    private void m1120d(Context context) {
        this.f1138i = m1107b(this.f1138i, BDStatCore.instance().getPageSessionHead());
        mo11600b(context, false);
        m1119d();
    }

    /* renamed from: d */
    private void m1119d() {
        this.f1135f++;
    }

    /* renamed from: e */
    private void m1123e() {
        this.f1135f = 0;
    }

    /* renamed from: c */
    private void m1118c(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(Config.TRACE_FAILED_CNT, 0);
            } catch (Exception unused) {
            }
            try {
                jSONObject.put(Config.TRACE_PART, jSONObject2);
            } catch (Exception unused2) {
            }
        }
    }

    /* renamed from: c */
    private void m1116c(Context context, String str) {
        LogSender.instance().saveLogData(context, str, true);
        if (this.f1131a != null) {
            try {
                this.f1131a.mo11611a(new JSONObject(str));
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    public void mo11589a(final Context context, final C0910an anVar) {
        this.f1133d.post(new Runnable() {
            public void run() {
                if (BDStatCore.instance().getSessionStartTime() > 0) {
                    C0939av.this.m1108b(context, anVar);
                }
            }
        });
    }

    /* renamed from: a */
    public void mo11593a(final Context context, final ArrayList<C0911ao> arrayList) {
        this.f1133d.post(new Runnable() {
            public void run() {
                C0939av.this.m1117c(context, (ArrayList<C0911ao>) arrayList);
            }
        });
    }

    /* renamed from: b */
    public void mo11599b(final Context context, final ArrayList<C0912ap> arrayList) {
        this.f1133d.post(new Runnable() {
            public void run() {
                C0939av.this.m1121d(context, (ArrayList<C0912ap>) arrayList);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m1108b(Context context, C0910an anVar) {
        if (context != null && anVar != null) {
            this.f1138i = m1107b(this.f1138i, BDStatCore.instance().getPageSessionHead());
            if (this.f1138i.length() != 0) {
                long a = m1092a(this.f1138i);
                if (a > 0) {
                    m1122d(context, anVar.mo11516a(a, C0933at.m1079a().mo11582a(anVar.mo11515a(), C0933at.C0935a.f1127b), C0933at.m1079a().mo11582a(anVar.mo11517b(), C0933at.C0935a.f1128c)));
                    mo11602c(context);
                }
            }
        }
    }

    /* renamed from: d */
    private void m1122d(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            if (C0963bg.m1227c().mo11630b()) {
                C0963bg c = C0963bg.m1227c();
                c.mo11624a("putPage: " + jSONObject.toString());
            }
            String jSONObject2 = jSONObject.toString();
            if (m1115b(context, jSONObject2)) {
                if (C0963bg.m1227c().mo11630b()) {
                    C0963bg c2 = C0963bg.m1227c();
                    c2.mo11624a("checkExceedLogLimit exceed:true; mCacheLogSize: " + this.f1134e + "; addedSize:" + jSONObject2.length());
                }
                m1120d(context);
            }
            m1104a(this.f1138i, jSONObject);
        }
    }

    /* renamed from: a */
    private void m1104a(JSONArray jSONArray, JSONObject jSONObject) {
        JSONObject jSONObject2;
        JSONArray jSONArray2 = null;
        try {
            jSONObject2 = jSONArray.getJSONObject(0);
        } catch (Exception unused) {
            jSONObject2 = null;
        }
        if (jSONObject2 != null) {
            try {
                jSONArray2 = jSONObject2.optJSONArray("p");
            } catch (Exception unused2) {
            }
        }
        if (jSONArray2 == null) {
            JSONArray jSONArray3 = new JSONArray();
            jSONArray3.put(jSONObject);
            if (jSONObject2 != null) {
                try {
                    jSONObject2.put("p", jSONArray3);
                } catch (Exception unused3) {
                }
            }
        } else {
            jSONArray2.put(jSONObject);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0053 A[SYNTHETIC, Splitter:B:29:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x005a  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONArray m1107b(org.json.JSONArray r6, org.json.JSONObject r7) {
        /*
            r5 = this;
            if (r7 == 0) goto L_0x005e
            if (r6 != 0) goto L_0x0005
            goto L_0x005e
        L_0x0005:
            java.lang.String r0 = "s"
            long r0 = r7.optLong(r0)
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 > 0) goto L_0x0012
            return r6
        L_0x0012:
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            int r1 = r6.length()
            java.lang.String r2 = "p"
            r3 = 0
            if (r1 != 0) goto L_0x0039
            java.lang.String r6 = r7.toString()     // Catch:{ Exception -> 0x0032 }
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ Exception -> 0x0032 }
            r7.<init>(r6)     // Catch:{ Exception -> 0x0032 }
            org.json.JSONArray r6 = new org.json.JSONArray     // Catch:{ Exception -> 0x0033 }
            r6.<init>()     // Catch:{ Exception -> 0x0033 }
            r7.put(r2, r6)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0033
        L_0x0032:
            r7 = r3
        L_0x0033:
            if (r7 == 0) goto L_0x005d
            r0.put(r7)
            goto L_0x005d
        L_0x0039:
            r1 = 0
            org.json.JSONObject r6 = r6.getJSONObject(r1)     // Catch:{ Exception -> 0x003f }
            goto L_0x0040
        L_0x003f:
            r6 = r3
        L_0x0040:
            if (r6 == 0) goto L_0x0047
            org.json.JSONArray r6 = r6.getJSONArray(r2)     // Catch:{ Exception -> 0x0047 }
            goto L_0x0048
        L_0x0047:
            r6 = r3
        L_0x0048:
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0057 }
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x0057 }
            r1.<init>(r7)     // Catch:{ Exception -> 0x0057 }
            if (r6 == 0) goto L_0x0058
            r1.put(r2, r6)     // Catch:{ Exception -> 0x0058 }
            goto L_0x0058
        L_0x0057:
            r1 = r3
        L_0x0058:
            if (r1 == 0) goto L_0x005d
            r0.put(r1)
        L_0x005d:
            return r0
        L_0x005e:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0939av.m1107b(org.json.JSONArray, org.json.JSONObject):org.json.JSONArray");
    }

    /* renamed from: a */
    private long m1092a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return 0;
        }
        try {
            return jSONArray.getJSONObject(0).optLong("s");
        } catch (Exception unused) {
            return 0;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m1117c(Context context, ArrayList<C0911ao> arrayList) {
        if (context != null && arrayList != null && arrayList.size() != 0) {
            JSONArray jSONArray = new JSONArray();
            Iterator<C0911ao> it = arrayList.iterator();
            while (it.hasNext()) {
                C0911ao next = it.next();
                JSONObject a = next.mo11519a(C0933at.m1079a().mo11582a(next.mo11518a(), C0933at.C0935a.f1128c));
                if (a != null) {
                    jSONArray.put(a);
                }
            }
            m1098a(context, jSONArray);
            mo11602c(context);
        }
    }

    /* renamed from: a */
    private void m1098a(Context context, JSONArray jSONArray) {
        if (context != null && this.f1139j != null && jSONArray != null && jSONArray.length() != 0) {
            if (C0963bg.m1227c().mo11630b()) {
                C0963bg c = C0963bg.m1227c();
                c.mo11624a("putFeedList: " + jSONArray.toString());
            }
            String jSONArray2 = jSONArray.toString();
            if (m1115b(context, jSONArray2)) {
                if (C0963bg.m1227c().mo11630b()) {
                    C0963bg c2 = C0963bg.m1227c();
                    c2.mo11624a("checkExceedLogLimit exceed:true; mCacheLogSize: " + this.f1134e + "; addedSize:" + jSONArray2.length());
                }
                m1120d(context);
            }
            m1103a(this.f1139j, jSONArray);
        }
    }

    /* renamed from: a */
    private void m1103a(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray != null && jSONArray2 != null) {
            int i = 0;
            while (i < jSONArray2.length()) {
                try {
                    jSONArray.put(jSONArray2.getJSONObject(i));
                    i++;
                } catch (Exception unused) {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m1121d(Context context, ArrayList<C0912ap> arrayList) {
        if (context != null && arrayList != null && arrayList.size() != 0) {
            JSONArray jSONArray = new JSONArray();
            Iterator<C0912ap> it = arrayList.iterator();
            while (it.hasNext()) {
                C0912ap next = it.next();
                JSONObject a = next.mo11527a(C0933at.m1079a().mo11582a(next.mo11531b(), C0933at.C0935a.f1127b), C0933at.m1079a().mo11582a(next.mo11537f(), C0933at.C0935a.f1128c), C0968bi.m1274c(next.mo11534c()));
                if (a != null) {
                    jSONArray.put(a);
                }
            }
            m1109b(context, jSONArray);
            mo11602c(context);
        }
    }

    /* renamed from: b */
    private void m1109b(Context context, JSONArray jSONArray) {
        if (context != null && this.f1140k != null && jSONArray != null && jSONArray.length() != 0) {
            if (C0963bg.m1227c().mo11630b()) {
                C0963bg c = C0963bg.m1227c();
                c.mo11624a("putFeedListItem: " + jSONArray.toString());
            }
            String jSONArray2 = jSONArray.toString();
            if (m1115b(context, jSONArray2)) {
                if (C0963bg.m1227c().mo11630b()) {
                    C0963bg c2 = C0963bg.m1227c();
                    c2.mo11624a("checkExceedLogLimit exceed:true; mCacheLogSize: " + this.f1134e + "; addedSize:" + jSONArray2.length());
                }
                m1120d(context);
            }
            m1112b(this.f1140k, jSONArray);
        }
    }

    /* renamed from: b */
    private void m1112b(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray != null && jSONArray2 != null) {
            int i = 0;
            while (i < jSONArray2.length()) {
                try {
                    JSONObject jSONObject = jSONArray2.getJSONObject(i);
                    if (jSONObject != null) {
                        if (jSONObject.length() != 0) {
                            JSONObject jSONObject2 = null;
                            int i2 = 0;
                            while (true) {
                                if (i2 >= jSONArray.length()) {
                                    break;
                                }
                                JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                                if (jSONObject3 != null) {
                                    if (jSONObject3.length() != 0) {
                                        if (m1106a(jSONObject3, jSONObject)) {
                                            jSONObject2 = jSONObject3;
                                            break;
                                        }
                                    }
                                }
                                i2++;
                            }
                            if (jSONObject2 == null) {
                                jSONArray.put(jSONObject);
                            } else {
                                m1114b(jSONObject2, jSONObject);
                            }
                        }
                    }
                    i++;
                } catch (Exception unused) {
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m1106a(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject jSONObject3 = jSONObject;
        JSONObject jSONObject4 = jSONObject2;
        if (jSONObject3 == null || jSONObject4 == null) {
            return false;
        }
        String optString = jSONObject3.optString("id");
        jSONObject3.optString("d");
        String optString2 = jSONObject3.optString("p");
        String optString3 = jSONObject3.optString(Config.FEED_LIST_ITEM_PATH);
        String optString4 = jSONObject3.optString("title");
        String optString5 = jSONObject3.optString(Config.FEED_LIST_ITEM_INDEX);
        String optString6 = jSONObject3.optString("n");
        String str = optString5;
        int optInt = jSONObject3.optInt("user");
        jSONObject3.optInt("c");
        String str2 = optString4;
        jSONObject3.optLong("t");
        String str3 = optString3;
        jSONObject3.optString("ps");
        String optString7 = jSONObject4.optString("id");
        jSONObject4.optString("d");
        String optString8 = jSONObject4.optString("p");
        String optString9 = jSONObject4.optString(Config.FEED_LIST_ITEM_PATH);
        String optString10 = jSONObject4.optString("title");
        String optString11 = jSONObject4.optString(Config.FEED_LIST_ITEM_INDEX);
        String optString12 = jSONObject4.optString("n");
        int optInt2 = jSONObject4.optInt("user");
        jSONObject4.optInt("c");
        jSONObject4.optLong("t");
        jSONObject4.optString("ps");
        if (m1105a(optString, optString7) && m1105a(optString2, optString8) && m1105a(str3, optString9) && m1105a(str2, optString10) && m1105a(str, optString11) && m1105a(optString6, optString12) && optInt == optInt2) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    private void m1114b(JSONObject jSONObject, JSONObject jSONObject2) {
        long j;
        String str;
        String str2;
        String str3;
        long j2;
        String[] strArr;
        String str4;
        long j3;
        JSONObject jSONObject3 = jSONObject;
        JSONObject jSONObject4 = jSONObject2;
        String optString = jSONObject3.optString("d");
        int optInt = jSONObject3.optInt("c");
        long optLong = jSONObject3.optLong("t");
        String str5 = "ps";
        String optString2 = jSONObject3.optString(str5);
        String optString3 = jSONObject4.optString("d");
        int optInt2 = jSONObject4.optInt("c");
        long optLong2 = jSONObject4.optLong("t");
        String optString4 = jSONObject4.optString(str5);
        int i = optInt + optInt2;
        long j4 = optLong <= optLong2 ? optLong : optLong2;
        if (optLong <= optLong2) {
            j = j4;
            str = optString + "|" + optString3;
        } else {
            j = j4;
            str = optString3 + "|" + optString;
        }
        int i2 = 0;
        long j5 = 0;
        if (optLong <= optLong2) {
            long j6 = optLong2 - optLong;
            StringBuilder sb = new StringBuilder();
            String[] split = optString4.split("\\|");
            if (split == null || split.length == 0) {
                str2 = str5;
                try {
                    j5 = Long.valueOf(optString4).longValue();
                } catch (Exception unused) {
                }
                sb.append(j6 + j5);
            } else {
                int length = split.length;
                while (i2 < length) {
                    String str6 = split[i2];
                    if (!TextUtils.isEmpty(sb.toString())) {
                        sb.append("|");
                    }
                    try {
                        j3 = Long.valueOf(str6).longValue();
                        strArr = split;
                        str4 = str5;
                    } catch (Exception unused2) {
                        strArr = split;
                        str4 = str5;
                        j3 = 0;
                    }
                    sb.append(j6 + j3);
                    i2++;
                    split = strArr;
                    str5 = str4;
                }
                str2 = str5;
            }
            str3 = optString2 + "|" + sb.toString();
        } else {
            str2 = str5;
            long j7 = optLong - optLong2;
            StringBuilder sb2 = new StringBuilder();
            String[] split2 = optString2.split("\\|");
            if (split2 == null || split2.length == 0) {
                try {
                    j5 = Long.valueOf(optString2).longValue();
                } catch (Exception unused3) {
                }
                sb2.append(j7 + j5);
            } else {
                int length2 = split2.length;
                while (i2 < length2) {
                    String str7 = split2[i2];
                    if (!TextUtils.isEmpty(sb2.toString())) {
                        sb2.append("|");
                    }
                    try {
                        j2 = Long.valueOf(str7).longValue();
                    } catch (Exception unused4) {
                        j2 = 0;
                    }
                    sb2.append(j2 + j7);
                    i2++;
                }
            }
            str3 = optString4 + "|" + sb2.toString();
        }
        try {
            jSONObject3.put("c", i);
            jSONObject3.put("t", j);
            jSONObject3.put("d", str);
            jSONObject3.put(str2, str3);
        } catch (Exception unused5) {
        }
    }

    /* renamed from: a */
    private boolean m1105a(String str, String str2) {
        if (str == str2) {
            return true;
        }
        return !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && str.equals(str2);
    }

    /* renamed from: a */
    public void mo11590a(Context context, String str) {
        JSONArray jSONArray = this.f1137h;
        if (jSONArray != null && jSONArray.length() != 0 && !TextUtils.isEmpty(str)) {
            try {
                if (str.length() > 1024) {
                    str = str.substring(0, 1024);
                }
                JSONObject jSONObject = (JSONObject) this.f1137h.get(this.f1137h.length() - 1);
                if (jSONObject != null) {
                    String optString = jSONObject.optString(Config.EVENT_NEXT_PAGENAME);
                    long optLong = jSONObject.optLong("t");
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - optLong <= 1500 && TextUtils.isEmpty(optString)) {
                        jSONObject.put(Config.EVENT_NEXT_PAGENAME, str + "|" + currentTimeMillis);
                        this.f1137h.put(this.f1137h.length() + -1, jSONObject);
                        mo11602c(context);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
