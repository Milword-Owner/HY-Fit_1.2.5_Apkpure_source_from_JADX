package com.huayu.tzc.p014ui.activity;

import android.view.View;
import android.widget.TextView;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, mo21895d2 = {"<anonymous>", "", "date", "Ljava/util/Date;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "Landroid/view/View;", "onTimeSelect"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.UserInfoActivity$showDatePickerView$pickerView$1 */
/* compiled from: UserInfoActivity.kt */
final class UserInfoActivity$showDatePickerView$pickerView$1 implements OnTimeSelectListener {
    final /* synthetic */ TextView $textView;
    final /* synthetic */ UserInfoActivity this$0;

    UserInfoActivity$showDatePickerView$pickerView$1(UserInfoActivity userInfoActivity, TextView textView) {
        this.this$0 = userInfoActivity;
        this.$textView = textView;
    }

    public final void onTimeSelect(Date date, View view) {
        TextView textView = this.$textView;
        UserInfoActivity userInfoActivity = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(date, "date");
        textView.setText(userInfoActivity.getTime(date));
    }
}
