package org.litepal;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import org.litepal.exceptions.GlobalException;

public class LitePalApplication extends Application {
    @SuppressLint({"StaticFieldLeak"})
    public static Context sContext;
    public static Handler sHandler = new Handler(Looper.getMainLooper());

    public LitePalApplication() {
        sContext = this;
    }

    public static Context getContext() {
        Context context = sContext;
        if (context != null) {
            return context;
        }
        throw new GlobalException(GlobalException.APPLICATION_CONTEXT_IS_NULL);
    }
}
