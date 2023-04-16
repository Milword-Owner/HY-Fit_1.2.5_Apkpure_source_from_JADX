package p005cn.sharesdk.onekeyshare.themes.classic;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.ImageView;

/* renamed from: cn.sharesdk.onekeyshare.themes.classic.RotateImageView */
public class RotateImageView extends ImageView {
    private float rotation;

    public RotateImageView(Context context) {
        super(context);
    }

    public void setRotation(float f) {
        this.rotation = f;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.rotate(this.rotation, (float) (getWidth() / 2), (float) (getHeight() / 2));
        super.onDraw(canvas);
    }
}
