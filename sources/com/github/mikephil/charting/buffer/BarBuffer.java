package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

public class BarBuffer extends AbstractBuffer<IBarDataSet> {
    protected float mBarWidth = 1.0f;
    protected boolean mContainsStacks = false;
    protected int mDataSetCount = 1;
    protected int mDataSetIndex = 0;
    protected boolean mInverted = false;

    public BarBuffer(int i, int i2, boolean z) {
        super(i);
        this.mDataSetCount = i2;
        this.mContainsStacks = z;
    }

    public void setBarWidth(float f) {
        this.mBarWidth = f;
    }

    public void setDataSet(int i) {
        this.mDataSetIndex = i;
    }

    public void setInverted(boolean z) {
        this.mInverted = z;
    }

    /* access modifiers changed from: protected */
    public void addBar(float f, float f2, float f3, float f4) {
        float[] fArr = this.buffer;
        int i = this.index;
        this.index = i + 1;
        fArr[i] = f;
        float[] fArr2 = this.buffer;
        int i2 = this.index;
        this.index = i2 + 1;
        fArr2[i2] = f2;
        float[] fArr3 = this.buffer;
        int i3 = this.index;
        this.index = i3 + 1;
        fArr3[i3] = f3;
        float[] fArr4 = this.buffer;
        int i4 = this.index;
        this.index = i4 + 1;
        fArr4[i4] = f4;
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
                    addBar(f6, f, f7, y);
                } else {
                    float f10 = -barEntry.getNegativeSum();
                    int i2 = 0;
                    float f11 = 0.0f;
                    while (i2 < yVals.length) {
                        float f12 = yVals[i2];
                        if (f12 == 0.0f && (f11 == 0.0f || f10 == 0.0f)) {
                            f2 = f10;
                            f3 = f11;
                            f11 = f12;
                        } else if (f12 >= 0.0f) {
                            f12 += f11;
                            f2 = f10;
                            f3 = f12;
                        } else {
                            float abs = Math.abs(f12) + f10;
                            float abs2 = Math.abs(f12) + f10;
                            float f13 = f10;
                            f3 = f11;
                            f11 = f13;
                            float f14 = abs;
                            f2 = abs2;
                            f12 = f14;
                        }
                        float f15 = x - f5;
                        float f16 = x + f5;
                        if (this.mInverted) {
                            float f17 = f11 >= f12 ? f11 : f12;
                            if (f11 > f12) {
                                f11 = f12;
                            }
                            float f18 = f11;
                            f11 = f17;
                            f4 = f18;
                        } else {
                            f4 = f11 >= f12 ? f11 : f12;
                            if (f11 > f12) {
                                f11 = f12;
                            }
                        }
                        addBar(f15, f4 * this.phaseY, f16, f11 * this.phaseY);
                        i2++;
                        f11 = f3;
                        f10 = f2;
                    }
                }
            }
        }
        reset();
    }
}
