package com.chad.library.adapter.base.entity.node;

import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo21895d2 = {"Lcom/chad/library/adapter/base/entity/node/BaseExpandNode;", "Lcom/chad/library/adapter/base/entity/node/BaseNode;", "()V", "isExpanded", "", "()Z", "setExpanded", "(Z)V", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: BaseExpandNode.kt */
public abstract class BaseExpandNode extends BaseNode {
    private boolean isExpanded = true;

    public final boolean isExpanded() {
        return this.isExpanded;
    }

    public final void setExpanded(boolean z) {
        this.isExpanded = z;
    }
}
