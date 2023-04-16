package com.mob.tools.log;

import com.mob.tools.MobLog;
import java.lang.Thread;

public class MobUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static boolean disable = false;
    private static boolean isDebug = false;
    private static Thread.UncaughtExceptionHandler oriHandler;

    public static void disable() {
        disable = true;
    }

    public static void closeLog() {
        isDebug = false;
    }

    public static void openLog() {
        isDebug = true;
    }

    public static void register() {
        if (!disable) {
            oriHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(new MobUncaughtExceptionHandler());
        }
    }

    private MobUncaughtExceptionHandler() {
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (isDebug) {
            MobLog.getInstance().wtf(th);
        }
        MobLog.getInstance().crash(th);
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = oriHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }
}
