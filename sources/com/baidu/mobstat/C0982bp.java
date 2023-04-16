package com.baidu.mobstat;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* renamed from: com.baidu.mobstat.bp */
public class C0982bp extends C0979bm {

    /* renamed from: a */
    private static final String f1269a = "__Baidu_Stat_SDK_SendRem";

    /* renamed from: b */
    private static C0982bp f1270b = new C0982bp();

    private C0982bp() {
    }

    /* renamed from: a */
    public static C0982bp m1357a() {
        return f1270b;
    }

    /* renamed from: a */
    public SharedPreferences mo11431a(Context context) {
        return context.getSharedPreferences(f1269a, 0);
    }

    /* renamed from: a */
    public void mo11674a(Context context, int i) {
        mo11669b(context, "sendLogtype", i);
    }

    /* renamed from: b */
    public int mo11678b(Context context) {
        return mo11665a(context, "sendLogtype", 0);
    }

    /* renamed from: b */
    public void mo11679b(Context context, int i) {
        mo11669b(context, "timeinterval", i);
    }

    /* renamed from: c */
    public int mo11683c(Context context) {
        return mo11665a(context, "timeinterval", 1);
    }

    /* renamed from: a */
    public void mo11677a(Context context, boolean z) {
        mo11672b(context, "onlywifi", z);
    }

    /* renamed from: d */
    public boolean mo11688d(Context context) {
        return mo11668a(context, "onlywifi", false);
    }

    /* renamed from: a */
    public void mo11676a(Context context, String str) {
        mo11671b(context, "device_id_1", str);
    }

    /* renamed from: e */
    public String mo11689e(Context context) {
        return mo11667a(context, "device_id_1", (String) null);
    }

    /* renamed from: b */
    public void mo11681b(Context context, String str) {
        if (mo11667a(context, "cuid", (String) null) != null) {
            mo11673c(context, "cuid");
        }
        mo11671b(context, "cuidsec_1", str);
        mo11673c(context, "cuidsec_1");
        mo11673c(context, "cuidsec_1");
        mo11673c(context, "cuidsec_2");
    }

    /* renamed from: d */
    public void mo11686d(Context context, String str) {
        mo11671b(context, "setchannelwithcodevalue", str);
    }

    /* renamed from: f */
    public String mo11691f(Context context) {
        return mo11667a(context, "setchannelwithcodevalue", (String) null);
    }

    /* renamed from: b */
    public void mo11682b(Context context, boolean z) {
        mo11672b(context, "setchannelwithcode", z);
    }

    /* renamed from: g */
    public boolean mo11694g(Context context) {
        return mo11668a(context, "setchannelwithcode", false);
    }

    /* renamed from: e */
    public void mo11690e(Context context, String str) {
        mo11671b(context, "mtjsdkmacss2_1", str);
    }

    /* renamed from: h */
    public String mo11695h(Context context) {
        return mo11667a(context, "mtjsdkmacss2_1", (String) null);
    }

    /* renamed from: c */
    public void mo11685c(Context context, boolean z) {
        mo11672b(context, "mtjtv", z);
    }

    /* renamed from: i */
    public boolean mo11698i(Context context) {
        return mo11668a(context, "mtjtv", false);
    }

    /* renamed from: f */
    public void mo11692f(Context context, String str) {
        mo11671b(context, "mtjsdkmacsstv_1", str);
    }

    /* renamed from: j */
    public String mo11699j(Context context) {
        return mo11667a(context, "mtjsdkmacsstv_1", (String) null);
    }

    /* renamed from: g */
    public void mo11693g(Context context, String str) {
        mo11671b(context, "he.ext", str);
    }

    /* renamed from: k */
    public String mo11701k(Context context) {
        return mo11667a(context, "he.ext", (String) null);
    }

    /* renamed from: h */
    public void mo11696h(Context context, String str) {
        mo11671b(context, "he.push", str);
    }

    /* renamed from: l */
    public String mo11703l(Context context) {
        return mo11667a(context, "he.push", (String) null);
    }

    /* renamed from: d */
    public void mo11687d(Context context, boolean z) {
        mo11672b(context, "mtjsdkmactrick", z);
    }

    /* renamed from: m */
    public boolean mo11706m(Context context) {
        return mo11668a(context, "mtjsdkmactrick", true);
    }

    /* renamed from: a */
    public void mo11675a(Context context, long j) {
        mo11670b(context, "autotrace_track_js_fetch_time", j);
    }

    /* renamed from: n */
    public long mo11707n(Context context) {
        return mo11666a(context, "autotrace_track_js_fetch_time", 0);
    }

    /* renamed from: b */
    public void mo11680b(Context context, long j) {
        mo11670b(context, "autotrace_track_js_fetch_interval", j);
    }

    /* renamed from: o */
    public long mo11709o(Context context) {
        return mo11666a(context, "autotrace_track_js_fetch_interval", 0);
    }

    /* renamed from: c */
    public void mo11684c(Context context, long j) {
        mo11670b(context, "autotrace_config_fetch_time", j);
    }

    /* renamed from: p */
    public long mo11711p(Context context) {
        return mo11666a(context, "autotrace_config_fetch_time", 0);
    }

    /* renamed from: i */
    public void mo11697i(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        mo11671b(context, "custom_userid", str);
    }

    /* renamed from: q */
    public String mo11712q(Context context) {
        return mo11667a(context, "custom_userid", "");
    }

    /* renamed from: j */
    public void mo11700j(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        mo11671b(context, "last_custom_userid", str);
    }

    /* renamed from: r */
    public String mo11713r(Context context) {
        return mo11667a(context, "last_custom_userid", "");
    }

    /* renamed from: k */
    public void mo11702k(Context context, String str) {
        mo11671b(context, "scheme_time", str);
    }

    /* renamed from: s */
    public String mo11714s(Context context) {
        return mo11667a(context, "scheme_time", "");
    }

    /* renamed from: l */
    public void mo11704l(Context context, String str) {
        mo11671b(context, "encrypt_device_id", str);
    }

    /* renamed from: t */
    public String mo11715t(Context context) {
        return mo11667a(context, "encrypt_device_id", "");
    }

    /* renamed from: m */
    public void mo11705m(Context context, String str) {
        mo11671b(context, Config.USER_PROPERTY, str);
    }

    /* renamed from: u */
    public String mo11716u(Context context) {
        return mo11667a(context, Config.USER_PROPERTY, "");
    }

    /* renamed from: n */
    public void mo11708n(Context context, String str) {
        mo11671b(context, "out_oaid", str);
    }

    /* renamed from: v */
    public String mo11717v(Context context) {
        return mo11667a(context, "out_oaid", "");
    }

    /* renamed from: o */
    public void mo11710o(Context context, String str) {
        mo11671b(context, "api_oaid", str);
    }

    /* renamed from: w */
    public String mo11718w(Context context) {
        return mo11667a(context, "api_oaid", "");
    }
}
