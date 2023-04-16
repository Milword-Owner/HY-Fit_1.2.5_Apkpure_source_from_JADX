package com.huayu.tzc.customview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.loadmore.BaseLoadMoreView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.huayu.tzc.C2128R;
import org.jetbrains.annotations.NotNull;

public class CustomLoadMoreView extends BaseLoadMoreView {
    @NotNull
    public View getRootView(@NotNull ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(C2128R.C2133layout.view_load_more, viewGroup, false);
    }

    @NotNull
    public View getLoadingView(@NotNull BaseViewHolder baseViewHolder) {
        return baseViewHolder.findView(C2128R.C2131id.load_more_loading_view);
    }

    @NotNull
    public View getLoadComplete(@NotNull BaseViewHolder baseViewHolder) {
        return baseViewHolder.findView(C2128R.C2131id.load_more_load_complete_view);
    }

    @NotNull
    public View getLoadEndView(@NotNull BaseViewHolder baseViewHolder) {
        return baseViewHolder.findView(C2128R.C2131id.load_more_load_end_view);
    }

    @NotNull
    public View getLoadFailView(@NotNull BaseViewHolder baseViewHolder) {
        return baseViewHolder.findView(C2128R.C2131id.load_more_load_fail_view);
    }
}
