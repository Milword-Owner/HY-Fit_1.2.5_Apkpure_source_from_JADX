package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.C0041Px;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.C1722R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;

public final class CircularProgressIndicatorSpec extends BaseProgressIndicatorSpec {
    public int indicatorDirection;
    @C0041Px
    public int indicatorInset;
    @C0041Px
    public int indicatorSize;

    public CircularProgressIndicatorSpec(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet, C1722R.attr.circularProgressIndicatorStyle);
        loadSpecFromAttributes(context, attributeSet);
    }

    private void loadSpecFromAttributes(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        loadAttributes(context, attributeSet);
        validateSpec();
    }

    private void loadAttributes(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(C1722R.dimen.mtrl_progress_circular_size);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(C1722R.dimen.mtrl_progress_circular_inset);
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, C1722R.styleable.CircularProgressIndicator, C1722R.attr.circularProgressIndicatorStyle, CircularProgressIndicator.DEF_STYLE_RES, new int[0]);
        this.indicatorSize = MaterialResources.getDimensionPixelSize(context, obtainStyledAttributes, C1722R.styleable.CircularProgressIndicator_indicatorSize, dimensionPixelSize);
        this.indicatorInset = MaterialResources.getDimensionPixelSize(context, obtainStyledAttributes, C1722R.styleable.CircularProgressIndicator_indicatorInset, dimensionPixelSize2);
        this.indicatorDirection = obtainStyledAttributes.getInt(C1722R.styleable.CircularProgressIndicator_indicatorDirectionCircular, 0);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: package-private */
    public void validateSpec() {
        if (this.indicatorSize < this.trackThickness * 2) {
            throw new IllegalArgumentException("The indicatorSize (" + this.indicatorSize + " px) cannot be less than twice of the trackThickness (" + this.trackThickness + " px).");
        }
    }
}
