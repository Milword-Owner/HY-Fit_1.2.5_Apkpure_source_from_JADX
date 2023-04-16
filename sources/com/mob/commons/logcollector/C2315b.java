package com.mob.commons.logcollector;

import android.content.ContentValues;
import android.database.Cursor;
import com.mob.tools.MobLog;

/* renamed from: com.mob.commons.logcollector.b */
/* compiled from: DBProvider */
public class C2315b {

    /* renamed from: b */
    private static C2315b f2155b;

    /* renamed from: a */
    private C2314a f2156a = new C2314a();

    private C2315b() {
    }

    /* renamed from: a */
    public static synchronized C2315b m2598a() {
        C2315b bVar;
        synchronized (C2315b.class) {
            if (f2155b == null) {
                f2155b = new C2315b();
            }
            bVar = f2155b;
        }
        return bVar;
    }

    /* renamed from: a */
    public long mo29116a(String str, ContentValues contentValues) {
        try {
            return this.f2156a.getWritableDatabase().replace(str, (String) null, contentValues);
        } catch (Exception e) {
            MobLog.getInstance().mo29788w(e, "when insert database occur error table:%s,", str);
            return -1;
        }
    }

    /* renamed from: a */
    public int mo29115a(String str, String str2, String[] strArr) {
        int i;
        try {
            i = this.f2156a.getWritableDatabase().delete(str, str2, strArr);
            try {
                MobLog.getInstance().mo29768d("Deleted %d rows from table: %s", Integer.valueOf(i), str);
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            i = 0;
            MobLog.getInstance().mo29788w(e, "when delete database occur error table:%s,", str);
            return i;
        }
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        if (r9 == null) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (r9 != null) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001c, code lost:
        r9.close();
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int mo29114a(java.lang.String r11) {
        /*
            r10 = this;
            com.mob.commons.logcollector.a r0 = r10.f2156a
            android.database.sqlite.SQLiteDatabase r1 = r0.getWritableDatabase()
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r0 = 0
            r9 = 0
            r2 = r11
            android.database.Cursor r9 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ Throwable -> 0x0022 }
            if (r9 != 0) goto L_0x0016
            goto L_0x001a
        L_0x0016:
            int r0 = r9.getCount()     // Catch:{ Throwable -> 0x0022 }
        L_0x001a:
            if (r9 == 0) goto L_0x002d
        L_0x001c:
            r9.close()
            goto L_0x002d
        L_0x0020:
            r11 = move-exception
            goto L_0x002e
        L_0x0022:
            r11 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0020 }
            r1.mo29787w((java.lang.Throwable) r11)     // Catch:{ all -> 0x0020 }
            if (r9 == 0) goto L_0x002d
            goto L_0x001c
        L_0x002d:
            return r0
        L_0x002e:
            if (r9 == 0) goto L_0x0033
            r9.close()
        L_0x0033:
            goto L_0x0035
        L_0x0034:
            throw r11
        L_0x0035:
            goto L_0x0034
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.logcollector.C2315b.mo29114a(java.lang.String):int");
    }

    /* renamed from: a */
    public Cursor mo29117a(String str, String[] strArr) {
        try {
            return this.f2156a.getWritableDatabase().rawQuery(str, strArr);
        } catch (Exception e) {
            MobLog.getInstance().mo29787w((Throwable) e);
            return null;
        }
    }
}
