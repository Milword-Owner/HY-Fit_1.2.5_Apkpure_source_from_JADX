package p005cn.sharesdk.framework.p007b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.huayu.tzc.utils.DateUtil;
import com.mob.MobSDK;
import com.mob.commons.SHARESDK;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import java.io.File;
import java.util.Calendar;
import p005cn.sharesdk.framework.ShareSDK;
import p005cn.sharesdk.framework.authorize.C0701f;
import p005cn.sharesdk.framework.p007b.p008a.C0713e;
import p005cn.sharesdk.framework.p007b.p009b.C0717c;
import p005cn.sharesdk.framework.p007b.p009b.C0719e;
import p005cn.sharesdk.framework.p007b.p009b.C0722g;
import p005cn.sharesdk.framework.utils.C0807e;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.framework.b.d */
/* compiled from: StatisticsLogger */
public class C0724d extends C0807e {

    /* renamed from: b */
    private static C0724d f277b;

    /* renamed from: c */
    private DeviceHelper f278c = DeviceHelper.getInstance(MobSDK.getContext());
    /* access modifiers changed from: private */

    /* renamed from: d */
    public C0708a f279d = C0708a.m166a();

    /* renamed from: e */
    private Handler f280e;

    /* renamed from: f */
    private boolean f281f;

    /* renamed from: g */
    private long f282g;

    /* renamed from: h */
    private boolean f283h;

    /* renamed from: i */
    private File f284i = new File(MobSDK.getContext().getFilesDir(), ".statistics");

    /* renamed from: j */
    private FileLocker f285j = new FileLocker();

    /* renamed from: a */
    public static synchronized C0724d m310a() {
        C0724d dVar;
        synchronized (C0724d.class) {
            if (f277b == null) {
                f277b = new C0724d();
            }
            dVar = f277b;
        }
        return dVar;
    }

    private C0724d() {
        if (!this.f284i.exists()) {
            try {
                this.f284i.createNewFile();
            } catch (Exception e) {
                SSDKLog.m645b().mo29769d(e);
            }
        }
    }

    /* renamed from: a */
    public void mo10639a(Handler handler) {
        this.f280e = handler;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo10640a(Message message) {
        if (!this.f281f) {
            this.f281f = true;
            try {
                this.f285j.setLockFile(this.f284i.getAbsolutePath());
                if (this.f285j.lock(false)) {
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                C0724d.this.f279d.mo10564a(DeviceAuthorizer.authorize(new SHARESDK()));
                            } catch (Exception e) {
                                SSDKLog.m645b().mo29769d(e);
                            }
                        }
                    }).start();
                    this.f279d.mo10567b();
                    this.f279d.mo10569c();
                    ShareSDK.setEnableAuthTag(true);
                    m314d();
                    this.f613a.sendEmptyMessageDelayed(4, DateUtil.HOUR_MILL_SECONDS);
                    this.f613a.sendEmptyMessage(1);
                    this.f613a.sendEmptyMessage(2);
                }
            } catch (Throwable th) {
                SSDKLog.m645b().mo29769d(th);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo10644c(Message message) {
        if (this.f281f) {
            C0719e eVar = new C0719e();
            eVar.f250a = System.currentTimeMillis() - this.f282g;
            mo10641a((C0717c) eVar);
            this.f281f = false;
            try {
                this.f280e.sendEmptyMessage(1);
            } catch (Throwable th) {
                SSDKLog.m645b().mo29769d(th);
            }
            f277b = null;
            this.f613a.getLooper().quit();
        }
    }

    /* renamed from: a */
    public void mo10641a(final C0717c cVar) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            new Thread() {
                public void run() {
                    C0724d.this.mo10643b(cVar);
                }
            }.start();
        } else {
            mo10643b(cVar);
        }
    }

    /* renamed from: b */
    public void mo10643b(C0717c cVar) {
        try {
            if (MobSDK.isMob() && this.f281f) {
                m312c(cVar);
                if (cVar.mo10620g()) {
                    Message message = new Message();
                    message.what = 3;
                    message.obj = cVar;
                    this.f613a.sendMessage(message);
                    return;
                }
                NLog b = SSDKLog.m645b();
                b.mo29768d("Drop event: " + cVar.toString(), new Object[0]);
            }
        } catch (Throwable th) {
            NLog b2 = SSDKLog.m645b();
            b2.mo29768d("logStart " + th, new Object[0]);
        }
    }

    /* renamed from: c */
    private void m312c(C0717c cVar) {
        cVar.f236f = this.f278c.getDeviceKey();
        cVar.f237g = this.f278c.getPackageName();
        cVar.f238h = this.f278c.getAppVersion();
        cVar.f239i = String.valueOf(ShareSDK.SDK_VERSION_CODE);
        cVar.f240j = this.f278c.getPlatformCode();
        cVar.f241k = this.f278c.getDetailNetworkTypeForStatic();
        if (TextUtils.isEmpty(MobSDK.getAppkey())) {
            SSDKLog.m645b().mo29785w("ShareSDKCore", "Your appKey of ShareSDK is null , this will cause its data won't be count!");
        } else if (!"cn.sharesdk.demo".equals(cVar.f237g) && ("api20".equals(MobSDK.getAppkey()) || "androidv1101".equals(MobSDK.getAppkey()))) {
            SSDKLog.m645b().mo29785w("ShareSDKCore", "Your app is using the appkey of ShareSDK Demo, this will cause its data won't be count!");
        }
        cVar.f242l = this.f278c.getDeviceData();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo10642b(Message message) {
        int i = message.what;
        if (i == 1) {
            m311b();
            try {
                this.f613a.sendEmptyMessageDelayed(1, Config.BPLUS_DELAY_TIME);
            } catch (Throwable th) {
                SSDKLog.m645b().mo29769d(th);
            }
        } else if (i == 2) {
            try {
                this.f279d.mo10570d();
            } catch (Throwable th2) {
                SSDKLog.m645b().mo29769d(th2);
            }
        } else if (i != 3) {
            if (i == 4) {
                long longValue = C0713e.m196a().mo10607j().longValue();
                Calendar instance = Calendar.getInstance();
                instance.setTimeInMillis(longValue);
                int i2 = instance.get(1);
                int i3 = instance.get(2);
                int i4 = instance.get(5);
                instance.setTimeInMillis(System.currentTimeMillis());
                int i5 = instance.get(1);
                int i6 = instance.get(2);
                int i7 = instance.get(5);
                if (!(i2 == i5 && i3 == i6 && i4 == i7)) {
                    this.f279d.mo10569c();
                }
                this.f613a.sendEmptyMessageDelayed(4, DateUtil.HOUR_MILL_SECONDS);
            }
        } else if (message.obj != null) {
            m315d((C0717c) message.obj);
            this.f613a.removeMessages(2);
            this.f613a.sendEmptyMessageDelayed(2, 2000);
        }
    }

    /* renamed from: d */
    private void m315d(C0717c cVar) {
        try {
            this.f279d.mo10563a(cVar);
            cVar.mo10621h();
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
            SSDKLog.m645b().mo29768d(cVar.toString(), new Object[0]);
        }
    }

    /* renamed from: b */
    private void m311b() {
        boolean c = m313c();
        if (c) {
            if (!this.f283h) {
                this.f283h = c;
                this.f282g = System.currentTimeMillis();
                mo10641a((C0717c) new C0722g());
            }
        } else if (this.f283h) {
            this.f283h = c;
            long currentTimeMillis = System.currentTimeMillis() - this.f282g;
            C0719e eVar = new C0719e();
            eVar.f250a = currentTimeMillis;
            mo10641a((C0717c) eVar);
        }
    }

    /* renamed from: c */
    private boolean m313c() {
        return DeviceHelper.getInstance(MobSDK.getContext()).amIOnForeground();
    }

    /* renamed from: d */
    private void m314d() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    C0701f.m152c().mo10551d();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }).start();
    }
}
