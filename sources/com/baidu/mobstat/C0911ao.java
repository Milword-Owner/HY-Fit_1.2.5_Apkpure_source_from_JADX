package com.baidu.mobstat;

import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.ao */
public class C0911ao {

    /* renamed from: a */
    private String f1032a;

    /* renamed from: b */
    private long f1033b;

    /* renamed from: c */
    private long f1034c;

    /* renamed from: d */
    private boolean f1035d;

    /* renamed from: e */
    private long f1036e;

    public C0911ao(String str, long j, long j2, long j3, boolean z) {
        this.f1032a = str;
        this.f1033b = j;
        this.f1034c = j2;
        this.f1036e = j3;
        this.f1035d = z;
    }

    /* renamed from: a */
    public void mo11520a(long j) {
        this.f1034c = j;
    }

    /* renamed from: a */
    public String mo11518a() {
        return this.f1032a;
    }

    /* renamed from: b */
    public long mo11521b() {
        return this.f1033b;
    }

    /* renamed from: c */
    public long mo11523c() {
        return this.f1034c;
    }

    /* renamed from: d */
    public boolean mo11524d() {
        return this.f1035d;
    }

    /* renamed from: e */
    public long mo11525e() {
        return this.f1036e;
    }

    /* renamed from: b */
    public void mo11522b(long j) {
        this.f1036e = j;
    }

    /* renamed from: a */
    public JSONObject mo11519a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", str);
            jSONObject.put("s", this.f1033b);
            jSONObject.put("e", this.f1034c);
            jSONObject.put("user", this.f1035d ? 1 : 0);
            return jSONObject;
        } catch (Exception unused) {
            return null;
        }
    }
}
