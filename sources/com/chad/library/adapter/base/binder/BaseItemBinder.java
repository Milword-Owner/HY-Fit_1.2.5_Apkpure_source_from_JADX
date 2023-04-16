package com.chad.library.adapter.base.binder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import com.chad.library.adapter.base.BaseBinderAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0014\u0010%\u001a\u00020&2\f\b\u0001\u0010'\u001a\u00020(\"\u00020\u0016J\u0014\u0010)\u001a\u00020&2\f\b\u0001\u0010'\u001a\u00020(\"\u00020\u0016J\u001d\u0010*\u001a\u00020&2\u0006\u0010+\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00028\u0000H&¢\u0006\u0002\u0010,J+\u0010*\u001a\u00020&2\u0006\u0010+\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00028\u00002\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00040.H\u0016¢\u0006\u0002\u0010/J\u0016\u00100\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017J\u0016\u00101\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017J-\u00102\u001a\u00020&2\u0006\u0010+\u001a\u00028\u00012\u0006\u00103\u001a\u0002042\u0006\u0010\u001e\u001a\u00028\u00002\u0006\u00105\u001a\u00020\u0016H\u0016¢\u0006\u0002\u00106J-\u00107\u001a\u0002082\u0006\u0010+\u001a\u00028\u00012\u0006\u00103\u001a\u0002042\u0006\u0010\u001e\u001a\u00028\u00002\u0006\u00105\u001a\u00020\u0016H\u0016¢\u0006\u0002\u00109J-\u0010:\u001a\u00020&2\u0006\u0010+\u001a\u00028\u00012\u0006\u00103\u001a\u0002042\u0006\u0010\u001e\u001a\u00028\u00002\u0006\u00105\u001a\u00020\u0016H\u0016¢\u0006\u0002\u00106J\u001d\u0010;\u001a\u00028\u00012\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u0016H&¢\u0006\u0002\u0010?J\u0015\u0010@\u001a\u0002082\u0006\u0010+\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010AJ-\u0010B\u001a\u0002082\u0006\u0010+\u001a\u00028\u00012\u0006\u00103\u001a\u0002042\u0006\u0010\u001e\u001a\u00028\u00002\u0006\u00105\u001a\u00020\u0016H\u0016¢\u0006\u0002\u00109J\u0015\u0010C\u001a\u00020&2\u0006\u0010+\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010DJ\u0015\u0010E\u001a\u00020&2\u0006\u0010+\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010DR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\tR+\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u00178BX\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001c\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u000fR\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\u001f8F¢\u0006\u0006\u001a\u0004\b \u0010!R+\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u00178BX\u0002¢\u0006\f\n\u0004\b$\u0010\u001b\u001a\u0004\b#\u0010\u0019¨\u0006F"}, mo21895d2 = {"Lcom/chad/library/adapter/base/binder/BaseItemBinder;", "T", "VH", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "", "()V", "_adapter", "Lcom/chad/library/adapter/base/BaseBinderAdapter;", "get_adapter$com_github_CymChad_brvah", "()Lcom/chad/library/adapter/base/BaseBinderAdapter;", "set_adapter$com_github_CymChad_brvah", "(Lcom/chad/library/adapter/base/BaseBinderAdapter;)V", "_context", "Landroid/content/Context;", "get_context$com_github_CymChad_brvah", "()Landroid/content/Context;", "set_context$com_github_CymChad_brvah", "(Landroid/content/Context;)V", "adapter", "getAdapter", "clickViewIds", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getClickViewIds", "()Ljava/util/ArrayList;", "clickViewIds$delegate", "Lkotlin/Lazy;", "context", "getContext", "data", "", "getData", "()Ljava/util/List;", "longClickViewIds", "getLongClickViewIds", "longClickViewIds$delegate", "addChildClickViewIds", "", "ids", "", "addChildLongClickViewIds", "convert", "holder", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Ljava/lang/Object;)V", "payloads", "", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Ljava/lang/Object;Ljava/util/List;)V", "getChildClickViewIds", "getChildLongClickViewIds", "onChildClick", "view", "Landroid/view/View;", "position", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Landroid/view/View;Ljava/lang/Object;I)V", "onChildLongClick", "", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Landroid/view/View;Ljava/lang/Object;I)Z", "onClick", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "(Landroid/view/ViewGroup;I)Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "onFailedToRecycleView", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;)Z", "onLongClick", "onViewAttachedToWindow", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;)V", "onViewDetachedFromWindow", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: BaseItemBinder.kt */
public abstract class BaseItemBinder<T, VH extends BaseViewHolder> {
    @Nullable
    private BaseBinderAdapter _adapter;
    @Nullable
    private Context _context;
    private final Lazy clickViewIds$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, BaseItemBinder$clickViewIds$2.INSTANCE);
    private final Lazy longClickViewIds$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, BaseItemBinder$longClickViewIds$2.INSTANCE);

    private final ArrayList<Integer> getClickViewIds() {
        return (ArrayList) this.clickViewIds$delegate.getValue();
    }

    private final ArrayList<Integer> getLongClickViewIds() {
        return (ArrayList) this.longClickViewIds$delegate.getValue();
    }

    public abstract void convert(@NotNull VH vh, T t);

    public void convert(@NotNull VH vh, T t, @NotNull List<? extends Object> list) {
        Intrinsics.checkParameterIsNotNull(vh, "holder");
        Intrinsics.checkParameterIsNotNull(list, "payloads");
    }

    public void onChildClick(@NotNull VH vh, @NotNull View view, T t, int i) {
        Intrinsics.checkParameterIsNotNull(vh, "holder");
        Intrinsics.checkParameterIsNotNull(view, ViewHierarchyConstants.VIEW_KEY);
    }

    public boolean onChildLongClick(@NotNull VH vh, @NotNull View view, T t, int i) {
        Intrinsics.checkParameterIsNotNull(vh, "holder");
        Intrinsics.checkParameterIsNotNull(view, ViewHierarchyConstants.VIEW_KEY);
        return false;
    }

    public void onClick(@NotNull VH vh, @NotNull View view, T t, int i) {
        Intrinsics.checkParameterIsNotNull(vh, "holder");
        Intrinsics.checkParameterIsNotNull(view, ViewHierarchyConstants.VIEW_KEY);
    }

    @NotNull
    public abstract VH onCreateViewHolder(@NotNull ViewGroup viewGroup, int i);

    public boolean onFailedToRecycleView(@NotNull VH vh) {
        Intrinsics.checkParameterIsNotNull(vh, "holder");
        return false;
    }

    public boolean onLongClick(@NotNull VH vh, @NotNull View view, T t, int i) {
        Intrinsics.checkParameterIsNotNull(vh, "holder");
        Intrinsics.checkParameterIsNotNull(view, ViewHierarchyConstants.VIEW_KEY);
        return false;
    }

    public void onViewAttachedToWindow(@NotNull VH vh) {
        Intrinsics.checkParameterIsNotNull(vh, "holder");
    }

    public void onViewDetachedFromWindow(@NotNull VH vh) {
        Intrinsics.checkParameterIsNotNull(vh, "holder");
    }

    @Nullable
    public final BaseBinderAdapter get_adapter$com_github_CymChad_brvah() {
        return this._adapter;
    }

    public final void set_adapter$com_github_CymChad_brvah(@Nullable BaseBinderAdapter baseBinderAdapter) {
        this._adapter = baseBinderAdapter;
    }

    @Nullable
    public final Context get_context$com_github_CymChad_brvah() {
        return this._context;
    }

    public final void set_context$com_github_CymChad_brvah(@Nullable Context context) {
        this._context = context;
    }

    @NotNull
    public final BaseBinderAdapter getAdapter() {
        BaseBinderAdapter baseBinderAdapter = this._adapter;
        if (baseBinderAdapter != null) {
            if (baseBinderAdapter == null) {
                Intrinsics.throwNpe();
            }
            return baseBinderAdapter;
        }
        throw new IllegalStateException(("This " + this + " has not been attached to BaseBinderAdapter yet.\n                    You should not call the method before addItemBinder().").toString());
    }

    @NotNull
    public final Context getContext() {
        Context context = this._context;
        if (context != null) {
            if (context == null) {
                Intrinsics.throwNpe();
            }
            return context;
        }
        throw new IllegalStateException(("This " + this + " has not been attached to BaseBinderAdapter yet.\n                    You should not call the method before onCreateViewHolder().").toString());
    }

    @NotNull
    public final List<Object> getData() {
        return getAdapter().getData();
    }

    @NotNull
    public final ArrayList<Integer> getChildClickViewIds() {
        return getClickViewIds();
    }

    @NotNull
    public final ArrayList<Integer> getChildLongClickViewIds() {
        return getLongClickViewIds();
    }

    public final void addChildClickViewIds(@NotNull @IdRes int... iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "ids");
        for (int valueOf : iArr) {
            getClickViewIds().add(Integer.valueOf(valueOf));
        }
    }

    public final void addChildLongClickViewIds(@NotNull @IdRes int... iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "ids");
        for (int valueOf : iArr) {
            getLongClickViewIds().add(Integer.valueOf(valueOf));
        }
    }
}
