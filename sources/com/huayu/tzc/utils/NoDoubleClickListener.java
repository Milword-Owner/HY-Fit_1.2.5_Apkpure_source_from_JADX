package com.huayu.tzc.utils;

import android.view.View;
import java.util.Calendar;

public abstract class NoDoubleClickListener implements View.OnClickListener {
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;

    /* access modifiers changed from: protected */
    public abstract void noDoubleClick(View view);

    public void onClick(View view) {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        if (timeInMillis - this.lastClickTime > 1000) {
            this.lastClickTime = timeInMillis;
            noDoubleClick(view);
        }
    }
}
