package com.chad.library.adapter.base.module;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo21895d2 = {"<anonymous>", "", "run"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* compiled from: LoadMoreModule.kt */
final class BaseLoadMoreModule$checkDisableLoadMoreIfNotFullPage$2 implements Runnable {
    final /* synthetic */ RecyclerView.LayoutManager $manager;
    final /* synthetic */ BaseLoadMoreModule this$0;

    BaseLoadMoreModule$checkDisableLoadMoreIfNotFullPage$2(BaseLoadMoreModule baseLoadMoreModule, RecyclerView.LayoutManager layoutManager) {
        this.this$0 = baseLoadMoreModule;
        this.$manager = layoutManager;
    }

    public final void run() {
        int[] iArr = new int[((StaggeredGridLayoutManager) this.$manager).getSpanCount()];
        ((StaggeredGridLayoutManager) this.$manager).findLastCompletelyVisibleItemPositions(iArr);
        if (this.this$0.getTheBiggestNumber(iArr) + 1 != this.this$0.baseQuickAdapter.getItemCount()) {
            this.this$0.mNextLoadEnable = true;
        }
    }
}
