package p005cn.sharesdk.framework.utils.QRCodeUtil;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import p005cn.sharesdk.framework.Service;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.QRCodeService */
public class QRCodeService extends Service {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static QRCodeListener f406a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public C0792j f407b = null;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public C0775a f408c;

    /* access modifiers changed from: protected */
    public int getServiceVersionInt() {
        return 1;
    }

    public String getServiceVersionName() {
        return "QRCodeService";
    }

    public void setSize(int i, int i2) {
        m500b();
        this.f407b.mo10879a(i, i2);
    }

    public void setBackground(int i) {
        m500b();
        this.f407b.mo10878a(i);
    }

    public void setBackgroundBitmap(Bitmap bitmap) {
        m500b();
        this.f407b.mo10881a(bitmap, false);
    }

    public void setBackgroundBitmap(Bitmap bitmap, boolean z) {
        m500b();
        this.f407b.mo10881a(bitmap, z);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        m500b();
        this.f407b.mo10882a(drawable, false);
    }

    public void setBackgroundDrawable(Drawable drawable, boolean z) {
        m500b();
        this.f407b.mo10882a(drawable, z);
    }

    public void setBackgroundPath(String str) {
        m500b();
        this.f407b.mo10884a(str, false);
    }

    public void setBackgroundPath(String str, boolean z) {
        m500b();
        this.f407b.mo10884a(str, z);
    }

    public void setBackgroundUrl(String str) {
        m500b();
        this.f407b.mo10890b(str, false);
    }

    public void setBackgroundUrl(String str, boolean z) {
        m500b();
        this.f407b.mo10890b(str, z);
    }

    public void setLogo(int i) {
        m500b();
        this.f407b.mo10886b(i);
    }

    public void setLogoBitmap(Bitmap bitmap) {
        m500b();
        this.f407b.mo10887b(bitmap, false);
    }

    public void setLogoBitmap(Bitmap bitmap, boolean z) {
        m500b();
        this.f407b.mo10887b(bitmap, z);
    }

    public void setLogoDrawable(Drawable drawable) {
        m500b();
        this.f407b.mo10888b(drawable, false);
    }

    public void setLogoDrawable(Drawable drawable, boolean z) {
        m500b();
        this.f407b.mo10888b(drawable, z);
    }

    public void setLogoPath(String str) {
        m500b();
        this.f407b.mo10892c(str, false);
    }

    public void setLogoPath(String str, boolean z) {
        m500b();
        this.f407b.mo10892c(str, z);
    }

    public void setLogoUrl(String str) {
        m500b();
        this.f407b.mo10893d(str, false);
    }

    public void setLogoUrl(String str, boolean z) {
        m500b();
        this.f407b.mo10890b(str, z);
    }

    public void setPoints(int i, int i2, int i3, int i4) {
        m500b();
        this.f407b.mo10880a(i, i2, i3, i4);
    }

    public void setCodeColor(int i) {
        m500b();
        this.f407b.mo10891c(i);
    }

    public void setUrl(String str) {
        m500b();
        this.f407b.mo10883a(str);
    }

    public void setContent(String str) {
        m500b();
        this.f407b.mo10889b(str);
    }

    public void setListener(QRCodeListener qRCodeListener) {
        f406a = qRCodeListener;
    }

    public void onBind() {
        m500b();
    }

    /* renamed from: b */
    private void m500b() {
        if (this.f407b == null) {
            this.f407b = new C0792j();
        }
        if (this.f408c == null) {
            this.f408c = new C0775a(this.f407b);
        }
    }

    public Bitmap generate() throws Throwable {
        m500b();
        return this.f407b.mo10885b();
    }

    public void generateAsync(QRCodeListener qRCodeListener) {
        f406a = qRCodeListener;
        generateAsync();
    }

    public void generateAsync() {
        m500b();
        if (f406a == null) {
            SSDKLog.m645b().mo29768d("listener can not be null when you generate bitmap in Async method", new Object[0]);
        } else {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        QRCodeService.this.f407b.mo10885b();
                        QRCodeService.this.f408c.sendEmptyMessage(1);
                    } catch (Throwable th) {
                        QRCodeService.f406a.onError(th);
                    }
                }
            }).start();
        }
    }

    /* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.QRCodeService$a */
    private static class C0775a extends Handler {

        /* renamed from: a */
        private final WeakReference<C0792j> f410a;

        public C0775a(C0792j jVar) {
            super(Looper.getMainLooper());
            this.f410a = new WeakReference<>(jVar);
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                Bitmap a = ((C0792j) this.f410a.get()).mo10877a();
                if (a == null) {
                    QRCodeService.f406a.onError(new Exception("bitmap gernerate error!!!"));
                    return;
                }
                QRCodeService.f406a.onSuccess(a);
            }
        }
    }
}
