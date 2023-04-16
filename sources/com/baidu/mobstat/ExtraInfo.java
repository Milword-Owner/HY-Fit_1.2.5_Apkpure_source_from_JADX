package com.baidu.mobstat;

import android.text.TextUtils;
import com.baidubce.AbstractBceClient;
import org.json.JSONException;
import org.json.JSONObject;

public class ExtraInfo {

    /* renamed from: a */
    String f811a = "";

    /* renamed from: b */
    String f812b = "";

    /* renamed from: c */
    String f813c = "";

    /* renamed from: d */
    String f814d = "";

    /* renamed from: e */
    String f815e = "";

    /* renamed from: f */
    String f816f = "";

    /* renamed from: g */
    String f817g = "";

    /* renamed from: h */
    String f818h = "";

    /* renamed from: i */
    String f819i = "";

    /* renamed from: j */
    String f820j = "";

    /* renamed from: a */
    private static boolean m756a(String str, int i) {
        int i2;
        if (str == null) {
            return false;
        }
        try {
            i2 = str.getBytes().length;
        } catch (Exception unused) {
            i2 = 0;
        }
        return i2 > i;
    }

    /* renamed from: a */
    private static String m755a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (m756a(str, 1024)) {
            return "";
        }
        return str;
    }

    public String getV1() {
        return this.f811a;
    }

    public void setV1(String str) {
        this.f811a = m755a(str);
    }

    public String getV2() {
        return this.f812b;
    }

    public void setV2(String str) {
        this.f812b = m755a(str);
    }

    public String getV3() {
        return this.f813c;
    }

    public void setV3(String str) {
        this.f813c = m755a(str);
    }

    public String getV4() {
        return this.f814d;
    }

    public void setV4(String str) {
        this.f814d = m755a(str);
    }

    public String getV5() {
        return this.f815e;
    }

    public void setV5(String str) {
        this.f815e = m755a(str);
    }

    public String getV6() {
        return this.f816f;
    }

    public void setV6(String str) {
        this.f816f = m755a(str);
    }

    public String getV7() {
        return this.f817g;
    }

    public void setV7(String str) {
        this.f817g = m755a(str);
    }

    public String getV8() {
        return this.f818h;
    }

    public void setV8(String str) {
        this.f818h = m755a(str);
    }

    public String getV9() {
        return this.f819i;
    }

    public void setV9(String str) {
        this.f819i = m755a(str);
    }

    public String getV10() {
        return this.f820j;
    }

    public void setV10(String str) {
        this.f820j = m755a(str);
    }

    public JSONObject dumpToJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.f811a)) {
                jSONObject.put(AbstractBceClient.URL_PREFIX, this.f811a);
            }
            if (!TextUtils.isEmpty(this.f812b)) {
                jSONObject.put("v2", this.f812b);
            }
            if (!TextUtils.isEmpty(this.f813c)) {
                jSONObject.put("v3", this.f813c);
            }
            if (!TextUtils.isEmpty(this.f814d)) {
                jSONObject.put("v4", this.f814d);
            }
            if (!TextUtils.isEmpty(this.f815e)) {
                jSONObject.put("v5", this.f815e);
            }
            if (!TextUtils.isEmpty(this.f816f)) {
                jSONObject.put("v6", this.f816f);
            }
            if (!TextUtils.isEmpty(this.f817g)) {
                jSONObject.put("v7", this.f817g);
            }
            if (!TextUtils.isEmpty(this.f818h)) {
                jSONObject.put("v8", this.f818h);
            }
            if (!TextUtils.isEmpty(this.f819i)) {
                jSONObject.put("v9", this.f819i);
            }
            if (!TextUtils.isEmpty(this.f820j)) {
                jSONObject.put("v10", this.f820j);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
