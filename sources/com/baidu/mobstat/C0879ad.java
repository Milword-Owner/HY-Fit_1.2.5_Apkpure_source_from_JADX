package com.baidu.mobstat;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import com.baidu.mobstat.ActivityLifeObserver;
import com.baidu.mobstat.BaiduStatJSInterface;
import org.json.JSONArray;

/* renamed from: com.baidu.mobstat.ad */
public class C0879ad {

    /* renamed from: a */
    private static volatile boolean f937a = true;

    /* renamed from: b */
    private static volatile boolean f938b = false;

    /* renamed from: a */
    public static boolean m835a() {
        return f938b;
    }

    /* renamed from: a */
    public static void m833a(String str) {
        f938b = true;
        C0900al.m880a().mo11497a(str);
    }

    /* renamed from: a */
    public static void m834a(boolean z) {
        C0900al.m880a().mo11498a(z);
    }

    /* renamed from: b */
    public static boolean m836b() {
        return f937a;
    }

    /* renamed from: c */
    public static JSONArray m837c() {
        return C0900al.m880a().mo11505e();
    }

    /* renamed from: com.baidu.mobstat.ad$a */
    public static class C0880a implements ActivityLifeObserver.IActivityLifeCallback {
        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (C0956bc.m1198c().mo11630b()) {
                C0956bc.m1198c().mo11624a("onActivityCreated");
            }
            C0900al.m880a().mo11494a(activity);
        }

        public void onActivityResumed(Activity activity) {
            if (C0956bc.m1198c().mo11630b()) {
                C0956bc.m1198c().mo11624a("onActivityResumed");
            }
            C0900al.m880a().mo11501b(activity);
        }

        public void onActivityPaused(Activity activity) {
            if (C0956bc.m1198c().mo11630b()) {
                C0956bc.m1198c().mo11624a("onActivityPaused");
            }
            C0900al.m880a().mo11503c(activity);
        }
    }

    /* renamed from: com.baidu.mobstat.ad$b */
    public static class C0881b implements BaiduStatJSInterface.IWebviewPageLoadCallback {
        public void onPageStarted(WebView webView, String str, C0974bk bkVar) {
            if (C0956bc.m1198c().mo11630b()) {
                C0956bc.m1198c().mo11624a("WebView onPageStarted");
            }
            webView.addJavascriptInterface(bkVar, "WebViewInterface");
        }

        public void onPageFinished(WebView webView, String str, C0974bk bkVar) {
            if (C0956bc.m1198c().mo11630b()) {
                C0956bc.m1198c().mo11624a("WebView onPageFinished");
            }
            webView.addJavascriptInterface(bkVar, "WebViewInterface");
            C0900al.m880a().mo11496a(webView, str, bkVar);
        }
    }
}
