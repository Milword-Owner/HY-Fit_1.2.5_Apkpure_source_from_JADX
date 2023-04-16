package com.hjq.permissions;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import java.util.ArrayList;
import java.util.List;

public final class PermissionFragment extends Fragment implements Runnable {
    private static final String REQUEST_CODE = "request_code";
    private static final SparseBooleanArray REQUEST_CODE_ARRAY = new SparseBooleanArray();
    private static final String REQUEST_PERMISSIONS = "request_permissions";
    private OnPermissionCallback mCallBack;
    private boolean mDangerousRequest;
    private int mScreenOrientation;
    private boolean mSpecialRequest;

    public static void beginRequest(FragmentActivity fragmentActivity, ArrayList<String> arrayList, OnPermissionCallback onPermissionCallback) {
        int randomRequestCode;
        PermissionFragment permissionFragment = new PermissionFragment();
        Bundle bundle = new Bundle();
        do {
            randomRequestCode = PermissionUtils.getRandomRequestCode();
        } while (REQUEST_CODE_ARRAY.get(randomRequestCode));
        REQUEST_CODE_ARRAY.put(randomRequestCode, true);
        bundle.putInt(REQUEST_CODE, randomRequestCode);
        bundle.putStringArrayList(REQUEST_PERMISSIONS, arrayList);
        permissionFragment.setArguments(bundle);
        permissionFragment.setRetainInstance(true);
        permissionFragment.setCallBack(onPermissionCallback);
        permissionFragment.attachActivity(fragmentActivity);
    }

    public void attachActivity(FragmentActivity fragmentActivity) {
        fragmentActivity.getSupportFragmentManager().beginTransaction().add((Fragment) this, toString()).commitAllowingStateLoss();
    }

    public void detachActivity(FragmentActivity fragmentActivity) {
        fragmentActivity.getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
    }

    public void setCallBack(OnPermissionCallback onPermissionCallback) {
        this.mCallBack = onPermissionCallback;
    }

    @SuppressLint({"SourceLockedOrientationActivity"})
    public void onAttach(Context context) {
        super.onAttach(context);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            this.mScreenOrientation = activity.getRequestedOrientation();
            if (this.mScreenOrientation == -1) {
                int i = activity.getResources().getConfiguration().orientation;
                if (i == 2) {
                    activity.setRequestedOrientation(0);
                } else if (i == 1) {
                    activity.setRequestedOrientation(1);
                }
            }
        }
    }

    public void onDetach() {
        super.onDetach();
        FragmentActivity activity = getActivity();
        if (activity != null && this.mScreenOrientation == -1) {
            activity.setRequestedOrientation(-1);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mCallBack = null;
    }

    public void onResume() {
        super.onResume();
        if (!this.mSpecialRequest) {
            this.mSpecialRequest = true;
            requestSpecialPermission();
        }
    }

    public void requestSpecialPermission() {
        Bundle arguments = getArguments();
        FragmentActivity activity = getActivity();
        if (arguments != null && activity != null) {
            ArrayList<String> stringArrayList = arguments.getStringArrayList(REQUEST_PERMISSIONS);
            boolean z = false;
            if (PermissionUtils.containsSpecialPermission(stringArrayList)) {
                if (stringArrayList.contains(Permission.MANAGE_EXTERNAL_STORAGE) && !PermissionUtils.isGrantedStoragePermission(activity) && PermissionUtils.isAndroid11()) {
                    startActivityForResult(PermissionSettingPage.getStoragePermissionIntent(activity), getArguments().getInt(REQUEST_CODE));
                    z = true;
                }
                if (stringArrayList.contains(Permission.REQUEST_INSTALL_PACKAGES) && !PermissionUtils.isGrantedInstallPermission(activity)) {
                    startActivityForResult(PermissionSettingPage.getInstallPermissionIntent(activity), getArguments().getInt(REQUEST_CODE));
                    z = true;
                }
                if (stringArrayList.contains(Permission.SYSTEM_ALERT_WINDOW) && !PermissionUtils.isGrantedWindowPermission(activity)) {
                    startActivityForResult(PermissionSettingPage.getWindowPermissionIntent(activity), getArguments().getInt(REQUEST_CODE));
                    z = true;
                }
                if (stringArrayList.contains(Permission.NOTIFICATION_SERVICE) && !PermissionUtils.isGrantedNotifyPermission(activity)) {
                    startActivityForResult(PermissionSettingPage.getNotifyPermissionIntent(activity), getArguments().getInt(REQUEST_CODE));
                    z = true;
                }
                if (stringArrayList.contains(Permission.WRITE_SETTINGS) && !PermissionUtils.isGrantedSettingPermission(activity)) {
                    startActivityForResult(PermissionSettingPage.getSettingPermissionIntent(activity), getArguments().getInt(REQUEST_CODE));
                    z = true;
                }
            }
            if (!z) {
                requestDangerousPermission();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000e, code lost:
        r2 = r1.getStringArrayList(REQUEST_PERMISSIONS);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void requestDangerousPermission() {
        /*
            r6 = this;
            androidx.fragment.app.FragmentActivity r0 = r6.getActivity()
            android.os.Bundle r1 = r6.getArguments()
            if (r0 == 0) goto L_0x0080
            if (r1 != 0) goto L_0x000e
            goto L_0x0080
        L_0x000e:
            java.lang.String r2 = "request_permissions"
            java.util.ArrayList r2 = r1.getStringArrayList(r2)
            if (r2 == 0) goto L_0x0080
            int r3 = r2.size()
            if (r3 != 0) goto L_0x001d
            goto L_0x0080
        L_0x001d:
            r3 = 0
            boolean r4 = com.hjq.permissions.PermissionUtils.isAndroid10()
            if (r4 == 0) goto L_0x0053
            java.lang.String r4 = "android.permission.ACCESS_BACKGROUND_LOCATION"
            boolean r4 = r2.contains(r4)
            if (r4 == 0) goto L_0x0053
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.lang.String r4 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r5 = r2.contains(r4)
            if (r5 == 0) goto L_0x0042
            boolean r5 = com.hjq.permissions.PermissionUtils.isGrantedPermission(r0, r4)
            if (r5 != 0) goto L_0x0042
            r3.add(r4)
        L_0x0042:
            java.lang.String r4 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r5 = r2.contains(r4)
            if (r5 == 0) goto L_0x0053
            boolean r5 = com.hjq.permissions.PermissionUtils.isGrantedPermission(r0, r4)
            if (r5 != 0) goto L_0x0053
            r3.add(r4)
        L_0x0053:
            if (r3 == 0) goto L_0x0065
            boolean r4 = r3.isEmpty()
            if (r4 == 0) goto L_0x005c
            goto L_0x0065
        L_0x005c:
            com.hjq.permissions.PermissionFragment$1 r4 = new com.hjq.permissions.PermissionFragment$1
            r4.<init>(r2, r1)
            beginRequest(r0, r3, r4)
            return
        L_0x0065:
            int r0 = r2.size()
            int r0 = r0 + -1
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.Object[] r0 = r2.toArray(r0)
            java.lang.String[] r0 = (java.lang.String[]) r0
            android.os.Bundle r1 = r6.getArguments()
            java.lang.String r2 = "request_code"
            int r1 = r1.getInt(r2)
            r6.requestPermissions(r0, r1)
        L_0x0080:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hjq.permissions.PermissionFragment.requestDangerousPermission():void");
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Bundle arguments = getArguments();
        FragmentActivity activity = getActivity();
        if (activity != null && arguments != null && this.mCallBack != null && i == arguments.getInt(REQUEST_CODE)) {
            OnPermissionCallback onPermissionCallback = this.mCallBack;
            this.mCallBack = null;
            for (int i2 = 0; i2 < strArr.length; i2++) {
                String str = strArr[i2];
                if (PermissionUtils.isSpecialPermission(str)) {
                    iArr[i2] = PermissionUtils.getPermissionStatus(activity, str);
                } else if (PermissionUtils.isAndroid11() && Permission.ACCESS_BACKGROUND_LOCATION.equals(str)) {
                    iArr[i2] = PermissionUtils.getPermissionStatus(activity, str);
                } else if (!PermissionUtils.isAndroid10() && (Permission.ACCESS_BACKGROUND_LOCATION.equals(str) || Permission.ACTIVITY_RECOGNITION.equals(str) || Permission.ACCESS_MEDIA_LOCATION.equals(str))) {
                    iArr[i2] = PermissionUtils.getPermissionStatus(activity, str);
                } else if (!PermissionUtils.isAndroid9() && Permission.ACCEPT_HANDOVER.equals(str)) {
                    iArr[i2] = PermissionUtils.getPermissionStatus(activity, str);
                } else if (!PermissionUtils.isAndroid8() && (Permission.ANSWER_PHONE_CALLS.equals(str) || Permission.READ_PHONE_NUMBERS.equals(str))) {
                    iArr[i2] = PermissionUtils.getPermissionStatus(activity, str);
                }
            }
            REQUEST_CODE_ARRAY.delete(i);
            detachActivity(activity);
            List<String> grantedPermissions = PermissionUtils.getGrantedPermissions(strArr, iArr);
            if (grantedPermissions.size() == strArr.length) {
                XXPermissions.getPermissionInterceptor().grantedPermissions(activity, onPermissionCallback, grantedPermissions, true);
                return;
            }
            List<String> deniedPermissions = PermissionUtils.getDeniedPermissions(strArr, iArr);
            XXPermissions.getPermissionInterceptor().deniedPermissions(activity, onPermissionCallback, deniedPermissions, PermissionUtils.isPermissionPermanentDenied((Activity) activity, deniedPermissions));
            if (!grantedPermissions.isEmpty()) {
                XXPermissions.getPermissionInterceptor().grantedPermissions(activity, onPermissionCallback, grantedPermissions, false);
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        FragmentActivity activity = getActivity();
        Bundle arguments = getArguments();
        if (activity != null && arguments != null && i == arguments.getInt(REQUEST_CODE) && !this.mDangerousRequest) {
            this.mDangerousRequest = true;
            activity.getWindow().getDecorView().postDelayed(this, 300);
        }
    }

    public void run() {
        if (isAdded()) {
            requestDangerousPermission();
        }
    }
}
