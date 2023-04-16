package com.huayu.tzc.customview;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;

public class MyEditTextView extends AppCompatEditText {
    public MyEditTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setFilters(new InputFilter[]{new SpaceFilter()});
    }

    public class SpaceFilter implements InputFilter {
        public SpaceFilter() {
        }

        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            if (charSequence.equals(" ")) {
                return "";
            }
            return null;
        }
    }
}
