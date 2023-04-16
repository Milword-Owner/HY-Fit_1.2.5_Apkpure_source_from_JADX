package com.baidu.mobstat;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.baidu.mobstat.C0976bl;
import com.baidubce.http.Headers;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.r */
public class C1067r {

    /* renamed from: a */
    private static String f1432a = (Build.VERSION.SDK_INT < 9 ? "http://openrcv.baidu.com/1010/bplus.gif" : "https://openrcv.baidu.com/1010/bplus.gif");

    /* renamed from: b */
    private static C1067r f1433b;

    /* renamed from: c */
    private Handler f1434c;

    private C1067r() {
        HandlerThread handlerThread = new HandlerThread("LogSender");
        handlerThread.start();
        this.f1434c = new Handler(handlerThread.getLooper());
    }

    /* renamed from: a */
    public static C1067r m1765a() {
        if (f1433b == null) {
            synchronized (C1067r.class) {
                if (f1433b == null) {
                    f1433b = new C1067r();
                }
            }
        }
        return f1433b;
    }

    /* renamed from: a */
    public void mo11867a(final Context context, final String str) {
        C0954ba c = C0954ba.m1191c();
        c.mo11624a("data = " + str);
        if (str != null && !"".equals(str)) {
            this.f1434c.post(new Runnable() {
                public void run() {
                    try {
                        if (context != null) {
                            C1067r.this.m1772b(context, str);
                            C1067r.this.m1766a(context.getApplicationContext());
                        }
                    } catch (Throwable th) {
                        C0954ba.m1191c().mo11629b(th);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m1772b(Context context, String str) {
        C0980bn.m1347a(context, C1087y.f1474f + System.currentTimeMillis(), str, false);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1766a(Context context) {
        Iterator<String> it = m1773c(context, C1087y.f1474f).iterator();
        while (true) {
            int i = 0;
            while (it.hasNext()) {
                String next = it.next();
                String a = C0980bn.m1345a(context, next);
                if (TextUtils.isEmpty(a)) {
                    C0980bn.m1349b(context, next);
                } else if (m1774d(context, a)) {
                    C0980bn.m1349b(context, next);
                } else {
                    m1767a(context, a, next);
                    i++;
                    if (i >= 5) {
                        return;
                    }
                }
            }
            return;
        }
    }

    /* renamed from: c */
    private ArrayList<String> m1773c(Context context, final String str) {
        File filesDir;
        ArrayList<String> arrayList = new ArrayList<>();
        if (!(context == null || (filesDir = context.getFilesDir()) == null || !filesDir.exists())) {
            C10692 r1 = new FilenameFilter() {
                public boolean accept(File file, String str) {
                    return str.startsWith(str);
                }
            };
            String[] strArr = null;
            try {
                strArr = filesDir.list(r1);
            } catch (Exception unused) {
            }
            if (!(strArr == null || strArr.length == 0)) {
                try {
                    Arrays.sort(strArr, new Comparator<String>() {
                        /* renamed from: a */
                        public int compare(String str, String str2) {
                            return str.compareTo(str2);
                        }
                    });
                } catch (Exception unused2) {
                }
                for (String add : strArr) {
                    arrayList.add(add);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m1767a(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            JSONObject jSONObject = null;
            try {
                jSONObject = new JSONObject(str);
            } catch (Exception unused) {
            }
            JSONObject a = C1052h.m1710a(jSONObject);
            if (a != null) {
                C1052h.m1711b(a);
                if (jSONObject != null) {
                    C0980bn.m1347a(context, str2, jSONObject.toString(), false);
                }
            }
        }
    }

    /* renamed from: d */
    private boolean m1774d(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        if (!C0991bw.m1442c().booleanValue()) {
            return true;
        }
        try {
            m1771b(context, f1432a, str);
            return true;
        } catch (Exception e) {
            C0954ba.m1191c().mo11633c((Throwable) e);
            return false;
        }
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: b */
    private String m1771b(Context context, String str, String str2) throws IOException {
        byte[] bArr;
        String str3 = str2;
        boolean z = !str.startsWith("https:");
        HttpURLConnection d = C0980bn.m1351d(context, str);
        d.setDoOutput(true);
        d.setInstanceFollowRedirects(false);
        d.setUseCaches(false);
        d.setRequestProperty(Headers.CONTENT_ENCODING, "gzip");
        try {
            JSONObject jSONObject = new JSONObject(str3).getJSONArray(MessengerShareContentUtility.ATTACHMENT_PAYLOAD).getJSONObject(0).getJSONObject(Config.HEADER_PART);
            d.setRequestProperty(Headers.CONTENT_TYPE, "gzip");
            d.setRequestProperty("mtj_appversion", jSONObject.getString("n"));
            d.setRequestProperty("mtj_os", "Android");
            d.setRequestProperty("mtj_pn", jSONObject.getString(Config.PACKAGE_NAME));
            d.setRequestProperty("mtj_tg", "1");
            d.setRequestProperty("mtj_ii", jSONObject.getString(Config.CUID_SEC));
            d.setRequestProperty(Config.FROM, jSONObject.getString(Config.FROM));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        d.connect();
        try {
            OutputStream outputStream = d.getOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
            gZIPOutputStream.write(new byte[]{72, 77, 48, 49});
            gZIPOutputStream.write(new byte[]{0, 0, 0, 1});
            gZIPOutputStream.write(new byte[]{0, 0, 3, -14});
            gZIPOutputStream.write(new byte[]{0, 0, 0, 0, 0, 0, 0, 0});
            gZIPOutputStream.write(new byte[]{0, 2});
            if (z) {
                gZIPOutputStream.write(new byte[]{0, 1});
            } else {
                gZIPOutputStream.write(new byte[]{0, 0});
            }
            gZIPOutputStream.write(new byte[]{72, 77, 48, 49});
            if (z) {
                byte[] a = C0976bl.C0977a.m1327a();
                byte[] a2 = C0990bv.m1427a(false, C0983bq.m1404a(), a);
                gZIPOutputStream.write(m1770a((long) a2.length, 4));
                gZIPOutputStream.write(a2);
                bArr = C0976bl.C0977a.m1328a(a, new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, str3.getBytes("utf-8"));
                gZIPOutputStream.write(m1770a((long) bArr.length, 2));
            } else {
                bArr = str3.getBytes("utf-8");
            }
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            outputStream.close();
            int responseCode = d.getResponseCode();
            int contentLength = d.getContentLength();
            C0954ba.m1191c().mo11631c("code: " + responseCode + "; len: " + contentLength);
            if (responseCode == 200 && contentLength == 0) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(d.getInputStream()));
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        String sb2 = sb.toString();
                        d.disconnect();
                        return sb2;
                    }
                    sb.append(readLine);
                }
            } else {
                throw new IOException("Response code = " + d.getResponseCode());
            }
        } catch (Exception e2) {
            C0954ba.m1191c().mo11629b((Throwable) e2);
            d.disconnect();
            return "";
        } catch (Throwable th) {
            d.disconnect();
            throw th;
        }
    }

    /* renamed from: a */
    private static byte[] m1770a(long j, int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[(i - i2) - 1] = (byte) ((int) (255 & j));
            j >>= 8;
        }
        return bArr;
    }
}
