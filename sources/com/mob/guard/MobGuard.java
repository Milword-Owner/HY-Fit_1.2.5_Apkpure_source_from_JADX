package com.mob.guard;

import android.os.Build;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.GuardMsg;
import com.mob.tools.proguard.ClassKeeper;
import java.io.File;

public class MobGuard implements ClassKeeper {
    public static String GDF = null;
    public static final String SDK_TAG = "MOBGUARD";
    public static final int SDK_VERSION_CODE;
    public static final String SDK_VERSION_NAME = "1.3.1";

    public static String getSdkTag() {
        return SDK_TAG;
    }

    static {
        int i;
        Throwable th;
        int i2 = 0;
        try {
            String[] split = SDK_VERSION_NAME.split("\\.");
            int length = split.length;
            if (length > 3) {
                length = 3;
            }
            i = 0;
            while (i2 < length) {
                try {
                    i = Integer.parseInt(split[i2]) + (i * 100);
                    i2++;
                } catch (Throwable th2) {
                    th = th2;
                    C2335e.m2675b().mo29769d(th);
                    SDK_VERSION_CODE = i;
                }
            }
            if (Build.VERSION.SDK_INT >= 8) {
                new File(MobSDK.getContext().getExternalFilesDir((String) null) + "/.fgd").delete();
            }
            C2328b.f2193a.mo29144a();
        } catch (Throwable th3) {
            th = th3;
            i = 0;
            C2335e.m2675b().mo29769d(th);
            SDK_VERSION_CODE = i;
        }
        SDK_VERSION_CODE = i;
    }

    public static String getGuardId() {
        try {
            if ((!TextUtils.isEmpty(C2341i.m2701d()) && C2341i.m2701d().equals("0")) || TextUtils.isEmpty(C2341i.m2692a())) {
                return null;
            }
            GuardMsg guardMsg = new GuardMsg();
            guardMsg.toObj(C2341i.m2692a(), guardMsg);
            return guardMsg.getId();
        } catch (Throwable th) {
            C2335e.m2675b().mo29769d(th);
            return null;
        }
    }
}
