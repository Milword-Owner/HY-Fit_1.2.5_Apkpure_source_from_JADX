package com.mob;

import android.util.Base64;
import com.baidubce.http.Headers;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.mob.commons.C2262b;
import com.mob.commons.C2312k;
import com.mob.commons.MobProductCollector;
import com.mob.commons.p024b.C2273d;
import com.mob.tools.MobLog;
import com.mob.tools.RxMob;
import com.mob.tools.log.NLog;
import com.mob.tools.network.HttpConnection;
import com.mob.tools.network.HttpResponseCallback;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.StringPart;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.MobRSA;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

public final class MobCommunicator implements PublicMemberKeeper {

    /* renamed from: a */
    private Random f1693a = new Random();

    /* renamed from: b */
    private BigInteger f1694b;

    /* renamed from: c */
    private BigInteger f1695c;

    /* renamed from: d */
    private MobRSA f1696d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public Hashon f1697e;

    /* renamed from: f */
    private NetworkHelper f1698f;

    /* renamed from: g */
    private NetworkHelper.NetworkTimeOut f1699g;

    public static class Callback<T> implements PublicMemberKeeper {
        public void onResultError(Throwable th) {
        }

        public void onResultOk(T t) {
        }
    }

    public MobCommunicator(int i, String str, String str2) {
        this.f1696d = new MobRSA(i);
        this.f1694b = new BigInteger(str, 16);
        this.f1695c = new BigInteger(str2, 16);
        this.f1697e = new Hashon();
        this.f1698f = new NetworkHelper();
        this.f1699g = new NetworkHelper.NetworkTimeOut();
        NetworkHelper.NetworkTimeOut networkTimeOut = this.f1699g;
        networkTimeOut.readTimout = 30000;
        networkTimeOut.connectionTimeout = FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS;
    }

    public <T> void request(HashMap<String, Object> hashMap, String str, boolean z, Callback<T> callback) {
        request(true, (HashMap<String, String>) null, hashMap, str, z, callback);
    }

    public <T> void request(HashMap<String, String> hashMap, HashMap<String, Object> hashMap2, String str, boolean z, Callback<T> callback) {
        request(true, hashMap, hashMap2, str, z, callback);
    }

    public <T> void request(boolean z, HashMap<String, String> hashMap, HashMap<String, Object> hashMap2, String str, boolean z2, final Callback<T> callback) {
        final boolean z3 = z;
        final HashMap<String, String> hashMap3 = hashMap;
        final HashMap<String, Object> hashMap4 = hashMap2;
        final String str2 = str;
        final boolean z4 = z2;
        RxMob.create(new RxMob.QuickSubscribe<T>() {
            /* access modifiers changed from: protected */
            public void doNext(RxMob.Subscriber<T> subscriber) throws Throwable {
                subscriber.onNext(!C2262b.m2276aa() ? MobCommunicator.this.requestSynchronized(z3, (HashMap<String, String>) hashMap3, (HashMap<String, Object>) hashMap4, str2, z4) : null);
            }
        }).subscribeOnNewThreadAndObserveOnUIThread(new RxMob.Subscriber<T>() {
            public void onNext(T t) {
                callback.onResultOk(t);
            }

            public void onError(Throwable th) {
                callback.onResultError(th);
            }
        });
    }

    public <T> T requestSynchronized(HashMap<String, Object> hashMap, String str, boolean z) throws Throwable {
        return requestSynchronized((HashMap<String, String>) null, hashMap, str, z);
    }

    public <T> T requestSynchronized(HashMap<String, String> hashMap, HashMap<String, Object> hashMap2, String str, boolean z) throws Throwable {
        return requestSynchronized(true, hashMap, hashMap2, str, z);
    }

    public <T> T requestSynchronized(String str, String str2, boolean z) throws Throwable {
        return requestSynchronized((HashMap<String, String>) null, str, str2, z);
    }

    public <T> T requestSynchronized(HashMap<String, String> hashMap, String str, String str2, boolean z) throws Throwable {
        return requestSynchronized(true, hashMap, str, str2, z);
    }

    public <T> T requestSynchronized(boolean z, HashMap<String, String> hashMap, HashMap<String, Object> hashMap2, String str, boolean z2) throws Throwable {
        String str2;
        if (hashMap2 != null) {
            String fromHashMap = this.f1697e.fromHashMap(hashMap2);
            if (fromHashMap.length() != 0) {
                str2 = fromHashMap;
                return requestSynchronized(z, hashMap, str2, str, z2);
            }
        }
        str2 = "{}";
        return requestSynchronized(z, hashMap, str2, str, z2);
    }

    public <T> T requestSynchronized(boolean z, HashMap<String, String> hashMap, String str, String str2, boolean z2) throws Throwable {
        byte[] a = m1923a();
        String a2 = m1918a(a, str, z2);
        ArrayList<KVPair<String>> a3 = m1921a(z, hashMap, str, a2.getBytes("utf-8").length);
        String[] strArr = new String[1];
        HttpResponseCallback a4 = m1914a(a, strArr);
        StringPart stringPart = new StringPart();
        stringPart.append(a2);
        NLog instance = MobLog.getInstance();
        instance.mo29768d(">>>  request: " + str + "\nurl = " + str2 + "\nheader = " + a3.toString(), new Object[0]);
        this.f1698f.rawPost(str2, a3, stringPart, -1, a4, this.f1699g);
        if (strArr[0] == null) {
            return null;
        }
        NLog instance2 = MobLog.getInstance();
        instance2.mo29768d(">>> response: " + strArr[0], new Object[0]);
        return m1916a(strArr[0]);
    }

    /* renamed from: a */
    private byte[] m1924a(byte[] bArr) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new GZIPOutputStream(byteArrayOutputStream));
        bufferedOutputStream.write(bArr);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: a */
    private byte[] m1923a() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        this.f1693a.setSeed(System.currentTimeMillis());
        dataOutputStream.writeLong(this.f1693a.nextLong());
        dataOutputStream.writeLong(this.f1693a.nextLong());
        dataOutputStream.flush();
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: a */
    private String m1918a(byte[] bArr, String str, boolean z) throws Throwable {
        byte[] bytes = str.getBytes("utf-8");
        if (z) {
            bytes = m1924a(bytes);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        byte[] encode = this.f1696d.encode(bArr, this.f1694b, this.f1695c);
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
    private ArrayList<KVPair<String>> m1921a(boolean z, HashMap<String, String> hashMap, String str, int i) throws Throwable {
        ArrayList<KVPair<String>> a = z ? m1920a(str, i) : null;
        if (a == null) {
            a = new ArrayList<>();
        }
        if (hashMap != null && !hashMap.isEmpty()) {
            for (Map.Entry next : hashMap.entrySet()) {
                if (next != null) {
                    a.add(new KVPair((String) next.getKey(), next.getValue()));
                }
            }
        }
        return a;
    }

    /* renamed from: a */
    private ArrayList<KVPair<String>> m1920a(String str, int i) throws Throwable {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair("sign", Data.MD5(str + MobSDK.getAppSecret())));
        arrayList.add(new KVPair("key", MobSDK.getAppkey()));
        arrayList.add(new KVPair(Headers.CONTENT_LENGTH, String.valueOf(i)));
        arrayList.add(new KVPair("User-Identity", MobProductCollector.getUserIdentity()));
        arrayList.add(new KVPair(C2312k.m2575a(68), C2273d.m2354d(MobSDK.getContext())));
        return arrayList;
    }

    /* renamed from: a */
    private HttpResponseCallback m1914a(final byte[] bArr, final String[] strArr) {
        return new HttpResponseCallback() {
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
                    long a = MobCommunicator.this.m1913a(httpConnection);
                    if (a == -1 || a != ((long) byteArray.length)) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("httpStatus", Integer.valueOf(responseCode));
                        hashMap.put("status", -2);
                        hashMap.put("error", "Illegal content length");
                        throw new NetworkError(MobCommunicator.this.f1697e.fromHashMap(hashMap));
                    }
                    strArr[0] = MobCommunicator.this.m1919a(bArr, byteArray);
                    return;
                }
                HashMap fromJson = MobCommunicator.this.f1697e.fromJson(new String(byteArray, "utf-8"));
                fromJson.put("httpStatus", Integer.valueOf(responseCode));
                throw new NetworkError(MobCommunicator.this.f1697e.fromHashMap(fromJson));
            }
        };
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public long m1913a(HttpConnection httpConnection) throws Throwable {
        List<String> a = m1922a(httpConnection, Headers.CONTENT_LENGTH);
        if (a == null || a.size() <= 0) {
            return -1;
        }
        return Long.parseLong(a.get(0));
    }

    /* renamed from: a */
    private List<String> m1922a(HttpConnection httpConnection, String str) throws Throwable {
        Map<String, List<String>> headerFields = httpConnection.getHeaderFields();
        if (headerFields == null || headerFields.isEmpty()) {
            return null;
        }
        for (String next : headerFields.keySet()) {
            if (next != null && next.equals(str)) {
                return headerFields.get(next);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public String m1919a(byte[] bArr, byte[] bArr2) throws Throwable {
        return new String(Data.AES128Decode(bArr, Base64.decode(bArr2, 2)), "utf-8");
    }

    /* renamed from: a */
    private Object m1916a(String str) throws Throwable {
        if (str != null) {
            HashMap fromJson = this.f1697e.fromJson(str.trim());
            if (!fromJson.isEmpty()) {
                Object obj = fromJson.get("res");
                if (obj == null) {
                    return fromJson.get(ShareConstants.WEB_DIALOG_PARAM_DATA);
                }
                return obj;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("status", -1);
            hashMap.put("error", "response is empty");
            throw new NetworkError(this.f1697e.fromHashMap(hashMap));
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("status", -1);
        hashMap2.put("error", "response is empty");
        throw new NetworkError(this.f1697e.fromHashMap(hashMap2));
    }

    public static class NetworkError extends Exception implements PublicMemberKeeper {
        private static final long serialVersionUID = -8447657431687664787L;

        public NetworkError(String str) {
            super(str);
        }
    }
}
