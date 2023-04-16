package com.huayu.tzc.customview.ruler;

import android.content.Context;
import android.graphics.Canvas;

public class TopHeadRulerAbstract extends HorizontalRulerAbstract {
    public TopHeadRulerAbstract(Context context) {
        super(context, (RuleLayout) null);
    }

    public TopHeadRulerAbstract(Context context, RuleLayout ruleLayout) {
        super(context, ruleLayout);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawScale(canvas);
        drawEdgeEffect(canvas);
    }

    private void drawScale(Canvas canvas) {
        float scrollX = (float) ((((getScrollX() + canvas.getWidth()) + this.mDrawOffset) / this.mParent.getInterval()) + this.mParent.getMinScale());
        for (float scrollX2 = (float) (((getScrollX() - this.mDrawOffset) / this.mParent.getInterval()) + this.mParent.getMinScale()); scrollX2 <= scrollX; scrollX2 += 1.0f) {
            float minScale = (scrollX2 - ((float) this.mParent.getMinScale())) * ((float) this.mParent.getInterval());
            if (scrollX2 >= ((float) this.mParent.getMinScale()) && scrollX2 <= ((float) this.mParent.getMaxScale())) {
                if (scrollX2 % ((float) this.mCount) == 0.0f) {
                    canvas.drawLine(minScale, 50.0f, minScale, ((float) this.mParent.getBigScaleLength()) + 50.0f, this.mBigScalePaint);
                    if (this.mParent.isShowText()) {
                        canvas.drawText(RulerStringUtil.resultValueOf(scrollX2, this.mParent.getFactor()), minScale, (float) this.mParent.getTextMarginHead(), this.mTextPaint);
                    }
                } else {
                    canvas.drawLine(minScale, 50.0f, minScale, ((float) this.mParent.getSmallScaleLength()) + 50.0f, this.mSmallScalePaint);
                }
            }
        }
    }

    private void drawEdgeEffect(Canvas canvas) {
        if (this.mParent.canEdgeEffect()) {
            if (!this.mStartEdgeEffect.isFinished()) {
                int save = canvas.save();
                canvas.rotate(270.0f);
                canvas.translate(0.0f, 0.0f);
                if (this.mStartEdgeEffect.draw(canvas)) {
                    postInvalidateOnAnimation();
                }
                canvas.restoreToCount(save);
            } else {
                this.mStartEdgeEffect.finish();
            }
            if (!this.mEndEdgeEffect.isFinished()) {
                int save2 = canvas.save();
                canvas.rotate(90.0f);
                canvas.translate(0.0f, (float) (-this.mLength));
                if (this.mEndEdgeEffect.draw(canvas)) {
                    postInvalidateOnAnimation();
                }
                canvas.restoreToCount(save2);
                return;
            }
            this.mEndEdgeEffect.finish();
        }
    }
}
