package p005cn.sharesdk.framework;

import com.mob.MobSDK;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.p007b.p009b.C0720f;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.framework.Service */
public abstract class Service {
    /* access modifiers changed from: protected */
    public abstract int getServiceVersionInt();

    public abstract String getServiceVersionName();

    public void onBind() {
    }

    public void onUnbind() {
    }

    public String getDeviceKey() {
        return DeviceHelper.getInstance(MobSDK.getContext()).getDeviceKey();
    }

    /* renamed from: cn.sharesdk.framework.Service$ServiceEvent */
    public static abstract class ServiceEvent {
        private static final int PLATFORM = 1;
        protected Service service;

        public ServiceEvent(Service service2) {
            this.service = service2;
        }

        /* access modifiers changed from: protected */
        public HashMap<String, Object> toMap() {
            HashMap<String, Object> hashMap = new HashMap<>();
            DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
            hashMap.put("deviceid", instance.getDeviceKey());
            hashMap.put("appkey", MobSDK.getAppkey());
            hashMap.put("apppkg", instance.getPackageName());
            hashMap.put("appver", Integer.valueOf(instance.getAppVersion()));
            hashMap.put("sdkver", Integer.valueOf(this.service.getServiceVersionInt()));
            hashMap.put("plat", 1);
            hashMap.put("networktype", instance.getDetailNetworkTypeForStatic());
            hashMap.put("deviceData", instance.getDeviceDataNotAES());
            return hashMap;
        }

        public final String toString() {
            return new Hashon().fromHashMap(toMap());
        }

        /* access modifiers changed from: protected */
        public HashMap<String, Object> filterShareContent(int i, Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
            Platform platform;
            try {
                platform = ShareSDK.getPlatform(ShareSDK.platformIdToName(i));
            } catch (Throwable th) {
                NLog b = SSDKLog.m645b();
                b.mo29768d("ShareSDK Service filterShareContent catch: " + th, new Object[0]);
                platform = null;
            }
            if (platform == null) {
                return null;
            }
            C0720f.C0721a filterShareContent = platform.filterShareContent(shareParams, hashMap);
            HashMap<String, Object> hashMap2 = new HashMap<>();
            hashMap2.put("shareID", filterShareContent.f259a);
            hashMap2.put("shareContent", new Hashon().fromJson(filterShareContent.toString()));
            SSDKLog.m645b().mo29775i("filterShareContent ==>>%s", hashMap2);
            return hashMap2;
        }
    }
}
