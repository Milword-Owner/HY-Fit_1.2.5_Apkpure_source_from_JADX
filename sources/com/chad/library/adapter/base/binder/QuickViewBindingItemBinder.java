package com.chad.library.adapter.base.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0006J%\u0010\u0007\u001a\u00028\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&¢\u0006\u0002\u0010\u000eJ\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u0011"}, mo21895d2 = {"Lcom/chad/library/adapter/base/binder/QuickViewBindingItemBinder;", "T", "VB", "Landroidx/viewbinding/ViewBinding;", "Lcom/chad/library/adapter/base/binder/BaseItemBinder;", "Lcom/chad/library/adapter/base/binder/QuickViewBindingItemBinder$BinderVBHolder;", "()V", "onCreateViewBinding", "layoutInflater", "Landroid/view/LayoutInflater;", "parent", "Landroid/view/ViewGroup;", "viewType", "", "(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;I)Landroidx/viewbinding/ViewBinding;", "onCreateViewHolder", "BinderVBHolder", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: QuickViewBindingItemBinder.kt */
public abstract class QuickViewBindingItemBinder<T, VB extends ViewBinding> extends BaseItemBinder<T, BinderVBHolder<VB>> {
    @NotNull
    public abstract VB onCreateViewBinding(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup, int i);

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0002¢\u0006\u0002\u0010\u0005R\u0013\u0010\u0004\u001a\u00028\u0002¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, mo21895d2 = {"Lcom/chad/library/adapter/base/binder/QuickViewBindingItemBinder$BinderVBHolder;", "VB", "Landroidx/viewbinding/ViewBinding;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "viewBinding", "(Landroidx/viewbinding/ViewBinding;)V", "getViewBinding", "()Landroidx/viewbinding/ViewBinding;", "Landroidx/viewbinding/ViewBinding;", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: QuickViewBindingItemBinder.kt */
    public static final class BinderVBHolder<VB extends ViewBinding> extends BaseViewHolder {
        @NotNull
        private final VB viewBinding;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public BinderVBHolder(@org.jetbrains.annotations.NotNull VB r3) {
            /*
                r2 = this;
                java.lang.String r0 = "viewBinding"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
                android.view.View r0 = r3.getRoot()
                java.lang.String r1 = "viewBinding.root"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
                r2.<init>(r0)
                r2.viewBinding = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.chad.library.adapter.base.binder.QuickViewBindingItemBinder.BinderVBHolder.<init>(androidx.viewbinding.ViewBinding):void");
        }

        @NotNull
        public final VB getViewBinding() {
            return this.viewBinding;
        }
    }

    @NotNull
    public BinderVBHolder<VB> onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        Intrinsics.checkExpressionValueIsNotNull(from, "LayoutInflater.from(parent.context)");
        return new BinderVBHolder<>(onCreateViewBinding(from, viewGroup, i));
    }
}
