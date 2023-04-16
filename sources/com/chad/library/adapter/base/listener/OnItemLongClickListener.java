package com.chad.library.adapter.base.listener;

import android.view.View;
import androidx.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;

public interface OnItemLongClickListener {
    boolean onItemLongClick(@NonNull BaseQuickAdapter baseQuickAdapter, @NonNull View view, int i);
}
