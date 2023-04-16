package com.huayu.tzc;

import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo21895d2 = {"<anonymous>", "", "run"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* compiled from: MainActivity.kt */
final class MainActivity$getUserInfo$1 implements Runnable {
    final /* synthetic */ MainActivity this$0;

    MainActivity$getUserInfo$1(MainActivity mainActivity) {
        this.this$0 = mainActivity;
    }

    public final void run() {
        this.this$0.uploadData();
    }
}
