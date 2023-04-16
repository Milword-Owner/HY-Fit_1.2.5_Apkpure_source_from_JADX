package com.zhihu.matisse.internal.p034ui.widget;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.res.ResourcesCompat;
import com.zhihu.matisse.C2570R;

/* renamed from: com.zhihu.matisse.internal.ui.widget.CheckRadioView */
public class CheckRadioView extends AppCompatImageView {
    private Drawable mDrawable;
    private int mSelectedColor;
    private int mUnSelectUdColor;

    public CheckRadioView(Context context) {
        super(context);
        init();
    }

    public CheckRadioView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.mSelectedColor = ResourcesCompat.getColor(getResources(), C2570R.C2571color.zhihu_item_checkCircle_backgroundColor, getContext().getTheme());
        this.mUnSelectUdColor = ResourcesCompat.getColor(getResources(), C2570R.C2571color.zhihu_check_original_radio_disable, getContext().getTheme());
        setChecked(false);
    }

    public void setChecked(boolean z) {
        if (z) {
            setImageResource(C2570R.C2572drawable.ic_preview_radio_on);
            this.mDrawable = getDrawable();
            this.mDrawable.setColorFilter(this.mSelectedColor, PorterDuff.Mode.SRC_IN);
            return;
        }
        setImageResource(C2570R.C2572drawable.ic_preview_radio_off);
        this.mDrawable = getDrawable();
        this.mDrawable.setColorFilter(this.mUnSelectUdColor, PorterDuff.Mode.SRC_IN);
    }

    public void setColor(int i) {
        if (this.mDrawable == null) {
            this.mDrawable = getDrawable();
        }
        this.mDrawable.setColorFilter(i, PorterDuff.Mode.SRC_IN);
    }
}
