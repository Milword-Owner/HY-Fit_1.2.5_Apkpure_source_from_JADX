package com.mob.tools.network;

import android.content.Context;
import android.os.Build;
import com.baidubce.http.Headers;
import com.baidubce.util.Mimetypes;
import com.mob.mobapm.instrumentation.MobInstrumented;
import com.mob.mobapm.proxy.URLConnectionInstrumentation;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
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
import java.util.List;
import java.util.Map;
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

@MobInstrumented
public class NetworkHelper {
    public static int connectionTimeout = 0;
    private static boolean followRedirects = true;
    public static int readTimout;
    protected boolean instanceFollowRedirects = followRedirects;

    public static class NetworkTimeOut {
        public int connectionTimeout;
        public int readTimout;
    }

    private Object getHttpPatch(String str) throws Throwable {
        return null;
    }

    public String httpGet(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut) throws Throwable {
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().mo29775i("httpGet: " + str, new Object[0]);
        if (arrayList != null) {
            String kvPairsToUrl = kvPairsToUrl(arrayList);
            if (kvPairsToUrl.length() > 0) {
                str = str + "?" + kvPairsToUrl;
            }
        }
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        if (arrayList2 != null) {
            Iterator<KVPair<String>> it = arrayList2.iterator();
            while (it.hasNext()) {
                KVPair next = it.next();
                connection.setRequestProperty(next.name, (String) next.value);
            }
        }
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("utf-8")));
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                if (sb.length() > 0) {
                    sb.append(10);
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            connection.disconnect();
            String sb2 = sb.toString();
            MobLog.getInstance().mo29775i("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return sb2;
        }
        StringBuilder sb3 = new StringBuilder();
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(connection.getErrorStream(), Charset.forName("utf-8")));
        for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
            if (sb3.length() > 0) {
                sb3.append(10);
            }
            sb3.append(readLine2);
        }
        bufferedReader2.close();
        connection.disconnect();
        HashMap hashMap = new HashMap();
        hashMap.put("error", sb3.toString());
        hashMap.put("status", Integer.valueOf(responseCode));
        throw new Throwable(new Hashon().fromHashMap(hashMap));
    }

    public void download(String str, final OutputStream outputStream, NetworkTimeOut networkTimeOut) throws Throwable {
        final byte[] bArr = new byte[1024];
        rawGet(str, (RawNetworkCallback) new RawNetworkCallback() {
            public void onResponse(InputStream inputStream) throws Throwable {
                int read = inputStream.read(bArr);
                while (read != -1) {
                    outputStream.write(bArr, 0, read);
                    read = inputStream.read(bArr);
                }
            }
        }, networkTimeOut);
        outputStream.flush();
    }

    public String downloadCache(Context context, String str, String str2, boolean z, NetworkTimeOut networkTimeOut) throws Throwable {
        return downloadCache(context, str, str2, z, networkTimeOut, (FileDownloadListener) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:65:0x0190  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01cd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String downloadCache(android.content.Context r18, java.lang.String r19, java.lang.String r20, boolean r21, com.mob.tools.network.NetworkHelper.NetworkTimeOut r22, com.mob.tools.network.FileDownloadListener r23) throws java.lang.Throwable {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            r2 = r19
            r3 = r20
            long r8 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r4 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "downloading: "
            r5.append(r6)
            r5.append(r2)
            java.lang.String r5 = r5.toString()
            r10 = 0
            java.lang.Object[] r6 = new java.lang.Object[r10]
            r4.mo29775i(r5, r6)
            java.lang.String r11 = "use time: "
            if (r21 == 0) goto L_0x0079
            java.lang.String r4 = com.mob.tools.utils.ResHelper.getCachePath(r0, r3)
            java.lang.String r5 = com.mob.tools.utils.Data.MD5((java.lang.String) r19)
            java.io.File r6 = new java.io.File
            r6.<init>(r4, r5)
            if (r21 == 0) goto L_0x0079
            boolean r4 = r6.exists()
            if (r4 == 0) goto L_0x0079
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r11)
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r8
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.Object[] r3 = new java.lang.Object[r10]
            r0.mo29775i(r2, r3)
            if (r23 == 0) goto L_0x0074
            r0 = 100
            long r2 = r6.length()
            long r4 = r6.length()
            r18 = r23
            r19 = r0
            r20 = r2
            r22 = r4
            r18.onProgress(r19, r20, r22)
        L_0x0074:
            java.lang.String r0 = r6.getAbsolutePath()
            return r0
        L_0x0079:
            r4 = r22
            java.net.HttpURLConnection r12 = r1.getConnection(r2, r4)
            boolean r4 = r1.instanceFollowRedirects
            r12.setInstanceFollowRedirects(r4)
            r12.connect()
            int r4 = r12.getResponseCode()
            r5 = 200(0xc8, float:2.8E-43)
            if (r4 != r5) goto L_0x02ab
            java.util.Map r4 = r12.getHeaderFields()
            r5 = 1
            if (r4 == 0) goto L_0x00ec
            java.lang.String r7 = "Content-Disposition"
            java.lang.Object r7 = r4.get(r7)
            java.util.List r7 = (java.util.List) r7
            if (r7 == 0) goto L_0x00ec
            int r14 = r7.size()
            if (r14 <= 0) goto L_0x00ec
            java.lang.Object r7 = r7.get(r10)
            java.lang.String r7 = (java.lang.String) r7
            java.lang.String r14 = ";"
            java.lang.String[] r7 = r7.split(r14)
            int r14 = r7.length
            r15 = 0
            r16 = 0
        L_0x00b6:
            if (r15 >= r14) goto L_0x00ee
            r13 = r7[r15]
            java.lang.String r6 = r13.trim()
            java.lang.String r10 = "filename"
            boolean r6 = r6.startsWith(r10)
            if (r6 == 0) goto L_0x00e8
            java.lang.String r6 = "="
            java.lang.String[] r6 = r13.split(r6)
            r6 = r6[r5]
            java.lang.String r10 = "\""
            boolean r13 = r6.startsWith(r10)
            if (r13 == 0) goto L_0x00e6
            boolean r10 = r6.endsWith(r10)
            if (r10 == 0) goto L_0x00e6
            int r10 = r6.length()
            int r10 = r10 - r5
            java.lang.String r16 = r6.substring(r5, r10)
            goto L_0x00e8
        L_0x00e6:
            r16 = r6
        L_0x00e8:
            int r15 = r15 + 1
            r10 = 0
            goto L_0x00b6
        L_0x00ec:
            r16 = 0
        L_0x00ee:
            if (r16 != 0) goto L_0x017d
            java.lang.String r6 = com.mob.tools.utils.Data.MD5((java.lang.String) r19)
            if (r4 == 0) goto L_0x017f
            java.lang.String r7 = "Content-Type"
            java.lang.Object r4 = r4.get(r7)
            java.util.List r4 = (java.util.List) r4
            if (r4 == 0) goto L_0x017f
            int r7 = r4.size()
            if (r7 <= 0) goto L_0x017f
            r7 = 0
            java.lang.Object r4 = r4.get(r7)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 != 0) goto L_0x0112
            java.lang.String r4 = ""
            goto L_0x0116
        L_0x0112:
            java.lang.String r4 = r4.trim()
        L_0x0116:
            java.lang.String r7 = "image/"
            boolean r7 = r4.startsWith(r7)
            if (r7 == 0) goto L_0x0142
            r2 = 6
            java.lang.String r2 = r4.substring(r2)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r6)
            java.lang.String r5 = "."
            r4.append(r5)
            java.lang.String r5 = "jpeg"
            boolean r5 = r5.equals(r2)
            if (r5 == 0) goto L_0x013a
            java.lang.String r2 = "jpg"
        L_0x013a:
            r4.append(r2)
            java.lang.String r16 = r4.toString()
            goto L_0x017d
        L_0x0142:
            r4 = 47
            int r4 = r2.lastIndexOf(r4)
            if (r4 <= 0) goto L_0x0150
            int r4 = r4 + r5
            java.lang.String r13 = r2.substring(r4)
            goto L_0x0151
        L_0x0150:
            r13 = 0
        L_0x0151:
            if (r13 == 0) goto L_0x017f
            int r2 = r13.length()
            if (r2 <= 0) goto L_0x017f
            r2 = 46
            int r2 = r13.lastIndexOf(r2)
            if (r2 <= 0) goto L_0x017f
            int r4 = r13.length()
            int r4 = r4 - r2
            r5 = 10
            if (r4 >= r5) goto L_0x017f
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r6)
            java.lang.String r2 = r13.substring(r2)
            r4.append(r2)
            java.lang.String r16 = r4.toString()
        L_0x017d:
            r6 = r16
        L_0x017f:
            java.lang.String r0 = com.mob.tools.utils.ResHelper.getCachePath(r0, r3)
            java.io.File r10 = new java.io.File
            r10.<init>(r0, r6)
            if (r21 == 0) goto L_0x01cd
            boolean r0 = r10.exists()
            if (r0 == 0) goto L_0x01cd
            r12.disconnect()
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r11)
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r8
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r0.mo29775i(r2, r3)
            if (r23 == 0) goto L_0x01c8
            r0 = 100
            long r2 = r10.length()
            long r4 = r10.length()
            r18 = r23
            r19 = r0
            r20 = r2
            r22 = r4
            r18.onProgress(r19, r20, r22)
        L_0x01c8:
            java.lang.String r0 = r10.getAbsolutePath()
            return r0
        L_0x01cd:
            java.io.File r0 = r10.getParentFile()
            boolean r0 = r0.exists()
            if (r0 != 0) goto L_0x01de
            java.io.File r0 = r10.getParentFile()
            r0.mkdirs()
        L_0x01de:
            boolean r0 = r10.exists()
            if (r0 == 0) goto L_0x01e7
            r10.delete()
        L_0x01e7:
            if (r23 == 0) goto L_0x01fa
            boolean r0 = r23.isCanceled()     // Catch:{ Throwable -> 0x02a0 }
            if (r0 == 0) goto L_0x01fa
            boolean r0 = r10.exists()     // Catch:{ Throwable -> 0x02a0 }
            if (r0 == 0) goto L_0x01f8
            r10.delete()     // Catch:{ Throwable -> 0x02a0 }
        L_0x01f8:
            r0 = 0
            return r0
        L_0x01fa:
            java.io.InputStream r0 = r12.getInputStream()     // Catch:{ Throwable -> 0x02a0 }
            int r13 = r12.getContentLength()     // Catch:{ Throwable -> 0x02a0 }
            java.io.FileOutputStream r14 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x02a0 }
            r14.<init>(r10)     // Catch:{ Throwable -> 0x02a0 }
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r15 = new byte[r2]     // Catch:{ Throwable -> 0x02a0 }
            int r2 = r0.read(r15)     // Catch:{ Throwable -> 0x02a0 }
            r3 = 0
        L_0x0210:
            if (r2 <= 0) goto L_0x0240
            r4 = 0
            r14.write(r15, r4, r2)     // Catch:{ Throwable -> 0x02a0 }
            int r6 = r3 + r2
            if (r23 == 0) goto L_0x0237
            if (r13 > 0) goto L_0x0221
            r2 = 100
            r3 = 100
            goto L_0x0225
        L_0x0221:
            int r2 = r6 * 100
            int r2 = r2 / r13
            r3 = r2
        L_0x0225:
            long r4 = (long) r6     // Catch:{ Throwable -> 0x02a0 }
            r18 = r6
            long r6 = (long) r13     // Catch:{ Throwable -> 0x02a0 }
            r2 = r23
            r16 = r18
            r2.onProgress(r3, r4, r6)     // Catch:{ Throwable -> 0x02a0 }
            boolean r2 = r23.isCanceled()     // Catch:{ Throwable -> 0x02a0 }
            if (r2 == 0) goto L_0x0239
            goto L_0x0240
        L_0x0237:
            r16 = r6
        L_0x0239:
            int r2 = r0.read(r15)     // Catch:{ Throwable -> 0x02a0 }
            r3 = r16
            goto L_0x0210
        L_0x0240:
            if (r23 == 0) goto L_0x0271
            boolean r2 = r23.isCanceled()     // Catch:{ Throwable -> 0x02a0 }
            if (r2 == 0) goto L_0x025c
            boolean r2 = r10.exists()     // Catch:{ Throwable -> 0x02a0 }
            if (r2 == 0) goto L_0x0251
            r10.delete()     // Catch:{ Throwable -> 0x02a0 }
        L_0x0251:
            r14.flush()     // Catch:{ Throwable -> 0x02a0 }
            r0.close()     // Catch:{ Throwable -> 0x02a0 }
            r14.close()     // Catch:{ Throwable -> 0x02a0 }
            r0 = 0
            return r0
        L_0x025c:
            r2 = 100
            long r3 = r10.length()     // Catch:{ Throwable -> 0x02a0 }
            long r5 = r10.length()     // Catch:{ Throwable -> 0x02a0 }
            r18 = r23
            r19 = r2
            r20 = r3
            r22 = r5
            r18.onProgress(r19, r20, r22)     // Catch:{ Throwable -> 0x02a0 }
        L_0x0271:
            r14.flush()     // Catch:{ Throwable -> 0x02a0 }
            r0.close()     // Catch:{ Throwable -> 0x02a0 }
            r14.close()     // Catch:{ Throwable -> 0x02a0 }
            r12.disconnect()
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r11)
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r8
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r0.mo29775i(r2, r3)
            java.lang.String r0 = r10.getAbsolutePath()
            return r0
        L_0x02a0:
            r0 = move-exception
            boolean r2 = r10.exists()
            if (r2 == 0) goto L_0x02aa
            r10.delete()
        L_0x02aa:
            throw r0
        L_0x02ab:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.io.InputStreamReader r2 = new java.io.InputStreamReader
            java.io.InputStream r3 = r12.getErrorStream()
            java.lang.String r5 = "utf-8"
            java.nio.charset.Charset r5 = java.nio.charset.Charset.forName(r5)
            r2.<init>(r3, r5)
            java.io.BufferedReader r3 = new java.io.BufferedReader
            r3.<init>(r2)
            java.lang.String r2 = r3.readLine()
        L_0x02c8:
            if (r2 == 0) goto L_0x02e0
            int r5 = r0.length()
            if (r5 <= 0) goto L_0x02d6
            r5 = 10
            r0.append(r5)
            goto L_0x02d8
        L_0x02d6:
            r5 = 10
        L_0x02d8:
            r0.append(r2)
            java.lang.String r2 = r3.readLine()
            goto L_0x02c8
        L_0x02e0:
            r3.close()
            r12.disconnect()
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            java.lang.String r0 = r0.toString()
            java.lang.String r3 = "error"
            r2.put(r3, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)
            java.lang.String r3 = "status"
            r2.put(r3, r0)
            java.lang.Throwable r0 = new java.lang.Throwable
            com.mob.tools.utils.Hashon r3 = new com.mob.tools.utils.Hashon
            r3.<init>()
            java.lang.String r2 = r3.fromHashMap(r2)
            r0.<init>(r2)
            goto L_0x030d
        L_0x030c:
            throw r0
        L_0x030d:
            goto L_0x030c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.downloadCache(android.content.Context, java.lang.String, java.lang.String, boolean, com.mob.tools.network.NetworkHelper$NetworkTimeOut, com.mob.tools.network.FileDownloadListener):java.lang.String");
    }

    public void rawGet(String str, RawNetworkCallback rawNetworkCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        rawGet(str, (ArrayList<KVPair<String>>) null, rawNetworkCallback, networkTimeOut);
    }

    public void rawGet(String str, ArrayList<KVPair<String>> arrayList, RawNetworkCallback rawNetworkCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        long currentTimeMillis = System.currentTimeMillis();
        NLog instance = MobLog.getInstance();
        instance.mo29775i("rawGet: " + str, new Object[0]);
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        if (arrayList != null) {
            Iterator<KVPair<String>> it = arrayList.iterator();
            while (it.hasNext()) {
                KVPair next = it.next();
                connection.setRequestProperty(next.name, (String) next.value);
            }
        }
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            if (rawNetworkCallback != null) {
                rawNetworkCallback.onResponse(connection.getInputStream());
            }
            connection.disconnect();
            NLog instance2 = MobLog.getInstance();
            instance2.mo29775i("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return;
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), Charset.forName("utf-8")));
        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
            if (sb.length() > 0) {
                sb.append(10);
            }
            sb.append(readLine);
        }
        bufferedReader.close();
        connection.disconnect();
        HashMap hashMap = new HashMap();
        hashMap.put("error", sb.toString());
        hashMap.put("status", Integer.valueOf(responseCode));
        throw new Throwable(new Hashon().fromHashMap(hashMap));
    }

    public void rawGet(String str, HttpResponseCallback httpResponseCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        rawGet(str, (ArrayList<KVPair<String>>) null, httpResponseCallback, networkTimeOut);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0068, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x006c, code lost:
        r7.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006f, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void rawGet(java.lang.String r7, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r8, com.mob.tools.network.HttpResponseCallback r9, com.mob.tools.network.NetworkHelper.NetworkTimeOut r10) throws java.lang.Throwable {
        /*
            r6 = this;
            long r0 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "rawGet: "
            r3.append(r4)
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            r4 = 0
            java.lang.Object[] r5 = new java.lang.Object[r4]
            r2.mo29775i(r3, r5)
            java.net.HttpURLConnection r7 = r6.getConnection(r7, r10)
            if (r8 == 0) goto L_0x003f
            java.util.Iterator r8 = r8.iterator()
        L_0x0029:
            boolean r2 = r8.hasNext()
            if (r2 == 0) goto L_0x003f
            java.lang.Object r2 = r8.next()
            com.mob.tools.network.KVPair r2 = (com.mob.tools.network.KVPair) r2
            java.lang.String r3 = r2.name
            T r2 = r2.value
            java.lang.String r2 = (java.lang.String) r2
            r7.setRequestProperty(r3, r2)
            goto L_0x0029
        L_0x003f:
            boolean r8 = r6.instanceFollowRedirects
            r7.setInstanceFollowRedirects(r8)
            r7.connect()
            int r8 = r7.getResponseCode()
            r2 = 301(0x12d, float:4.22E-43)
            if (r8 != r2) goto L_0x005a
            java.lang.String r8 = "Location"
            java.lang.String r7 = r7.getHeaderField(r8)
            r8 = 0
            r6.rawGet((java.lang.String) r7, (java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>>) r8, (com.mob.tools.network.HttpResponseCallback) r9, (com.mob.tools.network.NetworkHelper.NetworkTimeOut) r10)
            goto L_0x0073
        L_0x005a:
            if (r9 == 0) goto L_0x0070
            com.mob.tools.network.HttpConnectionImpl23 r8 = new com.mob.tools.network.HttpConnectionImpl23     // Catch:{ Throwable -> 0x006a }
            r8.<init>(r7)     // Catch:{ Throwable -> 0x006a }
            r9.onResponse(r8)     // Catch:{ Throwable -> 0x006a }
            r7.disconnect()
            goto L_0x0073
        L_0x0068:
            r8 = move-exception
            goto L_0x006c
        L_0x006a:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0068 }
        L_0x006c:
            r7.disconnect()
            throw r8
        L_0x0070:
            r7.disconnect()
        L_0x0073:
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
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.rawGet(java.lang.String, java.util.ArrayList, com.mob.tools.network.HttpResponseCallback, com.mob.tools.network.NetworkHelper$NetworkTimeOut):void");
    }

    public String jsonPost(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut) throws Throwable {
        final HashMap hashMap = new HashMap();
        jsonPost(str, arrayList, arrayList2, networkTimeOut, (HttpResponseCallback) new HttpResponseCallback() {
            public void onResponse(HttpConnection httpConnection) throws Throwable {
                int responseCode = httpConnection.getResponseCode();
                if (responseCode == 200 || responseCode == 201) {
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), Charset.forName("utf-8")));
                    for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        if (sb.length() > 0) {
                            sb.append(10);
                        }
                        sb.append(readLine);
                    }
                    bufferedReader.close();
                    hashMap.put("res", sb.toString());
                    return;
                }
                StringBuilder sb2 = new StringBuilder();
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream(), Charset.forName("utf-8")));
                for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
                    if (sb2.length() > 0) {
                        sb2.append(10);
                    }
                    sb2.append(readLine2);
                }
                bufferedReader2.close();
                HashMap hashMap = new HashMap();
                hashMap.put("error", sb2.toString());
                hashMap.put("status", Integer.valueOf(responseCode));
                throw new Throwable(new Hashon().fromHashMap(hashMap));
            }
        });
        if (hashMap.containsKey("res")) {
            return (String) hashMap.get("res");
        }
        return null;
    }

    public void jsonPost(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut, HttpResponseCallback httpResponseCallback) throws Throwable {
        HashMap hashMap = new HashMap();
        Iterator<KVPair<String>> it = arrayList.iterator();
        while (it.hasNext()) {
            KVPair next = it.next();
            hashMap.put(next.name, next.value);
        }
        jsonPost(str, (HashMap<String, Object>) hashMap, arrayList2, networkTimeOut, httpResponseCallback);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0099, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x009d, code lost:
        r7.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00a0, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void jsonPost(java.lang.String r7, java.util.HashMap<java.lang.String, java.lang.Object> r8, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r9, com.mob.tools.network.NetworkHelper.NetworkTimeOut r10, com.mob.tools.network.HttpResponseCallback r11) throws java.lang.Throwable {
        /*
            r6 = this;
            long r0 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "jsonPost: "
            r3.append(r4)
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            r4 = 0
            java.lang.Object[] r5 = new java.lang.Object[r4]
            r2.mo29775i(r3, r5)
            java.net.HttpURLConnection r7 = r6.getConnection(r7, r10)
            r10 = 1
            r7.setDoOutput(r10)
            r7.setChunkedStreamingMode(r4)
            java.lang.String r10 = "content-type"
            java.lang.String r2 = "application/json"
            r7.setRequestProperty(r10, r2)
            if (r9 == 0) goto L_0x004d
            java.util.Iterator r9 = r9.iterator()
        L_0x0037:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x004d
            java.lang.Object r10 = r9.next()
            com.mob.tools.network.KVPair r10 = (com.mob.tools.network.KVPair) r10
            java.lang.String r2 = r10.name
            T r10 = r10.value
            java.lang.String r10 = (java.lang.String) r10
            r7.setRequestProperty(r2, r10)
            goto L_0x0037
        L_0x004d:
            com.mob.tools.network.StringPart r9 = new com.mob.tools.network.StringPart
            r9.<init>()
            if (r8 == 0) goto L_0x0060
            com.mob.tools.utils.Hashon r10 = new com.mob.tools.utils.Hashon
            r10.<init>()
            java.lang.String r8 = r10.fromHashMap(r8)
            r9.append(r8)
        L_0x0060:
            boolean r8 = r6.instanceFollowRedirects
            r7.setInstanceFollowRedirects(r8)
            r7.connect()
            java.io.OutputStream r8 = r7.getOutputStream()
            java.io.InputStream r9 = r9.toInputStream()
            r10 = 65536(0x10000, float:9.18355E-41)
            byte[] r10 = new byte[r10]
            int r2 = r9.read(r10)
        L_0x0078:
            if (r2 <= 0) goto L_0x0082
            r8.write(r10, r4, r2)
            int r2 = r9.read(r10)
            goto L_0x0078
        L_0x0082:
            r8.flush()
            r9.close()
            r8.close()
            if (r11 == 0) goto L_0x00a1
            com.mob.tools.network.HttpConnectionImpl23 r8 = new com.mob.tools.network.HttpConnectionImpl23     // Catch:{ Throwable -> 0x009b }
            r8.<init>(r7)     // Catch:{ Throwable -> 0x009b }
            r11.onResponse(r8)     // Catch:{ Throwable -> 0x009b }
            r7.disconnect()
            goto L_0x00a4
        L_0x0099:
            r8 = move-exception
            goto L_0x009d
        L_0x009b:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0099 }
        L_0x009d:
            r7.disconnect()
            throw r8
        L_0x00a1:
            r7.disconnect()
        L_0x00a4:
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
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.jsonPost(java.lang.String, java.util.HashMap, java.util.ArrayList, com.mob.tools.network.NetworkHelper$NetworkTimeOut, com.mob.tools.network.HttpResponseCallback):void");
    }

    public String httpPost(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut) throws Throwable {
        return httpPost(str, arrayList, kVPair, arrayList2, 0, networkTimeOut);
    }

    public String httpPost(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, int i, NetworkTimeOut networkTimeOut) throws Throwable {
        ArrayList arrayList3 = new ArrayList();
        if (!(kVPair == null || kVPair.value == null || !new File((String) kVPair.value).exists())) {
            arrayList3.add(kVPair);
        }
        return httpPostFiles(str, arrayList, arrayList3, arrayList2, i, networkTimeOut);
    }

    public String httpPostFiles(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, ArrayList<KVPair<String>> arrayList3, NetworkTimeOut networkTimeOut) throws Throwable {
        return httpPostFiles(str, arrayList, arrayList2, arrayList3, 0, networkTimeOut);
    }

    public String httpPostFiles(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, ArrayList<KVPair<String>> arrayList3, int i, NetworkTimeOut networkTimeOut) throws Throwable {
        final HashMap hashMap = new HashMap();
        httpPost(str, arrayList, arrayList2, arrayList3, i, (HttpResponseCallback) new HttpResponseCallback() {
            public void onResponse(HttpConnection httpConnection) throws Throwable {
                int responseCode = httpConnection.getResponseCode();
                if (responseCode == 200 || responseCode < 300) {
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), Charset.forName("utf-8")));
                    for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        if (sb.length() > 0) {
                            sb.append(10);
                        }
                        sb.append(readLine);
                    }
                    bufferedReader.close();
                    hashMap.put("resp", sb.toString());
                    return;
                }
                StringBuilder sb2 = new StringBuilder();
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream(), Charset.forName("utf-8")));
                for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
                    if (sb2.length() > 0) {
                        sb2.append(10);
                    }
                    sb2.append(readLine2);
                }
                bufferedReader2.close();
                HashMap hashMap = new HashMap();
                hashMap.put("error", sb2.toString());
                hashMap.put("status", Integer.valueOf(responseCode));
                throw new Throwable(new Hashon().fromHashMap(hashMap));
            }
        }, networkTimeOut);
        return (String) hashMap.get("resp");
    }

    public String httpPostFilesChecked(String str, ArrayList<KVPair<String>> arrayList, byte[] bArr, ArrayList<KVPair<String>> arrayList2, int i, NetworkTimeOut networkTimeOut) throws Throwable {
        final HashMap hashMap = new HashMap();
        httpPost(str, arrayList, bArr, arrayList2, i, (HttpResponseCallback) new HttpResponseCallback() {
            public void onResponse(HttpConnection httpConnection) throws Throwable {
                int responseCode = httpConnection.getResponseCode();
                if (responseCode == 200 || responseCode < 300) {
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), Charset.forName("utf-8")));
                    for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        if (sb.length() > 0) {
                            sb.append(10);
                        }
                        sb.append(readLine);
                    }
                    bufferedReader.close();
                    hashMap.put("resp", sb.toString());
                    return;
                }
                StringBuilder sb2 = new StringBuilder();
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream(), Charset.forName("utf-8")));
                for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
                    if (sb2.length() > 0) {
                        sb2.append(10);
                    }
                    sb2.append(readLine2);
                }
                bufferedReader2.close();
                HashMap hashMap = new HashMap();
                hashMap.put("error", sb2.toString());
                hashMap.put("status", Integer.valueOf(responseCode));
                throw new Throwable(new Hashon().fromHashMap(hashMap));
            }
        }, networkTimeOut);
        return (String) hashMap.get("resp");
    }

    public String httpPost(String str, ArrayList<KVPair<String>> arrayList, int i, NetworkTimeOut networkTimeOut) throws Throwable {
        final HashMap hashMap = new HashMap();
        httpPost(str, arrayList, i, (HttpResponseCallback) new HttpResponseCallback() {
            public void onResponse(HttpConnection httpConnection) throws Throwable {
                int responseCode = httpConnection.getResponseCode();
                if (responseCode == 200 || responseCode < 300) {
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), Charset.forName("utf-8")));
                    for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        if (sb.length() > 0) {
                            sb.append(10);
                        }
                        sb.append(readLine);
                    }
                    bufferedReader.close();
                    hashMap.put("resp", sb.toString());
                    return;
                }
                StringBuilder sb2 = new StringBuilder();
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream(), Charset.forName("utf-8")));
                for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
                    if (sb2.length() > 0) {
                        sb2.append(10);
                    }
                    sb2.append(readLine2);
                }
                bufferedReader2.close();
                HashMap hashMap = new HashMap();
                hashMap.put("error", sb2.toString());
                hashMap.put("status", Integer.valueOf(responseCode));
                throw new Throwable(new Hashon().fromHashMap(hashMap));
            }
        }, networkTimeOut);
        return (String) hashMap.get("resp");
    }

    public void httpPost(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, ArrayList<KVPair<String>> arrayList3, HttpResponseCallback httpResponseCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        httpPost(str, arrayList, arrayList2, arrayList3, 0, httpResponseCallback, networkTimeOut);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00a1, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a5, code lost:
        r13.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a8, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void httpPost(java.lang.String r7, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r8, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r9, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r10, int r11, com.mob.tools.network.HttpResponseCallback r12, com.mob.tools.network.NetworkHelper.NetworkTimeOut r13) throws java.lang.Throwable {
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
            java.net.HttpURLConnection r13 = r6.getConnection(r7, r13)
            r2 = 1
            r13.setDoOutput(r2)
            java.lang.String r2 = "Connection"
            java.lang.String r3 = "Keep-Alive"
            r13.setRequestProperty(r2, r3)
            if (r9 == 0) goto L_0x0040
            int r2 = r9.size()
            if (r2 <= 0) goto L_0x0040
            com.mob.tools.network.HTTPPart r7 = r6.getFilePostHTTPPart(r13, r7, r8, r9)
            if (r11 < 0) goto L_0x004c
            r13.setChunkedStreamingMode(r11)
            goto L_0x004c
        L_0x0040:
            com.mob.tools.network.HTTPPart r7 = r6.getTextPostHTTPPart(r13, r7, r8)
            long r8 = r7.length()
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
            boolean r8 = r6.instanceFollowRedirects
            r13.setInstanceFollowRedirects(r8)
            r13.connect()
            java.io.OutputStream r8 = r13.getOutputStream()
            java.io.InputStream r7 = r7.toInputStream()
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
            if (r12 == 0) goto L_0x00a9
            com.mob.tools.network.HttpConnectionImpl23 r7 = new com.mob.tools.network.HttpConnectionImpl23     // Catch:{ Throwable -> 0x00a3 }
            r7.<init>(r13)     // Catch:{ Throwable -> 0x00a3 }
            r12.onResponse(r7)     // Catch:{ Throwable -> 0x00a3 }
            r13.disconnect()
            goto L_0x00ac
        L_0x00a1:
            r7 = move-exception
            goto L_0x00a5
        L_0x00a3:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x00a1 }
        L_0x00a5:
            r13.disconnect()
            throw r7
        L_0x00a9:
            r13.disconnect()
        L_0x00ac:
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
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.httpPost(java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, int, com.mob.tools.network.HttpResponseCallback, com.mob.tools.network.NetworkHelper$NetworkTimeOut):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x009e, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a2, code lost:
        r13.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a5, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void httpPost(java.lang.String r7, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r8, byte[] r9, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r10, int r11, com.mob.tools.network.HttpResponseCallback r12, com.mob.tools.network.NetworkHelper.NetworkTimeOut r13) throws java.lang.Throwable {
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
            java.net.HttpURLConnection r13 = r6.getConnection(r7, r13)
            r2 = 1
            r13.setDoOutput(r2)
            java.lang.String r2 = "Connection"
            java.lang.String r3 = "Keep-Alive"
            r13.setRequestProperty(r2, r3)
            if (r9 == 0) goto L_0x003d
            int r2 = r9.length
            if (r2 <= 0) goto L_0x003d
            com.mob.tools.network.HTTPPart r7 = r6.getDataPostHttpPart(r13, r7, r9)
            if (r11 < 0) goto L_0x0049
            r13.setChunkedStreamingMode(r11)
            goto L_0x0049
        L_0x003d:
            com.mob.tools.network.HTTPPart r7 = r6.getTextPostHTTPPart(r13, r7, r8)
            long r8 = r7.length()
            int r9 = (int) r8
            r13.setFixedLengthStreamingMode(r9)
        L_0x0049:
            if (r10 == 0) goto L_0x0065
            java.util.Iterator r8 = r10.iterator()
        L_0x004f:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x0065
            java.lang.Object r9 = r8.next()
            com.mob.tools.network.KVPair r9 = (com.mob.tools.network.KVPair) r9
            java.lang.String r10 = r9.name
            T r9 = r9.value
            java.lang.String r9 = (java.lang.String) r9
            r13.setRequestProperty(r10, r9)
            goto L_0x004f
        L_0x0065:
            boolean r8 = r6.instanceFollowRedirects
            r13.setInstanceFollowRedirects(r8)
            r13.connect()
            java.io.OutputStream r8 = r13.getOutputStream()
            java.io.InputStream r7 = r7.toInputStream()
            r9 = 65536(0x10000, float:9.18355E-41)
            byte[] r9 = new byte[r9]
            int r10 = r7.read(r9)
        L_0x007d:
            if (r10 <= 0) goto L_0x0087
            r8.write(r9, r4, r10)
            int r10 = r7.read(r9)
            goto L_0x007d
        L_0x0087:
            r8.flush()
            r7.close()
            r8.close()
            if (r12 == 0) goto L_0x00a6
            com.mob.tools.network.HttpConnectionImpl23 r7 = new com.mob.tools.network.HttpConnectionImpl23     // Catch:{ Throwable -> 0x00a0 }
            r7.<init>(r13)     // Catch:{ Throwable -> 0x00a0 }
            r12.onResponse(r7)     // Catch:{ Throwable -> 0x00a0 }
            r13.disconnect()
            goto L_0x00a9
        L_0x009e:
            r7 = move-exception
            goto L_0x00a2
        L_0x00a0:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x009e }
        L_0x00a2:
            r13.disconnect()
            throw r7
        L_0x00a6:
            r13.disconnect()
        L_0x00a9:
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
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.httpPost(java.lang.String, java.util.ArrayList, byte[], java.util.ArrayList, int, com.mob.tools.network.HttpResponseCallback, com.mob.tools.network.NetworkHelper$NetworkTimeOut):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x008c, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0090, code lost:
        r6.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0093, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void httpPost(java.lang.String r6, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r7, int r8, com.mob.tools.network.HttpResponseCallback r9, com.mob.tools.network.NetworkHelper.NetworkTimeOut r10) throws java.lang.Throwable {
        /*
            r5 = this;
            long r0 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r8 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "httpPost: "
            r2.append(r3)
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            r3 = 0
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r8.mo29775i(r2, r4)
            java.net.HttpURLConnection r6 = r5.getConnection(r6, r10)
            r8 = 1
            r6.setDoOutput(r8)
            java.lang.String r8 = "Connection"
            java.lang.String r10 = "Keep-Alive"
            r6.setRequestProperty(r8, r10)
            if (r7 == 0) goto L_0x004a
            java.util.Iterator r7 = r7.iterator()
        L_0x0034:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x004a
            java.lang.Object r8 = r7.next()
            com.mob.tools.network.KVPair r8 = (com.mob.tools.network.KVPair) r8
            java.lang.String r10 = r8.name
            T r8 = r8.value
            java.lang.String r8 = (java.lang.String) r8
            r6.setRequestProperty(r10, r8)
            goto L_0x0034
        L_0x004a:
            com.mob.tools.network.StringPart r7 = new com.mob.tools.network.StringPart
            r7.<init>()
            r8 = 0
            r7.append(r8)
            boolean r8 = r5.instanceFollowRedirects
            r6.setInstanceFollowRedirects(r8)
            r6.connect()
            java.io.OutputStream r8 = r6.getOutputStream()
            java.io.InputStream r7 = r7.toInputStream()
            r10 = 65536(0x10000, float:9.18355E-41)
            byte[] r10 = new byte[r10]
            int r2 = r7.read(r10)
        L_0x006b:
            if (r2 <= 0) goto L_0x0075
            r8.write(r10, r3, r2)
            int r2 = r7.read(r10)
            goto L_0x006b
        L_0x0075:
            r8.flush()
            r7.close()
            r8.close()
            if (r9 == 0) goto L_0x0094
            com.mob.tools.network.HttpConnectionImpl23 r7 = new com.mob.tools.network.HttpConnectionImpl23     // Catch:{ Throwable -> 0x008e }
            r7.<init>(r6)     // Catch:{ Throwable -> 0x008e }
            r9.onResponse(r7)     // Catch:{ Throwable -> 0x008e }
            r6.disconnect()
            goto L_0x0097
        L_0x008c:
            r7 = move-exception
            goto L_0x0090
        L_0x008e:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x008c }
        L_0x0090:
            r6.disconnect()
            throw r7
        L_0x0094:
            r6.disconnect()
        L_0x0097:
            com.mob.tools.log.NLog r6 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "use time: "
            r7.append(r8)
            long r8 = java.lang.System.currentTimeMillis()
            long r8 = r8 - r0
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            java.lang.Object[] r8 = new java.lang.Object[r3]
            r6.mo29775i(r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.httpPost(java.lang.String, java.util.ArrayList, int, com.mob.tools.network.HttpResponseCallback, com.mob.tools.network.NetworkHelper$NetworkTimeOut):void");
    }

    private HTTPPart getDataPostHttpPart(HttpURLConnection httpURLConnection, String str, byte[] bArr) throws Throwable {
        ByteArrayPart byteArrayPart = new ByteArrayPart();
        byteArrayPart.append(bArr);
        return byteArrayPart;
    }

    private HTTPPart getFilePostHTTPPart(HttpURLConnection httpURLConnection, String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2) throws Throwable {
        String uuid = UUID.randomUUID().toString();
        httpURLConnection.setRequestProperty(Headers.CONTENT_TYPE, "multipart/form-data; boundary=" + uuid);
        MultiPart multiPart = new MultiPart();
        StringPart stringPart = new StringPart();
        if (arrayList != null) {
            Iterator<KVPair<String>> it = arrayList.iterator();
            while (it.hasNext()) {
                KVPair next = it.next();
                stringPart.append("--").append(uuid).append("\r\n");
                stringPart.append("Content-Disposition: form-data; name=\"").append(next.name).append("\"\r\n\r\n");
                stringPart.append((String) next.value).append("\r\n");
            }
        }
        multiPart.append(stringPart);
        Iterator<KVPair<String>> it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            KVPair next2 = it2.next();
            StringPart stringPart2 = new StringPart();
            File file = new File((String) next2.value);
            stringPart2.append("--").append(uuid).append("\r\n");
            stringPart2.append("Content-Disposition: form-data; name=\"").append(next2.name).append("\"; filename=\"").append(file.getName()).append("\"\r\n");
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
            stringPart2.append("Content-Type: ").append(contentTypeFor).append("\r\n\r\n");
            multiPart.append(stringPart2);
            FilePart filePart = new FilePart();
            filePart.setFile((String) next2.value);
            multiPart.append(filePart);
            StringPart stringPart3 = new StringPart();
            stringPart3.append("\r\n");
            multiPart.append(stringPart3);
        }
        StringPart stringPart4 = new StringPart();
        stringPart4.append("--").append(uuid).append("--\r\n");
        multiPart.append(stringPart4);
        return multiPart;
    }

    private HTTPPart getTextPostHTTPPart(HttpURLConnection httpURLConnection, String str, ArrayList<KVPair<String>> arrayList) throws Throwable {
        httpURLConnection.setRequestProperty(Headers.CONTENT_TYPE, "application/x-www-form-urlencoded");
        StringPart stringPart = new StringPart();
        if (arrayList != null) {
            stringPart.append(kvPairsToUrl(arrayList));
        }
        return stringPart;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x008b, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x008f, code lost:
        if (r8 != null) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r8.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void rawPost(java.lang.String r7, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r8, com.mob.tools.network.HTTPPart r9, com.mob.tools.network.RawNetworkCallback r10, com.mob.tools.network.NetworkHelper.NetworkTimeOut r11) throws java.lang.Throwable {
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
            java.net.HttpURLConnection r7 = r6.getConnection(r7, r11)
            r11 = 1
            r7.setDoOutput(r11)
            r7.setChunkedStreamingMode(r4)
            if (r8 == 0) goto L_0x0046
            java.util.Iterator r8 = r8.iterator()
        L_0x0030:
            boolean r11 = r8.hasNext()
            if (r11 == 0) goto L_0x0046
            java.lang.Object r11 = r8.next()
            com.mob.tools.network.KVPair r11 = (com.mob.tools.network.KVPair) r11
            java.lang.String r2 = r11.name
            T r11 = r11.value
            java.lang.String r11 = (java.lang.String) r11
            r7.setRequestProperty(r2, r11)
            goto L_0x0030
        L_0x0046:
            boolean r8 = r6.instanceFollowRedirects
            r7.setInstanceFollowRedirects(r8)
            r7.connect()
            java.io.OutputStream r8 = r7.getOutputStream()
            java.io.InputStream r9 = r9.toInputStream()
            r11 = 65536(0x10000, float:9.18355E-41)
            byte[] r11 = new byte[r11]
            int r2 = r9.read(r11)
        L_0x005e:
            if (r2 <= 0) goto L_0x0068
            r8.write(r11, r4, r2)
            int r2 = r9.read(r11)
            goto L_0x005e
        L_0x0068:
            r8.flush()
            r9.close()
            r8.close()
            int r8 = r7.getResponseCode()
            r9 = 200(0xc8, float:2.8E-43)
            if (r8 != r9) goto L_0x00bb
            if (r10 == 0) goto L_0x0098
            java.io.InputStream r8 = r7.getInputStream()
            r10.onResponse(r8)     // Catch:{ Throwable -> 0x008d }
            if (r8 == 0) goto L_0x0087
            r8.close()     // Catch:{ Throwable -> 0x0087 }
        L_0x0087:
            r7.disconnect()
            goto L_0x009b
        L_0x008b:
            r9 = move-exception
            goto L_0x008f
        L_0x008d:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x008b }
        L_0x008f:
            if (r8 == 0) goto L_0x0094
            r8.close()     // Catch:{ Throwable -> 0x0094 }
        L_0x0094:
            r7.disconnect()
            throw r9
        L_0x0098:
            r7.disconnect()
        L_0x009b:
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
        L_0x00bb:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.io.InputStreamReader r10 = new java.io.InputStreamReader
            java.io.InputStream r11 = r7.getErrorStream()
            java.lang.String r0 = "utf-8"
            java.nio.charset.Charset r0 = java.nio.charset.Charset.forName(r0)
            r10.<init>(r11, r0)
            java.io.BufferedReader r11 = new java.io.BufferedReader
            r11.<init>(r10)
            java.lang.String r10 = r11.readLine()
        L_0x00d8:
            if (r10 == 0) goto L_0x00ed
            int r0 = r9.length()
            if (r0 <= 0) goto L_0x00e5
            r0 = 10
            r9.append(r0)
        L_0x00e5:
            r9.append(r10)
            java.lang.String r10 = r11.readLine()
            goto L_0x00d8
        L_0x00ed:
            r11.close()
            r7.disconnect()
            java.util.HashMap r7 = new java.util.HashMap
            r7.<init>()
            java.lang.String r9 = r9.toString()
            java.lang.String r10 = "error"
            r7.put(r10, r9)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            java.lang.String r9 = "status"
            r7.put(r9, r8)
            java.lang.Throwable r8 = new java.lang.Throwable
            com.mob.tools.utils.Hashon r9 = new com.mob.tools.utils.Hashon
            r9.<init>()
            java.lang.String r7 = r9.fromHashMap(r7)
            r8.<init>(r7)
            goto L_0x011a
        L_0x0119:
            throw r8
        L_0x011a:
            goto L_0x0119
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.rawPost(java.lang.String, java.util.ArrayList, com.mob.tools.network.HTTPPart, com.mob.tools.network.RawNetworkCallback, com.mob.tools.network.NetworkHelper$NetworkTimeOut):void");
    }

    public void rawPost(String str, ArrayList<KVPair<String>> arrayList, HTTPPart hTTPPart, HttpResponseCallback httpResponseCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        rawPost(str, arrayList, hTTPPart, 0, httpResponseCallback, networkTimeOut);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0081, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0085, code lost:
        r7.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0088, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void rawPost(java.lang.String r7, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r8, com.mob.tools.network.HTTPPart r9, int r10, com.mob.tools.network.HttpResponseCallback r11, com.mob.tools.network.NetworkHelper.NetworkTimeOut r12) throws java.lang.Throwable {
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
            java.net.HttpURLConnection r7 = r6.getConnection(r7, r12)
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
            boolean r8 = r6.instanceFollowRedirects
            r7.setInstanceFollowRedirects(r8)
            r7.connect()
            java.io.OutputStream r8 = r7.getOutputStream()
            java.io.InputStream r9 = r9.toInputStream()
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
            if (r11 == 0) goto L_0x0089
            com.mob.tools.network.HttpConnectionImpl23 r8 = new com.mob.tools.network.HttpConnectionImpl23     // Catch:{ Throwable -> 0x0083 }
            r8.<init>(r7)     // Catch:{ Throwable -> 0x0083 }
            r11.onResponse(r8)     // Catch:{ Throwable -> 0x0083 }
            r7.disconnect()
            goto L_0x008c
        L_0x0081:
            r8 = move-exception
            goto L_0x0085
        L_0x0083:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0081 }
        L_0x0085:
            r7.disconnect()
            throw r8
        L_0x0089:
            r7.disconnect()
        L_0x008c:
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
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.rawPost(java.lang.String, java.util.ArrayList, com.mob.tools.network.HTTPPart, int, com.mob.tools.network.HttpResponseCallback, com.mob.tools.network.NetworkHelper$NetworkTimeOut):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00a5, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a9, code lost:
        r12.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00ac, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getHttpPostResponse(java.lang.String r7, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r8, com.mob.tools.network.KVPair<java.lang.String> r9, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r10, com.mob.tools.network.HttpResponseCallback r11, com.mob.tools.network.NetworkHelper.NetworkTimeOut r12) throws java.lang.Throwable {
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
            java.net.HttpURLConnection r12 = r6.getConnection(r7, r12)
            r2 = 1
            r12.setDoOutput(r2)
            r12.setChunkedStreamingMode(r4)
            if (r9 == 0) goto L_0x004c
            T r2 = r9.value
            if (r2 == 0) goto L_0x004c
            java.io.File r2 = new java.io.File
            T r3 = r9.value
            java.lang.String r3 = (java.lang.String) r3
            r2.<init>(r3)
            boolean r2 = r2.exists()
            if (r2 == 0) goto L_0x004c
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r2.add(r9)
            com.mob.tools.network.HTTPPart r7 = r6.getFilePostHTTPPart(r12, r7, r8, r2)
            goto L_0x0050
        L_0x004c:
            com.mob.tools.network.HTTPPart r7 = r6.getTextPostHTTPPart(r12, r7, r8)
        L_0x0050:
            if (r10 == 0) goto L_0x006c
            java.util.Iterator r8 = r10.iterator()
        L_0x0056:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x006c
            java.lang.Object r9 = r8.next()
            com.mob.tools.network.KVPair r9 = (com.mob.tools.network.KVPair) r9
            java.lang.String r10 = r9.name
            T r9 = r9.value
            java.lang.String r9 = (java.lang.String) r9
            r12.setRequestProperty(r10, r9)
            goto L_0x0056
        L_0x006c:
            boolean r8 = r6.instanceFollowRedirects
            r12.setInstanceFollowRedirects(r8)
            r12.connect()
            java.io.OutputStream r8 = r12.getOutputStream()
            java.io.InputStream r7 = r7.toInputStream()
            r9 = 65536(0x10000, float:9.18355E-41)
            byte[] r9 = new byte[r9]
            int r10 = r7.read(r9)
        L_0x0084:
            if (r10 <= 0) goto L_0x008e
            r8.write(r9, r4, r10)
            int r10 = r7.read(r9)
            goto L_0x0084
        L_0x008e:
            r8.flush()
            r7.close()
            r8.close()
            if (r11 == 0) goto L_0x00ad
            com.mob.tools.network.HttpConnectionImpl23 r7 = new com.mob.tools.network.HttpConnectionImpl23     // Catch:{ Throwable -> 0x00a7 }
            r7.<init>(r12)     // Catch:{ Throwable -> 0x00a7 }
            r11.onResponse(r7)     // Catch:{ Throwable -> 0x00a7 }
            r12.disconnect()
            goto L_0x00b0
        L_0x00a5:
            r7 = move-exception
            goto L_0x00a9
        L_0x00a7:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x00a5 }
        L_0x00a9:
            r12.disconnect()
            throw r7
        L_0x00ad:
            r12.disconnect()
        L_0x00b0:
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
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.getHttpPostResponse(java.lang.String, java.util.ArrayList, com.mob.tools.network.KVPair, java.util.ArrayList, com.mob.tools.network.HttpResponseCallback, com.mob.tools.network.NetworkHelper$NetworkTimeOut):void");
    }

    public String httpPut(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut) throws Throwable {
        return httpPut(str, arrayList, kVPair, arrayList2, networkTimeOut, (OnReadListener) null);
    }

    public String httpPut(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut, OnReadListener onReadListener) throws Throwable {
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().mo29775i("httpPut: " + str, new Object[0]);
        if (arrayList != null) {
            String kvPairsToUrl = kvPairsToUrl(arrayList);
            if (kvPairsToUrl.length() > 0) {
                str = str + "?" + kvPairsToUrl;
            }
        }
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        connection.setDoOutput(true);
        connection.setChunkedStreamingMode(0);
        connection.setRequestMethod("PUT");
        connection.setRequestProperty(Headers.CONTENT_TYPE, Mimetypes.MIMETYPE_OCTET_STREAM);
        if (arrayList2 != null) {
            Iterator<KVPair<String>> it = arrayList2.iterator();
            while (it.hasNext()) {
                KVPair next = it.next();
                connection.setRequestProperty(next.name, (String) next.value);
            }
        }
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        OutputStream outputStream = connection.getOutputStream();
        FilePart filePart = new FilePart();
        if (onReadListener != null) {
            filePart.setOnReadListener(onReadListener);
        }
        filePart.setFile((String) kVPair.value);
        InputStream inputStream = filePart.toInputStream();
        byte[] bArr = new byte[65536];
        for (int read = inputStream.read(bArr); read > 0; read = inputStream.read(bArr)) {
            outputStream.write(bArr, 0, read);
        }
        outputStream.flush();
        inputStream.close();
        outputStream.close();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200 || responseCode == 201) {
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("utf-8")));
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                if (sb.length() > 0) {
                    sb.append(10);
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            connection.disconnect();
            String sb2 = sb.toString();
            MobLog.getInstance().mo29775i("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return sb2;
        }
        StringBuilder sb3 = new StringBuilder();
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(connection.getErrorStream(), Charset.forName("utf-8")));
        for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
            if (sb3.length() > 0) {
                sb3.append(10);
            }
            sb3.append(readLine2);
        }
        bufferedReader2.close();
        HashMap hashMap = new HashMap();
        hashMap.put("error", sb3.toString());
        hashMap.put("status", Integer.valueOf(responseCode));
        throw new Throwable(new Hashon().fromHashMap(hashMap));
    }

    public ArrayList<KVPair<String[]>> httpHead(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut) throws Throwable {
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().mo29775i("httpHead: " + str, new Object[0]);
        if (arrayList != null) {
            String kvPairsToUrl = kvPairsToUrl(arrayList);
            if (kvPairsToUrl.length() > 0) {
                str = str + "?" + kvPairsToUrl;
            }
        }
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        connection.setRequestMethod("HEAD");
        if (arrayList2 != null) {
            Iterator<KVPair<String>> it = arrayList2.iterator();
            while (it.hasNext()) {
                KVPair next = it.next();
                connection.setRequestProperty(next.name, (String) next.value);
            }
        }
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        Map headerFields = connection.getHeaderFields();
        ArrayList<KVPair<String[]>> arrayList3 = new ArrayList<>();
        if (headerFields != null) {
            for (Map.Entry entry : headerFields.entrySet()) {
                List list = (List) entry.getValue();
                if (list == null) {
                    arrayList3.add(new KVPair((String) entry.getKey(), new String[0]));
                } else {
                    String[] strArr = new String[list.size()];
                    for (int i = 0; i < strArr.length; i++) {
                        strArr[i] = (String) list.get(i);
                    }
                    arrayList3.add(new KVPair((String) entry.getKey(), strArr));
                }
            }
        }
        connection.disconnect();
        MobLog.getInstance().mo29775i("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
        return arrayList3;
    }

    public void httpPatch(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, long j, ArrayList<KVPair<String>> arrayList2, OnReadListener onReadListener, HttpResponseCallback httpResponseCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        if (Build.VERSION.SDK_INT >= 23) {
            httpPatchImpl23(str, arrayList, kVPair, j, arrayList2, onReadListener, httpResponseCallback, networkTimeOut);
        } else {
            httpPatchImpl(str, arrayList, kVPair, j, arrayList2, onReadListener, httpResponseCallback, networkTimeOut);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0203, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0207, code lost:
        com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r3, "shutdown", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x020c, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void httpPatchImpl(java.lang.String r17, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r18, com.mob.tools.network.KVPair<java.lang.String> r19, long r20, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r22, com.mob.tools.network.OnReadListener r23, com.mob.tools.network.HttpResponseCallback r24, com.mob.tools.network.NetworkHelper.NetworkTimeOut r25) throws java.lang.Throwable {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            r2 = r18
            r3 = r20
            r5 = r24
            r6 = r25
            long r7 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r9 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "httpPatch: "
            r10.append(r11)
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            r11 = 0
            java.lang.Object[] r12 = new java.lang.Object[r11]
            r9.mo29775i(r10, r12)
            java.lang.String r9 = "org.apache.http.entity.InputStreamEntity"
            com.mob.tools.utils.ReflectHelper.importClass(r9)
            java.lang.String r9 = "org.apache.http.params.BasicHttpParams"
            com.mob.tools.utils.ReflectHelper.importClass(r9)
            java.lang.String r9 = "org.apache.http.params.HttpConnectionParams"
            com.mob.tools.utils.ReflectHelper.importClass(r9)
            java.lang.String r9 = "org.apache.http.HttpVersion"
            com.mob.tools.utils.ReflectHelper.importClass(r9)
            java.lang.String r9 = "org.apache.http.params.HttpProtocolParams"
            com.mob.tools.utils.ReflectHelper.importClass(r9)
            java.lang.String r9 = "org.apache.http.conn.scheme.SchemeRegistry"
            com.mob.tools.utils.ReflectHelper.importClass(r9)
            java.lang.String r9 = "org.apache.http.conn.scheme.PlainSocketFactory"
            com.mob.tools.utils.ReflectHelper.importClass(r9)
            java.lang.String r9 = "org.apache.http.conn.scheme.Scheme"
            com.mob.tools.utils.ReflectHelper.importClass(r9)
            java.lang.String r9 = "org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager"
            com.mob.tools.utils.ReflectHelper.importClass(r9)
            java.lang.String r9 = "org.apache.http.impl.client.DefaultHttpClient"
            com.mob.tools.utils.ReflectHelper.importClass(r9)
            if (r2 == 0) goto L_0x007d
            java.lang.String r2 = r1.kvPairsToUrl(r2)
            int r9 = r2.length()
            if (r9 <= 0) goto L_0x007d
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r0)
            java.lang.String r0 = "?"
            r9.append(r0)
            r9.append(r2)
            java.lang.String r0 = r9.toString()
        L_0x007d:
            java.lang.Object r2 = r1.getHttpPatch(r0)
            r9 = 2
            r10 = 1
            if (r2 == 0) goto L_0x00a7
            if (r22 == 0) goto L_0x00a7
            java.util.Iterator r12 = r22.iterator()
        L_0x008b:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x00a7
            java.lang.Object r13 = r12.next()
            com.mob.tools.network.KVPair r13 = (com.mob.tools.network.KVPair) r13
            java.lang.Object[] r14 = new java.lang.Object[r9]
            java.lang.String r15 = r13.name
            r14[r11] = r15
            T r13 = r13.value
            r14[r10] = r13
            java.lang.String r13 = "setHeader"
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r2, r13, r14)
            goto L_0x008b
        L_0x00a7:
            com.mob.tools.network.FilePart r12 = new com.mob.tools.network.FilePart
            r12.<init>()
            r13 = r23
            r12.setOnReadListener(r13)
            r13 = r19
            T r13 = r13.value
            java.lang.String r13 = (java.lang.String) r13
            r12.setFile((java.lang.String) r13)
            r12.setOffset(r3)
            java.io.InputStream r13 = r12.toInputStream()
            long r14 = r12.length()
            long r14 = r14 - r3
            java.lang.Object[] r3 = new java.lang.Object[r9]
            r3[r11] = r13
            java.lang.Long r4 = java.lang.Long.valueOf(r14)
            r3[r10] = r4
            java.lang.String r4 = "InputStreamEntity"
            java.lang.Object r3 = com.mob.tools.utils.ReflectHelper.newInstance(r4, r3)
            java.lang.Object[] r4 = new java.lang.Object[r10]
            java.lang.String r12 = "application/offset+octet-stream"
            r4[r11] = r12
            java.lang.String r12 = "setContentEncoding"
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r3, r12, r4)
            java.lang.Object[] r4 = new java.lang.Object[r10]
            r4[r11] = r3
            java.lang.String r3 = "setEntity"
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r2, r3, r4)
            java.lang.Object[] r3 = new java.lang.Object[r11]
            java.lang.String r4 = "BasicHttpParams"
            java.lang.Object r3 = com.mob.tools.utils.ReflectHelper.newInstance(r4, r3)
            if (r6 != 0) goto L_0x00f7
            int r12 = connectionTimeout
            goto L_0x00f9
        L_0x00f7:
            int r12 = r6.connectionTimeout
        L_0x00f9:
            java.lang.String r13 = "HttpConnectionParams"
            if (r12 <= 0) goto L_0x010c
            java.lang.Object[] r14 = new java.lang.Object[r9]
            r14[r11] = r3
            java.lang.Integer r15 = java.lang.Integer.valueOf(r12)
            r14[r10] = r15
            java.lang.String r15 = "setConnectionTimeout"
            com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r13, r15, r14)
        L_0x010c:
            if (r6 != 0) goto L_0x0111
            int r6 = readTimout
            goto L_0x0113
        L_0x0111:
            int r6 = r6.readTimout
        L_0x0113:
            if (r6 <= 0) goto L_0x0124
            java.lang.Object[] r6 = new java.lang.Object[r9]
            r6[r11] = r3
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r6[r10] = r12
            java.lang.String r12 = "setSoTimeout"
            com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r13, r12, r6)
        L_0x0124:
            java.lang.Object[] r6 = new java.lang.Object[r10]
            r6[r11] = r3
            java.lang.String r3 = "setParams"
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r2, r3, r6)
            java.lang.String r3 = "https://"
            boolean r0 = r0.startsWith(r3)
            java.lang.String r3 = "DefaultHttpClient"
            if (r0 == 0) goto L_0x01d9
            java.lang.String r0 = java.security.KeyStore.getDefaultType()
            java.security.KeyStore r0 = java.security.KeyStore.getInstance(r0)
            r6 = 0
            r0.load(r6, r6)
            com.mob.tools.network.SSLSocketFactoryEx r6 = new com.mob.tools.network.SSLSocketFactoryEx
            r6.<init>(r0)
            org.apache.http.conn.ssl.X509HostnameVerifier r0 = org.apache.http.conn.ssl.SSLSocketFactory.STRICT_HOSTNAME_VERIFIER
            r6.setHostnameVerifier(r0)
            java.lang.Object[] r0 = new java.lang.Object[r11]
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.newInstance(r4, r0)
            java.lang.String r4 = "HttpVersion"
            java.lang.String r12 = "HTTP_1_1"
            java.lang.Object r4 = com.mob.tools.utils.ReflectHelper.getStaticField(r4, r12)
            java.lang.Object[] r12 = new java.lang.Object[r9]
            r12[r11] = r0
            r12[r10] = r4
            java.lang.String r4 = "HttpProtocolParams"
            java.lang.String r13 = "setVersion"
            com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r4, r13, r12)
            java.lang.Object[] r12 = new java.lang.Object[r9]
            r12[r11] = r0
            java.lang.String r13 = "UTF-8"
            r12[r10] = r13
            java.lang.String r13 = "setContentCharset"
            com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r4, r13, r12)
            java.lang.Object[] r4 = new java.lang.Object[r11]
            java.lang.String r12 = "SchemeRegistry"
            java.lang.Object r4 = com.mob.tools.utils.ReflectHelper.newInstance(r12, r4)
            java.lang.Object[] r12 = new java.lang.Object[r11]
            java.lang.String r13 = "PlainSocketFactory"
            java.lang.String r14 = "getSocketFactory"
            java.lang.Object r12 = com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r13, r14, r12)
            r13 = 3
            java.lang.Object[] r14 = new java.lang.Object[r13]
            java.lang.String r15 = "http"
            r14[r11] = r15
            r14[r10] = r12
            r12 = 80
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r14[r9] = r12
            java.lang.String r12 = "Scheme"
            java.lang.Object r14 = com.mob.tools.utils.ReflectHelper.newInstance(r12, r14)
            java.lang.Object[] r13 = new java.lang.Object[r13]
            java.lang.String r15 = "https"
            r13[r11] = r15
            r13[r10] = r6
            r6 = 443(0x1bb, float:6.21E-43)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r13[r9] = r6
            java.lang.Object r6 = com.mob.tools.utils.ReflectHelper.newInstance(r12, r13)
            java.lang.Object[] r12 = new java.lang.Object[r10]
            r12[r11] = r14
            java.lang.String r13 = "register"
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r4, r13, r12)
            java.lang.Object[] r12 = new java.lang.Object[r10]
            r12[r11] = r6
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r4, r13, r12)
            java.lang.Object[] r6 = new java.lang.Object[r9]
            r6[r11] = r0
            r6[r10] = r4
            java.lang.String r4 = "ThreadSafeClientConnManager"
            java.lang.Object r4 = com.mob.tools.utils.ReflectHelper.newInstance(r4, r6)
            java.lang.Object[] r6 = new java.lang.Object[r9]
            r6[r11] = r4
            r6[r10] = r0
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.newInstance(r3, r6)
            goto L_0x01df
        L_0x01d9:
            java.lang.Object[] r0 = new java.lang.Object[r11]
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.newInstance(r3, r0)
        L_0x01df:
            java.lang.Object[] r3 = new java.lang.Object[r10]
            r3[r11] = r2
            java.lang.String r2 = "execute"
            java.lang.Object r2 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r2, r3)
            java.lang.Object[] r3 = new java.lang.Object[r11]
            java.lang.String r4 = "getConnectionManager"
            java.lang.Object r3 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r4, r3)
            java.lang.String r4 = "shutdown"
            if (r5 == 0) goto L_0x020d
            com.mob.tools.network.HttpConnectionImpl r0 = new com.mob.tools.network.HttpConnectionImpl     // Catch:{ Throwable -> 0x0205 }
            r0.<init>(r2)     // Catch:{ Throwable -> 0x0205 }
            r5.onResponse(r0)     // Catch:{ Throwable -> 0x0205 }
            java.lang.Object[] r0 = new java.lang.Object[r11]
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r3, r4, r0)
            goto L_0x0212
        L_0x0203:
            r0 = move-exception
            goto L_0x0207
        L_0x0205:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0203 }
        L_0x0207:
            java.lang.Object[] r2 = new java.lang.Object[r11]
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r3, r4, r2)
            throw r0
        L_0x020d:
            java.lang.Object[] r0 = new java.lang.Object[r11]
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r3, r4, r0)
        L_0x0212:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "use time: "
            r2.append(r3)
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r7
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.Object[] r3 = new java.lang.Object[r11]
            r0.mo29775i(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.httpPatchImpl(java.lang.String, java.util.ArrayList, com.mob.tools.network.KVPair, long, java.util.ArrayList, com.mob.tools.network.OnReadListener, com.mob.tools.network.HttpResponseCallback, com.mob.tools.network.NetworkHelper$NetworkTimeOut):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00bd, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00c1, code lost:
        r7.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00c4, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void httpPatchImpl23(java.lang.String r7, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r8, com.mob.tools.network.KVPair<java.lang.String> r9, long r10, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r12, com.mob.tools.network.OnReadListener r13, com.mob.tools.network.HttpResponseCallback r14, com.mob.tools.network.NetworkHelper.NetworkTimeOut r15) throws java.lang.Throwable {
        /*
            r6 = this;
            long r0 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "httpPatch: "
            r3.append(r4)
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            r4 = 0
            java.lang.Object[] r5 = new java.lang.Object[r4]
            r2.mo29775i(r3, r5)
            if (r8 == 0) goto L_0x003f
            java.lang.String r8 = r6.kvPairsToUrl(r8)
            int r2 = r8.length()
            if (r2 <= 0) goto L_0x003f
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r7)
            java.lang.String r7 = "?"
            r2.append(r7)
            r2.append(r8)
            java.lang.String r7 = r2.toString()
        L_0x003f:
            java.net.HttpURLConnection r7 = r6.getConnection(r7, r15)
            r8 = 1
            r7.setDoOutput(r8)
            r7.setChunkedStreamingMode(r4)
            java.lang.String r8 = "PATCH"
            r7.setRequestMethod(r8)
            java.lang.String r8 = "Content-Type"
            java.lang.String r15 = "application/offset+octet-stream"
            r7.setRequestProperty(r8, r15)
            if (r12 == 0) goto L_0x0072
            java.util.Iterator r8 = r12.iterator()
        L_0x005c:
            boolean r12 = r8.hasNext()
            if (r12 == 0) goto L_0x0072
            java.lang.Object r12 = r8.next()
            com.mob.tools.network.KVPair r12 = (com.mob.tools.network.KVPair) r12
            java.lang.String r15 = r12.name
            T r12 = r12.value
            java.lang.String r12 = (java.lang.String) r12
            r7.setRequestProperty(r15, r12)
            goto L_0x005c
        L_0x0072:
            boolean r8 = r6.instanceFollowRedirects
            r7.setInstanceFollowRedirects(r8)
            r7.connect()
            java.io.OutputStream r8 = r7.getOutputStream()
            com.mob.tools.network.FilePart r12 = new com.mob.tools.network.FilePart
            r12.<init>()
            r12.setOnReadListener(r13)
            T r9 = r9.value
            java.lang.String r9 = (java.lang.String) r9
            r12.setFile((java.lang.String) r9)
            r12.setOffset(r10)
            java.io.InputStream r9 = r12.toInputStream()
            r10 = 65536(0x10000, float:9.18355E-41)
            byte[] r10 = new byte[r10]
            int r11 = r9.read(r10)
        L_0x009c:
            if (r11 <= 0) goto L_0x00a6
            r8.write(r10, r4, r11)
            int r11 = r9.read(r10)
            goto L_0x009c
        L_0x00a6:
            r8.flush()
            r9.close()
            r8.close()
            if (r14 == 0) goto L_0x00c5
            com.mob.tools.network.HttpConnectionImpl23 r8 = new com.mob.tools.network.HttpConnectionImpl23     // Catch:{ Throwable -> 0x00bf }
            r8.<init>(r7)     // Catch:{ Throwable -> 0x00bf }
            r14.onResponse(r8)     // Catch:{ Throwable -> 0x00bf }
            r7.disconnect()
            goto L_0x00c8
        L_0x00bd:
            r8 = move-exception
            goto L_0x00c1
        L_0x00bf:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x00bd }
        L_0x00c1:
            r7.disconnect()
            throw r8
        L_0x00c5:
            r7.disconnect()
        L_0x00c8:
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
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.httpPatchImpl23(java.lang.String, java.util.ArrayList, com.mob.tools.network.KVPair, long, java.util.ArrayList, com.mob.tools.network.OnReadListener, com.mob.tools.network.HttpResponseCallback, com.mob.tools.network.NetworkHelper$NetworkTimeOut):void");
    }

    private String kvPairsToUrl(ArrayList<KVPair<String>> arrayList) throws Throwable {
        StringBuilder sb = new StringBuilder();
        Iterator<KVPair<String>> it = arrayList.iterator();
        while (it.hasNext()) {
            KVPair next = it.next();
            String urlEncode = Data.urlEncode(next.name, "utf-8");
            String urlEncode2 = next.value != null ? Data.urlEncode((String) next.value, "utf-8") : "";
            if (sb.length() > 0) {
                sb.append(Typography.amp);
            }
            sb.append(urlEncode);
            sb.append('=');
            sb.append(urlEncode2);
        }
        return sb.toString();
    }

    private HttpURLConnection getConnection(String str, NetworkTimeOut networkTimeOut) throws Throwable {
        Object obj;
        String str2;
        boolean z;
        HttpURLConnection httpURLConnection = (HttpURLConnection) URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
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
            instance.init((KeyManager[]) null, new TrustManager[]{new SimpleX509TrustManager((KeyStore) null)}, new SecureRandom());
            httpsURLConnection.setSSLSocketFactory(instance.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(x509HostnameVerifier);
        }
        int i = networkTimeOut == null ? connectionTimeout : networkTimeOut.connectionTimeout;
        if (i > 0) {
            httpURLConnection.setConnectTimeout(i);
        }
        int i2 = networkTimeOut == null ? readTimout : networkTimeOut.readTimout;
        if (i2 > 0) {
            httpURLConnection.setReadTimeout(i2);
        }
        return httpURLConnection;
    }

    public boolean getInstanceFollowRedirects() {
        return this.instanceFollowRedirects;
    }

    public void setInstanceFollowRedirects(boolean z) {
        this.instanceFollowRedirects = z;
    }

    public static boolean getFollowRedirects() {
        return followRedirects;
    }

    public static void setFollowRedirects(boolean z) {
        followRedirects = z;
    }

    public static final class SimpleX509TrustManager implements X509TrustManager {
        private X509TrustManager standardTrustManager;

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public SimpleX509TrustManager(KeyStore keyStore) {
            try {
                TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
                instance.init(keyStore);
                TrustManager[] trustManagers = instance.getTrustManagers();
                if (trustManagers == null || trustManagers.length == 0) {
                    throw new NoSuchAlgorithmException("no trust manager found.");
                }
                this.standardTrustManager = (X509TrustManager) trustManagers[0];
            } catch (Exception e) {
                NLog instance2 = MobLog.getInstance();
                instance2.mo29768d("failed to initialize the standard trust manager: " + e.getMessage(), new Object[0]);
                this.standardTrustManager = null;
            }
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            if (x509CertificateArr == null) {
                throw new IllegalArgumentException("there were no certificates.");
            } else if (x509CertificateArr.length == 1) {
                x509CertificateArr[0].checkValidity();
            } else {
                X509TrustManager x509TrustManager = this.standardTrustManager;
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

    /* JADX WARNING: Removed duplicated region for block: B:29:0x008e A[Catch:{ Throwable -> 0x00b8 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String checkHttpRequestUrl(java.lang.String r9) {
        /*
            java.lang.String r0 = "isCleartextTrafficPermitted"
            boolean r1 = android.text.TextUtils.isEmpty(r9)     // Catch:{ Throwable -> 0x00b8 }
            if (r1 != 0) goto L_0x00c0
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x00b8 }
            r2 = 23
            if (r1 < r2) goto L_0x00c0
            java.lang.String r1 = "android.security.NetworkSecurityPolicy"
            java.lang.String r1 = com.mob.tools.utils.ReflectHelper.importClass(r1)     // Catch:{ Throwable -> 0x00b8 }
            java.lang.String r2 = "getInstance"
            r3 = 0
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x00b8 }
            java.lang.Object r1 = com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r1, r2, r4)     // Catch:{ Throwable -> 0x00b8 }
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x00b8 }
            java.lang.Object r2 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r1, r0, r2)     // Catch:{ Throwable -> 0x00b8 }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ Throwable -> 0x00b8 }
            boolean r2 = r2.booleanValue()     // Catch:{ Throwable -> 0x00b8 }
            if (r2 != 0) goto L_0x00c0
            java.lang.String r9 = r9.trim()     // Catch:{ Throwable -> 0x00b8 }
            java.lang.String r2 = "http://"
            boolean r2 = r9.startsWith(r2)     // Catch:{ Throwable -> 0x00b8 }
            if (r2 == 0) goto L_0x00c0
            java.lang.String r2 = r9.trim()     // Catch:{ Throwable -> 0x00b8 }
            android.net.Uri r2 = android.net.Uri.parse(r2)     // Catch:{ Throwable -> 0x00b8 }
            if (r2 == 0) goto L_0x00c0
            java.lang.String r4 = r2.getScheme()     // Catch:{ Throwable -> 0x00b8 }
            if (r4 == 0) goto L_0x00c0
            java.lang.String r5 = "http"
            boolean r4 = r4.equals(r5)     // Catch:{ Throwable -> 0x00b8 }
            if (r4 == 0) goto L_0x00c0
            java.lang.String r4 = r2.getHost()     // Catch:{ Throwable -> 0x00b8 }
            java.lang.String r5 = r2.getPath()     // Catch:{ Throwable -> 0x00b8 }
            java.lang.String r6 = ""
            if (r4 == 0) goto L_0x00a0
            int r2 = r2.getPort()     // Catch:{ Throwable -> 0x00b8 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00b8 }
            r7.<init>()     // Catch:{ Throwable -> 0x00b8 }
            r7.append(r4)     // Catch:{ Throwable -> 0x00b8 }
            if (r2 <= 0) goto L_0x0080
            r4 = 80
            if (r2 != r4) goto L_0x006e
            goto L_0x0080
        L_0x006e:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00b8 }
            r4.<init>()     // Catch:{ Throwable -> 0x00b8 }
            java.lang.String r8 = ":"
            r4.append(r8)     // Catch:{ Throwable -> 0x00b8 }
            r4.append(r2)     // Catch:{ Throwable -> 0x00b8 }
            java.lang.String r2 = r4.toString()     // Catch:{ Throwable -> 0x00b8 }
            goto L_0x0081
        L_0x0080:
            r2 = r6
        L_0x0081:
            r7.append(r2)     // Catch:{ Throwable -> 0x00b8 }
            java.lang.String r4 = r7.toString()     // Catch:{ Throwable -> 0x00b8 }
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x00b8 }
            r7 = 24
            if (r2 < r7) goto L_0x00a0
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00b8 }
            r2[r3] = r4     // Catch:{ Throwable -> 0x00b8 }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r1, r0, r2)     // Catch:{ Throwable -> 0x00b8 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ Throwable -> 0x00b8 }
            boolean r0 = r0.booleanValue()     // Catch:{ Throwable -> 0x00b8 }
            if (r0 == 0) goto L_0x00a0
            return r9
        L_0x00a0:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00b8 }
            r0.<init>()     // Catch:{ Throwable -> 0x00b8 }
            java.lang.String r1 = "https://"
            r0.append(r1)     // Catch:{ Throwable -> 0x00b8 }
            r0.append(r4)     // Catch:{ Throwable -> 0x00b8 }
            if (r5 != 0) goto L_0x00b0
            r5 = r6
        L_0x00b0:
            r0.append(r5)     // Catch:{ Throwable -> 0x00b8 }
            java.lang.String r9 = r0.toString()     // Catch:{ Throwable -> 0x00b8 }
            return r9
        L_0x00b8:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.mo29769d(r0)
        L_0x00c0:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.checkHttpRequestUrl(java.lang.String):java.lang.String");
    }
}
