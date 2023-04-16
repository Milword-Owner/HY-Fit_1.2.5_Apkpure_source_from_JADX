package com.huayu.tzc.adapter;

import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.gson.Gson;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.bean.Tweet;
import com.huayu.tzc.bean.TweetTitle;
import java.util.List;

public class TweetAdapter extends BaseQuickAdapter<Tweet, BaseViewHolder> implements LoadMoreModule {
    public TweetAdapter(@Nullable List<Tweet> list) {
        super(C2128R.C2133layout.recyclerview_tweet, list);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder baseViewHolder, Tweet tweet) {
        baseViewHolder.setText((int) C2128R.C2131id.tweet_title, (CharSequence) tweet.getTitle());
        TweetTitle tweetTitle = (TweetTitle) new Gson().fromJson(tweet.getSummary(), TweetTitle.class);
        baseViewHolder.setText((int) C2128R.C2131id.tweet_content, (CharSequence) tweetTitle.getTitle());
        Glide.with(getContext()).load(tweetTitle.replaceImg(tweetTitle.getImgurl())).into((ImageView) baseViewHolder.getView(C2128R.C2131id.tweet_img));
    }
}
