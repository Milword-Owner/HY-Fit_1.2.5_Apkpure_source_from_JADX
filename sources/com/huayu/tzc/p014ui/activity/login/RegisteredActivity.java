package com.huayu.tzc.p014ui.activity.login;

import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import kotlin.jvm.internal.Intrinsics;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0002J\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0016J\b\u0010\u000e\u001a\u00020\u000bH\u0014J\b\u0010\u000f\u001a\u00020\u0003H\u0016J\b\u0010\u0010\u001a\u00020\bH\u0002J\b\u0010\u0011\u001a\u00020\bH\u0014J\u0016\u0010\u0012\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00130\rH\u0016J\u0012\u0010\u0014\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0016\u0010\u0017\u001a\u00020\b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/login/RegisteredActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$LoginView;", "Lcom/huayu/tzc/presenter/LoginPresenter;", "()V", "uid", "", "checkEdit", "", "getData", "flag", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "getLayoutId", "getPresenter", "initClick", "initView", "login", "Lcom/huayu/tzc/bean/LoginBean;", "showError", "e", "", "thirdLogin", "loginBean", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.login.RegisteredActivity */
/* compiled from: RegisteredActivity.kt */
public final class RegisteredActivity extends BaseActivity<MainContract.LoginView, LoginPresenter> implements MainContract.LoginView {
    private HashMap _$_findViewCache;
    private String uid = "";

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
        return C2128R.C2133layout.activity_registered;
    }

    public void thirdLogin(@NotNull BaseBean<LoginBean> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "loginBean");
    }

    @NotNull
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        if (getIntent().getBooleanExtra("bind", false)) {
            TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.registerSignIn);
            Intrinsics.checkExpressionValueIsNotNull(textView, "registerSignIn");
            textView.setVisibility(8);
        }
        initClick();
    }

    private final void initClick() {
        ((MyImageView) _$_findCachedViewById(C2128R.C2131id.registerPassShow)).setOnClickListener(new RegisteredActivity$initClick$1(this));
        ((MyImageView) _$_findCachedViewById(C2128R.C2131id.registerRepeatPassShow)).setOnClickListener(new RegisteredActivity$initClick$2(this));
        ((MyImageView) _$_findCachedViewById(C2128R.C2131id.registerYs)).setOnClickListener(new RegisteredActivity$initClick$3(this));
        TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.registerYsText);
        textView.setOnClickListener(new RegisteredActivity$initClick$$inlined$singleClick$1(textView, 800, this));
        Button button = (Button) _$_findCachedViewById(C2128R.C2131id.registerBt);
        button.setOnClickListener(new RegisteredActivity$initClick$$inlined$singleClick$2(button, 800, this));
        TextView textView2 = (TextView) _$_findCachedViewById(C2128R.C2131id.registerSignIn);
        textView2.setOnClickListener(new RegisteredActivity$initClick$$inlined$singleClick$3(textView2, 800, this));
        ((ImageView) _$_findCachedViewById(C2128R.C2131id.registerBack)).setOnClickListener(new RegisteredActivity$initClick$7(this));
    }

    /* access modifiers changed from: private */
    public final void checkEdit() {
        MyEditTextView myEditTextView = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.registerMailEdit);
        Intrinsics.checkExpressionValueIsNotNull(myEditTextView, "registerMailEdit");
        CharSequence text = myEditTextView.getText();
        boolean z = false;
        if (!(text == null || text.length() == 0)) {
            MyEditTextView myEditTextView2 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.registerPassEdit);
            Intrinsics.checkExpressionValueIsNotNull(myEditTextView2, "registerPassEdit");
            CharSequence text2 = myEditTextView2.getText();
            if (!(text2 == null || text2.length() == 0)) {
                MyEditTextView myEditTextView3 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.registerRepeatPassEdit);
                Intrinsics.checkExpressionValueIsNotNull(myEditTextView3, "registerRepeatPassEdit");
                CharSequence text3 = myEditTextView3.getText();
                if (text3 == null || text3.length() == 0) {
                    z = true;
                }
                if (!z) {
                    MyEditTextView myEditTextView4 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.registerPassEdit);
                    Intrinsics.checkExpressionValueIsNotNull(myEditTextView4, "registerPassEdit");
                    Editable text4 = myEditTextView4.getText();
                    if (text4 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (text4.length() < 6) {
                        ToastUtils.show((CharSequence) getString(C2128R.string.pass_six));
                        return;
                    }
                    MyEditTextView myEditTextView5 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.registerRepeatPassEdit);
                    Intrinsics.checkExpressionValueIsNotNull(myEditTextView5, "registerRepeatPassEdit");
                    String valueOf = String.valueOf(myEditTextView5.getText());
                    MyEditTextView myEditTextView6 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.registerPassEdit);
                    Intrinsics.checkExpressionValueIsNotNull(myEditTextView6, "registerPassEdit");
                    if (!Intrinsics.areEqual((Object) valueOf, (Object) String.valueOf(myEditTextView6.getText()))) {
                        ToastUtils.show((CharSequence) getString(C2128R.string.register_password_again_error));
                        return;
                    }
                    MyImageView myImageView = (MyImageView) _$_findCachedViewById(C2128R.C2131id.registerYs);
                    Intrinsics.checkExpressionValueIsNotNull(myImageView, "registerYs");
                    if (!myImageView.isChecked()) {
                        ToastUtils.show((CharSequence) getString(C2128R.string.register_check_agreement));
                        return;
                    }
                    progressShow();
                    String stringExtra = getIntent().getStringExtra(Config.CUSTOM_USER_ID);
                    if (stringExtra == null) {
                        stringExtra = "";
                    }
                    this.uid = stringExtra;
                    FormBody.Builder builder = new FormBody.Builder();
                    MyEditTextView myEditTextView7 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.registerMailEdit);
                    Intrinsics.checkExpressionValueIsNotNull(myEditTextView7, "registerMailEdit");
                    FormBody.Builder add = builder.add("email", String.valueOf(myEditTextView7.getText()));
                    MyEditTextView myEditTextView8 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.registerPassEdit);
                    Intrinsics.checkExpressionValueIsNotNull(myEditTextView8, "registerPassEdit");
                    FormBody build = add.add("u_pwd", String.valueOf(myEditTextView8.getText())).add("facebookid", this.uid).build();
                    Intrinsics.checkExpressionValueIsNotNull(build, "FormBody.Builder()\n     …uid)\n            .build()");
                    RequestBody requestBody = build;
                    LoginPresenter loginPresenter = (LoginPresenter) getMPresenter();
                    if (loginPresenter != null) {
                        loginPresenter.register(requestBody);
                        return;
                    }
                    return;
                }
            }
        }
        ToastUtils.show((CharSequence) getString(C2128R.string.nonull));
    }

    public void login(@NotNull BaseBean<LoginBean> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            MMKV defaultMMKV = MMKV.defaultMMKV();
            MyEditTextView myEditTextView = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.registerMailEdit);
            Intrinsics.checkExpressionValueIsNotNull(myEditTextView, "registerMailEdit");
            defaultMMKV.encode("mail", String.valueOf(myEditTextView.getText()));
            LoginBean data = baseBean.getData();
            defaultMMKV.encode("token", data != null ? data.getToken() : null);
            MyEditTextView myEditTextView2 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.registerPassEdit);
            Intrinsics.checkExpressionValueIsNotNull(myEditTextView2, "registerPassEdit");
            defaultMMKV.encode("password", String.valueOf(myEditTextView2.getText()));
            startActivity(new Intent(this, MainActivity.class).setFlags(268468224));
            finish();
        }
    }

    public void getData(int i, @NotNull BaseBean<String> baseBean) {
        LoginPresenter loginPresenter;
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess() && (loginPresenter = (LoginPresenter) getMPresenter()) != null) {
            MyEditTextView myEditTextView = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.registerMailEdit);
            Intrinsics.checkExpressionValueIsNotNull(myEditTextView, "registerMailEdit");
            String valueOf = String.valueOf(myEditTextView.getText());
            MyEditTextView myEditTextView2 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.registerPassEdit);
            Intrinsics.checkExpressionValueIsNotNull(myEditTextView2, "registerPassEdit");
            loginPresenter.login(valueOf, String.valueOf(myEditTextView2.getText()));
        }
        ToastUtils.show((CharSequence) baseBean.getMeta().getMessage());
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }
}
