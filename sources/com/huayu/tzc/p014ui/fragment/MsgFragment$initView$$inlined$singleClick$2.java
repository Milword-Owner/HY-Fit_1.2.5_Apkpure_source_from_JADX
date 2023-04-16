package com.huayu.tzc.p014ui.fragment;

import android.text.Editable;
import android.view.View;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.TextView;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.utils.SingleClickKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u000e\u0010\u0004\u001a\n \u0005*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006¨\u0006\u0007"}, mo21895d2 = {"<anonymous>", "", "T", "Landroid/view/View;", "it", "kotlin.jvm.PlatformType", "onClick", "com/huayu/tzc/utils/SingleClickKt$singleClick$1"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.MsgFragment$initView$$inlined$singleClick$2 */
/* compiled from: singleClick.kt */
public final class MsgFragment$initView$$inlined$singleClick$2 implements View.OnClickListener {
    final /* synthetic */ View $this_singleClick;
    final /* synthetic */ long $time;
    final /* synthetic */ MsgFragment this$0;

    public MsgFragment$initView$$inlined$singleClick$2(View view, long j, MsgFragment msgFragment) {
        this.$this_singleClick = view;
        this.$time = j;
        this.this$0 = msgFragment;
    }

    public final void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - SingleClickKt.getLastClickTime(this.$this_singleClick) > this.$time || (this.$this_singleClick instanceof Checkable)) {
            SingleClickKt.setLastClickTime(this.$this_singleClick, currentTimeMillis);
            TextView textView = (TextView) this.$this_singleClick;
            EditText editText = (EditText) this.this$0._$_findCachedViewById(C2128R.C2131id.msg_edit);
            Intrinsics.checkExpressionValueIsNotNull(editText, "msg_edit");
            Editable text = editText.getText();
            Intrinsics.checkExpressionValueIsNotNull(text, "msg_edit.text");
            if (text.length() == 0) {
                ToastUtils.show((CharSequence) this.this$0.getString(C2128R.string.msg_not_empty));
                return;
            }
            MsgFragment msgFragment = this.this$0;
            EditText editText2 = (EditText) msgFragment._$_findCachedViewById(C2128R.C2131id.msg_edit);
            Intrinsics.checkExpressionValueIsNotNull(editText2, "msg_edit");
            msgFragment.sendMsg(editText2.getText().toString());
        }
    }
}
