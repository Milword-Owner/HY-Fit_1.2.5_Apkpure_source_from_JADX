package com.mob;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.mob.commons.C2262b;
import com.mob.commons.C2310j;
import com.mob.commons.MobProduct;
import com.mob.commons.MobProductCollector;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.UIHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public final class MobUser implements PublicMemberKeeper {

    /* renamed from: a */
    private static final String f1711a = C2310j.m2574c("http://api.u.mob.com");

    /* renamed from: b */
    private static MobCommunicator f1712b = new MobCommunicator(1024, "009cbd92ccef123be840deec0c6ed0547194c1e471d11b6f375e56038458fb18833e5bab2e1206b261495d7e2d1d9e5aa859e6d4b671a8ca5d78efede48e291a3f", "1dfd1d615cb891ce9a76f42d036af7fce5f8b8efaa11b2f42590ecc4ea4cff28f5f6b0726aeb76254ab5b02a58c1d5b486c39d9da1a58fa6ba2f22196493b3a4cbc283dcf749bf63679ee24d185de70c8dfe05605886c9b53e9f569082eabdf98c4fb0dcf07eb9bb3e647903489ff0b5d933bd004af5be4a1022fdda41f347f1");

    /* renamed from: c */
    private static Handler f1713c = MobHandlerThread.newHandler(Config.MODEL, (Handler.Callback) new Handler.Callback() {
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                Object[] objArr = (Object[]) message.obj;
                MobUser.m1938c((String) objArr[0], (String) objArr[1], (String) objArr[2], (HashMap) objArr[3], (String) objArr[4]);
            } else if (i == 2) {
                MobUser.m1937c((OnUserGotListener) message.obj);
            } else if (i == 3) {
                MobUser.m1941e();
            }
            return false;
        }
    });

    /* renamed from: d */
    private static String f1714d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public static MobUser f1715e = new MobUser();

    /* renamed from: f */
    private boolean f1716f;

    /* renamed from: g */
    private String f1717g;

    /* renamed from: h */
    private String f1718h;

    /* renamed from: i */
    private String f1719i;

    /* renamed from: j */
    private HashMap<String, Object> f1720j;

    /* renamed from: k */
    private String f1721k;

    /* renamed from: l */
    private String f1722l;

    /* renamed from: m */
    private long f1723m;

    /* renamed from: n */
    private HashSet<UserWatcher> f1724n = new HashSet<>();

    public interface OnUserGotListener {
        void onUserGot(MobUser mobUser);
    }

    public interface UserWatcher {
        void onUserStateChange(MobUser mobUser);
    }

    /* renamed from: a */
    static void m1930a(String str, String str2, String str3, HashMap<String, Object> hashMap, String str4) {
        Message message = new Message();
        message.what = 1;
        message.obj = new Object[]{str, str2, str3, hashMap, str4};
        f1713c.sendMessage(message);
    }

    /* renamed from: a */
    static void m1928a(OnUserGotListener onUserGotListener) {
        Message message = new Message();
        message.what = 2;
        message.obj = onUserGotListener;
        f1713c.sendMessage(message);
    }

    /* renamed from: a */
    static void m1927a() {
        f1713c.sendEmptyMessage(3);
    }

    /* renamed from: d */
    private static String m1939d() {
        if (f1714d == null) {
            ArrayList<MobProduct> products = MobProductCollector.getProducts();
            f1714d = DeviceAuthorizer.authorize(products.isEmpty() ? null : products.get(0));
        }
        return f1714d;
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public static void m1938c(String str, String str2, String str3, HashMap<String, Object> hashMap, String str4) {
        MobUser mobUser = f1715e;
        if (mobUser.f1722l == null || !mobUser.m1942f() || !ResHelper.isEqual(str, f1715e.f1717g)) {
            m1940d(str, str2, str3, hashMap, str4);
        } else {
            m1931a(str2, str3, hashMap);
        }
    }

    /* renamed from: d */
    private static void m1940d(String str, String str2, String str3, HashMap<String, Object> hashMap, String str4) {
        if (f1715e.f1722l != null) {
            m1941e();
        }
        HashMap hashMap2 = new HashMap();
        ArrayList<MobProduct> products = MobProductCollector.getProducts();
        ArrayList arrayList = new ArrayList();
        Iterator<MobProduct> it = products.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getProductTag());
        }
        hashMap2.put("sdks", arrayList);
        if (!TextUtils.isEmpty(str)) {
            hashMap2.put("appUserId", str);
        }
        hashMap2.put("appkey", MobSDK.getAppkey());
        String str5 = "";
        hashMap2.put("nickname", TextUtils.isEmpty(str2) ? str5 : str2);
        if (!TextUtils.isEmpty(str3)) {
            str5 = str3;
        }
        hashMap2.put("avatar", str5);
        hashMap2.put("appUserMap", hashMap == null ? new HashMap<>() : hashMap);
        hashMap2.put("duid", m1939d());
        if (!TextUtils.isEmpty(str4)) {
            hashMap2.put("sign", str4);
        }
        try {
            if (!C2262b.m2259Z()) {
                MobCommunicator mobCommunicator = f1712b;
                HashMap hashMap3 = (HashMap) mobCommunicator.requestSynchronized((HashMap<String, Object>) hashMap2, f1711a + "/login", false);
                long parseLong = Long.parseLong(String.valueOf(hashMap3.get("loginExpireAt")));
                C2262b.m2260a();
                f1715e.f1717g = str;
                f1715e.f1716f = TextUtils.isEmpty(str);
                f1715e.f1718h = str2;
                f1715e.f1719i = str3;
                f1715e.f1720j = hashMap;
                f1715e.f1721k = str4;
                f1715e.f1722l = (String) hashMap3.get("mobUserId");
                f1715e.f1723m = parseLong;
            }
            Iterator<UserWatcher> it2 = f1715e.f1724n.iterator();
            while (it2.hasNext()) {
                it2.next().onUserStateChange(f1715e);
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    /* renamed from: a */
    private static void m1931a(String str, String str2, HashMap<String, Object> hashMap) {
        MobUser mobUser = f1715e;
        if (mobUser.f1722l == null) {
            mobUser.f1718h = str;
            mobUser.f1719i = str2;
            mobUser.f1720j = hashMap;
        } else if (!mobUser.m1942f()) {
            MobUser mobUser2 = f1715e;
            m1940d(mobUser2.f1717g, str, str2, hashMap, mobUser2.f1721k);
        } else {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("mobUserId", f1715e.f1722l);
            String str3 = "";
            hashMap2.put("nickname", TextUtils.isEmpty(str) ? str3 : str);
            if (!TextUtils.isEmpty(str2)) {
                str3 = str2;
            }
            hashMap2.put("avatar", str3);
            hashMap2.put("appUserMap", hashMap != null ? new HashMap<>() : hashMap);
            try {
                if (!C2262b.m2259Z()) {
                    MobCommunicator mobCommunicator = f1712b;
                    mobCommunicator.requestSynchronized((HashMap<String, Object>) hashMap2, f1711a + "/modify", false);
                    f1715e.f1718h = str;
                    f1715e.f1719i = str2;
                    f1715e.f1720j = hashMap;
                }
                Iterator<UserWatcher> it = f1715e.f1724n.iterator();
                while (it.hasNext()) {
                    it.next().onUserStateChange(f1715e);
                }
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public static void m1937c(final OnUserGotListener onUserGotListener) {
        MobUser mobUser = f1715e;
        if (mobUser.f1722l == null || !mobUser.m1942f()) {
            MobUser mobUser2 = f1715e;
            m1940d(mobUser2.f1717g, mobUser2.f1718h, mobUser2.f1719i, mobUser2.f1720j, mobUser2.f1721k);
        }
        if (onUserGotListener != null) {
            UIHandler.sendEmptyMessage(0, new Handler.Callback() {
                public boolean handleMessage(Message message) {
                    onUserGotListener.onUserGot(MobUser.f1715e);
                    return false;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public static void m1941e() {
        boolean z = false;
        if (f1715e.f1722l != null && !C2262b.m2259Z()) {
            HashMap hashMap = new HashMap();
            hashMap.put("mobUserId", f1715e.f1722l);
            try {
                MobCommunicator mobCommunicator = f1712b;
                mobCommunicator.requestSynchronized((HashMap<String, Object>) hashMap, f1711a + "/logout", false);
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
            }
        }
        MobUser mobUser = f1715e;
        if (!(mobUser.f1722l == null && mobUser.f1717g == null && mobUser.f1718h == null && mobUser.f1719i == null && mobUser.f1720j == null && mobUser.f1721k == null && mobUser.f1716f && mobUser.f1723m == 0)) {
            z = true;
        }
        MobUser mobUser2 = f1715e;
        mobUser2.f1722l = null;
        mobUser2.f1717g = null;
        mobUser2.f1718h = null;
        mobUser2.f1719i = null;
        mobUser2.f1720j = null;
        mobUser2.f1721k = null;
        mobUser2.f1716f = true;
        mobUser2.f1723m = 0;
        if (z) {
            Iterator<UserWatcher> it = mobUser2.f1724n.iterator();
            while (it.hasNext()) {
                it.next().onUserStateChange(f1715e);
            }
        }
    }

    /* renamed from: a */
    static HashMap<String, String> m1926a(String[] strArr) {
        try {
            if (C2262b.m2259Z()) {
                return null;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("appUserIds", strArr);
            hashMap.put("appkey", MobSDK.getAppkey());
            MobCommunicator mobCommunicator = f1712b;
            return (HashMap) mobCommunicator.requestSynchronized((HashMap<String, Object>) hashMap, f1711a + "/exchange", false);
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    private MobUser() {
    }

    /* renamed from: f */
    private boolean m1942f() {
        return C2262b.m2260a() < this.f1723m;
    }

    public boolean isAnonymous() {
        return this.f1716f;
    }

    public String getMobUserId() {
        return this.f1722l;
    }

    public String getId() {
        return this.f1717g;
    }

    public String getNickName() {
        return this.f1718h;
    }

    public String getAvatar() {
        return this.f1719i;
    }

    public HashMap<String, Object> getExtraInfo() {
        return this.f1720j;
    }

    /* renamed from: a */
    static void m1929a(UserWatcher userWatcher) {
        synchronized (f1715e.f1724n) {
            f1715e.f1724n.add(userWatcher);
        }
    }

    /* renamed from: b */
    static void m1934b(UserWatcher userWatcher) {
        synchronized (f1715e.f1724n) {
            f1715e.f1724n.remove(userWatcher);
        }
    }
}
