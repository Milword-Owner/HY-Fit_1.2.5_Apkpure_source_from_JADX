package com.chad.library.adapter.base;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.DiffUtil;
import com.chad.library.adapter.base.binder.BaseItemBinder;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0016\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001-B\u0017\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J9\u0010\u0011\u001a\u00020\u0000\"\n\b\u0000\u0010\u0012\u0018\u0001*\u00020\u00022\u0010\u0010\u0013\u001a\f\u0012\u0004\u0012\u0002H\u0012\u0012\u0002\b\u00030\u000e2\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u0002H\u0012\u0018\u00010\nH\bJF\u0010\u0011\u001a\u00020\u0000\"\b\b\u0000\u0010\u0012*\u00020\u00022\u000e\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00120\t2\u0010\u0010\u0013\u001a\f\u0012\u0004\u0012\u0002H\u0012\u0012\u0002\b\u00030\u000e2\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u0002H\u0012\u0018\u00010\nH\u0007J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0010H\u0014J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0003H\u0014J\u0018\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0010H\u0014J\u0018\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u0002H\u0014J&\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00022\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00020 H\u0014J\u0014\u0010!\u001a\u00020\u00102\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\tH\u0004J\u0010\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u0010H\u0014J\u001c\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u000e2\u0006\u0010\u0019\u001a\u00020\u0010H\u0016J\u001e\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e2\u0006\u0010\u0019\u001a\u00020\u0010H\u0016J\u0018\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020(2\u0006\u0010\u0019\u001a\u00020\u0010H\u0014J\u0010\u0010)\u001a\u00020*2\u0006\u0010\u001d\u001a\u00020\u0003H\u0016J\u0010\u0010+\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0003H\u0016J\u0010\u0010,\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0003H\u0016RB\u0010\u0007\u001a6\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\n0\bj\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\n`\u000bX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u0002\u0012\u0002\b\u00030\u000e0\rX\u0004¢\u0006\u0002\n\u0000R2\u0010\u000f\u001a&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0004\u0012\u00020\u00100\bj\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0004\u0012\u00020\u0010`\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006."}, mo21895d2 = {"Lcom/chad/library/adapter/base/BaseBinderAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "list", "", "(Ljava/util/List;)V", "classDiffMap", "Ljava/util/HashMap;", "Ljava/lang/Class;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lkotlin/collections/HashMap;", "mBinderArray", "Landroid/util/SparseArray;", "Lcom/chad/library/adapter/base/binder/BaseItemBinder;", "mTypeMap", "", "addItemBinder", "T", "baseItemBinder", "callback", "clazz", "bindChildClick", "", "viewHolder", "viewType", "bindClick", "bindViewClickListener", "convert", "holder", "item", "payloads", "", "findViewType", "getDefItemViewType", "position", "getItemBinder", "getItemBinderOrNull", "onCreateDefViewHolder", "parent", "Landroid/view/ViewGroup;", "onFailedToRecycleView", "", "onViewAttachedToWindow", "onViewDetachedFromWindow", "ItemCallback", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: BaseBinderAdapter.kt */
public class BaseBinderAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    /* access modifiers changed from: private */
    public final HashMap<Class<?>, DiffUtil.ItemCallback<Object>> classDiffMap;
    private final SparseArray<BaseItemBinder<Object, ?>> mBinderArray;
    private final HashMap<Class<?>, Integer> mTypeMap;

    public BaseBinderAdapter() {
        this((List) null, 1, (DefaultConstructorMarker) null);
    }

    @NotNull
    @JvmOverloads
    public final <T> BaseBinderAdapter addItemBinder(@NotNull Class<? extends T> cls, @NotNull BaseItemBinder<T, ?> baseItemBinder) {
        return addItemBinder$default(this, cls, baseItemBinder, (DiffUtil.ItemCallback) null, 4, (Object) null);
    }

    public BaseBinderAdapter(@Nullable List<Object> list) {
        super(0, list);
        this.classDiffMap = new HashMap<>();
        this.mTypeMap = new HashMap<>();
        this.mBinderArray = new SparseArray<>();
        setDiffCallback(new ItemCallback());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BaseBinderAdapter(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }

    public static /* synthetic */ BaseBinderAdapter addItemBinder$default(BaseBinderAdapter baseBinderAdapter, Class cls, BaseItemBinder baseItemBinder, DiffUtil.ItemCallback itemCallback, int i, Object obj) {
        if (obj == null) {
            if ((i & 4) != 0) {
                itemCallback = null;
            }
            return baseBinderAdapter.addItemBinder(cls, baseItemBinder, itemCallback);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addItemBinder");
    }

    @NotNull
    @JvmOverloads
    public final <T> BaseBinderAdapter addItemBinder(@NotNull Class<? extends T> cls, @NotNull BaseItemBinder<T, ?> baseItemBinder, @Nullable DiffUtil.ItemCallback<T> itemCallback) {
        Intrinsics.checkParameterIsNotNull(cls, "clazz");
        Intrinsics.checkParameterIsNotNull(baseItemBinder, "baseItemBinder");
        int size = this.mTypeMap.size() + 1;
        this.mTypeMap.put(cls, Integer.valueOf(size));
        this.mBinderArray.append(size, baseItemBinder);
        baseItemBinder.set_adapter$com_github_CymChad_brvah(this);
        if (itemCallback != null) {
            Map map = this.classDiffMap;
            if (itemCallback != null) {
                map.put(cls, itemCallback);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type androidx.recyclerview.widget.DiffUtil.ItemCallback<kotlin.Any>");
            }
        }
        return this;
    }

    public static /* synthetic */ BaseBinderAdapter addItemBinder$default(BaseBinderAdapter baseBinderAdapter, BaseItemBinder baseItemBinder, DiffUtil.ItemCallback itemCallback, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                itemCallback = null;
            }
            Intrinsics.checkParameterIsNotNull(baseItemBinder, "baseItemBinder");
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            baseBinderAdapter.addItemBinder(Object.class, baseItemBinder, itemCallback);
            return baseBinderAdapter;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addItemBinder");
    }

    @NotNull
    public final /* synthetic */ <T> BaseBinderAdapter addItemBinder(@NotNull BaseItemBinder<T, ?> baseItemBinder, @Nullable DiffUtil.ItemCallback<T> itemCallback) {
        Intrinsics.checkParameterIsNotNull(baseItemBinder, "baseItemBinder");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        addItemBinder(Object.class, baseItemBinder, itemCallback);
        return this;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public BaseViewHolder onCreateDefViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        BaseItemBinder<Object, BaseViewHolder> itemBinder = getItemBinder(i);
        itemBinder.set_context$com_github_CymChad_brvah(getContext());
        return itemBinder.onCreateViewHolder(viewGroup, i);
    }

    /* access modifiers changed from: protected */
    public void convert(@NotNull BaseViewHolder baseViewHolder, @NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "holder");
        Intrinsics.checkParameterIsNotNull(obj, "item");
        getItemBinder(baseViewHolder.getItemViewType()).convert(baseViewHolder, obj);
    }

    /* access modifiers changed from: protected */
    public void convert(@NotNull BaseViewHolder baseViewHolder, @NotNull Object obj, @NotNull List<? extends Object> list) {
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "holder");
        Intrinsics.checkParameterIsNotNull(obj, "item");
        Intrinsics.checkParameterIsNotNull(list, "payloads");
        getItemBinder(baseViewHolder.getItemViewType()).convert(baseViewHolder, obj, list);
    }

    @NotNull
    public BaseItemBinder<Object, BaseViewHolder> getItemBinder(int i) {
        BaseItemBinder<Object, BaseViewHolder> baseItemBinder = this.mBinderArray.get(i);
        if (baseItemBinder != null) {
            return baseItemBinder;
        }
        throw new IllegalStateException(("getItemBinder: viewType '" + i + "' no such Binder found，please use addItemBinder() first!").toString());
    }

    @Nullable
    public BaseItemBinder<Object, BaseViewHolder> getItemBinderOrNull(int i) {
        BaseItemBinder<Object, BaseViewHolder> baseItemBinder = this.mBinderArray.get(i);
        if (!(baseItemBinder instanceof BaseItemBinder)) {
            return null;
        }
        return baseItemBinder;
    }

    /* access modifiers changed from: protected */
    public int getDefItemViewType(int i) {
        return findViewType(getData().get(i).getClass());
    }

    /* access modifiers changed from: protected */
    public void bindViewClickListener(@NotNull BaseViewHolder baseViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "viewHolder");
        super.bindViewClickListener(baseViewHolder, i);
        bindClick(baseViewHolder);
        bindChildClick(baseViewHolder, i);
    }

    public void onViewAttachedToWindow(@NotNull BaseViewHolder baseViewHolder) {
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "holder");
        super.onViewAttachedToWindow(baseViewHolder);
        BaseItemBinder<Object, BaseViewHolder> itemBinderOrNull = getItemBinderOrNull(baseViewHolder.getItemViewType());
        if (itemBinderOrNull != null) {
            itemBinderOrNull.onViewAttachedToWindow(baseViewHolder);
        }
    }

    public void onViewDetachedFromWindow(@NotNull BaseViewHolder baseViewHolder) {
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "holder");
        super.onViewDetachedFromWindow(baseViewHolder);
        BaseItemBinder<Object, BaseViewHolder> itemBinderOrNull = getItemBinderOrNull(baseViewHolder.getItemViewType());
        if (itemBinderOrNull != null) {
            itemBinderOrNull.onViewDetachedFromWindow(baseViewHolder);
        }
    }

    public boolean onFailedToRecycleView(@NotNull BaseViewHolder baseViewHolder) {
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "holder");
        BaseItemBinder<Object, BaseViewHolder> itemBinderOrNull = getItemBinderOrNull(baseViewHolder.getItemViewType());
        if (itemBinderOrNull != null) {
            return itemBinderOrNull.onFailedToRecycleView(baseViewHolder);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final int findViewType(@NotNull Class<?> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "clazz");
        Integer num = this.mTypeMap.get(cls);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException(("findViewType: ViewType: " + cls + " Not Find!").toString());
    }

    /* access modifiers changed from: protected */
    public void bindClick(@NotNull BaseViewHolder baseViewHolder) {
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "viewHolder");
        if (getOnItemClickListener() == null) {
            baseViewHolder.itemView.setOnClickListener(new BaseBinderAdapter$bindClick$1(this, baseViewHolder));
        }
        if (getOnItemLongClickListener() == null) {
            baseViewHolder.itemView.setOnLongClickListener(new BaseBinderAdapter$bindClick$2(this, baseViewHolder));
        }
    }

    /* access modifiers changed from: protected */
    public void bindChildClick(@NotNull BaseViewHolder baseViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(baseViewHolder, "viewHolder");
        if (getOnItemChildClickListener() == null) {
            BaseItemBinder<Object, BaseViewHolder> itemBinder = getItemBinder(i);
            for (Number intValue : itemBinder.getChildClickViewIds()) {
                View findViewById = baseViewHolder.itemView.findViewById(intValue.intValue());
                if (findViewById != null) {
                    if (!findViewById.isClickable()) {
                        findViewById.setClickable(true);
                    }
                    findViewById.setOnClickListener(new BaseBinderAdapter$bindChildClick$$inlined$forEach$lambda$1(this, baseViewHolder, itemBinder));
                }
            }
        }
        if (getOnItemChildLongClickListener() == null) {
            BaseItemBinder<Object, BaseViewHolder> itemBinder2 = getItemBinder(i);
            for (Number intValue2 : itemBinder2.getChildLongClickViewIds()) {
                View findViewById2 = baseViewHolder.itemView.findViewById(intValue2.intValue());
                if (findViewById2 != null) {
                    if (!findViewById2.isLongClickable()) {
                        findViewById2.setLongClickable(true);
                    }
                    findViewById2.setOnLongClickListener(new BaseBinderAdapter$bindChildClick$$inlined$forEach$lambda$2(this, baseViewHolder, itemBinder2));
                }
            }
        }
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0017J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u001a\u0010\t\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\n"}, mo21895d2 = {"Lcom/chad/library/adapter/base/BaseBinderAdapter$ItemCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "", "(Lcom/chad/library/adapter/base/BaseBinderAdapter;)V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "getChangePayload", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: BaseBinderAdapter.kt */
    private final class ItemCallback extends DiffUtil.ItemCallback<Object> {
        public ItemCallback() {
        }

        public boolean areItemsTheSame(@NotNull Object obj, @NotNull Object obj2) {
            DiffUtil.ItemCallback itemCallback;
            Intrinsics.checkParameterIsNotNull(obj, "oldItem");
            Intrinsics.checkParameterIsNotNull(obj2, "newItem");
            if (!Intrinsics.areEqual((Object) obj.getClass(), (Object) obj2.getClass()) || (itemCallback = (DiffUtil.ItemCallback) BaseBinderAdapter.this.classDiffMap.get(obj.getClass())) == null) {
                return Intrinsics.areEqual(obj, obj2);
            }
            return itemCallback.areItemsTheSame(obj, obj2);
        }

        @SuppressLint({"DiffUtilEquals"})
        public boolean areContentsTheSame(@NotNull Object obj, @NotNull Object obj2) {
            DiffUtil.ItemCallback itemCallback;
            Intrinsics.checkParameterIsNotNull(obj, "oldItem");
            Intrinsics.checkParameterIsNotNull(obj2, "newItem");
            if (!Intrinsics.areEqual((Object) obj.getClass(), (Object) obj2.getClass()) || (itemCallback = (DiffUtil.ItemCallback) BaseBinderAdapter.this.classDiffMap.get(obj.getClass())) == null) {
                return true;
            }
            return itemCallback.areContentsTheSame(obj, obj2);
        }

        @Nullable
        public Object getChangePayload(@NotNull Object obj, @NotNull Object obj2) {
            DiffUtil.ItemCallback itemCallback;
            Intrinsics.checkParameterIsNotNull(obj, "oldItem");
            Intrinsics.checkParameterIsNotNull(obj2, "newItem");
            if (!Intrinsics.areEqual((Object) obj.getClass(), (Object) obj2.getClass()) || (itemCallback = (DiffUtil.ItemCallback) BaseBinderAdapter.this.classDiffMap.get(obj.getClass())) == null) {
                return null;
            }
            return itemCallback.getChangePayload(obj, obj2);
        }
    }
}
