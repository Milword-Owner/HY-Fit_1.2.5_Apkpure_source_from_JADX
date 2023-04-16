package com.mob;

import android.app.Activity;
import android.os.Build;
import com.mob.tools.utils.ReflectHelper;

public class MobTranUpActivity extends Activity {
    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(10:5|(1:9)|10|(1:12)|13|14|15|16|17|19) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0088 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r4) {
        /*
            r3 = this;
            super.onCreate(r4)
            android.content.Intent r4 = r3.getIntent()     // Catch:{ Throwable -> 0x008b }
            if (r4 != 0) goto L_0x000a
            return
        L_0x000a:
            android.content.Intent r4 = r3.getIntent()     // Catch:{ Throwable -> 0x008b }
            java.lang.String r0 = "data"
            java.lang.String r4 = r4.getStringExtra(r0)     // Catch:{ Throwable -> 0x008b }
            android.content.Intent r0 = r3.getIntent()     // Catch:{ Throwable -> 0x008b }
            java.lang.String r1 = "pkgname"
            java.lang.String r0 = r0.getStringExtra(r1)     // Catch:{ Throwable -> 0x008b }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x008b }
            if (r1 != 0) goto L_0x0051
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x008b }
            r2 = 8
            if (r1 < r2) goto L_0x0051
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x008b }
            r1.<init>()     // Catch:{ Throwable -> 0x008b }
            r2 = 0
            java.io.File r2 = r3.getExternalFilesDir(r2)     // Catch:{ Throwable -> 0x008b }
            r1.append(r2)     // Catch:{ Throwable -> 0x008b }
            java.lang.String r2 = "/.fgd"
            r1.append(r2)     // Catch:{ Throwable -> 0x008b }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x008b }
            java.lang.String r2 = android.os.Build.BRAND     // Catch:{ Throwable -> 0x008b }
            java.lang.String r2 = com.mob.guard.C2327a.m2650a(r2, r0)     // Catch:{ Throwable -> 0x008b }
            com.mob.tools.utils.ResHelper.saveObjectToFile(r1, r2)     // Catch:{ Throwable -> 0x008b }
            java.lang.String r1 = android.os.Build.BRAND     // Catch:{ Throwable -> 0x008b }
            java.lang.String r0 = com.mob.guard.C2327a.m2650a(r1, r0)     // Catch:{ Throwable -> 0x008b }
            com.mob.guard.MobGuard.GDF = r0     // Catch:{ Throwable -> 0x008b }
        L_0x0051:
            boolean r0 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x008b }
            if (r0 != 0) goto L_0x005a
            m1925a(r4)     // Catch:{ Throwable -> 0x008b }
        L_0x005a:
            java.lang.String r4 = ".mmgd"
            java.io.File r4 = com.mob.tools.utils.ResHelper.getDataCacheFile(r3, r4)     // Catch:{ Throwable -> 0x008b }
            r4.createNewFile()     // Catch:{ Throwable -> 0x008b }
            android.content.pm.PackageManager r4 = r3.getPackageManager()     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r0 = r3.getPackageName()     // Catch:{ Throwable -> 0x0088 }
            r1 = 128(0x80, float:1.794E-43)
            android.content.pm.PackageInfo r4 = r4.getPackageInfo(r0, r1)     // Catch:{ Throwable -> 0x0088 }
            android.content.pm.ApplicationInfo r4 = r4.applicationInfo     // Catch:{ Throwable -> 0x0088 }
            android.os.Bundle r4 = r4.metaData     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r0 = "guard_listener"
            java.lang.String r4 = r4.getString(r0)     // Catch:{ Throwable -> 0x0088 }
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ Throwable -> 0x0088 }
            java.lang.Object r4 = r4.newInstance()     // Catch:{ Throwable -> 0x0088 }
            com.mob.guard.h r4 = (com.mob.guard.C2340h) r4     // Catch:{ Throwable -> 0x0088 }
            r4.mo29160a(r3)     // Catch:{ Throwable -> 0x0088 }
        L_0x0088:
            r3.finish()     // Catch:{ Throwable -> 0x008b }
        L_0x008b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.MobTranUpActivity.onCreate(android.os.Bundle):void");
    }

    /* renamed from: a */
    public static void m1925a(String str) {
        try {
            ReflectHelper.invokeStaticMethod(ReflectHelper.importClass("com.mob.pushsdk.MobPush"), "addGuardMessage", str);
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                finish();
            } catch (Throwable unused) {
            }
        }
        super.onResume();
    }
}
