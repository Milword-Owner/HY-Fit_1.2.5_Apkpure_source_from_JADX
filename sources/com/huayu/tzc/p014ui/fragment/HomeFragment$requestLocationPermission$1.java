package com.huayu.tzc.p014ui.fragment;

import android.util.Log;
import android.widget.LinearLayout;
import com.facebook.internal.NativeProtocol;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.MainActivity;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u0010\t\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\n\u001a\u00020\bH\u0016¨\u0006\u000b"}, mo21895d2 = {"com/huayu/tzc/ui/fragment/HomeFragment$requestLocationPermission$1", "Lcom/hjq/permissions/OnPermissionCallback;", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.HomeFragment$requestLocationPermission$1 */
/* compiled from: HomeFragment.kt */
public final class HomeFragment$requestLocationPermission$1 implements OnPermissionCallback {
    final /* synthetic */ HomeFragment this$0;

    HomeFragment$requestLocationPermission$1(HomeFragment homeFragment) {
        this.this$0 = homeFragment;
    }

    public void onGranted(@NotNull List<String> list, boolean z) {
        Intrinsics.checkParameterIsNotNull(list, NativeProtocol.RESULT_ARGS_PERMISSIONS);
        if (z && XXPermissions.isGrantedPermission(this.this$0.getContext(), Permission.ACCESS_FINE_LOCATION) && XXPermissions.isGrantedPermission(this.this$0.getContext(), Permission.ACCESS_COARSE_LOCATION)) {
            Log.e(this.this$0.getTAG(), "onGranted: ");
            LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(C2128R.C2131id.homeAuthorityLayout);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "homeAuthorityLayout");
            linearLayout.setVisibility(8);
            MainActivity mainActivity = (MainActivity) this.this$0.getActivity();
            if (mainActivity != null) {
                mainActivity.initBle();
            }
            this.this$0.checkMacEmpty();
        }
    }

    public void onDenied(@NotNull List<String> list, boolean z) {
        Intrinsics.checkParameterIsNotNull(list, NativeProtocol.RESULT_ARGS_PERMISSIONS);
        ToastUtils.show((CharSequence) this.this$0.getString(C2128R.string.open_permission));
        if (z) {
            XXPermissions.startPermissionActivity(this.this$0.getContext(), list);
        } else {
            ToastUtils.show((CharSequence) this.this$0.getString(C2128R.string.permiss_fail));
        }
    }
}
