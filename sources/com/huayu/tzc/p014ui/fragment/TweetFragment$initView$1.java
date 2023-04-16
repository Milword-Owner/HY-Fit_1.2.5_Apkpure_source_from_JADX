package com.huayu.tzc.p014ui.fragment;

import com.huayu.tzc.presenter.TweetPresenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo21895d2 = {"<anonymous>", "", "it", "Lcom/scwang/smart/refresh/layout/api/RefreshLayout;", "onRefresh"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.TweetFragment$initView$1 */
/* compiled from: TweetFragment.kt */
final class TweetFragment$initView$1 implements OnRefreshListener {
    final /* synthetic */ TweetFragment this$0;

    TweetFragment$initView$1(TweetFragment tweetFragment) {
        this.this$0 = tweetFragment;
    }

    public final void onRefresh(@NotNull RefreshLayout refreshLayout) {
        Intrinsics.checkParameterIsNotNull(refreshLayout, "it");
        this.this$0.pageNumber = 1;
        this.this$0.isRefresh = true;
        TweetPresenter access$getMPresenter$p = TweetFragment.access$getMPresenter$p(this.this$0);
        if (access$getMPresenter$p != null) {
            access$getMPresenter$p.getTweetList(this.this$0.pageNumber, this.this$0.pageSize);
        }
        refreshLayout.finishRefresh();
    }
}
