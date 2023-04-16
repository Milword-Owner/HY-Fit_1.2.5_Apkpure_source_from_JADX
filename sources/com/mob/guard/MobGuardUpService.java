package com.mob.guard;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import com.mob.tools.proguard.ClassKeeper;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;

public class MobGuardUpService extends Service implements ClassKeeper {
    public void onCreate() {
        super.onCreate();
    }

    public IBinder onBind(Intent intent) {
        getIntentData(intent);
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            getIntentData(intent);
        }
        return super.onStartCommand(intent, i, i2);
    }

    private void getIntentData(Intent intent) {
        String stringExtra = intent.getStringExtra(ShareConstants.WEB_DIALOG_PARAM_DATA);
        if (!TextUtils.isEmpty(stringExtra)) {
            setSecretDataToPush(stringExtra);
        }
        try {
            ResHelper.getDataCacheFile(this, ".mmgd").createNewFile();
            String stringExtra2 = intent.getStringExtra("pkgname");
            if (!TextUtils.isEmpty(stringExtra2) && Build.VERSION.SDK_INT >= 8) {
                ResHelper.saveObjectToFile(getExternalFilesDir((String) null) + "/.fgd", C2327a.m2650a(Build.BRAND, stringExtra2));
                MobGuard.GDF = C2327a.m2650a(Build.BRAND, stringExtra2);
            }
        } catch (Throwable th) {
            C2335e.m2675b().mo29769d(th);
        }
        try {
            ((C2340h) Class.forName(getPackageManager().getPackageInfo(getPackageName(), 128).applicationInfo.metaData.getString("guard_listener")).newInstance()).mo29160a(this);
        } catch (Throwable unused) {
        }
    }

    public static void setSecretDataToPush(String str) {
        try {
            ReflectHelper.invokeStaticMethod(ReflectHelper.importClass("com.mob.pushsdk.MobPush"), "addGuardMessage", str);
        } catch (Throwable th) {
            C2335e.m2675b().mo29769d(th);
        }
    }
}
