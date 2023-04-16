package com.huayu.tzc;

import android.content.Intent;
import java.util.TimerTask;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, mo21895d2 = {"com/huayu/tzc/MainActivity$initTime$1", "Ljava/util/TimerTask;", "run", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: MainActivity.kt */
public final class MainActivity$initTime$1 extends TimerTask {
    final /* synthetic */ MainActivity this$0;

    MainActivity$initTime$1(MainActivity mainActivity) {
        this.this$0 = mainActivity;
    }

    public void run() {
        Intent intent = new Intent();
        intent.setAction("com.huayu.tzc.im");
        this.this$0.sendBroadcast(intent);
    }
}
