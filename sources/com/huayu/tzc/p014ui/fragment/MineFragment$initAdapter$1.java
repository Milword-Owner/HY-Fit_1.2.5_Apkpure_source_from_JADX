package com.huayu.tzc.p014ui.fragment;

import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.facebook.share.internal.ShareConstants;
import com.huayu.tzc.p014ui.activity.UserInfoActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, mo21895d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "<anonymous parameter 1>", "Landroid/view/View;", "position", "", "onItemClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.MineFragment$initAdapter$1 */
/* compiled from: MineFragment.kt */
final class MineFragment$initAdapter$1 implements OnItemClickListener {
    final /* synthetic */ MineFragment this$0;

    MineFragment$initAdapter$1(MineFragment mineFragment) {
        this.this$0 = mineFragment;
    }

    public final void onItemClick(@NotNull BaseQuickAdapter<?, ?> baseQuickAdapter, @NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkParameterIsNotNull(view, "<anonymous parameter 1>");
        if (i == 0) {
            FragmentActivity activity = this.this$0.getActivity();
            if (activity == null) {
                Intrinsics.throwNpe();
            }
            activity.startActivityForResult(new Intent(this.this$0.getContext(), UserInfoActivity.class).putExtra("flag", 2), 800);
            return;
        }
        FragmentActivity activity2 = this.this$0.getActivity();
        if (activity2 == null) {
            Intrinsics.throwNpe();
        }
        activity2.startActivityForResult(new Intent(this.this$0.getContext(), UserInfoActivity.class).putExtra(ShareConstants.WEB_DIALOG_PARAM_DATA, (Parcelable) this.this$0.memberList.get(i)).putExtra("flag", 1), 803);
    }
}
