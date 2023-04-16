package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.LineHeightSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ReplacementSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

public final class SpanUtils {
    public static final int ALIGN_BASELINE = 1;
    public static final int ALIGN_BOTTOM = 0;
    public static final int ALIGN_CENTER = 2;
    public static final int ALIGN_TOP = 3;
    private static final int COLOR_DEFAULT = -16777217;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private int alignImage;
    private int alignLine;
    private Layout.Alignment alignment;
    private int backgroundColor;
    private float blurRadius;
    private int bulletColor;
    private int bulletGapWidth;
    private int bulletRadius;
    private ClickableSpan clickSpan;
    private int first;
    private int flag;
    private String fontFamily;
    private int fontSize;
    private boolean fontSizeIsDp;
    private int foregroundColor;
    private Bitmap imageBitmap;
    private Drawable imageDrawable;
    private int imageResourceId;
    private Uri imageUri;
    private boolean isBold;
    private boolean isBoldItalic;
    private boolean isCreated;
    private boolean isItalic;
    private boolean isStrikethrough;
    private boolean isSubscript;
    private boolean isSuperscript;
    private boolean isUnderline;
    private int lineHeight;
    private SerializableSpannableStringBuilder mBuilder;
    private CharSequence mText;
    private TextView mTextView;
    private int mType;
    private final int mTypeCharSequence;
    private final int mTypeImage;
    private final int mTypeSpace;
    private float proportion;
    private int quoteColor;
    private int quoteGapWidth;
    private int rest;
    private Shader shader;
    private int shadowColor;
    private float shadowDx;
    private float shadowDy;
    private float shadowRadius;
    private int spaceColor;
    private int spaceSize;
    private Object[] spans;
    private int stripeWidth;
    private BlurMaskFilter.Blur style;
    private Typeface typeface;
    private String url;
    private int verticalAlign;
    private float xProportion;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Align {
    }

    public static SpanUtils with(TextView textView) {
        return new SpanUtils(textView);
    }

    private SpanUtils(TextView textView) {
        this();
        this.mTextView = textView;
    }

    public SpanUtils() {
        this.mTypeCharSequence = 0;
        this.mTypeImage = 1;
        this.mTypeSpace = 2;
        this.mBuilder = new SerializableSpannableStringBuilder();
        this.mText = "";
        this.mType = -1;
        setDefault();
    }

    private void setDefault() {
        this.flag = 33;
        this.foregroundColor = COLOR_DEFAULT;
        this.backgroundColor = COLOR_DEFAULT;
        this.lineHeight = -1;
        this.quoteColor = COLOR_DEFAULT;
        this.first = -1;
        this.bulletColor = COLOR_DEFAULT;
        this.fontSize = -1;
        this.proportion = -1.0f;
        this.xProportion = -1.0f;
        this.isStrikethrough = false;
        this.isUnderline = false;
        this.isSuperscript = false;
        this.isSubscript = false;
        this.isBold = false;
        this.isItalic = false;
        this.isBoldItalic = false;
        this.fontFamily = null;
        this.typeface = null;
        this.alignment = null;
        this.verticalAlign = -1;
        this.clickSpan = null;
        this.url = null;
        this.blurRadius = -1.0f;
        this.shader = null;
        this.shadowRadius = -1.0f;
        this.spans = null;
        this.imageBitmap = null;
        this.imageDrawable = null;
        this.imageUri = null;
        this.imageResourceId = -1;
        this.spaceSize = -1;
    }

    public SpanUtils setFlag(int i) {
        this.flag = i;
        return this;
    }

    public SpanUtils setForegroundColor(@ColorInt int i) {
        this.foregroundColor = i;
        return this;
    }

    public SpanUtils setBackgroundColor(@ColorInt int i) {
        this.backgroundColor = i;
        return this;
    }

    public SpanUtils setLineHeight(@IntRange(from = 0) int i) {
        return setLineHeight(i, 2);
    }

    public SpanUtils setLineHeight(@IntRange(from = 0) int i, int i2) {
        this.lineHeight = i;
        this.alignLine = i2;
        return this;
    }

    public SpanUtils setQuoteColor(@ColorInt int i) {
        return setQuoteColor(i, 2, 2);
    }

    public SpanUtils setQuoteColor(@ColorInt int i, @IntRange(from = 1) int i2, @IntRange(from = 0) int i3) {
        this.quoteColor = i;
        this.stripeWidth = i2;
        this.quoteGapWidth = i3;
        return this;
    }

    public SpanUtils setLeadingMargin(@IntRange(from = 0) int i, @IntRange(from = 0) int i2) {
        this.first = i;
        this.rest = i2;
        return this;
    }

    public SpanUtils setBullet(@IntRange(from = 0) int i) {
        return setBullet(0, 3, i);
    }

    public SpanUtils setBullet(@ColorInt int i, @IntRange(from = 0) int i2, @IntRange(from = 0) int i3) {
        this.bulletColor = i;
        this.bulletRadius = i2;
        this.bulletGapWidth = i3;
        return this;
    }

    public SpanUtils setFontSize(@IntRange(from = 0) int i) {
        return setFontSize(i, false);
    }

    public SpanUtils setFontSize(@IntRange(from = 0) int i, boolean z) {
        this.fontSize = i;
        this.fontSizeIsDp = z;
        return this;
    }

    public SpanUtils setFontProportion(float f) {
        this.proportion = f;
        return this;
    }

    public SpanUtils setFontXProportion(float f) {
        this.xProportion = f;
        return this;
    }

    public SpanUtils setStrikethrough() {
        this.isStrikethrough = true;
        return this;
    }

    public SpanUtils setUnderline() {
        this.isUnderline = true;
        return this;
    }

    public SpanUtils setSuperscript() {
        this.isSuperscript = true;
        return this;
    }

    public SpanUtils setSubscript() {
        this.isSubscript = true;
        return this;
    }

    public SpanUtils setBold() {
        this.isBold = true;
        return this;
    }

    public SpanUtils setItalic() {
        this.isItalic = true;
        return this;
    }

    public SpanUtils setBoldItalic() {
        this.isBoldItalic = true;
        return this;
    }

    public SpanUtils setFontFamily(@NonNull String str) {
        if (str != null) {
            this.fontFamily = str;
            return this;
        }
        throw new NullPointerException("Argument 'fontFamily' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public SpanUtils setTypeface(@NonNull Typeface typeface2) {
        if (typeface2 != null) {
            this.typeface = typeface2;
            return this;
        }
        throw new NullPointerException("Argument 'typeface' of type Typeface (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public SpanUtils setHorizontalAlign(@NonNull Layout.Alignment alignment2) {
        if (alignment2 != null) {
            this.alignment = alignment2;
            return this;
        }
        throw new NullPointerException("Argument 'alignment' of type Alignment (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public SpanUtils setVerticalAlign(int i) {
        this.verticalAlign = i;
        return this;
    }

    public SpanUtils setClickSpan(@NonNull ClickableSpan clickableSpan) {
        if (clickableSpan != null) {
            TextView textView = this.mTextView;
            if (textView != null && textView.getMovementMethod() == null) {
                this.mTextView.setMovementMethod(LinkMovementMethod.getInstance());
            }
            this.clickSpan = clickableSpan;
            return this;
        }
        throw new NullPointerException("Argument 'clickSpan' of type ClickableSpan (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public SpanUtils setClickSpan(@ColorInt final int i, final boolean z, final View.OnClickListener onClickListener) {
        TextView textView = this.mTextView;
        if (textView != null && textView.getMovementMethod() == null) {
            this.mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        this.clickSpan = new ClickableSpan() {
            public void updateDrawState(@NonNull TextPaint textPaint) {
                if (textPaint != null) {
                    textPaint.setColor(i);
                    textPaint.setUnderlineText(z);
                    return;
                }
                throw new NullPointerException("Argument 'paint' of type TextPaint (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            }

            public void onClick(@NonNull View view) {
                if (view != null) {
                    View.OnClickListener onClickListener = onClickListener;
                    if (onClickListener != null) {
                        onClickListener.onClick(view);
                        return;
                    }
                    return;
                }
                throw new NullPointerException("Argument 'widget' of type View (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            }
        };
        return this;
    }

    public SpanUtils setUrl(@NonNull String str) {
        if (str != null) {
            TextView textView = this.mTextView;
            if (textView != null && textView.getMovementMethod() == null) {
                this.mTextView.setMovementMethod(LinkMovementMethod.getInstance());
            }
            this.url = str;
            return this;
        }
        throw new NullPointerException("Argument 'url' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public SpanUtils setBlur(@FloatRange(from = 0.0d, fromInclusive = false) float f, BlurMaskFilter.Blur blur) {
        this.blurRadius = f;
        this.style = blur;
        return this;
    }

    public SpanUtils setShader(@NonNull Shader shader2) {
        if (shader2 != null) {
            this.shader = shader2;
            return this;
        }
        throw new NullPointerException("Argument 'shader' of type Shader (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public SpanUtils setShadow(@FloatRange(from = 0.0d, fromInclusive = false) float f, float f2, float f3, int i) {
        this.shadowRadius = f;
        this.shadowDx = f2;
        this.shadowDy = f3;
        this.shadowColor = i;
        return this;
    }

    public SpanUtils setSpans(@NonNull Object... objArr) {
        if (objArr != null) {
            if (objArr.length > 0) {
                this.spans = objArr;
            }
            return this;
        }
        throw new NullPointerException("Argument 'spans' of type Object[] (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public SpanUtils append(@NonNull CharSequence charSequence) {
        if (charSequence != null) {
            apply(0);
            this.mText = charSequence;
            return this;
        }
        throw new NullPointerException("Argument 'text' of type CharSequence (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public SpanUtils appendLine() {
        apply(0);
        this.mText = LINE_SEPARATOR;
        return this;
    }

    public SpanUtils appendLine(@NonNull CharSequence charSequence) {
        if (charSequence != null) {
            apply(0);
            this.mText = charSequence + LINE_SEPARATOR;
            return this;
        }
        throw new NullPointerException("Argument 'text' of type CharSequence (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public SpanUtils appendImage(@NonNull Bitmap bitmap) {
        if (bitmap != null) {
            return appendImage(bitmap, 0);
        }
        throw new NullPointerException("Argument 'bitmap' of type Bitmap (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public SpanUtils appendImage(@NonNull Bitmap bitmap, int i) {
        if (bitmap != null) {
            apply(1);
            this.imageBitmap = bitmap;
            this.alignImage = i;
            return this;
        }
        throw new NullPointerException("Argument 'bitmap' of type Bitmap (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public SpanUtils appendImage(@NonNull Drawable drawable) {
        if (drawable != null) {
            return appendImage(drawable, 0);
        }
        throw new NullPointerException("Argument 'drawable' of type Drawable (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public SpanUtils appendImage(@NonNull Drawable drawable, int i) {
        if (drawable != null) {
            apply(1);
            this.imageDrawable = drawable;
            this.alignImage = i;
            return this;
        }
        throw new NullPointerException("Argument 'drawable' of type Drawable (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public SpanUtils appendImage(@NonNull Uri uri) {
        if (uri != null) {
            return appendImage(uri, 0);
        }
        throw new NullPointerException("Argument 'uri' of type Uri (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public SpanUtils appendImage(@NonNull Uri uri, int i) {
        if (uri != null) {
            apply(1);
            this.imageUri = uri;
            this.alignImage = i;
            return this;
        }
        throw new NullPointerException("Argument 'uri' of type Uri (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public SpanUtils appendImage(@DrawableRes int i) {
        return appendImage(i, 0);
    }

    public SpanUtils appendImage(@DrawableRes int i, int i2) {
        apply(1);
        this.imageResourceId = i;
        this.alignImage = i2;
        return this;
    }

    public SpanUtils appendSpace(@IntRange(from = 0) int i) {
        return appendSpace(i, 0);
    }

    public SpanUtils appendSpace(@IntRange(from = 0) int i, @ColorInt int i2) {
        apply(2);
        this.spaceSize = i;
        this.spaceColor = i2;
        return this;
    }

    private void apply(int i) {
        applyLast();
        this.mType = i;
    }

    public SpannableStringBuilder get() {
        return this.mBuilder;
    }

    public SpannableStringBuilder create() {
        applyLast();
        TextView textView = this.mTextView;
        if (textView != null) {
            textView.setText(this.mBuilder);
        }
        this.isCreated = true;
        return this.mBuilder;
    }

    private void applyLast() {
        if (!this.isCreated) {
            int i = this.mType;
            if (i == 0) {
                updateCharCharSequence();
            } else if (i == 1) {
                updateImage();
            } else if (i == 2) {
                updateSpace();
            }
            setDefault();
        }
    }

    private void updateCharCharSequence() {
        if (this.mText.length() != 0) {
            int length = this.mBuilder.length();
            if (length == 0 && this.lineHeight != -1) {
                this.mBuilder.append(Character.toString(2)).append("\n").setSpan(new AbsoluteSizeSpan(0), 0, 2, 33);
                length = 2;
            }
            this.mBuilder.append(this.mText);
            int length2 = this.mBuilder.length();
            int i = this.verticalAlign;
            if (i != -1) {
                this.mBuilder.setSpan(new VerticalAlignSpan(i), length, length2, this.flag);
            }
            int i2 = this.foregroundColor;
            if (i2 != COLOR_DEFAULT) {
                this.mBuilder.setSpan(new ForegroundColorSpan(i2), length, length2, this.flag);
            }
            int i3 = this.backgroundColor;
            if (i3 != COLOR_DEFAULT) {
                this.mBuilder.setSpan(new BackgroundColorSpan(i3), length, length2, this.flag);
            }
            int i4 = this.first;
            if (i4 != -1) {
                this.mBuilder.setSpan(new LeadingMarginSpan.Standard(i4, this.rest), length, length2, this.flag);
            }
            int i5 = this.quoteColor;
            if (i5 != COLOR_DEFAULT) {
                this.mBuilder.setSpan(new CustomQuoteSpan(i5, this.stripeWidth, this.quoteGapWidth), length, length2, this.flag);
            }
            int i6 = this.bulletColor;
            if (i6 != COLOR_DEFAULT) {
                this.mBuilder.setSpan(new CustomBulletSpan(i6, this.bulletRadius, this.bulletGapWidth), length, length2, this.flag);
            }
            int i7 = this.fontSize;
            if (i7 != -1) {
                this.mBuilder.setSpan(new AbsoluteSizeSpan(i7, this.fontSizeIsDp), length, length2, this.flag);
            }
            float f = this.proportion;
            if (f != -1.0f) {
                this.mBuilder.setSpan(new RelativeSizeSpan(f), length, length2, this.flag);
            }
            float f2 = this.xProportion;
            if (f2 != -1.0f) {
                this.mBuilder.setSpan(new ScaleXSpan(f2), length, length2, this.flag);
            }
            int i8 = this.lineHeight;
            if (i8 != -1) {
                this.mBuilder.setSpan(new CustomLineHeightSpan(i8, this.alignLine), length, length2, this.flag);
            }
            if (this.isStrikethrough) {
                this.mBuilder.setSpan(new StrikethroughSpan(), length, length2, this.flag);
            }
            if (this.isUnderline) {
                this.mBuilder.setSpan(new UnderlineSpan(), length, length2, this.flag);
            }
            if (this.isSuperscript) {
                this.mBuilder.setSpan(new SuperscriptSpan(), length, length2, this.flag);
            }
            if (this.isSubscript) {
                this.mBuilder.setSpan(new SubscriptSpan(), length, length2, this.flag);
            }
            if (this.isBold) {
                this.mBuilder.setSpan(new StyleSpan(1), length, length2, this.flag);
            }
            if (this.isItalic) {
                this.mBuilder.setSpan(new StyleSpan(2), length, length2, this.flag);
            }
            if (this.isBoldItalic) {
                this.mBuilder.setSpan(new StyleSpan(3), length, length2, this.flag);
            }
            String str = this.fontFamily;
            if (str != null) {
                this.mBuilder.setSpan(new TypefaceSpan(str), length, length2, this.flag);
            }
            Typeface typeface2 = this.typeface;
            if (typeface2 != null) {
                this.mBuilder.setSpan(new CustomTypefaceSpan(typeface2), length, length2, this.flag);
            }
            Layout.Alignment alignment2 = this.alignment;
            if (alignment2 != null) {
                this.mBuilder.setSpan(new AlignmentSpan.Standard(alignment2), length, length2, this.flag);
            }
            ClickableSpan clickableSpan = this.clickSpan;
            if (clickableSpan != null) {
                this.mBuilder.setSpan(clickableSpan, length, length2, this.flag);
            }
            String str2 = this.url;
            if (str2 != null) {
                this.mBuilder.setSpan(new URLSpan(str2), length, length2, this.flag);
            }
            float f3 = this.blurRadius;
            if (f3 != -1.0f) {
                this.mBuilder.setSpan(new MaskFilterSpan(new BlurMaskFilter(f3, this.style)), length, length2, this.flag);
            }
            Shader shader2 = this.shader;
            if (shader2 != null) {
                this.mBuilder.setSpan(new ShaderSpan(shader2), length, length2, this.flag);
            }
            float f4 = this.shadowRadius;
            if (f4 != -1.0f) {
                this.mBuilder.setSpan(new ShadowSpan(f4, this.shadowDx, this.shadowDy, this.shadowColor), length, length2, this.flag);
            }
            Object[] objArr = this.spans;
            if (objArr != null) {
                for (Object span : objArr) {
                    this.mBuilder.setSpan(span, length, length2, this.flag);
                }
            }
        }
    }

    private void updateImage() {
        int length = this.mBuilder.length();
        this.mText = "<img>";
        updateCharCharSequence();
        int length2 = this.mBuilder.length();
        Bitmap bitmap = this.imageBitmap;
        if (bitmap != null) {
            this.mBuilder.setSpan(new CustomImageSpan(bitmap, this.alignImage), length, length2, this.flag);
            return;
        }
        Drawable drawable = this.imageDrawable;
        if (drawable != null) {
            this.mBuilder.setSpan(new CustomImageSpan(drawable, this.alignImage), length, length2, this.flag);
            return;
        }
        Uri uri = this.imageUri;
        if (uri != null) {
            this.mBuilder.setSpan(new CustomImageSpan(uri, this.alignImage), length, length2, this.flag);
            return;
        }
        int i = this.imageResourceId;
        if (i != -1) {
            this.mBuilder.setSpan(new CustomImageSpan(i, this.alignImage), length, length2, this.flag);
        }
    }

    private void updateSpace() {
        int length = this.mBuilder.length();
        this.mText = "< >";
        updateCharCharSequence();
        this.mBuilder.setSpan(new SpaceSpan(this.spaceSize, this.spaceColor), length, this.mBuilder.length(), this.flag);
    }

    static class VerticalAlignSpan extends ReplacementSpan {
        static final int ALIGN_CENTER = 2;
        static final int ALIGN_TOP = 3;
        final int mVerticalAlignment;

        VerticalAlignSpan(int i) {
            this.mVerticalAlignment = i;
        }

        public int getSize(@NonNull Paint paint, CharSequence charSequence, int i, int i2, @Nullable Paint.FontMetricsInt fontMetricsInt) {
            if (paint != null) {
                return (int) paint.measureText(charSequence.subSequence(i, i2).toString());
            }
            throw new NullPointerException("Argument 'paint' of type Paint (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }

        public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
            if (canvas == null) {
                throw new NullPointerException("Argument 'canvas' of type Canvas (#0 out of 9, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            } else if (paint != null) {
                CharSequence subSequence = charSequence.subSequence(i, i2);
                Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
                canvas.drawText(subSequence.toString(), f, (float) (i4 - (((((fontMetricsInt.descent + i4) + i4) + fontMetricsInt.ascent) / 2) - ((i5 + i3) / 2))), paint);
            } else {
                throw new NullPointerException("Argument 'paint' of type Paint (#8 out of 9, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            }
        }
    }

    static class CustomLineHeightSpan implements LineHeightSpan {
        static final int ALIGN_CENTER = 2;
        static final int ALIGN_TOP = 3;
        static Paint.FontMetricsInt sfm;
        private final int height;
        final int mVerticalAlignment;

        CustomLineHeightSpan(int i, int i2) {
            this.height = i;
            this.mVerticalAlignment = i2;
        }

        public void chooseHeight(CharSequence charSequence, int i, int i2, int i3, int i4, Paint.FontMetricsInt fontMetricsInt) {
            Paint.FontMetricsInt fontMetricsInt2 = sfm;
            if (fontMetricsInt2 == null) {
                sfm = new Paint.FontMetricsInt();
                sfm.top = fontMetricsInt.top;
                sfm.ascent = fontMetricsInt.ascent;
                sfm.descent = fontMetricsInt.descent;
                sfm.bottom = fontMetricsInt.bottom;
                sfm.leading = fontMetricsInt.leading;
            } else {
                fontMetricsInt.top = fontMetricsInt2.top;
                fontMetricsInt.ascent = sfm.ascent;
                fontMetricsInt.descent = sfm.descent;
                fontMetricsInt.bottom = sfm.bottom;
                fontMetricsInt.leading = sfm.leading;
            }
            int i5 = this.height - (((fontMetricsInt.descent + i4) - fontMetricsInt.ascent) - i3);
            if (i5 > 0) {
                int i6 = this.mVerticalAlignment;
                if (i6 == 3) {
                    fontMetricsInt.descent += i5;
                } else if (i6 == 2) {
                    int i7 = i5 / 2;
                    fontMetricsInt.descent += i7;
                    fontMetricsInt.ascent -= i7;
                } else {
                    fontMetricsInt.ascent -= i5;
                }
            }
            int i8 = this.height - (((i4 + fontMetricsInt.bottom) - fontMetricsInt.top) - i3);
            if (i8 > 0) {
                int i9 = this.mVerticalAlignment;
                if (i9 == 3) {
                    fontMetricsInt.bottom += i8;
                } else if (i9 == 2) {
                    int i10 = i8 / 2;
                    fontMetricsInt.bottom += i10;
                    fontMetricsInt.top -= i10;
                } else {
                    fontMetricsInt.top -= i8;
                }
            }
            if (i2 == ((Spanned) charSequence).getSpanEnd(this)) {
                sfm = null;
            }
        }
    }

    static class SpaceSpan extends ReplacementSpan {
        private final Paint paint;
        private final int width;

        private SpaceSpan(int i) {
            this(i, 0);
        }

        private SpaceSpan(int i, int i2) {
            this.paint = new Paint();
            this.width = i;
            this.paint.setColor(i2);
            this.paint.setStyle(Paint.Style.FILL);
        }

        public int getSize(@NonNull Paint paint2, CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, @Nullable Paint.FontMetricsInt fontMetricsInt) {
            if (paint2 != null) {
                return this.width;
            }
            throw new NullPointerException("Argument 'paint' of type Paint (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }

        public void draw(@NonNull Canvas canvas, CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, float f, int i3, int i4, int i5, @NonNull Paint paint2) {
            if (canvas == null) {
                throw new NullPointerException("Argument 'canvas' of type Canvas (#0 out of 9, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            } else if (paint2 != null) {
                canvas.drawRect(f, (float) i3, f + ((float) this.width), (float) i5, this.paint);
            } else {
                throw new NullPointerException("Argument 'paint' of type Paint (#8 out of 9, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            }
        }
    }

    static class CustomQuoteSpan implements LeadingMarginSpan {
        private final int color;
        private final int gapWidth;
        private final int stripeWidth;

        private CustomQuoteSpan(int i, int i2, int i3) {
            this.color = i;
            this.stripeWidth = i2;
            this.gapWidth = i3;
        }

        public int getLeadingMargin(boolean z) {
            return this.stripeWidth + this.gapWidth;
        }

        public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
            Paint paint2 = paint;
            int i8 = i;
            Paint.Style style = paint.getStyle();
            int color2 = paint.getColor();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(this.color);
            canvas.drawRect((float) i8, (float) i3, (float) (i8 + (this.stripeWidth * i2)), (float) i5, paint);
            paint.setStyle(style);
            paint.setColor(color2);
        }
    }

    static class CustomBulletSpan implements LeadingMarginSpan {
        private final int color;
        private final int gapWidth;
        private final int radius;
        private Path sBulletPath;

        private CustomBulletSpan(int i, int i2, int i3) {
            this.sBulletPath = null;
            this.color = i;
            this.radius = i2;
            this.gapWidth = i3;
        }

        public int getLeadingMargin(boolean z) {
            return (this.radius * 2) + this.gapWidth;
        }

        public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
            if (((Spanned) charSequence).getSpanStart(this) == i6) {
                Paint.Style style = paint.getStyle();
                int color2 = paint.getColor();
                paint.setColor(this.color);
                paint.setStyle(Paint.Style.FILL);
                if (canvas.isHardwareAccelerated()) {
                    if (this.sBulletPath == null) {
                        this.sBulletPath = new Path();
                        this.sBulletPath.addCircle(0.0f, 0.0f, (float) this.radius, Path.Direction.CW);
                    }
                    canvas.save();
                    canvas.translate((float) (i + (i2 * this.radius)), ((float) (i3 + i5)) / 2.0f);
                    canvas.drawPath(this.sBulletPath, paint);
                    canvas.restore();
                } else {
                    int i8 = this.radius;
                    canvas.drawCircle((float) (i + (i2 * i8)), ((float) (i3 + i5)) / 2.0f, (float) i8, paint);
                }
                paint.setColor(color2);
                paint.setStyle(style);
            }
        }
    }

    @SuppressLint({"ParcelCreator"})
    static class CustomTypefaceSpan extends TypefaceSpan {
        private final Typeface newType;

        private CustomTypefaceSpan(Typeface typeface) {
            super("");
            this.newType = typeface;
        }

        public void updateDrawState(TextPaint textPaint) {
            apply(textPaint, this.newType);
        }

        public void updateMeasureState(TextPaint textPaint) {
            apply(textPaint, this.newType);
        }

        private void apply(Paint paint, Typeface typeface) {
            int i;
            Typeface typeface2 = paint.getTypeface();
            if (typeface2 == null) {
                i = 0;
            } else {
                i = typeface2.getStyle();
            }
            int style = i & (typeface.getStyle() ^ -1);
            if ((style & 1) != 0) {
                paint.setFakeBoldText(true);
            }
            if ((style & 2) != 0) {
                paint.setTextSkewX(-0.25f);
            }
            paint.getShader();
            paint.setTypeface(typeface);
        }
    }

    static class CustomImageSpan extends CustomDynamicDrawableSpan {
        private Uri mContentUri;
        private Drawable mDrawable;
        private int mResourceId;

        private CustomImageSpan(Bitmap bitmap, int i) {
            super(i);
            this.mDrawable = new BitmapDrawable(Utils.getApp().getResources(), bitmap);
            Drawable drawable = this.mDrawable;
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), this.mDrawable.getIntrinsicHeight());
        }

        private CustomImageSpan(Drawable drawable, int i) {
            super(i);
            this.mDrawable = drawable;
            Drawable drawable2 = this.mDrawable;
            drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), this.mDrawable.getIntrinsicHeight());
        }

        private CustomImageSpan(Uri uri, int i) {
            super(i);
            this.mContentUri = uri;
        }

        private CustomImageSpan(@DrawableRes int i, int i2) {
            super(i2);
            this.mResourceId = i;
        }

        public Drawable getDrawable() {
            Drawable drawable;
            Drawable drawable2 = this.mDrawable;
            if (drawable2 != null) {
                return drawable2;
            }
            BitmapDrawable bitmapDrawable = null;
            if (this.mContentUri != null) {
                try {
                    InputStream openInputStream = Utils.getApp().getContentResolver().openInputStream(this.mContentUri);
                    BitmapDrawable bitmapDrawable2 = new BitmapDrawable(Utils.getApp().getResources(), BitmapFactory.decodeStream(openInputStream));
                    try {
                        bitmapDrawable2.setBounds(0, 0, bitmapDrawable2.getIntrinsicWidth(), bitmapDrawable2.getIntrinsicHeight());
                        if (openInputStream != null) {
                            openInputStream.close();
                        }
                        return bitmapDrawable2;
                    } catch (Exception e) {
                        e = e;
                        bitmapDrawable = bitmapDrawable2;
                        Log.e("sms", "Failed to loaded content " + this.mContentUri, e);
                        return bitmapDrawable;
                    }
                } catch (Exception e2) {
                    e = e2;
                    Log.e("sms", "Failed to loaded content " + this.mContentUri, e);
                    return bitmapDrawable;
                }
            } else {
                try {
                    drawable = ContextCompat.getDrawable(Utils.getApp(), this.mResourceId);
                    try {
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        return drawable;
                    } catch (Exception unused) {
                    }
                } catch (Exception unused2) {
                    drawable = null;
                    Log.e("sms", "Unable to find resource: " + this.mResourceId);
                    return drawable;
                }
            }
        }
    }

    static abstract class CustomDynamicDrawableSpan extends ReplacementSpan {
        static final int ALIGN_BASELINE = 1;
        static final int ALIGN_BOTTOM = 0;
        static final int ALIGN_CENTER = 2;
        static final int ALIGN_TOP = 3;
        private WeakReference<Drawable> mDrawableRef;
        final int mVerticalAlignment;

        public abstract Drawable getDrawable();

        private CustomDynamicDrawableSpan() {
            this.mVerticalAlignment = 0;
        }

        private CustomDynamicDrawableSpan(int i) {
            this.mVerticalAlignment = i;
        }

        public int getSize(@NonNull Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
            int i3;
            if (paint != null) {
                Rect bounds = getCachedDrawable().getBounds();
                if (fontMetricsInt != null && (i3 = fontMetricsInt.bottom - fontMetricsInt.top) < bounds.height()) {
                    int i4 = this.mVerticalAlignment;
                    if (i4 == 3) {
                        fontMetricsInt.top = fontMetricsInt.top;
                        fontMetricsInt.bottom = bounds.height() + fontMetricsInt.top;
                    } else if (i4 == 2) {
                        int i5 = i3 / 4;
                        fontMetricsInt.top = ((-bounds.height()) / 2) - i5;
                        fontMetricsInt.bottom = (bounds.height() / 2) - i5;
                    } else {
                        fontMetricsInt.top = (-bounds.height()) + fontMetricsInt.bottom;
                        fontMetricsInt.bottom = fontMetricsInt.bottom;
                    }
                    fontMetricsInt.ascent = fontMetricsInt.top;
                    fontMetricsInt.descent = fontMetricsInt.bottom;
                }
                return bounds.right;
            }
            throw new NullPointerException("Argument 'paint' of type Paint (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }

        public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
            float f2;
            int height;
            if (canvas == null) {
                throw new NullPointerException("Argument 'canvas' of type Canvas (#0 out of 9, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            } else if (paint != null) {
                Drawable cachedDrawable = getCachedDrawable();
                Rect bounds = cachedDrawable.getBounds();
                canvas.save();
                if (bounds.height() < i5 - i3) {
                    int i6 = this.mVerticalAlignment;
                    if (i6 == 3) {
                        f2 = (float) i3;
                    } else {
                        if (i6 == 2) {
                            height = ((i5 + i3) - bounds.height()) / 2;
                        } else if (i6 == 1) {
                            f2 = (float) (i4 - bounds.height());
                        } else {
                            height = i5 - bounds.height();
                        }
                        f2 = (float) height;
                    }
                    canvas.translate(f, f2);
                } else {
                    canvas.translate(f, (float) i3);
                }
                cachedDrawable.draw(canvas);
                canvas.restore();
            } else {
                throw new NullPointerException("Argument 'paint' of type Paint (#8 out of 9, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            }
        }

        private Drawable getCachedDrawable() {
            WeakReference<Drawable> weakReference = this.mDrawableRef;
            Drawable drawable = weakReference != null ? (Drawable) weakReference.get() : null;
            if (drawable != null) {
                return drawable;
            }
            Drawable drawable2 = getDrawable();
            this.mDrawableRef = new WeakReference<>(drawable2);
            return drawable2;
        }
    }

    static class ShaderSpan extends CharacterStyle implements UpdateAppearance {
        private Shader mShader;

        private ShaderSpan(Shader shader) {
            this.mShader = shader;
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setShader(this.mShader);
        }
    }

    static class ShadowSpan extends CharacterStyle implements UpdateAppearance {

        /* renamed from: dx */
        private float f1499dx;

        /* renamed from: dy */
        private float f1500dy;
        private float radius;
        private int shadowColor;

        private ShadowSpan(float f, float f2, float f3, int i) {
            this.radius = f;
            this.f1499dx = f2;
            this.f1500dy = f3;
            this.shadowColor = i;
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setShadowLayer(this.radius, this.f1499dx, this.f1500dy, this.shadowColor);
        }
    }

    private static class SerializableSpannableStringBuilder extends SpannableStringBuilder implements Serializable {
        private static final long serialVersionUID = 4909567650765875771L;

        private SerializableSpannableStringBuilder() {
        }
    }
}
