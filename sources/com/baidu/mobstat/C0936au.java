package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebView;
import com.baidu.mobstat.ActivityLifeObserver;
import com.baidu.mobstat.BaiduStatJSInterface;
import com.baidu.mobstat.MtjConfig;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.au */
public class C0936au {
    /* renamed from: a */
    public static void m1089a(String str) {
        if (C0879ad.m835a() && !C0947aw.m1142a().mo11613b()) {
            C0949ay.m1153a().mo11621b(str);
        }
    }

    /* renamed from: a */
    public static void m1088a(MtjConfig.FeedTrackStrategy feedTrackStrategy) {
        if (!C0947aw.m1142a().mo11613b()) {
            C0913aq.m986a(feedTrackStrategy);
        }
    }

    /* renamed from: a */
    public static void m1090a(JSONObject jSONObject) {
        if (C0879ad.m835a() && !C0947aw.m1142a().mo11613b()) {
            C0939av.m1093a().mo11596a(jSONObject);
        }
    }

    /* renamed from: a */
    public static void m1086a(Context context) {
        if (C0879ad.m835a() && !C0947aw.m1142a().mo11613b()) {
            C0939av.m1093a().mo11588a(context);
        }
    }

    /* renamed from: a */
    public static void m1087a(Context context, boolean z) {
        if (C0879ad.m835a() && !C0947aw.m1142a().mo11613b()) {
            C0939av.m1093a().mo11594a(context, z);
        }
    }

    /* renamed from: com.baidu.mobstat.au$a */
    public static class C0937a implements ActivityLifeObserver.IActivityLifeCallback {
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
            if (C0879ad.m835a() && !C0947aw.m1142a().mo11613b()) {
                if (C0963bg.m1227c().mo11630b()) {
                    C0963bg.m1227c().mo11624a("onActivityResumed");
                }
                C0949ay.m1153a().mo11615a(activity);
            }
        }

        public void onActivityPaused(Activity activity) {
            if (C0879ad.m835a() && !C0947aw.m1142a().mo11613b()) {
                if (C0963bg.m1227c().mo11630b()) {
                    C0963bg.m1227c().mo11624a("onActivityPaused");
                }
                C0949ay.m1153a().mo11620b(activity);
            }
        }
    }

    /* renamed from: com.baidu.mobstat.au$b */
    public static class C0938b implements BaiduStatJSInterface.IWebviewPageLoadCallback {
        public void onPageStarted(WebView webView, String str, C0974bk bkVar) {
            if (C0879ad.m835a() && !C0947aw.m1142a().mo11613b()) {
                if (C0963bg.m1227c().mo11630b()) {
                    C0963bg.m1227c().mo11624a("WebView onPageStarted");
                }
                webView.addJavascriptInterface(bkVar, "WebViewInterface");
            }
        }

        public void onPageFinished(WebView webView, String str, C0974bk bkVar) {
            if (C0879ad.m835a() && !C0947aw.m1142a().mo11613b()) {
                if (C0963bg.m1227c().mo11630b()) {
                    C0963bg.m1227c().mo11624a("WebView onPageFinished");
                }
                webView.addJavascriptInterface(bkVar, "WebViewInterface");
                C0949ay.m1153a().mo11617a(webView, str, bkVar);
            }
        }
    }

    /* renamed from: b */
    public static void m1091b(String str) {
        if (C0879ad.m835a()) {
            C0949ay.m1153a().mo11618a(str);
        }
    }
}
