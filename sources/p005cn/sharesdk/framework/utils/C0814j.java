package p005cn.sharesdk.framework.utils;

import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;

/* renamed from: cn.sharesdk.framework.utils.j */
/* compiled from: ViewRound */
public class C0814j {
    /* renamed from: a */
    public static ShapeDrawable m699a(float f, int i) {
        float[] fArr = new float[8];
        float[] fArr2 = new float[8];
        for (int i2 = 0; i2 < 8; i2++) {
            fArr[i2] = 0.0f + f;
            fArr2[i2] = f;
        }
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, new RectF(0.0f, 0.0f, 0.0f, 0.0f), fArr2));
        shapeDrawable.getPaint().setColor(i);
        return shapeDrawable;
    }
}
