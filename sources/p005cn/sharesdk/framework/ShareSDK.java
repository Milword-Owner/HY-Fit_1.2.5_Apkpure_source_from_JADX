package p005cn.sharesdk.framework;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.mob.MobSDK;
import com.mob.commons.ForbThrowable;
import com.mob.commons.dialog.PolicyThrowable;
import com.mob.tools.log.NLog;
import java.util.HashMap;
import p005cn.sharesdk.framework.loopshare.LoopSharePasswordListener;
import p005cn.sharesdk.framework.loopshare.LoopShareResultListener;
import p005cn.sharesdk.framework.loopshare.MoblinkActionListener;
import p005cn.sharesdk.framework.loopshare.watermark.ReadQrImageListener;
import p005cn.sharesdk.framework.loopshare.watermark.WaterMarkListener;
import p005cn.sharesdk.framework.utils.SSDKLog;
import p005cn.sharesdk.onekeyshare.OnekeyShare;

/* renamed from: cn.sharesdk.framework.ShareSDK */
public class ShareSDK {
    public static final String SDK_TAG = "SHARESDK";
    public static final int SDK_VERSION_CODE;
    public static final String SDK_VERSION_NAME = "3.8.5";
    public static final String SHARESDK_MOBLINK_RESTORE = "sharesdk_moblink_restore";
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static C0753i f160a = null;

    /* renamed from: b */
    private static boolean f161b = true;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public static String f162c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public static HashMap<String, Object> f163d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public static int f164e;

    /* renamed from: g */
    static /* synthetic */ int m113g() {
        int i = f164e;
        f164e = i + 1;
        return i;
    }

    static {
        int i = 0;
        for (String parseInt : SDK_VERSION_NAME.split("\\.")) {
            i = (i * 100) + Integer.parseInt(parseInt);
        }
        SDK_VERSION_CODE = i;
        try {
            m116j();
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK static main catch ", new Object[0]);
        }
    }

    /* renamed from: i */
    private static boolean m115i() throws Throwable {
        if (!MobSDK.isForb()) {
            int isAuth = MobSDK.isAuth();
            if (isAuth == 1 || isAuth == 2) {
                return true;
            }
            throw new PolicyThrowable();
        }
        throw new ForbThrowable();
    }

    /* access modifiers changed from: private */
    /* renamed from: j */
    public static synchronized void m116j() throws Throwable {
        synchronized (ShareSDK.class) {
            m115i();
            if (f160a == null) {
                C0753i iVar = new C0753i();
                iVar.mo10741e();
                f160a = iVar;
            }
        }
    }

    public static void registerService(Class<? extends Service> cls) {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10719a(cls);
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK  registerService catch ", new Object[0]);
        }
    }

    public static void unregisterService(Class<? extends Service> cls) {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10731b(cls);
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK  unregisterService catch ", new Object[0]);
        }
    }

    public static <T extends Service> T getService(Class<T> cls) throws Throwable {
        m116j();
        C0753i iVar = f160a;
        if (iVar != null) {
            return iVar.mo10734c(cls);
        }
        return null;
    }

    public static void registerPlatform(Class<? extends CustomPlatform> cls) throws Throwable {
        m116j();
        C0753i iVar = f160a;
        if (iVar != null) {
            iVar.mo10740d(cls);
        }
    }

    public static void unregisterPlatform(Class<? extends CustomPlatform> cls) throws Throwable {
        m116j();
        C0753i iVar = f160a;
        if (iVar != null) {
            iVar.mo10742e(cls);
        }
    }

    public static void setConnTimeout(int i) {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10713a(i);
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK setConnTimeout catch", new Object[0]);
        }
    }

    public static void setReadTimeout(int i) {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10730b(i);
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK setReadTimeout catch", new Object[0]);
        }
    }

    public static void removeCookieOnAuthorize(boolean z) {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10737c(z);
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK removeCookieOnAuthorize catch ", new Object[0]);
        }
    }

    public static void deleteCache() {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10747j();
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK deleteCache catch ", new Object[0]);
        }
    }

    public static Platform[] getPlatformList() {
        try {
            m116j();
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK getPlatformList catch ", new Object[0]);
        }
        C0753i iVar = f160a;
        if (iVar != null) {
            return iVar.mo10743f();
        }
        return null;
    }

    public static Platform getPlatform(String str) {
        try {
            m116j();
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK ensureInit getPlatform catch", new Object[0]);
        }
        C0753i iVar = f160a;
        if (iVar != null) {
            return iVar.mo10709a(str);
        }
        return null;
    }

    public static void logDemoEvent(int i, Platform platform) {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10715a(i, platform);
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK logDemoEvent catch ", new Object[0]);
        }
    }

    public static void logApiEvent(String str, int i) {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10720a(str, i);
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK logApiEvent catch ", new Object[0]);
        }
    }

    public static void setPlatformDevInfo(String str, HashMap<String, Object> hashMap) {
        try {
            f162c = str;
            f163d = hashMap;
            if (MobSDK.isForb() || MobSDK.isAuth() != 1) {
                m117k();
                return;
            }
            m116j();
            if (f160a != null) {
                f160a.mo10723a(str, hashMap);
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK setPlatformDevInfo catch ", new Object[0]);
        }
    }

    /* renamed from: k */
    private static void m117k() {
        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();
                final C06871 r0 = new Handler(Looper.myLooper()) {
                    public void handleMessage(Message message) {
                        super.handleMessage(message);
                        if (message != null && message.what == 3) {
                            try {
                                ShareSDK.m116j();
                                if (ShareSDK.f160a != null) {
                                    ShareSDK.f160a.mo10723a(ShareSDK.f162c, (HashMap<String, Object>) ShareSDK.f163d);
                                }
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                    }
                };
                r0.post(new Runnable() {
                    public void run() {
                        int isAuth = MobSDK.isAuth();
                        if (isAuth == 0) {
                            ShareSDK.m113g();
                            if (ShareSDK.f164e == 90) {
                                r0.removeCallbacks(this);
                                return;
                            }
                            Log.e(OnekeyShare.SHARESDK_TAG, "Privacy Agreement is not agree, Please agree to the privacy agreement first ");
                            r0.postDelayed(this, 500);
                        } else if (isAuth != 1 && isAuth != 2) {
                            r0.removeCallbacks(this);
                        } else if (!MobSDK.isForb()) {
                            r0.removeCallbacks(this);
                            Message obtain = Message.obtain();
                            obtain.what = 3;
                            r0.sendMessage(obtain);
                        }
                    }
                });
                Looper.loop();
            }
        }).start();
    }

    public static String platformIdToName(int i) {
        try {
            m116j();
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29768d("ShareSDK platformIdToName catch: " + th, new Object[0]);
        }
        C0753i iVar = f160a;
        if (iVar != null) {
            return iVar.mo10735c(i);
        }
        return null;
    }

    public static int platformNameToId(String str) {
        try {
            m116j();
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK platformNameToId catch ", new Object[0]);
        }
        C0753i iVar = f160a;
        if (iVar != null) {
            return iVar.mo10728b(str);
        }
        return -1;
    }

    public static boolean isRemoveCookieOnAuthorize() {
        try {
            m116j();
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29768d("ShareSDK isRemoveCookieOnAuthorize catch: " + th, new Object[0]);
        }
        C0753i iVar = f160a;
        if (iVar != null) {
            return iVar.mo10744g();
        }
        return false;
    }

    public static void closeDebug() {
        f161b = false;
    }

    public static boolean isDebug() {
        return f161b;
    }

    public static void setEnableAuthTag(boolean z) {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10732b(z);
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK setEnableAuthTag catch", new Object[0]);
        }
    }

    public static void setActivity(Activity activity) {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10716a(activity);
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK setActivity is catch ", new Object[0]);
        }
    }

    public static Activity getAuthActivity() {
        try {
            m116j();
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK getAuthActivity catch ", new Object[0]);
        }
        C0753i iVar = f160a;
        if (iVar != null) {
            return iVar.mo10707a();
        }
        return null;
    }

    public static void setFBInstagram(boolean z) {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10726a(z);
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK setFBInstagram catch ", new Object[0]);
        }
    }

    public static boolean isFBInstagram() {
        try {
            m116j();
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29768d("ShareSDK isFBInstagram catch: " + th, new Object[0]);
        }
        C0753i iVar = f160a;
        if (iVar != null) {
            return iVar.mo10733b();
        }
        return false;
    }

    public static boolean getEnableAuthTag() {
        try {
            m116j();
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK getEnableAuthTag catch", new Object[0]);
        }
        C0753i iVar = f160a;
        if (iVar != null) {
            return iVar.mo10738c();
        }
        return false;
    }

    public static void getFirstQrImage(Context context, ReadQrImageListener readQrImageListener) {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10717a(context, readQrImageListener);
            }
        } catch (Throwable th) {
            if (readQrImageListener != null) {
                readQrImageListener.onFailed(th, -1);
            }
        }
    }

    public static void makeVideoWaterMark(String str, String str2, String str3, String str4, WaterMarkListener waterMarkListener) {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10722a(str, str2, str3, str4, waterMarkListener);
            }
        } catch (Throwable th) {
            if (waterMarkListener != null) {
                waterMarkListener.onFailed(th.getMessage(), -1);
            }
        }
    }

    public static Bitmap getQRCodeBitmap(String str, int i, int i2) throws Throwable {
        m116j();
        C0753i iVar = f160a;
        if (iVar != null) {
            return iVar.mo10708a(str, i, i2);
        }
        return null;
    }

    public static void mobLinkGetMobID(HashMap<String, Object> hashMap, MoblinkActionListener moblinkActionListener) {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10724a(hashMap, moblinkActionListener);
            }
        } catch (Throwable th) {
            if (moblinkActionListener != null) {
                moblinkActionListener.onError(th);
            }
        }
    }

    public static void prepareLoopShare(LoopShareResultListener loopShareResultListener) {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10718a(loopShareResultListener);
            }
        } catch (Throwable th) {
            if (loopShareResultListener != null) {
                loopShareResultListener.onError(th);
            }
        }
    }

    public static HashMap<String, Object> getCustomDataFromLoopShare() {
        try {
            m116j();
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK getCustomDataFromLoopShare catch ", new Object[0]);
        }
        C0753i iVar = f160a;
        if (iVar != null) {
            return iVar.mo10739d();
        }
        return new HashMap<>();
    }

    public static void preparePassWord(HashMap<String, Object> hashMap, String str, LoopSharePasswordListener loopSharePasswordListener) {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10725a(hashMap, str, loopSharePasswordListener);
            }
        } catch (Throwable th) {
            if (loopSharePasswordListener != null) {
                loopSharePasswordListener.onError(th);
            }
        }
    }

    public static void readPassWord(boolean z, LoopSharePasswordListener loopSharePasswordListener) {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10727a(z, loopSharePasswordListener);
            }
        } catch (Throwable th) {
            if (loopSharePasswordListener != null) {
                loopSharePasswordListener.onError(th);
            }
        }
    }

    /* renamed from: a */
    static void m106a(String str, String str2) {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10721a(str, str2);
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK copyDevinfo ", new Object[0]);
        }
    }

    /* renamed from: a */
    static void m105a(int i, int i2) {
        try {
            m116j();
            if (f160a != null) {
                f160a.mo10714a(i, i2);
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK copyNetworkDevinfo catch ", new Object[0]);
        }
    }

    public static String getDevinfo(String str, String str2) {
        try {
            m116j();
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK getDevinfo catch ", new Object[0]);
        }
        C0753i iVar = f160a;
        if (iVar != null) {
            return iVar.mo10729b(str, str2);
        }
        return null;
    }

    /* renamed from: a */
    static String m101a(int i, String str) {
        try {
            m116j();
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK getNetworkDevinfo catch ", new Object[0]);
        }
        C0753i iVar = f160a;
        if (iVar != null) {
            return iVar.mo10710a(i, str);
        }
        return null;
    }

    /* renamed from: a */
    static boolean m107a() {
        try {
            m116j();
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK isNetworkDevinfoRequested is catch ", new Object[0]);
        }
        C0753i iVar = f160a;
        if (iVar != null) {
            return iVar.mo10745h();
        }
        return false;
    }

    /* renamed from: b */
    static boolean m108b() throws Throwable {
        m116j();
        C0753i iVar = f160a;
        if (iVar != null) {
            return iVar.mo10746i();
        }
        return false;
    }

    /* renamed from: a */
    static String m104a(String str, boolean z, int i, String str2) {
        try {
            m116j();
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK getShortLink catch ", new Object[0]);
        }
        C0753i iVar = f160a;
        if (iVar != null) {
            return iVar.mo10712a(str, z, i, str2);
        }
        return null;
    }

    /* renamed from: a */
    static String m103a(String str) {
        try {
            m116j();
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK uploadImageToFileServer catch: ", new Object[0]);
        }
        C0753i iVar = f160a;
        if (iVar != null) {
            return iVar.mo10736c(str);
        }
        return null;
    }

    /* renamed from: a */
    static String m102a(Bitmap bitmap) {
        try {
            m116j();
        } catch (Throwable th) {
            SSDKLog.m645b().mo29770d(th, "ShareSDK uploadImageToFileServer catch ", new Object[0]);
        }
        C0753i iVar = f160a;
        if (iVar != null) {
            return iVar.mo10711a(bitmap);
        }
        return null;
    }
}
