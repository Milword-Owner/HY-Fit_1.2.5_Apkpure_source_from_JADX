package com.github.mikephil.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class SquareShapeRenderer implements IShapeRenderer {
    public void renderShape(Canvas canvas, IScatterDataSet iScatterDataSet, ViewPortHandler viewPortHandler, float f, float f2, Paint paint) {
        Paint paint2 = paint;
        float scatterShapeSize = iScatterDataSet.getScatterShapeSize();
        float f3 = scatterShapeSize / 2.0f;
        float convertDpToPixel = Utils.convertDpToPixel(iScatterDataSet.getScatterShapeHoleRadius());
        float f4 = (scatterShapeSize - (convertDpToPixel * 2.0f)) / 2.0f;
        float f5 = f4 / 2.0f;
        int scatterShapeHoleColor = iScatterDataSet.getScatterShapeHoleColor();
        if (((double) scatterShapeSize) > Utils.DOUBLE_EPSILON) {
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeWidth(f4);
            float f6 = f - convertDpToPixel;
            float f7 = f2 - convertDpToPixel;
            float f8 = f + convertDpToPixel;
            float f9 = f2 + convertDpToPixel;
            canvas.drawRect(f6 - f5, f7 - f5, f8 + f5, f9 + f5, paint);
            if (scatterShapeHoleColor != 1122867) {
                paint2.setStyle(Paint.Style.FILL);
                paint2.setColor(scatterShapeHoleColor);
                canvas.drawRect(f6, f7, f8, f9, paint);
                return;
            }
            return;
        }
        paint2.setStyle(Paint.Style.FILL);
        Canvas canvas2 = canvas;
        canvas2.drawRect(f - f3, f2 - f3, f + f3, f2 + f3, paint);
    }
}
