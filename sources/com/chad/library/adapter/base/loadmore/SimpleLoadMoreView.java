package com.chad.library.adapter.base.loadmore;

import android.view.View;
import android.view.ViewGroup;
import com.chad.library.C1271R;
import com.chad.library.adapter.base.util.AdapterUtilsKt;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, mo21895d2 = {"Lcom/chad/library/adapter/base/loadmore/SimpleLoadMoreView;", "Lcom/chad/library/adapter/base/loadmore/BaseLoadMoreView;", "()V", "getLoadComplete", "Landroid/view/View;", "holder", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "getLoadEndView", "getLoadFailView", "getLoadingView", "getRootView", "parent", "Landroid/view/ViewGroup;", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: SimpleLoadMoreView.kt */
public final class SimpleLoadMoreView extends BaseLoadMoreView {
    @NotNull
    public View getRootView(@NotNull ViewGroup viewGroup) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return AdapterUtilsKt.getItemView(viewGroup, C1271R.C1275layout.brvah_quick_view_load_more);
    }

    @NotNull
    public View getLoadingView(@NotNull BaseViewHolder baseViewHolder) {
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "holder");
        return baseViewHolder.getView(C1271R.C1274id.load_more_loading_view);
    }

    @NotNull
    public View getLoadComplete(@NotNull BaseViewHolder baseViewHolder) {
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "holder");
        return baseViewHolder.getView(C1271R.C1274id.load_more_load_complete_view);
    }

    @NotNull
    public View getLoadEndView(@NotNull BaseViewHolder baseViewHolder) {
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "holder");
        return baseViewHolder.getView(C1271R.C1274id.load_more_load_end_view);
    }

    @NotNull
    public View getLoadFailView(@NotNull BaseViewHolder baseViewHolder) {
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "holder");
        return baseViewHolder.getView(C1271R.C1274id.load_more_load_fail_view);
    }
}
