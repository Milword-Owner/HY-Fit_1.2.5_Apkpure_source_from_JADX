package com.huayu.tzc.p014ui.activity;

import android.view.View;
import com.huayu.tzc.customview.MyHeaderView;
import com.huayu.tzc.presenter.UserPresenter;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo21895d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onMenuTextClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.UserInfoActivity$initView$1 */
/* compiled from: UserInfoActivity.kt */
final class UserInfoActivity$initView$1 implements MyHeaderView.TextClickListener {
    final /* synthetic */ UserInfoActivity this$0;

    UserInfoActivity$initView$1(UserInfoActivity userInfoActivity) {
        this.this$0 = userInfoActivity;
    }

    public final void onMenuTextClick(View view) {
        this.this$0.progressShow2();
        UserPresenter access$getMPresenter$p = UserInfoActivity.access$getMPresenter$p(this.this$0);
        if (access$getMPresenter$p != null) {
            access$getMPresenter$p.deleteMem(String.valueOf(this.this$0.member.getMember_id()));
        }
    }
}
