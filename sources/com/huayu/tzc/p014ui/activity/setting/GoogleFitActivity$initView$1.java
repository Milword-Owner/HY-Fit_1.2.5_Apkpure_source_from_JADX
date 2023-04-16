package com.huayu.tzc.p014ui.activity.setting;

import android.widget.CompoundButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.tencent.mmkv.MMKV;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, mo21895d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "b", "", "onCheckedChanged"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.setting.GoogleFitActivity$initView$1 */
/* compiled from: GoogleFitActivity.kt */
final class GoogleFitActivity$initView$1 implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ GoogleFitActivity this$0;

    GoogleFitActivity$initView$1(GoogleFitActivity googleFitActivity) {
        this.this$0 = googleFitActivity;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (!z) {
            MMKV.defaultMMKV().encode("google", false);
        } else if (GoogleSignIn.getLastSignedInAccount(this.this$0) == null) {
            this.this$0.connectGoogleFit();
        } else {
            MMKV.defaultMMKV().encode("google", true);
        }
    }
}
