package com.baidu.mobstat;

import android.text.TextUtils;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.an */
public class C0910an {

    /* renamed from: a */
    private String f1020a;

    /* renamed from: b */
    private String f1021b;

    /* renamed from: c */
    private String f1022c;

    /* renamed from: d */
    private long f1023d;

    /* renamed from: e */
    private long f1024e;

    /* renamed from: f */
    private float f1025f;

    /* renamed from: g */
    private float f1026g;

    /* renamed from: h */
    private float f1027h;

    /* renamed from: i */
    private float f1028i;

    /* renamed from: j */
    private String f1029j;

    /* renamed from: k */
    private boolean f1030k;

    /* renamed from: l */
    private String f1031l;

    public C0910an(String str, String str2, String str3, long j, long j2, float f, float f2, float f3, float f4, String str4, boolean z, String str5) {
        this.f1020a = str;
        this.f1021b = str2;
        this.f1022c = str3;
        this.f1023d = j;
        this.f1024e = j2;
        this.f1025f = f;
        this.f1026g = f2;
        this.f1027h = f3;
        this.f1028i = f4;
        this.f1029j = str4;
        this.f1030k = z;
        this.f1031l = str5;
    }

    /* renamed from: a */
    public String mo11515a() {
        return this.f1020a;
    }

    /* renamed from: b */
    public String mo11517b() {
        return this.f1029j;
    }

    /* renamed from: a */
    public JSONObject mo11516a(long j, String str, String str2) {
        if (TextUtils.isEmpty(this.f1031l)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("n", str);
            jSONObject.put("t", this.f1021b);
            jSONObject.put("d", this.f1023d);
            long j2 = this.f1024e - j;
            long j3 = 0;
            if (j2 > 0) {
                j3 = j2;
            }
            jSONObject.put("ps", j3);
            jSONObject.put("at", 1);
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
            decimalFormatSymbols.setDecimalSeparator('.');
            decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
            jSONObject.put(Config.SESSTION_ACTIVITY_X_VIEW_HEIGHT, decimalFormat.format((double) this.f1025f));
            jSONObject.put(Config.SESSTION_ACTIVITY_Y_VIEW_HEIGHT, decimalFormat.format((double) this.f1026g));
            jSONObject.put(Config.SESSTION_ACTIVITY_X_TOTAL_HEIGHT, decimalFormat.format((double) this.f1027h));
            jSONObject.put(Config.SESSTION_ACTIVITY_Y_TOTAL_HEIGHT, decimalFormat.format((double) this.f1028i));
            jSONObject.put(Config.f780PY, DataCore.instance().getPagePy());
            jSONObject.put("h5", 0);
            jSONObject.put("sign", this.f1031l);
            return jSONObject;
        } catch (Exception unused) {
            return null;
        }
    }
}
