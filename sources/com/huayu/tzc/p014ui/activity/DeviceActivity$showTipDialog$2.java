package com.huayu.tzc.p014ui.activity;

import android.view.View;
import com.baidu.mobstat.Config;
import com.huayu.tzc.customview.BaseAlterDialog;
import com.huayu.tzc.utils.NoDoubleClickListener;
import com.tencent.mmkv.MMKV;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0014¨\u0006\u0006"}, mo21895d2 = {"com/huayu/tzc/ui/activity/DeviceActivity$showTipDialog$2", "Lcom/huayu/tzc/utils/NoDoubleClickListener;", "noDoubleClick", "", "v", "Landroid/view/View;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.DeviceActivity$showTipDialog$2 */
/* compiled from: DeviceActivity.kt */
public final class DeviceActivity$showTipDialog$2 extends NoDoubleClickListener {
    final /* synthetic */ BaseAlterDialog $dialog;
    final /* synthetic */ DeviceActivity this$0;

    DeviceActivity$showTipDialog$2(DeviceActivity deviceActivity, BaseAlterDialog baseAlterDialog) {
        this.this$0 = deviceActivity;
        this.$dialog = baseAlterDialog;
    }

    /* access modifiers changed from: protected */
    public void noDoubleClick(@Nullable View view) {
        MMKV.defaultMMKV().encode(Config.TRACE_VISIT_FIRST, false);
        this.$dialog.dismiss();
        this.this$0.showStepDialog();
    }
}
