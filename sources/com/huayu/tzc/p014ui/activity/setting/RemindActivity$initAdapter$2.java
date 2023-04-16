package com.huayu.tzc.p014ui.activity.setting;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012(\u0010\u0002\u001a$\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0005 \u0006*\r\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003¨\u0006\u00010\u0003¨\u0006\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\n¢\u0006\u0002\b\u000b"}, mo21895d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "kotlin.jvm.PlatformType", "view", "Landroid/view/View;", "position", "", "onItemChildClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.setting.RemindActivity$initAdapter$2 */
/* compiled from: RemindActivity.kt */
final class RemindActivity$initAdapter$2 implements OnItemChildClickListener {
    final /* synthetic */ RemindActivity this$0;

    RemindActivity$initAdapter$2(RemindActivity remindActivity) {
        this.this$0 = remindActivity;
    }

    public final void onItemChildClick(@NotNull BaseQuickAdapter<Object, BaseViewHolder> baseQuickAdapter, @NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkParameterIsNotNull(view, ViewHierarchyConstants.VIEW_KEY);
        this.this$0.clickSwitch(view, i);
    }
}
