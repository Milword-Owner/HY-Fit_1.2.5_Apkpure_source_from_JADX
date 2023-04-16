package p005cn.sharesdk.framework.utils;

import com.mob.commons.logcollector.DefaultLogsCollector;
import com.mob.tools.log.NLog;
import p005cn.sharesdk.framework.ShareSDK;

/* renamed from: cn.sharesdk.framework.utils.SSDKLog */
public class SSDKLog {

    /* renamed from: a */
    private static NLog f589a;

    /* renamed from: a */
    public static NLog m644a() {
        f589a = NLog.getInstance(ShareSDK.SDK_TAG);
        DefaultLogsCollector.get().addSDK(ShareSDK.SDK_TAG, ShareSDK.SDK_VERSION_CODE);
        return f589a;
    }

    /* renamed from: b */
    public static NLog m645b() {
        if (f589a == null) {
            m644a();
        }
        return f589a;
    }
}
