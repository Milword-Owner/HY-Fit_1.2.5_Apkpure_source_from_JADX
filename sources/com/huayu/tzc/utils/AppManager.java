package com.huayu.tzc.utils;

import android.app.Activity;
import java.util.Iterator;
import java.util.Stack;

public class AppManager {
    private static Stack<Activity> activityStack = new Stack<>();

    public static void addActivity(Activity activity) {
        activityStack.push(activity);
    }

    public static void finish(Activity activity) {
        Iterator it = activityStack.iterator();
        while (it.hasNext()) {
            Activity activity2 = (Activity) it.next();
            if (activity != activity2) {
                activity2.finish();
            }
        }
    }

    public static Activity currentActivity() {
        return (Activity) activityStack.lastElement();
    }

    public static void finishCurrentActivity() {
        activityStack.pop().finish();
    }

    public static void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static void finishNameActivity(Class<?> cls) {
        Stack stack = new Stack();
        Iterator it = activityStack.iterator();
        while (it.hasNext()) {
            Activity activity = (Activity) it.next();
            if (activity.getClass().equals(cls)) {
                stack.add(activity);
                if (stack.size() > 2) {
                    finishActivity((Activity) stack.get(0));
                }
            }
        }
    }

    public static void finishAllActivity() {
        Iterator it = activityStack.iterator();
        while (it.hasNext()) {
            Activity activity = (Activity) it.next();
            if (activity != null) {
                activity.finish();
            }
        }
        activityStack.clear();
    }
}
