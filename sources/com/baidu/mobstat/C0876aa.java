package com.baidu.mobstat;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.aa */
public class C0876aa {

    /* renamed from: a */
    public boolean f929a = false;

    /* renamed from: b */
    public String f930b = "";

    /* renamed from: c */
    public boolean f931c = false;

    public C0876aa() {
    }

    public C0876aa(JSONObject jSONObject) {
        try {
            this.f929a = jSONObject.getBoolean("SDK_BPLUS_SERVICE");
        } catch (Exception e) {
            C0954ba.m1191c().mo11629b((Throwable) e);
        }
        try {
            this.f930b = jSONObject.getString("SDK_PRODUCT_LY");
        } catch (Exception e2) {
            C0954ba.m1191c().mo11629b((Throwable) e2);
        }
        try {
            this.f931c = jSONObject.getBoolean("SDK_LOCAL_SERVER");
        } catch (Exception e3) {
            C0954ba.m1191c().mo11629b((Throwable) e3);
        }
    }

    /* renamed from: a */
    public JSONObject mo11428a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("SDK_BPLUS_SERVICE", this.f929a);
        } catch (JSONException e) {
            C0954ba.m1191c().mo11629b((Throwable) e);
        }
        try {
            jSONObject.put("SDK_PRODUCT_LY", this.f930b);
        } catch (JSONException e2) {
            C0954ba.m1191c().mo11629b((Throwable) e2);
        }
        try {
            jSONObject.put("SDK_LOCAL_SERVER", this.f931c);
        } catch (JSONException e3) {
            C0954ba.m1191c().mo11629b((Throwable) e3);
        }
        return jSONObject;
    }
}
