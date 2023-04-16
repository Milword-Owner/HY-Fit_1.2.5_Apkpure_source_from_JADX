package com.hjq.permissions;

import android.app.Activity;
import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import com.hjq.permissions.Permission;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

final class PermissionUtils {
    private static final int PRIVATE_FLAG_REQUEST_LEGACY_EXTERNAL_STORAGE = 536870912;

    PermissionUtils() {
    }

    static boolean isAndroid11() {
        return Build.VERSION.SDK_INT >= 30;
    }

    static boolean isAndroid10() {
        return Build.VERSION.SDK_INT >= 29;
    }

    static boolean isAndroid9() {
        return Build.VERSION.SDK_INT >= 28;
    }

    static boolean isAndroid8() {
        return Build.VERSION.SDK_INT >= 26;
    }

    static boolean isAndroid7() {
        return Build.VERSION.SDK_INT >= 24;
    }

    static boolean isAndroid6() {
        return Build.VERSION.SDK_INT >= 23;
    }

    static List<String> getManifestPermissions(Context context) {
        try {
            return asArrayList(context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    static boolean isGrantedStoragePermission(Context context) {
        if (isAndroid11()) {
            return Environment.isExternalStorageManager();
        }
        return XXPermissions.isGrantedPermission(context, Permission.Group.STORAGE);
    }

    static boolean isGrantedInstallPermission(Context context) {
        if (isAndroid8()) {
            return context.getPackageManager().canRequestPackageInstalls();
        }
        return true;
    }

    static boolean isGrantedWindowPermission(Context context) {
        if (isAndroid6()) {
            return Settings.canDrawOverlays(context);
        }
        return true;
    }

    static boolean isGrantedNotifyPermission(Context context) {
        if (isAndroid7()) {
            return ((NotificationManager) context.getSystemService(NotificationManager.class)).areNotificationsEnabled();
        }
        if (isAndroid6()) {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
            try {
                if (((Integer) appOpsManager.getClass().getMethod("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(appOpsManager, new Object[]{Integer.valueOf(((Integer) appOpsManager.getClass().getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(context.getApplicationInfo().uid), context.getPackageName()})).intValue() == 0) {
                    return true;
                }
                return false;
            } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    static boolean isGrantedSettingPermission(Context context) {
        if (isAndroid6()) {
            return Settings.System.canWrite(context);
        }
        return true;
    }

    static boolean containsSpecialPermission(List<String> list) {
        if (list != null && !list.isEmpty()) {
            for (String isSpecialPermission : list) {
                if (isSpecialPermission(isSpecialPermission)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean isSpecialPermission(String str) {
        return Permission.MANAGE_EXTERNAL_STORAGE.equals(str) || Permission.REQUEST_INSTALL_PACKAGES.equals(str) || Permission.SYSTEM_ALERT_WINDOW.equals(str) || Permission.NOTIFICATION_SERVICE.equals(str) || Permission.WRITE_SETTINGS.equals(str);
    }

    static boolean isGrantedPermissions(Context context, List<String> list) {
        if (!isAndroid6()) {
            return true;
        }
        for (String isGrantedPermission : list) {
            if (!isGrantedPermission(context, isGrantedPermission)) {
                return false;
            }
        }
        return true;
    }

    static List<String> getDeniedPermissions(Context context, List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        if (!isAndroid6()) {
            return arrayList;
        }
        for (String next : list) {
            if (!isGrantedPermission(context, next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    static boolean isGrantedPermission(Context context, String str) {
        if (!isAndroid6()) {
            return true;
        }
        if (Permission.MANAGE_EXTERNAL_STORAGE.equals(str)) {
            return isGrantedStoragePermission(context);
        }
        if (Permission.REQUEST_INSTALL_PACKAGES.equals(str)) {
            return isGrantedInstallPermission(context);
        }
        if (Permission.SYSTEM_ALERT_WINDOW.equals(str)) {
            return isGrantedWindowPermission(context);
        }
        if (Permission.NOTIFICATION_SERVICE.equals(str)) {
            return isGrantedNotifyPermission(context);
        }
        if (Permission.WRITE_SETTINGS.equals(str)) {
            return isGrantedSettingPermission(context);
        }
        if (!isAndroid10()) {
            if (Permission.ACCESS_BACKGROUND_LOCATION.equals(str) || Permission.ACCESS_MEDIA_LOCATION.equals(str)) {
                return true;
            }
            if (Permission.ACTIVITY_RECOGNITION.equals(str)) {
                if (context.checkSelfPermission(Permission.BODY_SENSORS) == 0) {
                    return true;
                }
                return false;
            }
        }
        if (!isAndroid9() && Permission.ACCEPT_HANDOVER.equals(str)) {
            return true;
        }
        if (!isAndroid8()) {
            if (Permission.ANSWER_PHONE_CALLS.equals(str)) {
                return true;
            }
            if (Permission.READ_PHONE_NUMBERS.equals(str)) {
                if (context.checkSelfPermission(Permission.READ_PHONE_STATE) == 0) {
                    return true;
                }
                return false;
            }
        }
        if (context.checkSelfPermission(str) == 0) {
            return true;
        }
        return false;
    }

    static int getPermissionStatus(Context context, String str) {
        return isGrantedPermission(context, str) ? 0 : -1;
    }

    static boolean isPermissionPermanentDenied(Activity activity, List<String> list) {
        for (String isPermissionPermanentDenied : list) {
            if (isPermissionPermanentDenied(activity, isPermissionPermanentDenied)) {
                return true;
            }
        }
        return false;
    }

    static boolean isPermissionPermanentDenied(Activity activity, String str) {
        if (!isAndroid6() || isSpecialPermission(str)) {
            return false;
        }
        if (!isAndroid10()) {
            if (Permission.ACCESS_BACKGROUND_LOCATION.equals(str) || Permission.ACCESS_MEDIA_LOCATION.equals(str)) {
                return false;
            }
            if (Permission.ACTIVITY_RECOGNITION.equals(str)) {
                if (activity.checkSelfPermission(Permission.BODY_SENSORS) != -1 || activity.shouldShowRequestPermissionRationale(str)) {
                    return false;
                }
                return true;
            }
        }
        if (!isAndroid9() && Permission.ACCEPT_HANDOVER.equals(str)) {
            return false;
        }
        if (!isAndroid8()) {
            if (Permission.ANSWER_PHONE_CALLS.equals(str)) {
                return true;
            }
            if (Permission.READ_PHONE_NUMBERS.equals(str)) {
                if (activity.checkSelfPermission(Permission.READ_PHONE_STATE) != -1 || activity.shouldShowRequestPermissionRationale(str)) {
                    return false;
                }
                return true;
            }
        }
        if (activity.checkSelfPermission(str) != -1 || activity.shouldShowRequestPermissionRationale(str)) {
            return false;
        }
        return true;
    }

    static List<String> getDeniedPermissions(String[] strArr, int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < iArr.length; i++) {
            if (iArr[i] == -1) {
                arrayList.add(strArr[i]);
            }
        }
        return arrayList;
    }

    static List<String> getGrantedPermissions(String[] strArr, int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < iArr.length; i++) {
            if (iArr[i] == 0) {
                arrayList.add(strArr[i]);
            }
        }
        return arrayList;
    }

    static void optimizeDeprecatedPermission(List<String> list) {
        if (list.contains(Permission.MANAGE_EXTERNAL_STORAGE)) {
            if (list.contains(Permission.READ_EXTERNAL_STORAGE) || list.contains(Permission.WRITE_EXTERNAL_STORAGE)) {
                throw new IllegalArgumentException("Please do not apply for these two permissions dynamically");
            } else if (!isAndroid11()) {
                list.add(Permission.READ_EXTERNAL_STORAGE);
                list.add(Permission.WRITE_EXTERNAL_STORAGE);
            }
        }
        if (!isAndroid8() && list.contains(Permission.READ_PHONE_NUMBERS) && !list.contains(Permission.READ_PHONE_STATE)) {
            list.add(Permission.READ_PHONE_STATE);
        }
        if (!isAndroid10() && list.contains(Permission.ACTIVITY_RECOGNITION) && !list.contains(Permission.BODY_SENSORS)) {
            list.add(Permission.BODY_SENSORS);
        }
    }

    static <T> ArrayList<T> asArrayList(T... tArr) {
        if (tArr == null || tArr.length == 0) {
            return null;
        }
        ArrayList<T> arrayList = new ArrayList<>(tArr.length);
        for (T add : tArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    static void checkStoragePermission(Context context, List<String> list) {
        int i = context.getApplicationInfo().targetSdkVersion;
        if (i >= 29 && isAndroid10() && (list.contains(Permission.MANAGE_EXTERNAL_STORAGE) || list.contains(Permission.READ_EXTERNAL_STORAGE) || list.contains(Permission.WRITE_EXTERNAL_STORAGE))) {
            try {
                if (!((((Integer) ApplicationInfo.class.getDeclaredField("privateFlags").get(context.getApplicationInfo())).intValue() & PRIVATE_FLAG_REQUEST_LEGACY_EXTERNAL_STORAGE) != 0)) {
                    throw new IllegalStateException("Please register the android:requestLegacyExternalStorage=\"true\" attribute in the manifest file");
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            }
        }
        if (i < 30) {
            return;
        }
        if (list.contains(Permission.READ_EXTERNAL_STORAGE) || list.contains(Permission.WRITE_EXTERNAL_STORAGE)) {
            throw new IllegalArgumentException("Please use Permission.MANAGE_EXTERNAL_STORAGE to request storage permission");
        }
    }

    static void checkLocationPermission(List<String> list) {
        if (list.contains(Permission.ACCESS_BACKGROUND_LOCATION)) {
            for (String next : list) {
                if (!Permission.ACCESS_FINE_LOCATION.equals(next) && !Permission.ACCESS_COARSE_LOCATION.equals(next) && !Permission.ACCESS_BACKGROUND_LOCATION.equals(next)) {
                    throw new IllegalArgumentException("Because it includes background location permissions, do not apply for permissions unrelated to location");
                }
            }
        }
    }

    static void checkTargetSdkVersion(Context context, List<String> list) {
        int i;
        if (list.contains(Permission.MANAGE_EXTERNAL_STORAGE)) {
            i = 30;
        } else if (list.contains(Permission.ACCEPT_HANDOVER)) {
            i = 28;
        } else {
            i = (list.contains(Permission.ACCESS_BACKGROUND_LOCATION) || list.contains(Permission.ACTIVITY_RECOGNITION) || list.contains(Permission.ACCESS_MEDIA_LOCATION)) ? 29 : (list.contains(Permission.REQUEST_INSTALL_PACKAGES) || list.contains(Permission.ANSWER_PHONE_CALLS) || list.contains(Permission.READ_PHONE_NUMBERS)) ? 26 : 23;
        }
        if (context.getApplicationInfo().targetSdkVersion < i) {
            throw new RuntimeException("The targetSdkVersion SDK must be " + i + " or more");
        }
    }

    static void checkPermissionManifest(Context context, List<String> list) {
        List<String> manifestPermissions = getManifestPermissions(context);
        if (manifestPermissions == null || manifestPermissions.isEmpty()) {
            throw new ManifestRegisterException();
        }
        int i = Build.VERSION.SDK_INT >= 24 ? context.getApplicationInfo().minSdkVersion : 23;
        for (String next : list) {
            if (i < 30 && Permission.MANAGE_EXTERNAL_STORAGE.equals(next)) {
                if (!manifestPermissions.contains(Permission.READ_EXTERNAL_STORAGE)) {
                    throw new ManifestRegisterException(Permission.READ_EXTERNAL_STORAGE);
                } else if (!manifestPermissions.contains(Permission.WRITE_EXTERNAL_STORAGE)) {
                    throw new ManifestRegisterException(Permission.WRITE_EXTERNAL_STORAGE);
                }
            }
            if (i < 29 && Permission.ACTIVITY_RECOGNITION.equals(next) && !manifestPermissions.contains(Permission.BODY_SENSORS)) {
                throw new ManifestRegisterException(Permission.BODY_SENSORS);
            } else if (i < 26 && Permission.READ_PHONE_NUMBERS.equals(next) && !manifestPermissions.contains(Permission.READ_PHONE_STATE)) {
                throw new ManifestRegisterException(Permission.READ_PHONE_STATE);
            } else if (!Permission.NOTIFICATION_SERVICE.equals(next) && !manifestPermissions.contains(next)) {
                throw new ManifestRegisterException(next);
            }
        }
    }

    static int getRandomRequestCode() {
        return new Random().nextInt((int) Math.pow(2.0d, 8.0d));
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x0007  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static androidx.fragment.app.FragmentActivity findFragmentActivity(android.content.Context r2) {
        /*
        L_0x0000:
            boolean r0 = r2 instanceof androidx.fragment.app.FragmentActivity
            if (r0 == 0) goto L_0x0007
            androidx.fragment.app.FragmentActivity r2 = (androidx.fragment.app.FragmentActivity) r2
            return r2
        L_0x0007:
            boolean r0 = r2 instanceof android.content.ContextWrapper
            r1 = 0
            if (r0 == 0) goto L_0x0014
            android.content.ContextWrapper r2 = (android.content.ContextWrapper) r2
            android.content.Context r2 = r2.getBaseContext()
            if (r2 != 0) goto L_0x0000
        L_0x0014:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hjq.permissions.PermissionUtils.findFragmentActivity(android.content.Context):androidx.fragment.app.FragmentActivity");
    }
}
