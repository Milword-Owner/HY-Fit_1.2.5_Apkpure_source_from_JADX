package com.huayu.tzc.p014ui.activity.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.MainActivity;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.bean.LoginBean;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.MyEditTextView;
import com.huayu.tzc.customview.MyImageView;
import com.huayu.tzc.presenter.LoginPresenter;
import com.tencent.mmkv.MMKV;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0002J\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0016J\b\u0010\u000e\u001a\u00020\u000bH\u0014J\b\u0010\u000f\u001a\u00020\u0003H\u0016J\b\u0010\u0010\u001a\u00020\bH\u0002J\b\u0010\u0011\u001a\u00020\bH\u0014J\u0016\u0010\u0012\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00130\rH\u0016J\u0012\u0010\u0014\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0016\u0010\u0017\u001a\u00020\b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/login/BindLoginActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$LoginView;", "Lcom/huayu/tzc/presenter/LoginPresenter;", "()V", "uid", "", "checkEdit", "", "getData", "flag", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "getLayoutId", "getPresenter", "initClick", "initView", "login", "Lcom/huayu/tzc/bean/LoginBean;", "showError", "e", "", "thirdLogin", "loginBean", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.login.BindLoginActivity */
/* compiled from: BindLoginActivity.kt */
public final class BindLoginActivity extends BaseActivity<MainContract.LoginView, LoginPresenter> implements MainContract.LoginView {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public String uid = "";

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
        return C2128R.C2133layout.activity_bind_login;
    }

    @NotNull
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        String stringExtra = getIntent().getStringExtra(Config.CUSTOM_USER_ID);
        if (stringExtra != null) {
            if (stringExtra == null) {
                stringExtra = "";
            }
            this.uid = stringExtra;
            ((MyEditTextView) _$_findCachedViewById(C2128R.C2131id.loginMailEdit)).setText(MMKV.defaultMMKV().decodeString("mail", ""));
            ((MyEditTextView) _$_findCachedViewById(C2128R.C2131id.loginPassEdit)).setText(MMKV.defaultMMKV().decodeString("password", ""));
            initClick();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
    }

    private final void initClick() {
        ((MyImageView) _$_findCachedViewById(C2128R.C2131id.loginPassShow)).setOnClickListener(new BindLoginActivity$initClick$1(this));
        ((MyImageView) _$_findCachedViewById(C2128R.C2131id.loginRemember)).setOnClickListener(new BindLoginActivity$initClick$2(this));
        TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.loginForget);
        textView.setOnClickListener(new BindLoginActivity$initClick$$inlined$singleClick$1(textView, 800, this));
        Button button = (Button) _$_findCachedViewById(C2128R.C2131id.loginBt);
        button.setOnClickListener(new BindLoginActivity$initClick$$inlined$singleClick$2(button, 800, this));
    }

    /* access modifiers changed from: private */
    public final void checkEdit() {
        MyEditTextView myEditTextView = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.loginMailEdit);
        Intrinsics.checkExpressionValueIsNotNull(myEditTextView, "loginMailEdit");
        CharSequence text = myEditTextView.getText();
        boolean z = false;
        if (!(text == null || text.length() == 0)) {
            MyEditTextView myEditTextView2 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.loginMailEdit);
            Intrinsics.checkExpressionValueIsNotNull(myEditTextView2, "loginMailEdit");
            CharSequence text2 = myEditTextView2.getText();
            if (text2 == null || text2.length() == 0) {
                z = true;
            }
            if (!z) {
                LoginPresenter loginPresenter = (LoginPresenter) getMPresenter();
                if (loginPresenter != null) {
                    String stringExtra = getIntent().getStringExtra(Config.CUSTOM_USER_ID);
                    MyEditTextView myEditTextView3 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.loginMailEdit);
                    Intrinsics.checkExpressionValueIsNotNull(myEditTextView3, "loginMailEdit");
                    String valueOf = String.valueOf(myEditTextView3.getText());
                    MyEditTextView myEditTextView4 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.loginPassEdit);
                    Intrinsics.checkExpressionValueIsNotNull(myEditTextView4, "loginPassEdit");
                    loginPresenter.bindThird(stringExtra, valueOf, String.valueOf(myEditTextView4.getText()));
                }
                progressShow();
                return;
            }
        }
        ToastUtils.show((CharSequence) "请填写完整");
    }

    public void login(@NotNull BaseBean<LoginBean> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            MMKV defaultMMKV = MMKV.defaultMMKV();
            MyEditTextView myEditTextView = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.loginMailEdit);
            Intrinsics.checkExpressionValueIsNotNull(myEditTextView, "loginMailEdit");
            defaultMMKV.encode("mail", String.valueOf(myEditTextView.getText()));
            LoginBean data = baseBean.getData();
            defaultMMKV.encode("token", data != null ? data.getToken() : null);
            MyImageView myImageView = (MyImageView) _$_findCachedViewById(C2128R.C2131id.loginRemember);
            Intrinsics.checkExpressionValueIsNotNull(myImageView, "loginRemember");
            if (myImageView.isChecked()) {
                MyEditTextView myEditTextView2 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.loginPassEdit);
                Intrinsics.checkExpressionValueIsNotNull(myEditTextView2, "loginPassEdit");
                defaultMMKV.encode("password", String.valueOf(myEditTextView2.getText()));
            } else {
                defaultMMKV.encode("password", "");
            }
            startActivity(new Intent(this, MainActivity.class).setFlags(268468224));
            finish();
        }
        ToastUtils.show((CharSequence) baseBean.getMeta().getMessage());
    }

    public void getData(int i, @NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
    }

    public void thirdLogin(@NotNull BaseBean<LoginBean> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "loginBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            progressShow();
            LoginPresenter loginPresenter = (LoginPresenter) getMPresenter();
            if (loginPresenter != null) {
                MyEditTextView myEditTextView = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.loginMailEdit);
                Intrinsics.checkExpressionValueIsNotNull(myEditTextView, "loginMailEdit");
                String valueOf = String.valueOf(myEditTextView.getText());
                MyEditTextView myEditTextView2 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.loginPassEdit);
                Intrinsics.checkExpressionValueIsNotNull(myEditTextView2, "loginPassEdit");
                loginPresenter.login(valueOf, String.valueOf(myEditTextView2.getText()));
            }
        }
        ToastUtils.show((CharSequence) baseBean.getMeta().getMessage());
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }
}
