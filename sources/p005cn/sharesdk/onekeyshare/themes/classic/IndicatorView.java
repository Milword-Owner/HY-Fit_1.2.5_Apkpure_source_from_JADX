package p005cn.sharesdk.onekeyshare.themes.classic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/* renamed from: cn.sharesdk.onekeyshare.themes.classic.IndicatorView */
public class IndicatorView extends View {
    private static final int DESIGN_BOTTOM_HEIGHT = 52;
    private static final int DESIGN_INDICATOR_DISTANCE = 14;
    private static final int DESIGN_INDICATOR_RADIUS = 6;
    private int count;
    private int current;

    public IndicatorView(Context context) {
        super(context);
    }

    public void setScreenCount(int i) {
        this.count = i;
    }

    public void onScreenChange(int i, int i2) {
        if (i != this.current) {
            this.current = i;
            postInvalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.count <= 1) {
            setVisibility(8);
            return;
        }
        float height = (float) getHeight();
        float f = (6.0f * height) / 52.0f;
        float f2 = (14.0f * height) / 52.0f;
        float f3 = f * 2.0f;
        int i = this.count;
        float width = (((float) getWidth()) - ((((float) i) * f3) + (((float) (i - 1)) * f2))) / 2.0f;
        float f4 = height / 2.0f;
        canvas.drawColor(-1);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        for (int i2 = 0; i2 < this.count; i2++) {
            if (i2 == this.current) {
                paint.setColor(-10653280);
            } else {
                paint.setColor(-5262921);
            }
            canvas.drawCircle(((f3 + f2) * ((float) i2)) + width, f4, f, paint);
        }
    }
}
