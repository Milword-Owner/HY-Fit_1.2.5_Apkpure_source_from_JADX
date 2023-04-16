package p005cn.sharesdk.framework.p007b;

import android.text.TextUtils;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.baidubce.BceConfig;
import com.clj.fastble.BleManager;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.mob.MobCommunicator;
import com.mob.MobSDK;
import com.mob.tools.log.NLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import p005cn.sharesdk.framework.C0690a;
import p005cn.sharesdk.framework.C0707b;
import p005cn.sharesdk.framework.ShareSDK;
import p005cn.sharesdk.framework.p006a.C0692a;
import p005cn.sharesdk.framework.p007b.p008a.C0711c;
import p005cn.sharesdk.framework.p007b.p008a.C0712d;
import p005cn.sharesdk.framework.p007b.p008a.C0713e;
import p005cn.sharesdk.framework.p007b.p009b.C0717c;
import p005cn.sharesdk.framework.utils.SSDKLog;
import p015io.reactivex.annotations.SchedulerSupport;

/* renamed from: cn.sharesdk.framework.b.c */
/* compiled from: Protocols */
public class C0723c {

    /* renamed from: i */
    private static MobCommunicator f268i;

    /* renamed from: a */
    private C0713e f269a = C0713e.m196a();

    /* renamed from: b */
    private DeviceHelper f270b = DeviceHelper.getInstance(MobSDK.getContext());

    /* renamed from: c */
    private NetworkHelper f271c = new NetworkHelper();

    /* renamed from: d */
    private Hashon f272d = new Hashon();

    /* renamed from: e */
    private String f273e;

    /* renamed from: f */
    private String f274f;

    /* renamed from: g */
    private boolean f275g;

    /* renamed from: h */
    private HashMap<String, String> f276h;

    /* renamed from: g */
    private static synchronized MobCommunicator m284g() {
        MobCommunicator mobCommunicator;
        synchronized (C0723c.class) {
            if (f268i == null) {
                f268i = new MobCommunicator(1024, "bb7addd7e33383b74e82aba9b1d274c73aea6c0c71fcc88730270f630dbe490e1d162004f74e9532f98e17004630fbea9b346de63c23e83a7dfad70dd47cebfd", "288e7c44e01569a905386e6341baabfcde63ec37d0f0835cc662c299a5d0072970808a7fa434f0a51fa581d09d5ec4350ba5d548eafbe1fd956fb3afd678c1fb6134c904668652ec5cceb5d85da337a0f2f13ea457cca74a01b3ba0f4c809ad30d382bba2562ec9b996ae44c3700731c1b914997ef826331759e4084a019a03f");
            }
            mobCommunicator = f268i;
        }
        return mobCommunicator;
    }

    public C0723c() {
        try {
            this.f276h = (HashMap) this.f269a.mo10608k("buffered_server_paths");
        } catch (Throwable unused) {
            this.f276h = new HashMap<>();
        }
        m285h();
    }

    /* renamed from: h */
    private void m285h() {
        this.f273e = (this.f270b.getPackageName() + BceConfig.BOS_DELIMITER + this.f270b.getAppVersionName()) + " " + "ShareSDK/3.8.5" + " " + ("Android/" + this.f270b.getOSVersionInt());
        try {
            this.f274f = MobSDK.dynamicModifyUrl("api.share.mob.com");
        } catch (Throwable th) {
            this.f274f = MobSDK.checkRequestUrl("api.share.mob.com");
            SSDKLog.m645b().mo29768d("001 dynamicModifyUrl catch, no problem " + th, new Object[0]);
        }
        this.f275g = true;
    }

    /* renamed from: a */
    public void mo10626a(String str) {
        if (!TextUtils.isEmpty(str)) {
            SSDKLog.m645b().mo29768d("duid === " + str, new Object[0]);
            this.f273e += " " + str;
        }
    }

    /* renamed from: b */
    public void mo10631b(String str) {
        this.f274f = str;
    }

    /* renamed from: a */
    public void mo10628a(HashMap<String, String> hashMap) {
        this.f276h = hashMap;
        this.f269a.mo10585a("buffered_server_paths", (Object) this.f276h);
    }

    /* renamed from: i */
    private String m286i() {
        return this.f274f + "/conn";
    }

    /* renamed from: a */
    public HashMap<String, Object> mo10623a() throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("appkey", MobSDK.getAppkey()));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Identity", C0692a.m119a()));
        NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
        networkTimeOut.readTimout = 30000;
        networkTimeOut.connectionTimeout = 30000;
        String httpPost = this.f271c.httpPost(m286i(), (ArrayList<KVPair<String>>) arrayList, (KVPair<String>) null, (ArrayList<KVPair<String>>) arrayList2, networkTimeOut);
        SSDKLog.m645b().mo29775i(" isConnectToServer response == %s", httpPost);
        return this.f272d.fromJson(httpPost);
    }

    /* renamed from: j */
    private String m287j() {
        HashMap<String, String> hashMap = this.f276h;
        if (hashMap == null || !hashMap.containsKey("/date")) {
            return this.f274f + "/date";
        }
        return this.f276h.get("/date") + "/date";
    }

    /* renamed from: b */
    public long mo10630b() throws Throwable {
        String str;
        if (!this.f269a.mo10609k()) {
            return 0;
        }
        try {
            str = this.f271c.httpGet(m287j(), (ArrayList<KVPair<String>>) null, (ArrayList<KVPair<String>>) null, (NetworkHelper.NetworkTimeOut) null);
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
            str = "{}";
        }
        HashMap fromJson = this.f272d.fromJson(str);
        if (!fromJson.containsKey("timestamp")) {
            return this.f269a.mo10587b();
        }
        try {
            long currentTimeMillis = System.currentTimeMillis() - ResHelper.parseLong(String.valueOf(fromJson.get("timestamp")));
            this.f269a.mo10584a("service_time", Long.valueOf(currentTimeMillis));
            return currentTimeMillis;
        } catch (Throwable th2) {
            SSDKLog.m645b().mo29769d(th2);
            return this.f269a.mo10587b();
        }
    }

    /* renamed from: k */
    private String m288k() {
        return this.f274f + "/conf5";
    }

    /* renamed from: c */
    public HashMap<String, Object> mo10633c() throws Throwable {
        String appkey = MobSDK.getAppkey();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("appkey", appkey));
        arrayList.add(new KVPair(Config.DEVICE_PART, this.f270b.getDeviceKey()));
        arrayList.add(new KVPair("plat", String.valueOf(this.f270b.getPlatformCode())));
        arrayList.add(new KVPair("apppkg", this.f270b.getPackageName()));
        arrayList.add(new KVPair("appver", String.valueOf(this.f270b.getAppVersion())));
        arrayList.add(new KVPair("sdkver", String.valueOf(ShareSDK.SDK_VERSION_CODE)));
        arrayList.add(new KVPair("networktype", this.f270b.getDetailNetworkTypeForStatic()));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Identity", C0692a.m119a()));
        NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
        networkTimeOut.readTimout = BleManager.DEFAULT_SCAN_TIME;
        networkTimeOut.connectionTimeout = BleManager.DEFAULT_SCAN_TIME;
        String httpPost = this.f271c.httpPost(m288k(), (ArrayList<KVPair<String>>) arrayList, (KVPair<String>) null, (ArrayList<KVPair<String>>) arrayList2, networkTimeOut);
        try {
            HashMap fromJson = new Hashon().fromJson(httpPost);
            if (fromJson.containsKey("error")) {
                if (String.valueOf(fromJson.get("error")).contains("'appkey' is illegal")) {
                    if (TextUtils.isEmpty(appkey)) {
                        C0707b.m163a().mo10560b();
                    } else {
                        C0690a.f170a = true;
                    }
                }
            } else if (!TextUtils.isEmpty(appkey)) {
                C0690a.f171b = appkey;
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
        }
        SSDKLog.m645b().mo29775i(" get server config response == %s", httpPost);
        return this.f272d.fromJson(httpPost);
    }

    /* renamed from: l */
    private String m289l() {
        try {
            return MobSDK.dynamicModifyUrl("up.mob.com/upload/image");
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29768d("002 dynamicModifyUrl catch, no problem " + th, new Object[0]);
            return MobSDK.checkRequestUrl("up.mob.com/upload/image");
        }
    }

    /* renamed from: c */
    public HashMap<String, Object> mo10634c(String str) throws Throwable {
        KVPair kVPair = new KVPair("file", str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("User-Identity", C0692a.m119a()));
        String httpPost = this.f271c.httpPost(m289l(), (ArrayList<KVPair<String>>) null, (KVPair<String>) kVPair, (ArrayList<KVPair<String>>) arrayList, (NetworkHelper.NetworkTimeOut) null);
        SSDKLog.m645b().mo29775i("upload file response == %s", httpPost);
        return this.f272d.fromJson(httpPost);
    }

    /* renamed from: m */
    private String m290m() {
        HashMap<String, String> hashMap = this.f276h;
        if (hashMap == null || !hashMap.containsKey("/log4")) {
            return this.f274f + "/log4";
        }
        return this.f276h.get("/log4") + "/log4";
    }

    /* renamed from: a */
    public boolean mo10629a(String str, boolean z) {
        try {
            if (!MobSDK.isMob()) {
                return true;
            }
            if (!SchedulerSupport.NONE.equals(this.f270b.getDetailNetworkTypeForStatic())) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new KVPair(Config.MODEL, str));
                arrayList.add(new KVPair("t", z ? "1" : "0"));
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new KVPair("User-Identity", C0692a.m119a()));
                NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
                networkTimeOut.readTimout = 30000;
                networkTimeOut.connectionTimeout = 30000;
                String httpPost = this.f271c.httpPost(m290m(), (ArrayList<KVPair<String>>) arrayList, (KVPair<String>) null, (ArrayList<KVPair<String>>) arrayList2, networkTimeOut);
                SSDKLog.m645b().mo29775i("> Upload All Log  resp: %s", httpPost);
                return TextUtils.isEmpty(httpPost) || ((Integer) this.f272d.fromJson(httpPost).get("status")).intValue() == 200;
            }
            throw new IllegalStateException("network is disconnected!");
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
            return false;
        }
    }

    /* renamed from: n */
    private String m291n() {
        try {
            return MobSDK.dynamicModifyUrl("l.mob.com/url/shareSdkEncryptMapping.do");
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29768d("003 dynamicModifyUrl catch, no problem " + th, new Object[0]);
            return MobSDK.checkRequestUrl("l.mob.com/url/shareSdkEncryptMapping.do");
        }
    }

    /* renamed from: a */
    public HashMap<String, Object> mo10624a(String str, ArrayList<String> arrayList, int i, String str2) throws Throwable {
        if (!this.f275g) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("key", MobSDK.getAppkey()));
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList2.add(new KVPair("urls", arrayList.get(i2).toString()));
        }
        arrayList2.add(new KVPair("deviceid", this.f270b.getDeviceKey()));
        arrayList2.add(new KVPair("snsplat", String.valueOf(i)));
        String e = m283e(str2);
        if (TextUtils.isEmpty(e)) {
            return null;
        }
        arrayList2.add(new KVPair(Config.MODEL, e));
        new ArrayList().add(new KVPair("User-Identity", C0692a.m119a()));
        NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
        networkTimeOut.readTimout = FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS;
        networkTimeOut.connectionTimeout = FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS;
        HashMap hashMap = new HashMap();
        hashMap.put("key", MobSDK.getAppkey());
        ArrayList arrayList3 = new ArrayList();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            arrayList3.add(URLEncoder.encode(arrayList.get(i3), "UTF-8"));
        }
        hashMap.put("urls", arrayList3);
        hashMap.put("deviceid", this.f270b.getDeviceKey());
        hashMap.put("snsplat", Integer.valueOf(i));
        if (TextUtils.isEmpty(e)) {
            return null;
        }
        hashMap.put(Config.MODEL, e);
        HashMap<String, Object> hashMap2 = (HashMap) m284g().requestSynchronized((HashMap<String, Object>) hashMap, m291n(), false);
        SSDKLog.m645b().mo29775i("> SERVER_SHORT_LINK_URL  resp: %s", hashMap2);
        if (hashMap2.size() == 0) {
            this.f275g = false;
            return null;
        } else if (hashMap2.get(ShareConstants.WEB_DIALOG_PARAM_DATA) == null) {
            return null;
        } else {
            return hashMap2;
        }
    }

    /* renamed from: e */
    private String m283e(String str) throws Throwable {
        boolean c = this.f269a.mo10593c();
        boolean d = this.f269a.mo10595d();
        StringBuilder sb = new StringBuilder();
        sb.append(Data.urlEncode(this.f270b.getPackageName(), "utf-8"));
        sb.append("|");
        sb.append(Data.urlEncode(this.f270b.getAppVersionName(), "utf-8"));
        sb.append("|");
        sb.append(Data.urlEncode(String.valueOf(ShareSDK.SDK_VERSION_CODE), "utf-8"));
        sb.append("|");
        sb.append(Data.urlEncode(String.valueOf(this.f270b.getPlatformCode()), "utf-8"));
        sb.append("|");
        sb.append(Data.urlEncode(this.f270b.getDetailNetworkTypeForStatic(), "utf-8"));
        sb.append("|");
        if (c) {
            sb.append(Data.urlEncode(String.valueOf(this.f270b.getOSVersionInt()), "utf-8"));
            sb.append("|");
            sb.append(Data.urlEncode(this.f270b.getScreenSize(), "utf-8"));
            sb.append("|");
            sb.append(Data.urlEncode(this.f270b.getManufacturer(), "utf-8"));
            sb.append("|");
            sb.append(Data.urlEncode(this.f270b.getModel(), "utf-8"));
            sb.append("|");
            sb.append(Data.urlEncode(this.f270b.getCarrier(), "utf-8"));
            sb.append("|");
        } else {
            sb.append("|||||");
        }
        if (d) {
            sb.append(str);
        } else {
            sb.append(str.split("\\|")[0]);
            sb.append("|||||");
        }
        String sb2 = sb.toString();
        SSDKLog.m645b().mo29775i("shorLinkMsg ===>>>>", sb2);
        return Base64.encodeToString(Data.AES128Encode(Data.rawMD5(String.format("%s:%s", new Object[]{this.f270b.getDeviceKey(), MobSDK.getAppkey()})), sb2), 2);
    }

    /* renamed from: o */
    private String m292o() {
        HashMap<String, String> hashMap = this.f276h;
        if (hashMap == null || !hashMap.containsKey("/snsconf")) {
            return this.f274f + "/snsconf";
        }
        return this.f276h.get("/snsconf") + "/snsconf";
    }

    /* renamed from: d */
    public HashMap<String, Object> mo10635d() throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("appkey", MobSDK.getAppkey()));
        arrayList.add(new KVPair(Config.DEVICE_PART, this.f270b.getDeviceKey()));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Identity", C0692a.m119a()));
        NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
        networkTimeOut.readTimout = BleManager.DEFAULT_SCAN_TIME;
        networkTimeOut.connectionTimeout = BleManager.DEFAULT_SCAN_TIME;
        return this.f272d.fromJson(this.f271c.httpPost(m292o(), (ArrayList<KVPair<String>>) arrayList, (KVPair<String>) null, (ArrayList<KVPair<String>>) arrayList2, networkTimeOut));
    }

    /* renamed from: a */
    public void mo10625a(C0717c cVar) throws Throwable {
        C0712d.m192a(cVar.toString(), cVar.f235e);
    }

    /* renamed from: e */
    public ArrayList<C0711c> mo10637e() throws Throwable {
        ArrayList<C0711c> a = C0712d.m194a();
        return a == null ? new ArrayList<>() : a;
    }

    /* renamed from: a */
    public void mo10627a(ArrayList<String> arrayList) throws Throwable {
        C0712d.m193a(arrayList);
    }

    /* renamed from: f */
    public HashMap<String, Object> mo10638f() throws Throwable {
        return this.f272d.fromJson(this.f269a.mo10605i());
    }

    /* renamed from: b */
    public void mo10632b(HashMap<String, Object> hashMap) throws Throwable {
        this.f269a.mo10600g(this.f272d.fromHashMap(hashMap));
    }

    /* renamed from: d */
    public HashMap<String, Object> mo10636d(String str) throws Throwable {
        byte[] decode = Base64.decode(str, 2);
        String deviceKey = this.f270b.getDeviceKey();
        return this.f272d.fromJson(new String(Data.AES128Decode(Data.rawMD5(MobSDK.getAppkey() + Config.TRACE_TODAY_VISIT_SPLIT + deviceKey), decode), "UTF-8").trim());
    }
}
