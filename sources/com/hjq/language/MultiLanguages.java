package com.hjq.language;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import java.util.Locale;

public final class MultiLanguages {
    public static void init(Application application) {
        LanguagesChange.register(application);
    }

    public static Context attach(Context context) {
        return !LanguagesUtils.equalsLanguages(context, getAppLanguage(context)) ? LanguagesUtils.updateLanguages(context, getAppLanguage(context)) : context;
    }

    public static Locale getAppLanguage(Context context) {
        return LanguagesSave.getAppLanguage(context);
    }

    public static boolean setAppLanguage(Context context, Locale locale) {
        LanguagesSave.saveAppLanguage(context, locale);
        if (LanguagesUtils.equalsLanguages(context, locale)) {
            return false;
        }
        LanguagesUtils.updateLanguages(context, locale);
        if (context == context.getApplicationContext()) {
            return true;
        }
        LanguagesUtils.updateLanguages(context.getApplicationContext(), locale);
        return true;
    }

    public static Locale getSystemLanguage() {
        return LanguagesChange.getSystemLanguage();
    }

    public static boolean setSystemLanguage(Context context) {
        LanguagesSave.clearLanguage(context);
        if (LanguagesUtils.equalsLanguages(context, getSystemLanguage())) {
            return false;
        }
        LanguagesUtils.updateLanguages(context, getSystemLanguage());
        return true;
    }

    public static boolean equalsLanguage(Locale locale, Locale locale2) {
        return locale.getLanguage().equals(locale2.getLanguage());
    }

    public static boolean equalsCountry(Locale locale, Locale locale2) {
        return equalsLanguage(locale, locale2) && locale.getCountry().equals(locale2.getCountry());
    }

    public static String getLanguageString(Context context, Locale locale, int i) {
        return getLanguageResources(context, locale).getString(i);
    }

    public static Resources getLanguageResources(Context context, Locale locale) {
        return LanguagesUtils.getLanguageResources(context, locale);
    }

    public static void setSharedPreferencesName(String str) {
        LanguagesSave.setSharedPreferencesName(str);
    }
}
