package p005cn.sharesdk.framework;

import android.os.Handler;
import android.os.Message;
import com.mob.tools.utils.UIHandler;
import java.util.HashMap;

/* renamed from: cn.sharesdk.framework.ReflectablePlatformActionListener */
public class ReflectablePlatformActionListener implements PlatformActionListener {

    /* renamed from: a */
    private int f154a;

    /* renamed from: b */
    private Handler.Callback f155b;

    /* renamed from: c */
    private int f156c;

    /* renamed from: d */
    private Handler.Callback f157d;

    /* renamed from: e */
    private int f158e;

    /* renamed from: f */
    private Handler.Callback f159f;

    public void setOnCompleteCallback(int i, Handler.Callback callback) {
        this.f154a = i;
        this.f155b = callback;
    }

    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if (this.f155b != null) {
            Message message = new Message();
            message.what = this.f154a;
            message.obj = new Object[]{platform, Integer.valueOf(i), hashMap};
            UIHandler.sendMessage(message, this.f155b);
        }
    }

    public void setOnErrorCallback(int i, Handler.Callback callback) {
        this.f156c = i;
        this.f157d = callback;
    }

    public void onError(Platform platform, int i, Throwable th) {
        if (this.f157d != null) {
            Message message = new Message();
            message.what = this.f156c;
            message.obj = new Object[]{platform, Integer.valueOf(i), th};
            UIHandler.sendMessage(message, this.f157d);
        }
    }

    public void setOnCancelCallback(int i, Handler.Callback callback) {
        this.f158e = i;
        this.f159f = callback;
    }

    public void onCancel(Platform platform, int i) {
        if (this.f159f != null) {
            Message message = new Message();
            message.what = this.f158e;
            message.obj = new Object[]{platform, Integer.valueOf(i)};
            UIHandler.sendMessage(message, this.f159f);
        }
    }
}
