package com.huayu.tzc.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.huayu.tzc.C2128R;

public class CircularView extends View {
    private Canvas canvas;
    private int centre;
    private View.OnClickListener listener;
    private Context mContext;
    private float num = 0.0f;
    private RectF oval;

    /* renamed from: p */
    private Paint f1688p;
    private Path path;
    private int radius;
    private RectF rectF3;
    private String unit = "";
    private String weight = "";

    public CircularView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        init();
    }

    private void init() {
        this.f1688p = new Paint();
        this.path = new Path();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas2) {
        Canvas canvas3 = canvas2;
        super.onDraw(canvas2);
        this.canvas = canvas3;
        this.f1688p.setStrokeWidth((float) dip2px(this.mContext, 9.0f));
        this.f1688p.setAntiAlias(true);
        this.f1688p.setStyle(Paint.Style.STROKE);
        this.f1688p.setColor(Color.parseColor("#56A3FA"));
        this.f1688p.setStrokeCap(Paint.Cap.ROUND);
        this.centre = getWidth() / 2;
        this.radius = (this.centre - (dip2px(this.mContext, 10.0f) / 2)) - 2;
        int i = this.centre;
        int i2 = this.radius;
        this.oval = new RectF((float) ((i - i2) - 1), (float) ((i - i2) - 1), (float) (i + i2 + 1), (float) (i + i2 + 1));
        this.f1688p.setColor(Color.parseColor("#56A3FA"));
        canvas2.drawArc(this.oval, 125.0f, 75.0f, false, this.f1688p);
        this.f1688p.setColor(Color.parseColor("#0EDAAE"));
        canvas2.drawArc(this.oval, 200.0f, 75.0f, false, this.f1688p);
        this.f1688p.setColor(Color.parseColor("#FAD65B"));
        canvas2.drawArc(this.oval, 275.0f, 75.0f, false, this.f1688p);
        this.f1688p.setColor(Color.parseColor("#F66B89"));
        canvas2.drawArc(this.oval, 342.0f, 75.0f, false, this.f1688p);
        int i3 = this.centre;
        int i4 = this.radius;
        float f = (float) (((i3 - i4) - 1) + 40);
        float f2 = (float) (((i3 - i4) - 1) + 40);
        float f3 = (float) (((i3 + i4) + 1) - 40);
        float f4 = (float) (((i3 + i4) + 1) - 40);
        RectF rectF = new RectF(f, f2, f3, f4);
        this.f1688p.setColor(Color.parseColor("#56A3FA"));
        this.f1688p.setStrokeWidth((float) dip2px(this.mContext, 2.0f));
        Log.e(ViewHierarchyConstants.TAG_KEY, "onDraw: " + this.radius);
        int i5 = 110;
        for (int i6 = 0; i6 < 18; i6++) {
            float[] coordinatePoint = getCoordinatePoint(rectF.centerX(), rectF.centerY(), this.radius - 40, (float) i5);
            canvas3.drawCircle(coordinatePoint[0], coordinatePoint[1], (float) dip2px(this.mContext, 1.0f), this.f1688p);
            i5 += 19;
        }
        RectF rectF2 = new RectF(f + 40.0f, f2 + 40.0f, f3 - 40.0f, f4 - 40.0f);
        this.f1688p.setColor(Color.parseColor("#D8E8FF"));
        this.f1688p.setStrokeWidth((float) dip2px(this.mContext, 9.0f));
        canvas2.drawArc(rectF2, 0.0f, 360.0f, false, this.f1688p);
        if (!TextUtils.isEmpty(this.weight)) {
            double d = (double) this.num;
            Double.isNaN(d);
            drawWeightPointer(50, (float) (d * 1.8823d));
        }
        this.f1688p.setStyle(Paint.Style.FILL);
        this.f1688p.setColor(Color.parseColor("#3A8BFF"));
        this.rectF3 = new RectF(f + 50.0f, f2 + 50.0f, f3 - 50.0f, f4 - 50.0f);
        canvas2.drawArc(this.rectF3, 0.0f, 360.0f, false, this.f1688p);
        if (TextUtils.isEmpty(this.weight)) {
            setWeight2(this.weight);
            return;
        }
        drawWeight(this.weight);
        drawText();
    }

    public float[] getCoordinatePoint(float f, float f2, int i, float f3) {
        float f4 = f;
        float f5 = f2;
        int i2 = i;
        float f6 = f3;
        float[] fArr = new float[2];
        double radians = Math.toRadians((double) f6);
        if (f6 < 90.0f) {
            double d = (double) f4;
            double cos = Math.cos(radians);
            double d2 = (double) i2;
            Double.isNaN(d2);
            Double.isNaN(d);
            fArr[0] = (float) (d + (cos * d2));
            double d3 = (double) f5;
            double sin = Math.sin(radians);
            Double.isNaN(d2);
            Double.isNaN(d3);
            fArr[1] = (float) (d3 + (sin * d2));
        } else if (f6 == 90.0f) {
            fArr[0] = f4;
            fArr[1] = ((float) i2) + f5;
        } else if (f6 > 90.0f && f6 < 180.0f) {
            double d4 = (double) (180.0f - f6);
            Double.isNaN(d4);
            double d5 = (d4 * 3.141592653589793d) / 180.0d;
            double d6 = (double) f4;
            double cos2 = Math.cos(d5);
            double d7 = (double) i2;
            Double.isNaN(d7);
            Double.isNaN(d6);
            fArr[0] = (float) (d6 - (cos2 * d7));
            double d8 = (double) f5;
            double sin2 = Math.sin(d5);
            Double.isNaN(d7);
            Double.isNaN(d8);
            fArr[1] = (float) (d8 + (sin2 * d7));
        } else if (f6 == 180.0f) {
            fArr[0] = f4 - ((float) i2);
            fArr[1] = f5;
        } else if (f6 > 180.0f && f6 < 270.0f) {
            double d9 = (double) (f6 - 180.0f);
            Double.isNaN(d9);
            double d10 = (d9 * 3.141592653589793d) / 180.0d;
            double d11 = (double) f4;
            double cos3 = Math.cos(d10);
            double d12 = (double) i2;
            Double.isNaN(d12);
            Double.isNaN(d11);
            fArr[0] = (float) (d11 - (cos3 * d12));
            double d13 = (double) f5;
            double sin3 = Math.sin(d10);
            Double.isNaN(d12);
            Double.isNaN(d13);
            fArr[1] = (float) (d13 - (sin3 * d12));
        } else if (f6 == 270.0f) {
            fArr[0] = f4;
            fArr[1] = f5 - ((float) i2);
        } else {
            double d14 = (double) (360.0f - f6);
            Double.isNaN(d14);
            double d15 = (d14 * 3.141592653589793d) / 180.0d;
            double d16 = (double) f4;
            double cos4 = Math.cos(d15);
            double d17 = (double) i2;
            Double.isNaN(d17);
            Double.isNaN(d16);
            fArr[0] = (float) (d16 + (cos4 * d17));
            double d18 = (double) f5;
            double sin4 = Math.sin(d15);
            Double.isNaN(d17);
            Double.isNaN(d18);
            fArr[1] = (float) (d18 - (sin4 * d17));
        }
        Log.e("getCoordinatePoint", "radius=" + i2 + ",cirAngle=" + f6 + ",point[0]=" + fArr[0] + ",point[1]=" + fArr[1]);
        return fArr;
    }

    private void drawText() {
        this.f1688p.setTypeface(Typeface.DEFAULT);
        this.f1688p.setTextAlign(Paint.Align.CENTER);
        this.f1688p.setColor(Color.parseColor("#ffffff"));
        this.f1688p.setTextSize((float) sp2px(this.mContext, 12.0f));
        this.f1688p.setStrokeWidth(0.0f);
        this.canvas.drawText(getContext().getString(C2128R.string.sj_weight), this.rectF3.centerX(), this.rectF3.centerY() + 80.0f, this.f1688p);
        this.f1688p.setTextAlign(Paint.Align.CENTER);
    }

    public void setWeight(float f, String str, String str2) {
        this.weight = str2 + str;
        this.num = f;
        invalidate();
    }

    public void drawWeight(String str) {
        this.f1688p.setTextAlign(Paint.Align.CENTER);
        this.f1688p.setTypeface(Typeface.DEFAULT_BOLD);
        this.f1688p.setColor(Color.parseColor("#ffffff"));
        this.f1688p.setTextSize((float) sp2px(this.mContext, 20.0f));
        this.f1688p.setStrokeWidth(0.0f);
        this.canvas.drawText(str, this.rectF3.centerX(), this.rectF3.centerY(), this.f1688p);
    }

    public void setWeight2(String str) {
        this.f1688p.setTextAlign(Paint.Align.CENTER);
        this.f1688p.setTypeface(Typeface.DEFAULT);
        this.f1688p.setColor(Color.parseColor("#ffffff"));
        this.f1688p.setTextSize((float) sp2px(this.mContext, 16.0f));
        this.f1688p.setStrokeWidth(0.0f);
        this.canvas.drawText(getContext().getString(C2128R.string.click), this.rectF3.centerX(), this.rectF3.centerY() - 20.0f, this.f1688p);
        this.canvas.drawText(getContext().getString(C2128R.string.start_measure), this.rectF3.centerX(), this.rectF3.centerY() + 60.0f, this.f1688p);
    }

    public void drawWeightPointer(int i, float f) {
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        this.canvas.save();
        this.canvas.rotate(f + 180.0f, (float) width, (float) height);
        int[] iArr = {width, (height - this.radius) + dip2px(this.mContext, 30.0f)};
        float f2 = (float) (i / 2);
        int[] iArr2 = {width - dip2px(this.mContext, f2), height};
        int[] iArr3 = {width + dip2px(this.mContext, f2), height};
        this.f1688p.setColor(Color.parseColor("#FAD65B"));
        Path path2 = new Path();
        path2.moveTo((float) iArr[0], (float) iArr[1]);
        path2.lineTo((float) iArr2[0], (float) iArr2[1]);
        path2.lineTo((float) iArr3[0], (float) iArr3[1]);
        path2.close();
        this.canvas.drawPath(path2, this.f1688p);
        this.canvas.restore();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        motionEvent.getAction();
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public int sp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public int dip2px(float f) {
        return (int) ((f * this.mContext.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
