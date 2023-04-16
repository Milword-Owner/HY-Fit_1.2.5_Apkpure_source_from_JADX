package com.mob.commons.utag;

import com.baidu.mobstat.Config;
import com.blankj.utilcode.constant.CacheConstants;
import com.mob.MobCommunicator;
import com.mob.MobSDK;
import com.mob.commons.C2262b;
import com.mob.commons.C2308i;
import com.mob.commons.C2310j;
import com.mob.commons.ForbThrowable;
import com.mob.commons.MobProduct;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.tools.MobLog;
import com.mob.tools.RxMob;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.DeviceHelper;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public final class TagRequester implements PublicMemberKeeper {

    /* renamed from: a */
    private static HashMap<String, Object> f2175a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public static DeviceHelper f2176b = DeviceHelper.getInstance(MobSDK.getContext());
    /* access modifiers changed from: private */

    /* renamed from: c */
    public static boolean f2177c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public UserTagsResponse f2178d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public UserTagError f2179e;

    public interface UserTagsResponse {
        void onResponse(Map<String, Object> map);
    }

    TagRequester() {
    }

    public TagRequester whenSuccess(UserTagsResponse userTagsResponse) {
        this.f2178d = userTagsResponse;
        return this;
    }

    public TagRequester whenError(UserTagError userTagError) {
        this.f2179e = userTagError;
        return this;
    }

    public synchronized void request() {
        RxMob.create(new RxMob.QuickSubscribe<HashMap<String, Object>>() {
            /* access modifiers changed from: protected */
            public void doNext(RxMob.Subscriber<HashMap<String, Object>> subscriber) throws Throwable {
                if (!C2262b.m2259Z()) {
                    TagRequester.m2634d();
                    subscriber.onNext(TagRequester.m2635e());
                    return;
                }
                throw new ForbThrowable();
            }
        }).subscribeOnNewThreadAndObserveOnUIThread(new RxMob.Subscriber<HashMap<String, Object>>() {
            /* renamed from: a */
            public void onNext(HashMap<String, Object> hashMap) {
                if (TagRequester.this.f2178d != null) {
                    TagRequester.this.f2178d.onResponse(hashMap);
                }
            }

            public void onError(Throwable th) {
                if (TagRequester.this.f2179e != null) {
                    TagRequester.this.f2179e.onError(th);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public static synchronized void m2634d() {
        boolean z;
        synchronized (TagRequester.class) {
            f2175a = C2308i.m2550k();
            if (f2175a != null && f2175a.containsKey("defHost") && f2175a.containsKey("defPort") && f2175a.containsKey("defSSLPort")) {
                if (f2175a.containsKey("tagExpire")) {
                    Calendar instance = Calendar.getInstance();
                    instance.setTimeInMillis(C2308i.m2547j());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String format = simpleDateFormat.format(instance.getTime());
                    instance.setTimeInMillis(System.currentTimeMillis());
                    z = !format.equals(simpleDateFormat.format(instance.getTime()));
                    if (z && !f2177c) {
                        f2177c = true;
                        new Thread() {
                            public void run() {
                                try {
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("duid", DeviceAuthorizer.authorize((MobProduct) null));
                                    hashMap.put("mac", TagRequester.f2176b.getMacAddress());
                                    hashMap.put("imei", TagRequester.f2176b.getIMEI());
                                    hashMap.put("serialno", TagRequester.f2176b.getSerialno());
                                    hashMap.put("model", TagRequester.f2176b.getModel());
                                    hashMap.put("appkey", MobSDK.getAppkey());
                                    hashMap.put("apppkg", TagRequester.f2176b.getPackageName());
                                    hashMap.put("appver", TagRequester.f2176b.getAppVersionName());
                                    hashMap.put("plat", 1);
                                    C2308i.m2511a((HashMap<String, Object>) TagRequester.m2632b(hashMap, C2310j.m2574c("http://api.utag.mob.com/conf")));
                                } catch (Throwable th) {
                                    MobLog.getInstance().mo29787w(th);
                                }
                                boolean unused = TagRequester.f2177c = false;
                            }
                        }.start();
                    }
                }
            }
            C2308i.m2511a((HashMap<String, Object>) null);
            f2175a = new HashMap<>();
            f2175a.put("defHost", "api.utag.mob.com");
            f2175a.put("defPort", 80);
            f2175a.put("defSSLPort", 443);
            f2175a.put("tagExpire", Integer.valueOf(CacheConstants.DAY));
            z = true;
            f2177c = true;
            new Thread() {
                public void run() {
                    try {
                        HashMap hashMap = new HashMap();
                        hashMap.put("duid", DeviceAuthorizer.authorize((MobProduct) null));
                        hashMap.put("mac", TagRequester.f2176b.getMacAddress());
                        hashMap.put("imei", TagRequester.f2176b.getIMEI());
                        hashMap.put("serialno", TagRequester.f2176b.getSerialno());
                        hashMap.put("model", TagRequester.f2176b.getModel());
                        hashMap.put("appkey", MobSDK.getAppkey());
                        hashMap.put("apppkg", TagRequester.f2176b.getPackageName());
                        hashMap.put("appver", TagRequester.f2176b.getAppVersionName());
                        hashMap.put("plat", 1);
                        C2308i.m2511a((HashMap<String, Object>) TagRequester.m2632b(hashMap, C2310j.m2574c("http://api.utag.mob.com/conf")));
                    } catch (Throwable th) {
                        MobLog.getInstance().mo29787w(th);
                    }
                    boolean unused = TagRequester.f2177c = false;
                }
            }.start();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public static synchronized HashMap<String, Object> m2635e() throws Throwable {
        HashMap<String, Object> l;
        String str;
        synchronized (TagRequester.class) {
            l = C2308i.m2553l();
            if (l == null || l.isEmpty()) {
                Object obj = f2175a.get("defPort");
                if (obj == null || !(obj instanceof Integer)) {
                    str = null;
                } else {
                    int intValue = ((Integer) obj).intValue();
                    if (intValue <= 0) {
                        str = "";
                    } else {
                        str = Config.TRACE_TODAY_VISIT_SPLIT + intValue;
                    }
                }
                String c = C2310j.m2574c("http://" + f2175a.get("defHost") + str + "/utag");
                HashMap hashMap = new HashMap();
                hashMap.put("duid", DeviceAuthorizer.authorize((MobProduct) null));
                hashMap.put("mac", f2176b.getMacAddress());
                hashMap.put("imei", f2176b.getIMEI());
                hashMap.put("serialno", f2176b.getSerialno());
                hashMap.put("model", f2176b.getModel());
                hashMap.put("appkey", MobSDK.getAppkey());
                hashMap.put("apppkg", f2176b.getPackageName());
                hashMap.put("appver", f2176b.getAppVersionName());
                hashMap.put("plat", 1);
                l = m2632b(hashMap, c);
                int i = 0;
                try {
                    i = Integer.parseInt(String.valueOf(f2175a.get("tagExpire")));
                } catch (Throwable th) {
                    MobLog.getInstance().mo29787w(th);
                }
                C2308i.m2512a(l, i);
            }
        }
        return l;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static HashMap<String, Object> m2632b(HashMap<String, Object> hashMap, String str) throws Throwable {
        return (HashMap) new MobCommunicator(1024, "e3e28dce5fe8fc1bb56a25964219d5dc2976edb171b99b1103c2c4f89ad0b66fb58669fe69eb0b5d11e8be990b0715b4de2b4e5a5dcce121f47f18063d5d99f9", "256f461cc45979b52264ac022ff1353ea5f8140d35686ffdae2faee09db2006c3b43c2bb74ce6f4c51698db6384c1c0ceca958208d65c7ed345a04ea6349ca39601818c3d5500565ba49ed49c0f4014b06980d17fc069c95d30092d0cfdaddf783ea96c5f8bdc42b6765d71a5d12192ef74646b41d92f1caeba3123e71938d39").requestSynchronized(hashMap, str, false);
    }
}
