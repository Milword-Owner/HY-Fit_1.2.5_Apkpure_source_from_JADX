package com.mob.mobapm.p031e;

import com.mob.MobSDK;
import com.mob.mobapm.p030d.C2373a;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.DeviceHelper;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.mob.mobapm.e.c */
public class C2377c {
    /* renamed from: a */
    public static Map<String, Object> m2817a(StackTraceElement[] stackTraceElementArr) {
        HashMap hashMap = new HashMap();
        try {
            StringBuilder sb = new StringBuilder();
            if (stackTraceElementArr != null && stackTraceElementArr.length > 0) {
                int i = 0;
                int i2 = 0;
                while (true) {
                    if (i >= stackTraceElementArr.length - 1) {
                        break;
                    } else if (i2 >= 100) {
                        sb.append("\t... ");
                        sb.append(stackTraceElementArr.length - i);
                        sb.append(" more");
                        break;
                    } else {
                        i2++;
                        sb.append("\tat " + stackTraceElementArr[i] + "\n");
                        i++;
                        if (stackTraceElementArr[i].toString().contains(DeviceHelper.getInstance(MobSDK.getContext()).getPackageName()) && !hashMap.containsKey("desc")) {
                            hashMap.put("desc", stackTraceElementArr[i].toString());
                        }
                    }
                }
            }
            hashMap.put("stack", sb);
        } catch (Throwable th) {
            NLog a = C2373a.m2807a();
            a.mo29768d("APM: get anr stack error: " + th, new Object[0]);
        }
        return hashMap;
    }

    /* renamed from: a */
    public static String m2816a(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            printWriter.flush();
            String stringWriter2 = stringWriter.toString();
            stringWriter.close();
            return stringWriter2;
        } catch (Throwable th2) {
            if (th2 instanceof OutOfMemoryError) {
                return "getStackTraceString oom";
            }
            return th2.getMessage();
        }
    }
}
