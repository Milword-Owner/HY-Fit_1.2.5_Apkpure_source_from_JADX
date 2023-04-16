package com.mob.tools;

import android.os.Handler;
import android.os.Message;

public abstract class SSDKHandlerThread implements Handler.Callback {
    private static final int MSG_START = -1;
    private static final int MSG_STOP = -2;
    protected final Handler handler = MobHandlerThread.newHandler("s", (Handler.Callback) this);

    /* access modifiers changed from: protected */
    public abstract void onMessage(Message message);

    /* access modifiers changed from: protected */
    public void onStart(Message message) {
    }

    /* access modifiers changed from: protected */
    public void onStop(Message message) {
    }

    public void startThread() {
        startThread(0, 0, (Object) null);
    }

    public void startThread(int i, int i2, Object obj) {
        Message message = new Message();
        message.what = -1;
        message.arg1 = i;
        message.arg2 = i2;
        message.obj = obj;
        this.handler.sendMessage(message);
    }

    public void stopThread() {
        stopThread(0, 0, (Object) null);
    }

    public void stopThread(int i, int i2, Object obj) {
        Message message = new Message();
        message.what = -2;
        message.arg1 = i;
        message.arg2 = i2;
        message.obj = obj;
        this.handler.sendMessage(message);
    }

    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == -2) {
            onStop(message);
            return false;
        } else if (i != -1) {
            onMessage(message);
            return false;
        } else {
            onStart(message);
            return false;
        }
    }
}
