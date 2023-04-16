package com.hjq.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.hjq.permissions.IPermissionInterceptor;
import java.util.ArrayList;
import java.util.List;

public final class XXPermissions {
    public static final int REQUEST_CODE = 1025;
    private static Boolean sDebugMode;
    private static IPermissionInterceptor sPermissionInterceptor;
    private final FragmentActivity mActivity;
    private List<String> mPermissions;

    public static XXPermissions with(FragmentActivity fragmentActivity) {
        return new XXPermissions(fragmentActivity);
    }

    public static XXPermissions with(Context context) {
        return with(PermissionUtils.findFragmentActivity(context));
    }

    public static XXPermissions with(Fragment fragment) {
        return with(fragment.getActivity());
    }

    public static void setDebugMode(Boolean bool) {
        sDebugMode = bool;
    }

    private static boolean isDebugMode(Context context) {
        if (sDebugMode == null) {
            sDebugMode = Boolean.valueOf((context.getApplicationInfo().flags & 2) != 0);
        }
        return sDebugMode.booleanValue();
    }

    public static void setPermissionInterceptor(IPermissionInterceptor iPermissionInterceptor) {
        sPermissionInterceptor = iPermissionInterceptor;
    }

    static IPermissionInterceptor getPermissionInterceptor() {
        if (sPermissionInterceptor == null) {
            sPermissionInterceptor = new IPermissionInterceptor() {
                public /* synthetic */ void deniedPermissions(FragmentActivity fragmentActivity, OnPermissionCallback onPermissionCallback, List<String> list, boolean z) {
                    IPermissionInterceptor.CC.$default$deniedPermissions(this, fragmentActivity, onPermissionCallback, list, z);
                }

                public /* synthetic */ void grantedPermissions(FragmentActivity fragmentActivity, OnPermissionCallback onPermissionCallback, List<String> list, boolean z) {
                    IPermissionInterceptor.CC.$default$grantedPermissions(this, fragmentActivity, onPermissionCallback, list, z);
                }

                public /* synthetic */ void requestPermissions(FragmentActivity fragmentActivity, OnPermissionCallback onPermissionCallback, List<String> list) {
                    IPermissionInterceptor.CC.$default$requestPermissions(this, fragmentActivity, onPermissionCallback, list);
                }
            };
        }
        return sPermissionInterceptor;
    }

    private XXPermissions(FragmentActivity fragmentActivity) {
        this.mActivity = fragmentActivity;
    }

    public XXPermissions permission(String str) {
        if (this.mPermissions == null) {
            this.mPermissions = new ArrayList(1);
        }
        this.mPermissions.add(str);
        return this;
    }

    public XXPermissions permission(String[] strArr) {
        return permission((List<String>) PermissionUtils.asArrayList(strArr));
    }

    public XXPermissions permission(List<String> list) {
        List<String> list2 = this.mPermissions;
        if (list2 == null) {
            this.mPermissions = list;
        } else {
            list2.addAll(list);
        }
        return this;
    }

    public void request(OnPermissionCallback onPermissionCallback) {
        FragmentActivity fragmentActivity = this.mActivity;
        if (fragmentActivity != null && !fragmentActivity.isFinishing()) {
            if (Build.VERSION.SDK_INT < 17 || !this.mActivity.isDestroyed()) {
                List<String> list = this.mPermissions;
                if (list != null && !list.isEmpty()) {
                    if (isDebugMode(this.mActivity)) {
                        PermissionUtils.checkStoragePermission(this.mActivity, this.mPermissions);
                        PermissionUtils.checkLocationPermission(this.mPermissions);
                        PermissionUtils.checkTargetSdkVersion(this.mActivity, this.mPermissions);
                    }
                    PermissionUtils.optimizeDeprecatedPermission(this.mPermissions);
                    if (isDebugMode(this.mActivity)) {
                        PermissionUtils.checkPermissionManifest(this.mActivity, this.mPermissions);
                    }
                    if (!PermissionUtils.isGrantedPermissions(this.mActivity, this.mPermissions)) {
                        getPermissionInterceptor().requestPermissions(this.mActivity, onPermissionCallback, this.mPermissions);
                    } else if (onPermissionCallback != null) {
                        onPermissionCallback.onGranted(this.mPermissions, true);
                    }
                } else if (isDebugMode(this.mActivity)) {
                    throw new IllegalArgumentException("The requested permission cannot be empty");
                }
            }
        }
    }

    public static boolean isGrantedPermission(Context context, String str) {
        return PermissionUtils.isGrantedPermission(context, str);
    }

    public static boolean isGrantedPermission(Context context, String[] strArr) {
        return isGrantedPermission(context, (List<String>) PermissionUtils.asArrayList(strArr));
    }

    public static boolean isGrantedPermission(Context context, List<String> list) {
        return PermissionUtils.isGrantedPermissions(context, list);
    }

    public static List<String> getDeniedPermissions(Context context, String[] strArr) {
        return getDeniedPermissions(context, (List<String>) PermissionUtils.asArrayList(strArr));
    }

    public static List<String> getDeniedPermissions(Context context, List<String> list) {
        return PermissionUtils.getDeniedPermissions(context, list);
    }

    public static boolean isPermissionPermanentDenied(Activity activity, String str) {
        return PermissionUtils.isPermissionPermanentDenied(activity, str);
    }

    public static boolean isPermissionPermanentDenied(Activity activity, String[] strArr) {
        return isPermissionPermanentDenied(activity, (List<String>) PermissionUtils.asArrayList(strArr));
    }

    public static boolean isPermissionPermanentDenied(Activity activity, List<String> list) {
        return PermissionUtils.isPermissionPermanentDenied(activity, list);
    }

    public static boolean isSpecialPermission(String str) {
        return PermissionUtils.isSpecialPermission(str);
    }

    public static void startApplicationDetails(Context context) {
        FragmentActivity findFragmentActivity = PermissionUtils.findFragmentActivity(context);
        if (findFragmentActivity != null) {
            startApplicationDetails((Activity) findFragmentActivity);
            return;
        }
        Intent applicationDetailsIntent = PermissionSettingPage.getApplicationDetailsIntent(context);
        applicationDetailsIntent.addFlags(268435456);
        context.startActivity(applicationDetailsIntent);
    }

    public static void startApplicationDetails(Activity activity) {
        activity.startActivityForResult(PermissionSettingPage.getApplicationDetailsIntent(activity), 1025);
    }

    public static void startApplicationDetails(Fragment fragment) {
        FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            fragment.startActivityForResult(PermissionSettingPage.getApplicationDetailsIntent(activity), 1025);
        }
    }

    public static void startPermissionActivity(Context context, String str) {
        startPermissionActivity(context, (List<String>) PermissionUtils.asArrayList(str));
    }

    public static void startPermissionActivity(Context context, String[] strArr) {
        startPermissionActivity(context, (List<String>) PermissionUtils.asArrayList(strArr));
    }

    public static void startPermissionActivity(Context context, List<String> list) {
        FragmentActivity findFragmentActivity = PermissionUtils.findFragmentActivity(context);
        if (findFragmentActivity != null) {
            startPermissionActivity((Activity) findFragmentActivity, list);
            return;
        }
        Intent smartPermissionIntent = PermissionSettingPage.getSmartPermissionIntent(context, list);
        smartPermissionIntent.addFlags(268435456);
        context.startActivity(smartPermissionIntent);
    }

    public static void startPermissionActivity(Activity activity, String str) {
        startPermissionActivity(activity, (List<String>) PermissionUtils.asArrayList(str));
    }

    public static void startPermissionActivity(Activity activity, String[] strArr) {
        startPermissionActivity(activity, (List<String>) PermissionUtils.asArrayList(strArr));
    }

    public static void startPermissionActivity(Activity activity, List<String> list) {
        activity.startActivityForResult(PermissionSettingPage.getSmartPermissionIntent(activity, list), 1025);
    }

    public static void startPermissionActivity(Fragment fragment, String str) {
        startPermissionActivity(fragment, (List<String>) PermissionUtils.asArrayList(str));
    }

    public static void startPermissionActivity(Fragment fragment, String[] strArr) {
        startPermissionActivity(fragment, (List<String>) PermissionUtils.asArrayList(strArr));
    }

    public static void startPermissionActivity(Fragment fragment, List<String> list) {
        FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            fragment.startActivityForResult(PermissionSettingPage.getSmartPermissionIntent(activity, list), 1025);
        }
    }
}
