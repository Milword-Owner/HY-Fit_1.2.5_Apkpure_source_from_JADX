package org.litepal.tablemanager;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.text.TextUtils;
import com.baidubce.BceConfig;
import java.io.File;
import org.litepal.LitePalApplication;
import org.litepal.parser.LitePalAttr;

public class Connector {
    private static LitePalOpenHelper mLitePalHelper;

    public static synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase writableDatabase;
        synchronized (Connector.class) {
            writableDatabase = buildConnection().getWritableDatabase();
        }
        return writableDatabase;
    }

    public static SQLiteDatabase getDatabase() {
        return getWritableDatabase();
    }

    private static LitePalOpenHelper buildConnection() {
        LitePalAttr instance = LitePalAttr.getInstance();
        instance.checkSelfValid();
        if (mLitePalHelper == null) {
            String dbName = instance.getDbName();
            if ("external".equalsIgnoreCase(instance.getStorage())) {
                dbName = LitePalApplication.getContext().getExternalFilesDir("") + "/databases/" + dbName;
            } else if (!"internal".equalsIgnoreCase(instance.getStorage()) && !TextUtils.isEmpty(instance.getStorage())) {
                String replace = (Environment.getExternalStorageDirectory().getPath() + BceConfig.BOS_DELIMITER + instance.getStorage()).replace("//", BceConfig.BOS_DELIMITER);
                File file = new File(replace);
                if (!file.exists()) {
                    file.mkdirs();
                }
                dbName = replace + BceConfig.BOS_DELIMITER + dbName;
            }
            mLitePalHelper = new LitePalOpenHelper(dbName, instance.getVersion());
        }
        return mLitePalHelper;
    }

    public static void clearLitePalOpenHelperInstance() {
        LitePalOpenHelper litePalOpenHelper = mLitePalHelper;
        if (litePalOpenHelper != null) {
            litePalOpenHelper.getWritableDatabase().close();
            mLitePalHelper = null;
        }
    }
}
