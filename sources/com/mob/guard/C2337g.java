package com.mob.guard;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.commons.GuardMsg;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.Hashon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* renamed from: com.mob.guard.g */
public class C2337g {

    /* renamed from: a */
    private static C2337g f2215a = new C2337g();

    /* renamed from: b */
    private int f2216b;

    /* renamed from: c */
    private int f2217c;

    /* renamed from: d */
    private String f2218d;

    /* renamed from: e */
    private String f2219e;

    /* renamed from: f */
    private List<GuardMsg> f2220f = new ArrayList();

    /* renamed from: g */
    private Handler f2221g;

    public C2337g() {
        try {
            this.f2221g = MobHandlerThread.newHandler(new Handler.Callback() {
                public boolean handleMessage(Message message) {
                    try {
                        C2337g.this.m2681a(message);
                        return false;
                    } catch (Throwable unused) {
                        return false;
                    }
                }
            });
        } catch (Throwable th) {
            C2335e.m2675b().mo29769d(th);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2681a(Message message) {
        int i = message.what;
        if (i == 0) {
            GuardMsg guardMsg = (GuardMsg) message.obj;
            if (guardMsg != null) {
                NLog b = C2335e.m2675b();
                b.mo29768d("[Guard]{MobGuardCommonIdBCReceiver} onReceive is accept mNewGuardMsg is :" + guardMsg.toString(), new Object[0]);
                C2341i.m2697b(guardMsg.getId());
                m2685c(guardMsg);
                C2341i.m2700c(new Hashon().fromObject(guardMsg));
                if (Build.VERSION.SDK_INT >= 18) {
                    this.f2221g.getLooper().quitSafely();
                } else {
                    this.f2221g.getLooper().quit();
                }
            }
        } else if (i == 1) {
            GuardMsg guardMsg2 = (GuardMsg) message.obj;
            if (guardMsg2 != null) {
                NLog b2 = C2335e.m2675b();
                b2.mo29768d("[Guard]{MobGuardCommonIdBCReceiver} onReceive is accept handlerNewGuardMsg is :" + guardMsg2.toString(), new Object[0]);
                if (guardMsg2.getHostPkgName().equals(MobSDK.getContext().getPackageName())) {
                    List<GuardMsg> list = this.f2220f;
                    if (list != null && list.isEmpty()) {
                        this.f2221g.sendEmptyMessageDelayed(2, Config.BPLUS_DELAY_TIME);
                    }
                    this.f2220f.add(guardMsg2);
                } else if (m2684a()) {
                    String a = C2341i.m2692a();
                    if (!TextUtils.isEmpty(a)) {
                        GuardMsg guardMsg3 = new GuardMsg();
                        guardMsg3.toObj(a, guardMsg3);
                        this.f2216b = guardMsg3.getVersion();
                        this.f2217c = guardMsg2.getVersion();
                        this.f2218d = guardMsg3.getId();
                        this.f2219e = guardMsg2.getId();
                        NLog b3 = C2335e.m2675b();
                        b3.mo29768d("[Guard]{MobGuardCommonIdBCReceiver}goalapp start Synchronization data oldVersion, newVersion, oldId, newId is, oldVersion=" + this.f2216b + "newVersion=" + this.f2217c + "oldI= " + this.f2218d + "newId=" + this.f2219e, new Object[0]);
                        if (!TextUtils.isEmpty(this.f2219e) && !TextUtils.isEmpty(this.f2218d)) {
                            int i2 = this.f2216b;
                            int i3 = this.f2217c;
                            if (i2 == i3 || i3 > i2) {
                                if (!TextUtils.isEmpty(this.f2219e) && !this.f2219e.equals(this.f2218d)) {
                                    guardMsg2.setMasterBigger(1);
                                }
                                guardMsg2.setSynchronousPublish(false);
                                m2686d(guardMsg2);
                                return;
                            }
                            if (!TextUtils.isEmpty(this.f2219e) && !this.f2219e.equals(this.f2218d)) {
                                guardMsg3.setMasterBigger(1);
                            }
                            guardMsg3.setHostPkgName(guardMsg2.getHostPkgName());
                            guardMsg3.setSynchronousPublish(false);
                            m2686d(guardMsg3);
                        }
                    }
                }
            }
        } else if (i == 2 && m2684a()) {
            m2679a(MobSDK.getContext());
        }
    }

    /* renamed from: a */
    private boolean m2684a() {
        try {
            if (!C2332c.f2199b) {
                long currentTimeMillis = System.currentTimeMillis() - MobGuardCommonIdBCReceiver.f2191a;
                if (currentTimeMillis < 3000) {
                    Thread.sleep(3000 - currentTimeMillis);
                    if (C2332c.f2199b && C2332c.f2198a) {
                        return true;
                    }
                }
            } else if (C2332c.f2198a) {
                return true;
            }
        } catch (Throwable th) {
            C2335e.m2675b().mo29769d(th);
        }
        return false;
    }

    /* renamed from: a */
    public static void m2682a(GuardMsg guardMsg) {
        f2215a.mo29154b(guardMsg);
    }

    /* renamed from: b */
    public void mo29154b(GuardMsg guardMsg) {
        try {
            if (guardMsg.isSynchronousPublish()) {
                Message obtain = Message.obtain();
                obtain.what = 0;
                obtain.obj = guardMsg;
                this.f2221g.sendMessage(obtain);
                return;
            }
            Message obtain2 = Message.obtain();
            obtain2.what = 1;
            obtain2.obj = guardMsg;
            this.f2221g.sendMessage(obtain2);
        } catch (Throwable th) {
            C2335e.m2675b().mo29769d(th);
        }
    }

    /* renamed from: c */
    private static void m2685c(GuardMsg guardMsg) {
        try {
            if (C2341i.m2699c().equals(guardMsg.getId())) {
                C2335e.m2675b().mo29768d("[Guard]{MobGuardCommonIdBCReceiver} guardid is same not reconnec tcp...", new Object[0]);
            } else if (System.currentTimeMillis() - C2341i.m2696b() < 30000) {
                C2335e.m2675b().mo29768d("[Guard]{MobGuardCommonIdBCReceiver} time is less than 30000, not reconnec tcp...", new Object[0]);
            } else {
                C2341i.m2693a(System.currentTimeMillis());
                if (guardMsg != null) {
                    C2328b.f2193a.mo29145a(guardMsg);
                }
            }
        } catch (Throwable th) {
            C2335e.m2675b().mo29769d(th);
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m2679a(android.content.Context r9) {
        /*
            r8 = this;
            java.util.List<com.mob.commons.GuardMsg> r0 = r8.f2220f     // Catch:{ Throwable -> 0x017a }
            if (r0 == 0) goto L_0x0179
            java.util.List<com.mob.commons.GuardMsg> r0 = r8.f2220f     // Catch:{ Throwable -> 0x017a }
            int r0 = r0.size()     // Catch:{ Throwable -> 0x017a }
            if (r0 == 0) goto L_0x0179
            java.lang.String r0 = com.mob.guard.C2341i.m2692a()     // Catch:{ Throwable -> 0x017a }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x017a }
            if (r0 == 0) goto L_0x0018
            goto L_0x0179
        L_0x0018:
            com.mob.commons.GuardMsg r0 = new com.mob.commons.GuardMsg     // Catch:{ Throwable -> 0x017a }
            r0.<init>()     // Catch:{ Throwable -> 0x017a }
            java.lang.String r1 = com.mob.guard.C2341i.m2692a()     // Catch:{ Throwable -> 0x017a }
            r0.toObj(r1, r0)     // Catch:{ Throwable -> 0x017a }
            r1 = 0
            com.mob.tools.log.NLog r2 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x004b }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x004b }
            r3.<init>()     // Catch:{ Throwable -> 0x004b }
            java.lang.String r4 = "[Guard]Host need sort original data is ,"
            r3.append(r4)     // Catch:{ Throwable -> 0x004b }
            com.mob.tools.utils.Hashon r4 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x004b }
            r4.<init>()     // Catch:{ Throwable -> 0x004b }
            java.util.List<com.mob.commons.GuardMsg> r5 = r8.f2220f     // Catch:{ Throwable -> 0x004b }
            java.lang.String r4 = r4.fromObject(r5)     // Catch:{ Throwable -> 0x004b }
            r3.append(r4)     // Catch:{ Throwable -> 0x004b }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x004b }
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x004b }
            r2.mo29768d(r3, r4)     // Catch:{ Throwable -> 0x004b }
            goto L_0x0053
        L_0x004b:
            r2 = move-exception
            com.mob.tools.log.NLog r3 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x017a }
            r3.mo29772e(r2)     // Catch:{ Throwable -> 0x017a }
        L_0x0053:
            java.util.List<com.mob.commons.GuardMsg> r2 = r8.f2220f     // Catch:{ Throwable -> 0x017a }
            r2.add(r0)     // Catch:{ Throwable -> 0x017a }
            java.util.List<com.mob.commons.GuardMsg> r2 = r8.f2220f     // Catch:{ Throwable -> 0x017a }
            boolean r2 = r8.mo29155b((java.util.List<com.mob.commons.GuardMsg>) r2)     // Catch:{ Throwable -> 0x017a }
            java.util.List<com.mob.commons.GuardMsg> r3 = r8.f2220f     // Catch:{ Throwable -> 0x017a }
            boolean r3 = r8.mo29156c((java.util.List<com.mob.commons.GuardMsg>) r3)     // Catch:{ Throwable -> 0x017a }
            r4 = 1
            if (r2 != 0) goto L_0x00a6
            if (r3 != 0) goto L_0x00a6
            com.mob.tools.log.NLog r5 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x017a }
            java.lang.String r6 = "[Guard]Host version and id is same"
            java.lang.Object[] r7 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x017a }
            r5.mo29768d(r6, r7)     // Catch:{ Throwable -> 0x017a }
            r5 = 0
            r6 = 0
        L_0x0076:
            java.util.List<com.mob.commons.GuardMsg> r7 = r8.f2220f     // Catch:{ Throwable -> 0x017a }
            int r7 = r7.size()     // Catch:{ Throwable -> 0x017a }
            if (r5 >= r7) goto L_0x008e
            java.util.List<com.mob.commons.GuardMsg> r7 = r8.f2220f     // Catch:{ Throwable -> 0x017a }
            java.lang.Object r7 = r7.get(r5)     // Catch:{ Throwable -> 0x017a }
            com.mob.commons.GuardMsg r7 = (com.mob.commons.GuardMsg) r7     // Catch:{ Throwable -> 0x017a }
            int r7 = r7.getMasterBigger()     // Catch:{ Throwable -> 0x017a }
            int r6 = r6 + r7
            int r5 = r5 + 1
            goto L_0x0076
        L_0x008e:
            r0.setVersion(r6)     // Catch:{ Throwable -> 0x017a }
            m2685c((com.mob.commons.GuardMsg) r0)     // Catch:{ Throwable -> 0x017a }
            com.mob.tools.utils.Hashon r5 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x017a }
            r5.<init>()     // Catch:{ Throwable -> 0x017a }
            java.lang.String r5 = r5.fromObject(r0)     // Catch:{ Throwable -> 0x017a }
            com.mob.guard.C2341i.m2700c(r5)     // Catch:{ Throwable -> 0x017a }
            r0.setSynchronousPublish(r4)     // Catch:{ Throwable -> 0x017a }
            r8.m2680a((android.content.Context) r9, (com.mob.commons.GuardMsg) r0)     // Catch:{ Throwable -> 0x017a }
        L_0x00a6:
            if (r2 != 0) goto L_0x00ca
            if (r3 == 0) goto L_0x00ca
            com.mob.tools.log.NLog r5 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x017a }
            java.lang.String r6 = "[Guard]Host version is same, id is different"
            java.lang.Object[] r7 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x017a }
            r5.mo29768d(r6, r7)     // Catch:{ Throwable -> 0x017a }
            com.mob.tools.utils.Hashon r5 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x017a }
            r5.<init>()     // Catch:{ Throwable -> 0x017a }
            java.lang.String r5 = r5.fromObject(r0)     // Catch:{ Throwable -> 0x017a }
            com.mob.guard.C2341i.m2700c(r5)     // Catch:{ Throwable -> 0x017a }
            m2685c((com.mob.commons.GuardMsg) r0)     // Catch:{ Throwable -> 0x017a }
            r0.setSynchronousPublish(r4)     // Catch:{ Throwable -> 0x017a }
            r8.m2680a((android.content.Context) r9, (com.mob.commons.GuardMsg) r0)     // Catch:{ Throwable -> 0x017a }
        L_0x00ca:
            if (r2 == 0) goto L_0x010f
            if (r3 != 0) goto L_0x010f
            com.mob.tools.log.NLog r5 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x017a }
            java.lang.String r6 = "[Guard]Host version is different, id is same"
            java.lang.Object[] r7 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x017a }
            r5.mo29768d(r6, r7)     // Catch:{ Throwable -> 0x017a }
            java.util.List<com.mob.commons.GuardMsg> r5 = r8.f2220f     // Catch:{ Throwable -> 0x017a }
            java.util.List r5 = m2678a((java.util.List<com.mob.commons.GuardMsg>) r5)     // Catch:{ Throwable -> 0x017a }
            if (r5 == 0) goto L_0x010e
            int r6 = r5.size()     // Catch:{ Throwable -> 0x017a }
            if (r6 != 0) goto L_0x00e8
            goto L_0x010e
        L_0x00e8:
            java.lang.Object r6 = r5.get(r1)     // Catch:{ Throwable -> 0x017a }
            com.mob.commons.GuardMsg r6 = (com.mob.commons.GuardMsg) r6     // Catch:{ Throwable -> 0x017a }
            m2685c((com.mob.commons.GuardMsg) r6)     // Catch:{ Throwable -> 0x017a }
            r0.setSynchronousPublish(r4)     // Catch:{ Throwable -> 0x017a }
            com.mob.tools.utils.Hashon r0 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x017a }
            r0.<init>()     // Catch:{ Throwable -> 0x017a }
            java.lang.Object r6 = r5.get(r1)     // Catch:{ Throwable -> 0x017a }
            java.lang.String r0 = r0.fromObject(r6)     // Catch:{ Throwable -> 0x017a }
            com.mob.guard.C2341i.m2700c(r0)     // Catch:{ Throwable -> 0x017a }
            java.lang.Object r0 = r5.get(r1)     // Catch:{ Throwable -> 0x017a }
            com.mob.commons.GuardMsg r0 = (com.mob.commons.GuardMsg) r0     // Catch:{ Throwable -> 0x017a }
            r8.m2680a((android.content.Context) r9, (com.mob.commons.GuardMsg) r0)     // Catch:{ Throwable -> 0x017a }
            goto L_0x010f
        L_0x010e:
            return
        L_0x010f:
            if (r2 == 0) goto L_0x015f
            if (r3 == 0) goto L_0x015f
            com.mob.tools.log.NLog r0 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x017a }
            java.lang.String r2 = "[Guard]Host version and id is different"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x017a }
            r0.mo29768d(r2, r3)     // Catch:{ Throwable -> 0x017a }
            java.util.List<com.mob.commons.GuardMsg> r0 = r8.f2220f     // Catch:{ Throwable -> 0x017a }
            java.util.List r0 = m2678a((java.util.List<com.mob.commons.GuardMsg>) r0)     // Catch:{ Throwable -> 0x017a }
            if (r0 == 0) goto L_0x015f
            int r2 = r0.size()     // Catch:{ Throwable -> 0x017a }
            if (r2 <= 0) goto L_0x015f
            java.lang.Object r2 = r0.get(r1)     // Catch:{ Throwable -> 0x017a }
            com.mob.commons.GuardMsg r2 = (com.mob.commons.GuardMsg) r2     // Catch:{ Throwable -> 0x017a }
            r3 = 0
        L_0x0133:
            int r5 = r0.size()     // Catch:{ Throwable -> 0x017a }
            if (r1 >= r5) goto L_0x0147
            java.lang.Object r5 = r0.get(r1)     // Catch:{ Throwable -> 0x017a }
            com.mob.commons.GuardMsg r5 = (com.mob.commons.GuardMsg) r5     // Catch:{ Throwable -> 0x017a }
            int r5 = r5.getMasterBigger()     // Catch:{ Throwable -> 0x017a }
            int r3 = r3 + r5
            int r1 = r1 + 1
            goto L_0x0133
        L_0x0147:
            r2.setVersion(r3)     // Catch:{ Throwable -> 0x017a }
            m2685c((com.mob.commons.GuardMsg) r2)     // Catch:{ Throwable -> 0x017a }
            com.mob.tools.utils.Hashon r0 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x017a }
            r0.<init>()     // Catch:{ Throwable -> 0x017a }
            java.lang.String r0 = r0.fromObject(r2)     // Catch:{ Throwable -> 0x017a }
            com.mob.guard.C2341i.m2700c(r0)     // Catch:{ Throwable -> 0x017a }
            r2.setSynchronousPublish(r4)     // Catch:{ Throwable -> 0x017a }
            r8.m2680a((android.content.Context) r9, (com.mob.commons.GuardMsg) r2)     // Catch:{ Throwable -> 0x017a }
        L_0x015f:
            int r9 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x017a }
            r0 = 18
            if (r9 < r0) goto L_0x016f
            android.os.Handler r9 = r8.f2221g     // Catch:{ Throwable -> 0x017a }
            android.os.Looper r9 = r9.getLooper()     // Catch:{ Throwable -> 0x017a }
            r9.quitSafely()     // Catch:{ Throwable -> 0x017a }
            goto L_0x0182
        L_0x016f:
            android.os.Handler r9 = r8.f2221g     // Catch:{ Throwable -> 0x017a }
            android.os.Looper r9 = r9.getLooper()     // Catch:{ Throwable -> 0x017a }
            r9.quit()     // Catch:{ Throwable -> 0x017a }
            goto L_0x0182
        L_0x0179:
            return
        L_0x017a:
            r9 = move-exception
            com.mob.tools.log.NLog r0 = com.mob.guard.C2335e.m2675b()
            r0.mo29769d(r9)
        L_0x0182:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.guard.C2337g.m2679a(android.content.Context):void");
    }

    /* renamed from: a */
    public static List<GuardMsg> m2678a(List<GuardMsg> list) {
        try {
            Collections.sort(list, new Comparator<GuardMsg>() {
                /* renamed from: a */
                public int compare(GuardMsg guardMsg, GuardMsg guardMsg2) {
                    try {
                        NLog b = C2335e.m2675b();
                        b.mo29768d("[Guard]{MobGuardCommonIdBCReceiver} sortListData o1 is, " + guardMsg.toString(), new Object[0]);
                        NLog b2 = C2335e.m2675b();
                        b2.mo29768d("[Guard]{MobGuardCommonIdBCReceiver} sortListData o1 is, " + guardMsg2.toString(), new Object[0]);
                    } catch (Throwable th) {
                        C2335e.m2675b().mo29772e(th);
                    }
                    C2335e.m2675b().mo29768d("[Guard]{MobGuardCommonIdBCReceiver} transfer MobConnect.reconnect() to connect TCP", new Object[0]);
                    boolean z = guardMsg == null;
                    boolean z2 = guardMsg2 == null;
                    if (z && z2) {
                        return 0;
                    }
                    return (z || z2 || guardMsg2.getVersion() <= guardMsg.getVersion()) ? -1 : 1;
                }
            });
            return list;
        } catch (Throwable th) {
            C2335e.m2675b().mo29769d(th);
            return null;
        }
    }

    /* renamed from: a */
    private void m2680a(Context context, GuardMsg guardMsg) {
        try {
            C2341i.m2697b(guardMsg.getId());
            for (int i = 0; i < this.f2220f.size(); i++) {
                if (!TextUtils.isEmpty(this.f2220f.get(i).getGoalPkgName())) {
                    ComponentName componentName = new ComponentName(this.f2220f.get(i).getGoalPkgName(), MobGuardCommonIdBCReceiver.class.getName());
                    Intent intent = new Intent("com.mlive.id");
                    intent.putExtra(NotificationCompat.CATEGORY_MESSAGE, guardMsg.toJson());
                    intent.setComponent(componentName);
                    context.sendBroadcast(intent);
                }
            }
        } catch (Throwable th) {
            C2335e.m2675b().mo29769d(th);
        }
    }

    /* renamed from: d */
    private void m2686d(GuardMsg guardMsg) {
        try {
            if (!guardMsg.isSynchronousPublish() && !TextUtils.isEmpty(guardMsg.getHostPkgName())) {
                ComponentName componentName = new ComponentName(guardMsg.getHostPkgName(), MobGuardCommonIdBCReceiver.class.getName());
                Intent intent = new Intent("com.mlive.id");
                guardMsg.setGoalPkgName(MobSDK.getContext().getPackageName());
                intent.putExtra(NotificationCompat.CATEGORY_MESSAGE, guardMsg.toJson());
                intent.setComponent(componentName);
                MobSDK.getContext().sendBroadcast(intent);
            }
        } catch (Throwable th) {
            C2335e.m2675b().mo29769d(th);
        }
    }

    /* renamed from: b */
    public boolean mo29155b(List<GuardMsg> list) {
        try {
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (GuardMsg version : list) {
                arrayList.add(Integer.valueOf(version.getVersion()));
            }
            if (arrayList.size() > 0) {
                int intValue = ((Integer) arrayList.get(0)).intValue();
                for (Integer intValue2 : arrayList) {
                    if (intValue2.intValue() != intValue) {
                        return true;
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            C2335e.m2675b().mo29769d(th);
        }
        return false;
    }

    /* renamed from: c */
    public boolean mo29156c(List<GuardMsg> list) {
        try {
            ArrayList<String> arrayList = new ArrayList<>();
            for (GuardMsg id : list) {
                arrayList.add(id.getId());
            }
            if (arrayList.size() > 0) {
                String str = (String) arrayList.get(0);
                for (String equals : arrayList) {
                    if (!equals.equals(str)) {
                        return true;
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            C2335e.m2675b().mo29769d(th);
        }
        return false;
    }
}
