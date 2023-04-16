package com.huayu.tzc.customview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.huayu.tzc.C2128R;

public class MyHeaderView extends RelativeLayout {
    private boolean back_show;
    private Context context;
    private ImageView pageBack;
    private TextView pageMenu;
    private ImageView pageMenuImg;
    private TextView pageTitle;
    /* access modifiers changed from: private */
    public TextClickListener textClickListener;
    /* access modifiers changed from: private */
    public View view;

    public interface TextClickListener {
        void onMenuTextClick(View view);
    }

    public MyHeaderView(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        this.context = context2;
        initView(attributeSet);
    }

    private void initView(AttributeSet attributeSet) {
        this.view = LayoutInflater.from(this.context).inflate(C2128R.C2133layout.header_title_bar, this, true);
        this.pageBack = (ImageView) findViewById(C2128R.C2131id.page_back);
        this.pageTitle = (TextView) findViewById(C2128R.C2131id.page_title);
        this.pageMenu = (TextView) findViewById(C2128R.C2131id.page_menu_text);
        this.pageMenuImg = (ImageView) findViewById(C2128R.C2131id.page_menu);
        TypedArray obtainStyledAttributes = this.context.obtainStyledAttributes(attributeSet, C2128R.styleable.MyHeaderView);
        if (obtainStyledAttributes != null) {
            String string = obtainStyledAttributes.getString(1);
            if (!TextUtils.isEmpty(string)) {
                this.pageTitle.setText(string);
            }
            if (obtainStyledAttributes.getBoolean(5, false)) {
                this.pageMenu.setVisibility(0);
            } else {
                this.pageMenu.setVisibility(8);
            }
            this.back_show = obtainStyledAttributes.getBoolean(0, true);
            if (this.back_show) {
                this.pageBack.setVisibility(0);
                this.pageBack.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ((Activity) MyHeaderView.this.getContext()).finish();
                    }
                });
            } else {
                this.pageBack.setVisibility(8);
            }
            String string2 = obtainStyledAttributes.getString(4);
            if (!TextUtils.isEmpty(string2)) {
                this.pageMenu.setText(string2);
                this.pageMenu.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (MyHeaderView.this.textClickListener != null) {
                            MyHeaderView.this.textClickListener.onMenuTextClick(view);
                        }
                    }
                });
            }
            this.pageMenuImg.setImageResource(obtainStyledAttributes.getResourceId(2, C2128R.C2130drawable.ic_add));
            if (obtainStyledAttributes.getBoolean(3, false)) {
                this.pageMenuImg.setVisibility(0);
                this.pageMenuImg.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (MyHeaderView.this.textClickListener != null) {
                            MyHeaderView.this.textClickListener.onMenuTextClick(MyHeaderView.this.view);
                        }
                    }
                });
            } else {
                this.pageMenuImg.setVisibility(8);
            }
        }
        obtainStyledAttributes.recycle();
    }

    public void setTextClickListener(TextClickListener textClickListener2) {
        this.textClickListener = textClickListener2;
    }

    public void setTitle(String str) {
        this.pageTitle.setText(str);
    }

    public void setBackShow(boolean z) {
        this.pageBack.setVisibility(8);
    }

    public void setMenu(String str) {
        this.pageMenu.setText(str);
        this.pageMenu.setVisibility(0);
        this.pageMenuImg.setVisibility(8);
        this.pageMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MyHeaderView.this.textClickListener != null) {
                    MyHeaderView.this.textClickListener.onMenuTextClick(MyHeaderView.this.view);
                }
            }
        });
    }

    public void setMenuImg(int i) {
        this.pageMenu.setVisibility(8);
        this.pageMenuImg.setVisibility(0);
        this.pageMenuImg.setImageDrawable(ContextCompat.getDrawable(this.context, i));
        this.pageMenuImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MyHeaderView.this.textClickListener != null) {
                    MyHeaderView.this.textClickListener.onMenuTextClick(MyHeaderView.this.view);
                }
            }
        });
    }

    public void setMenuTextColor(String str) {
        this.pageMenu.setTextColor(Color.parseColor(str));
    }
}
