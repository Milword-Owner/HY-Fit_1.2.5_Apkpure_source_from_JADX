package com.mob.commons.logcollector;

import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.mob.MobSDK;
import com.mob.tools.MobLog;
import com.mob.tools.log.LogCollector;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.Data;
import java.util.HashMap;
import p005cn.sharesdk.framework.ShareSDK;

public class DefaultLogsCollector implements LogCollector, PublicMemberKeeper {

    /* renamed from: a */
    private static DefaultLogsCollector f2151a;

    /* renamed from: b */
    private final HashMap<String, Integer> f2152b = new HashMap<>();

    /* renamed from: c */
    private C2316c f2153c = C2316c.m2603a();

    /* renamed from: d */
    private boolean f2154d;

    public static synchronized DefaultLogsCollector get() {
        DefaultLogsCollector defaultLogsCollector;
        synchronized (DefaultLogsCollector.class) {
            if (f2151a == null) {
                f2151a = new DefaultLogsCollector();
            }
            defaultLogsCollector = f2151a;
        }
        return defaultLogsCollector;
    }

    private DefaultLogsCollector() {
        try {
            if (MobSDK.getContext().getPackageManager().getPackageInfo("cn.sharesdk.log", 64) != null) {
                this.f2154d = true;
            }
        } catch (Throwable unused) {
            this.f2154d = false;
        }
    }

    public void addSDK(String str, int i) {
        synchronized (this.f2152b) {
            Integer num = this.f2152b.get(str);
            this.f2152b.put(str, Integer.valueOf(i));
            if (num == null && this.f2153c != null) {
                this.f2153c.mo29119a(i, str);
            }
        }
    }

    public final void log(String str, int i, int i2, String str2, String str3) {
        Integer num;
        mo29105a(i, str3);
        if ("MOBTOOLS".equalsIgnoreCase(str)) {
            num = this.f2152b.get("MOBSDK");
            if (num == null) {
                num = -999;
            }
        } else {
            num = this.f2152b.get(str);
            if (num == null) {
                return;
            }
        }
        if (ShareSDK.SDK_TAG.equals(str) && (!str3.contains("com.mob.") || !str3.contains("cn.sharesdk."))) {
            return;
        }
        if (i2 == 1) {
            this.f2153c.mo29121b(num.intValue(), i2, str, str3);
        } else if (i2 == 2) {
            this.f2153c.mo29118a(num.intValue(), i2, str, str3);
        } else if (i2 == 3) {
            this.f2153c.mo29118a(num.intValue(), i2, str, str3);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final int mo29105a(int i, String str) {
        if (MobSDK.getContext() == null || !this.f2154d) {
            return 0;
        }
        try {
            Intent intent = new Intent();
            intent.setPackage("cn.sharesdk.log");
            String packageName = MobSDK.getContext().getPackageName();
            intent.putExtra("package", packageName);
            intent.putExtra("priority", i);
            intent.putExtra(NotificationCompat.CATEGORY_MESSAGE, Data.AES128Encode(packageName, str));
            MobSDK.getContext().sendBroadcast(intent);
            return 0;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return 0;
        }
    }
}
