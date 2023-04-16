package com.huayu.tzc.p014ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.adapter.TweetAdapter;
import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.BaseFragment;
import com.huayu.tzc.base.BaseListBean;
import com.huayu.tzc.bean.Tweet;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.presenter.TweetPresenter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\bH\u0014J\b\u0010\u0010\u001a\u00020\u0003H\u0014J\u0016\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0014H\u0016J\u0016\u0010\u0015\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0016H\u0016J\u0016\u0010\u0017\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0014H\u0016J\b\u0010\u0018\u001a\u00020\u0012H\u0002J\b\u0010\u0019\u001a\u00020\u0012H\u0014J\u0012\u0010\u001a\u001a\u00020\u00122\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXD¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, mo21895d2 = {"Lcom/huayu/tzc/ui/fragment/TweetFragment;", "Lcom/huayu/tzc/base/BaseFragment;", "Lcom/huayu/tzc/contract/MainContract$TweetView;", "Lcom/huayu/tzc/presenter/TweetPresenter;", "()V", "isRefresh", "", "pageNumber", "", "pageSize", "tweetAdapter", "Lcom/huayu/tzc/adapter/TweetAdapter;", "tweetList", "", "Lcom/huayu/tzc/bean/Tweet;", "getLayoutId", "getPresenter", "getStatus", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "getTweetList", "Lcom/huayu/tzc/base/BaseListBean;", "giveUpCount", "initAdapter", "initView", "showError", "e", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.TweetFragment */
/* compiled from: TweetFragment.kt */
public final class TweetFragment extends BaseFragment<MainContract.TweetView, TweetPresenter> implements MainContract.TweetView {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public boolean isRefresh;
    /* access modifiers changed from: private */
    public int pageNumber = 1;
    /* access modifiers changed from: private */
    public final int pageSize = 10;
    private TweetAdapter tweetAdapter;
    /* access modifiers changed from: private */
    public List<Tweet> tweetList = new ArrayList();

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C2128R.C2133layout.fragment_tweet;
    }

    public void getStatus(@NotNull BaseBean<Boolean> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
    }

    public void giveUpCount(@NotNull BaseBean<Integer> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public static final /* synthetic */ TweetPresenter access$getMPresenter$p(TweetFragment tweetFragment) {
        return (TweetPresenter) tweetFragment.getMPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        initAdapter();
        ((SmartRefreshLayout) _$_findCachedViewById(C2128R.C2131id.tweet_smart)).autoRefresh();
        ((SmartRefreshLayout) _$_findCachedViewById(C2128R.C2131id.tweet_smart)).setEnableLoadMore(false);
        ((SmartRefreshLayout) _$_findCachedViewById(C2128R.C2131id.tweet_smart)).setOnRefreshListener(new TweetFragment$initView$1(this));
    }

    private final void initAdapter() {
        this.tweetAdapter = new TweetAdapter(this.tweetList);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.tweet_recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "tweet_recyclerview");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.tweet_recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "tweet_recyclerview");
        TweetAdapter tweetAdapter2 = this.tweetAdapter;
        if (tweetAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tweetAdapter");
        }
        recyclerView2.setAdapter(tweetAdapter2);
        TweetAdapter tweetAdapter3 = this.tweetAdapter;
        if (tweetAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tweetAdapter");
        }
        tweetAdapter3.getLoadMoreModule().setOnLoadMoreListener(new TweetFragment$initAdapter$1(this));
        TweetAdapter tweetAdapter4 = this.tweetAdapter;
        if (tweetAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tweetAdapter");
        }
        tweetAdapter4.setOnItemClickListener(new TweetFragment$initAdapter$2(this));
        View inflate = LayoutInflater.from(getContext()).inflate(C2128R.C2133layout.view_listnull, (ViewGroup) null);
        TweetAdapter tweetAdapter5 = this.tweetAdapter;
        if (tweetAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tweetAdapter");
        }
        Intrinsics.checkExpressionValueIsNotNull(inflate, ViewHierarchyConstants.VIEW_KEY);
        tweetAdapter5.setEmptyView(inflate);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public TweetPresenter getPresenter() {
        return new TweetPresenter();
    }

    public void getTweetList(@NotNull BaseListBean<Tweet> baseListBean) {
        Intrinsics.checkParameterIsNotNull(baseListBean, "baseBean");
        progressDissmiss();
        if (this.isRefresh) {
            this.tweetList.clear();
            this.isRefresh = false;
        }
        if (!baseListBean.getRows().isEmpty()) {
            this.tweetList.addAll(baseListBean.getRows());
            if (baseListBean.getRows().size() == this.pageSize) {
                TweetAdapter tweetAdapter2 = this.tweetAdapter;
                if (tweetAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tweetAdapter");
                }
                tweetAdapter2.getLoadMoreModule().loadMoreComplete();
            } else if (baseListBean.getRows().size() < this.pageSize) {
                TweetAdapter tweetAdapter3 = this.tweetAdapter;
                if (tweetAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tweetAdapter");
                }
                tweetAdapter3.getLoadMoreModule().loadMoreEnd(false);
            }
        }
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }
}
