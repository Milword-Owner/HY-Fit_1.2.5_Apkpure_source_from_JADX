package com.scwang.smart.refresh.layout.wrapper;

import android.annotation.SuppressLint;
import android.view.View;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.simple.SimpleComponent;

@SuppressLint({"ViewConstructor"})
public class RefreshFooterWrapper extends SimpleComponent implements RefreshFooter {
    public RefreshFooterWrapper(View view) {
        super(view);
    }
}
