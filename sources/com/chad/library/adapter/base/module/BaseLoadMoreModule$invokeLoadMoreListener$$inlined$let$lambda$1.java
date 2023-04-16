package com.chad.library.adapter.base.module;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, mo21895d2 = {"<anonymous>", "", "run", "com/chad/library/adapter/base/module/BaseLoadMoreModule$invokeLoadMoreListener$1$1"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* compiled from: LoadMoreModule.kt */
final class BaseLoadMoreModule$invokeLoadMoreListener$$inlined$let$lambda$1 implements Runnable {
    final /* synthetic */ BaseLoadMoreModule this$0;

    BaseLoadMoreModule$invokeLoadMoreListener$$inlined$let$lambda$1(BaseLoadMoreModule baseLoadMoreModule) {
        this.this$0 = baseLoadMoreModule;
    }

    public final void run() {
        OnLoadMoreListener access$getMLoadMoreListener$p = this.this$0.mLoadMoreListener;
        if (access$getMLoadMoreListener$p != null) {
            access$getMLoadMoreListener$p.onLoadMore();
        }
    }
}
