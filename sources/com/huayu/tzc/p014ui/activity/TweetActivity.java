package com.huayu.tzc.p014ui.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.BaseListBean;
import com.huayu.tzc.bean.Tweet;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.MyImageView;
import com.huayu.tzc.presenter.TweetPresenter;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\u0006H\u0014J\b\u0010\t\u001a\u00020\u0003H\u0016J\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J\u0016\u0010\u000f\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u0016\u0010\u0012\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0016J\b\u0010\u0013\u001a\u00020\u000bH\u0002J\b\u0010\u0014\u001a\u00020\u000bH\u0015J\u0012\u0010\u0015\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/TweetActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$TweetView;", "Lcom/huayu/tzc/presenter/TweetPresenter;", "()V", "count", "", "id", "getLayoutId", "getPresenter", "getStatus", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "", "getTweetList", "Lcom/huayu/tzc/base/BaseListBean;", "Lcom/huayu/tzc/bean/Tweet;", "giveUpCount", "initClick", "initView", "showError", "e", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.TweetActivity */
/* compiled from: TweetActivity.kt */
public final class TweetActivity extends BaseActivity<MainContract.TweetView, TweetPresenter> implements MainContract.TweetView {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public int count;
    /* access modifiers changed from: private */

    /* renamed from: id */
    public int f1689id;

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
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C2128R.C2133layout.activity_tweet;
    }

    public static final /* synthetic */ TweetPresenter access$getMPresenter$p(TweetActivity tweetActivity) {
        return (TweetPresenter) tweetActivity.getMPresenter();
    }

    @NotNull
    public TweetPresenter getPresenter() {
        return new TweetPresenter();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"JavascriptInterface"})
    public void initView() {
        this.f1689id = getIntent().getIntExtra("id", 0);
        ((WebView) _$_findCachedViewById(C2128R.C2131id.tweet_content)).loadDataWithBaseURL((String) null, getIntent().getStringExtra("content"), "text/html", "utf-8", (String) null);
        TweetPresenter tweetPresenter = (TweetPresenter) getMPresenter();
        if (tweetPresenter != null) {
            tweetPresenter.getStatus(this.f1689id);
        }
        TweetPresenter tweetPresenter2 = (TweetPresenter) getMPresenter();
        if (tweetPresenter2 != null) {
            tweetPresenter2.giveUpCount(this.f1689id);
        }
        initClick();
    }

    private final void initClick() {
        TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.tweet_kf);
        textView.setOnClickListener(new TweetActivity$initClick$$inlined$singleClick$1(textView, 800, this));
        MyImageView myImageView = (MyImageView) _$_findCachedViewById(C2128R.C2131id.tweet_agree);
        myImageView.setOnClickListener(new TweetActivity$initClick$$inlined$singleClick$2(myImageView, 800, this));
    }

    public void getTweetList(@NotNull BaseListBean<Tweet> baseListBean) {
        Intrinsics.checkParameterIsNotNull(baseListBean, "baseBean");
        progressDissmiss();
    }

    public void getStatus(@NotNull BaseBean<Boolean> baseBean) {
        Boolean data;
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess() && (data = baseBean.getData()) != null) {
            boolean booleanValue = data.booleanValue();
            MyImageView myImageView = (MyImageView) _$_findCachedViewById(C2128R.C2131id.tweet_agree);
            Intrinsics.checkExpressionValueIsNotNull(myImageView, "tweet_agree");
            myImageView.setChecked(booleanValue);
        }
        ToastUtils.show((CharSequence) baseBean.getMeta().getMessage());
    }

    public void giveUpCount(@NotNull BaseBean<Integer> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            Integer data = baseBean.getData();
            if (data == null) {
                Intrinsics.throwNpe();
            }
            this.count = data.intValue();
            TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.tweetNum);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tweetNum");
            textView.setText(String.valueOf(baseBean.getData()));
        }
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }
}
