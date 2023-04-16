package com.huayu.tzc.p014ui.fragment;

import android.content.Intent;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.huayu.tzc.bean.Member;
import com.huayu.tzc.p014ui.activity.UserInfoActivity;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, mo21895d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "<anonymous parameter 1>", "Landroid/view/View;", "position", "", "onItemClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.HomeFragment$initPopupAdapter$1 */
/* compiled from: HomeFragment.kt */
final class HomeFragment$initPopupAdapter$1 implements OnItemClickListener {
    final /* synthetic */ Ref.ObjectRef $popList;
    final /* synthetic */ HomeFragment this$0;

    HomeFragment$initPopupAdapter$1(HomeFragment homeFragment, Ref.ObjectRef objectRef) {
        this.this$0 = homeFragment;
        this.$popList = objectRef;
    }

    public final void onItemClick(@NotNull BaseQuickAdapter<?, ?> baseQuickAdapter, @NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkParameterIsNotNull(view, "<anonymous parameter 1>");
        this.this$0.selectUser = (Member) ((List) this.$popList.element).get(i);
        if (((Member) ((List) this.$popList.element).get(i)).getFlag() == 0) {
            this.this$0.updateData((Member) ((List) this.$popList.element).get(i));
        } else {
            FragmentActivity activity = this.this$0.getActivity();
            if (activity != null) {
                activity.startActivityForResult(new Intent(this.this$0.getContext(), UserInfoActivity.class).putExtra("flag", 2), 800);
            }
        }
        HomeFragment.access$getPop$p(this.this$0).dismiss();
    }
}
