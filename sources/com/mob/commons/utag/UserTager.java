package com.mob.commons.utag;

import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.share.internal.ShareConstants;
import com.mob.MobCommunicator;
import com.mob.MobSDK;
import com.mob.commons.C2262b;
import com.mob.commons.C2310j;
import com.mob.commons.MobProduct;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.SQLiteHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import p015io.reactivex.annotations.SchedulerSupport;

public final class UserTager implements PublicMemberKeeper {

    /* renamed from: a */
    private static Handler f2182a = MobHandlerThread.newHandler("t", (Handler.Callback) new Handler.Callback() {
        public boolean handleMessage(Message message) {
            if (!C2262b.m2259Z()) {
                int i = message.what;
                if (i != 1) {
                    if (i == 2) {
                        Object[] objArr = (Object[]) message.obj;
                        UserTager.m2643b((HashMap) objArr[0], (UserTagError) objArr[1]);
                        UserTager.m2645c();
                    }
                } else if (!UserTager.m2646d()) {
                    UserTager.m2645c();
                }
            }
            return false;
        }
    });

    /* renamed from: b */
    private static SQLiteHelper.SingleTableDB f2183b = SQLiteHelper.getDatabase(MobSDK.getContext(), "UserTag_1");

    /* renamed from: c */
    private static Hashon f2184c = new Hashon();

    /* renamed from: d */
    private static DeviceHelper f2185d = DeviceHelper.getInstance(MobSDK.getContext());

    /* renamed from: e */
    private HashMap<String, Object> f2186e = new HashMap<>();

    /* renamed from: f */
    private UserTagError f2187f;

    static {
        f2183b.addField("time", ViewHierarchyConstants.TEXT_KEY, true);
        f2183b.addField(ShareConstants.WEB_DIALOG_PARAM_DATA, ViewHierarchyConstants.TEXT_KEY, true);
        m2645c();
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public static void m2645c() {
        f2182a.removeMessages(1);
        String networkType = f2185d.getNetworkType();
        f2182a.sendEmptyMessageDelayed(1, (networkType == null || SchedulerSupport.NONE.equals(networkType)) ? 600000 : 10000);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0056, code lost:
        r0 = r1;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m2643b(java.util.HashMap<java.lang.String, java.lang.Object> r7, com.mob.commons.utag.UserTagError r8) {
        /*
            java.util.Set r0 = r7.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x0008:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0093
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L_0x0024
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "found a key of null"
            r0.<init>(r1)
            goto L_0x0094
        L_0x0024:
            int r3 = r2.length()
            java.lang.String r4 = " > "
            java.lang.String r5 = "' is too long: "
            r6 = 30
            if (r3 <= r6) goto L_0x0058
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "key '"
            r0.append(r1)
            r0.append(r2)
            r0.append(r5)
            int r1 = r2.length()
            r0.append(r1)
            r0.append(r4)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r0)
        L_0x0056:
            r0 = r1
            goto L_0x0094
        L_0x0058:
            java.lang.Object r1 = r1.getValue()
            if (r1 == 0) goto L_0x0008
            boolean r2 = r1 instanceof java.lang.String
            if (r2 == 0) goto L_0x0008
            java.lang.String r1 = (java.lang.String) r1
            int r2 = r1.length()
            r3 = 255(0xff, float:3.57E-43)
            if (r2 <= r3) goto L_0x0008
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "value '"
            r0.append(r2)
            r0.append(r1)
            r0.append(r5)
            int r1 = r1.length()
            r0.append(r1)
            r0.append(r4)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r0)
            goto L_0x0056
        L_0x0093:
            r0 = 0
        L_0x0094:
            if (r0 == 0) goto L_0x009c
            if (r8 == 0) goto L_0x009b
            r8.onError(r0)
        L_0x009b:
            return
        L_0x009c:
            long r0 = com.mob.commons.C2262b.m2260a()     // Catch:{ Throwable -> 0x00c8 }
            java.lang.String r2 = "___datetime"
            java.lang.Long r3 = java.lang.Long.valueOf(r0)     // Catch:{ Throwable -> 0x00c8 }
            r7.put(r2, r3)     // Catch:{ Throwable -> 0x00c8 }
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch:{ Throwable -> 0x00c8 }
            r2.<init>()     // Catch:{ Throwable -> 0x00c8 }
            java.lang.String r3 = "time"
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x00c8 }
            r2.put(r3, r0)     // Catch:{ Throwable -> 0x00c8 }
            java.lang.String r0 = "data"
            com.mob.tools.utils.Hashon r1 = f2184c     // Catch:{ Throwable -> 0x00c8 }
            java.lang.String r7 = r1.fromHashMap(r7)     // Catch:{ Throwable -> 0x00c8 }
            r2.put(r0, r7)     // Catch:{ Throwable -> 0x00c8 }
            com.mob.tools.utils.SQLiteHelper$SingleTableDB r7 = f2183b     // Catch:{ Throwable -> 0x00c8 }
            com.mob.tools.utils.SQLiteHelper.insert(r7, r2)     // Catch:{ Throwable -> 0x00c8 }
            goto L_0x00ce
        L_0x00c8:
            r7 = move-exception
            if (r8 == 0) goto L_0x00ce
            r8.onError(r7)
        L_0x00ce:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.utag.UserTager.m2643b(java.util.HashMap, com.mob.commons.utag.UserTagError):void");
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public static boolean m2646d() {
        String networkType = f2185d.getNetworkType();
        if (networkType == null || SchedulerSupport.NONE.equals(networkType) || C2262b.m2242I()) {
            return false;
        }
        ArrayList<HashMap<String, Object>> e = m2647e();
        if (e == null || e.size() <= 0) {
            return true;
        }
        if (!m2641a(e)) {
            return false;
        }
        m2642b(e);
        if (e.size() != 50) {
            return true;
        }
        m2646d();
        return true;
    }

    /* renamed from: e */
    private static ArrayList<HashMap<String, Object>> m2647e() {
        try {
            Cursor query = SQLiteHelper.query(f2183b, new String[]{ShareConstants.WEB_DIALOG_PARAM_DATA}, (String) null, (String[]) null, (String) null);
            if (query == null) {
                return null;
            }
            if (!query.moveToFirst()) {
                query.close();
                return null;
            }
            ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
            do {
                arrayList.add(f2184c.fromJson(query.getString(0)));
                if (arrayList.size() > 50 || !query.moveToNext()) {
                    query.close();
                }
                arrayList.add(f2184c.fromJson(query.getString(0)));
                break;
            } while (!query.moveToNext());
            query.close();
            return arrayList;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return null;
        }
    }

    /* renamed from: a */
    private static boolean m2641a(ArrayList<HashMap<String, Object>> arrayList) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("plat", 1);
            hashMap.put("mac", f2185d.getMacAddress());
            hashMap.put("duid", DeviceAuthorizer.authorize((MobProduct) null));
            hashMap.put("model", f2185d.getModel());
            hashMap.put("imei", f2185d.getIMEI());
            hashMap.put("serialno", f2185d.getSerialno());
            hashMap.put("appkey", MobSDK.getAppkey());
            hashMap.put("apppkg", f2185d.getPackageName());
            hashMap.put("appver", f2185d.getAppVersionName());
            hashMap.put("datas", arrayList);
            new MobCommunicator(1024, "e3e28dce5fe8fc1bb56a25964219d5dc2976edb171b99b1103c2c4f89ad0b66fb58669fe69eb0b5d11e8be990b0715b4de2b4e5a5dcce121f47f18063d5d99f9", "256f461cc45979b52264ac022ff1353ea5f8140d35686ffdae2faee09db2006c3b43c2bb74ce6f4c51698db6384c1c0ceca958208d65c7ed345a04ea6349ca39601818c3d5500565ba49ed49c0f4014b06980d17fc069c95d30092d0cfdaddf783ea96c5f8bdc42b6765d71a5d12192ef74646b41d92f1caeba3123e71938d39").requestSynchronized((HashMap<String, Object>) hashMap, C2310j.m2574c("http://api.utag.mob.com/bdata"), false);
            return true;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return false;
        }
    }

    /* renamed from: b */
    private static void m2642b(ArrayList<HashMap<String, Object>> arrayList) {
        try {
            StringBuilder sb = new StringBuilder();
            Iterator<HashMap<String, Object>> it = arrayList.iterator();
            while (it.hasNext()) {
                HashMap next = it.next();
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append('\'');
                sb.append(next.get("___datetime"));
                sb.append('\'');
            }
            SQLiteHelper.SingleTableDB singleTableDB = f2183b;
            SQLiteHelper.delete(singleTableDB, "time in (" + sb.toString() + ")", (String[]) null);
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    UserTager() {
    }

    public CustomTag set(String str) {
        return new CustomTag(this, str);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2639a(String str, Object obj) {
        this.f2186e.put(str, obj);
    }

    public UserTager whenError(UserTagError userTagError) {
        this.f2187f = userTagError;
        return this;
    }

    public void commit() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.f2186e);
        Message message = new Message();
        message.what = 2;
        message.obj = new Object[]{hashMap, this.f2187f};
        f2182a.sendMessage(message);
    }

    public class CustomTag implements PublicMemberKeeper {

        /* renamed from: b */
        private UserTager f2189b;

        /* renamed from: c */
        private String f2190c;

        private CustomTag(UserTager userTager, String str) {
            this.f2189b = userTager;
            this.f2190c = str;
        }

        public UserTager withValue(Number number) {
            return m2648a(number);
        }

        public UserTager withValue(Boolean bool) {
            return m2648a(bool);
        }

        public UserTager withValue(String str) {
            return m2648a(str);
        }

        /* renamed from: a */
        private UserTager m2648a(Object obj) {
            this.f2189b.m2639a(this.f2190c, obj);
            return this.f2189b;
        }
    }
}
