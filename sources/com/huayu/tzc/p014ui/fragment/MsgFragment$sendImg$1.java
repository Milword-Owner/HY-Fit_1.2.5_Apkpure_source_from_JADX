package com.huayu.tzc.p014ui.fragment;

import android.text.TextUtils;
import com.huayu.tzc.presenter.MsgPresenter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.FormBody;
import okhttp3.RequestBody;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo21895d2 = {"<anonymous>", "", "run"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.MsgFragment$sendImg$1 */
/* compiled from: MsgFragment.kt */
final class MsgFragment$sendImg$1 implements Runnable {
    final /* synthetic */ String $imgUrl;
    final /* synthetic */ MsgFragment this$0;

    MsgFragment$sendImg$1(MsgFragment msgFragment, String str) {
        this.this$0 = msgFragment;
        this.$imgUrl = str;
    }

    public final void run() {
        this.this$0.progressDissmiss();
        if (!TextUtils.isEmpty(this.$imgUrl)) {
            FormBody.Builder add = new FormBody.Builder().add("app_type", String.valueOf(2)).add("content", this.$imgUrl).add("flag", String.valueOf(true)).add("msgtype", String.valueOf(2)).add("receive", "1-kf");
            FormBody build = add.add("sender", "0-" + MsgFragment.access$getUser$p(this.this$0).getEmail()).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "FormBody.Builder()\n     …                 .build()");
            RequestBody requestBody = build;
            MsgPresenter access$getMPresenter$p = MsgFragment.access$getMPresenter$p(this.this$0);
            if (access$getMPresenter$p != null) {
                access$getMPresenter$p.sendMsg(requestBody);
            }
        }
    }
}
