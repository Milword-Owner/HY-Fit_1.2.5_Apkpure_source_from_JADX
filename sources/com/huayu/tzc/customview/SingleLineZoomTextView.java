package com.huayu.tzc.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

public class SingleLineZoomTextView extends AppCompatTextView {
    private Paint mPaint;
    private float mTextSize;

    public SingleLineZoomTextView(Context context) {
        super(context);
    }

    public SingleLineZoomTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SingleLineZoomTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void refitText(String str, int i) {
        if (i > 0) {
            this.mTextSize = getTextSize();
            this.mPaint = new Paint();
            this.mPaint.set(getPaint());
            Drawable[] compoundDrawables = getCompoundDrawables();
            int i2 = 0;
            for (int i3 = 0; i3 < compoundDrawables.length; i3++) {
                if (compoundDrawables[i3] != null) {
                    i2 += compoundDrawables[i3].getBounds().width();
                }
            }
            int paddingLeft = (((i - getPaddingLeft()) - getPaddingRight()) - getCompoundDrawablePadding()) - i2;
            float textLength = getTextLength(this.mTextSize, str);
            while (textLength > ((float) paddingLeft)) {
                Paint paint = this.mPaint;
                float f = this.mTextSize - 1.0f;
                this.mTextSize = f;
                paint.setTextSize(f);
                textLength = getTextLength(this.mTextSize, str);
            }
            setTextSize(0, this.mTextSize);
        }
    }

    private float getTextLength(float f, String str) {
        this.mPaint.setTextSize(f);
        return this.mPaint.measureText(str);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        refitText(getText().toString(), getWidth());
        super.onDraw(canvas);
    }
}
