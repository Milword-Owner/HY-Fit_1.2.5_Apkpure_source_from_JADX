package com.hjq.toast.style;

import android.content.Context;

public class ToastQQStyle extends BaseToastStyle {
    public int getBackgroundColor() {
        return -13421773;
    }

    public int getTextColor() {
        return -1842205;
    }

    public int getZ() {
        return 0;
    }

    public ToastQQStyle(Context context) {
        super(context);
    }

    public int getCornerRadius() {
        return dp2px(4.0f);
    }

    public float getTextSize() {
        return (float) sp2px(12.0f);
    }

    public int getPaddingStart() {
        return dp2px(16.0f);
    }

    public int getPaddingTop() {
        return dp2px(14.0f);
    }
}
