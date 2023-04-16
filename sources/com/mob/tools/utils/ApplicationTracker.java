package com.mob.tools.utils;

import android.os.Handler;
import android.os.Message;
import com.baidu.mobstat.Config;
import com.mob.tools.MobLog;
import java.util.HashSet;
import java.util.Iterator;

public class ApplicationTracker {
    /* access modifiers changed from: private */
    public static HashSet<Tracker> trackers = new HashSet<>();

    public static abstract class Tracker {
        /* access modifiers changed from: protected */
        public void onActivityConfigurationChanged(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onAttachAgent(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onBackgroundVisibleBehindChanged(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onBindApplication(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onBindService(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onCancelVisibleBehind(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onCleanUpContext(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onConfigurationChanged(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onCreateBackupAgent(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onCreateService(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onDestroyActivity(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onDestroyBackupAgent(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onDispatchPackageBroadcast(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onDumpActivity(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onDumpHeap(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onDumpProvider(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onDumpService(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onEnableJit(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onEnterAnimationComplete(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onExitApplication(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onGcWhenIdle(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onHideWindow(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onInstallProvider(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onLaunchActivity(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onLocalVoiceInteractionStarted(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onLowMemory(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onMultiWindowModeChanged(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onNewIntent(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onOnNewActivityOptions(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onPauseActivity(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onPauseActivityFinishing(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onPictureInPictureModeChanged(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onProfilerControl(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onReceiver(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onRelaunchActivity(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onRemoveProvider(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onRequestAssistContextExtras(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onResumeActivity(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onScheduleCrash(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onSendResult(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onServiceArgs(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onSetCoreSettings(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onShowWindow(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onSleeping(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onStartBinderTracking(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onStopActivityHide(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onStopActivityShow(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onStopBinderTrackingAndDump(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onStopService(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onSuicide(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onTranslucentConversionComplete(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onTrimMemory(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onUnbindService(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onUnstableProviderDied(Object obj, Message message) {
        }

        /* access modifiers changed from: protected */
        public void onUpdatePackageCompatibilityInfo(Object obj, Message message) {
        }
    }

    static {
        try {
            final Object currentActivityThread = DeviceHelper.currentActivityThread();
            Object instanceField = ReflectHelper.getInstanceField(currentActivityThread, "mH");
            String str = "mC" + "al" + "lb" + "ac" + Config.APP_KEY;
            final Handler.Callback callback = (Handler.Callback) ReflectHelper.getInstanceField(instanceField, str);
            ReflectHelper.setInstanceField(instanceField, str, new Handler.Callback() {
                public boolean handleMessage(Message message) {
                    Iterator it = ApplicationTracker.trackers.iterator();
                    while (it.hasNext()) {
                        Tracker tracker = (Tracker) it.next();
                        try {
                            switch (message.what) {
                                case 100:
                                    tracker.onLaunchActivity(currentActivityThread, message);
                                    break;
                                case 101:
                                    tracker.onPauseActivity(currentActivityThread, message);
                                    break;
                                case 102:
                                    tracker.onPauseActivityFinishing(currentActivityThread, message);
                                    break;
                                case 103:
                                    tracker.onStopActivityShow(currentActivityThread, message);
                                    break;
                                case 104:
                                    tracker.onStopActivityHide(currentActivityThread, message);
                                    break;
                                case 105:
                                    tracker.onShowWindow(currentActivityThread, message);
                                    break;
                                case 106:
                                    tracker.onHideWindow(currentActivityThread, message);
                                    break;
                                case 107:
                                    tracker.onResumeActivity(currentActivityThread, message);
                                    break;
                                case 108:
                                    tracker.onSendResult(currentActivityThread, message);
                                    break;
                                case 109:
                                    tracker.onDestroyActivity(currentActivityThread, message);
                                    break;
                                case 110:
                                    tracker.onBindApplication(currentActivityThread, message);
                                    break;
                                case 111:
                                    tracker.onExitApplication(currentActivityThread, message);
                                    break;
                                case 112:
                                    tracker.onNewIntent(currentActivityThread, message);
                                    break;
                                case 113:
                                    tracker.onReceiver(currentActivityThread, message);
                                    break;
                                case 114:
                                    tracker.onCreateService(currentActivityThread, message);
                                    break;
                                case 115:
                                    tracker.onServiceArgs(currentActivityThread, message);
                                    break;
                                case 116:
                                    tracker.onStopService(currentActivityThread, message);
                                    break;
                                case 118:
                                    tracker.onConfigurationChanged(currentActivityThread, message);
                                    break;
                                case 119:
                                    tracker.onCleanUpContext(currentActivityThread, message);
                                    break;
                                case 120:
                                    tracker.onGcWhenIdle(currentActivityThread, message);
                                    break;
                                case 121:
                                    tracker.onBindService(currentActivityThread, message);
                                    break;
                                case 122:
                                    tracker.onUnbindService(currentActivityThread, message);
                                    break;
                                case 123:
                                    tracker.onDumpService(currentActivityThread, message);
                                    break;
                                case 124:
                                    tracker.onLowMemory(currentActivityThread, message);
                                    break;
                                case 125:
                                    tracker.onActivityConfigurationChanged(currentActivityThread, message);
                                    break;
                                case 126:
                                    tracker.onRelaunchActivity(currentActivityThread, message);
                                    break;
                                case 127:
                                    tracker.onProfilerControl(currentActivityThread, message);
                                    break;
                                case 128:
                                    tracker.onCreateBackupAgent(currentActivityThread, message);
                                    break;
                                case 129:
                                    tracker.onDestroyBackupAgent(currentActivityThread, message);
                                    break;
                                case 130:
                                    tracker.onSuicide(currentActivityThread, message);
                                    break;
                                case 131:
                                    tracker.onRemoveProvider(currentActivityThread, message);
                                    break;
                                case 132:
                                    tracker.onEnableJit(currentActivityThread, message);
                                    break;
                                case 133:
                                    tracker.onDispatchPackageBroadcast(currentActivityThread, message);
                                    break;
                                case 134:
                                    tracker.onScheduleCrash(currentActivityThread, message);
                                    break;
                                case 135:
                                    tracker.onDumpHeap(currentActivityThread, message);
                                    break;
                                case 136:
                                    tracker.onDumpActivity(currentActivityThread, message);
                                    break;
                                case 137:
                                    tracker.onSleeping(currentActivityThread, message);
                                    break;
                                case 138:
                                    tracker.onSetCoreSettings(currentActivityThread, message);
                                    break;
                                case 139:
                                    tracker.onUpdatePackageCompatibilityInfo(currentActivityThread, message);
                                    break;
                                case 140:
                                    tracker.onTrimMemory(currentActivityThread, message);
                                    break;
                                case 141:
                                    tracker.onDumpProvider(currentActivityThread, message);
                                    break;
                                case 142:
                                    tracker.onUnstableProviderDied(currentActivityThread, message);
                                    break;
                                case 143:
                                    tracker.onRequestAssistContextExtras(currentActivityThread, message);
                                    break;
                                case 144:
                                    tracker.onTranslucentConversionComplete(currentActivityThread, message);
                                    break;
                                case 145:
                                    tracker.onInstallProvider(currentActivityThread, message);
                                    break;
                                case 146:
                                    tracker.onOnNewActivityOptions(currentActivityThread, message);
                                    break;
                                case 147:
                                    tracker.onCancelVisibleBehind(currentActivityThread, message);
                                    break;
                                case 148:
                                    tracker.onBackgroundVisibleBehindChanged(currentActivityThread, message);
                                    break;
                                case 149:
                                    tracker.onEnterAnimationComplete(currentActivityThread, message);
                                    break;
                                case 150:
                                    tracker.onStartBinderTracking(currentActivityThread, message);
                                    break;
                                case 151:
                                    tracker.onStopBinderTrackingAndDump(currentActivityThread, message);
                                    break;
                                case 152:
                                    tracker.onMultiWindowModeChanged(currentActivityThread, message);
                                    break;
                                case 153:
                                    tracker.onPictureInPictureModeChanged(currentActivityThread, message);
                                    break;
                                case 154:
                                    tracker.onLocalVoiceInteractionStarted(currentActivityThread, message);
                                    break;
                                case 155:
                                    tracker.onAttachAgent(currentActivityThread, message);
                                    break;
                            }
                        } catch (Throwable th) {
                            MobLog.getInstance().mo29787w(th);
                        }
                    }
                    Handler.Callback callback = callback;
                    return callback != null && callback.handleMessage(message);
                }
            });
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    public static void addTracker(Tracker tracker) {
        trackers.add(tracker);
    }

    public static void removeTracker(Tracker tracker) {
        trackers.remove(tracker);
    }
}
