package com.huayu.tzc.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Checkable;
import androidx.appcompat.widget.AppCompatImageView;
import com.huayu.tzc.C2128R;

public class MyImageView extends AppCompatImageView implements Checkable {
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final String TAG = "CheckedImageLayout";
    private int img_false;
    private int img_true;
    private boolean isChecked = false;
    private Context mContext;

    public MyImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, C2128R.styleable.MyImageView);
        if (obtainStyledAttributes != null) {
            this.img_false = obtainStyledAttributes.getResourceId(0, C2128R.C2130drawable.ic_close_eye);
            setImageResource(this.img_false);
            this.img_true = obtainStyledAttributes.getResourceId(2, C2128R.C2130drawable.ic_eye);
            this.isChecked = obtainStyledAttributes.getBoolean(1, false);
            setChecked(this.isChecked);
        }
        obtainStyledAttributes.recycle();
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
        if (this.isChecked) {
            setImageResource(this.img_true);
        } else {
            setImageResource(this.img_false);
        }
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void toggle() {
        setChecked(!this.isChecked);
    }

    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }
}
