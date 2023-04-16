package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.drawerlayout.widget.DrawerLayout;
import com.facebook.appevents.codeless.internal.Constants;

public final class BarUtils {
    private static final int KEY_OFFSET = -123;
    private static final String TAG_OFFSET = "TAG_OFFSET";
    private static final String TAG_STATUS_BAR = "TAG_STATUS_BAR";

    private BarUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int getStatusBarHeight() {
        Resources resources = Utils.getApp().getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", Constants.PLATFORM));
    }

    public static void setStatusBarVisibility(@NonNull Activity activity, boolean z) {
        if (activity != null) {
            setStatusBarVisibility(activity.getWindow(), z);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void setStatusBarVisibility(@NonNull Window window, boolean z) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (z) {
            window.clearFlags(1024);
            showStatusBarView(window);
            addMarginTopEqualStatusBarHeight(window);
        } else {
            window.addFlags(1024);
            hideStatusBarView(window);
            subtractMarginTopEqualStatusBarHeight(window);
        }
    }

    public static boolean isStatusBarVisible(@NonNull Activity activity) {
        if (activity != null) {
            return (activity.getWindow().getAttributes().flags & 1024) == 0;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void setStatusBarLightMode(@NonNull Activity activity, boolean z) {
        if (activity != null) {
            setStatusBarLightMode(activity.getWindow(), z);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void setStatusBarLightMode(@NonNull Window window, boolean z) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT >= 23) {
            View decorView = window.getDecorView();
            int systemUiVisibility = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(z ? systemUiVisibility | 8192 : systemUiVisibility & -8193);
        }
    }

    public static boolean isStatusBarLightMode(@NonNull Activity activity) {
        if (activity != null) {
            return isStatusBarLightMode(activity.getWindow());
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean isStatusBarLightMode(@NonNull Window window) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT < 23 || (window.getDecorView().getSystemUiVisibility() & 8192) == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void addMarginTopEqualStatusBarHeight(@NonNull View view) {
        if (view == null) {
            throw new NullPointerException("Argument 'view' of type View (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT >= 19) {
            view.setTag(TAG_OFFSET);
            Object tag = view.getTag(KEY_OFFSET);
            if (tag == null || !((Boolean) tag).booleanValue()) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin + getStatusBarHeight(), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
                view.setTag(KEY_OFFSET, true);
            }
        }
    }

    public static void subtractMarginTopEqualStatusBarHeight(@NonNull View view) {
        Object tag;
        if (view == null) {
            throw new NullPointerException("Argument 'view' of type View (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT >= 19 && (tag = view.getTag(KEY_OFFSET)) != null && ((Boolean) tag).booleanValue()) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin - getStatusBarHeight(), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            view.setTag(KEY_OFFSET, false);
        }
    }

    private static void addMarginTopEqualStatusBarHeight(Window window) {
        View findViewWithTag;
        if (Build.VERSION.SDK_INT >= 19 && (findViewWithTag = window.getDecorView().findViewWithTag(TAG_OFFSET)) != null) {
            addMarginTopEqualStatusBarHeight(findViewWithTag);
        }
    }

    private static void subtractMarginTopEqualStatusBarHeight(Window window) {
        View findViewWithTag;
        if (Build.VERSION.SDK_INT >= 19 && (findViewWithTag = window.getDecorView().findViewWithTag(TAG_OFFSET)) != null) {
            subtractMarginTopEqualStatusBarHeight(findViewWithTag);
        }
    }

    public static View setStatusBarColor(@NonNull Activity activity, @ColorInt int i) {
        if (activity != null) {
            return setStatusBarColor(activity, i, false);
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static View setStatusBarColor(@NonNull Activity activity, @ColorInt int i, boolean z) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT < 19) {
            return null;
        } else {
            transparentStatusBar(activity);
            return applyStatusBarColor(activity, i, z);
        }
    }

    public static View setStatusBarColor(@NonNull Window window, @ColorInt int i) {
        if (window != null) {
            return setStatusBarColor(window, i, false);
        }
        throw new NullPointerException("Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static View setStatusBarColor(@NonNull Window window, @ColorInt int i, boolean z) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT < 19) {
            return null;
        } else {
            transparentStatusBar(window);
            return applyStatusBarColor(window, i, z);
        }
    }

    public static void setStatusBarColor(@NonNull View view, @ColorInt int i) {
        Activity activityByContext;
        if (view == null) {
            throw new NullPointerException("Argument 'fakeStatusBar' of type View (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT >= 19 && (activityByContext = UtilsBridge.getActivityByContext(view.getContext())) != null) {
            transparentStatusBar(activityByContext);
            view.setVisibility(0);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = getStatusBarHeight();
            view.setBackgroundColor(i);
        }
    }

    public static void setStatusBarCustom(@NonNull View view) {
        Activity activityByContext;
        if (view == null) {
            throw new NullPointerException("Argument 'fakeStatusBar' of type View (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT >= 19 && (activityByContext = UtilsBridge.getActivityByContext(view.getContext())) != null) {
            transparentStatusBar(activityByContext);
            view.setVisibility(0);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-1, getStatusBarHeight()));
                return;
            }
            layoutParams.width = -1;
            layoutParams.height = getStatusBarHeight();
        }
    }

    public static void setStatusBarColor4Drawer(@NonNull DrawerLayout drawerLayout, @NonNull View view, @ColorInt int i) {
        if (drawerLayout == null) {
            throw new NullPointerException("Argument 'drawer' of type DrawerLayout (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (view != null) {
            setStatusBarColor4Drawer(drawerLayout, view, i, false);
        } else {
            throw new NullPointerException("Argument 'fakeStatusBar' of type View (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void setStatusBarColor4Drawer(@NonNull DrawerLayout drawerLayout, @NonNull View view, @ColorInt int i, boolean z) {
        Activity activityByContext;
        if (drawerLayout == null) {
            throw new NullPointerException("Argument 'drawer' of type DrawerLayout (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (view == null) {
            throw new NullPointerException("Argument 'fakeStatusBar' of type View (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT >= 19 && (activityByContext = UtilsBridge.getActivityByContext(view.getContext())) != null) {
            transparentStatusBar(activityByContext);
            drawerLayout.setFitsSystemWindows(false);
            setStatusBarColor(view, i);
            int childCount = drawerLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                drawerLayout.getChildAt(i2).setFitsSystemWindows(false);
            }
            if (z) {
                hideStatusBarView(activityByContext);
            } else {
                setStatusBarColor(activityByContext, i, false);
            }
        }
    }

    private static View applyStatusBarColor(Activity activity, int i, boolean z) {
        return applyStatusBarColor(activity.getWindow(), i, z);
    }

    private static View applyStatusBarColor(Window window, int i, boolean z) {
        ViewGroup viewGroup;
        if (z) {
            viewGroup = (ViewGroup) window.getDecorView();
        } else {
            viewGroup = (ViewGroup) window.findViewById(16908290);
        }
        View findViewWithTag = viewGroup.findViewWithTag(TAG_STATUS_BAR);
        if (findViewWithTag != null) {
            if (findViewWithTag.getVisibility() == 8) {
                findViewWithTag.setVisibility(0);
            }
            findViewWithTag.setBackgroundColor(i);
            return findViewWithTag;
        }
        View createStatusBarView = createStatusBarView(window.getContext(), i);
        viewGroup.addView(createStatusBarView);
        return createStatusBarView;
    }

    private static void hideStatusBarView(Activity activity) {
        hideStatusBarView(activity.getWindow());
    }

    private static void hideStatusBarView(Window window) {
        View findViewWithTag = ((ViewGroup) window.getDecorView()).findViewWithTag(TAG_STATUS_BAR);
        if (findViewWithTag != null) {
            findViewWithTag.setVisibility(8);
        }
    }

    private static void showStatusBarView(Window window) {
        View findViewWithTag = ((ViewGroup) window.getDecorView()).findViewWithTag(TAG_STATUS_BAR);
        if (findViewWithTag != null) {
            findViewWithTag.setVisibility(0);
        }
    }

    private static View createStatusBarView(Context context, int i) {
        View view = new View(context);
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, getStatusBarHeight()));
        view.setBackgroundColor(i);
        view.setTag(TAG_STATUS_BAR);
        return view;
    }

    public static void transparentStatusBar(Activity activity) {
        transparentStatusBar(activity.getWindow());
    }

    public static void transparentStatusBar(Window window) {
        if (Build.VERSION.SDK_INT >= 19) {
            if (Build.VERSION.SDK_INT >= 21) {
                window.clearFlags(67108864);
                window.addFlags(Integer.MIN_VALUE);
                window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() | 1280);
                window.setStatusBarColor(0);
                return;
            }
            window.addFlags(67108864);
        }
    }

    public static int getActionBarHeight() {
        TypedValue typedValue = new TypedValue();
        if (Utils.getApp().getTheme().resolveAttribute(16843499, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, Utils.getApp().getResources().getDisplayMetrics());
        }
        return 0;
    }

    @RequiresPermission("android.permission.EXPAND_STATUS_BAR")
    public static void setNotificationBarVisibility(boolean z) {
        String str;
        if (z) {
            str = Build.VERSION.SDK_INT <= 16 ? "expand" : "expandNotificationsPanel";
        } else {
            str = Build.VERSION.SDK_INT <= 16 ? "collapse" : "collapsePanels";
        }
        invokePanels(str);
    }

    private static void invokePanels(String str) {
        try {
            Class.forName("android.app.StatusBarManager").getMethod(str, new Class[0]).invoke(Utils.getApp().getSystemService("statusbar"), new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getNavBarHeight() {
        Resources resources = Utils.getApp().getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", Constants.PLATFORM);
        if (identifier != 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static void setNavBarVisibility(@NonNull Activity activity, boolean z) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT >= 19) {
            setNavBarVisibility(activity.getWindow(), z);
        }
    }

    public static void setNavBarVisibility(@NonNull Window window, boolean z) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT >= 19) {
            ViewGroup viewGroup = (ViewGroup) window.getDecorView();
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                int id = childAt.getId();
                if (id != -1 && "navigationBarBackground".equals(getResNameById(id))) {
                    childAt.setVisibility(z ? 0 : 4);
                }
            }
            if (z) {
                viewGroup.setSystemUiVisibility(viewGroup.getSystemUiVisibility() & -4611);
            } else {
                viewGroup.setSystemUiVisibility(viewGroup.getSystemUiVisibility() | 4610);
            }
        }
    }

    public static boolean isNavBarVisible(@NonNull Activity activity) {
        if (activity != null) {
            return isNavBarVisible(activity.getWindow());
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean isNavBarVisible(@NonNull Window window) {
        boolean z;
        if (window != null) {
            ViewGroup viewGroup = (ViewGroup) window.getDecorView();
            int childCount = viewGroup.getChildCount();
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    z = false;
                    break;
                }
                View childAt = viewGroup.getChildAt(i);
                int id = childAt.getId();
                if (id != -1 && "navigationBarBackground".equals(getResNameById(id)) && childAt.getVisibility() == 0) {
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                return z;
            }
            if (UtilsBridge.isSamsung() && Build.VERSION.SDK_INT >= 17 && Build.VERSION.SDK_INT < 29) {
                try {
                    if (Settings.Global.getInt(Utils.getApp().getContentResolver(), "navigationbar_hide_bar_enabled") == 0) {
                        return true;
                    }
                    return false;
                } catch (Exception unused) {
                }
            }
            return (viewGroup.getSystemUiVisibility() & 2) == 0;
        }
        throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    private static String getResNameById(int i) {
        try {
            return Utils.getApp().getResources().getResourceEntryName(i);
        } catch (Exception unused) {
            return "";
        }
    }

    @RequiresApi(21)
    public static void setNavBarColor(@NonNull Activity activity, @ColorInt int i) {
        if (activity != null) {
            setNavBarColor(activity.getWindow(), i);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @RequiresApi(21)
    public static void setNavBarColor(@NonNull Window window, @ColorInt int i) {
        if (window != null) {
            window.addFlags(Integer.MIN_VALUE);
            window.setNavigationBarColor(i);
            return;
        }
        throw new NullPointerException("Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @RequiresApi(21)
    public static int getNavBarColor(@NonNull Activity activity) {
        if (activity != null) {
            return getNavBarColor(activity.getWindow());
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @RequiresApi(21)
    public static int getNavBarColor(@NonNull Window window) {
        if (window != null) {
            return window.getNavigationBarColor();
        }
        throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean isSupportNavBar() {
        if (Build.VERSION.SDK_INT >= 17) {
            WindowManager windowManager = (WindowManager) Utils.getApp().getSystemService("window");
            if (windowManager == null) {
                return false;
            }
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point point = new Point();
            Point point2 = new Point();
            defaultDisplay.getSize(point);
            defaultDisplay.getRealSize(point2);
            if (point2.y == point.y && point2.x == point.x) {
                return false;
            }
            return true;
        }
        boolean hasPermanentMenuKey = ViewConfiguration.get(Utils.getApp()).hasPermanentMenuKey();
        boolean deviceHasKey = KeyCharacterMap.deviceHasKey(4);
        if (hasPermanentMenuKey || deviceHasKey) {
            return false;
        }
        return true;
    }

    public static void setNavBarLightMode(@NonNull Activity activity, boolean z) {
        if (activity != null) {
            setNavBarLightMode(activity.getWindow(), z);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void setNavBarLightMode(@NonNull Window window, boolean z) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT >= 26) {
            View decorView = window.getDecorView();
            int systemUiVisibility = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(z ? systemUiVisibility | 16 : systemUiVisibility & -17);
        }
    }

    public static boolean isNavBarLightMode(@NonNull Activity activity) {
        if (activity != null) {
            return isNavBarLightMode(activity.getWindow());
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean isNavBarLightMode(@NonNull Window window) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT < 26 || (window.getDecorView().getSystemUiVisibility() & 16) == 0) {
            return false;
        } else {
            return true;
        }
    }
}
