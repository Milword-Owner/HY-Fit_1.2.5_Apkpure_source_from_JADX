package com.baidu.mobstat;

/* renamed from: com.baidu.mobstat.ar */
public class C0914ar {

    /* renamed from: e */
    private static final C0914ar f1052e = new C0914ar();

    /* renamed from: a */
    private boolean f1053a = false;

    /* renamed from: b */
    private float f1054b = 50.0f;

    /* renamed from: c */
    private long f1055c = 500;

    /* renamed from: d */
    private volatile boolean f1056d;

    /* renamed from: a */
    public static C0914ar m990a() {
        return f1052e;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:7|(1:9)(1:10)|11|(2:13|14)|15|16|(1:18)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0040 */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0046 A[Catch:{ Exception -> 0x0050 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo11544a(java.lang.String r5) {
        /*
            r4 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            r0 = 1
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x0050 }
            r1.<init>(r5)     // Catch:{ Exception -> 0x0050 }
            java.lang.String r5 = "sv"
            java.lang.Object r5 = r1.opt(r5)     // Catch:{ Exception -> 0x0050 }
            org.json.JSONObject r5 = (org.json.JSONObject) r5     // Catch:{ Exception -> 0x0050 }
            if (r5 == 0) goto L_0x0050
            java.lang.String r1 = "close"
            int r1 = r5.optInt(r1)     // Catch:{ Exception -> 0x0050 }
            java.lang.String r2 = "area"
            java.lang.String r2 = r5.optString(r2)     // Catch:{ Exception -> 0x0050 }
            java.lang.String r3 = "duration"
            java.lang.String r5 = r5.optString(r3)     // Catch:{ Exception -> 0x0050 }
            if (r1 == 0) goto L_0x002d
            r1 = 1
            goto L_0x002e
        L_0x002d:
            r1 = 0
        L_0x002e:
            r4.f1053a = r1     // Catch:{ Exception -> 0x0050 }
            boolean r1 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x0050 }
            if (r1 != 0) goto L_0x0040
            java.lang.Float r1 = java.lang.Float.valueOf(r2)     // Catch:{ Exception -> 0x0040 }
            float r1 = r1.floatValue()     // Catch:{ Exception -> 0x0040 }
            r4.f1054b = r1     // Catch:{ Exception -> 0x0040 }
        L_0x0040:
            boolean r1 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception -> 0x0050 }
            if (r1 != 0) goto L_0x0050
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ Exception -> 0x0050 }
            long r1 = r5.longValue()     // Catch:{ Exception -> 0x0050 }
            r4.f1055c = r1     // Catch:{ Exception -> 0x0050 }
        L_0x0050:
            r4.f1056d = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0914ar.mo11544a(java.lang.String):void");
    }

    /* renamed from: b */
    public boolean mo11545b() {
        return this.f1053a;
    }

    /* renamed from: c */
    public float mo11546c() {
        float f = this.f1054b;
        if (f < 0.0f) {
            f = 0.0f;
        } else if (f > 100.0f) {
            f = 100.0f;
        }
        return f / 100.0f;
    }

    /* renamed from: d */
    public long mo11547d() {
        return this.f1055c;
    }
}
