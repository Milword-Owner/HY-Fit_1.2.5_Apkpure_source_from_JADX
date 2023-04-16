package com.baidu.mobstat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/* renamed from: com.baidu.mobstat.l */
class C1061l extends SQLiteOpenHelper {

    /* renamed from: a */
    private String f1430a;

    /* renamed from: b */
    private SQLiteDatabase f1431b;

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public C1061l(Context context, String str) throws SQLiteException {
        super(context, C1087y.f1473e, (SQLiteDatabase.CursorFactory) null, 1);
        this.f1430a = str;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.f1431b = sQLiteDatabase;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:9|10|11|12|13) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x001c */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean mo11859a() {
        /*
            r3 = this;
            monitor-enter(r3)
            android.database.sqlite.SQLiteDatabase r0 = r3.f1431b     // Catch:{ all -> 0x0034 }
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0009
        L_0x0007:
            r0 = 1
            goto L_0x0013
        L_0x0009:
            android.database.sqlite.SQLiteDatabase r0 = r3.f1431b     // Catch:{ all -> 0x0034 }
            boolean r0 = r0.isOpen()     // Catch:{ all -> 0x0034 }
            if (r0 != 0) goto L_0x0012
            goto L_0x0007
        L_0x0012:
            r0 = 0
        L_0x0013:
            if (r0 == 0) goto L_0x0024
            android.database.sqlite.SQLiteDatabase r0 = r3.getWritableDatabase()     // Catch:{ NullPointerException -> 0x001c }
            r3.f1431b = r0     // Catch:{ NullPointerException -> 0x001c }
            goto L_0x0024
        L_0x001c:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x0034 }
            java.lang.String r1 = "db path is null"
            r0.<init>(r1)     // Catch:{ all -> 0x0034 }
            throw r0     // Catch:{ all -> 0x0034 }
        L_0x0024:
            android.database.sqlite.SQLiteDatabase r0 = r3.f1431b     // Catch:{ all -> 0x0034 }
            if (r0 == 0) goto L_0x0032
            android.database.sqlite.SQLiteDatabase r0 = r3.f1431b     // Catch:{ all -> 0x0034 }
            boolean r0 = r0.isOpen()     // Catch:{ all -> 0x0034 }
            if (r0 != 0) goto L_0x0031
            goto L_0x0032
        L_0x0031:
            r1 = 1
        L_0x0032:
            monitor-exit(r3)
            return r1
        L_0x0034:
            r0 = move-exception
            monitor-exit(r3)
            goto L_0x0038
        L_0x0037:
            throw r0
        L_0x0038:
            goto L_0x0037
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C1061l.mo11859a():boolean");
    }

    public synchronized void close() {
        super.close();
        if (this.f1431b != null) {
            this.f1431b.close();
            this.f1431b = null;
        }
    }

    public synchronized SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    public synchronized SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
    }

    /* renamed from: a */
    public void mo11858a(String str) {
        getWritableDatabase().execSQL(str);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.String[], android.database.Cursor] */
    /* renamed from: b */
    public final int mo11860b() {
        Cursor cursor = 0;
        try {
            SQLiteDatabase sQLiteDatabase = this.f1431b;
            cursor = sQLiteDatabase.rawQuery("SELECT COUNT(*) FROM " + this.f1430a, cursor);
            if (cursor != null && cursor.moveToNext()) {
                return cursor.getInt(0);
            }
            if (cursor != null) {
                cursor.close();
            }
            return 0;
        } finally {
            if (cursor != 0) {
                cursor.close();
            }
        }
    }

    /* renamed from: a */
    public Cursor mo11857a(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) {
        return this.f1431b.query(this.f1430a, strArr, str, strArr2, str2, str3, str4, str5);
    }

    /* renamed from: a */
    public long mo11856a(String str, ContentValues contentValues) {
        return this.f1431b.insert(this.f1430a, str, contentValues);
    }

    /* renamed from: a */
    public int mo11855a(String str, String[] strArr) {
        return this.f1431b.delete(this.f1430a, str, strArr);
    }
}
