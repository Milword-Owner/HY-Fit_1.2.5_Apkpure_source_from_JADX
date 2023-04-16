package com.hjq.permissions;

import androidx.fragment.app.FragmentActivity;
import java.util.ArrayList;
import java.util.List;

public interface IPermissionInterceptor {
    void deniedPermissions(FragmentActivity fragmentActivity, OnPermissionCallback onPermissionCallback, List<String> list, boolean z);

    void grantedPermissions(FragmentActivity fragmentActivity, OnPermissionCallback onPermissionCallback, List<String> list, boolean z);

    void requestPermissions(FragmentActivity fragmentActivity, OnPermissionCallback onPermissionCallback, List<String> list);

    /* renamed from: com.hjq.permissions.IPermissionInterceptor$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$requestPermissions(IPermissionInterceptor _this, FragmentActivity fragmentActivity, OnPermissionCallback onPermissionCallback, List list) {
            PermissionFragment.beginRequest(fragmentActivity, new ArrayList(list), onPermissionCallback);
        }

        public static void $default$grantedPermissions(IPermissionInterceptor _this, FragmentActivity fragmentActivity, OnPermissionCallback onPermissionCallback, List list, boolean z) {
            onPermissionCallback.onGranted(list, z);
        }

        public static void $default$deniedPermissions(IPermissionInterceptor _this, FragmentActivity fragmentActivity, OnPermissionCallback onPermissionCallback, List list, boolean z) {
            onPermissionCallback.onDenied(list, z);
        }
    }
}
