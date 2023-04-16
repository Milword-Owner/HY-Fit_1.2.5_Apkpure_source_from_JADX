package com.mob.guard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.mob.commons.GuardMsg;

public class MobGuardCommonIdBCReceiver extends BroadcastReceiver {

    /* renamed from: a */
    public static long f2191a;

    /* renamed from: b */
    private String f2192b;

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            try {
                if ("com.mlive.id".equals(intent.getAction())) {
                    if (f2191a == 0) {
                        f2191a = System.currentTimeMillis();
                    }
                    this.f2192b = intent.getStringExtra(NotificationCompat.CATEGORY_MESSAGE);
                    if (!TextUtils.isEmpty(this.f2192b)) {
                        m2649a();
                    }
                }
            } catch (Throwable th) {
                C2335e.m2675b().mo29769d(th);
            }
        }
    }

    /* renamed from: a */
    private void m2649a() {
        GuardMsg guardMsg = new GuardMsg();
        guardMsg.toObj(this.f2192b, guardMsg);
        C2337g.m2682a(guardMsg);
    }
}
