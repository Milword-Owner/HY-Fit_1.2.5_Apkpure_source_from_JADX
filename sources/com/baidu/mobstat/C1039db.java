package com.baidu.mobstat;

/* renamed from: com.baidu.mobstat.db */
public class C1039db extends C1042de implements C1035cz {

    /* renamed from: a */
    private String f1378a = "*";

    /* renamed from: a */
    public void mo11816a(String str) throws IllegalArgumentException {
        if (str != null) {
            this.f1378a = str;
            return;
        }
        throw new IllegalArgumentException("http resource descriptor must not be null");
    }

    /* renamed from: a */
    public String mo11815a() {
        return this.f1378a;
    }
}
