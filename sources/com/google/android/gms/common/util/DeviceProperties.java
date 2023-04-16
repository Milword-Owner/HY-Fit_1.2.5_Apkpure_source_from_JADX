package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
public final class DeviceProperties {
    private static Boolean zzhb;
    private static Boolean zzhc;
    private static Boolean zzhd;
    private static Boolean zzhe;
    private static Boolean zzhf;
    private static Boolean zzhg;
    private static Boolean zzhh;
    private static Boolean zzhi;
    private static Boolean zzhj;

    private DeviceProperties() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003c, code lost:
        if (zzhc.booleanValue() != false) goto L_0x003e;
     */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isTablet(android.content.res.Resources r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.Boolean r1 = zzhb
            if (r1 != 0) goto L_0x0045
            android.content.res.Configuration r1 = r4.getConfiguration()
            int r1 = r1.screenLayout
            r1 = r1 & 15
            r2 = 3
            r3 = 1
            if (r1 <= r2) goto L_0x0016
            r1 = 1
            goto L_0x0017
        L_0x0016:
            r1 = 0
        L_0x0017:
            if (r1 != 0) goto L_0x003e
            java.lang.Boolean r1 = zzhc
            if (r1 != 0) goto L_0x0036
            android.content.res.Configuration r4 = r4.getConfiguration()
            int r1 = r4.screenLayout
            r1 = r1 & 15
            if (r1 > r2) goto L_0x002f
            int r4 = r4.smallestScreenWidthDp
            r1 = 600(0x258, float:8.41E-43)
            if (r4 < r1) goto L_0x002f
            r4 = 1
            goto L_0x0030
        L_0x002f:
            r4 = 0
        L_0x0030:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            zzhc = r4
        L_0x0036:
            java.lang.Boolean r4 = zzhc
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x003f
        L_0x003e:
            r0 = 1
        L_0x003f:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r0)
            zzhb = r4
        L_0x0045:
            java.lang.Boolean r4 = zzhb
            boolean r4 = r4.booleanValue()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.DeviceProperties.isTablet(android.content.res.Resources):boolean");
    }

    @TargetApi(20)
    @KeepForSdk
    public static boolean isWearable(Context context) {
        if (zzhd == null) {
            zzhd = Boolean.valueOf(PlatformVersion.isAtLeastKitKatWatch() && context.getPackageManager().hasSystemFeature("android.hardware.type.watch"));
        }
        return zzhd.booleanValue();
    }

    @TargetApi(26)
    @KeepForSdk
    public static boolean isWearableWithoutPlayStore(Context context) {
        if (!isWearable(context)) {
            return false;
        }
        if (PlatformVersion.isAtLeastN()) {
            return zzf(context) && !PlatformVersion.isAtLeastO();
        }
        return true;
    }

    @TargetApi(21)
    @KeepForSdk
    public static boolean isSidewinder(Context context) {
        return zzf(context);
    }

    @TargetApi(21)
    private static boolean zzf(Context context) {
        if (zzhe == null) {
            zzhe = Boolean.valueOf(PlatformVersion.isAtLeastLollipop() && context.getPackageManager().hasSystemFeature("cn.google"));
        }
        return zzhe.booleanValue();
    }

    @KeepForSdk
    public static boolean isLatchsky(Context context) {
        if (zzhf == null) {
            PackageManager packageManager = context.getPackageManager();
            zzhf = Boolean.valueOf(packageManager.hasSystemFeature("com.google.android.feature.services_updater") && packageManager.hasSystemFeature("cn.google.services"));
        }
        return zzhf.booleanValue();
    }

    public static boolean zzg(Context context) {
        if (zzhg == null) {
            zzhg = Boolean.valueOf(context.getPackageManager().hasSystemFeature("android.hardware.type.iot") || context.getPackageManager().hasSystemFeature("android.hardware.type.embedded"));
        }
        return zzhg.booleanValue();
    }

    @KeepForSdk
    public static boolean isFeaturePhone(Context context) {
        if (zzhj == null) {
            zzhj = Boolean.valueOf(context.getPackageManager().hasSystemFeature("android.hardware.type.featurephone"));
        }
        return zzhj.booleanValue();
    }

    @KeepForSdk
    public static boolean isAuto(Context context) {
        if (zzhh == null) {
            zzhh = Boolean.valueOf(PlatformVersion.isAtLeastO() && context.getPackageManager().hasSystemFeature("android.hardware.type.automotive"));
        }
        return zzhh.booleanValue();
    }

    @KeepForSdk
    public static boolean isTv(Context context) {
        if (zzhi == null) {
            PackageManager packageManager = context.getPackageManager();
            zzhi = Boolean.valueOf(packageManager.hasSystemFeature("com.google.android.tv") || packageManager.hasSystemFeature("android.hardware.type.television") || packageManager.hasSystemFeature("android.software.leanback"));
        }
        return zzhi.booleanValue();
    }

    @KeepForSdk
    public static boolean isUserBuild() {
        return "user".equals(Build.TYPE);
    }
}
