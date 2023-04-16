package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.C0043R;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.AutoSizeableTextView;
import java.lang.ref.WeakReference;

class AppCompatTextHelper {
    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int TEXT_FONT_WEIGHT_UNSPECIFIED = -1;
    private boolean mAsyncFontPending;
    @NonNull
    private final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
    private TintInfo mDrawableBottomTint;
    private TintInfo mDrawableEndTint;
    private TintInfo mDrawableLeftTint;
    private TintInfo mDrawableRightTint;
    private TintInfo mDrawableStartTint;
    private TintInfo mDrawableTint;
    private TintInfo mDrawableTopTint;
    private Typeface mFontTypeface;
    private int mFontWeight = -1;
    private int mStyle = 0;
    @NonNull
    private final TextView mView;

    AppCompatTextHelper(@NonNull TextView textView) {
        this.mView = textView;
        this.mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(this.mView);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x013f  */
    @android.annotation.SuppressLint({"NewApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadFromAttributes(@androidx.annotation.Nullable android.util.AttributeSet r19, int r20) {
        /*
            r18 = this;
            r7 = r18
            r8 = r19
            r9 = r20
            android.widget.TextView r0 = r7.mView
            android.content.Context r10 = r0.getContext()
            androidx.appcompat.widget.AppCompatDrawableManager r11 = androidx.appcompat.widget.AppCompatDrawableManager.get()
            int[] r0 = androidx.appcompat.C0043R.styleable.AppCompatTextHelper
            r12 = 0
            androidx.appcompat.widget.TintTypedArray r13 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r10, r8, r0, r9, r12)
            android.widget.TextView r0 = r7.mView
            android.content.Context r1 = r0.getContext()
            int[] r2 = androidx.appcompat.C0043R.styleable.AppCompatTextHelper
            android.content.res.TypedArray r4 = r13.getWrappedTypeArray()
            r6 = 0
            r3 = r19
            r5 = r20
            androidx.core.view.ViewCompat.saveAttributeDataForStyleable(r0, r1, r2, r3, r4, r5, r6)
            int r0 = androidx.appcompat.C0043R.styleable.AppCompatTextHelper_android_textAppearance
            r14 = -1
            int r0 = r13.getResourceId(r0, r14)
            int r1 = androidx.appcompat.C0043R.styleable.AppCompatTextHelper_android_drawableLeft
            boolean r1 = r13.hasValue(r1)
            if (r1 == 0) goto L_0x0046
            int r1 = androidx.appcompat.C0043R.styleable.AppCompatTextHelper_android_drawableLeft
            int r1 = r13.getResourceId(r1, r12)
            androidx.appcompat.widget.TintInfo r1 = createTintInfo(r10, r11, r1)
            r7.mDrawableLeftTint = r1
        L_0x0046:
            int r1 = androidx.appcompat.C0043R.styleable.AppCompatTextHelper_android_drawableTop
            boolean r1 = r13.hasValue(r1)
            if (r1 == 0) goto L_0x005a
            int r1 = androidx.appcompat.C0043R.styleable.AppCompatTextHelper_android_drawableTop
            int r1 = r13.getResourceId(r1, r12)
            androidx.appcompat.widget.TintInfo r1 = createTintInfo(r10, r11, r1)
            r7.mDrawableTopTint = r1
        L_0x005a:
            int r1 = androidx.appcompat.C0043R.styleable.AppCompatTextHelper_android_drawableRight
            boolean r1 = r13.hasValue(r1)
            if (r1 == 0) goto L_0x006e
            int r1 = androidx.appcompat.C0043R.styleable.AppCompatTextHelper_android_drawableRight
            int r1 = r13.getResourceId(r1, r12)
            androidx.appcompat.widget.TintInfo r1 = createTintInfo(r10, r11, r1)
            r7.mDrawableRightTint = r1
        L_0x006e:
            int r1 = androidx.appcompat.C0043R.styleable.AppCompatTextHelper_android_drawableBottom
            boolean r1 = r13.hasValue(r1)
            if (r1 == 0) goto L_0x0082
            int r1 = androidx.appcompat.C0043R.styleable.AppCompatTextHelper_android_drawableBottom
            int r1 = r13.getResourceId(r1, r12)
            androidx.appcompat.widget.TintInfo r1 = createTintInfo(r10, r11, r1)
            r7.mDrawableBottomTint = r1
        L_0x0082:
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 17
            if (r1 < r2) goto L_0x00b0
            int r1 = androidx.appcompat.C0043R.styleable.AppCompatTextHelper_android_drawableStart
            boolean r1 = r13.hasValue(r1)
            if (r1 == 0) goto L_0x009c
            int r1 = androidx.appcompat.C0043R.styleable.AppCompatTextHelper_android_drawableStart
            int r1 = r13.getResourceId(r1, r12)
            androidx.appcompat.widget.TintInfo r1 = createTintInfo(r10, r11, r1)
            r7.mDrawableStartTint = r1
        L_0x009c:
            int r1 = androidx.appcompat.C0043R.styleable.AppCompatTextHelper_android_drawableEnd
            boolean r1 = r13.hasValue(r1)
            if (r1 == 0) goto L_0x00b0
            int r1 = androidx.appcompat.C0043R.styleable.AppCompatTextHelper_android_drawableEnd
            int r1 = r13.getResourceId(r1, r12)
            androidx.appcompat.widget.TintInfo r1 = createTintInfo(r10, r11, r1)
            r7.mDrawableEndTint = r1
        L_0x00b0:
            r13.recycle()
            android.widget.TextView r1 = r7.mView
            android.text.method.TransformationMethod r1 = r1.getTransformationMethod()
            boolean r1 = r1 instanceof android.text.method.PasswordTransformationMethod
            r2 = 26
            r4 = 23
            if (r0 == r14) goto L_0x0144
            int[] r5 = androidx.appcompat.C0043R.styleable.TextAppearance
            androidx.appcompat.widget.TintTypedArray r0 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes((android.content.Context) r10, (int) r0, (int[]) r5)
            if (r1 != 0) goto L_0x00da
            int r5 = androidx.appcompat.C0043R.styleable.TextAppearance_textAllCaps
            boolean r5 = r0.hasValue(r5)
            if (r5 == 0) goto L_0x00da
            int r5 = androidx.appcompat.C0043R.styleable.TextAppearance_textAllCaps
            boolean r5 = r0.getBoolean(r5, r12)
            r6 = r5
            r5 = 1
            goto L_0x00dc
        L_0x00da:
            r5 = 0
            r6 = 0
        L_0x00dc:
            r7.updateTypefaceAndStyle(r10, r0)
            int r15 = android.os.Build.VERSION.SDK_INT
            if (r15 >= r4) goto L_0x0119
            int r15 = androidx.appcompat.C0043R.styleable.TextAppearance_android_textColor
            boolean r15 = r0.hasValue(r15)
            if (r15 == 0) goto L_0x00f2
            int r15 = androidx.appcompat.C0043R.styleable.TextAppearance_android_textColor
            android.content.res.ColorStateList r15 = r0.getColorStateList(r15)
            goto L_0x00f3
        L_0x00f2:
            r15 = 0
        L_0x00f3:
            int r3 = androidx.appcompat.C0043R.styleable.TextAppearance_android_textColorHint
            boolean r3 = r0.hasValue(r3)
            if (r3 == 0) goto L_0x0102
            int r3 = androidx.appcompat.C0043R.styleable.TextAppearance_android_textColorHint
            android.content.res.ColorStateList r3 = r0.getColorStateList(r3)
            goto L_0x0103
        L_0x0102:
            r3 = 0
        L_0x0103:
            int r13 = androidx.appcompat.C0043R.styleable.TextAppearance_android_textColorLink
            boolean r13 = r0.hasValue(r13)
            if (r13 == 0) goto L_0x0117
            int r13 = androidx.appcompat.C0043R.styleable.TextAppearance_android_textColorLink
            android.content.res.ColorStateList r13 = r0.getColorStateList(r13)
            r17 = r15
            r15 = r13
            r13 = r17
            goto L_0x011c
        L_0x0117:
            r13 = r15
            goto L_0x011b
        L_0x0119:
            r3 = 0
            r13 = 0
        L_0x011b:
            r15 = 0
        L_0x011c:
            int r14 = androidx.appcompat.C0043R.styleable.TextAppearance_textLocale
            boolean r14 = r0.hasValue(r14)
            if (r14 == 0) goto L_0x012b
            int r14 = androidx.appcompat.C0043R.styleable.TextAppearance_textLocale
            java.lang.String r14 = r0.getString(r14)
            goto L_0x012c
        L_0x012b:
            r14 = 0
        L_0x012c:
            int r4 = android.os.Build.VERSION.SDK_INT
            if (r4 < r2) goto L_0x013f
            int r4 = androidx.appcompat.C0043R.styleable.TextAppearance_fontVariationSettings
            boolean r4 = r0.hasValue(r4)
            if (r4 == 0) goto L_0x013f
            int r4 = androidx.appcompat.C0043R.styleable.TextAppearance_fontVariationSettings
            java.lang.String r4 = r0.getString(r4)
            goto L_0x0140
        L_0x013f:
            r4 = 0
        L_0x0140:
            r0.recycle()
            goto L_0x014b
        L_0x0144:
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r13 = 0
            r14 = 0
            r15 = 0
        L_0x014b:
            int[] r0 = androidx.appcompat.C0043R.styleable.TextAppearance
            androidx.appcompat.widget.TintTypedArray r0 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r10, r8, r0, r9, r12)
            if (r1 != 0) goto L_0x0164
            int r2 = androidx.appcompat.C0043R.styleable.TextAppearance_textAllCaps
            boolean r2 = r0.hasValue(r2)
            if (r2 == 0) goto L_0x0164
            int r2 = androidx.appcompat.C0043R.styleable.TextAppearance_textAllCaps
            boolean r6 = r0.getBoolean(r2, r12)
            r16 = 1
            goto L_0x0166
        L_0x0164:
            r16 = r5
        L_0x0166:
            int r2 = android.os.Build.VERSION.SDK_INT
            r5 = 23
            if (r2 >= r5) goto L_0x0196
            int r2 = androidx.appcompat.C0043R.styleable.TextAppearance_android_textColor
            boolean r2 = r0.hasValue(r2)
            if (r2 == 0) goto L_0x017a
            int r2 = androidx.appcompat.C0043R.styleable.TextAppearance_android_textColor
            android.content.res.ColorStateList r13 = r0.getColorStateList(r2)
        L_0x017a:
            int r2 = androidx.appcompat.C0043R.styleable.TextAppearance_android_textColorHint
            boolean r2 = r0.hasValue(r2)
            if (r2 == 0) goto L_0x0188
            int r2 = androidx.appcompat.C0043R.styleable.TextAppearance_android_textColorHint
            android.content.res.ColorStateList r3 = r0.getColorStateList(r2)
        L_0x0188:
            int r2 = androidx.appcompat.C0043R.styleable.TextAppearance_android_textColorLink
            boolean r2 = r0.hasValue(r2)
            if (r2 == 0) goto L_0x0196
            int r2 = androidx.appcompat.C0043R.styleable.TextAppearance_android_textColorLink
            android.content.res.ColorStateList r15 = r0.getColorStateList(r2)
        L_0x0196:
            int r2 = androidx.appcompat.C0043R.styleable.TextAppearance_textLocale
            boolean r2 = r0.hasValue(r2)
            if (r2 == 0) goto L_0x01a4
            int r2 = androidx.appcompat.C0043R.styleable.TextAppearance_textLocale
            java.lang.String r14 = r0.getString(r2)
        L_0x01a4:
            int r2 = android.os.Build.VERSION.SDK_INT
            r5 = 26
            if (r2 < r5) goto L_0x01b8
            int r2 = androidx.appcompat.C0043R.styleable.TextAppearance_fontVariationSettings
            boolean r2 = r0.hasValue(r2)
            if (r2 == 0) goto L_0x01b8
            int r2 = androidx.appcompat.C0043R.styleable.TextAppearance_fontVariationSettings
            java.lang.String r4 = r0.getString(r2)
        L_0x01b8:
            int r2 = android.os.Build.VERSION.SDK_INT
            r5 = 28
            if (r2 < r5) goto L_0x01d5
            int r2 = androidx.appcompat.C0043R.styleable.TextAppearance_android_textSize
            boolean r2 = r0.hasValue(r2)
            if (r2 == 0) goto L_0x01d5
            int r2 = androidx.appcompat.C0043R.styleable.TextAppearance_android_textSize
            r5 = -1
            int r2 = r0.getDimensionPixelSize(r2, r5)
            if (r2 != 0) goto L_0x01d5
            android.widget.TextView r2 = r7.mView
            r5 = 0
            r2.setTextSize(r12, r5)
        L_0x01d5:
            r7.updateTypefaceAndStyle(r10, r0)
            r0.recycle()
            if (r13 == 0) goto L_0x01e2
            android.widget.TextView r0 = r7.mView
            r0.setTextColor(r13)
        L_0x01e2:
            if (r3 == 0) goto L_0x01e9
            android.widget.TextView r0 = r7.mView
            r0.setHintTextColor(r3)
        L_0x01e9:
            if (r15 == 0) goto L_0x01f0
            android.widget.TextView r0 = r7.mView
            r0.setLinkTextColor(r15)
        L_0x01f0:
            if (r1 != 0) goto L_0x01f7
            if (r16 == 0) goto L_0x01f7
            r7.setAllCaps(r6)
        L_0x01f7:
            android.graphics.Typeface r0 = r7.mFontTypeface
            if (r0 == 0) goto L_0x020d
            int r1 = r7.mFontWeight
            r2 = -1
            if (r1 != r2) goto L_0x0208
            android.widget.TextView r1 = r7.mView
            int r2 = r7.mStyle
            r1.setTypeface(r0, r2)
            goto L_0x020d
        L_0x0208:
            android.widget.TextView r1 = r7.mView
            r1.setTypeface(r0)
        L_0x020d:
            if (r4 == 0) goto L_0x0214
            android.widget.TextView r0 = r7.mView
            r0.setFontVariationSettings(r4)
        L_0x0214:
            if (r14 == 0) goto L_0x023f
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 24
            if (r0 < r1) goto L_0x0226
            android.widget.TextView r0 = r7.mView
            android.os.LocaleList r1 = android.os.LocaleList.forLanguageTags(r14)
            r0.setTextLocales(r1)
            goto L_0x023f
        L_0x0226:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            if (r0 < r1) goto L_0x023f
            r0 = 44
            int r0 = r14.indexOf(r0)
            java.lang.String r0 = r14.substring(r12, r0)
            android.widget.TextView r1 = r7.mView
            java.util.Locale r0 = java.util.Locale.forLanguageTag(r0)
            r1.setTextLocale(r0)
        L_0x023f:
            androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper r0 = r7.mAutoSizeTextHelper
            r0.loadFromAttributes(r8, r9)
            boolean r0 = androidx.core.widget.AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE
            if (r0 == 0) goto L_0x0283
            androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper r0 = r7.mAutoSizeTextHelper
            int r0 = r0.getAutoSizeTextType()
            if (r0 == 0) goto L_0x0283
            androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper r0 = r7.mAutoSizeTextHelper
            int[] r0 = r0.getAutoSizeTextAvailableSizes()
            int r1 = r0.length
            if (r1 <= 0) goto L_0x0283
            android.widget.TextView r1 = r7.mView
            int r1 = r1.getAutoSizeStepGranularity()
            float r1 = (float) r1
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 == 0) goto L_0x027e
            android.widget.TextView r0 = r7.mView
            androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper r1 = r7.mAutoSizeTextHelper
            int r1 = r1.getAutoSizeMinTextSize()
            androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper r2 = r7.mAutoSizeTextHelper
            int r2 = r2.getAutoSizeMaxTextSize()
            androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper r3 = r7.mAutoSizeTextHelper
            int r3 = r3.getAutoSizeStepGranularity()
            r0.setAutoSizeTextTypeUniformWithConfiguration(r1, r2, r3, r12)
            goto L_0x0283
        L_0x027e:
            android.widget.TextView r1 = r7.mView
            r1.setAutoSizeTextTypeUniformWithPresetSizes(r0, r12)
        L_0x0283:
            int[] r0 = androidx.appcompat.C0043R.styleable.AppCompatTextView
            androidx.appcompat.widget.TintTypedArray r8 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes((android.content.Context) r10, (android.util.AttributeSet) r8, (int[]) r0)
            int r0 = androidx.appcompat.C0043R.styleable.AppCompatTextView_drawableLeftCompat
            r1 = -1
            int r0 = r8.getResourceId(r0, r1)
            if (r0 == r1) goto L_0x0298
            android.graphics.drawable.Drawable r0 = r11.getDrawable(r10, r0)
            r2 = r0
            goto L_0x0299
        L_0x0298:
            r2 = 0
        L_0x0299:
            int r0 = androidx.appcompat.C0043R.styleable.AppCompatTextView_drawableTopCompat
            int r0 = r8.getResourceId(r0, r1)
            if (r0 == r1) goto L_0x02a7
            android.graphics.drawable.Drawable r0 = r11.getDrawable(r10, r0)
            r3 = r0
            goto L_0x02a8
        L_0x02a7:
            r3 = 0
        L_0x02a8:
            int r0 = androidx.appcompat.C0043R.styleable.AppCompatTextView_drawableRightCompat
            int r0 = r8.getResourceId(r0, r1)
            if (r0 == r1) goto L_0x02b6
            android.graphics.drawable.Drawable r0 = r11.getDrawable(r10, r0)
            r4 = r0
            goto L_0x02b7
        L_0x02b6:
            r4 = 0
        L_0x02b7:
            int r0 = androidx.appcompat.C0043R.styleable.AppCompatTextView_drawableBottomCompat
            int r0 = r8.getResourceId(r0, r1)
            if (r0 == r1) goto L_0x02c5
            android.graphics.drawable.Drawable r0 = r11.getDrawable(r10, r0)
            r5 = r0
            goto L_0x02c6
        L_0x02c5:
            r5 = 0
        L_0x02c6:
            int r0 = androidx.appcompat.C0043R.styleable.AppCompatTextView_drawableStartCompat
            int r0 = r8.getResourceId(r0, r1)
            if (r0 == r1) goto L_0x02d4
            android.graphics.drawable.Drawable r0 = r11.getDrawable(r10, r0)
            r6 = r0
            goto L_0x02d5
        L_0x02d4:
            r6 = 0
        L_0x02d5:
            int r0 = androidx.appcompat.C0043R.styleable.AppCompatTextView_drawableEndCompat
            int r0 = r8.getResourceId(r0, r1)
            if (r0 == r1) goto L_0x02e3
            android.graphics.drawable.Drawable r0 = r11.getDrawable(r10, r0)
            r9 = r0
            goto L_0x02e4
        L_0x02e3:
            r9 = 0
        L_0x02e4:
            r0 = r18
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r9
            r0.setCompoundDrawables(r1, r2, r3, r4, r5, r6)
            int r0 = androidx.appcompat.C0043R.styleable.AppCompatTextView_drawableTint
            boolean r0 = r8.hasValue(r0)
            if (r0 == 0) goto L_0x0302
            int r0 = androidx.appcompat.C0043R.styleable.AppCompatTextView_drawableTint
            android.content.res.ColorStateList r0 = r8.getColorStateList(r0)
            android.widget.TextView r1 = r7.mView
            androidx.core.widget.TextViewCompat.setCompoundDrawableTintList(r1, r0)
        L_0x0302:
            int r0 = androidx.appcompat.C0043R.styleable.AppCompatTextView_drawableTintMode
            boolean r0 = r8.hasValue(r0)
            if (r0 == 0) goto L_0x031c
            int r0 = androidx.appcompat.C0043R.styleable.AppCompatTextView_drawableTintMode
            r1 = -1
            int r0 = r8.getInt(r0, r1)
            r2 = 0
            android.graphics.PorterDuff$Mode r0 = androidx.appcompat.widget.DrawableUtils.parseTintMode(r0, r2)
            android.widget.TextView r2 = r7.mView
            androidx.core.widget.TextViewCompat.setCompoundDrawableTintMode(r2, r0)
            goto L_0x031d
        L_0x031c:
            r1 = -1
        L_0x031d:
            int r0 = androidx.appcompat.C0043R.styleable.AppCompatTextView_firstBaselineToTopHeight
            int r0 = r8.getDimensionPixelSize(r0, r1)
            int r2 = androidx.appcompat.C0043R.styleable.AppCompatTextView_lastBaselineToBottomHeight
            int r2 = r8.getDimensionPixelSize(r2, r1)
            int r3 = androidx.appcompat.C0043R.styleable.AppCompatTextView_lineHeight
            int r3 = r8.getDimensionPixelSize(r3, r1)
            r8.recycle()
            if (r0 == r1) goto L_0x0339
            android.widget.TextView r4 = r7.mView
            androidx.core.widget.TextViewCompat.setFirstBaselineToTopHeight(r4, r0)
        L_0x0339:
            if (r2 == r1) goto L_0x0340
            android.widget.TextView r0 = r7.mView
            androidx.core.widget.TextViewCompat.setLastBaselineToBottomHeight(r0, r2)
        L_0x0340:
            if (r3 == r1) goto L_0x0347
            android.widget.TextView r0 = r7.mView
            androidx.core.widget.TextViewCompat.setLineHeight(r0, r3)
        L_0x0347:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatTextHelper.loadFromAttributes(android.util.AttributeSet, int):void");
    }

    private void updateTypefaceAndStyle(Context context, TintTypedArray tintTypedArray) {
        int i;
        String string;
        this.mStyle = tintTypedArray.getInt(C0043R.styleable.TextAppearance_android_textStyle, this.mStyle);
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 28) {
            this.mFontWeight = tintTypedArray.getInt(C0043R.styleable.TextAppearance_android_textFontWeight, -1);
            if (this.mFontWeight != -1) {
                this.mStyle = (this.mStyle & 2) | 0;
            }
        }
        if (tintTypedArray.hasValue(C0043R.styleable.TextAppearance_android_fontFamily) || tintTypedArray.hasValue(C0043R.styleable.TextAppearance_fontFamily)) {
            this.mFontTypeface = null;
            if (tintTypedArray.hasValue(C0043R.styleable.TextAppearance_fontFamily)) {
                i = C0043R.styleable.TextAppearance_fontFamily;
            } else {
                i = C0043R.styleable.TextAppearance_android_fontFamily;
            }
            final int i2 = this.mFontWeight;
            final int i3 = this.mStyle;
            if (!context.isRestricted()) {
                final WeakReference weakReference = new WeakReference(this.mView);
                try {
                    Typeface font = tintTypedArray.getFont(i, this.mStyle, new ResourcesCompat.FontCallback() {
                        public void onFontRetrievalFailed(int i) {
                        }

                        public void onFontRetrieved(@NonNull Typeface typeface) {
                            int i;
                            if (Build.VERSION.SDK_INT >= 28 && (i = i2) != -1) {
                                typeface = Typeface.create(typeface, i, (i3 & 2) != 0);
                            }
                            AppCompatTextHelper.this.onAsyncTypefaceReceived(weakReference, typeface);
                        }
                    });
                    if (font != null) {
                        if (Build.VERSION.SDK_INT < 28 || this.mFontWeight == -1) {
                            this.mFontTypeface = font;
                        } else {
                            this.mFontTypeface = Typeface.create(Typeface.create(font, 0), this.mFontWeight, (this.mStyle & 2) != 0);
                        }
                    }
                    this.mAsyncFontPending = this.mFontTypeface == null;
                } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
                }
            }
            if (this.mFontTypeface == null && (string = tintTypedArray.getString(i)) != null) {
                if (Build.VERSION.SDK_INT < 28 || this.mFontWeight == -1) {
                    this.mFontTypeface = Typeface.create(string, this.mStyle);
                    return;
                }
                Typeface create = Typeface.create(string, 0);
                int i4 = this.mFontWeight;
                if ((this.mStyle & 2) != 0) {
                    z = true;
                }
                this.mFontTypeface = Typeface.create(create, i4, z);
            }
        } else if (tintTypedArray.hasValue(C0043R.styleable.TextAppearance_android_typeface)) {
            this.mAsyncFontPending = false;
            int i5 = tintTypedArray.getInt(C0043R.styleable.TextAppearance_android_typeface, 1);
            if (i5 == 1) {
                this.mFontTypeface = Typeface.SANS_SERIF;
            } else if (i5 == 2) {
                this.mFontTypeface = Typeface.SERIF;
            } else if (i5 == 3) {
                this.mFontTypeface = Typeface.MONOSPACE;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onAsyncTypefaceReceived(WeakReference<TextView> weakReference, Typeface typeface) {
        if (this.mAsyncFontPending) {
            this.mFontTypeface = typeface;
            TextView textView = (TextView) weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, this.mStyle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onSetTextAppearance(Context context, int i) {
        String string;
        ColorStateList colorStateList;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, i, C0043R.styleable.TextAppearance);
        if (obtainStyledAttributes.hasValue(C0043R.styleable.TextAppearance_textAllCaps)) {
            setAllCaps(obtainStyledAttributes.getBoolean(C0043R.styleable.TextAppearance_textAllCaps, false));
        }
        if (Build.VERSION.SDK_INT < 23 && obtainStyledAttributes.hasValue(C0043R.styleable.TextAppearance_android_textColor) && (colorStateList = obtainStyledAttributes.getColorStateList(C0043R.styleable.TextAppearance_android_textColor)) != null) {
            this.mView.setTextColor(colorStateList);
        }
        if (obtainStyledAttributes.hasValue(C0043R.styleable.TextAppearance_android_textSize) && obtainStyledAttributes.getDimensionPixelSize(C0043R.styleable.TextAppearance_android_textSize, -1) == 0) {
            this.mView.setTextSize(0, 0.0f);
        }
        updateTypefaceAndStyle(context, obtainStyledAttributes);
        if (Build.VERSION.SDK_INT >= 26 && obtainStyledAttributes.hasValue(C0043R.styleable.TextAppearance_fontVariationSettings) && (string = obtainStyledAttributes.getString(C0043R.styleable.TextAppearance_fontVariationSettings)) != null) {
            this.mView.setFontVariationSettings(string);
        }
        obtainStyledAttributes.recycle();
        Typeface typeface = this.mFontTypeface;
        if (typeface != null) {
            this.mView.setTypeface(typeface, this.mStyle);
        }
    }

    /* access modifiers changed from: package-private */
    public void setAllCaps(boolean z) {
        this.mView.setAllCaps(z);
    }

    /* access modifiers changed from: package-private */
    public void onSetCompoundDrawables() {
        applyCompoundDrawablesTints();
    }

    /* access modifiers changed from: package-private */
    public void applyCompoundDrawablesTints() {
        if (!(this.mDrawableLeftTint == null && this.mDrawableTopTint == null && this.mDrawableRightTint == null && this.mDrawableBottomTint == null)) {
            Drawable[] compoundDrawables = this.mView.getCompoundDrawables();
            applyCompoundDrawableTint(compoundDrawables[0], this.mDrawableLeftTint);
            applyCompoundDrawableTint(compoundDrawables[1], this.mDrawableTopTint);
            applyCompoundDrawableTint(compoundDrawables[2], this.mDrawableRightTint);
            applyCompoundDrawableTint(compoundDrawables[3], this.mDrawableBottomTint);
        }
        if (Build.VERSION.SDK_INT < 17) {
            return;
        }
        if (this.mDrawableStartTint != null || this.mDrawableEndTint != null) {
            Drawable[] compoundDrawablesRelative = this.mView.getCompoundDrawablesRelative();
            applyCompoundDrawableTint(compoundDrawablesRelative[0], this.mDrawableStartTint);
            applyCompoundDrawableTint(compoundDrawablesRelative[2], this.mDrawableEndTint);
        }
    }

    private void applyCompoundDrawableTint(Drawable drawable, TintInfo tintInfo) {
        if (drawable != null && tintInfo != null) {
            AppCompatDrawableManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState());
        }
    }

    private static TintInfo createTintInfo(Context context, AppCompatDrawableManager appCompatDrawableManager, int i) {
        ColorStateList tintList = appCompatDrawableManager.getTintList(context, i);
        if (tintList == null) {
            return null;
        }
        TintInfo tintInfo = new TintInfo();
        tintInfo.mHasTintList = true;
        tintInfo.mTintList = tintList;
        return tintInfo;
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE) {
            autoSizeText();
        }
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void setTextSize(int i, float f) {
        if (!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE && !isAutoSizeEnabled()) {
            setTextSizeInternal(i, f);
        }
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void autoSizeText() {
        this.mAutoSizeTextHelper.autoSizeText();
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public boolean isAutoSizeEnabled() {
        return this.mAutoSizeTextHelper.isAutoSizeEnabled();
    }

    private void setTextSizeInternal(int i, float f) {
        this.mAutoSizeTextHelper.setTextSizeInternal(i, f);
    }

    /* access modifiers changed from: package-private */
    public void setAutoSizeTextTypeWithDefaults(int i) {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeWithDefaults(i);
    }

    /* access modifiers changed from: package-private */
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
    }

    /* access modifiers changed from: package-private */
    public void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] iArr, int i) throws IllegalArgumentException {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
    }

    /* access modifiers changed from: package-private */
    public int getAutoSizeTextType() {
        return this.mAutoSizeTextHelper.getAutoSizeTextType();
    }

    /* access modifiers changed from: package-private */
    public int getAutoSizeStepGranularity() {
        return this.mAutoSizeTextHelper.getAutoSizeStepGranularity();
    }

    /* access modifiers changed from: package-private */
    public int getAutoSizeMinTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMinTextSize();
    }

    /* access modifiers changed from: package-private */
    public int getAutoSizeMaxTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMaxTextSize();
    }

    /* access modifiers changed from: package-private */
    public int[] getAutoSizeTextAvailableSizes() {
        return this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public ColorStateList getCompoundDrawableTintList() {
        TintInfo tintInfo = this.mDrawableTint;
        if (tintInfo != null) {
            return tintInfo.mTintList;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void setCompoundDrawableTintList(@Nullable ColorStateList colorStateList) {
        if (this.mDrawableTint == null) {
            this.mDrawableTint = new TintInfo();
        }
        TintInfo tintInfo = this.mDrawableTint;
        tintInfo.mTintList = colorStateList;
        tintInfo.mHasTintList = colorStateList != null;
        setCompoundTints();
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public PorterDuff.Mode getCompoundDrawableTintMode() {
        TintInfo tintInfo = this.mDrawableTint;
        if (tintInfo != null) {
            return tintInfo.mTintMode;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void setCompoundDrawableTintMode(@Nullable PorterDuff.Mode mode) {
        if (this.mDrawableTint == null) {
            this.mDrawableTint = new TintInfo();
        }
        TintInfo tintInfo = this.mDrawableTint;
        tintInfo.mTintMode = mode;
        tintInfo.mHasTintMode = mode != null;
        setCompoundTints();
    }

    private void setCompoundTints() {
        TintInfo tintInfo = this.mDrawableTint;
        this.mDrawableLeftTint = tintInfo;
        this.mDrawableTopTint = tintInfo;
        this.mDrawableRightTint = tintInfo;
        this.mDrawableBottomTint = tintInfo;
        this.mDrawableStartTint = tintInfo;
        this.mDrawableEndTint = tintInfo;
    }

    private void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        if (Build.VERSION.SDK_INT >= 17 && (drawable5 != null || drawable6 != null)) {
            Drawable[] compoundDrawablesRelative = this.mView.getCompoundDrawablesRelative();
            TextView textView = this.mView;
            if (drawable5 == null) {
                drawable5 = compoundDrawablesRelative[0];
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative[1];
            }
            if (drawable6 == null) {
                drawable6 = compoundDrawablesRelative[2];
            }
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative[3];
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable5, drawable2, drawable6, drawable4);
        } else if (drawable != null || drawable2 != null || drawable3 != null || drawable4 != null) {
            if (Build.VERSION.SDK_INT >= 17) {
                Drawable[] compoundDrawablesRelative2 = this.mView.getCompoundDrawablesRelative();
                if (!(compoundDrawablesRelative2[0] == null && compoundDrawablesRelative2[2] == null)) {
                    TextView textView2 = this.mView;
                    Drawable drawable7 = compoundDrawablesRelative2[0];
                    if (drawable2 == null) {
                        drawable2 = compoundDrawablesRelative2[1];
                    }
                    Drawable drawable8 = compoundDrawablesRelative2[2];
                    if (drawable4 == null) {
                        drawable4 = compoundDrawablesRelative2[3];
                    }
                    textView2.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable7, drawable2, drawable8, drawable4);
                    return;
                }
            }
            Drawable[] compoundDrawables = this.mView.getCompoundDrawables();
            TextView textView3 = this.mView;
            if (drawable == null) {
                drawable = compoundDrawables[0];
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawables[1];
            }
            if (drawable3 == null) {
                drawable3 = compoundDrawables[2];
            }
            if (drawable4 == null) {
                drawable4 = compoundDrawables[3];
            }
            textView3.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        }
    }
}
