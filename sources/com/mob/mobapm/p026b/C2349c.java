package com.mob.mobapm.p026b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.mob.mobapm.p030d.C2373a;
import com.mob.mobapm.p031e.C2383h;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.Hashon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.mob.mobapm.b.c */
public class C2349c {

    /* renamed from: c */
    private static C2349c f2231c;

    /* renamed from: a */
    private C2348b f2232a;

    /* renamed from: b */
    private Hashon f2233b = new Hashon();

    private C2349c(Context context) {
        this.f2232a = new C2348b(context);
    }

    /* renamed from: a */
    public static C2349c m2739a(Context context) {
        if (f2231c == null) {
            synchronized (C2349c.class) {
                if (f2231c == null) {
                    f2231c = new C2349c(context);
                }
            }
        }
        return f2231c;
    }

    /* renamed from: a */
    public void mo29173a(ContentValues contentValues) {
        SQLiteDatabase writableDatabase = this.f2232a.getWritableDatabase();
        try {
            long insert = writableDatabase.insert("Sockets", (String) null, contentValues);
            NLog a = C2373a.m2807a();
            a.mo29768d("APM: insert socket result: " + insert, new Object[0]);
        } catch (Throwable th) {
            writableDatabase.close();
            throw th;
        }
        writableDatabase.close();
    }

    /* renamed from: a */
    public void mo29172a() {
        m2740a("delete from Sockets");
    }

    /* renamed from: a */
    public List<HashMap<String, Object>> mo29171a(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4) {
        SQLiteDatabase readableDatabase = this.f2232a.getReadableDatabase();
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = readableDatabase.query(true, "Sockets", strArr, str, strArr2, str2, str3, str4, "");
            while (query.moveToNext()) {
                HashMap fromJson = this.f2233b.fromJson(this.f2233b.fromObject(C2383h.m2825a().mo29280c(query.getString(query.getColumnIndex(query.getColumnName(1))))));
                if (fromJson != null && !fromJson.isEmpty()) {
                    fromJson.remove("transStatus");
                    arrayList.add(fromJson);
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th) {
            NLog a = C2373a.m2807a();
            a.mo29771e("SocketDao", "queryDatas" + th.toString());
        } finally {
            readableDatabase.close();
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m2740a(String str) {
        SQLiteDatabase writableDatabase = this.f2232a.getWritableDatabase();
        writableDatabase.execSQL(str);
        writableDatabase.close();
    }
}
