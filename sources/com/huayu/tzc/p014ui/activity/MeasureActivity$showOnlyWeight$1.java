package com.huayu.tzc.p014ui.activity;

import android.content.Intent;
import android.view.View;
import com.clj.fastble.data.BleDevice;
import com.facebook.share.internal.ShareConstants;
import com.huayu.tzc.bean.Measure;
import com.huayu.tzc.customview.BaseAlterDialog;
import com.huayu.tzc.utils.NoDoubleClickListener;
import com.huayu.tzc.utils.UnitUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0014¨\u0006\u0006"}, mo21895d2 = {"com/huayu/tzc/ui/activity/MeasureActivity$showOnlyWeight$1", "Lcom/huayu/tzc/utils/NoDoubleClickListener;", "noDoubleClick", "", "v", "Landroid/view/View;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.MeasureActivity$showOnlyWeight$1 */
/* compiled from: MeasureActivity.kt */
public final class MeasureActivity$showOnlyWeight$1 extends NoDoubleClickListener {
    final /* synthetic */ BaseAlterDialog $alterDialog;
    final /* synthetic */ BleDevice $bleDevice;
    final /* synthetic */ byte[] $weight;
    final /* synthetic */ MeasureActivity this$0;

    MeasureActivity$showOnlyWeight$1(MeasureActivity measureActivity, BleDevice bleDevice, byte[] bArr, BaseAlterDialog baseAlterDialog) {
        this.this$0 = measureActivity;
        this.$bleDevice = bleDevice;
        this.$weight = bArr;
        this.$alterDialog = baseAlterDialog;
    }

    /* access modifiers changed from: protected */
    public void noDoubleClick(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "v");
        Measure measure = new Measure();
        MeasureActivity measureActivity = this.this$0;
        byte[] scanRecord = this.$bleDevice.getScanRecord();
        Intrinsics.checkExpressionValueIsNotNull(scanRecord, "bleDevice.scanRecord");
        measure.setMsgType(measureActivity.getMsgType(scanRecord));
        measure.setScanRecord(this.$bleDevice.getScanRecord());
        measure.setHeight(MeasureActivity.access$getMember$p(this.this$0).getM_height());
        String mac = this.$bleDevice.getMac();
        Intrinsics.checkExpressionValueIsNotNull(mac, "bleDevice.mac");
        measure.setDevmac(mac);
        measure.computeWeight(this.$weight);
        float height = (float) MeasureActivity.access$getMember$p(this.this$0).getHeight();
        Double oneD = UnitUtils.oneD((double) (measure.getKgWeight() / (((height * 0.01f) * height) * 0.01f)));
        Intrinsics.checkExpressionValueIsNotNull(oneD, "UnitUtils.oneD((bleBean.…ght * 0.01f)).toDouble())");
        measure.setBmi(oneD.doubleValue());
        this.this$0.setResult(3, new Intent().putExtra(ShareConstants.WEB_DIALOG_PARAM_DATA, measure));
        this.this$0.finish();
        this.$alterDialog.dismiss();
    }
}
