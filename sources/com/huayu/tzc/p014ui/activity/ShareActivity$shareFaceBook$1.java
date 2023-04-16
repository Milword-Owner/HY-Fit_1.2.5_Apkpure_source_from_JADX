package com.huayu.tzc.p014ui.activity;

import android.util.Log;
import com.hjq.toast.ToastUtils;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.PlatformActionListener;
import p005cn.sharesdk.onekeyshare.OnekeyShare;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u00005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J0\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u00072\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\fH\u0016J \u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¨\u0006\u0012"}, mo21895d2 = {"com/huayu/tzc/ui/activity/ShareActivity$shareFaceBook$1", "Lcn/sharesdk/framework/PlatformActionListener;", "onCancel", "", "arg0", "Lcn/sharesdk/framework/Platform;", "arg1", "", "onComplete", "p0", "p1", "p2", "Ljava/util/HashMap;", "", "", "onError", "arg2", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.ShareActivity$shareFaceBook$1 */
/* compiled from: ShareActivity.kt */
public final class ShareActivity$shareFaceBook$1 implements PlatformActionListener {
    final /* synthetic */ ShareActivity this$0;

    ShareActivity$shareFaceBook$1(ShareActivity shareActivity) {
        this.this$0 = shareActivity;
    }

    public void onError(@NotNull Platform platform, int i, @NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(platform, "arg0");
        Intrinsics.checkParameterIsNotNull(th, "arg2");
        Log.d(OnekeyShare.SHARESDK_TAG, "onError ---->  分享失败" + th.toString());
        Log.d(OnekeyShare.SHARESDK_TAG, "ThreadID -----> : " + Thread.currentThread().getId());
        Log.e(OnekeyShare.SHARESDK_TAG, "onError ---->  分享失败" + th.toString());
        this.this$0.progressDissmiss();
        ToastUtils.show((CharSequence) "error");
        this.this$0.finish();
    }

    public void onComplete(@Nullable Platform platform, int i, @Nullable HashMap<String, Object> hashMap) {
        Log.d(OnekeyShare.SHARESDK_TAG, "onComplete ---->  分享cg");
        Log.e(OnekeyShare.SHARESDK_TAG, " onComplete ");
        this.this$0.progressDissmiss();
        ToastUtils.show((CharSequence) "ok");
        this.this$0.finish();
    }

    public void onCancel(@NotNull Platform platform, int i) {
        Intrinsics.checkParameterIsNotNull(platform, "arg0");
        Log.d(OnekeyShare.SHARESDK_TAG, "onCancel ---->  分享xq");
        Log.e(OnekeyShare.SHARESDK_TAG, " onCancel ");
        this.this$0.progressDissmiss();
        ToastUtils.show((CharSequence) "cancel");
        this.this$0.finish();
    }
}
