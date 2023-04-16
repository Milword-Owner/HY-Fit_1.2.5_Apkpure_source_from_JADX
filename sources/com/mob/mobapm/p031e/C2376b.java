package com.mob.mobapm.p031e;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import com.mob.mobapm.p030d.C2373a;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.ReflectHelper;
import java.util.List;

/* renamed from: com.mob.mobapm.e.b */
public class C2376b {
    /* renamed from: a */
    public static boolean m2815a(Context context) {
        String str = null;
        try {
            List<ActivityManager.RunningAppProcessInfo> list = (List) ReflectHelper.invokeInstanceMethod((ActivityManager) context.getSystemService("activity"), "getRunningAppProcesses", new Object[0]);
            if (list == null) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : list) {
                if (runningAppProcessInfo.pid == Process.myPid()) {
                    str = runningAppProcessInfo.processName;
                }
            }
            boolean z = str != null && str.equalsIgnoreCase(context.getPackageName());
            NLog a = C2373a.m2807a();
            a.mo29768d("APM: isInMainProcess process:" + str + ",res:" + z, new Object[0]);
            return z;
        } catch (Throwable th) {
            C2373a.m2807a().mo29772e(th);
        }
    }
}
