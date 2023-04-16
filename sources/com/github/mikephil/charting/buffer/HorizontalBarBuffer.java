package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

public class HorizontalBarBuffer extends BarBuffer {
    public HorizontalBarBuffer(int i, int i2, boolean z) {
        super(i, i2, z);
    }

    public void feed(IBarDataSet iBarDataSet) {
        float f;
        float f2;
        float f3;
        float f4;
        float entryCount = ((float) iBarDataSet.getEntryCount()) * this.phaseX;
        float f5 = this.mBarWidth / 2.0f;
        for (int i = 0; ((float) i) < entryCount; i++) {
            BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForIndex(i);
            if (barEntry != null) {
                float x = barEntry.getX();
                float y = barEntry.getY();
                float[] yVals = barEntry.getYVals();
                if (!this.mContainsStacks || yVals == null) {
                    float f6 = x - f5;
                    float f7 = x + f5;
                    if (this.mInverted) {
                        float f8 = y >= 0.0f ? y : 0.0f;
                        if (y > 0.0f) {
                            y = 0.0f;
                        }
                        float f9 = y;
                        y = f8;
                        f = f9;
                    } else {
                        f = y >= 0.0f ? y : 0.0f;
                        if (y > 0.0f) {
                            y = 0.0f;
                        }
                    }
                    if (f > 0.0f) {
                        f *= this.phaseY;
                    } else {
                        y *= this.phaseY;
                    }
                    addBar(y, f7, f, f6);
                } else {
                    float f10 = -barEntry.getNegativeSum();
                    int i2 = 0;
                    float f11 = 0.0f;
                    while (i2 < yVals.length) {
                        float f12 = yVals[i2];
                        if (f12 >= 0.0f) {
                            f3 = f12 + f11;
                            f2 = f10;
                            f10 = f11;
                            f11 = f3;
                        } else {
                            float abs = Math.abs(f12) + f10;
                            f2 = Math.abs(f12) + f10;
                            f3 = abs;
                        }
                        float f13 = x - f5;
                        float f14 = x + f5;
                        if (this.mInverted) {
                            float f15 = f10 >= f3 ? f10 : f3;
                            if (f10 > f3) {
                                f10 = f3;
                            }
                            float f16 = f10;
                            f10 = f15;
                            f4 = f16;
                        } else {
                            f4 = f10 >= f3 ? f10 : f3;
                            if (f10 > f3) {
                                f10 = f3;
                            }
                        }
                        addBar(f10 * this.phaseY, f14, f4 * this.phaseY, f13);
                        i2++;
                        f10 = f2;
                    }
                }
            }
        }
        reset();
    }
}
