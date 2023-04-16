package com.mob.mobapm.p026b;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* renamed from: com.mob.mobapm.b.d */
public class C2350d extends SQLiteOpenHelper {
    public C2350d(Context context) {
        super(context, "mob_apm.db", (SQLiteDatabase.CursorFactory) null, 2);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists Transactions (Id integer primary key, trans text, tag text)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS Transactions");
        onCreate(sQLiteDatabase);
    }
}
