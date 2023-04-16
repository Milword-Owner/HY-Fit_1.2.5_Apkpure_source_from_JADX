package com.huayu.tzc.p014ui.activity.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.baidu.mobstat.Config;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.bean.LoginBean;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.MyEditTextView;
import com.huayu.tzc.presenter.LoginPresenter;
import com.huayu.tzc.utils.DateUtil;
import com.huayu.tzc.utils.TimeCount;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0002J\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0016J\b\u0010\u000e\u001a\u00020\u000bH\u0014J\b\u0010\u000f\u001a\u00020\u0003H\u0016J\b\u0010\u0010\u001a\u00020\bH\u0002J\b\u0010\u0011\u001a\u00020\bH\u0014J\u0016\u0010\u0012\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00130\rH\u0016J\b\u0010\u0014\u001a\u00020\bH\u0002J\u0012\u0010\u0015\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0016\u0010\u0018\u001a\u00020\b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00130\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/login/CodeActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$LoginView;", "Lcom/huayu/tzc/presenter/LoginPresenter;", "()V", "mail", "", "checkCode", "", "getData", "flag", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "getLayoutId", "getPresenter", "initClick", "initView", "login", "Lcom/huayu/tzc/bean/LoginBean;", "sendCode", "showError", "e", "", "thirdLogin", "loginBean", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.login.CodeActivity */
/* compiled from: CodeActivity.kt */
public final class CodeActivity extends BaseActivity<MainContract.LoginView, LoginPresenter> implements MainContract.LoginView {
    private HashMap _$_findViewCache;
    private String mail = "";

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
        return C2128R.C2133layout.activity_code;
    }

    @NotNull
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        initClick();
    }

    private final void initClick() {
        ((ImageView) _$_findCachedViewById(C2128R.C2131id.codeBack)).setOnClickListener(new CodeActivity$initClick$1(this));
        Button button = (Button) _$_findCachedViewById(C2128R.C2131id.codeSend);
        button.setOnClickListener(new CodeActivity$initClick$$inlined$singleClick$1(button, 800, this));
        Button button2 = (Button) _$_findCachedViewById(C2128R.C2131id.codeNext);
        button2.setOnClickListener(new CodeActivity$initClick$$inlined$singleClick$2(button2, 800, this));
    }

    /* access modifiers changed from: private */
    public final void checkCode() {
        MyEditTextView myEditTextView = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.codeEdit);
        Intrinsics.checkExpressionValueIsNotNull(myEditTextView, "codeEdit");
        CharSequence text = myEditTextView.getText();
        boolean z = false;
        if (text == null || text.length() == 0) {
            ToastUtils.show((CharSequence) getString(C2128R.string.forget_code_empty));
            return;
        }
        CharSequence charSequence = this.mail;
        if (charSequence == null || charSequence.length() == 0) {
            z = true;
        }
        if (z) {
            ToastUtils.show((CharSequence) getString(C2128R.string.forget_get_code));
            return;
        }
        progressShow2();
        LoginPresenter loginPresenter = (LoginPresenter) getMPresenter();
        if (loginPresenter != null) {
            String str = this.mail;
            MyEditTextView myEditTextView2 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.codeEdit);
            Intrinsics.checkExpressionValueIsNotNull(myEditTextView2, "codeEdit");
            loginPresenter.checkCode(str, String.valueOf(myEditTextView2.getText()));
        }
    }

    /* access modifiers changed from: private */
    public final void sendCode() {
        MyEditTextView myEditTextView = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.codeMailEdit);
        Intrinsics.checkExpressionValueIsNotNull(myEditTextView, "codeMailEdit");
        CharSequence text = myEditTextView.getText();
        if (text == null || text.length() == 0) {
            ToastUtils.show((CharSequence) getString(C2128R.string.login_email_hint));
            return;
        }
        progressShow2();
        LoginPresenter loginPresenter = (LoginPresenter) getMPresenter();
        if (loginPresenter != null) {
            MyEditTextView myEditTextView2 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.codeMailEdit);
            Intrinsics.checkExpressionValueIsNotNull(myEditTextView2, "codeMailEdit");
            loginPresenter.getMailCode(String.valueOf(myEditTextView2.getText()));
        }
    }

    public void login(@NotNull BaseBean<LoginBean> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
    }

    public void getData(int i, @NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            if (i == 2) {
                MyEditTextView myEditTextView = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.codeMailEdit);
                Intrinsics.checkExpressionValueIsNotNull(myEditTextView, "codeMailEdit");
                this.mail = String.valueOf(myEditTextView.getText());
                new TimeCount(this, (Button) _$_findCachedViewById(C2128R.C2131id.codeSend), DateUtil.MINUTE_MILL_SECONDS, 1000).start();
            } else {
                startActivity(new Intent(this, ForgetActivity.class).putExtra("mail", this.mail).putExtra(Config.CUSTOM_USER_ID, getIntent().getStringExtra(Config.CUSTOM_USER_ID)));
            }
        }
        ToastUtils.show((CharSequence) baseBean.getMeta().getMessage());
    }

    public void thirdLogin(@NotNull BaseBean<LoginBean> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "loginBean");
        progressDissmiss();
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }
}
