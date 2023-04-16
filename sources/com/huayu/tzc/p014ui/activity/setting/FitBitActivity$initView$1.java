package com.huayu.tzc.p014ui.activity.setting;

import android.content.Intent;
import android.os.Parcelable;
import android.widget.CompoundButton;
import android.widget.Switch;
import com.huayu.tzc.C2128R;
import com.tencent.mmkv.MMKV;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, mo21895d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "b", "", "onCheckedChanged"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.setting.FitBitActivity$initView$1 */
/* compiled from: FitBitActivity.kt */
final class FitBitActivity$initView$1 implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ FitBitActivity this$0;

    FitBitActivity$initView$1(FitBitActivity fitBitActivity) {
        this.this$0 = fitBitActivity;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (!z) {
            Switch switchR = (Switch) this.this$0._$_findCachedViewById(C2128R.C2131id.fitSwitch);
            Intrinsics.checkExpressionValueIsNotNull(switchR, "fitSwitch");
            switchR.setChecked(false);
            FitBitActivity.access$getFitBit$p(this.this$0).setConnectFitBit(false);
            MMKV.defaultMMKV().encode("fitbit", (Parcelable) FitBitActivity.access$getFitBit$p(this.this$0));
            return;
        }
        if (!(FitBitActivity.access$getFitBit$p(this.this$0).getFitToken().length() == 0)) {
            if (!(FitBitActivity.access$getFitBit$p(this.this$0).getFitId().length() == 0)) {
                Switch switchR2 = (Switch) this.this$0._$_findCachedViewById(C2128R.C2131id.fitSwitch);
                Intrinsics.checkExpressionValueIsNotNull(switchR2, "fitSwitch");
                switchR2.setChecked(true);
                FitBitActivity.access$getFitBit$p(this.this$0).setConnectFitBit(true);
                MMKV.defaultMMKV().encode("fitbit", (Parcelable) FitBitActivity.access$getFitBit$p(this.this$0));
                return;
            }
        }
        Switch switchR3 = (Switch) this.this$0._$_findCachedViewById(C2128R.C2131id.fitSwitch);
        Intrinsics.checkExpressionValueIsNotNull(switchR3, "fitSwitch");
        switchR3.setChecked(false);
        FitBitActivity fitBitActivity = this.this$0;
        fitBitActivity.startActivityForResult(new Intent(fitBitActivity, FitWebActivity.class), 818);
    }
}
