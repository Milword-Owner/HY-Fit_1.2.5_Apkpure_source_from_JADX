package com.mob.commons.logcollector;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Base64;
import androidx.core.app.NotificationCompat;
import com.mob.tools.MobLog;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.mob.commons.logcollector.e */
/* compiled from: MessageUtils */
public class C2321e {
    /* renamed from: a */
    public static synchronized long m2622a(long j, String str, int i, String str2) throws Throwable {
        synchronized (C2321e.class) {
            if (TextUtils.isEmpty(str)) {
                return -1;
            }
            C2315b a = C2315b.m2598a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("exception_time", Long.valueOf(j));
            contentValues.put("exception_msg", str.toString());
            contentValues.put("exception_level", Integer.valueOf(i));
            contentValues.put("exception_md5", str2);
            long a2 = a.mo29116a("table_exception", contentValues);
            return a2;
        }
    }

    /* renamed from: a */
    public static synchronized long m2623a(ArrayList<String> arrayList) throws Throwable {
        synchronized (C2321e.class) {
            if (arrayList == null) {
                return 0;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arrayList.size(); i++) {
                sb.append("'");
                sb.append(arrayList.get(i));
                sb.append("'");
                sb.append(",");
            }
            String substring = sb.toString().substring(0, sb.length() - 1);
            C2315b a = C2315b.m2598a();
            int a2 = a.mo29115a("table_exception", "exception_md5 in ( " + substring + " )", (String[]) null);
            MobLog.getInstance().mo29775i("delete COUNT == %s", Integer.valueOf(a2));
            long j = (long) a2;
            return j;
        }
    }

    /* renamed from: a */
    private static synchronized ArrayList<C2320d> m2624a(String str, String[] strArr) throws Throwable {
        ArrayList<C2320d> arrayList;
        synchronized (C2321e.class) {
            arrayList = new ArrayList<>();
            C2320d dVar = new C2320d();
            C2315b a = C2315b.m2598a();
            String str2 = " select exception_md5, exception_level, exception_time, exception_msg, sum(exception_counts) from table_exception group by exception_md5 having max(_id)";
            if (!TextUtils.isEmpty(str) && strArr != null && strArr.length > 0) {
                str2 = " select exception_md5, exception_level, exception_time, exception_msg, sum(exception_counts) from table_exception where " + str + " group by exception_md5 having max(_id)";
            }
            Cursor a2 = a.mo29117a(str2, strArr);
            while (true) {
                if (a2 == null || !a2.moveToNext()) {
                    break;
                }
                dVar.f2174b.add(a2.getString(0));
                HashMap hashMap = new HashMap();
                hashMap.put("type", Integer.valueOf(a2.getInt(1)));
                hashMap.put("errat", Long.valueOf(a2.getLong(2)));
                hashMap.put(NotificationCompat.CATEGORY_MESSAGE, Base64.encodeToString(a2.getString(3).getBytes(), 2));
                hashMap.put("times", Integer.valueOf(a2.getInt(4)));
                dVar.f2173a.add(hashMap);
                if (dVar.f2174b.size() == 50) {
                    arrayList.add(dVar);
                    dVar = new C2320d();
                    break;
                }
            }
            a2.close();
            if (dVar.f2174b.size() != 0) {
                arrayList.add(dVar);
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0009, code lost:
        if (r4.length <= 0) goto L_0x000b;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.util.ArrayList<com.mob.commons.logcollector.C2320d> m2625a(java.lang.String[] r4) throws java.lang.Throwable {
        /*
            java.lang.Class<com.mob.commons.logcollector.e> r0 = com.mob.commons.logcollector.C2321e.class
            monitor-enter(r0)
            java.lang.String r1 = "exception_level = ?"
            r2 = 0
            if (r4 == 0) goto L_0x000b
            int r3 = r4.length     // Catch:{ all -> 0x0026 }
            if (r3 > 0) goto L_0x000d
        L_0x000b:
            r4 = r2
            r1 = r4
        L_0x000d:
            com.mob.commons.logcollector.b r2 = com.mob.commons.logcollector.C2315b.m2598a()     // Catch:{ all -> 0x0026 }
            java.lang.String r3 = "table_exception"
            int r2 = r2.mo29114a(r3)     // Catch:{ all -> 0x0026 }
            if (r2 <= 0) goto L_0x001f
            java.util.ArrayList r4 = m2624a(r1, r4)     // Catch:{ all -> 0x0026 }
            monitor-exit(r0)
            return r4
        L_0x001f:
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x0026 }
            r4.<init>()     // Catch:{ all -> 0x0026 }
            monitor-exit(r0)
            return r4
        L_0x0026:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.logcollector.C2321e.m2625a(java.lang.String[]):java.util.ArrayList");
    }
}
