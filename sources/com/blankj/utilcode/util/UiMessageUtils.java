package com.blankj.utilcode.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public final class UiMessageUtils implements Handler.Callback {
    private static final boolean DEBUG = UtilsBridge.isAppDebug();
    private static final String TAG = "UiMessageUtils";
    private final List<UiMessageCallback> mDefensiveCopyList;
    private final Handler mHandler;
    private final SparseArray<List<UiMessageCallback>> mListenersSpecific;
    private final List<UiMessageCallback> mListenersUniversal;
    private final UiMessage mMessage;

    public interface UiMessageCallback {
        void handleMessage(@NonNull UiMessage uiMessage);
    }

    public static UiMessageUtils getInstance() {
        return LazyHolder.INSTANCE;
    }

    private UiMessageUtils() {
        this.mHandler = new Handler(Looper.getMainLooper(), this);
        this.mMessage = new UiMessage((Message) null);
        this.mListenersSpecific = new SparseArray<>();
        this.mListenersUniversal = new ArrayList();
        this.mDefensiveCopyList = new ArrayList();
    }

    public final void send(int i) {
        this.mHandler.sendEmptyMessage(i);
    }

    public final void send(int i, @NonNull Object obj) {
        if (obj != null) {
            Handler handler = this.mHandler;
            handler.sendMessage(handler.obtainMessage(i, obj));
            return;
        }
        throw new NullPointerException("Argument 'obj' of type Object (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void addListener(int i, @NonNull UiMessageCallback uiMessageCallback) {
        if (uiMessageCallback != null) {
            synchronized (this.mListenersSpecific) {
                List list = this.mListenersSpecific.get(i);
                if (list == null) {
                    list = new ArrayList();
                    this.mListenersSpecific.put(i, list);
                }
                if (!list.contains(uiMessageCallback)) {
                    list.add(uiMessageCallback);
                }
            }
            return;
        }
        throw new NullPointerException("Argument 'listener' of type UiMessageCallback (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void addListener(@NonNull UiMessageCallback uiMessageCallback) {
        if (uiMessageCallback != null) {
            synchronized (this.mListenersUniversal) {
                if (!this.mListenersUniversal.contains(uiMessageCallback)) {
                    this.mListenersUniversal.add(uiMessageCallback);
                } else if (DEBUG) {
                    Log.w(TAG, "Listener is already added. " + uiMessageCallback.toString());
                }
            }
            return;
        }
        throw new NullPointerException("Argument 'listener' of type UiMessageCallback (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void removeListener(@NonNull UiMessageCallback uiMessageCallback) {
        if (uiMessageCallback != null) {
            synchronized (this.mListenersUniversal) {
                if (DEBUG && !this.mListenersUniversal.contains(uiMessageCallback)) {
                    Log.w(TAG, "Trying to remove a listener that is not registered. " + uiMessageCallback.toString());
                }
                this.mListenersUniversal.remove(uiMessageCallback);
            }
            return;
        }
        throw new NullPointerException("Argument 'listener' of type UiMessageCallback (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void removeListeners(int i) {
        List list;
        if (DEBUG && ((list = this.mListenersSpecific.get(i)) == null || list.size() == 0)) {
            Log.w(TAG, "Trying to remove specific listeners that are not registered. ID " + i);
        }
        synchronized (this.mListenersSpecific) {
            this.mListenersSpecific.delete(i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0066, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeListener(int r5, @androidx.annotation.NonNull com.blankj.utilcode.util.UiMessageUtils.UiMessageCallback r6) {
        /*
            r4 = this;
            if (r6 == 0) goto L_0x006a
            android.util.SparseArray<java.util.List<com.blankj.utilcode.util.UiMessageUtils$UiMessageCallback>> r0 = r4.mListenersSpecific
            monitor-enter(r0)
            android.util.SparseArray<java.util.List<com.blankj.utilcode.util.UiMessageUtils$UiMessageCallback>> r1 = r4.mListenersSpecific     // Catch:{ all -> 0x0067 }
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x0067 }
            java.util.List r1 = (java.util.List) r1     // Catch:{ all -> 0x0067 }
            if (r1 == 0) goto L_0x0043
            boolean r2 = r1.isEmpty()     // Catch:{ all -> 0x0067 }
            if (r2 != 0) goto L_0x0043
            boolean r2 = DEBUG     // Catch:{ all -> 0x0067 }
            if (r2 == 0) goto L_0x003f
            boolean r2 = r1.contains(r6)     // Catch:{ all -> 0x0067 }
            if (r2 != 0) goto L_0x003f
            java.lang.String r1 = "UiMessageUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0067 }
            r2.<init>()     // Catch:{ all -> 0x0067 }
            java.lang.String r3 = "Trying to remove specific listener that is not registered. ID "
            r2.append(r3)     // Catch:{ all -> 0x0067 }
            r2.append(r5)     // Catch:{ all -> 0x0067 }
            java.lang.String r5 = ", "
            r2.append(r5)     // Catch:{ all -> 0x0067 }
            r2.append(r6)     // Catch:{ all -> 0x0067 }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x0067 }
            android.util.Log.w(r1, r5)     // Catch:{ all -> 0x0067 }
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            return
        L_0x003f:
            r1.remove(r6)     // Catch:{ all -> 0x0067 }
            goto L_0x0065
        L_0x0043:
            boolean r1 = DEBUG     // Catch:{ all -> 0x0067 }
            if (r1 == 0) goto L_0x0065
            java.lang.String r1 = "UiMessageUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0067 }
            r2.<init>()     // Catch:{ all -> 0x0067 }
            java.lang.String r3 = "Trying to remove specific listener that is not registered. ID "
            r2.append(r3)     // Catch:{ all -> 0x0067 }
            r2.append(r5)     // Catch:{ all -> 0x0067 }
            java.lang.String r5 = ", "
            r2.append(r5)     // Catch:{ all -> 0x0067 }
            r2.append(r6)     // Catch:{ all -> 0x0067 }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x0067 }
            android.util.Log.w(r1, r5)     // Catch:{ all -> 0x0067 }
        L_0x0065:
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            return
        L_0x0067:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            throw r5
        L_0x006a:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.String r6 = "Argument 'listener' of type UiMessageCallback (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.UiMessageUtils.removeListener(int, com.blankj.utilcode.util.UiMessageUtils$UiMessageCallback):void");
    }

    public boolean handleMessage(Message message) {
        this.mMessage.setMessage(message);
        if (DEBUG) {
            logMessageHandling(this.mMessage);
        }
        synchronized (this.mListenersSpecific) {
            List list = this.mListenersSpecific.get(message.what);
            if (list != null) {
                if (list.size() == 0) {
                    this.mListenersSpecific.remove(message.what);
                } else {
                    this.mDefensiveCopyList.addAll(list);
                    for (UiMessageCallback handleMessage : this.mDefensiveCopyList) {
                        handleMessage.handleMessage(this.mMessage);
                    }
                    this.mDefensiveCopyList.clear();
                }
            }
        }
        synchronized (this.mListenersUniversal) {
            if (this.mListenersUniversal.size() > 0) {
                this.mDefensiveCopyList.addAll(this.mListenersUniversal);
                for (UiMessageCallback handleMessage2 : this.mDefensiveCopyList) {
                    handleMessage2.handleMessage(this.mMessage);
                }
                this.mDefensiveCopyList.clear();
            }
        }
        this.mMessage.setMessage((Message) null);
        return true;
    }

    private void logMessageHandling(@NonNull UiMessage uiMessage) {
        if (uiMessage != null) {
            List list = this.mListenersSpecific.get(uiMessage.getId());
            if ((list == null || list.size() == 0) && this.mListenersUniversal.size() == 0) {
                Log.w(TAG, "Delivering FAILED for message ID " + uiMessage.getId() + ". No listeners. " + uiMessage.toString());
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Delivering message ID ");
            sb.append(uiMessage.getId());
            sb.append(", Specific listeners: ");
            if (list == null || list.size() == 0) {
                sb.append(0);
            } else {
                sb.append(list.size());
                sb.append(" [");
                for (int i = 0; i < list.size(); i++) {
                    sb.append(((UiMessageCallback) list.get(i)).getClass().getSimpleName());
                    if (i < list.size() - 1) {
                        sb.append(",");
                    }
                }
                sb.append("]");
            }
            sb.append(", Universal listeners: ");
            synchronized (this.mListenersUniversal) {
                if (this.mListenersUniversal.size() == 0) {
                    sb.append(0);
                } else {
                    sb.append(this.mListenersUniversal.size());
                    sb.append(" [");
                    for (int i2 = 0; i2 < this.mListenersUniversal.size(); i2++) {
                        sb.append(this.mListenersUniversal.get(i2).getClass().getSimpleName());
                        if (i2 < this.mListenersUniversal.size() - 1) {
                            sb.append(",");
                        }
                    }
                    sb.append("], Message: ");
                }
            }
            sb.append(uiMessage.toString());
            Log.v(TAG, sb.toString());
            return;
        }
        throw new NullPointerException("Argument 'msg' of type UiMessage (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static final class UiMessage {
        private Message mMessage;

        private UiMessage(Message message) {
            this.mMessage = message;
        }

        /* access modifiers changed from: private */
        public void setMessage(Message message) {
            this.mMessage = message;
        }

        public int getId() {
            return this.mMessage.what;
        }

        public Object getObject() {
            return this.mMessage.obj;
        }

        public String toString() {
            return "{ id=" + getId() + ", obj=" + getObject() + " }";
        }
    }

    private static final class LazyHolder {
        /* access modifiers changed from: private */
        public static final UiMessageUtils INSTANCE = new UiMessageUtils();

        private LazyHolder() {
        }
    }
}
