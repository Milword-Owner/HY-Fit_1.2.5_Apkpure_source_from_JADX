package com.chad.library.adapter.base;

import android.widget.LinearLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* compiled from: BaseQuickAdapter.kt */
final /* synthetic */ class BaseQuickAdapter$setFooterView$1 extends MutablePropertyReference0 {
    BaseQuickAdapter$setFooterView$1(BaseQuickAdapter baseQuickAdapter) {
        super(baseQuickAdapter);
    }

    public String getName() {
        return "mFooterLayout";
    }

    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(BaseQuickAdapter.class);
    }

    public String getSignature() {
        return "getMFooterLayout()Landroid/widget/LinearLayout;";
    }

    @Nullable
    public Object get() {
        return BaseQuickAdapter.access$getMFooterLayout$p((BaseQuickAdapter) this.receiver);
    }

    public void set(@Nullable Object obj) {
        ((BaseQuickAdapter) this.receiver).mFooterLayout = (LinearLayout) obj;
    }
}
