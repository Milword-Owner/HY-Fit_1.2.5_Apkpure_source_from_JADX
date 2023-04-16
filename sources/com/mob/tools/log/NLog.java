package com.mob.tools.log;

import android.content.Context;
import android.os.Process;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;

public abstract class NLog {
    private static LogCollector defaultCollector;
    private static boolean disable;
    private static HashMap<String, NLog> loggers = new HashMap<>();
    private LogCollector collector;

    public static void setContext(Context context) {
    }

    /* access modifiers changed from: protected */
    public abstract String getSDKTag();

    public static void disable() {
        disable = true;
    }

    static {
        MobUncaughtExceptionHandler.register();
    }

    public static void setCollector(String str, LogCollector logCollector) {
        getInstance(str).setCollector(logCollector);
    }

    protected static NLog getInstanceForSDK(String str, boolean z) {
        return getInstance(str);
    }

    public static NLog getInstance(final String str) {
        NLog nLog;
        synchronized (loggers) {
            nLog = loggers.get(str);
            if (nLog == null) {
                nLog = new NLog() {
                    /* access modifiers changed from: protected */
                    public String getSDKTag() {
                        return str;
                    }
                };
                loggers.put(str, nLog);
            }
        }
        return nLog;
    }

    public static <Collector extends LogCollector> Collector setDefaultCollector(Collector collector2) {
        defaultCollector = collector2;
        return collector2;
    }

    public NLog setCollector(LogCollector logCollector) {
        this.collector = logCollector;
        return this;
    }

    /* renamed from: v */
    public final int mo29783v(Throwable th) {
        if (disable) {
            return 0;
        }
        return println(2, 0, getStackTraceString(th));
    }

    /* renamed from: v */
    public final int mo29782v(Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return println(2, 0, obj2);
    }

    /* renamed from: v */
    public final int mo29784v(Throwable th, Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        StringBuilder sb = new StringBuilder();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        sb.append(obj2);
        sb.append(10);
        sb.append(getStackTraceString(th));
        return println(2, 0, sb.toString());
    }

    /* renamed from: d */
    public final int mo29769d(Throwable th) {
        if (disable) {
            return 0;
        }
        return println(3, 0, getStackTraceString(th));
    }

    /* renamed from: d */
    public final int mo29768d(Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return println(3, 0, obj2);
    }

    /* renamed from: d */
    public final int mo29770d(Throwable th, Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        StringBuilder sb = new StringBuilder();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        sb.append(obj2);
        sb.append(10);
        sb.append(getStackTraceString(th));
        return println(3, 0, sb.toString());
    }

    /* renamed from: i */
    public final int mo29776i(Throwable th) {
        if (disable) {
            return 0;
        }
        return println(4, 0, getStackTraceString(th));
    }

    /* renamed from: i */
    public final int mo29775i(Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return println(4, 0, obj2);
    }

    /* renamed from: i */
    public final int mo29777i(Throwable th, Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        StringBuilder sb = new StringBuilder();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        sb.append(obj2);
        sb.append(10);
        sb.append(getStackTraceString(th));
        return println(4, 0, sb.toString());
    }

    /* renamed from: w */
    public final int mo29787w(Throwable th) {
        if (disable) {
            return 0;
        }
        return println(5, 0, getStackTraceString(th));
    }

    /* renamed from: w */
    public final int mo29785w(Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return println(5, 0, obj2);
    }

    /* renamed from: w */
    public final int mo29788w(Throwable th, Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        StringBuilder sb = new StringBuilder();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        sb.append(obj2);
        sb.append(10);
        sb.append(getStackTraceString(th));
        return println(5, 0, sb.toString());
    }

    /* renamed from: w */
    public final int mo29786w(String str) {
        if (disable) {
            return 0;
        }
        return println(5, 0, str);
    }

    /* renamed from: e */
    public final int mo29772e(Throwable th) {
        if (disable) {
            return 0;
        }
        return println(6, 0, getStackTraceString(th));
    }

    /* renamed from: e */
    public final int mo29771e(Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return println(6, 0, obj2);
    }

    /* renamed from: e */
    public final int mo29773e(Throwable th, Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        StringBuilder sb = new StringBuilder();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        sb.append(obj2);
        sb.append(10);
        sb.append(getStackTraceString(th));
        return println(6, 0, sb.toString());
    }

    public int wtf(Throwable th) {
        if (disable) {
            return 0;
        }
        return println(6, 0, getStackTraceString(th));
    }

    public final int crash(Throwable th) {
        if (disable) {
            return 0;
        }
        return println(6, 1, getStackTraceString(th));
    }

    public final int sdkErr(Throwable th) {
        if (disable) {
            return 0;
        }
        return println(6, 3, getStackTraceString(th));
    }

    public final int sdkErr(Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return println(6, 3, obj2);
    }

    public final int sdkErr(Throwable th, Object obj, Object... objArr) {
        if (disable) {
            return 0;
        }
        String obj2 = obj.toString();
        StringBuilder sb = new StringBuilder();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        sb.append(obj2);
        sb.append(10);
        sb.append(getStackTraceString(th));
        return println(6, 3, sb.toString());
    }

    private String getStackTraceString(Throwable th) {
        if (th == null) {
            return "";
        }
        Throwable th2 = th;
        while (th2 != null) {
            try {
                if (th2 instanceof UnknownHostException) {
                    return "";
                }
                th2 = th2.getCause();
            } catch (Throwable th3) {
                if (th3 instanceof OutOfMemoryError) {
                    return "getStackTraceString oom";
                }
                return th3.getMessage();
            }
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        String stringWriter2 = stringWriter.toString();
        stringWriter.close();
        return stringWriter2;
    }

    private int println(int i, int i2, String str) {
        try {
            str = Process.myPid() + "-" + Process.myTid() + "(" + Thread.currentThread().getName() + ") " + str;
        } catch (Throwable unused) {
        }
        String str2 = str;
        String sDKTag = getSDKTag();
        LogCollector logCollector = this.collector;
        if (logCollector == null) {
            logCollector = defaultCollector;
        }
        LogCollector logCollector2 = logCollector;
        if (logCollector2 == null) {
            return 0;
        }
        logCollector2.log(sDKTag, i, i2, (String) null, str2);
        return 0;
    }
}
