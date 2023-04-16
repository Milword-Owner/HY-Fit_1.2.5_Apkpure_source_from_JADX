package com.hjq.language;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Locale;

final class LanguagesSave {
    private static final String KEY_COUNTRY = "key_country";
    private static final String KEY_LANGUAGE = "key_language";
    private static Locale sCurrentLanguage = null;
    private static String sSharedPreferencesName = "language_setting";

    LanguagesSave() {
    }

    static void setSharedPreferencesName(String str) {
        sSharedPreferencesName = str;
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(sSharedPreferencesName, 0);
    }

    static void saveAppLanguage(Context context, Locale locale) {
        sCurrentLanguage = locale;
        getSharedPreferences(context).edit().putString(KEY_LANGUAGE, locale.getLanguage()).putString(KEY_COUNTRY, locale.getCountry()).apply();
    }

    static Locale getAppLanguage(Context context) {
        if (sCurrentLanguage == null) {
            String string = getSharedPreferences(context).getString(KEY_LANGUAGE, (String) null);
            String string2 = getSharedPreferences(context).getString(KEY_COUNTRY, (String) null);
            if (string == null || "".equals(string)) {
                sCurrentLanguage = Locale.getDefault();
            } else {
                sCurrentLanguage = new Locale(string, string2);
            }
        }
        return sCurrentLanguage;
    }

    static void clearLanguage(Context context) {
        sCurrentLanguage = LanguagesChange.getSystemLanguage();
        getSharedPreferences(context).edit().remove(KEY_LANGUAGE).remove(KEY_COUNTRY).apply();
    }
}
