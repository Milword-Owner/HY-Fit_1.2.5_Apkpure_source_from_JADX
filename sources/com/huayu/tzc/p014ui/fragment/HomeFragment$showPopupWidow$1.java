package com.huayu.tzc.p014ui.fragment;

import android.widget.PopupWindow;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo21895d2 = {"<anonymous>", "", "onDismiss"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.HomeFragment$showPopupWidow$1 */
/* compiled from: HomeFragment.kt */
final class HomeFragment$showPopupWidow$1 implements PopupWindow.OnDismissListener {
    final /* synthetic */ HomeFragment this$0;

    HomeFragment$showPopupWidow$1(HomeFragment homeFragment) {
        this.this$0 = homeFragment;
    }

    public final void onDismiss() {
        this.this$0.backgroundAlpha(1.0f);
    }
}
