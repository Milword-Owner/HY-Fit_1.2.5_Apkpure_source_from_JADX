package p005cn.sharesdk.framework.p007b.p008a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.framework.b.a.b */
/* compiled from: DBProvider */
public class C0710b {

    /* renamed from: b */
    private static C0710b f216b;

    /* renamed from: a */
    private C0709a f217a = new C0709a();

    private C0710b() {
    }

    /* renamed from: a */
    public static synchronized C0710b m187a() {
        C0710b bVar;
        synchronized (C0710b.class) {
            if (f216b == null) {
                f216b = new C0710b();
            }
            bVar = f216b;
        }
        return bVar;
    }

    /* renamed from: a */
    public Cursor mo10580a(String str, String[] strArr, String str2, String[] strArr2, String str3) {
        SQLiteDatabase writableDatabase = this.f217a.getWritableDatabase();
        SSDKLog.m645b().mo29768d("Query table: %s", str);
        try {
            return writableDatabase.query(str, strArr, str2, strArr2, (String) null, (String) null, str3);
        } catch (Exception e) {
            Exception exc = e;
            SSDKLog.m645b().mo29788w(exc, "when query database occur error table:%s,", str);
            return null;
        }
    }

    /* renamed from: a */
    public long mo10579a(String str, ContentValues contentValues) {
        try {
            return this.f217a.getWritableDatabase().replace(str, (String) null, contentValues);
        } catch (Exception e) {
            SSDKLog.m645b().mo29788w(e, "when insert database occur error table:%s,", str);
            return -1;
        }
    }

    /* renamed from: a */
    public int mo10578a(String str, String str2, String[] strArr) {
        int i;
        try {
            i = this.f217a.getWritableDatabase().delete(str, str2, strArr);
            try {
                SSDKLog.m645b().mo29768d("Deleted %d rows from table: %s", Integer.valueOf(i), str);
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            i = 0;
            SSDKLog.m645b().mo29788w(e, "when delete database occur error table:%s,", str);
            return i;
        }
        return i;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.String[], android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int mo10577a(java.lang.String r6) {
        /*
            r5 = this;
            cn.sharesdk.framework.b.a.a r0 = r5.f217a
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            r1 = 0
            r2 = 0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x002a }
            r3.<init>()     // Catch:{ Exception -> 0x002a }
            java.lang.String r4 = "select count(*) from "
            r3.append(r4)     // Catch:{ Exception -> 0x002a }
            r3.append(r6)     // Catch:{ Exception -> 0x002a }
            java.lang.String r6 = r3.toString()     // Catch:{ Exception -> 0x002a }
            android.database.Cursor r1 = r0.rawQuery(r6, r1)     // Catch:{ Exception -> 0x002a }
            boolean r6 = r1.moveToNext()     // Catch:{ Exception -> 0x002a }
            if (r6 == 0) goto L_0x0032
            int r2 = r1.getInt(r2)     // Catch:{ Exception -> 0x002a }
            goto L_0x0032
        L_0x0028:
            r6 = move-exception
            goto L_0x0036
        L_0x002a:
            r6 = move-exception
            com.mob.tools.log.NLog r0 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ all -> 0x0028 }
            r0.mo29787w((java.lang.Throwable) r6)     // Catch:{ all -> 0x0028 }
        L_0x0032:
            r1.close()
            return r2
        L_0x0036:
            r1.close()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.framework.p007b.p008a.C0710b.mo10577a(java.lang.String):int");
    }
}
