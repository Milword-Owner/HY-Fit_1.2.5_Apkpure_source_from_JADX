package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.C1722R;
import com.google.android.material.chip.Chip;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Arrays;

class ChipTextInputComboView extends FrameLayout implements Checkable {
    /* access modifiers changed from: private */
    public final Chip chip;
    private final EditText editText;
    private TextView label;
    /* access modifiers changed from: private */
    public final TextInputLayout textInputLayout;
    private TextWatcher watcher;

    public ChipTextInputComboView(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public ChipTextInputComboView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChipTextInputComboView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater from = LayoutInflater.from(context);
        this.chip = (Chip) from.inflate(C1722R.C1727layout.material_time_chip, this, false);
        this.textInputLayout = (TextInputLayout) from.inflate(C1722R.C1727layout.material_time_input, this, false);
        this.editText = this.textInputLayout.getEditText();
        this.editText.setVisibility(4);
        this.watcher = new HintSetterTextWatcher();
        this.editText.addTextChangedListener(this.watcher);
        updateHintLocales();
        addView(this.chip);
        addView(this.textInputLayout);
        this.label = (TextView) findViewById(C1722R.C1725id.material_label);
    }

    private void updateHintLocales() {
        if (Build.VERSION.SDK_INT >= 24) {
            this.editText.setImeHintLocales(getContext().getResources().getConfiguration().getLocales());
        }
    }

    public boolean isChecked() {
        return this.chip.isChecked();
    }

    public void setChecked(boolean z) {
        this.chip.setChecked(z);
        int i = 0;
        this.editText.setVisibility(z ? 0 : 4);
        Chip chip2 = this.chip;
        if (z) {
            i = 8;
        }
        chip2.setVisibility(i);
        if (isChecked()) {
            this.editText.requestFocus();
        }
    }

    public void toggle() {
        this.chip.toggle();
    }

    public void setText(CharSequence charSequence) {
        this.chip.setText(formatText(charSequence));
        EditText editText2 = this.textInputLayout.getEditText();
        if (!TextUtils.isEmpty(editText2.getText())) {
            editText2.removeTextChangedListener(this.watcher);
            editText2.setText((CharSequence) null);
            editText2.setHint(formatText("00"));
            editText2.addTextChangedListener(this.watcher);
        }
        editText2.setHint(formatText(charSequence));
    }

    /* access modifiers changed from: private */
    public String formatText(CharSequence charSequence) {
        return TimeModel.formatText(getResources(), charSequence);
    }

    public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
        this.chip.setOnClickListener(onClickListener);
    }

    public void setTag(int i, Object obj) {
        this.chip.setTag(i, obj);
    }

    public void setHelperText(CharSequence charSequence) {
        this.label.setText(charSequence);
    }

    public void setCursorVisible(boolean z) {
        this.editText.setCursorVisible(z);
    }

    public void addInputFilter(InputFilter inputFilter) {
        InputFilter[] filters = this.editText.getFilters();
        InputFilter[] inputFilterArr = (InputFilter[]) Arrays.copyOf(filters, filters.length + 1);
        inputFilterArr[filters.length] = inputFilter;
        this.editText.setFilters(inputFilterArr);
    }

    public TextInputLayout getTextInput() {
        return this.textInputLayout;
    }

    public void setChipDelegate(AccessibilityDelegateCompat accessibilityDelegateCompat) {
        ViewCompat.setAccessibilityDelegate(this.chip, accessibilityDelegateCompat);
    }

    private class HintSetterTextWatcher extends TextWatcherAdapter {
        private static final String DEFAULT_HINT = "00";

        private HintSetterTextWatcher() {
        }

        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(editable)) {
                ChipTextInputComboView chipTextInputComboView = ChipTextInputComboView.this;
                chipTextInputComboView.setText(chipTextInputComboView.formatText(DEFAULT_HINT));
                return;
            }
            ChipTextInputComboView.this.textInputLayout.getEditText().setHint((CharSequence) null);
            ChipTextInputComboView.this.chip.setText(ChipTextInputComboView.this.formatText(editable));
        }
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateHintLocales();
    }
}
