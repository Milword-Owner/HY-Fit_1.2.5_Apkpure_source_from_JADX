package com.huayu.tzc.p014ui.fragment;

import androidx.recyclerview.widget.GridLayoutManager;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, mo21895d2 = {"com/huayu/tzc/ui/fragment/HomeFragment$initTzAdapter$1", "Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;", "getSpanSize", "", "position", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.HomeFragment$initTzAdapter$1 */
/* compiled from: HomeFragment.kt */
public final class HomeFragment$initTzAdapter$1 extends GridLayoutManager.SpanSizeLookup {
    final /* synthetic */ HomeFragment this$0;

    HomeFragment$initTzAdapter$1(HomeFragment homeFragment) {
        this.this$0 = homeFragment;
    }

    public int getSpanSize(int i) {
        return HomeFragment.access$getHomeTzAdapter$p(this.this$0).getItemViewType(i) == 1 ? 1 : 3;
    }
}
