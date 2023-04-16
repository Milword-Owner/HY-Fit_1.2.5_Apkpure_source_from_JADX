package p005cn.sharesdk.onekeyshare.themes.classic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/* renamed from: cn.sharesdk.onekeyshare.themes.classic.XView */
public class XView extends View {
    private float ratio;

    public XView(Context context) {
        super(context);
    }

    public void setRatio(float f) {
        this.ratio = f;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(-6250336);
        float width = (float) (getWidth() / 2);
        canvas.drawRect(width, 0.0f, (float) getWidth(), (float) (getHeight() / 2), paint);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setStrokeWidth(this.ratio * 3.0f);
        paint2.setColor(-1);
        float f = this.ratio * 8.0f;
        float f2 = width + f;
        float f3 = width - f;
        Canvas canvas2 = canvas;
        float f4 = f2;
        Paint paint3 = paint2;
        canvas2.drawLine(f4, f, ((float) getWidth()) - f, f3, paint3);
        canvas2.drawLine(f4, f3, ((float) getWidth()) - f, f, paint3);
    }
}
