package com.huayu.tzc.p014ui.fragment;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.bean.ImagePreview;
import com.huayu.tzc.bean.MsgBean;
import com.previewlibrary.GPreviewBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012(\u0010\u0002\u001a$\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0005 \u0006*\r\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003¨\u0006\u00010\u0003¨\u0006\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\n¢\u0006\u0002\b\u000b"}, mo21895d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "kotlin.jvm.PlatformType", "view", "Landroid/view/View;", "position", "", "onItemChildClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.MsgFragment$initAdapter$1 */
/* compiled from: MsgFragment.kt */
final class MsgFragment$initAdapter$1 implements OnItemChildClickListener {
    final /* synthetic */ MsgFragment this$0;

    MsgFragment$initAdapter$1(MsgFragment msgFragment) {
        this.this$0 = msgFragment;
    }

    public final void onItemChildClick(@NotNull BaseQuickAdapter<Object, BaseViewHolder> baseQuickAdapter, @NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkParameterIsNotNull(view, ViewHierarchyConstants.VIEW_KEY);
        if (view.getId() == C2128R.C2131id.msg_img || view.getId() == C2128R.C2131id.msg_kf_img) {
            String msg = ((MsgBean) this.this$0.msgList.get(i)).getMsg();
            if (msg == null) {
                Intrinsics.throwNpe();
            }
            if (msg.length() > 0) {
                String access$getTAG$p = this.this$0.getTAG();
                Log.e(access$getTAG$p, "initAdapter: " + String.valueOf(((MsgBean) this.this$0.msgList.get(i)).getMsg()));
                FragmentActivity activity = this.this$0.getActivity();
                if (activity == null) {
                    Intrinsics.throwNpe();
                }
                GPreviewBuilder.from((Activity) activity).setSingleData(new ImagePreview(String.valueOf(((MsgBean) this.this$0.msgList.get(i)).getMsg()))).setCurrentIndex(0).setSingleFling(true).setDrag(true).setType(GPreviewBuilder.IndicatorType.Number).start();
            }
        }
    }
}
