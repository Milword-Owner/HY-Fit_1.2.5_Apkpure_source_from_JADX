package com.huayu.tzc.customview.ruler;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import com.huayu.tzc.C2128R;

public class UnitLayout extends RelativeLayout implements RulerCallback {
    private RuleLayout mRuler;
    @ColorInt
    private int mScaleTextColor = Color.parseColor("#FF666666");
    private float mScaleTextSize = 80.0f;
    private String mUnitText = "kg";
    @ColorInt
    private int mUnitTextColor = Color.parseColor("#FF666666");
    private float mUnitTextSize = 40.0f;
    private OnValueChangeListener onValueChangeListener;
    private TextView textScale;
    private TextView textUnit;

    public interface OnValueChangeListener {
        void onValue(String str);
    }

    public UnitLayout(Context context) {
        super(context);
        init(context);
    }

    public UnitLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initAttrs(context, attributeSet);
        init(context);
    }

    public UnitLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C2128R.styleable.UnitLayout, 0, 0);
        this.mScaleTextSize = obtainStyledAttributes.getDimension(1, this.mScaleTextSize);
        this.mUnitTextSize = obtainStyledAttributes.getDimension(4, this.mUnitTextSize);
        this.mScaleTextColor = obtainStyledAttributes.getColor(0, this.mScaleTextColor);
        this.mUnitTextColor = obtainStyledAttributes.getColor(3, this.mUnitTextColor);
        String string = obtainStyledAttributes.getString(2);
        if (string != null) {
            this.mUnitText = string;
        }
        obtainStyledAttributes.recycle();
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(C2128R.C2133layout.layout_unit_number, this);
        this.textScale = (TextView) findViewById(C2128R.C2131id.textScale);
        this.textUnit = (TextView) findViewById(C2128R.C2131id.textUnit);
        this.textScale.setTextSize(0, this.mScaleTextSize);
        this.textScale.setTextColor(this.mScaleTextColor);
        this.textUnit.setTextSize(0, this.mUnitTextSize);
        this.textUnit.setTextColor(this.mUnitTextColor);
        this.textUnit.setText(this.mUnitText);
    }

    public void setTextUnit(String str) {
        this.mUnitText = str;
        this.textUnit.setText(this.mUnitText);
    }

    public void setOnValueChangeListener(OnValueChangeListener onValueChangeListener2) {
        this.onValueChangeListener = onValueChangeListener2;
    }

    public void bindRuler(RuleLayout ruleLayout) {
        this.mRuler = ruleLayout;
        ruleLayout.setCallback(this);
    }

    public void onScaleChanging(float f) {
        if (this.mRuler != null) {
            Log.e("s", "onScaleChanging: " + f);
            String resultValueOf = RulerStringUtil.resultValueOf(f, this.mRuler.getFactor());
            this.onValueChangeListener.onValue(resultValueOf);
            this.textScale.setText(resultValueOf);
        }
    }
}
