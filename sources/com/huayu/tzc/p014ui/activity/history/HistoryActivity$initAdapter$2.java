package com.huayu.tzc.p014ui.activity.history;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.share.internal.ShareConstants;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.bean.History;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012(\u0010\u0002\u001a$\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0005 \u0006*\r\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003¨\u0006\u00010\u0003¨\u0006\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\n¢\u0006\u0002\b\u000b"}, mo21895d2 = {"<anonymous>", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "kotlin.jvm.PlatformType", "view", "Landroid/view/View;", "position", "", "onItemChildClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.history.HistoryActivity$initAdapter$2 */
/* compiled from: HistoryActivity.kt */
final class HistoryActivity$initAdapter$2 implements OnItemChildClickListener {
    final /* synthetic */ HistoryActivity this$0;

    HistoryActivity$initAdapter$2(HistoryActivity historyActivity) {
        this.this$0 = historyActivity;
    }

    public final void onItemChildClick(@NotNull BaseQuickAdapter<Object, BaseViewHolder> baseQuickAdapter, @NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(baseQuickAdapter, "adapter");
        Intrinsics.checkParameterIsNotNull(view, ViewHierarchyConstants.VIEW_KEY);
        int i2 = 0;
        if (baseQuickAdapter.getItemViewType(i) == 2 && view.getId() == C2128R.C2131id.historySelect) {
            ((History) this.this$0.historyList.get(i)).setSelect(!((History) this.this$0.historyList.get(i)).getSelect());
            HistoryActivity.access$getHistoryAdapter$p(this.this$0).notifyItemChanged(i);
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
        } else if (baseQuickAdapter.getItemViewType(i) == 2 && view.getId() == C2128R.C2131id.history_item_layout && !this.this$0.isEdit) {
            HistoryActivity historyActivity = this.this$0;
            historyActivity.startActivity(new Intent(historyActivity, HistoryInfoActivity.class).putExtra("member", HistoryActivity.access$getMember$p(this.this$0)).putExtra(ShareConstants.WEB_DIALOG_PARAM_DATA, ((History) this.this$0.historyList.get(i)).getMeasure()));
        } else if (baseQuickAdapter.getItemViewType(i) == 2 && view.getId() == C2128R.C2131id.history_item_layout && this.this$0.isEdit) {
            ((History) this.this$0.historyList.get(i)).setSelect(!((History) this.this$0.historyList.get(i)).getSelect());
            HistoryActivity.access$getHistoryAdapter$p(this.this$0).notifyItemChanged(i);
            for (History select2 : this.this$0.historyList) {
                if (select2.getSelect()) {
                    i2++;
                }
            }
            TextView textView2 = (TextView) this.this$0._$_findCachedViewById(C2128R.C2131id.historyNum);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "historyNum");
            String string2 = this.this$0.getString(C2128R.string.select_num);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.select_num)");
            textView2.setText(StringsKt.replace$default(string2, "_num", String.valueOf(i2), false, 4, (Object) null));
        }
    }
}
