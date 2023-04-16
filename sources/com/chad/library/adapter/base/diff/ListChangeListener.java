package com.chad.library.adapter.base.diff;

import androidx.annotation.NonNull;
import java.util.List;

public interface ListChangeListener<T> {
    void onCurrentListChanged(@NonNull List<T> list, @NonNull List<T> list2);
}
