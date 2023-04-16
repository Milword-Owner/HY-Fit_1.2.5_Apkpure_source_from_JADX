package com.mob.mobapm.internal;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.mobapm.p026b.C2345a;
import com.mob.tools.utils.ResHelper;
import java.util.HashMap;

/* renamed from: com.mob.mobapm.internal.g */
public class C2396g {

    /* renamed from: b */
    private static C2396g f2320b;

    /* renamed from: a */
    private String f2321a;

    private C2396g() {
    }

    /* renamed from: b */
    public static C2396g m2883b() {
        if (f2320b == null) {
            synchronized (C2396g.class) {
                if (f2320b == null) {
                    f2320b = new C2396g();
                }
            }
        }
        return f2320b;
    }

    /* renamed from: c */
    private String m2884c() {
        HashMap hashMap;
        try {
            HashMap<String, Object> d = m2885d();
            if (d != null && !d.isEmpty() && (hashMap = (HashMap) d.get("deviceInfo")) != null && !hashMap.isEmpty()) {
                return (String) hashMap.get("oaid");
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }

    /* renamed from: d */
    private HashMap<String, Object> m2885d() {
        try {
            return m2881a(ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.duid"));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: e */
    private String m2886e() {
        return C2345a.m2729i();
    }

    /* renamed from: a */
    public String mo29324a() {
        if (TextUtils.isEmpty(this.f2321a)) {
            String e = m2886e();
            this.f2321a = e;
            if (TextUtils.isEmpty(e)) {
                String c = m2884c();
                this.f2321a = c;
                if (!TextUtils.isEmpty(c)) {
                    m2882a(this.f2321a);
                }
            }
        }
        return this.f2321a;
    }

    /* renamed from: a */
    private void m2882a(String str) {
        C2345a.m2711a(str);
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0039 */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x002f A[SYNTHETIC, Splitter:B:20:0x002f] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.HashMap<java.lang.String, java.lang.Object> m2881a(java.io.File r4) {
        /*
            r3 = this;
            r0 = 0
            boolean r1 = r4.exists()     // Catch:{ all -> 0x003a }
            if (r1 == 0) goto L_0x003a
            boolean r1 = r4.isFile()     // Catch:{ all -> 0x003a }
            if (r1 == 0) goto L_0x003a
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0023 }
            r1.<init>(r4)     // Catch:{ all -> 0x0023 }
            java.io.ObjectInputStream r4 = new java.io.ObjectInputStream     // Catch:{ all -> 0x0023 }
            r4.<init>(r1)     // Catch:{ all -> 0x0023 }
            java.lang.Object r1 = r4.readObject()     // Catch:{ all -> 0x0021 }
            java.util.HashMap r1 = (java.util.HashMap) r1     // Catch:{ all -> 0x0021 }
            r4.close()     // Catch:{ all -> 0x0020 }
        L_0x0020:
            return r1
        L_0x0021:
            r1 = move-exception
            goto L_0x0026
        L_0x0023:
            r4 = move-exception
            r1 = r4
            r4 = r0
        L_0x0026:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0033 }
            r2.mo29787w((java.lang.Throwable) r1)     // Catch:{ all -> 0x0033 }
            if (r4 == 0) goto L_0x003a
            r4.close()     // Catch:{ all -> 0x003a }
            goto L_0x003a
        L_0x0033:
            r1 = move-exception
            if (r4 == 0) goto L_0x0039
            r4.close()     // Catch:{ all -> 0x0039 }
        L_0x0039:
            throw r1     // Catch:{ all -> 0x003a }
        L_0x003a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.mobapm.internal.C2396g.m2881a(java.io.File):java.util.HashMap");
    }
}
