package com.huayu.tzc.p014ui.activity.login;

import android.text.TextUtils;
import android.util.Log;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.presenter.LoginPresenter;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.PlatformActionListener;
import p005cn.sharesdk.framework.PlatformDb;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u00005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J,\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nH\u0016J \u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0010"}, mo21895d2 = {"com/huayu/tzc/ui/activity/login/LoginActivity$loginFacebook$1", "Lcn/sharesdk/framework/PlatformActionListener;", "onCancel", "", "platform", "Lcn/sharesdk/framework/Platform;", "i", "", "onComplete", "hashMap", "Ljava/util/HashMap;", "", "", "onError", "throwable", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.login.LoginActivity$loginFacebook$1 */
/* compiled from: LoginActivity.kt */
public final class LoginActivity$loginFacebook$1 implements PlatformActionListener {
    final /* synthetic */ LoginActivity this$0;

    LoginActivity$loginFacebook$1(LoginActivity loginActivity) {
        this.this$0 = loginActivity;
    }

    public void onComplete(@NotNull Platform platform, int i, @NotNull HashMap<String, Object> hashMap) {
        Intrinsics.checkParameterIsNotNull(platform, "platform");
        Intrinsics.checkParameterIsNotNull(hashMap, "hashMap");
        this.this$0.runOnUiThread(new LoginActivity$loginFacebook$1$onComplete$1(this));
        PlatformDb db = platform.getDb();
        LoginActivity loginActivity = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(db, "platformDb");
        String userId = db.getUserId();
        Intrinsics.checkExpressionValueIsNotNull(userId, "platformDb.userId");
        loginActivity.uid = userId;
        String access$getTAG$p = this.this$0.getTAG();
        Log.e(access$getTAG$p, "onComplete: token  " + this.this$0.uid);
        if (!TextUtils.isEmpty(db.getUserId())) {
            LoginPresenter access$getMPresenter$p = LoginActivity.access$getMPresenter$p(this.this$0);
            if (access$getMPresenter$p != null) {
                access$getMPresenter$p.thirdLogin(db.getUserId());
                return;
            }
            return;
        }
        ToastUtils.show((CharSequence) this.this$0.getString(C2128R.string.newlogin));
    }

    public void onError(@NotNull Platform platform, int i, @NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(platform, "platform");
        Intrinsics.checkParameterIsNotNull(th, "throwable");
        String access$getTAG$p = this.this$0.getTAG();
        Log.e(access$getTAG$p, "onComplete:  错误" + th.getMessage());
        ToastUtils.show((CharSequence) th.getMessage());
        this.this$0.progressDissmiss();
    }

    public void onCancel(@NotNull Platform platform, int i) {
        Intrinsics.checkParameterIsNotNull(platform, "platform");
        this.this$0.progressDissmiss();
    }
}
