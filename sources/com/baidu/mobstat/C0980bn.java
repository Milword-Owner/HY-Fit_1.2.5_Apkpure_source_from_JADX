package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/* renamed from: com.baidu.mobstat.bn */
public final class C0980bn {

    /* renamed from: a */
    private static final Proxy f1266a = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.172", 80));

    /* renamed from: b */
    private static final Proxy f1267b = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.200", 80));

    /* renamed from: a */
    public static void m1347a(Context context, String str, String str2, boolean z) {
        if (context != null) {
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = context.openFileOutput(str, z ? 32768 : 0);
                C0988bt.m1415a(new ByteArrayInputStream(str2.getBytes("utf-8")), fileOutputStream);
            } catch (Exception unused) {
            } catch (Throwable th) {
                C0988bt.m1414a(fileOutputStream);
                throw th;
            }
            C0988bt.m1414a(fileOutputStream);
        }
    }

    /* renamed from: a */
    public static String m1345a(Context context, String str) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(str);
            byte[] a = m1348a(fileInputStream);
            if (a != null) {
                String str2 = new String(a, "utf-8");
                C0988bt.m1414a(fileInputStream);
                return str2;
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            C0988bt.m1414a((Closeable) null);
            throw th;
        }
        C0988bt.m1414a(fileInputStream);
        return "";
    }

    /* renamed from: a */
    private static byte[] m1348a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (C0988bt.m1415a(inputStream, byteArrayOutputStream)) {
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    /* renamed from: b */
    public static boolean m1349b(Context context, String str) {
        return context.deleteFile(str);
    }

    /* renamed from: c */
    public static boolean m1350c(Context context, String str) {
        return context.getFileStreamPath(str).exists();
    }

    /* renamed from: d */
    public static HttpURLConnection m1351d(Context context, String str) throws IOException {
        return m1346a(context, str, 50000, 50000);
    }

    @SuppressLint({"DefaultLocale"})
    /* renamed from: a */
    public static HttpURLConnection m1346a(Context context, String str, int i, int i2) throws IOException {
        HttpURLConnection httpURLConnection;
        URL url = new URL(str);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
        if (networkInfo2 == null || !networkInfo2.isAvailable()) {
            if (networkInfo != null && networkInfo.isAvailable()) {
                String extraInfo = networkInfo.getExtraInfo();
                String lowerCase = extraInfo != null ? extraInfo.toLowerCase() : "";
                if (lowerCase.startsWith("cmwap") || lowerCase.startsWith("uniwap") || lowerCase.startsWith("3gwap")) {
                    httpURLConnection = (HttpURLConnection) url.openConnection(f1266a);
                } else if (lowerCase.startsWith("ctwap")) {
                    httpURLConnection = (HttpURLConnection) url.openConnection(f1267b);
                }
            }
            httpURLConnection = null;
        } else {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        if (httpURLConnection == null) {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        httpURLConnection.setConnectTimeout(i);
        httpURLConnection.setReadTimeout(i2);
        return httpURLConnection;
    }

    /* renamed from: e */
    public static boolean m1352e(Context context, String str) {
        boolean z = false;
        try {
            if (context.checkCallingOrSelfPermission(str) == 0) {
                z = true;
            }
        } catch (Exception unused) {
        }
        if (!z) {
            C0955bb c = C0955bb.m1194c();
            c.mo11627b("[WARNING] not have permission " + str + ", please add it in AndroidManifest.xml according our developer doc");
        }
        return z;
    }
}
