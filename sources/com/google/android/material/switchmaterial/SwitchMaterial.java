package com.google.android.material.switchmaterial;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.C1722R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

public class SwitchMaterial extends SwitchCompat {
    private static final int DEF_STYLE_RES = C1722R.C1728style.Widget_MaterialComponents_CompoundButton_Switch;
    private static final int[][] ENABLED_CHECKED_STATES = {new int[]{16842910, 16842912}, new int[]{16842910, -16842912}, new int[]{-16842910, 16842912}, new int[]{-16842910, -16842912}};
    @NonNull
    private final ElevationOverlayProvider elevationOverlayProvider;
    @Nullable
    private ColorStateList materialThemeColorsThumbTintList;
    @Nullable
    private ColorStateList materialThemeColorsTrackTintList;
    private boolean useMaterialThemeColors;

    public SwitchMaterial(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public SwitchMaterial(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, C1722R.attr.switchStyle);
    }

    public SwitchMaterial(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, DEF_STYLE_RES), attributeSet, i);
        Context context2 = getContext();
        this.elevationOverlayProvider = new ElevationOverlayProvider(context2);
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, C1722R.styleable.SwitchMaterial, i, DEF_STYLE_RES, new int[0]);
        this.useMaterialThemeColors = obtainStyledAttributes.getBoolean(C1722R.styleable.SwitchMaterial_useMaterialThemeColors, false);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.useMaterialThemeColors && getThumbTintList() == null) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
        }
        if (this.useMaterialThemeColors && getTrackTintList() == null) {
            setTrackTintList(getMaterialThemeColorsTrackTintList());
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.useMaterialThemeColors = z;
        if (z) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
            setTrackTintList(getMaterialThemeColorsTrackTintList());
            return;
        }
        setThumbTintList((ColorStateList) null);
        setTrackTintList((ColorStateList) null);
    }

    public boolean isUseMaterialThemeColors() {
        return this.useMaterialThemeColors;
    }

    private ColorStateList getMaterialThemeColorsThumbTintList() {
        if (this.materialThemeColorsThumbTintList == null) {
            int color = MaterialColors.getColor(this, C1722R.attr.colorSurface);
            int color2 = MaterialColors.getColor(this, C1722R.attr.colorControlActivated);
            float dimension = getResources().getDimension(C1722R.dimen.mtrl_switch_thumb_elevation);
            if (this.elevationOverlayProvider.isThemeElevationOverlayEnabled()) {
                dimension += ViewUtils.getParentAbsoluteElevation(this);
            }
            int compositeOverlayIfNeeded = this.elevationOverlayProvider.compositeOverlayIfNeeded(color, dimension);
            int[] iArr = new int[ENABLED_CHECKED_STATES.length];
            iArr[0] = MaterialColors.layer(color, color2, 1.0f);
            iArr[1] = compositeOverlayIfNeeded;
            iArr[2] = MaterialColors.layer(color, color2, 0.38f);
            iArr[3] = compositeOverlayIfNeeded;
            this.materialThemeColorsThumbTintList = new ColorStateList(ENABLED_CHECKED_STATES, iArr);
        }
        return this.materialThemeColorsThumbTintList;
    }

    private ColorStateList getMaterialThemeColorsTrackTintList() {
        if (this.materialThemeColorsTrackTintList == null) {
            int[] iArr = new int[ENABLED_CHECKED_STATES.length];
            int color = MaterialColors.getColor(this, C1722R.attr.colorSurface);
            int color2 = MaterialColors.getColor(this, C1722R.attr.colorControlActivated);
            int color3 = MaterialColors.getColor(this, C1722R.attr.colorOnSurface);
            iArr[0] = MaterialColors.layer(color, color2, 0.54f);
            iArr[1] = MaterialColors.layer(color, color3, 0.32f);
            iArr[2] = MaterialColors.layer(color, color2, 0.12f);
            iArr[3] = MaterialColors.layer(color, color3, 0.12f);
            this.materialThemeColorsTrackTintList = new ColorStateList(ENABLED_CHECKED_STATES, iArr);
        }
        return this.materialThemeColorsTrackTintList;
    }
}
