package com.baidu.mobstat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;

/* renamed from: com.baidu.mobstat.p */
class C1065p extends C1054j {
    public C1065p(Context context) {
        super(context, "app_list3", "Create table if not exists app_list3(_id Integer primary key AUTOINCREMENT,time VARCHAR(50),content TEXT);");
    }

    /* renamed from: a */
    public ArrayList<C1053i> mo11841a(int i, int i2) {
        Cursor a = mo11839a("time", i, i2);
        ArrayList<C1053i> a2 = m1757a(a);
        if (a != null) {
            a.close();
        }
        return a2;
    }

    /* renamed from: a */
    public long mo11838a(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("time", str);
        contentValues.put("content", str2);
        return mo11837a(contentValues);
    }

    /* renamed from: b */
    public boolean mo11845b(long j) {
        return mo11843a(j);
    }

    /* renamed from: a */
    private ArrayList<C1053i> m1757a(Cursor cursor) {
        ArrayList<C1053i> arrayList = new ArrayList<>();
        if (cursor == null || cursor.getCount() == 0) {
            return arrayList;
        }
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("time");
        int columnIndex3 = cursor.getColumnIndex("content");
        while (cursor.moveToNext()) {
            arrayList.add(new C1053i(cursor.getLong(columnIndex), cursor.getString(columnIndex2), cursor.getString(columnIndex3)));
        }
        return arrayList;
    }
}
