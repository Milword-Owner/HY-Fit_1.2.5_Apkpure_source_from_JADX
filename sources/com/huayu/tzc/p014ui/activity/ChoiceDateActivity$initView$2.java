package com.huayu.tzc.p014ui.activity;

import android.content.Intent;
import com.huayu.tzc.customview.calendar.CalendarList;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, mo21895d2 = {"<anonymous>", "", "startDate", "", "kotlin.jvm.PlatformType", "endDate", "selected"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.ChoiceDateActivity$initView$2 */
/* compiled from: ChoiceDateActivity.kt */
final class ChoiceDateActivity$initView$2 implements CalendarList.OnDateSelected {
    final /* synthetic */ ChoiceDateActivity this$0;

    ChoiceDateActivity$initView$2(ChoiceDateActivity choiceDateActivity) {
        this.this$0 = choiceDateActivity;
    }

    public final void selected(String str, String str2) {
        this.this$0.setResult(-1, new Intent().putExtra("startTime", str).putExtra("endTime", str2));
    }
}
