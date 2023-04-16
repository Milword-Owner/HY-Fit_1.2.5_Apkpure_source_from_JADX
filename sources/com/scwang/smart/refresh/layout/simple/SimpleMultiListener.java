package com.scwang.smart.refresh.layout.simple;

import androidx.annotation.NonNull;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.listener.OnMultiListener;

public class SimpleMultiListener implements OnMultiListener {
    public void onFooterFinish(RefreshFooter refreshFooter, boolean z) {
    }

    public void onFooterMoving(RefreshFooter refreshFooter, boolean z, float f, int i, int i2, int i3) {
    }

    public void onFooterReleased(RefreshFooter refreshFooter, int i, int i2) {
    }

    public void onFooterStartAnimator(RefreshFooter refreshFooter, int i, int i2) {
    }

    public void onHeaderFinish(RefreshHeader refreshHeader, boolean z) {
    }

    public void onHeaderMoving(RefreshHeader refreshHeader, boolean z, float f, int i, int i2, int i3) {
    }

    public void onHeaderReleased(RefreshHeader refreshHeader, int i, int i2) {
    }

    public void onHeaderStartAnimator(RefreshHeader refreshHeader, int i, int i2) {
    }

    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
    }

    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
    }

    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
    }
}
