package com.google.android.material.shape;

import android.graphics.RectF;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import java.util.Arrays;

public final class RelativeCornerSize implements CornerSize {
    private final float percent;

    public RelativeCornerSize(@FloatRange(from = 0.0d, mo652to = 1.0d) float f) {
        this.percent = f;
    }

    @FloatRange(from = 0.0d, mo652to = 1.0d)
    public float getRelativePercent() {
        return this.percent;
    }

    public float getCornerSize(@NonNull RectF rectF) {
        return this.percent * rectF.height();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof RelativeCornerSize) && this.percent == ((RelativeCornerSize) obj).percent) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.percent)});
    }
}
