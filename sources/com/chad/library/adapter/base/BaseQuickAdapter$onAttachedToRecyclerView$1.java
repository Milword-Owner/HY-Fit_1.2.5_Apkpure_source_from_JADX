package com.chad.library.adapter.base;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.listener.GridSpanSizeLookup;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, mo21895d2 = {"com/chad/library/adapter/base/BaseQuickAdapter$onAttachedToRecyclerView$1", "Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;", "getSpanSize", "", "position", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: BaseQuickAdapter.kt */
public final class BaseQuickAdapter$onAttachedToRecyclerView$1 extends GridLayoutManager.SpanSizeLookup {
    final /* synthetic */ GridLayoutManager.SpanSizeLookup $defSpanSizeLookup;
    final /* synthetic */ RecyclerView.LayoutManager $manager;
    final /* synthetic */ BaseQuickAdapter this$0;

    BaseQuickAdapter$onAttachedToRecyclerView$1(BaseQuickAdapter baseQuickAdapter, RecyclerView.LayoutManager layoutManager, GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        this.this$0 = baseQuickAdapter;
        this.$manager = layoutManager;
        this.$defSpanSizeLookup = spanSizeLookup;
    }

    public int getSpanSize(int i) {
        int itemViewType = this.this$0.getItemViewType(i);
        if (itemViewType == 268435729 && this.this$0.getHeaderViewAsFlow()) {
            return 1;
        }
        if (itemViewType == 268436275 && this.this$0.getFooterViewAsFlow()) {
            return 1;
        }
        if (this.this$0.mSpanSizeLookup == null) {
            return this.this$0.isFixedViewType(itemViewType) ? ((GridLayoutManager) this.$manager).getSpanCount() : this.$defSpanSizeLookup.getSpanSize(i);
        }
        if (this.this$0.isFixedViewType(itemViewType)) {
            return ((GridLayoutManager) this.$manager).getSpanCount();
        }
        GridSpanSizeLookup access$getMSpanSizeLookup$p = this.this$0.mSpanSizeLookup;
        if (access$getMSpanSizeLookup$p == null) {
            Intrinsics.throwNpe();
        }
        return access$getMSpanSizeLookup$p.getSpanSize((GridLayoutManager) this.$manager, itemViewType, i - this.this$0.getHeaderLayoutCount());
    }
}
