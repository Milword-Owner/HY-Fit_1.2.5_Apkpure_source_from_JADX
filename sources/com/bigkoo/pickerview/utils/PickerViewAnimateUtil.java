package com.bigkoo.pickerview.utils;

import com.bigkoo.pickerview.C1097R;

public class PickerViewAnimateUtil {
    private static final int INVALID = -1;

    public static int getAnimationResource(int i, boolean z) {
        if (i != 80) {
            return -1;
        }
        return z ? C1097R.anim.pickerview_slide_in_bottom : C1097R.anim.pickerview_slide_out_bottom;
    }
}
