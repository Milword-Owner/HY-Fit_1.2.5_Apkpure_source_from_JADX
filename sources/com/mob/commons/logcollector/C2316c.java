package com.mob.commons.logcollector;

import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.clj.fastble.BleManager;
import com.facebook.internal.ServerProtocol;
import com.mob.MobSDK;
import com.mob.commons.C2212a;
import com.mob.commons.C2262b;
import com.mob.commons.C2300e;
import com.mob.commons.C2310j;
import com.mob.commons.LockAction;
import com.mob.commons.MobProductCollector;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.Hashon;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.GZIPOutputStream;
import p015io.reactivex.annotations.SchedulerSupport;

/* renamed from: com.mob.commons.logcollector.c */
/* compiled from: LogsManager */
public class C2316c {

    /* renamed from: b */
    private static C2316c f2157b;

    /* renamed from: c */
    private static String f2158c = C2310j.m2573b("api.exc.mob.com");

    /* renamed from: a */
    protected final Handler f2159a = MobHandlerThread.newHandler("l", (Handler.Callback) new Handler.Callback() {
        public boolean handleMessage(Message message) {
            C2212a.m1957b();
            if (C2262b.m2259Z()) {
                return false;
            }
            C2316c.this.mo29120a(message);
            return false;
        }
    });
    /* access modifiers changed from: private */

    /* renamed from: d */
    public HashMap<String, Integer> f2160d = new HashMap<>();

    /* renamed from: e */
    private NetworkHelper f2161e = new NetworkHelper();

    /* renamed from: f */
    private File f2162f;

    private C2316c() {
    }

    /* renamed from: a */
    public static synchronized C2316c m2603a() {
        C2316c cVar;
        synchronized (C2316c.class) {
            if (f2157b == null) {
                f2157b = new C2316c();
            }
            cVar = f2157b;
        }
        return cVar;
    }

    /* renamed from: a */
    public void mo29119a(int i, String str) {
        Message message = new Message();
        message.what = 100;
        message.arg1 = i;
        message.obj = str;
        this.f2159a.sendMessage(message);
    }

    /* renamed from: a */
    public void mo29118a(int i, int i2, String str, String str2) {
        Message message = new Message();
        message.what = 101;
        message.arg1 = i;
        message.arg2 = i2;
        message.obj = new Object[]{str, str2};
        this.f2159a.sendMessage(message);
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m2614b(Message message) {
        this.f2159a.sendMessageDelayed(message, 1000);
    }

    /* renamed from: b */
    public void mo29121b(int i, int i2, String str, String str2) {
        mo29118a(i, i2, str, str2);
        try {
            this.f2159a.wait();
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo29120a(Message message) {
        int i = message.what;
        if (i == 100) {
            m2616c(message);
        } else if (i == 101) {
            m2617d(message);
        }
    }

    /* renamed from: c */
    private void m2616c(Message message) {
        try {
            int i = message.arg1;
            String str = (String) message.obj;
            boolean af = C2262b.m2281af();
            boolean ag = C2262b.m2282ag();
            if (af) {
                m2608a(i, str, new String[]{String.valueOf(1)});
            } else if (ag) {
                m2608a(i, str, new String[]{String.valueOf(2)});
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    /* renamed from: b */
    private void m2613b() {
        if (this.f2162f == null) {
            this.f2162f = new File(MobSDK.getContext().getFilesDir(), ".lock");
        }
        if (!this.f2162f.exists()) {
            try {
                this.f2162f.createNewFile();
            } catch (Exception e) {
                MobLog.getInstance().mo29787w((Throwable) e);
            }
        }
    }

    /* renamed from: d */
    private void m2617d(Message message) {
        int i;
        Message message2 = message;
        try {
            int i2 = message2.arg1;
            Object[] objArr = (Object[]) message2.obj;
            String str = (String) objArr[0];
            final String str2 = (String) objArr[1];
            if (message2.arg2 == 3) {
                i = 2;
            } else {
                int i3 = message2.arg2;
                i = 1;
            }
            boolean af = C2262b.m2281af();
            boolean ag = C2262b.m2282ag();
            if (1 == i && !af) {
                return;
            }
            if (2 != i || ag) {
                String MD5 = Data.MD5(str2);
                m2613b();
                C23182 r12 = r1;
                final int i4 = i;
                File file = this.f2162f;
                final String str3 = MD5;
                String str4 = MD5;
                final Message message3 = message;
                C23182 r1 = new LockAction() {
                    public boolean run(FileLocker fileLocker) {
                        try {
                            C2321e.m2622a(C2262b.m2260a(), str2, i4, str3);
                        } catch (Throwable th) {
                            int intValue = (C2316c.this.f2160d.containsKey(str3) ? ((Integer) C2316c.this.f2160d.get(str3)).intValue() : 0) + 1;
                            C2316c.this.f2160d.put(str3, Integer.valueOf(intValue));
                            if (intValue < 3) {
                                C2316c.this.m2614b(message3);
                            } else {
                                C2316c.this.f2160d.remove(str3);
                                MobLog.getInstance().mo29787w(th);
                            }
                        }
                        return false;
                    }
                };
                if (C2300e.m2467a(file, r12)) {
                    this.f2160d.remove(str4);
                    if (1 == i && af) {
                        m2608a(i2, str, new String[]{String.valueOf(1)});
                    } else if (2 == i && ag) {
                        m2608a(i2, str, new String[]{String.valueOf(2)});
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    /* renamed from: c */
    private String m2615c() {
        return f2158c + "/errlog";
    }

    /* renamed from: a */
    private void m2608a(final int i, final String str, final String[] strArr) {
        try {
            if (!SchedulerSupport.NONE.equals(DeviceHelper.getInstance(MobSDK.getContext()).getDetailNetworkTypeForStatic())) {
                m2613b();
                C2300e.m2467a(this.f2162f, new LockAction() {
                    public boolean run(FileLocker fileLocker) {
                        try {
                            ArrayList<C2320d> a = C2321e.m2625a(strArr);
                            for (int i = 0; i < a.size(); i++) {
                                C2320d dVar = a.get(i);
                                HashMap a2 = C2316c.this.m2612b(i, str);
                                a2.put("errmsg", dVar.f2173a);
                                if (C2316c.this.m2611a(C2316c.this.m2605a(new Hashon().fromHashMap(a2)), true)) {
                                    C2321e.m2623a(dVar.f2174b);
                                }
                            }
                        } catch (Throwable th) {
                            MobLog.getInstance().mo29776i(th);
                        }
                        return false;
                    }
                });
                return;
            }
            throw new IllegalStateException("network is disconnected!");
        } catch (Throwable th) {
            MobLog.getInstance().mo29776i(th);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public HashMap<String, Object> m2612b(int i, String str) throws Throwable {
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
        hashMap.put("key", MobSDK.getAppkey());
        hashMap.put("plat", Integer.valueOf(instance.getPlatformCode()));
        hashMap.put(ServerProtocol.DIALOG_PARAM_SDK_VERSION, str);
        hashMap.put("sdkver", Integer.valueOf(i));
        hashMap.put("appname", instance.getAppName());
        hashMap.put("apppkg", instance.getPackageName());
        hashMap.put("appver", String.valueOf(instance.getAppVersion()));
        hashMap.put("model", instance.getModel());
        if (C2262b.m2305b()) {
            hashMap.put("deviceid", instance.getDeviceKey());
            hashMap.put("mac", instance.getMacAddress());
            hashMap.put("udid", instance.getDeviceId());
        }
        hashMap.put("sysver", String.valueOf(instance.getOSVersionInt()));
        hashMap.put("networktype", instance.getDetailNetworkTypeForStatic());
        return hashMap;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public String m2605a(String str) throws Throwable {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = byteArrayInputStream.read(bArr, 0, 1024);
            if (read != -1) {
                gZIPOutputStream.write(bArr, 0, read);
            } else {
                try {
                    break;
                } catch (Throwable unused) {
                }
            }
        }
        gZIPOutputStream.flush();
        gZIPOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        byteArrayInputStream.close();
        return Base64.encodeToString(byteArray, 2);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public boolean m2611a(String str, boolean z) throws Throwable {
        if (C2262b.m2242I()) {
            return false;
        }
        try {
            if (!SchedulerSupport.NONE.equals(DeviceHelper.getInstance(MobSDK.getContext()).getDetailNetworkTypeForStatic())) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new KVPair(Config.MODEL, str));
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new KVPair("User-Identity", MobProductCollector.getUserIdentity()));
                NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
                networkTimeOut.readTimout = BleManager.DEFAULT_SCAN_TIME;
                networkTimeOut.connectionTimeout = BleManager.DEFAULT_SCAN_TIME;
                this.f2161e.httpPost(m2615c(), (ArrayList<KVPair<String>>) arrayList, (KVPair<String>) null, (ArrayList<KVPair<String>>) arrayList2, networkTimeOut);
                return true;
            }
            throw new IllegalStateException("network is disconnected!");
        } catch (Throwable th) {
            MobLog.getInstance().mo29776i(th);
            return false;
        }
    }
}
