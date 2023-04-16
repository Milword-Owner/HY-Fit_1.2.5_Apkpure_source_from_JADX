package com.huayu.tzc.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;
import com.huayu.tzc.C2128R;

public class TimeCount extends CountDownTimer {
    private final Context mContext;
    private final TextView mTextView;

    public TimeCount(Context context, TextView textView, long j, long j2) {
        super(j, j2);
        this.mTextView = textView;
        this.mContext = context;
    }

    public void onTick(long j) {
        this.mTextView.setEnabled(false);
        this.mTextView.setClickable(false);
        TextView textView = this.mTextView;
        textView.setText((j / 1000) + "");
    }

    public void onFinish() {
        this.mTextView.setText("Send Code");
        this.mTextView.setEnabled(true);
        this.mTextView.setClickable(true);
        this.mTextView.setTextColor(this.mContext.getResources().getColor(C2128R.C2129color.colorWhite));
    }
}
