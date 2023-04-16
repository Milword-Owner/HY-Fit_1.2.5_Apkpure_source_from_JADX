package com.huayu.tzc.p014ui.activity.setting;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.huayu.tzc.bean.LanguageBean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, mo21895d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "<anonymous parameter 1>", "Landroid/view/View;", "position", "", "onItemClick"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.setting.LanguageActivity$initAdapter$1 */
/* compiled from: LanguageActivity.kt */
final class LanguageActivity$initAdapter$1 implements OnItemClickListener {
    final /* synthetic */ LanguageActivity this$0;

    LanguageActivity$initAdapter$1(LanguageActivity languageActivity) {
        this.this$0 = languageActivity;
    }

    public final void onItemClick(@NotNull BaseQuickAdapter<?, ?> baseQuickAdapter, @NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkParameterIsNotNull(view, "<anonymous parameter 1>");
        for (LanguageBean checked : this.this$0.languageList) {
            checked.setChecked(false);
        }
        ((LanguageBean) this.this$0.languageList.get(i)).setChecked(true);
        LanguageActivity.access$getLanguageAdapter$p(this.this$0).notifyDataSetChanged();
        this.this$0.switchLanguage(i);
    }
}
