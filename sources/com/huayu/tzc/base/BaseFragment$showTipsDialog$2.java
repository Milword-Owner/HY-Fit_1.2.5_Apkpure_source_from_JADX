package com.huayu.tzc.base;

import android.content.DialogInterface;
import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0010\b\u0001\u0010\u0003*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u00042\u000e\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00062\u0006\u0010\b\u001a\u00020\tH\n¢\u0006\u0002\b\n"}, mo21895d2 = {"<anonymous>", "", "V", "T", "Lcom/huayu/tzc/base/BaseContract$BasePresenter;", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* compiled from: BaseFragment.kt */
final class BaseFragment$showTipsDialog$2 implements DialogInterface.OnClickListener {
    final /* synthetic */ BaseFragment this$0;

    BaseFragment$showTipsDialog$2(BaseFragment baseFragment) {
        this.this$0 = baseFragment;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.this$0.startAppSettings();
    }
}
