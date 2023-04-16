package p005cn.sharesdk.framework.authorize;

import android.app.Activity;

/* renamed from: cn.sharesdk.framework.authorize.b */
/* compiled from: AuthorizeParams */
public class C0697b {

    /* renamed from: a */
    private static volatile C0697b f182a;

    /* renamed from: b */
    private Activity f183b;

    /* renamed from: c */
    private boolean f184c;

    /* renamed from: a */
    public boolean mo10541a() {
        return this.f184c;
    }

    /* renamed from: a */
    public void mo10540a(boolean z) {
        this.f184c = z;
    }

    /* renamed from: b */
    public Activity mo10542b() {
        return this.f183b;
    }

    /* renamed from: a */
    public void mo10539a(Activity activity) {
        this.f183b = activity;
    }

    /* renamed from: c */
    public static C0697b m141c() {
        synchronized (C0697b.class) {
            if (f182a == null) {
                synchronized (C0697b.class) {
                    if (f182a == null) {
                        f182a = new C0697b();
                    }
                }
            }
        }
        return f182a;
    }
}
