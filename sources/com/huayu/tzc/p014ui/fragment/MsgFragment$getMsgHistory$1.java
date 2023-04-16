package com.huayu.tzc.p014ui.fragment;

import com.hjq.toast.ToastUtils;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo21895d2 = {"<anonymous>", "", "it", "Lcom/scwang/smart/refresh/layout/api/RefreshLayout;", "onRefresh"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.MsgFragment$getMsgHistory$1 */
/* compiled from: MsgFragment.kt */
final class MsgFragment$getMsgHistory$1 implements OnRefreshListener {
    public static final MsgFragment$getMsgHistory$1 INSTANCE = new MsgFragment$getMsgHistory$1();

    MsgFragment$getMsgHistory$1() {
    }

    public final void onRefresh(@NotNull RefreshLayout refreshLayout) {
        Intrinsics.checkParameterIsNotNull(refreshLayout, "it");
        ToastUtils.show((CharSequence) "No More Data !");
        refreshLayout.finishRefresh();
    }
}
