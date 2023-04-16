package com.previewlibrary.loader;

import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;

public interface MySimpleTarget {
    void onLoadFailed(@Nullable Drawable drawable);

    void onResourceReady();
}
