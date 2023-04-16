package com.huayu.tzc.p014ui.fragment;

import com.hjq.toast.ToastUtils;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import top.zibin.luban.OnCompressListener;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, mo21895d2 = {"com/huayu/tzc/ui/fragment/MsgFragment$compressImg$1", "Ltop/zibin/luban/OnCompressListener;", "onError", "", "e", "", "onStart", "onSuccess", "file", "Ljava/io/File;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.MsgFragment$compressImg$1 */
/* compiled from: MsgFragment.kt */
public final class MsgFragment$compressImg$1 implements OnCompressListener {
    final /* synthetic */ MsgFragment this$0;

    public void onStart() {
    }

    MsgFragment$compressImg$1(MsgFragment msgFragment) {
        this.this$0 = msgFragment;
    }

    public void onSuccess(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        this.this$0.uploadImg(file);
    }

    public void onError(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "e");
        ToastUtils.show((CharSequence) "Image upload failed");
    }
}
