package com.huayu.tzc.p014ui.activity.login;

import android.content.Intent;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
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
import com.huayu.tzc.utils.AppManager;
import com.tencent.mmkv.MMKV;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p005cn.sharesdk.facebook.Facebook;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.ShareSDK;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0002J\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0014J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\b\u0010\u0014\u001a\u00020\fH\u0002J\b\u0010\u0015\u001a\u00020\fH\u0014J\u0016\u0010\u0016\u001a\u00020\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00170\u0011H\u0016J\b\u0010\u0018\u001a\u00020\fH\u0002J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0012\u0010\u001e\u001a\u00020\f2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0016\u0010!\u001a\u00020\f2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00170\u0011H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/login/LoginActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$LoginView;", "Lcom/huayu/tzc/presenter/LoginPresenter;", "()V", "exitTime", "", "mail", "", "password", "uid", "checkEdit", "", "getData", "flag", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "getLayoutId", "getPresenter", "initClick", "initView", "login", "Lcom/huayu/tzc/bean/LoginBean;", "loginFacebook", "onKeyDown", "", "keyCode", "event", "Landroid/view/KeyEvent;", "showError", "e", "", "thirdLogin", "loginBean", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.login.LoginActivity */
/* compiled from: LoginActivity.kt */
public final class LoginActivity extends BaseActivity<MainContract.LoginView, LoginPresenter> implements MainContract.LoginView {
    private HashMap _$_findViewCache;
    private long exitTime;
    private String mail = "";
    private String password = "";
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

    public void getData(int i, @NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C2128R.C2133layout.activity_login;
    }

    public static final /* synthetic */ LoginPresenter access$getMPresenter$p(LoginActivity loginActivity) {
        return (LoginPresenter) loginActivity.getMPresenter();
    }

    @NotNull
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        String decodeString = MMKV.defaultMMKV().decodeString("mail", "");
        Intrinsics.checkExpressionValueIsNotNull(decodeString, "MMKV.defaultMMKV().decodeString(\"mail\",\"\")");
        this.mail = decodeString;
        String decodeString2 = MMKV.defaultMMKV().decodeString("password", "");
        Intrinsics.checkExpressionValueIsNotNull(decodeString2, "MMKV.defaultMMKV().decodeString(\"password\",\"\")");
        this.password = decodeString2;
        ((MyEditTextView) _$_findCachedViewById(C2128R.C2131id.loginMailEdit)).setText(this.mail);
        ((MyEditTextView) _$_findCachedViewById(C2128R.C2131id.loginPassEdit)).setText(this.password);
        initClick();
    }

    private final void initClick() {
        ((MyImageView) _$_findCachedViewById(C2128R.C2131id.loginPassShow)).setOnClickListener(new LoginActivity$initClick$1(this));
        ((MyImageView) _$_findCachedViewById(C2128R.C2131id.loginRemember)).setOnClickListener(new LoginActivity$initClick$2(this));
        TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.loginForget);
        textView.setOnClickListener(new LoginActivity$initClick$$inlined$singleClick$1(textView, 800, this));
        TextView textView2 = (TextView) _$_findCachedViewById(C2128R.C2131id.loginSingUp);
        textView2.setOnClickListener(new LoginActivity$initClick$$inlined$singleClick$2(textView2, 800, this));
        Button button = (Button) _$_findCachedViewById(C2128R.C2131id.loginBt);
        button.setOnClickListener(new LoginActivity$initClick$$inlined$singleClick$3(button, 800, this));
        ImageView imageView = (ImageView) _$_findCachedViewById(C2128R.C2131id.loginFaceBook);
        imageView.setOnClickListener(new LoginActivity$initClick$$inlined$singleClick$4(imageView, 800, this));
    }

    /* access modifiers changed from: private */
    public final void checkEdit() {
        MyEditTextView myEditTextView = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.loginMailEdit);
        Intrinsics.checkExpressionValueIsNotNull(myEditTextView, "loginMailEdit");
        CharSequence text = myEditTextView.getText();
        boolean z = false;
        if (!(text == null || text.length() == 0)) {
            MyEditTextView myEditTextView2 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.loginPassEdit);
            Intrinsics.checkExpressionValueIsNotNull(myEditTextView2, "loginPassEdit");
            CharSequence text2 = myEditTextView2.getText();
            if (text2 == null || text2.length() == 0) {
                z = true;
            }
            if (!z) {
                MyEditTextView myEditTextView3 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.loginPassEdit);
                Intrinsics.checkExpressionValueIsNotNull(myEditTextView3, "loginPassEdit");
                Editable text3 = myEditTextView3.getText();
                if (text3 == null) {
                    Intrinsics.throwNpe();
                }
                if (text3.length() < 6) {
                    ToastUtils.show((CharSequence) getString(C2128R.string.pass_six));
                    return;
                }
                progressShow();
                LoginPresenter loginPresenter = (LoginPresenter) getMPresenter();
                if (loginPresenter != null) {
                    MyEditTextView myEditTextView4 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.loginMailEdit);
                    Intrinsics.checkExpressionValueIsNotNull(myEditTextView4, "loginMailEdit");
                    String valueOf = String.valueOf(myEditTextView4.getText());
                    MyEditTextView myEditTextView5 = (MyEditTextView) _$_findCachedViewById(C2128R.C2131id.loginPassEdit);
                    Intrinsics.checkExpressionValueIsNotNull(myEditTextView5, "loginPassEdit");
                    loginPresenter.login(valueOf, String.valueOf(myEditTextView5.getText()));
                    return;
                }
                return;
            }
        }
        ToastUtils.show((CharSequence) getString(C2128R.string.nonull));
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

    public void thirdLogin(@NotNull BaseBean<LoginBean> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "loginBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            MMKV defaultMMKV = MMKV.defaultMMKV();
            LoginBean data = baseBean.getData();
            String str = null;
            defaultMMKV.encode("mail", data != null ? data.getEmail() : null);
            MMKV defaultMMKV2 = MMKV.defaultMMKV();
            LoginBean data2 = baseBean.getData();
            if (data2 != null) {
                str = data2.getToken();
            }
            defaultMMKV2.encode("token", str);
            MMKV.defaultMMKV().encode("password", "");
            startActivity(new Intent(this, MainActivity.class).setFlags(268468224));
            finish();
        } else {
            startActivity(new Intent(this, BindActivity.class).putExtra(Config.CUSTOM_USER_ID, this.uid));
        }
        ToastUtils.show((CharSequence) baseBean.getMeta().getMessage());
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }

    /* access modifiers changed from: private */
    public final void loginFacebook() {
        Platform platform = ShareSDK.getPlatform(Facebook.NAME);
        Intrinsics.checkExpressionValueIsNotNull(platform, "plat");
        if (platform.isAuthValid()) {
            platform.removeAccount(true);
        }
        platform.SSOSetting(false);
        platform.setPlatformActionListener(new LoginActivity$loginFacebook$1(this));
        platform.showUser((String) null);
    }

    public boolean onKeyDown(int i, @NotNull KeyEvent keyEvent) {
        Intrinsics.checkParameterIsNotNull(keyEvent, NotificationCompat.CATEGORY_EVENT);
        if (i != 4 || keyEvent.getAction() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        if (System.currentTimeMillis() - this.exitTime > ((long) 2000)) {
            ToastUtils.show((CharSequence) getString(C2128R.string.common_exit));
            this.exitTime = System.currentTimeMillis();
            return true;
        }
        AppManager.finishAllActivity();
        System.exit(0);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }
}
