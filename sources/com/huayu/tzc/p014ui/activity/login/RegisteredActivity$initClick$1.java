package com.huayu.tzc.p014ui.activity.login;

import android.view.View;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.customview.MyEditTextView;
import com.huayu.tzc.customview.MyImageView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo21895d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.login.RegisteredActivity$initClick$1 */
/* compiled from: RegisteredActivity.kt */
final class RegisteredActivity$initClick$1 implements View.OnClickListener {
    final /* synthetic */ RegisteredActivity this$0;

    RegisteredActivity$initClick$1(RegisteredActivity registeredActivity) {
        this.this$0 = registeredActivity;
    }

    public final void onClick(View view) {
        RegisteredActivity registeredActivity = this.this$0;
        MyImageView myImageView = (MyImageView) registeredActivity._$_findCachedViewById(C2128R.C2131id.registerPassShow);
        Intrinsics.checkExpressionValueIsNotNull(myImageView, "registerPassShow");
        MyEditTextView myEditTextView = (MyEditTextView) this.this$0._$_findCachedViewById(C2128R.C2131id.registerPassEdit);
        Intrinsics.checkExpressionValueIsNotNull(myEditTextView, "registerPassEdit");
        registeredActivity.eyeEditText(myImageView, myEditTextView);
    }
}
