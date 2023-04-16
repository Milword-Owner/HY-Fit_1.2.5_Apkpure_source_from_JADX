package com.huayu.tzc.p014ui.fragment;

import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, mo21895d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/view/View;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "Landroid/view/MotionEvent;", "onTouch"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.HomeFragment$showPopupWidow$2 */
/* compiled from: HomeFragment.kt */
final class HomeFragment$showPopupWidow$2 implements View.OnTouchListener {
    final /* synthetic */ HomeFragment this$0;

    HomeFragment$showPopupWidow$2(HomeFragment homeFragment) {
        this.this$0 = homeFragment;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (HomeFragment.access$getPop$p(this.this$0) == null) {
            return false;
        }
        PopupWindow access$getPop$p = HomeFragment.access$getPop$p(this.this$0);
        if (access$getPop$p == null) {
            Intrinsics.throwNpe();
        }
        if (!access$getPop$p.isShowing()) {
            return false;
        }
        HomeFragment.access$getPop$p(this.this$0).dismiss();
        return false;
    }
}
