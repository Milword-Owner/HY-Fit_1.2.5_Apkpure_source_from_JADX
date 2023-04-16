package p005cn.sharesdk.framework.utils;

import android.os.Handler;
import android.os.Message;
import com.mob.tools.MobHandlerThread;

/* renamed from: cn.sharesdk.framework.utils.e */
/* compiled from: SSDKHandlerThread */
public abstract class C0807e implements Handler.Callback {

    /* renamed from: a */
    protected final Handler f613a = MobHandlerThread.newHandler(this);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo10640a(Message message) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract void mo10642b(Message message);

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo10644c(Message message) {
    }

    /* renamed from: e */
    public void mo10741e() {
        mo10929a(0, 0, (Object) null);
    }

    /* renamed from: a */
    public void mo10929a(int i, int i2, Object obj) {
        Message message = new Message();
        message.what = -1;
        message.arg1 = i;
        message.arg2 = i2;
        message.obj = obj;
        this.f613a.sendMessage(message);
    }

    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == -2) {
            mo10644c(message);
            return false;
        } else if (i != -1) {
            mo10642b(message);
            return false;
        } else {
            mo10640a(message);
            return false;
        }
    }
}
