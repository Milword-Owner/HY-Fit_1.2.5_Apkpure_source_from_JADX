package com.mob.wrappers;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.UIHandler;
import com.mob.ums.OperationCallback;
import com.mob.ums.UMSSDK;
import com.mob.ums.gui.UMSGUI;

public class UMSSDKWrapper extends SDKWrapper implements PublicMemberKeeper {
    private static int state;
    private static int stateGUI;

    public interface CallbackWrapper {
        void onError(Throwable th);

        void onSuccess(User user);
    }

    private static synchronized boolean isAvailable() {
        boolean z;
        synchronized (UMSSDKWrapper.class) {
            if (state == 0) {
                state = isAvailable("UMSSDK");
            }
            z = true;
            if (state != 1) {
                z = false;
            }
        }
        return z;
    }

    public static String getLoginUserToken() {
        if (isAvailable()) {
            return UMSSDK.getLoginUserToken();
        }
        return null;
    }

    public static String getLoginUserId() {
        if (isAvailable()) {
            return UMSSDK.getLoginUserId();
        }
        return null;
    }

    public static boolean hasLogin() {
        return !TextUtils.isEmpty(getLoginUserToken());
    }

    public static void logout() {
        if (isAvailable()) {
            UMSSDK.logout((OperationCallback) null);
        }
    }

    public static void getLoginUser(final CallbackWrapper callbackWrapper) {
        if (isAvailable()) {
            try {
                UMSSDK.getLoginUser(new OperationCallback<com.mob.ums.User>() {
                    public void onSuccess(com.mob.ums.User user) {
                        CallbackWrapper callbackWrapper = callbackWrapper;
                        if (callbackWrapper != null) {
                            callbackWrapper.onSuccess(new User(user));
                        }
                    }

                    public void onFailed(Throwable th) {
                        CallbackWrapper callbackWrapper = callbackWrapper;
                        if (callbackWrapper != null) {
                            callbackWrapper.onError(th);
                        }
                    }
                });
            } catch (Throwable th) {
                UIHandler.sendEmptyMessage(0, new Handler.Callback() {
                    public boolean handleMessage(Message message) {
                        CallbackWrapper callbackWrapper = callbackWrapper;
                        if (callbackWrapper == null) {
                            return false;
                        }
                        callbackWrapper.onError(th);
                        return false;
                    }
                });
            }
        } else {
            unAvailable(callbackWrapper, 1);
        }
    }

    private static boolean isAvailableGUI() {
        if (stateGUI == 0) {
            stateGUI = -1;
            try {
                new UMSGUI();
                stateGUI = 1;
            } catch (Throwable unused) {
            }
        }
        if (stateGUI == 1) {
            return true;
        }
        return false;
    }

    public static void showLogin(final CallbackWrapper callbackWrapper) {
        if (isAvailableGUI()) {
            UMSGUI.showLogin(new OperationCallback<com.mob.ums.User>() {
                public void onSuccess(com.mob.ums.User user) {
                    CallbackWrapper callbackWrapper = callbackWrapper;
                    if (callbackWrapper != null) {
                        callbackWrapper.onSuccess(new User(user));
                    }
                }

                public void onFailed(Throwable th) {
                    CallbackWrapper callbackWrapper = callbackWrapper;
                    if (callbackWrapper != null) {
                        callbackWrapper.onError(th);
                    }
                }
            });
        } else {
            unAvailable(callbackWrapper, 2);
        }
    }

    private static void unAvailable(final CallbackWrapper callbackWrapper, final int i) {
        UIHandler.sendEmptyMessage(0, new Handler.Callback() {
            public boolean handleMessage(Message message) {
                Throwable th;
                if (callbackWrapper == null) {
                    return false;
                }
                if (i == 2) {
                    th = new Throwable("UMSSDKGUI is not available");
                } else {
                    th = new Throwable("UMSSDK is not available");
                }
                callbackWrapper.onError(th);
                return false;
            }
        });
    }

    public static class User implements PublicMemberKeeper {
        private com.mob.ums.User user;

        private User(com.mob.ums.User user2) {
            this.user = user2;
        }

        public String getUserId() {
            com.mob.ums.User user2 = this.user;
            if (user2 == null) {
                return null;
            }
            return (String) user2.id.get();
        }

        public String getNickname() {
            com.mob.ums.User user2 = this.user;
            if (user2 == null) {
                return null;
            }
            return (String) user2.nickname.get();
        }

        public String getAvatar() {
            com.mob.ums.User user2 = this.user;
            if (user2 != null && !user2.avatar.isNull()) {
                return ((String[]) this.user.avatar.get())[0];
            }
            return null;
        }
    }
}
