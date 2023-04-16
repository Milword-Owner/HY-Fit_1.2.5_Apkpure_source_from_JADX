package com.baidu.mobstat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.widget.TextView;

/* renamed from: com.baidu.mobstat.ae */
class C0882ae extends TextView {

    /* renamed from: a */
    private Paint f939a = new Paint();

    /* renamed from: b */
    private PaintFlagsDrawFilter f940b = new PaintFlagsDrawFilter(0, 3);

    public C0882ae(Context context) {
        super(context);
        this.f939a.setColor(-1);
        this.f939a.setAntiAlias(true);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int max = Math.max(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(max, max);
    }

    public void setBackgroundColor(int i) {
        this.f939a.setColor(i);
    }

    public void draw(Canvas canvas) {
        canvas.setDrawFilter(this.f940b);
        canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) (Math.max(getWidth(), getHeight()) / 2), this.f939a);
        super.draw(canvas);
    }
}
