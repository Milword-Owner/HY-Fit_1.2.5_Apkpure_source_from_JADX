package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.baidu.mobstat.BaiduStatJSInterface;
import com.baidu.mobstat.C0879ad;
import com.baidu.mobstat.C0936au;
import com.baidu.mobstat.MtjConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatService {
    public static final int EXCEPTION_LOG = 1;
    public static final int JAVA_EXCEPTION_LOG = 16;

    /* renamed from: a */
    private static boolean f927a = false;

    /* renamed from: b */
    private static boolean f928b;

    public interface OnZidReceiveListener {
        String getZid();
    }

    public interface WearListener {
        boolean onSendLogData(String str);
    }

    /* renamed from: a */
    private static boolean m811a(Class<?> cls, String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        boolean z = false;
        for (int i = 2; i < stackTrace.length; i++) {
            StackTraceElement stackTraceElement = stackTrace[i];
            if (stackTraceElement.getMethodName().equals(str)) {
                try {
                    Class cls2 = Class.forName(stackTraceElement.getClassName());
                    while (cls2.getSuperclass() != null && cls2.getSuperclass() != cls) {
                        cls2 = cls2.getSuperclass();
                    }
                    z = true;
                } catch (Exception unused) {
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    private static boolean m809a(Context context) {
        String u = C0991bw.m1474u(context);
        return !TextUtils.isEmpty(u) && u.contains("helios");
    }

    public static void enableDeviceMac(Context context, boolean z) {
        if (context != null && !m809a(context)) {
            CooperService.instance().enableDeviceMac(context, z);
            BDStatCore.instance().init(context);
        }
    }

    public static synchronized void setGlobalExtraInfo(Context context, ExtraInfo extraInfo) {
        synchronized (StatService.class) {
            if (context != null) {
                if (!m809a(context)) {
                    CooperService.instance().setHeaderExt(context, extraInfo);
                    BDStatCore.instance().init(context);
                }
            }
        }
    }

    public static synchronized void onResume(Activity activity) {
        synchronized (StatService.class) {
            if (m810a((Context) activity, "onResume(...)")) {
                if (!m811a((Class<?>) Activity.class, "onResume")) {
                    C0955bb.m1194c().mo11631c("[WARNING] onResume must be called in Activity.onResume()");
                } else if (!m809a((Context) activity)) {
                    BDStatCore.instance().onResume(activity, false);
                }
            }
        }
    }

    public static synchronized void onPause(Activity activity) {
        synchronized (StatService.class) {
            onPause(activity, (ExtraInfo) null);
        }
    }

    public static synchronized void onPause(Activity activity, ExtraInfo extraInfo) {
        synchronized (StatService.class) {
            if (m810a((Context) activity, "onPause(...)")) {
                if (!m811a((Class<?>) Activity.class, "onPause")) {
                    C0955bb.m1194c().mo11631c("[WARNING] onPause must be called in Activity.onPause");
                } else if (!m809a((Context) activity)) {
                    BDStatCore.instance().onPause(activity, false, extraInfo);
                }
            }
        }
    }

    public static synchronized void onPageStart(Context context, String str) {
        synchronized (StatService.class) {
            if (context != null) {
                if (!TextUtils.isEmpty(str)) {
                    if (!m809a(context)) {
                        BDStatCore.instance().onPageStart(context, str);
                        return;
                    }
                    return;
                }
            }
            C0955bb.m1194c().mo11631c("[WARNING] onPageStart parameter invalid");
        }
    }

    public static synchronized void onPageEnd(Context context, String str) {
        synchronized (StatService.class) {
            m802a(context, str, (ExtraInfo) null);
        }
    }

    /* renamed from: a */
    private static synchronized void m802a(Context context, String str, ExtraInfo extraInfo) {
        synchronized (StatService.class) {
            if (context != null) {
                if (!TextUtils.isEmpty(str)) {
                    if (!m809a(context)) {
                        BDStatCore.instance().onPageEnd(context, str, extraInfo);
                        return;
                    }
                    return;
                }
            }
            C0955bb.m1194c().mo11631c("[WARNING] onPageEnd parameter invalid");
        }
    }

    public static void setOn(Context context, int i) {
        if (m810a(context, "setOn(...)") && !f927a && !m809a(context)) {
            f927a = true;
            if ((i & 1) != 0) {
                m807a(context, false);
            } else if ((i & 16) != 0) {
                m807a(context, true);
            }
            BDStatCore.instance().init(context);
        }
    }

    public static void start(Context context) {
        if (m810a(context, "start(...)")) {
            boolean a = C0994bx.m1486a((Class<?>) Application.class, "onCreate");
            if (a) {
                C0955bb.m1194c().mo11631c("[WARNING] start 方法被 Application.onCreate()调用，not a good practice; 可能由于多进程反复重启等原因造成Application.onCreate() 方法多次被执行，导致启动次数高；建议埋点在统计路径触发的第一个页面中，比如APP主页面中");
            }
            if (!m809a(context)) {
                BDStatCore.instance().onSessionStart(context, a);
            }
        }
    }

    @Deprecated
    public static void setSendLogStrategy(Context context, SendStrategyEnum sendStrategyEnum, int i, boolean z) {
        if (m810a(context, "setSendLogStrategy(...)")) {
            boolean a = C0994bx.m1486a((Class<?>) Application.class, "onCreate");
            if (a) {
                C0955bb.m1194c().mo11631c("[WARNING] setSendLogStrategy 方法被 Application.onCreate()调用，not a good practice; 可能由于多进程反复重启等原因造成Application.onCreate() 方法多次被执行，导致启动次数高；建议埋点在统计路径触发的第一个页面中，比如APP主页面中");
            }
            if (!m809a(context)) {
                BDStatCore.instance().onSessionStart(context, a);
                LogSender.instance().setSendLogStrategy(context.getApplicationContext(), sendStrategyEnum, i, z);
            }
        }
    }

    @Deprecated
    public static void setSendLogStrategy(Context context, SendStrategyEnum sendStrategyEnum, int i) {
        setSendLogStrategy(context, sendStrategyEnum, i, false);
    }

    /* renamed from: a */
    private static void m807a(Context context, boolean z) {
        if (m810a(context, "onError(...)") && !m809a(context)) {
            BDStatCore.instance().init(context);
            ExceptionAnalysis.getInstance().openExceptionAnalysis(context.getApplicationContext(), z);
        }
    }

    /* renamed from: a */
    private static void m803a(Context context, String str, String str2, int i, ExtraInfo extraInfo, Map<String, String> map) {
        if (m810a(context, "onEvent(...)") && !TextUtils.isEmpty(str)) {
            boolean a = C0994bx.m1486a((Class<?>) Application.class, "onCreate");
            if (a) {
                C0955bb.m1194c().mo11631c("[WARNING] onEvent 方法被 Application.onCreate()调用，not a good practice; 可能由于多进程反复重启等原因造成Application.onCreate() 方法多次被执行，导致启动次数高；建议埋点在统计路径触发的第一个页面中，比如APP主页面中");
            }
            if (!m809a(context)) {
                BDStatCore.instance().onEvent(context.getApplicationContext(), str, str2, i, extraInfo, C0994bx.m1485a(map), a);
            }
        }
    }

    public static void onEvent(Context context, String str, String str2, int i, Map<String, String> map) {
        m803a(context, str, str2, i, (ExtraInfo) null, map);
    }

    public static void onEvent(Context context, String str, String str2, int i) {
        m803a(context, str, str2, i, (ExtraInfo) null, (Map<String, String>) null);
    }

    /* renamed from: a */
    private static void m805a(Context context, String str, String str2, ExtraInfo extraInfo) {
        m803a(context, str, str2, 1, extraInfo, (Map<String, String>) null);
    }

    public static void onEvent(Context context, String str, String str2) {
        m805a(context, str, str2, (ExtraInfo) null);
    }

    public static void onEventStart(Context context, String str, String str2) {
        if (m810a(context, "onEventStart(...)") && !TextUtils.isEmpty(str) && !m809a(context)) {
            BDStatCore.instance().onEventStart(context.getApplicationContext(), str, str2, false);
        }
    }

    public static void onEventEnd(Context context, String str, String str2) {
        m806a(context, str, str2, (ExtraInfo) null, (Map<String, String>) null);
    }

    public static void onEventEnd(Context context, String str, String str2, Map<String, String> map) {
        m806a(context.getApplicationContext(), str, str2, (ExtraInfo) null, map);
    }

    /* renamed from: a */
    private static void m806a(Context context, String str, String str2, ExtraInfo extraInfo, Map<String, String> map) {
        if (m810a(context, "onEventEnd(...)") && !TextUtils.isEmpty(str) && !m809a(context)) {
            BDStatCore.instance().onEventEnd(context.getApplicationContext(), str, str2, extraInfo, C0994bx.m1485a(map));
        }
    }

    /* renamed from: a */
    private static void m804a(Context context, String str, String str2, long j, ExtraInfo extraInfo, Map<String, String> map) {
        Context context2 = context;
        if (!m810a(context, "onEventDuration(...)") || TextUtils.isEmpty(str)) {
            return;
        }
        if (j <= 0) {
            C0955bb.m1194c().mo11627b("[WARNING] onEventDuration duration must be greater than zero");
            return;
        }
        boolean a = C0994bx.m1486a((Class<?>) Application.class, "onCreate");
        if (a) {
            C0955bb.m1194c().mo11631c("[WARNING] onEventDuration 方法被 Application.onCreate()调用，not a good practice; 可能由于多进程反复重启等原因造成Application.onCreate() 方法多次被执行，导致启动次数高；建议埋点在统计路径触发的第一个页面中，比如APP主页面中");
        }
        if (!m809a(context)) {
            BDStatCore.instance().onEventDuration(context.getApplicationContext(), str, str2, j, extraInfo, C0994bx.m1485a(map), a);
        }
    }

    public static void onEventDuration(Context context, String str, String str2, long j, Map<String, String> map) {
        m804a(context, str, str2, j, (ExtraInfo) null, map);
    }

    public static void onEventDuration(Context context, String str, String str2, long j) {
        m804a(context, str, str2, j, (ExtraInfo) null, (Map<String, String>) null);
    }

    /* renamed from: a */
    private static boolean m810a(Context context, String str) {
        if (context != null) {
            return true;
        }
        C0955bb c = C0955bb.m1194c();
        c.mo11627b("[WARNING] " + str + ", context is null, invalid");
        return false;
    }

    public static void setAppKey(String str) {
        PrefOperate.setAppKey(str);
    }

    public static String getAppKey(Context context) {
        return PrefOperate.getAppKey(context);
    }

    @Deprecated
    public static void setAppChannel(String str) {
        PrefOperate.setAppChannel(str);
    }

    public static void setAppChannel(Context context, String str, boolean z) {
        if (context != null && !m809a(context)) {
            PrefOperate.setAppChannel(context, str, z);
            BDStatCore.instance().init(context);
        }
    }

    public static void setLogSenderDelayed(int i) {
        LogSender.instance().setLogSenderDelayed(i);
    }

    public static void setSessionTimeOut(int i) {
        BDStatCore.instance().setSessionTimeOut(i);
    }

    public static void setDebugOn(boolean z) {
        C0955bb.m1194c().mo11636a(z);
    }

    public static void setForTv(Context context, boolean z) {
        if (context != null && !m809a(context)) {
            C0982bp.m1357a().mo11685c(context, z);
            BDStatCore.instance().init(context);
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    private static void m808a(WebView webView) {
        if (Build.VERSION.SDK_INT >= 11 && Build.VERSION.SDK_INT <= 18) {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        }
    }

    public static void bindJSInterface(Context context, WebView webView) {
        bindJSInterface(context, webView, (WebViewClient) null);
    }

    @SuppressLint({"NewApi", "SetJavaScriptEnabled"})
    public static void bindJSInterface(Context context, WebView webView, WebViewClient webViewClient) {
        m801a(context, webView, webViewClient, (WebChromeClient) null, false);
    }

    @SuppressLint({"NewApi", "SetJavaScriptEnabled"})
    /* renamed from: a */
    private static void m801a(Context context, WebView webView, WebViewClient webViewClient, WebChromeClient webChromeClient, boolean z) {
        if (context == null) {
            C0955bb.m1194c().mo11631c("[WARNING] context is null, invalid");
        } else if (webView == null) {
            C0955bb.m1194c().mo11631c("[WARNING] webview is null, invalid");
        } else if (!m809a(context)) {
            m808a(webView);
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setDefaultTextEncodingName("UTF-8");
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            if (!z) {
                webView.setWebViewClient(new BaiduStatJSInterface.CustomWebViewClient(context, webViewClient, (BaiduStatJSInterface.IWebviewPageLoadCallback) null, (C0974bk) null));
            } else {
                C0974bk bkVar = new C0974bk();
                webView.addJavascriptInterface(bkVar, "WebViewInterface");
                ArrayList arrayList = new ArrayList();
                arrayList.add(new C0879ad.C0881b());
                arrayList.add(new C0936au.C0938b());
                BaiduStatJSInterface.CustomWebChromeViewClient customWebChromeViewClient = new BaiduStatJSInterface.CustomWebChromeViewClient(context, webChromeClient, arrayList, bkVar);
                webView.setWebChromeClient(customWebChromeViewClient);
                webView.setTag(-96001, customWebChromeViewClient);
            }
            BDStatCore.instance().init(context);
        }
    }

    public static String getTestDeviceId(Context context) {
        return C0991bw.m1438b(context);
    }

    public static String getSdkVersion() {
        return CooperService.instance().getMTJSDKVersion();
    }

    public static void onErised(Context context, String str, String str2, String str3) {
        if (m810a(context, "onErised(...)")) {
            if (str == null || "".equals(str)) {
                C0955bb.m1194c().mo11631c("[WARNING] AppKey is invalid");
            } else {
                BDStatCore.instance().onErised(context, str, str2, str3);
            }
        }
    }

    public static void autoTrace(Context context) {
        autoTrace(context, true, false);
    }

    public static void autoTrace(Context context, boolean z, boolean z2) {
        if (z && m810a(context, "autoTrace(...)") && !m809a(context)) {
            String appKey = CooperService.instance().getAppKey(context);
            if (TextUtils.isEmpty(appKey)) {
                C0955bb.m1194c().mo11631c("[WARNING] AppKey is invalid, auto trace will do not take effect");
                return;
            }
            C0879ad.m833a(appKey);
            C0879ad.m834a(z2);
            if (!f928b) {
                setFeedTrack(MtjConfig.FeedTrackStrategy.TRACK_ALL);
            }
            BDStatCore.instance().init(context);
        }
    }

    public static void setAttributes(View view, Map<String, String> map) {
        if (view != null) {
            view.setTag(-96000, map);
        }
    }

    public static void trackWebView(Context context, WebView webView, WebChromeClient webChromeClient) {
        m801a(context, webView, (WebViewClient) null, webChromeClient, true);
    }

    public static void setUserId(Context context, String str) {
        if (context != null && !m809a(context)) {
            CooperService.instance().setUserId(context, str);
            HashMap hashMap = new HashMap();
            hashMap.put("uid_", str);
            if (TextUtils.isEmpty(str)) {
                hashMap = null;
            }
            DataCore.instance().setPydProperty(context, C0994bx.m1485a((Map<String, String>) hashMap), "1", "0");
            BDStatCore.instance().init(context);
        }
    }

    public static void setUserProperty(Context context, Map<String, String> map) {
        if (context != null && !m809a(context)) {
            CooperService.instance().setUserProperty(context, C0994bx.m1485a(map));
            DataCore.instance().setPydProperty(context, C0994bx.m1485a(map), "1", "1");
            BDStatCore.instance().init(context);
        }
    }

    public static void setSessionProperty(Context context, Map<String, String> map) {
        if (context != null && !m809a(context)) {
            DataCore.instance().setPydProperty(context, C0994bx.m1485a(map), "2", "2");
            BDStatCore.instance().init(context);
        }
    }

    public static void setPageProperty(Context context, Map<String, String> map) {
        if (context != null && !m809a(context)) {
            DataCore.instance().setPydProperty(context, C0994bx.m1485a(map), "3", PropertyType.PAGE_PROPERTRY);
            BDStatCore.instance().init(context);
        }
    }

    public static void setAutoEventProperty(Context context, Map<String, String> map) {
        if (context != null && !m809a(context)) {
            DataCore.instance().setPydProperty(context, C0994bx.m1485a(map), "3", "3");
            BDStatCore.instance().init(context);
        }
    }

    public static void recordException(Context context, Throwable th) {
        if (context != null && th != null) {
            ExceptionAnalysis.getInstance().saveCrashInfo(context, th, false);
        }
    }

    public static void setListName(View view, String str) {
        if (view != null) {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            if (str.length() > 256) {
                str = str.substring(0, 256);
            }
            view.setTag(-97001, str);
        }
    }

    public static void setContentTitle(View view, String str) {
        if (view != null) {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            if (str.length() > 256) {
                str = str.substring(0, 256);
            }
            view.setTag(-97003, str);
        }
    }

    public static void setContentId(View view, String str) {
        if (view != null) {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            if (str.length() > 256) {
                str = str.substring(0, 256);
            }
            view.setTag(-97004, str);
        }
    }

    public static void setFeedTrack(MtjConfig.FeedTrackStrategy feedTrackStrategy) {
        C0936au.m1088a(feedTrackStrategy);
        f928b = true;
    }

    public static void enableListTrack(View view) {
        if (view != null) {
            view.setTag(-97002, true);
        }
    }

    public static void setAppVersionName(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.length() > 256) {
                str = str.substring(0, 256);
            }
            CooperService.instance().setAppVersionName(context, str);
        }
    }

    public static synchronized void setPushId(Context context, MtjConfig.PushPlatform pushPlatform, String str) {
        synchronized (StatService.class) {
            if (context != null) {
                if (pushPlatform != null) {
                    if (!m809a(context)) {
                        if (TextUtils.isEmpty(str)) {
                            str = "";
                        }
                        if (str.length() > 1024) {
                            str = str.substring(0, 1024);
                        }
                        CooperService.instance().setPushId(context, pushPlatform.value(), pushPlatform.showName(), str);
                        BDStatCore.instance().init(context);
                    }
                }
            }
        }
    }

    public static synchronized void setStartType(boolean z) {
        synchronized (StatService.class) {
            CooperService.instance().setStartType(z);
        }
    }

    public static void setCrashExtraInfo(String str) {
        ExceptionAnalysis.getInstance().setCrashExtraInfo(str);
    }

    public static void setEnableBackgroundSendLog(Context context, boolean z) {
        BDStatCore.instance().setAutoSendLog(context, z);
    }

    public static void setAuthorizedState(Context context, boolean z) {
        C0989bu.m1416a().mo11723b(z);
    }

    public static void setOaid(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (str.length() > 256) {
            str = str.substring(0, 256);
        }
        C0982bp.m1357a().mo11708n(context, str);
    }

    public static void crashEnableSendLog(boolean z) {
        ExceptionAnalysis.getInstance().setEnableSend(z);
    }

    public static void setOnAppBackgroundListener(OnAppBackgroundListener onAppBackgroundListener) {
        if (onAppBackgroundListener != null) {
            C0989bu.m1416a().mo11719a(onAppBackgroundListener);
        }
    }
}
