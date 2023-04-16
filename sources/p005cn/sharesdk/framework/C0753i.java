package p005cn.sharesdk.framework;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Message;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.eventrecoder.EventRecorder;
import com.mob.tools.log.NLog;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import p005cn.sharesdk.framework.loopshare.LoopSharePasswordListener;
import p005cn.sharesdk.framework.loopshare.LoopShareResultListener;
import p005cn.sharesdk.framework.loopshare.MoblinkActionListener;
import p005cn.sharesdk.framework.loopshare.watermark.ReadQrImageListener;
import p005cn.sharesdk.framework.loopshare.watermark.WaterMarkListener;
import p005cn.sharesdk.framework.p007b.C0708a;
import p005cn.sharesdk.framework.utils.C0807e;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.framework.i */
/* compiled from: ShareSDKCoreThread */
public class C0753i extends C0807e {

    /* renamed from: b */
    private C0756a f339b;

    /* renamed from: c */
    private HashMap<String, HashMap<String, String>> f340c = new HashMap<>();

    /* renamed from: d */
    private ArrayList<Platform> f341d = new ArrayList<>();

    /* renamed from: e */
    private HashMap<String, Integer> f342e = new HashMap<>();

    /* renamed from: f */
    private HashMap<Integer, String> f343f = new HashMap<>();

    /* renamed from: g */
    private HashMap<Integer, CustomPlatform> f344g = new HashMap<>();

    /* renamed from: h */
    private HashMap<Integer, HashMap<String, Object>> f345h = new HashMap<>();

    /* renamed from: i */
    private HashMap<Integer, Service> f346i = new HashMap<>();

    /* renamed from: j */
    private boolean f347j = true;

    /* renamed from: k */
    private boolean f348k;

    /* renamed from: cn.sharesdk.framework.i$a */
    /* compiled from: ShareSDKCoreThread */
    private enum C0756a {
        INITIALIZING,
        READY
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo10642b(Message message) {
    }

    /* renamed from: a */
    public void mo10716a(Activity activity) {
        C0751h.m384a(activity);
    }

    /* renamed from: a */
    public Activity mo10707a() {
        return C0751h.m395b();
    }

    /* renamed from: a */
    public void mo10726a(boolean z) {
        C0751h.m393a(z);
    }

    /* renamed from: b */
    public boolean mo10733b() {
        return C0751h.m397c();
    }

    /* renamed from: b */
    public void mo10732b(boolean z) {
        C0751h.m396b(z);
    }

    /* renamed from: c */
    public boolean mo10738c() {
        return C0751h.m398d();
    }

    /* renamed from: a */
    public void mo10717a(Context context, ReadQrImageListener readQrImageListener) {
        C0751h.m385a(context, readQrImageListener);
    }

    /* renamed from: a */
    public void mo10722a(String str, String str2, String str3, String str4, WaterMarkListener waterMarkListener) {
        C0751h.m389a(str, str2, str3, str4, waterMarkListener);
    }

    /* renamed from: a */
    public Bitmap mo10708a(String str, int i, int i2) {
        return C0751h.m380a(str, i, i2);
    }

    /* renamed from: a */
    public void mo10724a(HashMap<String, Object> hashMap, MoblinkActionListener moblinkActionListener) {
        try {
            C0751h.m391a(hashMap, moblinkActionListener);
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29771e("ShareSDKCoreThread mobLinkGetMobID " + th, new Object[0]);
        }
    }

    /* renamed from: a */
    public void mo10725a(HashMap<String, Object> hashMap, String str, LoopSharePasswordListener loopSharePasswordListener) {
        try {
            C0751h.m392a(hashMap, str, loopSharePasswordListener);
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29771e("ShareSDKCoreThrad preparePassWord catch: " + th, new Object[0]);
        }
    }

    /* renamed from: a */
    public void mo10727a(boolean z, LoopSharePasswordListener loopSharePasswordListener) {
        try {
            C0751h.m394a(z, loopSharePasswordListener);
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29771e("ShareSDKCoreThrad readPassWord catch: " + th, new Object[0]);
        }
    }

    /* renamed from: a */
    public void mo10718a(LoopShareResultListener loopShareResultListener) {
        try {
            C0751h.m387a(loopShareResultListener);
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29771e("ShareSDKCoreThrad prepareLoopShare " + th, new Object[0]);
        }
    }

    /* renamed from: d */
    public HashMap<String, Object> mo10739d() {
        return C0751h.m399e();
    }

    /* renamed from: e */
    public void mo10741e() {
        this.f339b = C0756a.INITIALIZING;
        SSDKLog.m644a();
        EventRecorder.prepare();
        m406k();
        super.mo10741e();
    }

    /* renamed from: k */
    private void m406k() {
        XmlPullParser newPullParser;
        InputStream inputStream;
        synchronized (this.f340c) {
            this.f340c.clear();
            try {
                XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
                newInstance.setNamespaceAware(true);
                newPullParser = newInstance.newPullParser();
                inputStream = null;
                inputStream = MobSDK.getContext().getAssets().open("ShareSDK.xml");
            } catch (Throwable th) {
                SSDKLog.m645b().mo29769d(th);
            }
            newPullParser.setInput(inputStream, "utf-8");
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType == 2) {
                    String name = newPullParser.getName();
                    HashMap hashMap = new HashMap();
                    int attributeCount = newPullParser.getAttributeCount();
                    for (int i = 0; i < attributeCount; i++) {
                        hashMap.put(newPullParser.getAttributeName(i), newPullParser.getAttributeValue(i).trim());
                    }
                    this.f340c.put(name, hashMap);
                }
            }
            inputStream.close();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo10640a(Message message) {
        HashMap<Integer, Service> hashMap;
        synchronized (this.f346i) {
            synchronized (this.f341d) {
                try {
                    String checkRecord = EventRecorder.checkRecord(ShareSDK.SDK_TAG);
                    if (!TextUtils.isEmpty(checkRecord)) {
                        C0708a.m166a().mo10565a((HashMap<String, Object>) null);
                        NLog b = SSDKLog.m645b();
                        b.mo29786w("EventRecorder checkRecord result ==" + checkRecord);
                        mo10747j();
                    }
                    EventRecorder.clear();
                } catch (Throwable th) {
                    try {
                        SSDKLog.m645b().mo29787w(th);
                        this.f339b = C0756a.READY;
                        this.f341d.notify();
                        hashMap = this.f346i;
                    } catch (Throwable th2) {
                        this.f339b = C0756a.READY;
                        this.f341d.notify();
                        this.f346i.notify();
                        throw th2;
                    }
                }
                this.f341d.clear();
                ArrayList<Platform> a = C0751h.m381a();
                if (a != null) {
                    this.f341d.addAll(a);
                }
                Iterator<Platform> it = this.f341d.iterator();
                while (it.hasNext()) {
                    Platform next = it.next();
                    this.f343f.put(Integer.valueOf(next.getPlatformId()), next.getName());
                    this.f342e.put(next.getName(), Integer.valueOf(next.getPlatformId()));
                }
                C0751h.m386a(this.f613a);
                this.f339b = C0756a.READY;
                new Thread() {
                    public void run() {
                        C0753i.this.mo10746i();
                    }
                }.start();
                this.f339b = C0756a.READY;
                this.f341d.notify();
                hashMap = this.f346i;
                hashMap.notify();
            }
        }
    }

    /* renamed from: a */
    public void mo10719a(Class<? extends Service> cls) {
        synchronized (this.f346i) {
            if (!this.f346i.containsKey(Integer.valueOf(cls.hashCode()))) {
                try {
                    Service service = (Service) cls.newInstance();
                    this.f346i.put(Integer.valueOf(cls.hashCode()), service);
                    service.onBind();
                } catch (Throwable th) {
                    SSDKLog.m645b().mo29787w(th);
                }
            }
        }
    }

    /* renamed from: b */
    public void mo10731b(Class<? extends Service> cls) {
        synchronized (this.f346i) {
            int hashCode = cls.hashCode();
            if (this.f346i.containsKey(Integer.valueOf(hashCode))) {
                this.f346i.get(Integer.valueOf(hashCode)).onUnbind();
                this.f346i.remove(Integer.valueOf(hashCode));
            }
        }
    }

    /* renamed from: c */
    public <T extends Service> T mo10734c(Class<T> cls) {
        T t;
        synchronized (this.f346i) {
            if (this.f339b == C0756a.INITIALIZING) {
                try {
                    this.f346i.wait();
                } catch (Throwable th) {
                    SSDKLog.m645b().mo29787w(th);
                }
            }
            try {
                t = (Service) cls.cast(this.f346i.get(Integer.valueOf(cls.hashCode())));
            } catch (Throwable th2) {
                SSDKLog.m645b().mo29787w(th2);
                return null;
            }
        }
        return t;
    }

    /* renamed from: d */
    public void mo10740d(Class<? extends CustomPlatform> cls) {
        synchronized (this.f344g) {
            if (!this.f344g.containsKey(Integer.valueOf(cls.hashCode()))) {
                try {
                    CustomPlatform customPlatform = (CustomPlatform) cls.newInstance();
                    this.f344g.put(Integer.valueOf(cls.hashCode()), customPlatform);
                    if (customPlatform != null && customPlatform.mo10418b()) {
                        this.f343f.put(Integer.valueOf(customPlatform.getPlatformId()), customPlatform.getName());
                        this.f342e.put(customPlatform.getName(), Integer.valueOf(customPlatform.getPlatformId()));
                    }
                } catch (Throwable th) {
                    SSDKLog.m645b().mo29787w(th);
                }
            }
        }
    }

    /* renamed from: e */
    public void mo10742e(Class<? extends CustomPlatform> cls) {
        int hashCode = cls.hashCode();
        synchronized (this.f344g) {
            this.f344g.remove(Integer.valueOf(hashCode));
        }
    }

    /* renamed from: a */
    public Platform mo10709a(String str) {
        Platform[] f;
        if (str == null || (f = mo10743f()) == null) {
            return null;
        }
        for (Platform platform : f) {
            if (str.equals(platform.getName())) {
                return platform;
            }
        }
        return null;
    }

    /* renamed from: f */
    public Platform[] mo10743f() {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.f341d) {
            if (this.f339b == C0756a.INITIALIZING) {
                try {
                    this.f341d.wait();
                } catch (Throwable th) {
                    SSDKLog.m645b().mo29787w(th);
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Platform> it = this.f341d.iterator();
        while (it.hasNext()) {
            Platform next = it.next();
            if (next != null && next.mo10418b()) {
                next.mo10414a();
                arrayList.add(next);
            }
        }
        C0751h.m390a((ArrayList<Platform>) arrayList);
        for (Map.Entry<Integer, CustomPlatform> value : this.f344g.entrySet()) {
            Platform platform = (Platform) value.getValue();
            if (platform != null && platform.mo10418b()) {
                arrayList.add(platform);
            }
        }
        if (arrayList.size() <= 0) {
            return null;
        }
        Platform[] platformArr = new Platform[arrayList.size()];
        for (int i = 0; i < platformArr.length; i++) {
            platformArr[i] = (Platform) arrayList.get(i);
        }
        SSDKLog.m645b().mo29775i("sort list use time: %s", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return platformArr;
    }

    /* renamed from: a */
    public void mo10713a(int i) {
        NetworkHelper.connectionTimeout = i;
    }

    /* renamed from: b */
    public void mo10730b(int i) {
        NetworkHelper.readTimout = i;
    }

    /* renamed from: c */
    public void mo10737c(boolean z) {
        this.f348k = z;
    }

    /* renamed from: g */
    public boolean mo10744g() {
        return this.f348k;
    }

    /* renamed from: a */
    public void mo10715a(int i, Platform platform) {
        C0751h.m383a(i, platform);
    }

    /* renamed from: a */
    public void mo10720a(String str, int i) {
        C0751h.m388a(str, i);
    }

    /* renamed from: a */
    public void mo10723a(String str, HashMap<String, Object> hashMap) {
        synchronized (this.f340c) {
            HashMap hashMap2 = this.f340c.get(str);
            if (hashMap2 == null) {
                hashMap2 = new HashMap();
            }
            synchronized (hashMap2) {
                for (Map.Entry next : hashMap.entrySet()) {
                    String str2 = (String) next.getKey();
                    Object value = next.getValue();
                    if (value != null) {
                        hashMap2.put(str2, String.valueOf(value));
                    }
                }
            }
            this.f340c.put(str, hashMap2);
        }
        synchronized (this.f341d) {
            if (this.f339b == C0756a.INITIALIZING) {
                try {
                    this.f341d.wait();
                } catch (Throwable th) {
                    SSDKLog.m645b().mo29787w(th);
                }
            }
        }
        Iterator<Platform> it = this.f341d.iterator();
        while (it.hasNext()) {
            Platform next2 = it.next();
            if (next2 != null && next2.getName().equals(str)) {
                next2.mo10414a();
                return;
            }
        }
    }

    /* renamed from: c */
    public String mo10735c(int i) {
        String str;
        synchronized (this.f341d) {
            synchronized (this.f344g) {
                str = this.f343f.get(Integer.valueOf(i));
            }
        }
        return str;
    }

    /* renamed from: b */
    public int mo10728b(String str) {
        synchronized (this.f341d) {
            synchronized (this.f344g) {
                if (!this.f342e.containsKey(str)) {
                    return 0;
                }
                int intValue = this.f342e.get(str).intValue();
                return intValue;
            }
        }
    }

    /* renamed from: a */
    public void mo10721a(String str, String str2) {
        synchronized (this.f340c) {
            this.f340c.put(str2, this.f340c.get(str));
        }
    }

    /* renamed from: a */
    public void mo10714a(int i, int i2) {
        synchronized (this.f345h) {
            this.f345h.put(Integer.valueOf(i2), this.f345h.get(Integer.valueOf(i)));
        }
    }

    /* renamed from: b */
    public String mo10729b(String str, String str2) {
        synchronized (this.f340c) {
            HashMap hashMap = this.f340c.get(str);
            if (hashMap == null) {
                return null;
            }
            String str3 = (String) hashMap.get(str2);
            return str3;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        return r1;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String mo10710a(int r3, java.lang.String r4) {
        /*
            r2 = this;
            java.util.HashMap<java.lang.Integer, java.util.HashMap<java.lang.String, java.lang.Object>> r0 = r2.f345h
            monitor-enter(r0)
            java.util.HashMap<java.lang.Integer, java.util.HashMap<java.lang.String, java.lang.Object>> r1 = r2.f345h     // Catch:{ all -> 0x0021 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0021 }
            java.lang.Object r3 = r1.get(r3)     // Catch:{ all -> 0x0021 }
            java.util.HashMap r3 = (java.util.HashMap) r3     // Catch:{ all -> 0x0021 }
            r1 = 0
            if (r3 != 0) goto L_0x0014
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            return r1
        L_0x0014:
            java.lang.Object r3 = r3.get(r4)     // Catch:{ all -> 0x0021 }
            if (r3 != 0) goto L_0x001b
            goto L_0x001f
        L_0x001b:
            java.lang.String r1 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0021 }
        L_0x001f:
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            return r1
        L_0x0021:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.framework.C0753i.mo10710a(int, java.lang.String):java.lang.String");
    }

    /* renamed from: h */
    public boolean mo10745h() {
        synchronized (this.f345h) {
            if (this.f345h == null || this.f345h.size() <= 0) {
                return false;
            }
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0046, code lost:
        r2 = m405a(r3);
     */
    /* renamed from: i */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo10746i() {
        /*
            r5 = this;
            cn.sharesdk.framework.i$a r0 = p005cn.sharesdk.framework.C0753i.C0756a.READY
            cn.sharesdk.framework.i$a r1 = r5.f339b
            r2 = 0
            if (r0 == r1) goto L_0x0013
            com.mob.tools.log.NLog r0 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()
            java.lang.Object[] r1 = new java.lang.Object[r2]
            java.lang.String r3 = "Statistics module unopened"
            r0.mo29768d(r3, r1)
            return r2
        L_0x0013:
            cn.sharesdk.framework.b.a r0 = p005cn.sharesdk.framework.p007b.C0708a.m166a()
            java.util.HashMap r1 = r0.mo10571e()
            java.util.HashMap r1 = r5.m402a((p005cn.sharesdk.framework.p007b.C0708a) r0, (java.util.HashMap<java.lang.String, java.lang.Object>) r1)
            if (r1 == 0) goto L_0x002b
            int r3 = r1.size()
            if (r3 <= 0) goto L_0x002b
            boolean r2 = r5.m405a((java.util.HashMap<java.lang.String, java.lang.Object>) r1)
        L_0x002b:
            if (r2 == 0) goto L_0x0036
            cn.sharesdk.framework.i$2 r1 = new cn.sharesdk.framework.i$2
            r1.<init>(r0)
            r1.start()
            goto L_0x0058
        L_0x0036:
            java.util.HashMap r1 = r0.mo10572f()     // Catch:{ Throwable -> 0x0050 }
            java.util.HashMap r3 = r5.m402a((p005cn.sharesdk.framework.p007b.C0708a) r0, (java.util.HashMap<java.lang.String, java.lang.Object>) r1)     // Catch:{ Throwable -> 0x0050 }
            if (r3 == 0) goto L_0x0058
            int r4 = r3.size()     // Catch:{ Throwable -> 0x0050 }
            if (r4 <= 0) goto L_0x0058
            boolean r2 = r5.m405a((java.util.HashMap<java.lang.String, java.lang.Object>) r3)     // Catch:{ Throwable -> 0x0050 }
            if (r2 == 0) goto L_0x0058
            r0.mo10565a((java.util.HashMap<java.lang.String, java.lang.Object>) r1)     // Catch:{ Throwable -> 0x0050 }
            goto L_0x0058
        L_0x0050:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()
            r1.mo29787w((java.lang.Throwable) r0)
        L_0x0058:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.framework.C0753i.mo10746i():boolean");
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public HashMap<String, Object> m402a(C0708a aVar, HashMap<String, Object> hashMap) {
        try {
            if (hashMap.containsKey("error")) {
                SSDKLog.m645b().mo29775i("ShareSDK parse sns config ==>>", new Hashon().fromHashMap(hashMap));
                return null;
            } else if (!hashMap.containsKey("res")) {
                SSDKLog.m645b().mo29768d("ShareSDK platform config result ==>>", "SNS configuration is empty");
                return null;
            } else {
                String str = (String) hashMap.get("res");
                if (str == null) {
                    return null;
                }
                return aVar.mo10568c(str);
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29787w(th);
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public boolean m405a(HashMap<String, Object> hashMap) {
        synchronized (this.f345h) {
            HashMap<Integer, HashMap<String, Object>> a = C0751h.m382a(hashMap);
            if (a == null || a.size() <= 0) {
                return false;
            }
            this.f345h.clear();
            this.f345h = a;
            return true;
        }
    }

    /* renamed from: a */
    public String mo10712a(String str, boolean z, int i, String str2) {
        if (C0756a.READY != this.f339b) {
            return str;
        }
        return C0708a.m166a().mo10562a(str, i, z, str2);
    }

    /* renamed from: c */
    public String mo10736c(String str) {
        if (C0756a.READY != this.f339b) {
            return null;
        }
        return C0708a.m166a().mo10566b(str);
    }

    /* renamed from: a */
    public String mo10711a(Bitmap bitmap) {
        if (C0756a.READY != this.f339b) {
            return null;
        }
        return C0708a.m166a().mo10561a(bitmap);
    }

    /* renamed from: j */
    public void mo10747j() {
        try {
            ResHelper.clearCache(MobSDK.getContext());
        } catch (Throwable th) {
            SSDKLog.m645b().mo29787w(th);
        }
    }
}
