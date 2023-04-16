package com.baidu.mobstat;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import com.baidu.mobstat.C0898ak;

/* renamed from: com.baidu.mobstat.aj */
public class C0894aj {

    /* renamed from: a */
    private static volatile boolean f959a = true;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public C0897a f960b;

    /* renamed from: c */
    private Activity f961c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public Handler f962d = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 100 && C0894aj.this.f960b != null) {
                C0894aj.this.f960b.mo11465a();
            }
        }
    };

    /* renamed from: com.baidu.mobstat.aj$a */
    public interface C0897a {
        /* renamed from: a */
        void mo11465a();
    }

    public C0894aj(C0897a aVar) {
        this.f960b = aVar;
    }

    /* renamed from: a */
    public static void m866a(boolean z) {
        if (z) {
            C0906am.m940a();
        }
        f959a = z;
    }

    /* renamed from: a */
    public static boolean m867a() {
        return f959a;
    }

    /* renamed from: a */
    public void mo11460a(Activity activity) {
        if (activity != null) {
            this.f961c = activity;
            m869b(activity);
        }
    }

    /* renamed from: b */
    public void mo11461b() {
        m870c(this.f961c);
        this.f961c = null;
    }

    /* renamed from: b */
    private void m869b(Activity activity) {
        m871d(activity);
    }

    /* renamed from: c */
    private void m870c(Activity activity) {
        Window window;
        if (activity != null && (window = activity.getWindow()) != null) {
            window.setCallback(m864a(window.getCallback()));
        }
    }

    /* renamed from: a */
    private Window.Callback m864a(Window.Callback callback) {
        while (callback != null && (callback instanceof C0898ak)) {
            callback = ((C0898ak) callback).mo11466a();
        }
        return callback;
    }

    /* renamed from: d */
    private void m871d(Activity activity) {
        Window.Callback callback;
        Window window = activity.getWindow();
        if (window != null && (callback = window.getCallback()) != null) {
            window.setCallback(new C0898ak(callback, new C0898ak.C0899a() {
                /* renamed from: a */
                public void mo11463a(KeyEvent keyEvent) {
                }

                /* renamed from: a */
                public void mo11464a(MotionEvent motionEvent) {
                    C0894aj.m866a(true);
                    int actionMasked = motionEvent.getActionMasked();
                    if (actionMasked != 0 && actionMasked != 1) {
                        if (actionMasked == 5) {
                            int pointerCount = motionEvent.getPointerCount();
                            if (pointerCount == 3 && motionEvent.getEventTime() - motionEvent.getDownTime() <= 50) {
                                C0894aj.this.f962d.sendEmptyMessageDelayed(100, 2500);
                            } else if (pointerCount > 3) {
                                C0894aj.this.f962d.removeMessages(100);
                            }
                        } else if (actionMasked == 6 && motionEvent.getEventTime() - motionEvent.getDownTime() < 2500) {
                            C0894aj.this.f962d.removeMessages(100);
                        }
                    }
                }
            }));
        }
    }
}
