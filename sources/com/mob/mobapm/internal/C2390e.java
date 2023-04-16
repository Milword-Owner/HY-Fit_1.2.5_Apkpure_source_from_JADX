package com.mob.mobapm.internal;

import android.os.Build;
import com.baidubce.http.Headers;
import com.baidubce.util.Mimetypes;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.HttpConnection;
import com.mob.tools.network.HttpResponseCallback;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.RawNetworkCallback;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ReflectHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.text.Typography;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;

/* renamed from: com.mob.mobapm.internal.e */
public class C2390e {

    /* renamed from: b */
    private static boolean f2311b = true;

    /* renamed from: a */
    protected boolean f2312a = f2311b;

    /* renamed from: com.mob.mobapm.internal.e$a */
    class C2391a implements RawNetworkCallback {

        /* renamed from: a */
        final /* synthetic */ byte[] f2313a;

        /* renamed from: b */
        final /* synthetic */ OutputStream f2314b;

        C2391a(C2390e eVar, byte[] bArr, OutputStream outputStream) {
            this.f2313a = bArr;
            this.f2314b = outputStream;
        }

        public void onResponse(InputStream inputStream) throws Throwable {
            int read = inputStream.read(this.f2313a);
            while (read != -1) {
                this.f2314b.write(this.f2313a, 0, read);
                read = inputStream.read(this.f2313a);
            }
        }
    }

    /* renamed from: com.mob.mobapm.internal.e$b */
    class C2392b implements HttpResponseCallback {

        /* renamed from: a */
        final /* synthetic */ HashMap f2315a;

        C2392b(C2390e eVar, HashMap hashMap) {
            this.f2315a = hashMap;
        }

        public void onResponse(HttpConnection httpConnection) throws Throwable {
            this.f2315a.put("code", String.valueOf(httpConnection.getResponseCode()));
        }
    }

    /* renamed from: com.mob.mobapm.internal.e$c */
    public static class C2393c {

        /* renamed from: a */
        public int f2316a;

        /* renamed from: b */
        public int f2317b;
    }

    /* renamed from: com.mob.mobapm.internal.e$d */
    public static final class C2394d implements X509TrustManager {

        /* renamed from: a */
        private X509TrustManager f2318a;

        public C2394d(KeyStore keyStore) {
            try {
                TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
                instance.init(keyStore);
                TrustManager[] trustManagers = instance.getTrustManagers();
                if (trustManagers == null || trustManagers.length == 0) {
                    throw new NoSuchAlgorithmException("no trust manager found.");
                }
                this.f2318a = (X509TrustManager) trustManagers[0];
            } catch (Exception e) {
                NLog instance2 = MobLog.getInstance();
                instance2.mo29768d("failed to initialize the standard trust manager: " + e.getMessage(), new Object[0]);
                this.f2318a = null;
            }
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            if (x509CertificateArr == null) {
                throw new IllegalArgumentException("there were no certificates.");
            } else if (x509CertificateArr.length == 1) {
                x509CertificateArr[0].checkValidity();
            } else {
                X509TrustManager x509TrustManager = this.f2318a;
                if (x509TrustManager != null) {
                    x509TrustManager.checkServerTrusted(x509CertificateArr, str);
                    return;
                }
                throw new CertificateException("there were one more certificates but no trust manager found.");
            }
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /* renamed from: a */
    public String mo29308a(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, C2393c cVar) throws Throwable {
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().mo29775i("httpGet: " + str, new Object[0]);
        if (arrayList != null) {
            String a = m2866a(arrayList);
            if (a.length() > 0) {
                str = str + "?" + a;
            }
        }
        HttpURLConnection a2 = m2867a(str, cVar);
        if (arrayList2 != null) {
            Iterator<KVPair<String>> it = arrayList2.iterator();
            while (it.hasNext()) {
                KVPair next = it.next();
                a2.setRequestProperty(next.name, (String) next.value);
            }
        }
        a2.setInstanceFollowRedirects(this.f2312a);
        a2.connect();
        int responseCode = a2.getResponseCode();
        if (responseCode == 200) {
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(a2.getInputStream(), Charset.forName("utf-8")));
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                if (sb.length() > 0) {
                    sb.append(10);
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            a2.disconnect();
            String sb2 = sb.toString();
            MobLog.getInstance().mo29775i("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return sb2;
        }
        StringBuilder sb3 = new StringBuilder();
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(a2.getErrorStream(), Charset.forName("utf-8")));
        for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
            if (sb3.length() > 0) {
                sb3.append(10);
            }
            sb3.append(readLine2);
        }
        bufferedReader2.close();
        a2.disconnect();
        HashMap hashMap = new HashMap();
        hashMap.put("error", sb3.toString());
        hashMap.put("status", Integer.valueOf(responseCode));
        throw new Throwable(new Hashon().fromHashMap(hashMap));
    }

    /* renamed from: b */
    public HashMap<String, Object> mo29317b(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, C2393c cVar) throws Throwable {
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().mo29775i("httpGet: " + str, new Object[0]);
        if (arrayList != null) {
            String a = m2866a(arrayList);
            if (a.length() > 0) {
                str = str + "?" + a;
            }
        }
        HttpURLConnection a2 = m2867a(str, cVar);
        if (arrayList2 != null) {
            Iterator<KVPair<String>> it = arrayList2.iterator();
            while (it.hasNext()) {
                KVPair next = it.next();
                a2.setRequestProperty(next.name, (String) next.value);
            }
        }
        a2.setInstanceFollowRedirects(this.f2312a);
        a2.connect();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code", String.valueOf(a2.getResponseCode()));
        MobLog.getInstance().mo29775i("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
        return hashMap;
    }

    /* renamed from: a */
    public boolean mo29316a(String str, OutputStream outputStream, C2393c cVar) throws Throwable {
        mo29312a(str, (RawNetworkCallback) new C2391a(this, new byte[1024], outputStream), cVar);
        outputStream.flush();
        return true;
    }

    /* renamed from: a */
    public void mo29312a(String str, RawNetworkCallback rawNetworkCallback, C2393c cVar) throws Throwable {
        mo29314a(str, (ArrayList<KVPair<String>>) null, rawNetworkCallback, cVar);
    }

    /* renamed from: a */
    public void mo29314a(String str, ArrayList<KVPair<String>> arrayList, RawNetworkCallback rawNetworkCallback, C2393c cVar) throws Throwable {
        long currentTimeMillis = System.currentTimeMillis();
        NLog instance = MobLog.getInstance();
        instance.mo29775i("rawGet: " + str, new Object[0]);
        HttpURLConnection a = m2867a(str, cVar);
        if (arrayList != null) {
            Iterator<KVPair<String>> it = arrayList.iterator();
            while (it.hasNext()) {
                KVPair next = it.next();
                a.setRequestProperty(next.name, (String) next.value);
            }
        }
        a.setInstanceFollowRedirects(this.f2312a);
        a.connect();
        int responseCode = a.getResponseCode();
        if (responseCode == 200) {
            if (rawNetworkCallback != null) {
                rawNetworkCallback.onResponse(a.getInputStream());
            }
            a.disconnect();
            NLog instance2 = MobLog.getInstance();
            instance2.mo29775i("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return;
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(a.getErrorStream(), Charset.forName("utf-8")));
        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
            if (sb.length() > 0) {
                sb.append(10);
            }
            sb.append(readLine);
        }
        bufferedReader.close();
        a.disconnect();
        HashMap hashMap = new HashMap();
        hashMap.put("error", sb.toString());
        hashMap.put("status", Integer.valueOf(responseCode));
        throw new Throwable(new Hashon().fromHashMap(hashMap));
    }

    /* renamed from: a */
    public HashMap<String, String> mo29310a(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, C2393c cVar) throws Throwable {
        return mo29309a(str, arrayList, kVPair, arrayList2, 0, cVar);
    }

    /* renamed from: a */
    public HashMap<String, String> mo29309a(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, int i, C2393c cVar) throws Throwable {
        ArrayList arrayList3 = new ArrayList();
        if (!(kVPair == null || kVPair.value == null || !new File((String) kVPair.value).exists())) {
            arrayList3.add(kVPair);
        }
        return mo29311a(str, arrayList, (ArrayList<KVPair<String>>) arrayList3, arrayList2, i, cVar);
    }

    /* renamed from: a */
    public HashMap<String, String> mo29311a(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, ArrayList<KVPair<String>> arrayList3, int i, C2393c cVar) throws Throwable {
        HashMap<String, String> hashMap = new HashMap<>();
        mo29315a(str, arrayList, arrayList2, arrayList3, i, new C2392b(this, hashMap), cVar);
        return hashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a3, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a4, code lost:
        r13.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a7, code lost:
        throw r7;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo29315a(java.lang.String r7, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r8, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r9, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r10, int r11, com.mob.tools.network.HttpResponseCallback r12, com.mob.mobapm.internal.C2390e.C2393c r13) throws java.lang.Throwable {
        /*
            r6 = this;
            long r0 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "httpPost: "
            r3.append(r4)
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            r4 = 0
            java.lang.Object[] r5 = new java.lang.Object[r4]
            r2.mo29775i(r3, r5)
            java.net.HttpURLConnection r13 = r6.m2867a(r7, r13)
            r2 = 1
            r13.setDoOutput(r2)
            java.lang.String r2 = "Connection"
            java.lang.String r3 = "Keep-Alive"
            r13.setRequestProperty(r2, r3)
            if (r9 == 0) goto L_0x0040
            int r2 = r9.size()
            if (r2 <= 0) goto L_0x0040
            com.mob.mobapm.internal.b r7 = r6.m2865a((java.net.HttpURLConnection) r13, (java.lang.String) r7, (java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>>) r8, (java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>>) r9)
            if (r11 < 0) goto L_0x004c
            r13.setChunkedStreamingMode(r11)
            goto L_0x004c
        L_0x0040:
            com.mob.mobapm.internal.b r7 = r6.m2864a((java.net.HttpURLConnection) r13, (java.lang.String) r7, (java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>>) r8)
            long r8 = r7.mo29297b()
            int r9 = (int) r8
            r13.setFixedLengthStreamingMode(r9)
        L_0x004c:
            if (r10 == 0) goto L_0x0068
            java.util.Iterator r8 = r10.iterator()
        L_0x0052:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x0068
            java.lang.Object r9 = r8.next()
            com.mob.tools.network.KVPair r9 = (com.mob.tools.network.KVPair) r9
            java.lang.String r10 = r9.name
            T r9 = r9.value
            java.lang.String r9 = (java.lang.String) r9
            r13.setRequestProperty(r10, r9)
            goto L_0x0052
        L_0x0068:
            boolean r8 = r6.f2312a
            r13.setInstanceFollowRedirects(r8)
            r13.connect()
            java.io.OutputStream r8 = r13.getOutputStream()
            java.io.InputStream r7 = r7.mo29299c()
            r9 = 65536(0x10000, float:9.18355E-41)
            byte[] r9 = new byte[r9]
            int r10 = r7.read(r9)
        L_0x0080:
            if (r10 <= 0) goto L_0x008a
            r8.write(r9, r4, r10)
            int r10 = r7.read(r9)
            goto L_0x0080
        L_0x008a:
            r8.flush()
            r7.close()
            r8.close()
            if (r12 == 0) goto L_0x00a8
            com.mob.tools.network.HttpConnectionImpl23 r7 = new com.mob.tools.network.HttpConnectionImpl23     // Catch:{ all -> 0x00a1 }
            r7.<init>(r13)     // Catch:{ all -> 0x00a1 }
            r12.onResponse(r7)     // Catch:{ all -> 0x00a1 }
            r13.disconnect()
            goto L_0x00ab
        L_0x00a1:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x00a3 }
        L_0x00a3:
            r7 = move-exception
            r13.disconnect()
            throw r7
        L_0x00a8:
            r13.disconnect()
        L_0x00ab:
            com.mob.tools.log.NLog r7 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "use time: "
            r8.append(r9)
            long r9 = java.lang.System.currentTimeMillis()
            long r9 = r9 - r0
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            java.lang.Object[] r9 = new java.lang.Object[r4]
            r7.mo29775i(r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.mobapm.internal.C2390e.mo29315a(java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, int, com.mob.tools.network.HttpResponseCallback, com.mob.mobapm.internal.e$c):void");
    }

    /* renamed from: a */
    private C2387b m2865a(HttpURLConnection httpURLConnection, String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2) throws Throwable {
        String uuid = UUID.randomUUID().toString();
        httpURLConnection.setRequestProperty(Headers.CONTENT_TYPE, "multipart/form-data; boundary=" + uuid);
        C2388c cVar = new C2388c();
        C2395f fVar = new C2395f();
        if (arrayList != null) {
            Iterator<KVPair<String>> it = arrayList.iterator();
            while (it.hasNext()) {
                KVPair next = it.next();
                fVar.mo29322a("--").mo29322a(uuid).mo29322a("\r\n");
                fVar.mo29322a("Content-Disposition: form-data; name=\"").mo29322a(next.name).mo29322a("\"\r\n\r\n");
                fVar.mo29322a((String) next.value).mo29322a("\r\n");
            }
        }
        cVar.mo29300a(fVar);
        Iterator<KVPair<String>> it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            KVPair next2 = it2.next();
            C2395f fVar2 = new C2395f();
            File file = new File((String) next2.value);
            fVar2.mo29322a("--").mo29322a(uuid).mo29322a("\r\n");
            fVar2.mo29322a("Content-Disposition: form-data; name=\"").mo29322a(next2.name).mo29322a("\"; filename=\"").mo29322a(file.getName()).mo29322a("\"\r\n");
            String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor((String) next2.value);
            if (contentTypeFor == null || contentTypeFor.length() <= 0) {
                if (((String) next2.value).toLowerCase().endsWith("jpg") || ((String) next2.value).toLowerCase().endsWith("jpeg")) {
                    contentTypeFor = "image/jpeg";
                } else if (((String) next2.value).toLowerCase().endsWith("png")) {
                    contentTypeFor = "image/png";
                } else if (((String) next2.value).toLowerCase().endsWith("gif")) {
                    contentTypeFor = "image/gif";
                } else {
                    FileInputStream fileInputStream = new FileInputStream((String) next2.value);
                    String guessContentTypeFromStream = URLConnection.guessContentTypeFromStream(fileInputStream);
                    fileInputStream.close();
                    contentTypeFor = (guessContentTypeFromStream == null || guessContentTypeFromStream.length() <= 0) ? Mimetypes.MIMETYPE_OCTET_STREAM : guessContentTypeFromStream;
                }
            }
            fVar2.mo29322a("Content-Type: ").mo29322a(contentTypeFor).mo29322a("\r\n\r\n");
            cVar.mo29300a(fVar2);
            C2386a aVar = new C2386a();
            aVar.mo29296a((String) next2.value);
            cVar.mo29300a(aVar);
            C2395f fVar3 = new C2395f();
            fVar3.mo29322a("\r\n");
            cVar.mo29300a(fVar3);
        }
        C2395f fVar4 = new C2395f();
        fVar4.mo29322a("--").mo29322a(uuid).mo29322a("--\r\n");
        cVar.mo29300a(fVar4);
        return cVar;
    }

    /* renamed from: a */
    private C2387b m2864a(HttpURLConnection httpURLConnection, String str, ArrayList<KVPair<String>> arrayList) throws Throwable {
        httpURLConnection.setRequestProperty(Headers.CONTENT_TYPE, "application/x-www-form-urlencoded");
        C2395f fVar = new C2395f();
        if (arrayList != null) {
            fVar.mo29322a(m2866a(arrayList));
        }
        return fVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0083, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0084, code lost:
        r7.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0087, code lost:
        throw r8;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo29313a(java.lang.String r7, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r8, com.mob.mobapm.internal.C2387b r9, int r10, com.mob.tools.network.HttpResponseCallback r11, com.mob.mobapm.internal.C2390e.C2393c r12) throws java.lang.Throwable {
        /*
            r6 = this;
            long r0 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "rawpost: "
            r3.append(r4)
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            r4 = 0
            java.lang.Object[] r5 = new java.lang.Object[r4]
            r2.mo29775i(r3, r5)
            java.net.HttpURLConnection r7 = r6.m2867a(r7, r12)
            r12 = 1
            r7.setDoOutput(r12)
            if (r10 < 0) goto L_0x002c
            r7.setChunkedStreamingMode(r4)
        L_0x002c:
            if (r8 == 0) goto L_0x0048
            java.util.Iterator r8 = r8.iterator()
        L_0x0032:
            boolean r10 = r8.hasNext()
            if (r10 == 0) goto L_0x0048
            java.lang.Object r10 = r8.next()
            com.mob.tools.network.KVPair r10 = (com.mob.tools.network.KVPair) r10
            java.lang.String r12 = r10.name
            T r10 = r10.value
            java.lang.String r10 = (java.lang.String) r10
            r7.setRequestProperty(r12, r10)
            goto L_0x0032
        L_0x0048:
            boolean r8 = r6.f2312a
            r7.setInstanceFollowRedirects(r8)
            r7.connect()
            java.io.OutputStream r8 = r7.getOutputStream()
            java.io.InputStream r9 = r9.mo29299c()
            r10 = 65536(0x10000, float:9.18355E-41)
            byte[] r10 = new byte[r10]
            int r12 = r9.read(r10)
        L_0x0060:
            if (r12 <= 0) goto L_0x006a
            r8.write(r10, r4, r12)
            int r12 = r9.read(r10)
            goto L_0x0060
        L_0x006a:
            r8.flush()
            r9.close()
            r8.close()
            if (r11 == 0) goto L_0x0088
            com.mob.tools.network.HttpConnectionImpl23 r8 = new com.mob.tools.network.HttpConnectionImpl23     // Catch:{ all -> 0x0081 }
            r8.<init>(r7)     // Catch:{ all -> 0x0081 }
            r11.onResponse(r8)     // Catch:{ all -> 0x0081 }
            r7.disconnect()
            goto L_0x008b
        L_0x0081:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0083 }
        L_0x0083:
            r8 = move-exception
            r7.disconnect()
            throw r8
        L_0x0088:
            r7.disconnect()
        L_0x008b:
            com.mob.tools.log.NLog r7 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "use time: "
            r8.append(r9)
            long r9 = java.lang.System.currentTimeMillis()
            long r9 = r9 - r0
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            java.lang.Object[] r9 = new java.lang.Object[r4]
            r7.mo29775i(r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.mobapm.internal.C2390e.mo29313a(java.lang.String, java.util.ArrayList, com.mob.mobapm.internal.b, int, com.mob.tools.network.HttpResponseCallback, com.mob.mobapm.internal.e$c):void");
    }

    /* renamed from: a */
    private String m2866a(ArrayList<KVPair<String>> arrayList) throws Throwable {
        StringBuilder sb = new StringBuilder();
        Iterator<KVPair<String>> it = arrayList.iterator();
        while (it.hasNext()) {
            KVPair next = it.next();
            String urlEncode = Data.urlEncode(next.name, "utf-8");
            T t = next.value;
            String urlEncode2 = t != null ? Data.urlEncode((String) t, "utf-8") : "";
            if (sb.length() > 0) {
                sb.append(Typography.amp);
            }
            sb.append(urlEncode);
            sb.append('=');
            sb.append(urlEncode2);
        }
        return sb.toString();
    }

    /* renamed from: a */
    private HttpURLConnection m2867a(String str, C2393c cVar) throws Throwable {
        Object obj;
        String str2;
        boolean z;
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        try {
            obj = ReflectHelper.getInstanceField(httpURLConnection, "methodTokens");
        } catch (Throwable unused) {
            obj = null;
        }
        if (obj == null) {
            try {
                obj = ReflectHelper.getStaticField("HttpURLConnection", "PERMITTED_USER_METHODS");
            } catch (Throwable unused2) {
            }
            str2 = "PERMITTED_USER_METHODS";
            z = true;
        } else {
            str2 = "methodTokens";
            z = false;
        }
        if (obj != null) {
            String[] strArr = (String[]) obj;
            String[] strArr2 = new String[(strArr.length + 1)];
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
            strArr2[strArr.length] = "PATCH";
            if (z) {
                ReflectHelper.setStaticField("HttpURLConnection", str2, strArr2);
            } else {
                ReflectHelper.setInstanceField(httpURLConnection, str2, strArr2);
            }
        }
        if (Build.VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", "false");
        }
        if (httpURLConnection instanceof HttpsURLConnection) {
            X509HostnameVerifier x509HostnameVerifier = SSLSocketFactory.STRICT_HOSTNAME_VERIFIER;
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init((KeyManager[]) null, new TrustManager[]{new C2394d((KeyStore) null)}, new SecureRandom());
            httpsURLConnection.setSSLSocketFactory(instance.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(x509HostnameVerifier);
        }
        int i = cVar == null ? NetworkHelper.connectionTimeout : cVar.f2317b;
        if (i > 0) {
            httpURLConnection.setConnectTimeout(i);
        }
        int i2 = cVar == null ? NetworkHelper.readTimout : cVar.f2316a;
        if (i2 > 0) {
            httpURLConnection.setReadTimeout(i2);
        }
        return httpURLConnection;
    }
}
