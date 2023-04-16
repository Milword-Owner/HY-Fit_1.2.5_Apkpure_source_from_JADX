package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.NotificationUtils;
import com.blankj.utilcode.util.Utils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MessengerUtils {
    private static final String KEY_STRING = "MESSENGER_UTILS";
    private static final int WHAT_SEND = 2;
    private static final int WHAT_SUBSCRIBE = 0;
    private static final int WHAT_UNSUBSCRIBE = 1;
    private static Map<String, Client> sClientMap = new HashMap();
    private static Client sLocalClient;
    /* access modifiers changed from: private */
    public static ConcurrentHashMap<String, MessageCallback> subscribers = new ConcurrentHashMap<>();

    public interface MessageCallback {
        void messageCall(Bundle bundle);
    }

    public static void register() {
        if (UtilsBridge.isMainProcess()) {
            if (UtilsBridge.isServiceRunning(ServerService.class.getName())) {
                Log.i("MessengerUtils", "Server service is running.");
            } else {
                startServiceCompat(new Intent(Utils.getApp(), ServerService.class));
            }
        } else if (sLocalClient == null) {
            Client client = new Client((String) null);
            if (client.bind()) {
                sLocalClient = client;
            } else {
                Log.e("MessengerUtils", "Bind service failed.");
            }
        } else {
            Log.i("MessengerUtils", "The client have been bind.");
        }
    }

    public static void unregister() {
        if (UtilsBridge.isMainProcess()) {
            if (!UtilsBridge.isServiceRunning(ServerService.class.getName())) {
                Log.i("MessengerUtils", "Server service isn't running.");
                return;
            } else {
                Utils.getApp().stopService(new Intent(Utils.getApp(), ServerService.class));
            }
        }
        Client client = sLocalClient;
        if (client != null) {
            client.unbind();
        }
    }

    public static void register(String str) {
        if (sClientMap.containsKey(str)) {
            Log.i("MessengerUtils", "register: client registered: " + str);
            return;
        }
        Client client = new Client(str);
        if (client.bind()) {
            sClientMap.put(str, client);
            return;
        }
        Log.e("MessengerUtils", "register: client bind failed: " + str);
    }

    public static void unregister(String str) {
        if (!sClientMap.containsKey(str)) {
            Log.i("MessengerUtils", "unregister: client didn't register: " + str);
            return;
        }
        Client client = sClientMap.get(str);
        sClientMap.remove(str);
        if (client != null) {
            client.unbind();
        }
    }

    public static void subscribe(@NonNull String str, @NonNull MessageCallback messageCallback) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (messageCallback != null) {
            subscribers.put(str, messageCallback);
        } else {
            throw new NullPointerException("Argument 'callback' of type MessageCallback (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void unsubscribe(@NonNull String str) {
        if (str != null) {
            subscribers.remove(str);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void post(@NonNull String str, @NonNull Bundle bundle) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (bundle != null) {
            bundle.putString(KEY_STRING, str);
            Client client = sLocalClient;
            if (client != null) {
                client.sendMsg2Server(bundle);
            } else {
                Intent intent = new Intent(Utils.getApp(), ServerService.class);
                intent.putExtras(bundle);
                startServiceCompat(intent);
            }
            for (Client sendMsg2Server : sClientMap.values()) {
                sendMsg2Server.sendMsg2Server(bundle);
            }
        } else {
            throw new NullPointerException("Argument 'data' of type Bundle (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    private static void startServiceCompat(Intent intent) {
        try {
            intent.setFlags(32);
            if (Build.VERSION.SDK_INT >= 26) {
                Utils.getApp().startForegroundService(intent);
            } else {
                Utils.getApp().startService(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Client {
        LinkedList<Bundle> mCached = new LinkedList<>();
        Messenger mClient = new Messenger(this.mReceiveServeMsgHandler);
        ServiceConnection mConn = new ServiceConnection() {
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d("MessengerUtils", "client service connected " + componentName);
                Client.this.mServer = new Messenger(iBinder);
                Message obtain = Message.obtain(Client.this.mReceiveServeMsgHandler, 0, UtilsBridge.getCurrentProcessName().hashCode(), 0);
                obtain.getData().setClassLoader(MessengerUtils.class.getClassLoader());
                obtain.replyTo = Client.this.mClient;
                try {
                    Client.this.mServer.send(obtain);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Client.this.sendCachedMsg2Server();
            }

            public void onServiceDisconnected(ComponentName componentName) {
                Log.w("MessengerUtils", "client service disconnected:" + componentName);
                Client client = Client.this;
                client.mServer = null;
                if (!client.bind()) {
                    Log.e("MessengerUtils", "client service rebind failed: " + componentName);
                }
            }
        };
        String mPkgName;
        @SuppressLint({"HandlerLeak"})
        Handler mReceiveServeMsgHandler = new Handler() {
            public void handleMessage(Message message) {
                MessageCallback messageCallback;
                Bundle data = message.getData();
                data.setClassLoader(MessengerUtils.class.getClassLoader());
                String string = data.getString(MessengerUtils.KEY_STRING);
                if (string != null && (messageCallback = (MessageCallback) MessengerUtils.subscribers.get(string)) != null) {
                    messageCallback.messageCall(data);
                }
            }
        };
        Messenger mServer;

        Client(String str) {
            this.mPkgName = str;
        }

        /* access modifiers changed from: package-private */
        public boolean bind() {
            if (TextUtils.isEmpty(this.mPkgName)) {
                return Utils.getApp().bindService(new Intent(Utils.getApp(), ServerService.class), this.mConn, 1);
            } else if (!UtilsBridge.isAppInstalled(this.mPkgName)) {
                Log.e("MessengerUtils", "bind: the app is not installed -> " + this.mPkgName);
                return false;
            } else if (UtilsBridge.isAppRunning(this.mPkgName)) {
                Intent intent = new Intent(this.mPkgName + ".messenger");
                intent.setPackage(this.mPkgName);
                return Utils.getApp().bindService(intent, this.mConn, 1);
            } else {
                Log.e("MessengerUtils", "bind: the app is not running -> " + this.mPkgName);
                return false;
            }
        }

        /* access modifiers changed from: package-private */
        public void unbind() {
            Message obtain = Message.obtain(this.mReceiveServeMsgHandler, 1, UtilsBridge.getCurrentProcessName().hashCode(), 0);
            obtain.replyTo = this.mClient;
            try {
                this.mServer.send(obtain);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                Utils.getApp().unbindService(this.mConn);
            } catch (Exception unused) {
            }
        }

        /* access modifiers changed from: package-private */
        public void sendMsg2Server(Bundle bundle) {
            if (this.mServer == null) {
                this.mCached.addFirst(bundle);
                Log.i("MessengerUtils", "save the bundle " + bundle);
                return;
            }
            sendCachedMsg2Server();
            if (!send2Server(bundle)) {
                this.mCached.addFirst(bundle);
            }
        }

        /* access modifiers changed from: private */
        public void sendCachedMsg2Server() {
            if (!this.mCached.isEmpty()) {
                for (int size = this.mCached.size() - 1; size >= 0; size--) {
                    if (send2Server(this.mCached.get(size))) {
                        this.mCached.remove(size);
                    }
                }
            }
        }

        private boolean send2Server(Bundle bundle) {
            Message obtain = Message.obtain(this.mReceiveServeMsgHandler, 2);
            bundle.setClassLoader(MessengerUtils.class.getClassLoader());
            obtain.setData(bundle);
            obtain.replyTo = this.mClient;
            try {
                this.mServer.send(obtain);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public static class ServerService extends Service {
        /* access modifiers changed from: private */
        public final ConcurrentHashMap<Integer, Messenger> mClientMap = new ConcurrentHashMap<>();
        @SuppressLint({"HandlerLeak"})
        private final Handler mReceiveClientMsgHandler = new Handler() {
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    ServerService.this.mClientMap.put(Integer.valueOf(message.arg1), message.replyTo);
                } else if (i == 1) {
                    ServerService.this.mClientMap.remove(Integer.valueOf(message.arg1));
                } else if (i != 2) {
                    super.handleMessage(message);
                } else {
                    ServerService.this.sendMsg2Client(message);
                    ServerService.this.consumeServerProcessCallback(message);
                }
            }
        };
        private final Messenger messenger = new Messenger(this.mReceiveClientMsgHandler);

        @Nullable
        public IBinder onBind(Intent intent) {
            return this.messenger.getBinder();
        }

        public int onStartCommand(Intent intent, int i, int i2) {
            Bundle extras;
            if (Build.VERSION.SDK_INT >= 26) {
                startForeground(1, UtilsBridge.getNotification(NotificationUtils.ChannelConfig.DEFAULT_CHANNEL_CONFIG, (Utils.Consumer<NotificationCompat.Builder>) null));
            }
            if (!(intent == null || (extras = intent.getExtras()) == null)) {
                Message obtain = Message.obtain(this.mReceiveClientMsgHandler, 2);
                obtain.replyTo = this.messenger;
                obtain.setData(extras);
                sendMsg2Client(obtain);
                consumeServerProcessCallback(obtain);
            }
            return 2;
        }

        /* access modifiers changed from: private */
        public void sendMsg2Client(Message message) {
            for (Messenger next : this.mClientMap.values()) {
                if (next != null) {
                    try {
                        next.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /* access modifiers changed from: private */
        public void consumeServerProcessCallback(Message message) {
            String string;
            MessageCallback messageCallback;
            Bundle data = message.getData();
            if (data != null && (string = data.getString(MessengerUtils.KEY_STRING)) != null && (messageCallback = (MessageCallback) MessengerUtils.subscribers.get(string)) != null) {
                messageCallback.messageCall(data);
            }
        }
    }
}
