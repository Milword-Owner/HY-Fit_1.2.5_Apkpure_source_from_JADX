package com.github.mikephil.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class TriangleShapeRenderer implements IShapeRenderer {
    protected Path mTrianglePathBuffer = new Path();

    public void renderShape(Canvas canvas, IScatterDataSet iScatterDataSet, ViewPortHandler viewPortHandler, float f, float f2, Paint paint) {
        Canvas canvas2 = canvas;
        float f3 = f;
        Paint paint2 = paint;
        float scatterShapeSize = iScatterDataSet.getScatterShapeSize();
        float f4 = scatterShapeSize / 2.0f;
        float convertDpToPixel = (scatterShapeSize - (Utils.convertDpToPixel(iScatterDataSet.getScatterShapeHoleRadius()) * 2.0f)) / 2.0f;
        int scatterShapeHoleColor = iScatterDataSet.getScatterShapeHoleColor();
        paint2.setStyle(Paint.Style.FILL);
        Path path = this.mTrianglePathBuffer;
        path.reset();
        float f5 = f2 - f4;
        path.moveTo(f3, f5);
        float f6 = f3 + f4;
        float f7 = f2 + f4;
        path.lineTo(f6, f7);
        float f8 = f3 - f4;
        path.lineTo(f8, f7);
        double d = (double) scatterShapeSize;
        if (d > Utils.DOUBLE_EPSILON) {
            path.lineTo(f3, f5);
            float f9 = f8 + convertDpToPixel;
            float f10 = f7 - convertDpToPixel;
            path.moveTo(f9, f10);
            path.lineTo(f6 - convertDpToPixel, f10);
            path.lineTo(f3, f5 + convertDpToPixel);
            path.lineTo(f9, f10);
        }
        path.close();
        canvas2.drawPath(path, paint2);
        path.reset();
        if (d > Utils.DOUBLE_EPSILON && scatterShapeHoleColor != 1122867) {
            paint2.setColor(scatterShapeHoleColor);
            path.moveTo(f3, f5 + convertDpToPixel);
            float f11 = f7 - convertDpToPixel;
            path.lineTo(f6 - convertDpToPixel, f11);
            path.lineTo(f8 + convertDpToPixel, f11);
            path.close();
            canvas2.drawPath(path, paint2);
            path.reset();
        }
    }
}
