package com.baidu.mobstat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.view.View;
import com.baidu.mobstat.C0964bh;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.bj */
public abstract class C0970bj implements C0964bh.C0965a {

    /* renamed from: a */
    private C0964bh f1250a;

    /* renamed from: com.baidu.mobstat.bj$b */
    public interface C0973b {
        /* renamed from: a */
        void mo11637a(View view, boolean z, Activity activity);
    }

    /* renamed from: a */
    public abstract void mo11654a();

    @TargetApi(14)
    /* renamed from: com.baidu.mobstat.bj$a */
    public static class C0971a extends C0970bj {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public WeakReference<Activity> f1251a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public C0973b f1252b;

        /* renamed from: c */
        private final WeakHashMap<View, C0972a> f1253c = new WeakHashMap<>();

        public C0971a(int i, WeakReference<Activity> weakReference, C0973b bVar) {
            this.f1251a = weakReference;
            this.f1252b = bVar;
        }

        /* renamed from: com.baidu.mobstat.bj$a$a */
        class C0972a extends View.AccessibilityDelegate {

            /* renamed from: b */
            private View.AccessibilityDelegate f1255b;

            /* renamed from: c */
            private View f1256c;

            /* renamed from: d */
            private volatile boolean f1257d;

            /* renamed from: a */
            public void mo11658a(boolean z) {
                this.f1257d = z;
            }

            public C0972a(WeakReference<Activity> weakReference, View view, String str, View.AccessibilityDelegate accessibilityDelegate, boolean z) {
                this.f1255b = accessibilityDelegate;
                WeakReference unused = C0971a.this.f1251a = weakReference;
                this.f1256c = view;
                this.f1257d = z;
            }

            /* renamed from: a */
            public View.AccessibilityDelegate mo11657a() {
                return this.f1255b;
            }

            public void sendAccessibilityEvent(View view, int i) {
                Activity activity;
                if (view == this.f1256c && i == 1) {
                    if (C0956bc.m1198c().mo11630b() && this.f1257d) {
                        C0956bc c = C0956bc.m1198c();
                        c.mo11624a("watch view  OnEvent:" + view.getClass().getName());
                    }
                    if (C0963bg.m1227c().mo11630b()) {
                        C0963bg c2 = C0963bg.m1227c();
                        c2.mo11624a("watch view  OnEvent:" + view.getClass().getName());
                    }
                    if (!(C0971a.this.f1251a == null || (activity = (Activity) C0971a.this.f1251a.get()) == null)) {
                        C0971a.this.f1252b.mo11637a(view, this.f1257d, activity);
                    }
                }
                View.AccessibilityDelegate accessibilityDelegate = this.f1255b;
                if (accessibilityDelegate != null) {
                    accessibilityDelegate.sendAccessibilityEvent(view, i);
                } else {
                    super.sendAccessibilityEvent(view, i);
                }
            }
        }

        /* renamed from: a */
        public void mo11656a(WeakReference<Activity> weakReference, View view, String str, boolean z) {
            View.AccessibilityDelegate a = m1308a(view);
            if (!(a instanceof C0972a)) {
                C0972a aVar = new C0972a(weakReference, view, str, a, z);
                view.setAccessibilityDelegate(aVar);
                this.f1253c.put(view, aVar);
                return;
            }
            ((C0972a) a).mo11658a(z);
        }

        /* renamed from: a */
        private View.AccessibilityDelegate m1308a(View view) {
            try {
                return (View.AccessibilityDelegate) view.getClass().getMethod("getAccessibilityDelegate", new Class[0]).invoke(view, new Object[0]);
            } catch (Exception unused) {
                return null;
            }
        }

        /* renamed from: a */
        public void mo11647a(View view, boolean z) {
            mo11656a(this.f1251a, view, C0968bi.m1245a(view), z);
        }

        /* renamed from: a */
        public void mo11654a() {
            WeakHashMap<View, C0972a> weakHashMap = this.f1253c;
            if (weakHashMap != null) {
                for (Map.Entry next : weakHashMap.entrySet()) {
                    ((View) next.getKey()).setAccessibilityDelegate(((C0972a) next.getValue()).mo11657a());
                }
                this.f1253c.clear();
            }
        }
    }

    /* renamed from: a */
    public void mo11655a(Activity activity, JSONObject jSONObject, boolean z) {
        if (this.f1250a == null) {
            this.f1250a = new C0964bh(activity, this, z);
            this.f1250a.mo11646a(jSONObject);
        }
        this.f1250a.mo11645a(activity);
    }
}
