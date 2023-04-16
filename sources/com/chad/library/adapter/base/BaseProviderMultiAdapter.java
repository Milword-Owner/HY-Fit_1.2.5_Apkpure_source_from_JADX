package com.chad.library.adapter.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002B\u0017\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0016J\u0018\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0003H\u0014J\u0018\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\u001d\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u001aJ+\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00028\u00002\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0014¢\u0006\u0002\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u0014H\u0014J\u0018\u0010!\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\t2\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\u001e\u0010\"\u001a\u00020\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u001c2\u0006\u0010 \u001a\u00020\u0014H$J\u0018\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\u0010\u0010&\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0003H\u0016J\u0010\u0010'\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0003H\u0016R'\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t0\b8BX\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u0006("}, mo21895d2 = {"Lcom/chad/library/adapter/base/BaseProviderMultiAdapter;", "T", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "data", "", "(Ljava/util/List;)V", "mItemProviders", "Landroid/util/SparseArray;", "Lcom/chad/library/adapter/base/provider/BaseItemProvider;", "getMItemProviders", "()Landroid/util/SparseArray;", "mItemProviders$delegate", "Lkotlin/Lazy;", "addItemProvider", "", "provider", "bindChildClick", "viewHolder", "viewType", "", "bindClick", "bindViewClickListener", "convert", "holder", "item", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Ljava/lang/Object;)V", "payloads", "", "", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Ljava/lang/Object;Ljava/util/List;)V", "getDefItemViewType", "position", "getItemProvider", "getItemType", "onCreateDefViewHolder", "parent", "Landroid/view/ViewGroup;", "onViewAttachedToWindow", "onViewDetachedFromWindow", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: BaseProviderMultiAdapter.kt */
public abstract class BaseProviderMultiAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    private final Lazy mItemProviders$delegate;

    public BaseProviderMultiAdapter() {
        this((List) null, 1, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public final SparseArray<BaseItemProvider<T>> getMItemProviders() {
        return (SparseArray) this.mItemProviders$delegate.getValue();
    }

    /* access modifiers changed from: protected */
    public abstract int getItemType(@NotNull List<? extends T> list, int i);

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BaseProviderMultiAdapter(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }

    public BaseProviderMultiAdapter(@Nullable List<T> list) {
        super(0, list);
        this.mItemProviders$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, BaseProviderMultiAdapter$mItemProviders$2.INSTANCE);
    }

    public void addItemProvider(@NotNull BaseItemProvider<T> baseItemProvider) {
        Intrinsics.checkParameterIsNotNull(baseItemProvider, "provider");
        baseItemProvider.setAdapter$com_github_CymChad_brvah(this);
        getMItemProviders().put(baseItemProvider.getItemViewType(), baseItemProvider);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public BaseViewHolder onCreateDefViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        BaseItemProvider itemProvider = getItemProvider(i);
        if (itemProvider != null) {
            Context context = viewGroup.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "parent.context");
            itemProvider.setContext(context);
            BaseViewHolder onCreateViewHolder = itemProvider.onCreateViewHolder(viewGroup, i);
            itemProvider.onViewHolderCreated(onCreateViewHolder, i);
            return onCreateViewHolder;
        }
        throw new IllegalStateException(("ViewType: " + i + " no such provider found，please use addItemProvider() first!").toString());
    }

    /* access modifiers changed from: protected */
    public int getDefItemViewType(int i) {
        return getItemType(getData(), i);
    }

    /* access modifiers changed from: protected */
    public void convert(@NotNull BaseViewHolder baseViewHolder, T t) {
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "holder");
        BaseItemProvider itemProvider = getItemProvider(baseViewHolder.getItemViewType());
        if (itemProvider == null) {
            Intrinsics.throwNpe();
        }
        itemProvider.convert(baseViewHolder, t);
    }

    /* access modifiers changed from: protected */
    public void convert(@NotNull BaseViewHolder baseViewHolder, T t, @NotNull List<? extends Object> list) {
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "holder");
        Intrinsics.checkParameterIsNotNull(list, "payloads");
        BaseItemProvider itemProvider = getItemProvider(baseViewHolder.getItemViewType());
        if (itemProvider == null) {
            Intrinsics.throwNpe();
        }
        itemProvider.convert(baseViewHolder, t, list);
    }

    /* access modifiers changed from: protected */
    public void bindViewClickListener(@NotNull BaseViewHolder baseViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "viewHolder");
        super.bindViewClickListener(baseViewHolder, i);
        bindClick(baseViewHolder);
        bindChildClick(baseViewHolder, i);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public BaseItemProvider<T> getItemProvider(int i) {
        return (BaseItemProvider) getMItemProviders().get(i);
    }

    public void onViewAttachedToWindow(@NotNull BaseViewHolder baseViewHolder) {
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "holder");
        super.onViewAttachedToWindow(baseViewHolder);
        BaseItemProvider itemProvider = getItemProvider(baseViewHolder.getItemViewType());
        if (itemProvider != null) {
            itemProvider.onViewAttachedToWindow(baseViewHolder);
        }
    }

    public void onViewDetachedFromWindow(@NotNull BaseViewHolder baseViewHolder) {
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "holder");
        super.onViewDetachedFromWindow(baseViewHolder);
        BaseItemProvider itemProvider = getItemProvider(baseViewHolder.getItemViewType());
        if (itemProvider != null) {
            itemProvider.onViewDetachedFromWindow(baseViewHolder);
        }
    }

    /* access modifiers changed from: protected */
    public void bindClick(@NotNull BaseViewHolder baseViewHolder) {
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "viewHolder");
        if (getOnItemClickListener() == null) {
            baseViewHolder.itemView.setOnClickListener(new BaseProviderMultiAdapter$bindClick$1(this, baseViewHolder));
        }
        if (getOnItemLongClickListener() == null) {
            baseViewHolder.itemView.setOnLongClickListener(new BaseProviderMultiAdapter$bindClick$2(this, baseViewHolder));
        }
    }

    /* access modifiers changed from: protected */
    public void bindChildClick(@NotNull BaseViewHolder baseViewHolder, int i) {
        BaseItemProvider itemProvider;
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "viewHolder");
        if (getOnItemChildClickListener() == null) {
            BaseItemProvider itemProvider2 = getItemProvider(i);
            if (itemProvider2 != null) {
                for (Number intValue : itemProvider2.getChildClickViewIds()) {
                    View findViewById = baseViewHolder.itemView.findViewById(intValue.intValue());
                    if (findViewById != null) {
                        if (!findViewById.isClickable()) {
                            findViewById.setClickable(true);
                        }
                        findViewById.setOnClickListener(new C1277xb952c17c(this, baseViewHolder, itemProvider2));
                    }
                }
            } else {
                return;
            }
        }
        if (getOnItemChildLongClickListener() == null && (itemProvider = getItemProvider(i)) != null) {
            for (Number intValue2 : itemProvider.getChildLongClickViewIds()) {
                View findViewById2 = baseViewHolder.itemView.findViewById(intValue2.intValue());
                if (findViewById2 != null) {
                    if (!findViewById2.isLongClickable()) {
                        findViewById2.setLongClickable(true);
                    }
                    findViewById2.setOnLongClickListener(new C1278xb952c17d(this, baseViewHolder, itemProvider));
                }
            }
        }
    }
}
