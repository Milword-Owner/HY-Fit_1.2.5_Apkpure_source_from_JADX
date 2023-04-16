package com.baidu.mobstat;

import android.app.Activity;
import android.graphics.Rect;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.facebook.share.internal.ShareConstants;
import java.lang.ref.WeakReference;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.bk */
public class C0974bk {

    /* renamed from: a */
    private static String f1258a;

    /* renamed from: b */
    private WeakReference<WebView> f1259b;

    /* renamed from: c */
    private WeakReference<Activity> f1260c;

    /* renamed from: d */
    private JSONObject f1261d;

    /* renamed from: e */
    private boolean f1262e;

    /* renamed from: f */
    private boolean f1263f;

    /* renamed from: a */
    public void mo11661a(WebView webView, String str) {
        if (!TextUtils.isEmpty(str)) {
            webView.loadUrl("javascript:" + str);
        }
    }

    @JavascriptInterface
    public void setViewportTreeToNative(String str) {
        if (C0956bc.m1198c().mo11630b()) {
            C0956bc c = C0956bc.m1198c();
            c.mo11624a("setViewportTreeToNative " + str);
        }
        f1258a = str;
    }

    /* renamed from: a */
    public static String m1319a(Activity activity, WebView webView, Rect rect) {
        f1258a = "";
        m1323b(activity, webView, rect);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i >= 15) {
                return "";
            }
            try {
                Thread.sleep(20);
            } catch (Exception unused) {
            }
            if (!TextUtils.isEmpty(f1258a)) {
                return f1258a;
            }
            i = i2;
        }
    }

    /* renamed from: b */
    private static void m1323b(Activity activity, final WebView webView, Rect rect) {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        if (rect != null) {
            i4 = C0884ag.m838a(activity, (float) rect.left);
            i3 = C0884ag.m838a(activity, (float) rect.top);
            i2 = C0884ag.m838a(activity, (float) rect.width());
            i = C0884ag.m838a(activity, (float) rect.height());
        } else {
            i = 0;
            i3 = 0;
            i2 = 0;
        }
        final String str = "javascript:window._automtj.getViewportTree('android', '" + ("{\"x\": " + i4 + ", \"y\": " + i3 + ", \"w\": " + i2 + ", \"h\": " + i + ", \"sw\": " + C0884ag.m838a(activity, (float) C0968bi.m1270c(activity)) + ", \"sh\": " + C0884ag.m838a(activity, (float) C0968bi.m1278d(activity)) + "}") + "', 'window.WebViewInterface.setViewportTreeToNative')";
        activity.runOnUiThread(new Runnable() {
            public void run() {
                webView.loadUrl(str);
            }
        });
    }

    /* renamed from: a */
    public void mo11660a(Activity activity, WebView webView, String str, JSONObject jSONObject, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                this.f1263f = z;
                this.f1261d = jSONObject;
            }
            if (z) {
                C0956bc.m1198c().mo11624a("injectTrackJs circleConfig: " + jSONObject);
            }
            if (!m1321a(webView, z)) {
                if (activity != null) {
                    this.f1260c = new WeakReference<>(activity);
                }
                if (webView != null) {
                    this.f1259b = new WeakReference<>(webView);
                }
                this.f1262e = z;
                String a = m1318a();
                if (TextUtils.isEmpty(a)) {
                    a = new JSONObject().toString();
                }
                if (C0956bc.m1198c().mo11630b() && this.f1263f) {
                    C0956bc.m1198c().mo11624a("injectTrackJs h5Config: " + a);
                }
                if (C0963bg.m1227c().mo11630b()) {
                    C0963bg.m1227c().mo11624a("injectTrackJs h5Config: " + a);
                }
                String str2 = "(function(){var h5conf = {\"sdkAPI\": \"window.WebViewInterface.setEventToNative\", \"sdkType\": \"android\", \"events\": " + a + "};" + str + "})()";
                if (webView != null) {
                    webView.loadUrl("javascript:" + str2);
                }
            } else if (z) {
                C0956bc.m1198c().mo11624a("injectTrackJs, no need to entry");
            } else {
                C0963bg.m1227c().mo11624a("injectTrackJs, no need to entry");
            }
        }
    }

    /* renamed from: a */
    private boolean m1321a(WebView webView, boolean z) {
        WeakReference<WebView> weakReference = this.f1259b;
        return (weakReference == null || ((WebView) weakReference.get()) != webView || this.f1262e == z) ? false : true;
    }

    /* renamed from: a */
    private String m1318a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("matchAll", 1);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("meta", jSONObject);
            return jSONObject2.toString();
        } catch (Exception unused) {
            return new JSONObject().toString();
        }
    }

    @JavascriptInterface
    public void setEventToNative(String str) {
        Activity activity;
        WeakReference<WebView> weakReference;
        WebView webView;
        if (C0956bc.m1198c().mo11630b() && this.f1263f) {
            C0956bc c = C0956bc.m1198c();
            c.mo11624a("setEventToNative: " + str);
        }
        if (C0963bg.m1227c().mo11630b()) {
            C0963bg c2 = C0963bg.m1227c();
            c2.mo11624a("setEventToNative: " + str);
        }
        WeakReference<Activity> weakReference2 = this.f1260c;
        if (weakReference2 != null && (activity = (Activity) weakReference2.get()) != null && (weakReference = this.f1259b) != null && (webView = (WebView) weakReference.get()) != null) {
            m1320a(str, activity, webView);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0034 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0035  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1320a(java.lang.String r37, android.app.Activity r38, android.webkit.WebView r39) {
        /*
            r36 = this;
            r6 = r36
            r0 = 0
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x0028 }
            r2 = r37
            r1.<init>(r2)     // Catch:{ Exception -> 0x0028 }
            java.lang.String r2 = "h3"
            org.json.JSONArray r2 = r1.optJSONArray(r2)     // Catch:{ Exception -> 0x0028 }
            java.lang.String r3 = "p2"
            java.lang.String r3 = r1.optString(r3)     // Catch:{ Exception -> 0x0026 }
            java.lang.String r4 = "l"
            java.lang.String r4 = r1.optString(r4)     // Catch:{ Exception -> 0x0024 }
            java.lang.String r5 = "point"
            org.json.JSONObject r0 = r1.optJSONObject(r5)     // Catch:{ Exception -> 0x002b }
            r1 = 1
            goto L_0x002c
        L_0x0024:
            r4 = r0
            goto L_0x002b
        L_0x0026:
            r3 = r0
            goto L_0x002a
        L_0x0028:
            r2 = r0
            r3 = r2
        L_0x002a:
            r4 = r3
        L_0x002b:
            r1 = 0
        L_0x002c:
            r26 = r0
            r22 = r2
            r25 = r4
            if (r1 != 0) goto L_0x0035
            return
        L_0x0035:
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 == 0) goto L_0x0040
            java.lang.String r0 = "/"
            r27 = r0
            goto L_0x0042
        L_0x0040:
            r27 = r3
        L_0x0042:
            org.json.JSONArray r28 = com.baidu.mobstat.C0968bi.m1253a((android.app.Activity) r38, (android.view.View) r39)
            java.lang.Class r0 = r38.getClass()
            java.lang.String r29 = r0.getName()
            java.lang.String r4 = com.baidu.mobstat.C0968bi.m1252a((org.json.JSONArray) r28)
            java.lang.String r5 = com.baidu.mobstat.C0968bi.m1266b((org.json.JSONArray) r22)
            java.lang.String r30 = com.baidu.mobstat.C0968bi.m1285f((android.view.View) r39)
            java.util.Map r31 = com.baidu.mobstat.C0968bi.m1287g((android.view.View) r39)
            android.content.Context r32 = r38.getApplicationContext()
            java.lang.String r33 = ""
            long r34 = java.lang.System.currentTimeMillis()
            org.json.JSONObject r1 = r6.f1261d
            java.lang.Class r0 = r38.getClass()
            java.lang.String r2 = r0.getName()
            r0 = r36
            r3 = r27
            boolean r0 = r0.m1322a((org.json.JSONObject) r1, (java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r4, (java.lang.String) r5)
            if (r0 == 0) goto L_0x009d
            com.baidu.mobstat.BDStatCore r7 = com.baidu.mobstat.BDStatCore.instance()
            r11 = 1
            long r12 = java.lang.System.currentTimeMillis()
            r20 = 1
            r8 = r32
            r9 = r33
            r10 = r25
            r14 = r28
            r15 = r22
            r16 = r29
            r17 = r27
            r18 = r30
            r19 = r31
            r7.onEvent(r8, r9, r10, r11, r12, r14, r15, r16, r17, r18, r19, r20)
            goto L_0x00b4
        L_0x009d:
            com.baidu.mobstat.bc r0 = com.baidu.mobstat.C0956bc.m1198c()
            boolean r0 = r0.mo11630b()
            if (r0 == 0) goto L_0x00b4
            boolean r0 = r6.f1263f
            if (r0 == 0) goto L_0x00b4
            com.baidu.mobstat.bc r0 = com.baidu.mobstat.C0956bc.m1198c()
            java.lang.String r1 = "setEventToNative: not circle event, will not take effect"
            r0.mo11624a((java.lang.String) r1)
        L_0x00b4:
            com.baidu.mobstat.av r7 = com.baidu.mobstat.C0939av.m1093a()
            r12 = 1
            r21 = 1
            r24 = 0
            java.lang.String r11 = ""
            java.lang.String r23 = ""
            r8 = r32
            r9 = r33
            r10 = r25
            r13 = r34
            r15 = r29
            r16 = r28
            r17 = r27
            r18 = r22
            r19 = r30
            r20 = r31
            r22 = r26
            r7.mo11592a(r8, r9, r10, r11, r12, r13, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0974bk.m1320a(java.lang.String, android.app.Activity, android.webkit.WebView):void");
    }

    /* renamed from: a */
    private boolean m1322a(JSONObject jSONObject, String str, String str2, String str3, String str4) {
        int i = 0;
        if (jSONObject == null || jSONObject.toString().equals(new JSONObject().toString()) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            return false;
        }
        try {
            if (((JSONObject) jSONObject.get("meta")).getInt("matchAll") != 0) {
                return true;
            }
        } catch (Exception unused) {
        }
        try {
            JSONArray jSONArray = (JSONArray) jSONObject.get(ShareConstants.WEB_DIALOG_PARAM_DATA);
            boolean z = false;
            while (i < jSONArray.length()) {
                try {
                    JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                    String optString = jSONObject2.optString("page");
                    String optString2 = jSONObject2.optString("layout");
                    String str5 = (String) jSONObject2.opt("url");
                    String str6 = (String) jSONObject2.opt("webLayout");
                    if (str.equals(optString) && str2.equals(str5) && str3.equals(optString2) && str4.equals(str6)) {
                        z = true;
                    }
                    i++;
                } catch (Exception unused2) {
                    return z;
                }
            }
            return z;
        } catch (Exception unused3) {
            return false;
        }
    }
}
