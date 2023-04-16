package com.mob.commons.p023a;

import android.os.Message;
import com.facebook.share.internal.ShareConstants;
import com.mob.MobSDK;
import com.mob.commons.C2262b;
import com.mob.commons.C2293c;
import com.mob.commons.C2300e;
import com.mob.commons.C2308i;
import com.mob.tools.MobLog;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.SmltHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/* renamed from: com.mob.commons.a.h */
/* compiled from: DClt */
public class C2233h extends C2226d {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public File mo28993a() {
        return C2300e.m2466a("comm/locks/.lesd_lock");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b_ */
    public boolean mo28994b_() {
        return C2262b.m2239F() > 0 || C2262b.m2241H() > 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo28995d() {
        mo28999b(1);
        mo28999b(3);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28987a(Message message) {
        if (message.what == 1) {
            if (C2262b.m2239F() > 0) {
                DeviceHelper.getInstance(MobSDK.getContext()).getBatteryState(new ReflectHelper.ReflectRunnable<HashMap<String, Object>, Void>() {
                    /* renamed from: a */
                    public Void run(HashMap<String, Object> hashMap) {
                        Message message = new Message();
                        message.obj = hashMap;
                        message.what = 2;
                        C2233h.this.mo29000b(message);
                        return null;
                    }
                });
            }
        } else if (message.what == 2) {
            long a = m2035a((HashMap<String, Object>) (HashMap) message.obj);
            if (a == 0) {
                a = C2262b.m2239F() * 1000;
            }
            mo28997a(1, a);
        } else if (message.what == 3) {
            long H = C2262b.m2241H();
            if (H > 0) {
                m2038h();
                mo28997a(3, H * 1000);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v1, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v2, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v26, resolved type: java.util.HashMap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v3, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v4, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v5, resolved type: java.util.HashMap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v102, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v13, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v6, resolved type: java.util.HashMap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v104, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v9, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v7, resolved type: java.util.HashMap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v106, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v8, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v8, resolved type: java.util.HashMap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v108, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v6, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v9, resolved type: java.util.HashMap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v27, resolved type: java.util.HashMap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v10, resolved type: java.util.HashMap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v28, resolved type: java.util.HashMap} */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v25 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0357  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x035a  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x035f A[SYNTHETIC, Splitter:B:105:0x035f] */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x038b A[Catch:{ Throwable -> 0x0388, Throwable -> 0x0391 }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00cd A[SYNTHETIC, Splitter:B:57:0x00cd] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x033b A[Catch:{ Throwable -> 0x0393 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long m2035a(java.util.HashMap<java.lang.String, java.lang.Object> r29) {
        /*
            r28 = this;
            r1 = r28
            r2 = r29
            java.lang.String r3 = "technology"
            java.lang.String r4 = "present"
            java.lang.String r5 = "batteryState"
            java.lang.String r6 = "lightMode"
            java.lang.String r7 = "light"
            java.lang.String r8 = "time"
            java.lang.String r9 = "level"
            java.lang.String r10 = "plugged"
            java.lang.String r11 = "status"
            java.lang.String r12 = "voltage"
            java.lang.String r13 = "temperature"
            java.lang.String r14 = "nextUploadTime"
            java.util.HashMap r15 = r28.m2040j()     // Catch:{ Throwable -> 0x039b }
            r16 = 0
            java.lang.Object r0 = r15.get(r8)     // Catch:{ Throwable -> 0x00b1 }
            r17 = r0
            java.util.ArrayList r17 = (java.util.ArrayList) r17     // Catch:{ Throwable -> 0x00b1 }
            java.lang.Object r0 = r15.get(r7)     // Catch:{ Throwable -> 0x00ad }
            r18 = r0
            java.util.ArrayList r18 = (java.util.ArrayList) r18     // Catch:{ Throwable -> 0x00ad }
            java.lang.Object r0 = r15.get(r6)     // Catch:{ Throwable -> 0x00a9 }
            r19 = r0
            java.util.ArrayList r19 = (java.util.ArrayList) r19     // Catch:{ Throwable -> 0x00a9 }
            java.lang.Object r0 = r15.get(r5)     // Catch:{ Throwable -> 0x00a5 }
            r20 = r0
            java.util.HashMap r20 = (java.util.HashMap) r20     // Catch:{ Throwable -> 0x00a5 }
            if (r20 != 0) goto L_0x0058
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Throwable -> 0x004b }
            r0.<init>()     // Catch:{ Throwable -> 0x004b }
            r1 = r0
            goto L_0x005a
        L_0x004b:
            r0 = move-exception
            r21 = r16
            r22 = r21
            r23 = r22
            r24 = r20
            r20 = r23
            goto L_0x00c2
        L_0x0058:
            r1 = r20
        L_0x005a:
            java.lang.Object r0 = r1.get(r13)     // Catch:{ Throwable -> 0x0099 }
            r20 = r0
            java.util.ArrayList r20 = (java.util.ArrayList) r20     // Catch:{ Throwable -> 0x0099 }
            java.lang.Object r0 = r1.get(r12)     // Catch:{ Throwable -> 0x0093 }
            r21 = r0
            java.util.ArrayList r21 = (java.util.ArrayList) r21     // Catch:{ Throwable -> 0x0093 }
            java.lang.Object r0 = r1.get(r11)     // Catch:{ Throwable -> 0x008d }
            r22 = r0
            java.util.ArrayList r22 = (java.util.ArrayList) r22     // Catch:{ Throwable -> 0x008d }
            java.lang.Object r0 = r1.get(r10)     // Catch:{ Throwable -> 0x0087 }
            r23 = r0
            java.util.ArrayList r23 = (java.util.ArrayList) r23     // Catch:{ Throwable -> 0x0087 }
            java.lang.Object r0 = r1.get(r9)     // Catch:{ Throwable -> 0x0083 }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ Throwable -> 0x0083 }
            r16 = r0
            goto L_0x00cb
        L_0x0083:
            r0 = move-exception
            r24 = r1
            goto L_0x00c2
        L_0x0087:
            r0 = move-exception
            r24 = r1
            r23 = r16
            goto L_0x00c2
        L_0x008d:
            r0 = move-exception
            r24 = r1
            r22 = r16
            goto L_0x00a2
        L_0x0093:
            r0 = move-exception
            r24 = r1
            r21 = r16
            goto L_0x00a0
        L_0x0099:
            r0 = move-exception
            r24 = r1
            r20 = r16
            r21 = r20
        L_0x00a0:
            r22 = r21
        L_0x00a2:
            r23 = r22
            goto L_0x00c2
        L_0x00a5:
            r0 = move-exception
            r20 = r16
            goto L_0x00ba
        L_0x00a9:
            r0 = move-exception
            r19 = r16
            goto L_0x00b8
        L_0x00ad:
            r0 = move-exception
            r18 = r16
            goto L_0x00b6
        L_0x00b1:
            r0 = move-exception
            r17 = r16
            r18 = r17
        L_0x00b6:
            r19 = r18
        L_0x00b8:
            r20 = r19
        L_0x00ba:
            r21 = r20
            r22 = r21
            r23 = r22
            r24 = r23
        L_0x00c2:
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x0395 }
            r1.mo29769d(r0)     // Catch:{ Throwable -> 0x0395 }
            r1 = r24
        L_0x00cb:
            if (r17 != 0) goto L_0x00da
            java.util.ArrayList r17 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00d3 }
            r17.<init>()     // Catch:{ Throwable -> 0x00d3 }
            goto L_0x00da
        L_0x00d3:
            r0 = move-exception
            r3 = 0
        L_0x00d6:
            r1 = r28
            goto L_0x039f
        L_0x00da:
            int r0 = r17.size()     // Catch:{ Throwable -> 0x0395 }
            if (r18 == 0) goto L_0x0134
            if (r19 == 0) goto L_0x0134
            if (r20 == 0) goto L_0x0134
            if (r21 == 0) goto L_0x0134
            if (r22 == 0) goto L_0x0134
            if (r23 == 0) goto L_0x0134
            if (r16 == 0) goto L_0x0134
            r24 = r1
            int r1 = r18.size()     // Catch:{ Throwable -> 0x00d3 }
            if (r1 != r0) goto L_0x0134
            int r1 = r19.size()     // Catch:{ Throwable -> 0x00d3 }
            if (r1 != r0) goto L_0x0134
            int r1 = r20.size()     // Catch:{ Throwable -> 0x00d3 }
            if (r1 != r0) goto L_0x0134
            int r1 = r21.size()     // Catch:{ Throwable -> 0x00d3 }
            if (r1 != r0) goto L_0x0134
            int r1 = r22.size()     // Catch:{ Throwable -> 0x00d3 }
            if (r1 != r0) goto L_0x0134
            int r1 = r23.size()     // Catch:{ Throwable -> 0x00d3 }
            if (r1 != r0) goto L_0x0134
            int r1 = r16.size()     // Catch:{ Throwable -> 0x00d3 }
            if (r1 == r0) goto L_0x0119
            goto L_0x0134
        L_0x0119:
            r26 = r16
            r27 = r17
            r0 = r18
            r1 = r19
            r25 = r23
            r18 = r5
            r17 = r8
            r19 = r9
            r16 = r14
            r8 = r20
            r5 = r21
            r9 = r22
            r14 = r24
            goto L_0x017a
        L_0x0134:
            java.util.ArrayList r17 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0395 }
            r17.<init>()     // Catch:{ Throwable -> 0x0395 }
            java.util.ArrayList r18 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0395 }
            r18.<init>()     // Catch:{ Throwable -> 0x0395 }
            java.util.ArrayList r19 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0395 }
            r19.<init>()     // Catch:{ Throwable -> 0x0395 }
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ Throwable -> 0x0395 }
            r1.<init>()     // Catch:{ Throwable -> 0x0395 }
            java.util.ArrayList r20 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0395 }
            r20.<init>()     // Catch:{ Throwable -> 0x0395 }
            java.util.ArrayList r21 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0395 }
            r21.<init>()     // Catch:{ Throwable -> 0x0395 }
            java.util.ArrayList r22 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0395 }
            r22.<init>()     // Catch:{ Throwable -> 0x0395 }
            java.util.ArrayList r23 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0395 }
            r23.<init>()     // Catch:{ Throwable -> 0x0395 }
            java.util.ArrayList r16 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0395 }
            r16.<init>()     // Catch:{ Throwable -> 0x0395 }
            r26 = r16
            r27 = r17
            r0 = r18
            r25 = r23
            r18 = r5
            r17 = r8
            r16 = r14
            r8 = r20
            r5 = r21
            r14 = r1
            r1 = r19
            r19 = r9
            r9 = r22
        L_0x017a:
            android.content.Context r20 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x0395 }
            com.mob.tools.utils.DeviceHelper r20 = com.mob.tools.utils.DeviceHelper.getInstance(r20)     // Catch:{ Throwable -> 0x0395 }
            int r21 = r20.getScreenBrightness()     // Catch:{ Throwable -> 0x0395 }
            r22 = r10
            java.lang.Integer r10 = java.lang.Integer.valueOf(r21)     // Catch:{ Throwable -> 0x0395 }
            r0.add(r10)     // Catch:{ Throwable -> 0x0395 }
            int r10 = r20.getScreenBrightnessMode()     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ Throwable -> 0x0395 }
            r1.add(r10)     // Catch:{ Throwable -> 0x0395 }
            r15.put(r7, r0)     // Catch:{ Throwable -> 0x0395 }
            r15.put(r6, r1)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Object r0 = r2.get(r4)     // Catch:{ Throwable -> 0x0395 }
            r1 = 0
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0, r1)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ Throwable -> 0x0395 }
            boolean r0 = r0.booleanValue()     // Catch:{ Throwable -> 0x0395 }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ Throwable -> 0x0395 }
            r14.put(r4, r0)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Object r0 = r2.get(r3)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0)     // Catch:{ Throwable -> 0x0395 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x0395 }
            r14.put(r3, r0)     // Catch:{ Throwable -> 0x0395 }
            java.lang.String r0 = "charge_type"
            java.lang.Object r0 = r2.get(r0)     // Catch:{ Throwable -> 0x0395 }
            r1 = -1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0, r3)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Throwable -> 0x0395 }
            int r0 = r0.intValue()     // Catch:{ Throwable -> 0x0395 }
            java.lang.String r3 = "charge_type"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0395 }
            r14.put(r3, r0)     // Catch:{ Throwable -> 0x0395 }
            java.lang.String r0 = "scale"
            java.lang.Object r0 = r2.get(r0)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0, r3)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Throwable -> 0x0395 }
            int r0 = r0.intValue()     // Catch:{ Throwable -> 0x0395 }
            java.lang.String r3 = "scale"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0395 }
            r14.put(r3, r0)     // Catch:{ Throwable -> 0x0395 }
            java.lang.String r0 = "current_now"
            java.lang.Object r0 = r2.get(r0)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0, r3)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Throwable -> 0x0395 }
            int r0 = r0.intValue()     // Catch:{ Throwable -> 0x0395 }
            java.lang.String r3 = "current_now"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0395 }
            r14.put(r3, r0)     // Catch:{ Throwable -> 0x0395 }
            java.lang.String r0 = "current_avg"
            java.lang.Object r0 = r2.get(r0)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0, r3)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Throwable -> 0x0395 }
            int r0 = r0.intValue()     // Catch:{ Throwable -> 0x0395 }
            java.lang.String r3 = "current_avg"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0395 }
            r14.put(r3, r0)     // Catch:{ Throwable -> 0x0395 }
            java.lang.String r0 = "health"
            java.lang.Object r0 = r2.get(r0)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0, r3)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Throwable -> 0x0395 }
            int r0 = r0.intValue()     // Catch:{ Throwable -> 0x0395 }
            java.lang.String r3 = "health"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0395 }
            r14.put(r3, r0)     // Catch:{ Throwable -> 0x0395 }
            java.lang.String r0 = "capacity"
            java.lang.Object r0 = r2.get(r0)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0, r3)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Throwable -> 0x0395 }
            int r0 = r0.intValue()     // Catch:{ Throwable -> 0x0395 }
            java.lang.String r3 = "capacity"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0395 }
            r14.put(r3, r0)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Object r0 = r2.get(r13)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0, r3)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Throwable -> 0x0395 }
            int r0 = r0.intValue()     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0395 }
            r8.add(r0)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Object r0 = r2.get(r12)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0, r3)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Throwable -> 0x0395 }
            int r0 = r0.intValue()     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0395 }
            r5.add(r0)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Object r0 = r2.get(r11)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0, r3)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Throwable -> 0x0395 }
            int r0 = r0.intValue()     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0395 }
            r9.add(r0)     // Catch:{ Throwable -> 0x0395 }
            r3 = r22
            java.lang.Object r0 = r2.get(r3)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r1)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0, r4)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Throwable -> 0x0395 }
            int r0 = r0.intValue()     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0395 }
            r4 = r25
            r4.add(r0)     // Catch:{ Throwable -> 0x0395 }
            r6 = r19
            java.lang.Object r0 = r2.get(r6)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Object r0 = com.mob.tools.utils.ResHelper.forceCast(r0, r1)     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Throwable -> 0x0395 }
            int r0 = r0.intValue()     // Catch:{ Throwable -> 0x0395 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0395 }
            r1 = r26
            r1.add(r0)     // Catch:{ Throwable -> 0x0395 }
            r14.put(r13, r8)     // Catch:{ Throwable -> 0x0395 }
            r14.put(r12, r5)     // Catch:{ Throwable -> 0x0395 }
            r14.put(r11, r9)     // Catch:{ Throwable -> 0x0395 }
            r14.put(r3, r4)     // Catch:{ Throwable -> 0x0395 }
            r14.put(r6, r1)     // Catch:{ Throwable -> 0x0395 }
            r1 = r18
            r15.put(r1, r14)     // Catch:{ Throwable -> 0x0395 }
            long r0 = com.mob.commons.C2262b.m2260a()     // Catch:{ Throwable -> 0x0395 }
            java.lang.Long r2 = java.lang.Long.valueOf(r0)     // Catch:{ Throwable -> 0x0395 }
            r3 = r27
            r3.add(r2)     // Catch:{ Throwable -> 0x0395 }
            r2 = r17
            r15.put(r2, r3)     // Catch:{ Throwable -> 0x0395 }
            r2 = r16
            java.lang.Object r4 = r15.get(r2)     // Catch:{ Throwable -> 0x0395 }
            r5 = 0
            java.lang.Long r7 = java.lang.Long.valueOf(r5)     // Catch:{ Throwable -> 0x0393 }
            java.lang.Object r4 = com.mob.tools.utils.ResHelper.forceCast(r4, r7)     // Catch:{ Throwable -> 0x0393 }
            java.lang.Long r4 = (java.lang.Long) r4     // Catch:{ Throwable -> 0x0393 }
            long r7 = r4.longValue()     // Catch:{ Throwable -> 0x0393 }
            int r4 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r4 != 0) goto L_0x034b
            long r7 = com.mob.commons.C2262b.m2240G()     // Catch:{ Throwable -> 0x0393 }
            r9 = 1000(0x3e8, double:4.94E-321)
            long r7 = r7 * r9
            long r7 = r7 + r0
            java.lang.Long r4 = java.lang.Long.valueOf(r7)     // Catch:{ Throwable -> 0x0393 }
            r15.put(r2, r4)     // Catch:{ Throwable -> 0x0393 }
        L_0x034b:
            int r3 = r3.size()     // Catch:{ Throwable -> 0x0393 }
            r4 = 200(0xc8, float:2.8E-43)
            if (r3 < r4) goto L_0x035a
            int r3 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r3 >= 0) goto L_0x035a
            long r3 = r7 - r0
            goto L_0x035b
        L_0x035a:
            r3 = r5
        L_0x035b:
            int r5 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r5 < 0) goto L_0x038b
            r15.remove(r2)     // Catch:{ Throwable -> 0x0388 }
            java.lang.String r0 = "cacheSize"
            r15.remove(r0)     // Catch:{ Throwable -> 0x0388 }
            java.lang.String r0 = "LIGHT_ELECTRIC_INFO"
            r1 = r28
            r1.m2036a(r15, r0)     // Catch:{ Throwable -> 0x0391 }
            java.util.HashMap r15 = new java.util.HashMap     // Catch:{ Throwable -> 0x0391 }
            r15.<init>()     // Catch:{ Throwable -> 0x0391 }
            long r5 = com.mob.commons.C2262b.m2260a()     // Catch:{ Throwable -> 0x0391 }
            long r7 = com.mob.commons.C2262b.m2240G()     // Catch:{ Throwable -> 0x0391 }
            r9 = 1000(0x3e8, double:4.94E-321)
            long r7 = r7 * r9
            long r5 = r5 + r7
            java.lang.Long r0 = java.lang.Long.valueOf(r5)     // Catch:{ Throwable -> 0x0391 }
            r15.put(r2, r0)     // Catch:{ Throwable -> 0x0391 }
            goto L_0x038d
        L_0x0388:
            r0 = move-exception
            goto L_0x00d6
        L_0x038b:
            r1 = r28
        L_0x038d:
            r1.m2037b(r15)     // Catch:{ Throwable -> 0x0391 }
            goto L_0x03a6
        L_0x0391:
            r0 = move-exception
            goto L_0x039f
        L_0x0393:
            r0 = move-exception
            goto L_0x0398
        L_0x0395:
            r0 = move-exception
            r5 = 0
        L_0x0398:
            r1 = r28
            goto L_0x039e
        L_0x039b:
            r0 = move-exception
            r5 = 0
        L_0x039e:
            r3 = r5
        L_0x039f:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29769d(r0)
        L_0x03a6:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p023a.C2233h.m2035a(java.util.HashMap):long");
    }

    /* renamed from: h */
    private void m2038h() {
        Object[] i = m2039i();
        StringBuilder sb = new StringBuilder();
        if (i != null && i.length == 2) {
            sb.append(i[0]);
        }
        SmltHelper smltHelper = new SmltHelper();
        int checkBaseband = smltHelper.checkBaseband(MobSDK.getContext());
        sb.append(checkBaseband);
        int checkBoard = smltHelper.checkBoard(MobSDK.getContext());
        sb.append(checkBoard);
        int checkPlatform = smltHelper.checkPlatform(MobSDK.getContext());
        sb.append(checkPlatform);
        int checkFlavor = smltHelper.checkFlavor(MobSDK.getContext());
        sb.append(checkFlavor);
        int checkCgroup = smltHelper.checkCgroup();
        sb.append(checkCgroup);
        int checkBluetooth = smltHelper.checkBluetooth(MobSDK.getContext());
        sb.append(checkBluetooth);
        int checkImei = smltHelper.checkImei(MobSDK.getContext());
        sb.append(checkImei);
        int checkCommonApp = smltHelper.checkCommonApp(MobSDK.getContext());
        sb.append(checkCommonApp);
        int checkCpuInfo = smltHelper.checkCpuInfo();
        sb.append(checkCpuInfo);
        int checkSensor = smltHelper.checkSensor(MobSDK.getContext());
        sb.append(checkSensor);
        String MD5 = Data.MD5(sb.toString());
        String m = C2308i.m2555m();
        if (MD5 == null || !MD5.equals(m)) {
            C2308i.m2543h(MD5);
            HashMap hashMap = new HashMap();
            if (i != null && i.length == 2 && (i[1] instanceof HashMap)) {
                hashMap.putAll((HashMap) i[1]);
            }
            hashMap.put("ckBaseband", Integer.valueOf(checkBaseband));
            hashMap.put("ckBoard", Integer.valueOf(checkBoard));
            hashMap.put("ckPlatform", Integer.valueOf(checkPlatform));
            hashMap.put("ckFlavor", Integer.valueOf(checkFlavor));
            hashMap.put("ckCgroup", Integer.valueOf(checkCgroup));
            hashMap.put("ckBluetooth", Integer.valueOf(checkBluetooth));
            hashMap.put("ckImei", Integer.valueOf(checkImei));
            hashMap.put("ckCommonapp", Integer.valueOf(checkCommonApp));
            hashMap.put("ckCpuinfo", Integer.valueOf(checkCpuInfo));
            hashMap.put("ckSensor", Integer.valueOf(checkSensor));
            m2036a(hashMap, "SIMULATOR_DET_INFO");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:105:0x0134 A[Catch:{ Throwable -> 0x0120 }] */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0142 A[Catch:{ Throwable -> 0x0120 }] */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0150 A[Catch:{ Throwable -> 0x0120 }] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0167  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x018e  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007e A[SYNTHETIC, Splitter:B:26:0x007e] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a9 A[SYNTHETIC, Splitter:B:41:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00b5 A[Catch:{ Throwable -> 0x008e }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00c3 A[Catch:{ Throwable -> 0x008e }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00d1 A[Catch:{ Throwable -> 0x008e }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00df A[Catch:{ Throwable -> 0x008e }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00ed A[Catch:{ Throwable -> 0x008e }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00fb A[Catch:{ Throwable -> 0x008e }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0128 A[Catch:{ Throwable -> 0x0120 }] */
    /* renamed from: i */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object[] m2039i() {
        /*
            r14 = this;
            java.lang.String r0 = "com.bluestacks.appmart"
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            android.content.Context r3 = com.mob.MobSDK.getContext()
            com.mob.tools.utils.DeviceHelper r3 = com.mob.tools.utils.DeviceHelper.getInstance(r3)
            java.lang.String r4 = r3.getQemuKernel()
            r2.append(r4)
            r5 = 1
            r6 = 0
            java.io.File r7 = new java.io.File     // Catch:{ Throwable -> 0x0044 }
            java.lang.String r8 = "/system/lib/libc_malloc_debug_qemu.so"
            r7.<init>(r8)     // Catch:{ Throwable -> 0x0044 }
            java.io.File r8 = new java.io.File     // Catch:{ Throwable -> 0x0044 }
            java.lang.String r9 = "/sys/qemu_trace"
            r8.<init>(r9)     // Catch:{ Throwable -> 0x0044 }
            java.io.File r9 = new java.io.File     // Catch:{ Throwable -> 0x0044 }
            java.lang.String r10 = "/system/bin/qemu-props"
            r9.<init>(r10)     // Catch:{ Throwable -> 0x0044 }
            boolean r7 = r7.exists()     // Catch:{ Throwable -> 0x0044 }
            if (r7 != 0) goto L_0x0042
            boolean r7 = r8.exists()     // Catch:{ Throwable -> 0x0044 }
            if (r7 != 0) goto L_0x0042
            boolean r7 = r9.exists()     // Catch:{ Throwable -> 0x0044 }
            if (r7 == 0) goto L_0x004c
        L_0x0042:
            r7 = 1
            goto L_0x004d
        L_0x0044:
            r7 = move-exception
            com.mob.tools.log.NLog r8 = com.mob.tools.MobLog.getInstance()
            r8.mo29769d(r7)
        L_0x004c:
            r7 = 0
        L_0x004d:
            r2.append(r7)
            java.io.File r8 = new java.io.File     // Catch:{ Throwable -> 0x006c }
            java.lang.String r9 = "/dev/qemu_pipe"
            r8.<init>(r9)     // Catch:{ Throwable -> 0x006c }
            java.io.File r9 = new java.io.File     // Catch:{ Throwable -> 0x006c }
            java.lang.String r10 = "/dev/socket/qemud"
            r9.<init>(r10)     // Catch:{ Throwable -> 0x006c }
            boolean r8 = r8.exists()     // Catch:{ Throwable -> 0x006c }
            if (r8 != 0) goto L_0x006a
            boolean r8 = r9.exists()     // Catch:{ Throwable -> 0x006c }
            if (r8 == 0) goto L_0x0074
        L_0x006a:
            r8 = 1
            goto L_0x0075
        L_0x006c:
            r8 = move-exception
            com.mob.tools.log.NLog r9 = com.mob.tools.MobLog.getInstance()
            r9.mo29769d(r8)
        L_0x0074:
            r8 = 0
        L_0x0075:
            r2.append(r8)
            boolean r9 = r3.isPackageInstalled(r0)     // Catch:{ Throwable -> 0x016b }
            if (r9 != 0) goto L_0x0091
            java.io.File r10 = new java.io.File     // Catch:{ Throwable -> 0x008e }
            java.lang.String r11 = "/data/bluestacks.prop"
            r10.<init>(r11)     // Catch:{ Throwable -> 0x008e }
            boolean r9 = r10.exists()     // Catch:{ Throwable -> 0x008e }
            if (r9 == 0) goto L_0x008c
            goto L_0x0091
        L_0x008c:
            r9 = 0
            goto L_0x0092
        L_0x008e:
            r0 = move-exception
            goto L_0x016d
        L_0x0091:
            r9 = 1
        L_0x0092:
            if (r9 != 0) goto L_0x00a4
            java.io.File r10 = new java.io.File     // Catch:{ Throwable -> 0x008e }
            java.lang.String r11 = "/mnt/prebundledapps/bluestacks.prop.orig"
            r10.<init>(r11)     // Catch:{ Throwable -> 0x008e }
            boolean r9 = r10.exists()     // Catch:{ Throwable -> 0x008e }
            if (r9 == 0) goto L_0x00a2
            goto L_0x00a4
        L_0x00a2:
            r9 = 0
            goto L_0x00a5
        L_0x00a4:
            r9 = 1
        L_0x00a5:
            java.lang.String r10 = "com.bluestacks.BstCommandProcessor"
            if (r9 != 0) goto L_0x00b2
            boolean r9 = r3.isPackageInstalled(r10)     // Catch:{ Throwable -> 0x008e }
            if (r9 == 0) goto L_0x00b0
            goto L_0x00b2
        L_0x00b0:
            r9 = 0
            goto L_0x00b3
        L_0x00b2:
            r9 = 1
        L_0x00b3:
            if (r9 != 0) goto L_0x00c0
            java.lang.String r11 = "com.bluestacks.help"
            boolean r9 = r3.isPackageInstalled(r11)     // Catch:{ Throwable -> 0x008e }
            if (r9 == 0) goto L_0x00be
            goto L_0x00c0
        L_0x00be:
            r9 = 0
            goto L_0x00c1
        L_0x00c0:
            r9 = 1
        L_0x00c1:
            if (r9 != 0) goto L_0x00ce
            java.lang.String r11 = "com.bluestacks.home"
            boolean r9 = r3.isPackageInstalled(r11)     // Catch:{ Throwable -> 0x008e }
            if (r9 == 0) goto L_0x00cc
            goto L_0x00ce
        L_0x00cc:
            r9 = 0
            goto L_0x00cf
        L_0x00ce:
            r9 = 1
        L_0x00cf:
            if (r9 != 0) goto L_0x00dc
            java.lang.String r11 = "com.bluestacks.s2p"
            boolean r9 = r3.isPackageInstalled(r11)     // Catch:{ Throwable -> 0x008e }
            if (r9 == 0) goto L_0x00da
            goto L_0x00dc
        L_0x00da:
            r9 = 0
            goto L_0x00dd
        L_0x00dc:
            r9 = 1
        L_0x00dd:
            if (r9 != 0) goto L_0x00ea
            java.lang.String r11 = "com.bluestacks.searchapp"
            boolean r9 = r3.isPackageInstalled(r11)     // Catch:{ Throwable -> 0x008e }
            if (r9 == 0) goto L_0x00e8
            goto L_0x00ea
        L_0x00e8:
            r9 = 0
            goto L_0x00eb
        L_0x00ea:
            r9 = 1
        L_0x00eb:
            if (r9 != 0) goto L_0x00f8
            java.lang.String r11 = "com.bluestacks.accelerometerui"
            boolean r9 = r3.isPackageInstalled(r11)     // Catch:{ Throwable -> 0x008e }
            if (r9 == 0) goto L_0x00f6
            goto L_0x00f8
        L_0x00f6:
            r9 = 0
            goto L_0x00f9
        L_0x00f8:
            r9 = 1
        L_0x00f9:
            if (r9 != 0) goto L_0x0106
            java.lang.String r11 = "com.bluestacks.appfinder"
            boolean r9 = r3.isPackageInstalled(r11)     // Catch:{ Throwable -> 0x008e }
            if (r9 == 0) goto L_0x0104
            goto L_0x0106
        L_0x0104:
            r9 = 0
            goto L_0x0107
        L_0x0106:
            r9 = 1
        L_0x0107:
            if (r9 != 0) goto L_0x0112
            boolean r0 = r3.isPackageInstalled(r0)     // Catch:{ Throwable -> 0x008e }
            if (r0 == 0) goto L_0x0110
            goto L_0x0112
        L_0x0110:
            r0 = 0
            goto L_0x0113
        L_0x0112:
            r0 = 1
        L_0x0113:
            if (r0 != 0) goto L_0x0125
            java.lang.String r9 = "com.bluestacks.appsettings"
            boolean r0 = r3.isPackageInstalled(r9)     // Catch:{ Throwable -> 0x0120 }
            if (r0 == 0) goto L_0x011e
            goto L_0x0125
        L_0x011e:
            r0 = 0
            goto L_0x0126
        L_0x0120:
            r9 = move-exception
            r13 = r9
            r9 = r0
            r0 = r13
            goto L_0x016d
        L_0x0125:
            r0 = 1
        L_0x0126:
            if (r0 != 0) goto L_0x0131
            boolean r0 = r3.isPackageInstalled(r10)     // Catch:{ Throwable -> 0x0120 }
            if (r0 == 0) goto L_0x012f
            goto L_0x0131
        L_0x012f:
            r0 = 0
            goto L_0x0132
        L_0x0131:
            r0 = 1
        L_0x0132:
            if (r0 != 0) goto L_0x013f
            java.lang.String r9 = "com.bluestacks.bstfolder"
            boolean r0 = r3.isPackageInstalled(r9)     // Catch:{ Throwable -> 0x0120 }
            if (r0 == 0) goto L_0x013d
            goto L_0x013f
        L_0x013d:
            r0 = 0
            goto L_0x0140
        L_0x013f:
            r0 = 1
        L_0x0140:
            if (r0 != 0) goto L_0x014d
            java.lang.String r9 = "com.bluestacks.setup"
            boolean r0 = r3.isPackageInstalled(r9)     // Catch:{ Throwable -> 0x0120 }
            if (r0 == 0) goto L_0x014b
            goto L_0x014d
        L_0x014b:
            r0 = 0
            goto L_0x014e
        L_0x014d:
            r0 = 1
        L_0x014e:
            if (r0 != 0) goto L_0x015b
            java.lang.String r9 = "com.bluestacks.spotlight"
            boolean r0 = r3.isPackageInstalled(r9)     // Catch:{ Throwable -> 0x0120 }
            if (r0 == 0) goto L_0x0159
            goto L_0x015b
        L_0x0159:
            r0 = 0
            goto L_0x015c
        L_0x015b:
            r0 = 1
        L_0x015c:
            if (r0 != 0) goto L_0x0169
            java.lang.String r9 = "com.androVM.vmconfig"
            boolean r0 = r3.isPackageInstalled(r9)     // Catch:{ Throwable -> 0x0120 }
            if (r0 == 0) goto L_0x0167
            goto L_0x0169
        L_0x0167:
            r0 = 0
            goto L_0x0175
        L_0x0169:
            r0 = 1
            goto L_0x0175
        L_0x016b:
            r0 = move-exception
            r9 = 0
        L_0x016d:
            com.mob.tools.log.NLog r10 = com.mob.tools.MobLog.getInstance()
            r10.mo29769d(r0)
            r0 = r9
        L_0x0175:
            r2.append(r0)
            java.util.ArrayList r9 = r3.getTTYDriversInfo()
            if (r9 == 0) goto L_0x01b0
            boolean r10 = r9.isEmpty()
            if (r10 != 0) goto L_0x01b0
            java.util.Iterator r10 = r9.iterator()
        L_0x0188:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x01b0
            java.lang.Object r11 = r10.next()
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            if (r11 == 0) goto L_0x0188
            boolean r12 = r11.isEmpty()
            if (r12 != 0) goto L_0x0188
            java.util.Iterator r11 = r11.iterator()
        L_0x01a0:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x0188
            java.lang.Object r12 = r11.next()
            java.lang.String r12 = (java.lang.String) r12
            r2.append(r12)
            goto L_0x01a0
        L_0x01b0:
            java.util.HashMap r3 = r3.getCPUInfo()
            com.mob.tools.utils.Hashon r10 = new com.mob.tools.utils.Hashon
            r10.<init>()
            java.lang.String r10 = r10.fromHashMap(r3)
            r2.append(r10)
            java.util.HashMap r10 = new java.util.HashMap
            r10.<init>()
            java.lang.String r11 = "qemuKernel"
            r10.put(r11, r4)
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r7)
            java.lang.String r7 = "qemuFileExist"
            r10.put(r7, r4)
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r8)
            java.lang.String r7 = "qemuDevExist"
            r10.put(r7, r4)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            java.lang.String r4 = "blueStacksFileExist"
            r10.put(r4, r0)
            java.lang.String r0 = "ttyDrivers"
            r10.put(r0, r9)
            java.lang.String r0 = "cpuInfo"
            r10.put(r0, r3)
            java.lang.String r0 = r2.toString()
            r1[r6] = r0
            r1[r5] = r10
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p023a.C2233h.m2039i():java.lang.Object[]");
    }

    /* renamed from: a */
    private void m2036a(HashMap<String, Object> hashMap, String str) {
        HashMap hashMap2 = new HashMap();
        hashMap2.put("type", str);
        hashMap2.put(ShareConstants.WEB_DIALOG_PARAM_DATA, hashMap);
        long a = C2262b.m2260a();
        hashMap2.put("datetime", Long.valueOf(a));
        C2293c.m2435a().mo29068a(a, (HashMap<String, Object>) hashMap2);
    }

    /* renamed from: b */
    private void m2037b(HashMap<String, Object> hashMap) {
        File dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.lecd");
        if (dataCacheFile == null || (hashMap != null && !hashMap.isEmpty())) {
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataCacheFile));
                objectOutputStream.writeObject(hashMap);
                objectOutputStream.close();
            } catch (Throwable th) {
                MobLog.getInstance().mo29769d(th);
            }
        } else {
            dataCacheFile.delete();
        }
    }

    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: type inference failed for: r1v2, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.util.HashMap<java.lang.String, java.lang.Object>] */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* renamed from: j */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.HashMap<java.lang.String, java.lang.Object> m2040j() {
        /*
            r5 = this;
            android.content.Context r0 = com.mob.MobSDK.getContext()
            java.lang.String r1 = "comm/dbs/.lecd"
            java.io.File r0 = com.mob.tools.utils.ResHelper.getDataCacheFile(r0, r1)
            if (r0 == 0) goto L_0x0018
            boolean r1 = r0.exists()
            if (r1 != 0) goto L_0x0018
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            return r0
        L_0x0018:
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0032, all -> 0x0030 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x0032, all -> 0x0030 }
            java.io.ObjectInputStream r0 = new java.io.ObjectInputStream     // Catch:{ Throwable -> 0x0032, all -> 0x0030 }
            r0.<init>(r2)     // Catch:{ Throwable -> 0x0032, all -> 0x0030 }
            java.lang.Object r2 = r0.readObject()     // Catch:{ Throwable -> 0x002e }
            java.util.HashMap r2 = (java.util.HashMap) r2     // Catch:{ Throwable -> 0x002e }
            r5.mo28998a((java.io.Closeable) r0)
            r1 = r2
            goto L_0x003e
        L_0x002e:
            r2 = move-exception
            goto L_0x0034
        L_0x0030:
            r0 = move-exception
            goto L_0x004a
        L_0x0032:
            r2 = move-exception
            r0 = r1
        L_0x0034:
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0046 }
            r3.mo29769d(r2)     // Catch:{ all -> 0x0046 }
            r5.mo28998a((java.io.Closeable) r0)
        L_0x003e:
            if (r1 != 0) goto L_0x0045
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
        L_0x0045:
            return r1
        L_0x0046:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x004a:
            r5.mo28998a((java.io.Closeable) r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p023a.C2233h.m2040j():java.util.HashMap");
    }
}
