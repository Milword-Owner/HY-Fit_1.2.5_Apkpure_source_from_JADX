package com.github.mikephil.charting.data.filter;

import android.annotation.TargetApi;
import java.util.Arrays;

public class Approximator {
    @TargetApi(9)
    public float[] reduceWithDouglasPeucker(float[] fArr, float f) {
        Line line = new Line(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1]);
        float f2 = 0.0f;
        int i = 0;
        for (int i2 = 2; i2 < fArr.length - 2; i2 += 2) {
            float distance = line.distance(fArr[i2], fArr[i2 + 1]);
            if (distance > f2) {
                i = i2;
                f2 = distance;
            }
        }
        if (f2 <= f) {
            return line.getPoints();
        }
        float[] reduceWithDouglasPeucker = reduceWithDouglasPeucker(Arrays.copyOfRange(fArr, 0, i + 2), f);
        float[] reduceWithDouglasPeucker2 = reduceWithDouglasPeucker(Arrays.copyOfRange(fArr, i, fArr.length), f);
        return concat(reduceWithDouglasPeucker, Arrays.copyOfRange(reduceWithDouglasPeucker2, 2, reduceWithDouglasPeucker2.length));
    }

    /* access modifiers changed from: package-private */
    public float[] concat(float[]... fArr) {
        int i = 0;
        for (float[] length : fArr) {
            i += length.length;
        }
        float[] fArr2 = new float[i];
        int length2 = fArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length2) {
            int i4 = i3;
            for (float f : fArr[i2]) {
                fArr2[i4] = f;
                i4++;
            }
            i2++;
            i3 = i4;
        }
        return fArr2;
    }

    private class Line {

        /* renamed from: dx */
        private float f1553dx;

        /* renamed from: dy */
        private float f1554dy;
        private float exsy;
        private float length;
        private float[] points;
        private float sxey;

        public Line(float f, float f2, float f3, float f4) {
            this.f1553dx = f - f3;
            this.f1554dy = f2 - f4;
            this.sxey = f * f4;
            this.exsy = f3 * f2;
            float f5 = this.f1553dx;
            float f6 = this.f1554dy;
            this.length = (float) Math.sqrt((double) ((f5 * f5) + (f6 * f6)));
            this.points = new float[]{f, f2, f3, f4};
        }

        public float distance(float f, float f2) {
            return Math.abs((((this.f1554dy * f) - (this.f1553dx * f2)) + this.sxey) - this.exsy) / this.length;
        }

        public float[] getPoints() {
            return this.points;
        }
    }
}
