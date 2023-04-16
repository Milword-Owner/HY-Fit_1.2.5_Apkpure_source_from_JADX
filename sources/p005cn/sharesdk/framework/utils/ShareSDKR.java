package p005cn.sharesdk.framework.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.mob.tools.log.NLog;

/* renamed from: cn.sharesdk.framework.utils.ShareSDKR */
public class ShareSDKR {
    public static int getResId(Context context, String str, String str2) {
        int i = 0;
        if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            String packageName = context.getPackageName();
            if (TextUtils.isEmpty(packageName)) {
                return 0;
            }
            i = context.getResources().getIdentifier(str2, str, packageName);
            if (i <= 0) {
                i = context.getResources().getIdentifier(str2.toLowerCase(), str, packageName);
            }
            if (i <= 0) {
                Resources resources = context.getResources();
                i = resources.getIdentifier("ssdk_" + str2, str, packageName);
                if (i <= 0) {
                    Resources resources2 = context.getResources();
                    i = resources2.getIdentifier("ssdk_" + str2.toLowerCase(), str, packageName);
                }
            }
            if (i <= 0) {
                Resources resources3 = context.getResources();
                i = resources3.getIdentifier("ssdk_oks_" + str2, str, packageName);
                if (i <= 0) {
                    Resources resources4 = context.getResources();
                    i = resources4.getIdentifier("ssdk_oks_" + str2.toLowerCase(), str, packageName);
                }
            }
            if (i <= 0) {
                NLog b = SSDKLog.m645b();
                b.mo29786w("failed to parse " + str + " resource \"" + str2 + "\"");
            }
        }
        return i;
    }

    public static int getBitmapRes(Context context, String str) {
        return getResId(context, "drawable", str);
    }

    public static int getStringRes(Context context, String str) {
        return getResId(context, "string", str);
    }

    public static int getStringArrayRes(Context context, String str) {
        return getResId(context, "array", str);
    }

    public static int getLayoutRes(Context context, String str) {
        return getResId(context, "layout", str);
    }

    public static int getStyleRes(Context context, String str) {
        return getResId(context, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, str);
    }

    public static int getIdRes(Context context, String str) {
        return getResId(context, "id", str);
    }

    public static int getColorRes(Context context, String str) {
        return getResId(context, "color", str);
    }

    public static int getRawRes(Context context, String str) {
        return getResId(context, "raw", str);
    }

    public static int getPluralsRes(Context context, String str) {
        return getResId(context, "plurals", str);
    }

    public static int getAnimRes(Context context, String str) {
        return getResId(context, "anim", str);
    }
}
