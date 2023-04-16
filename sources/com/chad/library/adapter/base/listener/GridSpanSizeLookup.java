package com.chad.library.adapter.base.listener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

public interface GridSpanSizeLookup {
    int getSpanSize(@NonNull GridLayoutManager gridLayoutManager, int i, int i2);
}
