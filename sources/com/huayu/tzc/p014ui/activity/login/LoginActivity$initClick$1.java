package com.huayu.tzc.p014ui.activity.login;

import android.view.View;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.customview.MyEditTextView;
import com.huayu.tzc.customview.MyImageView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo21895d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.login.LoginActivity$initClick$1 */
/* compiled from: LoginActivity.kt */
final class LoginActivity$initClick$1 implements View.OnClickListener {
    final /* synthetic */ LoginActivity this$0;

    LoginActivity$initClick$1(LoginActivity loginActivity) {
        this.this$0 = loginActivity;
    }

    public final void onClick(View view) {
        LoginActivity loginActivity = this.this$0;
        MyImageView myImageView = (MyImageView) loginActivity._$_findCachedViewById(C2128R.C2131id.loginPassShow);
        Intrinsics.checkExpressionValueIsNotNull(myImageView, "loginPassShow");
        MyEditTextView myEditTextView = (MyEditTextView) this.this$0._$_findCachedViewById(C2128R.C2131id.loginPassEdit);
        Intrinsics.checkExpressionValueIsNotNull(myEditTextView, "loginPassEdit");
        loginActivity.eyeEditText(myImageView, myEditTextView);
    }
}
