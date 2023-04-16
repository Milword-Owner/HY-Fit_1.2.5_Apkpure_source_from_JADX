package com.baidu.mobstat;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import com.baidu.mobstat.MtjConfig;

/* renamed from: com.baidu.mobstat.aq */
public class C0913aq {
    /* renamed from: a */
    public static void m986a(MtjConfig.FeedTrackStrategy feedTrackStrategy) {
        C0915as.m997a().mo11553a(feedTrackStrategy);
    }

    /* renamed from: a */
    public static void m983a(Activity activity, boolean z) {
        if (z && !C0914ar.m990a().mo11545b() && !C0915as.m997a().mo11557b()) {
            C0915as.m997a().mo11550a(activity);
        }
    }

    /* renamed from: b */
    public static void m988b(Activity activity, boolean z) {
        if (z && !C0914ar.m990a().mo11545b() && !C0915as.m997a().mo11557b()) {
            C0915as.m997a().mo11556b(activity);
        }
    }

    /* renamed from: c */
    public static void m989c(Activity activity, boolean z) {
        if (z && !C0914ar.m990a().mo11545b() && !C0915as.m997a().mo11557b()) {
            C0915as.m997a().mo11558c(activity);
        }
    }

    /* renamed from: a */
    public static void m985a(View view, Activity activity) {
        if (!C0914ar.m990a().mo11545b() && !C0915as.m997a().mo11557b()) {
            C0915as.m997a().mo11552a(view, activity);
        }
    }

    /* renamed from: a */
    public static void m984a(KeyEvent keyEvent) {
        if (!C0914ar.m990a().mo11545b() && !C0915as.m997a().mo11557b()) {
            C0915as.m997a().mo11551a(keyEvent);
        }
    }

    /* renamed from: a */
    public static void m987a(String str) {
        if (!C0915as.m997a().mo11557b()) {
            C0915as.m997a().mo11554a(str);
        }
    }
}
