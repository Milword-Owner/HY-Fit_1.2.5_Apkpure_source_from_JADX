package com.huayu.tzc.p021im;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import java.util.Timer;
import java.util.TimerTask;

/* renamed from: com.huayu.tzc.im.JWebSocketClientService */
public class JWebSocketClientService extends Service {
    private JWebSocketClientBinder mBinder = new JWebSocketClientBinder();

    /* renamed from: com.huayu.tzc.im.JWebSocketClientService$JWebSocketClientBinder */
    public class JWebSocketClientBinder extends Binder {
        public JWebSocketClientBinder() {
        }

        public JWebSocketClientService getService() {
            return JWebSocketClientService.this;
        }
    }

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        new Timer().schedule(new TimerTask() {
            public void run() {
                Intent intent = new Intent();
                intent.setAction("com.huayu.tzc.im");
                JWebSocketClientService.this.sendBroadcast(intent);
            }
        }, 0, 30000);
        return 1;
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
