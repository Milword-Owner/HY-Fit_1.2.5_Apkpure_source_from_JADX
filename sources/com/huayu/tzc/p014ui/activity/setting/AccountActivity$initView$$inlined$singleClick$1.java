package com.huayu.tzc.p014ui.activity.setting;

import android.content.Intent;
import android.view.View;
import android.widget.Checkable;
import com.huayu.tzc.customview.MyLinearLayout;
import com.huayu.tzc.utils.SingleClickKt;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u000e\u0010\u0004\u001a\n \u0005*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006¨\u0006\u0007"}, mo21895d2 = {"<anonymous>", "", "T", "Landroid/view/View;", "it", "kotlin.jvm.PlatformType", "onClick", "com/huayu/tzc/utils/SingleClickKt$singleClick$1"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.setting.AccountActivity$initView$$inlined$singleClick$1 */
/* compiled from: singleClick.kt */
public final class AccountActivity$initView$$inlined$singleClick$1 implements View.OnClickListener {
    final /* synthetic */ View $this_singleClick;
    final /* synthetic */ long $time;
    final /* synthetic */ AccountActivity this$0;

    public AccountActivity$initView$$inlined$singleClick$1(View view, long j, AccountActivity accountActivity) {
        this.$this_singleClick = view;
        this.$time = j;
        this.this$0 = accountActivity;
    }

    public final void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - SingleClickKt.getLastClickTime(this.$this_singleClick) > this.$time || (this.$this_singleClick instanceof Checkable)) {
            SingleClickKt.setLastClickTime(this.$this_singleClick, currentTimeMillis);
            MyLinearLayout myLinearLayout = (MyLinearLayout) this.$this_singleClick;
            AccountActivity accountActivity = this.this$0;
            accountActivity.startActivity(new Intent(accountActivity, ModifyPassActivity.class));
        }
    }
}
