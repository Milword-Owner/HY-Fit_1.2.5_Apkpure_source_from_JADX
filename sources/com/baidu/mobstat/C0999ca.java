package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;

/* renamed from: com.baidu.mobstat.ca */
public class C0999ca {

    /* renamed from: a */
    private static C0999ca f1289a = new C0999ca();
    /* access modifiers changed from: private */

    /* renamed from: b */
    public static String f1290b = "";

    /* renamed from: a */
    public static C0999ca m1493a() {
        return f1289a;
    }

    /* renamed from: a */
    public void mo11730a(Context context, final C0997bz bzVar) {
        f1290b = C1002cc.m1499a(context);
        if (TextUtils.isEmpty(f1290b)) {
            C0995by.m1487a(context, new C1001cb() {
                /* renamed from: a */
                public void mo11731a(String str) {
                    if (!TextUtils.isEmpty(str)) {
                        String unused = C0999ca.f1290b = str;
                        C0997bz bzVar = bzVar;
                        if (bzVar != null) {
                            bzVar.mo11725a(C0999ca.f1290b);
                        }
                    }
                }
            });
        } else if (bzVar != null) {
            bzVar.mo11725a(f1290b);
        }
    }
}
