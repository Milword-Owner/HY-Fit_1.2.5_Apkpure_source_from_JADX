package com.hjq.toast.style;

import android.content.Context;

public class ToastBlackStyle extends BaseToastStyle {
    public int getBackgroundColor() {
        return -2013265920;
    }

    public int getTextColor() {
        return -285212673;
    }

    public ToastBlackStyle(Context context) {
        super(context);
    }

    public int getCornerRadius() {
        return dp2px(8.0f);
    }

    public float getTextSize() {
        return (float) sp2px(14.0f);
    }

    public int getPaddingStart() {
        return dp2px(24.0f);
    }

    public int getPaddingTop() {
        return dp2px(16.0f);
    }
}
