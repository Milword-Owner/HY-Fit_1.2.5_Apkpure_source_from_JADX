package com.huayu.tzc.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.huayu.tzc.utils.Utils;

public class BodyStateView extends View {
    private String[] colors = {"#56A3FA", "#0EDAAE", "#FAD65B", "#F8A139", "#F66B89"};
    private String[] content = {"偏瘦", "正常", "偏胖", "肥胖", "重度肥胖"};
    private Canvas mCanvas;
    private Context mContext;
    private Paint mPaint;
    private float num = 0.0f;
    private float[] nums = {18.5f, 24.0f, 28.0f, 35.0f};
    private float startY = 150.0f;
    private int state = 0;
    private float width;

    public BodyStateView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        init();
    }

    private void init() {
        this.mPaint = new Paint();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mCanvas = canvas;
        this.mPaint.setStrokeWidth((float) Utils.dip2px(this.mContext, 10.0f));
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(Color.parseColor("#56A3FA"));
        this.mPaint.setStrokeCap(Paint.Cap.BUTT);
        drawBackGround();
    }

    private void drawBackGround() {
        this.width = (float) ((getWidth() - 60) / this.content.length);
        float f = 30.0f;
        for (int i = 0; i < this.content.length; i++) {
            this.mPaint.setColor(Color.parseColor(this.colors[i]));
            if (i != this.content.length - 1) {
                Canvas canvas = this.mCanvas;
                float f2 = this.startY;
                canvas.drawLine(f, f2, (this.width + f) - 2.0f, f2, this.mPaint);
                this.mPaint.setColor(Color.parseColor("#999999"));
                this.mPaint.setTextSize((float) sp2px(this.mContext, 10.0f));
                this.mPaint.setTextAlign(Paint.Align.CENTER);
                float[] fArr = this.nums;
                if (fArr.length > 0) {
                    this.mCanvas.drawText(String.valueOf(fArr[i]), (this.width + f) - 2.0f, this.startY - 40.0f, this.mPaint);
                }
            } else {
                Canvas canvas2 = this.mCanvas;
                float f3 = this.startY;
                canvas2.drawLine(f, f3, f + this.width, f3, this.mPaint);
            }
            this.mPaint.setColor(Color.parseColor(this.colors[i]));
            if (i == 0) {
                this.mCanvas.drawCircle(f, this.startY, (float) Utils.dip2px(this.mContext, 5.0f), this.mPaint);
            } else if (i == this.content.length - 1) {
                this.mCanvas.drawCircle(this.width + f, this.startY, (float) Utils.dip2px(this.mContext, 5.0f), this.mPaint);
            }
            this.mPaint.setColor(Color.parseColor("#999999"));
            this.mPaint.setTextSize((float) sp2px(this.mContext, 10.0f));
            this.mPaint.setTextAlign(Paint.Align.CENTER);
            this.mCanvas.drawText(this.content[i], (this.width / 2.0f) + f, this.startY + 80.0f, this.mPaint);
            f += this.width;
        }
        if (this.nums.length > 0 && this.num >= 0.0f) {
            canvasText();
        }
    }

    public void canvasContent(String[] strArr, float[] fArr, float f) {
        this.content = strArr;
        this.num = f;
        this.nums = fArr;
        invalidate();
    }

    public void canvasContent(String[] strArr, float[] fArr, float f, String[] strArr2) {
        this.colors = strArr2;
        this.content = strArr;
        this.num = f;
        this.nums = fArr;
        invalidate();
    }

    public void canvasContent(String[] strArr, float[] fArr, float f, String[] strArr2, int i) {
        this.colors = strArr2;
        this.content = strArr;
        this.num = f;
        this.nums = fArr;
        this.state = i;
        invalidate();
    }

    public void setColors(String[] strArr) {
        this.colors = strArr;
    }

    public void setState() {
        this.state = this.state;
    }

    public int getState() {
        return this.state;
    }

    private void canvasText() {
        float f;
        int i = 0;
        int i2 = 0;
        while (true) {
            float[] fArr = this.nums;
            f = 30.0f;
            if (i > fArr.length) {
                break;
            }
            if (i == 0) {
                float f2 = this.num;
                if (f2 <= fArr[i]) {
                    f = 30.0f + ((f2 / fArr[0]) * this.width);
                    i2 = 0;
                    break;
                }
            }
            float[] fArr2 = this.nums;
            if (i == fArr2.length) {
                i2 = fArr2.length;
                float f3 = this.num;
                int i3 = i - 1;
                float f4 = this.width;
                f = 30.0f + (((f3 - fArr2[i3]) / ((f3 * 2.0f) - fArr2[i3])) * f4) + (f4 * ((float) i));
                break;
            }
            if (i < fArr2.length && i > 0) {
                float f5 = this.num;
                int i4 = i - 1;
                if (f5 >= fArr2[i4] && f5 <= fArr2[i]) {
                    float f6 = this.width;
                    f = 30.0f + (((f5 - fArr2[i4]) / (fArr2[i] - fArr2[i4])) * f6) + (f6 * ((float) i));
                    i2 = i;
                    break;
                }
                i2 = i;
            }
            i++;
        }
        this.mPaint.setColor(Color.parseColor(this.colors[i2]));
        this.mCanvas.drawCircle(f, this.startY - 50.0f, (float) Utils.dip2px(this.mContext, 4.0f), this.mPaint);
        float[] fArr3 = {f, this.startY - 20.0f};
        float[] fArr4 = {f - ((float) Utils.dip2px(this.mContext, 4.0f)), this.startY - 50.0f};
        float[] fArr5 = {((float) Utils.dip2px(this.mContext, 4.0f)) + f, this.startY - 50.0f};
        Path path = new Path();
        path.moveTo(fArr3[0], fArr3[1]);
        path.lineTo(fArr4[0], fArr4[1]);
        path.lineTo(fArr5[0], fArr5[1]);
        this.mCanvas.drawPath(path, this.mPaint);
        this.mPaint.setColor(Color.parseColor(this.colors[i2]));
        this.mPaint.setTextSize((float) sp2px(this.mContext, 10.0f));
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mCanvas.drawText(String.valueOf(this.num), f, this.startY - 70.0f, this.mPaint);
    }

    public int sp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }
}
