package org.litepal.tablemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.litepal.LitePalApplication;
import org.litepal.Operator;
import org.litepal.parser.LitePalAttr;
import org.litepal.tablemanager.callback.DatabaseListener;
import org.litepal.util.SharedUtil;

class LitePalOpenHelper extends SQLiteOpenHelper {
    public static final String TAG = "LitePalHelper";

    LitePalOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
    }

    LitePalOpenHelper(String str, int i) {
        this(LitePalApplication.getContext(), str, (SQLiteDatabase.CursorFactory) null, i);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Generator.create(sQLiteDatabase);
        final DatabaseListener dBListener = Operator.getDBListener();
        if (dBListener != null) {
            LitePalApplication.sHandler.post(new Runnable() {
                public void run() {
                    dBListener.onCreate();
                }
            });
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, final int i, final int i2) {
        Generator.upgrade(sQLiteDatabase);
        SharedUtil.updateVersion(LitePalAttr.getInstance().getExtraKeyName(), i2);
        final DatabaseListener dBListener = Operator.getDBListener();
        if (dBListener != null) {
            LitePalApplication.sHandler.post(new Runnable() {
                public void run() {
                    dBListener.onUpgrade(i, i2);
                }
            });
        }
    }
}
