package com.hjq.language;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import java.util.Locale;

final class LanguagesUtils {
    LanguagesUtils() {
    }

    static boolean equalsLanguages(Context context, Locale locale) {
        Configuration configuration = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= 24) {
            return configuration.getLocales().get(0).equals(locale);
        }
        return configuration.locale.equals(locale);
    }

    static Context updateLanguages(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = new Configuration(resources.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            if (Build.VERSION.SDK_INT >= 24) {
                LocaleList localeList = new LocaleList(new Locale[]{locale});
                LocaleList.setDefault(localeList);
                configuration.setLocales(localeList);
            } else {
                configuration.setLocale(locale);
            }
            context = context.createConfigurationContext(configuration);
        } else {
            configuration.locale = locale;
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        Locale.setDefault(locale);
        return context;
    }

    static Resources getLanguageResources(Context context, Locale locale) {
        Configuration configuration = new Configuration();
        if (Build.VERSION.SDK_INT >= 17) {
            if (Build.VERSION.SDK_INT >= 24) {
                LocaleList localeList = new LocaleList(new Locale[]{locale});
                LocaleList.setDefault(localeList);
                configuration.setLocales(localeList);
            } else {
                configuration.setLocale(locale);
            }
            return context.createConfigurationContext(configuration).getResources();
        }
        configuration.locale = locale;
        return new Resources(context.getAssets(), context.getResources().getDisplayMetrics(), configuration);
    }
}
