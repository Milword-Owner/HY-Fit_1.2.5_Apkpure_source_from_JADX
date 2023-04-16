package com.hjq.toast.style;

import android.content.Context;

public class ToastAliPayStyle extends BaseToastStyle {
    public int getBackgroundColor() {
        return -296265897;
    }

    public int getGravity() {
        return 81;
    }

    public int getTextColor() {
        return -1;
    }

    public ToastAliPayStyle(Context context) {
        super(context);
    }

    public int getYOffset() {
        return dp2px(100.0f);
    }

    public int getCornerRadius() {
        return dp2px(5.0f);
    }

    public float getTextSize() {
        return (float) sp2px(16.0f);
    }

    public int getPaddingStart() {
        return dp2px(16.0f);
    }

    public int getPaddingTop() {
        return dp2px(10.0f);
    }
}
