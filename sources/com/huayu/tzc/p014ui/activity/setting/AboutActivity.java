package com.huayu.tzc.p014ui.activity.setting;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.blankj.utilcode.util.AppUtils;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.Constant;
import com.huayu.tzc.bean.Measure;
import com.huayu.tzc.bean.Member;
import com.huayu.tzc.bean.User;
import com.huayu.tzc.bean.Version;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.BaseAlterDialog;
import com.huayu.tzc.presenter.PrimaryPresenter;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0014J\u001c\u0010\u0010\u001a\u00020\n2\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\fH\u0016J\u0016\u0010\u0014\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u0016J\b\u0010\u0015\u001a\u00020\u0003H\u0016J\u0016\u0010\u0016\u001a\u00020\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00170\fH\u0016J\b\u0010\u0018\u001a\u00020\nH\u0014J\b\u0010\u0019\u001a\u00020\nH\u0002J\u001e\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\fH\u0016J\u0012\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u0010\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\bH\u0002J\u0016\u0010\"\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\fH\u0016¨\u0006#"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/setting/AboutActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$PrimaryView;", "Lcom/huayu/tzc/presenter/PrimaryPresenter;", "()V", "checkVersion", "", "versionName", "", "getAppVersion", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "Lcom/huayu/tzc/bean/Version;", "getLayoutId", "", "getMineList", "loginBean", "", "Lcom/huayu/tzc/bean/Member;", "getNotReadCount", "getPresenter", "getUserInfo", "Lcom/huayu/tzc/bean/User;", "initView", "openGooglePlay", "reporting", "item", "Lcom/huayu/tzc/bean/Measure;", "showError", "e", "", "showVersionDialog", "content", "updateUserInfo", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.setting.AboutActivity */
/* compiled from: AboutActivity.kt */
public final class AboutActivity extends BaseActivity<MainContract.PrimaryView, PrimaryPresenter> implements MainContract.PrimaryView {
    private HashMap _$_findViewCache;

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
        return C2128R.C2133layout.activity_about;
    }

    public void getNotReadCount(@NotNull BaseBean<Integer> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
    }

    public void reporting(@NotNull Measure measure, @NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(measure, "item");
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
    }

    public static final /* synthetic */ PrimaryPresenter access$getMPresenter$p(AboutActivity aboutActivity) {
        return (PrimaryPresenter) aboutActivity.getMPresenter();
    }

    @NotNull
    public PrimaryPresenter getPresenter() {
        return new PrimaryPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.aboutVersion);
        Intrinsics.checkExpressionValueIsNotNull(textView, "aboutVersion");
        textView.setText(getString(C2128R.string.version) + "  V" + AppUtils.getAppVersionName());
        Button button = (Button) _$_findCachedViewById(C2128R.C2131id.aboutUpdate);
        button.setOnClickListener(new AboutActivity$initView$$inlined$singleClick$1(button, 800, this));
    }

    public void getAppVersion(@NotNull BaseBean<Version> baseBean) {
        String dupdatecontent;
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            Version data = baseBean.getData();
            if (data == null) {
                Intrinsics.throwNpe();
            }
            if (!checkVersion(data.getVersion())) {
                Version data2 = baseBean.getData();
                if (data2 != null && (dupdatecontent = data2.getDupdatecontent()) != null) {
                    showVersionDialog(dupdatecontent);
                    return;
                }
                return;
            }
            ToastUtils.show((CharSequence) getString(C2128R.string.new_version));
        }
    }

    private final void showVersionDialog(String str) {
        BaseAlterDialog baseAlterDialog = new BaseAlterDialog((Context) this, str);
        baseAlterDialog.setTitle(getString(C2128R.string.update));
        baseAlterDialog.setmOkBtn(getString(C2128R.string.update), new AboutActivity$showVersionDialog$1(this, baseAlterDialog));
        baseAlterDialog.setmCancelBtn(getString(C2128R.string.common_cancel), new AboutActivity$showVersionDialog$2(baseAlterDialog));
        baseAlterDialog.show();
    }

    /* access modifiers changed from: private */
    public final void openGooglePlay() {
        if (!AppUtils.isAppInstalled("com.google.market")) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(Constant.googleplay));
            startActivity(Intent.createChooser(intent, getString(C2128R.string.select_browser)));
            return;
        }
        Intent intent2 = new Intent("android.intent.action.VIEW");
        intent2.setData(Uri.parse("market://details?id=com.huayu.tzc&target=market&from=met"));
        startActivity(intent2);
    }

    public void updateUserInfo(@NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
    }

    private final boolean checkVersion(String str) {
        List split$default = StringsKt.split$default((CharSequence) str, new String[]{"\\."}, false, 0, 6, (Object) null);
        String appVersionName = AppUtils.getAppVersionName();
        Intrinsics.checkExpressionValueIsNotNull(appVersionName, "AppUtils.getAppVersionName()");
        List split$default2 = StringsKt.split$default((CharSequence) appVersionName, new String[]{"\\."}, false, 0, 6, (Object) null);
        int size = split$default2.size();
        for (int i = 0; i < size; i++) {
            if (((String) split$default2.get(i)).compareTo((String) split$default.get(i)) < 0) {
                return false;
            }
        }
        return true;
    }

    public void getUserInfo(@NotNull BaseBean<User> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "loginBean");
        progressDissmiss();
    }

    public void getMineList(@NotNull BaseBean<List<Member>> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "loginBean");
        progressDissmiss();
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }
}
