package com.mob.tools.gui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.ListView;
import com.mob.tools.gui.Scrollable;

public class ScrollableListView extends ListView implements Scrollable {
    private Scrollable.OnScrollListener osListener;
    /* access modifiers changed from: private */
    public boolean pullEnable;

    public ScrollableListView(Context context) {
        super(context);
        init(context);
    }

    public ScrollableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public ScrollableListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        setCacheColorHint(0);
        setSelector(new ColorDrawable());
        setDividerHeight(0);
        this.pullEnable = true;
        this.osListener = new Scrollable.OnScrollListener() {
            public void onScrollChanged(Scrollable scrollable, int i, int i2, int i3, int i4) {
                boolean unused = ScrollableListView.this.pullEnable = i2 <= 0 && i4 <= 0;
            }
        };
    }

    public boolean isReadyToPull() {
        return this.pullEnable;
    }

    /* access modifiers changed from: protected */
    public int computeVerticalScrollOffset() {
        int computeVerticalScrollOffset = super.computeVerticalScrollOffset();
        Scrollable.OnScrollListener onScrollListener = this.osListener;
        if (onScrollListener != null) {
            onScrollListener.onScrollChanged(this, 0, computeVerticalScrollOffset, 0, 0);
        }
        return computeVerticalScrollOffset;
    }
}
