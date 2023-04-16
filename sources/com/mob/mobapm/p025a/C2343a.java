package com.mob.mobapm.p025a;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import com.mob.MobSDK;
import com.mob.mobapm.bean.CarrierType;
import com.mob.mobapm.core.C2357d;
import com.mob.mobapm.p026b.C2345a;
import com.mob.mobapm.p030d.C2373a;
import com.mob.mobapm.p031e.C2379e;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.mob.mobapm.a.a */
public class C2343a implements Handler.Callback {

    /* renamed from: c */
    private static C2343a f2224c;

    /* renamed from: d */
    private static boolean f2225d;

    /* renamed from: a */
    private HashMap<String, Object> f2226a = new HashMap<>();

    /* renamed from: b */
    private ExecutorService f2227b = Executors.newFixedThreadPool(8);

    /* renamed from: com.mob.mobapm.a.a$a */
    class C2344a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ HashMap f2228a;

        /* renamed from: b */
        final /* synthetic */ String f2229b;

        C2344a(C2343a aVar, HashMap hashMap, String str) {
            this.f2228a = hashMap;
            this.f2229b = str;
        }

        public void run() {
            try {
                HashMap hashMap = (HashMap) this.f2228a.get(this.f2229b);
                int intValue = (hashMap == null || !hashMap.containsKey("times")) ? 0 : ((Integer) hashMap.get("times")).intValue();
                long currentTimeMillis = System.currentTimeMillis();
                int i = -1;
                int i2 = intValue;
                boolean z = false;
                do {
                    Object a = C2357d.m2768a((HashMap<String, Object>) hashMap);
                    if (a instanceof HashMap) {
                        HashMap hashMap2 = (HashMap) a;
                        String str = (hashMap2 == null || hashMap2.isEmpty()) ? "-1" : (String) hashMap2.get("code");
                        C2373a.m2807a().mo29775i("APM: send radar code: " + str, new Object[0]);
                        i = Integer.valueOf(str).intValue();
                        if ("200".equals(str)) {
                            z = true;
                            i2 = 0;
                        } else {
                            i2--;
                            z = false;
                        }
                    }
                    if (i2 <= 0) {
                        break;
                    }
                } while (!z);
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                HashMap hashMap3 = new HashMap();
                hashMap3.put("taskId", hashMap.get("id"));
                hashMap3.put("url", hashMap.get("url"));
                hashMap3.put("responseIp", C2379e.m2820a(Uri.parse((String) hashMap.get("url")).getHost()));
                hashMap3.put("carrier", CarrierType.valuesOf(DeviceHelper.getInstance(MobSDK.getContext()).getCarrier()).name);
                hashMap3.put("rt", Long.valueOf(currentTimeMillis2));
                hashMap3.put("responseStatus", Integer.valueOf(i));
                Object c = C2357d.m2775c(hashMap3);
                if ((c instanceof HashMap) && ((Integer) ((HashMap) c).get("code")).intValue() == 200) {
                    C2345a.m2718c(hashMap);
                }
                C2373a.m2807a().mo29775i("APM: send radar result: " + c, new Object[0]);
            } catch (Throwable th) {
                C2373a.m2807a().mo29775i("APM: radar send error: " + th, new Object[0]);
            }
        }
    }

    private C2343a() {
        MobHandlerThread.newHandler(this);
        new Hashon();
    }

    /* renamed from: b */
    public static synchronized C2343a m2707b() {
        C2343a aVar;
        synchronized (C2343a.class) {
            if (f2224c == null) {
                f2224c = new C2343a();
            }
            aVar = f2224c;
        }
        return aVar;
    }

    /* renamed from: a */
    public void mo29161a() {
        HashMap<String, Object> j;
        if (f2225d && (j = C2345a.m2731j()) != null && !j.isEmpty()) {
            this.f2226a.putAll(j);
            for (String aVar : j.keySet()) {
                this.f2227b.submit(new C2344a(this, j, aVar));
            }
        }
    }

    public boolean handleMessage(Message message) {
        return false;
    }

    /* renamed from: a */
    public void mo29162a(boolean z) {
        f2225d = z;
    }
}
