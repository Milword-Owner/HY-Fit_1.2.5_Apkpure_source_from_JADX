package com.huayu.tzc.p014ui.activity;

import com.huayu.tzc.customview.ruler.UnitLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo21895d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "onValue"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.TargetActivity$initGoglRuler$1 */
/* compiled from: TargetActivity.kt */
final class TargetActivity$initGoglRuler$1 implements UnitLayout.OnValueChangeListener {
    final /* synthetic */ TargetActivity this$0;

    TargetActivity$initGoglRuler$1(TargetActivity targetActivity) {
        this.this$0 = targetActivity;
    }

    public final void onValue(String str) {
        TargetActivity targetActivity = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(str, "it");
        targetActivity.goal = Float.parseFloat(str);
    }
}
