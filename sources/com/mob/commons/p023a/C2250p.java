package com.mob.commons.p023a;

import android.os.Message;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.commons.C2262b;
import com.mob.commons.C2293c;
import com.mob.commons.C2300e;
import com.mob.tools.MobLog;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
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

/* renamed from: com.mob.commons.a.p */
/* compiled from: PkgSClt */
public class C2250p extends C2226d {

    /* renamed from: a */
    private Hashon f1995a = new Hashon();

    /* renamed from: b */
    private DeviceHelper f1996b;

    C2250p() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public File mo28993a() {
        return C2300e.m2466a("comm/locks/.pkgs_lock");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b_ */
    public boolean mo28994b_() {
        return C2262b.m2248O() > 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo28995d() {
        mo28999b(1);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28987a(Message message) {
        if (message.what == 1) {
            long O = C2262b.m2248O();
            if (O > 0) {
                m2156h();
                mo28997a(1, O * 1000);
            }
        }
    }

    /* renamed from: h */
    private void m2156h() {
        ArrayList<HashMap<String, String>> arrayList;
        boolean z;
        ArrayList<HashMap<String, String>> i = m2157i();
        try {
            if (this.f1996b == null) {
                this.f1996b = DeviceHelper.getInstance(MobSDK.getContext());
            }
            arrayList = this.f1996b.getSA();
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            arrayList = new ArrayList<>();
        }
        if (!arrayList.isEmpty()) {
            boolean z2 = i == null || i.isEmpty();
            if (!z2) {
                long j = m2158j();
                z2 = j == 0 || C2262b.m2260a() >= j;
                if (!z2) {
                    z2 = arrayList.size() != i.size();
                    if (!z2) {
                        Iterator<HashMap<String, String>> it = arrayList.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            String str = (String) it.next().get(Config.INPUT_DEF_PKG);
                            if (!TextUtils.isEmpty(str)) {
                                Iterator<HashMap<String, String>> it2 = i.iterator();
                                while (true) {
                                    if (it2.hasNext()) {
                                        if (str.equals(it2.next().get(Config.INPUT_DEF_PKG))) {
                                            z = true;
                                            break;
                                        }
                                    } else {
                                        z = false;
                                        break;
                                    }
                                }
                                if (!z) {
                                    z2 = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (z2) {
                m2154a("SYSTEM_APPS", arrayList);
                m2155a(arrayList);
                m2153a(C2262b.m2260a() + (C2262b.m2249P() * 1000));
            }
        }
    }

    /* renamed from: i */
    private ArrayList<HashMap<String, String>> m2157i() {
        ArrayList<HashMap<String, String>> arrayList;
        BufferedReader bufferedReader;
        File dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.sal");
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
                    HashMap fromJson = this.f1995a.fromJson(readLine);
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
    private void m2154a(String str, ArrayList<HashMap<String, String>> arrayList) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", str);
        hashMap.put("list", arrayList);
        long a = C2262b.m2260a();
        hashMap.put("datetime", Long.valueOf(a));
        C2293c.m2435a().mo29068a(a, (HashMap<String, Object>) hashMap);
    }

    /* renamed from: a */
    private void m2155a(ArrayList<HashMap<String, String>> arrayList) {
        OutputStreamWriter outputStreamWriter = null;
        try {
            OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new GZIPOutputStream(new FileOutputStream(ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.sal"))), "utf-8");
            try {
                Iterator<HashMap<String, String>> it = arrayList.iterator();
                while (it.hasNext()) {
                    outputStreamWriter2.append(this.f1995a.fromHashMap(it.next())).append(10);
                }
                mo28998a((Closeable) outputStreamWriter2);
            } catch (Throwable th) {
                th = th;
                outputStreamWriter = outputStreamWriter2;
                mo28998a((Closeable) outputStreamWriter);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            MobLog.getInstance().mo29769d(th);
            mo28998a((Closeable) outputStreamWriter);
        }
    }

    /* renamed from: a */
    private void m2153a(long j) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.snulal")));
            dataOutputStream.writeLong(j);
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    /* renamed from: j */
    private long m2158j() {
        File dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.snulal");
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
}
