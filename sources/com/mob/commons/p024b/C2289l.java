package com.mob.commons.p024b;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import com.mob.commons.C2312k;
import com.mob.commons.p024b.C2277f;

/* renamed from: com.mob.commons.b.l */
/* compiled from: Vivo */
public class C2289l extends C2277f {

    /* renamed from: c */
    private C2290a f2098c = null;

    /* renamed from: d */
    private C2290a f2099d = null;

    /* renamed from: e */
    private C2290a f2100e = null;

    /* renamed from: f */
    private String f2101f = "11154";

    public C2289l(Context context) {
        super(context);
    }

    /* renamed from: h */
    public synchronized boolean mo29056h() {
        return "1".equals(m2415a(C2312k.m2575a(94), "0"));
    }

    /* renamed from: a */
    private String m2415a(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{str, "unknown"});
        } catch (Throwable th) {
            C2272c.m2344a().mo29044a(th);
            return str2;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public C2277f.C2281c mo29050c() {
        C2277f.C2281c cVar = new C2277f.C2281c();
        cVar.f2082b = mo29066a(0);
        cVar.f2085e = mo29066a(1);
        cVar.f2083c = mo29066a(2);
        if (TextUtils.isEmpty(cVar.f2083c)) {
            cVar.f2083c = mo29057i();
        }
        return cVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0037, code lost:
        if (r0 != null) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        m2419c(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004e, code lost:
        if (r0 != null) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0051, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0031 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x003c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:36:0x0058 */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0055 A[SYNTHETIC, Splitter:B:34:0x0055] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String mo29066a(int r10) {
        /*
            r9 = this;
            java.lang.String r0 = r9.m2418b(r10)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            android.net.Uri r3 = android.net.Uri.parse(r0)     // Catch:{ Throwable -> 0x0045, all -> 0x0040 }
            android.content.Context r0 = r9.f2069a     // Catch:{ Throwable -> 0x0045, all -> 0x0040 }
            android.content.ContentResolver r2 = r0.getContentResolver()     // Catch:{ Throwable -> 0x0045, all -> 0x0040 }
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r0 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Throwable -> 0x0045, all -> 0x0040 }
            if (r0 == 0) goto L_0x0037
            boolean r2 = r0.moveToNext()     // Catch:{ Throwable -> 0x0035 }
            if (r2 == 0) goto L_0x0037
            java.lang.String r2 = "value"
            int r2 = r0.getColumnIndex(r2)     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r1 = r0.getString(r2)     // Catch:{ Throwable -> 0x0035 }
            if (r0 == 0) goto L_0x0031
            r0.close()     // Catch:{ Throwable -> 0x0031 }
        L_0x0031:
            r9.m2419c(r10)     // Catch:{ Throwable -> 0x0034 }
        L_0x0034:
            return r1
        L_0x0035:
            r2 = move-exception
            goto L_0x0047
        L_0x0037:
            if (r0 == 0) goto L_0x003c
        L_0x0039:
            r0.close()     // Catch:{ Throwable -> 0x003c }
        L_0x003c:
            r9.m2419c(r10)     // Catch:{ Throwable -> 0x0051 }
            goto L_0x0051
        L_0x0040:
            r0 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
            goto L_0x0053
        L_0x0045:
            r2 = move-exception
            r0 = r1
        L_0x0047:
            com.mob.commons.b.c r3 = com.mob.commons.p024b.C2272c.m2344a()     // Catch:{ all -> 0x0052 }
            r3.mo29044a(r2)     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x003c
            goto L_0x0039
        L_0x0051:
            return r1
        L_0x0052:
            r1 = move-exception
        L_0x0053:
            if (r0 == 0) goto L_0x0058
            r0.close()     // Catch:{ Throwable -> 0x0058 }
        L_0x0058:
            r9.m2419c(r10)     // Catch:{ Throwable -> 0x005b }
        L_0x005b:
            goto L_0x005d
        L_0x005c:
            throw r1
        L_0x005d:
            goto L_0x005c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p024b.C2289l.mo29066a(int):java.lang.String");
    }

    /* renamed from: b */
    private String m2418b(int i) {
        if (i == 0) {
            return C2312k.m2575a(95);
        }
        if (i == 1) {
            return C2312k.m2575a(96) + this.f2101f;
        } else if (i != 2) {
            return null;
        } else {
            return C2312k.m2575a(97) + this.f2101f;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2417a(boolean z, int i) {
        try {
            String a = mo29066a(i);
            if (i == 0) {
                mo29048a(a);
            } else if (i == 2) {
                mo29049b(a);
            } else if (i == 1) {
                mo29051c(a);
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: c */
    private void m2419c(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i == 2 && this.f2100e == null) {
                    this.f2100e = new C2290a(this, 2);
                    this.f2069a.getContentResolver().registerContentObserver(Uri.parse(m2418b(2)), false, this.f2100e);
                }
            } else if (this.f2099d == null) {
                this.f2099d = new C2290a(this, 1);
                this.f2069a.getContentResolver().registerContentObserver(Uri.parse(m2418b(1)), false, this.f2099d);
            }
        } else if (this.f2098c == null) {
            this.f2098c = new C2290a(this, 0);
            this.f2069a.getContentResolver().registerContentObserver(Uri.parse(m2418b(0)), true, this.f2098c);
        }
    }

    /* renamed from: com.mob.commons.b.l$a */
    /* compiled from: Vivo */
    private static class C2290a extends ContentObserver {

        /* renamed from: a */
        private int f2102a;

        /* renamed from: b */
        private C2289l f2103b;

        public C2290a(C2289l lVar, int i) {
            super((Handler) null);
            this.f2102a = i;
            this.f2103b = lVar;
        }

        public void onChange(boolean z) {
            C2289l lVar = this.f2103b;
            if (lVar != null) {
                lVar.m2417a(z, this.f2102a);
            }
        }
    }
}
