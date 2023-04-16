package com.mob.commons;

import android.content.ContentValues;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.SparseArray;
import com.baidu.mobstat.Config;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.share.internal.ShareConstants;
import com.mob.MobSDK;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.commons.p023a.C2226d;
import com.mob.commons.p024b.C2273d;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.MobRSA;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.SQLiteHelper;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.zip.GZIPOutputStream;
import p015io.reactivex.annotations.SchedulerSupport;

/* renamed from: com.mob.commons.c */
/* compiled from: DataHeap */
public class C2293c implements Handler.Callback {

    /* renamed from: a */
    private static C2293c f2104a;

    /* renamed from: b */
    private Handler f2105b = MobHandlerThread.newHandler("d", (Handler.Callback) this);

    /* renamed from: c */
    private SQLiteHelper.SingleTableDB f2106c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public Hashon f2107d = new Hashon();

    /* renamed from: e */
    private Random f2108e = new Random();
    /* access modifiers changed from: private */

    /* renamed from: f */
    public boolean f2109f = true;

    /* renamed from: a */
    public static synchronized C2293c m2435a() {
        C2293c cVar;
        synchronized (C2293c.class) {
            if (f2104a == null) {
                f2104a = new C2293c();
            }
            cVar = f2104a;
        }
        return cVar;
    }

    private C2293c() {
        m2439b();
        m2444c();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public synchronized SQLiteHelper.SingleTableDB m2439b() {
        File dataCacheFile;
        if (this.f2106c == null && (dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.dh")) != null) {
            if (dataCacheFile.length() > 209715200) {
                dataCacheFile.delete();
                dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.dh");
            }
            this.f2106c = SQLiteHelper.getDatabase(dataCacheFile.getAbsolutePath(), "DataHeap_1");
            this.f2106c.addField("time", ViewHierarchyConstants.TEXT_KEY, true);
            this.f2106c.addField(ShareConstants.WEB_DIALOG_PARAM_DATA, ViewHierarchyConstants.TEXT_KEY, true);
        }
        return this.f2106c;
    }

    /* renamed from: a */
    public synchronized void mo29068a(long j, HashMap<String, Object> hashMap) {
        if (!C2262b.m2276aa()) {
            Message message = new Message();
            message.what = 2;
            message.obj = new Object[]{Long.valueOf(j), hashMap};
            if (hashMap != null) {
                NLog instance = MobLog.getInstance();
                instance.mo29768d("type: " + hashMap.get("type"), new Object[0]);
            }
            this.f2105b.sendMessage(message);
        }
    }

    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            this.f2105b.removeMessages(1);
            if (!m2445d()) {
                m2444c();
            }
        } else if (i == 2) {
            Object[] objArr = (Object[]) message.obj;
            long longValue = ((Long) ResHelper.forceCast(objArr[0], -1L)).longValue();
            if (longValue > 0) {
                m2443b(longValue, (HashMap) objArr[1]);
                m2444c();
            }
        }
        return false;
    }

    /* renamed from: c */
    private void m2444c() {
        String networkType;
        if (!C2262b.m2276aa()) {
            long K = C2262b.m2244K();
            DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
            if (instance != null && ((networkType = instance.getNetworkType()) == null || SchedulerSupport.NONE.equals(networkType))) {
                K = 600000;
            }
            this.f2105b.sendEmptyMessageDelayed(1, K);
        }
    }

    /* renamed from: a */
    public void mo29069a(Object... objArr) {
        try {
            C2296d.m2449a().mo29071a(13);
            ResHelper.deleteFileAndFolder(m2441b(objArr));
            return;
        } catch (Throwable th) {
            C2296d.m2449a().mo29072a(4, th);
            return;
        }
        throw th;
    }

    /* renamed from: b */
    private File m2441b(Object... objArr) throws Throwable {
        int i;
        InputStream inputStream;
        String str = objArr[0];
        String str2 = objArr[1];
        String str3 = objArr[4];
        String str4 = objArr[5];
        File file = null;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            File file2 = new File(MobSDK.getContext().getFilesDir(), C2312k.m2575a(5));
            byte[] bArr = objArr[2];
            try {
                i = Integer.parseInt(String.valueOf(objArr[3]));
            } catch (Throwable unused) {
                i = 0;
            }
            if (bArr == null || i <= 0 || bArr.length < i || !str.equals(Data.MD5(bArr, 0, i))) {
                File file3 = new File(file2, C2312k.m2575a(14));
                if (!file3.exists() || !str.equals(Data.MD5(file3))) {
                    C2296d.m2449a().mo29071a(20);
                    file3.delete();
                    inputStream = null;
                } else {
                    inputStream = new FileInputStream(file3);
                }
            } else {
                inputStream = new ByteArrayInputStream(bArr, 0, i);
            }
            if (inputStream != null) {
                file = new File(file2, String.valueOf(System.currentTimeMillis()));
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file4 = new File(file, file.getName() + ".zip");
                FileOutputStream fileOutputStream = new FileOutputStream(file4);
                Data.AES128Decode(str2, inputStream, (OutputStream) fileOutputStream);
                inputStream.close();
                fileOutputStream.close();
                try {
                    DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
                    if (!instance.checkADBModel(17) || !instance.checkUA()) {
                        C2296d.m2449a().mo29071a(14);
                        C2226d.m2016a(str, file4, str3, str4);
                    } else {
                        C2296d.m2449a().mo29071a(19);
                    }
                    ResHelper.deleteFileAndFolder(file);
                } catch (Throwable th) {
                    C2296d.m2449a().mo29072a(4, th);
                }
            }
        }
        return file;
        throw th;
    }

    /* renamed from: b */
    private void m2443b(final long j, final HashMap<String, Object> hashMap) {
        Object obj;
        if (!C2300e.m2467a(C2300e.m2466a("comm/locks/.dhlock"), new LockAction() {
            public boolean run(FileLocker fileLocker) {
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("time", String.valueOf(j));
                    DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
                    if (hashMap != null) {
                        hashMap.put("appkey", MobSDK.getAppkey());
                        hashMap.put("apppkg", instance.getPackageName());
                        hashMap.put("appver", instance.getAppVersionName());
                        long ah = C2262b.m2283ah();
                        if (ah != 0) {
                            hashMap.put("strategyId", Long.valueOf(ah));
                        }
                    }
                    contentValues.put(ShareConstants.WEB_DIALOG_PARAM_DATA, Base64.encodeToString(Data.AES128Encode(Data.rawMD5(instance.getManufacturer()), C2293c.this.f2107d.fromHashMap(hashMap).getBytes("utf-8")), 2));
                    SQLiteHelper.insert(C2293c.this.m2439b(), contentValues);
                    return false;
                } catch (Throwable th) {
                    MobLog.getInstance().mo29787w(th);
                    return false;
                }
            }
        })) {
            NLog instance = MobLog.getInstance();
            StringBuilder sb = new StringBuilder();
            sb.append("DataHeap add log error data type = ");
            if (hashMap == null) {
                obj = null;
            } else {
                obj = hashMap.get("type");
            }
            sb.append(obj);
            sb.append(", updateTime = ");
            sb.append(j);
            instance.mo29772e(new Throwable(sb.toString()));
        }
    }

    /* renamed from: d */
    private boolean m2445d() {
        String networkType;
        if (C2262b.m2242I()) {
            return true;
        }
        DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
        if (instance == null || (networkType = instance.getNetworkType()) == null || SchedulerSupport.NONE.equals(networkType)) {
            return false;
        }
        this.f2109f = true;
        boolean a = C2300e.m2467a(C2300e.m2466a("comm/locks/.dhlock"), new LockAction() {
            public boolean run(FileLocker fileLocker) {
                String[][] strArr = new String[50][];
                int a = C2293c.this.m2432a(strArr);
                while (true) {
                    if (a <= 0) {
                        break;
                    }
                    SparseArray a2 = C2293c.this.m2434a(strArr, a);
                    if (a2 == null) {
                        boolean unused = C2293c.this.f2109f = false;
                        break;
                    }
                    if (a2.size() > 0) {
                        int unused2 = C2293c.this.m2429a((SparseArray<String>) a2);
                    }
                    if (a < strArr.length) {
                        break;
                    }
                    a = C2293c.this.m2432a(strArr);
                }
                return false;
            }
        });
        if (!this.f2109f || !a) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0040 A[SYNTHETIC, Splitter:B:20:0x0040] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int m2432a(java.lang.String[][] r10) {
        /*
            r9 = this;
            r0 = 0
            java.lang.String r1 = "time"
            java.lang.String r2 = "data"
            java.lang.String[] r1 = new java.lang.String[]{r1, r2}     // Catch:{ Throwable -> 0x0053 }
            com.mob.tools.utils.SQLiteHelper$SingleTableDB r2 = r9.m2439b()     // Catch:{ Throwable -> 0x0053 }
            r3 = 0
            android.database.Cursor r1 = com.mob.tools.utils.SQLiteHelper.query(r2, r1, r3, r3, r3)     // Catch:{ Throwable -> 0x0053 }
            if (r1 != 0) goto L_0x0015
            return r0
        L_0x0015:
            boolean r2 = r1.moveToFirst()     // Catch:{ Throwable -> 0x0053 }
            if (r2 != 0) goto L_0x001f
            r1.close()     // Catch:{ Throwable -> 0x0053 }
            return r0
        L_0x001f:
            long r2 = com.mob.commons.C2262b.m2260a()     // Catch:{ Throwable -> 0x0053 }
            r4 = 0
        L_0x0024:
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ Throwable -> 0x0051 }
            java.lang.String r6 = r1.getString(r0)     // Catch:{ Throwable -> 0x0051 }
            r5[r0] = r6     // Catch:{ Throwable -> 0x0051 }
            r6 = 1
            java.lang.String r7 = r1.getString(r6)     // Catch:{ Throwable -> 0x0051 }
            r5[r6] = r7     // Catch:{ Throwable -> 0x0051 }
            r6 = -1
            r8 = r5[r0]     // Catch:{ Throwable -> 0x003c }
            long r6 = java.lang.Long.parseLong(r8)     // Catch:{ Throwable -> 0x003c }
        L_0x003c:
            int r8 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r8 > 0) goto L_0x0044
            r10[r4] = r5     // Catch:{ Throwable -> 0x0051 }
            int r4 = r4 + 1
        L_0x0044:
            int r5 = r10.length     // Catch:{ Throwable -> 0x0051 }
            if (r4 >= r5) goto L_0x004d
            boolean r5 = r1.moveToNext()     // Catch:{ Throwable -> 0x0051 }
            if (r5 != 0) goto L_0x0024
        L_0x004d:
            r1.close()     // Catch:{ Throwable -> 0x0051 }
            goto L_0x005c
        L_0x0051:
            r10 = move-exception
            goto L_0x0055
        L_0x0053:
            r10 = move-exception
            r4 = 0
        L_0x0055:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            r0.mo29787w((java.lang.Throwable) r10)
        L_0x005c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C2293c.m2432a(java.lang.String[][]):int");
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public SparseArray<String> m2434a(String[][] strArr, int i) {
        try {
            SparseArray<String> sparseArray = new SparseArray<>();
            HashMap hashMap = new HashMap();
            DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
            hashMap.put("plat", Integer.valueOf(instance.getPlatformCode()));
            hashMap.put(Config.DEVICE_PART, instance.getDeviceKey());
            hashMap.put("mac", instance.getMacAddress());
            hashMap.put("model", instance.getModel());
            hashMap.put("duid", DeviceAuthorizer.authorize((MobProduct) null));
            hashMap.put("imei", instance.getIMEI());
            hashMap.put("serialno", instance.getSerialno());
            hashMap.put("networktype", instance.getDetailNetworkTypeForStatic());
            hashMap.put("dataNetworkType", Integer.valueOf(instance.getDataNtType()));
            ArrayList arrayList = new ArrayList();
            byte[] rawMD5 = Data.rawMD5(instance.getManufacturer());
            int i2 = 0;
            while (i2 < i) {
                String[] strArr2 = strArr[i2];
                HashMap fromJson = this.f2107d.fromJson(new String(Data.AES128Decode(rawMD5, Base64.decode(strArr2[1], 2)), "utf-8").trim());
                if (fromJson == null || fromJson.isEmpty() || m2438a((String) ResHelper.forceCast(fromJson.get("type"), null))) {
                    sparseArray.put(i2, strArr2[0]);
                    arrayList.add(fromJson);
                    i2++;
                } else {
                    i2++;
                }
            }
            if (arrayList.isEmpty()) {
                return new SparseArray<>();
            }
            hashMap.put("datas", arrayList);
            hashMap.put("token", C2313l.m2576a().mo29104b());
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new KVPair("appkey", MobSDK.getAppkey()));
            arrayList2.add(new KVPair(Config.MODEL, m2442b(this.f2107d.fromHashMap(hashMap))));
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(new KVPair("User-Identity", MobProductCollector.getUserIdentity()));
            arrayList3.add(new KVPair(C2312k.m2575a(68), C2273d.m2354d(MobSDK.getContext())));
            NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
            networkTimeOut.readTimout = 30000;
            networkTimeOut.connectionTimeout = 30000;
            if (!"200".equals(String.valueOf(this.f2107d.fromJson(new NetworkHelper().httpPost(m2446e(), (ArrayList<KVPair<String>>) arrayList2, (KVPair<String>) null, (ArrayList<KVPair<String>>) arrayList3, networkTimeOut)).get("status")))) {
                C2308i.m2532e((String) null);
            }
            return sparseArray;
        } catch (Throwable th) {
            C2308i.m2532e((String) null);
            MobLog.getInstance().mo29787w(th);
            return null;
        }
    }

    /* renamed from: a */
    private boolean m2438a(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (C2312k.m2575a(19).equals(str)) {
            return C2262b.m2311f();
        }
        if (C2312k.m2575a(20).equals(str)) {
            return C2262b.m2310e();
        }
        if (C2312k.m2575a(21).equals(str)) {
            return C2262b.m2312g();
        }
        if (C2312k.m2575a(22).equals(str)) {
            return C2262b.m2307c();
        }
        if (C2312k.m2575a(23).equals(str)) {
            return C2262b.m2315j();
        }
        if (C2312k.m2575a(24).equals(str)) {
            return C2262b.m2316k();
        }
        if (C2312k.m2575a(25).equals(str)) {
            return C2262b.m2318m();
        }
        if (C2312k.m2575a(26).equals(str)) {
            return C2262b.m2329x();
        }
        if (C2312k.m2575a(27).equals(str)) {
            return C2262b.m2320o();
        }
        if (C2312k.m2575a(28).equals(str)) {
            return C2262b.m2322q();
        }
        if (C2312k.m2575a(29).equals(str)) {
            return C2262b.m2328w();
        }
        if (C2312k.m2575a(30).equals(str)) {
            if (C2262b.m2234A() > 0) {
                return true;
            }
            return false;
        } else if (C2312k.m2575a(31).equals(str)) {
            return C2262b.m2235B();
        } else {
            if (C2312k.m2575a(32).equals(str)) {
                if (C2262b.m2239F() > 0) {
                    return true;
                }
                return false;
            } else if (C2312k.m2575a(33).equals(str)) {
                if (C2262b.m2241H() > 0) {
                    return true;
                }
                return false;
            } else if (C2312k.m2575a(34).equals(str)) {
                if (C2262b.m2246M() > 0) {
                    return true;
                }
                return false;
            } else if (C2312k.m2575a(35).equals(str)) {
                if (C2262b.m2247N() > 0) {
                    return true;
                }
                return false;
            } else if (C2312k.m2575a(36).equals(str)) {
                if (C2262b.m2248O() > 0) {
                    return true;
                }
                return false;
            } else if (C2312k.m2575a(37).equals(str)) {
                if (C2262b.m2250Q() > 0) {
                    return true;
                }
                return false;
            } else if (C2312k.m2575a(38).equals(str)) {
                return C2262b.m2252S();
            } else {
                if (C2312k.m2575a(39).equals(str)) {
                    if (C2262b.m2253T() > 0) {
                        return true;
                    }
                    return false;
                } else if (!C2312k.m2575a(40).equals(str) || C2262b.m2255V() > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    /* renamed from: b */
    private String m2442b(String str) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeLong(this.f2108e.nextLong());
        dataOutputStream.writeLong(this.f2108e.nextLong());
        dataOutputStream.flush();
        dataOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new GZIPOutputStream(byteArrayOutputStream2));
        bufferedOutputStream.write(str.getBytes("utf-8"));
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        byte[] AES128Encode = Data.AES128Encode(byteArray, byteArrayOutputStream2.toByteArray());
        byte[] encode = new MobRSA(1024).encode(byteArray, new BigInteger("ceeef5035212dfe7c6a0acdc0ef35ce5b118aab916477037d7381f85c6b6176fcf57b1d1c3296af0bb1c483fe5e1eb0ce9eb2953b44e494ca60777a1b033cc07", 16), new BigInteger("191737288d17e660c4b61440d5d14228a0bf9854499f9d68d8274db55d6d954489371ecf314f26bec236e58fac7fffa9b27bcf923e1229c4080d49f7758739e5bd6014383ed2a75ce1be9b0ab22f283c5c5e11216c5658ba444212b6270d629f2d615b8dfdec8545fb7d4f935b0cc10b6948ab4fc1cb1dd496a8f94b51e888dd", 16));
        ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream3);
        dataOutputStream2.writeInt(encode.length);
        dataOutputStream2.write(encode);
        dataOutputStream2.writeInt(AES128Encode.length);
        dataOutputStream2.write(AES128Encode);
        dataOutputStream2.flush();
        dataOutputStream2.close();
        return Base64.encodeToString(byteArrayOutputStream3.toByteArray(), 2);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public int m2429a(SparseArray<String> sparseArray) {
        StringBuilder sb;
        try {
            sb = new StringBuilder();
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append('\'');
                sb.append(sparseArray.valueAt(i));
                sb.append('\'');
            }
            SQLiteHelper.SingleTableDB b = m2439b();
            return SQLiteHelper.delete(b, "time in (" + sb.toString() + ")", (String[]) null);
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m2446e() {
        /*
            java.lang.String r0 = com.mob.commons.C2308i.m2537g()     // Catch:{ Throwable -> 0x000b }
            java.lang.String r0 = com.mob.commons.C2310j.m2573b(r0)     // Catch:{ Throwable -> 0x0009 }
            goto L_0x0014
        L_0x0009:
            r1 = move-exception
            goto L_0x000d
        L_0x000b:
            r1 = move-exception
            r0 = 0
        L_0x000d:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29787w((java.lang.Throwable) r1)
        L_0x0014:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x002f
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = com.mob.commons.C2310j.m2572b()
            r0.append(r1)
            java.lang.String r1 = "/v5/gcl"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L_0x002f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C2293c.m2446e():java.lang.String");
    }
}
