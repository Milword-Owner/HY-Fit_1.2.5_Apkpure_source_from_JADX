package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.C1722R;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ViewUtils {

    public interface OnApplyWindowInsetsListener {
        WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat, RelativePadding relativePadding);
    }

    private ViewUtils() {
    }

    public static PorterDuff.Mode parseTintMode(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    public static boolean isLayoutRtl(View view) {
        return ViewCompat.getLayoutDirection(view) == 1;
    }

    public static float dpToPx(@NonNull Context context, @Dimension(unit = 0) int i) {
        return TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    public static void requestFocusAndShowKeyboard(@NonNull final View view) {
        view.requestFocus();
        view.post(new Runnable() {
            public void run() {
                ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 1);
            }
        });
    }

    public static class RelativePadding {
        public int bottom;
        public int end;
        public int start;

        /* renamed from: top  reason: collision with root package name */
        public int f2832top;

        public RelativePadding(int i, int i2, int i3, int i4) {
            this.start = i;
            this.f2832top = i2;
            this.end = i3;
            this.bottom = i4;
        }

        public RelativePadding(@NonNull RelativePadding relativePadding) {
            this.start = relativePadding.start;
            this.f2832top = relativePadding.f2832top;
            this.end = relativePadding.end;
            this.bottom = relativePadding.bottom;
        }

        public void applyToView(View view) {
            ViewCompat.setPaddingRelative(view, this.start, this.f2832top, this.end, this.bottom);
        }
    }

    public static void doOnApplyWindowInsets(@NonNull View view, @Nullable AttributeSet attributeSet, int i, int i2) {
        doOnApplyWindowInsets(view, attributeSet, i, i2, (OnApplyWindowInsetsListener) null);
    }

    public static void doOnApplyWindowInsets(@NonNull View view, @Nullable AttributeSet attributeSet, int i, int i2, @Nullable final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        TypedArray obtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, C1722R.styleable.Insets, i, i2);
        final boolean z = obtainStyledAttributes.getBoolean(C1722R.styleable.Insets_paddingBottomSystemWindowInsets, false);
        final boolean z2 = obtainStyledAttributes.getBoolean(C1722R.styleable.Insets_paddingLeftSystemWindowInsets, false);
        final boolean z3 = obtainStyledAttributes.getBoolean(C1722R.styleable.Insets_paddingRightSystemWindowInsets, false);
        obtainStyledAttributes.recycle();
        doOnApplyWindowInsets(view, new OnApplyWindowInsetsListener() {
            @NonNull
            public WindowInsetsCompat onApplyWindowInsets(View view, @NonNull WindowInsetsCompat windowInsetsCompat, @NonNull RelativePadding relativePadding) {
                if (z) {
                    relativePadding.bottom += windowInsetsCompat.getSystemWindowInsetBottom();
                }
                boolean isLayoutRtl = ViewUtils.isLayoutRtl(view);
                if (z2) {
                    if (isLayoutRtl) {
                        relativePadding.end += windowInsetsCompat.getSystemWindowInsetLeft();
                    } else {
                        relativePadding.start += windowInsetsCompat.getSystemWindowInsetLeft();
                    }
                }
                if (z3) {
                    if (isLayoutRtl) {
                        relativePadding.start += windowInsetsCompat.getSystemWindowInsetRight();
                    } else {
                        relativePadding.end += windowInsetsCompat.getSystemWindowInsetRight();
                    }
                }
                relativePadding.applyToView(view);
                OnApplyWindowInsetsListener onApplyWindowInsetsListener = onApplyWindowInsetsListener;
                return onApplyWindowInsetsListener != null ? onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsetsCompat, relativePadding) : windowInsetsCompat;
            }
        });
    }

    public static void doOnApplyWindowInsets(@NonNull View view, @NonNull final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        final RelativePadding relativePadding = new RelativePadding(ViewCompat.getPaddingStart(view), view.getPaddingTop(), ViewCompat.getPaddingEnd(view), view.getPaddingBottom());
        ViewCompat.setOnApplyWindowInsetsListener(view, new androidx.core.view.OnApplyWindowInsetsListener() {
            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsetsCompat, new RelativePadding(relativePadding));
            }
        });
        requestApplyInsetsWhenAttached(view);
    }

    public static void requestApplyInsetsWhenAttached(@NonNull View view) {
        if (ViewCompat.isAttachedToWindow(view)) {
            ViewCompat.requestApplyInsets(view);
        } else {
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                public void onViewDetachedFromWindow(View view) {
                }

                public void onViewAttachedToWindow(@NonNull View view) {
                    view.removeOnAttachStateChangeListener(this);
                    ViewCompat.requestApplyInsets(view);
                }
            });
        }
    }

    public static float getParentAbsoluteElevation(@NonNull View view) {
        float f = 0.0f;
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            f += ViewCompat.getElevation((View) parent);
        }
        return f;
    }

    @Nullable
    public static ViewOverlayImpl getOverlay(@Nullable View view) {
        if (view == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            return new ViewOverlayApi18(view);
        }
        return ViewOverlayApi14.createFrom(view);
    }

    @Nullable
    public static ViewGroup getContentView(@Nullable View view) {
        if (view == null) {
            return null;
        }
        View rootView = view.getRootView();
        ViewGroup viewGroup = (ViewGroup) rootView.findViewById(16908290);
        if (viewGroup != null) {
            return viewGroup;
        }
        if (rootView == view || !(rootView instanceof ViewGroup)) {
            return null;
        }
        return (ViewGroup) rootView;
    }

    @Nullable
    public static ViewOverlayImpl getContentViewOverlay(@NonNull View view) {
        return getOverlay(getContentView(view));
    }

    public static void addOnGlobalLayoutListener(@Nullable View view, @NonNull ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (view != null) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public static void removeOnGlobalLayoutListener(@Nullable View view, @NonNull ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (view != null) {
            removeOnGlobalLayoutListener(view.getViewTreeObserver(), onGlobalLayoutListener);
        }
    }

    public static void removeOnGlobalLayoutListener(@NonNull ViewTreeObserver viewTreeObserver, @NonNull ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (Build.VERSION.SDK_INT >= 16) {
            viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
        } else {
            viewTreeObserver.removeGlobalOnLayoutListener(onGlobalLayoutListener);
        }
    }
}
