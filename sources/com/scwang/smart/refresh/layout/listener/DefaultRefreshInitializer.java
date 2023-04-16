package com.scwang.smart.refresh.layout.listener;

import android.content.Context;
import androidx.annotation.NonNull;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

public interface DefaultRefreshInitializer {
    void initialize(@NonNull Context context, @NonNull RefreshLayout refreshLayout);
}
