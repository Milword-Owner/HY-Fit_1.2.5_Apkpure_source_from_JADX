package com.baidu.mobstat;

import android.text.TextUtils;
import com.baidu.mobstat.C0985bs;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.ap */
public class C0912ap {

    /* renamed from: a */
    private String f1037a;

    /* renamed from: b */
    private String f1038b;

    /* renamed from: c */
    private JSONArray f1039c;

    /* renamed from: d */
    private String f1040d;

    /* renamed from: e */
    private String f1041e;

    /* renamed from: f */
    private String f1042f;

    /* renamed from: g */
    private boolean f1043g;

    /* renamed from: h */
    private long f1044h;

    /* renamed from: i */
    private long f1045i;

    /* renamed from: j */
    private long f1046j;

    /* renamed from: k */
    private int f1047k;

    /* renamed from: l */
    private String f1048l;

    /* renamed from: m */
    private String f1049m;

    /* renamed from: n */
    private String f1050n;

    /* renamed from: o */
    private JSONArray f1051o;

    public C0912ap(String str, String str2, JSONArray jSONArray, String str3, String str4, String str5, String str6, boolean z, int i, long j, long j2, long j3, String str7, String str8, JSONArray jSONArray2) {
        this.f1037a = str;
        this.f1038b = str2;
        this.f1039c = jSONArray;
        this.f1040d = str3;
        this.f1041e = str5;
        this.f1042f = str6;
        this.f1043g = z;
        this.f1047k = i;
        this.f1044h = j;
        this.f1045i = j2;
        this.f1046j = j3;
        this.f1048l = str7;
        this.f1049m = str8;
        this.f1050n = str4;
        this.f1051o = jSONArray2;
    }

    /* renamed from: a */
    public String mo11526a() {
        return this.f1037a;
    }

    /* renamed from: b */
    public String mo11531b() {
        return this.f1038b;
    }

    /* renamed from: c */
    public JSONArray mo11534c() {
        return this.f1039c;
    }

    /* renamed from: d */
    public String mo11535d() {
        return this.f1040d;
    }

    /* renamed from: e */
    public String mo11536e() {
        return this.f1041e;
    }

    /* renamed from: f */
    public String mo11537f() {
        return this.f1042f;
    }

    /* renamed from: g */
    public boolean mo11538g() {
        return this.f1043g;
    }

    /* renamed from: h */
    public int mo11539h() {
        return this.f1047k;
    }

    /* renamed from: i */
    public long mo11540i() {
        return this.f1044h;
    }

    /* renamed from: a */
    public void mo11528a(int i) {
        this.f1047k = i;
    }

    /* renamed from: j */
    public long mo11541j() {
        return this.f1045i;
    }

    /* renamed from: k */
    public long mo11542k() {
        return this.f1046j;
    }

    /* renamed from: a */
    public void mo11529a(long j) {
        this.f1045i = j;
    }

    /* renamed from: b */
    public void mo11532b(long j) {
        this.f1046j = j;
    }

    /* renamed from: l */
    public String mo11543l() {
        return this.f1048l;
    }

    /* renamed from: a */
    public void mo11530a(String str) {
        this.f1048l = str;
    }

    /* renamed from: b */
    public void mo11533b(String str) {
        this.f1049m = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x006e A[Catch:{ Exception -> 0x008f }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0070 A[Catch:{ Exception -> 0x008f }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject mo11527a(java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            r7 = this;
            java.lang.String r0 = r7.f1037a
            java.lang.String r1 = r7.f1038b
            org.json.JSONArray r2 = r7.f1039c
            java.lang.String r3 = r7.f1040d
            java.lang.String r4 = r7.f1041e
            java.lang.String r5 = r7.f1042f
            boolean r6 = r7.f1043g
            java.lang.String r0 = m964a(r0, r1, r2, r3, r4, r5, r6)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r2 = 0
            if (r1 == 0) goto L_0x001a
            return r2
        L_0x001a:
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            java.lang.String r3 = "id"
            java.lang.String r4 = r7.f1037a     // Catch:{ Exception -> 0x008f }
            r1.put(r3, r4)     // Catch:{ Exception -> 0x008f }
            java.lang.String r3 = "d"
            java.lang.String r4 = r7.f1048l     // Catch:{ Exception -> 0x008f }
            r1.put(r3, r4)     // Catch:{ Exception -> 0x008f }
            java.lang.String r3 = "p"
            r1.put(r3, r8)     // Catch:{ Exception -> 0x008f }
            java.lang.String r8 = "path"
            r1.put(r8, r10)     // Catch:{ Exception -> 0x008f }
            java.lang.String r8 = "v5"
            org.json.JSONArray r10 = r7.f1051o     // Catch:{ Exception -> 0x008f }
            if (r10 == 0) goto L_0x0049
            org.json.JSONArray r10 = r7.f1051o     // Catch:{ Exception -> 0x008f }
            int r10 = r10.length()     // Catch:{ Exception -> 0x008f }
            if (r10 != 0) goto L_0x0046
            goto L_0x0049
        L_0x0046:
            org.json.JSONArray r10 = r7.f1051o     // Catch:{ Exception -> 0x008f }
            goto L_0x004b
        L_0x0049:
            java.lang.String r10 = ""
        L_0x004b:
            r1.put(r8, r10)     // Catch:{ Exception -> 0x008f }
            java.lang.String r8 = "title"
            java.lang.String r10 = r7.f1040d     // Catch:{ Exception -> 0x008f }
            r1.put(r8, r10)     // Catch:{ Exception -> 0x008f }
            java.lang.String r8 = "content"
            java.lang.String r10 = r7.f1050n     // Catch:{ Exception -> 0x008f }
            r1.put(r8, r10)     // Catch:{ Exception -> 0x008f }
            java.lang.String r8 = "index"
            java.lang.String r10 = r7.f1041e     // Catch:{ Exception -> 0x008f }
            r1.put(r8, r10)     // Catch:{ Exception -> 0x008f }
            java.lang.String r8 = "n"
            r1.put(r8, r9)     // Catch:{ Exception -> 0x008f }
            java.lang.String r8 = "user"
            boolean r9 = r7.f1043g     // Catch:{ Exception -> 0x008f }
            if (r9 == 0) goto L_0x0070
            r9 = 1
            goto L_0x0071
        L_0x0070:
            r9 = 0
        L_0x0071:
            r1.put(r8, r9)     // Catch:{ Exception -> 0x008f }
            java.lang.String r8 = "c"
            int r9 = r7.f1047k     // Catch:{ Exception -> 0x008f }
            r1.put(r8, r9)     // Catch:{ Exception -> 0x008f }
            java.lang.String r8 = "t"
            long r9 = r7.f1044h     // Catch:{ Exception -> 0x008f }
            r1.put(r8, r9)     // Catch:{ Exception -> 0x008f }
            java.lang.String r8 = "ps"
            java.lang.String r9 = r7.f1049m     // Catch:{ Exception -> 0x008f }
            r1.put(r8, r9)     // Catch:{ Exception -> 0x008f }
            java.lang.String r8 = "sign"
            r1.put(r8, r0)     // Catch:{ Exception -> 0x008f }
            goto L_0x0090
        L_0x008f:
            r1 = r2
        L_0x0090:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0912ap.mo11527a(java.lang.String, java.lang.String, java.lang.String):org.json.JSONObject");
    }

    /* renamed from: a */
    public static String m964a(String str, String str2, JSONArray jSONArray, String str3, String str4, String str5, boolean z) {
        String str6;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", str);
            jSONObject.put("p", str2);
            jSONObject.put(Config.FEED_LIST_ITEM_PATH, jSONArray);
            jSONObject.put("title", str3);
            jSONObject.put(Config.FEED_LIST_ITEM_INDEX, str4);
            jSONObject.put("n", str5);
            jSONObject.put("user", z ? 1 : 0);
            str6 = jSONObject.toString();
        } catch (Exception unused) {
            str6 = "";
        }
        if (!TextUtils.isEmpty(str6)) {
            return C0985bs.C0986a.m1411a(str6.getBytes());
        }
        return "";
    }
}
