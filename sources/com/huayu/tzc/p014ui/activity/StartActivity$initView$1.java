package com.huayu.tzc.p014ui.activity;

import android.content.Intent;
import com.huayu.tzc.MainActivity;
import com.tencent.mmkv.MMKV;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo21895d2 = {"<anonymous>", "", "run"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.StartActivity$initView$1 */
/* compiled from: StartActivity.kt */
final class StartActivity$initView$1 implements Runnable {
    final /* synthetic */ StartActivity this$0;

    StartActivity$initView$1(StartActivity startActivity) {
        this.this$0 = startActivity;
    }

    public final void run() {
        String decodeString = MMKV.defaultMMKV().decodeString("token", "");
        Intrinsics.checkExpressionValueIsNotNull(decodeString, "MMKV.defaultMMKV().decodeString(\"token\",\"\")");
        if (decodeString.length() > 0) {
            StartActivity startActivity = this.this$0;
            startActivity.startActivity(new Intent(startActivity, MainActivity.class));
        } else {
            StartActivity startActivity2 = this.this$0;
            startActivity2.startActivity(new Intent(startActivity2, GuideActivity.class));
        }
        this.this$0.finish();
    }
}
