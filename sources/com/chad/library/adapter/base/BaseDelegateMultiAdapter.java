package com.chad.library.adapter.base;

import android.view.ViewGroup;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004B\u0017\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0014J\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\tJ\u001d\u0010\u000e\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000bH\u0014¢\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\tR\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, mo21895d2 = {"Lcom/chad/library/adapter/base/BaseDelegateMultiAdapter;", "T", "VH", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "data", "", "(Ljava/util/List;)V", "mMultiTypeDelegate", "Lcom/chad/library/adapter/base/delegate/BaseMultiTypeDelegate;", "getDefItemViewType", "", "position", "getMultiTypeDelegate", "onCreateDefViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "(Landroid/view/ViewGroup;I)Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "setMultiTypeDelegate", "", "multiTypeDelegate", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: BaseDelegateMultiAdapter.kt */
public abstract class BaseDelegateMultiAdapter<T, VH extends BaseViewHolder> extends BaseQuickAdapter<T, VH> {
    private BaseMultiTypeDelegate<T> mMultiTypeDelegate;

    public BaseDelegateMultiAdapter() {
        this((List) null, 1, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BaseDelegateMultiAdapter(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }

    public BaseDelegateMultiAdapter(@Nullable List<T> list) {
        super(0, list);
    }

    public final void setMultiTypeDelegate(@NotNull BaseMultiTypeDelegate<T> baseMultiTypeDelegate) {
        Intrinsics.checkParameterIsNotNull(baseMultiTypeDelegate, "multiTypeDelegate");
        this.mMultiTypeDelegate = baseMultiTypeDelegate;
    }

    @Nullable
    public final BaseMultiTypeDelegate<T> getMultiTypeDelegate() {
        return this.mMultiTypeDelegate;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public VH onCreateDefViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        BaseMultiTypeDelegate multiTypeDelegate = getMultiTypeDelegate();
        if (multiTypeDelegate != null) {
            return createBaseViewHolder(viewGroup, multiTypeDelegate.getLayoutId(i));
        }
        throw new IllegalStateException("Please use setMultiTypeDelegate first!".toString());
    }

    /* access modifiers changed from: protected */
    public int getDefItemViewType(int i) {
        BaseMultiTypeDelegate multiTypeDelegate = getMultiTypeDelegate();
        if (multiTypeDelegate != null) {
            return multiTypeDelegate.getItemType(getData(), i);
        }
        throw new IllegalStateException("Please use setMultiTypeDelegate first!".toString());
    }
}
