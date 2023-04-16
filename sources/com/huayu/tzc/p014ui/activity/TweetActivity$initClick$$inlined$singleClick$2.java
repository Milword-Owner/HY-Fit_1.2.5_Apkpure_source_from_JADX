package com.huayu.tzc.p014ui.activity;

import android.view.View;
import android.widget.Checkable;
import android.widget.TextView;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.customview.MyImageView;
import com.huayu.tzc.presenter.TweetPresenter;
import com.huayu.tzc.utils.SingleClickKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u000e\u0010\u0004\u001a\n \u0005*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006¨\u0006\u0007"}, mo21895d2 = {"<anonymous>", "", "T", "Landroid/view/View;", "it", "kotlin.jvm.PlatformType", "onClick", "com/huayu/tzc/utils/SingleClickKt$singleClick$1"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.TweetActivity$initClick$$inlined$singleClick$2 */
/* compiled from: singleClick.kt */
public final class TweetActivity$initClick$$inlined$singleClick$2 implements View.OnClickListener {
    final /* synthetic */ View $this_singleClick;
    final /* synthetic */ long $time;
    final /* synthetic */ TweetActivity this$0;

    public TweetActivity$initClick$$inlined$singleClick$2(View view, long j, TweetActivity tweetActivity) {
        this.$this_singleClick = view;
        this.$time = j;
        this.this$0 = tweetActivity;
    }

    public final void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - SingleClickKt.getLastClickTime(this.$this_singleClick) > this.$time || (this.$this_singleClick instanceof Checkable)) {
            SingleClickKt.setLastClickTime(this.$this_singleClick, currentTimeMillis);
            MyImageView myImageView = (MyImageView) this.$this_singleClick;
            MyImageView myImageView2 = (MyImageView) this.this$0._$_findCachedViewById(C2128R.C2131id.tweet_agree);
            Intrinsics.checkExpressionValueIsNotNull(myImageView2, "tweet_agree");
            if (myImageView2.isChecked()) {
                TweetActivity tweetActivity = this.this$0;
                tweetActivity.count = tweetActivity.count - 1;
                MyImageView myImageView3 = (MyImageView) this.this$0._$_findCachedViewById(C2128R.C2131id.tweet_agree);
                Intrinsics.checkExpressionValueIsNotNull(myImageView3, "tweet_agree");
                myImageView3.setChecked(false);
            } else {
                TweetActivity tweetActivity2 = this.this$0;
                tweetActivity2.count = tweetActivity2.count + 1;
                MyImageView myImageView4 = (MyImageView) this.this$0._$_findCachedViewById(C2128R.C2131id.tweet_agree);
                Intrinsics.checkExpressionValueIsNotNull(myImageView4, "tweet_agree");
                myImageView4.setChecked(true);
            }
            if (this.this$0.count < 0) {
                this.this$0.count = 0;
            }
            TextView textView = (TextView) this.this$0._$_findCachedViewById(C2128R.C2131id.tweetNum);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tweetNum");
            textView.setText(String.valueOf(this.this$0.count));
            TweetPresenter access$getMPresenter$p = TweetActivity.access$getMPresenter$p(this.this$0);
            if (access$getMPresenter$p != null) {
                access$getMPresenter$p.giveUpStatus(this.this$0.f1689id);
            }
        }
    }
}
