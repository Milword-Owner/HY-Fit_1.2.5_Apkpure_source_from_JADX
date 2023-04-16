package com.huayu.tzc.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.huayu.tzc.C2128R;

public class MyLinearLayout extends LinearLayout {
    private ImageView imageView;
    private ImageView imageViewNext;
    private Context mContext;
    private TextView textView;
    private TextView textViewContent;

    public MyLinearLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initView(attributeSet);
    }

    private void initView(AttributeSet attributeSet) {
        LayoutInflater.from(this.mContext).inflate(C2128R.C2133layout.mylinearlayout, this, true);
        this.imageView = (ImageView) findViewById(C2128R.C2131id.linear_img);
        this.imageViewNext = (ImageView) findViewById(C2128R.C2131id.linear_next);
        this.textView = (TextView) findViewById(C2128R.C2131id.linear_text);
        this.textViewContent = (TextView) findViewById(C2128R.C2131id.linear_content);
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, C2128R.styleable.MyLinearLayout);
        if (obtainStyledAttributes != null) {
            this.imageView.setImageResource(obtainStyledAttributes.getResourceId(1, C2128R.C2130drawable.ic_about));
            this.textView.setText(obtainStyledAttributes.getString(3));
            String string = obtainStyledAttributes.getString(0);
            if (!TextUtils.isEmpty(string)) {
                this.textViewContent.setText(string);
                this.textViewContent.setVisibility(0);
            }
            if (obtainStyledAttributes.getBoolean(2, true)) {
                this.imageViewNext.setVisibility(0);
            } else {
                this.imageViewNext.setVisibility(8);
            }
        }
        obtainStyledAttributes.recycle();
    }

    public void setContent(String str) {
        this.textViewContent.setText(str);
        this.textViewContent.setVisibility(0);
    }
}
