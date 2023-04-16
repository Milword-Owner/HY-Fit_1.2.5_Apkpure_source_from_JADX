package com.github.mikephil.charting.model;

public class GradientColor {
    private int endColor;
    private int startColor;

    public GradientColor(int i, int i2) {
        this.startColor = i;
        this.endColor = i2;
    }

    public int getStartColor() {
        return this.startColor;
    }

    public void setStartColor(int i) {
        this.startColor = i;
    }

    public int getEndColor() {
        return this.endColor;
    }

    public void setEndColor(int i) {
        this.endColor = i;
    }
}
