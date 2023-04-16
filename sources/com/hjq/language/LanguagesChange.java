package com.hjq.language;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import java.util.Locale;

final class LanguagesChange implements ComponentCallbacks {
    private static Locale sSystemLanguage;

    public void onLowMemory() {
    }

    LanguagesChange() {
    }

    static {
        if (Build.VERSION.SDK_INT >= 24) {
            sSystemLanguage = Resources.getSystem().getConfiguration().getLocales().get(0);
        } else {
            sSystemLanguage = Resources.getSystem().getConfiguration().locale;
        }
    }

    static Locale getSystemLanguage() {
        return sSystemLanguage;
    }

    static void register(Context context) {
        context.registerComponentCallbacks(new LanguagesChange());
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 24) {
            sSystemLanguage = configuration.getLocales().get(0);
        } else {
            sSystemLanguage = configuration.locale;
        }
    }
}
