package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class EventAnalysis {

    /* renamed from: a */
    private Map<String, C0863a> f800a = new HashMap();

    public void onEvent(Context context, long j, String str, String str2, int i, long j2, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        m746a(context, j, str, str2, i, j2, 0, extraInfo, map, z);
    }

    public void onEvent(Context context, long j, String str, String str2, int i, long j2, String str3, String str4, int i2, boolean z) {
        m747a(context, j, str, str2, i, j2, 0, str3, str4, i2);
    }

    public void onEvent(Context context, long j, String str, String str2, int i, long j2, JSONArray jSONArray, JSONArray jSONArray2, String str3, String str4, String str5, Map<String, String> map, boolean z) {
        flushEvent(context, j, str, str2, i, j2, jSONArray, jSONArray2, str3, str4, str5, map, z);
    }

    public void onEventStart(Context context, String str, String str2, long j) {
        C0863a aVar = new C0863a();
        aVar.f803c = j;
        aVar.f801a = str;
        aVar.f802b = str2;
        String a = m745a(str, str2);
        if (this.f800a.containsKey(a)) {
            C0955bb c = C0955bb.m1194c();
            c.mo11627b("[WARNING] eventId: " + str + ", with label: " + str2 + " is duplicated, older is removed");
        }
        this.f800a.put(a, aVar);
    }

    public void onEventEnd(Context context, long j, String str, String str2, long j2, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        String str3 = str;
        String str4 = str2;
        String a = m745a(str3, str4);
        C0863a aVar = this.f800a.get(a);
        if (aVar == null) {
            C0955bb c = C0955bb.m1194c();
            c.mo11627b("[WARNING] eventId: " + str3 + ", with label: " + str4 + " is not started or alread ended");
        } else if ((str3 == null || str3.equals(aVar.f801a)) && (str4 == null || str4.equals(aVar.f802b))) {
            this.f800a.remove(a);
            long j3 = j2 - aVar.f803c;
            if (j3 < 0) {
                C0955bb.m1194c().mo11627b("[WARNING] onEventEnd must be invoked after onEventStart");
            }
            onEventDuration(context, j, str, str2, aVar.f803c, j3, extraInfo, map, z);
        } else {
            C0955bb.m1194c().mo11627b("[WARNING] eventId/label pair not match");
        }
    }

    public void onEventDuration(Context context, long j, String str, String str2, long j2, long j3, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        if (j3 > 0) {
            m746a(context, j, str, str2, 1, j2, j3, extraInfo, map, z);
        }
    }

    /* renamed from: a */
    private void m746a(Context context, long j, String str, String str2, int i, long j2, long j3, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        Context context2 = context;
        Context context3 = context;
        DataCore.instance().putEvent(context3, getEvent(context, j, str, str2, i, j2, j3, "", "", 0, 0, extraInfo, map, z));
        DataCore.instance().flush(context3);
    }

    /* renamed from: a */
    private void m747a(Context context, long j, String str, String str2, int i, long j2, long j3, String str3, String str4, int i2) {
        Context context2 = context;
        Context context3 = context;
        DataCore.instance().putEvent(context3, getEvent(context, j, str, str2, i, j2, j3, str3, str4, i2, 1, (ExtraInfo) null, (Map<String, String>) null, false));
        DataCore.instance().flush(context3);
    }

    public void flushEvent(Context context, long j, String str, String str2, int i, long j2, JSONArray jSONArray, JSONArray jSONArray2, String str3, String str4, String str5, Map<String, String> map, boolean z) {
        Context context2 = context;
        String str6 = "";
        Context context3 = context;
        DataCore.instance().putEvent(context3, getEvent(context, j, str, str2, i, j2, 0, str6, jSONArray, jSONArray2, str3, str4, str5, Config.EventViewType.EDIT.getValue(), 2, (ExtraInfo) null, map, "", "", z));
        DataCore.instance().flush(context3);
    }

    /* renamed from: a */
    private String m745a(String str, String str2) {
        return "__sdk_" + str + "$|$" + str2;
    }

    /* renamed from: com.baidu.mobstat.EventAnalysis$a */
    static class C0863a {

        /* renamed from: a */
        String f801a;

        /* renamed from: b */
        String f802b;

        /* renamed from: c */
        long f803c;

        private C0863a() {
        }
    }

    public static JSONObject getEvent(Context context, long j, String str, String str2, int i, long j2, long j3, String str3, String str4, int i2, int i3, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        return getEvent(context, j, str, str2, i, j2, j3, str3, (JSONArray) null, (JSONArray) null, str4, (String) null, (String) null, i2, i3, extraInfo, map, "", "", z);
    }

    public static JSONObject getEvent(Context context, long j, String str, String str2, int i, long j2, long j3, String str3, JSONArray jSONArray, JSONArray jSONArray2, String str4, String str5, String str6, int i2, int i3, ExtraInfo extraInfo, Map<String, String> map, String str7, String str8, boolean z) {
        return getEvent(context, j, str, str2, "", i, j2, j3, str3, jSONArray, jSONArray2, str4, str5, str6, i2, i3, extraInfo, map, str7, str8, z, (JSONObject) null, "", (JSONArray) null);
    }

    /* JADX WARNING: type inference failed for: r35v0, types: [org.json.JSONArray] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject getEvent(android.content.Context r9, long r10, java.lang.String r12, java.lang.String r13, java.lang.String r14, int r15, long r16, long r18, java.lang.String r20, org.json.JSONArray r21, org.json.JSONArray r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, int r26, int r27, com.baidu.mobstat.ExtraInfo r28, java.util.Map<java.lang.String, java.lang.String> r29, java.lang.String r30, java.lang.String r31, boolean r32, org.json.JSONObject r33, java.lang.String r34, org.json.JSONArray r35) {
        /*
            r0 = r27
            r1 = r33
            java.lang.String r2 = "v"
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            java.lang.String r4 = "ss"
            r5 = r10
            r3.put(r4, r10)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = "i"
            r5 = r12
            r3.put(r4, r12)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = "l"
            r5 = r13
            r3.put(r4, r13)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = "c"
            r5 = r15
            r3.put(r4, r15)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = "t"
            r5 = r16
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = "d"
            r5 = r18
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = "h"
            r5 = r20
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0134 }
            r4 = 3
            java.lang.String r5 = "h3"
            java.lang.String r6 = "h2"
            if (r0 == r4) goto L_0x004a
            r4 = r21
            r3.put(r6, r4)     // Catch:{ Exception -> 0x0134 }
            r4 = r22
            r3.put(r5, r4)     // Catch:{ Exception -> 0x0134 }
            goto L_0x0067
        L_0x004a:
            r4 = r30
            r3.put(r6, r4)     // Catch:{ Exception -> 0x0134 }
            r4 = r31
            r3.put(r5, r4)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = "content"
            r5 = r14
            r3.put(r4, r14)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = "py"
            com.baidu.mobstat.DataCore r5 = com.baidu.mobstat.DataCore.instance()     // Catch:{ Exception -> 0x0134 }
            java.lang.String r5 = r5.getEventPy()     // Catch:{ Exception -> 0x0134 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0134 }
        L_0x0067:
            java.lang.String r4 = "p"
            r5 = r23
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = "p2"
            r5 = r24
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = "rn"
            r5 = r25
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0134 }
            r4 = r26
            r3.put(r2, r4)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = "at"
            r3.put(r4, r0)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r0 = "h5"
            if (r32 == 0) goto L_0x008c
            r4 = 1
            goto L_0x008d
        L_0x008c:
            r4 = 0
        L_0x008d:
            r3.put(r0, r4)     // Catch:{ Exception -> 0x0134 }
            if (r28 == 0) goto L_0x00a5
            org.json.JSONObject r0 = r28.dumpToJson()     // Catch:{ Exception -> 0x0134 }
            int r0 = r0.length()     // Catch:{ Exception -> 0x0134 }
            if (r0 == 0) goto L_0x00a5
            java.lang.String r0 = "ext"
            org.json.JSONObject r4 = r28.dumpToJson()     // Catch:{ Exception -> 0x0134 }
            r3.put(r0, r4)     // Catch:{ Exception -> 0x0134 }
        L_0x00a5:
            if (r29 == 0) goto L_0x00fe
            java.util.Set r0 = r29.entrySet()     // Catch:{ Exception -> 0x0134 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x0134 }
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ Exception -> 0x0134 }
            r4.<init>()     // Catch:{ Exception -> 0x0134 }
        L_0x00b4:
            boolean r5 = r0.hasNext()     // Catch:{ Exception -> 0x0134 }
            if (r5 == 0) goto L_0x00f3
            java.lang.Object r5 = r0.next()     // Catch:{ Exception -> 0x0134 }
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5     // Catch:{ Exception -> 0x0134 }
            java.lang.Object r6 = r5.getKey()     // Catch:{ Exception -> 0x0134 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x0134 }
            java.lang.Object r5 = r5.getValue()     // Catch:{ Exception -> 0x0134 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x0134 }
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Exception -> 0x0134 }
            if (r7 != 0) goto L_0x00b4
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception -> 0x0134 }
            if (r7 == 0) goto L_0x00d9
            goto L_0x00b4
        L_0x00d9:
            r7 = 1024(0x400, float:1.435E-42)
            boolean r7 = m750a((java.lang.String) r5, (int) r7)     // Catch:{ Exception -> 0x0134 }
            if (r7 == 0) goto L_0x00e2
            goto L_0x00b4
        L_0x00e2:
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ Exception -> 0x0134 }
            r7.<init>()     // Catch:{ Exception -> 0x0134 }
            java.lang.String r8 = "k"
            r7.put(r8, r6)     // Catch:{ Exception -> 0x0134 }
            r7.put(r2, r5)     // Catch:{ Exception -> 0x0134 }
            r4.put(r7)     // Catch:{ Exception -> 0x0134 }
            goto L_0x00b4
        L_0x00f3:
            int r0 = r4.length()     // Catch:{ Exception -> 0x0134 }
            if (r0 == 0) goto L_0x00fe
            java.lang.String r0 = "attribute"
            r3.put(r0, r4)     // Catch:{ Exception -> 0x0134 }
        L_0x00fe:
            if (r1 == 0) goto L_0x0113
            int r0 = r33.length()     // Catch:{ Exception -> 0x0134 }
            if (r0 == 0) goto L_0x0113
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ Exception -> 0x0134 }
            r0.<init>()     // Catch:{ Exception -> 0x0134 }
            r0.put(r1)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r1 = "point"
            r3.put(r1, r0)     // Catch:{ Exception -> 0x0134 }
        L_0x0113:
            java.lang.String r0 = "sign"
            boolean r1 = android.text.TextUtils.isEmpty(r34)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r2 = ""
            if (r1 == 0) goto L_0x011f
            r1 = r2
            goto L_0x0121
        L_0x011f:
            r1 = r34
        L_0x0121:
            r3.put(r0, r1)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r0 = "v5"
            if (r35 == 0) goto L_0x0131
            int r1 = r35.length()     // Catch:{ Exception -> 0x0134 }
            if (r1 != 0) goto L_0x012f
            goto L_0x0131
        L_0x012f:
            r2 = r35
        L_0x0131:
            r3.put(r0, r2)     // Catch:{ Exception -> 0x0134 }
        L_0x0134:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.EventAnalysis.getEvent(android.content.Context, long, java.lang.String, java.lang.String, java.lang.String, int, long, long, java.lang.String, org.json.JSONArray, org.json.JSONArray, java.lang.String, java.lang.String, java.lang.String, int, int, com.baidu.mobstat.ExtraInfo, java.util.Map, java.lang.String, java.lang.String, boolean, org.json.JSONObject, java.lang.String, org.json.JSONArray):org.json.JSONObject");
    }

    /* renamed from: a */
    private static boolean m750a(String str, int i) {
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

    /* renamed from: b */
    private static boolean m752b(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !new JSONObject().toString().equals(str)) {
            return true;
        }
        if (TextUtils.isEmpty(str2) || new JSONArray().toString().equals(str2)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void doEventMerge(org.json.JSONArray r25, org.json.JSONObject r26) {
        /*
            r1 = r26
            java.lang.String r0 = "s"
            com.baidu.mobstat.Config$EventViewType r2 = com.baidu.mobstat.Config.EventViewType.EDIT
            r2.getValue()
            java.lang.String r2 = "py"
            java.lang.String r21 = r1.optString(r2)     // Catch:{ JSONException -> 0x00c9 }
            java.lang.String r2 = "ss"
            long r2 = r1.optLong(r2)     // Catch:{ JSONException -> 0x00c9 }
            java.lang.String r4 = "i"
            java.lang.String r4 = r1.getString(r4)     // Catch:{ JSONException -> 0x00c9 }
            java.lang.String r5 = "l"
            java.lang.String r5 = r1.getString(r5)     // Catch:{ JSONException -> 0x00c9 }
            java.lang.String r6 = "t"
            long r6 = r1.getLong(r6)     // Catch:{ JSONException -> 0x00c9 }
            r8 = 3600000(0x36ee80, double:1.7786363E-317)
            long r7 = r6 / r8
            java.lang.String r6 = r1.optString(r0)     // Catch:{ JSONException -> 0x00c9 }
            java.lang.String r9 = "at"
            int r15 = r1.optInt(r9)     // Catch:{ JSONException -> 0x00c9 }
            java.lang.String r9 = "h"
            java.lang.String r9 = r1.optString(r9)     // Catch:{ JSONException -> 0x00c9 }
            r10 = 3
            java.lang.String r11 = "h3"
            java.lang.String r12 = "h2"
            java.lang.String r13 = ""
            r14 = 0
            if (r15 == r10) goto L_0x0053
            org.json.JSONArray r10 = r1.optJSONArray(r12)     // Catch:{ JSONException -> 0x00c9 }
            org.json.JSONArray r11 = r1.optJSONArray(r11)     // Catch:{ JSONException -> 0x00c9 }
            r17 = r13
            r18 = r17
            goto L_0x0061
        L_0x0053:
            java.lang.String r10 = r1.optString(r12)     // Catch:{ JSONException -> 0x00c9 }
            java.lang.String r11 = r1.optString(r11)     // Catch:{ JSONException -> 0x00c9 }
            r17 = r10
            r18 = r11
            r10 = r14
            r11 = r10
        L_0x0061:
            java.lang.String r12 = "p"
            java.lang.String r12 = r1.optString(r12)     // Catch:{ JSONException -> 0x00c9 }
            java.lang.String r13 = "p2"
            java.lang.String r13 = r1.optString(r13)     // Catch:{ JSONException -> 0x00c9 }
            java.lang.String r14 = "rn"
            java.lang.String r14 = r1.optString(r14)     // Catch:{ JSONException -> 0x00c9 }
            r16 = r15
            java.lang.String r15 = "v"
            int r15 = r1.optInt(r15)     // Catch:{ JSONException -> 0x00c9 }
            r19 = r0
            java.lang.String r0 = "ext"
            java.lang.String r0 = r1.optString(r0)     // Catch:{ JSONException -> 0x00c9 }
            r20 = r15
            java.lang.String r15 = "attribute"
            java.lang.String r15 = r1.optString(r15)     // Catch:{ JSONException -> 0x00c9 }
            r22 = r14
            java.lang.String r14 = "h5"
            int r23 = r1.optInt(r14)     // Catch:{ JSONException -> 0x00c9 }
            java.lang.String r14 = "sign"
            java.lang.String r24 = r1.optString(r14)     // Catch:{ JSONException -> 0x00c9 }
            java.lang.String r14 = "d"
            int r14 = r1.getInt(r14)     // Catch:{ JSONException -> 0x00a0 }
            goto L_0x00a1
        L_0x00a0:
            r14 = 0
        L_0x00a1:
            if (r14 != 0) goto L_0x00b9
            boolean r0 = m752b(r0, r15)
            if (r0 != 0) goto L_0x00b9
            r0 = r25
            r1 = r26
            r14 = r22
            r15 = r20
            r19 = r23
            r20 = r24
            m748a(r0, r1, r2, r4, r5, r6, r7, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            goto L_0x00c9
        L_0x00b9:
            int r0 = r25.length()
            java.lang.String r2 = "0"
            r3 = r19
            r1.put(r3, r2)     // Catch:{  }
            r2 = r25
            r2.put(r0, r1)     // Catch:{  }
        L_0x00c9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.EventAnalysis.doEventMerge(org.json.JSONArray, org.json.JSONObject):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0171, code lost:
        if (r4.equalsIgnoreCase(r5) != false) goto L_0x017a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x001a, code lost:
        if (r2.equals(r8) != false) goto L_0x001c;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void m748a(org.json.JSONArray r31, org.json.JSONObject r32, long r33, java.lang.String r35, java.lang.String r36, java.lang.String r37, long r38, java.lang.String r40, org.json.JSONArray r41, org.json.JSONArray r42, java.lang.String r43, java.lang.String r44, java.lang.String r45, int r46, int r47, java.lang.String r48, java.lang.String r49, int r50, java.lang.String r51, java.lang.String r52) {
        /*
            r0 = r31
            r1 = r32
            r2 = r37
            java.lang.String r3 = "t"
            java.lang.String r4 = "c"
            int r5 = r31.length()
            java.lang.String r6 = "0|"
            java.lang.String r7 = "s"
            java.lang.String r8 = ""
            if (r2 == 0) goto L_0x001c
            boolean r2 = r2.equals(r8)     // Catch:{ JSONException -> 0x001f }
            if (r2 == 0) goto L_0x001f
        L_0x001c:
            r1.put(r7, r6)     // Catch:{ JSONException -> 0x001f }
        L_0x001f:
            r10 = r5
            r9 = 0
        L_0x0021:
            if (r9 >= r5) goto L_0x0290
            org.json.JSONObject r11 = r0.getJSONObject(r9)     // Catch:{ JSONException -> 0x025f }
            java.lang.String r12 = "ss"
            long r12 = r11.optLong(r12)     // Catch:{ JSONException -> 0x025f }
            java.lang.String r14 = "i"
            java.lang.String r14 = r11.getString(r14)     // Catch:{ JSONException -> 0x025f }
            java.lang.String r15 = "l"
            java.lang.String r15 = r11.getString(r15)     // Catch:{ JSONException -> 0x025f }
            long r16 = r11.getLong(r3)     // Catch:{ JSONException -> 0x025f }
            r18 = 3600000(0x36ee80, double:1.7786363E-317)
            long r16 = r16 / r18
            java.lang.String r2 = "d"
            int r2 = r11.getInt(r2)     // Catch:{ JSONException -> 0x004b }
            r18 = r6
            goto L_0x004e
        L_0x004b:
            r18 = r6
            r2 = 0
        L_0x004e:
            java.lang.String r6 = "h"
            java.lang.String r6 = r11.optString(r6)     // Catch:{ JSONException -> 0x0250 }
            r19 = r10
            java.lang.String r10 = "p"
            java.lang.String r10 = r11.optString(r10)     // Catch:{ JSONException -> 0x0238 }
            java.lang.String r0 = "p2"
            java.lang.String r0 = r11.optString(r0)     // Catch:{ JSONException -> 0x0238 }
            r20 = r5
            java.lang.String r5 = "rn"
            java.lang.String r5 = r11.optString(r5)     // Catch:{ JSONException -> 0x0229 }
            r21 = r9
            java.lang.String r9 = "v"
            int r9 = r11.optInt(r9)     // Catch:{ JSONException -> 0x0212 }
            r22 = r3
            java.lang.String r3 = "at"
            int r3 = r11.optInt(r3)     // Catch:{ JSONException -> 0x0204 }
            r23 = r8
            java.lang.String r8 = "h3"
            r25 = r7
            java.lang.String r7 = "h2"
            r26 = 0
            r1 = 3
            if (r3 == r1) goto L_0x0099
            org.json.JSONArray r26 = r11.optJSONArray(r7)     // Catch:{ JSONException -> 0x01f2 }
            org.json.JSONArray r1 = r11.optJSONArray(r8)     // Catch:{ JSONException -> 0x01f2 }
            r7 = r1
            r24 = r4
            r8 = r23
            r1 = r26
            r26 = r8
            goto L_0x00a9
        L_0x0099:
            java.lang.String r1 = r11.optString(r7)     // Catch:{ JSONException -> 0x01f2 }
            java.lang.String r8 = r11.optString(r8)     // Catch:{ JSONException -> 0x01f2 }
            r24 = r4
            r7 = r26
            r26 = r8
            r8 = r1
            r1 = r7
        L_0x00a9:
            java.lang.String r4 = "ext"
            java.lang.String r4 = r11.optString(r4)     // Catch:{ JSONException -> 0x01dc }
            r27 = r8
            java.lang.String r8 = "attribute"
            java.lang.String r8 = r11.optString(r8)     // Catch:{ JSONException -> 0x01dc }
            r28 = r3
            java.lang.String r3 = "h5"
            int r3 = r11.optInt(r3)     // Catch:{ JSONException -> 0x01dc }
            r29 = r3
            java.lang.String r3 = "sign"
            java.lang.String r3 = r11.optString(r3)     // Catch:{ JSONException -> 0x01dc }
            int r30 = (r16 > r38 ? 1 : (r16 == r38 ? 0 : -1))
            if (r30 != 0) goto L_0x01dc
            if (r2 == 0) goto L_0x00cf
            goto L_0x01dc
        L_0x00cf:
            boolean r2 = m752b(r4, r8)     // Catch:{ JSONException -> 0x01dc }
            if (r2 == 0) goto L_0x00d7
            goto L_0x01dc
        L_0x00d7:
            java.lang.String r2 = "py"
            java.lang.String r2 = r11.optString(r2)     // Catch:{ JSONException -> 0x01dc }
            int r4 = (r12 > r33 ? 1 : (r12 == r33 ? 0 : -1))
            if (r4 != 0) goto L_0x01dc
            r4 = r35
            boolean r8 = r14.equals(r4)     // Catch:{ JSONException -> 0x01dc }
            if (r8 == 0) goto L_0x01dc
            r8 = r36
            boolean r12 = r15.equals(r8)     // Catch:{ JSONException -> 0x01d9 }
            if (r12 == 0) goto L_0x01d9
            r12 = r40
            boolean r6 = r6.equals(r12)     // Catch:{ JSONException -> 0x01d6 }
            if (r6 == 0) goto L_0x01d6
            r6 = r43
            boolean r10 = r10.equals(r6)     // Catch:{ JSONException -> 0x01d6 }
            if (r10 == 0) goto L_0x01d6
            r10 = r44
            boolean r0 = r0.equals(r10)     // Catch:{ JSONException -> 0x01d3 }
            if (r0 == 0) goto L_0x01d3
            r0 = r41
            boolean r1 = m751a((org.json.JSONArray) r1, (org.json.JSONArray) r0)     // Catch:{ JSONException -> 0x01d3 }
            if (r1 == 0) goto L_0x01d3
            r1 = r42
            boolean r7 = m751a((org.json.JSONArray) r7, (org.json.JSONArray) r1)     // Catch:{ JSONException -> 0x01d3 }
            if (r7 == 0) goto L_0x01d3
            r7 = r45
            boolean r5 = r5.equals(r7)     // Catch:{ JSONException -> 0x01d3 }
            if (r5 == 0) goto L_0x01d3
            r5 = r46
            if (r9 != r5) goto L_0x01d3
            r9 = r47
            r13 = r28
            if (r13 != r9) goto L_0x01d0
            r13 = r48
            r14 = r27
            boolean r14 = r14.equals(r13)     // Catch:{ JSONException -> 0x01cd }
            if (r14 == 0) goto L_0x01cd
            r14 = r49
            r15 = r26
            boolean r15 = r15.equals(r14)     // Catch:{ JSONException -> 0x01ca }
            if (r15 == 0) goto L_0x01ca
            r15 = r50
            r0 = r29
            if (r0 != r15) goto L_0x01c7
            r0 = r51
            boolean r3 = r3.equals(r0)     // Catch:{ JSONException -> 0x01c7 }
            if (r3 == 0) goto L_0x01c7
            r3 = r52
            boolean r2 = r2.equals(r3)     // Catch:{ JSONException -> 0x01c7 }
            if (r2 == 0) goto L_0x01c7
            r2 = r32
            r0 = r24
            int r16 = r2.getInt(r0)     // Catch:{ JSONException -> 0x01ee }
            int r17 = r11.getInt(r0)     // Catch:{ JSONException -> 0x01ee }
            int r1 = r16 + r17
            r3 = r25
            java.lang.String r4 = r11.optString(r3)     // Catch:{ JSONException -> 0x027c }
            if (r4 == 0) goto L_0x0178
            r5 = r23
            boolean r16 = r4.equalsIgnoreCase(r5)     // Catch:{ JSONException -> 0x0174 }
            if (r16 == 0) goto L_0x017c
            goto L_0x017a
        L_0x0174:
            r23 = r5
            goto L_0x027c
        L_0x0178:
            r5 = r23
        L_0x017a:
            r4 = r18
        L_0x017c:
            r23 = r5
            r5 = r22
            long r16 = r2.getLong(r5)     // Catch:{ JSONException -> 0x01c3 }
            long r24 = r11.getLong(r5)     // Catch:{ JSONException -> 0x01c3 }
            long r16 = r16 - r24
            r24 = 0
            int r22 = (r16 > r24 ? 1 : (r16 == r24 ? 0 : -1))
            if (r22 >= 0) goto L_0x0195
            r22 = r5
            r5 = r24
            goto L_0x0199
        L_0x0195:
            r22 = r5
            r5 = r16
        L_0x0199:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x027c }
            r7.<init>()     // Catch:{ JSONException -> 0x027c }
            r7.append(r4)     // Catch:{ JSONException -> 0x027c }
            r7.append(r5)     // Catch:{ JSONException -> 0x027c }
            java.lang.String r4 = "|"
            r7.append(r4)     // Catch:{ JSONException -> 0x027c }
            java.lang.String r4 = r7.toString()     // Catch:{ JSONException -> 0x027c }
            r11.remove(r0)     // Catch:{ JSONException -> 0x01bf }
            r11.put(r0, r1)     // Catch:{ JSONException -> 0x01bf }
            r11.put(r3, r4)     // Catch:{ JSONException -> 0x01bf }
            m749a((org.json.JSONObject) r11, (org.json.JSONObject) r2)     // Catch:{ JSONException -> 0x01bf }
            r0 = r20
            r1 = r21
            goto L_0x0296
        L_0x01bf:
            r19 = r21
            goto L_0x027c
        L_0x01c3:
            r22 = r5
            goto L_0x027c
        L_0x01c7:
            r2 = r32
            goto L_0x01ec
        L_0x01ca:
            r2 = r32
            goto L_0x01ea
        L_0x01cd:
            r2 = r32
            goto L_0x01e8
        L_0x01d0:
            r2 = r32
            goto L_0x01e6
        L_0x01d3:
            r2 = r32
            goto L_0x01e4
        L_0x01d6:
            r2 = r32
            goto L_0x01e2
        L_0x01d9:
            r2 = r32
            goto L_0x01e0
        L_0x01dc:
            r2 = r32
            r8 = r36
        L_0x01e0:
            r12 = r40
        L_0x01e2:
            r10 = r44
        L_0x01e4:
            r9 = r47
        L_0x01e6:
            r13 = r48
        L_0x01e8:
            r14 = r49
        L_0x01ea:
            r15 = r50
        L_0x01ec:
            r0 = r24
        L_0x01ee:
            r3 = r25
            goto L_0x027c
        L_0x01f2:
            r2 = r32
            r8 = r36
            r12 = r40
            r10 = r44
            r9 = r47
            r13 = r48
            r14 = r49
            r15 = r50
            r0 = r4
            goto L_0x01ee
        L_0x0204:
            r12 = r40
            r10 = r44
            r9 = r47
            r13 = r48
            r14 = r49
            r15 = r50
            r2 = r1
            goto L_0x0221
        L_0x0212:
            r12 = r40
            r10 = r44
            r9 = r47
            r13 = r48
            r14 = r49
            r15 = r50
            r2 = r1
            r22 = r3
        L_0x0221:
            r0 = r4
            r3 = r7
            r23 = r8
            r8 = r36
            goto L_0x027c
        L_0x0229:
            r12 = r40
            r10 = r44
            r13 = r48
            r14 = r49
            r15 = r50
            r2 = r1
            r22 = r3
            r0 = r4
            goto L_0x0248
        L_0x0238:
            r12 = r40
            r10 = r44
            r13 = r48
            r14 = r49
            r15 = r50
            r2 = r1
            r22 = r3
            r0 = r4
            r20 = r5
        L_0x0248:
            r3 = r7
            r23 = r8
            r21 = r9
            r8 = r36
            goto L_0x027a
        L_0x0250:
            r12 = r40
            r13 = r48
            r14 = r49
            r15 = r50
            r2 = r1
            r22 = r3
            r0 = r4
            r20 = r5
            goto L_0x026f
        L_0x025f:
            r12 = r40
            r13 = r48
            r14 = r49
            r15 = r50
            r2 = r1
            r22 = r3
            r0 = r4
            r20 = r5
            r18 = r6
        L_0x026f:
            r3 = r7
            r23 = r8
            r21 = r9
            r19 = r10
            r8 = r36
            r10 = r44
        L_0x027a:
            r9 = r47
        L_0x027c:
            int r1 = r21 + 1
            r4 = r0
            r9 = r1
            r1 = r2
            r7 = r3
            r6 = r18
            r10 = r19
            r5 = r20
            r3 = r22
            r8 = r23
            r0 = r31
            goto L_0x0021
        L_0x0290:
            r2 = r1
            r19 = r10
            r0 = r5
            r1 = r19
        L_0x0296:
            if (r1 >= r0) goto L_0x0299
            return
        L_0x0299:
            r1 = r31
            r1.put(r0, r2)     // Catch:{ JSONException -> 0x029e }
        L_0x029e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.EventAnalysis.m748a(org.json.JSONArray, org.json.JSONObject, long, java.lang.String, java.lang.String, java.lang.String, long, java.lang.String, org.json.JSONArray, org.json.JSONArray, java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String):void");
    }

    /* renamed from: a */
    private static void m749a(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONArray optJSONArray;
        if (jSONObject != null && jSONObject2 != null) {
            JSONArray jSONArray = new JSONArray();
            JSONArray optJSONArray2 = jSONObject.optJSONArray(Config.EVENT_HEAT_POINT);
            if (!(optJSONArray2 == null || optJSONArray2.length() == 0)) {
                for (int i = 0; i < optJSONArray2.length(); i++) {
                    try {
                        jSONArray.put(optJSONArray2.getJSONObject(i));
                    } catch (Exception unused) {
                    }
                }
            }
            if (!(jSONArray.length() >= 10 || (optJSONArray = jSONObject2.optJSONArray(Config.EVENT_HEAT_POINT)) == null || optJSONArray.length() == 0)) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    try {
                        jSONArray.put(optJSONArray.getJSONObject(i2));
                    } catch (Exception unused2) {
                    }
                }
            }
            if (jSONArray.length() != 0) {
                try {
                    jSONObject.put(Config.EVENT_HEAT_POINT, jSONArray);
                } catch (Exception unused3) {
                }
            }
        }
    }

    /* renamed from: a */
    private static boolean m751a(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray == null && jSONArray2 == null) {
            return true;
        }
        return (jSONArray == null || jSONArray2 == null || !jSONArray.toString().equals(jSONArray2.toString())) ? false : true;
    }
}
