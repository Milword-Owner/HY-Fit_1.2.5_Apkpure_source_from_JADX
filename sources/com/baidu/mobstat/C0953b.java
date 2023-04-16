package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;

/* renamed from: com.baidu.mobstat.b */
class C0953b {

    /* renamed from: a */
    static C0953b f1196a = new C0953b();

    C0953b() {
    }

    /* renamed from: a */
    public synchronized void mo11635a(Context context) {
        String o = C0991bw.m1468o(context);
        if (!TextUtils.isEmpty(o)) {
            C1055k.AP_LIST.mo11848a(context, System.currentTimeMillis(), o);
        }
    }
}
