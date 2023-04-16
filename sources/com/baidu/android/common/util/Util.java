package com.baidu.android.common.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.baidu.android.common.security.MD5Util;

public final class Util {
    private Util() {
    }

    public static String toMd5(byte[] bArr, boolean z) {
        return MD5Util.toMd5(bArr, z);
    }

    public static String toHexString(byte[] bArr, String str, boolean z) {
        return MD5Util.toHexString(bArr, str, z);
    }

    public static boolean hasOtherServiceRuninMyPid(Context context, String str) {
        for (ActivityManager.RunningServiceInfo next : ((ActivityManager) context.getApplicationContext().getSystemService("activity")).getRunningServices(100)) {
            if (next.pid == Process.myPid() && !TextUtils.equals(next.service.getClassName(), str)) {
                return true;
            }
        }
        return false;
    }
}
