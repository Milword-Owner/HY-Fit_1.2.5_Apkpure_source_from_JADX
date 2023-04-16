package com.huayu.tzc.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.internal.NativeProtocol;
import com.hjq.language.MultiLanguages;
import com.hjq.permissions.Permission;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.base.BaseContract;
import com.huayu.tzc.base.BaseContract.BasePresenter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u000f\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0010\b\u0001\u0010\u0002*\n\u0012\u0004\u0012\u0002H\u0001\u0018\u00010\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u001d\u0010\u001a\u001a\u00020\u001b2\u000e\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001dH\u0002¢\u0006\u0002\u0010\u001eJ%\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0 2\u000e\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001dH\u0002¢\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020\u0007H$J\r\u0010#\u001a\u00028\u0001H$¢\u0006\u0002\u0010\u0010J\b\u0010$\u001a\u00020%H$J\u0010\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020(H\u0016J\u0012\u0010)\u001a\u00020%2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J&\u0010,\u001a\u0004\u0018\u00010\u00152\u0006\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u0001002\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u00101\u001a\u00020%H\u0016J+\u00102\u001a\u00020%2\u0006\u00103\u001a\u00020\u00072\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\u001d2\u0006\u00104\u001a\u000205H\u0016¢\u0006\u0002\u00106J\u001a\u00107\u001a\u00020%2\u0006\u00108\u001a\u00020\u00152\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u0010\u00109\u001a\u00020%2\u0006\u00103\u001a\u00020\u0007H\u0014J\u0010\u0010:\u001a\u00020%2\u0006\u00103\u001a\u00020\u0007H\u0014J\u0006\u0010;\u001a\u00020%J\u0006\u0010<\u001a\u00020%J\u0006\u0010=\u001a\u00020%J#\u0010>\u001a\u00020%2\u000e\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001d2\u0006\u00103\u001a\u00020\u0007¢\u0006\u0002\u0010?J\u000e\u0010>\u001a\u00020%2\u0006\u0010@\u001a\u00020\u0007J\b\u0010A\u001a\u00020%H\u0002J\b\u0010B\u001a\u00020%H\u0002J\u0010\u0010C\u001a\u00020\u001b2\u0006\u00104\u001a\u000205H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0004\u0018\u00018\u0001X\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006D"}, mo21895d2 = {"Lcom/huayu/tzc/base/BaseFragment;", "V", "T", "Lcom/huayu/tzc/base/BaseContract$BasePresenter;", "Landroidx/fragment/app/Fragment;", "()V", "REQUEST_CODE_PERMISSION", "", "TAG", "", "getTAG", "()Ljava/lang/String;", "mDialog", "Landroid/app/Dialog;", "mPresenter", "getMPresenter", "()Lcom/huayu/tzc/base/BaseContract$BasePresenter;", "setMPresenter", "(Lcom/huayu/tzc/base/BaseContract$BasePresenter;)V", "Lcom/huayu/tzc/base/BaseContract$BasePresenter;", "mView", "Landroid/view/View;", "getMView", "()Landroid/view/View;", "setMView", "(Landroid/view/View;)V", "checkPermissions", "", "permissions", "", "([Ljava/lang/String;)Z", "getDeniedPermissions", "", "([Ljava/lang/String;)Ljava/util/List;", "getLayoutId", "getPresenter", "initView", "", "onAttach", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onRequestPermissionsResult", "requestCode", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onViewCreated", "view", "permissionFail", "permissionSuccess", "progressDissmiss", "progressShow", "progressShow2", "requestPermission", "([Ljava/lang/String;I)V", "code", "showTipsDialog", "startAppSettings", "verifyPermissions", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: BaseFragment.kt */
public abstract class BaseFragment<V, T extends BaseContract.BasePresenter<V>> extends Fragment {
    private int REQUEST_CODE_PERMISSION = 153;
    @NotNull
    private final String TAG;
    private HashMap _$_findViewCache;
    private Dialog mDialog;
    @Nullable
    private T mPresenter;
    @Nullable
    private View mView;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public abstract int getLayoutId();

    /* access modifiers changed from: protected */
    public abstract T getPresenter();

    /* access modifiers changed from: protected */
    public abstract void initView();

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* access modifiers changed from: protected */
    public void permissionFail(int i) {
    }

    /* access modifiers changed from: protected */
    public void permissionSuccess(int i) {
    }

    public BaseFragment() {
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this.javaClass.simpleName");
        this.TAG = simpleName;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final T getMPresenter() {
        return this.mPresenter;
    }

    /* access modifiers changed from: protected */
    public final void setMPresenter(@Nullable T t) {
        this.mPresenter = t;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final View getMView() {
        return this.mView;
    }

    /* access modifiers changed from: protected */
    public final void setMView(@Nullable View view) {
        this.mView = view;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final String getTAG() {
        return this.TAG;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        this.mView = layoutInflater.inflate(getLayoutId(), viewGroup, false);
        return this.mView;
    }

    public void onAttach(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(MultiLanguages.attach(context));
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mPresenter = getPresenter();
        T t = this.mPresenter;
        if (!(t == null || t == null)) {
            t.attachView(this);
        }
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        this.mDialog = new Dialog(context, C2128R.C2134style.loadingDialogStyle);
        Dialog dialog = this.mDialog;
        if (dialog == null) {
            Intrinsics.throwNpe();
        }
        dialog.setContentView(View.inflate(getContext(), C2128R.C2133layout.dialog_loading, (ViewGroup) null));
    }

    public final void progressDissmiss() {
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            if (dialog == null) {
                Intrinsics.throwNpe();
            }
            dialog.dismiss();
        }
    }

    public final void progressShow() {
        Dialog dialog = this.mDialog;
        if (dialog == null) {
            Intrinsics.throwNpe();
        }
        if (!dialog.isShowing()) {
            Dialog dialog2 = this.mDialog;
            if (dialog2 == null) {
                Intrinsics.throwNpe();
            }
            dialog2.show();
        }
    }

    public final void progressShow2() {
        Dialog dialog = this.mDialog;
        if (dialog == null) {
            Intrinsics.throwNpe();
        }
        dialog.setCancelable(false);
        Dialog dialog2 = this.mDialog;
        if (dialog2 == null) {
            Intrinsics.throwNpe();
        }
        if (!dialog2.isShowing()) {
            Dialog dialog3 = this.mDialog;
            if (dialog3 == null) {
                Intrinsics.throwNpe();
            }
            dialog3.show();
        }
    }

    public final void requestPermission(int i) {
        requestPermission(new String[]{Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE, Permission.CAMERA}, i);
    }

    public final void requestPermission(@NotNull String[] strArr, int i) {
        Intrinsics.checkParameterIsNotNull(strArr, NativeProtocol.RESULT_ARGS_PERMISSIONS);
        this.REQUEST_CODE_PERMISSION = i;
        if (checkPermissions(strArr)) {
            permissionSuccess(this.REQUEST_CODE_PERMISSION);
            return;
        }
        List<String> deniedPermissions = getDeniedPermissions(strArr);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Activity activity2 = activity;
        Object[] array = deniedPermissions.toArray(new String[0]);
        if (array != null) {
            ActivityCompat.requestPermissions(activity2, (String[]) array, this.REQUEST_CODE_PERMISSION);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final boolean checkPermissions(String[] strArr) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        for (String str : strArr) {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                Intrinsics.throwNpe();
            }
            Context context = activity;
            if (str == null) {
                Intrinsics.throwNpe();
            }
            if (ContextCompat.checkSelfPermission(context, str) != 0) {
                return false;
            }
        }
        return true;
    }

    private final List<String> getDeniedPermissions(String[] strArr) {
        List<String> arrayList = new ArrayList<>();
        for (String str : strArr) {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                Intrinsics.throwNpe();
            }
            Context context = activity;
            if (str == null) {
                Intrinsics.throwNpe();
            }
            if (ContextCompat.checkSelfPermission(context, str) == 0) {
                FragmentActivity activity2 = getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                if (!ActivityCompat.shouldShowRequestPermissionRationale(activity2, str)) {
                }
            }
            arrayList.add(str);
        }
        return arrayList;
    }

    public void onRequestPermissionsResult(int i, @NotNull String[] strArr, @NotNull int[] iArr) {
        Intrinsics.checkParameterIsNotNull(strArr, NativeProtocol.RESULT_ARGS_PERMISSIONS);
        Intrinsics.checkParameterIsNotNull(iArr, "grantResults");
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != this.REQUEST_CODE_PERMISSION) {
            return;
        }
        if (verifyPermissions(iArr)) {
            permissionSuccess(this.REQUEST_CODE_PERMISSION);
            return;
        }
        permissionFail(this.REQUEST_CODE_PERMISSION);
        showTipsDialog();
    }

    private final boolean verifyPermissions(int[] iArr) {
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    private final void showTipsDialog() {
        new AlertDialog.Builder(getContext()).setTitle("提示信息").setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心手动进行开启权限。").setNegativeButton("取消", BaseFragment$showTipsDialog$1.INSTANCE).setPositiveButton("确定", new BaseFragment$showTipsDialog$2(this)).show();
    }

    /* access modifiers changed from: private */
    public final void startAppSettings() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        StringBuilder sb = new StringBuilder();
        sb.append("package:");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
        sb.append(activity.getPackageName());
        intent.setData(Uri.parse(sb.toString()));
        startActivity(intent);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, ViewHierarchyConstants.VIEW_KEY);
        super.onViewCreated(view, bundle);
        initView();
    }

    public void onDestroy() {
        super.onDestroy();
        T t = this.mPresenter;
        if (t != null) {
            if (t != null) {
                t.cancelAll();
            }
            T t2 = this.mPresenter;
            if (t2 != null) {
                t2.detachView();
            }
        }
        System.gc();
    }
}
