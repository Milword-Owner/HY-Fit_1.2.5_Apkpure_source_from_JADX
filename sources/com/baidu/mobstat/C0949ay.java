package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.webkit.WebView;
import com.baidu.mobstat.C0898ak;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.ay */
public class C0949ay {

    /* renamed from: k */
    private static final C0949ay f1181k = new C0949ay();

    /* renamed from: l */
    private static volatile boolean f1182l = true;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public Context f1183a;

    /* renamed from: b */
    private Activity f1184b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public volatile boolean f1185c;

    /* renamed from: d */
    private volatile boolean f1186d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public volatile String f1187e;

    /* renamed from: f */
    private long f1188f;

    /* renamed from: g */
    private long f1189g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public String f1190h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public PointF f1191i;

    /* renamed from: j */
    private C0958be f1192j = C0958be.m1204a();

    /* renamed from: a */
    public static C0949ay m1153a() {
        return f1181k;
    }

    private C0949ay() {
    }

    /* renamed from: a */
    public void mo11618a(String str) {
        C0947aw.m1142a().mo11612a(str);
    }

    /* renamed from: b */
    public void mo11621b(String str) {
        this.f1190h = str;
    }

    /* renamed from: b */
    public PointF mo11619b() {
        return this.f1191i;
    }

    /* renamed from: a */
    public static void m1155a(boolean z) {
        if (z) {
            C0958be.m1208b();
        }
        f1182l = z;
    }

    /* renamed from: c */
    public static boolean m1161c() {
        return f1182l;
    }

    /* renamed from: c */
    private void m1160c(Activity activity) {
        Window window;
        Window.Callback callback;
        if (activity != null && (window = activity.getWindow()) != null && (callback = window.getCallback()) != null) {
            window.setCallback(new C0898ak(callback, new C0898ak.C0899a() {
                /* renamed from: a */
                public void mo11464a(MotionEvent motionEvent) {
                    int actionMasked = motionEvent.getActionMasked();
                    if (actionMasked == 0) {
                        return;
                    }
                    if (actionMasked == 1) {
                        C0949ay.m1155a(true);
                        if (C0949ay.this.f1191i == null) {
                            PointF unused = C0949ay.this.f1191i = new PointF();
                        }
                        C0949ay.this.f1191i.set(motionEvent.getRawX(), motionEvent.getRawY());
                    } else if (actionMasked == 5) {
                    }
                }

                /* renamed from: a */
                public void mo11463a(KeyEvent keyEvent) {
                    C0913aq.m984a(keyEvent);
                }
            }));
        }
    }

    /* renamed from: a */
    public void mo11615a(Activity activity) {
        if (m1164d()) {
            m1155a(true);
            this.f1183a = activity.getApplicationContext();
            this.f1184b = activity;
            m1165e();
            m1160c(activity);
            mo11616a(activity, true);
        }
    }

    /* renamed from: a */
    public void mo11616a(Activity activity, boolean z) {
        if (!(activity instanceof IIgnoreAutoEvent)) {
            if (z) {
                this.f1192j.mo11639a(activity, false, (JSONObject) null, false);
            } else {
                this.f1192j.mo11638a(activity, false);
            }
        }
    }

    /* renamed from: d */
    private void m1163d(Activity activity) {
        Window window;
        if (activity != null && (window = activity.getWindow()) != null) {
            window.setCallback(m1152a(window.getCallback()));
        }
    }

    /* renamed from: a */
    private Window.Callback m1152a(Window.Callback callback) {
        while (callback != null && (callback instanceof C0898ak)) {
            callback = ((C0898ak) callback).mo11466a();
        }
        return callback;
    }

    /* renamed from: b */
    public void mo11620b(Activity activity) {
        if (m1164d()) {
            m1163d(this.f1184b);
            this.f1184b = null;
            mo11616a(activity, false);
        }
    }

    /* renamed from: a */
    public void mo11617a(WebView webView, String str, C0974bk bkVar) {
        if (TextUtils.isEmpty(this.f1187e)) {
            this.f1187e = C0980bn.m1345a(this.f1183a, C0883af.f942b);
        }
        m1157b(webView, this.f1187e, bkVar);
    }

    /* renamed from: d */
    private boolean m1164d() {
        return !TextUtils.isEmpty(this.f1190h);
    }

    /* renamed from: e */
    private void m1165e() {
        if (C0991bw.m1472s(this.f1183a) && !this.f1185c) {
            if (!this.f1186d) {
                this.f1187e = C0980bn.m1345a(this.f1183a, C0883af.f942b);
                this.f1186d = true;
            }
            if (this.f1188f == 0) {
                this.f1188f = C0982bp.m1357a().mo11707n(this.f1183a);
                this.f1189g = C0982bp.m1357a().mo11709o(this.f1183a);
            }
            if ((this.f1186d && TextUtils.isEmpty(this.f1187e)) || System.currentTimeMillis() - this.f1188f > this.f1189g) {
                m1166f();
            }
        }
    }

    /* renamed from: f */
    private void m1166f() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                if (!C0949ay.this.f1185c) {
                    boolean a = C0962bf.m1225a(C0949ay.this.f1183a, C0949ay.this.f1190h, 1, false);
                    boolean unused = C0949ay.this.f1185c = true;
                    if (a) {
                        C0949ay ayVar = C0949ay.this;
                        String unused2 = ayVar.f1187e = C0980bn.m1345a(ayVar.f1183a, C0883af.f942b);
                    }
                }
            }
        });
        thread.setName("downloadThread");
        thread.start();
    }

    /* renamed from: b */
    private void m1157b(WebView webView, String str, C0974bk bkVar) {
        if (bkVar != null) {
            bkVar.mo11660a(this.f1184b, webView, str, (JSONObject) null, false);
        }
    }
}
