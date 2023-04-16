package org.litepal.util;

import android.content.SharedPreferences;
import android.text.TextUtils;
import org.litepal.LitePalApplication;
import org.litepal.util.Const;

public class SharedUtil {
    private static final String LITEPAL_PREPS = "litepal_prefs";
    private static final String VERSION = "litepal_version";

    public static void updateVersion(String str, int i) {
        SharedPreferences.Editor edit = LitePalApplication.getContext().getSharedPreferences(LITEPAL_PREPS, 0).edit();
        if (TextUtils.isEmpty(str)) {
            edit.putInt(VERSION, i);
        } else {
            if (str.endsWith(Const.Config.DB_NAME_SUFFIX)) {
                str = str.replace(Const.Config.DB_NAME_SUFFIX, "");
            }
            edit.putInt("litepal_version_" + str, i);
        }
        edit.apply();
    }

    public static int getLastVersion(String str) {
        SharedPreferences sharedPreferences = LitePalApplication.getContext().getSharedPreferences(LITEPAL_PREPS, 0);
        if (TextUtils.isEmpty(str)) {
            return sharedPreferences.getInt(VERSION, 0);
        }
        if (str.endsWith(Const.Config.DB_NAME_SUFFIX)) {
            str = str.replace(Const.Config.DB_NAME_SUFFIX, "");
        }
        return sharedPreferences.getInt("litepal_version_" + str, 0);
    }

    public static void removeVersion(String str) {
        SharedPreferences.Editor edit = LitePalApplication.getContext().getSharedPreferences(LITEPAL_PREPS, 0).edit();
        if (TextUtils.isEmpty(str)) {
            edit.remove(VERSION);
        } else {
            if (str.endsWith(Const.Config.DB_NAME_SUFFIX)) {
                str = str.replace(Const.Config.DB_NAME_SUFFIX, "");
            }
            edit.remove("litepal_version_" + str);
        }
        edit.apply();
    }
}
