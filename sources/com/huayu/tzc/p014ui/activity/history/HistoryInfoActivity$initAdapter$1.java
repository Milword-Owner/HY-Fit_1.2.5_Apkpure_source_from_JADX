package com.huayu.tzc.p014ui.activity.history;

import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.bean.HistoryInfo;
import com.huayu.tzc.customview.BodyStateView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, mo21895d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "view", "Landroid/view/View;", "position", "", "onItemClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.history.HistoryInfoActivity$initAdapter$1 */
/* compiled from: HistoryInfoActivity.kt */
final class HistoryInfoActivity$initAdapter$1 implements OnItemClickListener {
    final /* synthetic */ HistoryInfoActivity this$0;

    HistoryInfoActivity$initAdapter$1(HistoryInfoActivity historyInfoActivity) {
        this.this$0 = historyInfoActivity;
    }

    public final void onItemClick(@NotNull BaseQuickAdapter<?, ?> baseQuickAdapter, @NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkParameterIsNotNull(view, ViewHierarchyConstants.VIEW_KEY);
        if (i != 15 && i != 16) {
            View findViewById = view.findViewById(C2128R.C2131id.history_info_view);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.history_info_view)");
            BodyStateView bodyStateView = (BodyStateView) findViewById;
            TextView textView = (TextView) view.findViewById(C2128R.C2131id.history_info_text);
            Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
            if (textView.getVisibility() == 0) {
                bodyStateView.setVisibility(8);
                textView.setVisibility(8);
                return;
            }
            textView.setVisibility(0);
            textView.setText(this.this$0.getResources().getStringArray(C2128R.array.details_body_description)[i]);
            HistoryInfoActivity historyInfoActivity = this.this$0;
            historyInfoActivity.drawView(i, bodyStateView, ((HistoryInfo) historyInfoActivity.historyInfoList.get(i)).getNum());
        }
    }
}
