package com.mob.wrappers;

import android.os.Handler;
import android.os.Message;
import com.mob.MobSDK;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.UIHandler;
import java.lang.reflect.Field;
import java.util.HashMap;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.PlatformActionListener;
import p005cn.sharesdk.framework.ShareSDK;
import p005cn.sharesdk.onekeyshare.OnekeyShare;

public class ShareSDKWrapper extends SDKWrapper implements PublicMemberKeeper {
    private static int state;

    public interface CallbackWrapper {
        void onCancel(String str, int i);

        void onComplete(String str, int i, HashMap<String, Object> hashMap);

        void onError(String str, int i, Throwable th);
    }

    private static synchronized boolean isAvailable() {
        boolean z;
        synchronized (ShareSDKWrapper.class) {
            if (state == 0) {
                state = isAvailable(ShareSDK.SDK_TAG);
            }
            z = true;
            if (state != 1) {
                z = false;
            }
        }
        return z;
    }

    public static String[] getPlatformList() {
        Platform[] platformList;
        if (!isAvailable() || (platformList = ShareSDK.getPlatformList()) == null) {
            return null;
        }
        String[] strArr = new String[platformList.length];
        for (int i = 0; i < platformList.length; i++) {
            strArr[i] = platformList[i].getName();
        }
        return strArr;
    }

    public static void login(final String str, final CallbackWrapper callbackWrapper) {
        if (isAvailable()) {
            Platform platform = ShareSDK.getPlatform(str);
            if (platform == null) {
                UIHandler.sendEmptyMessage(0, new Handler.Callback() {
                    public boolean handleMessage(Message message) {
                        callbackWrapper.onError(str, 8, new Throwable("platform " + str + " is not exist or disable"));
                        return false;
                    }
                });
                return;
            }
            platform.showUser((String) null);
            if (callbackWrapper != null) {
                platform.setPlatformActionListener(new PlatformActionListener() {
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        callbackWrapper.onComplete(platform.getName(), i, hashMap);
                    }

                    public void onError(Platform platform, int i, Throwable th) {
                        callbackWrapper.onError(platform.getName(), i, th);
                    }

                    public void onCancel(Platform platform, int i) {
                        callbackWrapper.onCancel(platform.getName(), i);
                    }
                });
            }
        } else if (callbackWrapper != null) {
            UIHandler.sendEmptyMessage(0, new Handler.Callback() {
                public boolean handleMessage(Message message) {
                    callbackWrapper.onError(str, 8, new Throwable("platform " + str + " is not exist or disable"));
                    return false;
                }
            });
        }
    }

    public static void logout(String str) {
        Platform platform;
        if (isAvailable() && (platform = ShareSDK.getPlatform(str)) != null) {
            platform.removeAccount(true);
        }
    }

    public static int getPlatformToId(String str) {
        if (isAvailable()) {
            return ShareSDK.platformNameToId(str);
        }
        return 0;
    }

    public static String getDbData(String str) {
        Platform platform;
        if (!isAvailable() || (platform = ShareSDK.getPlatform(str)) == null) {
            return null;
        }
        return platform.getDb().exportData();
    }

    public static String getToken(String str) {
        Platform platform;
        if (!isAvailable() || (platform = ShareSDK.getPlatform(str)) == null) {
            return null;
        }
        return platform.getDb().getToken();
    }

    public static String getUserID(String str) {
        Platform platform;
        if (!isAvailable() || (platform = ShareSDK.getPlatform(str)) == null) {
            return null;
        }
        return platform.getDb().getUserId();
    }

    public static String getUserName(String str) {
        Platform platform;
        if (!isAvailable() || (platform = ShareSDK.getPlatform(str)) == null) {
            return null;
        }
        return platform.getDb().getUserName();
    }

    public static boolean isLogin(String str) {
        Platform platform;
        if (!isAvailable() || (platform = ShareSDK.getPlatform(str)) == null) {
            return false;
        }
        return platform.isAuthValid();
    }

    public static void share(final String str, HashMap<String, Object> hashMap, final CallbackWrapper callbackWrapper) {
        if (isAvailable()) {
            Platform platform = ShareSDK.getPlatform(str);
            if (platform == null) {
                UIHandler.sendEmptyMessage(0, new Handler.Callback() {
                    public boolean handleMessage(Message message) {
                        callbackWrapper.onError(str, 9, new Throwable("platform " + str + " is not exist or disable"));
                        return false;
                    }
                });
                return;
            }
            Platform.ShareParams shareParams = new Platform.ShareParams(hashMap);
            if (callbackWrapper != null) {
                platform.setPlatformActionListener(new PlatformActionListener() {
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        callbackWrapper.onComplete(platform.getName(), i, hashMap);
                    }

                    public void onError(Platform platform, int i, Throwable th) {
                        callbackWrapper.onError(platform.getName(), i, th);
                    }

                    public void onCancel(Platform platform, int i) {
                        callbackWrapper.onCancel(platform.getName(), i);
                    }
                });
            }
            platform.share(shareParams);
        } else if (callbackWrapper != null) {
            UIHandler.sendEmptyMessage(0, new Handler.Callback() {
                public boolean handleMessage(Message message) {
                    callbackWrapper.onError(str, 9, new Throwable("platform " + str + " is not exist or disable"));
                    return false;
                }
            });
        }
    }

    private static boolean isOneKeyShareAvailable() {
        try {
            new OnekeyShare();
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void oneKeyShare(String str, HashMap<String, Object> hashMap, final CallbackWrapper callbackWrapper) {
        if (isOneKeyShareAvailable()) {
            OnekeyShare onekeyShare = new OnekeyShare();
            if (str != null) {
                onekeyShare.setPlatform(str);
            }
            if (callbackWrapper != null) {
                onekeyShare.setCallback(new PlatformActionListener() {
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        callbackWrapper.onComplete(platform.getName(), i, hashMap);
                    }

                    public void onError(Platform platform, int i, Throwable th) {
                        callbackWrapper.onError(platform.getName(), i, th);
                    }

                    public void onCancel(Platform platform, int i) {
                        callbackWrapper.onCancel(platform.getName(), i);
                    }
                });
            }
            Field[] declaredFields = onekeyShare.getClass().getDeclaredFields();
            int length = declaredFields.length;
            int i = 0;
            while (i < length) {
                Field field = declaredFields[i];
                if (HashMap.class.isAssignableFrom(field.getType())) {
                    try {
                        field.setAccessible(true);
                        ((HashMap) field.get(onekeyShare)).putAll(hashMap);
                        break;
                    } catch (Throwable unused) {
                        continue;
                    }
                } else {
                    i++;
                }
            }
            onekeyShare.show(MobSDK.getContext());
        }
    }

    public static void oneKeyShare(HashMap<String, Object> hashMap, CallbackWrapper callbackWrapper) {
        oneKeyShare((String) null, hashMap, callbackWrapper);
    }
}
