package com.mob.guard;

import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.MotionEvent;

public class MobTranPullLockActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            if (getIntent() != null) {
                for (int addFlags : getIntent().getIntArrayExtra("fg")) {
                    getWindow().addFlags(addFlags);
                }
            }
        } catch (Throwable th) {
            C2335e.m2675b().mo29772e(th);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        try {
            if (((PowerManager) getSystemService("power")).isScreenOn()) {
                finish();
            }
        } catch (Throwable th) {
            C2335e.m2675b().mo29772e(th);
        }
        super.onResume();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            finish();
            return true;
        } catch (Throwable th) {
            C2335e.m2675b().mo29772e(th);
            return true;
        }
    }
}
