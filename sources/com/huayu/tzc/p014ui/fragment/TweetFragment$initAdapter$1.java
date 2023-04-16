package com.huayu.tzc.p014ui.fragment;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.huayu.tzc.presenter.TweetPresenter;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo21895d2 = {"<anonymous>", "", "onLoadMore"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.TweetFragment$initAdapter$1 */
/* compiled from: TweetFragment.kt */
final class TweetFragment$initAdapter$1 implements OnLoadMoreListener {
    final /* synthetic */ TweetFragment this$0;

    TweetFragment$initAdapter$1(TweetFragment tweetFragment) {
        this.this$0 = tweetFragment;
    }

    public final void onLoadMore() {
        TweetFragment tweetFragment = this.this$0;
        tweetFragment.pageNumber = tweetFragment.pageNumber + 1;
        TweetPresenter access$getMPresenter$p = TweetFragment.access$getMPresenter$p(this.this$0);
        if (access$getMPresenter$p != null) {
            access$getMPresenter$p.getTweetList(this.this$0.pageNumber, this.this$0.pageSize);
        }
    }
}
