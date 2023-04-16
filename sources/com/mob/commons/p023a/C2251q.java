package com.mob.commons.p023a;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.mob.MobSDK;
import com.mob.commons.C2262b;
import com.mob.commons.C2293c;
import com.mob.commons.C2300e;
import com.mob.commons.C2312k;
import com.mob.tools.MobLog;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* renamed from: com.mob.commons.a.q */
/* compiled from: RtClt */
public class C2251q extends C2226d {

    /* renamed from: a */
    private static final String f1997a = (Build.VERSION.SDK_INT >= 16 ? "^u\\d+_a\\d+" : "^app_\\d+");

    /* renamed from: b */
    private PackageManager f1998b;

    /* renamed from: c */
    private Process f1999c = null;

    /* renamed from: d */
    private OutputStream f2000d = null;

    /* renamed from: e */
    private String f2001e = null;

    /* renamed from: f */
    private long f2002f = 0;

    /* renamed from: g */
    private boolean f2003g = true;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public File mo28993a() {
        if (Build.VERSION.SDK_INT > 24) {
            return null;
        }
        return C2300e.m2466a("comm/locks/.rc_lock");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b_ */
    public boolean mo28994b_() {
        return C2262b.m2307c();
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo28995d() {
        mo28999b(0);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28987a(Message message) {
        m2173i();
        mo28997a(0, (long) (C2262b.m2309d() * 1000));
    }

    /* renamed from: h */
    private void m2172h() throws Throwable {
        File dataCacheFile;
        if ((this.f2000d == null || TextUtils.isEmpty(this.f2001e)) && (dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.plst")) != null) {
            if (!dataCacheFile.exists()) {
                dataCacheFile.createNewFile();
            }
            this.f2001e = dataCacheFile.getAbsolutePath();
            this.f2002f = m2174j();
            this.f2003g = true;
            this.f2000d = null;
            this.f1999c = (Process) ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(C2312k.m2575a(146)), C2312k.m2575a(147), new Object[0]), C2312k.m2575a(148), C2312k.m2575a(0));
            this.f2000d = (OutputStream) ReflectHelper.invokeInstanceMethod(this.f1999c, C2312k.m2575a(150), new Object[0]);
        }
    }

    /* renamed from: i */
    private void m2173i() {
        String str;
        try {
            m2172h();
            if (C2262b.m2307c()) {
                long a = C2262b.m2260a();
                this.f2000d.write((C2312k.m2575a(1) + " " + this.f2001e + " " + C2312k.m2575a(2) + " \"" + "======================" + "\" >> " + this.f2001e + "\n").getBytes("ascii"));
                if (this.f2003g) {
                    str = C2312k.m2575a(3) + " \"" + a + "_0\" >> " + this.f2001e + "\n";
                    this.f2003g = false;
                } else {
                    str = C2312k.m2575a(3) + " \"" + a + Config.replace + C2262b.m2309d() + "\" >> " + this.f2001e + "\n";
                }
                this.f2000d.write(str.getBytes("ascii"));
                this.f2000d.flush();
                if (a >= this.f2002f) {
                    this.f2000d.write((C2312k.m2575a(4) + "\n").getBytes("ascii"));
                    this.f2000d.flush();
                    this.f2000d.close();
                    this.f1999c.waitFor();
                    this.f1999c.destroy();
                    if (m2169a(this.f2001e)) {
                        long k = m2175k();
                        if (k > 0) {
                            this.f2002f = k;
                        }
                        this.f2003g = true;
                    }
                    this.f1999c = (Process) ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(C2312k.m2575a(146)), C2312k.m2575a(147), new Object[0]), C2312k.m2575a(148), C2312k.m2575a(0));
                    this.f2000d = (OutputStream) ReflectHelper.invokeInstanceMethod(this.f1999c, C2312k.m2575a(150), new Object[0]);
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    /* renamed from: j */
    private long m2174j() {
        long j;
        long a = C2262b.m2260a();
        try {
            File dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.nulplt");
            if (dataCacheFile.exists()) {
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(dataCacheFile));
                long readLong = dataInputStream.readLong();
                dataInputStream.close();
                return readLong;
            }
            dataCacheFile.createNewFile();
            m2175k();
            j = C2262b.m2327v();
            return a + j;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            j = C2262b.m2327v();
        }
    }

    /* renamed from: k */
    private long m2175k() {
        long a = C2262b.m2260a() + C2262b.m2327v();
        DataOutputStream dataOutputStream = null;
        try {
            DataOutputStream dataOutputStream2 = new DataOutputStream(new FileOutputStream(ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.nulplt")));
            try {
                dataOutputStream2.writeLong(a);
                mo28998a((Closeable) dataOutputStream2);
                return a;
            } catch (Throwable th) {
                th = th;
                dataOutputStream = dataOutputStream2;
                mo28998a((Closeable) dataOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            MobLog.getInstance().mo29769d(th);
            mo28998a((Closeable) dataOutputStream);
            return 0;
        }
    }

    /* renamed from: a */
    private boolean m2169a(String str) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        m2166a(str, (ArrayList<ArrayList<HashMap<String, String>>>) arrayList, (ArrayList<long[]>) arrayList2);
        try {
            m2168a(m2163a((HashMap<String, String>[][]) m2170a(m2165a((ArrayList<ArrayList<HashMap<String, String>>>) arrayList), (ArrayList<ArrayList<HashMap<String, String>>>) arrayList), (ArrayList<long[]>) arrayList2), (ArrayList<long[]>) arrayList2);
        } catch (Throwable unused) {
        }
        return m2171b(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007a, code lost:
        r10 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007c, code lost:
        r10 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007d, code lost:
        r0 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x005a */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0062 A[Catch:{ Throwable -> 0x007c, all -> 0x007a }, LOOP:2: B:18:0x0060->B:19:0x0062, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007a A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0016] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m2166a(java.lang.String r10, java.util.ArrayList<java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.String>>> r11, java.util.ArrayList<long[]> r12) {
        /*
            r9 = this;
            r0 = 0
            java.util.HashMap r1 = r9.m2176l()     // Catch:{ Throwable -> 0x0082 }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0082 }
            r2.<init>(r10)     // Catch:{ Throwable -> 0x0082 }
            java.io.InputStreamReader r10 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0082 }
            java.lang.String r3 = "utf-8"
            r10.<init>(r2, r3)     // Catch:{ Throwable -> 0x0082 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0082 }
            r2.<init>(r10)     // Catch:{ Throwable -> 0x0082 }
            java.lang.String r10 = r2.readLine()     // Catch:{ Throwable -> 0x007c, all -> 0x007a }
            r0 = 0
            r3 = r10
            r10 = 0
        L_0x001d:
            r4 = 7
            if (r10 >= r4) goto L_0x0027
            java.lang.String r3 = r2.readLine()     // Catch:{ Throwable -> 0x007c, all -> 0x007a }
            int r10 = r10 + 1
            goto L_0x001d
        L_0x0027:
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ Throwable -> 0x007c, all -> 0x007a }
            r10.<init>()     // Catch:{ Throwable -> 0x007c, all -> 0x007a }
        L_0x002c:
            if (r3 == 0) goto L_0x0076
            java.lang.String r5 = "======================"
            boolean r5 = r5.equals(r3)     // Catch:{ Throwable -> 0x007c, all -> 0x007a }
            if (r5 == 0) goto L_0x0068
            java.lang.String r3 = r2.readLine()     // Catch:{ Throwable -> 0x007c, all -> 0x007a }
            java.lang.String r5 = "_"
            java.lang.String[] r3 = r3.split(r5)     // Catch:{ Throwable -> 0x005a, all -> 0x007a }
            r5 = 2
            long[] r5 = new long[r5]     // Catch:{ Throwable -> 0x005a, all -> 0x007a }
            r6 = r3[r0]     // Catch:{ Throwable -> 0x005a, all -> 0x007a }
            long r6 = java.lang.Long.parseLong(r6)     // Catch:{ Throwable -> 0x005a, all -> 0x007a }
            r5[r0] = r6     // Catch:{ Throwable -> 0x005a, all -> 0x007a }
            r6 = 1
            r3 = r3[r6]     // Catch:{ Throwable -> 0x005a, all -> 0x007a }
            long r7 = java.lang.Long.parseLong(r3)     // Catch:{ Throwable -> 0x005a, all -> 0x007a }
            r5[r6] = r7     // Catch:{ Throwable -> 0x005a, all -> 0x007a }
            r11.add(r10)     // Catch:{ Throwable -> 0x005a, all -> 0x007a }
            r12.add(r5)     // Catch:{ Throwable -> 0x005a, all -> 0x007a }
        L_0x005a:
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ Throwable -> 0x007c, all -> 0x007a }
            r10.<init>()     // Catch:{ Throwable -> 0x007c, all -> 0x007a }
            r3 = 0
        L_0x0060:
            if (r3 >= r4) goto L_0x0071
            r2.readLine()     // Catch:{ Throwable -> 0x007c, all -> 0x007a }
            int r3 = r3 + 1
            goto L_0x0060
        L_0x0068:
            int r5 = r3.length()     // Catch:{ Throwable -> 0x007c, all -> 0x007a }
            if (r5 <= 0) goto L_0x0071
            r9.m2167a((java.lang.String) r3, (java.util.HashMap<java.lang.String, java.lang.String[]>) r1, (java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.String>>) r10)     // Catch:{ Throwable -> 0x007c, all -> 0x007a }
        L_0x0071:
            java.lang.String r3 = r2.readLine()     // Catch:{ Throwable -> 0x007c, all -> 0x007a }
            goto L_0x002c
        L_0x0076:
            r9.mo28998a((java.io.Closeable) r2)
            goto L_0x008d
        L_0x007a:
            r10 = move-exception
            goto L_0x008e
        L_0x007c:
            r10 = move-exception
            r0 = r2
            goto L_0x0083
        L_0x007f:
            r10 = move-exception
            r2 = r0
            goto L_0x008e
        L_0x0082:
            r10 = move-exception
        L_0x0083:
            com.mob.tools.log.NLog r11 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x007f }
            r11.mo29769d(r10)     // Catch:{ all -> 0x007f }
            r9.mo28998a((java.io.Closeable) r0)
        L_0x008d:
            return
        L_0x008e:
            r9.mo28998a((java.io.Closeable) r2)
            goto L_0x0093
        L_0x0092:
            throw r10
        L_0x0093:
            goto L_0x0092
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p023a.C2251q.m2166a(java.lang.String, java.util.ArrayList, java.util.ArrayList):void");
    }

    /* renamed from: a */
    private void m2167a(String str, HashMap<String, String[]> hashMap, ArrayList<HashMap<String, String>> arrayList) {
        String[] split = str.replaceAll(" +", " ").split(" ");
        if (split != null && split.length >= 10) {
            String str2 = split[split.length - 1];
            if (split[split.length - 2].matches(f1997a) && !ViewHierarchyConstants.DIMENSION_TOP_KEY.equals(str2)) {
                if (hashMap == null || hashMap.isEmpty()) {
                    HashMap<String, String> a = m2164a(str2, split);
                    if (a != null) {
                        arrayList.add(a);
                        return;
                    }
                    return;
                }
                String[] strArr = hashMap.get(str2);
                if (strArr != null) {
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put(Config.INPUT_DEF_PKG, str2);
                    hashMap2.put("name", strArr[0]);
                    hashMap2.put("version", strArr[1]);
                    hashMap2.put("pcy", split[split.length - 3]);
                    arrayList.add(hashMap2);
                }
            }
        }
    }

    /* renamed from: a */
    private HashMap<String, String> m2164a(String str, String[] strArr) {
        CharSequence charSequence;
        try {
            if (this.f1998b == null) {
                this.f1998b = MobSDK.getContext().getPackageManager();
            }
            boolean z = false;
            PackageInfo packageInfo = this.f1998b.getPackageInfo(str, 0);
            boolean z2 = (packageInfo.applicationInfo.flags & 1) == 1;
            if ((packageInfo.applicationInfo.flags & 128) == 1) {
                z = true;
            }
            if (!z2 && !z) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Config.INPUT_DEF_PKG, str);
                try {
                    charSequence = packageInfo.applicationInfo.loadLabel(this.f1998b);
                } catch (Throwable unused) {
                    charSequence = null;
                }
                hashMap.put("name", charSequence == null ? packageInfo.packageName : charSequence.toString());
                hashMap.put("version", packageInfo.versionName);
                hashMap.put("pcy", strArr[strArr.length - 3]);
                return hashMap;
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        return null;
    }

    /* renamed from: l */
    private HashMap<String, String[]> m2176l() {
        ArrayList<HashMap<String, String>> m = m2177m();
        HashMap<String, String[]> hashMap = new HashMap<>();
        Iterator<HashMap<String, String>> it = m.iterator();
        while (it.hasNext()) {
            HashMap next = it.next();
            hashMap.put((String) next.get(Config.INPUT_DEF_PKG), new String[]{(String) next.get("name"), (String) next.get("version")});
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /* renamed from: m */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.String>> m2177m() {
        /*
            r7 = this;
            r0 = 0
            android.content.Context r1 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x000f }
            com.mob.tools.utils.DeviceHelper r1 = com.mob.tools.utils.DeviceHelper.getInstance(r1)     // Catch:{ Throwable -> 0x000f }
            r2 = 0
            java.util.ArrayList r1 = r1.getIA(r2)     // Catch:{ Throwable -> 0x000f }
            goto L_0x0018
        L_0x000f:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29787w((java.lang.Throwable) r1)
            r1 = r0
        L_0x0018:
            if (r1 != 0) goto L_0x0080
            android.content.Context r2 = com.mob.MobSDK.getContext()
            java.lang.String r3 = "comm/dbs/.al"
            java.io.File r2 = com.mob.tools.utils.ResHelper.getDataCacheFile(r2, r3)
            if (r2 == 0) goto L_0x0080
            boolean r3 = r2.exists()
            if (r3 == 0) goto L_0x0080
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0070 }
            r3.<init>()     // Catch:{ Throwable -> 0x0070 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0070 }
            r4.<init>(r2)     // Catch:{ Throwable -> 0x0070 }
            java.util.zip.GZIPInputStream r2 = new java.util.zip.GZIPInputStream     // Catch:{ Throwable -> 0x0070 }
            r2.<init>(r4)     // Catch:{ Throwable -> 0x0070 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0070 }
            java.lang.String r5 = "utf-8"
            r4.<init>(r2, r5)     // Catch:{ Throwable -> 0x0070 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0070 }
            r2.<init>(r4)     // Catch:{ Throwable -> 0x0070 }
            java.lang.String r0 = r2.readLine()     // Catch:{ Throwable -> 0x0069, all -> 0x0065 }
        L_0x004b:
            if (r0 == 0) goto L_0x0060
            com.mob.tools.utils.Hashon r4 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x0069, all -> 0x0065 }
            r4.<init>()     // Catch:{ Throwable -> 0x0069, all -> 0x0065 }
            java.util.HashMap r0 = r4.fromJson((java.lang.String) r0)     // Catch:{ Throwable -> 0x0069, all -> 0x0065 }
            if (r0 == 0) goto L_0x005b
            r3.add(r0)     // Catch:{ Throwable -> 0x0069, all -> 0x0065 }
        L_0x005b:
            java.lang.String r0 = r2.readLine()     // Catch:{ Throwable -> 0x0069, all -> 0x0065 }
            goto L_0x004b
        L_0x0060:
            r7.mo28998a((java.io.Closeable) r2)
            r1 = r3
            goto L_0x0080
        L_0x0065:
            r0 = move-exception
            r1 = r0
            r0 = r2
            goto L_0x007c
        L_0x0069:
            r0 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto L_0x0071
        L_0x006e:
            r1 = move-exception
            goto L_0x007c
        L_0x0070:
            r2 = move-exception
        L_0x0071:
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x006e }
            r3.mo29769d(r2)     // Catch:{ all -> 0x006e }
            r7.mo28998a((java.io.Closeable) r0)
            goto L_0x0080
        L_0x007c:
            r7.mo28998a((java.io.Closeable) r0)
            throw r1
        L_0x0080:
            if (r1 != 0) goto L_0x0087
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
        L_0x0087:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p023a.C2251q.m2177m():java.util.ArrayList");
    }

    /* renamed from: a */
    private HashMap<String, Integer> m2165a(ArrayList<ArrayList<HashMap<String, String>>> arrayList) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        Iterator<ArrayList<HashMap<String, String>>> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            Iterator it2 = it.next().iterator();
            while (it2.hasNext()) {
                HashMap hashMap2 = (HashMap) it2.next();
                String str = ((String) hashMap2.get(Config.INPUT_DEF_PKG)) + Config.TRACE_TODAY_VISIT_SPLIT + ((String) hashMap2.get("version"));
                if (!hashMap.containsKey(str)) {
                    hashMap.put(str, Integer.valueOf(i));
                    i++;
                }
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    private HashMap<String, String>[][] m2170a(HashMap<String, Integer> hashMap, ArrayList<ArrayList<HashMap<String, String>>> arrayList) {
        HashMap<String, String>[][] hashMapArr = (HashMap[][]) Array.newInstance(HashMap.class, new int[]{hashMap.size(), arrayList.size()});
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ArrayList arrayList2 = arrayList.get(i);
            int size2 = arrayList2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                HashMap<String, String> hashMap2 = (HashMap) arrayList2.get(i2);
                hashMapArr[hashMap.get(hashMap2.get(Config.INPUT_DEF_PKG) + Config.TRACE_TODAY_VISIT_SPLIT + hashMap2.get("version")).intValue()][i] = hashMap2;
            }
        }
        return hashMapArr;
    }

    /* renamed from: a */
    private ArrayList<HashMap<String, Object>> m2163a(HashMap<String, String>[][] hashMapArr, ArrayList<long[]> arrayList) {
        long j;
        HashMap<String, String>[][] hashMapArr2 = hashMapArr;
        ArrayList<HashMap<String, Object>> arrayList2 = new ArrayList<>(hashMapArr2.length);
        for (HashMap<String, String>[] hashMapArr3 : hashMapArr2) {
            HashMap hashMap = new HashMap();
            long j2 = 0;
            hashMap.put("runtimes", 0L);
            hashMap.put("fg", 0);
            hashMap.put("bg", 0);
            hashMap.put("empty", 0);
            arrayList2.add(hashMap);
            int length = hashMapArr3.length - 1;
            while (length >= 0) {
                if (hashMapArr3[length] != null) {
                    long longValue = ((Long) ResHelper.forceCast(hashMap.get("runtimes"), Long.valueOf(j2))).longValue();
                    if (length == 0) {
                        j = j2;
                        ArrayList<long[]> arrayList3 = arrayList;
                    } else {
                        j = arrayList.get(length)[1];
                    }
                    hashMap.put("runtimes", Long.valueOf(longValue + j));
                    if ("fg".equals(hashMapArr3[length].get("pcy"))) {
                        hashMap.put("fg", Integer.valueOf(((Integer) ResHelper.forceCast(hashMap.get("fg"), 0)).intValue() + 1));
                    } else if ("bg".equals(hashMapArr3[length].get("pcy"))) {
                        hashMap.put("bg", Integer.valueOf(((Integer) ResHelper.forceCast(hashMap.get("bg"), 0)).intValue() + 1));
                    } else {
                        hashMap.put("empty", Integer.valueOf(((Integer) ResHelper.forceCast(hashMap.get("empty"), 0)).intValue() + 1));
                    }
                    hashMap.put(Config.INPUT_DEF_PKG, hashMapArr3[length].get(Config.INPUT_DEF_PKG));
                    hashMap.put("name", hashMapArr3[length].get("name"));
                    hashMap.put("version", hashMapArr3[length].get("version"));
                } else {
                    ArrayList<long[]> arrayList4 = arrayList;
                }
                length--;
                j2 = 0;
            }
            ArrayList<long[]> arrayList5 = arrayList;
        }
        return arrayList2;
    }

    /* renamed from: a */
    private void m2168a(ArrayList<HashMap<String, Object>> arrayList, ArrayList<long[]> arrayList2) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", "APP_RUNTIMES");
        hashMap.put("list", arrayList);
        hashMap.put("datetime", Long.valueOf(C2262b.m2260a()));
        hashMap.put("recordat", Long.valueOf(arrayList2.get(0)[0]));
        long j = 0;
        for (int i = 1; i < arrayList2.size(); i++) {
            j += arrayList2.get(i)[1];
        }
        hashMap.put("sdk_runtime_len", Long.valueOf(j));
        hashMap.put("top_count", Integer.valueOf(arrayList2.size()));
        C2293c.m2435a().mo29068a(C2262b.m2260a(), (HashMap<String, Object>) hashMap);
    }

    /* renamed from: b */
    private boolean m2171b(String str) {
        try {
            File file = new File(str);
            file.delete();
            file.createNewFile();
            return true;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return false;
        }
    }
}
