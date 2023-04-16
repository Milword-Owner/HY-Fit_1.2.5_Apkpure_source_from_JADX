package com.mob.tools;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.mob.tools.log.NLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class MobUIShell extends Activity {
    private static HashMap<String, FakeActivity> executors = new HashMap<>();
    private FakeActivity executor;

    static {
        MobLog.getInstance().mo29768d("===============================", new Object[0]);
        String replace = "2020-12-17".replace("-0", "-").replace("-", ".");
        NLog instance = MobLog.getInstance();
        instance.mo29768d("MobTools " + replace, new Object[0]);
        MobLog.getInstance().mo29768d("===============================", new Object[0]);
    }

    public static Uri toMobUIShellUri(String str, HashMap<String, Object> hashMap) {
        ArrayList arrayList = new ArrayList();
        for (String next : hashMap.keySet()) {
            arrayList.add(new KVPair(next, String.valueOf(hashMap.get(next))));
        }
        return Uri.parse("mobui://" + str + "?" + ResHelper.encodeUrl((ArrayList<KVPair<String>>) arrayList));
    }

    protected static String registerExecutor(Object obj) {
        return registerExecutor(String.valueOf(System.currentTimeMillis()), obj);
    }

    protected static String registerExecutor(String str, Object obj) {
        executors.put(str, (FakeActivity) obj);
        return str;
    }

    public final void setTheme(int i) {
        if (initExecutor()) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            int i2 = 0;
            while (i2 < stackTrace.length) {
                if (!stackTrace[i2].toString().startsWith("java.lang.Thread.getStackTrace") || (i2 = i2 + 2) >= stackTrace.length) {
                    i2++;
                } else {
                    int onSetTheme = this.executor.onSetTheme(i, stackTrace[i2].toString().startsWith("android.app.ActivityThread.performLaunchActivity"));
                    if (onSetTheme > 0) {
                        super.setTheme(onSetTheme);
                        return;
                    }
                    return;
                }
            }
        }
        super.setTheme(i);
    }

    private boolean initExecutor() {
        if (this.executor == null) {
            Intent intent = getIntent();
            Uri data = intent.getData();
            if (data != null && "mobui".equals(data.getScheme())) {
                this.executor = activityForName(data.getHost());
                if (this.executor != null) {
                    NLog instance = MobLog.getInstance();
                    instance.mo29775i("MobUIShell found executor: " + this.executor.getClass(), new Object[0]);
                    this.executor.setActivity(this);
                    return true;
                }
            }
            try {
                String stringExtra = intent.getStringExtra("launch_time");
                String stringExtra2 = intent.getStringExtra("executor_name");
                this.executor = executors.remove(stringExtra);
                if (this.executor == null) {
                    this.executor = executors.remove(intent.getScheme());
                    if (this.executor == null) {
                        this.executor = getDefault();
                        if (this.executor == null) {
                            NLog instance2 = MobLog.getInstance();
                            instance2.mo29787w((Throwable) new RuntimeException("Executor lost! launchTime = " + stringExtra + ", executorName: " + stringExtra2));
                            return false;
                        }
                    }
                }
                NLog instance3 = MobLog.getInstance();
                instance3.mo29775i("MobUIShell found executor: " + this.executor.getClass(), new Object[0]);
                this.executor.setActivity(this);
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
                return false;
            }
        }
        return true;
    }

    private FakeActivity activityForName(String str) {
        Object newInstance;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (str.startsWith(".")) {
                str = getPackageName() + str;
            }
            String importClass = ReflectHelper.importClass(str);
            if (TextUtils.isEmpty(importClass) || (newInstance = ReflectHelper.newInstance(importClass, new Object[0])) == null || !(newInstance instanceof FakeActivity)) {
                return null;
            }
            return (FakeActivity) newInstance;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        if (initExecutor()) {
            NLog instance = MobLog.getInstance();
            instance.mo29768d(this.executor.getClass().getSimpleName() + " onCreate", new Object[0]);
            if (Build.VERSION.SDK_INT == 26 && isTranslucentOrFloating()) {
                fixOrientation();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                this.executor.activity.getWindow().addFlags(Integer.MIN_VALUE);
                this.executor.activity.getWindow().setStatusBarColor(0);
            }
            super.onCreate(bundle);
            this.executor.onCreate();
            return;
        }
        super.onCreate(bundle);
        finish();
    }

    public FakeActivity getDefault() {
        String str;
        try {
            str = getPackageManager().getActivityInfo(getComponentName(), 128).metaData.getString("defaultActivity");
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            str = null;
        }
        return activityForName(str);
    }

    public void setContentView(int i) {
        setContentView(LayoutInflater.from(this).inflate(i, (ViewGroup) null));
    }

    public void setContentView(View view) {
        if (view != null) {
            super.setContentView(view);
            FakeActivity fakeActivity = this.executor;
            if (fakeActivity != null) {
                fakeActivity.setContentView(view);
            }
        }
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        if (view != null) {
            if (layoutParams == null) {
                super.setContentView(view);
            } else {
                super.setContentView(view, layoutParams);
            }
            FakeActivity fakeActivity = this.executor;
            if (fakeActivity != null) {
                fakeActivity.setContentView(view);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        FakeActivity fakeActivity = this.executor;
        if (fakeActivity == null) {
            super.onNewIntent(intent);
        } else {
            fakeActivity.onNewIntent(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        if (this.executor != null) {
            NLog instance = MobLog.getInstance();
            instance.mo29768d(this.executor.getClass().getSimpleName() + " onStart", new Object[0]);
            this.executor.onStart();
        }
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        if (this.executor != null) {
            NLog instance = MobLog.getInstance();
            instance.mo29768d(this.executor.getClass().getSimpleName() + " onResume", new Object[0]);
            this.executor.onResume();
        }
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        if (this.executor != null) {
            NLog instance = MobLog.getInstance();
            instance.mo29768d(this.executor.getClass().getSimpleName() + " onPause", new Object[0]);
            this.executor.onPause();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        if (this.executor != null) {
            NLog instance = MobLog.getInstance();
            instance.mo29768d(this.executor.getClass().getSimpleName() + " onStop", new Object[0]);
            this.executor.onStop();
        }
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        if (this.executor != null) {
            NLog instance = MobLog.getInstance();
            instance.mo29768d(this.executor.getClass().getSimpleName() + " onRestart", new Object[0]);
            this.executor.onRestart();
        }
        super.onRestart();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        FakeActivity fakeActivity = this.executor;
        if (fakeActivity != null) {
            fakeActivity.sendResult();
            NLog instance = MobLog.getInstance();
            instance.mo29768d(this.executor.getClass().getSimpleName() + " onDestroy", new Object[0]);
            this.executor.onDestroy();
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        FakeActivity fakeActivity = this.executor;
        if (fakeActivity != null) {
            fakeActivity.onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        try {
            if (this.executor != null ? this.executor.onKeyEvent(i, keyEvent) : false) {
                return true;
            }
            return super.onKeyDown(i, keyEvent);
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return false;
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        try {
            if (this.executor != null ? this.executor.onKeyEvent(i, keyEvent) : false) {
                return true;
            }
            return super.onKeyUp(i, keyEvent);
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return false;
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        FakeActivity fakeActivity = this.executor;
        if (fakeActivity != null) {
            fakeActivity.onConfigurationChanged(configuration);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        FakeActivity fakeActivity = this.executor;
        if (fakeActivity != null) {
            fakeActivity.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    public void finish() {
        FakeActivity fakeActivity = this.executor;
        if (fakeActivity == null || !fakeActivity.onFinish()) {
            super.finish();
        }
    }

    public void setRequestedOrientation(int i) {
        if (Build.VERSION.SDK_INT != 26 || !isTranslucentOrFloating()) {
            super.setRequestedOrientation(i);
        }
    }

    public Object getExecutor() {
        return this.executor;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        boolean onCreateOptionsMenu = super.onCreateOptionsMenu(menu);
        FakeActivity fakeActivity = this.executor;
        return fakeActivity != null ? fakeActivity.onCreateOptionsMenu(menu) : onCreateOptionsMenu;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        boolean onOptionsItemSelected = super.onOptionsItemSelected(menuItem);
        FakeActivity fakeActivity = this.executor;
        return fakeActivity != null ? fakeActivity.onOptionsItemSelected(menuItem) : onOptionsItemSelected;
    }

    public void startActivityForResult(Intent intent, int i) {
        FakeActivity fakeActivity = this.executor;
        if (fakeActivity != null) {
            fakeActivity.beforeStartActivityForResult(intent, i, (Bundle) null);
        }
        super.startActivityForResult(intent, i);
    }

    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        FakeActivity fakeActivity = this.executor;
        if (fakeActivity != null) {
            fakeActivity.beforeStartActivityForResult(intent, i, bundle);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            super.startActivityForResult(intent, i, bundle);
        } else {
            super.startActivityForResult(intent, i);
        }
    }

    private boolean fixOrientation() {
        if (Build.VERSION.SDK_INT > 27) {
            return false;
        }
        try {
            Field declaredField = Activity.class.getDeclaredField("mActivityInfo");
            declaredField.setAccessible(true);
            ((ActivityInfo) declaredField.get(this)).screenOrientation = -1;
            declaredField.setAccessible(false);
            return true;
        } catch (Exception e) {
            MobLog.getInstance().mo29788w(e, "Fix orientation for 8.0 encountered exception", new Object[0]);
            return false;
        }
    }

    private boolean isTranslucentOrFloating() {
        boolean z = false;
        if (Build.VERSION.SDK_INT > 27) {
            return false;
        }
        try {
            TypedArray obtainStyledAttributes = this.executor.activity.obtainStyledAttributes((int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get((Object) null));
            Method method = ActivityInfo.class.getMethod("isTranslucentOrFloating", new Class[]{TypedArray.class});
            method.setAccessible(true);
            boolean booleanValue = ((Boolean) method.invoke((Object) null, new Object[]{obtainStyledAttributes})).booleanValue();
            try {
                method.setAccessible(false);
                return booleanValue;
            } catch (Exception e) {
                Exception exc = e;
                z = booleanValue;
                e = exc;
            }
        } catch (Exception e2) {
            e = e2;
            MobLog.getInstance().mo29787w((Throwable) e);
            return z;
        }
    }
}
