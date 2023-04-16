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

/* renamed from: com.mob.mobapm.b.e */
public class C2351e {

    /* renamed from: c */
    private static C2351e f2234c;

    /* renamed from: a */
    private C2350d f2235a;

    /* renamed from: b */
    private Hashon f2236b = new Hashon();

    private C2351e(Context context) {
        this.f2235a = new C2350d(context);
    }

    /* renamed from: a */
    public static C2351e m2744a(Context context) {
        if (f2234c == null) {
            synchronized (C2351e.class) {
                if (f2234c == null) {
                    f2234c = new C2351e(context);
                }
            }
        }
        return f2234c;
    }

    /* renamed from: a */
    public void mo29178a(ContentValues contentValues) {
        SQLiteDatabase writableDatabase = this.f2235a.getWritableDatabase();
        try {
            long insert = writableDatabase.insert("Transactions", (String) null, contentValues);
            NLog a = C2373a.m2807a();
            a.mo29768d("APM: insert result: " + insert, new Object[0]);
        } catch (Throwable th) {
            writableDatabase.close();
            throw th;
        }
        writableDatabase.close();
    }

    /* renamed from: a */
    public void mo29177a() {
        m2745a("delete from Transactions");
    }

    /* renamed from: a */
    public List<HashMap<String, Object>> mo29176a(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4) {
        SQLiteDatabase readableDatabase = this.f2235a.getReadableDatabase();
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = readableDatabase.query("Transactions", strArr, str, strArr2, str2, str3, str4);
            while (query.moveToNext()) {
                query.getInt(query.getColumnIndex("Id"));
                HashMap fromJson = this.f2236b.fromJson(this.f2236b.fromObject(C2383h.m2825a().mo29282e(query.getString(query.getColumnIndex("trans")))));
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
            a.mo29771e("TransactionDao", "queryDatas" + th.toString());
        } finally {
            readableDatabase.close();
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m2745a(String str) {
        SQLiteDatabase writableDatabase = this.f2235a.getWritableDatabase();
        writableDatabase.execSQL(str);
        writableDatabase.close();
    }
}
