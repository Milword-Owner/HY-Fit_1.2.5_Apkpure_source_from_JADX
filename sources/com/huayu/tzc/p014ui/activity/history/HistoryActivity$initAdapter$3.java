package com.huayu.tzc.p014ui.activity.history;

import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.bean.History;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, mo21895d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "<anonymous parameter 1>", "Landroid/view/View;", "position", "", "onItemClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.history.HistoryActivity$initAdapter$3 */
/* compiled from: HistoryActivity.kt */
final class HistoryActivity$initAdapter$3 implements OnItemClickListener {
    final /* synthetic */ HistoryActivity this$0;

    HistoryActivity$initAdapter$3(HistoryActivity historyActivity) {
        this.this$0 = historyActivity;
    }

    public final void onItemClick(@NotNull BaseQuickAdapter<?, ?> baseQuickAdapter, @NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkParameterIsNotNull(view, "<anonymous parameter 1>");
        if (this.this$0.isEdit) {
            ((History) this.this$0.historyList.get(i)).setSelect(!((History) this.this$0.historyList.get(i)).getSelect());
            HistoryActivity.access$getHistoryAdapter$p(this.this$0).notifyItemChanged(i);
            int i2 = 0;
            for (History select : this.this$0.historyList) {
                if (select.getSelect()) {
                    i2++;
                }
            }
            TextView textView = (TextView) this.this$0._$_findCachedViewById(C2128R.C2131id.historyNum);
            Intrinsics.checkExpressionValueIsNotNull(textView, "historyNum");
            String string = this.this$0.getString(C2128R.string.select_num);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.select_num)");
            textView.setText(StringsKt.replace$default(string, "_num", String.valueOf(i2), false, 4, (Object) null));
        }
    }
}
