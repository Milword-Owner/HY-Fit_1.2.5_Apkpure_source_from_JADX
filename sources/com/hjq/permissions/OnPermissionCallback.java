package com.hjq.permissions;

import java.util.List;

public interface OnPermissionCallback {

    /* renamed from: com.hjq.permissions.OnPermissionCallback$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onDenied(OnPermissionCallback onPermissionCallback, List list, boolean z) {
        }
    }

    void onDenied(List<String> list, boolean z);

    void onGranted(List<String> list, boolean z);
}
