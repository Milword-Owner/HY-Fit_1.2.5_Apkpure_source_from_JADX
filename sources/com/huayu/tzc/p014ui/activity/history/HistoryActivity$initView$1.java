package com.huayu.tzc.p014ui.activity.history;

import android.view.View;
import com.huayu.tzc.customview.MyHeaderView;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo21895d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onMenuTextClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.history.HistoryActivity$initView$1 */
/* compiled from: HistoryActivity.kt */
final class HistoryActivity$initView$1 implements MyHeaderView.TextClickListener {
    final /* synthetic */ HistoryActivity this$0;

    HistoryActivity$initView$1(HistoryActivity historyActivity) {
        this.this$0 = historyActivity;
    }

    public final void onMenuTextClick(View view) {
        if (this.this$0.isEdit) {
            this.this$0.showEdit();
        } else {
            this.this$0.hideEdit();
        }
    }
}
