package p005cn.sharesdk.framework.authorize;

import android.text.TextUtils;
import android.util.Base64;
import com.mob.MobCommunicator;
import com.mob.MobSDK;
import com.mob.commons.SHARESDK;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.tools.log.NLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import java.util.ArrayList;
import java.util.HashMap;
import p005cn.sharesdk.framework.p006a.C0693b;
import p005cn.sharesdk.framework.p007b.p008a.C0713e;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.framework.authorize.f */
/* compiled from: SdkPlusTags */
public class C0701f {

    /* renamed from: a */
    private static volatile C0701f f190a;

    /* renamed from: b */
    private MobCommunicator f191b;

    /* renamed from: c */
    private String f192c = MobSDK.getAppkey();

    /* renamed from: d */
    private String f193d = this.f194e.getDeviceKey();

    /* renamed from: e */
    private DeviceHelper f194e = DeviceHelper.getInstance(MobSDK.getContext());

    /* renamed from: f */
    private C0693b f195f = C0693b.m120a();

    /* renamed from: g */
    private boolean f196g = false;

    /* renamed from: h */
    private HashMap<String, Object> f197h;

    /* renamed from: a */
    public boolean mo10549a() {
        return this.f196g;
    }

    /* renamed from: a */
    public void mo10548a(boolean z) {
        this.f196g = z;
    }

    /* renamed from: b */
    public HashMap<String, Object> mo10550b() {
        return this.f197h;
    }

    /* renamed from: e */
    private synchronized MobCommunicator m153e() {
        if (this.f191b == null) {
            this.f191b = new MobCommunicator(1024, "009cbd92ccef123be840deec0c6ed0547194c1e471d11b6f375e56038458fb18833e5bab2e1206b261495d7e2d1d9e5aa859e6d4b671a8ca5d78efede48e291a3f", "1dfd1d615cb891ce9a76f42d036af7fce5f8b8efaa11b2f42590ecc4ea4cff28f5f6b0726aeb76254ab5b02a58c1d5b486c39d9da1a58fa6ba2f22196493b3a4cbc283dcf749bf63679ee24d185de70c8dfe05605886c9b53e9f569082eabdf98c4fb0dcf07eb9bb3e647903489ff0b5d933bd004af5be4a1022fdda41f347f1");
        }
        return this.f191b;
    }

    /* renamed from: c */
    public static C0701f m152c() {
        synchronized (C0701f.class) {
            if (f190a == null) {
                synchronized (C0701f.class) {
                    if (f190a == null) {
                        f190a = new C0701f();
                    }
                }
            }
        }
        return f190a;
    }

    /* renamed from: d */
    public void mo10551d() throws Throwable {
        C0713e a = C0713e.m196a();
        if (this.f196g && a.mo10601g()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("Content-type", "application/json"));
            arrayList.add(new KVPair("sign", m151a(this.f192c, this.f193d)));
            HashMap hashMap = new HashMap();
            hashMap.put("appkey", this.f192c);
            hashMap.put("deviceId", this.f193d);
            String authorize = DeviceAuthorizer.authorize(new SHARESDK());
            hashMap.put("duid", authorize);
            try {
                if (TextUtils.isEmpty(this.f192c) || TextUtils.isEmpty(this.f193d) || TextUtils.isEmpty(authorize)) {
                    SSDKLog.m645b().mo29768d("SdkPlusTags request userTags that appkey or deviceId or duid is null", new Object[0]);
                } else {
                    this.f197h = (HashMap) m153e().requestSynchronized((HashMap<String, Object>) hashMap, "http://p.share.mob.com/tags/getTagList", false);
                }
            } catch (Exception e) {
                NLog b = SSDKLog.m645b();
                b.mo29768d("SdkPlusTags request userTags is error T===> " + e, new Object[0]);
            }
        }
    }

    /* renamed from: a */
    private String m151a(String str, String str2) throws Throwable {
        byte[] rawMD5 = Data.rawMD5(String.format("%s:%s", new Object[]{this.f194e.getDeviceKey(), MobSDK.getAppkey()}));
        return Base64.encodeToString(Data.AES128Encode(rawMD5, str + str2), 2);
    }
}
