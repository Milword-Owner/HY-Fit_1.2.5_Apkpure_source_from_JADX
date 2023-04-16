package com.chad.library.adapter.base.provider;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, mo21895d2 = {"Lcom/chad/library/adapter/base/provider/BaseNodeProvider;", "Lcom/chad/library/adapter/base/provider/BaseItemProvider;", "Lcom/chad/library/adapter/base/entity/node/BaseNode;", "()V", "getAdapter", "Lcom/chad/library/adapter/base/BaseNodeAdapter;", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: BaseNodeProvider.kt */
public abstract class BaseNodeProvider extends BaseItemProvider<BaseNode> {
    @Nullable
    public BaseNodeAdapter getAdapter() {
        BaseProviderMultiAdapter adapter = super.getAdapter();
        if (!(adapter instanceof BaseNodeAdapter)) {
            adapter = null;
        }
        return (BaseNodeAdapter) adapter;
    }
}
