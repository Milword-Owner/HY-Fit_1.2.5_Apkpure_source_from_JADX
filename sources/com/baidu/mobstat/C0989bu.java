package com.baidu.mobstat;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.bu */
public class C0989bu {

    /* renamed from: b */
    private static volatile C0989bu f1272b;

    /* renamed from: c */
    private static final Pattern f1273c = Pattern.compile("\\s*|\t|\r|\n");

    /* renamed from: i */
    private static boolean f1274i = true;

    /* renamed from: j */
    private static boolean f1275j = false;

    /* renamed from: k */
    private static OnAppBackgroundListener f1276k;

    /* renamed from: a */
    public final int f1277a = 100;

    /* renamed from: d */
    private List<PermissionEnum> f1278d = new ArrayList();

    /* renamed from: e */
    private String f1279e = "android.permission.APP_LIST";

    /* renamed from: f */
    private String f1280f;

    /* renamed from: g */
    private String f1281g;

    /* renamed from: h */
    private List<JSONObject> f1282h = new ArrayList();

    /* renamed from: a */
    public static C0989bu m1416a() {
        if (f1272b == null) {
            synchronized (C0989bu.class) {
                if (f1272b == null) {
                    f1272b = new C0989bu();
                }
            }
        }
        return f1272b;
    }

    /* renamed from: a */
    public void mo11720a(String str) {
        this.f1281g = str;
    }

    /* renamed from: b */
    public String mo11722b() {
        if (TextUtils.isEmpty(this.f1280f)) {
            return "";
        }
        return C0991bw.m1435a(f1273c.matcher(this.f1280f).replaceAll(""));
    }

    /* renamed from: c */
    public boolean mo11724c() {
        return mo11721a(true);
    }

    /* renamed from: a */
    public boolean mo11721a(boolean z) {
        if (z) {
            return f1274i && !m1417d();
        }
        return f1274i;
    }

    /* renamed from: b */
    public void mo11723b(boolean z) {
        f1274i = z;
    }

    /* renamed from: d */
    private boolean m1417d() {
        OnAppBackgroundListener onAppBackgroundListener = f1276k;
        if (onAppBackgroundListener != null) {
            return onAppBackgroundListener.isBackground();
        }
        return f1275j;
    }

    /* renamed from: a */
    public void mo11719a(OnAppBackgroundListener onAppBackgroundListener) {
        f1276k = onAppBackgroundListener;
    }
}
