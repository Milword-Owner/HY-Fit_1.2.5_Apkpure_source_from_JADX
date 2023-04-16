package com.mob.tools.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SQLiteHelper {
    public static SingleTableDB getDatabase(Context context, String str) {
        return getDatabase(context.getDatabasePath(str).getPath(), str);
    }

    public static SingleTableDB getDatabase(String str, String str2) {
        return new SingleTableDB(str, str2);
    }

    public static long insert(SingleTableDB singleTableDB, ContentValues contentValues) throws Throwable {
        singleTableDB.open();
        return singleTableDB.f2353db.replace(singleTableDB.getName(), (String) null, contentValues);
    }

    public static int delete(SingleTableDB singleTableDB, String str, String[] strArr) throws Throwable {
        singleTableDB.open();
        return singleTableDB.f2353db.delete(singleTableDB.getName(), str, strArr);
    }

    public static int update(SingleTableDB singleTableDB, ContentValues contentValues, String str, String[] strArr) throws Throwable {
        singleTableDB.open();
        return singleTableDB.f2353db.update(singleTableDB.getName(), contentValues, str, strArr);
    }

    public static Cursor query(SingleTableDB singleTableDB, String[] strArr, String str, String[] strArr2, String str2) throws Throwable {
        singleTableDB.open();
        return singleTableDB.f2353db.query(singleTableDB.getName(), strArr, str, strArr2, (String) null, (String) null, str2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0020, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        com.mob.tools.utils.SQLiteHelper.SingleTableDB.access$300(r1).endTransaction();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void execSQL(com.mob.tools.utils.SQLiteHelper.SingleTableDB r1, java.lang.String r2) throws java.lang.Throwable {
        /*
            r1.open()
            android.database.sqlite.SQLiteDatabase r0 = r1.f2353db
            r0.beginTransaction()
            android.database.sqlite.SQLiteDatabase r0 = r1.f2353db     // Catch:{ Throwable -> 0x0022 }
            r0.execSQL(r2)     // Catch:{ Throwable -> 0x0022 }
            android.database.sqlite.SQLiteDatabase r2 = r1.f2353db     // Catch:{ Throwable -> 0x0022 }
            r2.setTransactionSuccessful()     // Catch:{ Throwable -> 0x0022 }
            android.database.sqlite.SQLiteDatabase r1 = r1.f2353db
            r1.endTransaction()
            return
        L_0x0020:
            r2 = move-exception
            goto L_0x0024
        L_0x0022:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0020 }
        L_0x0024:
            android.database.sqlite.SQLiteDatabase r1 = r1.f2353db
            r1.endTransaction()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.SQLiteHelper.execSQL(com.mob.tools.utils.SQLiteHelper$SingleTableDB, java.lang.String):void");
    }

    public static Cursor rawQuery(SingleTableDB singleTableDB, String str, String[] strArr) throws Throwable {
        singleTableDB.open();
        return singleTableDB.f2353db.rawQuery(str, strArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        if (r0 != null) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        throw r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        r9 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getSize(com.mob.tools.utils.SQLiteHelper.SingleTableDB r9) throws java.lang.Throwable {
        /*
            r9.open()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r9.f2353db     // Catch:{ Throwable -> 0x0026 }
            java.lang.String r2 = r9.getName()     // Catch:{ Throwable -> 0x0026 }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ Throwable -> 0x0026 }
            if (r0 != 0) goto L_0x001a
            r9 = 0
            goto L_0x001e
        L_0x001a:
            int r9 = r0.getCount()     // Catch:{ Throwable -> 0x0026 }
        L_0x001e:
            if (r0 == 0) goto L_0x0023
            r0.close()
        L_0x0023:
            return r9
        L_0x0024:
            r9 = move-exception
            goto L_0x0028
        L_0x0026:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0024 }
        L_0x0028:
            if (r0 == 0) goto L_0x002d
            r0.close()
        L_0x002d:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.SQLiteHelper.getSize(com.mob.tools.utils.SQLiteHelper$SingleTableDB):int");
    }

    public static void close(SingleTableDB singleTableDB) {
        singleTableDB.close();
    }

    public static class SingleTableDB {
        /* access modifiers changed from: private */

        /* renamed from: db */
        public SQLiteDatabase f2353db;
        private HashMap<String, Boolean> fieldLimits;
        private LinkedHashMap<String, String> fieldTypes;
        private String name;
        private String path;
        private String primary;
        private boolean primaryAutoincrement;

        private SingleTableDB(String str, String str2) {
            this.path = str;
            this.name = str2;
            this.fieldTypes = new LinkedHashMap<>();
            this.fieldLimits = new HashMap<>();
        }

        public void addField(String str, String str2, boolean z) {
            if (this.f2353db == null) {
                this.fieldTypes.put(str, str2);
                this.fieldLimits.put(str, Boolean.valueOf(z));
            }
        }

        public void setPrimary(String str, boolean z) {
            this.primary = str;
            this.primaryAutoincrement = z;
        }

        /* access modifiers changed from: private */
        public void open() {
            boolean z;
            File file = new File(this.path);
            if (this.f2353db != null && !file.exists()) {
                this.f2353db.close();
                File parentFile = file.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                this.f2353db = null;
            }
            if (this.f2353db == null) {
                this.f2353db = SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase.CursorFactory) null);
                Cursor query = this.f2353db.query("sqlite_master", (String[]) null, "type=? and name=?", new String[]{"table", this.name}, (String) null, (String) null, (String) null);
                if (query != null) {
                    z = query.getCount() <= 0;
                    query.close();
                } else {
                    z = true;
                }
                if (z) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("create table  ");
                    sb.append(this.name);
                    sb.append("(");
                    for (Map.Entry next : this.fieldTypes.entrySet()) {
                        String str = (String) next.getKey();
                        String str2 = (String) next.getValue();
                        boolean booleanValue = this.fieldLimits.get(str).booleanValue();
                        boolean equals = str.equals(this.primary);
                        boolean z2 = equals ? this.primaryAutoincrement : false;
                        sb.append(str);
                        sb.append(" ");
                        sb.append(str2);
                        String str3 = "";
                        sb.append(booleanValue ? " not null" : str3);
                        if (equals) {
                            str3 = " primary key";
                        }
                        sb.append(str3);
                        sb.append(z2 ? " autoincrement," : ",");
                    }
                    sb.replace(sb.length() - 1, sb.length(), ");");
                    this.f2353db.execSQL(sb.toString());
                }
            }
        }

        /* access modifiers changed from: private */
        public void close() {
            SQLiteDatabase sQLiteDatabase = this.f2353db;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
                this.f2353db = null;
            }
        }

        /* access modifiers changed from: private */
        public String getName() {
            return this.name;
        }
    }
}
