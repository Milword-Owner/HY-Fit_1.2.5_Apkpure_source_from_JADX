package com.chad.library.adapter.base.module;

import android.view.View;
import com.chad.library.adapter.base.loadmore.LoadMoreStatus;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo21895d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* compiled from: LoadMoreModule.kt */
final class BaseLoadMoreModule$setupViewHolder$1 implements View.OnClickListener {
    final /* synthetic */ BaseLoadMoreModule this$0;

    BaseLoadMoreModule$setupViewHolder$1(BaseLoadMoreModule baseLoadMoreModule) {
        this.this$0 = baseLoadMoreModule;
    }

    public final void onClick(View view) {
        if (this.this$0.getLoadMoreStatus() == LoadMoreStatus.Fail) {
            this.this$0.loadMoreToLoading();
        } else if (this.this$0.getLoadMoreStatus() == LoadMoreStatus.Complete) {
            this.this$0.loadMoreToLoading();
        } else if (this.this$0.getEnableLoadMoreEndClick() && this.this$0.getLoadMoreStatus() == LoadMoreStatus.End) {
            this.this$0.loadMoreToLoading();
        }
    }
}
