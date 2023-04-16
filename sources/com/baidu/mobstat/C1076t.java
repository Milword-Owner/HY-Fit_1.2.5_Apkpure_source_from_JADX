package com.baidu.mobstat;

import android.content.Context;

/* renamed from: com.baidu.mobstat.t */
public class C1076t {
    /* renamed from: a */
    public static synchronized C0875a m1784a(Context context) {
        C1077u uVar;
        synchronized (C1076t.class) {
            C0954ba.m1191c().mo11624a("getBPStretegyController begin");
            uVar = new C1077u();
            C0954ba.m1191c().mo11624a("Get BPStretegyController load local class");
            C1083v.m1791a(context, uVar);
            C0954ba.m1191c().mo11624a("getBPStretegyController end");
        }
        return uVar;
    }
}
