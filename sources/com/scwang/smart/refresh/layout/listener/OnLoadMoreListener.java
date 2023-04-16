package com.scwang.smart.refresh.layout.listener;

import androidx.annotation.NonNull;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

public interface OnLoadMoreListener {
    void onLoadMore(@NonNull RefreshLayout refreshLayout);
}
