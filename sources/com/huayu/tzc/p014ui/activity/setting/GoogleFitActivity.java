package com.huayu.tzc.p014ui.activity.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Switch;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataType;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.presenter.NotPresenter;
import com.tencent.mmkv.MMKV;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\b\u0010\u000f\u001a\u00020\nH\u0014J\"\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000¨\u0006\u0015"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/setting/GoogleFitActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$View;", "Lcom/huayu/tzc/presenter/NotPresenter;", "()V", "connectGoogle", "", "fitnessOptions", "Lcom/google/android/gms/fitness/FitnessOptions;", "accessGoogleFit", "", "connectGoogleFit", "getLayoutId", "", "getPresenter", "initView", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.setting.GoogleFitActivity */
/* compiled from: GoogleFitActivity.kt */
public final class GoogleFitActivity extends BaseActivity<MainContract.View, NotPresenter> {
    private HashMap _$_findViewCache;
    private boolean connectGoogle;
    private FitnessOptions fitnessOptions;

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
    public int getLayoutId() {
        return C2128R.C2133layout.activity_google_fit;
    }

    @NotNull
    public NotPresenter getPresenter() {
        return new NotPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.connectGoogle = MMKV.defaultMMKV().decodeBool("google", false);
        Switch switchR = (Switch) _$_findCachedViewById(C2128R.C2131id.googleSwitch);
        Intrinsics.checkExpressionValueIsNotNull(switchR, "googleSwitch");
        switchR.setChecked(this.connectGoogle);
        ((Switch) _$_findCachedViewById(C2128R.C2131id.googleSwitch)).setOnCheckedChangeListener(new GoogleFitActivity$initView$1(this));
    }

    /* access modifiers changed from: private */
    public final void connectGoogleFit() {
        FitnessOptions build = FitnessOptions.builder().addDataType(DataType.TYPE_WEIGHT, 1).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "FitnessOptions.builder()…ITE)\n            .build()");
        this.fitnessOptions = build;
        Context context = this;
        FitnessOptions fitnessOptions2 = this.fitnessOptions;
        if (fitnessOptions2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessOptions");
        }
        GoogleSignInAccount accountForExtension = GoogleSignIn.getAccountForExtension(context, fitnessOptions2);
        Intrinsics.checkExpressionValueIsNotNull(accountForExtension, "GoogleSignIn.getAccountF…sion(this,fitnessOptions)");
        FitnessOptions fitnessOptions3 = this.fitnessOptions;
        if (fitnessOptions3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessOptions");
        }
        if (!GoogleSignIn.hasPermissions(accountForExtension, (GoogleSignInOptionsExtension) fitnessOptions3)) {
            Switch switchR = (Switch) _$_findCachedViewById(C2128R.C2131id.googleSwitch);
            Intrinsics.checkExpressionValueIsNotNull(switchR, "googleSwitch");
            switchR.setChecked(false);
            Activity activity = this;
            FitnessOptions fitnessOptions4 = this.fitnessOptions;
            if (fitnessOptions4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessOptions");
            }
            GoogleSignIn.requestPermissions(activity, 817, accountForExtension, (GoogleSignInOptionsExtension) fitnessOptions4);
            return;
        }
        accessGoogleFit();
    }

    private final void accessGoogleFit() {
        MMKV.defaultMMKV().encode("google", true);
        Switch switchR = (Switch) _$_findCachedViewById(C2128R.C2131id.googleSwitch);
        Intrinsics.checkExpressionValueIsNotNull(switchR, "googleSwitch");
        switchR.setChecked(true);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 817) {
            accessGoogleFit();
        } else {
            ToastUtils.show((CharSequence) getString(C2128R.string.google_sq));
        }
    }
}
