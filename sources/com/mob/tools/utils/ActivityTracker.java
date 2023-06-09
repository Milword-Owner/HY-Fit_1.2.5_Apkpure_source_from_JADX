package com.mob.tools.utils;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.mob.tools.MobLog;
import java.util.HashSet;

public class ActivityTracker {
    private static ActivityTracker instance;
    private HashSet<Tracker> trackers = new HashSet<>();

    private interface EachTracker {
        void each(Tracker tracker);
    }

    public interface Tracker {
        void onCreated(Activity activity, Bundle bundle);

        void onDestroyed(Activity activity);

        void onPaused(Activity activity);

        void onResumed(Activity activity);

        void onSaveInstanceState(Activity activity, Bundle bundle);

        void onStarted(Activity activity);

        void onStopped(Activity activity);
    }

    private ActivityTracker(Context context) {
        if (Build.VERSION.SDK_INT >= 14) {
            initLevel14(context);
        } else {
            init(context);
        }
    }

    public static synchronized ActivityTracker getInstance(Context context) {
        ActivityTracker activityTracker;
        synchronized (ActivityTracker.class) {
            if (instance == null) {
                instance = new ActivityTracker(context);
            }
            activityTracker = instance;
        }
        return activityTracker;
    }

    private void initLevel14(Context context) {
        try {
            ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    ActivityTracker.this.onCreated(activity, bundle);
                }

                public void onActivityStarted(Activity activity) {
                    ActivityTracker.this.onStarted(activity);
                }

                public void onActivityResumed(Activity activity) {
                    ActivityTracker.this.onResumed(activity);
                }

                public void onActivityPaused(Activity activity) {
                    ActivityTracker.this.onPaused(activity);
                }

                public void onActivityStopped(Activity activity) {
                    ActivityTracker.this.onStopped(activity);
                }

                public void onActivityDestroyed(Activity activity) {
                    ActivityTracker.this.onDestroyed(activity);
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    ActivityTracker.this.onSaveInstanceState(activity, bundle);
                }
            });
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    private void init(Context context) {
        try {
            DeviceHelper.getInstance(context);
            Object currentActivityThread = DeviceHelper.currentActivityThread();
            final Object instanceField = ReflectHelper.getInstanceField(currentActivityThread, "mInstrumentation");
            ReflectHelper.setInstanceField(currentActivityThread, "mInstrumentation", new Instrumentation() {
                public void callActivityOnCreate(Activity activity, Bundle bundle) {
                    Object obj = instanceField;
                    if (obj != null) {
                        try {
                            ReflectHelper.invokeInstanceMethod(obj, "callActivityOnCreate", activity, bundle);
                        } catch (Throwable unused) {
                        }
                    }
                    super.callActivityOnCreate(activity, bundle);
                    ActivityTracker.this.onCreated(activity, bundle);
                }

                public void callActivityOnStart(Activity activity) {
                    Object obj = instanceField;
                    if (obj != null) {
                        try {
                            ReflectHelper.invokeInstanceMethod(obj, "callActivityOnStart", activity);
                        } catch (Throwable unused) {
                        }
                    }
                    super.callActivityOnStart(activity);
                    ActivityTracker.this.onStarted(activity);
                }

                public void callActivityOnResume(Activity activity) {
                    Object obj = instanceField;
                    if (obj != null) {
                        try {
                            ReflectHelper.invokeInstanceMethod(obj, "callActivityOnResume", activity);
                        } catch (Throwable unused) {
                        }
                    }
                    super.callActivityOnResume(activity);
                    ActivityTracker.this.onResumed(activity);
                }

                public void callActivityOnPause(Activity activity) {
                    Object obj = instanceField;
                    if (obj != null) {
                        try {
                            ReflectHelper.invokeInstanceMethod(obj, "callActivityOnPause", activity);
                        } catch (Throwable unused) {
                        }
                    }
                    super.callActivityOnPause(activity);
                    ActivityTracker.this.onPaused(activity);
                }

                public void callActivityOnStop(Activity activity) {
                    Object obj = instanceField;
                    if (obj != null) {
                        try {
                            ReflectHelper.invokeInstanceMethod(obj, "callActivityOnStop", activity);
                        } catch (Throwable unused) {
                        }
                    }
                    super.callActivityOnStop(activity);
                    ActivityTracker.this.onStopped(activity);
                }

                public void callActivityOnDestroy(Activity activity) {
                    Object obj = instanceField;
                    if (obj != null) {
                        try {
                            ReflectHelper.invokeInstanceMethod(obj, "callActivityOnDestroy", activity);
                        } catch (Throwable unused) {
                        }
                    }
                    super.callActivityOnDestroy(activity);
                    ActivityTracker.this.onDestroyed(activity);
                }

                public void callActivityOnSaveInstanceState(Activity activity, Bundle bundle) {
                    Object obj = instanceField;
                    if (obj != null) {
                        try {
                            ReflectHelper.invokeInstanceMethod(obj, "callActivityOnSaveInstanceState", activity, bundle);
                        } catch (Throwable unused) {
                        }
                    }
                    super.callActivityOnSaveInstanceState(activity, bundle);
                    ActivityTracker.this.onSaveInstanceState(activity, bundle);
                }
            });
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    public void addTracker(Tracker tracker) {
        synchronized (this.trackers) {
            this.trackers.add(tracker);
        }
    }

    public void removeTracker(Tracker tracker) {
        synchronized (this.trackers) {
            this.trackers.remove(tracker);
        }
    }

    private void eachTracker(EachTracker eachTracker) {
        Tracker[] trackerArr;
        try {
            synchronized (this.trackers) {
                trackerArr = (Tracker[]) this.trackers.toArray(new Tracker[this.trackers.size()]);
            }
            for (Tracker tracker : trackerArr) {
                if (tracker != null) {
                    eachTracker.each(tracker);
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    /* access modifiers changed from: private */
    public void onCreated(final Activity activity, final Bundle bundle) {
        eachTracker(new EachTracker() {
            public void each(Tracker tracker) {
                tracker.onCreated(activity, bundle);
            }
        });
    }

    /* access modifiers changed from: private */
    public void onStarted(final Activity activity) {
        eachTracker(new EachTracker() {
            public void each(Tracker tracker) {
                tracker.onStarted(activity);
            }
        });
    }

    /* access modifiers changed from: private */
    public void onResumed(final Activity activity) {
        eachTracker(new EachTracker() {
            public void each(Tracker tracker) {
                tracker.onResumed(activity);
            }
        });
    }

    /* access modifiers changed from: private */
    public void onPaused(final Activity activity) {
        eachTracker(new EachTracker() {
            public void each(Tracker tracker) {
                tracker.onPaused(activity);
            }
        });
    }

    /* access modifiers changed from: private */
    public void onStopped(final Activity activity) {
        eachTracker(new EachTracker() {
            public void each(Tracker tracker) {
                tracker.onStopped(activity);
            }
        });
    }

    /* access modifiers changed from: private */
    public void onDestroyed(final Activity activity) {
        eachTracker(new EachTracker() {
            public void each(Tracker tracker) {
                tracker.onDestroyed(activity);
            }
        });
    }

    /* access modifiers changed from: private */
    public void onSaveInstanceState(final Activity activity, final Bundle bundle) {
        eachTracker(new EachTracker() {
            public void each(Tracker tracker) {
                tracker.onSaveInstanceState(activity, bundle);
            }
        });
    }
}
