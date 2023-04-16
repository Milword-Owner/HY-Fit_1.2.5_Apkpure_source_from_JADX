package com.mob.mobapm.internal;

import android.text.TextUtils;
import android.util.Base64;
import com.baidubce.http.Headers;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.mob.MobSDK;
import com.mob.commons.MobProductCollector;
import com.mob.mobapm.internal.C2390e;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.HttpConnection;
import com.mob.tools.network.HttpResponseCallback;
import com.mob.tools.network.KVPair;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.MobRSA;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

public final class APMMobCommunicator {

    /* renamed from: a */
    private Random f2295a = new Random();

    /* renamed from: b */
    private BigInteger f2296b;

    /* renamed from: c */
    private BigInteger f2297c;

    /* renamed from: d */
    private MobRSA f2298d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public Hashon f2299e;

    /* renamed from: f */
    private C2390e f2300f;

    /* renamed from: g */
    private C2390e.C2393c f2301g;

    public static class Callback<T> implements PublicMemberKeeper {
        public void onResultError(Throwable th) {
        }

        public void onResultOk(T t) {
        }
    }

    public static class NetworkError extends Exception implements PublicMemberKeeper {
        private static final long serialVersionUID = -8447657431687664787L;

        public NetworkError(String str) {
            super(str);
        }
    }

    /* renamed from: com.mob.mobapm.internal.APMMobCommunicator$a */
    class C2384a implements HttpResponseCallback {

        /* renamed from: a */
        final /* synthetic */ String[] f2302a;

        C2384a(APMMobCommunicator aPMMobCommunicator, String[] strArr) {
            this.f2302a = strArr;
        }

        public void onResponse(HttpConnection httpConnection) throws Throwable {
            this.f2302a[0] = String.valueOf(httpConnection.getResponseCode());
        }
    }

    /* renamed from: com.mob.mobapm.internal.APMMobCommunicator$b */
    class C2385b implements HttpResponseCallback {

        /* renamed from: a */
        final /* synthetic */ String[] f2303a;

        C2385b(String[] strArr) {
            this.f2303a = strArr;
        }

        public void onResponse(HttpConnection httpConnection) throws Throwable {
            int responseCode = httpConnection.getResponseCode();
            InputStream inputStream = responseCode == 200 ? httpConnection.getInputStream() : httpConnection.getErrorStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            for (int read = inputStream.read(bArr); read != -1; read = inputStream.read(bArr)) {
                byteArrayOutputStream.write(bArr, 0, read);
            }
            inputStream.close();
            byteArrayOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (responseCode == 200) {
                this.f2303a[0] = new String(byteArray, "utf-8");
                return;
            }
            HashMap fromJson = APMMobCommunicator.this.f2299e.fromJson(new String(byteArray, "utf-8"));
            fromJson.put("httpStatus", Integer.valueOf(responseCode));
            throw new NetworkError(APMMobCommunicator.this.f2299e.fromHashMap(fromJson));
        }
    }

    public APMMobCommunicator(int i, String str, String str2) {
        this.f2298d = new MobRSA(i);
        this.f2296b = new BigInteger(str, 16);
        this.f2297c = new BigInteger(str2, 16);
        this.f2299e = new Hashon();
        this.f2300f = new C2390e();
        C2390e.C2393c cVar = new C2390e.C2393c();
        this.f2301g = cVar;
        cVar.f2316a = 30000;
        cVar.f2317b = FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS;
    }

    /* renamed from: b */
    private ArrayList<KVPair<String>> m2842b() throws Throwable {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair(Headers.CONTENT_TYPE, "application/json"));
        arrayList.add(new KVPair("User-Identity", MobProductCollector.getUserIdentity()));
        if (!TextUtils.isEmpty(C2396g.m2883b().mo29324a())) {
            arrayList.add(new KVPair("moid", C2396g.m2883b().mo29324a()));
        }
        return arrayList;
    }

    /* renamed from: c */
    private Object m2843c(String str) throws Throwable {
        if (str != null) {
            HashMap fromJson = this.f2299e.fromJson(str.trim());
            if (!fromJson.isEmpty()) {
                Object obj = fromJson.get("res");
                if (obj == null) {
                    obj = fromJson.get(ShareConstants.WEB_DIALOG_PARAM_DATA);
                }
                return obj == null ? fromJson : obj;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("status", -1);
            hashMap.put("error", "response is empty");
            throw new NetworkError(this.f2299e.fromHashMap(hashMap));
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("status", -1);
        hashMap2.put("error", "response is empty");
        throw new NetworkError(this.f2299e.fromHashMap(hashMap2));
    }

    /* renamed from: a */
    public <T> T mo29286a(HashMap<String, Object> hashMap, String str, boolean z, boolean z2) throws Throwable {
        return mo29287a((HashMap<String, String>) null, hashMap, str, z, z2);
    }

    /* renamed from: a */
    public <T> T mo29287a(HashMap<String, String> hashMap, HashMap<String, Object> hashMap2, String str, boolean z, boolean z2) throws Throwable {
        return mo29289a(true, z2, hashMap, hashMap2, str, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000d, code lost:
        if (r11.length() == 0) goto L_0x000f;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T mo29289a(boolean r8, boolean r9, java.util.HashMap<java.lang.String, java.lang.String> r10, java.util.HashMap<java.lang.String, java.lang.Object> r11, java.lang.String r12, boolean r13) throws java.lang.Throwable {
        /*
            r7 = this;
            if (r11 != 0) goto L_0x0003
            goto L_0x000f
        L_0x0003:
            com.mob.tools.utils.Hashon r0 = r7.f2299e
            java.lang.String r11 = r0.fromHashMap(r11)
            int r0 = r11.length()
            if (r0 != 0) goto L_0x0011
        L_0x000f:
            java.lang.String r11 = "{}"
        L_0x0011:
            r4 = r11
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r5 = r12
            r6 = r13
            java.lang.Object r8 = r0.mo29288a((boolean) r1, (boolean) r2, (java.util.HashMap<java.lang.String, java.lang.String>) r3, (java.lang.String) r4, (java.lang.String) r5, (boolean) r6)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.mobapm.internal.APMMobCommunicator.mo29289a(boolean, boolean, java.util.HashMap, java.util.HashMap, java.lang.String, boolean):java.lang.Object");
    }

    /* renamed from: b */
    private Object m2841b(String str) throws Throwable {
        HashMap hashMap = new HashMap();
        hashMap.put("code", str);
        return hashMap;
    }

    /* renamed from: b */
    public <T> T mo29292b(String str, boolean z, HashMap<String, String> hashMap, String str2) throws Throwable {
        ArrayList<KVPair<String>> a = m2838a(z, hashMap);
        C2395f fVar = new C2395f();
        if (!TextUtils.isEmpty(str2)) {
            fVar.mo29322a(str2);
        }
        String[] strArr = new String[1];
        String str3 = str;
        this.f2300f.mo29313a(str3, a, (C2387b) fVar, -1, m2835a(strArr), this.f2301g);
        if (strArr[0] == null) {
            return null;
        }
        NLog instance = MobLog.getInstance();
        instance.mo29768d(">>> response code: " + strArr[0], new Object[0]);
        return m2841b(strArr[0]);
    }

    /* renamed from: a */
    public <T> T mo29288a(boolean z, boolean z2, HashMap<String, String> hashMap, String str, String str2, boolean z3) throws Throwable {
        byte[] a = m2839a();
        String a2 = m2837a(a, str, z3);
        int length = a2.getBytes("utf-8").length;
        ArrayList<KVPair<String>> a3 = m2838a(z, hashMap);
        String[] strArr = new String[1];
        HttpResponseCallback a4 = m2834a(a, strArr);
        C2395f fVar = new C2395f();
        fVar.mo29322a(a2);
        NLog instance = MobLog.getInstance();
        instance.mo29768d(">>>  request: " + str + "\nurl = " + str2 + "\nheader = " + a3.toString(), new Object[0]);
        if (z2) {
            ArrayList arrayList = new ArrayList();
            HashMap fromJson = this.f2299e.fromJson(str);
            for (String str3 : fromJson.keySet()) {
                arrayList.add(new KVPair(str3, String.valueOf(fromJson.get(str3))));
            }
            return this.f2300f.mo29308a(str2, (ArrayList<KVPair<String>>) arrayList, a3, this.f2301g);
        }
        this.f2300f.mo29313a(str2, a3, (C2387b) fVar, -1, a4, this.f2301g);
        if (strArr[0] == null) {
            return null;
        }
        NLog instance2 = MobLog.getInstance();
        instance2.mo29768d(">>> response: " + strArr[0], new Object[0]);
        return m2843c(strArr[0]);
    }

    /* renamed from: b */
    public <T> T mo29291b(String str, String str2, boolean z) throws Throwable {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str2)) {
            HashMap fromJson = this.f2299e.fromJson(str2);
            for (String str3 : fromJson.keySet()) {
                arrayList.add(new KVPair(str3, String.valueOf(fromJson.get(str3))));
            }
        }
        ArrayList<KVPair<String>> arrayList2 = null;
        if (z) {
            arrayList2 = m2838a(z, (HashMap<String, String>) null);
        }
        return this.f2300f.mo29317b(str, arrayList, arrayList2, this.f2301g);
    }

    /* renamed from: a */
    private byte[] m2840a(byte[] bArr) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new GZIPOutputStream(byteArrayOutputStream));
        bufferedOutputStream.write(bArr);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: a */
    private byte[] m2839a() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        this.f2295a.setSeed(System.currentTimeMillis());
        dataOutputStream.writeLong(this.f2295a.nextLong());
        dataOutputStream.writeLong(this.f2295a.nextLong());
        dataOutputStream.flush();
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: a */
    private String m2837a(byte[] bArr, String str, boolean z) throws Throwable {
        byte[] bytes = str.getBytes("utf-8");
        if (z) {
            bytes = m2840a(bytes);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        byte[] encode = this.f2298d.encode(bArr, this.f2296b, this.f2297c);
        dataOutputStream.writeInt(encode.length);
        dataOutputStream.write(encode);
        byte[] AES128Encode = Data.AES128Encode(bArr, bytes);
        dataOutputStream.writeInt(AES128Encode.length);
        dataOutputStream.write(AES128Encode);
        dataOutputStream.flush();
        dataOutputStream.close();
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
    }

    /* renamed from: a */
    private ArrayList<KVPair<String>> m2838a(boolean z, HashMap<String, String> hashMap) throws Throwable {
        ArrayList<KVPair<String>> b = z ? m2842b() : null;
        if (b == null) {
            b = new ArrayList<>();
        }
        if (hashMap != null && !hashMap.isEmpty()) {
            for (Map.Entry next : hashMap.entrySet()) {
                if (next != null) {
                    b.add(new KVPair((String) next.getKey(), next.getValue()));
                }
            }
        }
        return b;
    }

    /* renamed from: a */
    private HttpResponseCallback m2835a(String[] strArr) {
        return new C2384a(this, strArr);
    }

    /* renamed from: a */
    private HttpResponseCallback m2834a(byte[] bArr, String[] strArr) {
        return new C2385b(strArr);
    }

    /* renamed from: a */
    public <T> T mo29285a(String str, boolean z, HashMap<String, String> hashMap, String str2) throws Throwable {
        ArrayList<KVPair<String>> a = m2838a(z, hashMap);
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str2)) {
            HashMap fromJson = this.f2299e.fromJson(str2);
            for (String str3 : fromJson.keySet()) {
                arrayList.add(new KVPair(str3, String.valueOf(fromJson.get(str3))));
            }
        }
        return this.f2300f.mo29310a(str, arrayList, (KVPair<String>) null, a, this.f2301g);
    }

    /* renamed from: a */
    public HashMap<String, Object> mo29290a(String str) throws Throwable {
        File file = new File(MobSDK.getContext().getFilesDir(), "temp");
        File file2 = new File(file, "radar");
        if (!file2.exists()) {
            file.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code", this.f2300f.mo29316a(str, (OutputStream) fileOutputStream, this.f2301g) ? "200" : "-1");
        return hashMap;
    }

    /* renamed from: a */
    public <T> T mo29284a(String str, String str2, boolean z) throws Throwable {
        ArrayList arrayList = new ArrayList();
        HashMap fromJson = this.f2299e.fromJson(str2);
        for (String str3 : fromJson.keySet()) {
            arrayList.add(new KVPair(str3, String.valueOf(fromJson.get(str3))));
        }
        ArrayList<KVPair<String>> arrayList2 = null;
        if (z) {
            arrayList2 = m2838a(z, (HashMap<String, String>) null);
        }
        return this.f2300f.mo29308a(str, (ArrayList<KVPair<String>>) arrayList, arrayList2, this.f2301g);
    }
}
