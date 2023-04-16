package com.chad.library.adapter.base.listener;

import android.view.View;
import androidx.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;

public interface OnItemClickListener {
    void onItemClick(@NonNull BaseQuickAdapter<?, ?> baseQuickAdapter, @NonNull View view, int i);
}
