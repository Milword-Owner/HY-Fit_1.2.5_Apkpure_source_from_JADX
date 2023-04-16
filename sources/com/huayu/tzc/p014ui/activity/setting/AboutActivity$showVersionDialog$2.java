package com.huayu.tzc.p014ui.activity.setting;

import android.view.View;
import com.huayu.tzc.customview.BaseAlterDialog;
import com.huayu.tzc.utils.NoDoubleClickListener;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0014¨\u0006\u0006"}, mo21895d2 = {"com/huayu/tzc/ui/activity/setting/AboutActivity$showVersionDialog$2", "Lcom/huayu/tzc/utils/NoDoubleClickListener;", "noDoubleClick", "", "v", "Landroid/view/View;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.setting.AboutActivity$showVersionDialog$2 */
/* compiled from: AboutActivity.kt */
public final class AboutActivity$showVersionDialog$2 extends NoDoubleClickListener {
    final /* synthetic */ BaseAlterDialog $baseAlterDialog;

    AboutActivity$showVersionDialog$2(BaseAlterDialog baseAlterDialog) {
        this.$baseAlterDialog = baseAlterDialog;
    }

    /* access modifiers changed from: protected */
    public void noDoubleClick(@Nullable View view) {
        this.$baseAlterDialog.dismiss();
    }
}
