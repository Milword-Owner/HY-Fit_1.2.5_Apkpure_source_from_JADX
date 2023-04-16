package com.zhihu.matisse.internal.p034ui.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: com.zhihu.matisse.internal.ui.widget.MediaGridInset */
public class MediaGridInset extends RecyclerView.ItemDecoration {
    private boolean mIncludeEdge;
    private int mSpacing;
    private int mSpanCount;

    public MediaGridInset(int i, int i2, boolean z) {
        this.mSpanCount = i;
        this.mSpacing = i2;
        this.mIncludeEdge = z;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int i = this.mSpanCount;
        int i2 = childAdapterPosition % i;
        if (this.mIncludeEdge) {
            int i3 = this.mSpacing;
            rect.left = i3 - ((i2 * i3) / i);
            rect.right = ((i2 + 1) * i3) / i;
            if (childAdapterPosition < i) {
                rect.top = i3;
            }
            rect.bottom = this.mSpacing;
            return;
        }
        int i4 = this.mSpacing;
        rect.left = (i2 * i4) / i;
        rect.right = i4 - (((i2 + 1) * i4) / i);
        if (childAdapterPosition >= i) {
            rect.top = i4;
        }
    }
}
