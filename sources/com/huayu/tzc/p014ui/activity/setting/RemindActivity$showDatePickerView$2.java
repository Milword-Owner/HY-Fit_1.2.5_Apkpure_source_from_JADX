package com.huayu.tzc.p014ui.activity.setting;

import android.view.View;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo21895d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.setting.RemindActivity$showDatePickerView$2 */
/* compiled from: RemindActivity.kt */
final class RemindActivity$showDatePickerView$2 implements View.OnClickListener {
    final /* synthetic */ int $position;
    final /* synthetic */ RemindActivity this$0;

    RemindActivity$showDatePickerView$2(RemindActivity remindActivity, int i) {
        this.this$0 = remindActivity;
        this.$position = i;
    }

    public final void onClick(View view) {
        RemindActivity.access$getPickerView$p(this.this$0).dismiss();
        this.this$0.remindList.remove(this.$position);
        RemindActivity.access$getRemindAdapter$p(this.this$0).notifyDataSetChanged();
    }
}
