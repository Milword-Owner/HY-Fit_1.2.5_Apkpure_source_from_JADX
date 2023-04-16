package com.mob.commons.p023a;

import android.os.Message;
import com.facebook.share.internal.ShareConstants;
import com.mob.MobSDK;
import com.mob.commons.C2262b;
import com.mob.commons.C2293c;
import com.mob.commons.C2300e;
import com.mob.commons.C2308i;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.mob.commons.a.l */
/* compiled from: DvcvClt */
public class C2241l extends C2226d {

    /* renamed from: a */
    private HashMap<String, Object> f1980a;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public File mo28993a() {
        return C2300e.m2466a("comm/locks/.dvcv_lock");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b_ */
    public boolean mo28994b_() {
        return C2262b.m2278ac();
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo28995d() {
        mo28999b(1);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28987a(Message message) {
        if (message.what == 1 && mo28994b_()) {
            m2098h();
            mo28997a(1, C2262b.m2279ad() * 1000);
        }
    }

    /* renamed from: h */
    private void m2098h() {
        try {
            if (this.f1980a == null) {
                this.f1980a = m2100j();
            }
            if (this.f1980a == null) {
                this.f1980a = new HashMap<>();
            }
            HashMap<String, Object> i = m2099i();
            ArrayList arrayList = (ArrayList) this.f1980a.get("list");
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(i);
            this.f1980a.put("list", arrayList);
            m2094a(this.f1980a);
            m2096b(this.f1980a);
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    /* renamed from: i */
    private HashMap<String, Object> m2099i() {
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
        hashMap.putAll(instance.getCPUFreq());
        hashMap.put("photoCount", Integer.valueOf(instance.getAlbumCount()));
        hashMap.putAll(instance.getTraffic());
        hashMap.putAll(instance.getDeviceMemUsage());
        hashMap.put("createTime", Long.valueOf(C2262b.m2260a()));
        return hashMap;
    }

    /* renamed from: a */
    private void m2094a(HashMap<String, Object> hashMap) {
        File dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.dextvcd");
        try {
            byte[] a = m2095a(DeviceHelper.getInstance(MobSDK.getContext()).getModel(), hashMap);
            FileChannel channel = new FileOutputStream(dataCacheFile).getChannel();
            channel.write(ByteBuffer.wrap(a));
            channel.force(true);
            channel.close();
        } catch (Throwable th) {
            MobLog.getInstance().mo29770d(th, "[%s] %s", "DvcvClt", th.getMessage());
        }
    }

    /* renamed from: a */
    private byte[] m2095a(String str, HashMap<String, Object> hashMap) {
        String fromHashMap = new Hashon().fromHashMap(hashMap);
        try {
            return Data.AES128Encode(str, fromHashMap);
        } catch (Throwable th) {
            MobLog.getInstance().mo29770d(th, "[%s] %s", "DvcvClt", th.getMessage());
            return fromHashMap.getBytes();
        }
    }

    /* renamed from: j */
    private HashMap<String, Object> m2100j() {
        File dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.dextvcd");
        if (dataCacheFile.exists()) {
            try {
                FileChannel channel = new FileInputStream(dataCacheFile).getChannel();
                ByteBuffer allocate = ByteBuffer.allocate((int) channel.size());
                while (channel.read(allocate) > 0) {
                }
                return m2093a(DeviceHelper.getInstance(MobSDK.getContext()).getModel(), allocate.array());
            } catch (Throwable th) {
                MobLog.getInstance().mo29770d(th, "[%s] %s", "DvcvClt", "Read cache error");
            }
        }
        return new HashMap<>();
    }

    /* renamed from: a */
    private HashMap<String, Object> m2093a(String str, byte[] bArr) {
        try {
            return new Hashon().fromJson(Data.AES128Decode(str, bArr));
        } catch (Throwable th) {
            MobLog.getInstance().mo29770d(th, "[%s] %s", "DvcvClt", "Decrypt error");
            return new HashMap<>();
        }
    }

    /* renamed from: b */
    private void m2096b(HashMap<String, Object> hashMap) {
        long H = C2308i.m2503H();
        long ae = C2262b.m2280ae() * 1000;
        if (H == 0) {
            H = C2262b.m2260a() + ae;
            C2308i.m2551k(H);
        }
        if (C2262b.m2260a() >= H) {
            m2097c(hashMap);
        }
    }

    /* renamed from: c */
    private void m2097c(HashMap<String, Object> hashMap) {
        if (hashMap != null) {
            try {
                if (!hashMap.isEmpty()) {
                    long a = C2262b.m2260a();
                    HashMap hashMap2 = new HashMap();
                    hashMap2.putAll(hashMap);
                    HashMap hashMap3 = new HashMap();
                    hashMap3.put("type", "DEVEXT_VAR");
                    hashMap3.put(ShareConstants.WEB_DIALOG_PARAM_DATA, hashMap2);
                    hashMap3.put("datetime", Long.valueOf(a));
                    C2293c.m2435a().mo29068a(a, (HashMap<String, Object>) hashMap3);
                    this.f1980a.clear();
                    m2094a(this.f1980a);
                    C2308i.m2551k(a + (C2262b.m2280ae() * 1000));
                }
            } catch (Throwable th) {
                NLog instance = MobLog.getInstance();
                instance.mo29770d(th, th.getMessage() + "", new Object[0]);
            }
        }
    }
}
