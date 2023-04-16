package com.scwang.smart.refresh.layout.api;

import androidx.annotation.RestrictTo;

public interface RefreshFooter extends RefreshComponent {
    @RestrictTo({RestrictTo.Scope.LIBRARY, RestrictTo.Scope.LIBRARY_GROUP, RestrictTo.Scope.SUBCLASSES})
    boolean setNoMoreData(boolean z);
}
