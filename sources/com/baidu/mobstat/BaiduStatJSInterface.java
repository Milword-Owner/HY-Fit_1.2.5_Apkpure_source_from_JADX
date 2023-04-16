package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.HttpAuthHandler;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.internal.NativeProtocol;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class BaiduStatJSInterface {

    public interface IWebviewPageLoadCallback {
        void onPageFinished(WebView webView, String str, C0974bk bkVar);

        void onPageStarted(WebView webView, String str, C0974bk bkVar);
    }

    public static class CustomWebViewClient extends WebViewClient {

        /* renamed from: a */
        private WeakReference<Context> f774a;

        /* renamed from: b */
        private WebViewClient f775b;

        /* renamed from: c */
        private IWebviewPageLoadCallback f776c;

        /* renamed from: d */
        private C0974bk f777d;

        public CustomWebViewClient(Context context, WebViewClient webViewClient, IWebviewPageLoadCallback iWebviewPageLoadCallback, C0974bk bkVar) {
            this.f774a = new WeakReference<>(context);
            this.f775b = webViewClient;
            this.f776c = iWebviewPageLoadCallback;
            this.f777d = bkVar;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            try {
                str = URLDecoder.decode(str, "UTF-8");
                if (!TextUtils.isEmpty(str) && str.startsWith("bmtj:")) {
                    m718a(str.substring(5));
                    return true;
                }
            } catch (UnsupportedEncodingException | JSONException unused) {
            }
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                return webViewClient.shouldOverrideUrlLoading(webView, str);
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }

        @SuppressLint({"NewApi"})
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                return webViewClient.shouldOverrideUrlLoading(webView, webResourceRequest);
            }
            return super.shouldOverrideUrlLoading(webView, webResourceRequest);
        }

        /* renamed from: a */
        private void m718a(String str) throws JSONException {
            JSONObject jSONObject;
            JSONObject jSONObject2;
            JSONObject jSONObject3;
            JSONObject jSONObject4 = new JSONObject(str);
            String string = jSONObject4.getString(NativeProtocol.WEB_DIALOG_ACTION);
            JSONObject jSONObject5 = jSONObject4.getJSONObject("obj");
            Context context = (Context) this.f774a.get();
            if (context != null) {
                if ("onPageStart".equals(string)) {
                    String string2 = jSONObject5.getString("page");
                    if (!TextUtils.isEmpty(string2)) {
                        BDStatCore.instance().onPageStart(context, string2);
                    }
                } else if ("onPageEnd".equals(string)) {
                    String string3 = jSONObject5.getString("page");
                    if (!TextUtils.isEmpty(string3)) {
                        BDStatCore.instance().onPageEnd(context, string3, (ExtraInfo) null, true);
                    }
                } else if ("onEvent".equals(string)) {
                    String string4 = jSONObject5.getString("event_id");
                    String string5 = jSONObject5.getString("label");
                    int i = jSONObject5.getInt("acc");
                    if (!TextUtils.isEmpty(string4)) {
                        try {
                            jSONObject3 = (JSONObject) jSONObject5.get("attributes");
                        } catch (Exception unused) {
                            jSONObject3 = null;
                        }
                        BDStatCore.instance().onEvent(context, string4, string5, i, (ExtraInfo) null, (Map<String, String>) m717a(jSONObject3), false, true);
                    }
                } else if ("onEventStart".equals(string)) {
                    String string6 = jSONObject5.getString("event_id");
                    String string7 = jSONObject5.getString("label");
                    if (!TextUtils.isEmpty(string6)) {
                        BDStatCore.instance().onEventStart(context, string6, string7, false);
                    }
                } else if ("onEventEnd".equals(string)) {
                    String string8 = jSONObject5.getString("event_id");
                    String string9 = jSONObject5.getString("label");
                    if (!TextUtils.isEmpty(string8)) {
                        try {
                            jSONObject2 = (JSONObject) jSONObject5.get("attributes");
                        } catch (Exception unused2) {
                            jSONObject2 = null;
                        }
                        BDStatCore.instance().onEventEnd(context, string8, string9, (ExtraInfo) null, m717a(jSONObject2), true);
                    }
                } else if ("onEventDuration".equals(string)) {
                    String string10 = jSONObject5.getString("event_id");
                    String string11 = jSONObject5.getString("label");
                    long j = jSONObject5.getLong("duration");
                    if (!TextUtils.isEmpty(string10)) {
                        try {
                            jSONObject = (JSONObject) jSONObject5.get("attributes");
                        } catch (Exception unused3) {
                            jSONObject = null;
                        }
                        BDStatCore.instance().onEventDuration(context, string10, string11, j, (ExtraInfo) null, m717a(jSONObject), false, true);
                    }
                }
            }
        }

        /* renamed from: a */
        private HashMap<String, String> m717a(JSONObject jSONObject) {
            HashMap<String, String> hashMap = null;
            if (jSONObject == null) {
                return null;
            }
            if (jSONObject.length() != 0) {
                hashMap = new HashMap<>();
            }
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                try {
                    String next = keys.next();
                    String string = jSONObject.getString(next);
                    if (hashMap != null) {
                        hashMap.put(next, string);
                    }
                } catch (Exception unused) {
                }
            }
            return hashMap;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                webViewClient.onPageStarted(webView, str, bitmap);
            }
        }

        public void onPageFinished(WebView webView, String str) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                webViewClient.onPageFinished(webView, str);
            }
        }

        public void onLoadResource(WebView webView, String str) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                webViewClient.onLoadResource(webView, str);
            }
        }

        @Deprecated
        public void onTooManyRedirects(WebView webView, Message message, Message message2) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                webViewClient.onTooManyRedirects(webView, message, message2);
            }
        }

        public void onFormResubmission(WebView webView, Message message, Message message2) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                webViewClient.onFormResubmission(webView, message, message2);
            }
        }

        public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                webViewClient.doUpdateVisitedHistory(webView, str, z);
            }
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                webViewClient.onReceivedSslError(webView, sslErrorHandler, sslError);
            }
        }

        public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                webViewClient.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
            }
        }

        public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                return webViewClient.shouldOverrideKeyEvent(webView, keyEvent);
            }
            return super.shouldOverrideKeyEvent(webView, keyEvent);
        }

        public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                webViewClient.onUnhandledKeyEvent(webView, keyEvent);
            }
        }

        public void onScaleChanged(WebView webView, float f, float f2) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                webViewClient.onScaleChanged(webView, f, f2);
            }
        }

        @SuppressLint({"NewApi"})
        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                webViewClient.onReceivedLoginRequest(webView, str, str2, str3);
            }
        }

        @SuppressLint({"NewApi"})
        public void onReceivedClientCertRequest(WebView webView, ClientCertRequest clientCertRequest) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                webViewClient.onReceivedClientCertRequest(webView, clientCertRequest);
            }
        }

        @SuppressLint({"NewApi"})
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                return webViewClient.shouldInterceptRequest(webView, str);
            }
            return super.shouldInterceptRequest(webView, str);
        }

        @SuppressLint({"NewApi"})
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                return webViewClient.shouldInterceptRequest(webView, webResourceRequest);
            }
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }

        @SuppressLint({"NewApi"})
        public void onPageCommitVisible(WebView webView, String str) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                webViewClient.onPageCommitVisible(webView, str);
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                webViewClient.onReceivedError(webView, i, str, str2);
            }
        }

        @SuppressLint({"NewApi"})
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                webViewClient.onReceivedError(webView, webResourceRequest, webResourceError);
            }
        }

        @SuppressLint({"NewApi"})
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                webViewClient.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            }
        }

        @SuppressLint({"NewApi"})
        public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
            WebViewClient webViewClient = this.f775b;
            if (webViewClient != null) {
                return webViewClient.onRenderProcessGone(webView, renderProcessGoneDetail);
            }
            return super.onRenderProcessGone(webView, renderProcessGoneDetail);
        }
    }

    public static class CustomWebChromeViewClient extends WebChromeClient {

        /* renamed from: a */
        private WeakReference<Context> f769a;

        /* renamed from: b */
        private WebChromeClient f770b;

        /* renamed from: c */
        private ArrayList<IWebviewPageLoadCallback> f771c;

        /* renamed from: d */
        private C0974bk f772d;

        /* renamed from: e */
        private int f773e = 0;

        public CustomWebChromeViewClient(Context context, WebChromeClient webChromeClient, ArrayList<IWebviewPageLoadCallback> arrayList, C0974bk bkVar) {
            this.f769a = new WeakReference<>(context);
            this.f770b = webChromeClient;
            this.f771c = arrayList;
            this.f772d = bkVar;
        }

        public void onProgressChanged(WebView webView, int i) {
            ArrayList<IWebviewPageLoadCallback> arrayList = this.f771c;
            if (arrayList != null) {
                if (this.f773e == 0) {
                    Iterator<IWebviewPageLoadCallback> it = arrayList.iterator();
                    while (it.hasNext()) {
                        IWebviewPageLoadCallback next = it.next();
                        if (next != null) {
                            next.onPageStarted(webView, webView.getUrl(), this.f772d);
                        }
                    }
                }
                this.f773e = i;
                if (i == 100) {
                    Iterator<IWebviewPageLoadCallback> it2 = this.f771c.iterator();
                    while (it2.hasNext()) {
                        IWebviewPageLoadCallback next2 = it2.next();
                        if (next2 != null) {
                            next2.onPageFinished(webView, webView.getUrl(), this.f772d);
                        }
                    }
                }
            }
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                webChromeClient.onProgressChanged(webView, i);
            }
        }

        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                return webChromeClient.onCreateWindow(webView, z, z2, message);
            }
            return super.onCreateWindow(webView, z, z2, message);
        }

        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                return webChromeClient.onJsAlert(webView, str, str2, jsResult);
            }
            return super.onJsAlert(webView, str, str2, jsResult);
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                return webChromeClient.onConsoleMessage(consoleMessage);
            }
            return super.onConsoleMessage(consoleMessage);
        }

        @Deprecated
        public void onConsoleMessage(String str, int i, String str2) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                webChromeClient.onConsoleMessage(str, i, str2);
            }
        }

        public void onCloseWindow(WebView webView) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                webChromeClient.onCloseWindow(webView);
            }
        }

        @Deprecated
        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                webChromeClient.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
            }
        }

        public void onGeolocationPermissionsHidePrompt() {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                webChromeClient.onGeolocationPermissionsHidePrompt();
            }
        }

        public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                webChromeClient.onGeolocationPermissionsShowPrompt(str, callback);
            }
        }

        public void onHideCustomView() {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                webChromeClient.onHideCustomView();
            }
        }

        public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                return webChromeClient.onJsBeforeUnload(webView, str, str2, jsResult);
            }
            return super.onJsBeforeUnload(webView, str, str2, jsResult);
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                return webChromeClient.onJsConfirm(webView, str, str2, jsResult);
            }
            return super.onJsConfirm(webView, str, str2, jsResult);
        }

        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                return webChromeClient.onJsPrompt(webView, str, str2, str3, jsPromptResult);
            }
            return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
        }

        @Deprecated
        public boolean onJsTimeout() {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                return webChromeClient.onJsTimeout();
            }
            return super.onJsTimeout();
        }

        @SuppressLint({"NewApi"})
        public void onPermissionRequest(PermissionRequest permissionRequest) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                webChromeClient.onPermissionRequest(permissionRequest);
            }
        }

        @SuppressLint({"NewApi"})
        public void onPermissionRequestCanceled(PermissionRequest permissionRequest) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                webChromeClient.onPermissionRequestCanceled(permissionRequest);
            }
        }

        @Deprecated
        public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                webChromeClient.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
            }
        }

        public void onReceivedIcon(WebView webView, Bitmap bitmap) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                webChromeClient.onReceivedIcon(webView, bitmap);
            }
        }

        public void onReceivedTitle(WebView webView, String str) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                webChromeClient.onReceivedTitle(webView, str);
            }
        }

        public void onReceivedTouchIconUrl(WebView webView, String str, boolean z) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                webChromeClient.onReceivedTouchIconUrl(webView, str, z);
            }
        }

        public void onRequestFocus(WebView webView) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                webChromeClient.onRequestFocus(webView);
            }
        }

        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                webChromeClient.onShowCustomView(view, customViewCallback);
            }
        }

        @SuppressLint({"NewApi"})
        @Deprecated
        public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                webChromeClient.onShowCustomView(view, i, customViewCallback);
            }
        }

        @SuppressLint({"NewApi"})
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            WebChromeClient webChromeClient = this.f770b;
            if (webChromeClient != null) {
                return webChromeClient.onShowFileChooser(webView, valueCallback, fileChooserParams);
            }
            return super.onShowFileChooser(webView, valueCallback, fileChooserParams);
        }
    }
}
