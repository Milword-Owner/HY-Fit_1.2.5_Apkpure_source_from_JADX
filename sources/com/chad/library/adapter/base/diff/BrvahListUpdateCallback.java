package com.chad.library.adapter.base.diff;

import androidx.recyclerview.widget.ListUpdateCallback;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0018\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0016J\u0018\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016R\u0016\u0010\u0002\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, mo21895d2 = {"Lcom/chad/library/adapter/base/diff/BrvahListUpdateCallback;", "Landroidx/recyclerview/widget/ListUpdateCallback;", "mAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "(Lcom/chad/library/adapter/base/BaseQuickAdapter;)V", "onChanged", "", "position", "", "count", "payload", "", "onInserted", "onMoved", "fromPosition", "toPosition", "onRemoved", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: BrvahListUpdateCallback.kt */
public final class BrvahListUpdateCallback implements ListUpdateCallback {
    private final BaseQuickAdapter<?, ?> mAdapter;

    public BrvahListUpdateCallback(@NotNull BaseQuickAdapter<?, ?> baseQuickAdapter) {
        Intrinsics.checkParameterIsNotNull(baseQuickAdapter, "mAdapter");
        this.mAdapter = baseQuickAdapter;
    }

    public void onInserted(int i, int i2) {
        BaseQuickAdapter<?, ?> baseQuickAdapter = this.mAdapter;
        baseQuickAdapter.notifyItemRangeInserted(i + baseQuickAdapter.getHeaderLayoutCount(), i2);
    }

    public void onRemoved(int i, int i2) {
        BaseLoadMoreModule mLoadMoreModule$com_github_CymChad_brvah = this.mAdapter.getMLoadMoreModule$com_github_CymChad_brvah();
        if (mLoadMoreModule$com_github_CymChad_brvah != null && mLoadMoreModule$com_github_CymChad_brvah.hasLoadMoreView() && this.mAdapter.getItemCount() == 0) {
            BaseQuickAdapter<?, ?> baseQuickAdapter = this.mAdapter;
            baseQuickAdapter.notifyItemRangeRemoved(i + baseQuickAdapter.getHeaderLayoutCount(), i2 + 1);
            return;
        }
        BaseQuickAdapter<?, ?> baseQuickAdapter2 = this.mAdapter;
        baseQuickAdapter2.notifyItemRangeRemoved(i + baseQuickAdapter2.getHeaderLayoutCount(), i2);
    }

    public void onMoved(int i, int i2) {
        BaseQuickAdapter<?, ?> baseQuickAdapter = this.mAdapter;
        baseQuickAdapter.notifyItemMoved(i + baseQuickAdapter.getHeaderLayoutCount(), i2 + this.mAdapter.getHeaderLayoutCount());
    }

    public void onChanged(int i, int i2, @Nullable Object obj) {
        BaseQuickAdapter<?, ?> baseQuickAdapter = this.mAdapter;
        baseQuickAdapter.notifyItemRangeChanged(i + baseQuickAdapter.getHeaderLayoutCount(), i2, obj);
    }
}
