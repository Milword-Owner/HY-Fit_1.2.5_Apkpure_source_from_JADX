package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.annotation.RequiresPermission;

public final class PhoneUtils {
    private PhoneUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isPhone() {
        return getTelephonyManager().getPhoneType() != 0;
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    @SuppressLint({"HardwareIds"})
    public static String getDeviceId() {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        TelephonyManager telephonyManager = getTelephonyManager();
        String deviceId = telephonyManager.getDeviceId();
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        if (Build.VERSION.SDK_INT < 26) {
            return "";
        }
        String imei = telephonyManager.getImei();
        if (!TextUtils.isEmpty(imei)) {
            return imei;
        }
        String meid = telephonyManager.getMeid();
        if (TextUtils.isEmpty(meid)) {
            return "";
        }
        return meid;
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    @SuppressLint({"HardwareIds"})
    public static String getSerial() {
        if (Build.VERSION.SDK_INT < 29) {
            return Build.VERSION.SDK_INT >= 26 ? Build.getSerial() : Build.SERIAL;
        }
        try {
            return Build.getSerial();
        } catch (SecurityException e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String getIMEI() {
        return getImeiOrMeid(true);
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String getMEID() {
        return getImeiOrMeid(false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a9, code lost:
        if (r0.length() < 15) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00bb, code lost:
        if (r0.length() == 14) goto L_0x00bd;
     */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x009a A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ac A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00db A[RETURN] */
    @androidx.annotation.RequiresPermission("android.permission.READ_PHONE_STATE")
    @android.annotation.SuppressLint({"HardwareIds"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getImeiOrMeid(boolean r12) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            java.lang.String r1 = ""
            r2 = 29
            if (r0 < r2) goto L_0x0009
            return r1
        L_0x0009:
            android.telephony.TelephonyManager r0 = getTelephonyManager()
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 26
            r4 = 1
            r5 = 0
            if (r2 < r3) goto L_0x0031
            if (r12 == 0) goto L_0x0024
            java.lang.String r12 = r0.getImei(r5)
            java.lang.String r0 = r0.getImei(r4)
            java.lang.String r12 = getMinOne(r12, r0)
            return r12
        L_0x0024:
            java.lang.String r12 = r0.getMeid(r5)
            java.lang.String r0 = r0.getMeid(r4)
            java.lang.String r12 = getMinOne(r12, r0)
            return r12
        L_0x0031:
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 21
            r6 = 15
            r7 = 14
            if (r2 < r3) goto L_0x00c3
            if (r12 == 0) goto L_0x0040
            java.lang.String r2 = "ril.gsm.imei"
            goto L_0x0042
        L_0x0040:
            java.lang.String r2 = "ril.cdma.meid"
        L_0x0042:
            java.lang.String r2 = getSystemPropertyByReflect(r2)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            r8 = 2
            if (r3 != 0) goto L_0x0062
            java.lang.String r12 = ","
            java.lang.String[] r12 = r2.split(r12)
            int r0 = r12.length
            if (r0 != r8) goto L_0x005f
            r0 = r12[r5]
            r12 = r12[r4]
            java.lang.String r12 = getMinOne(r0, r12)
            return r12
        L_0x005f:
            r12 = r12[r5]
            return r12
        L_0x0062:
            java.lang.String r2 = r0.getDeviceId()
            java.lang.Class r3 = r0.getClass()     // Catch:{ NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x008e, InvocationTargetException -> 0x0089 }
            java.lang.String r9 = "getDeviceId"
            java.lang.Class[] r10 = new java.lang.Class[r4]     // Catch:{ NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x008e, InvocationTargetException -> 0x0089 }
            java.lang.Class r11 = java.lang.Integer.TYPE     // Catch:{ NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x008e, InvocationTargetException -> 0x0089 }
            r10[r5] = r11     // Catch:{ NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x008e, InvocationTargetException -> 0x0089 }
            java.lang.reflect.Method r3 = r3.getMethod(r9, r10)     // Catch:{ NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x008e, InvocationTargetException -> 0x0089 }
            java.lang.Object[] r9 = new java.lang.Object[r4]     // Catch:{ NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x008e, InvocationTargetException -> 0x0089 }
            if (r12 == 0) goto L_0x007b
            goto L_0x007c
        L_0x007b:
            r4 = 2
        L_0x007c:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x008e, InvocationTargetException -> 0x0089 }
            r9[r5] = r4     // Catch:{ NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x008e, InvocationTargetException -> 0x0089 }
            java.lang.Object r0 = r3.invoke(r0, r9)     // Catch:{ NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x008e, InvocationTargetException -> 0x0089 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ NoSuchMethodException -> 0x0093, IllegalAccessException -> 0x008e, InvocationTargetException -> 0x0089 }
            goto L_0x0098
        L_0x0089:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0097
        L_0x008e:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0097
        L_0x0093:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0097:
            r0 = r1
        L_0x0098:
            if (r12 == 0) goto L_0x00ac
            if (r2 == 0) goto L_0x00a3
            int r12 = r2.length()
            if (r12 >= r6) goto L_0x00a3
            r2 = r1
        L_0x00a3:
            if (r0 == 0) goto L_0x00be
            int r12 = r0.length()
            if (r12 >= r6) goto L_0x00be
            goto L_0x00bd
        L_0x00ac:
            if (r2 == 0) goto L_0x00b5
            int r12 = r2.length()
            if (r12 != r7) goto L_0x00b5
            r2 = r1
        L_0x00b5:
            if (r0 == 0) goto L_0x00be
            int r12 = r0.length()
            if (r12 != r7) goto L_0x00be
        L_0x00bd:
            r0 = r1
        L_0x00be:
            java.lang.String r12 = getMinOne(r2, r0)
            return r12
        L_0x00c3:
            java.lang.String r0 = r0.getDeviceId()
            if (r12 == 0) goto L_0x00d2
            if (r0 == 0) goto L_0x00db
            int r12 = r0.length()
            if (r12 < r6) goto L_0x00db
            return r0
        L_0x00d2:
            if (r0 == 0) goto L_0x00db
            int r12 = r0.length()
            if (r12 != r7) goto L_0x00db
            return r0
        L_0x00db:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.PhoneUtils.getImeiOrMeid(boolean):java.lang.String");
    }

    private static String getMinOne(String str, String str2) {
        boolean isEmpty = TextUtils.isEmpty(str);
        boolean isEmpty2 = TextUtils.isEmpty(str2);
        if (!isEmpty || !isEmpty2) {
            return (isEmpty || isEmpty2) ? !isEmpty ? str : str2 : str.compareTo(str2) <= 0 ? str : str2;
        }
        return "";
    }

    private static String getSystemPropertyByReflect(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{str, ""});
        } catch (Exception unused) {
            return "";
        }
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    @SuppressLint({"HardwareIds"})
    public static String getIMSI() {
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                getTelephonyManager().getSubscriberId();
            } catch (SecurityException e) {
                e.printStackTrace();
                return "";
            }
        }
        return getTelephonyManager().getSubscriberId();
    }

    public static int getPhoneType() {
        return getTelephonyManager().getPhoneType();
    }

    public static boolean isSimCardReady() {
        return getTelephonyManager().getSimState() == 5;
    }

    public static String getSimOperatorName() {
        return getTelephonyManager().getSimOperatorName();
    }

    public static String getSimOperatorByMnc() {
        String simOperator = getTelephonyManager().getSimOperator();
        if (simOperator == null) {
            return "";
        }
        char c = 65535;
        int hashCode = simOperator.hashCode();
        if (hashCode != 49679479) {
            if (hashCode != 49679502) {
                if (hashCode != 49679532) {
                    switch (hashCode) {
                        case 49679470:
                            if (simOperator.equals("46000")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 49679471:
                            if (simOperator.equals("46001")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 49679472:
                            if (simOperator.equals("46002")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 49679473:
                            if (simOperator.equals("46003")) {
                                c = 7;
                                break;
                            }
                            break;
                        default:
                            switch (hashCode) {
                                case 49679475:
                                    if (simOperator.equals("46005")) {
                                        c = 8;
                                        break;
                                    }
                                    break;
                                case 49679476:
                                    if (simOperator.equals("46006")) {
                                        c = 5;
                                        break;
                                    }
                                    break;
                                case 49679477:
                                    if (simOperator.equals("46007")) {
                                        c = 2;
                                        break;
                                    }
                                    break;
                            }
                    }
                } else if (simOperator.equals("46020")) {
                    c = 3;
                }
            } else if (simOperator.equals("46011")) {
                c = 9;
            }
        } else if (simOperator.equals("46009")) {
            c = 6;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
                return "中国移动";
            case 4:
            case 5:
            case 6:
                return "中国联通";
            case 7:
            case 8:
            case 9:
                return "中国电信";
            default:
                return simOperator;
        }
    }

    public static void dial(String str) {
        Utils.getApp().startActivity(UtilsBridge.getDialIntent(str));
    }

    @RequiresPermission("android.permission.CALL_PHONE")
    public static void call(String str) {
        Utils.getApp().startActivity(UtilsBridge.getCallIntent(str));
    }

    public static void sendSms(String str, String str2) {
        Utils.getApp().startActivity(UtilsBridge.getSendSmsIntent(str, str2));
    }

    private static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) Utils.getApp().getSystemService("phone");
    }
}
