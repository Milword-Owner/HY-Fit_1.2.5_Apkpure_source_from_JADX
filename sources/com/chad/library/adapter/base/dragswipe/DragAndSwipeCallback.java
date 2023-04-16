package com.chad.library.adapter.base.dragswipe;

import android.graphics.Canvas;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.C1271R;
import com.chad.library.adapter.base.module.BaseDraggableModule;

public class DragAndSwipeCallback extends ItemTouchHelper.Callback {
    private int mDragMoveFlags = 15;
    private BaseDraggableModule mDraggableModule;
    private float mMoveThreshold = 0.1f;
    private int mSwipeMoveFlags = 32;
    private float mSwipeThreshold = 0.7f;

    public DragAndSwipeCallback(BaseDraggableModule baseDraggableModule) {
        this.mDraggableModule = baseDraggableModule;
    }

    public boolean isLongPressDragEnabled() {
        BaseDraggableModule baseDraggableModule = this.mDraggableModule;
        if (baseDraggableModule == null || !baseDraggableModule.isDragEnabled() || this.mDraggableModule.hasToggleView()) {
            return false;
        }
        return true;
    }

    public boolean isItemViewSwipeEnabled() {
        BaseDraggableModule baseDraggableModule = this.mDraggableModule;
        if (baseDraggableModule != null) {
            return baseDraggableModule.isSwipeEnabled();
        }
        return false;
    }

    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        if (i == 2 && !isViewCreateByAdapter(viewHolder)) {
            BaseDraggableModule baseDraggableModule = this.mDraggableModule;
            if (baseDraggableModule != null) {
                baseDraggableModule.onItemDragStart(viewHolder);
            }
            viewHolder.itemView.setTag(C1271R.C1274id.BaseQuickAdapter_dragging_support, true);
        } else if (i == 1 && !isViewCreateByAdapter(viewHolder)) {
            BaseDraggableModule baseDraggableModule2 = this.mDraggableModule;
            if (baseDraggableModule2 != null) {
                baseDraggableModule2.onItemSwipeStart(viewHolder);
            }
            viewHolder.itemView.setTag(C1271R.C1274id.BaseQuickAdapter_swiping_support, true);
        }
        super.onSelectedChanged(viewHolder, i);
    }

    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (!isViewCreateByAdapter(viewHolder)) {
            if (viewHolder.itemView.getTag(C1271R.C1274id.BaseQuickAdapter_dragging_support) != null && ((Boolean) viewHolder.itemView.getTag(C1271R.C1274id.BaseQuickAdapter_dragging_support)).booleanValue()) {
                BaseDraggableModule baseDraggableModule = this.mDraggableModule;
                if (baseDraggableModule != null) {
                    baseDraggableModule.onItemDragEnd(viewHolder);
                }
                viewHolder.itemView.setTag(C1271R.C1274id.BaseQuickAdapter_dragging_support, false);
            }
            if (viewHolder.itemView.getTag(C1271R.C1274id.BaseQuickAdapter_swiping_support) != null && ((Boolean) viewHolder.itemView.getTag(C1271R.C1274id.BaseQuickAdapter_swiping_support)).booleanValue()) {
                BaseDraggableModule baseDraggableModule2 = this.mDraggableModule;
                if (baseDraggableModule2 != null) {
                    baseDraggableModule2.onItemSwipeClear(viewHolder);
                }
                viewHolder.itemView.setTag(C1271R.C1274id.BaseQuickAdapter_swiping_support, false);
            }
        }
    }

    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        if (isViewCreateByAdapter(viewHolder)) {
            return makeMovementFlags(0, 0);
        }
        return makeMovementFlags(this.mDragMoveFlags, this.mSwipeMoveFlags);
    }

    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder2) {
        return viewHolder.getItemViewType() == viewHolder2.getItemViewType();
    }

    public void onMoved(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, int i, @NonNull RecyclerView.ViewHolder viewHolder2, int i2, int i3, int i4) {
        super.onMoved(recyclerView, viewHolder, i, viewHolder2, i2, i3, i4);
        BaseDraggableModule baseDraggableModule = this.mDraggableModule;
        if (baseDraggableModule != null) {
            baseDraggableModule.onItemDragMoving(viewHolder, viewHolder2);
        }
    }

    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        BaseDraggableModule baseDraggableModule;
        if (!isViewCreateByAdapter(viewHolder) && (baseDraggableModule = this.mDraggableModule) != null) {
            baseDraggableModule.onItemSwiped(viewHolder);
        }
    }

    public float getMoveThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return this.mMoveThreshold;
    }

    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return this.mSwipeThreshold;
    }

    public void setSwipeThreshold(float f) {
        this.mSwipeThreshold = f;
    }

    public void setMoveThreshold(float f) {
        this.mMoveThreshold = f;
    }

    public void setDragMoveFlags(int i) {
        this.mDragMoveFlags = i;
    }

    public void setSwipeMoveFlags(int i) {
        this.mSwipeMoveFlags = i;
    }

    public void onChildDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        super.onChildDrawOver(canvas, recyclerView, viewHolder, f, f2, i, z);
        if (i == 1 && !isViewCreateByAdapter(viewHolder)) {
            View view = viewHolder.itemView;
            canvas.save();
            if (f > 0.0f) {
                canvas.clipRect((float) view.getLeft(), (float) view.getTop(), ((float) view.getLeft()) + f, (float) view.getBottom());
                canvas.translate((float) view.getLeft(), (float) view.getTop());
            } else {
                canvas.clipRect(((float) view.getRight()) + f, (float) view.getTop(), (float) view.getRight(), (float) view.getBottom());
                canvas.translate(((float) view.getRight()) + f, (float) view.getTop());
            }
            BaseDraggableModule baseDraggableModule = this.mDraggableModule;
            if (baseDraggableModule != null) {
                baseDraggableModule.onItemSwiping(canvas, viewHolder, f, f2, z);
            }
            canvas.restore();
        }
    }

    private boolean isViewCreateByAdapter(@NonNull RecyclerView.ViewHolder viewHolder) {
        int itemViewType = viewHolder.getItemViewType();
        return itemViewType == 268435729 || itemViewType == 268436002 || itemViewType == 268436275 || itemViewType == 268436821;
    }
}
