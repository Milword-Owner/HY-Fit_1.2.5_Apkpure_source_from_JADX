package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.baidu.mobstat.C0970bj;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.be */
public class C0958be {

    /* renamed from: a */
    private static volatile int f1202a;

    /* renamed from: k */
    private static final C0958be f1203k = new C0958be();

    /* renamed from: b */
    private WeakReference<Activity> f1204b;

    /* renamed from: c */
    private int f1205c;

    /* renamed from: d */
    private boolean f1206d;

    /* renamed from: e */
    private JSONObject f1207e;

    /* renamed from: f */
    private boolean f1208f;

    /* renamed from: g */
    private final Handler f1209g = new Handler(Looper.getMainLooper());

    /* renamed from: h */
    private final Handler f1210h;

    /* renamed from: i */
    private C0959a f1211i;

    /* renamed from: j */
    private C0957bd f1212j = new C0957bd();

    /* renamed from: c */
    static /* synthetic */ int m1211c() {
        int i = f1202a + 1;
        f1202a = i;
        return i;
    }

    /* renamed from: a */
    public static C0958be m1204a() {
        return f1203k;
    }

    private C0958be() {
        HandlerThread handlerThread = new HandlerThread("visitorThread");
        handlerThread.start();
        this.f1210h = new Handler(handlerThread.getLooper());
    }

    /* renamed from: b */
    public static void m1208b() {
        f1202a = 0;
    }

    /* renamed from: a */
    public void mo11639a(Activity activity, boolean z, JSONObject jSONObject, boolean z2) {
        C0959a aVar;
        C0913aq.m983a(activity, !z);
        if (!this.f1206d) {
            this.f1206d = z2;
        }
        if (z) {
            this.f1208f = z;
            this.f1207e = jSONObject;
        }
        if (!m1207a(activity, 1)) {
            if (!(this.f1204b == null || (aVar = this.f1211i) == null)) {
                aVar.mo11640a();
            }
            this.f1204b = new WeakReference<>(activity);
            this.f1205c = 1;
            Activity activity2 = activity;
            this.f1211i = new C0959a(activity2, C0968bi.m1241a(activity), new C0970bj.C0971a(1, this.f1204b, this.f1212j), this.f1209g, this.f1210h, this.f1207e, this.f1206d, true, this.f1208f);
        }
    }

    /* renamed from: a */
    public void mo11638a(Activity activity, boolean z) {
        C0913aq.m988b(activity, !z);
        if (!m1207a(activity, 2)) {
            this.f1204b = new WeakReference<>(activity);
            this.f1205c = 2;
            C0959a aVar = this.f1211i;
            if (aVar != null) {
                aVar.mo11640a();
            }
        }
    }

    /* renamed from: a */
    private boolean m1207a(Activity activity, int i) {
        WeakReference<Activity> weakReference = this.f1204b;
        return weakReference != null && ((Activity) weakReference.get()) == activity && this.f1205c == i;
    }

    /* renamed from: com.baidu.mobstat.be$a */
    static class C0959a implements ViewTreeObserver.OnGlobalLayoutListener, Runnable {

        /* renamed from: a */
        private volatile boolean f1213a;

        /* renamed from: b */
        private boolean f1214b;

        /* renamed from: c */
        private final WeakReference<View> f1215c;

        /* renamed from: d */
        private final C0970bj f1216d;

        /* renamed from: e */
        private final Handler f1217e;

        /* renamed from: f */
        private final Handler f1218f;

        /* renamed from: g */
        private JSONObject f1219g;

        /* renamed from: h */
        private WeakReference<Activity> f1220h;

        /* renamed from: i */
        private boolean f1221i;

        /* renamed from: j */
        private boolean f1222j;
        /* access modifiers changed from: private */

        /* renamed from: k */
        public boolean f1223k;

        /* renamed from: l */
        private Runnable f1224l = null;

        public C0959a(Activity activity, View view, C0970bj bjVar, Handler handler, Handler handler2, JSONObject jSONObject, boolean z, boolean z2, boolean z3) {
            this.f1220h = new WeakReference<>(activity);
            this.f1219g = jSONObject;
            this.f1216d = bjVar;
            this.f1215c = new WeakReference<>(view);
            this.f1217e = handler;
            this.f1218f = handler2;
            this.f1214b = true;
            this.f1213a = false;
            this.f1221i = z;
            this.f1222j = z2;
            this.f1223k = z3;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(this);
            }
            run();
        }

        public void onGlobalLayout() {
            run();
        }

        public void run() {
            if (this.f1214b) {
                if (((View) this.f1215c.get()) == null || this.f1213a) {
                    m1217b();
                    return;
                }
                if (C0956bc.m1198c().mo11630b() && this.f1223k) {
                    C0956bc.m1198c().mo11624a("onGlobalLayout");
                }
                if (C0963bg.m1227c().mo11630b()) {
                    C0963bg.m1227c().mo11624a("onGlobalLayout");
                }
                if (C0879ad.m836b()) {
                    if (C0949ay.m1161c()) {
                        Activity activity = (Activity) this.f1220h.get();
                        if (activity != null) {
                            C0958be.m1210b(activity, this.f1221i, this.f1223k);
                            m1215a(this.f1220h, this.f1219g, this.f1216d, this.f1218f, this.f1222j);
                        }
                    } else {
                        if (C0956bc.m1198c().mo11630b() && this.f1223k) {
                            C0956bc.m1198c().mo11624a("no touch, skip onGlobalLayout");
                        }
                        if (C0963bg.m1227c().mo11630b()) {
                            C0963bg.m1227c().mo11624a("no touch, skip onGlobalLayout");
                        }
                    }
                }
                this.f1217e.removeCallbacks(this);
            }
        }

        /* renamed from: a */
        public void mo11640a() {
            if (!this.f1213a) {
                this.f1213a = true;
                this.f1217e.post(this);
            }
        }

        @SuppressLint({"NewApi"})
        /* renamed from: b */
        private void m1217b() {
            if (this.f1214b) {
                View view = (View) this.f1215c.get();
                if (view != null) {
                    ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                    if (viewTreeObserver.isAlive()) {
                        if (Build.VERSION.SDK_INT < 16) {
                            viewTreeObserver.removeGlobalOnLayoutListener(this);
                        } else {
                            viewTreeObserver.removeOnGlobalLayoutListener(this);
                        }
                    }
                }
                m1214a(this.f1216d, this.f1218f);
            }
            this.f1214b = false;
        }

        /* renamed from: a */
        private void m1215a(WeakReference<Activity> weakReference, JSONObject jSONObject, C0970bj bjVar, Handler handler, boolean z) {
            if (bjVar != null && handler != null) {
                final WeakReference<Activity> weakReference2 = weakReference;
                final boolean z2 = z;
                final C0970bj bjVar2 = bjVar;
                final JSONObject jSONObject2 = jSONObject;
                C09601 r0 = new Runnable() {
                    public void run() {
                        if (!C0949ay.m1161c()) {
                            if (C0956bc.m1198c().mo11630b() && C0959a.this.f1223k) {
                                C0956bc.m1198c().mo11624a("no touch, skip doViewVisit");
                            }
                            if (C0963bg.m1227c().mo11630b()) {
                                C0963bg.m1227c().mo11624a("no touch, skip doViewVisit");
                                return;
                            }
                            return;
                        }
                        if (C0958be.m1211c() >= 3) {
                            C0949ay.m1155a(false);
                        }
                        Activity activity = (Activity) weakReference2.get();
                        if (activity != null) {
                            C0913aq.m989c(activity, z2);
                            bjVar2.mo11655a(activity, jSONObject2, z2);
                        }
                    }
                };
                Runnable runnable = this.f1224l;
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                }
                this.f1224l = r0;
                handler.postDelayed(r0, 500);
            }
        }

        /* renamed from: a */
        private void m1214a(final C0970bj bjVar, Handler handler) {
            if (bjVar != null && handler != null) {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        bjVar.mo11654a();
                    }
                }, 500);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static void m1210b(Activity activity, boolean z, boolean z2) {
        if (z) {
            m1209b(activity, z2);
        }
    }

    /* renamed from: b */
    private static void m1209b(Activity activity, boolean z) {
        m1205a(activity, C0968bi.m1241a(activity), z);
    }

    /* renamed from: a */
    private static void m1205a(Activity activity, View view, boolean z) {
        if (view == null || C0968bi.m1276c(activity, view)) {
            return;
        }
        if (view instanceof WebView) {
            WebView webView = (WebView) view;
            if (webView.getTag(-96001) == null) {
                if (C0956bc.m1198c().mo11630b() && z) {
                    C0956bc c = C0956bc.m1198c();
                    c.mo11624a("webview auto set " + activity.getClass().getName());
                }
                if (C0963bg.m1227c().mo11630b()) {
                    C0963bg c2 = C0963bg.m1227c();
                    c2.mo11624a("webview auto set " + activity.getClass().getName());
                }
                StatService.trackWebView(activity.getApplicationContext(), webView, (WebChromeClient) null);
            }
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                m1205a(activity, viewGroup.getChildAt(i), z);
            }
        }
    }
}
