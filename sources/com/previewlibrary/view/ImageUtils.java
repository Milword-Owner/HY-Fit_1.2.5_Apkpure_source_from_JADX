package com.previewlibrary.view;

import android.content.Context;
import com.facebook.appevents.codeless.internal.Constants;
import com.previewlibrary.C2517R;

public class ImageUtils {
    public static int getStatusBarHeight(Context context) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(C2517R.dimen.yms_dimens_50_0_px);
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", Constants.PLATFORM);
        return identifier > 0 ? context.getResources().getDimensionPixelSize(identifier) : dimensionPixelSize;
    }

    public static boolean isLongPressed(float f, float f2, float f3, float f4, long j, long j2, long j3) {
        return Math.abs(f3 - f) <= 10.0f && Math.abs(f4 - f2) <= 10.0f && j2 - j >= j3;
    }
}
