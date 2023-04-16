package com.huayu.tzc.p014ui.activity;

import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo21895d2 = {"<anonymous>", "", "run"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.ShareActivity$setData$1 */
/* compiled from: ShareActivity.kt */
final class ShareActivity$setData$1 implements Runnable {
    final /* synthetic */ ShareActivity this$0;

    ShareActivity$setData$1(ShareActivity shareActivity) {
        this.this$0 = shareActivity;
    }

    public final void run() {
        this.this$0.requestImgPermission();
    }
}
