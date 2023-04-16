package com.mob.commons.p023a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.huayu.tzc.utils.DateUtil;
import com.mob.MobSDK;
import com.mob.commons.C2262b;
import com.mob.commons.C2293c;
import com.mob.commons.C2300e;
import com.mob.tools.MobLog;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* renamed from: com.mob.commons.a.o */
/* compiled from: PkgClt */
public class C2248o extends C2226d {

    /* renamed from: a */
    private static final String[] f1991a = {"android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_CHANGED", "android.intent.action.PACKAGE_REMOVED", "android.intent.action.PACKAGE_REPLACED"};

    /* renamed from: b */
    private BroadcastReceiver f1992b;

    /* renamed from: c */
    private Hashon f1993c;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public File mo28993a() {
        return C2300e.m2466a("comm/locks/.pkg_lock");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b_ */
    public boolean mo28994b_() {
        if (!C2262b.m2311f()) {
            return true;
        }
        m2141h();
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo28995d() {
        mo28999b(2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28987a(Message message) {
        int i = message.what;
        if (i == 1) {
            m2146m();
        } else if (i == 2) {
            m2144k();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo28990b() {
        m2145l();
    }

    /* renamed from: h */
    private void m2141h() {
        ArrayList<HashMap<String, String>> arrayList;
        ArrayList<HashMap<String, String>> arrayList2;
        ArrayList<HashMap<String, String>> i = m2142i();
        if (i == null || i.isEmpty()) {
            try {
                arrayList = DeviceHelper.getInstance(MobSDK.getContext()).getIA(false);
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
                arrayList = new ArrayList<>();
            }
            m2135a(C2262b.m2326u(), "APPS_ALL", arrayList);
            m2136a(arrayList);
            m2134a(C2262b.m2260a() + (C2262b.m2313h() * 1000));
            return;
        }
        long a = C2262b.m2260a();
        long j = m2143j();
        if (j == 0 || a >= j) {
            try {
                arrayList2 = DeviceHelper.getInstance(MobSDK.getContext()).getIA(false);
            } catch (Throwable th2) {
                MobLog.getInstance().mo29787w(th2);
                arrayList2 = new ArrayList<>();
            }
            if (arrayList2 != null && !arrayList2.isEmpty()) {
                ArrayList<HashMap<String, String>> a2 = m2133a(arrayList2, i);
                m2135a(C2262b.m2326u(), "APPS_ALL", a2);
                m2136a(a2);
                m2134a(a + (C2262b.m2313h() * 1000));
                return;
            }
            return;
        }
        m2146m();
    }

    /* renamed from: i */
    private ArrayList<HashMap<String, String>> m2142i() {
        ArrayList<HashMap<String, String>> arrayList;
        BufferedReader bufferedReader;
        File dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.al");
        if (dataCacheFile != null && dataCacheFile.exists()) {
            BufferedReader bufferedReader2 = null;
            try {
                arrayList = new ArrayList<>();
                bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(dataCacheFile)), "utf-8"));
            } catch (Throwable th) {
                th = th;
                try {
                    MobLog.getInstance().mo29769d(th);
                    mo28998a((Closeable) bufferedReader2);
                    return new ArrayList<>();
                } catch (Throwable th2) {
                    th = th2;
                    mo28998a((Closeable) bufferedReader2);
                    throw th;
                }
            }
            try {
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    HashMap fromJson = m2147n().fromJson(readLine);
                    if (fromJson != null) {
                        arrayList.add(fromJson);
                    }
                }
                mo28998a((Closeable) bufferedReader);
                return arrayList;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                bufferedReader2 = bufferedReader;
                th = th4;
                mo28998a((Closeable) bufferedReader2);
                throw th;
            }
        }
        return new ArrayList<>();
    }

    /* renamed from: a */
    private void m2135a(long j, String str, ArrayList<HashMap<String, String>> arrayList) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", str);
        hashMap.put("list", arrayList);
        hashMap.put("datetime", Long.valueOf(C2262b.m2260a()));
        C2293c.m2435a().mo29068a(j, (HashMap<String, Object>) hashMap);
    }

    /* renamed from: a */
    private ArrayList<HashMap<String, String>> m2133a(ArrayList<HashMap<String, String>> arrayList, ArrayList<HashMap<String, String>> arrayList2) {
        if (arrayList == null || arrayList.isEmpty() || arrayList2 == null || arrayList2.isEmpty()) {
            return arrayList;
        }
        ArrayList<HashMap<String, String>> arrayList3 = new ArrayList<>();
        Iterator<HashMap<String, String>> it = arrayList.iterator();
        while (it.hasNext()) {
            HashMap next = it.next();
            boolean z = false;
            Iterator it2 = next.keySet().iterator();
            while (true) {
                if (it2.hasNext()) {
                    String str = (String) it2.next();
                    if (str != null && str.contains(Config.TRACE_VISIT_FIRST)) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (z) {
                arrayList3.add(next);
            } else {
                String str2 = (String) next.get(Config.INPUT_DEF_PKG);
                if (!TextUtils.isEmpty(str2)) {
                    Iterator<HashMap<String, String>> it3 = arrayList2.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            break;
                        }
                        HashMap next2 = it3.next();
                        if (str2.equals(next2.get(Config.INPUT_DEF_PKG))) {
                            Iterator it4 = next2.keySet().iterator();
                            while (true) {
                                if (it4.hasNext()) {
                                    String str3 = (String) it4.next();
                                    if (str3 != null && str3.contains(Config.TRACE_VISIT_FIRST)) {
                                        arrayList3.add(next2);
                                        z = true;
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                    if (!z) {
                        arrayList3.add(next);
                    }
                }
            }
        }
        return arrayList3;
    }

    /* renamed from: a */
    private void m2136a(ArrayList<HashMap<String, String>> arrayList) {
        try {
            m2140b(arrayList);
        } catch (Throwable unused) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    /* renamed from: b */
    private void m2140b(ArrayList<HashMap<String, String>> arrayList) throws Throwable {
        OutputStreamWriter outputStreamWriter;
        try {
            outputStreamWriter = new OutputStreamWriter(new GZIPOutputStream(new FileOutputStream(ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.al"))), "utf-8");
            try {
                Iterator<HashMap<String, String>> it = arrayList.iterator();
                while (it.hasNext()) {
                    outputStreamWriter.append(m2147n().fromHashMap(it.next())).append(10);
                }
                mo28998a((Closeable) outputStreamWriter);
            } catch (Throwable th) {
                th = th;
                mo28998a((Closeable) outputStreamWriter);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            outputStreamWriter = null;
            mo28998a((Closeable) outputStreamWriter);
            throw th;
        }
    }

    /* renamed from: a */
    private void m2134a(long j) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.nulal")));
            dataOutputStream.writeLong(j);
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    /* renamed from: j */
    private long m2143j() {
        File dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.nulal");
        if (!dataCacheFile.exists()) {
            return 0;
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(dataCacheFile));
            long readLong = dataInputStream.readLong();
            dataInputStream.close();
            return readLong;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return 0;
        }
    }

    /* renamed from: k */
    private void m2144k() {
        if (!C2262b.m2310e() || !C2262b.m2312g()) {
            mo28996a(1);
            m2145l();
        } else if (this.f1992b == null) {
            this.f1992b = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (C2248o.this.m2138a(intent != null ? intent.getAction() : null)) {
                        C2248o.this.mo28996a(1);
                        C2248o.this.mo28997a(1, (long) Config.BPLUS_DELAY_TIME);
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            int i = 0;
            while (true) {
                String[] strArr = f1991a;
                if (i >= strArr.length) {
                    break;
                }
                intentFilter.addAction(strArr[i]);
                i++;
            }
            intentFilter.addDataScheme("package");
            try {
                ReflectHelper.invokeInstanceMethod(MobSDK.getContext(), "registerReceiver", new Object[]{this.f1992b, intentFilter}, new Class[]{BroadcastReceiver.class, IntentFilter.class});
            } catch (Throwable unused) {
            }
        }
        mo28997a(2, (long) DateUtil.HOUR_MILL_SECONDS);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public boolean m2138a(String str) {
        for (String equals : f1991a) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: l */
    private void m2145l() {
        if (this.f1992b != null) {
            try {
                ReflectHelper.invokeInstanceMethod(MobSDK.getContext(), "unregisterReceiver", new Object[]{this.f1992b}, new Class[]{BroadcastReceiver.class});
            } catch (Throwable unused) {
            }
            this.f1992b = null;
        }
        if (this.f1993c != null) {
            this.f1993c = null;
        }
    }

    /* renamed from: m */
    private void m2146m() {
        ArrayList<HashMap<String, String>> arrayList;
        ArrayList<HashMap<String, String>> i = m2142i();
        try {
            arrayList = DeviceHelper.getInstance(MobSDK.getContext()).getIA(false);
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            arrayList = new ArrayList<>();
        }
        if (i == null || i.isEmpty()) {
            m2135a(C2262b.m2326u(), "APPS_ALL", arrayList);
            m2136a(arrayList);
            m2134a(C2262b.m2260a() + (C2262b.m2313h() * 1000));
            return;
        }
        ArrayList<HashMap<String, String>> b = m2139b(arrayList, i);
        if (!b.isEmpty()) {
            m2135a(C2262b.m2260a(), "APPS_INCR", b);
        }
        ArrayList<HashMap<String, String>> b2 = m2139b(i, arrayList);
        if (!b2.isEmpty()) {
            m2135a(C2262b.m2260a(), "UNINSTALL", b2);
        }
        m2136a(m2133a(arrayList, i));
    }

    /* renamed from: b */
    private ArrayList<HashMap<String, String>> m2139b(ArrayList<HashMap<String, String>> arrayList, ArrayList<HashMap<String, String>> arrayList2) {
        ArrayList<HashMap<String, String>> arrayList3 = new ArrayList<>();
        Iterator<HashMap<String, String>> it = arrayList.iterator();
        while (it.hasNext()) {
            HashMap next = it.next();
            String str = (String) next.get(Config.INPUT_DEF_PKG);
            if (!TextUtils.isEmpty(str)) {
                boolean z = false;
                Iterator<HashMap<String, String>> it2 = arrayList2.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (str.equals(it2.next().get(Config.INPUT_DEF_PKG))) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (!z) {
                    arrayList3.add(next);
                }
            }
        }
        return arrayList3;
    }

    /* renamed from: n */
    private Hashon m2147n() {
        if (this.f1993c == null) {
            this.f1993c = new Hashon();
        }
        return this.f1993c;
    }
}
