package com.baidu.mobstat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.baidu.mobstat.C0976bl;
import com.baidubce.http.Headers;
import com.facebook.GraphResponse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

public class LogSender {

    /* renamed from: a */
    private static LogSender f858a = new LogSender();
    /* access modifiers changed from: private */

    /* renamed from: b */
    public boolean f859b = false;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public int f860c = 0;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public int f861d = 1;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public SendStrategyEnum f862e = SendStrategyEnum.APP_START;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public Timer f863f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public Handler f864g;

    public static LogSender instance() {
        return f858a;
    }

    private LogSender() {
        HandlerThread handlerThread = new HandlerThread("LogSenderThread");
        handlerThread.start();
        this.f864g = new Handler(handlerThread.getLooper());
    }

    public void setLogSenderDelayed(int i) {
        if (i >= 0 && i <= 30) {
            this.f860c = i;
        }
    }

    public void setSendLogStrategy(Context context, SendStrategyEnum sendStrategyEnum, int i, boolean z) {
        if (!sendStrategyEnum.equals(SendStrategyEnum.SET_TIME_INTERVAL)) {
            this.f862e = sendStrategyEnum;
            C0982bp.m1357a().mo11674a(context, this.f862e.ordinal());
            if (sendStrategyEnum.equals(SendStrategyEnum.ONCE_A_DAY)) {
                C0982bp.m1357a().mo11679b(context, 24);
            }
        } else if (i > 0 && i <= 24) {
            this.f861d = i;
            this.f862e = SendStrategyEnum.SET_TIME_INTERVAL;
            C0982bp.m1357a().mo11674a(context, this.f862e.ordinal());
            C0982bp.m1357a().mo11679b(context, this.f861d);
        }
        this.f859b = z;
        C0982bp.m1357a().mo11677a(context, this.f859b);
    }

    public void onSend(final Context context) {
        if (context != null) {
            context = context.getApplicationContext();
        }
        if (context != null) {
            this.f864g.post(new Runnable() {
                public void run() {
                    if (LogSender.this.f863f != null) {
                        LogSender.this.f863f.cancel();
                        Timer unused = LogSender.this.f863f = null;
                    }
                    SendStrategyEnum unused2 = LogSender.this.f862e = SendStrategyEnum.values()[C0982bp.m1357a().mo11678b(context)];
                    int unused3 = LogSender.this.f861d = C0982bp.m1357a().mo11683c(context);
                    boolean unused4 = LogSender.this.f859b = C0982bp.m1357a().mo11688d(context);
                    if (LogSender.this.f862e.equals(SendStrategyEnum.SET_TIME_INTERVAL)) {
                        LogSender.this.setSendingLogTimer(context);
                    } else if (LogSender.this.f862e.equals(SendStrategyEnum.ONCE_A_DAY)) {
                        LogSender.this.setSendingLogTimer(context);
                    }
                    LogSender.this.f864g.postDelayed(new Runnable() {
                        public void run() {
                            LogSender.this.m764a(context);
                        }
                    }, (long) (LogSender.this.f860c * 1000));
                }
            });
        }
    }

    public void setSendingLogTimer(Context context) {
        final Context applicationContext = context.getApplicationContext();
        long j = (long) (this.f861d * 3600000);
        try {
            this.f863f = new Timer();
            this.f863f.schedule(new TimerTask() {
                public void run() {
                    LogSender.this.m764a(applicationContext);
                }
            }, j, j);
        } catch (Exception unused) {
        }
    }

    public void saveLogData(Context context, String str, boolean z) {
        String str2 = z ? Config.PREFIX_SEND_DATA_FULL : Config.PREFIX_SEND_DATA;
        C0980bn.m1347a(context, str2 + System.currentTimeMillis(), str, false);
        if (z) {
            m765a(context, (long) Config.FULL_TRACE_LOG_LIMIT, Config.PREFIX_SEND_DATA_FULL);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        if (r2 == null) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0021, code lost:
        if (r2 != null) goto L_0x002e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003d A[LOOP:1: B:22:0x003b->B:23:0x003d, LOOP_END] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m765a(android.content.Context r8, long r9, java.lang.String r11) {
        /*
            r7 = this;
            java.util.ArrayList r11 = r7.m760a((android.content.Context) r8, (java.lang.String) r11)
            int r0 = r11.size()
            int r0 = r0 + -1
            r1 = 0
            r2 = 0
            r3 = r2
            r2 = r1
        L_0x000f:
            if (r0 < 0) goto L_0x003a
            java.lang.Object r5 = r11.get(r0)     // Catch:{ Exception -> 0x002b, all -> 0x0024 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x002b, all -> 0x0024 }
            java.io.FileInputStream r2 = r8.openFileInput(r5)     // Catch:{ Exception -> 0x002b, all -> 0x0024 }
            int r5 = r2.available()     // Catch:{ Exception -> 0x002b, all -> 0x0024 }
            long r5 = (long) r5
            long r3 = r3 + r5
            if (r2 == 0) goto L_0x0032
            goto L_0x002e
        L_0x0024:
            r8 = move-exception
            if (r2 == 0) goto L_0x002a
            r2.close()     // Catch:{ Exception -> 0x002a }
        L_0x002a:
            throw r8
        L_0x002b:
            if (r2 == 0) goto L_0x0032
        L_0x002e:
            r2.close()     // Catch:{ Exception -> 0x0031 }
        L_0x0031:
            r2 = r1
        L_0x0032:
            int r5 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r5 <= 0) goto L_0x0037
            goto L_0x003a
        L_0x0037:
            int r0 = r0 + -1
            goto L_0x000f
        L_0x003a:
            r9 = 0
        L_0x003b:
            if (r9 > r0) goto L_0x0049
            java.lang.Object r10 = r11.get(r9)
            java.lang.String r10 = (java.lang.String) r10
            com.baidu.mobstat.C0980bn.m1349b(r8, r10)
            int r9 = r9 + 1
            goto L_0x003b
        L_0x0049:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.LogSender.m765a(android.content.Context, long, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public ArrayList<String> m760a(Context context, final String str) {
        File filesDir;
        ArrayList<String> arrayList = new ArrayList<>();
        if (!(context == null || (filesDir = context.getFilesDir()) == null || !filesDir.exists())) {
            C08673 r1 = new FilenameFilter() {
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

    /* renamed from: com.baidu.mobstat.LogSender$a */
    class C0872a implements Callable<Object> {

        /* renamed from: b */
        private Context f882b;

        /* renamed from: c */
        private String f883c;

        /* renamed from: d */
        private String f884d;

        /* renamed from: e */
        private boolean f885e;

        C0872a(Context context, String str, String str2, boolean z) {
            this.f882b = context;
            this.f884d = str;
            this.f883c = str2;
            this.f885e = z;
        }

        public Object call() {
            boolean z;
            if (LogSender.this.m768a(this.f882b, this.f883c, this.f885e)) {
                C0980bn.m1349b(this.f882b, this.f884d);
                z = true;
            } else {
                LogSender.m773b(this.f882b, this.f884d, this.f883c);
                z = false;
            }
            return Boolean.valueOf(z);
        }
    }

    public void sendLogDataWithSyn(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(m760a(context, Config.PREFIX_SEND_DATA));
        int size = arrayList.size();
        if (size != 0) {
            if (size > 20) {
                size = 20;
            }
            ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(size);
            ArrayList<Future> arrayList2 = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Context context2 = context;
                arrayList2.add(newFixedThreadPool.submit(new C0872a(context2, (String) arrayList.get(i), C0980bn.m1345a(context, (String) arrayList.get(i)), ((String) arrayList.get(i)).contains(Config.PREFIX_SEND_DATA_FULL))));
            }
            newFixedThreadPool.shutdown();
            for (Future future : arrayList2) {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m764a(final Context context) {
        if (!this.f859b || C0991bw.m1470q(context)) {
            this.f864g.post(new Runnable() {
                public void run() {
                    try {
                        ArrayList arrayList = new ArrayList();
                        arrayList.addAll(LogSender.this.m760a(context, Config.PREFIX_SEND_DATA));
                        arrayList.addAll(LogSender.this.m760a(context, Config.PREFIX_SEND_DATA_FULL));
                        Iterator it = arrayList.iterator();
                        while (true) {
                            int i = 0;
                            while (it.hasNext()) {
                                String str = (String) it.next();
                                String a = C0980bn.m1345a(context, str);
                                if (TextUtils.isEmpty(a)) {
                                    C0980bn.m1349b(context, str);
                                } else {
                                    if (LogSender.this.m768a(context, a, str.contains(Config.PREFIX_SEND_DATA_FULL))) {
                                        C0980bn.m1349b(context, str);
                                    } else {
                                        LogSender.m773b(context, str, a);
                                        i++;
                                        if (i >= 5) {
                                            return;
                                        }
                                    }
                                }
                            }
                            return;
                        }
                    } catch (Exception unused) {
                    }
                }
            });
        }
    }

    public void sendLogData(Context context, final String str, boolean z) {
        if (context != null && !TextUtils.isEmpty(str)) {
            final Context applicationContext = context.getApplicationContext();
            if (z) {
                m772b(applicationContext, str);
            } else {
                this.f864g.post(new Runnable() {
                    public void run() {
                        LogSender.this.m772b(applicationContext, str);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m772b(Context context, String str) {
        String str2 = Config.PREFIX_SEND_DATA + System.currentTimeMillis();
        C0980bn.m1347a(context, str2, str, false);
        if (m777c(context, str)) {
            C0980bn.m1349b(context, str2);
        } else {
            m773b(context, str2, str);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static void m773b(Context context, String str, String str2) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str2);
        } catch (Exception unused) {
            jSONObject = null;
        }
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = (JSONObject) jSONObject.get(Config.TRACE_PART);
                jSONObject2.put(Config.TRACE_FAILED_CNT, jSONObject2.getLong(Config.TRACE_FAILED_CNT) + 1);
            } catch (Exception unused2) {
            }
            C0980bn.m1347a(context, str, jSONObject.toString(), false);
        }
    }

    public void sendEmptyLogData(Context context, final String str) {
        final Context applicationContext = context.getApplicationContext();
        this.f864g.post(new Runnable() {
            public void run() {
                String constructLogWithEmptyBody = DataCore.instance().constructLogWithEmptyBody(applicationContext, str);
                if (!TextUtils.isEmpty(constructLogWithEmptyBody)) {
                    boolean unused = LogSender.this.m777c(applicationContext, constructLogWithEmptyBody);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public boolean m777c(Context context, String str) {
        return m768a(context, str, false);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public boolean m768a(Context context, String str, boolean z) {
        if (!z) {
            C0955bb.m1194c().mo11624a("Start send log \n" + str);
        }
        boolean z2 = false;
        if (!this.f859b || C0991bw.m1470q(context)) {
            String str2 = Config.LOG_SEND_URL;
            if (z) {
                str2 = Config.LOG_FULL_SEND_URL;
            }
            try {
                m776c(context, str2, str);
                z2 = true;
            } catch (Exception e) {
                C0955bb.m1194c().mo11633c((Throwable) e);
            }
            if (!z) {
                String str3 = z2 ? GraphResponse.SUCCESS_KEY : "failed";
                C0955bb.m1194c().mo11624a("Send log " + str3);
            }
            return z2;
        }
        C0955bb.m1194c().mo11624a("[WARNING] wifi not available, log will be cached, next time will try to resend");
        return false;
    }

    /* renamed from: c */
    private String m776c(Context context, String str, String str2) throws Exception {
        if (!str.startsWith("https://")) {
            return m781e(context, str, str2);
        }
        return m780d(context, str, str2);
    }

    /* renamed from: d */
    private String m780d(Context context, String str, String str2) throws IOException {
        HttpURLConnection d = C0980bn.m1351d(context, str);
        d.setDoOutput(true);
        d.setInstanceFollowRedirects(false);
        d.setUseCaches(false);
        d.setRequestProperty(Headers.CONTENT_TYPE, "gzip");
        try {
            JSONObject jSONObject = new JSONObject(str2).getJSONObject(Config.HEADER_PART);
            d.setRequestProperty("mtj_appkey", jSONObject.getString(Config.APP_KEY));
            d.setRequestProperty("mtj_appversion", jSONObject.getString("n"));
            d.setRequestProperty("mtj_os", jSONObject.getString(Config.f779OS));
            d.setRequestProperty("mtj_pn", jSONObject.getString(Config.PACKAGE_NAME));
            d.setRequestProperty("mtj_tg", jSONObject.getString(Config.SDK_TAG));
            d.setRequestProperty("mtj_ii", jSONObject.getString(Config.CUID_SEC));
            d.setRequestProperty(Config.FROM, jSONObject.getString(Config.FROM));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        d.connect();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(d.getOutputStream())));
            bufferedWriter.write(str2);
            bufferedWriter.flush();
            bufferedWriter.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(d.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            int contentLength = d.getContentLength();
            if (d.getResponseCode() == 200 && contentLength == 0) {
                return sb.toString();
            }
            throw new IOException("http code = " + d.getResponseCode() + "; contentResponse = " + sb);
        } finally {
            d.disconnect();
        }
    }

    /* renamed from: e */
    private String m781e(Context context, String str, String str2) throws Exception {
        HttpURLConnection d = C0980bn.m1351d(context, str);
        d.setDoOutput(true);
        d.setInstanceFollowRedirects(false);
        d.setUseCaches(false);
        d.setRequestProperty(Headers.CONTENT_TYPE, "gzip");
        byte[] a = C0976bl.C0977a.m1327a();
        byte[] b = C0976bl.C0977a.m1330b();
        d.setRequestProperty("key", C0990bv.m1424a(a));
        d.setRequestProperty("iv", C0990bv.m1424a(b));
        byte[] a2 = C0976bl.C0977a.m1328a(a, b, str2.getBytes("utf-8"));
        d.connect();
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(d.getOutputStream());
            gZIPOutputStream.write(a2);
            gZIPOutputStream.flush();
            gZIPOutputStream.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(d.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            int contentLength = d.getContentLength();
            if (d.getResponseCode() == 200 && contentLength == 0) {
                return sb.toString();
            }
            throw new IOException("http code = " + d.getResponseCode() + "; contentResponse = " + sb);
        } finally {
            d.disconnect();
        }
    }
}
