package com.chad.library.adapter.base.diff;

import androidx.annotation.NonNull;

public interface DifferImp<T> {
    void addListListener(@NonNull ListChangeListener<T> listChangeListener);
}
