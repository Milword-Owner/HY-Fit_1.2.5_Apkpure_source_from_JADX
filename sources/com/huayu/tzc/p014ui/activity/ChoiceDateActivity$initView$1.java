package com.huayu.tzc.p014ui.activity;

import android.view.View;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.customview.MyHeaderView;
import com.huayu.tzc.customview.calendar.CalendarList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo21895d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onMenuTextClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.ChoiceDateActivity$initView$1 */
/* compiled from: ChoiceDateActivity.kt */
final class ChoiceDateActivity$initView$1 implements MyHeaderView.TextClickListener {
    final /* synthetic */ ChoiceDateActivity this$0;

    ChoiceDateActivity$initView$1(ChoiceDateActivity choiceDateActivity) {
        this.this$0 = choiceDateActivity;
    }

    public final void onMenuTextClick(View view) {
        CalendarList calendarList = (CalendarList) this.this$0._$_findCachedViewById(C2128R.C2131id.calendarList);
        Intrinsics.checkExpressionValueIsNotNull(calendarList, "calendarList");
        if (calendarList.getStartDate() != null) {
            CalendarList calendarList2 = (CalendarList) this.this$0._$_findCachedViewById(C2128R.C2131id.calendarList);
            Intrinsics.checkExpressionValueIsNotNull(calendarList2, "calendarList");
            if (calendarList2.getEndDate() != null) {
                this.this$0.finish();
            }
        }
    }
}
