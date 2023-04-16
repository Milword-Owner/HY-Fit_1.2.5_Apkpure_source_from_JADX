package p005cn.sharesdk.framework;

import android.text.TextUtils;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.authorize.C0701f;
import p005cn.sharesdk.framework.p007b.C0724d;
import p005cn.sharesdk.framework.p007b.p009b.C0716b;
import p005cn.sharesdk.framework.p007b.p009b.C0717c;
import p005cn.sharesdk.framework.p007b.p009b.C0720f;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.framework.d */
/* compiled from: InnerPlatformActionListener */
public class C0730d implements PlatformActionListener {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public PlatformActionListener f293a;

    /* renamed from: b */
    private HashMap<Platform, Platform.ShareParams> f294b = new HashMap<>();

    /* renamed from: c */
    private int f295c;

    C0730d() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10652a(PlatformActionListener platformActionListener) {
        this.f293a = platformActionListener;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public PlatformActionListener mo10649a() {
        return this.f293a;
    }

    /* renamed from: a */
    public void mo10651a(Platform platform, Platform.ShareParams shareParams) {
        this.f294b.put(platform, shareParams);
    }

    public void onError(Platform platform, int i, Throwable th) {
        PlatformActionListener platformActionListener = this.f293a;
        if (platformActionListener != null) {
            platformActionListener.onError(platform, i, th);
            this.f293a = null;
            this.f295c = 0;
        }
    }

    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if (platform instanceof CustomPlatform) {
            PlatformActionListener platformActionListener = this.f293a;
            if (platformActionListener != null) {
                platformActionListener.onComplete(platform, i, hashMap);
                this.f293a = null;
                this.f295c = 0;
            }
        } else if (i == 1) {
            m329a(platform, i, hashMap);
        } else if (i != 9) {
            PlatformActionListener platformActionListener2 = this.f293a;
            if (platformActionListener2 != null) {
                platformActionListener2.onComplete(platform, i, hashMap);
                if (!"Wechat".equals(platform.getName())) {
                    int i2 = this.f295c;
                    if (i2 == 0 || i2 == i) {
                        this.f293a = null;
                        this.f295c = 0;
                    }
                }
            }
        } else {
            m332b(platform, i, hashMap);
        }
    }

    public void onCancel(Platform platform, int i) {
        PlatformActionListener platformActionListener = this.f293a;
        if (platformActionListener != null) {
            platformActionListener.onCancel(platform, i);
            this.f293a = null;
            this.f295c = 0;
        }
    }

    /* renamed from: b */
    private void m331b() {
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

    /* renamed from: a */
    private void m329a(Platform platform, final int i, final HashMap<String, Object> hashMap) {
        if (C0701f.m152c().mo10550b() == null) {
            m331b();
        }
        final PlatformActionListener platformActionListener = this.f293a;
        this.f293a = new PlatformActionListener() {
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                PlatformActionListener unused = C0730d.this.f293a = platformActionListener;
                if (C0730d.this.f293a != null) {
                    try {
                        if (ShareSDK.getEnableAuthTag()) {
                            String fromHashMap = new Hashon().fromHashMap(C0701f.m152c().mo10550b());
                            if (!TextUtils.isEmpty(fromHashMap)) {
                                platform.getDb().put("userTags", fromHashMap);
                            }
                        }
                    } catch (Exception unused2) {
                    }
                    C0730d.this.f293a.onComplete(platform, i, hashMap);
                }
                C0716b bVar = new C0716b();
                bVar.f231a = platform.getPlatformId();
                bVar.f232b = "TencentWeibo".equals(platform.getName()) ? platform.getDb().get("name") : platform.getDb().getUserId();
                bVar.f233c = new Hashon().fromHashMap(hashMap);
                bVar.f234d = C0730d.this.m326a(platform);
                C0724d a = C0724d.m310a();
                if (a != null) {
                    a.mo10641a((C0717c) bVar);
                }
            }

            public void onError(Platform platform, int i, Throwable th) {
                SSDKLog.m645b().mo29787w(th);
                PlatformActionListener unused = C0730d.this.f293a = platformActionListener;
                if (C0730d.this.f293a != null) {
                    C0730d.this.f293a.onComplete(platform, i, hashMap);
                }
            }

            public void onCancel(Platform platform, int i) {
                PlatformActionListener unused = C0730d.this.f293a = platformActionListener;
                if (C0730d.this.f293a != null) {
                    C0730d.this.f293a.onComplete(platform, i, hashMap);
                }
            }
        };
        platform.showUser((String) null);
    }

    /* renamed from: b */
    private void m332b(Platform platform, int i, HashMap<String, Object> hashMap) {
        HashMap<String, Object> hashMap2;
        Platform platform2;
        Platform.ShareParams remove = this.f294b.remove(platform);
        if (hashMap != null) {
            remove = (Platform.ShareParams) hashMap.remove("ShareParams");
        }
        try {
            hashMap2 = (HashMap) hashMap.clone();
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
            hashMap2 = hashMap;
        }
        if (remove != null) {
            C0720f fVar = new C0720f();
            fVar.f258n = remove.getCustomFlag();
            String userId = platform.getDb().getUserId();
            if (("WechatMoments".equals(platform.getName()) || "WechatFavorite".equals(platform.getName())) && TextUtils.isEmpty(userId)) {
                try {
                    platform2 = ShareSDK.getPlatform("Wechat");
                } catch (Throwable th2) {
                    SSDKLog.m645b().mo29770d(th2, "InnerPlatformActionListener wechat is null", new Object[0]);
                    platform2 = null;
                }
                if (platform2 != null) {
                    userId = platform2.getDb().getUserId();
                }
            } else if ("TencentWeibo".equals(platform.getName())) {
                userId = platform.getDb().get("name");
            }
            fVar.f254b = userId;
            fVar.f253a = platform.getPlatformId();
            C0720f.C0721a filterShareContent = platform.filterShareContent(remove, hashMap2);
            if (filterShareContent != null) {
                fVar.f255c = filterShareContent.f259a;
                fVar.f256d = filterShareContent;
            }
            if (platform != null) {
                fVar.f257m = m330b(platform);
            }
            C0724d a = C0724d.m310a();
            if (a != null) {
                a.mo10641a((C0717c) fVar);
            }
        }
        PlatformActionListener platformActionListener = this.f293a;
        if (platformActionListener != null) {
            try {
                platformActionListener.onComplete(platform, i, hashMap);
                this.f293a = null;
                this.f295c = 0;
            } catch (Throwable th3) {
                SSDKLog.m645b().mo29769d(th3);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10650a(Platform platform, final int i, final Object obj) {
        if (C0701f.m152c().mo10550b() == null) {
            m331b();
        }
        this.f295c = i;
        final PlatformActionListener platformActionListener = this.f293a;
        this.f293a = new PlatformActionListener() {
            public void onError(Platform platform, int i, Throwable th) {
                PlatformActionListener unused = C0730d.this.f293a = platformActionListener;
                if (C0730d.this.f293a != null) {
                    C0730d.this.f293a.onError(platform, i, th);
                }
            }

            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                if (ShareSDK.getEnableAuthTag()) {
                    String fromHashMap = new Hashon().fromHashMap(C0701f.m152c().mo10550b());
                    if (!TextUtils.isEmpty(fromHashMap)) {
                        platform.getDb().put("userTags", fromHashMap);
                    }
                }
                PlatformActionListener unused = C0730d.this.f293a = platformActionListener;
                platform.afterRegister(i, obj);
            }

            public void onCancel(Platform platform, int i) {
                PlatformActionListener unused = C0730d.this.f293a = platformActionListener;
                if (C0730d.this.f293a != null) {
                    C0730d.this.f293a.onCancel(platform, i);
                }
            }
        };
        platform.doAuthorize((String[]) null);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public String m326a(Platform platform) {
        try {
            try {
                return m327a(platform.getDb(), new String[]{"nickname", "icon", "gender", "snsUserUrl", "resume", "secretType", "secret", "birthday", "followerCount", "favouriteCount", "shareCount", "snsregat", "snsUserLevel", "educationJSONArrayStr", "workJSONArrayStr"});
            } catch (Throwable th) {
                th = th;
                SSDKLog.m645b().mo29787w(th);
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            SSDKLog.m645b().mo29787w(th);
            return null;
        }
    }

    /* renamed from: b */
    private String m330b(Platform platform) {
        Platform platform2;
        PlatformDb db = platform.getDb();
        if (("WechatMoments".equals(platform.getName()) || "WechatFavorite".equals(platform.getName())) && TextUtils.isEmpty(db.getUserGender())) {
            try {
                platform2 = ShareSDK.getPlatform("Wechat");
            } catch (Throwable th) {
                SSDKLog.m645b().mo29770d(th, "InnerPlatformActionListener getUserDataBrief catch ", new Object[0]);
                platform2 = null;
            }
            if (platform2 != null) {
                db = platform2.getDb();
            }
        }
        try {
            return m327a(db, new String[]{"gender", "birthday", "secretType", "educationJSONArrayStr", "workJSONArrayStr"});
        } catch (Throwable th2) {
            SSDKLog.m645b().mo29787w(th2);
            return null;
        }
    }

    /* renamed from: a */
    private String m327a(PlatformDb platformDb, String[] strArr) throws Throwable {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int i = 0;
        for (String str : strArr) {
            if (i > 0) {
                sb2.append('|');
                sb.append('|');
            }
            i++;
            String str2 = platformDb.get(str);
            if (!TextUtils.isEmpty(str2)) {
                sb.append(str2);
                sb2.append(Data.urlEncode(str2, "utf-8"));
            }
        }
        SSDKLog.m645b().mo29775i("======UserData: " + sb.toString(), new Object[0]);
        return sb2.toString();
    }
}
