package com.mob.mobapm.p026b;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* renamed from: com.mob.mobapm.b.b */
public class C2348b extends SQLiteOpenHelper {
    public C2348b(Context context) {
        super(context, "mob_apm_socket.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists Sockets (Id integer primary key, sockets text)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS Sockets");
        onCreate(sQLiteDatabase);
    }
}
