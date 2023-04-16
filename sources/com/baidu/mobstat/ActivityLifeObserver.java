package com.baidu.mobstat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import java.util.LinkedHashSet;
import java.util.Set;

public class ActivityLifeObserver {

    /* renamed from: b */
    private static final ActivityLifeObserver f629b = new ActivityLifeObserver();

    /* renamed from: a */
    private boolean f630a;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public Set<IActivityLifeCallback> f631c = new LinkedHashSet();

    public interface IActivityLifeCallback {
        void onActivityCreated(Activity activity, Bundle bundle);

        void onActivityDestroyed(Activity activity);

        void onActivityPaused(Activity activity);

        void onActivityResumed(Activity activity);

        void onActivitySaveInstanceState(Activity activity, Bundle bundle);

        void onActivityStarted(Activity activity);

        void onActivityStopped(Activity activity);
    }

    public static ActivityLifeObserver instance() {
        return f629b;
    }

    public void addObserver(IActivityLifeCallback iActivityLifeCallback) {
        synchronized (this.f631c) {
            this.f631c.add(iActivityLifeCallback);
        }
    }

    public void clearObservers() {
        synchronized (this.f631c) {
            this.f631c.clear();
        }
    }

    public void removeObserver(IActivityLifeCallback iActivityLifeCallback) {
        synchronized (this.f631c) {
            this.f631c.remove(iActivityLifeCallback);
        }
    }

    public void registerActivityLifeCallback(Context context) {
        if (!this.f630a && Build.VERSION.SDK_INT >= 14) {
            doRegister(context);
            this.f630a = true;
        }
    }

    @TargetApi(14)
    public void doRegister(Context context) {
        try {
            ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                public void onActivityResumed(Activity activity) {
                    synchronized (ActivityLifeObserver.this.f631c) {
                        for (IActivityLifeCallback onActivityResumed : ActivityLifeObserver.this.f631c) {
                            onActivityResumed.onActivityResumed(activity);
                        }
                    }
                }

                public void onActivityPaused(Activity activity) {
                    synchronized (ActivityLifeObserver.this.f631c) {
                        for (IActivityLifeCallback onActivityPaused : ActivityLifeObserver.this.f631c) {
                            onActivityPaused.onActivityPaused(activity);
                        }
                    }
                }

                public void onActivityCreated(Activity activity, Bundle bundle) {
                    synchronized (ActivityLifeObserver.this.f631c) {
                        for (IActivityLifeCallback onActivityCreated : ActivityLifeObserver.this.f631c) {
                            onActivityCreated.onActivityCreated(activity, bundle);
                        }
                    }
                }

                public void onActivityStarted(Activity activity) {
                    synchronized (ActivityLifeObserver.this.f631c) {
                        for (IActivityLifeCallback onActivityStarted : ActivityLifeObserver.this.f631c) {
                            onActivityStarted.onActivityStarted(activity);
                        }
                    }
                }

                public void onActivityStopped(Activity activity) {
                    synchronized (ActivityLifeObserver.this.f631c) {
                        for (IActivityLifeCallback onActivityStopped : ActivityLifeObserver.this.f631c) {
                            onActivityStopped.onActivityStopped(activity);
                        }
                    }
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    synchronized (ActivityLifeObserver.this.f631c) {
                        for (IActivityLifeCallback onActivitySaveInstanceState : ActivityLifeObserver.this.f631c) {
                            onActivitySaveInstanceState.onActivitySaveInstanceState(activity, bundle);
                        }
                    }
                }

                public void onActivityDestroyed(Activity activity) {
                    synchronized (ActivityLifeObserver.this.f631c) {
                        for (IActivityLifeCallback onActivityDestroyed : ActivityLifeObserver.this.f631c) {
                            onActivityDestroyed.onActivityDestroyed(activity);
                        }
                    }
                }
            });
        } catch (Exception unused) {
            C0955bb.m1194c().mo11624a("registerActivityLifecycleCallbacks encounter exception");
        }
    }
}
