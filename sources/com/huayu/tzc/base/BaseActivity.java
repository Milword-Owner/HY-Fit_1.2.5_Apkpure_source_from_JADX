package com.huayu.tzc.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.baidu.mobstat.Config;
import com.facebook.internal.NativeProtocol;
import com.hjq.language.MultiLanguages;
import com.hjq.permissions.Permission;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.base.BaseContract;
import com.huayu.tzc.base.BaseContract.BasePresenter;
import com.huayu.tzc.customview.MyImageView;
import com.huayu.tzc.statusbar.StatusBarUtil;
import com.huayu.tzc.utils.AppManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u000e\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\tH\u0016J\u0012\u0010\u0019\u001a\u00020\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\u001b\u0010\u001c\u001a\u00020\r2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u001eH\u0002¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\"H\u0016J\u0018\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0016J!\u0010(\u001a\b\u0012\u0004\u0012\u00020\t0)2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u001eH\u0002¢\u0006\u0002\u0010*J\b\u0010+\u001a\u00020\u0007H$J\r\u0010,\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0012J\u0012\u0010-\u001a\u00020\u00172\b\u0010.\u001a\u0004\u0018\u00010/H\u0002J\b\u00100\u001a\u00020\u0017H$J\u001a\u00101\u001a\u00020\r2\b\u00102\u001a\u0004\u0018\u0001032\u0006\u00104\u001a\u00020\"H\u0002J\u0012\u00105\u001a\u00020\u00172\b\u00106\u001a\u0004\u0018\u000107H\u0014J\b\u00108\u001a\u00020\u0017H\u0014J-\u00109\u001a\u00020\u00172\u0006\u0010:\u001a\u00020\u00072\u000e\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001e2\u0006\u0010;\u001a\u00020<H\u0016¢\u0006\u0002\u0010=J\u0010\u0010>\u001a\u00020\u00172\u0006\u0010:\u001a\u00020\u0007H\u0016J\u0010\u0010?\u001a\u00020\u00172\u0006\u0010:\u001a\u00020\u0007H\u0016J\b\u0010@\u001a\u00020\u0017H\u0016J\b\u0010A\u001a\u00020\u0017H\u0016J\b\u0010B\u001a\u00020\u0017H\u0016J#\u0010C\u001a\u00020\u00172\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u001e2\u0006\u0010:\u001a\u00020\u0007H\u0016¢\u0006\u0002\u0010DJ\u0010\u0010C\u001a\u00020\u00172\u0006\u0010E\u001a\u00020\u0007H\u0016J\b\u0010F\u001a\u00020\u0017H\u0016J\u0018\u0010G\u001a\u00020\u00172\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020'H\u0016J\b\u0010K\u001a\u00020\u0017H\u0002J\b\u0010L\u001a\u00020\u0017H\u0002J\u0010\u0010M\u001a\u00020\r2\u0006\u0010;\u001a\u00020<H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u0004\u0018\u00018\u0001X\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006N"}, mo21895d2 = {"Lcom/huayu/tzc/base/BaseActivity;", "V", "T", "Lcom/huayu/tzc/base/BaseContract$BasePresenter;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "REQUEST_CODE_PERMISSION", "", "TAG", "", "getTAG", "()Ljava/lang/String;", "isShowLog", "", "mDialog", "Landroid/app/Dialog;", "mPresenter", "getMPresenter", "()Lcom/huayu/tzc/base/BaseContract$BasePresenter;", "setMPresenter", "(Lcom/huayu/tzc/base/BaseContract$BasePresenter;)V", "Lcom/huayu/tzc/base/BaseContract$BasePresenter;", "LoggUtil", "", "s", "attachBaseContext", "newBase", "Landroid/content/Context;", "checkPermissions", "permissions", "", "([Ljava/lang/String;)Z", "dispatchTouchEvent", "ev", "Landroid/view/MotionEvent;", "eyeEditText", "imgView", "Lcom/huayu/tzc/customview/MyImageView;", "ediTextView", "Landroid/widget/EditText;", "getDeniedPermissions", "", "([Ljava/lang/String;)Ljava/util/List;", "getLayoutId", "getPresenter", "hideKeyboard", "token", "Landroid/os/IBinder;", "initView", "isShouldHideKeyboard", "v", "Landroid/view/View;", "event", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onRequestPermissionsResult", "requestCode", "grantResults", "", "(I[Ljava/lang/String;[I)V", "permissionFail", "permissionSuccess", "progressDissmiss", "progressShow", "progressShow2", "requestPermission", "([Ljava/lang/String;I)V", "code", "setStatusBarColor", "showSoftInputFromWindow", "activity", "Landroid/app/Activity;", "editText", "showTipsDialog", "startAppSettings", "verifyPermissions", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: BaseActivity.kt */
public abstract class BaseActivity<V, T extends BaseContract.BasePresenter<V>> extends AppCompatActivity {
    private int REQUEST_CODE_PERMISSION = 153;
    @NotNull
    private final String TAG;
    private HashMap _$_findViewCache;
    private boolean isShowLog = true;
    private Dialog mDialog;
    @Nullable
    private T mPresenter;

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
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public abstract int getLayoutId();

    @NotNull
    public abstract T getPresenter();

    /* access modifiers changed from: protected */
    public abstract void initView();

    public void permissionFail(int i) {
    }

    public void permissionSuccess(int i) {
    }

    public BaseActivity() {
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
    @NotNull
    public final String getTAG() {
        return this.TAG;
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(@Nullable Context context) {
        super.attachBaseContext(MultiLanguages.attach(context));
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mPresenter = getPresenter();
        T t = this.mPresenter;
        if (t != null) {
            t.attachView(this);
        }
        Context context = this;
        this.mDialog = new Dialog(context, C2128R.C2134style.loadingDialogStyle);
        Dialog dialog = this.mDialog;
        if (dialog == null) {
            Intrinsics.throwNpe();
        }
        dialog.setContentView(View.inflate(context, C2128R.C2133layout.dialog_loading, (ViewGroup) null));
        Activity activity = this;
        StatusBarUtil.setRootViewFitsSystemWindows(activity, true);
        StatusBarUtil.setTranslucentStatus(activity);
        if (!StatusBarUtil.setStatusBarDarkTheme(activity, true)) {
            StatusBarUtil.setStatusBarColor(activity, Color.parseColor("#ffffff"));
        }
        AppManager.addActivity(activity);
        setContentView(getLayoutId());
        setStatusBarColor();
        initView();
    }

    public void LoggUtil(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "s");
        if (this.isShowLog) {
            Log.e(this.TAG, str);
        }
    }

    public void setStatusBarColor() {
        StatusBarUtil.setStatusBarColor(this, Color.parseColor("#ffffff"));
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        T t = this.mPresenter;
        if (t != null) {
            if (t == null) {
                Intrinsics.throwNpe();
            }
            t.cancelAll();
            T t2 = this.mPresenter;
            if (t2 == null) {
                Intrinsics.throwNpe();
            }
            t2.detachView();
        }
        this.mDialog = null;
        System.gc();
        AppManager.finishActivity(this);
    }

    public void progressDissmiss() {
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            if (dialog == null) {
                Intrinsics.throwNpe();
            }
            dialog.dismiss();
        }
    }

    public void progressShow() {
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

    public void requestPermission(int i) {
        requestPermission(new String[]{Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE, Permission.CAMERA}, i);
    }

    public void progressShow2() {
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

    public void eyeEditText(@NotNull MyImageView myImageView, @NotNull EditText editText) {
        Intrinsics.checkParameterIsNotNull(myImageView, "imgView");
        Intrinsics.checkParameterIsNotNull(editText, "ediTextView");
        if (myImageView.isChecked()) {
            myImageView.setChecked(false);
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            myImageView.setChecked(true);
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        Editable text = editText.getText();
        if (text == null) {
            Intrinsics.throwNpe();
        }
        editText.setSelection(text.length());
    }

    public void requestPermission(@NotNull String[] strArr, int i) {
        Intrinsics.checkParameterIsNotNull(strArr, NativeProtocol.RESULT_ARGS_PERMISSIONS);
        this.REQUEST_CODE_PERMISSION = i;
        if (checkPermissions(strArr)) {
            permissionSuccess(this.REQUEST_CODE_PERMISSION);
            return;
        }
        Activity activity = this;
        Object[] array = getDeniedPermissions(strArr).toArray(new String[0]);
        if (array != null) {
            ActivityCompat.requestPermissions(activity, (String[]) array, this.REQUEST_CODE_PERMISSION);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final boolean checkPermissions(String[] strArr) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        for (String checkSelfPermission : strArr) {
            if (ContextCompat.checkSelfPermission(this, checkSelfPermission) != 0) {
                return false;
            }
        }
        return true;
    }

    private final List<String> getDeniedPermissions(String[] strArr) {
        List<String> arrayList = new ArrayList<>();
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(this, str) != 0 || ActivityCompat.shouldShowRequestPermissionRationale(this, str)) {
                arrayList.add(str);
            }
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
        new AlertDialog.Builder(this).setTitle("提示信息").setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心手动进行开启权限。").setNegativeButton("取消", BaseActivity$showTipsDialog$1.INSTANCE).setPositiveButton("确定", new BaseActivity$showTipsDialog$2(this)).show();
    }

    /* access modifiers changed from: private */
    public final void startAppSettings() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    public boolean dispatchTouchEvent(@NotNull MotionEvent motionEvent) {
        Intrinsics.checkParameterIsNotNull(motionEvent, Config.EVENT_PART);
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (isShouldHideKeyboard(currentFocus, motionEvent)) {
                if (currentFocus == null) {
                    Intrinsics.throwNpe();
                }
                hideKeyboard(currentFocus.getWindowToken());
                currentFocus.clearFocus();
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private final boolean isShouldHideKeyboard(View view, MotionEvent motionEvent) {
        if (!(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        int height = view.getHeight() + i2;
        int width = view.getWidth() + i;
        if (motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) width) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) height)) {
            return true;
        }
        return false;
    }

    public void showSoftInputFromWindow(@NotNull Activity activity, @NotNull EditText editText) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(editText, "editText");
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(5);
    }

    private final void hideKeyboard(IBinder iBinder) {
        if (iBinder != null) {
            Object systemService = getSystemService("input_method");
            if (systemService != null) {
                ((InputMethodManager) systemService).hideSoftInputFromWindow(iBinder, 2);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        }
    }
}
