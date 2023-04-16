package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.CallSuper;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.blankj.utilcode.C1121R;
import com.blankj.utilcode.util.Utils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

public final class ToastUtils {
    private static final int COLOR_DEFAULT = -16777217;
    private static final ToastUtils DEFAULT_MAKER = make();
    private static final String NOTHING = "toast nothing";
    private static final String NULL = "toast null";
    private static final String TAG_TOAST = "TAG_TOAST";
    /* access modifiers changed from: private */
    public static IToast iToast;
    private boolean isLong = false;
    private boolean isNotUseSystemToast = false;
    /* access modifiers changed from: private */
    public int mBgColor = COLOR_DEFAULT;
    /* access modifiers changed from: private */
    public int mBgResource = -1;
    /* access modifiers changed from: private */
    public int mGravity = -1;
    private Drawable[] mIcons = new Drawable[4];
    private String mMode;
    /* access modifiers changed from: private */
    public int mTextColor = COLOR_DEFAULT;
    /* access modifiers changed from: private */
    public int mTextSize = -1;
    /* access modifiers changed from: private */
    public int mXOffset = -1;
    /* access modifiers changed from: private */
    public int mYOffset = -1;

    interface IToast {
        void cancel();

        void setToastView(View view);

        void setToastView(CharSequence charSequence);

        void show(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MODE {
        public static final String DARK = "dark";
        public static final String LIGHT = "light";
    }

    public static ToastUtils make() {
        return new ToastUtils();
    }

    public final ToastUtils setMode(String str) {
        this.mMode = str;
        return this;
    }

    public final ToastUtils setGravity(int i, int i2, int i3) {
        this.mGravity = i;
        this.mXOffset = i2;
        this.mYOffset = i3;
        return this;
    }

    public final ToastUtils setBgColor(@ColorInt int i) {
        this.mBgColor = i;
        return this;
    }

    public final ToastUtils setBgResource(@DrawableRes int i) {
        this.mBgResource = i;
        return this;
    }

    public final ToastUtils setTextColor(@ColorInt int i) {
        this.mTextColor = i;
        return this;
    }

    public final ToastUtils setTextSize(int i) {
        this.mTextSize = i;
        return this;
    }

    public final ToastUtils setDurationIsLong(boolean z) {
        this.isLong = z;
        return this;
    }

    public final ToastUtils setLeftIcon(@DrawableRes int i) {
        return setLeftIcon(ContextCompat.getDrawable(Utils.getApp(), i));
    }

    public final ToastUtils setLeftIcon(Drawable drawable) {
        this.mIcons[0] = drawable;
        return this;
    }

    public final ToastUtils setTopIcon(@DrawableRes int i) {
        return setTopIcon(ContextCompat.getDrawable(Utils.getApp(), i));
    }

    public final ToastUtils setTopIcon(Drawable drawable) {
        this.mIcons[1] = drawable;
        return this;
    }

    public final ToastUtils setRightIcon(@DrawableRes int i) {
        return setRightIcon(ContextCompat.getDrawable(Utils.getApp(), i));
    }

    public final ToastUtils setRightIcon(Drawable drawable) {
        this.mIcons[2] = drawable;
        return this;
    }

    public final ToastUtils setBottomIcon(int i) {
        return setBottomIcon(ContextCompat.getDrawable(Utils.getApp(), i));
    }

    public final ToastUtils setBottomIcon(Drawable drawable) {
        this.mIcons[3] = drawable;
        return this;
    }

    public final ToastUtils setNotUseSystemToast() {
        this.isNotUseSystemToast = true;
        return this;
    }

    public static ToastUtils getDefaultMaker() {
        return DEFAULT_MAKER;
    }

    public final void show(CharSequence charSequence) {
        show(charSequence, getDuration(), this);
    }

    public final void show(@StringRes int i) {
        show((CharSequence) UtilsBridge.getString(i), getDuration(), this);
    }

    public final void show(@StringRes int i, Object... objArr) {
        show((CharSequence) UtilsBridge.getString(i, objArr), getDuration(), this);
    }

    public final void show(String str, Object... objArr) {
        show((CharSequence) UtilsBridge.format(str, objArr), getDuration(), this);
    }

    public final void show(View view) {
        show(view, getDuration(), this);
    }

    private int getDuration() {
        return this.isLong ? 1 : 0;
    }

    /* access modifiers changed from: private */
    public View tryApplyUtilsToastView(CharSequence charSequence) {
        if (!MODE.DARK.equals(this.mMode) && !MODE.LIGHT.equals(this.mMode)) {
            Drawable[] drawableArr = this.mIcons;
            if (drawableArr[0] == null && drawableArr[1] == null && drawableArr[2] == null && drawableArr[3] == null) {
                return null;
            }
        }
        View layoutId2View = UtilsBridge.layoutId2View(C1121R.C1124layout.utils_toast_view);
        TextView textView = (TextView) layoutId2View.findViewById(16908299);
        if (MODE.DARK.equals(this.mMode)) {
            ((GradientDrawable) layoutId2View.getBackground().mutate()).setColor(Color.parseColor("#BB000000"));
            textView.setTextColor(-1);
        }
        textView.setText(charSequence);
        if (this.mIcons[0] != null) {
            View findViewById = layoutId2View.findViewById(C1121R.C1123id.utvLeftIconView);
            ViewCompat.setBackground(findViewById, this.mIcons[0]);
            findViewById.setVisibility(0);
        }
        if (this.mIcons[1] != null) {
            View findViewById2 = layoutId2View.findViewById(C1121R.C1123id.utvTopIconView);
            ViewCompat.setBackground(findViewById2, this.mIcons[1]);
            findViewById2.setVisibility(0);
        }
        if (this.mIcons[2] != null) {
            View findViewById3 = layoutId2View.findViewById(C1121R.C1123id.utvRightIconView);
            ViewCompat.setBackground(findViewById3, this.mIcons[2]);
            findViewById3.setVisibility(0);
        }
        if (this.mIcons[3] != null) {
            View findViewById4 = layoutId2View.findViewById(C1121R.C1123id.utvBottomIconView);
            ViewCompat.setBackground(findViewById4, this.mIcons[3]);
            findViewById4.setVisibility(0);
        }
        return layoutId2View;
    }

    public static void showShort(CharSequence charSequence) {
        show(charSequence, 0, DEFAULT_MAKER);
    }

    public static void showShort(@StringRes int i) {
        show((CharSequence) UtilsBridge.getString(i), 0, DEFAULT_MAKER);
    }

    public static void showShort(@StringRes int i, Object... objArr) {
        show((CharSequence) UtilsBridge.getString(i, objArr), 0, DEFAULT_MAKER);
    }

    public static void showShort(String str, Object... objArr) {
        show((CharSequence) UtilsBridge.format(str, objArr), 0, DEFAULT_MAKER);
    }

    public static void showLong(CharSequence charSequence) {
        show(charSequence, 1, DEFAULT_MAKER);
    }

    public static void showLong(@StringRes int i) {
        show((CharSequence) UtilsBridge.getString(i), 1, DEFAULT_MAKER);
    }

    public static void showLong(@StringRes int i, Object... objArr) {
        show((CharSequence) UtilsBridge.getString(i), 1, DEFAULT_MAKER);
    }

    public static void showLong(String str, Object... objArr) {
        show((CharSequence) UtilsBridge.format(str, objArr), 1, DEFAULT_MAKER);
    }

    public static void cancel() {
        IToast iToast2 = iToast;
        if (iToast2 != null) {
            iToast2.cancel();
            iToast = null;
        }
    }

    private static void show(CharSequence charSequence, int i, ToastUtils toastUtils) {
        show((View) null, getToastFriendlyText(charSequence), i, toastUtils);
    }

    private static void show(View view, int i, ToastUtils toastUtils) {
        show(view, (CharSequence) null, i, toastUtils);
    }

    private static void show(@Nullable final View view, final CharSequence charSequence, final int i, ToastUtils toastUtils) {
        UtilsBridge.runOnUiThread(new Runnable(toastUtils) {
            final /* synthetic */ ToastUtils val$utils;

            {
                this.val$utils = r1;
            }

            public void run() {
                ToastUtils.cancel();
                IToast unused = ToastUtils.iToast = ToastUtils.newToast(this.val$utils);
                if (view != null) {
                    ToastUtils.iToast.setToastView(view);
                } else {
                    ToastUtils.iToast.setToastView(charSequence);
                }
                ToastUtils.iToast.show(i);
            }
        });
    }

    private static CharSequence getToastFriendlyText(CharSequence charSequence) {
        if (charSequence == null) {
            return NULL;
        }
        return charSequence.length() == 0 ? NOTHING : charSequence;
    }

    /* access modifiers changed from: private */
    public static IToast newToast(ToastUtils toastUtils) {
        if (!toastUtils.isNotUseSystemToast && NotificationManagerCompat.from(Utils.getApp()).areNotificationsEnabled()) {
            if (Build.VERSION.SDK_INT < 23) {
                return new SystemToast(toastUtils);
            }
            if (!UtilsBridge.isGrantedDrawOverlays()) {
                return new SystemToast(toastUtils);
            }
        }
        if (Build.VERSION.SDK_INT < 25) {
            return new WindowManagerToast(toastUtils, 2005);
        }
        if (UtilsBridge.isGrantedDrawOverlays()) {
            if (Build.VERSION.SDK_INT >= 26) {
                new WindowManagerToast(toastUtils, 2038);
            } else {
                new WindowManagerToast(toastUtils, 2002);
            }
        }
        return new ActivityToast(toastUtils);
    }

    static final class SystemToast extends AbsToast {
        SystemToast(ToastUtils toastUtils) {
            super(toastUtils);
            if (Build.VERSION.SDK_INT == 25) {
                try {
                    Field declaredField = Toast.class.getDeclaredField("mTN");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(this.mToast);
                    Field declaredField2 = declaredField.getType().getDeclaredField("mHandler");
                    declaredField2.setAccessible(true);
                    declaredField2.set(obj, new SafeHandler((Handler) declaredField2.get(obj)));
                } catch (Exception unused) {
                }
            }
        }

        public void show(int i) {
            if (this.mToast != null) {
                this.mToast.setDuration(i);
                this.mToast.show();
            }
        }

        static class SafeHandler extends Handler {
            private Handler impl;

            SafeHandler(Handler handler) {
                this.impl = handler;
            }

            public void handleMessage(@NonNull Message message) {
                if (message != null) {
                    this.impl.handleMessage(message);
                    return;
                }
                throw new NullPointerException("Argument 'msg' of type Message (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            }

            public void dispatchMessage(@NonNull Message message) {
                if (message != null) {
                    try {
                        this.impl.dispatchMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    throw new NullPointerException("Argument 'msg' of type Message (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
                }
            }
        }
    }

    static final class WindowManagerToast extends AbsToast {
        private Utils.ActivityLifecycleCallbacks mActivityLifecycleCallbacks;
        private WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
        private WindowManager mWM;

        WindowManagerToast(ToastUtils toastUtils, int i) {
            super(toastUtils);
            this.mParams.type = i;
        }

        public void show(int i) {
            if (this.mToast != null) {
                WindowManager.LayoutParams layoutParams = this.mParams;
                layoutParams.height = -2;
                layoutParams.width = -2;
                layoutParams.format = -3;
                layoutParams.windowAnimations = 16973828;
                layoutParams.setTitle("ToastWithoutNotification");
                WindowManager.LayoutParams layoutParams2 = this.mParams;
                layoutParams2.flags = 152;
                layoutParams2.packageName = Utils.getApp().getPackageName();
                this.mParams.gravity = this.mToast.getGravity();
                if ((this.mParams.gravity & 7) == 7) {
                    this.mParams.horizontalWeight = 1.0f;
                }
                if ((this.mParams.gravity & 112) == 112) {
                    this.mParams.verticalWeight = 1.0f;
                }
                this.mParams.x = this.mToast.getXOffset();
                this.mParams.y = this.mToast.getYOffset();
                this.mParams.horizontalMargin = this.mToast.getHorizontalMargin();
                this.mParams.verticalMargin = this.mToast.getVerticalMargin();
                this.mWM = (WindowManager) Utils.getApp().getSystemService("window");
                try {
                    if (this.mWM != null) {
                        this.mWM.addView(this.mToastView, this.mParams);
                    }
                } catch (Exception unused) {
                }
                UtilsBridge.runOnUiThreadDelayed(new Runnable() {
                    public void run() {
                        WindowManagerToast.this.cancel();
                    }
                }, i == 0 ? 2000 : 3500);
            }
        }

        public void cancel() {
            try {
                if (this.mWM != null) {
                    this.mWM.removeViewImmediate(this.mToastView);
                    this.mWM = null;
                }
            } catch (Exception unused) {
            }
            super.cancel();
        }
    }

    static final class ActivityToast extends AbsToast {
        private static int sShowingIndex;
        private Utils.ActivityLifecycleCallbacks mActivityLifecycleCallbacks;

        ActivityToast(ToastUtils toastUtils) {
            super(toastUtils);
        }

        public void show(int i) {
            if (this.mToast != null) {
                if (!UtilsBridge.isAppForeground()) {
                    showSystemToast(i);
                    return;
                }
                boolean z = false;
                for (Activity next : UtilsBridge.getActivityList()) {
                    if (UtilsBridge.isActivityAlive(next)) {
                        showWithActivity(next, sShowingIndex, true);
                        z = true;
                    }
                }
                if (z) {
                    registerLifecycleCallback();
                    UtilsBridge.runOnUiThreadDelayed(new Runnable() {
                        public void run() {
                            ActivityToast.this.cancel();
                        }
                    }, i == 0 ? 2000 : 3500);
                    sShowingIndex++;
                    return;
                }
                showSystemToast(i);
            }
        }

        public void cancel() {
            Window window;
            if (isShowing()) {
                unregisterLifecycleCallback();
                for (Activity next : UtilsBridge.getActivityList()) {
                    if (UtilsBridge.isActivityAlive(next) && (window = next.getWindow()) != null) {
                        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
                        StringBuilder sb = new StringBuilder();
                        sb.append(ToastUtils.TAG_TOAST);
                        sb.append(sShowingIndex - 1);
                        View findViewWithTag = viewGroup.findViewWithTag(sb.toString());
                        if (findViewWithTag != null) {
                            try {
                                viewGroup.removeView(findViewWithTag);
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
            }
            super.cancel();
        }

        private void showSystemToast(int i) {
            SystemToast systemToast = new SystemToast(this.mToastUtils);
            systemToast.mToast = this.mToast;
            systemToast.show(i);
        }

        /* access modifiers changed from: private */
        public void showWithActivity(Activity activity, int i, boolean z) {
            Window window = activity.getWindow();
            if (window != null) {
                ViewGroup viewGroup = (ViewGroup) window.getDecorView();
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                layoutParams.gravity = this.mToast.getGravity();
                layoutParams.bottomMargin = this.mToast.getYOffset() + UtilsBridge.getNavBarHeight();
                layoutParams.leftMargin = this.mToast.getXOffset();
                View toastViewSnapshot = getToastViewSnapshot(i);
                if (z) {
                    toastViewSnapshot.setAlpha(0.0f);
                    toastViewSnapshot.animate().alpha(1.0f).setDuration(200).start();
                }
                viewGroup.addView(toastViewSnapshot, layoutParams);
            }
        }

        private View getToastViewSnapshot(int i) {
            Bitmap view2Bitmap = UtilsBridge.view2Bitmap(this.mToastView);
            ImageView imageView = new ImageView(Utils.getApp());
            imageView.setTag(ToastUtils.TAG_TOAST + i);
            imageView.setImageBitmap(view2Bitmap);
            return imageView;
        }

        private void registerLifecycleCallback() {
            final int i = sShowingIndex;
            this.mActivityLifecycleCallbacks = new Utils.ActivityLifecycleCallbacks() {
                public void onActivityCreated(@NonNull Activity activity) {
                    if (activity == null) {
                        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
                    } else if (ActivityToast.this.isShowing()) {
                        ActivityToast.this.showWithActivity(activity, i, false);
                    }
                }
            };
            UtilsBridge.addActivityLifecycleCallbacks(this.mActivityLifecycleCallbacks);
        }

        private void unregisterLifecycleCallback() {
            UtilsBridge.removeActivityLifecycleCallbacks(this.mActivityLifecycleCallbacks);
            this.mActivityLifecycleCallbacks = null;
        }

        /* access modifiers changed from: private */
        public boolean isShowing() {
            return this.mActivityLifecycleCallbacks != null;
        }
    }

    static abstract class AbsToast implements IToast {
        protected Toast mToast = new Toast(Utils.getApp());
        protected ToastUtils mToastUtils;
        protected View mToastView;

        AbsToast(ToastUtils toastUtils) {
            this.mToastUtils = toastUtils;
            if (this.mToastUtils.mGravity != -1 || this.mToastUtils.mXOffset != -1 || this.mToastUtils.mYOffset != -1) {
                this.mToast.setGravity(this.mToastUtils.mGravity, this.mToastUtils.mXOffset, this.mToastUtils.mYOffset);
            }
        }

        public void setToastView(View view) {
            this.mToastView = view;
            this.mToast.setView(this.mToastView);
        }

        public void setToastView(CharSequence charSequence) {
            View access$700 = this.mToastUtils.tryApplyUtilsToastView(charSequence);
            if (access$700 != null) {
                setToastView(access$700);
                return;
            }
            this.mToastView = this.mToast.getView();
            View view = this.mToastView;
            if (view == null || view.findViewById(16908299) == null) {
                setToastView(UtilsBridge.layoutId2View(C1121R.C1124layout.utils_toast_view));
            }
            TextView textView = (TextView) this.mToastView.findViewById(16908299);
            textView.setText(charSequence);
            if (this.mToastUtils.mTextColor != ToastUtils.COLOR_DEFAULT) {
                textView.setTextColor(this.mToastUtils.mTextColor);
            }
            if (this.mToastUtils.mTextSize != -1) {
                textView.setTextSize((float) this.mToastUtils.mTextSize);
            }
            setBg(textView);
        }

        /* access modifiers changed from: protected */
        public void setBg(TextView textView) {
            if (this.mToastUtils.mBgResource != -1) {
                this.mToastView.setBackgroundResource(this.mToastUtils.mBgResource);
                textView.setBackgroundColor(0);
            } else if (this.mToastUtils.mBgColor != ToastUtils.COLOR_DEFAULT) {
                Drawable background = this.mToastView.getBackground();
                Drawable background2 = textView.getBackground();
                if (background != null && background2 != null) {
                    background.mutate().setColorFilter(new PorterDuffColorFilter(this.mToastUtils.mBgColor, PorterDuff.Mode.SRC_IN));
                    textView.setBackgroundColor(0);
                } else if (background != null) {
                    background.mutate().setColorFilter(new PorterDuffColorFilter(this.mToastUtils.mBgColor, PorterDuff.Mode.SRC_IN));
                } else if (background2 != null) {
                    background2.mutate().setColorFilter(new PorterDuffColorFilter(this.mToastUtils.mBgColor, PorterDuff.Mode.SRC_IN));
                } else {
                    this.mToastView.setBackgroundColor(this.mToastUtils.mBgColor);
                }
            }
        }

        @CallSuper
        public void cancel() {
            Toast toast = this.mToast;
            if (toast != null) {
                toast.cancel();
            }
            this.mToast = null;
            this.mToastView = null;
        }
    }

    public static final class UtilsMaxWidthRelativeLayout extends RelativeLayout {
        private static final int SPACING = UtilsBridge.dp2px(80.0f);

        public UtilsMaxWidthRelativeLayout(Context context) {
            super(context);
        }

        public UtilsMaxWidthRelativeLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public UtilsMaxWidthRelativeLayout(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(UtilsBridge.getAppScreenWidth() - SPACING, Integer.MIN_VALUE), i2);
        }
    }
}
