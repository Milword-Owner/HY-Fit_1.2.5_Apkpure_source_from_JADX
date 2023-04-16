package com.mob.mobapm.p031e;

import android.text.TextUtils;
import android.util.Base64;
import com.mob.MobSDK;
import com.mob.mobapm.bean.SocketTransaction;
import com.mob.mobapm.core.Transaction;
import com.mob.mobapm.p030d.C2373a;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.mob.mobapm.e.h */
public class C2383h {

    /* renamed from: c */
    private static C2383h f2292c;

    /* renamed from: a */
    private Hashon f2293a = new Hashon();

    /* renamed from: b */
    public byte[] f2294b;

    private C2383h() {
        String str;
        String str2 = "";
        String replace = DeviceHelper.getInstance(MobSDK.getContext()).getModel().replace(" ", str2);
        if (replace.length() > 16) {
            str = replace.substring(0, 16);
        } else if (16 % replace.length() == 0) {
            for (int i = 0; i < 16 / replace.length(); i++) {
                str2 = str2 + replace;
            }
            str = str2;
        } else {
            String str3 = str2;
            for (int i2 = 0; i2 < 16 / replace.length(); i2++) {
                str3 = str3 + replace;
            }
            str = str3 + replace.substring(0, 16 % replace.length());
        }
        this.f2294b = str.getBytes();
    }

    /* renamed from: a */
    public static synchronized C2383h m2825a() {
        C2383h hVar;
        synchronized (C2383h.class) {
            if (f2292c == null) {
                f2292c = new C2383h();
            }
            hVar = f2292c;
        }
        return hVar;
    }

    /* renamed from: b */
    public synchronized List<HashMap<String, Object>> mo29279b(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return (List) ((HashMap) this.f2293a.fromJson(new String(Data.AES128Decode(this.f2294b, Base64.decode(str, 2)), "utf-8"), HashMap.class)).get("fakelist");
            } catch (Throwable th) {
                NLog a = C2373a.m2807a();
                a.mo29775i("APM: encode trans error: " + th, new Object[0]);
            }
        }
        return null;
    }

    /* renamed from: c */
    public synchronized SocketTransaction mo29280c(String str) {
        try {
        } catch (Throwable th) {
            NLog a = C2373a.m2807a();
            a.mo29775i("APM: decode socket trans error: " + th, new Object[0]);
            return null;
        }
        return (SocketTransaction) this.f2293a.fromJson(new String(Data.AES128Decode(this.f2294b, Base64.decode(str, 2)), "utf-8"), SocketTransaction.class);
    }

    /* renamed from: d */
    public String mo29281d(String str) {
        try {
            return new String(Data.AES128Decode(this.f2294b, Base64.decode(str, 2)), "utf-8");
        } catch (Throwable th) {
            NLog a = C2373a.m2807a();
            a.mo29775i("APM: encode trans error: " + th, new Object[0]);
            return null;
        }
    }

    /* renamed from: e */
    public synchronized Transaction mo29282e(String str) {
        try {
        } catch (Throwable th) {
            NLog a = C2373a.m2807a();
            a.mo29775i("APM: decode trans error: " + th, new Object[0]);
            return null;
        }
        return (Transaction) this.f2293a.fromJson(new String(Data.AES128Decode(this.f2294b, Base64.decode(str, 2)), "utf-8"), Transaction.class);
    }

    /* renamed from: f */
    public String mo29283f(String str) {
        try {
            return Base64.encodeToString(Data.AES128Encode(this.f2294b, str), 2);
        } catch (Throwable th) {
            NLog a = C2373a.m2807a();
            a.mo29775i("APM: encode trans error: " + th, new Object[0]);
            return null;
        }
    }

    /* renamed from: a */
    public String mo29276a(Object obj) {
        try {
            HashMap fromJson = this.f2293a.fromJson(this.f2293a.fromObject(obj));
            fromJson.remove("transStatus");
            fromJson.remove("transType");
            return Base64.encodeToString(Data.AES128Encode(this.f2294b, this.f2293a.fromHashMap(fromJson)), 2);
        } catch (Throwable th) {
            NLog a = C2373a.m2807a();
            a.mo29775i("APM: encode trans error: " + th, new Object[0]);
            return null;
        }
    }

    /* renamed from: a */
    public synchronized String mo29277a(HashMap<String, Object> hashMap) {
        try {
        } catch (Throwable th) {
            NLog a = C2373a.m2807a();
            a.mo29775i("APM: encode trans error: " + th, new Object[0]);
            return null;
        }
        return Base64.encodeToString(Data.AES128Encode(this.f2294b, this.f2293a.fromHashMap(hashMap)), 2);
    }

    /* renamed from: a */
    public synchronized HashMap<String, Object> mo29278a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return this.f2293a.fromJson(new String(Data.AES128Decode(this.f2294b, Base64.decode(str, 2)), "utf-8"));
            } catch (Throwable th) {
                NLog a = C2373a.m2807a();
                a.mo29775i("APM: encode trans error: " + th, new Object[0]);
            }
        }
        return null;
    }
}
