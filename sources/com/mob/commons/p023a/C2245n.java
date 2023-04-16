package com.mob.commons.p023a;

import android.os.Message;
import android.os.SystemClock;
import com.baidu.mobstat.Config;
import com.facebook.share.internal.ShareConstants;
import com.mob.MobSDK;
import com.mob.commons.C2262b;
import com.mob.commons.C2293c;
import com.mob.commons.C2300e;
import com.mob.tools.MobLog;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;

/* renamed from: com.mob.commons.a.n */
/* compiled from: IMcClt */
public class C2245n extends C2226d {

    /* renamed from: a */
    private Random f1988a;

    /* renamed from: b */
    private DeviceHelper f1989b;

    /* renamed from: c */
    private HashMap<String, Object> f1990c;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public File mo28993a() {
        return C2300e.m2466a("comm/locks/.im_lock");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b_ */
    public boolean mo28994b_() {
        return C2262b.m2250Q() > 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo28995d() {
        if (C2262b.m2250Q() > 0) {
            mo28997a(1, (long) m2127k());
            return;
        }
        MobLog.getInstance().mo29768d("[%s] %s", "IMcClt", "Config no process");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28987a(Message message) {
        if (this.f1989b == null) {
            this.f1989b = DeviceHelper.getInstance(MobSDK.getContext());
        }
        if (message.what == 1) {
            m2124h();
        }
    }

    /* renamed from: h */
    private void m2124h() {
        MobLog.getInstance().mo29768d("[%s] %s", "IMcClt", ">>> Pre-obtain");
        if ("wifi".equals(this.f1989b.getNetworkType())) {
            HashMap<String, Object> hashMap = this.f1990c;
            if (hashMap == null || hashMap.isEmpty()) {
                this.f1990c = m2121a("comm/dbs/.imcd");
            }
            HashMap<String, Object> hashMap2 = this.f1990c;
            if (hashMap2 == null || hashMap2.isEmpty()) {
                m2125i();
            } else {
                String bssid = this.f1989b.getBssid();
                if (!this.f1990c.containsKey(bssid)) {
                    m2125i();
                } else if (C2262b.m2260a() >= ((Long) this.f1990c.get(bssid)).longValue()) {
                    m2125i();
                } else {
                    MobLog.getInstance().mo29768d("[%s] %s", "IMcClt", "Interval not reached");
                }
            }
            this.f1990c = null;
            this.f1988a = null;
            this.f1989b = null;
            return;
        }
        MobLog.getInstance().mo29768d("[%s] %s", "IMcClt", "No wifi");
        mo29001e();
    }

    /* renamed from: i */
    private void m2125i() {
        try {
            int i = 0;
            MobLog.getInstance().mo29768d("[%s] %s", "IMcClt", ">>> Obtain");
            if ("wifi".equals(this.f1989b.getNetworkType())) {
                MobLog.getInstance().mo29768d("[%s] %s", "IMcClt", "Communicating");
                m2126j();
                MobLog.getInstance().mo29768d("[%s] %s", "IMcClt", "Waiting for update");
                SystemClock.sleep(Config.BPLUS_DELAY_TIME);
                MobLog.getInstance().mo29768d("[%s] %s", "IMcClt", "Obtaining");
                ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
                while (arrayList.isEmpty() && i < 8) {
                    arrayList = this.f1989b.getArpList();
                    SystemClock.sleep(arrayList.size() > 0 ? 0 : 1000);
                    i++;
                }
                Collections.sort(arrayList, new C2247a());
                HashMap hashMap = new HashMap();
                hashMap.put("bssid", this.f1989b.getBssid());
                hashMap.put("ssid", this.f1989b.getSSID());
                hashMap.put("curip", this.f1989b.getIPAddress());
                hashMap.put("list", arrayList);
                m2123b(hashMap, "WIFI_IP_MAC");
                return;
            }
            MobLog.getInstance().mo29768d("[%s] %s", "IMcClt", "No wifi");
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    /* renamed from: j */
    private void m2126j() {
        String[] split = this.f1989b.getIPAddress().split("\\.");
        int i = 2;
        if (split.length > 2) {
            String str = split[0] + "." + split[1] + "." + split[2] + ".";
            try {
                DatagramPacket datagramPacket = new DatagramPacket(new byte[0], 0, 0);
                DatagramSocket datagramSocket = new DatagramSocket();
                while (i < 255) {
                    datagramPacket.setAddress(InetAddress.getByName(str + String.valueOf(i)));
                    datagramSocket.send(datagramPacket);
                    i++;
                    if (i == 125) {
                        datagramSocket.close();
                        datagramSocket = new DatagramSocket();
                    }
                }
                datagramSocket.close();
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r0v2, types: [java.util.HashMap<java.lang.String, java.lang.Object>] */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.HashMap<java.lang.String, java.lang.Object> m2121a(java.lang.String r5) {
        /*
            r4 = this;
            r0 = 0
            android.content.Context r1 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x0039, all -> 0x0037 }
            java.io.File r5 = com.mob.tools.utils.ResHelper.getDataCacheFile(r1, r5)     // Catch:{ Throwable -> 0x0039, all -> 0x0037 }
            boolean r1 = r5.exists()     // Catch:{ Throwable -> 0x0039, all -> 0x0037 }
            if (r1 != 0) goto L_0x0018
            java.util.HashMap r5 = new java.util.HashMap     // Catch:{ Throwable -> 0x0039, all -> 0x0037 }
            r5.<init>()     // Catch:{ Throwable -> 0x0039, all -> 0x0037 }
            r4.mo28998a((java.io.Closeable) r0)
            return r5
        L_0x0018:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0039, all -> 0x0037 }
            r1.<init>(r5)     // Catch:{ Throwable -> 0x0039, all -> 0x0037 }
            java.io.ObjectInputStream r5 = new java.io.ObjectInputStream     // Catch:{ Throwable -> 0x0039, all -> 0x0037 }
            r5.<init>(r1)     // Catch:{ Throwable -> 0x0039, all -> 0x0037 }
            java.lang.Object r1 = r5.readObject()     // Catch:{ Throwable -> 0x0032, all -> 0x002d }
            java.util.HashMap r1 = (java.util.HashMap) r1     // Catch:{ Throwable -> 0x0032, all -> 0x002d }
            r4.mo28998a((java.io.Closeable) r5)
            r0 = r1
            goto L_0x0045
        L_0x002d:
            r0 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
            goto L_0x004f
        L_0x0032:
            r1 = move-exception
            r3 = r1
            r1 = r5
            r5 = r3
            goto L_0x003b
        L_0x0037:
            r5 = move-exception
            goto L_0x004f
        L_0x0039:
            r5 = move-exception
            r1 = r0
        L_0x003b:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x004d }
            r2.mo29769d(r5)     // Catch:{ all -> 0x004d }
            r4.mo28998a((java.io.Closeable) r1)
        L_0x0045:
            if (r0 != 0) goto L_0x004c
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
        L_0x004c:
            return r0
        L_0x004d:
            r5 = move-exception
            r0 = r1
        L_0x004f:
            r4.mo28998a((java.io.Closeable) r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p023a.C2245n.m2121a(java.lang.String):java.util.HashMap");
    }

    /* renamed from: a */
    private void m2122a(HashMap<String, Object> hashMap, String str) {
        ObjectOutputStream objectOutputStream = null;
        try {
            File dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), str);
            if (hashMap != null) {
                if (!hashMap.isEmpty()) {
                    ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(dataCacheFile));
                    try {
                        objectOutputStream2.writeObject(hashMap);
                        mo28998a((Closeable) objectOutputStream2);
                        return;
                    } catch (Throwable th) {
                        th = th;
                        objectOutputStream = objectOutputStream2;
                        mo28998a((Closeable) objectOutputStream);
                        throw th;
                    }
                }
            }
            dataCacheFile.delete();
            mo28998a((Closeable) null);
        } catch (Throwable th2) {
            th = th2;
            MobLog.getInstance().mo29787w(th);
            mo28998a((Closeable) objectOutputStream);
        }
    }

    /* renamed from: b */
    private void m2123b(HashMap<String, Object> hashMap, String str) {
        MobLog.getInstance().mo29768d("[%s] %s", "IMcClt", "Write into queue");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("type", str);
        hashMap2.put(ShareConstants.WEB_DIALOG_PARAM_DATA, hashMap);
        hashMap2.put(Config.CELL_LOCATION, mo29002f());
        long a = C2262b.m2260a();
        hashMap2.put("datetime", Long.valueOf(a));
        C2293c.m2435a().mo29068a(a, (HashMap<String, Object>) hashMap2);
        if (this.f1990c == null) {
            this.f1990c = new HashMap<>();
        }
        if (this.f1990c.size() >= 500) {
            this.f1990c.clear();
        }
        this.f1990c.put((String) hashMap.get("bssid"), Long.valueOf(a + (C2262b.m2251R() * 1000)));
        m2122a(this.f1990c, "comm/dbs/.imcd");
    }

    /* renamed from: k */
    private int m2127k() {
        int i;
        if (C2262b.m2250Q() > 0) {
            if (this.f1988a == null) {
                this.f1988a = new Random();
            }
            i = this.f1988a.nextInt((int) C2262b.m2250Q());
        } else {
            i = 0;
        }
        return i * 1000;
    }

    /* renamed from: com.mob.commons.a.n$a */
    /* compiled from: IMcClt */
    private static class C2247a implements Comparator<HashMap<String, Object>> {
        private C2247a() {
        }

        /* renamed from: a */
        public int compare(HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
            if ((hashMap != null && !hashMap.isEmpty()) || (hashMap2 != null && !hashMap2.isEmpty())) {
                if ((hashMap == null || hashMap.isEmpty()) && hashMap2 != null && !hashMap2.isEmpty()) {
                    return -1;
                }
                if (hashMap != null && !hashMap.isEmpty() && (hashMap2 == null || hashMap2.isEmpty())) {
                    return 1;
                }
                try {
                    String str = (String) hashMap.get("ip");
                    String str2 = (String) hashMap2.get("ip");
                    if (str != null && !"".equals(str) && str2 != null && !"".equals(str2)) {
                        String[] split = str.split("\\.");
                        String[] split2 = str2.split("\\.");
                        if (split != null && split.length == 4 && split2 != null && split2.length == 4) {
                            int intValue = Integer.valueOf(split[2]).intValue();
                            int intValue2 = Integer.valueOf(split2[2]).intValue();
                            if (intValue < intValue2) {
                                return -1;
                            }
                            if (intValue == intValue2) {
                                int intValue3 = Integer.valueOf(split[3]).intValue();
                                int intValue4 = Integer.valueOf(split2[3]).intValue();
                                if (intValue3 < intValue4) {
                                    return -1;
                                }
                                if (intValue3 == intValue4) {
                                    return 0;
                                }
                            }
                            return 1;
                        }
                    }
                    return 0;
                } catch (Throwable th) {
                    MobLog.getInstance().mo29787w(th);
                }
            }
            return 0;
        }
    }
}
