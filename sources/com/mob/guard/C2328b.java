package com.mob.guard;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import com.mob.MobSDK;
import com.mob.commons.GuardMsg;
import com.mob.socketservice.MobConnect;
import com.mob.socketservice.ServiceMessageData;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.Hashon;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.mob.guard.b */
public class C2328b {

    /* renamed from: a */
    public static C2328b f2193a = new C2328b();

    /* renamed from: b */
    public static volatile long f2194b;

    /* renamed from: a */
    public void mo29144a() {
        try {
            f2194b = System.currentTimeMillis();
            mo29146b();
            NLog b = C2335e.m2675b();
            b.mo29768d("[Guard]{GuardImpl}startTaskTime is :" + f2194b, new Object[0]);
        } catch (Throwable th) {
            C2335e.m2675b().mo29769d(th);
        }
    }

    /* renamed from: b */
    public void mo29146b() {
        new Thread() {
            public void run() {
                try {
                    if (!MobSDK.isForb()) {
                        C2335e.m2674a();
                        if (C2336f.m2676a()) {
                            C2328b.this.m2659d();
                        }
                        new C2332c().start();
                    }
                } catch (Throwable th) {
                    C2335e.m2675b().mo29769d(th);
                }
            }
        }.start();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x004c A[SYNTHETIC, Splitter:B:10:0x004c] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0091  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m2655a(java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            r7 = this;
            java.lang.String r0 = "data"
            r1 = 1
            r2 = 0
            java.lang.Class<com.mob.guard.MobGuardUpService> r3 = com.mob.guard.MobGuardUpService.class
            java.lang.String r3 = r3.getName()     // Catch:{ SecurityException -> 0x0041 }
            android.content.Intent r4 = new android.content.Intent     // Catch:{ SecurityException -> 0x0041 }
            java.lang.String r5 = "com.mob.intent.MOB_GUARD_SERVICE"
            r4.<init>(r5)     // Catch:{ SecurityException -> 0x0041 }
            r4.setClassName(r8, r3)     // Catch:{ SecurityException -> 0x0041 }
            r4.putExtra(r0, r9)     // Catch:{ SecurityException -> 0x0041 }
            android.content.Context r3 = com.mob.MobSDK.getContext()     // Catch:{ SecurityException -> 0x0041 }
            com.mob.guard.b$2 r5 = new com.mob.guard.b$2     // Catch:{ SecurityException -> 0x0041 }
            r5.<init>()     // Catch:{ SecurityException -> 0x0041 }
            boolean r3 = r3.bindService(r4, r5, r1)     // Catch:{ SecurityException -> 0x0041 }
            com.mob.tools.log.NLog r4 = com.mob.guard.C2335e.m2675b()     // Catch:{ SecurityException -> 0x003f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SecurityException -> 0x003f }
            r5.<init>()     // Catch:{ SecurityException -> 0x003f }
            java.lang.String r6 = "[GuardImpl]{MobGuard}get tcp bind response is to start service data is :"
            r5.append(r6)     // Catch:{ SecurityException -> 0x003f }
            r5.append(r9)     // Catch:{ SecurityException -> 0x003f }
            java.lang.String r5 = r5.toString()     // Catch:{ SecurityException -> 0x003f }
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ SecurityException -> 0x003f }
            r4.mo29768d(r5, r6)     // Catch:{ SecurityException -> 0x003f }
            goto L_0x004a
        L_0x003f:
            r4 = move-exception
            goto L_0x0043
        L_0x0041:
            r4 = move-exception
            r3 = 0
        L_0x0043:
            com.mob.tools.log.NLog r5 = com.mob.guard.C2335e.m2675b()
            r5.mo29769d(r4)
        L_0x004a:
            if (r3 != 0) goto L_0x0091
            android.content.ComponentName r3 = new android.content.ComponentName     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r4 = "com.mob.MobTranUpActivity"
            r3.<init>(r8, r4)     // Catch:{ Throwable -> 0x0088 }
            android.content.Intent r8 = new android.content.Intent     // Catch:{ Throwable -> 0x0088 }
            r8.<init>()     // Catch:{ Throwable -> 0x0088 }
            r4 = 276824064(0x10800000, float:5.0487098E-29)
            r8.addFlags(r4)     // Catch:{ Throwable -> 0x0088 }
            r8.setComponent(r3)     // Catch:{ Throwable -> 0x0088 }
            r8.putExtra(r0, r9)     // Catch:{ Throwable -> 0x0088 }
            com.mob.tools.log.NLog r0 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x0088 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0088 }
            r3.<init>()     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r4 = "[GuardImpl]{MobGuard}get tcp bind response is to start pull app data is :"
            r3.append(r4)     // Catch:{ Throwable -> 0x0088 }
            r3.append(r9)     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r9 = r3.toString()     // Catch:{ Throwable -> 0x0088 }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x0088 }
            r0.mo29768d(r9, r2)     // Catch:{ Throwable -> 0x0088 }
            android.content.Context r9 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x0088 }
            r9.startActivity(r8)     // Catch:{ Throwable -> 0x0088 }
            m2656b(r1, r10)     // Catch:{ Throwable -> 0x0088 }
            goto L_0x009f
        L_0x0088:
            r8 = move-exception
            com.mob.tools.log.NLog r9 = com.mob.guard.C2335e.m2675b()
            r9.mo29769d(r8)
            goto L_0x009f
        L_0x0091:
            com.mob.tools.log.NLog r8 = com.mob.guard.C2335e.m2675b()
            java.lang.Object[] r9 = new java.lang.Object[r2]
            java.lang.String r0 = "[GuardImpl]{MobGuard}up process sucess(service) send receipt 1 :"
            r8.mo29768d(r0, r9)
            m2656b(r1, r10)
        L_0x009f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.guard.C2328b.m2655a(java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* renamed from: c */
    public static boolean m2658c() {
        try {
            ActivityManager activityManager = (ActivityManager) MobSDK.getContext().getApplicationContext().getSystemService("activity");
            String packageName = MobSDK.getContext().getApplicationContext().getPackageName();
            List<ActivityManager.RunningAppProcessInfo> list = null;
            if (Build.VERSION.SDK_INT >= 3) {
                list = activityManager.getRunningAppProcesses();
            }
            if (list == null) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo next : list) {
                if (Build.VERSION.SDK_INT >= 3 && next.processName.equals(packageName) && next.importance == 100) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            C2335e.m2675b().mo29769d(th);
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static void m2656b(int i, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", String.valueOf(i));
            jSONObject.put("messageId", str);
            MobConnect.guardAck(2001, jSONObject.toString());
        } catch (Throwable th) {
            C2335e.m2675b().mo29769d(th);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m2659d() {
        try {
            Class<?> cls = Class.forName("com.mob.socketservice.ServiceMessageData");
            Constructor<?> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            Method declaredMethod = cls.getDeclaredMethod("setGuardId", new Class[]{String.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(declaredConstructor.newInstance(new Object[0]), new Object[]{MobGuard.getGuardId()});
            HashMap hashMap = new HashMap();
            hashMap.put(9004, "content");
            Method declaredMethod2 = cls.getDeclaredMethod("setTypeMap", new Class[]{HashMap.class});
            declaredMethod2.setAccessible(true);
            declaredMethod2.invoke(declaredConstructor.newInstance(new Object[0]), new Object[]{hashMap});
            Class<?> cls2 = Class.forName("com.mob.socketservice.MobConnect");
            Class<?> cls3 = Class.forName("com.mob.socketservice.ConnectManager$ServiceConnectionListener");
            Object newProxyInstance = Proxy.newProxyInstance(C2328b.class.getClassLoader(), new Class[]{cls3}, new InvocationHandler() {
                public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    Message message;
                    HashMap hashMap;
                    if (String.class == method.getReturnType()) {
                        return "";
                    }
                    if (Integer.class == method.getReturnType()) {
                        return 0;
                    }
                    if (Integer.TYPE == method.getReturnType()) {
                        return 0;
                    }
                    if (Boolean.class == method.getReturnType()) {
                        return Boolean.FALSE;
                    }
                    if (Boolean.TYPE == method.getReturnType()) {
                        return false;
                    }
                    if (method != null && method.getName().equals("onServiceResponseMessage") && (message = objArr[0]) != null && message.what == 9004) {
                        try {
                            if (TextUtils.isEmpty(C2341i.m2701d())) {
                                C2335e.m2675b().mo29768d("[GuardImpl]{MobGuard}Do not execute without cache up app", new Object[0]);
                                return null;
                            }
                            Bundle data = message.getData();
                            if (data == null) {
                                return null;
                            }
                            String string = data.getString("content");
                            C2335e.m2675b().mo29768d("[GuardImpl]{MobGuard}get tcp bind response content is :" + string, new Object[0]);
                            if (string == null || (hashMap = (HashMap) new Hashon().fromJson(string).get(ShareConstants.WEB_DIALOG_PARAM_DATA)) == null) {
                                return null;
                            }
                            String str = null;
                            String str2 = null;
                            for (Map.Entry entry : hashMap.entrySet()) {
                                String str3 = (String) entry.getKey();
                                if (str3.equals("messageId")) {
                                    str2 = (String) entry.getValue();
                                    C2335e.m2675b().mo29768d("[Guard]{GuardImpl}get tcp bind response is up messageId is :" + str2, new Object[0]);
                                }
                                if (str3.equals("targetPackage")) {
                                    str = (String) entry.getValue();
                                    C2335e.m2675b().mo29768d("[Guard]{GuardImpl}get tcp bind response is up packagename is :" + str, new Object[0]);
                                }
                            }
                            if (!TextUtils.isEmpty(C2341i.m2701d()) && C2341i.m2701d().equals("0")) {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("code", "3");
                                jSONObject.put("messageId", str2);
                                C2335e.m2675b().mo29768d("[Guard]{GuardImpl}The device is not on the whitelist send receipt 3 :" + C2341i.m2703e(), new Object[0]);
                                MobConnect.guardAck(2001, jSONObject.toString());
                                return null;
                            } else if (C2328b.m2658c()) {
                                C2328b.this.m2655a(str, (String) hashMap.get(ShareConstants.WEB_DIALOG_PARAM_DATA), str2);
                            } else {
                                C2335e.m2675b().mo29768d("[Guard]{GuardImpl} current process in backstage send receipt 2 :" + C2328b.m2658c(), new Object[0]);
                                C2328b.m2656b(2, str2);
                            }
                        } catch (Throwable th) {
                            C2335e.m2675b().mo29769d(th);
                        }
                    }
                    return null;
                }
            });
            Method declaredMethod3 = cls2.getDeclaredMethod("bindService", new Class[]{Context.class, cls, cls3});
            declaredMethod3.setAccessible(true);
            declaredMethod3.invoke(cls2.newInstance(), new Object[]{MobSDK.getContext(), cls.newInstance(), newProxyInstance});
        } catch (Exception e) {
            C2335e.m2675b().mo29769d(e);
        }
    }

    /* renamed from: a */
    public void mo29145a(GuardMsg guardMsg) {
        if (guardMsg != null) {
            m2657b(guardMsg);
        }
    }

    /* renamed from: b */
    private void m2657b(GuardMsg guardMsg) {
        ServiceMessageData serviceMessageData = new ServiceMessageData();
        serviceMessageData.setGuardId(guardMsg.getId());
        boolean reconnect = MobConnect.reconnect(serviceMessageData);
        NLog b = C2335e.m2675b();
        b.mo29768d("[Guard]{GuardImpl} transfer MobConnect.reconnect() to connect TCP isreconSucess is, " + reconnect, new Object[0]);
        if (reconnect) {
            C2341i.m2702d(guardMsg.getId());
        }
    }
}
