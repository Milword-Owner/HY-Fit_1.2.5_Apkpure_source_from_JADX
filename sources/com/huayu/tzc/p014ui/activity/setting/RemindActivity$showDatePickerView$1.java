package com.huayu.tzc.p014ui.activity.setting;

import android.view.View;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.huayu.tzc.bean.Remind;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, mo21895d2 = {"<anonymous>", "", "date", "Ljava/util/Date;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "Landroid/view/View;", "onTimeSelect"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.setting.RemindActivity$showDatePickerView$1 */
/* compiled from: RemindActivity.kt */
final class RemindActivity$showDatePickerView$1 implements OnTimeSelectListener {
    final /* synthetic */ int $position;
    final /* synthetic */ RemindActivity this$0;

    RemindActivity$showDatePickerView$1(RemindActivity remindActivity, int i) {
        this.this$0 = remindActivity;
        this.$position = i;
    }

    public final void onTimeSelect(Date date, View view) {
        RemindActivity remindActivity = this.this$0;
        remindActivity.setTitle(((Remind) remindActivity.remindList.get(this.$position)).getDate());
        this.this$0.requestCalendarPermission(617);
        RemindActivity remindActivity2 = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(date, "date");
        ((Remind) this.this$0.remindList.get(this.$position)).setDate(remindActivity2.getTime(date));
        ((Remind) this.this$0.remindList.get(this.$position)).setChecked(false);
        RemindActivity.access$getRemindAdapter$p(this.this$0).notifyDataSetChanged();
    }
}
