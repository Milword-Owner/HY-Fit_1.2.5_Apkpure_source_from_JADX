package p005cn.sharesdk.framework;

import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.clj.fastble.BleManager;
import com.mob.MobSDK;
import com.mob.tools.log.NLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import java.util.ArrayList;
import java.util.HashMap;
import p005cn.sharesdk.framework.p006a.C0692a;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.framework.b */
/* compiled from: CheckAppKeyRequestUrl */
public class C0707b {

    /* renamed from: a */
    private static String f206a = "";

    /* renamed from: b */
    private static volatile C0707b f207b;

    /* renamed from: c */
    private DeviceHelper f208c = DeviceHelper.getInstance(MobSDK.getContext());

    /* renamed from: d */
    private NetworkHelper f209d = new NetworkHelper();

    /* renamed from: e */
    private String f210e = MobSDK.checkRequestUrl("api.share.mob.com");

    private C0707b() {
    }

    /* renamed from: a */
    public static C0707b m163a() {
        synchronized (C0707b.class) {
            if (f207b == null) {
                synchronized (C0707b.class) {
                    if (f207b == null) {
                        f207b = new C0707b();
                    }
                }
            }
        }
        return f207b;
    }

    /* renamed from: b */
    public void mo10560b() {
        try {
            ArrayList arrayList = new ArrayList();
            String appkey = MobSDK.getAppkey();
            if (!TextUtils.isEmpty(appkey)) {
                arrayList.add(new KVPair("appkey", appkey));
                arrayList.add(new KVPair(Config.DEVICE_PART, this.f208c.getDeviceKey()));
                arrayList.add(new KVPair("plat", String.valueOf(this.f208c.getPlatformCode())));
                arrayList.add(new KVPair("apppkg", this.f208c.getPackageName()));
                arrayList.add(new KVPair("appver", String.valueOf(this.f208c.getAppVersion())));
                arrayList.add(new KVPair("sdkver", String.valueOf(ShareSDK.SDK_VERSION_CODE)));
                arrayList.add(new KVPair("networktype", this.f208c.getDetailNetworkTypeForStatic()));
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new KVPair("User-Identity", C0692a.m119a()));
                NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
                networkTimeOut.readTimout = BleManager.DEFAULT_SCAN_TIME;
                networkTimeOut.connectionTimeout = BleManager.DEFAULT_SCAN_TIME;
                HashMap fromJson = new Hashon().fromJson(this.f209d.httpPost(m164c(), (ArrayList<KVPair<String>>) arrayList, (KVPair<String>) null, (ArrayList<KVPair<String>>) arrayList2, networkTimeOut));
                if (!fromJson.containsKey("error")) {
                    C0690a.f171b = appkey;
                } else if (String.valueOf(fromJson.get("error")).contains("'appkey' is illegal")) {
                    C0690a.f170a = true;
                }
            }
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29768d("updateServerConfig " + th, new Object[0]);
        }
    }

    /* renamed from: c */
    private String m164c() {
        return this.f210e + "/conf5";
    }
}
