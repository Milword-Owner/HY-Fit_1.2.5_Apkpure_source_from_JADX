package com.chad.library.adapter.base;

import android.view.View;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004H\n¢\u0006\u0002\b\u0006"}, mo21895d2 = {"<anonymous>", "", "T", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onLongClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* compiled from: BaseProviderMultiAdapter.kt */
final class BaseProviderMultiAdapter$bindClick$2 implements View.OnLongClickListener {
    final /* synthetic */ BaseViewHolder $viewHolder;
    final /* synthetic */ BaseProviderMultiAdapter this$0;

    BaseProviderMultiAdapter$bindClick$2(BaseProviderMultiAdapter baseProviderMultiAdapter, BaseViewHolder baseViewHolder) {
        this.this$0 = baseProviderMultiAdapter;
        this.$viewHolder = baseViewHolder;
    }

    public final boolean onLongClick(View view) {
        int adapterPosition = this.$viewHolder.getAdapterPosition();
        if (adapterPosition == -1) {
            return false;
        }
        int headerLayoutCount = adapterPosition - this.this$0.getHeaderLayoutCount();
        int itemViewType = this.$viewHolder.getItemViewType();
        BaseViewHolder baseViewHolder = this.$viewHolder;
        Intrinsics.checkExpressionValueIsNotNull(view, "it");
        return ((BaseItemProvider) this.this$0.getMItemProviders().get(itemViewType)).onLongClick(baseViewHolder, view, this.this$0.getData().get(headerLayoutCount), headerLayoutCount);
    }
}
