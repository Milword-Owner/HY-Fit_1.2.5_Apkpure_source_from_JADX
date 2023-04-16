package com.chad.library.adapter.base.module;

import android.view.MotionEvent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.C1271R;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, mo21895d2 = {"<anonymous>", "", "v", "Landroid/view/View;", "kotlin.jvm.PlatformType", "event", "Landroid/view/MotionEvent;", "onTouch"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* compiled from: DraggableModule.kt */
final class BaseDraggableModule$isDragOnLongPressEnabled$2 implements View.OnTouchListener {
    final /* synthetic */ BaseDraggableModule this$0;

    BaseDraggableModule$isDragOnLongPressEnabled$2(BaseDraggableModule baseDraggableModule) {
        this.this$0 = baseDraggableModule;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        Intrinsics.checkExpressionValueIsNotNull(motionEvent, NotificationCompat.CATEGORY_EVENT);
        if (motionEvent.getAction() != 0 || this.this$0.isDragOnLongPressEnabled()) {
            return false;
        }
        if (this.this$0.isDragEnabled()) {
            ItemTouchHelper itemTouchHelper = this.this$0.getItemTouchHelper();
            Object tag = view.getTag(C1271R.C1274id.BaseQuickAdapter_viewholder_support);
            if (tag != null) {
                itemTouchHelper.startDrag((RecyclerView.ViewHolder) tag);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.ViewHolder");
            }
        }
        return true;
    }
}
