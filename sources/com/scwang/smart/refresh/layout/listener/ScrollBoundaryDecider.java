package com.scwang.smart.refresh.layout.listener;

import android.view.View;

public interface ScrollBoundaryDecider {
    boolean canLoadMore(View view);

    boolean canRefresh(View view);
}
