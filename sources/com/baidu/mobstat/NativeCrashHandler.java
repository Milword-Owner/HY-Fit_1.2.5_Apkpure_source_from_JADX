package com.baidu.mobstat;

import android.content.Context;
import java.io.File;

public final class NativeCrashHandler {

    /* renamed from: a */
    private static boolean f890a = true;

    /* renamed from: b */
    private static Context f891b;

    private static native void nativeException();

    private static native void nativeInit(String str);

    private static native void nativeProcess(String str);

    private static native void nativeUnint();

    static {
        try {
            System.loadLibrary("crash_analysis");
        } catch (Throwable unused) {
        }
    }

    private NativeCrashHandler() {
    }

    public static void doNativeCrash() {
        if (f890a) {
            try {
                nativeException();
            } catch (Throwable unused) {
            }
        }
    }

    public static void init(Context context) {
        if (context != null) {
            f891b = context;
            if (f890a) {
                File cacheDir = context.getCacheDir();
                if (cacheDir.exists() && cacheDir.isDirectory()) {
                    try {
                        nativeInit(cacheDir.getAbsolutePath());
                    } catch (Throwable unused) {
                    }
                }
            }
        }
    }

    public static void uninit() {
        if (f890a) {
            try {
                nativeUnint();
            } catch (Throwable unused) {
            }
        }
    }

    public static void process(String str) {
        if (str != null && str.length() != 0 && f890a) {
            File file = new File(str);
            if (file.exists() && file.isFile()) {
                try {
                    nativeProcess(str);
                } catch (Throwable unused) {
                }
            }
        }
    }

    public static void onCrashCallbackFromNative(String str) {
        ExceptionAnalysis.getInstance().saveCrashInfo(f891b, System.currentTimeMillis(), str, "NativeException", 1, 0);
    }
}
