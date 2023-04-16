package com.mob.commons;

import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import com.clj.fastble.BleManager;
import com.facebook.share.internal.ShareConstants;
import com.mob.MobSDK;
import com.mob.PrivacyPolicy;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/* renamed from: com.mob.commons.h */
/* compiled from: PolicyFetcher */
public class C2307h {

    /* renamed from: a */
    private static final String f2135a = (C2310j.m2570a() + "/privacy/policy");

    /* renamed from: b */
    private int f2136b = C2308i.m2566w();

    /* renamed from: c */
    private String f2137c = C2308i.m2565v();

    /* renamed from: d */
    private int f2138d = C2308i.m2568y();

    /* renamed from: e */
    private String f2139e = C2308i.m2567x();

    /* renamed from: f */
    private String f2140f = C2308i.m2569z();

    /* renamed from: a */
    public PrivacyPolicy mo29101a(int i, Locale locale) throws Throwable {
        if (i == 1 || i == 2) {
            if (locale == null) {
                if (Build.VERSION.SDK_INT >= 24) {
                    LocaleList locales = MobSDK.getContext().getResources().getConfiguration().getLocales();
                    if (locales != null && !locales.isEmpty()) {
                        locale = locales.get(0);
                    }
                } else {
                    locale = MobSDK.getContext().getResources().getConfiguration().locale;
                }
            }
            if (!m2493c(i, locale)) {
                return mo29102b(i, locale);
            }
            if (i == 1) {
                return new PrivacyPolicy(this.f2139e);
            }
            return new PrivacyPolicy(this.f2137c);
        }
        throw new IllegalArgumentException("Parameter 'type' should be either 1 or 2");
    }

    /* renamed from: c */
    private boolean m2493c(int i, Locale locale) {
        if (i == 1) {
            if (!TextUtils.isEmpty(this.f2139e) && this.f2138d >= C2212a.m1968h()) {
                return locale == null || locale.toString().equals(this.f2140f);
            }
            return false;
        } else if (i != 2 || TextUtils.isEmpty(this.f2137c) || this.f2136b < C2212a.m1968h()) {
            return false;
        } else {
            return locale == null || locale.toString().equals(this.f2140f);
        }
    }

    /* renamed from: b */
    public PrivacyPolicy mo29102b(int i, Locale locale) throws Throwable {
        int i2;
        DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
        String appkey = MobSDK.getAppkey();
        String packageName = instance.getPackageName();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("type", String.valueOf(i)));
        arrayList.add(new KVPair("appkey", appkey));
        arrayList.add(new KVPair("apppkg", packageName));
        if (i == 1) {
            i2 = C2308i.m2568y();
        } else {
            i2 = C2308i.m2566w();
        }
        arrayList.add(new KVPair("ppVersion", String.valueOf(i2)));
        arrayList.add(new KVPair("language", locale.toString()));
        NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
        networkTimeOut.readTimout = 30000;
        networkTimeOut.connectionTimeout = BleManager.DEFAULT_SCAN_TIME;
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Identity", MobProductCollector.getUserIdentity()));
        NLog instance2 = MobLog.getInstance();
        instance2.mo29768d("Request: " + f2135a + "\nHeaders: " + arrayList2 + "\nValues: " + arrayList, new Object[0]);
        String httpGet = new NetworkHelper().httpGet(f2135a, arrayList, arrayList2, networkTimeOut);
        NLog instance3 = MobLog.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("Response: ");
        sb.append(httpGet);
        instance3.mo29768d(sb.toString(), new Object[0]);
        Hashon hashon = new Hashon();
        HashMap fromJson = hashon.fromJson(httpGet);
        if (fromJson == null) {
            throw new Throwable("Response is illegal: " + httpGet);
        } else if ("200".equals(String.valueOf(fromJson.get("code")))) {
            Object obj = fromJson.get(ShareConstants.WEB_DIALOG_PARAM_DATA);
            if (obj != null) {
                String fromObject = hashon.fromObject(obj);
                if (!TextUtils.isEmpty(fromObject)) {
                    m2492a(i, locale.toString(), fromObject);
                    return new PrivacyPolicy(fromObject);
                }
                throw new Throwable("Response is illegal: " + httpGet);
            }
            throw new Throwable("Response is illegal: " + httpGet);
        } else {
            throw new Throwable("Response code is not 200: " + httpGet);
        }
    }

    /* renamed from: a */
    private void m2492a(int i, String str, String str2) {
        PrivacyPolicy privacyPolicy = new PrivacyPolicy(str2);
        if (i == 1) {
            this.f2139e = str2;
            this.f2138d = privacyPolicy.getPpVersion();
            C2308i.m2554l(this.f2139e);
            C2308i.m2515b(this.f2138d);
        } else if (i == 2) {
            this.f2137c = str2;
            this.f2136b = privacyPolicy.getPpVersion();
            C2308i.m2552k(this.f2137c);
            C2308i.m2507a(this.f2136b);
        }
        this.f2140f = str;
        C2308i.m2556m(this.f2140f);
    }
}
