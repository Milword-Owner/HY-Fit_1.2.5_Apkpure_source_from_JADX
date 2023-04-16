package com.huayu.tzc.p014ui.activity.history;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.huayu.tzc.presenter.HistoryPresenter;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo21895d2 = {"<anonymous>", "", "onLoadMore"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.history.HistoryActivity$initAdapter$1 */
/* compiled from: HistoryActivity.kt */
final class HistoryActivity$initAdapter$1 implements OnLoadMoreListener {
    final /* synthetic */ HistoryActivity this$0;

    HistoryActivity$initAdapter$1(HistoryActivity historyActivity) {
        this.this$0 = historyActivity;
    }

    public final void onLoadMore() {
        HistoryActivity historyActivity = this.this$0;
        historyActivity.pageNumber = historyActivity.pageNumber + 1;
        HistoryPresenter access$getMPresenter$p = HistoryActivity.access$getMPresenter$p(this.this$0);
        if (access$getMPresenter$p != null) {
            access$getMPresenter$p.getMeasures(this.this$0.pageNumber, this.this$0.pageSize, HistoryActivity.access$getMember$p(this.this$0).getMember_id());
        }
    }
}
