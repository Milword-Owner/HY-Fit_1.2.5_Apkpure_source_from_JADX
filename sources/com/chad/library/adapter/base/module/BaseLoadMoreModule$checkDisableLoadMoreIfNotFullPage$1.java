package com.chad.library.adapter.base.module;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo21895d2 = {"<anonymous>", "", "run"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* compiled from: LoadMoreModule.kt */
final class BaseLoadMoreModule$checkDisableLoadMoreIfNotFullPage$1 implements Runnable {
    final /* synthetic */ RecyclerView.LayoutManager $manager;
    final /* synthetic */ BaseLoadMoreModule this$0;

    BaseLoadMoreModule$checkDisableLoadMoreIfNotFullPage$1(BaseLoadMoreModule baseLoadMoreModule, RecyclerView.LayoutManager layoutManager) {
        this.this$0 = baseLoadMoreModule;
        this.$manager = layoutManager;
    }

    public final void run() {
        if (this.this$0.isFullScreen((LinearLayoutManager) this.$manager)) {
            this.this$0.mNextLoadEnable = true;
        }
    }
}
