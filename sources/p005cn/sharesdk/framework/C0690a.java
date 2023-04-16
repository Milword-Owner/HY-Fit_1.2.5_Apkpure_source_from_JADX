package p005cn.sharesdk.framework;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.tools.log.NLog;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.framework.a */
/* compiled from: CheckAppKey */
public class C0690a {

    /* renamed from: a */
    public static volatile boolean f170a = false;

    /* renamed from: b */
    public static String f171b;

    /* renamed from: a */
    public static boolean m118a() {
        String appkey = MobSDK.getAppkey();
        if (f170a || TextUtils.isEmpty(appkey)) {
            return false;
        }
        if (TextUtils.isEmpty(f171b)) {
            SSDKLog.m645b().mo29768d("CheckAppKeyAsynchronously verify the appkey", new Object[0]);
            new Thread() {
                public void run() {
                    try {
                        C0707b.m163a().mo10560b();
                    } catch (Throwable th) {
                        NLog b = SSDKLog.m645b();
                        b.mo29768d("CheckAppKeyAsyn verify the appkey catch " + th, new Object[0]);
                    }
                }
            }.start();
            return true;
        }
        SSDKLog.m645b().mo29768d("CheckAppKeyDetermine whether successAppKey is equal to mobsdk.getappkey", new Object[0]);
        return appkey.equals(f171b);
    }
}
