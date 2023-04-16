package com.baidu.mobstat;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.at */
public class C0933at {

    /* renamed from: a */
    private static final C0933at f1118a = new C0933at();

    /* renamed from: b */
    private HashMap<String, String> f1119b = new HashMap<>();

    /* renamed from: c */
    private HashMap<Character, Integer> f1120c = new HashMap<>();

    /* renamed from: d */
    private HashMap<String, String> f1121d = new HashMap<>();

    /* renamed from: e */
    private HashMap<Character, Integer> f1122e = new HashMap<>();

    /* renamed from: f */
    private HashMap<String, String> f1123f = new HashMap<>();

    /* renamed from: g */
    private HashMap<Character, Integer> f1124g = new HashMap<>();

    /* renamed from: com.baidu.mobstat.at$a */
    public static class C0935a {

        /* renamed from: a */
        public static int f1126a = 0;

        /* renamed from: b */
        public static int f1127b = 1;

        /* renamed from: c */
        public static int f1128c = 2;
    }

    /* renamed from: a */
    public static C0933at m1079a() {
        return f1118a;
    }

    /* renamed from: a */
    public String mo11582a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (i == C0935a.f1126a) {
            String str2 = this.f1119b.get(str);
            if (!TextUtils.isEmpty(str2)) {
                return str2;
            }
            m1080a(str, this.f1120c, this.f1119b);
            return this.f1119b.get(str);
        } else if (i == C0935a.f1128c) {
            String str3 = this.f1123f.get(str);
            if (!TextUtils.isEmpty(str3)) {
                return str3;
            }
            m1080a(str, this.f1124g, this.f1123f);
            return this.f1123f.get(str);
        } else {
            String str4 = this.f1121d.get(str);
            if (!TextUtils.isEmpty(str4)) {
                return str4;
            }
            m1080a(str, this.f1122e, this.f1121d);
            return this.f1121d.get(str);
        }
    }

    /* renamed from: a */
    private void m1080a(String str, HashMap<Character, Integer> hashMap, HashMap<String, String> hashMap2) {
        int i = 0;
        char lowerCase = Character.toLowerCase(str.charAt(0));
        Integer num = hashMap.get(Character.valueOf(lowerCase));
        if (num != null) {
            i = num.intValue() + 1;
        }
        hashMap.put(Character.valueOf(lowerCase), Integer.valueOf(i));
        hashMap2.put(str, Character.toString(lowerCase) + i);
    }

    /* renamed from: a */
    public JSONObject mo11583a(int i) {
        HashMap<String, String> hashMap;
        if (i == C0935a.f1126a) {
            hashMap = this.f1119b;
        } else if (i == C0935a.f1128c) {
            hashMap = this.f1123f;
        } else {
            hashMap = this.f1121d;
        }
        JSONObject jSONObject = new JSONObject();
        if (hashMap == null) {
            return jSONObject;
        }
        ArrayList<Map.Entry> arrayList = new ArrayList<>(hashMap.entrySet());
        try {
            Collections.sort(arrayList, new Comparator<Map.Entry<String, String>>() {
                /* renamed from: a */
                public int compare(Map.Entry<String, String> entry, Map.Entry<String, String> entry2) {
                    return entry.getValue().compareTo(entry2.getValue());
                }
            });
        } catch (Exception unused) {
        }
        for (Map.Entry entry : arrayList) {
            try {
                jSONObject.put((String) entry.getValue(), (String) entry.getKey());
            } catch (Exception unused2) {
            }
        }
        return jSONObject;
    }

    /* renamed from: b */
    public void mo11585b(int i) {
        if (i == C0935a.f1126a) {
            this.f1120c.clear();
            this.f1119b.clear();
        } else if (i == C0935a.f1128c) {
            this.f1124g.clear();
            this.f1123f.clear();
        } else {
            this.f1122e.clear();
            this.f1121d.clear();
        }
    }

    /* renamed from: b */
    public void mo11584b() {
        mo11585b(C0935a.f1126a);
        mo11585b(C0935a.f1128c);
        mo11585b(C0935a.f1127b);
    }
}
