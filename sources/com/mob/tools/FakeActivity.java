package com.mob.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.UIHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class FakeActivity {
    private static Class<? extends Activity> shellClass;
    /* access modifiers changed from: protected */
    public Activity activity;
    private View contentView;
    private HashMap<String, Object> result;
    private FakeActivity resultReceiver;

    public void beforeStartActivityForResult(Intent intent, int i, Bundle bundle) {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onCreate() {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void onDestroy() {
    }

    public boolean onFinish() {
        return false;
    }

    public boolean onKeyEvent(int i, KeyEvent keyEvent) {
        return false;
    }

    public void onNewIntent(Intent intent) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onPause() {
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    public void onRestart() {
    }

    public void onResult(HashMap<String, Object> hashMap) {
    }

    public void onResume() {
    }

    /* access modifiers changed from: protected */
    public int onSetTheme(int i, boolean z) {
        return i;
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public static void setShell(Class<? extends Activity> cls) {
        shellClass = cls;
    }

    public static void registerExecutor(String str, Object obj) {
        Class<? extends Activity> cls = shellClass;
        if (cls != null) {
            try {
                Method method = cls.getMethod("registerExecutor", new Class[]{String.class, Object.class});
                method.setAccessible(true);
                method.invoke((Object) null, new Object[]{str, obj});
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
            }
        } else {
            MobUIShell.registerExecutor(str, obj);
        }
    }

    public void setActivity(Activity activity2) {
        this.activity = activity2;
    }

    /* access modifiers changed from: protected */
    public boolean disableScreenCapture() {
        if (this.activity == null || Build.VERSION.SDK_INT < 11) {
            return false;
        }
        this.activity.getWindow().setFlags(8192, 8192);
        return true;
    }

    public void setContentViewLayoutResName(String str) {
        int layoutRes;
        Activity activity2 = this.activity;
        if (activity2 != null && (layoutRes = ResHelper.getLayoutRes(activity2, str)) > 0) {
            this.activity.setContentView(layoutRes);
        }
    }

    public void setContentView(View view) {
        this.contentView = view;
    }

    public View getContentView() {
        return this.contentView;
    }

    public <T extends View> T findViewById(int i) {
        Activity activity2 = this.activity;
        if (activity2 == null) {
            return null;
        }
        return activity2.findViewById(i);
    }

    public <T extends View> T findViewByResName(View view, String str) {
        int idRes;
        Activity activity2 = this.activity;
        if (activity2 != null && (idRes = ResHelper.getIdRes(activity2, str)) > 0) {
            return view.findViewById(idRes);
        }
        return null;
    }

    public <T extends View> T findViewByResName(String str) {
        int idRes;
        Activity activity2 = this.activity;
        if (activity2 != null && (idRes = ResHelper.getIdRes(activity2, str)) > 0) {
            return findViewById(idRes);
        }
        return null;
    }

    public final void finish() {
        Activity activity2 = this.activity;
        if (activity2 != null) {
            activity2.finish();
        }
    }

    public void startActivity(Intent intent) {
        startActivityForResult(intent, -1);
    }

    public void startActivityForResult(Intent intent, int i) {
        Activity activity2 = this.activity;
        if (activity2 != null) {
            activity2.startActivityForResult(intent, i);
        }
    }

    public Context getContext() {
        return this.activity;
    }

    public void show(Context context, Intent intent) {
        showForResult(context, intent, (FakeActivity) null);
    }

    public void showForResult(final Context context, Intent intent, FakeActivity fakeActivity) {
        final Intent intent2;
        this.resultReceiver = fakeActivity;
        Class<? extends Activity> cls = shellClass;
        String str = null;
        if (cls != null) {
            intent2 = new Intent(context, cls);
            try {
                Method method = shellClass.getMethod("registerExecutor", new Class[]{Object.class});
                method.setAccessible(true);
                str = (String) method.invoke((Object) null, new Object[]{this});
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
            }
        } else {
            intent2 = new Intent(context, MobUIShell.class);
            str = MobUIShell.registerExecutor(this);
        }
        intent2.putExtra("launch_time", str);
        intent2.putExtra("executor_name", getClass().getName());
        if (intent != null) {
            intent2.putExtras(intent);
        }
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            showActivity(context, intent2);
        } else {
            UIHandler.sendEmptyMessage(0, new Handler.Callback() {
                public boolean handleMessage(Message message) {
                    FakeActivity.this.showActivity(context, intent2);
                    return false;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void showActivity(Context context, Intent intent) {
        if (!(context instanceof Activity)) {
            Activity topActivity = DeviceHelper.getInstance(context).getTopActivity();
            if (topActivity == null) {
                intent.addFlags(268435456);
            } else {
                context = topActivity;
            }
        }
        context.startActivity(intent);
    }

    public FakeActivity getParent() {
        return this.resultReceiver;
    }

    public final void setResult(HashMap<String, Object> hashMap) {
        this.result = hashMap;
    }

    public void sendResult() {
        FakeActivity fakeActivity = this.resultReceiver;
        if (fakeActivity != null) {
            fakeActivity.onResult(this.result);
        }
    }

    public void runOnUIThread(final Runnable runnable) {
        UIHandler.sendEmptyMessage(0, new Handler.Callback() {
            public boolean handleMessage(Message message) {
                runnable.run();
                return false;
            }
        });
    }

    public void runOnUIThread(final Runnable runnable, long j) {
        UIHandler.sendEmptyMessageDelayed(0, j, new Handler.Callback() {
            public boolean handleMessage(Message message) {
                runnable.run();
                return false;
            }
        });
    }

    public void setRequestedOrientation(int i) {
        if (this.activity != null) {
            if (Build.VERSION.SDK_INT < 26 || getContext().getApplicationInfo().targetSdkVersion < 27) {
                this.activity.setRequestedOrientation(i);
            }
        }
    }

    public void requestPortraitOrientation() {
        setRequestedOrientation(1);
    }

    public void requestLandscapeOrientation() {
        setRequestedOrientation(0);
    }

    public void requestSensorPortraitOrientation() {
        setRequestedOrientation(7);
    }

    public void requestSensorLandscapeOrientation() {
        setRequestedOrientation(6);
    }

    public int getOrientation() {
        return this.activity.getResources().getConfiguration().orientation;
    }

    public void requestFullScreen(boolean z) {
        Activity activity2 = this.activity;
        if (activity2 != null) {
            if (z) {
                activity2.getWindow().addFlags(1024);
                this.activity.getWindow().clearFlags(2048);
            } else {
                activity2.getWindow().addFlags(2048);
                this.activity.getWindow().clearFlags(1024);
            }
            this.activity.getWindow().getDecorView().requestLayout();
        }
    }

    public void requestPermissions(String[] strArr, int i) {
        if (this.activity != null && Build.VERSION.SDK_INT >= 23) {
            try {
                ReflectHelper.invokeInstanceMethod(this.activity, "requestPermissions", new Object[]{strArr, Integer.valueOf(i)}, new Class[]{String.class, Integer.TYPE});
            } catch (Throwable th) {
                MobLog.getInstance().mo29769d(th);
            }
        }
    }
}
