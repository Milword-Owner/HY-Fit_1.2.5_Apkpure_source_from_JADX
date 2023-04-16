package com.chad.library.adapter.base.module;

import android.view.View;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.C1271R;
import kotlin.Metadata;
import kotlin.TypeCastException;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo21895d2 = {"<anonymous>", "", "v", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onLongClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* compiled from: DraggableModule.kt */
final class BaseDraggableModule$isDragOnLongPressEnabled$1 implements View.OnLongClickListener {
    final /* synthetic */ BaseDraggableModule this$0;

    BaseDraggableModule$isDragOnLongPressEnabled$1(BaseDraggableModule baseDraggableModule) {
        this.this$0 = baseDraggableModule;
    }

    public final boolean onLongClick(View view) {
        if (!this.this$0.isDragEnabled()) {
            return true;
        }
        ItemTouchHelper itemTouchHelper = this.this$0.getItemTouchHelper();
        Object tag = view.getTag(C1271R.C1274id.BaseQuickAdapter_viewholder_support);
        if (tag != null) {
            itemTouchHelper.startDrag((RecyclerView.ViewHolder) tag);
            return true;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.ViewHolder");
    }
}
