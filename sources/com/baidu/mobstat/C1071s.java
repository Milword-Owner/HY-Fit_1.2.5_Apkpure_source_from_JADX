package com.baidu.mobstat;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import androidx.exifinterface.media.ExifInterface;
import java.util.List;

/* renamed from: com.baidu.mobstat.s */
public enum C1071s {
    SERVICE(1) {
        /* renamed from: a */
        public void mo11872a(Context context) {
            if (C1076t.m1784a(context).mo11427b(context)) {
                try {
                    Intent intent = new Intent(context, Class.forName("com.baidu.bottom.service.BottomService"));
                    intent.putExtra("SDK_PRODUCT_LY", "MS");
                    context.startService(intent);
                } catch (Throwable th) {
                    C0954ba.m1191c().mo11629b(th);
                }
            }
        }
    },
    NO_SERVICE(2) {
        /* renamed from: a */
        public void mo11872a(Context context) {
            Context applicationContext = context.getApplicationContext();
            C0875a a = C1076t.m1784a(context);
            C0876aa aaVar = new C0876aa();
            aaVar.f929a = false;
            aaVar.f930b = "M";
            aaVar.f931c = false;
            a.mo11424a(applicationContext, aaVar.mo11428a());
        }
    },
    RECEIVER(3) {
        /* renamed from: a */
        public void mo11872a(Context context) {
            Context applicationContext = context.getApplicationContext();
            C0875a a = C1076t.m1784a(context);
            C0876aa aaVar = new C0876aa();
            aaVar.f929a = false;
            aaVar.f930b = "R";
            aaVar.f931c = false;
            a.mo11424a(applicationContext, aaVar.mo11428a());
        }
    },
    ERISED(4) {
        /* renamed from: a */
        public void mo11872a(Context context) {
            Context applicationContext = context.getApplicationContext();
            C0875a a = C1076t.m1784a(context);
            C0876aa aaVar = new C0876aa();
            aaVar.f929a = false;
            aaVar.f930b = ExifInterface.LONGITUDE_EAST;
            aaVar.f931c = false;
            a.mo11424a(applicationContext, aaVar.mo11428a());
        }
    };
    

    /* renamed from: e */
    private int f1446e;

    /* renamed from: a */
    public abstract void mo11872a(Context context);

    private C1071s(int i) {
        this.f1446e = i;
    }

    public String toString() {
        return String.valueOf(this.f1446e);
    }

    /* renamed from: a */
    public static C1071s m1777a(int i) {
        for (C1071s sVar : values()) {
            if (sVar.f1446e == i) {
                return sVar;
            }
        }
        return NO_SERVICE;
    }

    /* renamed from: b */
    public static boolean m1778b(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null) {
            try {
                List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(Integer.MAX_VALUE);
                int i = 0;
                while (runningServices != null && i < runningServices.size()) {
                    if ("com.baidu.bottom.service.BottomService".equals(runningServices.get(i).service.getClassName())) {
                        return true;
                    }
                    i++;
                }
            } catch (Exception e) {
                C0954ba.m1191c().mo11626a((Throwable) e);
            }
        }
        return false;
    }
}
