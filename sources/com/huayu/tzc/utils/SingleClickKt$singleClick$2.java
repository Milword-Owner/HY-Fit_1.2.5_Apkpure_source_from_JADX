package com.huayu.tzc.utils;

import android.view.View;
import android.widget.Checkable;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u000e\u0010\u0004\u001a\n \u0005*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, mo21895d2 = {"<anonymous>", "", "T", "Landroid/view/View;", "it", "kotlin.jvm.PlatformType", "onClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* compiled from: singleClick.kt */
final class SingleClickKt$singleClick$2 implements View.OnClickListener {
    final /* synthetic */ View.OnClickListener $onClickListener;
    final /* synthetic */ View $this_singleClick;
    final /* synthetic */ long $time;

    SingleClickKt$singleClick$2(View view, long j, View.OnClickListener onClickListener) {
        this.$this_singleClick = view;
        this.$time = j;
        this.$onClickListener = onClickListener;
    }

    public final void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - SingleClickKt.getLastClickTime(this.$this_singleClick) > this.$time || (this.$this_singleClick instanceof Checkable)) {
            SingleClickKt.setLastClickTime(this.$this_singleClick, currentTimeMillis);
            this.$onClickListener.onClick(this.$this_singleClick);
        }
    }
}
