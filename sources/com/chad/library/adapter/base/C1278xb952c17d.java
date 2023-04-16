package com.chad.library.adapter.base;

import android.view.View;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004H\n¢\u0006\u0002\b\u0006¨\u0006\b"}, mo21895d2 = {"<anonymous>", "", "T", "v", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onLongClick", "com/chad/library/adapter/base/BaseProviderMultiAdapter$bindChildClick$2$1$1", "com/chad/library/adapter/base/BaseProviderMultiAdapter$$special$$inlined$let$lambda$2"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.chad.library.adapter.base.BaseProviderMultiAdapter$bindChildClick$$inlined$forEach$lambda$2 */
/* compiled from: BaseProviderMultiAdapter.kt */
final class C1278xb952c17d implements View.OnLongClickListener {
    final /* synthetic */ BaseItemProvider $provider$inlined;
    final /* synthetic */ BaseViewHolder $viewHolder$inlined;
    final /* synthetic */ BaseProviderMultiAdapter this$0;

    C1278xb952c17d(BaseProviderMultiAdapter baseProviderMultiAdapter, BaseViewHolder baseViewHolder, BaseItemProvider baseItemProvider) {
        this.this$0 = baseProviderMultiAdapter;
        this.$viewHolder$inlined = baseViewHolder;
        this.$provider$inlined = baseItemProvider;
    }

    public final boolean onLongClick(View view) {
        int adapterPosition = this.$viewHolder$inlined.getAdapterPosition();
        if (adapterPosition == -1) {
            return false;
        }
        int headerLayoutCount = adapterPosition - this.this$0.getHeaderLayoutCount();
        BaseItemProvider baseItemProvider = this.$provider$inlined;
        BaseViewHolder baseViewHolder = this.$viewHolder$inlined;
        Intrinsics.checkExpressionValueIsNotNull(view, "v");
        return baseItemProvider.onChildLongClick(baseViewHolder, view, this.this$0.getData().get(headerLayoutCount), headerLayoutCount);
    }
}
