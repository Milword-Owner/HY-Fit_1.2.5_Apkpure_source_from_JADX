package com.baidu.mobstat;

import android.content.Context;

public class GetReverse {

    /* renamed from: a */
    private static ICooperService f821a;

    private GetReverse() {
    }

    public static ICooperService getCooperService(Context context) {
        if (f821a == null) {
            f821a = CooperService.instance();
        }
        return f821a;
    }
}
