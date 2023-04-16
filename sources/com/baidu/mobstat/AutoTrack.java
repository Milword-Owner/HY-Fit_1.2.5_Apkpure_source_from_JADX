package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.baidu.mobstat.ActivityLifeObserver;

public class AutoTrack {
    public static final int JOB_TYPE_BGSEND = 2;
    public static final int JOB_TYPE_SESSIONTIME = 1;

    public static class MyActivityLifeCallback implements ActivityLifeObserver.IActivityLifeCallback {

        /* renamed from: a */
        private int f638a;

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public MyActivityLifeCallback(int i) {
            this.f638a = i;
        }

        public void onActivityResumed(Activity activity) {
            Context applicationContext = activity.getApplicationContext();
            int i = this.f638a;
            if (i == 1) {
                BDStatCore.instance().autoTrackSessionStartTime(applicationContext);
            } else if (i == 2) {
                BDStatCore.instance().cancelSendLogCheck();
            }
        }

        public void onActivityPaused(Activity activity) {
            Context applicationContext = activity.getApplicationContext();
            int i = this.f638a;
            if (i == 1) {
                BDStatCore.instance().autoTrackSessionEndTime(applicationContext);
            } else if (i == 2) {
                BDStatCore.instance().doSendLogCheck(applicationContext);
            }
        }
    }
}
