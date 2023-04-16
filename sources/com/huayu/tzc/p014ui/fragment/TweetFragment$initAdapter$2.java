package com.huayu.tzc.p014ui.fragment;

import android.content.Intent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.huayu.tzc.bean.Tweet;
import com.huayu.tzc.p014ui.activity.TweetActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, mo21895d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "<anonymous parameter 1>", "Landroid/view/View;", "position", "", "onItemClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.TweetFragment$initAdapter$2 */
/* compiled from: TweetFragment.kt */
final class TweetFragment$initAdapter$2 implements OnItemClickListener {
    final /* synthetic */ TweetFragment this$0;

    TweetFragment$initAdapter$2(TweetFragment tweetFragment) {
        this.this$0 = tweetFragment;
    }

    public final void onItemClick(@NotNull BaseQuickAdapter<?, ?> baseQuickAdapter, @NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkParameterIsNotNull(view, "<anonymous parameter 1>");
        FragmentActivity activity = this.this$0.getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        activity.startActivityForResult(new Intent(this.this$0.getContext(), TweetActivity.class).putExtra("id", ((Tweet) this.this$0.tweetList.get(i)).getPushmessageid()).putExtra(NotificationCompat.CATEGORY_MESSAGE, ((Tweet) this.this$0.tweetList.get(i)).getSummary()).putExtra("title", ((Tweet) this.this$0.tweetList.get(i)).getTitle()).putExtra("content", ((Tweet) this.this$0.tweetList.get(i)).getContent()), 812);
    }
}
